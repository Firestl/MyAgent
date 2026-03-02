"""Configuration loader for Telegram bot runtime."""

from dataclasses import dataclass
import os

from dotenv import load_dotenv

load_dotenv()


@dataclass(frozen=True)
class BotConfig:
    telegram_bot_token: str
    anthropic_api_key: str
    anthropic_base_url: str | None
    bot_log_level: str
    owner_id: int


def _must_get_env(name: str) -> str:
    value = (os.getenv(name) or "").strip()
    if not value:
        raise RuntimeError(f"Missing required environment variable: {name}")
    return value


def _optional_get_env(name: str) -> str | None:
    value = (os.getenv(name) or "").strip()
    return value or None


def _get_log_level() -> str:
    value = (_optional_get_env("BOT_LOG_LEVEL") or "INFO").upper()
    allowed = {"DEBUG", "INFO", "WARNING", "ERROR", "CRITICAL"}
    if value not in allowed:
        allowed_text = ", ".join(sorted(allowed))
        raise RuntimeError(f"BOT_LOG_LEVEL must be one of: {allowed_text}")
    return value


def load_config() -> BotConfig:
    telegram_bot_token = _must_get_env("TELEGRAM_BOT_TOKEN")
    anthropic_api_key = _must_get_env("ANTHROPIC_API_KEY")
    anthropic_base_url = _optional_get_env("ANTHROPIC_BASE_URL")
    bot_log_level = _get_log_level()
    owner_id_raw = _must_get_env("OWNER_ID")

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
    )
