import click
from dotenv import load_dotenv

from cli.attendance.service import AttendanceError, get_attendance_status
from cli.auth.login import LoginError, MFARequiredError, login
from cli.auth.token import clear_session, load_session
from cli.formatters import print_schedule, print_semester_list
from cli.schedule.service import ScheduleError, get_available_semesters, get_schedule

load_dotenv()


def _require_session() -> dict:
    """Return current session or exit with an error message."""
    session = load_session()
    if session is None:
        click.echo("Not logged in. Please run login first.", err=True)
        raise SystemExit(1)

    if not session.get("id_token", ""):
        click.echo("Session is missing id_token. Please login again.", err=True)
        raise SystemExit(1)

    return session


@click.group()
def cli():
    """ZUEB CLI — command-line interface for Zhengzhou University of Economics and Business."""


@cli.command(name="login")
@click.option(
    "-u",
    "--username",
    envvar="ZUEB_USERNAME",
    required=True,
    help="Employee/student ID (工号/学号)",
)
@click.option(
    "-p",
    "--password",
    envvar="ZUEB_PASSWORD",
    default=None,
    help="Password (prompted if omitted)",
)
def login_cmd(username, password):
    """Login with employee/student ID and password."""
    if password is None:
        password = click.prompt("Password", hide_input=True)

    click.echo("Logging in...")
    try:
        result = login(username, password)
    except MFARequiredError as e:
        click.echo(f"MFA required: {e}", err=True)
        raise SystemExit(1)
    except LoginError as e:
        click.echo(f"Login failed: {e}", err=True)
        raise SystemExit(1)

    click.echo("Login successful!")

    # Display token to verify authentication
    token = result.get("id_token", "")
    if token:
        click.echo(f"Token: {token[:20]}...{token[-10:]}")

    user = result.get("user") or {}
    if user:
        name = user.get("name") or user.get("realName") or user.get("username") or ""
        if name:
            click.echo(f"Welcome, {name}")
        # Print any extra user fields that are available
        for key in ("mobile", "email", "orgName", "userType"):
            val = user.get(key)
            if val:
                click.echo(f"  {key}: {val}")


@cli.command()
def status():
    """Show current session information."""
    session = load_session()
    if session is None:
        click.echo("Not logged in.")
        return
    click.echo(f"Logged in as: {session.get('username', '(unknown)')}")
    click.echo(f"Device ID:    {session.get('device_id', '(unknown)')}")
    token = session.get("id_token", "")
    if token:
        click.echo(f"Token:        {token[:20]}...{token[-10:]}")


@cli.command()
def logout():
    """Clear saved session."""
    session = load_session()
    if session is None:
        click.echo("Not logged in.")
        return
    clear_session()
    click.echo(f"Logged out (was: {session.get('username', '(unknown)')})")


@cli.command()
def attendance():
    """Show today's attendance card status."""
    session = _require_session()
    id_token = session["id_token"]

    click.echo("Fetching attendance status...")
    try:
        result = get_attendance_status(id_token)
    except AttendanceError as e:
        click.echo(f"Attendance query failed: {e}", err=True)
        raise SystemExit(1)

    sbk_time = result.get("sbk_time") or "--:--"
    xbk_time = result.get("xbk_time") or "--:--"
    sbk_mark = "✓" if result.get("sbk_done") else "✗"
    xbk_mark = "✓" if result.get("xbk_done") else "✗"

    click.echo("Attendance Status:")
    click.echo(f"  上班卡 (Clock-in):  {sbk_time}  {sbk_mark}")
    click.echo(f"  下班卡 (Clock-out): {xbk_time}  {xbk_mark}")


@cli.command()
@click.option(
    "--semester",
    "semester_code",
    default=None,
    help="Semester code, e.g. 20250 or 20251",
)
@click.option(
    "--year",
    type=int,
    default=None,
    help="Academic year start, e.g. 2025 for 2025-2026",
)
@click.option(
    "--term", type=click.Choice(["1", "2"]), default=None, help="Term number: 1 or 2"
)
@click.option(
    "--week", type=click.IntRange(min=1), default=None, help="Week number, e.g. 1"
)
@click.option(
    "--list-semesters", is_flag=True, help="List selectable semesters and exit"
)
def schedule(semester_code, year, term, week, list_semesters):
    """Show current semester course schedule."""
    session = _require_session()
    id_token = session["id_token"]

    if list_semesters:
        click.echo("Fetching semester list...")
        try:
            semesters = get_available_semesters(id_token)
        except ScheduleError as e:
            click.echo(f"Schedule query failed: {e}", err=True)
            raise SystemExit(1)
        print_semester_list(semesters)
        return

    click.echo("Fetching course schedule...")
    try:
        data = get_schedule(
            id_token,
            semester_code=semester_code,
            year=year,
            term=int(term) if term is not None else None,
            week=week,
        )
    except ScheduleError as e:
        click.echo(f"Schedule query failed: {e}", err=True)
        raise SystemExit(1)

    print_schedule(data)
