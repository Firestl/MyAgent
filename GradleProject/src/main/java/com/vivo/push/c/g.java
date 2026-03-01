package com.vivo.push.c;

/* JADX INFO: compiled from: OnClearCacheReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends y {
    public g(com.vivo.push.o oVar) {
        super(oVar);
    }

    @Override // com.vivo.push.l
    public final void a(com.vivo.push.o oVar) {
        com.vivo.push.util.o.d("OnClearCacheTask", "delete push info " + this.f5616a.getPackageName());
        com.vivo.push.util.y.b(this.f5616a).a();
    }
}
