package supwisdom;

import supwisdom.t20;

/* JADX INFO: compiled from: XingSeeker.java */
/* JADX INFO: loaded from: classes.dex */
public final class v20 implements t20.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f9471a;
    public final long b;
    public final long c;
    public final long[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f9472e;
    public final int f;

    public v20(long j, long j2, long j3) {
        this(j, j2, j3, null, 0L, 0);
    }

    public static v20 a(c50 c50Var, o80 o80Var, long j, long j2) {
        int iT;
        int i = c50Var.g;
        int i2 = c50Var.d;
        long j3 = j + ((long) c50Var.c);
        int iN = o80Var.n();
        if ((iN & 1) != 1 || (iT = o80Var.t()) == 0) {
            return null;
        }
        long jA = x80.a(iT, ((long) i) * 1000000, i2);
        if ((iN & 6) != 6) {
            return new v20(j3, jA, j2);
        }
        long jT = o80Var.t();
        o80Var.d(1);
        long[] jArr = new long[99];
        for (int i3 = 0; i3 < 99; i3++) {
            jArr[i3] = o80Var.g();
        }
        return new v20(j3, jA, j2, jArr, jT, c50Var.c);
    }

    @Override // supwisdom.e50
    public long b(long j) {
        if (!a()) {
            return this.f9471a;
        }
        float f = (j * 100.0f) / this.b;
        if (f <= 0.0f) {
            f = 0.0f;
        } else if (f < 100.0f) {
            int i = (int) f;
            float f2 = i != 0 ? this.d[i - 1] : 0.0f;
            f = (((i < 99 ? this.d[i] : 256.0f) - f2) * (f - i)) + f2;
        }
        long jRound = Math.round(((double) f) * 0.00390625d * this.f9472e);
        long j2 = this.f9471a;
        long j3 = jRound + j2;
        long j4 = this.c;
        return Math.min(j3, j4 != -1 ? j4 - 1 : ((j2 - ((long) this.f)) + this.f9472e) - 1);
    }

    public v20(long j, long j2, long j3, long[] jArr, long j4, int i) {
        this.f9471a = j;
        this.b = j2;
        this.c = j3;
        this.d = jArr;
        this.f9472e = j4;
        this.f = i;
    }

    @Override // supwisdom.e50
    public long b() {
        return this.b;
    }

    @Override // supwisdom.e50
    public boolean a() {
        return this.d != null;
    }

    @Override // supwisdom.t20.b
    public long a(long j) {
        if (a()) {
            if (j >= this.f9471a) {
                double d = ((j - r3) * 256.0d) / this.f9472e;
                int iA = x80.a(this.d, (long) d, true, false) + 1;
                long jA = a(iA);
                long j2 = iA == 0 ? 0L : this.d[iA - 1];
                return jA + ((iA == 99 ? 256L : this.d[iA]) != j2 ? (long) (((a(iA + 1) - jA) * (d - j2)) / (r9 - j2)) : 0L);
            }
        }
        return 0L;
    }

    public final long a(int i) {
        return (this.b * ((long) i)) / 100;
    }
}
