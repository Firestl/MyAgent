"""Command handlers for Telegram bot — 处理 /start、/help、/login、/logout 等命令。"""

from __future__ import annotations

import asyncio
import logging
import os

from aiogram import Router
from aiogram.filters import Command, CommandObject
from aiogram.types import Message

from bot.agent.client import AgentManager, AgentManagerError
from bot.handlers.utils import chat_session_scope
from cli.auth.login import LoginError, MFARequiredError, login
from cli.auth.token import clear_session, load_session
from cli.types import UserInfo

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


def _user_display_name(user: UserInfo, fallback: str) -> str:
    """Choose a human-friendly display name from login result."""
    return user.get("name") or user.get("realName") or user.get("username") or fallback


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
            "/login <学号或工号> <密码> 登录\n"
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
            "1) /login <学号或工号> <密码>\n"
            "2) 直接发消息，例如：查看我本周课表、打卡了吗\n"
            "3) /logout 退出当前账号\n"
            "4) /reset 清空当前聊天历史上下文\n\n"
            "提示：/login 指令消息会在处理后尝试删除，以减少密码暴露风险。"
        )

    @router.message(Command("login"))
    async def login_handler(message: Message, command: CommandObject) -> None:
        """处理 /login — 解析学号和密码，执行登录流程。"""
        user_id, chat_id = _message_context(message)
        logger.info("Command /login received: user_id=%s chat_id=%s", user_id, chat_id)
        args = (command.args or "").strip()
        username = ""
        password = ""
        has_password_in_message = False  # controls whether to delete the message

        try:
            if not args:
                # No args — try environment variables for unattended / scheduler use
                username = os.environ.get("ZUEB_USERNAME", "")
                password = os.environ.get("ZUEB_PASSWORD", "")
                if not username or not password:
                    await message.answer(
                        "用法：/login <学号或工号> <密码>\n"
                        "（或设置环境变量 ZUEB_USERNAME / ZUEB_PASSWORD）"
                    )
                    return
                logger.info(
                    "Command /login using env-var credentials: user_id=%s chat_id=%s username=%s",
                    user_id,
                    chat_id,
                    _mask_username(username),
                )
            else:
                # 按空格拆分参数：第一段为学号/工号，其余为密码
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

                has_password_in_message = True
                logger.info(
                    "Command /login attempt: user_id=%s chat_id=%s username=%s",
                    user_id,
                    chat_id,
                    _mask_username(username),
                )

            await message.answer("正在登录，请稍候...")
            # login() 是同步阻塞函数，放到线程中执行避免阻塞事件循环
            result = await asyncio.to_thread(login, username, password)

            # 从登录结果中提取用于显示的用户姓名
            user = result["user"]
            display_name = _user_display_name(user, username)
            logger.info(
                "Command /login success: user_id=%s chat_id=%s username=%s",
                user_id,
                chat_id,
                _mask_username(username),
            )
            await message.answer(f"登录成功，欢迎你：{display_name}")
        except MFARequiredError as exc:
            # 账号启用了多因素认证，当前 Bot 流程暂不支持
            logger.warning(
                "Command /login MFA required: user_id=%s chat_id=%s username=%s error=%s",
                user_id,
                chat_id,
                _mask_username(username),
                exc,
            )
            await message.answer(f"登录失败：该账号需要 MFA，当前流程不支持。\n{exc}")
        except LoginError as exc:
            # 登录业务逻辑错误（如密码错误、账号不存在等）
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
            # 仅当消息中包含明文密码时才删除，降低密码泄露风险
            if has_password_in_message:
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
