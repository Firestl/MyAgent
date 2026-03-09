"""Tests for cli/attendance/parsers.py — WebHR response parsers."""

from __future__ import annotations

import pytest

from cli.attendance.parsers import (
    _parse_card_field,
    parse_webhr_card_info_response,
    parse_webhr_save_response,
    parse_webhr_token_response,
)


# ---------------------------------------------------------------------------
# parse_webhr_token_response
# ---------------------------------------------------------------------------

def _wrap_token(token: object) -> dict:
    """构造 token 响应外壳，供 token 解析测试复用。"""
    """构造 appLoginsso 嵌套 response 结构。"""
    return {"data": {"data": {"token": token}}}


def test_token_valid() -> None:
    """测试 token 解析在合法非空字符串输入时返回原值。"""
    assert parse_webhr_token_response(_wrap_token("abc123")) == "abc123"


def test_token_empty_string_raises() -> None:
    """测试 token 为空字符串时触发显式校验错误。"""
    with pytest.raises(ValueError, match=r"non-empty string"):
        parse_webhr_token_response(_wrap_token(""))


def test_token_missing_key_raises() -> None:
    """测试 token 字段缺失时不会静默通过。"""
    with pytest.raises(ValueError, match=r"non-empty string"):
        parse_webhr_token_response({"data": {"data": {}}})


def test_token_non_dict_outer_raises() -> None:
    """测试最外层不是对象时会被拒绝。"""
    with pytest.raises(ValueError, match=r"must be a JSON object"):
        parse_webhr_token_response("flat-string")


# ---------------------------------------------------------------------------
# parse_webhr_card_info_response
# ---------------------------------------------------------------------------

def _wrap_card(sbk: object = "无", xbk: object = "无") -> dict:
    """构造考勤卡信息响应外壳，供上下班卡场景测试复用。"""
    """构造 getKqCardInfo 嵌套 response 结构。"""
    return {"data": {"data": {"sbk": sbk, "xbk": xbk}}}


def test_card_info_both_punched() -> None:
    """测试上下班卡都存在时，解析结果完整保留两个字段。"""
    payload = _wrap_card(["上班卡", "08:30"], ["下班卡", "17:30"])
    result = parse_webhr_card_info_response(payload)
    assert result["data"]["data"]["sbk"] == ["上班卡", "08:30"]
    assert result["data"]["data"]["xbk"] == ["下班卡", "17:30"]


def test_card_info_both_unpunched() -> None:
    """测试上下班卡都为字符串占位值时按原样返回。"""
    result = parse_webhr_card_info_response(_wrap_card("无", "无"))
    assert result["data"]["data"]["sbk"] == "无"
    assert result["data"]["data"]["xbk"] == "无"


def test_card_info_only_sbk() -> None:
    """测试缺少 xbk 字段时解析器不会强行补默认值。"""
    # 只有上班卡字段，下班卡缺失。
    payload = {"data": {"data": {"sbk": ["上班卡", "08:30"]}}}
    result = parse_webhr_card_info_response(payload)
    assert result["data"]["data"]["sbk"] == ["上班卡", "08:30"]
    assert "xbk" not in result["data"]["data"]


def test_card_info_empty_inner() -> None:
    """测试空的内层 data 对象会被解析为空结果字典。"""
    payload = {"data": {"data": {}}}
    result = parse_webhr_card_info_response(payload)
    assert result["data"]["data"] == {}


# ---------------------------------------------------------------------------
# _parse_card_field
# ---------------------------------------------------------------------------

def test_card_field_string() -> None:
    """测试卡字段为字符串时直接返回该字符串。"""
    assert _parse_card_field("无", context="test") == "无"


def test_card_field_list() -> None:
    """测试卡字段为字符串列表时按列表返回。"""
    assert _parse_card_field(["上班卡", "08:30"], context="test") == ["上班卡", "08:30"]


def test_card_field_invalid_type_raises() -> None:
    """测试卡字段为非字符串/列表类型时抛出校验错误。"""
    with pytest.raises(ValueError, match=r"must be a string or string list"):
        _parse_card_field(42, context="test")


# ---------------------------------------------------------------------------
# parse_webhr_save_response
# ---------------------------------------------------------------------------

def test_save_passthrough() -> None:
    """测试保存打卡响应解析器对合法对象做透传。"""
    payload = {"code": 0, "message": "打卡成功", "extra": True}
    result = parse_webhr_save_response(payload)
    assert result["code"] == 0
    assert result["message"] == "打卡成功"


def test_save_non_dict_raises() -> None:
    """测试保存打卡响应的最外层必须是对象。"""
    with pytest.raises(ValueError, match=r"must be a JSON object"):
        parse_webhr_save_response(None)
