package com.vivo.push.b;

/* JADX INFO: compiled from: OnUndoMsgReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u extends v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5577a;
    public int b;

    public u() {
        super(20);
        this.f5577a = -1L;
    }

    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("undo_msg_v1", this.f5577a);
        aVar.a("undo_msg_type_v1", this.b);
    }

    public final long d() {
        return this.f5577a;
    }

    public final String e() {
        long j = this.f5577a;
        if (j != -1) {
            return String.valueOf(j);
        }
        return null;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final String toString() {
        return "OnUndoMsgCommand";
    }

    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5577a = aVar.b("undo_msg_v1", this.f5577a);
        this.b = aVar.b("undo_msg_type_v1", 0);
    }
}
