"""配置加载模块，从环境变量读取所有运行时配置。"""

from __future__ import annotations

from dataclasses import dataclass
import os
import re
from zoneinfo import ZoneInfo

from dotenv import load_dotenv

# 加载 .env 文件中的环境变量
load_dotenv()

# 时间格式校验正则：匹配 "HH:MM"
_TIME_PATTERN = re.compile(r"^\d{2}:\d{2}$")


# 不可变配置数据类，保存所有运行时配置项
@dataclass(frozen=True)
class BotConfig:
    telegram_bot_token: str
    anthropic_api_key: str
    anthropic_base_url: str | None
    bot_log_level: str
    owner_id: int
    nightly_check_enabled: bool
    nightly_check_time: str
    nightly_check_timezone: str
    nightly_check_retries: int
    nightly_check_prompt: str


# 读取必需的环境变量，缺失时抛出异常
def _must_get_env(name: str) -> str:
    value = (os.getenv(name) or "").strip()
    if not value:
        raise RuntimeError(f"Missing required environment variable: {name}")
    return value


# 读取可选的环境变量，不存在则返回 None
def _optional_get_env(name: str) -> str | None:
    value = (os.getenv(name) or "").strip()
    return value or None


# 读取布尔型环境变量，支持 true/false/1/0/yes/no 等写法
def _get_bool_env(name: str, default: bool) -> bool:
    value = _optional_get_env(name)
    if value is None:
        return default
    normalized = value.lower()
    if normalized in {"1", "true", "yes", "on"}:
        return True
    if normalized in {"0", "false", "no", "off"}:
        return False
    raise RuntimeError(f"{name} must be a boolean: true/false")


# 读取日志级别，默认 INFO，仅允许标准日志级别
def _get_log_level() -> str:
    value = (_optional_get_env("BOT_LOG_LEVEL") or "INFO").upper()
    allowed = {"DEBUG", "INFO", "WARNING", "ERROR", "CRITICAL"}
    if value not in allowed:
        allowed_text = ", ".join(sorted(allowed))
        raise RuntimeError(f"BOT_LOG_LEVEL must be one of: {allowed_text}")
    return value


# 读取每晚考勤检查时间，默认 "21:30"，校验 HH:MM 格式和合法范围
def _get_nightly_time() -> str:
    value = _optional_get_env("NIGHTLY_CHECK_TIME") or "21:30"
    if not _TIME_PATTERN.match(value):
        raise RuntimeError("NIGHTLY_CHECK_TIME must be in HH:MM format")
    hour, minute = value.split(":")
    hour_num = int(hour)
    minute_num = int(minute)
    if hour_num not in range(24) or minute_num not in range(60):
        raise RuntimeError("NIGHTLY_CHECK_TIME must be a valid 24h time")
    return value


# 读取时区配置，默认 "Asia/Shanghai"，使用 ZoneInfo 校验合法性
def _get_nightly_timezone() -> str:
    value = _optional_get_env("NIGHTLY_CHECK_TIMEZONE") or "Asia/Shanghai"
    try:
        ZoneInfo(value)
    except Exception as exc:
        raise RuntimeError("NIGHTLY_CHECK_TIMEZONE must be a valid IANA timezone") from exc
    return value


# 读取考勤检查失败重试次数，默认 2，范围 0~5
def _get_nightly_retries() -> int:
    value = _optional_get_env("NIGHTLY_CHECK_RETRIES")
    if value is None:
        return 2
    try:
        retries = int(value)
    except ValueError as exc:
        raise RuntimeError("NIGHTLY_CHECK_RETRIES must be an integer") from exc
    if retries < 0 or retries > 5:
        raise RuntimeError("NIGHTLY_CHECK_RETRIES must be in range 0..5")
    return retries


# 读取考勤检查提示词，默认 "查看是否打卡"
def _get_nightly_prompt() -> str:
    value = _optional_get_env("NIGHTLY_CHECK_PROMPT") or "查看是否打卡"
    if not value.strip():
        raise RuntimeError("NIGHTLY_CHECK_PROMPT cannot be empty")
    return value


# 组装并返回完整的 BotConfig 配置对象
def load_config() -> BotConfig:
    # 读取必需配置
    telegram_bot_token = _must_get_env("TELEGRAM_BOT_TOKEN")
    anthropic_api_key = _must_get_env("ANTHROPIC_API_KEY")
    anthropic_base_url = _optional_get_env("ANTHROPIC_BASE_URL")
    bot_log_level = _get_log_level()
    owner_id_raw = _must_get_env("OWNER_ID")
    # 读取每晚考勤相关配置
    nightly_check_enabled = _get_bool_env("NIGHTLY_CHECK_ENABLED", default=True)
    nightly_check_time = _get_nightly_time()
    nightly_check_timezone = _get_nightly_timezone()
    nightly_check_retries = _get_nightly_retries()
    nightly_check_prompt = _get_nightly_prompt()

    # 将 OWNER_ID 转为整数（Telegram 用户 ID）
    try:
        owner_id = int(owner_id_raw)
    except ValueError as exc:
        raise RuntimeError("OWNER_ID must be an integer Telegram user id") from exc

    return BotConfig(
        telegram_bot_token=telegram_bot_token,
        anthropic_api_key=anthropic_api_key,
        anthropic_base_url=anthropic_base_url,
        bot_log_level=bot_log_level,
        owner_id=owner_id,
        nightly_check_enabled=nightly_check_enabled,
        nightly_check_time=nightly_check_time,
        nightly_check_timezone=nightly_check_timezone,
        nightly_check_retries=nightly_check_retries,
        nightly_check_prompt=nightly_check_prompt,
    )
