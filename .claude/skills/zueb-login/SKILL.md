---
name: zueb-login
description: 登录 ZUEB 校园系统。用于用户要求登录、重新登录、切换账号登录，或已经提供账号密码并希望建立校园系统会话时。
---

# ZUEB Login

用户已经提供账号和密码时，调用 helper：

```bash
.venv/bin/python bot/agent/helper.py login "<username>" "<password>"
```

## Instructions

- 用户只说“登录”但还未提供账号或密码时，先索取所需凭据，不要调用命令。
- 将用户提供的账号和密码替换到命令参数中，并保持引号包裹。
- 解析 JSON 后汇报结果，不要原样输出 JSON。
- 永远不要在回复中回显、复述或总结用户密码。
- 登录成功时，说明已登录；若 `user` 中有 `realName` 或 `name`，可一并展示。
- 登录失败时，展示错误摘要，并建议用户检查账号密码后重试。
