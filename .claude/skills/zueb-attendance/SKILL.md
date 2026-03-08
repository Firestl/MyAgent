---
name: zueb-attendance
description: 查询今日打卡考勤状态。用于回答“打卡了吗”“今天是否已打卡”“查询考勤”“上班卡/下班卡状态”“今天还要不要打卡”这类只读问题，不执行真实提交。
---

# ZUEB Attendance

查询今日打卡状态时，调用 helper：

```bash
.venv/bin/python bot/agent/helper.py attendance
```

## Instructions

- 解析 JSON 后再回答，不要原样输出 JSON。
- 重点字段包括：`sbk_time`、`xbk_time`、`sbk_done`、`xbk_done`、`all_done`。
- 若上班卡未打，明确提醒用户。
- 若已打上班卡但未打下班卡，展示上班打卡时间并提醒下班卡。
- 若上下班卡都已完成，明确说明并展示时间。
- 若用户接着要求“现在帮我打卡”，切换到 `zueb-attendance-punch`。
