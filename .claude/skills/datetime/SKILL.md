---
name: datetime
description: 查询当前日期、时间、星期几。用于回答今天几号、今天星期几、现在几点、当前日期时间、北京时间等需要实时日期时间的问题。
---

# Datetime

查询实时日期时间时，调用 helper：

```bash
.venv/bin/python bot/agent/helper.py datetime --timezone Asia/Shanghai
```

## Instructions

- 始终依赖工具结果，不要自行推断日期、时间或星期。
- 默认使用 `Asia/Shanghai`；只有用户明确指定其他时区时才改用对应时区。
- 解析 JSON 后再回答，不要原样输出 JSON。
- 用户问“今天几号/星期几”时，优先使用 `date` 和 `weekday_cn`。
- 用户问“现在几点”时，优先使用 `time`。
- 回复保持简洁，适合 Telegram 聊天场景。
