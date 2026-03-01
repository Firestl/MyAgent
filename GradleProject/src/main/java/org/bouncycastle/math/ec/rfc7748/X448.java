package org.bouncycastle.math.ec.rfc7748;

import com.igexin.c.a.d.g;
import java.security.SecureRandom;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public abstract class X448 {
    public static final int C_A = 156326;
    public static final int C_A24 = 39082;
    public static final int POINT_SIZE = 56;
    public static final int SCALAR_SIZE = 56;

    public static class Friend {
        public static final Friend INSTANCE = new Friend();
    }

    public static boolean calculateAgreement(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        scalarMult(bArr, i, bArr2, i2, bArr3, i3);
        return !Arrays.areAllZeroes(bArr3, i3, 56);
    }

    public static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & 255) << 16);
    }

    public static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < 14; i2++) {
            iArr[i2] = decode32(bArr, (i2 * 4) + i);
        }
        iArr[0] = iArr[0] & (-4);
        iArr[13] = iArr[13] | Integer.MIN_VALUE;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
        bArr[0] = (byte) (bArr[0] & Poly1305KeyGenerator.R_MASK_LOW_2);
        bArr[55] = (byte) (bArr[55] | g.n);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        scalarMultBase(bArr, i, bArr2, i2);
    }

    public static void pointDouble(int[] iArr, int[] iArr2) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.add(iArr, iArr2, iArrCreate);
        X448Field.sub(iArr, iArr2, iArrCreate2);
        X448Field.sqr(iArrCreate, iArrCreate);
        X448Field.sqr(iArrCreate2, iArrCreate2);
        X448Field.mul(iArrCreate, iArrCreate2, iArr);
        X448Field.sub(iArrCreate, iArrCreate2, iArrCreate);
        X448Field.mul(iArrCreate, C_A24, iArr2);
        X448Field.add(iArr2, iArrCreate2, iArr2);
        X448Field.mul(iArr2, iArrCreate, iArr2);
    }

    public static void precompute() {
        Ed448.precompute();
    }

    public static void scalarMult(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int[] iArr = new int[14];
        decodeScalar(bArr, i, iArr);
        int[] iArrCreate = X448Field.create();
        X448Field.decode(bArr2, i2, iArrCreate);
        int[] iArrCreate2 = X448Field.create();
        X448Field.copy(iArrCreate, 0, iArrCreate2, 0);
        int[] iArrCreate3 = X448Field.create();
        iArrCreate3[0] = 1;
        int[] iArrCreate4 = X448Field.create();
        iArrCreate4[0] = 1;
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        int i4 = 447;
        int i5 = 1;
        while (true) {
            X448Field.add(iArrCreate4, iArrCreate5, iArrCreate6);
            X448Field.sub(iArrCreate4, iArrCreate5, iArrCreate4);
            X448Field.add(iArrCreate2, iArrCreate3, iArrCreate5);
            X448Field.sub(iArrCreate2, iArrCreate3, iArrCreate2);
            X448Field.mul(iArrCreate6, iArrCreate2, iArrCreate6);
            X448Field.mul(iArrCreate4, iArrCreate5, iArrCreate4);
            X448Field.sqr(iArrCreate5, iArrCreate5);
            X448Field.sqr(iArrCreate2, iArrCreate2);
            X448Field.sub(iArrCreate5, iArrCreate2, iArrCreate7);
            X448Field.mul(iArrCreate7, C_A24, iArrCreate3);
            X448Field.add(iArrCreate3, iArrCreate2, iArrCreate3);
            X448Field.mul(iArrCreate3, iArrCreate7, iArrCreate3);
            X448Field.mul(iArrCreate2, iArrCreate5, iArrCreate2);
            X448Field.sub(iArrCreate6, iArrCreate4, iArrCreate5);
            X448Field.add(iArrCreate6, iArrCreate4, iArrCreate4);
            X448Field.sqr(iArrCreate4, iArrCreate4);
            X448Field.sqr(iArrCreate5, iArrCreate5);
            X448Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
            i4--;
            int i6 = (iArr[i4 >>> 5] >>> (i4 & 31)) & 1;
            int i7 = i5 ^ i6;
            X448Field.cswap(i7, iArrCreate2, iArrCreate4);
            X448Field.cswap(i7, iArrCreate3, iArrCreate5);
            if (i4 < 2) {
                break;
            } else {
                i5 = i6;
            }
        }
        for (int i8 = 0; i8 < 2; i8++) {
            pointDouble(iArrCreate2, iArrCreate3);
        }
        X448Field.inv(iArrCreate3, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.normalize(iArrCreate2);
        X448Field.encode(iArrCreate2, bArr3, i3);
    }

    public static void scalarMultBase(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        Ed448.scalarMultBaseXY(Friend.INSTANCE, bArr, i, iArrCreate, iArrCreate2);
        X448Field.inv(iArrCreate, iArrCreate);
        X448Field.mul(iArrCreate, iArrCreate2, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate);
        X448Field.normalize(iArrCreate);
        X448Field.encode(iArrCreate, bArr2, i2);
    }
}
