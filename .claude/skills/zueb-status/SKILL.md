---
name: zueb-status
description: 查看当前 ZUEB 登录状态。用于回答“我现在登录了吗”“校园系统还在线吗”“当前账号是谁”“会话还在吗”这类会话状态问题。
---

# ZUEB Status

查询当前登录状态时，调用 helper：

```bash
.venv/bin/python bot/agent/helper.py status
```

## Instructions

- 解析 JSON 后再回答，不要原样输出 JSON。
- 若 `logged_in` 为 `true`，说明当前已登录，并展示 `username`。
- 若 `logged_in` 为 `false`，说明当前未登录，并提示先执行 `/login`。
- 不要主动展示 `device_id`，除非用户明确追问会话细节。
