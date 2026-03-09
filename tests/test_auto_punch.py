from __future__ import annotations

from unittest.mock import AsyncMock, Mock

import pytest
from bot.scheduler.auto_punch import AutoPunchScheduler
from bot.scheduler.cancel_gate import CancelGate
from cli.attendance.service import AttendanceError


async def _immediate_to_thread(func, /, *args, **kwargs):
    # 测试里不需要真的切线程，直接同步执行即可。
    return func(*args, **kwargs)


def _make_scheduler(retries: int = 2) -> AutoPunchScheduler:
    # 只构造 _do_punch() 所需的最小调度器实例，并拦截通知发送。
    scheduler = AutoPunchScheduler(
        bot=object(),
        cancel_gate=CancelGate(),
        owner_id=123,
        enabled=True,
        timezone_name="Asia/Shanghai",
        morning_notify="07:52",
        morning_punch="07:55",
        evening_notify="17:57",
        evening_punch="18:00",
        retries=retries,
    )
    scheduler._notify = AsyncMock()
    return scheduler


@pytest.mark.anyio
async def test_do_punch_keeps_retry_budget_after_relogin(monkeypatch: pytest.MonkeyPatch) -> None:
    # 目标场景：首次因 SSO 失败，重登后又遇到一次普通错误，随后仍继续走剩余重试。
    scheduler = _make_scheduler(retries=2)
    punch_calls: list[str] = []

    def fake_punch(token: str, *, mode: str):
        # 按调用次数编排结果，验证 token 切换和重试是否符合预期。
        punch_calls.append(token)
        if len(punch_calls) == 1:
            raise AttendanceError("SSO authentication failed")
        if len(punch_calls) == 2:
            raise AttendanceError("temporary WebHR error")
        return {"message": f"{mode}:{token}"}

    relogin_mock = Mock(return_value="fresh-token")
    sleep_mock = AsyncMock()
    # 把线程切换、重登录、重试等待和真实打卡调用都替换成可控的测试替身。
    monkeypatch.setattr("bot.scheduler.auto_punch.asyncio.to_thread", _immediate_to_thread)
    monkeypatch.setattr("bot.scheduler.auto_punch._auto_login_sync", relogin_mock)
    monkeypatch.setattr("bot.scheduler.auto_punch.get_retry_delay", lambda attempt: 0)
    monkeypatch.setattr("bot.scheduler.auto_punch.asyncio.sleep", sleep_mock)
    monkeypatch.setattr("cli.attendance.service.punch_attendance", fake_punch)

    await scheduler._do_punch(id_token="expired-token", mode="sbk", card_label="上班卡")

    assert punch_calls == ["expired-token", "fresh-token", "fresh-token"]
    relogin_mock.assert_called_once_with()
    sleep_mock.assert_awaited_once_with(0)
    scheduler._notify.assert_awaited_once_with("【自动打卡】上班卡完成：sbk:fresh-token")


@pytest.mark.anyio
async def test_do_punch_stops_when_relogin_fails(monkeypatch: pytest.MonkeyPatch) -> None:
    # 目标场景：首次 SSO 失败后，自动重登录也失败，应立即结束并通知。
    scheduler = _make_scheduler(retries=2)

    relogin_mock = Mock(return_value=None)
    monkeypatch.setattr("bot.scheduler.auto_punch.asyncio.to_thread", _immediate_to_thread)
    monkeypatch.setattr("bot.scheduler.auto_punch._auto_login_sync", relogin_mock)
    monkeypatch.setattr(
        "cli.attendance.service.punch_attendance",
        lambda token, *, mode: (_ for _ in ()).throw(AttendanceError("SSO authentication failed")),
    )

    await scheduler._do_punch(id_token="expired-token", mode="xbk", card_label="下班卡")

    relogin_mock.assert_called_once_with()
    scheduler._notify.assert_awaited_once_with(
        "【自动打卡】下班卡失败（SSO 认证失败，重登录也失败）：SSO authentication failed"
    )
