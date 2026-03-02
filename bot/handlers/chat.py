"""Free-text chat handlers."""

from __future__ import annotations

import logging
from time import perf_counter

from aiogram import F, Router
from aiogram.enums import ChatAction
from aiogram.types import Message

from bot.agent.client import AgentManager, AgentManagerError

_TELEGRAM_TEXT_LIMIT = 3900
logger = logging.getLogger(__name__)


def _split_text(text: str, limit: int = _TELEGRAM_TEXT_LIMIT) -> list[str]:
    cleaned = text.strip()
    if not cleaned:
        return []

    chunks: list[str] = []
    current = cleaned
    while len(current) > limit:
        split_at = current.rfind("\n", 0, limit)
        if split_at <= 0:
            split_at = limit
        chunks.append(current[:split_at].strip())
        current = current[split_at:].strip()
    if current:
        chunks.append(current)
    return chunks


def create_chat_router(agent_manager: AgentManager) -> Router:
    router = Router(name="chat")

    @router.message(F.text & ~F.text.startswith("/"))
    async def chat_handler(message: Message) -> None:
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
            await message.bot.send_chat_action(
                chat_id=message.chat.id,
                action=ChatAction.TYPING,
            )
            reply = await agent_manager.query(text)
        except AgentManagerError as exc:
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
        chunks = _split_text(reply)
        if not chunks:
            logger.warning(
                "Chat query produced no chunks: user_id=%s chat_id=%s",
                user_id,
                chat_id,
            )
            await message.answer("暂时没有可返回的结果，请重试。")
            return

        for chunk in chunks:
            await message.answer(chunk)

    return router
