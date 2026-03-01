# ZUEB CLI

学院相关系统命令行工具（当前实现：登录、会话状态、考勤查询、课表查询）。

## 已实现功能

- `login`：账号密码登录，保存会话。
- `status`：查看当前会话信息。
- `logout`：清除本地会话。
- `attendance`：查询当日上下班打卡状态（只读查询，不打卡）。
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

4. 查询课表（当前学期）：

```bash
uv run python -m cli schedule
```

5. 退出登录：

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

- 按学年 + 学期查询：

```bash
uv run python -m cli schedule --year 2025 --term 2
```

参数规则：

- `--semester` 与 `--year/--term` 互斥。
- `--term` 取值：`1`（第一学期）或 `2`（第二学期）。

## 命令总览

```bash
uv run python -m cli --help
```

当前命令：

- `login`
- `status`
- `logout`
- `attendance`
- `schedule`

## 本地数据文件

会话和设备信息保存在：

- `~/.config/zueb-cli/session.json`
- `~/.config/zueb-cli/device.json`
