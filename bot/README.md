# bot/ 模块开发文档

> 面向接手项目的初级开发者，假设你不了解 aiogram（Telegram 机器人框架）和 Claude Agent SDK。

## 一、这个模块做什么？

`bot/` 是一个 **Telegram 机器人**，让用户在 Telegram 聊天中通过自然语言操作 ZUEB 校园系统——查课表、查考勤、登录/登出，而不需要在终端敲命令行。

它的核心思路是：

```
用户在 Telegram 发消息
  → 机器人收到消息
    → 交给 Claude AI 理解用户意图
      → Claude 自动调用对应的脚本查询校园数据
        → 把结果翻译成人话回复给用户
```

此外还有一个**定时任务**：每晚自动检查你今天有没有打卡，并把结果主动推送到 Telegram。

---

## 二、你需要先了解的概念

### 2.1 aiogram：Python 的 Telegram Bot 框架

[aiogram](https://docs.aiogram.dev/) 是一个异步（`async/await`）的 Telegram Bot 开发框架。你需要理解几个核心概念：

| 概念 | 类比 | 说明 |
|------|------|------|
| `Bot` | HTTP 客户端 | 封装了 Telegram Bot API 的调用，比如发消息、删消息 |
| `Dispatcher` | Web 框架的路由分发器 | 接收 Telegram 推送的消息事件，按规则分发给不同的处理函数 |
| `Router` | 路由组 | 把一组相关的消息处理函数归到一起，类似 Flask 的 Blueprint |
| `Middleware` | 中间件 | 在消息到达处理函数之前/之后执行的拦截逻辑，比如权限检查 |
| `Filter` | 路由匹配条件 | 决定一条消息由哪个处理函数来处理，比如 `Command("start")` 匹配 `/start` 命令 |
| `Polling` | 轮询模式 | Bot 主动向 Telegram 服务器拉取新消息（另一种是 Webhook 模式，本项目用轮询） |

**一条消息的处理流程：**

```
Telegram 服务器
  → Dispatcher（通过 polling 拉取）
    → Middleware（权限检查）
      → Router 匹配
        → 具体的 handler 函数处理
          → 调用 bot.send_message() 回复
```

### 2.2 Claude Agent SDK：让 AI 能"动手做事"

普通的 Claude API 调用只能做"对话"——你问它问题，它回答文字。而 **Claude Agent SDK** 让 Claude 变成了一个能"动手"的 Agent：它不仅能理解你说的话，还能根据你的意图**自动执行工具**来完成任务。

在本项目中，这些"工具"就是 **Skills**——一组预定义的 Markdown 文件（`.claude/skills/*.md`），里面写了：

1. 什么情况下触发这个 Skill（如"用户问打卡"）
2. 具体执行什么 Bash 命令（如 `python helper.py attendance`）
3. 怎么解读命令的输出

**SDK 的使用模式：**

```python
# 1. 创建客户端，配置系统提示词和允许的工具
client = ClaudeSDKClient(options=ClaudeAgentOptions(
    system_prompt="你是校园助手...",
    allowed_tools=["Skill", "Bash"],   # 允许 Claude 使用 Skill 和 Bash
    permission_mode="bypassPermissions", # 跳过权限确认（因为是无人值守的 Bot）
    max_turns=10,                        # 最多执行 10 轮工具调用
))

# 2. 连接
await client.connect()

# 3. 发送查询
await client.query("本周课表", session_id="xxx")

# 4. 流式接收响应
async for msg in client.receive_response():
    if isinstance(msg, AssistantMessage):   # AI 的文字回复
        ...
    elif isinstance(msg, ResultMessage):    # 最终结果
        ...
```

**关键参数解释：**

- `system_prompt`：告诉 Claude 它是谁、能做什么、该遵守什么规则
- `cwd`：工作目录，SDK 会在这个目录下查找 `.claude/skills/`
- `allowed_tools`：Claude 被允许使用的工具类型。`Skill` 表示可以调用 Skill 文件，`Bash` 表示可以执行 shell 命令
- `permission_mode="bypassPermissions"`：Bot 是无人值守运行的，没有人能点"确认"按钮，所以跳过所有权限提示
- `max_turns`：防止 Claude 陷入无限循环调用工具，限制最多 10 轮
- `session_id`：会话标识，同一个 session_id 内的对话共享上下文记忆

### 2.3 Skills：Claude 的"技能说明书"

Skills 是一种让 Claude 知道"怎么完成某个任务"的机制。每个 Skill 是一个 Markdown 文件，存放在 `.claude/skills/` 目录下。

以考勤查询 Skill 为例（`.claude/skills/zueb-attendance/SKILL.md`）：

```markdown
---
name: zueb-attendance
description: 查询今日打卡考勤状态。当用户问打卡、考勤、签到时触发。
---

# ZUEB Attendance

Run the helper script to check today's attendance:

\```bash
.venv/bin/python bot/agent/helper.py attendance
\```

## Presenting Results
Parse the JSON output...（告诉 Claude 怎么解读结果）
```

**工作原理：** 当用户问"今天打卡了没"，Claude 读到 `description` 里的"打卡、考勤"关键词，判断应该使用这个 Skill，然后按照里面的指示执行 Bash 命令，拿到 JSON 结果后按照 `Presenting Results` 的说明格式化成用户友好的回复。

---

## 三、目录结构与文件说明

```
bot/
├── __main__.py          # 入口：python -m bot 时执行
├── app.py               # 引导启动：创建 Bot、注册路由、启动轮询
├── config.py            # 配置加载：从 .env 读取所有配置项
├── logging_config.py    # 日志配置：统一日志格式
│
├── agent/               # Claude AI Agent 集成
│   ├── client.py        # AgentManager：管理 Claude SDK 客户端的生命周期和查询
│   ├── helper.py        # 辅助 CLI：Claude Skill 通过 Bash 调用此脚本操作校园系统
│   └── prompts.py       # 系统提示词：定义 Claude 的角色和行为规则
│
├── handlers/            # Telegram 消息处理器
│   ├── commands.py      # 命令处理：/start、/help、/logout、/reset
│   └── chat.py          # 聊天处理：非命令的自由文本 → Claude Agent
│
└── scheduler/           # 定时任务
    └── nightly_attendance.py  # 每晚考勤检查调度器
```

---

## 四、核心模块详解

### 4.1 启动流程 — `app.py`

`app.py` 的 `run()` 函数是整个 Bot 的启动入口，做了以下事情：

```
1. 加载配置 → load_config() 从 .env 读取 Token、API Key 等
2. 配置日志 → 统一日志格式
3. 设置环境变量 → Claude SDK 需要 ANTHROPIC_API_KEY（可选 ANTHROPIC_BASE_URL / ANTHROPIC_MODEL）
4. 创建核心组件 → Bot、Dispatcher、AgentManager、Scheduler
5. 注册中间件和路由 → 权限过滤 + 命令路由 + 聊天路由
6. 启动轮询 → 开始从 Telegram 拉取消息
```

**为什么需要 `OwnerOnlyMiddleware`？**

这个 Bot 只服务一个人（拥有者）。中间件在每条消息到达处理函数之前检查发送者的 Telegram 用户 ID 是否等于配置的 `OWNER_ID`。非 owner 的消息直接拒绝，不会到达 Claude Agent 消耗 API 额度。

**为什么关闭时要按顺序清理？**

```python
finally:
    await scheduler.stop()        # 1. 先停定时任务（它依赖 agent_manager）
    await agent_manager.disconnect()  # 2. 再断开 Claude 连接
    await bot.session.close()     # 3. 最后关闭 Telegram 网络会话
```

因为 scheduler 内部会调用 `agent_manager.query()`，如果先断开 agent 再停 scheduler，可能导致 scheduler 正在执行的查询报错。**依赖关系决定了清理顺序。**

### 4.2 Claude Agent 管理 — `agent/client.py`

`AgentManager` 是对 Claude SDK 客户端的封装，解决了几个实际问题：

#### 为什么需要 `asyncio.Lock`？

Telegram 消息是异步并发到达的。如果两条消息同时触发 `query()`，两个协程可能同时操作同一个 SDK 客户端，导致流式响应混乱。锁保证了**同一时刻只有一个查询在执行**。

```python
async def query(self, text: str) -> str:
    async with self._lock:  # 拿到锁才能查询，其他查询在外面排队
        ...
```

#### 流式响应是怎么收的？

Claude 不是一次性返回完整回复，而是**流式**逐块发送。SDK 通过 `receive_response()` 异步迭代器暴露这些块：

```python
async for msg in client.receive_response():
    if isinstance(msg, AssistantMessage):
        # Claude 的文字回复，可能有多个 TextBlock
        for block in msg.content:
            if isinstance(block, TextBlock):
                parts.append(block.text)
    elif isinstance(msg, ResultMessage):
        # 标记流结束的最终消息
        ...
```

整个过程可能涉及多轮工具调用（Claude 执行 Skill → 拿到结果 → 再生成回复），所以响应流中可能包含多个 `AssistantMessage`。

#### 为什么有超时和重连？

```python
async with asyncio.timeout(45):  # 45 秒超时
    async for msg in client.receive_response():
        ...
```

SDK 的流式响应依赖底层的进程通信。如果 Claude 卡住（比如网络中断、API 限流），`receive_response()` 会永远等下去。45 秒超时是一个安全网。

超时后不能简单地重用旧客户端——底层的通信管道可能已经损坏。所以 `_reconnect_locked()` 会**销毁旧客户端、创建新客户端**，确保下次查询正常。

### 4.3 辅助 CLI 脚本 — `agent/helper.py`

这个文件是 **Claude 和校园系统之间的桥梁**。

**为什么不让 Claude 直接调用 `cli/` 的 Python 函数？**

因为 Claude Agent SDK 的工具执行是通过 **Bash 命令**实现的。Claude 看到 Skill 里写的 `python helper.py attendance`，就会在 shell 里执行这条命令，然后读取 stdout 的输出。

所以 `helper.py` 的职责是：
1. 接收 Claude 传来的 Bash 命令参数
2. 调用 `cli/` 下的实际服务函数
3. 把结果统一转为 JSON 输出到 stdout

**为什么所有输出都是 JSON？**

```python
# 成功：
{"ok": true, "schedule": {...}}

# 失败：
{"ok": false, "error": "当前未登录"}
```

因为 Claude 需要**程序化地解析**输出结果。如果输出是自由格式的文本，Claude 可能误解。JSON 结构清晰，Claude 能可靠地提取字段。

**为什么 login 要过滤敏感字段？**

```python
safe_user = {
    k: v for k, v in user.items()
    if k.lower() not in {"password", "idtoken", "token", "authorization"}
}
```

`helper.py` 的 stdout 会被 Claude 看到。如果 JSON 里包含密码或 token，Claude 可能把它们复述给用户。过滤掉这些字段是安全防线。

### 4.4 命令处理 — `handlers/commands.py`

处理 Telegram 的斜杠命令（`/start`、`/help`、`/logout`、`/reset`）。

### 4.5 聊天处理 — `handlers/chat.py`

处理所有非命令的自由文本消息，比如"本周课表"、"打卡了吗"。

```python
@router.message(F.text & ~F.text.startswith("/"))
```

这个 Filter 的含义是：**有文本内容** 且 **不以 `/` 开头**。命令消息由 `commands.py` 处理，其他文本到这里。

**为什么要发 `TYPING` 状态？**

```python
await message.bot.send_chat_action(chat_id=..., action=ChatAction.TYPING)
```

Claude Agent 的查询可能耗时数秒到十几秒。如果用户发了消息后什么反馈都没有，会以为 Bot 挂了。发送 `TYPING` 状态后，用户在聊天界面会看到"机器人正在输入..."的提示。

**为什么要拆分回复？**

Telegram 单条消息最多 4096 个字符。如果 Claude 的回复超过这个限制，直接发送会失败。`_split_text()` 把长文本按 3900 字符（留一些余量）拆分成多条消息，优先在换行符处断开以保持可读性。

### 4.6 定时考勤调度 — `scheduler/nightly_attendance.py`

每晚自动检查打卡状态，不需要用户手动询问。

**为什么不用 cron 或 APScheduler？**

本项目用了最简单的方案：一个 `asyncio.Task` 在后台循环运行，每次 sleep 到目标时间点，醒来后执行一次检查。

```python
async def _run_loop(self):
    while True:
        run_at = self._next_run_at()        # 下次执行时间
        sleep_seconds = (run_at - now).total_seconds()
        await asyncio.sleep(sleep_seconds)   # 等到那个时间
        await self._run_once()               # 执行检查
```

这种方案的好处：
- 不引入额外依赖（APScheduler 等）
- 和 Bot 的 asyncio 事件循环天然融合
- 代码简单，容易理解和调试

**重试机制是怎么工作的？**

考勤查询可能因为网络抖动、Claude API 限流等原因失败。配置 `retries=2` 意味着：

```
第 1 次尝试 → 失败 → 等 60 秒
第 2 次尝试 → 失败 → 等 180 秒
第 3 次尝试 → 失败 → 放弃，发送错误通知
```

延迟递增（60s → 180s → 300s）是为了给后端恢复的时间，避免频繁重试加重负担。

**为什么失败了也要发消息？**

```python
# 所有重试都失败后
error_text = f"【每晚打卡检查失败】\n原因：{last_error}"
await self._send_text(error_text)
```

因为这是无人值守的定时任务。如果静默失败，用户不知道今天没检查，可能忘了打卡。推送错误通知至少让用户知道"自动检查出了问题，需要自己确认"。

### 4.7 配置加载 — `config.py`

从 `.env` 文件和环境变量读取所有配置，做校验后返回一个不可变的 `BotConfig` 对象。

**为什么用 `@dataclass(frozen=True)`？**

`frozen=True` 让配置对象创建后不可修改。这是防御性编程——配置应该在启动时确定，运行时不应被意外修改。

**为什么每个配置项都有校验函数？**

```python
def _get_nightly_time() -> str:
    value = _optional_get_env("NIGHTLY_CHECK_TIME") or "21:30"
    if not _TIME_PATTERN.match(value):
        raise RuntimeError("NIGHTLY_CHECK_TIME must be in HH:MM format")
    ...
```

**快速失败原则**：如果配置有误（比如时间格式写错了），启动时立即报错退出，而不是等到晚上定时任务执行时才发现问题。这样运维人员能马上知道配置有问题。

---

## 五、数据流总结

### 5.1 用户发送自由文本（如"本周课表"）

```
用户 → Telegram API → Dispatcher → OwnerOnlyMiddleware(权限检查)
  → chat_handler → send_chat_action(TYPING)
  → AgentManager.query("本周课表")
  → Claude SDK → Claude API
  → Claude 识别意图 → 加载 zueb-schedule Skill
  → 执行 Bash: python helper.py schedule
  → helper.py → cli/schedule/service.py → 校园 API
  → JSON 结果返回给 Claude
  → Claude 格式化为可读文本
  → 流式返回 → AgentManager 收集文本
  → _split_text 拆分 → 逐条发送给用户
```

### 5.2 每晚定时考勤检查

```
NightlyAttendanceScheduler._run_loop()
  → asyncio.sleep 到 21:30
  → _run_once()
  → AgentManager.query("查看是否打卡")
  → （同 5.1 的 Claude 调用流程）
  → 拼接"【每晚打卡检查】"标题
  → bot.send_message 主动推送给 owner
  → 失败则重试（最多 N 次）
  → 最终失败也推送错误通知
```

---

## 六、环境变量参考

| 变量名 | 必填 | 默认值 | 说明 |
|--------|------|--------|------|
| `TELEGRAM_BOT_TOKEN` | ✅ | - | 从 [@BotFather](https://t.me/BotFather) 获取的 Bot Token |
| `ANTHROPIC_API_KEY` | ✅ | - | Anthropic Claude API Key |
| `OWNER_ID` | ✅ | - | 你的 Telegram 数字用户 ID |
| `ANTHROPIC_BASE_URL` | ❌ | Anthropic 官方 | 第三方 API 网关地址 |
| `ANTHROPIC_MODEL` | ❌ | SDK 默认模型 | 传递给 Claude Agent SDK 的模型名 |
| `BOT_LOG_LEVEL` | ❌ | `INFO` | 日志级别 |
| `NIGHTLY_CHECK_ENABLED` | ❌ | `true` | 是否启用每晚考勤检查 |
| `NIGHTLY_CHECK_TIME` | ❌ | `21:30` | 检查执行时间（HH:MM） |
| `NIGHTLY_CHECK_TIMEZONE` | ❌ | `Asia/Shanghai` | 时区 |
| `NIGHTLY_CHECK_RETRIES` | ❌ | `2` | 失败重试次数（0-5） |
| `NIGHTLY_CHECK_PROMPT` | ❌ | `查看是否打卡` | 发给 Claude 的考勤查询提示词 |

---

## 七、如何运行

```bash
# 1. 安装依赖
uv pip install aiogram claude-agent-sdk python-dotenv

# 2. 配置环境变量
cp .env.example .env
# 编辑 .env 填入你的 Token、API Key、Owner ID

# 3. 启动
.venv/bin/python -m bot
```

---

## 八、常见扩展场景

### 添加新的 Skill（如：查成绩）

1. 在 `cli/` 下实现查询逻辑（如 `cli/grade/service.py`）
2. 在 `bot/agent/helper.py` 添加新的 click 子命令 `grade`
3. 在 `.claude/skills/zueb-grade/SKILL.md` 写 Skill 描述和 Bash 命令
4. 在 `bot/agent/prompts.py` 的系统提示词里加上 `- zueb-grade: 查询成绩`

不需要修改 `handlers/`、`app.py` 等其他文件——Claude 会自动识别新 Skill。

### 添加新的 Telegram 命令

1. 在 `bot/handlers/commands.py` 的 `create_commands_router()` 里添加新的 handler 函数
2. 用 `@router.message(Command("xxx"))` 装饰器注册

### 修改定时任务的行为

修改 `bot/scheduler/nightly_attendance.py` 中的 `_run_once()` 方法。如果要添加新的定时任务，可以参照 `NightlyAttendanceScheduler` 的模式创建新的调度器类，在 `app.py` 中实例化并启动。
