"""Stateful Claude SDK client manager for Telegram chat flow.

管理一个持久的 ClaudeSDKClient 实例，提供连接/断开/查询接口。
所有操作通过异步锁保证线程安全，查询支持流式接收和超时重连。
"""

from __future__ import annotations

import asyncio
import logging
from time import perf_counter

from claude_agent_sdk import (
    AssistantMessage,
    ClaudeAgentOptions,
    ClaudeSDKClient,
    ResultMessage,
    TextBlock,
)

from bot.agent.prompts import SYSTEM_PROMPT

# 项目根目录，SDK 据此查找 .claude/skills/ 等资源
_PROJECT_CWD = "/home/lsl/Projects/zueb-app"
# 流式响应的最长等待时间（秒），防止 SDK 永久挂起
_RESPONSE_TIMEOUT_SECONDS = 45

logger = logging.getLogger(__name__)


class AgentManagerError(Exception):
    pass


class AgentManager:
    """为单个 Telegram 机器人拥有者管理一个持久的 ClaudeSDKClient 实例。"""

    def __init__(self) -> None:
        self._client: ClaudeSDKClient | None = None
        self._lock = asyncio.Lock()  # 所有连接/查询操作的互斥锁
        self._session_id = "telegram-owner"

    @staticmethod
    def _build_options() -> ClaudeAgentOptions:
        """构建 SDK 配置项，初始连接和重连时复用。"""
        return ClaudeAgentOptions(
            system_prompt=SYSTEM_PROMPT,
            cwd=_PROJECT_CWD,
            setting_sources=["project"],
            allowed_tools=["Skill", "Bash"],
            permission_mode="bypassPermissions",
            max_turns=10,
        )

    async def _connect_locked(self) -> None:
        """在持有锁的前提下创建并连接客户端（幂等：已连接则跳过）。"""
        if self._client is not None:
            return

        logger.info("Connecting ClaudeSDKClient")
        client = ClaudeSDKClient(options=self._build_options())
        await client.connect()
        self._client = client
        logger.info("ClaudeSDKClient connected")

    async def _reconnect_locked(self) -> None:
        """流式响应超时或失败后重建客户端。

        不复用可能已损坏的连接，而是销毁旧客户端、重新创建，
        确保后续查询不受上次失败影响。
        """
        logger.warning("Reconnecting ClaudeSDKClient")
        old_client = self._client
        self._client = None
        if old_client is not None:
            try:
                await old_client.disconnect()
            except Exception:
                # Best-effort cleanup: reconnect path should still continue.
                logger.exception("ClaudeSDKClient disconnect during reconnect failed")
                pass

        await self._connect_locked()

    async def connect(self) -> None:
        async with self._lock:
            await self._connect_locked()

    async def disconnect(self) -> None:
        async with self._lock:
            if self._client is None:
                return
            logger.info("Disconnecting ClaudeSDKClient")
            client = self._client
            self._client = None
            await client.disconnect()
            logger.info("ClaudeSDKClient disconnected")

    async def query(self, text: str) -> str:
        prompt = text.strip()
        if not prompt:
            return "请输入要查询的内容。"

        started_at = perf_counter()
        logger.info("Claude query started: prompt_len=%s", len(prompt))
        async with self._lock:
            if self._client is None:
                raise AgentManagerError("Agent 尚未连接。")

            client = self._client
            try:
                # 向 Claude 发送用户提问
                await client.query(prompt, session_id=self._session_id)

                parts: list[str] = []
                # 流式接收响应，设置硬超时防止 SDK 永久阻塞
                async with asyncio.timeout(_RESPONSE_TIMEOUT_SECONDS):
                    async for msg in client.receive_response():
                        if isinstance(msg, AssistantMessage):
                            # 从助手消息中提取所有文本块
                            for block in msg.content:
                                if isinstance(block, TextBlock):
                                    chunk = block.text.strip()
                                    if chunk:
                                        parts.append(chunk)
                        elif isinstance(msg, ResultMessage):
                            # 若未收到文本块，则取最终结果作为兜底
                            if not parts and msg.result:
                                result_text = msg.result.strip()
                                if result_text:
                                    parts.append(result_text)

                if not parts:
                    elapsed_ms = int((perf_counter() - started_at) * 1000)
                    logger.warning("Claude query completed with empty result: elapsed_ms=%s", elapsed_ms)
                    return "我没有拿到有效回复，请重试一次。"
                elapsed_ms = int((perf_counter() - started_at) * 1000)
                logger.info(
                    "Claude query completed: elapsed_ms=%s response_chars=%s chunks=%s",
                    elapsed_ms,
                    len("\n".join(parts).strip()),
                    len(parts),
                )
                return "\n".join(parts).strip()
            except TimeoutError as exc:
                # 超时后尝试重连，避免后续查询受损坏连接影响
                elapsed_ms = int((perf_counter() - started_at) * 1000)
                logger.warning("Claude query timeout: elapsed_ms=%s", elapsed_ms)
                try:
                    await self._reconnect_locked()
                except Exception as reconnect_exc:
                    logger.exception("Claude query timeout and reconnect failed")
                    raise AgentManagerError(
                        f"Claude 响应超时，且重连失败：{reconnect_exc}"
                    ) from reconnect_exc
                raise AgentManagerError("Claude 响应超时，请重试。") from exc
            except Exception as exc:
                elapsed_ms = int((perf_counter() - started_at) * 1000)
                logger.exception("Claude query failed: elapsed_ms=%s", elapsed_ms)
                raise AgentManagerError(f"Claude 查询失败：{exc}") from exc
