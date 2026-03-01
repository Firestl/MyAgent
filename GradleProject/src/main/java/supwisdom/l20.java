package supwisdom;

import java.io.IOException;
import java.util.Stack;

/* JADX INFO: compiled from: DefaultEbmlReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class l20 implements m20 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f8235a = new byte[8];
    public final Stack<b> b = new Stack<>();
    public final q20 c = new q20();
    public n20 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8236e;
    public int f;
    public long g;

    /* JADX INFO: compiled from: DefaultEbmlReader.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8237a;
        public final long b;

        public b(int i, long j) {
            this.f8237a = i;
            this.b = j;
        }
    }

    @Override // supwisdom.m20
    public void a(n20 n20Var) {
        this.d = n20Var;
    }

    public final long b(v40 v40Var) throws InterruptedException, IOException {
        v40Var.a();
        while (true) {
            v40Var.c(this.f8235a, 0, 4);
            int iA = q20.a(this.f8235a[0]);
            if (iA != -1 && iA <= 4) {
                int iA2 = (int) q20.a(this.f8235a, iA, false);
                if (this.d.b(iA2)) {
                    v40Var.b(iA);
                    return iA2;
                }
            }
            v40Var.b(1);
        }
    }

    public final String c(v40 v40Var, int i) throws InterruptedException, IOException {
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        v40Var.b(bArr, 0, i);
        return new String(bArr);
    }

    @Override // supwisdom.m20
    public void a() {
        this.f8236e = 0;
        this.b.clear();
        this.c.a();
    }

    @Override // supwisdom.m20
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        e80.b(this.d != null);
        while (true) {
            if (!this.b.isEmpty() && v40Var.c() >= this.b.peek().b) {
                this.d.c(this.b.pop().f8237a);
                return true;
            }
            if (this.f8236e == 0) {
                long jA = this.c.a(v40Var, true, false, 4);
                if (jA == -2) {
                    jA = b(v40Var);
                }
                if (jA == -1) {
                    return false;
                }
                this.f = (int) jA;
                this.f8236e = 1;
            }
            if (this.f8236e == 1) {
                this.g = this.c.a(v40Var, false, true, 8);
                this.f8236e = 2;
            }
            int iA = this.d.a(this.f);
            if (iA != 0) {
                if (iA == 1) {
                    long jC = v40Var.c();
                    this.b.add(new b(this.f, this.g + jC));
                    this.d.a(this.f, jC, this.g);
                    this.f8236e = 0;
                    return true;
                }
                if (iA == 2) {
                    long j = this.g;
                    if (j <= 8) {
                        this.d.a(this.f, a(v40Var, (int) j));
                        this.f8236e = 0;
                        return true;
                    }
                    throw new com.google.android.exoplayer2.n("Invalid integer size: " + this.g);
                }
                if (iA == 3) {
                    long j2 = this.g;
                    if (j2 <= 2147483647L) {
                        this.d.a(this.f, c(v40Var, (int) j2));
                        this.f8236e = 0;
                        return true;
                    }
                    throw new com.google.android.exoplayer2.n("String element size: " + this.g);
                }
                if (iA == 4) {
                    this.d.a(this.f, (int) this.g, v40Var);
                    this.f8236e = 0;
                    return true;
                }
                if (iA == 5) {
                    long j3 = this.g;
                    if (j3 != 4 && j3 != 8) {
                        throw new com.google.android.exoplayer2.n("Invalid float size: " + this.g);
                    }
                    this.d.a(this.f, b(v40Var, (int) this.g));
                    this.f8236e = 0;
                    return true;
                }
                throw new com.google.android.exoplayer2.n("Invalid element type " + iA);
            }
            v40Var.b((int) this.g);
            this.f8236e = 0;
        }
    }

    public final double b(v40 v40Var, int i) throws InterruptedException, IOException {
        long jA = a(v40Var, i);
        if (i == 4) {
            return Float.intBitsToFloat((int) jA);
        }
        return Double.longBitsToDouble(jA);
    }

    public final long a(v40 v40Var, int i) throws InterruptedException, IOException {
        v40Var.b(this.f8235a, 0, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.f8235a[i2] & 255));
        }
        return j;
    }
}
