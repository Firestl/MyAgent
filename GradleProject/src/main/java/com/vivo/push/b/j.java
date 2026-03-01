package com.vivo.push.b;

/* JADX INFO: compiled from: OnChangePushStatusReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5567a;
    public int b;

    public j() {
        super(12);
        this.f5567a = -1;
        this.b = -1;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.f5567a);
        aVar.a("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.b);
    }

    public final int d() {
        return this.f5567a;
    }

    public final int e() {
        return this.b;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final String toString() {
        return "OnChangePushStatusCommand";
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5567a = aVar.b("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.f5567a);
        this.b = aVar.b("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.b);
    }
}
