package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class py0 {
    public static int a(int i) {
        int i2 = i >> 7;
        int i3 = 0;
        while (i2 != 0) {
            i2 >>= 7;
            i3++;
        }
        return i3 + 1;
    }

    public static void a(ry0 ry0Var, int i) {
        int i2 = i >> 7;
        int i3 = (Integer.MIN_VALUE & i) == 0 ? 0 : -1;
        boolean z = true;
        while (true) {
            int i4 = i2;
            int i5 = i;
            i = i4;
            if (!z) {
                return;
            }
            z = (i == i3 && (i & 1) == ((i5 >> 6) & 1)) ? false : true;
            ry0Var.writeByte((byte) ((i5 & 127) | (z ? 128 : 0)));
            i2 = i >> 7;
        }
    }

    public static void b(ry0 ry0Var, int i) {
        while (true) {
            int i2 = i;
            i >>>= 7;
            if (i == 0) {
                ry0Var.writeByte((byte) (i2 & 127));
                return;
            }
            ry0Var.writeByte((byte) ((i2 & 127) | 128));
        }
    }
}
