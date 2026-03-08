"""Telegram 机器人引导启动模块。"""

from __future__ import annotations

from collections.abc import Awaitable, Callable
import logging
import os
import pathlib
import sys
from typing import Any

from aiogram import BaseMiddleware, Bot, Dispatcher
from aiogram.types import Message, TelegramObject

from bot.agent.client import AgentManager
from bot.config import load_config
from bot.handlers import create_chat_router, create_commands_router
from bot.logging_config import configure_logging
from bot.scheduler import NightlyAttendanceScheduler
from bot.startup_check import check_tool_calling

logger = logging.getLogger(__name__)


# 权限中间件：仅允许 owner 用户与机器人交互，其余用户直接拒绝
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
        # 获取消息发送者
        user = getattr(event, "from_user", None)
        # 非 owner 用户：记录警告日志并回复无权限提示，不继续处理
        if user is None or user.id != self._owner_id:
            if isinstance(event, Message):
                logger.warning(
                    "Rejected non-owner message: user_id=%s chat_id=%s",
                    getattr(user, "id", None),
                    getattr(getattr(event, "chat", None), "id", None),
                )
                await event.answer("无权限访问该机器人。")
            return None
        # owner 用户：放行，交给后续 handler 处理
        return await handler(event, data)


# 机器人主启动流程
async def run() -> None:
    # 1. 加载配置并初始化日志
    config = load_config()
    configure_logging(config.bot_log_level)
    logger.info("Bot runtime starting")

    # 2. 设置 Claude SDK 所需的环境变量（API Key 和可选的自定义网关地址）
    os.environ["ANTHROPIC_API_KEY"] = config.anthropic_api_key
    if config.anthropic_base_url:
        os.environ["ANTHROPIC_BASE_URL"] = config.anthropic_base_url
        logger.info("Using custom Anthropic base URL")
    else:
        logger.info("Using default Anthropic base URL")
    if config.anthropic_model:
        logger.info("Using custom Claude model: %s", config.anthropic_model)
    else:
        logger.info("Using default Claude model")

    # 3. 创建核心组件
    bot = Bot(token=config.telegram_bot_token)       # Telegram Bot 实例
    dp = Dispatcher()                                 # 消息分发器
    agent_manager = AgentManager(model=config.anthropic_model)  # Claude Agent 管理器
    # 每晚定时考勤检查调度器
    scheduler = NightlyAttendanceScheduler(
        bot=bot,
        agent_manager=agent_manager,
        owner_id=config.owner_id,
        enabled=config.nightly_check_enabled,
        run_time=config.nightly_check_time,           # 执行时间，如 "21:30"
        timezone_name=config.nightly_check_timezone,  # 时区，如 "Asia/Shanghai"
        retries=config.nightly_check_retries,         # 失败重试次数
        prompt=config.nightly_check_prompt,           # 发送给 Agent 的考勤提示词
    )

    # 3b. Tool calling capability check — fast-fail if the API endpoint is broken
    logger.info("Running tool calling capability check")
    if not await check_tool_calling():
        msg = (
            "Bot 启动失败：API 端点不支持工具调用（tool calling）。"
            "请检查 ANTHROPIC_BASE_URL 配置。"
        )
        logger.critical(msg)
        # Use a flag file to ensure the Telegram notification is sent at most
        # once, even if the process manager restarts us with Restart=always.
        flag = pathlib.Path(__file__).resolve().parent.parent / ".tool_check_failed"
        if not flag.exists():
            try:
                await bot.send_message(chat_id=config.owner_id, text=msg)
                flag.touch()
            except Exception:
                logger.exception("Failed to send startup failure notification")
        await bot.session.close()
        sys.exit(1)
    # Clear flag on successful check (e.g. after config is fixed and bot restarts)
    flag = pathlib.Path(__file__).resolve().parent.parent / ".tool_check_failed"
    if flag.exists():
        flag.unlink(missing_ok=True)
    logger.info("Tool calling capability check passed")

    # 4. 注册中间件和路由
    dp.message.middleware(OwnerOnlyMiddleware(config.owner_id))  # 权限过滤
    dp.include_router(create_commands_router(agent_manager))     # 命令路由（/start 等）
    dp.include_router(create_chat_router(agent_manager))         # 普通聊天路由

    # 5. 注册启动回调：连接 Agent 并启动定时任务
    async def on_startup(**_: Any) -> None:
        logger.info("Connecting Claude agent")
        await agent_manager.connect()
        logger.info("Claude agent connected")
        await scheduler.start()

    dp.startup.register(on_startup)

    # 6. 启动 Telegram 长轮询，退出时依次清理资源
    try:
        logger.info("Starting Telegram polling")
        await dp.start_polling(bot, allowed_updates=dp.resolve_used_update_types())
    finally:
        logger.info("Shutting down bot runtime")
        await scheduler.stop()
        await agent_manager.disconnect()
        await bot.session.close()
        logger.info("Bot runtime stopped")
