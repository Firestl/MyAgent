package com.vivo.push.b;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: OnTagsReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<String> f5576a;
    public ArrayList<String> b;

    public t(int i) {
        super(i);
        this.f5576a = null;
        this.b = null;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("content", this.f5576a);
        aVar.a("error_msg", this.b);
    }

    public final ArrayList<String> d() {
        return this.f5576a;
    }

    public final List<String> e() {
        return this.b;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final String toString() {
        return "OnSetTagsCommand";
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5576a = aVar.c("content");
        this.b = aVar.c("error_msg");
    }
}
