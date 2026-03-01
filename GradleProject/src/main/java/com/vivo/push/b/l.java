package com.vivo.push.b;

import com.tencent.liteav.TXLiteAVCode;

/* JADX INFO: compiled from: OnDispatcherReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5568a;
    public int b;

    public l() {
        super(TXLiteAVCode.EVT_VOD_PLAY_TCP_CONNECT_SUCC);
        this.f5568a = -1;
        this.b = -1;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("key_dispatch_environment", this.f5568a);
        aVar.a("key_dispatch_area", this.b);
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5568a = aVar.b("key_dispatch_environment", 1);
        this.b = aVar.b("key_dispatch_area", 1);
    }

    public final int e() {
        return this.b;
    }

    public final int d() {
        return this.f5568a;
    }
}
