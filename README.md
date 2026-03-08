# ZUEB CLI

学院相关系统命令行工具（当前实现：登录、会话状态、考勤查询、执行打卡、课表查询）。

## 已实现功能

- `login`：账号密码登录，保存会话。
- `status`：查看当前会话信息。
- `logout`：清除本地会话。
- `attendance`：查询当日上下班打卡状态。
- `attendance-punch`：提交上班卡/下班卡，支持自动判断。
- `schedule`：查询课表，支持当前学期/指定学期代码/指定学年学期。

## 运行环境

- Python 3.13（推荐通过 `uv` 运行）
- 依赖：`click`、`python-dotenv`、`httpx`、`cryptography`

## 安装

1. 安装依赖：

```bash
uv pip install click python-dotenv httpx cryptography
```

2. 配置账号（可选）：

```bash
cp .env.example .env
```

`.env` 示例：

```env
ZUEB_USERNAME=你的工号
ZUEB_PASSWORD=你的密码
ZUEB_ATTENDANCE_DEFAULT_XY=113.719755,34.615436
```

## 快速开始

1. 登录：

```bash
uv run python -m cli login -u 你的工号 -p 你的密码
```

2. 查看会话：

```bash
uv run python -m cli status
```

3. 查询考勤：

```bash
uv run python -m cli attendance
```

4. 提交打卡：

```bash
uv run python -m cli attendance-punch --mode auto --yes
```

5. 查询课表（当前学期）：

```bash
uv run python -m cli schedule
```

6. 退出登录：

```bash
uv run python -m cli logout
```

## 课表查询用法

- 查看系统可选学期：

```bash
uv run python -m cli schedule --list-semesters
```

- 按学期代码查询：

```bash
uv run python -m cli schedule --semester 20251
```

- 按学期代码 + 周次查询：

```bash
uv run python -m cli schedule --semester 20251 --week 3
```

- 按学年 + 学期查询：

```bash
uv run python -m cli schedule --year 2025 --term 2
```

- 按学年 + 学期 + 周次查询：

```bash
uv run python -m cli schedule --year 2025 --term 2 --week 8
```

参数规则：

- `--semester` 与 `--year/--term` 互斥。
- `--term` 取值：`1`（第一学期）或 `2`（第二学期）。
- `--week` 为正整数，且需在学期周次范围 `1..maxzc` 内。

## 日志与调试

默认输出 INFO 级别日志，可观察每步操作。加 `-v` 开启 DEBUG 详细日志：

```bash
uv run python -m cli -v login -u 你的工号 -p 你的密码
uv run python -m cli -v schedule
```

## 命令总览

```bash
uv run python -m cli --help
```

全局选项：

- `-v` / `--verbose`：开启 DEBUG 级别详细日志

当前命令：

- `login`
- `status`
- `logout`
- `attendance`
- `attendance-punch`
- `schedule`

## Telegram Bot / Skills 使用方法

1. 配置 Bot 环境变量（建议写入 `.env`）：

```env
TELEGRAM_BOT_TOKEN=你的TelegramBotToken
ANTHROPIC_API_KEY=你的AnthropicKey
ANTHROPIC_BASE_URL=https://your-third-party-gateway.example.com
ANTHROPIC_MODEL=claude-sonnet-4-5
BOT_LOG_LEVEL=INFO
OWNER_ID=你的Telegram数字用户ID
NIGHTLY_CHECK_ENABLED=true
NIGHTLY_CHECK_TIME=21:30
NIGHTLY_CHECK_TIMEZONE=Asia/Shanghai
NIGHTLY_CHECK_RETRIES=2
NIGHTLY_CHECK_PROMPT=查看是否打卡
```

`ANTHROPIC_BASE_URL` 可选；配置后会使用第三方网关而非 Anthropic 默认地址。
`ANTHROPIC_MODEL` 可选；配置后会将该模型名传给 Claude Agent SDK（不填使用 SDK 默认模型）。
`BOT_LOG_LEVEL` 可选；支持 `DEBUG/INFO/WARNING/ERROR/CRITICAL`，默认 `INFO`。
夜间自动检查配置可选，默认每日 `21:30`（`Asia/Shanghai`）执行一次。

2. 启动机器人：

```bash
.venv/bin/python -m bot
```

3. 在 Telegram 中使用：

- `查看课表`、`下周有什么课`、`打卡了吗`
- `/logout`
- `帮我打卡`、`现在打卡`（会先确认再提交）

说明：

- Skills 从项目目录 `.claude/skills/` 自动加载。
- Agent 通过 `bot/agent/helper.py` 调用底层 CLI 服务，脚本统一输出 JSON。
- 会话为文件持久化（`~/.config/zueb-cli/session.json`），登录后可跨消息复用。

4. 可直接测试 helper：

```bash
.venv/bin/python bot/agent/helper.py status
.venv/bin/python bot/agent/helper.py schedule --list
.venv/bin/python bot/agent/helper.py attendance
.venv/bin/python bot/agent/helper.py attendance-punch --mode auto --confirm yes
```

### 课表具体上课时间（Skill 推理）

- `bot/agent/helper.py schedule` 只返回原始 `schedule` 数据，不做地点->时间的代码映射。
- 机器人通过 `.claude/skills/zueb-schedule/SKILL.md` 内的 markdown 错峰时间表推理具体上课时间。
- 推理时基于 `skdd`（上课地点）判定批次，再基于 `jcxx`（节次）映射到具体时段。
- 若地点无法匹配批次，机器人会明确提示“暂无法确认精确时间”，并继续展示原始节次信息。

## 打卡提交说明

- 默认坐标来自环境变量 `ZUEB_ATTENDANCE_DEFAULT_XY`，格式为 `经度,纬度`。
- CLI 可用 `--xy` 临时覆盖默认坐标；helper/skill 也支持同名参数。
- CLI 执行真实打卡前默认会二次确认；加 `--yes` 才会直接提交。
- Helper 执行真实打卡前必须显式传 `--confirm yes`。
- 自动模式会先查询状态：若上班卡未打则提交 `sbk`，否则若下班卡未打则提交 `xbk`。
- 本地时间窗限制与旧实现保持一致：`sbk` 仅允许 `08:00` 前，`xbk` 仅允许 `16:30` 后。

## 每晚自动打卡检查推送

- 机器人进程启动后会在后台调度每日任务，不需要额外 `cron`。
- 默认每天 `21:30`（`Asia/Shanghai`）执行 `查看是否打卡` Skill，并主动发消息到 `OWNER_ID`。
- 查询失败会自动重试（默认 2 次）；最终失败也会推送错误原因。
- 若未登录或会话失效，不会自动重登，只会推送失败信息。

## 本地数据文件

会话和设备信息保存在：

- `~/.config/zueb-cli/session.json`
- `~/.config/zueb-cli/device.json`
