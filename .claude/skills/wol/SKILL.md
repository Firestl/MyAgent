---
name: wol
description: 远程唤醒台式电脑（Wake-on-LAN）。用于用户要求开机、唤醒台式机、远程开机、发送 WOL 魔术包、启动家里电脑时。
---

# WOL

需要远程唤醒台式电脑时，调用 helper：

```bash
.venv/bin/python bot/agent/helper.py wol
```

## Instructions

- 解析 JSON 后给出结果，不要原样输出 JSON。
- 成功时，明确说明魔术包已发送，台式机通常会在约 30 秒内启动。
- 失败时，展示错误摘要，并建议检查路由器连通性或稍后重试。
- 明确说明该操作只能发送唤醒包，不能确认电脑一定已经成功开机。
- 不要展开 SSH 或路由器实现细节，除非用户继续追问。
