---
name: zueb-attendance-punch
description: 提交 ZUEB 真实打卡。仅在用户明确要求“帮我打卡”“现在打卡”“提交上班卡/下班卡”这类实际执行操作，并且已经明确确认可以提交时使用。
---

# ZUEB Attendance Punch

执行真实打卡前，先确认以下两点都成立：

1. 用户要求的是立即执行真实打卡，而不是仅查询状态。
2. 用户已经在当前对话中明确确认可以提交。

任一条件不满足时，不要调用提交命令；先用自然语言确认。

## Run

默认自动判断应打上班卡还是下班卡：

```bash
.venv/bin/python bot/agent/helper.py attendance-punch --mode auto --confirm yes
```

显式提交上班卡：

```bash
.venv/bin/python bot/agent/helper.py attendance-punch --mode sbk --confirm yes
```

显式提交下班卡：

```bash
.venv/bin/python bot/agent/helper.py attendance-punch --mode xbk --confirm yes
```

如需覆盖默认坐标，追加：

```bash
--xy "113.719755,34.615436"
```

## Instructions

- 未得到明确确认前，不要传入 `--confirm yes`。
- 若用户只是想看今天是否已打卡，改用 `zueb-attendance`。
- 解析 JSON 后先说明是否真的执行了提交，重点看 `executed`。
- 若 `executed` 为 `false`，直接解释原因，例如已打过卡或当前无需重复提交。
- 若 `executed` 为 `true`，展示 `mode_executed` 和 `message`。
- 可简要补充 `attendance_before` 中的上下班卡状态。
- 不要回显 token、签名、坐标以外的敏感字段。
