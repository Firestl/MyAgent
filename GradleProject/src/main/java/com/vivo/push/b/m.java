package com.vivo.push.b;

import java.util.ArrayList;

/* JADX INFO: compiled from: OnListTagReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<String> f5569a;

    public m() {
        super(8);
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("tags_list", this.f5569a);
    }

    public final ArrayList<String> d() {
        return this.f5569a;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final String toString() {
        return "OnListTagCommand";
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5569a = aVar.c("tags_list");
    }
}
