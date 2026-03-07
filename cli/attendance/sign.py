"""
RSA request-signing for the WebHR attendance system.

The WebHR app (rsxt1.zueb.edu.cn/webhr/) verifies every API call with an
RSA-SHA256 signature so it can authenticate the client without transmitting
a password on each request.

How the key was obtained:
  The private key below was extracted from the Supwisdom SuperApp APK.
  It is embedded in the app and used by all clients of this deployment.
  It is NOT a user-specific secret — it is a shared app-level signing key.

Signature payload format:
  "{md5str}&{user_code}&{timestamp}"
  where md5str and user_code come from the WebHR SSO redirect URL,
  and timestamp is the current Unix epoch (seconds).
"""

import base64
import logging
import time

from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import padding, rsa

from cli.types import SignatureResult

logger = logging.getLogger(__name__)


class SignatureError(Exception):
    pass


# Shared RSA private key extracted from the Supwisdom SuperApp APK.
# Used to sign WebHR API requests (appLoginsso and getKqCardInfo).
# This key is the same for all app clients in this deployment.
_PRIVATE_KEY_PEM = """-----BEGIN PRIVATE KEY-----
MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAw1OBuvkAyMU70/cn
0WKNk6xgB2leC+jd1i3fAt+lmxMFMjHuv4On/0ZoEILzidwlSkkA3lfjVT135ADE
i2WThwIDAQABAkEAqptW9hQmXjcAUd1QdzAozzLB0ddz4N0uXrREVUM5aB+HPUsw
+H/1i9oTm2bYznzdWfa8Rz/c/zOaJDhRnrF3CQIhAOL6EJxOl57Ny2O3C1Cwp1ki
iQ4juRzi+/aaNs0KGE1jAiEA3E1hRK+k9/Q1iRFVczIx3nJ6r8tQbR+e/VVaFT2N
fI0CIHLuTrTnMu0tpbyDNk6QdNy0mRO40QYohY80PWWLpAyjAiB1fa9KeoJ42v1S
RLRV44dFI7ja2IvkJuEcKtEIUvOKYQIgElLvoKegVbSfhFhhezxoJ2MLZ03ybWTH
LF5qmtC+MjI=
-----END PRIVATE KEY-----"""

# Pre-loaded at import time to avoid repeated PEM parsing on every request.
_PRIVATE_KEY = serialization.load_pem_private_key(
    _PRIVATE_KEY_PEM.encode("ascii"),
    password=None,
)


def generate_signature(md5str: str, user_code: str) -> SignatureResult:
    """Sign a WebHR API request payload with RSA-SHA256.

    The signature is required for both the appLoginsso (get token) and
    getKqCardInfo (fetch attendance) endpoints.  Each call needs a fresh
    signature with the current timestamp, so this function is called twice
    in the attendance flow.

    Args:
        md5str:    The md5Str value from the WebHR SSO redirect URL.
        user_code: The userCode value from the WebHR SSO redirect URL.

    Returns:
        dict with keys:
            sign      — base64-encoded RSA-SHA256 signature string
            timestamp — Unix epoch seconds used in the signed payload

    Raises:
        SignatureError: If either argument is empty or signing fails.
    """
    if not md5str:
        raise SignatureError("md5str is required")
    if not user_code:
        raise SignatureError("user_code is required")

    timestamp = int(time.time())
    logger.debug("Generating RSA-SHA256 signature for user_code=%s, timestamp=%d", user_code, timestamp)
    # Payload format matches the server-side verification logic in the WebHR app.
    payload = f"{md5str}&{user_code}&{timestamp}"

    try:
        if not isinstance(_PRIVATE_KEY, rsa.RSAPrivateKey):
            raise SignatureError("invalid RSA private key")
        raw_signature = _PRIVATE_KEY.sign(
            payload.encode("utf-8"),
            padding.PKCS1v15(),
            hashes.SHA256(),
        )
    except Exception as exc:
        raise SignatureError(f"failed to sign payload: {exc}") from exc

    signature = base64.b64encode(raw_signature).decode("ascii")
    logger.debug("Signature generated (len=%d)", len(signature))
    return {
        "sign": signature,
        "timestamp": timestamp,
    }
