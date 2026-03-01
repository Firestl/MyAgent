package com.vivo.push.c;

/* JADX INFO: compiled from: OnUnBindAppReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ad implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.vivo.push.b.i f5588a;
    public final /* synthetic */ ac b;

    public ad(ac acVar, com.vivo.push.b.i iVar) {
        this.b = acVar;
        this.f5588a = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ac acVar = this.b;
        ((y) acVar).b.onUnBind(acVar.f5616a, this.f5588a.h(), this.f5588a.d());
    }
}
