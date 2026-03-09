"""Tests for bot/scheduler/utils.py — parse_time, next_run_at, get_retry_delay."""

from __future__ import annotations

from datetime import datetime, timedelta
from zoneinfo import ZoneInfo

import pytest

from bot.scheduler.utils import get_retry_delay, next_run_at, parse_time


# ---------------------------------------------------------------------------
# parse_time
# ---------------------------------------------------------------------------

def test_parse_time_normal() -> None:
    """测试标准 HH:MM 字符串可解析为时分元组。"""
    assert parse_time("07:30") == (7, 30)


def test_parse_time_midnight() -> None:
    """测试午夜时间字符串可被正确解析。"""
    assert parse_time("00:00") == (0, 0)


def test_parse_time_end_of_day() -> None:
    """测试一天最后一分钟可被正确解析。"""
    assert parse_time("23:59") == (23, 59)


def test_parse_time_missing_colon_raises() -> None:
    """测试缺少冒号分隔符的时间字符串会抛错。"""
    with pytest.raises(ValueError):
        parse_time("0730")


def test_parse_time_non_numeric_raises() -> None:
    """测试非数字时分字符串会抛错。"""
    with pytest.raises(ValueError):
        parse_time("ab:cd")


# ---------------------------------------------------------------------------
# next_run_at
# ---------------------------------------------------------------------------

_TZ = ZoneInfo("Asia/Shanghai")


def test_next_run_at_today_not_yet(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试目标时间尚未到达时，next_run_at 返回今天的触发时间。"""
    # now=07:00，target=08:00 → 应该是今天。
    fake_now = datetime(2025, 6, 15, 7, 0, 0, tzinfo=_TZ)
    monkeypatch.setattr(
        "bot.scheduler.utils.datetime",
        _FakeDatetime(fake_now),
    )
    result = next_run_at(8, 0, _TZ)
    assert result.day == 15
    assert result.hour == 8 and result.minute == 0


def test_next_run_at_already_passed(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试目标时间已过时，next_run_at 推到次日。"""
    # now=09:00，target=08:00 → 应该推到明天。
    fake_now = datetime(2025, 6, 15, 9, 0, 0, tzinfo=_TZ)
    monkeypatch.setattr(
        "bot.scheduler.utils.datetime",
        _FakeDatetime(fake_now),
    )
    result = next_run_at(8, 0, _TZ)
    assert result.day == 16


def test_next_run_at_exact_now(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试目标时间等于当前时间时也会顺延到明天。"""
    # now 恰好等于 target（<= 判断），应推到明天。
    fake_now = datetime(2025, 6, 15, 8, 0, 0, tzinfo=_TZ)
    monkeypatch.setattr(
        "bot.scheduler.utils.datetime",
        _FakeDatetime(fake_now),
    )
    result = next_run_at(8, 0, _TZ)
    assert result.day == 16


def test_next_run_at_midnight_boundary(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试跨午夜场景会正确落到次日零点。"""
    # now=23:59，target=00:00 → 推到明天 00:00。
    fake_now = datetime(2025, 6, 15, 23, 59, 0, tzinfo=_TZ)
    monkeypatch.setattr(
        "bot.scheduler.utils.datetime",
        _FakeDatetime(fake_now),
    )
    result = next_run_at(0, 0, _TZ)
    assert result.day == 16 and result.hour == 0


def test_next_run_at_preserves_timezone(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试计算出的触发时间保留传入时区信息。"""
    tz = ZoneInfo("UTC")
    fake_now = datetime(2025, 6, 15, 7, 0, 0, tzinfo=tz)
    monkeypatch.setattr(
        "bot.scheduler.utils.datetime",
        _FakeDatetime(fake_now),
    )
    result = next_run_at(8, 0, tz)
    assert result.tzinfo is not None
    assert str(result.tzinfo) == "UTC"


# ---------------------------------------------------------------------------
# get_retry_delay
# ---------------------------------------------------------------------------

def test_retry_delay_attempt_0() -> None:
    """测试首次重试使用最短延迟。"""
    assert get_retry_delay(0) == 60


def test_retry_delay_attempt_1() -> None:
    """测试第二次尝试使用中间档延迟。"""
    assert get_retry_delay(1) == 180


def test_retry_delay_attempt_2_and_beyond() -> None:
    """测试第三次及之后的尝试都被钳制到最大延迟。"""
    assert get_retry_delay(2) == 300
    assert get_retry_delay(5) == 300
    assert get_retry_delay(99) == 300


# ---------------------------------------------------------------------------
# Helper: 模拟 datetime.now() 同时保留 replace() / timedelta 行为
# ---------------------------------------------------------------------------

class _FakeDatetime(datetime):
    """用于 monkeypatch datetime 模块的子类——只覆盖 now()，
    其余 replace()、__sub__、__add__ 等继续走真实 datetime 逻辑。"""

    def __init__(self, fixed_now: datetime) -> None:
        """保存固定的当前时间，供 now() 返回。"""
        # __init__ 仅存储 fixed_now 供 now() 使用，
        # datetime 本身是不可变的，真正的构造走 __new__。
        self._fixed_now = fixed_now

    def __new__(cls, fixed_now: datetime):  # type: ignore[override]
        """创建可供 monkeypatch 使用的 datetime 子类实例。"""
        # 我们不直接当 datetime 实例使用，所以随便给一个值即可。
        instance = super().__new__(cls, 2000, 1, 1)
        return instance

    def now(self, tz=None):  # noqa: ANN001
        """返回预设时间，替代真实的 datetime.now()。"""
        return self._fixed_now
