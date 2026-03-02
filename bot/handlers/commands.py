"""Command handlers for Telegram bot."""

from __future__ import annotations

import asyncio
import logging

from aiogram import Router
from aiogram.filters import Command, CommandObject
from aiogram.types import Message

from bot.agent.client import AgentManager
from cli.auth.login import LoginError, MFARequiredError, login
from cli.auth.token import clear_session, load_session

logger = logging.getLogger(__name__)


def _message_context(message: Message) -> tuple[int | None, int | None]:
    user_id = getattr(getattr(message, "from_user", None), "id", None)
    chat_id = getattr(getattr(message, "chat", None), "id", None)
    return user_id, chat_id


def _mask_username(username: str) -> str:
    value = username.strip()
    if not value:
        return ""
    if len(value) <= 2:
        return "*" * len(value)
    return f"{value[:2]}***{value[-2:]}"


def create_commands_router(agent_manager: AgentManager) -> Router:
    router = Router(name="commands")

    @router.message(Command("start"))
    async def start_handler(message: Message) -> None:
        user_id, chat_id = _message_context(message)
        logger.info("Command /start: user_id=%s chat_id=%s", user_id, chat_id)
        await message.answer(
            "欢迎使用 ZUEB 助手机器人。\n"
            "可用命令：\n"
            "/help 查看帮助\n"
            "/login <学号或工号> <密码> 登录\n"
            "/logout 退出登录"
        )

    @router.message(Command("help"))
    async def help_handler(message: Message) -> None:
        user_id, chat_id = _message_context(message)
        logger.info("Command /help: user_id=%s chat_id=%s", user_id, chat_id)
        await message.answer(
            "使用方式：\n"
            "1) /login <学号或工号> <密码>\n"
            "2) 直接发消息，例如：查看我本周课表、打卡了吗\n"
            "3) /logout 退出当前账号\n\n"
            "提示：/login 指令消息会在处理后尝试删除，以减少密码暴露风险。"
        )

    @router.message(Command("login"))
    async def login_handler(message: Message, command: CommandObject) -> None:
        user_id, chat_id = _message_context(message)
        logger.info("Command /login received: user_id=%s chat_id=%s", user_id, chat_id)
        args = (command.args or "").strip()
        username = ""
        password = ""

        try:
            parts = args.split(maxsplit=1)
            if len(parts) < 2:
                logger.warning("Command /login invalid args: user_id=%s chat_id=%s", user_id, chat_id)
                await message.answer("用法：/login <学号或工号> <密码>")
                return

            username = parts[0].strip()
            password = parts[1]
            if not username or not password:
                logger.warning("Command /login empty credential field: user_id=%s chat_id=%s", user_id, chat_id)
                await message.answer("用法：/login <学号或工号> <密码>")
                return

            logger.info(
                "Command /login attempt: user_id=%s chat_id=%s username=%s",
                user_id,
                chat_id,
                _mask_username(username),
            )
            await message.answer("正在登录，请稍候...")
            result = await asyncio.to_thread(login, username, password)

            user = result.get("user") if isinstance(result.get("user"), dict) else {}
            display_name = (
                user.get("name")
                or user.get("realName")
                or user.get("username")
                or username
            )
            logger.info(
                "Command /login success: user_id=%s chat_id=%s username=%s",
                user_id,
                chat_id,
                _mask_username(username),
            )
            await message.answer(f"登录成功，欢迎你：{display_name}")
        except MFARequiredError as exc:
            logger.warning(
                "Command /login MFA required: user_id=%s chat_id=%s username=%s error=%s",
                user_id,
                chat_id,
                _mask_username(username),
                exc,
            )
            await message.answer(f"登录失败：该账号需要 MFA，当前流程不支持。\n{exc}")
        except LoginError as exc:
            logger.warning(
                "Command /login failed: user_id=%s chat_id=%s username=%s error=%s",
                user_id,
                chat_id,
                _mask_username(username),
                exc,
            )
            await message.answer(f"登录失败：{exc}")
        except Exception as exc:  # pragma: no cover - defensive guard
            logger.exception(
                "Command /login unexpected error: user_id=%s chat_id=%s username=%s",
                user_id,
                chat_id,
                _mask_username(username),
            )
            await message.answer(f"登录异常：{exc}")
        finally:
            # Attempt to remove password-bearing command message.
            try:
                await message.delete()
            except Exception:
                logger.debug(
                    "Command /login delete message failed: user_id=%s chat_id=%s",
                    user_id,
                    chat_id,
                )
                pass

    @router.message(Command("logout"))
    async def logout_handler(message: Message) -> None:
        user_id, chat_id = _message_context(message)
        logger.info("Command /logout: user_id=%s chat_id=%s", user_id, chat_id)
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
        logger.info("Command /logout no active session: user_id=%s chat_id=%s", user_id, chat_id)
        await message.answer("已退出登录。")

    return router
