package supwisdom;

import android.util.Log;
import android.util.Pair;
import java.util.Arrays;
import java.util.Collections;
import supwisdom.u40;

/* JADX INFO: compiled from: AdtsReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class c40 implements g40 {
    public static final byte[] r = {73, 68, 51};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7142a;
    public final n80 b;
    public final o80 c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f7143e;
    public f50 f;
    public f50 g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;
    public long m;
    public int n;
    public long o;
    public f50 p;
    public long q;

    public c40(boolean z) {
        this(z, null);
    }

    @Override // supwisdom.g40
    public void a() {
        c();
    }

    @Override // supwisdom.g40
    public void b() {
    }

    public final void b(o80 o80Var) {
        byte[] bArr = o80Var.f8644a;
        int iD = o80Var.d();
        int iC = o80Var.c();
        while (iD < iC) {
            int i = iD + 1;
            int i2 = bArr[iD] & 255;
            if (this.j == 512 && i2 >= 240 && i2 != 255) {
                this.k = (i2 & 1) == 0;
                e();
                o80Var.c(i);
                return;
            }
            int i3 = this.j;
            int i4 = i2 | i3;
            if (i4 == 329) {
                this.j = 768;
            } else if (i4 == 511) {
                this.j = 512;
            } else if (i4 == 836) {
                this.j = 1024;
            } else if (i4 == 1075) {
                d();
                o80Var.c(i);
                return;
            } else if (i3 != 256) {
                this.j = 256;
                i--;
            }
            iD = i;
        }
        o80Var.c(iD);
    }

    public final void c() {
        this.h = 0;
        this.i = 0;
        this.j = 256;
    }

    public final void d() {
        this.h = 1;
        this.i = r.length;
        this.n = 0;
        this.c.c(0);
    }

    public final void e() {
        this.h = 2;
        this.i = 0;
    }

    public final void f() {
        this.g.a(this.c, 10);
        this.c.c(6);
        a(this.g, 0L, 10, this.c.s() + 10);
    }

    public final void g() {
        this.b.a(0);
        if (this.l) {
            this.b.b(10);
        } else {
            int iC = this.b.c(2) + 1;
            if (iC != 2) {
                Log.w("AdtsReader", "Detected audio object type: " + iC + ", but assuming AAC LC.");
                iC = 2;
            }
            int iC2 = this.b.c(4);
            this.b.b(1);
            byte[] bArrA = f80.a(iC, iC2, this.b.c(3));
            Pair<Integer, Integer> pairA = f80.a(bArrA);
            com.google.android.exoplayer2.j jVarA = com.google.android.exoplayer2.j.a(this.f7143e, "audio/mp4a-latm", null, -1, -1, ((Integer) pairA.second).intValue(), ((Integer) pairA.first).intValue(), Collections.singletonList(bArrA), null, 0, this.d);
            this.m = 1024000000 / ((long) jVarA.s);
            this.f.a(jVarA);
            this.l = true;
        }
        this.b.b(4);
        int iC3 = (this.b.c(13) - 2) - 5;
        if (this.k) {
            iC3 -= 2;
        }
        a(this.f, this.m, 0, iC3);
    }

    public c40(boolean z, String str) {
        this.b = new n80(new byte[7]);
        this.c = new o80(Arrays.copyOf(r, 10));
        c();
        this.f7142a = z;
        this.d = str;
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        this.f7143e = dVar.c();
        this.f = z40Var.a(dVar.b(), 1);
        if (!this.f7142a) {
            this.g = new w30();
            return;
        }
        dVar.a();
        f50 f50VarA = z40Var.a(dVar.b(), 4);
        this.g = f50VarA;
        f50VarA.a(com.google.android.exoplayer2.j.a(dVar.c(), "application/id3", null, -1, null));
    }

    public final void c(o80 o80Var) {
        int iMin = Math.min(o80Var.b(), this.n - this.i);
        this.p.a(o80Var, iMin);
        int i = this.i + iMin;
        this.i = i;
        int i2 = this.n;
        if (i == i2) {
            this.p.a(this.o, 1, i2, 0, null);
            this.o += this.q;
            c();
        }
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        this.o = j;
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        while (o80Var.b() > 0) {
            int i = this.h;
            if (i == 0) {
                b(o80Var);
            } else if (i != 1) {
                if (i == 2) {
                    if (a(o80Var, this.b.f8483a, this.k ? 7 : 5)) {
                        g();
                    }
                } else if (i == 3) {
                    c(o80Var);
                }
            } else if (a(o80Var, this.c.f8644a, 10)) {
                f();
            }
        }
    }

    public final boolean a(o80 o80Var, byte[] bArr, int i) {
        int iMin = Math.min(o80Var.b(), i - this.i);
        o80Var.a(bArr, this.i, iMin);
        int i2 = this.i + iMin;
        this.i = i2;
        return i2 == i;
    }

    public final void a(f50 f50Var, long j, int i, int i2) {
        this.h = 3;
        this.i = i;
        this.p = f50Var;
        this.q = j;
        this.n = i2;
    }
}
