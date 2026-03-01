"""HTTP client for JWXT (教务系统) API."""

import hashlib
import math
import re
import time
from typing import Any

import httpx

from cli.config import JWXT_BASE_URL


class JWXTClientError(Exception):
    """JWXT client operation error."""


class JWXTClient:
    """HTTP client for JWXT course schedule system."""

    BASE_URL = JWXT_BASE_URL
    _DEFAULT_ENCRYPT_KEY = "1tkdum1tkcbb"
    _REQUEST_TYPE = "kbvueh5"
    _STEP_XNXQ = "xnxq"
    _STEP_DETAIL = "detail"

    def __init__(self, jsessionid: str, login_id: str = "", user_type: str = ""):
        """
        Initialize JWXT client with session cookie.

        Args:
            jsessionid: JSESSIONID cookie value from SSO
            login_id: Optional fallback user id (2091099 style)
            user_type: Optional fallback user type (TEA/STU)
        """
        self.jsessionid = jsessionid
        self.login_id = login_id
        self.user_type = user_type
        self.school_code = ""
        self.encrypt_key = self._DEFAULT_ENCRYPT_KEY

        self._client = httpx.Client(
            base_url=self.BASE_URL,
            timeout=30.0,
            headers={
                "User-Agent": (
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 18_7 like Mac OS X) "
                    "AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 "
                    "SuperApp SuperApp-13508"
                ),
                "Referer": f"{self.BASE_URL}/h5/index.html",
            },
            cookies={"JSESSIONID": jsessionid},
        )
        self._bootstrap_context()

    def close(self) -> None:
        """Close underlying HTTP client."""
        self._client.close()

    def __enter__(self) -> "JWXTClient":
        return self

    def __exit__(self, *args) -> None:
        self.close()

    def _bootstrap_context(self) -> None:
        """Load runtime encryption/user context from H5 bootstrap JS."""
        # Keep behavior close to app traffic: visit index first, then context JS.
        self._client.get("/h5/index.html")
        resp = self._client.get("/custom/js/SetRootPath4H5.jsp")
        resp.raise_for_status()

        script = resp.text
        self.encrypt_key = self._extract_js_var(script, "G_ENCRYPT") or self.encrypt_key
        self.login_id = self._extract_js_var(script, "G_LOGIN_ID") or self.login_id
        self.user_type = self._extract_js_var(script, "G_USER_TYPE") or self.user_type
        self.school_code = (
            self._extract_js_var(script, "G_SCHOOL_CODE") or self.school_code
        )
        user_code = self._extract_js_var(script, "G_USER_CODE")

        if not self.user_type:
            self.user_type = self._infer_user_type_from_user_code(user_code)

        if not self.login_id:
            raise JWXTClientError("missing G_LOGIN_ID from SetRootPath4H5.jsp")

    @staticmethod
    def _extract_js_var(script: str, name: str) -> str:
        # SetRootPath4H5.jsp uses: var NAME = 'value';
        # Keep regex tolerant to spaces and quote style.
        match = re.search(
            rf"var\s+{re.escape(name)}\s*=\s*['\"]([^'\"]*)['\"]\s*;", script
        )
        return match.group(1) if match else ""

    @staticmethod
    def _infer_user_type_from_user_code(user_code: str) -> str:
        # Common pattern in many deployments: txxxx -> TEA, sxxxx -> STU.
        if not user_code:
            return ""
        first = user_code[0].lower()
        if first == "t":
            return "TEA"
        if first == "s":
            return "STU"
        return ""

    @staticmethod
    def _to_base36(value: int) -> str:
        if value == 0:
            return "0"
        if value < 0:
            return "-" + JWXTClient._to_base36(-value)
        digits = "0123456789abcdefghijklmnopqrstuvwxyz"
        out = []
        n = value
        while n > 0:
            out.append(digits[n % 36])
            n //= 36
        return "".join(reversed(out))

    @staticmethod
    def _get_md5_2(plain: str) -> str:
        # JS getMd5_2:
        #   h1 = md5(plain)
        #   remove chars at 1-index positions 3,10,17,25
        #   return md5(filtered)
        h1 = hashlib.md5(plain.encode("utf-8")).hexdigest()
        filtered = "".join(
            ch for i, ch in enumerate(h1, start=1) if i not in {3, 10, 17, 25}
        )
        return hashlib.md5(filtered.encode("utf-8")).hexdigest()

    @classmethod
    def _of_encrypt(cls, plain: str, key: str) -> str:
        # Port of module 76e3/of_encrypt from H5 bundle.
        if not plain or not key:
            return plain

        key_len = len(key)
        data_len = len(plain)
        rounds = math.ceil(data_len / key_len)
        cipher_len = 6 * math.ceil(data_len / 3)
        offset = cipher_len % key_len

        decimal_stream = ""
        for round_index in range(rounds):
            for key_index in range(1, key_len + 1):
                pos = round_index * key_len + key_index
                if pos > data_len:
                    break
                data_ch = plain[pos - 1]
                key_ch = key[key_index - 1]
                mixed = ord(data_ch) + ord(key_ch) + offset
                decimal_stream += f"{mixed:03d}"[-3:]

        encoded = ""
        cursor = 0
        while cursor < len(decimal_stream):
            chunk = decimal_stream[cursor : cursor + 9]
            cursor += 9
            base36 = cls._to_base36(int(chunk))
            encoded += ("000000" + base36)[-6:]
        return encoded

    def _build_plain_payload(self, step: str, payload: dict[str, Any]) -> str:
        """
        Build plaintext query string exactly like JWXT H5 request interceptor.

        The order matters because `param2` is derived from this raw query string.
        """
        data: dict[str, Any] = {
            "action": "jw_apply",
            "type": self._REQUEST_TYPE,
            "step": step,
        }

        # Keep insertion order from caller payload, mirroring JS object behavior.
        data.update(payload)

        if not data.get("userid"):
            data["userid"] = self.login_id
        if not data.get("userId"):
            data["userId"] = self.login_id
        if not data.get("yhzh"):
            data["yhzh"] = self.login_id
        if not data.get("usertype"):
            data["usertype"] = self.user_type

        # Config extraParam for kbvueh5.detail in module 3e1a.
        if step == self._STEP_DETAIL:
            data.setdefault("bjdm", "")
            data.setdefault("jsdm", "")

        parts = [f"{k}={v}" for k, v in data.items()]
        return "&".join(parts)

    def _jw_apply_get(self, endpoint: str, step: str, payload: dict[str, Any]) -> dict:
        plain = self._build_plain_payload(step, payload)
        params = {
            "action": "jw_apply",
            "param": self._of_encrypt(plain, self.encrypt_key),
            "param2": self._get_md5_2(plain),
            "timestamp": str(int(time.time() * 1000)),
        }

        resp = self._client.get(endpoint, params=params)
        resp.raise_for_status()
        return resp.json()

    def get_semester_items(self) -> list[dict[str, Any]]:
        """
        Return raw semester list items from getxnxq_xl.

        Each item usually contains:
            - dm: semester code (e.g. 20250 / 20251)
            - mc: display name
            - dqxq: current-semester marker ("1" means current)
        """
        data = self.get_semester_list()
        items = data.get("xnxq", [])
        if not isinstance(items, list):
            raise JWXTClientError(
                "unexpected semester list response: xnxq is not a list"
            )
        return [item for item in items if isinstance(item, dict)]

    def resolve_semester_code(self, year: int, term: int) -> str:
        """
        Resolve semester code from year/term.

        Args:
            year: academic year start, e.g. 2025 for "2025-2026学年"
            term: 1 (第一学期) or 2 (第二学期)
        """
        if term not in (1, 2):
            raise JWXTClientError("term must be 1 or 2")

        items = self.get_semester_items()

        # Common convention in this deployment: 20250 -> 第一学期, 20251 -> 第二学期.
        target_dm = f"{year}{0 if term == 1 else 1}"
        for item in items:
            if str(item.get("dm", "")) == target_dm:
                return target_dm

        # Fallback by display name.
        year_label = f"{year}-{year + 1}学年"
        term_label = "第一学期" if term == 1 else "第二学期"
        for item in items:
            mc = str(item.get("mc", ""))
            dm = str(item.get("dm", ""))
            if dm and year_label in mc and term_label in mc:
                return dm

        raise JWXTClientError(
            f"cannot resolve semester code for year={year}, term={term}"
        )

    def get_semester_list(self) -> dict:
        """
        Get available semester list with jw_apply encrypted parameters.

        Returns:
            Response JSON with semester list

        Raises:
            httpx.HTTPError: If request fails
        """
        return self._jw_apply_get("/wap/getxnxq_xl.action", self._STEP_XNXQ, {})

    def get_course_schedule(
        self, semester_code: str | None = None, week: str | None = None
    ) -> dict:
        """
        Get course schedule for specified semester.

        Args:
            semester_code: Semester code (e.g., "20251"), None for current
            week: Optional explicit week parameter

        Returns:
            Response JSON with course schedule data

        Raises:
            httpx.HTTPError: If request fails
        """
        semester_items = self.get_semester_items()
        current_dm = ""
        for item in semester_items:
            if str(item.get("dqxq", "")) == "1":
                current_dm = str(item.get("dm", ""))
                break

        if semester_code is None:
            if not current_dm:
                raise JWXTClientError(
                    "cannot determine current semester from getxnxq_xl"
                )
            semester_code = current_dm
            if not semester_code:
                raise JWXTClientError("current semester has empty dm")

        if week is None:
            # Matches packet behavior:
            # - current semester uses empty week
            # - non-current/future semester commonly uses week=1
            week = "" if semester_code == current_dm else "1"

        payload = {"xnxq": semester_code, "week": week}
        return self._jw_apply_get(
            "/wap/mycourseschedule.action", self._STEP_DETAIL, payload
        )
