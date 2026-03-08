# MyAgent — Personal AI Agent

Telegram bot powered by Claude Agent SDK. Acts as a personal assistant for campus operations (ZUEB) and general-purpose tasks. Extensible via Skills (Markdown-based tool definitions).

## Architecture

```
Telegram (aiogram) → Claude Agent SDK → Skills (.claude/skills/) → helper.py (JSON bridge) → CLI services
```

| Directory | Purpose |
|-----------|---------|
| `bot/` | Telegram bot: agent management, handlers, nightly scheduler |
| `cli/` | Standalone CLI & service layer (also called by bot via `bot/agent/helper.py`) |
| `.claude/skills/` | Skill definitions — Claude auto-discovers these, no code changes needed |

## Development & Deployment

**本地开发**：
- Python: `.venv/bin/python`
- Install deps: `uv pip install -r requirements.txt`
- Run CLI: `.venv/bin/python -m cli`
- Run Bot: `.venv/bin/python -m bot`
- Tests: `.venv/bin/python -m pytest tests/`

## Code Conventions

- CLI uses `click`; bot uses `aiogram>=3`
- Helper output is strict JSON: `{"ok": true, ...}` or `{"ok": false, "error": "..."}`
- Sensitive fields (password, token) must be filtered from helper output
- All POST params for ZUEB APIs are URL query string (`@Query`), NOT request body

## Extending the Agent

To add a new capability:
1. Add service logic in `cli/` (new module or extend existing)
2. Add a subcommand in `bot/agent/helper.py` (JSON bridge)
3. Create a Skill file at `.claude/skills/<name>/SKILL.md`
4. **Update `bot/agent/prompts.py`** — add the new skill to the system prompt's skill list
5. Bot auto-discovers new skills — no handler changes needed

## ZUEB Campus Module

Wraps the campus Android app's APIs.

### Bot Skill Rule (Schedule Time)

- For Telegram schedule replies, derive concrete class time in `.claude/skills/zueb-schedule/SKILL.md`.
- Use markdown-table rules (location batch + period mapping) to infer times from `skdd` and `jcxx`.
- Do not implement location-to-time mapping logic in Python code unless explicitly requested.
