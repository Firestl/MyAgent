package com.vivo.push;

/* JADX INFO: compiled from: PushClientManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.vivo.push.b.b f5614a;
    public final /* synthetic */ String b;
    public final /* synthetic */ e c;

    public j(e eVar, com.vivo.push.b.b bVar, String str) {
        this.c = eVar;
        this.f5614a = bVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.c.a(this.f5614a);
        this.c.e(this.b);
    }
}
