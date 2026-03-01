package com.unicom.xiaowo.account.shield.e;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5550a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f5551e = "";
    public static int f = 5000;
    public static int g = -1;
    public static String h = "";
    public static String i = "";
    public static boolean j = false;
    public static boolean k = false;

    public static String a() {
        return f5550a;
    }

    public static void a(int i2) {
        f = i2;
    }

    public static synchronized void a(Context context) {
        j.a(context, "");
        j.a(context, 0L);
    }

    public static synchronized void a(Context context, String str) {
        j.a(context, str);
        j.a(context, System.currentTimeMillis());
    }

    public static void a(String str) {
        f5550a = str;
    }

    public static void a(boolean z) {
        k = z;
    }

    public static String b() {
        return b;
    }

    public static synchronized JSONObject b(Context context) {
        JSONObject jSONObject;
        long jB = j.b(context);
        String strA = j.a(context);
        JSONObject jSONObject2 = null;
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(strA);
            try {
                if (System.currentTimeMillis() - jB >= (jSONObject.optLong("expires") - 60) * 1000) {
                    a(context);
                    return null;
                }
            } catch (Exception e2) {
                e = e2;
                jSONObject2 = jSONObject;
                e.printStackTrace();
                jSONObject = jSONObject2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        return jSONObject;
    }

    public static void b(int i2) {
        g = i2;
    }

    public static void b(String str) {
        b = str;
    }

    public static int c() {
        return f;
    }

    public static void c(String str) {
        c = str;
    }

    public static String d() {
        return i;
    }

    public static void d(String str) {
        d = str;
    }

    public static void e(String str) {
        f5551e = str;
    }

    public static boolean e() {
        return k;
    }

    public static void f(String str) {
        h = str;
    }

    public static void g(String str) {
        i = str;
    }
}
