package supwisdom;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.Stack;
import supwisdom.y20;

/* JADX INFO: compiled from: Mp4Extractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class e30 implements y30, e50 {
    public static final int p;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7414e;
    public int f;
    public long g;
    public int h;
    public o80 i;
    public int j;
    public int k;
    public z40 l;
    public b[] m;
    public long n;
    public boolean o;
    public final o80 c = new o80(16);
    public final Stack<y20.a> d = new Stack<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f7413a = new o80(m80.f8362a);
    public final o80 b = new o80(4);

    /* JADX INFO: compiled from: Mp4Extractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new e30()};
        }
    }

    /* JADX INFO: compiled from: Mp4Extractor.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h30 f7415a;
        public final k30 b;
        public final f50 c;
        public int d;

        public b(h30 h30Var, k30 k30Var, f50 f50Var) {
            this.f7415a = h30Var;
            this.b = k30Var;
            this.c = f50Var;
        }
    }

    static {
        new a();
        p = x80.g("qt  ");
    }

    @Override // supwisdom.e50
    public boolean a() {
        return true;
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        return g30.b(v40Var);
    }

    @Override // supwisdom.e50
    public long b() {
        return this.n;
    }

    public final int c(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        int iE = e();
        if (iE == -1) {
            return -1;
        }
        b bVar = this.m[iE];
        f50 f50Var = bVar.c;
        int i = bVar.d;
        k30 k30Var = bVar.b;
        long j = k30Var.b[i];
        int i2 = k30Var.c[i];
        if (bVar.f7415a.g == 1) {
            j += 8;
            i2 -= 8;
        }
        long jC = (j - v40Var.c()) + ((long) this.j);
        if (jC < 0 || jC >= PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
            d50Var.f7296a = j;
            return 1;
        }
        v40Var.b((int) jC);
        int i3 = bVar.f7415a.k;
        if (i3 == 0) {
            while (true) {
                int i4 = this.j;
                if (i4 >= i2) {
                    break;
                }
                int iA = f50Var.a(v40Var, i2 - i4, false);
                this.j += iA;
                this.k -= iA;
            }
        } else {
            byte[] bArr = this.b.f8644a;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i5 = 4 - i3;
            while (this.j < i2) {
                int i6 = this.k;
                if (i6 == 0) {
                    v40Var.b(this.b.f8644a, i5, i3);
                    this.b.c(0);
                    this.k = this.b.t();
                    this.f7413a.c(0);
                    f50Var.a(this.f7413a, 4);
                    this.j += 4;
                    i2 += i5;
                } else {
                    int iA2 = f50Var.a(v40Var, i6, false);
                    this.j += iA2;
                    this.k -= iA2;
                }
            }
        }
        k30 k30Var2 = bVar.b;
        f50Var.a(k30Var2.f8118e[i], k30Var2.f[i], i2, 0, null);
        bVar.d++;
        this.j = 0;
        this.k = 0;
        return 0;
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public final void d() {
        this.f7414e = 0;
        this.h = 0;
    }

    public final int e() {
        int i = -1;
        long j = Long.MAX_VALUE;
        int i2 = 0;
        while (true) {
            b[] bVarArr = this.m;
            if (i2 >= bVarArr.length) {
                return i;
            }
            b bVar = bVarArr[i2];
            int i3 = bVar.d;
            k30 k30Var = bVar.b;
            if (i3 != k30Var.f8117a) {
                long j2 = k30Var.b[i3];
                if (j2 < j) {
                    i = i2;
                    j = j2;
                }
            }
            i2++;
        }
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.l = z40Var;
    }

    @Override // supwisdom.e50
    public long b(long j) {
        long j2 = Long.MAX_VALUE;
        for (b bVar : this.m) {
            k30 k30Var = bVar.b;
            int iA = k30Var.a(j);
            if (iA == -1) {
                iA = k30Var.b(j);
            }
            long j3 = k30Var.b[iA];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.d.clear();
        this.h = 0;
        this.j = 0;
        this.k = 0;
        if (j == 0) {
            d();
        } else if (this.m != null) {
            c(j2);
        }
    }

    public final boolean b(v40 v40Var) throws InterruptedException, IOException {
        if (this.h == 0) {
            if (!v40Var.a(this.c.f8644a, 0, 8, true)) {
                return false;
            }
            this.h = 8;
            this.c.c(0);
            this.g = this.c.l();
            this.f = this.c.n();
        }
        if (this.g == 1) {
            v40Var.b(this.c.f8644a, 8, 8);
            this.h += 8;
            this.g = this.c.v();
        }
        if (b(this.f)) {
            long jC = (v40Var.c() + this.g) - ((long) this.h);
            this.d.add(new y20.a(this.f, jC));
            if (this.g == this.h) {
                a(jC);
            } else {
                d();
            }
        } else if (a(this.f)) {
            e80.b(this.h == 8);
            e80.b(this.g <= 2147483647L);
            o80 o80Var = new o80((int) this.g);
            this.i = o80Var;
            System.arraycopy(this.c.f8644a, 0, o80Var.f8644a, 0, 8);
            this.f7414e = 1;
        } else {
            this.i = null;
            this.f7414e = 1;
        }
        return true;
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        while (true) {
            int i = this.f7414e;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        return c(v40Var, d50Var);
                    }
                    throw new IllegalStateException();
                }
                if (b(v40Var, d50Var)) {
                    return 1;
                }
            } else if (!b(v40Var)) {
                return -1;
            }
        }
    }

    public final void a(long j) throws com.google.android.exoplayer2.n {
        while (!this.d.isEmpty() && this.d.peek().P0 == j) {
            y20.a aVarPop = this.d.pop();
            if (aVarPop.f9840a == y20.C) {
                a(aVarPop);
                this.d.clear();
                this.f7414e = 2;
            } else if (!this.d.isEmpty()) {
                this.d.peek().a(aVarPop);
            }
        }
        if (this.f7414e != 2) {
            d();
        }
    }

    public static boolean a(o80 o80Var) {
        o80Var.c(8);
        if (o80Var.n() == p) {
            return true;
        }
        o80Var.d(4);
        while (o80Var.b() > 0) {
            if (o80Var.n() == p) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(supwisdom.y20.a r19) throws com.google.android.exoplayer2.n {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.e30.a(supwisdom.y20$a):void");
    }

    public final boolean b(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        boolean z;
        long j = this.g - ((long) this.h);
        long jC = v40Var.c() + j;
        o80 o80Var = this.i;
        if (o80Var != null) {
            v40Var.b(o80Var.f8644a, this.h, (int) j);
            if (this.f == y20.b) {
                this.o = a(this.i);
            } else if (!this.d.isEmpty()) {
                this.d.peek().a(new y20.b(this.f, this.i));
            }
        } else if (j < PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
            v40Var.b((int) j);
        } else {
            d50Var.f7296a = v40Var.c() + j;
            z = true;
            a(jC);
            return (z || this.f7414e == 2) ? false : true;
        }
        z = false;
        a(jC);
        if (z) {
        }
    }

    public final void c(long j) {
        for (b bVar : this.m) {
            k30 k30Var = bVar.b;
            int iA = k30Var.a(j);
            if (iA == -1) {
                iA = k30Var.b(j);
            }
            bVar.d = iA;
        }
    }

    public static boolean b(int i) {
        return i == y20.C || i == y20.E || i == y20.F || i == y20.G || i == y20.H || i == y20.Q;
    }

    public static boolean a(int i) {
        return i == y20.S || i == y20.D || i == y20.T || i == y20.U || i == y20.m0 || i == y20.n0 || i == y20.o0 || i == y20.R || i == y20.p0 || i == y20.q0 || i == y20.r0 || i == y20.s0 || i == y20.t0 || i == y20.P || i == y20.b || i == y20.A0;
    }
}
