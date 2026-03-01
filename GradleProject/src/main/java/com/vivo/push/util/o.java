package com.vivo.push.util;

import android.content.Context;

/* JADX INFO: compiled from: LogUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f5640a = new m();
    public static boolean b;
    public static boolean c;

    static {
        b();
    }

    public static boolean a() {
        return b;
    }

    public static void b() {
        b = z.b("persist.sys.log.ctrl", "no").equals("yes");
    }

    public static int c(String str, String str2) {
        return f5640a.c(str, str2);
    }

    public static int d(String str, String str2) {
        return f5640a.d(str, str2);
    }

    public static int e(String str, String str2) {
        return f5640a.e(str, str2);
    }

    public static void a(boolean z) {
        b();
        c = z;
    }

    public static int b(String str, String str2) {
        return f5640a.b(str, str2);
    }

    public static void c(Context context, String str) {
        f5640a.c(context, str);
    }

    public static int b(String str, String str2, Throwable th) {
        return f5640a.b(str, str2, th);
    }

    public static int a(String str, String str2) {
        return f5640a.a(str, str2);
    }

    public static void b(Context context, String str) {
        f5640a.b(context, str);
    }

    public static int a(String str, Throwable th) {
        return f5640a.a(str, th);
    }

    public static int a(String str, String str2, Throwable th) {
        return f5640a.a(str, str2, th);
    }

    public static String a(Throwable th) {
        return f5640a.a(th);
    }

    public static void a(Context context, String str) {
        f5640a.a(context, str);
    }
}
