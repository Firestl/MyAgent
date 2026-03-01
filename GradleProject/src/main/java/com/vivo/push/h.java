package com.vivo.push;

/* JADX INFO: compiled from: PushClientManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.vivo.push.b.b f5612a;
    public final /* synthetic */ String b;
    public final /* synthetic */ e c;

    public h(e eVar, com.vivo.push.b.b bVar, String str) {
        this.c = eVar;
        this.f5612a = bVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.c.a(this.f5612a);
        this.c.e(this.b);
    }
}
