package com.vivo.push.c;

import com.vivo.push.cache.ClientConfigManagerImpl;

/* JADX INFO: compiled from: InitTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends com.vivo.push.l {
    public c(com.vivo.push.o oVar) {
        super(oVar);
    }

    @Override // com.vivo.push.l
    public final void a(com.vivo.push.o oVar) {
        com.vivo.push.util.o.a(ClientConfigManagerImpl.getInstance(this.f5616a).isDebug());
    }
}
