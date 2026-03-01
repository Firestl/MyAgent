package com.vivo.push.c;

import android.text.TextUtils;

/* JADX INFO: compiled from: OnBindAppReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f5589a;
    public final /* synthetic */ com.vivo.push.b.i b;
    public final /* synthetic */ d c;

    public e(d dVar, String str, com.vivo.push.b.i iVar) {
        this.c = dVar;
        this.f5589a = str;
        this.b = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!TextUtils.isEmpty(this.f5589a)) {
            d dVar = this.c;
            ((y) dVar).b.onReceiveRegId(dVar.f5616a, this.f5589a);
        }
        d dVar2 = this.c;
        ((y) dVar2).b.onBind(dVar2.f5616a, this.b.h(), this.b.d());
    }
}
