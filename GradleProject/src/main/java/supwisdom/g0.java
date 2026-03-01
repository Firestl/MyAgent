package supwisdom;

import com.taobao.weex.wson.Wson;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;

/* JADX INFO: loaded from: classes.dex */
public class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7673a = {Wson.STRING_TYPE, com.igexin.c.a.d.g.n, 22, KeyFactorySpi.x448_type, 73, 20, -78, -71, 23, 36, 66, -41, -38, -118, 6, 0, -87, KeyFactorySpi.x448_type, 48, PSSSigner.TRAILER_IMPLICIT, 22, 49, 56, -86, -29, -115, -18, 77, -80, -5, com.umeng.analytics.pro.db.l, 78};
    public static int[] b = new int[64];

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            b[i2] = 2043430169;
            i2++;
        }
        for (i = 16; i < 64; i++) {
            b[i] = 2055708042;
        }
    }

    public static int a(int i, int i2) {
        int i3 = i2 % 32;
        byte[] bArrA = a(k0.a(i));
        int i4 = i3 / 8;
        int i5 = i3 % 8;
        if (i4 > 0) {
            byte[] bArr = new byte[bArrA.length];
            System.arraycopy(bArrA, i4, bArr, 0, bArrA.length - i4);
            System.arraycopy(bArrA, 0, bArr, bArrA.length - i4, i4);
            bArrA = bArr;
        }
        if (i5 > 0) {
            int length = bArrA.length;
            byte[] bArr2 = new byte[length];
            for (int i6 = 0; i6 < length; i6++) {
                bArr2[i6] = (byte) (((byte) ((bArrA[i6] & 255) << i5)) | ((byte) ((bArrA[r4 % length] & 255) >> (8 - i5))));
            }
            bArrA = bArr2;
        }
        return k0.a(a(bArrA));
    }

    public static byte[] a(int i) {
        return a(k0.a(i));
    }

    public static byte[] a(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[(length - i) - 1];
        }
        return bArr2;
    }

    public static byte[] a(byte[] bArr, int i) {
        int length = 448 - (((bArr.length * 8) + 1) % 512);
        if (length < 0) {
            length = 960 - (((bArr.length * 8) + 1) % 512);
        }
        int i2 = (length + 1) / 8;
        byte[] bArr2 = new byte[i2];
        bArr2[0] = com.igexin.c.a.d.g.n;
        long length2 = (i * 512) + (bArr.length * 8);
        byte[] bArr3 = new byte[bArr.length + i2 + 8];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        int length3 = bArr.length + 0;
        System.arraycopy(bArr2, 0, bArr3, length3, i2);
        int i3 = length3 + i2;
        byte[] bArrA = a(k0.a(length2));
        System.arraycopy(bArrA, 0, bArr3, i3, bArrA.length);
        return bArr3;
    }

    public static int[] b(byte[] bArr) {
        int[] iArr = new int[bArr.length / 4];
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < bArr.length; i += 4) {
            System.arraycopy(bArr, i, bArr2, 0, 4);
            iArr[i / 4] = k0.a(a(bArr2));
        }
        return iArr;
    }
}
