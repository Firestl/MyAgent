---
name: workday
description: 判断指定日期（默认今天）是工作日还是节假日，支持中国法定节假日和调休制度。用于回答"今天要上班吗""今天是工作日吗""明天放假吗""今天需要打卡吗""是否调休上班""这天是什么假""明天是不是节假日"等问题。
---

# Workday

查询某天是否为中国工作日时，调用 helper：

```bash
# 今天
.venv/bin/python bot/agent/helper.py workday

# 指定日期
.venv/bin/python bot/agent/helper.py workday --date 2026-05-01
```

## 返回字段

| 字段 | 说明 |
|------|------|
| `is_workday` | `true`=工作日，`false`=非工作日 |
| `type` | `workday`/`holiday`/`weekend`/`adjusted_workday` |
| `holiday_name` | 节假日名称，如"春节"；普通周末为 `null` |
| `weekday_cn` | 星期几（中文） |

## 日期类型说明

- `workday`：普通工作日
- `holiday`：法定节假日（有 `holiday_name`）
- `weekend`：普通周末（无 `holiday_name`）
- `adjusted_workday`：调休上班（周末但需正常上班，`holiday_name` 为对应节假日）

## Instructions

- 优先调用工具，不要凭记忆推断节假日安排。
- 若用户问"今天"，直接调用无参版本（默认取上海时区当天）。
- 回复简洁，直接给结论，如："今天（3月9日，星期日）是普通周末，不用上班。"
- 若 `type=adjusted_workday`，明确提示"今天是调休工作日，需要正常上班"。
- 若用户接着询问打卡状态，切换到 `zueb-attendance`。
