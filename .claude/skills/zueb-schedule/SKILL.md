---
name: zueb-schedule
description: 查询ZUEB课表。当用户问课表、课程安排、本周或下周有什么课时触发。
---

# ZUEB Schedule

Run the helper script to query the course schedule:

```bash
.venv/bin/python bot/agent/helper.py schedule [OPTIONS]
```

## Options

| Flag | Description |
|------|-------------|
| `--semester CODE` | Semester code, e.g. `20250` |
| `--year N` | Academic year start, e.g. `2025` |
| `--term 1\|2` | Term number (1 = first, 2 = second) |
| `--week N` | Week number |
| `--list` | List available semesters instead of schedule |

## Usage

- Default (no options): returns current week schedule.
- If the user asks about a specific week (e.g. "下周课表"), calculate the week number and pass `--week`.
- If the user asks to list semesters, use `--list`.
- `--year` and `--term` must be used together.

## Presenting Results

Parse the JSON output. The schedule data contains:
- `week1-7` keys: arrays of course entries for Monday through Sunday
- Each course has: `kcmc` (name), `skdd` (room), `rkjs` (teacher), `jcxx` (periods), `skzs` (weeks)
- `zc`: current week number, `xn`: year, `xq`: term

Present the schedule in a readable format grouped by day. Omit days with no courses.
