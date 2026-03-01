package supwisdom;

import android.os.SystemClock;
import com.google.android.exoplayer2.i.q;
import java.io.IOException;
import java.util.List;
import supwisdom.aa0;
import supwisdom.s70;

/* JADX INFO: compiled from: DefaultDashChunkSource.java */
/* JADX INFO: loaded from: classes.dex */
public class qa0 implements aa0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b80 f8901a;
    public final int b;
    public final k70 c;
    public final b[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s70 f8902e;
    public final long f;
    public final int g;
    public ca0 h;
    public int i;
    public IOException j;
    public boolean k;

    /* JADX INFO: compiled from: DefaultDashChunkSource.java */
    public static final class a implements aa0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final s70.a f8903a;
        public final int b;

        public a(s70.a aVar) {
            this(aVar, 1);
        }

        @Override // supwisdom.aa0.a
        public aa0 a(b80 b80Var, ca0 ca0Var, int i, int i2, k70 k70Var, long j, boolean z, boolean z2) {
            return new qa0(b80Var, ca0Var, i, i2, k70Var, this.f8903a.a(), j, this.b, z, z2);
        }

        public a(s70.a aVar, int i) {
            this.f8903a = aVar;
            this.b = i;
        }
    }

    /* JADX INFO: compiled from: DefaultDashChunkSource.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8904a;
        public final o90 b;
        public ga0 c;
        public oa0 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f8905e;
        public int f;

        public b(long j, ga0 ga0Var, boolean z, boolean z2, int i) {
            y30 c30Var;
            this.f8905e = j;
            this.c = ga0Var;
            this.f8904a = i;
            String str = ga0Var.f7707a.f2304e;
            if (b(str)) {
                this.b = null;
            } else {
                if ("application/x-rawcc".equals(str)) {
                    c30Var = new x30(ga0Var.f7707a);
                } else if (a(str)) {
                    c30Var = new o20(1);
                } else {
                    int i2 = z ? 4 : 0;
                    c30Var = new c30(z2 ? i2 | 8 : i2);
                }
                this.b = new o90(c30Var, ga0Var.f7707a);
            }
            this.d = ga0Var.e();
        }

        public void a(long j, ga0 ga0Var) throws com.google.android.exoplayer2.source.b {
            int iA;
            oa0 oa0VarE = this.c.e();
            oa0 oa0VarE2 = ga0Var.e();
            this.f8905e = j;
            this.c = ga0Var;
            if (oa0VarE == null) {
                return;
            }
            this.d = oa0VarE2;
            if (oa0VarE.b() && (iA = oa0VarE.a(this.f8905e)) != 0) {
                int iA2 = (oa0VarE.a() + iA) - 1;
                long jA = oa0VarE.a(iA2) + oa0VarE.a(iA2, this.f8905e);
                int iA3 = oa0VarE2.a();
                long jA2 = oa0VarE2.a(iA3);
                if (jA == jA2) {
                    this.f += (iA2 + 1) - iA3;
                } else {
                    if (jA < jA2) {
                        throw new com.google.android.exoplayer2.source.b();
                    }
                    this.f += oa0VarE.a(jA2, this.f8905e) - iA3;
                }
            }
        }

        public int b() {
            return this.d.a(this.f8905e);
        }

        public fa0 c(int i) {
            return this.d.b(i - this.f);
        }

        public long b(int i) {
            return a(i) + this.d.a(i - this.f, this.f8905e);
        }

        public static boolean b(String str) {
            return l80.c(str) || "application/ttml+xml".equals(str);
        }

        public int a() {
            return this.d.a() + this.f;
        }

        public long a(int i) {
            return this.d.a(i - this.f);
        }

        public int a(long j) {
            return this.d.a(j, this.f8905e) + this.f;
        }

        public static boolean a(String str) {
            return str.startsWith("video/webm") || str.startsWith("audio/webm") || str.startsWith("application/webm");
        }
    }

    public qa0(b80 b80Var, ca0 ca0Var, int i, int i2, k70 k70Var, s70 s70Var, long j, int i3, boolean z, boolean z2) {
        this.f8901a = b80Var;
        this.h = ca0Var;
        this.b = i2;
        this.c = k70Var;
        this.f8902e = s70Var;
        this.i = i;
        this.f = j;
        this.g = i3;
        long jC = ca0Var.c(i);
        ba0 ba0VarB = b();
        List<ga0> list = ba0VarB.c;
        this.d = new b[k70Var.e()];
        for (int i4 = 0; i4 < this.d.length; i4++) {
            this.d[i4] = new b(jC, list.get(k70Var.b(i4)), z, z2, ba0VarB.b);
        }
    }

    @Override // supwisdom.aa0
    public void a(ca0 ca0Var, int i) {
        try {
            this.h = ca0Var;
            this.i = i;
            long jC = ca0Var.c(i);
            List<ga0> list = b().c;
            for (int i2 = 0; i2 < this.d.length; i2++) {
                this.d[i2].a(jC, list.get(this.c.b(i2)));
            }
        } catch (com.google.android.exoplayer2.source.b e2) {
            this.j = e2;
        }
    }

    public final ba0 b() {
        return this.h.a(this.i).c.get(this.b);
    }

    public final long c() {
        return (this.f != 0 ? SystemClock.elapsedRealtime() + this.f : System.currentTimeMillis()) * 1000;
    }

    @Override // supwisdom.r90
    public void a() throws IOException {
        IOException iOException = this.j;
        if (iOException == null) {
            this.f8901a.d();
            return;
        }
        throw iOException;
    }

    @Override // supwisdom.r90
    public final void a(w90 w90Var, long j, p90 p90Var) {
        int iA;
        int iE;
        if (this.j != null) {
            return;
        }
        this.c.a(w90Var != null ? w90Var.g - j : 0L);
        b bVar = this.d[this.c.a()];
        o90 o90Var = bVar.b;
        if (o90Var != null) {
            ga0 ga0Var = bVar.c;
            fa0 fa0VarC = o90Var.c() == null ? ga0Var.c() : null;
            fa0 fa0VarD = bVar.d == null ? ga0Var.d() : null;
            if (fa0VarC != null || fa0VarD != null) {
                p90Var.f8778a = a(bVar, this.f8902e, this.c.f(), this.c.b(), this.c.c(), fa0VarC, fa0VarD);
                return;
            }
        }
        long jC = c();
        int iB = bVar.b();
        if (iB == 0) {
            ca0 ca0Var = this.h;
            p90Var.b = !ca0Var.c || this.i < ca0Var.a() - 1;
            return;
        }
        int iA2 = bVar.a();
        if (iB == -1) {
            ca0 ca0Var2 = this.h;
            long j2 = (jC - (ca0Var2.f7168a * 1000)) - (ca0Var2.a(this.i).b * 1000);
            long j3 = this.h.f7169e;
            if (j3 != -9223372036854775807L) {
                iA2 = Math.max(iA2, bVar.a(j2 - (j3 * 1000)));
            }
            iA = bVar.a(j2) - 1;
        } else {
            iA = (iB + iA2) - 1;
        }
        if (w90Var == null) {
            iE = x80.a(bVar.a(j), iA2, iA);
        } else {
            iE = w90Var.e();
            if (iE < iA2) {
                this.j = new com.google.android.exoplayer2.source.b();
                return;
            }
        }
        int i = iE;
        if (i <= iA && (!this.k || i < iA)) {
            p90Var.f8778a = a(bVar, this.f8902e, this.c.f(), this.c.b(), this.c.c(), i, Math.min(this.g, (iA - i) + 1));
        } else {
            ca0 ca0Var3 = this.h;
            p90Var.b = !ca0Var3.c || this.i < ca0Var3.a() - 1;
        }
    }

    @Override // supwisdom.r90
    public void a(n90 n90Var) {
        e50 e50VarB;
        if (n90Var instanceof v90) {
            b bVar = this.d[this.c.a(((v90) n90Var).c)];
            if (bVar.d != null || (e50VarB = bVar.b.b()) == null) {
                return;
            }
            bVar.d = new pa0((k20) e50VarB);
        }
    }

    @Override // supwisdom.r90
    public boolean a(n90 n90Var, boolean z, Exception exc) {
        b bVar;
        int iB;
        if (!z) {
            return false;
        }
        if (!this.h.c && (n90Var instanceof w90) && (exc instanceof q.e) && ((q.e) exc).responseCode == 404 && (iB = (bVar = this.d[this.c.a(n90Var.c)]).b()) != -1 && iB != 0) {
            if (((w90) n90Var).e() > (bVar.a() + iB) - 1) {
                this.k = true;
                return true;
            }
        }
        k70 k70Var = this.c;
        return s90.a(k70Var, k70Var.a(n90Var.c), exc);
    }

    public static n90 a(b bVar, s70 s70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, fa0 fa0Var, fa0 fa0Var2) {
        String str = bVar.c.b;
        if (fa0Var == null || (fa0Var2 = fa0Var.a(fa0Var2, str)) != null) {
            fa0Var = fa0Var2;
        }
        return new v90(s70Var, new u70(fa0Var.a(str), fa0Var.f7580a, fa0Var.b, bVar.c.f()), jVar, i, obj, bVar.b);
    }

    public static n90 a(b bVar, s70 s70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, int i2, int i3) {
        ga0 ga0Var = bVar.c;
        long jA = bVar.a(i2);
        fa0 fa0VarC = bVar.c(i2);
        String str = ga0Var.b;
        if (bVar.b == null) {
            return new x90(s70Var, new u70(fa0VarC.a(str), fa0VarC.f7580a, fa0VarC.b, ga0Var.f()), jVar, i, obj, jA, bVar.b(i2), i2, bVar.f8904a, jVar);
        }
        int i4 = 1;
        int i5 = 1;
        while (i4 < i3) {
            fa0 fa0VarA = fa0VarC.a(bVar.c(i2 + i4), str);
            if (fa0VarA == null) {
                break;
            }
            i5++;
            i4++;
            fa0VarC = fa0VarA;
        }
        return new t90(s70Var, new u70(fa0VarC.a(str), fa0VarC.f7580a, fa0VarC.b, ga0Var.f()), jVar, i, obj, jA, bVar.b((i2 + i5) - 1), i2, i5, -ga0Var.c, bVar.b);
    }
}
