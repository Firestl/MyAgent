---
name: zueb-schedule
description: 查询 ZUEB 课表和课程安排。用于回答课表、本周或下周有什么课、第几节课、上课时间、某天有没有课、教室在哪、可选学期列表等问题，并按地点批次和节次给出具体上课时间。
---

# ZUEB Schedule

查询课表时，调用 helper：

```bash
.venv/bin/python bot/agent/helper.py schedule [OPTIONS]
```

## Options

| Flag | Description |
|------|-------------|
| `--semester CODE` | 学期代码，例如 `20250` |
| `--year N` | 学年起始年，例如 `2025` |
| `--term 1\|2` | 学期编号，`1` 为第一学期，`2` 为第二学期 |
| `--week N` | 指定周次 |
| `--list` | 仅列出可选学期 |

## Instructions

- 用户只问当前课表或本周课表时，先直接无参查询。
- 用户要求查看可选学期时，使用 `--list`。
- 用户给出“第 N 周”这类明确周次时，直接传 `--week N`。
- 用户给出“下周/上周”这类相对周次时，先无参查询拿到当前 `zc`，再换算目标周次并重新调用 `--week`。
- 使用 `--year` 和 `--term` 时，必须同时提供；不要与 `--semester` 混用。
- 若目标周次超出接口返回范围，直接说明该周次无效或超出学期范围。

## Input JSON Fields

- `week1-7`：周一到周日的课程数组。
- 课程项通常包含：
  - `kcmc`：课程名
  - `skdd`：上课地点
  - `rkjs`：教师
  - `jcxx`：节次文本，例如 `3-4节`
  - `skzs`：教学周范围
- `zc`：当前周次；`xn`：学年；`xq`：学期。

## Staggered-Time Rules

必须根据 `skdd` 和 `jcxx` 推导具体上课时间。地点批次和节次时间表见 [TIME_TABLE.md](TIME_TABLE.md)。

## Presenting Results

- 按星期分组，省略空白日期。
- 每门课至少给出：课程名、地点、`jcxx`、推导后的具体时间。
- 若只能回答某一天的课，只返回该天内容。
- 若该周或该天无课，明确说明没有课程安排。
- 回复保持简洁，适合 Telegram。
