package supwisdom;

import android.text.Layout;

/* JADX INFO: compiled from: TtmlStyle.java */
/* JADX INFO: loaded from: classes.dex */
public final class k60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8127a;
    public int b;
    public boolean c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8128e;
    public int f = -1;
    public int g = -1;
    public int h = -1;
    public int i = -1;
    public int j = -1;
    public float k;
    public String l;
    public k60 m;
    public Layout.Alignment n;

    public int a() {
        if (this.h == -1 && this.i == -1) {
            return -1;
        }
        return (this.h == 1 ? 1 : 0) | (this.i == 1 ? 2 : 0);
    }

    public boolean b() {
        return this.f == 1;
    }

    public boolean c() {
        return this.g == 1;
    }

    public k60 d(boolean z) {
        e80.b(this.m == null);
        this.i = z ? 1 : 0;
        return this;
    }

    public int e() {
        if (this.c) {
            return this.b;
        }
        throw new IllegalStateException("Font color has not been defined.");
    }

    public boolean f() {
        return this.c;
    }

    public int g() {
        if (this.f8128e) {
            return this.d;
        }
        throw new IllegalStateException("Background color has not been defined.");
    }

    public boolean h() {
        return this.f8128e;
    }

    public String i() {
        return this.l;
    }

    public Layout.Alignment j() {
        return this.n;
    }

    public int k() {
        return this.j;
    }

    public float l() {
        return this.k;
    }

    public k60 b(boolean z) {
        e80.b(this.m == null);
        this.g = z ? 1 : 0;
        return this;
    }

    public k60 c(boolean z) {
        e80.b(this.m == null);
        this.h = z ? 1 : 0;
        return this;
    }

    public k60 a(boolean z) {
        e80.b(this.m == null);
        this.f = z ? 1 : 0;
        return this;
    }

    public String d() {
        return this.f8127a;
    }

    public k60 b(int i) {
        this.d = i;
        this.f8128e = true;
        return this;
    }

    public k60 c(int i) {
        this.j = i;
        return this;
    }

    public k60 a(String str) {
        e80.b(this.m == null);
        this.f8127a = str;
        return this;
    }

    public k60 b(String str) {
        this.l = str;
        return this;
    }

    public k60 a(int i) {
        e80.b(this.m == null);
        this.b = i;
        this.c = true;
        return this;
    }

    public k60 a(k60 k60Var) {
        a(k60Var, true);
        return this;
    }

    public final k60 a(k60 k60Var, boolean z) {
        if (k60Var != null) {
            if (!this.c && k60Var.c) {
                a(k60Var.b);
            }
            if (this.h == -1) {
                this.h = k60Var.h;
            }
            if (this.i == -1) {
                this.i = k60Var.i;
            }
            if (this.f8127a == null) {
                this.f8127a = k60Var.f8127a;
            }
            if (this.f == -1) {
                this.f = k60Var.f;
            }
            if (this.g == -1) {
                this.g = k60Var.g;
            }
            if (this.n == null) {
                this.n = k60Var.n;
            }
            if (this.j == -1) {
                this.j = k60Var.j;
                this.k = k60Var.k;
            }
            if (z && !this.f8128e && k60Var.f8128e) {
                b(k60Var.d);
            }
        }
        return this;
    }

    public k60 a(Layout.Alignment alignment) {
        this.n = alignment;
        return this;
    }

    public k60 a(float f) {
        this.k = f;
        return this;
    }
}
