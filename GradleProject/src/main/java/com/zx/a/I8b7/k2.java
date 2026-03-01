package com.zx.a.I8b7;

/* JADX INFO: loaded from: classes2.dex */
public class k2 implements Runnable {
    public k2(e2 e2Var, boolean z) {
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            e2.a().a(false);
        } catch (Throwable th) {
            l.b(th.getMessage());
        }
    }
}
