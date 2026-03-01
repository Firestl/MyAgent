package com.zx.a.I8b7;

/* JADX INFO: loaded from: classes2.dex */
public class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i0 f6221a = new i0();
    public d0 b;
    public f0 c;

    public g0() {
        f0 f0Var = new f0(new e0());
        this.c = f0Var;
        d0 d0Var = new d0(f0Var);
        this.b = d0Var;
        this.f6221a.a(d0Var);
    }

    public void a(String str) {
        this.f6221a.a(2, null, str, null);
    }

    public void b(String str) {
        this.c.b = str;
    }

    public void a(boolean z) {
        this.b.b = z;
    }

    public void a(int i) {
        this.c.c = i + 8;
    }
}
