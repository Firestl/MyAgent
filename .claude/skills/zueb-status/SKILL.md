---
name: zueb-status
description: 查看当前ZUEB登录状态。当用户询问是否已登录时触发。
---

# ZUEB Status

Run the helper script to check login status:

```bash
.venv/bin/python bot/agent/helper.py status
```

- Parse the JSON output.
- If `logged_in` is true, tell the user they are logged in and show the username.
- If `logged_in` is false, tell the user they are not logged in and suggest `/login`.
