package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import supwisdom.oq;
import supwisdom.qq;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static synchronized Map<String, String> a() {
        HashMap map;
        map = new HashMap();
        try {
            new com.alipay.apmobilesecuritysdk.c.b();
            map.put("AE16", "");
        } catch (Throwable unused) {
        }
        return map;
    }

    public static synchronized Map<String, String> a(Context context) {
        HashMap map;
        qq.a();
        oq.b();
        map = new HashMap();
        map.put("AE1", qq.b());
        StringBuilder sb = new StringBuilder();
        sb.append(qq.c() ? "1" : "0");
        map.put("AE2", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(qq.a(context) ? "1" : "0");
        map.put("AE3", sb2.toString());
        map.put("AE4", qq.d());
        map.put("AE5", qq.e());
        map.put("AE6", qq.f());
        map.put("AE7", qq.g());
        map.put("AE8", qq.h());
        map.put("AE9", qq.i());
        map.put("AE10", qq.j());
        map.put("AE11", qq.k());
        map.put("AE12", qq.l());
        map.put("AE13", qq.m());
        map.put("AE14", qq.n());
        map.put("AE15", qq.o());
        map.put("AE21", oq.g());
        return map;
    }
}
