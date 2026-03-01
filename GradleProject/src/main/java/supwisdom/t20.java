package supwisdom;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: Mp3Extractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class t20 implements y30 {
    public static final int n;
    public static final int o;
    public static final int p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9237a;
    public final long b;
    public final o80 c;
    public final c50 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b50 f9238e;
    public z40 f;
    public f50 g;
    public int h;
    public com.google.android.exoplayer2.f.a i;
    public b j;
    public long k;
    public long l;
    public int m;

    /* JADX INFO: compiled from: Mp3Extractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new t20()};
        }
    }

    /* JADX INFO: compiled from: Mp3Extractor.java */
    public interface b extends e50 {
        long a(long j);
    }

    static {
        new a();
        n = x80.g("Xing");
        o = x80.g("Info");
        p = x80.g("VBRI");
    }

    public t20() {
        this(0);
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        return a(v40Var, true);
    }

    public final int b(v40 v40Var) throws InterruptedException, IOException {
        if (this.m == 0) {
            v40Var.a();
            if (!v40Var.b(this.c.f8644a, 0, 4, true)) {
                return -1;
            }
            this.c.c(0);
            int iN = this.c.n();
            if ((iN & (-128000)) != ((-128000) & this.h) || c50.a(iN) == -1) {
                v40Var.b(1);
                this.h = 0;
                return 0;
            }
            c50.a(iN, this.d);
            if (this.k == -9223372036854775807L) {
                this.k = this.j.a(v40Var.c());
                if (this.b != -9223372036854775807L) {
                    this.k += this.b - this.j.a(0L);
                }
            }
            this.m = this.d.c;
        }
        int iA = this.g.a(v40Var, this.m, true);
        if (iA == -1) {
            return -1;
        }
        int i = this.m - iA;
        this.m = i;
        if (i > 0) {
            return 0;
        }
        long j = this.k;
        long j2 = this.l * 1000000;
        c50 c50Var = this.d;
        this.g.a(j + (j2 / ((long) c50Var.d)), 1, c50Var.c, 0, null);
        this.l += (long) this.d.g;
        this.m = 0;
        return 0;
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public final void c(v40 v40Var) throws InterruptedException, IOException {
        int i = 0;
        while (true) {
            v40Var.c(this.c.f8644a, 0, 10);
            this.c.c(0);
            if (this.c.k() != l50.b) {
                v40Var.a();
                v40Var.c(i);
                return;
            }
            this.c.d(3);
            int iS = this.c.s();
            int i2 = iS + 10;
            if (this.i == null) {
                byte[] bArr = new byte[i2];
                System.arraycopy(this.c.f8644a, 0, bArr, 0, 10);
                v40Var.c(bArr, 10, iS);
                com.google.android.exoplayer2.f.a aVarA = new l50((this.f9237a & 2) != 0 ? b50.c : null).a(bArr, i2);
                this.i = aVarA;
                if (aVarA != null) {
                    this.f9238e.a(aVarA);
                }
            } else {
                v40Var.c(iS);
            }
            i += i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.t20.b d(supwisdom.v40 r14) throws java.lang.InterruptedException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.t20.d(supwisdom.v40):supwisdom.t20$b");
    }

    public t20(int i) {
        this(i, -9223372036854775807L);
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.f = z40Var;
        this.g = z40Var.a(0, 1);
        this.f.a();
    }

    public t20(int i, long j) {
        this.f9237a = i;
        this.b = j;
        this.c = new o80(10);
        this.d = new c50();
        this.f9238e = new b50();
        this.k = -9223372036854775807L;
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.h = 0;
        this.k = -9223372036854775807L;
        this.l = 0L;
        this.m = 0;
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        if (this.h == 0) {
            try {
                a(v40Var, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.j == null) {
            b bVarD = d(v40Var);
            this.j = bVarD;
            this.f.a(bVarD);
            f50 f50Var = this.g;
            c50 c50Var = this.d;
            String str = c50Var.b;
            int i = c50Var.f7147e;
            int i2 = c50Var.d;
            b50 b50Var = this.f9238e;
            f50Var.a(com.google.android.exoplayer2.j.a((String) null, str, (String) null, -1, 4096, i, i2, -1, b50Var.f7024a, b50Var.b, (List<byte[]>) null, (com.google.android.exoplayer2.c.a) null, 0, (String) null, (this.f9237a & 2) != 0 ? null : this.i));
        }
        return b(v40Var);
    }

    public final boolean a(v40 v40Var, boolean z) throws InterruptedException, IOException {
        int i;
        int iB;
        int iA;
        int i2 = z ? 4096 : 131072;
        v40Var.a();
        if (v40Var.c() == 0) {
            c(v40Var);
            iB = (int) v40Var.b();
            if (!z) {
                v40Var.b(iB);
            }
            i = 0;
        } else {
            i = 0;
            iB = 0;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (!v40Var.b(this.c.f8644a, 0, 4, i > 0)) {
                break;
            }
            this.c.c(0);
            int iN = this.c.n();
            if ((i3 == 0 || (iN & (-128000)) == ((-128000) & i3)) && (iA = c50.a(iN)) != -1) {
                i++;
                if (i != 1) {
                    if (i == 4) {
                        break;
                    }
                } else {
                    c50.a(iN, this.d);
                    i3 = iN;
                }
                v40Var.c(iA - 4);
            } else {
                int i5 = i4 + 1;
                if (i4 == i2) {
                    if (z) {
                        return false;
                    }
                    throw new com.google.android.exoplayer2.n("Searched too many bytes.");
                }
                if (z) {
                    v40Var.a();
                    v40Var.c(iB + i5);
                } else {
                    v40Var.b(1);
                }
                i4 = i5;
                i = 0;
                i3 = 0;
            }
        }
        if (z) {
            v40Var.b(iB + i4);
        } else {
            v40Var.a();
        }
        this.h = i3;
        return true;
    }
}
