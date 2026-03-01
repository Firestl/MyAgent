package com.vivo.push.b;

/* JADX INFO: compiled from: PushModeCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w extends com.vivo.push.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5579a;

    public w() {
        super(2011);
        this.f5579a = 0;
    }

    @Override // com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        aVar.a("com.bbk.push.ikey.MODE_TYPE", this.f5579a);
    }

    @Override // com.vivo.push.o
    public final boolean c() {
        return true;
    }

    public final int d() {
        return this.f5579a;
    }

    @Override // com.vivo.push.o
    public final String toString() {
        return "PushModeCommand";
    }

    @Override // com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        this.f5579a = aVar.b("com.bbk.push.ikey.MODE_TYPE", 0);
    }
}
