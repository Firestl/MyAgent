package supwisdom;

/* JADX INFO: compiled from: TrackSampleTable.java */
/* JADX INFO: loaded from: classes.dex */
public final class k30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8117a;
    public final long[] b;
    public final int[] c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long[] f8118e;
    public final int[] f;

    public k30(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
        e80.a(iArr.length == jArr2.length);
        e80.a(jArr.length == jArr2.length);
        e80.a(iArr2.length == jArr2.length);
        this.b = jArr;
        this.c = iArr;
        this.d = i;
        this.f8118e = jArr2;
        this.f = iArr2;
        this.f8117a = jArr.length;
    }

    public int a(long j) {
        for (int iA = x80.a(this.f8118e, j, true, false); iA >= 0; iA--) {
            if ((this.f[iA] & 1) != 0) {
                return iA;
            }
        }
        return -1;
    }

    public int b(long j) {
        for (int iB = x80.b(this.f8118e, j, true, false); iB < this.f8118e.length; iB++) {
            if ((this.f[iB] & 1) != 0) {
                return iB;
            }
        }
        return -1;
    }
}
