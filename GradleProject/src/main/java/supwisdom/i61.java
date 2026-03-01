package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class i61 {
    public static void a(int[] iArr, int i) {
        int i2 = i >> 5;
        iArr[i2] = (~(1 << (i & 31))) & iArr[i2];
    }

    public static int b(int[] iArr, int i) {
        int iA;
        int length = iArr.length;
        int i2 = i & 31;
        for (int i3 = i >> 5; i3 < length; i3++) {
            int i4 = iArr[i3];
            if (i4 != 0 && (iA = a(i4, i2)) >= 0) {
                return (i3 << 5) + iA;
            }
            i2 = 0;
        }
        return -1;
    }

    public static boolean c(int[] iArr, int i) {
        return (iArr[i >> 5] & (1 << (i & 31))) != 0;
    }

    public static void d(int[] iArr, int i) {
        int i2 = i >> 5;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public static int[] a(int i) {
        return new int[(i + 31) >> 5];
    }

    public static int a(int i, int i2) {
        int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i & (~((1 << i2) - 1)));
        if (iNumberOfTrailingZeros == 32) {
            return -1;
        }
        return iNumberOfTrailingZeros;
    }
}
