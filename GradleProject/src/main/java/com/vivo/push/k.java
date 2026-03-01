package com.vivo.push;

import com.vivo.push.e;

/* JADX INFO: compiled from: PushClientManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f5615a;
    public final /* synthetic */ e b;

    public k(e eVar, String str) {
        this.b = eVar;
        this.f5615a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        e.a aVarD = this.b.d(this.f5615a);
        if (aVarD != null) {
            aVarD.a(1003, new Object[0]);
        }
    }
}
