package com.igexin.c.a.d;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b implements com.igexin.c.a.d.a.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile boolean f3194a;
    public long b;
    public String y = getClass().getName();

    @Override // com.igexin.c.a.d.a.e
    public final void a(boolean z) {
        this.f3194a = !z;
    }

    @Override // com.igexin.c.a.d.a.e
    public final void b(long j) {
        this.b = j;
    }

    @Override // com.igexin.c.a.d.a.e
    public final boolean i() {
        return this.f3194a;
    }

    @Override // com.igexin.c.a.d.a.e
    public final long j() {
        return this.b;
    }
}
