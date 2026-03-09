"""Tests for cli/auth/parsers.py — login / MFA / user info response parsers."""

from __future__ import annotations

import pytest

from cli.auth.parsers import (
    parse_login_configs_response,
    parse_mfa_response,
    parse_password_login_response,
    parse_user_info,
    parse_user_info_response,
)


# ---------------------------------------------------------------------------
# parse_login_configs_response
# ---------------------------------------------------------------------------

def test_login_configs_full_payload() -> None:
    """测试登录配置完整 payload 可被正确解析。"""
    payload = {
        "code": 0,
        "message": "ok",
        "data": {
            "loginPageConfig": {"encryptEnabled": True},
        },
    }
    result = parse_login_configs_response(payload)
    assert result["code"] == 0
    assert result["message"] == "ok"
    assert result["data"]["loginPageConfig"]["encryptEnabled"] is True


def test_login_configs_empty_dict() -> None:
    """测试登录配置允许所有字段都缺省。"""
    # 空 dict 是合法的——所有字段都是 optional。
    result = parse_login_configs_response({})
    assert "code" not in result
    assert "data" not in result


def test_login_configs_non_dict_raises() -> None:
    """测试登录配置响应不是对象时抛错。"""
    with pytest.raises(ValueError, match=r"must be a JSON object"):
        parse_login_configs_response("not-a-dict")


def test_login_configs_bool_code_rejected() -> None:
    """测试 code 即使是 bool 也不能被当作合法 int 接受。"""
    # bool 是 int 的子类，但 _optional_int 应拒绝 bool。
    with pytest.raises(ValueError, match=r"must be an integer"):
        parse_login_configs_response({"code": True})


# ---------------------------------------------------------------------------
# parse_mfa_response
# ---------------------------------------------------------------------------

def test_mfa_full_payload() -> None:
    """测试 MFA 响应在完整字段下的解析结果。"""
    payload = {
        "code": 0,
        "data": {"mfaEnabled": True, "need": False, "state": "abc123"},
    }
    result = parse_mfa_response(payload)
    assert result["data"]["mfaEnabled"] is True
    assert result["data"]["need"] is False
    assert result["data"]["state"] == "abc123"


def test_mfa_only_enabled() -> None:
    """测试 MFA 响应允许只返回部分可选字段。"""
    payload = {"code": 0, "data": {"mfaEnabled": False}}
    result = parse_mfa_response(payload)
    assert result["data"]["mfaEnabled"] is False
    assert "need" not in result["data"]


def test_mfa_non_dict_raises() -> None:
    """测试 MFA 响应最外层不是对象时会被拒绝。"""
    with pytest.raises(ValueError, match=r"must be a JSON object"):
        parse_mfa_response([1, 2, 3])


# ---------------------------------------------------------------------------
# parse_password_login_response
# ---------------------------------------------------------------------------

def test_password_login_with_id_token() -> None:
    """测试密码登录成功响应可解析出 idToken。"""
    payload = {"code": 0, "data": {"idToken": "jwt.token.here"}}
    result = parse_password_login_response(payload)
    assert result["data"]["idToken"] == "jwt.token.here"


def test_password_login_no_data() -> None:
    """测试密码登录失败类响应在无 data 时仍可被安全解析。"""
    result = parse_password_login_response({"code": 1, "message": "failed"})
    assert "data" not in result


def test_password_login_non_dict_raises() -> None:
    """测试密码登录响应最外层必须是对象。"""
    with pytest.raises(ValueError, match=r"must be a JSON object"):
        parse_password_login_response(42)


# ---------------------------------------------------------------------------
# parse_user_info / parse_user_info_response
# ---------------------------------------------------------------------------

def test_user_info_full_fields() -> None:
    """测试用户信息解析在完整字段输入下保留所有支持字段。"""
    value = {
        "name": "zhangsan",
        "realName": "张三",
        "username": "s12345",
        "mobile": "138xxxx",
        "email": "a@b.com",
        "orgName": "计算机学院",
        "userType": "STU",
    }
    result = parse_user_info(value, context="test")
    assert result["realName"] == "张三"
    assert result["userType"] == "STU"
    assert len(result) == 7


def test_user_info_partial_fields() -> None:
    """测试用户信息解析会跳过缺失字段，仅保留已有字段。"""
    # 只有部分字段，其余被跳过。
    result = parse_user_info({"name": "zhangsan"}, context="test")
    assert result == {"name": "zhangsan"}


def test_user_info_non_string_field_raises() -> None:
    """测试用户信息字段出现非字符串类型时立即报错。"""
    with pytest.raises(ValueError, match=r"must be a string"):
        parse_user_info({"name": 12345}, context="test")


def test_user_info_none_field_skipped() -> None:
    """测试值为 None 的用户信息字段会被忽略而非报错。"""
    # None 字段不报错，只是跳过。
    result = parse_user_info({"name": None, "realName": "张三"}, context="test")
    assert "name" not in result
    assert result["realName"] == "张三"
