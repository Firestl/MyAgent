package com.alipay.apmobilesecuritysdk.b;

import supwisdom.er;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static a b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1581a = 0;

    public static a a() {
        return b;
    }

    public final void a(int i) {
        this.f1581a = i;
    }

    public final int b() {
        return this.f1581a;
    }

    public final String c() {
        String strA = er.a();
        if (lq.b(strA)) {
            return strA;
        }
        int i = this.f1581a;
        return i != 1 ? i != 3 ? i != 4 ? "https://mobilegw.alipay.com/mgw.htm" : "http://mobilegw.aaa.alipay.net/mgw.htm" : "http://mobilegw-1-64.test.alipay.net/mgw.htm" : "http://mobilegw.stable.alipay.net/mgw.htm";
    }
}
