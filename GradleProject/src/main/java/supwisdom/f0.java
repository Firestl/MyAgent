package supwisdom;

import com.taobao.weex.utils.FunctionParser;

/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7554a = new byte[128];
    public static final char[] b = new char[64];

    static {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < 128; i4++) {
            f7554a[i4] = -1;
        }
        for (int i5 = 90; i5 >= 65; i5--) {
            f7554a[i5] = (byte) (i5 - 65);
        }
        int i6 = 122;
        while (true) {
            i = 26;
            if (i6 < 97) {
                break;
            }
            f7554a[i6] = (byte) ((i6 - 97) + 26);
            i6--;
        }
        int i7 = 57;
        while (true) {
            i2 = 52;
            if (i7 < 48) {
                break;
            }
            f7554a[i7] = (byte) ((i7 - 48) + 52);
            i7--;
        }
        byte[] bArr = f7554a;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i8 = 0; i8 <= 25; i8++) {
            b[i8] = (char) (i8 + 65);
        }
        int i9 = 0;
        while (i <= 51) {
            b[i] = (char) (i9 + 97);
            i++;
            i9++;
        }
        while (i2 <= 61) {
            b[i2] = (char) (i3 + 48);
            i2++;
            i3++;
        }
        char[] cArr = b;
        cArr[62] = FunctionParser.Lexer.PLUS;
        cArr[63] = '/';
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr = new char[(i != 0 ? i2 + 1 : i2) * 4];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i2) {
            int i6 = i4 + 1;
            byte b2 = bArr[i4];
            int i7 = i6 + 1;
            byte b3 = bArr[i6];
            int i8 = i7 + 1;
            byte b4 = bArr[i7];
            byte b5 = (byte) (b3 & 15);
            byte b6 = (byte) (b2 & 3);
            int i9 = b2 & com.igexin.c.a.d.g.n;
            int i10 = b2 >> 2;
            if (i9 != 0) {
                i10 ^= 192;
            }
            byte b7 = (byte) i10;
            int i11 = b3 & com.igexin.c.a.d.g.n;
            int i12 = b3 >> 4;
            if (i11 != 0) {
                i12 ^= 240;
            }
            byte b8 = (byte) i12;
            int i13 = (b4 & com.igexin.c.a.d.g.n) == 0 ? b4 >> 6 : (b4 >> 6) ^ 252;
            int i14 = i5 + 1;
            char[] cArr2 = b;
            cArr[i5] = cArr2[b7];
            int i15 = i14 + 1;
            cArr[i14] = cArr2[(b6 << 4) | b8];
            int i16 = i15 + 1;
            cArr[i15] = cArr2[(b5 << 2) | ((byte) i13)];
            cArr[i16] = cArr2[b4 & 63];
            i3++;
            i5 = i16 + 1;
            i4 = i8;
        }
        if (i == 8) {
            byte b9 = bArr[i4];
            byte b10 = (byte) (b9 & 3);
            int i17 = b9 & com.igexin.c.a.d.g.n;
            int i18 = b9 >> 2;
            if (i17 != 0) {
                i18 ^= 192;
            }
            int i19 = i5 + 1;
            char[] cArr3 = b;
            cArr[i5] = cArr3[(byte) i18];
            int i20 = i19 + 1;
            cArr[i19] = cArr3[b10 << 4];
            cArr[i20] = '=';
            cArr[i20 + 1] = '=';
        } else if (i == 16) {
            byte b11 = bArr[i4];
            byte b12 = bArr[i4 + 1];
            byte b13 = (byte) (b12 & 15);
            byte b14 = (byte) (b11 & 3);
            int i21 = b11 & com.igexin.c.a.d.g.n;
            int i22 = b11 >> 2;
            if (i21 != 0) {
                i22 ^= 192;
            }
            byte b15 = (byte) i22;
            int i23 = b12 & com.igexin.c.a.d.g.n;
            int i24 = b12 >> 4;
            if (i23 != 0) {
                i24 ^= 240;
            }
            int i25 = i5 + 1;
            char[] cArr4 = b;
            cArr[i5] = cArr4[b15];
            int i26 = i25 + 1;
            cArr[i25] = cArr4[((byte) i24) | (b14 << 4)];
            cArr[i26] = cArr4[b13 << 2];
            cArr[i26 + 1] = '=';
        }
        return new String(cArr);
    }

    public static boolean a(char c) {
        return c < 128 && f7554a[c] != -1;
    }

    public static byte[] a(String str) {
        int i;
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        if (charArray == null) {
            i = 0;
        } else {
            int length = charArray.length;
            i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char c = charArray[i2];
                if (!(c == ' ' || c == '\r' || c == '\n' || c == '\t')) {
                    charArray[i] = charArray[i2];
                    i++;
                }
            }
        }
        if (i % 4 != 0) {
            return null;
        }
        int i3 = i / 4;
        if (i3 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i3 * 3];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i3 - 1) {
            int i7 = i5 + 1;
            char c2 = charArray[i5];
            if (a(c2)) {
                int i8 = i7 + 1;
                char c3 = charArray[i7];
                if (a(c3)) {
                    int i9 = i8 + 1;
                    char c4 = charArray[i8];
                    if (a(c4)) {
                        int i10 = i9 + 1;
                        char c5 = charArray[i9];
                        if (a(c5)) {
                            byte[] bArr2 = f7554a;
                            byte b2 = bArr2[c2];
                            byte b3 = bArr2[c3];
                            byte b4 = bArr2[c4];
                            byte b5 = bArr2[c5];
                            int i11 = i6 + 1;
                            bArr[i6] = (byte) ((b2 << 2) | (b3 >> 4));
                            int i12 = i11 + 1;
                            bArr[i11] = (byte) (((b3 & 15) << 4) | ((b4 >> 2) & 15));
                            i6 = i12 + 1;
                            bArr[i12] = (byte) ((b4 << 6) | b5);
                            i4++;
                            i5 = i10;
                        }
                    }
                }
            }
            return null;
        }
        int i13 = i5 + 1;
        char c6 = charArray[i5];
        if (!a(c6)) {
            return null;
        }
        int i14 = i13 + 1;
        char c7 = charArray[i13];
        if (!a(c7)) {
            return null;
        }
        byte[] bArr3 = f7554a;
        byte b6 = bArr3[c6];
        byte b7 = bArr3[c7];
        int i15 = i14 + 1;
        char c8 = charArray[i14];
        char c9 = charArray[i15];
        if (a(c8) && a(c9)) {
            byte[] bArr4 = f7554a;
            byte b8 = bArr4[c8];
            byte b9 = bArr4[c9];
            int i16 = i6 + 1;
            bArr[i6] = (byte) ((b6 << 2) | (b7 >> 4));
            bArr[i16] = (byte) (((b7 & 15) << 4) | ((b8 >> 2) & 15));
            bArr[i16 + 1] = (byte) (b9 | (b8 << 6));
            return bArr;
        }
        if (b(c8) && b(c9)) {
            if ((b7 & 15) != 0) {
                return null;
            }
            int i17 = i4 * 3;
            byte[] bArr5 = new byte[i17 + 1];
            System.arraycopy(bArr, 0, bArr5, 0, i17);
            bArr5[i6] = (byte) ((b6 << 2) | (b7 >> 4));
            return bArr5;
        }
        if (b(c8) || !b(c9)) {
            return null;
        }
        byte b10 = f7554a[c8];
        if ((b10 & 3) != 0) {
            return null;
        }
        int i18 = i4 * 3;
        byte[] bArr6 = new byte[i18 + 2];
        System.arraycopy(bArr, 0, bArr6, 0, i18);
        bArr6[i6] = (byte) ((b6 << 2) | (b7 >> 4));
        bArr6[i6 + 1] = (byte) (((b10 >> 2) & 15) | ((b7 & 15) << 4));
        return bArr6;
    }

    public static boolean b(char c) {
        return c == '=';
    }
}
