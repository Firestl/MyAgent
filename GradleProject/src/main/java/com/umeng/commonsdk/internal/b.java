package com.umeng.commonsdk.internal;

import android.content.Context;

/* JADX INFO: compiled from: UMInternalData.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static b b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f5386a;
    public c c;

    public b(Context context) {
        this.f5386a = context;
        this.c = new c(context);
    }

    public static synchronized b a(Context context) {
        if (b == null) {
            b = new b(context.getApplicationContext());
        }
        return b;
    }

    public c a() {
        return this.c;
    }
}
