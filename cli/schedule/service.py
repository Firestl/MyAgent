"""Schedule service orchestration (token -> SSO -> JWXT API)."""

import base64
import json
import logging

from cli.schedule.client import JWXTClient
from cli.schedule.sso import SSOError, get_jwxt_session
from cli.types import JSONObject, ScheduleData, SemesterItem

logger = logging.getLogger(__name__)


class ScheduleError(Exception):
    """Course schedule operation error."""


def _decode_token_payload(id_token: str) -> JSONObject:
    """Best-effort JWT payload decode without signature verification.

    Args:
        id_token: Raw JWT string from login flow.

    Returns:
        Decoded payload dict when parsing succeeds; otherwise an empty dict.
    """
    logger.debug("Decoding JWT payload...")
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
    """Best-effort extraction of login id for JWXT fallback identity fields.

    Args:
        id_token: Raw JWT string from login flow.

    Returns:
        Login id string (for example, student/staff number), or empty string.
    """
    payload = _decode_token_payload(id_token)
    if not payload:
        return ""

    raw_id = payload.get("ATTR_userNo") or payload.get("sub") or ""
    login_id = raw_id if isinstance(raw_id, str) else str(raw_id)
    logger.debug("Extracted login_id=%s from token", login_id)
    return login_id


def _extract_user_type_from_token(id_token: str) -> str:
    """Best-effort extraction of JWXT user type from token payload.

    Args:
        id_token: Raw JWT string from login flow.

    Returns:
        One of `TEA`/`STU`/`NST` when recognized, otherwise empty string.
    """
    payload = _decode_token_payload(id_token)
    if not payload:
        return ""

    direct = payload.get("usertype") or payload.get("userType")
    if isinstance(direct, str) and direct in {"TEA", "STU", "NST"}:
        logger.debug("Extracted user_type=%s from token", direct)
        return direct

    code = payload.get("ATTR_identityTypeCode") or payload.get("ATTR_identityTypeId")
    if isinstance(code, str):
        code = code.upper()
        if code.startswith("T"):
            logger.debug("Extracted user_type=%s from token", "TEA")
            return "TEA"
        if code.startswith("S"):
            logger.debug("Extracted user_type=%s from token", "STU")
            return "STU"
        if code.startswith("N"):
            logger.debug("Extracted user_type=%s from token", "NST")
            return "NST"

    name = payload.get("ATTR_identityTypeName")
    if isinstance(name, str):
        if "教" in name or "职工" in name:
            logger.debug("Extracted user_type=%s from token", "TEA")
            return "TEA"
        if "学" in name:
            logger.debug("Extracted user_type=%s from token", "STU")
            return "STU"

    return ""


def _build_client(id_token: str) -> JWXTClient:
    """Create an authenticated JWXT client from CAS id_token."""
    logger.debug("Building JWXT client: SSO → JSESSIONID...")
    try:
        jsessionid = get_jwxt_session(id_token)
    except SSOError as e:
        raise ScheduleError(f"SSO authentication failed: {e}")

    login_id = _extract_login_id_from_token(id_token)
    user_type = _extract_user_type_from_token(id_token)
    logger.debug("JWXT client ready (login_id=%s, user_type=%s)", login_id, user_type)
    return JWXTClient(
        jsessionid,
        login_id=login_id,
        user_type=user_type,
    )


def get_available_semesters(id_token: str) -> list[SemesterItem]:
    """Get selectable semester items from getxnxq_xl.

    Args:
        id_token: JWT token from login.

    Returns:
        Raw semester item list (each item is a dict containing at least `dm` and `mc`).

    Raises:
        ScheduleError: If SSO/JWXT request fails.
    """
    logger.debug("Fetching available semesters...")
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
) -> ScheduleData:
    """
    Fetch schedule with one of:
    - default current semester/week behavior
    - explicit semester code
    - year + term (resolved via getxnxq_xl)
    - optional explicit week (validated against maxzc)

    Args:
        id_token: JWT token from login
        semester_code: Direct semester code, e.g. "20250"
        year: academic year start, e.g. 2025
        term: 1 for 第一学期, 2 for 第二学期
        week: explicit week number, e.g. 3
    """
    logger.debug("Fetching schedule (semester=%s, year=%s, term=%s, week=%s)", semester_code, year, term, week)
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

            # Explicit week query is sent once and returned directly.
            # Some out-of-range week queries return a shell payload without maxzc.
            data = client.get_course_schedule(
                semester_code=resolved_code, week=str(week)
            )
            maxzc_raw = data.get("maxzc")
            try:
                maxzc = int(str(maxzc_raw))
            except (TypeError, ValueError):
                # Fallback to baseline request to recover maxzc for validation.
                baseline = client.get_course_schedule(semester_code=resolved_code)
                baseline_maxzc_raw = baseline.get("maxzc")
                try:
                    maxzc = int(str(baseline_maxzc_raw))
                except (TypeError, ValueError):
                    raise ScheduleError("invalid maxzc in schedule response")

            if week > maxzc:
                raise ScheduleError(f"week {week} out of range: 1..{maxzc}")

            return data
    except ScheduleError:
        raise
    except Exception as e:
        raise ScheduleError(f"Failed to fetch schedule: {e}")


def get_current_schedule(id_token: str) -> ScheduleData:
    """Backward-compatible alias of get_schedule(id_token)."""
    return get_schedule(id_token)
