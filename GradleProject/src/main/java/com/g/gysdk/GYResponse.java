package com.g.gysdk;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class GYResponse implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1937a;
    public final int b;
    public final String c;
    public String d;

    public GYResponse(GYResponse gYResponse, int i) {
        this.f1937a = gYResponse.f1937a;
        this.c = gYResponse.c;
        this.d = gYResponse.d;
        this.b = i;
    }

    public GYResponse(String str, int i, String str2, String str3) {
        this.f1937a = str;
        this.b = i;
        this.c = str2;
        this.d = str3;
    }

    public void a(String str) {
        this.d = str;
    }

    public int getCode() {
        return this.b;
    }

    public String getGyuid() {
        return this.f1937a;
    }

    public String getMsg() {
        return this.d;
    }

    public String getOperator() {
        return this.c;
    }

    public boolean isSuccess() {
        return this.b == GyCode.SUCCESS.value;
    }

    public String toString() {
        return "GYResponse{gyuid='" + this.f1937a + Operators.SINGLE_QUOTE + ", success=" + isSuccess() + ", code=" + this.b + ", operator='" + this.c + Operators.SINGLE_QUOTE + ", msg='" + this.d + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
