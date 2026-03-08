#!/usr/bin/env python
"""Helper CLI that wraps CLI service layer for Claude Skills.

Claude 通过 SKILL.md 中的 Bash 指令调用这些子命令。
所有输出均为 JSON 格式（stdout），错误也以 {"ok": false, "error": "..."} 返回。
"""

from __future__ import annotations

from collections.abc import Mapping
from datetime import datetime
import json
import sys
from typing import NoReturn
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


@click.group()
def cli() -> None:
    """ZUEB helper — JSON interface for Claude Skills."""


@cli.command()
@click.argument("username")
@click.argument("password")
def login(username: str, password: str) -> None:
    """Login with username and password."""
    from cli.auth.login import LoginError, MFARequiredError, login as do_login

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
    from cli.auth.token import load_session
    from cli.schedule.service import ScheduleError, get_available_semesters, get_schedule

    session = load_session()
    if not session or not session.get("id_token"):
        _error_out("当前未登录，请手动更新认证文件后重试。")

    id_token = session["id_token"]

    try:
        # --list 模式：仅返回可选学期列表
        if list_semesters:
            semesters = get_available_semesters(id_token)
            _json_out({"ok": True, "semesters": semesters})
            return

        data = get_schedule(id_token, semester, year, term, week)
        _json_out({"ok": True, "schedule": data})
    except ScheduleError as exc:
        _error_out(str(exc))
    except Exception as exc:
        _error_out(f"课表查询异常：{exc}")


@cli.command()
def attendance() -> None:
    """Query today's attendance status."""
    from cli.auth.token import load_session
    from cli.attendance.service import AttendanceError, get_attendance_status

    session = load_session()
    if not session or not session.get("id_token"):
        _error_out("当前未登录，请手动更新认证文件后重试。")

    id_token = session["id_token"]

    try:
        data = get_attendance_status(id_token)
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
    from cli.auth.token import load_session
    from cli.attendance.service import AttendanceError, punch_attendance

    if confirm != "yes":
        _error_out('执行打卡前必须显式传入 --confirm yes。')

    session = load_session()
    if not session or not session.get("id_token"):
        _error_out("当前未登录，请手动更新认证文件后重试。")

    id_token = session["id_token"]

    try:
        data = punch_attendance(id_token, mode=mode, xy=xy)
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
