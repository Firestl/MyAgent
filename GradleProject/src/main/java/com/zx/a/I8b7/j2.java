package com.zx.a.I8b7;

import com.zx.a.I8b7.t1;

/* JADX INFO: loaded from: classes2.dex */
public class j2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f6235a;

    public j2(e2 e2Var, boolean z) {
        this.f6235a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b3 b3Var = t1.a.f6285a.f6284a;
            boolean z = this.f6235a;
            b3Var.getClass();
            if (z != t2.r) {
                t2.r = z ? 1 : 0;
                b3Var.a(20, t2.r + "", false);
            }
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXManager.allowPermissionDialog failed: "));
        }
    }
}
