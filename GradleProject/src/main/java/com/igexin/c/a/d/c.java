package com.igexin.c.a.d;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c implements com.igexin.c.a.d.a.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3195a = true;

    @Override // com.igexin.c.a.d.a.g
    public final boolean a(long j, f fVar) {
        return TimeUnit.SECONDS.toMillis((long) fVar.B) < j - fVar.z;
    }

    @Override // com.igexin.c.a.d.a.g
    public final long b(long j, f fVar) {
        return (TimeUnit.SECONDS.toMillis(fVar.B) + fVar.z) - j;
    }

    @Override // com.igexin.c.a.d.a.g
    public void b() {
        this.f3195a = false;
    }

    @Override // com.igexin.c.a.d.a.g
    public final boolean d() {
        return this.f3195a;
    }
}
