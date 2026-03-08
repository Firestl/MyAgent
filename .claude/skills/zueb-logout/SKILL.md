---
name: zueb-logout
description: 退出 ZUEB 登录状态。用于用户要求退出登录、注销校园系统会话、清除当前登录状态时。
---

# ZUEB Logout

需要退出当前会话时，调用 helper：

```bash
.venv/bin/python bot/agent/helper.py logout
```

## Instructions

- 解析 JSON 后确认结果，不要原样输出 JSON。
- 成功时，明确说明当前校园系统会话已清除。
- 若用户目的是切换账号，可在确认退出后提示重新执行登录。
