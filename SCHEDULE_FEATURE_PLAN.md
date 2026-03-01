# Schedule 功能开发上下文（开发者版）

目标：实现 `schedule detail`、`schedule next`、`schedule today`。

## 一、先看哪些文件（按顺序）

### 1) 先理解现有入口
- `cli/main.py`
- 重点看：`schedule` 命令参数、`_print_schedule` 输出结构。
- 目的：确认新增参数放在哪里、输出从哪里改。

### 2) 再看业务编排
- `cli/schedule/service.py`
- 重点看：`get_schedule(...)`、`_build_client(...)`。
- 目的：确认新增逻辑是放 service 层（推荐），不是直接堆在 CLI 层。

### 3) 再看协议实现
- `cli/schedule/client.py`
- 重点看：
  - `_jw_apply_get(...)`（统一请求入口）
  - `_build_plain_payload(...)`（明文拼接顺序）
  - `get_course_schedule(...)`（当前已支持 `semester_code/week`）
- 目的：新增 endpoint 时，沿用同一签名链路，避免重复造轮子。

### 4) 再看 SSO 前置
- `cli/schedule/sso.py`
- 重点看：JSESSIONID 获取流程。
- 目的：排错时快速判断是 SSO 问题还是 JWXT 参数问题。

## 二、必须对照的抓包文件（协议真相）

### A. 学期列表（xnxq）
- `CapturePacket/39_97_1772362933364/request_header_raw.txt`
- `CapturePacket/39_97_1772362933364/response_body_raw`
- 你要提取的信息：
  - 请求：`/wap/getxnxq_xl.action?action=jw_apply...`
  - 响应：`xnxq[].dm/mc/dqxq`

### B. 课表主接口（detail）
- 当前学期：
  - `CapturePacket/39_98_1772362934019/request_header_raw.txt`
  - `CapturePacket/39_98_1772362934019/response_body_raw`
- 非当前学期：
  - `CapturePacket/39_101_1772362940875/request_header_raw.txt`
  - `CapturePacket/39_101_1772362940875/response_body_raw`
- 你要提取的信息：
  - `step=detail`
  - `xnxq` 与 `week` 关系：当前学期 `week=` 空；非当前学期常见 `week=1`
  - 响应结构：`zc/maxzc/week1..week7/sjhjinfo`

### C. 备注接口（kb_kbdetail_bz）
- `CapturePacket/39_100_1772362936662/request_header_raw.txt`
- `CapturePacket/39_100_1772362936662/response_body_raw`
- `CapturePacket/39_102_1772362943215/request_header_raw.txt`
- `CapturePacket/39_102_1772362943215/response_body_raw`
- 你要提取的信息：
  - `step=kb_kbdetail_bz`
  - 参数：`xnxq` + `zc`
  - 响应：`{"bz": [...]}`

### D. H5 运行时上下文
- `CapturePacket/39_96_1772362930815/response_body_raw`
- 你要提取的信息：`G_ENCRYPT/G_LOGIN_ID/G_USER_TYPE/G_SCHOOL_CODE` 来源。

## 三、3 个功能具体该改哪些文件

## 1) schedule detail

改动文件：
- `cli/schedule/client.py`
- `cli/schedule/service.py`
- `cli/main.py`

最小改动：
1. client 新增：`get_schedule_detail_notes(semester_code: str, week: str)`
   - endpoint: `/wap/kb_kbdetail_bz.action`
   - step: `kb_kbdetail_bz`
   - payload: `xnxq`, `zc`
2. service 在现有 `get_schedule(...)` 基础上新增组合函数（如 `get_schedule_with_detail(...)`）
   - 先拿课表，再拿 `bz`
3. main 在输出末尾追加“备注/调课信息”区块。

## 2) schedule next

改动文件：
- `cli/main.py`
- `cli/schedule/service.py`
- （可选）`cli/schedule/client.py`

最小改动：
1. main 增加 `--next` 参数（与 `--today` 互斥）
2. service 新增 `get_next_week_schedule(...)`
   - 先取本次课表，读 `zc/maxzc`
   - `next_zc = int(zc) + 1`
   - 若 `next_zc <= maxzc`，同学期再查一次（显式 `week`）
   - 越界策略先做成“明确报错提示，不自动跨学期”

## 3) schedule today

改动文件：
- `cli/main.py`
- `cli/schedule/service.py`

最小改动：
1. main 增加 `--today` 参数（与 `--next` 互斥）
2. service 新增 `get_today_courses(...)`
   - 先拿本周课表
   - `weekday = date.today().isoweekday()`
   - 返回 `week{weekday}` 数据
3. main 增加 today 专用输出函数（只打印当天课）。

## 四、实现前必须确定的 2 个规则

1. `--next` 周次越界策略
- v1 建议：直接提示“已到学期末，未自动跨学期”。

2. `--today` 时间基准
- v1 建议：使用本机本地时区日期；不做服务器时区换算。

## 五、最小联调命令

- 列学期：
```bash
uv run python -m cli schedule --list-semesters
```

- 指定学期：
```bash
uv run python -m cli schedule --semester 20251
```

- 按学年学期：
```bash
uv run python -m cli schedule --year 2025 --term 2
```

- 默认当前学期：
```bash
uv run python -m cli schedule
```

