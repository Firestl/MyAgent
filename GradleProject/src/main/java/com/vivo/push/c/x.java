package com.vivo.push.c;

/* JADX INFO: compiled from: OnPublishReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.vivo.push.b.r f5600a;
    public final /* synthetic */ w b;

    public x(w wVar, com.vivo.push.b.r rVar) {
        this.b = wVar;
        this.f5600a = rVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        w wVar = this.b;
        ((y) wVar).b.onPublish(wVar.f5616a, this.f5600a.h(), this.f5600a.g());
    }
}
