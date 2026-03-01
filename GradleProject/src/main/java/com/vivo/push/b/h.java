package com.vivo.push.b;

/* JADX INFO: compiled from: MsgArriveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h extends com.vivo.push.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5565a;

    public h() {
        super(2013);
    }

    @Override // com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        aVar.a("MsgArriveCommand.MSG_TAG", this.f5565a);
    }

    @Override // com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        this.f5565a = aVar.a("MsgArriveCommand.MSG_TAG");
    }

    public h(String str) {
        this();
        this.f5565a = str;
    }
}
