import click
from dotenv import load_dotenv

from cli.attendance.service import AttendanceError, get_attendance_status
from cli.auth.login import LoginError, MFARequiredError, login
from cli.auth.token import clear_session, load_session
from cli.schedule.service import ScheduleError, get_available_semesters, get_schedule

load_dotenv()


@click.group()
def cli():
    """ZUEB CLI — command-line interface for Zhengzhou University of Economics and Business."""


@cli.command(name="login")
@click.option("-u", "--username", envvar="ZUEB_USERNAME", required=True, help="Employee/student ID (工号/学号)")
@click.option("-p", "--password", envvar="ZUEB_PASSWORD", default=None, help="Password (prompted if omitted)")
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
    session = load_session()
    if session is None:
        click.echo("Not logged in. Please run login first.", err=True)
        raise SystemExit(1)

    id_token = session.get("id_token", "")
    if not id_token:
        click.echo("Session is missing id_token. Please login again.", err=True)
        raise SystemExit(1)

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
@click.option("--semester", "semester_code", default=None, help="Semester code, e.g. 20250 or 20251")
@click.option("--year", type=int, default=None, help="Academic year start, e.g. 2025 for 2025-2026")
@click.option("--term", type=click.Choice(["1", "2"]), default=None, help="Term number: 1 or 2")
@click.option("--list-semesters", is_flag=True, help="List selectable semesters and exit")
def schedule(semester_code, year, term, list_semesters):
    """Show current semester course schedule."""
    session = load_session()
    if session is None:
        click.echo("Not logged in. Please run login first.", err=True)
        raise SystemExit(1)

    id_token = session.get("id_token", "")
    if not id_token:
        click.echo("Session is missing id_token. Please login again.", err=True)
        raise SystemExit(1)

    if semester_code and (year is not None or term is not None):
        click.echo("Use either --semester or --year/--term, not both.", err=True)
        raise SystemExit(1)
    if (year is None) != (term is None):
        click.echo("--year and --term must be provided together.", err=True)
        raise SystemExit(1)

    if list_semesters:
        click.echo("Fetching semester list...")
        try:
            semesters = get_available_semesters(id_token)
        except ScheduleError as e:
            click.echo(f"Schedule query failed: {e}", err=True)
            raise SystemExit(1)
        _print_semester_list(semesters)
        return

    click.echo("Fetching course schedule...")
    try:
        data = get_schedule(
            id_token,
            semester_code=semester_code,
            year=year,
            term=int(term) if term is not None else None,
        )
    except ScheduleError as e:
        click.echo(f"Schedule query failed: {e}", err=True)
        raise SystemExit(1)

    _print_schedule(data)


def _print_schedule(data: dict) -> None:
    """Format and print course schedule."""
    xn = data.get("xn", "")
    xq = data.get("xq", "")
    xq_name = "第一学期" if xq == "0" else "第二学期"
    zc = data.get("zc", "")
    qssj = data.get("qssj", "")
    jssj = data.get("jssj", "")

    click.echo(f"\n{xn}-{int(xn)+1}学年 {xq_name}  (第{zc}周，{qssj} ~ {jssj})\n")

    weekdays = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
    has_any = False

    for i, day in enumerate(weekdays, 1):
        courses = data.get(f"week{i}", [])
        if not courses:
            continue
        has_any = True
        click.echo(f"  {day}:")
        for c in courses:
            name = c.get("kcmc", "")
            room = c.get("skdd", "")
            teacher = c.get("rkjs", "")
            periods = c.get("jcxx", "")   # e.g., "7-8"
            weeks = c.get("skzs", "")     # e.g., "1-5周"
            campus = c.get("xq", "")

            line = f"    {periods}节  {name}"
            if room:
                line += f"  {room}"
            if teacher:
                line += f"  [{teacher}]"
            if weeks:
                line += f"  ({weeks})"
            if campus and campus != "主校区":
                line += f"  @{campus}"
            click.echo(line)

    if not has_any:
        click.echo("  (本周无课)")

    # Print practical session info
    sjhj = data.get("sjhjinfo", [])
    if sjhj:
        click.echo("\n实践环节:")
        for item in sjhj:
            click.echo(f"  - {item.get('value', '')}")


def _print_semester_list(semesters: list[dict]) -> None:
    """Print selectable semesters from getxnxq_xl."""
    if not semesters:
        click.echo("No semester data returned.")
        return

    click.echo("\nAvailable semesters:")
    for item in semesters:
        dm = str(item.get("dm", ""))
        mc = str(item.get("mc", ""))
        current = "  [current]" if str(item.get("dqxq", "")) == "1" else ""
        click.echo(f"  {dm:>6}  {mc}{current}")
