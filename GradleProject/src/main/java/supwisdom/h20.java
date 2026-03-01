package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: FlvExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class h20 implements y30, e50 {
    public static final int n;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public z40 f7791e;
    public int g;
    public int h;
    public int i;
    public long j;
    public g20 k;
    public j20 l;
    public i20 m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f7790a = new o80(4);
    public final o80 b = new o80(9);
    public final o80 c = new o80(11);
    public final o80 d = new o80();
    public int f = 1;

    /* JADX INFO: compiled from: FlvExtractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new h20()};
        }
    }

    static {
        new a();
        n = x80.g("FLV");
    }

    @Override // supwisdom.e50
    public boolean a() {
        return false;
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        v40Var.c(this.f7790a.f8644a, 0, 3);
        this.f7790a.c(0);
        if (this.f7790a.k() != n) {
            return false;
        }
        v40Var.c(this.f7790a.f8644a, 0, 2);
        this.f7790a.c(0);
        if ((this.f7790a.h() & 250) != 0) {
            return false;
        }
        v40Var.c(this.f7790a.f8644a, 0, 4);
        this.f7790a.c(0);
        int iN = this.f7790a.n();
        v40Var.a();
        v40Var.c(iN);
        v40Var.c(this.f7790a.f8644a, 0, 4);
        this.f7790a.c(0);
        return this.f7790a.n() == 0;
    }

    @Override // supwisdom.e50
    public long b(long j) {
        return 0L;
    }

    public final boolean b(v40 v40Var) throws InterruptedException, IOException {
        if (!v40Var.a(this.b.f8644a, 0, 9, true)) {
            return false;
        }
        this.b.c(0);
        this.b.d(4);
        int iG = this.b.g();
        boolean z = (iG & 4) != 0;
        boolean z2 = (iG & 1) != 0;
        if (z && this.k == null) {
            this.k = new g20(this.f7791e.a(8, 1));
        }
        if (z2 && this.l == null) {
            this.l = new j20(this.f7791e.a(9, 2));
        }
        if (this.m == null) {
            this.m = new i20(null);
        }
        this.f7791e.a();
        this.f7791e.a(this);
        this.g = (this.b.n() - 9) + 4;
        this.f = 2;
        return true;
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public final void c(v40 v40Var) throws InterruptedException, IOException {
        v40Var.b(this.g);
        this.g = 0;
        this.f = 3;
    }

    public final boolean d(v40 v40Var) throws InterruptedException, IOException {
        if (!v40Var.a(this.c.f8644a, 0, 11, true)) {
            return false;
        }
        this.c.c(0);
        this.h = this.c.g();
        this.i = this.c.k();
        this.j = this.c.k();
        this.j = (((long) (this.c.g() << 24)) | this.j) * 1000;
        this.c.d(3);
        this.f = 4;
        return true;
    }

    public final boolean e(v40 v40Var) throws InterruptedException, IOException {
        boolean z;
        i20 i20Var;
        j20 j20Var;
        g20 g20Var;
        if (this.h == 8 && (g20Var = this.k) != null) {
            g20Var.b(f(v40Var), this.j);
        } else if (this.h == 9 && (j20Var = this.l) != null) {
            j20Var.b(f(v40Var), this.j);
        } else {
            if (this.h != 18 || (i20Var = this.m) == null) {
                v40Var.b(this.i);
                z = false;
                this.g = 4;
                this.f = 2;
                return z;
            }
            i20Var.b(f(v40Var), this.j);
        }
        z = true;
        this.g = 4;
        this.f = 2;
        return z;
    }

    public final o80 f(v40 v40Var) throws InterruptedException, IOException {
        if (this.i > this.d.e()) {
            o80 o80Var = this.d;
            o80Var.a(new byte[Math.max(o80Var.e() * 2, this.i)], 0);
        } else {
            this.d.c(0);
        }
        this.d.b(this.i);
        v40Var.b(this.d.f8644a, 0, this.i);
        return this.d;
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.f7791e = z40Var;
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.f = 1;
        this.g = 0;
    }

    @Override // supwisdom.e50
    public long b() {
        return this.m.a();
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        while (true) {
            int i = this.f;
            if (i != 1) {
                if (i == 2) {
                    c(v40Var);
                } else if (i != 3) {
                    if (i == 4 && e(v40Var)) {
                        return 0;
                    }
                } else if (!d(v40Var)) {
                    return -1;
                }
            } else if (!b(v40Var)) {
                return -1;
            }
        }
    }
}
