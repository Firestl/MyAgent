package com.vivo.push;

import com.vivo.push.e;

/* JADX INFO: compiled from: PushClientManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements IPushActionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e.a f5611a;
    public final /* synthetic */ e b;

    public g(e eVar, e.a aVar) {
        this.b = eVar;
        this.f5611a = aVar;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        if (i != 0) {
            this.b.k = null;
            this.b.j.b("APP_TOKEN");
            return;
        }
        Object[] objArrB = this.f5611a.b();
        if (objArrB == null || objArrB.length == 0) {
            com.vivo.push.util.o.a("PushClientManager", "bind app result is null");
        } else {
            this.b.a((String) this.f5611a.b()[0]);
        }
    }
}
