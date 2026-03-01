package com.loc;

import android.content.Context;

/* JADX INFO: compiled from: WiFiUplateStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class br extends bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3690a;
    public boolean b;

    public br(Context context) {
        this.b = false;
        this.f3690a = context;
        this.b = false;
    }

    @Override // com.loc.bq
    public final boolean a() {
        return n.q(this.f3690a) == 1 || this.b;
    }
}
