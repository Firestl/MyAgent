package com.zx.a.I8b7;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class z2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f6311a;
    public final /* synthetic */ a3 b;

    public z2(a3 a3Var, Context context) {
        this.b = a3Var;
        this.f6311a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            t2.a(this.f6311a);
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXCore init failed: "));
            this.b.b.set(false);
        }
    }
}
