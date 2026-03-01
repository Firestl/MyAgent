package supwisdom;

import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: compiled from: DefaultOggSeeker.java */
/* JADX INFO: loaded from: classes.dex */
public final class l30 implements q30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p30 f8238a = new p30();
    public final long b;
    public final long c;
    public final s30 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8239e;
    public long f;
    public long g;
    public long h;
    public long i;
    public long j;
    public long k;
    public long l;

    public l30(long j, long j2, s30 s30Var, int i, long j3) {
        e80.a(j >= 0 && j2 > j);
        this.d = s30Var;
        this.b = j;
        this.c = j2;
        if (i != j2 - j) {
            this.f8239e = 0;
        } else {
            this.f = j3;
            this.f8239e = 3;
        }
    }

    public void b() {
        this.i = this.b;
        this.j = this.c;
        this.k = 0L;
        this.l = this.f;
    }

    /* JADX INFO: compiled from: DefaultOggSeeker.java */
    public class b implements e50 {
        public b() {
        }

        @Override // supwisdom.e50
        public boolean a() {
            return true;
        }

        @Override // supwisdom.e50
        public long b(long j) {
            if (j == 0) {
                return l30.this.b;
            }
            long jB = l30.this.d.b(j);
            l30 l30Var = l30.this;
            return l30Var.a(l30Var.b, jB, com.igexin.push.config.c.k);
        }

        @Override // supwisdom.e50
        public long b() {
            return l30.this.d.a(l30.this.f);
        }
    }

    @Override // supwisdom.q30
    public long a(v40 v40Var) throws InterruptedException, IOException {
        int i = this.f8239e;
        if (i == 0) {
            long jC = v40Var.c();
            this.g = jC;
            this.f8239e = 1;
            long j = this.c - 65307;
            if (j > jC) {
                return j;
            }
        } else if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return -1L;
                }
                throw new IllegalStateException();
            }
            long j2 = this.h;
            long jA = 0;
            if (j2 != 0) {
                long jA2 = a(j2, v40Var);
                if (jA2 >= 0) {
                    return jA2;
                }
                jA = a(v40Var, this.h, -(jA2 + 2));
            }
            this.f8239e = 3;
            return -(jA + 2);
        }
        this.f = c(v40Var);
        this.f8239e = 3;
        return this.g;
    }

    public long c(v40 v40Var) throws InterruptedException, IOException {
        b(v40Var);
        this.f8238a.a();
        while ((this.f8238a.b & 4) != 4 && v40Var.c() < this.c) {
            this.f8238a.a(v40Var, false);
            p30 p30Var = this.f8238a;
            v40Var.b(p30Var.f8764e + p30Var.f);
        }
        return this.f8238a.c;
    }

    public void b(v40 v40Var) throws InterruptedException, IOException {
        if (!a(v40Var, this.c)) {
            throw new EOFException();
        }
    }

    @Override // supwisdom.q30
    public long a(long j) {
        int i = this.f8239e;
        e80.a(i == 3 || i == 2);
        this.h = j != 0 ? this.d.b(j) : 0L;
        this.f8239e = 2;
        b();
        return this.h;
    }

    @Override // supwisdom.q30
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public b c() {
        if (this.f != 0) {
            return new b();
        }
        return null;
    }

    public long a(long j, v40 v40Var) throws InterruptedException, IOException {
        if (this.i == this.j) {
            return -(this.k + 2);
        }
        long jC = v40Var.c();
        if (!a(v40Var, this.j)) {
            long j2 = this.i;
            if (j2 != jC) {
                return j2;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.f8238a.a(v40Var, false);
        v40Var.a();
        p30 p30Var = this.f8238a;
        long j3 = j - p30Var.c;
        int i = p30Var.f8764e + p30Var.f;
        if (j3 >= 0 && j3 <= 72000) {
            v40Var.b(i);
            return -(this.f8238a.c + 2);
        }
        if (j3 < 0) {
            this.j = jC;
            this.l = this.f8238a.c;
        } else {
            long j4 = i;
            long jC2 = v40Var.c() + j4;
            this.i = jC2;
            this.k = this.f8238a.c;
            if ((this.j - jC2) + j4 < 100000) {
                v40Var.b(i);
                return -(this.k + 2);
            }
        }
        long j5 = this.j;
        long j6 = this.i;
        if (j5 - j6 < 100000) {
            this.j = j6;
            return j6;
        }
        long jC3 = v40Var.c() - ((long) (i * (j3 <= 0 ? 2 : 1)));
        long j7 = this.j;
        long j8 = this.i;
        return Math.min(Math.max(jC3 + ((j3 * (j7 - j8)) / (this.l - this.k)), j8), this.j - 1);
    }

    public final long a(long j, long j2, long j3) {
        long j4 = this.c;
        long j5 = this.b;
        long j6 = j + (((j2 * (j4 - j5)) / this.f) - j3);
        if (j6 >= j5) {
            j5 = j6;
        }
        long j7 = this.c;
        return j5 >= j7 ? j7 - 1 : j5;
    }

    public boolean a(v40 v40Var, long j) throws InterruptedException, IOException {
        int i;
        long jMin = Math.min(j + 3, this.c);
        int iC = 2048;
        byte[] bArr = new byte[2048];
        while (true) {
            int i2 = 0;
            if (v40Var.c() + ((long) iC) > jMin && (iC = (int) (jMin - v40Var.c())) < 4) {
                return false;
            }
            v40Var.b(bArr, 0, iC, false);
            while (true) {
                i = iC - 3;
                if (i2 < i) {
                    if (bArr[i2] == 79 && bArr[i2 + 1] == 103 && bArr[i2 + 2] == 103 && bArr[i2 + 3] == 83) {
                        v40Var.b(i2);
                        return true;
                    }
                    i2++;
                }
            }
            v40Var.b(i);
        }
    }

    public long a(v40 v40Var, long j, long j2) throws InterruptedException, IOException {
        this.f8238a.a(v40Var, false);
        while (true) {
            p30 p30Var = this.f8238a;
            if (p30Var.c < j) {
                v40Var.b(p30Var.f8764e + p30Var.f);
                p30 p30Var2 = this.f8238a;
                long j3 = p30Var2.c;
                p30Var2.a(v40Var, false);
                j2 = j3;
            } else {
                v40Var.a();
                return j2;
            }
        }
    }
}
