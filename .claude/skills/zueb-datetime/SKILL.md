---
name: zueb-datetime
description: 查询当前日期、时间、星期几。当用户问今天几号、今天星期几、现在几点、当前日期时间时触发。必须使用工具结果，不可自行推断星期。
---

# ZUEB Datetime

Run the helper script to query deterministic date/time:

```bash
.venv/bin/python bot/agent/helper.py datetime --timezone Asia/Shanghai
```

## Presenting Results

- Parse the JSON output.
- Use `date` + `weekday_cn` as the primary answer when users ask "今天是几号/星期几".
- Use `time` when users ask "现在几点".
- Prefer concise reply in Telegram style.
- Never infer weekday from memory; always trust tool output.
