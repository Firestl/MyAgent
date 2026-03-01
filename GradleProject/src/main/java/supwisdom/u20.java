package supwisdom;

import supwisdom.t20;

/* JADX INFO: compiled from: VbriSeeker.java */
/* JADX INFO: loaded from: classes.dex */
public final class u20 implements t20.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long[] f9357a;
    public final long[] b;
    public final long c;

    public u20(long[] jArr, long[] jArr2, long j) {
        this.f9357a = jArr;
        this.b = jArr2;
        this.c = j;
    }

    public static u20 a(c50 c50Var, o80 o80Var, long j, long j2) {
        int iG;
        o80Var.d(10);
        int iN = o80Var.n();
        if (iN <= 0) {
            return null;
        }
        int i = c50Var.d;
        long jA = x80.a(iN, 1000000 * ((long) (i >= 32000 ? 1152 : 576)), i);
        int iH = o80Var.h();
        int iH2 = o80Var.h();
        int iH3 = o80Var.h();
        int i2 = 2;
        o80Var.d(2);
        long j3 = j + ((long) c50Var.c);
        int i3 = iH + 1;
        long[] jArr = new long[i3];
        long[] jArr2 = new long[i3];
        jArr[0] = 0;
        jArr2[0] = j3;
        int i4 = 1;
        while (i4 < i3) {
            if (iH3 == 1) {
                iG = o80Var.g();
            } else if (iH3 == i2) {
                iG = o80Var.h();
            } else if (iH3 == 3) {
                iG = o80Var.k();
            } else {
                if (iH3 != 4) {
                    return null;
                }
                iG = o80Var.t();
            }
            int i5 = i3;
            j3 += (long) (iG * iH2);
            int i6 = iH2;
            int i7 = iH3;
            jArr[i4] = (((long) i4) * jA) / ((long) iH);
            jArr2[i4] = j2 == -1 ? j3 : Math.min(j2, j3);
            i4++;
            i3 = i5;
            iH2 = i6;
            iH3 = i7;
            i2 = 2;
        }
        return new u20(jArr, jArr2, jA);
    }

    @Override // supwisdom.e50
    public boolean a() {
        return true;
    }

    @Override // supwisdom.e50
    public long b(long j) {
        return this.b[x80.a(this.f9357a, j, true, true)];
    }

    @Override // supwisdom.e50
    public long b() {
        return this.c;
    }

    @Override // supwisdom.t20.b
    public long a(long j) {
        return this.f9357a[x80.a(this.b, j, true, true)];
    }
}
