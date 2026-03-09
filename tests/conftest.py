"""Shared pytest fixtures for the test suite."""

from __future__ import annotations

import pytest
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives.serialization import (
    Encoding,
    PublicFormat,
)


@pytest.fixture(scope="session")
def rsa_keypair() -> tuple[rsa.RSAPrivateKey, str]:
    """提供 RSA 测试密钥对，供加密/解密相关测试验证往返行为。"""
    """生成 512-bit RSA 测试密钥对，返回 (私钥对象, 公钥 PEM 字符串)。

    仅供加密往返测试使用，1024-bit 是 cryptography 库允许的最小值。
    """
    private_key = rsa.generate_private_key(public_exponent=65537, key_size=1024)
    pub_pem = private_key.public_key().public_bytes(
        Encoding.PEM, PublicFormat.SubjectPublicKeyInfo
    ).decode("ascii")
    return private_key, pub_pem
