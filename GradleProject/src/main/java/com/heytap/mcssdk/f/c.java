package com.heytap.mcssdk.f;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2577a = "mcssdk---";
    public static String b = "MCS";
    public static boolean c = false;
    public static boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f2578e = true;
    public static boolean f = true;
    public static boolean g = true;
    public static String h = "-->";
    public static boolean i = true;

    public static String a() {
        return b;
    }

    public static void a(Exception exc) {
        if (!g || exc == null) {
            return;
        }
        Log.e(f2577a, exc.getMessage());
    }

    public static void a(String str) {
        if (c && i) {
            Log.v(f2577a, b + h + str);
        }
    }

    public static void a(String str, String str2) {
        if (c && i) {
            Log.v(str, b + h + str2);
        }
    }

    public static void a(String str, Throwable th) {
        if (g) {
            Log.e(str, th.toString());
        }
    }

    public static void a(boolean z) {
        c = z;
    }

    public static void b(String str) {
        if (f2578e && i) {
            Log.d(f2577a, b + h + str);
        }
    }

    public static void b(String str, String str2) {
        if (f2578e && i) {
            Log.d(str, b + h + str2);
        }
    }

    public static void b(boolean z) {
        f2578e = z;
    }

    public static boolean b() {
        return c;
    }

    public static void c(String str) {
        if (d && i) {
            Log.i(f2577a, b + h + str);
        }
    }

    public static void c(String str, String str2) {
        if (d && i) {
            Log.i(str, b + h + str2);
        }
    }

    public static void c(boolean z) {
        d = z;
    }

    public static boolean c() {
        return f2578e;
    }

    public static void d(String str) {
        if (f && i) {
            Log.w(f2577a, b + h + str);
        }
    }

    public static void d(String str, String str2) {
        if (f && i) {
            Log.w(str, b + h + str2);
        }
    }

    public static void d(boolean z) {
        f = z;
    }

    public static boolean d() {
        return d;
    }

    public static void e(String str) {
        if (g && i) {
            Log.e(f2577a, b + h + str);
        }
    }

    public static void e(String str, String str2) {
        if (g && i) {
            Log.e(str, b + h + str2);
        }
    }

    public static void e(boolean z) {
        g = z;
    }

    public static boolean e() {
        return f;
    }

    public static void f(String str) {
        b = str;
    }

    public static void f(boolean z) {
        i = z;
        boolean z2 = z;
        c = z2;
        f2578e = z2;
        d = z2;
        f = z2;
        g = z2;
    }

    public static boolean f() {
        return g;
    }

    public static void g(String str) {
        h = str;
    }

    public static boolean g() {
        return i;
    }

    public static String h() {
        return h;
    }
}
