package com.vivo.push.c;

/* JADX INFO: compiled from: OnListTagReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.vivo.push.b.m f5594a;
    public final /* synthetic */ l b;

    public m(l lVar, com.vivo.push.b.m mVar) {
        this.b = lVar;
        this.f5594a = mVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        l lVar = this.b;
        ((y) lVar).b.onListTags(lVar.f5616a, this.f5594a.h(), this.f5594a.d(), this.f5594a.g());
    }
}
