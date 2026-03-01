package com.vivo.push.b;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: compiled from: TagCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<String> f5582a;

    public z(boolean z, String str, ArrayList<String> arrayList) {
        super(z ? 2004 : 2005, str);
        this.f5582a = arrayList;
    }

    @Override // com.vivo.push.b.c, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("tags", (Serializable) this.f5582a);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5582a = aVar.c("tags");
    }

    @Override // com.vivo.push.b.c, com.vivo.push.o
    public final String toString() {
        return "TagCommand";
    }
}
