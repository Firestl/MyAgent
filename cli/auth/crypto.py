import base64
import logging

from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives.asymmetric.rsa import RSAPublicKey
from cryptography.hazmat.primitives.serialization import load_der_public_key

logger = logging.getLogger(__name__)


def rsa_encrypt(pub_key_pem: str, plaintext: str) -> str:
    """Replicate bn1.a(String pubKeyPem, String plaintext) from bn1.java.

    Strips PEM headers, base64-decodes DER bytes, encrypts with
    RSA/ECB/PKCS1Padding, returns base64-encoded ciphertext.
    """
    pem = pub_key_pem
    pem = pem.replace("-----BEGIN PUBLIC KEY-----", "")
    pem = pem.replace("-----END PUBLIC KEY-----", "")
    pem = pem.replace("\n", "")

    der = base64.b64decode(pem)
    public_key = load_der_public_key(der)
    if not isinstance(public_key, RSAPublicKey):
        raise TypeError(f"Expected RSA public key, got {type(public_key).__name__}")
    logger.debug("RSA encrypting %d bytes of plaintext", len(plaintext.encode("utf-8")))
    ciphertext = public_key.encrypt(plaintext.encode("utf-8"), padding.PKCS1v15())
    logger.debug("RSA encryption complete, ciphertext length=%d", len(ciphertext))
    return base64.b64encode(ciphertext).decode("ascii")
