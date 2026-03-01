package com.zx.a.I8b7;

import com.zx.sdk.api.Callback;

/* JADX INFO: loaded from: classes2.dex */
public class h2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f6226a;
    public final /* synthetic */ Callback b;

    public h2(e2 e2Var, String str, Callback callback) {
        this.f6226a = str;
        this.b = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            e2.a().a(this.f6226a, this.b);
        } catch (Throwable th) {
            Callback callback = this.b;
            if (callback != null) {
                callback.onFailed(10000, th.getMessage());
            }
            n2.a(th, m2.a("ZXManager.getTag() failed: "));
        }
    }
}
