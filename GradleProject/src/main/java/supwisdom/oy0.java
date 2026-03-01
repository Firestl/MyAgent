package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class oy0 {
    public static void a(ry0 ry0Var, int i, long j) {
        int iNumberOfTrailingZeros = 64 - Long.numberOfTrailingZeros(j);
        if (iNumberOfTrailingZeros == 0) {
            iNumberOfTrailingZeros = 1;
        }
        int i2 = (iNumberOfTrailingZeros + 7) >> 3;
        long j2 = j >> (64 - (i2 * 8));
        ry0Var.writeByte(i | ((i2 - 1) << 5));
        while (i2 > 0) {
            ry0Var.writeByte((byte) j2);
            j2 >>= 8;
            i2--;
        }
    }

    public static void b(ry0 ry0Var, int i, long j) {
        int iNumberOfLeadingZeros = ((65 - Long.numberOfLeadingZeros((j >> 63) ^ j)) + 7) >> 3;
        ry0Var.writeByte(i | ((iNumberOfLeadingZeros - 1) << 5));
        while (iNumberOfLeadingZeros > 0) {
            ry0Var.writeByte((byte) j);
            j >>= 8;
            iNumberOfLeadingZeros--;
        }
    }

    public static void c(ry0 ry0Var, int i, long j) {
        int iNumberOfLeadingZeros = 64 - Long.numberOfLeadingZeros(j);
        if (iNumberOfLeadingZeros == 0) {
            iNumberOfLeadingZeros = 1;
        }
        int i2 = (iNumberOfLeadingZeros + 7) >> 3;
        ry0Var.writeByte(i | ((i2 - 1) << 5));
        while (i2 > 0) {
            ry0Var.writeByte((byte) j);
            j >>= 8;
            i2--;
        }
    }
}
