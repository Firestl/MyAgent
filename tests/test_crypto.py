"""Tests for cli/auth/crypto.py — RSA encryption (PKCS1v15)."""

from __future__ import annotations

import base64

import pytest
from cryptography.hazmat.primitives.asymmetric import ec, padding, rsa

from cli.auth.crypto import rsa_encrypt


def test_roundtrip_encrypt_decrypt(
    rsa_keypair: tuple[rsa.RSAPrivateKey, str],
) -> None:
    """测试 RSA 加密结果可被配套私钥成功解密回原文。"""
    # 用测试密钥加密再解密，断言明文恢复。
    private_key, pub_pem = rsa_keypair
    plaintext = "hello world"
    ciphertext_b64 = rsa_encrypt(pub_pem, plaintext)
    ciphertext = base64.b64decode(ciphertext_b64)
    recovered = private_key.decrypt(ciphertext, padding.PKCS1v15())
    assert recovered.decode("utf-8") == plaintext


def test_output_is_valid_base64(
    rsa_keypair: tuple[rsa.RSAPrivateKey, str],
) -> None:
    """测试加密输出是可解码的 base64 字符串。"""
    _, pub_pem = rsa_keypair
    result = rsa_encrypt(pub_pem, "test")
    # 如果不是合法 base64 会抛 binascii.Error。
    decoded = base64.b64decode(result)
    assert len(decoded) > 0


def test_ciphertext_length_matches_key_size(
    rsa_keypair: tuple[rsa.RSAPrivateKey, str],
) -> None:
    """测试密文长度与 1024-bit RSA 密钥长度一致。"""
    # 1024-bit key → 128 bytes RSA ciphertext。
    _, pub_pem = rsa_keypair
    ciphertext = base64.b64decode(rsa_encrypt(pub_pem, "x"))
    assert len(ciphertext) == 128


def test_rejects_non_rsa_key() -> None:
    """测试传入非 RSA 公钥时抛出类型错误。"""
    # 传 EC 公钥 PEM → 应抛 TypeError。
    from cryptography.hazmat.primitives.serialization import Encoding, PublicFormat

    ec_key = ec.generate_private_key(ec.SECP256R1())
    ec_pem = ec_key.public_key().public_bytes(Encoding.PEM, PublicFormat.SubjectPublicKeyInfo).decode()
    with pytest.raises(TypeError, match=r"Expected RSA public key"):
        rsa_encrypt(ec_pem, "test")


def test_malformed_pem_raises() -> None:
    """测试损坏的 PEM 公钥数据不会被静默接受。"""
    garbage = "-----BEGIN PUBLIC KEY-----\nnotvalidbase64!!!\n-----END PUBLIC KEY-----"
    with pytest.raises(Exception):
        rsa_encrypt(garbage, "test")


def test_empty_plaintext(
    rsa_keypair: tuple[rsa.RSAPrivateKey, str],
) -> None:
    """测试空字符串明文也能被 PKCS#1 v1.5 正常加密解密。"""
    # 空字符串可以加密（PKCS1v15 padding 仍然产生输出）。
    private_key, pub_pem = rsa_keypair
    ciphertext_b64 = rsa_encrypt(pub_pem, "")
    ciphertext = base64.b64decode(ciphertext_b64)
    recovered = private_key.decrypt(ciphertext, padding.PKCS1v15())
    assert recovered == b""


def test_unicode_plaintext(
    rsa_keypair: tuple[rsa.RSAPrivateKey, str],
) -> None:
    """测试 Unicode 明文在加密往返后仍保持原内容。"""
    # 中文字符串往返正确。
    private_key, pub_pem = rsa_keypair
    plaintext = "你好世界"
    ciphertext_b64 = rsa_encrypt(pub_pem, plaintext)
    ciphertext = base64.b64decode(ciphertext_b64)
    recovered = private_key.decrypt(ciphertext, padding.PKCS1v15())
    assert recovered.decode("utf-8") == plaintext
