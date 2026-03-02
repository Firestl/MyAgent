---
name: zueb-attendance
description: 查询今日打卡考勤状态。当用户问打卡、考勤、签到时触发。
---

# ZUEB Attendance

Run the helper script to check today's attendance:

```bash
.venv/bin/python bot/agent/helper.py attendance
```

## Presenting Results

Parse the JSON output. The attendance data contains:
- `sbk_time`: clock-in time (e.g. "08:30") or empty if not punched
- `xbk_time`: clock-out time (e.g. "17:30") or empty if not punched
- `sbk_done`: true if clocked in
- `xbk_done`: true if clocked out
- `all_done`: true if both done

Present clearly:
- If not clocked in yet, remind the user.
- If clocked in but not out, show clock-in time and remind about clock-out.
- If both done, confirm with times.
