package supwisdom;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: Bytes.java */
/* JADX INFO: loaded from: classes.dex */
public final class zr0 {
    public static final boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    public static final byte[] b(byte[] bArr, byte[] bArr2) {
        if (bArr.length == bArr2.length) {
            return a(bArr, 0, bArr2, 0, bArr.length);
        }
        throw new IllegalArgumentException("The lengths of x and y should match.");
    }

    public static byte[] a(byte[]... bArr) throws GeneralSecurityException {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            if (length <= Integer.MAX_VALUE - bArr2.length) {
                length += bArr2.length;
            } else {
                throw new GeneralSecurityException("exceeded size limit");
            }
        }
        byte[] bArr3 = new byte[length];
        int length2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
            length2 += bArr4.length;
        }
        return bArr3;
    }

    public static final byte[] a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (i3 >= 0 && bArr.length - i3 >= i && bArr2.length - i3 >= i2) {
            byte[] bArr3 = new byte[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                bArr3[i4] = (byte) (bArr[i4 + i] ^ bArr2[i4 + i2]);
            }
            return bArr3;
        }
        throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
    }
}
