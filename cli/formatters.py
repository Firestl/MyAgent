"""Display formatting helpers for CLI output."""

import click

from cli.types import ScheduleData, SemesterItem


def print_schedule(data: ScheduleData) -> None:
    """Render a /wap/mycourseschedule.action response for terminal output."""
    xn = data.get("xn", "")
    xq = data.get("xq", "")
    # JWXT payload convention: xq "0"=第一学期, "1"=第二学期.
    xq_name = "第一学期" if xq == "0" else "第二学期"
    zc = data.get("zc", "")
    qssj = data.get("qssj", "")
    jssj = data.get("jssj", "")

    click.echo(f"\n{xn}-{int(xn) + 1}学年 {xq_name}  (第{zc}周，{qssj} ~ {jssj})\n")

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
            periods = c.get("jcxx", "")  # e.g., "7-8"
            weeks = c.get("skzs", "")  # e.g., "1-5周"
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


def print_semester_list(semesters: list[SemesterItem]) -> None:
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
