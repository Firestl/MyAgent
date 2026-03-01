package com.vivo.push.b;

import com.tencent.connect.common.Constants;

/* JADX INFO: compiled from: OnAppReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5566a;
    public String b;
    public String c;

    public i(int i) {
        super(i);
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("app_id", this.f5566a);
        aVar.a(Constants.PARAM_CLIENT_ID, this.b);
        aVar.a("client_token", this.c);
    }

    public final String d() {
        return this.f5566a;
    }

    public final String e() {
        return this.c;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final String toString() {
        return "OnBindCommand";
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5566a = aVar.a("app_id");
        this.b = aVar.a(Constants.PARAM_CLIENT_ID);
        this.c = aVar.a("client_token");
    }
}
