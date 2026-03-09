#!/usr/bin/env python
"""Helper CLI that wraps CLI service layer for Claude Skills.

Claude 通过 SKILL.md 中的 Bash 指令调用这些子命令。
所有输出均为 JSON 格式（stdout），错误也以 {"ok": false, "error": "..."} 返回。
"""

from __future__ import annotations

from collections.abc import Callable, Mapping
from datetime import datetime
import json
import os
import sys
from typing import Any, NoReturn
from zoneinfo import ZoneInfo, ZoneInfoNotFoundError

import click
from dotenv import load_dotenv

# 将项目根目录加入 sys.path，确保直接执行时也能正确导入 cli/ 模块
sys.path.insert(0, str(__import__("pathlib").Path(__file__).resolve().parents[2]))

from cli.types import UserInfo

load_dotenv()


def _sanitize_user_info(user: UserInfo) -> UserInfo:
    """Return a JSON-safe user dict with sensitive fields removed."""
    safe_user: UserInfo = {}

    name = user.get("name")
    if name:
        safe_user["name"] = name

    real_name = user.get("realName")
    if real_name:
        safe_user["realName"] = real_name

    username = user.get("username")
    if username:
        safe_user["username"] = username

    mobile = user.get("mobile")
    if mobile:
        safe_user["mobile"] = mobile

    email = user.get("email")
    if email:
        safe_user["email"] = email

    org_name = user.get("orgName")
    if org_name:
        safe_user["orgName"] = org_name

    user_type = user.get("userType")
    if user_type:
        safe_user["userType"] = user_type

    return safe_user


def _json_out(data: Mapping[str, object]) -> None:
    """将字典序列化为 JSON 输出到 stdout。"""
    click.echo(json.dumps(data, ensure_ascii=False))


def _error_out(message: str) -> NoReturn:
    """输出错误 JSON 并以非零状态码退出。"""
    _json_out({"ok": False, "error": message})
    sys.exit(1)


def _auto_login() -> str | None:
    """Attempt login using ZUEB_USERNAME / ZUEB_PASSWORD from environment.

    Returns the new id_token on success, or None on any failure (missing env
    vars, wrong credentials, network error, etc.).
    """
    from cli.auth.login import login as do_login

    username = os.environ.get("ZUEB_USERNAME", "")
    password = os.environ.get("ZUEB_PASSWORD", "")
    if not username or not password:
        return None
    try:
        result = do_login(username, password)
        return result["id_token"]
    except Exception:
        return None


def _ensure_session() -> str:
    """Return a valid id_token, transparently auto-logging in if no session exists.

    Exits via _error_out only if both the stored session and auto-login fail.
    """
    from cli.auth.token import load_session

    session = load_session()
    if session and session.get("id_token"):
        return session["id_token"]

    token = _auto_login()
    if token:
        return token

    _error_out("当前未登录，且自动登录失败，请先执行 /login <学号或工号> <密码>。")


def _is_sso_error(error_msg: str) -> bool:
    """Return True if the error message indicates an SSO / token-expiry failure."""
    sso_keywords = (
        "SSO authentication failed",
        "SSO redirect request failed",
        "CAS login",
        "CAS did not return redirect",
        "No ticket in CAS redirect",
        "JWXT",
        "userCode/md5Str not found",
        "id_token is required",
    )
    return any(kw in error_msg for kw in sso_keywords)


def _run_with_relogin(
    fn: Callable[[str], Any],
    id_token: str,
    error_type: type[Exception],
) -> Any:
    """Call fn(id_token), retrying once with a fresh token on SSO-related errors.

    Non-SSO errors from the first call are re-raised so the caller can handle
    them with its own except clauses.  Any failure on the retry call exits via
    _error_out.
    """
    try:
        return fn(id_token)
    except error_type as exc:
        if not _is_sso_error(str(exc)):
            raise
        new_token = _auto_login()
        if not new_token:
            _error_out(f"SSO 认证失败，自动重登录也失败，请手动 /login。原始错误：{exc}")
        try:
            return fn(new_token)
        except error_type as exc2:
            _error_out(str(exc2))


@click.group()
def cli() -> None:
    """ZUEB helper — JSON interface for Claude Skills."""


@cli.command()
@click.argument("username", default="", required=False)
@click.argument("password", default="", required=False)
def login(username: str, password: str) -> None:
    """Login with username and password (falls back to env vars if omitted)."""
    from cli.auth.login import LoginError, MFARequiredError, login as do_login

    if not username:
        username = os.environ.get("ZUEB_USERNAME", "")
    if not password:
        password = os.environ.get("ZUEB_PASSWORD", "")
    if not username or not password:
        _error_out("未提供账号或密码，且环境变量 ZUEB_USERNAME / ZUEB_PASSWORD 未设置。")

    try:
        result = do_login(username, password)
    except MFARequiredError as exc:
        _error_out(f"该账号需要 MFA，当前不支持：{exc}")
    except LoginError as exc:
        _error_out(f"登录失败：{exc}")
    except Exception as exc:
        _error_out(f"登录异常：{exc}")

    # 过滤敏感字段（密码、token 等），防止通过 JSON 输出泄露
    safe_user = _sanitize_user_info(result["user"])
    _json_out({"ok": True, "message": "登录成功", "username": username, "user": safe_user})


@cli.command()
def status() -> None:
    """Show current login status."""
    from cli.auth.token import load_session

    # 从本地会话文件读取登录状态
    session = load_session()
    if not session or not session.get("id_token"):
        _json_out({"ok": True, "logged_in": False, "message": "未登录"})
        return

    _json_out({
        "ok": True,
        "logged_in": True,
        "username": session.get("username", ""),
        "device_id": session.get("device_id", ""),
    })


@cli.command()
def logout() -> None:
    """Clear current session."""
    from cli.auth.token import clear_session

    clear_session()
    _json_out({"ok": True, "message": "已退出登录"})


@cli.command()
@click.option("--semester", default=None, help="Semester code, e.g. 20250")
@click.option("--year", default=None, type=int, help="Academic year start, e.g. 2025")
@click.option("--term", default=None, type=int, help="1 or 2")
@click.option("--week", default=None, type=int, help="Week number")
@click.option("--list", "list_semesters", is_flag=True, help="List available semesters")
def schedule(
    semester: str | None,
    year: int | None,
    term: int | None,
    week: int | None,
    list_semesters: bool,
) -> None:
    """Query course schedule."""
    from cli.schedule.service import ScheduleError, get_available_semesters, get_schedule

    id_token = _ensure_session()

    try:
        # --list 模式：仅返回可选学期列表
        if list_semesters:
            semesters = _run_with_relogin(get_available_semesters, id_token, ScheduleError)
            _json_out({"ok": True, "semesters": semesters})
            return

        data = _run_with_relogin(
            lambda tok: get_schedule(tok, semester, year, term, week),
            id_token,
            ScheduleError,
        )
        _json_out({"ok": True, "schedule": data})
    except ScheduleError as exc:
        _error_out(str(exc))
    except Exception as exc:
        _error_out(f"课表查询异常：{exc}")


@cli.command()
def attendance() -> None:
    """Query today's attendance status."""
    from cli.attendance.service import AttendanceError, get_attendance_status

    id_token = _ensure_session()

    try:
        data = _run_with_relogin(get_attendance_status, id_token, AttendanceError)
        _json_out({"ok": True, "attendance": data})
    except AttendanceError as exc:
        _error_out(str(exc))
    except Exception as exc:
        _error_out(f"考勤查询异常：{exc}")


@cli.command("attendance-punch")
@click.option(
    "--mode",
    type=click.Choice(["auto", "sbk", "xbk"]),
    default="auto",
    show_default=True,
    help="Punch mode: auto / sbk / xbk",
)
@click.option("--xy", default=None, help="Attendance coordinates in 'lng,lat' format")
@click.option("--confirm", default=None, help='Type "yes" to confirm actual punch submission')
def attendance_punch(mode: str, xy: str | None, confirm: str | None) -> None:
    """Submit today's attendance punch."""
    from cli.attendance.service import AttendanceError, punch_attendance

    if confirm != "yes":
        _error_out('执行打卡前必须显式传入 --confirm yes。')

    id_token = _ensure_session()

    try:
        data = _run_with_relogin(
            lambda tok: punch_attendance(tok, mode=mode, xy=xy),
            id_token,
            AttendanceError,
        )
        _json_out({"ok": True, **data})
    except AttendanceError as exc:
        _error_out(str(exc))
    except Exception as exc:
        _error_out(f"打卡提交异常：{exc}")


@cli.command("datetime")
@click.option(
    "--timezone",
    "timezone_name",
    default="Asia/Shanghai",
    show_default=True,
    help="IANA timezone, e.g. Asia/Shanghai",
)
def datetime_info(timezone_name: str) -> None:
    """Query current date/time with deterministic weekday."""
    try:
        timezone = ZoneInfo(timezone_name)
    except ZoneInfoNotFoundError:
        _error_out(f"无效时区：{timezone_name}")
    except Exception as exc:
        _error_out(f"时区解析失败：{exc}")

    now = datetime.now(timezone)
    weekday_cn = [
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
        "星期日",
    ][now.weekday()]
    _json_out({
        "ok": True,
        "timezone": timezone_name,
        "iso_datetime": now.isoformat(timespec="seconds"),
        "date": now.strftime("%Y-%m-%d"),
        "time": now.strftime("%H:%M:%S"),
        "weekday_index": now.isoweekday(),
        "weekday_cn": weekday_cn,
        "weekday_en": now.strftime("%A"),
    })


@cli.command()
@click.option("--date", "date_str", default=None, help="Date in YYYY-MM-DD format (defaults to today in Asia/Shanghai)")
def workday(date_str: str | None) -> None:
    """Check if a date is a Chinese workday or holiday."""
    import chinese_calendar
    from datetime import date as date_cls

    if date_str:
        try:
            target_date = date_cls.fromisoformat(date_str)
        except ValueError:
            _error_out(f"无效日期格式：{date_str}，请使用 YYYY-MM-DD。")
    else:
        target_date = datetime.now(ZoneInfo("Asia/Shanghai")).date()

    try:
        is_holiday, holiday_name = chinese_calendar.get_holiday_detail(target_date)
    except NotImplementedError:
        _error_out(f"暂无 {target_date.year} 年的节假日数据，请更新 chinesecalendar 库。")
    except Exception as exc:
        _error_out(f"节假日查询异常：{exc}")

    weekday = target_date.isoweekday()  # 1=Mon … 7=Sun
    weekday_cn = ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"][weekday - 1]

    if is_holiday and holiday_name:
        day_type = "holiday"
    elif is_holiday:
        day_type = "weekend"
    elif holiday_name:
        day_type = "adjusted_workday"  # weekend made a workday to compensate for holiday
    else:
        day_type = "workday"

    _json_out({
        "ok": True,
        "date": target_date.isoformat(),
        "weekday_cn": weekday_cn,
        "is_workday": not is_holiday,
        "type": day_type,
        "holiday_name": holiday_name,
    })


@cli.command()
def wol() -> None:
    """Wake desktop PC via Wake-on-LAN through OpenWrt router."""
    import subprocess

    try:
        result = subprocess.run(
            ["ssh", "openwrt", "etherwake", "-i", "br-lan", "10:FF:E0:AC:62:5D"],
            capture_output=True,
            text=True,
            timeout=10,
        )
    except subprocess.TimeoutExpired:
        _error_out("SSH 连接超时，请检查路由器是否可达。")
    except FileNotFoundError:
        _error_out("未找到 ssh 命令。")
    except Exception as exc:
        _error_out(f"执行异常：{exc}")

    if result.returncode != 0:
        stderr = result.stderr.strip()
        _error_out(f"唤醒失败：{stderr or '未知错误'}")

    _json_out({"ok": True, "message": "魔术包已发送，台式机正在启动。"})


if __name__ == "__main__":
    cli()
