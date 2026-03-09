"""Tests for cli/formatters.py — print_schedule, print_semester_list."""

from __future__ import annotations

from cli.formatters import print_schedule, print_semester_list
from cli.types import CourseItem, ScheduleData, SemesterItem


def _capture(capsys, fn, *args):
    """执行格式化函数并返回其标准输出，供断言展示文本使用。"""
    """执行函数并返回捕获到的标准输出文本。"""
    fn(*args)
    return capsys.readouterr().out


# ---------------------------------------------------------------------------
# print_schedule
# ---------------------------------------------------------------------------

def test_schedule_with_courses(capsys) -> None:
    """测试课表输出会展示课程名、地点、教师和学期标题。"""
    data: ScheduleData = {
        "xn": "2025",
        "xq": "0",
        "zc": "3",
        "qssj": "03-03",
        "jssj": "03-09",
        "week1": [
            {"kcmc": "高等数学", "skdd": "A101", "rkjs": "张老师", "jcxx": "1-2", "skzs": "1-16周", "xq": "主校区"},
        ],
    }
    out = _capture(capsys, print_schedule, data)
    assert "高等数学" in out
    assert "A101" in out
    assert "张老师" in out
    assert "第一学期" in out


def test_schedule_empty_week(capsys) -> None:
    """测试整周无课时输出明确的无课提示。"""
    data: ScheduleData = {
        "xn": "2025",
        "xq": "0",
        "zc": "3",
        "qssj": "03-03",
        "jssj": "03-09",
    }
    out = _capture(capsys, print_schedule, data)
    assert "(本周无课)" in out


def test_non_main_campus_shows_marker(capsys) -> None:
    """测试非主校区课程会在输出中标注校区。"""
    # 非主校区应显示 @校区名。
    data: ScheduleData = {
        "xn": "2025",
        "xq": "0",
        "zc": "1",
        "qssj": "02-17",
        "jssj": "02-23",
        "week1": [{"kcmc": "英语", "xq": "南校区"}],
    }
    out = _capture(capsys, print_schedule, data)
    assert "@南校区" in out


def test_main_campus_hidden(capsys) -> None:
    """测试主校区课程不会额外输出校区标记。"""
    # 主校区不输出 @ 标记。
    data: ScheduleData = {
        "xn": "2025",
        "xq": "0",
        "zc": "1",
        "qssj": "02-17",
        "jssj": "02-23",
        "week1": [{"kcmc": "体育", "xq": "主校区"}],
    }
    out = _capture(capsys, print_schedule, data)
    assert "@" not in out


def test_second_semester(capsys) -> None:
    """测试 xq=1 时标题显示第二学期。"""
    data: ScheduleData = {
        "xn": "2025",
        "xq": "1",
        "zc": "1",
        "qssj": "02-17",
        "jssj": "02-23",
    }
    out = _capture(capsys, print_schedule, data)
    assert "第二学期" in out


def test_practical_session(capsys) -> None:
    """测试实践环节信息会以单独区块输出。"""
    data: ScheduleData = {
        "xn": "2025",
        "xq": "0",
        "zc": "1",
        "qssj": "02-17",
        "jssj": "02-23",
        "sjhjinfo": [{"value": "毕业实习"}],
    }
    out = _capture(capsys, print_schedule, data)
    assert "实践环节:" in out
    assert "毕业实习" in out


# ---------------------------------------------------------------------------
# print_semester_list
# ---------------------------------------------------------------------------

def test_semester_list_with_current(capsys) -> None:
    """测试学期列表输出会标记当前学期。"""
    semesters: list[SemesterItem] = [
        {"dm": "20250", "mc": "2025-2026学年第一学期", "dqxq": "1"},
        {"dm": "20241", "mc": "2024-2025学年第二学期", "dqxq": "0"},
    ]
    out = _capture(capsys, print_semester_list, semesters)
    assert "[current]" in out
    assert "20250" in out
    assert "20241" in out


def test_semester_list_empty(capsys) -> None:
    """测试空学期列表时输出统一提示语。"""
    out = _capture(capsys, print_semester_list, [])
    assert "No semester data returned." in out
