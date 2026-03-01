import json

import httpx

from cli.config import DEFAULT_HEADERS, WEBHR_BASE_URL


class WebHRError(Exception):
    pass


def _parse_json_response(response: httpx.Response, action: str) -> dict:
    try:
        response.raise_for_status()
    except httpx.HTTPStatusError as exc:
        body = response.text.strip()
        raise WebHRError(
            f"{action} failed with status {response.status_code}: {body or exc}"
        ) from exc

    try:
        return response.json()
    except json.JSONDecodeError as exc:
        raise WebHRError(f"{action} returned non-JSON response") from exc


class WebHRClient:
    def __init__(self):
        self._client = httpx.Client(
            headers=DEFAULT_HEADERS,
            follow_redirects=True,
            timeout=30.0,
        )

    def get_webhrtoken(
        self,
        user_code: str,
        md5str: str,
        signature: str,
        timestamp: int,
    ) -> str:
        headers = {
            "Content-Type": "application/json",
            "Accept": "*/*",
            "Origin": "https://rsxt1.zueb.edu.cn",
            "Referer": (
                "https://rsxt1.zueb.edu.cn/webhrApp/static/loginsso.html"
                f"?userCode={user_code}&md5Str={md5str}"
                "&targetUrl=App-apply/App-apply-kqcard/index"
            ),
            "webhrtoken": "null",
        }
        payload = {
            "userCode": user_code,
            "md5Str": md5str,
            "signature": signature,
            "timestamp": timestamp,
        }

        response = self._client.post(
            WEBHR_BASE_URL + "appLoginsso",
            headers=headers,
            json=payload,
        )
        data = _parse_json_response(response, "appLoginsso")

        token = ((data.get("data") or {}).get("data") or {}).get("token")
        if not token:
            raise WebHRError("appLoginsso response missing data.data.token")
        return token

    def get_kqcard_info(
        self,
        webhrtoken: str,
        signature: str,
        timestamp: int,
    ) -> dict:
        headers = {
            "Content-Type": "application/json",
            "Accept": "*/*",
            "Origin": "https://rsxt1.zueb.edu.cn",
            "Referer": "https://rsxt1.zueb.edu.cn/webhrApp/",
            "cookie": f"token={webhrtoken}",
        }

        payload = {
            "webhrtoken": webhrtoken,
            "signature": signature,
            "timestamp": timestamp,
        }

        response = self._client.post(
            WEBHR_BASE_URL + "AppKqCard/getKqCardInfo",
            headers=headers,
            json=payload,
        )
        return _parse_json_response(response, "getKqCardInfo")

    def close(self):
        self._client.close()

    def __enter__(self):
        return self

    def __exit__(self, *args):
        self.close()
