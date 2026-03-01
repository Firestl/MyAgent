package com.zx.a.I8b7;

import com.zx.sdk.api.ZXIDListener;

/* JADX INFO: loaded from: classes2.dex */
public class z1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f6310a;
    public final /* synthetic */ ZXIDListener b;

    public z1(e2 e2Var, String str, ZXIDListener zXIDListener) {
        this.f6310a = str;
        this.b = zXIDListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            e2.a().a(this.f6310a, this.b);
        } catch (Throwable th) {
            ZXIDListener zXIDListener = this.b;
            if (zXIDListener != null) {
                zXIDListener.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getZXID(zxidListener) failed: "));
        }
    }
}
