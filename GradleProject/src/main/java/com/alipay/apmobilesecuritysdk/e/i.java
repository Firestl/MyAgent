package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1589a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f1590e = "";
    public static Map<String, String> f = new HashMap();

    public static synchronized String a(String str) {
        String str2 = "apdidTokenCache" + str;
        if (f.containsKey(str2)) {
            String str3 = f.get(str2);
            if (lq.b(str3)) {
                return str3;
            }
        }
        return "";
    }

    public static synchronized void a() {
    }

    public static synchronized void a(b bVar) {
        if (bVar != null) {
            f1589a = bVar.f1583a;
            b = bVar.b;
            c = bVar.c;
        }
    }

    public static synchronized void a(c cVar) {
        if (cVar != null) {
            f1589a = cVar.f1584a;
            b = cVar.b;
            d = cVar.d;
            f1590e = cVar.f1585e;
            c = cVar.c;
        }
    }

    public static synchronized void a(String str, String str2) {
        String str3 = "apdidTokenCache" + str;
        if (f.containsKey(str3)) {
            f.remove(str3);
        }
        f.put(str3, str2);
    }

    public static synchronized boolean a(Context context, String str) {
        long j = 86400000;
        try {
            long jA = h.a(context);
            if (jA >= 0) {
                j = jA;
            }
        } catch (Throwable unused) {
        }
        try {
            if (Math.abs(System.currentTimeMillis() - h.h(context, str)) < j) {
                return true;
            }
        } finally {
        }
        return false;
    }

    public static synchronized String b() {
        return f1589a;
    }

    public static void b(String str) {
        f1589a = str;
    }

    public static synchronized String c() {
        return b;
    }

    public static void c(String str) {
        b = str;
    }

    public static synchronized String d() {
        return d;
    }

    public static void d(String str) {
        c = str;
    }

    public static synchronized String e() {
        return f1590e;
    }

    public static void e(String str) {
        d = str;
    }

    public static synchronized String f() {
        return c;
    }

    public static void f(String str) {
        f1590e = str;
    }

    public static synchronized c g() {
        return new c(f1589a, b, c, d, f1590e);
    }

    public static void h() {
        f.clear();
        f1589a = "";
        b = "";
        d = "";
        f1590e = "";
        c = "";
    }
}
