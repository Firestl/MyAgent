"""Tests for cli/attendance/sign.py — RSA-SHA256 request signing."""

from __future__ import annotations

import base64

import pytest
from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import padding, rsa

from cli.attendance.sign import SignatureError, _PRIVATE_KEY, generate_signature


# 从嵌入的私钥提取公钥，用于验签。
_PUBLIC_KEY = _PRIVATE_KEY.public_key()  # type: ignore[union-attr]


def test_signature_verifiable(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试生成的签名可被对应公钥成功验签。"""
    # 用公钥验签，payload 格式 = "{md5str}&{user_code}&{ts}"。
    monkeypatch.setattr("cli.attendance.sign.time", _FakeTime(1700000000.0))
    result = generate_signature("abc", "user1")
    raw_sig = base64.b64decode(result["sign"])
    expected_payload = "abc&user1&1700000000"
    # 如果验签失败，verify() 会抛 InvalidSignature。
    _PUBLIC_KEY.verify(
        raw_sig,
        expected_payload.encode("utf-8"),
        padding.PKCS1v15(),
        hashes.SHA256(),
    )


def test_timestamp_matches_mocked_time(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试时间戳取整逻辑与 time.time() 的 floor 行为一致。"""
    # time.time()=1700000000.9 → timestamp 应该是 int(floor) = 1700000000。
    monkeypatch.setattr("cli.attendance.sign.time", _FakeTime(1700000000.9))
    result = generate_signature("md5", "code")
    assert result["timestamp"] == 1700000000


def test_signature_is_valid_base64(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试签名字符串本身是合法的 base64 编码。"""
    monkeypatch.setattr("cli.attendance.sign.time", _FakeTime(1000000000.0))
    result = generate_signature("m", "c")
    decoded = base64.b64decode(result["sign"])
    assert len(decoded) > 0


def test_deterministic_for_same_input(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试相同输入和时间下 PKCS1v15 签名输出稳定一致。"""
    # PKCS1v15 + SHA256 签名是确定性的（无随机填充），相同输入 → 相同输出。
    monkeypatch.setattr("cli.attendance.sign.time", _FakeTime(1700000000.0))
    r1 = generate_signature("md5", "code")
    r2 = generate_signature("md5", "code")
    assert r1["sign"] == r2["sign"]
    assert r1["timestamp"] == r2["timestamp"]


def test_empty_md5str_raises() -> None:
    """测试缺少 md5str 时函数拒绝生成签名。"""
    with pytest.raises(SignatureError, match=r"md5str is required"):
        generate_signature("", "user1")


def test_empty_user_code_raises() -> None:
    """测试缺少 user_code 时函数拒绝生成签名。"""
    with pytest.raises(SignatureError, match=r"user_code is required"):
        generate_signature("abc", "")


def test_none_args_raises() -> None:
    """测试传入 None 参数时也会走统一的签名错误分支。"""
    with pytest.raises(SignatureError):
        generate_signature(None, "code")  # type: ignore[arg-type]
    with pytest.raises(SignatureError):
        generate_signature("md5", None)  # type: ignore[arg-type]


def test_return_keys(monkeypatch: pytest.MonkeyPatch) -> None:
    """测试返回结果只包含 sign 和 timestamp 两个约定字段。"""
    monkeypatch.setattr("cli.attendance.sign.time", _FakeTime(1700000000.0))
    result = generate_signature("md5", "code")
    assert set(result.keys()) == {"sign", "timestamp"}
    assert isinstance(result["sign"], str)
    assert isinstance(result["timestamp"], int)


# ---------------------------------------------------------------------------
# Helper: mock time 模块
# ---------------------------------------------------------------------------

class _FakeTime:
    """替换整个 time 模块的最小 stub——只需要 time() 方法。"""

    def __init__(self, fixed: float) -> None:
        """保存固定时间值，供 time() 返回。"""
        self._fixed = fixed

    def time(self) -> float:
        """返回预设的 Unix 时间戳秒数。"""
        return self._fixed
