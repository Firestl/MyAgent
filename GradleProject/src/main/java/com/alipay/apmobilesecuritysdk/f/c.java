package com.alipay.apmobilesecuritysdk.f;

import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b f1592a;

    public c(b bVar) {
        this.f1592a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.f1592a.c.isEmpty()) {
                Runnable runnable = (Runnable) this.f1592a.c.get(0);
                this.f1592a.c.remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            b.b(this.f1592a);
            throw th;
        }
        b.b(this.f1592a);
    }
}
