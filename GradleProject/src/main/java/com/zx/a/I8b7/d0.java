package com.zx.a.I8b7;

/* JADX INFO: loaded from: classes2.dex */
public class d0 implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a0 f6205a;
    public boolean b = true;

    public d0(a0 a0Var) {
        this.f6205a = (a0) n1.a(a0Var);
    }

    @Override // com.zx.a.I8b7.y
    public boolean a(int i, String str) {
        int i2 = i & 240;
        if (i2 == 0 || i2 == 16) {
            return this.b;
        }
        return false;
    }

    @Override // com.zx.a.I8b7.y
    public void a(int i, String str, String str2, Throwable th) {
        if ((i & 240) != 0) {
            i &= 15;
        }
        this.f6205a.a(i, str, str2, th);
    }
}
