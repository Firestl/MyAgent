package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: TrackFragment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a30 f8015a;
    public long b;
    public long c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8016e;
    public int f;
    public long[] g;
    public int[] h;
    public int[] i;
    public int[] j;
    public long[] k;
    public boolean[] l;
    public boolean m;
    public boolean[] n;
    public i30 o;
    public int p;
    public o80 q;
    public boolean r;
    public long s;

    public void a() {
        this.f8016e = 0;
        this.s = 0L;
        this.m = false;
        this.r = false;
        this.o = null;
    }

    public long b(int i) {
        return this.k[i] + ((long) this.j[i]);
    }

    public void a(int i, int i2) {
        this.f8016e = i;
        this.f = i2;
        int[] iArr = this.h;
        if (iArr == null || iArr.length < i) {
            this.g = new long[i];
            this.h = new int[i];
        }
        int[] iArr2 = this.i;
        if (iArr2 == null || iArr2.length < i2) {
            int i3 = (i2 * 125) / 100;
            this.i = new int[i3];
            this.j = new int[i3];
            this.k = new long[i3];
            this.l = new boolean[i3];
            this.n = new boolean[i3];
        }
    }

    public void a(int i) {
        o80 o80Var = this.q;
        if (o80Var == null || o80Var.c() < i) {
            this.q = new o80(i);
        }
        this.p = i;
        this.m = true;
        this.r = true;
    }

    public void a(v40 v40Var) throws InterruptedException, IOException {
        v40Var.b(this.q.f8644a, 0, this.p);
        this.q.c(0);
        this.r = false;
    }

    public void a(o80 o80Var) {
        o80Var.a(this.q.f8644a, 0, this.p);
        this.q.c(0);
        this.r = false;
    }
}
