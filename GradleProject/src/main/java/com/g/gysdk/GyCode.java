package com.g.gysdk;

import com.igexin.sdk.PushConsts;

/* JADX INFO: loaded from: classes.dex */
public enum GyCode {
    SUCCESS("请求成功", 30000),
    UNKNOWN_ERROR("初始化、以及其他错误", PushConsts.ALIAS_OPERATE_ALIAS_FAILED),
    PRELOGIN_ERROR("预登录失败", PushConsts.ALIAS_CID_LOST),
    LOGIN_ERROR("一键登录失败", PushConsts.ALIAS_CONNECT_LOST),
    PREVERIFY_ERROR("获取校验token失败", PushConsts.ALIAS_INVALID),
    VERIFY_ERROR("号码校验失败", PushConsts.ALIAS_SN_INVALID);

    public String name;
    public int value;

    GyCode(String str, int i) {
        this.name = str;
        this.value = i;
    }

    public static GyCode valueOf(int i) {
        if (i == 30000) {
            return SUCCESS;
        }
        switch (i) {
            case PushConsts.ALIAS_CID_LOST /* 30005 */:
                return PRELOGIN_ERROR;
            case PushConsts.ALIAS_CONNECT_LOST /* 30006 */:
                return LOGIN_ERROR;
            case PushConsts.ALIAS_INVALID /* 30007 */:
                return PREVERIFY_ERROR;
            case PushConsts.ALIAS_SN_INVALID /* 30008 */:
                return VERIFY_ERROR;
            default:
                return UNKNOWN_ERROR;
        }
    }
}
