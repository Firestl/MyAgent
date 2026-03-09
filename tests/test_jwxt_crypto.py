"""Tests for cli/schedule/client.py static/class methods — JWXT crypto utilities."""

from __future__ import annotations

import re

import pytest

from cli.schedule.client import JWXTClient


# ---------------------------------------------------------------------------
# _to_base36
# ---------------------------------------------------------------------------

def test_base36_zero() -> None:
    """测试十进制 0 转 base36 的特例输出。"""
    assert JWXTClient._to_base36(0) == "0"


def test_base36_exact_36() -> None:
    """测试 36 会进位为 base36 的 10。"""
    # 36 → "10" (base-36)。
    assert JWXTClient._to_base36(36) == "10"


def test_base36_large_number() -> None:
    """测试较大整数转换为 base36 后可正确往返。"""
    # 已知：123456789 在 base-36 = "21i3v9"。
    result = JWXTClient._to_base36(123456789)
    # 往返验证
    assert int(result, 36) == 123456789


def test_base36_negative() -> None:
    """测试负数 base36 转换会保留负号且可往返。"""
    result = JWXTClient._to_base36(-42)
    assert result.startswith("-")
    assert int(result, 36) == -42


# ---------------------------------------------------------------------------
# _get_md5_2
# ---------------------------------------------------------------------------

def test_md5_2_deterministic() -> None:
    """测试 _get_md5_2 对相同输入输出稳定一致。"""
    # 相同输入 → 相同输出。
    a = JWXTClient._get_md5_2("action=jw_apply&type=kbvueh5")
    b = JWXTClient._get_md5_2("action=jw_apply&type=kbvueh5")
    assert a == b
    assert len(a) == 32


def test_md5_2_removes_positions() -> None:
    """测试 _get_md5_2 严格遵循先删位再二次 MD5 的算法。"""
    # 手动验证 MD5 → 删除 1-indexed 位置 3,10,17,25 → 再 MD5。
    import hashlib

    plain = "test_input"
    h1 = hashlib.md5(plain.encode("utf-8")).hexdigest()
    # 删除位置 3,10,17,25（1-indexed）
    filtered = "".join(
        ch for i, ch in enumerate(h1, start=1) if i not in {3, 10, 17, 25}
    )
    expected = hashlib.md5(filtered.encode("utf-8")).hexdigest()
    assert JWXTClient._get_md5_2(plain) == expected


def test_md5_2_empty_input() -> None:
    """测试空字符串也能生成固定长度的摘要。"""
    result = JWXTClient._get_md5_2("")
    assert len(result) == 32


# ---------------------------------------------------------------------------
# _of_encrypt
# ---------------------------------------------------------------------------

def test_of_encrypt_empty_plain() -> None:
    """测试明文为空时加密函数直接返回空字符串。"""
    # 空 plain → 原样返回。
    assert JWXTClient._of_encrypt("", "somekey") == ""


def test_of_encrypt_empty_key() -> None:
    """测试密钥为空时加密函数直接返回原始明文。"""
    # 空 key → 原样返回 plain。
    assert JWXTClient._of_encrypt("hello", "") == "hello"


def test_of_encrypt_known_pair() -> None:
    """测试加密结果格式符合预期的 base36 字符集合。"""
    # 用默认密钥加密一个已知短串，验证输出格式。
    key = "1tkdum1tkcbb"
    result = JWXTClient._of_encrypt("abc", key)
    # 输出应该非空且全由 [0-9a-z] 组成。
    assert result
    assert re.fullmatch(r"[0-9a-z]+", result)


def test_of_encrypt_output_length() -> None:
    """测试密文长度满足算法定义的 6 * ceil(N/3) 规律。"""
    # 输出长度 = 6 * ceil(N/3)，其中 N = len(plain)。
    import math

    key = "1tkdum1tkcbb"
    for n in (1, 3, 4, 9, 10, 12):
        plain = "a" * n
        result = JWXTClient._of_encrypt(plain, key)
        expected_len = 6 * math.ceil(n / 3)
        assert len(result) == expected_len, f"n={n}: got {len(result)}, expected {expected_len}"


def test_of_encrypt_charset() -> None:
    """测试加密输出只包含数字和小写字母。"""
    # 所有输出字符必须在 [0-9a-z] 范围内。
    key = "1tkdum1tkcbb"
    result = JWXTClient._of_encrypt("action=jw_apply&type=kbvueh5&step=xnxq", key)
    assert re.fullmatch(r"[0-9a-z]+", result)


# ---------------------------------------------------------------------------
# _extract_js_var
# ---------------------------------------------------------------------------

def test_extract_js_var_single_quote() -> None:
    """测试 JS 变量提取支持单引号写法。"""
    script = "var G_ENCRYPT = '2jrcnu003cfy';"
    assert JWXTClient._extract_js_var(script, "G_ENCRYPT") == "2jrcnu003cfy"


def test_extract_js_var_double_quote() -> None:
    """测试 JS 变量提取支持双引号写法。"""
    script = 'var G_LOGIN_ID = "s12345";'
    assert JWXTClient._extract_js_var(script, "G_LOGIN_ID") == "s12345"


def test_extract_js_var_not_found() -> None:
    """测试找不到目标 JS 变量时返回空字符串。"""
    assert JWXTClient._extract_js_var("var OTHER = 'x';", "G_ENCRYPT") == ""


# ---------------------------------------------------------------------------
# _infer_user_type_from_user_code
# ---------------------------------------------------------------------------

def test_infer_user_type_teacher() -> None:
    """测试以 t 开头的用户编码被推断为教师。"""
    assert JWXTClient._infer_user_type_from_user_code("t20001") == "TEA"


def test_infer_user_type_student() -> None:
    """测试以 s 开头的用户编码被推断为学生。"""
    assert JWXTClient._infer_user_type_from_user_code("s12345") == "STU"


def test_infer_user_type_unknown() -> None:
    """测试未知前缀和空字符串不会被误判用户类型。"""
    assert JWXTClient._infer_user_type_from_user_code("x99999") == ""
    assert JWXTClient._infer_user_type_from_user_code("") == ""
