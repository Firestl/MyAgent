package com.alipay.sdk.app;

import com.baidu.speech.utils.AsrError;
import com.huawei.hms.api.ConnectionResult;
import dc.squareup.okhttp3.internal.platform.AndroidPlatform;

/* JADX INFO: loaded from: classes.dex */
public enum c {
    SUCCEEDED(ConnectionResult.NETWORK_ERROR, "处理成功"),
    FAILED(AndroidPlatform.MAX_LOG_LENGTH, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(AsrError.ERROR_SERVER_PARAM, "参数错误"),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");

    public int h;
    public String i;

    c(int i, String str) {
        this.h = i;
        this.i = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public String b() {
        return this.i;
    }

    public static c b(int i) {
        return i != 4001 ? i != 5000 ? i != 8000 ? i != 9000 ? i != 6001 ? i != 6002 ? FAILED : NETWORK_ERROR : CANCELED : SUCCEEDED : PAY_WAITTING : DOUBLE_REQUEST : PARAMS_ERROR;
    }

    public int a() {
        return this.h;
    }

    public void a(String str) {
        this.i = str;
    }
}
