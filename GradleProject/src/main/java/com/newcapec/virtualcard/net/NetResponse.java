package com.newcapec.virtualcard.net;

import com.alibaba.fastjson.JSON;

/* JADX INFO: loaded from: classes2.dex */
public class NetResponse {
    public int code;
    public String content;
    public String message;
    public boolean success;

    public NetResponse(boolean z, int i, String str, String str2) {
        this.success = z;
        this.code = i;
        this.message = str;
        this.content = str2;
    }

    public int getCode() {
        return this.code;
    }

    public String getContent() {
        return this.content;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
