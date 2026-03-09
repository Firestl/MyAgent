from __future__ import annotations

import os

import pytest
from bot.config import load_config


def _env(**overrides: str) -> dict[str, str]:
    # 提供 load_config() 的最小必需环境，再由各测试覆盖关心的配置项。
    env = {
        "TELEGRAM_BOT_TOKEN": "bot-token",
        "ANTHROPIC_API_KEY": "api-key",
        "OWNER_ID": "123456",
    }
    env.update(overrides)
    return env


def test_load_config_accepts_valid_auto_punch_boundaries(monkeypatch: pytest.MonkeyPatch) -> None:
    # 正例：早卡 < 08:00，晚卡 >= 16:30，且通知时间早于打卡时间。
    monkeypatch.setattr(os, "environ", _env(
        AUTO_PUNCH_MORNING_NOTIFY="07:50",
        AUTO_PUNCH_MORNING_PUNCH="07:59",
        AUTO_PUNCH_EVENING_NOTIFY="16:20",
        AUTO_PUNCH_EVENING_PUNCH="16:30",
    ).copy())

    config = load_config()

    assert config.auto_punch_morning_punch == "07:59"
    assert config.auto_punch_evening_punch == "16:30"


def test_load_config_rejects_morning_punch_at_or_after_eight(monkeypatch: pytest.MonkeyPatch) -> None:
    # 反例：早卡到 08:00 及以后，启动阶段就应报错。
    monkeypatch.setattr(os, "environ", _env(AUTO_PUNCH_MORNING_PUNCH="08:00").copy())

    with pytest.raises(
        RuntimeError,
        match=r"AUTO_PUNCH_MORNING_PUNCH \(08:00\) must be earlier than 08:00",
    ):
        load_config()


def test_load_config_rejects_evening_punch_before_sixteen_thirty(monkeypatch: pytest.MonkeyPatch) -> None:
    # 反例：晚卡早于 16:30，启动阶段就应报错。
    monkeypatch.setattr(os, "environ", _env(
        AUTO_PUNCH_EVENING_NOTIFY="16:20",
        AUTO_PUNCH_EVENING_PUNCH="16:29",
    ).copy())

    with pytest.raises(
        RuntimeError,
        match=r"AUTO_PUNCH_EVENING_PUNCH \(16:29\) must be at or after 16:30",
    ):
        load_config()


def test_load_config_still_requires_notify_before_punch(monkeypatch: pytest.MonkeyPatch) -> None:
    # 回归：新增时间窗口校验后，原有的“提醒时间必须早于打卡时间”规则仍需保留。
    monkeypatch.setattr(os, "environ", _env(
        AUTO_PUNCH_MORNING_NOTIFY="07:55",
        AUTO_PUNCH_MORNING_PUNCH="07:54",
    ).copy())

    with pytest.raises(
        RuntimeError,
        match=r"AUTO_PUNCH_MORNING_NOTIFY \(07:55\) must be earlier than AUTO_PUNCH_MORNING_PUNCH \(07:54\)",
    ):
        load_config()
