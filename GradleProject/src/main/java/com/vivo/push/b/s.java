package com.vivo.push.b;

/* JADX INFO: compiled from: OnReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public class s extends com.vivo.push.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5575a;
    public int b;

    public s(int i) {
        super(i);
        this.f5575a = null;
        this.b = 0;
    }

    @Override // com.vivo.push.o
    public void c(com.vivo.push.a aVar) {
        aVar.a("req_id", this.f5575a);
        aVar.a("status_msg_code", this.b);
    }

    @Override // com.vivo.push.o
    public void d(com.vivo.push.a aVar) {
        this.f5575a = aVar.a("req_id");
        this.b = aVar.b("status_msg_code", this.b);
    }

    public final String g() {
        return this.f5575a;
    }

    public final int h() {
        return this.b;
    }

    @Override // com.vivo.push.o
    public String toString() {
        return "OnReceiveCommand";
    }
}
