"""Tests for bot/handlers/utils.py — chat_session_scope."""

from __future__ import annotations

from bot.handlers.utils import chat_session_scope


def test_normal_chat_id() -> None:
    """测试普通私聊 chat_id 被拼成稳定的会话 scope。"""
    # 正常 Telegram 私聊 chat_id。
    assert chat_session_scope(123456) == "telegram-chat:123456"


def test_none_chat_id() -> None:
    """测试 chat_id 缺失时回退到 unknown。"""
    # chat_id 为 None 时回退到 "unknown"。
    assert chat_session_scope(None) == "telegram-chat:unknown"


def test_negative_chat_id() -> None:
    """测试群组负数 chat_id 也能被原样保留。"""
    # 群组/超级群组的 chat_id 是负数。
    assert chat_session_scope(-100123) == "telegram-chat:-100123"


def test_zero_chat_id() -> None:
    """测试边界值 0 不会导致 scope 生成失败。"""
    # 边界：chat_id 为 0（不会出现在生产中，但不应崩溃）。
    assert chat_session_scope(0) == "telegram-chat:0"
