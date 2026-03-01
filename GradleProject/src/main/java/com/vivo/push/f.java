package com.vivo.push;

import android.text.TextUtils;
import com.vivo.push.util.z;

/* JADX INFO: compiled from: PushClientManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f5610a;
    public final /* synthetic */ e b;

    public f(e eVar, String str) {
        this.b = eVar;
        this.f5610a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.b.h == null || TextUtils.isEmpty(this.f5610a) || !z.a(this.b.h, this.b.h.getPackageName(), this.f5610a)) {
            return;
        }
        this.b.i();
    }
}
