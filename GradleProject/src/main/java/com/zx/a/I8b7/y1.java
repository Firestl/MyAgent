package com.zx.a.I8b7;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class y1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0 f6305a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final y1 f6306a = new y1();
    }

    public y1() {
        Context context = t2.f6286a;
        g0 g0Var = new g0();
        this.f6305a = g0Var;
        g0Var.b("zx_tag");
        g0Var.a(false);
        g0Var.a(1);
    }

    public static void a(String str) {
        try {
            a.f6306a.f6305a.f6221a.a(2, null, str, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(String str) {
        try {
            a.f6306a.f6305a.f6221a.a(5, null, str, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, Throwable th) {
        try {
            a.f6306a.f6305a.f6221a.a(5, null, str, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static void a(Throwable th) {
        try {
            a.f6306a.f6305a.f6221a.a(5, null, null, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
