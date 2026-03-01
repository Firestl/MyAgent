package com.vivo.push.c;

/* JADX INFO: compiled from: OnLogReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.vivo.push.b.n f5595a;
    public final /* synthetic */ n b;

    public o(n nVar, com.vivo.push.b.n nVar2) {
        this.b = nVar;
        this.f5595a = nVar2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        n nVar = this.b;
        ((y) nVar).b.onLog(nVar.f5616a, this.f5595a.d(), this.f5595a.e(), this.f5595a.f());
    }
}
