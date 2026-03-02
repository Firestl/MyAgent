"""Logging setup for the Telegram bot runtime."""

from __future__ import annotations

import logging

_DEFAULT_LOG_LEVEL = "INFO"
_LOG_FORMAT = "%(asctime)s | %(levelname)s | %(name)s | %(message)s"
_DATE_FORMAT = "%Y-%m-%d %H:%M:%S"


def configure_logging(level: str) -> None:
    """Configure process-wide logging for bot modules."""
    level_name = (level or _DEFAULT_LOG_LEVEL).upper().strip()
    log_level = getattr(logging, level_name, logging.INFO)

    formatter = logging.Formatter(_LOG_FORMAT, _DATE_FORMAT)
    root = logging.getLogger()
    root.setLevel(log_level)

    if root.handlers:
        for handler in root.handlers:
            handler.setLevel(log_level)
            handler.setFormatter(formatter)
    else:
        handler = logging.StreamHandler()
        handler.setLevel(log_level)
        handler.setFormatter(formatter)
        root.addHandler(handler)

    logging.captureWarnings(True)
