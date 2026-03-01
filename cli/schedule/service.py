"""Course schedule service logic."""

import base64
import json

from cli.schedule.client import JWXTClient
from cli.schedule.sso import SSOError, get_jwxt_session


class ScheduleError(Exception):
    """Course schedule operation error."""


def _decode_token_payload(id_token: str) -> dict:
    """Best-effort JWT payload decode without signature verification."""
    parts = id_token.split(".")
    if len(parts) < 2:
        return {}

    payload_b64 = parts[1]
    payload_b64 += "=" * (-len(payload_b64) % 4)

    try:
        payload = json.loads(base64.urlsafe_b64decode(payload_b64))
    except Exception:
        return {}

    if not isinstance(payload, dict):
        return {}
    return payload


def _extract_login_id_from_token(id_token: str) -> str:
    """Best-effort extraction of user id from JWT payload without verification."""
    payload = _decode_token_payload(id_token)
    if not payload:
        return ""

    return payload.get("ATTR_userNo") or payload.get("sub") or ""


def _extract_user_type_from_token(id_token: str) -> str:
    """Best-effort extraction of JWXT user type (TEA/STU/NST) from token payload."""
    payload = _decode_token_payload(id_token)
    if not payload:
        return ""

    direct = payload.get("usertype") or payload.get("userType")
    if isinstance(direct, str) and direct in {"TEA", "STU", "NST"}:
        return direct

    code = payload.get("ATTR_identityTypeCode") or payload.get("ATTR_identityTypeId")
    if isinstance(code, str):
        code = code.upper()
        if code.startswith("T"):
            return "TEA"
        if code.startswith("S"):
            return "STU"
        if code.startswith("N"):
            return "NST"

    name = payload.get("ATTR_identityTypeName")
    if isinstance(name, str):
        if "教" in name or "职工" in name:
            return "TEA"
        if "学" in name:
            return "STU"

    return ""


def _build_client(id_token: str) -> JWXTClient:
    """Create authenticated JWXT client from id_token."""
    try:
        jsessionid = get_jwxt_session(id_token)
    except SSOError as e:
        raise ScheduleError(f"SSO authentication failed: {e}")

    return JWXTClient(
        jsessionid,
        login_id=_extract_login_id_from_token(id_token),
        user_type=_extract_user_type_from_token(id_token),
    )


def get_available_semesters(id_token: str) -> list[dict]:
    """Get all selectable semesters from JWXT."""
    try:
        with _build_client(id_token) as client:
            return client.get_semester_items()
    except ScheduleError:
        raise
    except Exception as e:
        raise ScheduleError(f"Failed to fetch semester list: {e}")


def get_schedule(
    id_token: str,
    semester_code: str | None = None,
    year: int | None = None,
    term: int | None = None,
    week: int | None = None,
) -> dict:
    """
    Get schedule by semester code or by year/term.

    Args:
        id_token: JWT token from login
        semester_code: Direct semester code, e.g. "20250"
        year: academic year start, e.g. 2025
        term: 1 for 第一学期, 2 for 第二学期
        week: explicit week number, e.g. 3
    """
    if semester_code and (year is not None or term is not None):
        raise ScheduleError("use either semester_code or year+term, not both")
    if (year is None) != (term is None):
        raise ScheduleError("year and term must be provided together")

    try:
        with _build_client(id_token) as client:
            resolved_code = semester_code
            if year is not None and term is not None:
                resolved_code = client.resolve_semester_code(year, term)

            if week is None:
                return client.get_course_schedule(semester_code=resolved_code)

            baseline = client.get_course_schedule(semester_code=resolved_code)
            maxzc_raw = baseline.get("maxzc")
            try:
                maxzc = int(str(maxzc_raw))
            except (TypeError, ValueError):
                raise ScheduleError("invalid maxzc in schedule response")

            if week > maxzc:
                raise ScheduleError(f"week {week} out of range: 1..{maxzc}")

            return client.get_course_schedule(
                semester_code=resolved_code, week=str(week)
            )
    except ScheduleError:
        raise
    except Exception as e:
        raise ScheduleError(f"Failed to fetch schedule: {e}")


def get_current_schedule(id_token: str) -> dict:
    """
    Get current semester course schedule.

    Args:
        id_token: JWT token from login

    Returns:
        dict with schedule data:
            - xn: 学年 (e.g., "2025")
            - xq: 学期 (0=第一学期, 1=第二学期)
            - maxzc: 最大周次
            - zc: 当前周
            - qssj: 起始时间
            - jssj: 结束时间
            - maxjc: 最大节次
            - week1-7: 周一到周日的课程列表
            - sjhjinfo: 实践环节信息

    Raises:
        ScheduleError: If schedule retrieval fails
    """
    return get_schedule(id_token)
