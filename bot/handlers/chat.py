"""Free-text chat handlers — 处理非命令的自由文本消息。"""

from __future__ import annotations

import logging
from time import perf_counter

from aiogram import F, Router
from aiogram.enums import ChatAction
from aiogram.types import Message

from bot.agent.client import AgentManager, AgentManagerError
from bot.handlers.utils import chat_session_scope

# Telegram 单条消息字符上限（留一定余量，官方限制 4096）
_TELEGRAM_TEXT_LIMIT = 3900
logger = logging.getLogger(__name__)


def _split_text(text: str, limit: int = _TELEGRAM_TEXT_LIMIT) -> list[str]:
    """将长文本按字符上限拆分为多条消息，优先在换行处断开。"""
    cleaned = text.strip()
    if not cleaned:
        return []

    chunks: list[str] = []
    current = cleaned
    while len(current) > limit:
        # 在 limit 范围内找最后一个换行符，尽量保持段落完整
        split_at = current.rfind("\n", 0, limit)
        if split_at <= 0:
            # 找不到换行符时直接硬截断
            split_at = limit
        chunks.append(current[:split_at].strip())
        current = current[split_at:].strip()
    if current:
        chunks.append(current)
    return chunks


def create_chat_router(agent_manager: AgentManager) -> Router:
    """创建自由文本聊天路由器，绑定到 agent_manager 处理查询。"""
    router = Router(name="chat")

    # 匹配：有文本内容 且 不以 "/" 开头（排除命令消息）
    @router.message(F.text & ~F.text.startswith("/"))
    async def chat_handler(message: Message) -> None:
        # 安全提取 user_id / chat_id，防止属性缺失
        user_id = getattr(getattr(message, "from_user", None), "id", None)
        chat_id = getattr(getattr(message, "chat", None), "id", None)
        text = (message.text or "").strip()
        if not text:
            logger.info("Chat ignored empty text: user_id=%s chat_id=%s", user_id, chat_id)
            await message.answer("请输入要查询的内容。")
            return

        logger.info(
            "Chat query received: user_id=%s chat_id=%s text_len=%s",
            user_id,
            chat_id,
            len(text),
        )
        started_at = perf_counter()
        try:
            # 发送"正在输入"状态，让用户知道机器人在处理中
            if message.bot:
                await message.bot.send_chat_action(
                    chat_id=message.chat.id,
                    action=ChatAction.TYPING,
                )
            # 同一 chat 复用同一个 Agent session，保留多轮上下文
            reply = await agent_manager.query(
                text,
                session_scope=chat_session_scope(chat_id),
            )
        except AgentManagerError as exc:
            # Agent 已知错误，直接将错误信息返回给用户
            elapsed_ms = int((perf_counter() - started_at) * 1000)
            logger.warning(
                "Chat query failed: user_id=%s chat_id=%s elapsed_ms=%s error=%s",
                user_id,
                chat_id,
                elapsed_ms,
                exc,
            )
            await message.answer(str(exc))
            return
        except Exception:
            # 未预期的异常，返回通用提示并记录完整堆栈
            elapsed_ms = int((perf_counter() - started_at) * 1000)
            logger.exception(
                "Chat handler unexpected error: user_id=%s chat_id=%s elapsed_ms=%s",
                user_id,
                chat_id,
                elapsed_ms,
            )
            await message.answer("处理消息时发生异常，请稍后重试。")
            return

        elapsed_ms = int((perf_counter() - started_at) * 1000)
        logger.info(
            "Chat query completed: user_id=%s chat_id=%s elapsed_ms=%s reply_len=%s",
            user_id,
            chat_id,
            elapsed_ms,
            len(reply),
        )
        # 将回复拆分为符合 Telegram 限制的多条消息
        chunks = _split_text(reply)
        if not chunks:
            logger.warning(
                "Chat query produced no chunks: user_id=%s chat_id=%s",
                user_id,
                chat_id,
            )
            await message.answer("暂时没有可返回的结果，请重试。")
            return

        # 逐条发送拆分后的消息
        for chunk in chunks:
            await message.answer(chunk)

    return router
