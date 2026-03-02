"""Stateful Claude SDK client manager for Telegram chat flow.

管理一个持久的 ClaudeSDKClient 实例，提供连接/断开/查询接口。
对 claude_agent_sdk 的所有调用统一在单个后台 worker 任务中执行，
避免跨异步上下文调用导致的 anyio cancel scope 异常。
"""

from __future__ import annotations

import asyncio
from dataclasses import dataclass
import logging
from pathlib import Path
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
_PROJECT_CWD = str(Path(__file__).resolve().parents[2])
# 流式响应的最长等待时间（秒），防止 SDK 永久挂起
_RESPONSE_TIMEOUT_SECONDS = 45

logger = logging.getLogger(__name__)


class AgentManagerError(Exception):
    pass


@dataclass(slots=True)
class _QueryRequest:
    prompt: str
    future: asyncio.Future[str]


@dataclass(slots=True)
class _ShutdownRequest:
    future: asyncio.Future[None]


_Request = _QueryRequest | _ShutdownRequest


class AgentManager:
    """为单个 Telegram 机器人拥有者管理一个持久的 ClaudeSDKClient 实例。"""

    def __init__(self) -> None:
        self._lock = asyncio.Lock()
        self._session_id = "telegram-owner"
        self._request_queue: asyncio.Queue[_Request] | None = None
        self._worker_task: asyncio.Task[None] | None = None

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

    async def _new_client(self) -> ClaudeSDKClient:
        """创建并连接一个新的 Claude 客户端。"""
        logger.info("Connecting ClaudeSDKClient")
        client = ClaudeSDKClient(options=self._build_options())
        await client.connect()
        logger.info("ClaudeSDKClient connected")
        return client

    async def _reconnect_client(self, client: ClaudeSDKClient | None) -> ClaudeSDKClient | None:
        """在 worker 任务内重建客户端，确保 connect/disconnect 同上下文。"""
        logger.warning("Reconnecting ClaudeSDKClient")
        if client is not None:
            try:
                await client.disconnect()
            except Exception:
                # Best-effort cleanup: reconnect path should still continue.
                logger.exception("ClaudeSDKClient disconnect during reconnect failed")
        try:
            return await self._new_client()
        except Exception:
            logger.exception("ClaudeSDKClient reconnect failed")
            return None

    async def _collect_response(self, client: ClaudeSDKClient, prompt: str) -> tuple[str, int]:
        """执行一次查询并收集流式文本块。"""
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
            return "我没有拿到有效回复，请重试一次。", 0
        return "\n".join(parts).strip(), len(parts)

    @staticmethod
    def _finish_query_future(fut: asyncio.Future[str], *, result: str | None = None, error: Exception | None = None) -> None:
        """统一完成 query future，避免重复 set_result/set_exception。"""
        if fut.done():
            return
        if error is not None:
            fut.set_exception(error)
            return
        assert result is not None
        fut.set_result(result)

    async def _worker_loop(
        self,
        queue: asyncio.Queue[_Request],
        ready: asyncio.Future[None],
    ) -> None:
        """后台 worker：串行处理 query / reconnect / shutdown。"""
        client: ClaudeSDKClient | None = None
        try:
            client = await self._new_client()
            if not ready.done():
                ready.set_result(None)

            while True:
                request = await queue.get()
                if isinstance(request, _ShutdownRequest):
                    if not request.future.done():
                        request.future.set_result(None)
                    break

                if request.future.cancelled():
                    continue

                started_at = perf_counter()
                try:
                    if client is None:
                        client = await self._new_client()

                    reply, chunks = await self._collect_response(client, request.prompt)
                    elapsed_ms = int((perf_counter() - started_at) * 1000)
                    logger.info(
                        "Claude query completed: elapsed_ms=%s response_chars=%s chunks=%s",
                        elapsed_ms,
                        len(reply),
                        chunks,
                    )
                    self._finish_query_future(request.future, result=reply)
                except TimeoutError:
                    elapsed_ms = int((perf_counter() - started_at) * 1000)
                    logger.warning("Claude query timeout: elapsed_ms=%s", elapsed_ms)
                    client = await self._reconnect_client(client)
                    if client is None:
                        self._finish_query_future(
                            request.future,
                            error=AgentManagerError("Claude 响应超时，且重连失败。"),
                        )
                    else:
                        self._finish_query_future(
                            request.future,
                            error=AgentManagerError("Claude 响应超时，请重试。"),
                        )
                except Exception as exc:
                    elapsed_ms = int((perf_counter() - started_at) * 1000)
                    logger.exception("Claude query failed: elapsed_ms=%s", elapsed_ms)
                    self._finish_query_future(
                        request.future,
                        error=AgentManagerError(f"Claude 查询失败：{exc}"),
                    )
        except asyncio.CancelledError:
            if not ready.done():
                ready.set_exception(AgentManagerError("Claude worker 被取消。"))
            raise
        except Exception as exc:
            logger.exception("Claude worker crashed")
            if not ready.done():
                ready.set_exception(exc)
            self._flush_pending_queue(queue, AgentManagerError(f"Claude worker 异常：{exc}"))
        finally:
            if client is not None:
                logger.info("Disconnecting ClaudeSDKClient")
                try:
                    await client.disconnect()
                    logger.info("ClaudeSDKClient disconnected")
                except Exception:
                    logger.exception("ClaudeSDKClient disconnect failed in worker shutdown")
            self._flush_pending_queue(queue, AgentManagerError("Agent 已断开连接。"))

    def _flush_pending_queue(self, queue: asyncio.Queue[_Request], error: Exception) -> None:
        """在 worker 退出时清空队列，避免调用方 future 永久悬挂。"""
        while True:
            try:
                request = queue.get_nowait()
            except asyncio.QueueEmpty:
                return

            if isinstance(request, _QueryRequest):
                self._finish_query_future(request.future, error=error)
            elif isinstance(request, _ShutdownRequest) and not request.future.done():
                request.future.set_result(None)

    async def connect(self) -> None:
        loop = asyncio.get_running_loop()
        ready = loop.create_future()

        async with self._lock:
            if self._worker_task is not None and not self._worker_task.done():
                return

            queue: asyncio.Queue[_Request] = asyncio.Queue()
            worker_task = asyncio.create_task(
                self._worker_loop(queue, ready),
                name="claude-agent-worker",
            )
            self._request_queue = queue
            self._worker_task = worker_task

        try:
            await ready
        except Exception:
            async with self._lock:
                if self._worker_task is worker_task:
                    self._worker_task = None
                    self._request_queue = None
            try:
                await worker_task
            except Exception:
                pass
            raise

    async def disconnect(self) -> None:
        loop = asyncio.get_running_loop()
        shutdown_future: asyncio.Future[None] | None = None

        async with self._lock:
            task = self._worker_task
            queue = self._request_queue
            if task is None:
                return

            self._worker_task = None
            self._request_queue = None

            if queue is not None and not task.done():
                shutdown_future = loop.create_future()
                await queue.put(_ShutdownRequest(future=shutdown_future))

        if shutdown_future is not None:
            try:
                await shutdown_future
            except Exception:
                # 交由等待 worker 任务时统一处理日志
                pass

        if task is not None:
            try:
                await task
            except asyncio.CancelledError:
                pass
            except Exception:
                logger.exception("Claude worker stopped with error")

    async def query(self, text: str) -> str:
        prompt = text.strip()
        if not prompt:
            return "请输入要查询的内容。"

        started_at = perf_counter()
        logger.info("Claude query started: prompt_len=%s", len(prompt))
        loop = asyncio.get_running_loop()
        future: asyncio.Future[str] = loop.create_future()
        async with self._lock:
            queue = self._request_queue
            worker = self._worker_task
            if queue is None or worker is None or worker.done():
                raise AgentManagerError("Agent 尚未连接。")
            await queue.put(_QueryRequest(prompt=prompt, future=future))

        try:
            reply = await future
        except AgentManagerError:
            raise
        except Exception as exc:
            elapsed_ms = int((perf_counter() - started_at) * 1000)
            logger.exception("Claude query failed: elapsed_ms=%s", elapsed_ms)
            raise AgentManagerError(f"Claude 查询失败：{exc}") from exc

        elapsed_ms = int((perf_counter() - started_at) * 1000)
        logger.info("Claude query finished: elapsed_ms=%s response_chars=%s", elapsed_ms, len(reply))
        return reply
