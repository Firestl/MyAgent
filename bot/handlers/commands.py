"""Command handlers for Telegram bot — 处理 /start、/help、/logout、/reset 等命令。"""

from __future__ import annotations

import asyncio
import logging

from aiogram import Router
from aiogram.filters import Command
from aiogram.types import Message

from bot.agent.client import AgentManager, AgentManagerError
from bot.handlers.utils import chat_session_scope
from cli.auth.token import clear_session, load_session

logger = logging.getLogger(__name__)


def _message_context(message: Message) -> tuple[int | None, int | None]:
    """从消息中安全提取 user_id 和 chat_id，属性缺失时返回 None。"""
    user_id = getattr(getattr(message, "from_user", None), "id", None)
    chat_id = getattr(getattr(message, "chat", None), "id", None)
    return user_id, chat_id


def _mask_username(username: str) -> str:
    """对用户名脱敏：保留首尾各 2 个字符，中间用 *** 代替。"""
    value = username.strip()
    if not value:
        return ""
    if len(value) <= 2:
        return "*" * len(value)
    return f"{value[:2]}***{value[-2:]}"


def create_commands_router(agent_manager: AgentManager) -> Router:
    """创建命令路由器，注册所有 slash 命令处理函数。"""
    router = Router(name="commands")

    @router.message(Command("start"))
    async def start_handler(message: Message) -> None:
        """处理 /start — 发送欢迎信息和可用命令列表。"""
        user_id, chat_id = _message_context(message)
        logger.info("Command /start: user_id=%s chat_id=%s", user_id, chat_id)
        await message.answer(
            "欢迎使用 ZUEB 助手机器人。\n"
            "可用命令：\n"
            "/help 查看帮助\n"
            "/logout 退出登录\n"
            "/reset 清空当前聊天上下文"
        )

    @router.message(Command("help"))
    async def help_handler(message: Message) -> None:
        """处理 /help — 发送详细使用说明。"""
        user_id, chat_id = _message_context(message)
        logger.info("Command /help: user_id=%s chat_id=%s", user_id, chat_id)
        await message.answer(
            "使用方式：\n"
            "1) 直接发消息，例如：查看我本周课表、打卡了吗\n"
            "2) /logout 退出当前账号\n"
            "3) /reset 清空当前聊天历史上下文"
        )

    @router.message(Command("logout"))
    async def logout_handler(message: Message) -> None:
        """处理 /logout — 清除本地 session，退出登录。"""
        user_id, chat_id = _message_context(message)
        logger.info("Command /logout: user_id=%s chat_id=%s", user_id, chat_id)
        # 先加载当前 session 以获取用户名用于回显，再清除
        session = await asyncio.to_thread(load_session)
        await asyncio.to_thread(clear_session)

        if session and session.get("username"):
            logger.info(
                "Command /logout success: user_id=%s chat_id=%s username=%s",
                user_id,
                chat_id,
                _mask_username(str(session.get("username", ""))),
            )
            await message.answer(f"已退出登录：{session.get('username')}")
            return
        # 没有活跃 session 时也正常提示
        logger.info("Command /logout no active session: user_id=%s chat_id=%s", user_id, chat_id)
        await message.answer("已退出登录。")

    @router.message(Command("reset"))
    async def reset_handler(message: Message) -> None:
        """处理 /reset — 清空当前 Telegram chat 的 Agent 上下文。"""
        user_id, chat_id = _message_context(message)
        logger.info("Command /reset: user_id=%s chat_id=%s", user_id, chat_id)
        try:
            await agent_manager.reset_session(chat_session_scope(chat_id))
        except AgentManagerError as exc:
            logger.warning(
                "Command /reset failed: user_id=%s chat_id=%s error=%s",
                user_id,
                chat_id,
                exc,
            )
            await message.answer(f"清空聊天上下文失败：{exc}")
            return
        except Exception:
            logger.exception(
                "Command /reset unexpected error: user_id=%s chat_id=%s",
                user_id,
                chat_id,
            )
            await message.answer("清空聊天上下文时发生异常，请稍后重试。")
            return
        await message.answer("已清空当前聊天上下文。接下来的消息会作为新的会话处理。")

    return router
