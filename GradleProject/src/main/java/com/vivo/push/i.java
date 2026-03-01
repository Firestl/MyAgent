package com.vivo.push;

/* JADX INFO: compiled from: PushClientManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements IPushActionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f5613a;

    public i(e eVar) {
        this.f5613a = eVar;
    }

    @Override // com.vivo.push.IPushActionListener
    public final void onStateChanged(int i) {
        if (i != 0) {
            this.f5613a.k = null;
            this.f5613a.j.b("APP_TOKEN");
        } else {
            this.f5613a.k = "";
            this.f5613a.j.a("APP_TOKEN", "");
            this.f5613a.m();
            this.f5613a.j.b("APP_TAGS");
        }
    }
}
