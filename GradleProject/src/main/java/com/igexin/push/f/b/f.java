package com.igexin.push.f.b;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f extends com.igexin.c.a.d.f {
    public long d;

    public f(long j) {
        super(5);
        this.d = j;
        a(j, TimeUnit.MILLISECONDS);
    }

    public f(long j, byte b) {
        this(j);
    }

    public abstract void b();

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        b();
    }

    @Override // com.igexin.c.a.d.f
    public final void e() {
    }

    @Override // com.igexin.c.a.d.f
    public final void f() {
    }
}
