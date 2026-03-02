"""Application bootstrap for Telegram bot."""

from __future__ import annotations

from collections.abc import Awaitable, Callable
import logging
import os
from typing import Any

from aiogram import BaseMiddleware, Bot, Dispatcher
from aiogram.types import Message, TelegramObject

from bot.agent.client import AgentManager
from bot.config import load_config
from bot.handlers import create_chat_router, create_commands_router
from bot.logging_config import configure_logging

logger = logging.getLogger(__name__)


class OwnerOnlyMiddleware(BaseMiddleware):
    """Reject messages from non-owner user ids."""

    def __init__(self, owner_id: int) -> None:
        super().__init__()
        self._owner_id = owner_id

    async def __call__(
        self,
        handler: Callable[[TelegramObject, dict[str, Any]], Awaitable[Any]],
        event: TelegramObject,
        data: dict[str, Any],
    ) -> Any:
        user = getattr(event, "from_user", None)
        if user is None or user.id != self._owner_id:
            if isinstance(event, Message):
                logger.warning(
                    "Rejected non-owner message: user_id=%s chat_id=%s",
                    getattr(user, "id", None),
                    getattr(getattr(event, "chat", None), "id", None),
                )
                await event.answer("无权限访问该机器人。")
            return None
        return await handler(event, data)


async def run() -> None:
    config = load_config()
    configure_logging(config.bot_log_level)
    logger.info("Bot runtime starting")

    # Claude SDK uses environment variable auth.
    os.environ["ANTHROPIC_API_KEY"] = config.anthropic_api_key
    # Optional: route Claude API traffic through a third-party gateway.
    if config.anthropic_base_url:
        os.environ["ANTHROPIC_BASE_URL"] = config.anthropic_base_url
        logger.info("Using custom Anthropic base URL")
    else:
        logger.info("Using default Anthropic base URL")

    bot = Bot(token=config.telegram_bot_token)
    dp = Dispatcher()
    agent_manager = AgentManager()

    dp.message.middleware(OwnerOnlyMiddleware(config.owner_id))
    dp.include_router(create_commands_router(agent_manager))
    dp.include_router(create_chat_router(agent_manager))

    async def on_startup(**_: Any) -> None:
        logger.info("Connecting Claude agent")
        await agent_manager.connect()
        logger.info("Claude agent connected")

    dp.startup.register(on_startup)

    try:
        logger.info("Starting Telegram polling")
        await dp.start_polling(bot, allowed_updates=dp.resolve_used_update_types())
    finally:
        logger.info("Shutting down bot runtime")
        await agent_manager.disconnect()
        await bot.session.close()
        logger.info("Bot runtime stopped")
