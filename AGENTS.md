# Project Introduction
I have developed an Android application and packaged it into an APK file. Now I intend to encapsulate the core logic into the form of a CLI tool. Your task is to carry out development based on Gradle java code.

- `GradleProject/src/main/java/com/supwisdom/superapp` Foucus on this folder

## Use Python to implement
- `.venv/bin/python`
- `uv pip install` to install package
- Dependencies: `httpx click cryptography`
- Run CLI: `.venv/bin/python -m cli`

## CLI Structure
```
cli/
├── __main__.py       # Entry point
├── main.py           # Click group: login, status, logout, schedule
├── config.py         # URLs, constants, paths
├── auth/
│   ├── crypto.py     # RSA encrypt (ref: supwisdom/bn1.java)
│   ├── client.py     # HTTP wrapper (ref: supwisdom/mj1.java)
│   ├── login.py      # 4-step login flow (ref: ui/activity/LoginActivity.java)
│   └── token.py      # Session persistence (~/.config/zueb-cli/session.json)
└── schedule/
    ├── sso.py        # CAS SSO: JWT → JSESSIONID
    ├── client.py     # JWXT HTTP client with jw_apply encryption
    └── service.py    # Orchestrates schedule fetch flow
```

## Bot Skill Rule (Schedule Time)

- For Telegram schedule replies, derive concrete class time in `.claude/skills/zueb-schedule/SKILL.md`.
- Use markdown-table rules (location batch + period mapping) to infer times from `skdd` and `jcxx`.
- Do not implement location-to-time mapping logic in Python code unless explicitly requested.
