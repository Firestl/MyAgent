#!/usr/bin/env python
"""Helper CLI that wraps CLI service layer for Claude Skills.

Claude invokes these subcommands via Bash from SKILL.md instructions.
All output is JSON to stdout; errors are {"ok": false, "error": "..."}.
"""

from __future__ import annotations

import json
import sys

import click

# Ensure project root is importable when invoked directly.
sys.path.insert(0, str(__import__("pathlib").Path(__file__).resolve().parents[2]))


def _json_out(data: dict) -> None:
    click.echo(json.dumps(data, ensure_ascii=False))


def _error_out(message: str) -> None:
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

    user = result.get("user") if isinstance(result.get("user"), dict) else {}
    safe_user = {
        k: v
        for k, v in user.items()
        if k.lower() not in {"password", "idtoken", "token", "authorization"}
    }
    _json_out({"ok": True, "message": "登录成功", "username": username, "user": safe_user})


@cli.command()
def status() -> None:
    """Show current login status."""
    from cli.auth.token import load_session

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
        _error_out("当前未登录，请先执行 /login <学号或工号> <密码>。")

    id_token = session["id_token"]

    try:
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
        _error_out("当前未登录，请先执行 /login <学号或工号> <密码>。")

    id_token = session["id_token"]

    try:
        data = get_attendance_status(id_token)
        _json_out({"ok": True, "attendance": data})
    except AttendanceError as exc:
        _error_out(str(exc))
    except Exception as exc:
        _error_out(f"考勤查询异常：{exc}")


if __name__ == "__main__":
    cli()
