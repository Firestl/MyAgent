package supwisdom;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class qj0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final oj0 f8928a;

    static {
        if (nj0.b() && nj0.c()) {
            int i = qi0.f8927a;
        }
        f8928a = new pj0();
    }

    public static boolean a(byte[] bArr, int i, int i2) {
        return f8928a.a(bArr, 0, i2);
    }

    public static /* synthetic */ int b(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 != 0) {
            if (i3 == 1) {
                byte b2 = bArr[i];
                if (b <= -12 && b2 <= -65) {
                    return b ^ (b2 << 8);
                }
            } else {
                if (i3 != 2) {
                    throw new AssertionError();
                }
                byte b3 = bArr[i];
                byte b4 = bArr[i + 1];
                if (b <= -12 && b3 <= -65 && b4 <= -65) {
                    return ((b3 << 8) ^ b) ^ (b4 << 16);
                }
            }
        } else if (b <= -12) {
            return b;
        }
        return -1;
    }
}
