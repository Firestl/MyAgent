from urllib.parse import parse_qs, urlparse

import httpx

from cli.config import DEFAULT_HEADERS, WEBHR_BASE_URL

_TARGET_URL = "App-apply/App-apply-kqcard/index"


class SSOError(Exception):
    pass


def _extract_query_value(query_params: dict, key: str) -> str:
    values = query_params.get(key) or []
    if not values:
        return ""
    return values[0]


def get_sso_credentials(id_token: str) -> dict:
    if not id_token:
        raise SSOError("id_token is required")

    headers = {
        **DEFAULT_HEADERS,
        "x-id-token": id_token,
        "Accept": "text/html",
    }

    params = {
        "token": id_token,
        "targetUrl": _TARGET_URL,
    }

    try:
        with httpx.Client(
            headers=headers, follow_redirects=True, timeout=30.0
        ) as client:
            resp = client.get(WEBHR_BASE_URL + "webhrN2SSOAPP", params=params)
            resp.raise_for_status()
    except httpx.HTTPError as exc:
        raise SSOError(f"SSO redirect request failed: {exc}") from exc

    final_url = str(resp.url)
    parsed = urlparse(final_url)
    query = parse_qs(parsed.query)

    user_code = _extract_query_value(query, "userCode")
    md5str = _extract_query_value(query, "md5Str")

    if not user_code or not md5str:
        raise SSOError(
            f"userCode/md5Str not found in final redirected URL: {final_url}"
        )

    return {
        "user_code": user_code,
        "md5str": md5str,
    }
