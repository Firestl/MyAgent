---
name: zueb-login
description: 登录ZUEB校园系统。当用户提供账号密码或说要登录时触发。
---

# ZUEB Login

Run the helper script to login:

```bash
.venv/bin/python bot/agent/helper.py login "<username>" "<password>"
```

- Replace `<username>` and `<password>` with the user's credentials.
- Parse the JSON output and report the result.
- **Never echo or repeat the user's password in your response.**
- On success, tell the user they are logged in and show the display name if available.
- On failure, show the error message and suggest retrying.
