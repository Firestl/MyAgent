package com.zx.module.exception;

/* JADX INFO: loaded from: classes2.dex */
public class ZXBizException extends ZXModuleException {
    public final String bizMessage;
    public final int code;

    public ZXBizException(int i, String str) {
        super("ZXBizException: code=" + i + ", bizMessage=" + str);
        this.code = i;
        this.bizMessage = str;
    }

    public String getBizMessage() {
        return this.bizMessage;
    }

    public int getCode() {
        return this.code;
    }
}
