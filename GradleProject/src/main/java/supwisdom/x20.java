package supwisdom;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DefaultTrackOutput.java */
/* JADX INFO: loaded from: classes.dex */
public final class x20 implements f50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q70 f9699a;
    public final int b;
    public final c c = new c();
    public final LinkedBlockingDeque<p70> d = new LinkedBlockingDeque<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f9700e = new b();
    public final o80 f = new o80(32);
    public final AtomicInteger g = new AtomicInteger();
    public long h;
    public com.google.android.exoplayer2.j i;
    public boolean j;
    public com.google.android.exoplayer2.j k;
    public long l;
    public long m;
    public p70 n;
    public int o;
    public boolean p;
    public d q;

    /* JADX INFO: compiled from: DefaultTrackOutput.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9701a;
        public long b;
        public long c;
        public byte[] d;

        public b() {
        }
    }

    /* JADX INFO: compiled from: DefaultTrackOutput.java */
    public interface d {
        void a(com.google.android.exoplayer2.j jVar);
    }

    public x20(q70 q70Var) {
        this.f9699a = q70Var;
        this.b = q70Var.c();
        this.o = this.b;
    }

    public void a(boolean z) {
        int andSet = this.g.getAndSet(z ? 0 : 2);
        l();
        this.c.b();
        if (andSet == 2) {
            this.i = null;
        }
    }

    public int b() {
        return this.c.c();
    }

    public void c() {
        if (this.g.getAndSet(2) == 0) {
            l();
        }
    }

    public boolean d() {
        return this.c.f();
    }

    public int e() {
        return this.c.d();
    }

    public int f() {
        return this.c.e();
    }

    public com.google.android.exoplayer2.j g() {
        return this.c.g();
    }

    public long h() {
        return this.c.h();
    }

    public void i() {
        long jI = this.c.i();
        if (jI != -1) {
            c(jI);
        }
    }

    public final boolean j() {
        return this.g.compareAndSet(0, 1);
    }

    public final void k() {
        if (this.g.compareAndSet(1, 0)) {
            return;
        }
        l();
    }

    public final void l() {
        this.c.a();
        q70 q70Var = this.f9699a;
        LinkedBlockingDeque<p70> linkedBlockingDeque = this.d;
        q70Var.a((p70[]) linkedBlockingDeque.toArray(new p70[linkedBlockingDeque.size()]));
        this.d.clear();
        this.f9699a.b();
        this.h = 0L;
        this.m = 0L;
        this.n = null;
        this.o = this.b;
    }

    /* JADX INFO: compiled from: DefaultTrackOutput.java */
    public static final class c {
        public int i;
        public int j;
        public int k;
        public int l;
        public com.google.android.exoplayer2.j q;
        public int r;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9702a = 1000;
        public int[] b = new int[1000];
        public long[] c = new long[1000];
        public long[] f = new long[1000];

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f9703e = new int[1000];
        public int[] d = new int[1000];
        public byte[][] g = new byte[1000][];
        public com.google.android.exoplayer2.j[] h = new com.google.android.exoplayer2.j[1000];
        public long m = Long.MIN_VALUE;
        public long n = Long.MIN_VALUE;
        public boolean p = true;
        public boolean o = true;

        public void a() {
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.i = 0;
            this.o = true;
        }

        public void b() {
            this.m = Long.MIN_VALUE;
            this.n = Long.MIN_VALUE;
        }

        public int c() {
            return this.j + this.i;
        }

        public int d() {
            return this.j;
        }

        public int e() {
            return this.i == 0 ? this.r : this.b[this.k];
        }

        public synchronized boolean f() {
            return this.i == 0;
        }

        public synchronized com.google.android.exoplayer2.j g() {
            return this.p ? null : this.q;
        }

        public synchronized long h() {
            return Math.max(this.m, this.n);
        }

        public synchronized long i() {
            if (this.i == 0) {
                return -1L;
            }
            int i = ((this.k + this.i) - 1) % this.f9702a;
            this.k = (this.k + this.i) % this.f9702a;
            this.j += this.i;
            this.i = 0;
            return this.c[i] + ((long) this.d[i]);
        }

        public void b(int i) {
            this.r = i;
        }

        public synchronized boolean b(long j) {
            if (this.m >= j) {
                return false;
            }
            int i = this.i;
            while (i > 0 && this.f[((this.k + i) - 1) % this.f9702a] >= j) {
                i--;
            }
            a(this.j + i);
            return true;
        }

        public long a(int i) {
            int iC = c() - i;
            e80.a(iC >= 0 && iC <= this.i);
            if (iC == 0) {
                if (this.j == 0) {
                    return 0L;
                }
                int i2 = this.l;
                if (i2 == 0) {
                    i2 = this.f9702a;
                }
                int i3 = i2 - 1;
                return this.c[i3] + ((long) this.d[i3]);
            }
            int i4 = this.i - iC;
            this.i = i4;
            int i5 = this.l;
            int i6 = this.f9702a;
            this.l = ((i5 + i6) - iC) % i6;
            this.n = Long.MIN_VALUE;
            for (int i7 = i4 - 1; i7 >= 0; i7--) {
                int i8 = (this.k + i7) % this.f9702a;
                this.n = Math.max(this.n, this.f[i8]);
                if ((this.f9703e[i8] & 1) != 0) {
                    break;
                }
            }
            return this.c[this.l];
        }

        public synchronized int a(e90 e90Var, y10 y10Var, boolean z, boolean z2, com.google.android.exoplayer2.j jVar, b bVar) {
            if (this.i == 0) {
                if (z2) {
                    y10Var.b(4);
                    return -4;
                }
                if (this.q == null || (!z && this.q == jVar)) {
                    return -3;
                }
                e90Var.f7455a = this.q;
                return -5;
            }
            if (!z && this.h[this.k] == jVar) {
                if (y10Var.f()) {
                    return -3;
                }
                y10Var.d = this.f[this.k];
                y10Var.b(this.f9703e[this.k]);
                bVar.f9701a = this.d[this.k];
                bVar.b = this.c[this.k];
                bVar.d = this.g[this.k];
                this.m = Math.max(this.m, y10Var.d);
                this.i--;
                int i = this.k + 1;
                this.k = i;
                this.j++;
                if (i == this.f9702a) {
                    this.k = 0;
                }
                bVar.c = this.i > 0 ? this.c[this.k] : bVar.b + ((long) bVar.f9701a);
                return -4;
            }
            e90Var.f7455a = this.h[this.k];
            return -5;
        }

        public synchronized long a(long j, boolean z) {
            if (this.i != 0 && j >= this.f[this.k]) {
                if (j > this.n && !z) {
                    return -1L;
                }
                int i = 0;
                int i2 = this.k;
                int i3 = -1;
                while (i2 != this.l && this.f[i2] <= j) {
                    if ((this.f9703e[i2] & 1) != 0) {
                        i3 = i;
                    }
                    i2 = (i2 + 1) % this.f9702a;
                    i++;
                }
                if (i3 == -1) {
                    return -1L;
                }
                int i4 = (this.k + i3) % this.f9702a;
                this.k = i4;
                this.j += i3;
                this.i -= i3;
                return this.c[i4];
            }
            return -1L;
        }

        public synchronized boolean a(com.google.android.exoplayer2.j jVar) {
            if (jVar == null) {
                this.p = true;
                return false;
            }
            this.p = false;
            if (x80.a(jVar, this.q)) {
                return false;
            }
            this.q = jVar;
            return true;
        }

        public synchronized void a(long j, int i, long j2, int i2, byte[] bArr) {
            if (this.o) {
                if ((i & 1) == 0) {
                    return;
                } else {
                    this.o = false;
                }
            }
            e80.b(!this.p);
            a(j);
            this.f[this.l] = j;
            this.c[this.l] = j2;
            this.d[this.l] = i2;
            this.f9703e[this.l] = i;
            this.g[this.l] = bArr;
            this.h[this.l] = this.q;
            this.b[this.l] = this.r;
            int i3 = this.i + 1;
            this.i = i3;
            if (i3 == this.f9702a) {
                int i4 = this.f9702a + 1000;
                int[] iArr = new int[i4];
                long[] jArr = new long[i4];
                long[] jArr2 = new long[i4];
                int[] iArr2 = new int[i4];
                int[] iArr3 = new int[i4];
                byte[][] bArr2 = new byte[i4][];
                com.google.android.exoplayer2.j[] jVarArr = new com.google.android.exoplayer2.j[i4];
                int i5 = this.f9702a - this.k;
                System.arraycopy(this.c, this.k, jArr, 0, i5);
                System.arraycopy(this.f, this.k, jArr2, 0, i5);
                System.arraycopy(this.f9703e, this.k, iArr2, 0, i5);
                System.arraycopy(this.d, this.k, iArr3, 0, i5);
                System.arraycopy(this.g, this.k, bArr2, 0, i5);
                System.arraycopy(this.h, this.k, jVarArr, 0, i5);
                System.arraycopy(this.b, this.k, iArr, 0, i5);
                int i6 = this.k;
                System.arraycopy(this.c, 0, jArr, i5, i6);
                System.arraycopy(this.f, 0, jArr2, i5, i6);
                System.arraycopy(this.f9703e, 0, iArr2, i5, i6);
                System.arraycopy(this.d, 0, iArr3, i5, i6);
                System.arraycopy(this.g, 0, bArr2, i5, i6);
                System.arraycopy(this.h, 0, jVarArr, i5, i6);
                System.arraycopy(this.b, 0, iArr, i5, i6);
                this.c = jArr;
                this.f = jArr2;
                this.f9703e = iArr2;
                this.d = iArr3;
                this.g = bArr2;
                this.h = jVarArr;
                this.b = iArr;
                this.k = 0;
                this.l = this.f9702a;
                this.i = this.f9702a;
                this.f9702a = i4;
            } else {
                int i7 = this.l + 1;
                this.l = i7;
                if (i7 == this.f9702a) {
                    this.l = 0;
                }
            }
        }

        public synchronized void a(long j) {
            this.n = Math.max(this.n, j);
        }
    }

    public void b(int i) {
        long jA = this.c.a(i);
        this.m = jA;
        b(jA);
    }

    public final void c(long j) {
        int i = ((int) (j - this.h)) / this.b;
        for (int i2 = 0; i2 < i; i2++) {
            this.f9699a.a(this.d.remove());
            this.h += (long) this.b;
        }
    }

    public final void b(long j) {
        int i = (int) (j - this.h);
        int i2 = this.b;
        int i3 = i / i2;
        int i4 = i % i2;
        int size = (this.d.size() - i3) - 1;
        if (i4 == 0) {
            size++;
        }
        for (int i5 = 0; i5 < size; i5++) {
            this.f9699a.a(this.d.removeLast());
        }
        this.n = this.d.peekLast();
        if (i4 == 0) {
            i4 = this.b;
        }
        this.o = i4;
    }

    public void a(int i) {
        this.c.b(i);
    }

    public void a() {
        this.p = true;
    }

    public boolean a(long j, boolean z) {
        long jA = this.c.a(j, z);
        if (jA == -1) {
            return false;
        }
        c(jA);
        return true;
    }

    public final int c(int i) {
        if (this.o == this.b) {
            this.o = 0;
            p70 p70VarA = this.f9699a.a();
            this.n = p70VarA;
            this.d.add(p70VarA);
        }
        return Math.min(i, this.b - this.o);
    }

    public int a(e90 e90Var, y10 y10Var, boolean z, boolean z2, long j) {
        int iA = this.c.a(e90Var, y10Var, z, z2, this.i, this.f9700e);
        if (iA == -5) {
            this.i = e90Var.f7455a;
            return -5;
        }
        if (iA != -4) {
            if (iA == -3) {
                return -3;
            }
            throw new IllegalStateException();
        }
        if (!y10Var.d()) {
            if (y10Var.d < j) {
                y10Var.c(Integer.MIN_VALUE);
            }
            if (y10Var.g()) {
                a(y10Var, this.f9700e);
            }
            y10Var.f(this.f9700e.f9701a);
            b bVar = this.f9700e;
            a(bVar.b, y10Var.c, bVar.f9701a);
            c(this.f9700e.c);
        }
        return -4;
    }

    public final void a(y10 y10Var, b bVar) {
        int iH;
        long j = bVar.b;
        this.f.a(1);
        a(j, this.f.f8644a, 1);
        long j2 = j + 1;
        byte b2 = this.f.f8644a[0];
        boolean z = (b2 & com.igexin.c.a.d.g.n) != 0;
        int i = b2 & 127;
        v10 v10Var = y10Var.b;
        if (v10Var.f9466a == null) {
            v10Var.f9466a = new byte[16];
        }
        a(j2, y10Var.b.f9466a, i);
        long j3 = j2 + ((long) i);
        if (z) {
            this.f.a(2);
            a(j3, this.f.f8644a, 2);
            j3 += 2;
            iH = this.f.h();
        } else {
            iH = 1;
        }
        int[] iArr = y10Var.b.d;
        if (iArr == null || iArr.length < iH) {
            iArr = new int[iH];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = y10Var.b.f9467e;
        if (iArr3 == null || iArr3.length < iH) {
            iArr3 = new int[iH];
        }
        int[] iArr4 = iArr3;
        if (z) {
            int i2 = iH * 6;
            this.f.a(i2);
            a(j3, this.f.f8644a, i2);
            j3 += (long) i2;
            this.f.c(0);
            for (int i3 = 0; i3 < iH; i3++) {
                iArr2[i3] = this.f.h();
                iArr4[i3] = this.f.t();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = bVar.f9701a - ((int) (j3 - bVar.b));
        }
        v10 v10Var2 = y10Var.b;
        v10Var2.a(iH, iArr2, iArr4, bVar.d, v10Var2.f9466a, 1);
        long j4 = bVar.b;
        int i4 = (int) (j3 - j4);
        bVar.b = j4 + ((long) i4);
        bVar.f9701a -= i4;
    }

    public final void a(long j, ByteBuffer byteBuffer, int i) {
        while (i > 0) {
            c(j);
            int i2 = (int) (j - this.h);
            int iMin = Math.min(i, this.b - i2);
            p70 p70VarPeek = this.d.peek();
            byteBuffer.put(p70VarPeek.f8774a, p70VarPeek.a(i2), iMin);
            j += (long) iMin;
            i -= iMin;
        }
    }

    public final void a(long j, byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            c(j);
            int i3 = (int) (j - this.h);
            int iMin = Math.min(i - i2, this.b - i3);
            p70 p70VarPeek = this.d.peek();
            System.arraycopy(p70VarPeek.f8774a, p70VarPeek.a(i3), bArr, i2, iMin);
            j += (long) iMin;
            i2 += iMin;
        }
    }

    public void a(d dVar) {
        this.q = dVar;
    }

    public void a(long j) {
        if (this.l != j) {
            this.l = j;
            this.j = true;
        }
    }

    @Override // supwisdom.f50
    public void a(com.google.android.exoplayer2.j jVar) {
        com.google.android.exoplayer2.j jVarA = a(jVar, this.l);
        boolean zA = this.c.a(jVarA);
        this.k = jVar;
        this.j = false;
        d dVar = this.q;
        if (dVar == null || !zA) {
            return;
        }
        dVar.a(jVarA);
    }

    @Override // supwisdom.f50
    public int a(v40 v40Var, int i, boolean z) throws InterruptedException, IOException {
        if (!j()) {
            int iA = v40Var.a(i);
            if (iA != -1) {
                return iA;
            }
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
        try {
            int iA2 = v40Var.a(this.n.f8774a, this.n.a(this.o), c(i));
            if (iA2 == -1) {
                if (z) {
                    return -1;
                }
                throw new EOFException();
            }
            this.o += iA2;
            this.m += (long) iA2;
            return iA2;
        } finally {
            k();
        }
    }

    @Override // supwisdom.f50
    public void a(o80 o80Var, int i) {
        if (!j()) {
            o80Var.d(i);
            return;
        }
        while (i > 0) {
            int iC = c(i);
            p70 p70Var = this.n;
            o80Var.a(p70Var.f8774a, p70Var.a(this.o), iC);
            this.o += iC;
            this.m += (long) iC;
            i -= iC;
        }
        k();
    }

    @Override // supwisdom.f50
    public void a(long j, int i, int i2, int i3, byte[] bArr) {
        if (this.j) {
            a(this.k);
        }
        if (!j()) {
            this.c.a(j);
            return;
        }
        try {
            if (this.p) {
                if ((i & 1) != 0 && this.c.b(j)) {
                    this.p = false;
                }
                return;
            }
            this.c.a(j + this.l, i, (this.m - ((long) i2)) - ((long) i3), i2, bArr);
        } finally {
            k();
        }
    }

    public static com.google.android.exoplayer2.j a(com.google.android.exoplayer2.j jVar, long j) {
        if (jVar == null) {
            return null;
        }
        if (j == 0) {
            return jVar;
        }
        long j2 = jVar.w;
        return j2 != Long.MAX_VALUE ? jVar.a(j2 + j) : jVar;
    }
}
