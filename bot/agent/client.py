"""Stateful Claude SDK client manager for Telegram chat flow."""

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

# Project root where .claude/skills/ lives.
_PROJECT_CWD = "/home/lsl/Projects/zueb-app"
# Guardrail for streaming response completion.
_RESPONSE_TIMEOUT_SECONDS = 45

logger = logging.getLogger(__name__)


class AgentManagerError(Exception):
    pass


class AgentManager:
    """Manage one persistent ClaudeSDKClient for a single Telegram owner."""

    def __init__(self) -> None:
        self._client: ClaudeSDKClient | None = None
        self._lock = asyncio.Lock()
        self._session_id = "telegram-owner"

    @staticmethod
    def _build_options() -> ClaudeAgentOptions:
        """Build SDK options used by both initial connect and reconnect."""
        return ClaudeAgentOptions(
            system_prompt=SYSTEM_PROMPT,
            cwd=_PROJECT_CWD,
            setting_sources=["project"],
            allowed_tools=["Skill", "Bash"],
            permission_mode="bypassPermissions",
            max_turns=10,
        )

    async def _connect_locked(self) -> None:
        """Create and connect a client while holding the manager lock."""
        if self._client is not None:
            return

        logger.info("Connecting ClaudeSDKClient")
        client = ClaudeSDKClient(options=self._build_options())
        await client.connect()
        self._client = client
        logger.info("ClaudeSDKClient connected")

    async def _reconnect_locked(self) -> None:
        """Reset transport state after a stalled/failed stream.

        We rebuild the SDK client instead of trying to reuse a possibly broken stream.
        This keeps future queries healthy after timeout or protocol-level failures.
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
                await client.query(prompt, session_id=self._session_id)

                parts: list[str] = []
                # The SDK stream may wait forever if a terminal ResultMessage is
                # never emitted, so enforce a hard timeout here.
                async with asyncio.timeout(_RESPONSE_TIMEOUT_SECONDS):
                    async for msg in client.receive_response():
                        if isinstance(msg, AssistantMessage):
                            for block in msg.content:
                                if isinstance(block, TextBlock):
                                    chunk = block.text.strip()
                                    if chunk:
                                        parts.append(chunk)
                        elif isinstance(msg, ResultMessage):
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
