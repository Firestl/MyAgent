package supwisdom;

import io.dcloud.common.util.Base64;

/* JADX INFO: loaded from: classes3.dex */
public class gq1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7765a = Base64.CRLF.getBytes();
    public static byte[] b = new byte[255];
    public static byte[] c = new byte[64];

    static {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < 255; i4++) {
            b[i4] = -1;
        }
        for (int i5 = 90; i5 >= 65; i5--) {
            b[i5] = (byte) (i5 - 65);
        }
        int i6 = 122;
        while (true) {
            i = 26;
            if (i6 < 97) {
                break;
            }
            b[i6] = (byte) ((i6 - 97) + 26);
            i6--;
        }
        int i7 = 57;
        while (true) {
            i2 = 52;
            if (i7 < 48) {
                break;
            }
            b[i7] = (byte) ((i7 - 48) + 52);
            i7--;
        }
        byte[] bArr = b;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i8 = 0; i8 <= 25; i8++) {
            c[i8] = (byte) (i8 + 65);
        }
        int i9 = 0;
        while (i <= 51) {
            c[i] = (byte) (i9 + 97);
            i++;
            i9++;
        }
        while (i2 <= 61) {
            c[i2] = (byte) (i3 + 48);
            i2++;
            i3++;
        }
        byte[] bArr2 = c;
        bArr2[62] = 43;
        bArr2[63] = 47;
    }
}
