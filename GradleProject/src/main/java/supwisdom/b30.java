package supwisdom;

/* JADX INFO: compiled from: FixedSampleSizeRechunker.java */
/* JADX INFO: loaded from: classes.dex */
public final class b30 {

    /* JADX INFO: compiled from: FixedSampleSizeRechunker.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long[] f7013a;
        public final int[] b;
        public final int c;
        public final long[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int[] f7014e;

        public b(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
            this.f7013a = jArr;
            this.b = iArr;
            this.c = i;
            this.d = jArr2;
            this.f7014e = iArr2;
        }
    }

    public static b a(int i, long[] jArr, int[] iArr, long j) {
        int i2 = 8192 / i;
        int iA = 0;
        for (int i3 : iArr) {
            iA += x80.a(i3, i2);
        }
        long[] jArr2 = new long[iA];
        int[] iArr2 = new int[iA];
        long[] jArr3 = new long[iA];
        int[] iArr3 = new int[iA];
        int i4 = 0;
        int i5 = 0;
        int iMax = 0;
        for (int i6 = 0; i6 < iArr.length; i6++) {
            int i7 = iArr[i6];
            long j2 = jArr[i6];
            while (i7 > 0) {
                int iMin = Math.min(i2, i7);
                jArr2[i5] = j2;
                iArr2[i5] = i * iMin;
                iMax = Math.max(iMax, iArr2[i5]);
                jArr3[i5] = ((long) i4) * j;
                iArr3[i5] = 1;
                j2 += (long) iArr2[i5];
                i4 += iMin;
                i7 -= iMin;
                i5++;
            }
        }
        return new b(jArr2, iArr2, iMax, jArr3, iArr3);
    }
}
