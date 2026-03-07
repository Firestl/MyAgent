"""
SSO credential exchange for the WebHR attendance system.

The WebHR system (rsxt1.zueb.edu.cn/webhr/) uses a separate SSO flow from
the JWXT academic system.  After the main CAS login, the client calls
`webhrN2SSOAPP` with the id_token to get a redirect to the actual attendance
page.  The redirected URL contains `userCode` and `md5Str` query parameters
that act as session credentials for subsequent signed API calls.

Flow:
    GET webhrN2SSOAPP?token=<id_token>&targetUrl=<attendance-path>
    → follows redirects → final URL contains userCode + md5Str
"""

import logging
from urllib.parse import parse_qs, urlparse

import httpx

from cli.config import DEFAULT_HEADERS, WEBHR_BASE_URL
from cli.types import SSOCredentials

logger = logging.getLogger(__name__)

# The target attendance card page path within the WebHR app.
_TARGET_URL = "App-apply/App-apply-kqcard/index"


class SSOError(Exception):
    pass


def _extract_query_value(query_params: dict[str, list[str]], key: str) -> str:
    """Safely extract the first value for a query parameter key.

    Returns an empty string if the key is absent or has no values,
    avoiding KeyError / IndexError on unexpected redirect URLs.
    """
    values = query_params.get(key) or []
    if not values:
        return ""
    return values[0]


def get_sso_credentials(id_token: str) -> SSOCredentials:
    """Exchange the CAS id_token for WebHR SSO credentials.

    Follows the redirect chain from webhrN2SSOAPP to the attendance app page.
    Extracts `userCode` and `md5Str` from the final URL's query string;
    these are passed to `generate_signature` and the WebHR token endpoint.

    Args:
        id_token: JWT token obtained from the main CAS login.

    Returns:
        dict with keys:
            user_code — WebHR user identifier (e.g. "E12345")
            md5str    — Session credential used in signature payloads

    Raises:
        SSOError: If the request fails or the expected parameters are absent.
    """
    if not id_token:
        raise SSOError("id_token is required")

    logger.info("Requesting WebHR SSO credentials...")

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
            logger.debug("Following SSO redirects to WebHR...")
            resp = client.get(WEBHR_BASE_URL + "webhrN2SSOAPP", params=params)
            resp.raise_for_status()
    except httpx.HTTPError as exc:
        raise SSOError(f"SSO redirect request failed: {exc}") from exc

    # After following all redirects, the final URL should contain the credentials.
    final_url = str(resp.url)
    logger.debug("SSO final URL: %s", final_url[:120])
    parsed = urlparse(final_url)
    query = parse_qs(parsed.query)

    user_code = _extract_query_value(query, "userCode")
    md5str = _extract_query_value(query, "md5Str")

    if not user_code or not md5str:
        raise SSOError(
            f"userCode/md5Str not found in final redirected URL: {final_url}"
        )

    logger.debug("Extracted user_code=%s, md5str=%s...", user_code, md5str[:8])
    return {
        "user_code": user_code,
        "md5str": md5str,
    }
