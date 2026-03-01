package org.bouncycastle.math.ec.rfc8032;

import com.igexin.c.a.d.g;
import com.umeng.analytics.pro.co;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Ed448 {
    public static final int C_d = -39081;
    public static final int L4_0 = 43969588;
    public static final int L4_1 = 30366549;
    public static final int L4_2 = 163752818;
    public static final int L4_3 = 258169998;
    public static final int L4_4 = 96434764;
    public static final int L4_5 = 227822194;
    public static final int L4_6 = 149865618;
    public static final int L4_7 = 550336261;
    public static final int L_0 = 78101261;
    public static final int L_1 = 141809365;
    public static final int L_2 = 175155932;
    public static final int L_3 = 64542499;
    public static final int L_4 = 158326419;
    public static final int L_5 = 191173276;
    public static final int L_6 = 104575268;
    public static final int L_7 = 137584065;
    public static final long M26L = 67108863;
    public static final long M28L = 268435455;
    public static final long M32L = 4294967295L;
    public static final int POINT_BYTES = 57;
    public static final int PRECOMP_BLOCKS = 5;
    public static final int PRECOMP_MASK = 15;
    public static final int PRECOMP_POINTS = 16;
    public static final int PRECOMP_SPACING = 18;
    public static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    public static final int SCALAR_BYTES = 57;
    public static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    public static final int WNAF_WIDTH_BASE = 7;
    public static final byte[] DOM4_PREFIX = Strings.toByteArray("SigEd448");
    public static final int[] P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    public static final int[] L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, 1073741823};
    public static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    public static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    public static final Object precompLock = new Object();
    public static PointExt[] precompBaseTable = null;
    public static int[] precompBase = null;

    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    public static class PointExt {
        public int[] x;
        public int[] y;
        public int[] z;

        public PointExt() {
            this.x = X448Field.create();
            this.y = X448Field.create();
            this.z = X448Field.create();
        }
    }

    public static class PointPrecomp {
        public int[] x;
        public int[] y;

        public PointPrecomp() {
            this.x = X448Field.create();
            this.y = X448Field.create();
        }
    }

    public static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        for (int i = 0; i < 28; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    public static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    public static int checkPoint(int[] iArr, int[] iArr2) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        X448Field.sqr(iArr, iArrCreate2);
        X448Field.sqr(iArr2, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate);
        X448Field.add(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.mul(iArrCreate, 39081, iArrCreate);
        X448Field.subOne(iArrCreate);
        X448Field.add(iArrCreate, iArrCreate2, iArrCreate);
        X448Field.normalize(iArrCreate);
        return X448Field.isZero(iArrCreate);
    }

    public static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        X448Field.sqr(iArr, iArrCreate2);
        X448Field.sqr(iArr2, iArrCreate3);
        X448Field.sqr(iArr3, iArrCreate4);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate);
        X448Field.add(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        X448Field.sqr(iArrCreate4, iArrCreate4);
        X448Field.mul(iArrCreate, 39081, iArrCreate);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate);
        X448Field.add(iArrCreate, iArrCreate2, iArrCreate);
        X448Field.normalize(iArrCreate);
        return X448Field.isZero(iArrCreate);
    }

    public static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & 127) != 0) {
            return false;
        }
        decode32(bArr, 0, new int[14], 0, 14);
        return !Nat.gte(14, r2, P);
    }

    public static boolean checkScalarVar(byte[] bArr) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, new int[14]);
        return !Nat.gte(14, r2, L);
    }

    public static Xof createPrehash() {
        return createXof();
    }

    public static Xof createXof() {
        return new SHAKEDigest(256);
    }

    public static int decode16(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    public static int decode24(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        return ((bArr[i3 + 1] & 255) << 16) | i2 | ((bArr[i3] & 255) << 8);
    }

    public static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & 255) << 16);
    }

    public static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    public static boolean decodePointVar(byte[] bArr, int i, boolean z, PointExt pointExt) {
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i + 57);
        if (!checkPointVar(bArrCopyOfRange)) {
            return false;
        }
        int i2 = (bArrCopyOfRange[56] & g.n) >>> 7;
        bArrCopyOfRange[56] = (byte) (bArrCopyOfRange[56] & 127);
        X448Field.decode(bArrCopyOfRange, 0, pointExt.y);
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.sqr(pointExt.y, iArrCreate);
        X448Field.mul(iArrCreate, 39081, iArrCreate2);
        X448Field.negate(iArrCreate, iArrCreate);
        X448Field.addOne(iArrCreate);
        X448Field.addOne(iArrCreate2);
        if (!X448Field.sqrtRatioVar(iArrCreate, iArrCreate2, pointExt.x)) {
            return false;
        }
        X448Field.normalize(pointExt.x);
        if (i2 == 1 && X448Field.isZeroVar(pointExt.x)) {
            return false;
        }
        if (z ^ (i2 != (pointExt.x[0] & 1))) {
            int[] iArr = pointExt.x;
            X448Field.negate(iArr, iArr);
        }
        pointExtendXY(pointExt);
        return true;
    }

    public static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 14);
    }

    public static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        xof.update(bArr2, 0, bArr2.length);
        xof.update(b);
        xof.update((byte) bArr.length);
        xof.update(bArr, 0, bArr.length);
    }

    public static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    public static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    public static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    public static int encodePoint(PointExt pointExt, byte[] bArr, int i) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.inv(pointExt.z, iArrCreate2);
        X448Field.mul(pointExt.x, iArrCreate2, iArrCreate);
        X448Field.mul(pointExt.y, iArrCreate2, iArrCreate2);
        X448Field.normalize(iArrCreate);
        X448Field.normalize(iArrCreate2);
        int iCheckPoint = checkPoint(iArrCreate, iArrCreate2);
        X448Field.encode(iArrCreate2, bArr, i);
        bArr[(i + 57) - 1] = (byte) ((iArrCreate[0] & 1) << 7);
        return iCheckPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Xof xofCreateXof = createXof();
        byte[] bArr3 = new byte[114];
        xofCreateXof.update(bArr, i, 57);
        xofCreateXof.doFinal(bArr3, 0, 114);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    public static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    public static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[28];
        int i2 = 0;
        int i3 = 14;
        int i4 = 28;
        int i5 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i6 = iArr[i3];
            int i7 = i4 - 1;
            iArr2[i7] = (i5 << 16) | (i6 >>> 16);
            i4 = i7 - 1;
            iArr2[i4] = i6;
            i5 = i6;
        }
        byte[] bArr = new byte[447];
        int i8 = 1 << i;
        int i9 = i8 - 1;
        int i10 = i8 >>> 1;
        int i11 = 0;
        int i12 = 0;
        while (i2 < 28) {
            int i13 = iArr2[i2];
            while (i11 < 16) {
                int i14 = i13 >>> i11;
                if ((i14 & 1) == i12) {
                    i11++;
                } else {
                    int i15 = (i14 & i9) + i12;
                    int i16 = i15 & i10;
                    int i17 = i15 - (i16 << 1);
                    i12 = i16 >>> (i - 1);
                    bArr[(i2 << 4) + i11] = (byte) i17;
                    i11 += i;
                }
            }
            i2++;
            i11 -= 16;
        }
        return bArr;
    }

    public static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] bArrReduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArrReduceScalar, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] bArrCalculateS = calculateS(bArrReduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(bArrCalculateS, 0, bArr6, i4 + 57, 57);
    }

    public static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof xofCreateXof = createXof();
        byte[] bArr5 = new byte[114];
        xofCreateXof.update(bArr, i, 57);
        xofCreateXof.doFinal(bArr5, 0, 114);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(xofCreateXof, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    public static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof xofCreateXof = createXof();
        byte[] bArr6 = new byte[114];
        xofCreateXof.update(bArr, i, 57);
        xofCreateXof.doFinal(bArr6, 0, 114);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSign(xofCreateXof, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    public static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        int i5 = i + 57;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i5);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, i5, i + 114);
        if (!checkPointVar(bArrCopyOfRange) || !checkScalarVar(bArrCopyOfRange2)) {
            return false;
        }
        PointExt pointExt = new PointExt();
        if (!decodePointVar(bArr2, i2, true, pointExt)) {
            return false;
        }
        Xof xofCreateXof = createXof();
        byte[] bArr5 = new byte[114];
        dom4(xofCreateXof, b, bArr3);
        xofCreateXof.update(bArrCopyOfRange, 0, 57);
        xofCreateXof.update(bArr2, i2, 57);
        xofCreateXof.update(bArr4, i3, i4);
        xofCreateXof.doFinal(bArr5, 0, 114);
        byte[] bArrReduceScalar = reduceScalar(bArr5);
        int[] iArr = new int[14];
        decodeScalar(bArrCopyOfRange2, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArrReduceScalar, 0, iArr2);
        PointExt pointExt2 = new PointExt();
        scalarMultStrausVar(iArr, iArr2, pointExt, pointExt2);
        byte[] bArr6 = new byte[57];
        return encodePoint(pointExt2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, bArrCopyOfRange);
    }

    public static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        int[] iArrCreate8 = X448Field.create();
        X448Field.mul(pointExt.z, pointExt2.z, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate2);
        X448Field.mul(pointExt.x, pointExt2.x, iArrCreate3);
        X448Field.mul(pointExt.y, pointExt2.y, iArrCreate4);
        X448Field.mul(iArrCreate3, iArrCreate4, iArrCreate5);
        X448Field.mul(iArrCreate5, 39081, iArrCreate5);
        X448Field.add(iArrCreate2, iArrCreate5, iArrCreate6);
        X448Field.sub(iArrCreate2, iArrCreate5, iArrCreate7);
        X448Field.add(pointExt.x, pointExt.y, iArrCreate2);
        X448Field.add(pointExt2.x, pointExt2.y, iArrCreate5);
        X448Field.mul(iArrCreate2, iArrCreate5, iArrCreate8);
        X448Field.add(iArrCreate4, iArrCreate3, iArrCreate2);
        X448Field.sub(iArrCreate4, iArrCreate3, iArrCreate5);
        X448Field.carry(iArrCreate2);
        X448Field.sub(iArrCreate8, iArrCreate2, iArrCreate8);
        X448Field.mul(iArrCreate8, iArrCreate, iArrCreate8);
        X448Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
        X448Field.mul(iArrCreate6, iArrCreate8, pointExt2.x);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt2.y);
        X448Field.mul(iArrCreate6, iArrCreate7, pointExt2.z);
    }

    public static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        X448Field.sqr(pointExt.z, iArrCreate);
        X448Field.mul(pointPrecomp.x, pointExt.x, iArrCreate2);
        X448Field.mul(pointPrecomp.y, pointExt.y, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate4);
        X448Field.mul(iArrCreate4, 39081, iArrCreate4);
        X448Field.add(iArrCreate, iArrCreate4, iArrCreate5);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate6);
        X448Field.add(pointPrecomp.x, pointPrecomp.y, iArrCreate);
        X448Field.add(pointExt.x, pointExt.y, iArrCreate4);
        X448Field.mul(iArrCreate, iArrCreate4, iArrCreate7);
        X448Field.add(iArrCreate3, iArrCreate2, iArrCreate);
        X448Field.sub(iArrCreate3, iArrCreate2, iArrCreate4);
        X448Field.carry(iArrCreate);
        X448Field.sub(iArrCreate7, iArrCreate, iArrCreate7);
        X448Field.mul(iArrCreate7, pointExt.z, iArrCreate7);
        X448Field.mul(iArrCreate4, pointExt.z, iArrCreate4);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt.x);
        X448Field.mul(iArrCreate4, iArrCreate6, pointExt.y);
        X448Field.mul(iArrCreate5, iArrCreate6, pointExt.z);
    }

    public static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        int[] iArrCreate8 = X448Field.create();
        if (z) {
            X448Field.sub(pointExt.y, pointExt.x, iArrCreate8);
            iArr2 = iArrCreate2;
            iArr = iArrCreate5;
            iArr4 = iArrCreate6;
            iArr3 = iArrCreate7;
        } else {
            X448Field.add(pointExt.y, pointExt.x, iArrCreate8);
            iArr = iArrCreate2;
            iArr2 = iArrCreate5;
            iArr3 = iArrCreate6;
            iArr4 = iArrCreate7;
        }
        X448Field.mul(pointExt.z, pointExt2.z, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate2);
        X448Field.mul(pointExt.x, pointExt2.x, iArrCreate3);
        X448Field.mul(pointExt.y, pointExt2.y, iArrCreate4);
        X448Field.mul(iArrCreate3, iArrCreate4, iArrCreate5);
        X448Field.mul(iArrCreate5, 39081, iArrCreate5);
        X448Field.add(iArrCreate2, iArrCreate5, iArr3);
        X448Field.sub(iArrCreate2, iArrCreate5, iArr4);
        X448Field.add(pointExt2.x, pointExt2.y, iArrCreate5);
        X448Field.mul(iArrCreate8, iArrCreate5, iArrCreate8);
        X448Field.add(iArrCreate4, iArrCreate3, iArr);
        X448Field.sub(iArrCreate4, iArrCreate3, iArr2);
        X448Field.carry(iArr);
        X448Field.sub(iArrCreate8, iArrCreate2, iArrCreate8);
        X448Field.mul(iArrCreate8, iArrCreate, iArrCreate8);
        X448Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
        X448Field.mul(iArrCreate6, iArrCreate8, pointExt2.x);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt2.y);
        X448Field.mul(iArrCreate6, iArrCreate7, pointExt2.z);
    }

    public static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    public static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X448Field.copy(pointExt.x, 0, pointExt2.x, 0);
        X448Field.copy(pointExt.y, 0, pointExt2.y, 0);
        X448Field.copy(pointExt.z, 0, pointExt2.z, 0);
    }

    public static void pointDouble(PointExt pointExt) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        X448Field.add(pointExt.x, pointExt.y, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate);
        X448Field.sqr(pointExt.x, iArrCreate2);
        X448Field.sqr(pointExt.y, iArrCreate3);
        X448Field.add(iArrCreate2, iArrCreate3, iArrCreate4);
        X448Field.carry(iArrCreate4);
        X448Field.sqr(pointExt.z, iArrCreate5);
        X448Field.add(iArrCreate5, iArrCreate5, iArrCreate5);
        X448Field.carry(iArrCreate5);
        X448Field.sub(iArrCreate4, iArrCreate5, iArrCreate6);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate);
        X448Field.sub(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.mul(iArrCreate, iArrCreate6, pointExt.x);
        X448Field.mul(iArrCreate4, iArrCreate2, pointExt.y);
        X448Field.mul(iArrCreate4, iArrCreate6, pointExt.z);
    }

    public static void pointExtendXY(PointExt pointExt) {
        X448Field.one(pointExt.z);
    }

    public static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 16 * 2 * 16;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            X448Field.cmov(i5, precompBase, i3, pointPrecomp.x, 0);
            int i6 = i3 + 16;
            X448Field.cmov(i5, precompBase, i6, pointPrecomp.y, 0);
            i3 = i6 + 16;
        }
    }

    public static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X448Field.cmov(i6, iArr2, i4, pointExt.x, 0);
            int i7 = i4 + 16;
            X448Field.cmov(i6, iArr2, i7, pointExt.y, 0);
            int i8 = i7 + 16;
            X448Field.cmov(i6, iArr2, i8, pointExt.z, 0);
            i4 = i8 + 16;
        }
        X448Field.cnegate(i2, pointExt.x);
    }

    public static int[] pointPrecomp(PointExt pointExt, int i) {
        PointExt pointExtPointCopy = pointCopy(pointExt);
        PointExt pointExtPointCopy2 = pointCopy(pointExtPointCopy);
        pointDouble(pointExtPointCopy2);
        int[] iArrCreateTable = X448Field.createTable(i * 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            X448Field.copy(pointExtPointCopy.x, 0, iArrCreateTable, i2);
            int i4 = i2 + 16;
            X448Field.copy(pointExtPointCopy.y, 0, iArrCreateTable, i4);
            int i5 = i4 + 16;
            X448Field.copy(pointExtPointCopy.z, 0, iArrCreateTable, i5);
            i2 = i5 + 16;
            i3++;
            if (i3 == i) {
                return iArrCreateTable;
            }
            pointAdd(pointExtPointCopy2, pointExtPointCopy);
        }
    }

    public static PointExt[] pointPrecompVar(PointExt pointExt, int i) {
        PointExt pointExtPointCopy = pointCopy(pointExt);
        pointDouble(pointExtPointCopy);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            pointExtArr[i2] = pointCopy(pointExtArr[i2 - 1]);
            pointAddVar(false, pointExtPointCopy, pointExtArr[i2]);
        }
        return pointExtArr;
    }

    public static void pointSetNeutral(PointExt pointExt) {
        X448Field.zero(pointExt.x);
        X448Field.one(pointExt.y);
        X448Field.one(pointExt.z);
    }

    public static void precompute() {
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            X448Field.copy(B_x, 0, pointExt.x, 0);
            X448Field.copy(B_y, 0, pointExt.y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecompVar(pointExt, 32);
            precompBase = X448Field.createTable(160);
            int i = 0;
            for (int i2 = 0; i2 < 5; i2++) {
                PointExt[] pointExtArr = new PointExt[5];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i3 = 0;
                while (true) {
                    if (i3 >= 5) {
                        break;
                    }
                    pointAddVar(true, pointExt, pointExt2);
                    pointDouble(pointExt);
                    pointExtArr[i3] = pointCopy(pointExt);
                    if (i2 + i3 != 8) {
                        for (int i4 = 1; i4 < 18; i4++) {
                            pointDouble(pointExt);
                        }
                    }
                    i3++;
                }
                PointExt[] pointExtArr2 = new PointExt[16];
                pointExtArr2[0] = pointExt2;
                int i5 = 1;
                for (int i6 = 0; i6 < 4; i6++) {
                    int i7 = 1 << i6;
                    int i8 = 0;
                    while (i8 < i7) {
                        pointExtArr2[i5] = pointCopy(pointExtArr2[i5 - i7]);
                        pointAddVar(false, pointExtArr[i6], pointExtArr2[i5]);
                        i8++;
                        i5++;
                    }
                }
                for (int i9 = 0; i9 < 16; i9++) {
                    PointExt pointExt3 = pointExtArr2[i9];
                    X448Field.inv(pointExt3.z, pointExt3.z);
                    X448Field.mul(pointExt3.x, pointExt3.z, pointExt3.x);
                    X448Field.mul(pointExt3.y, pointExt3.z, pointExt3.y);
                    X448Field.copy(pointExt3.x, 0, precompBase, i);
                    int i10 = i + 16;
                    X448Field.copy(pointExt3.y, 0, precompBase, i10);
                    i = i10 + 16;
                }
            }
        }
    }

    public static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & Poly1305KeyGenerator.R_MASK_LOW_2);
        bArr2[55] = (byte) (bArr2[55] | g.n);
        bArr2[56] = 0;
    }

    public static byte[] reduceScalar(byte[] bArr) {
        long jDecode32 = ((long) decode32(bArr, 0)) & 4294967295L;
        long jDecode24 = ((long) (decode24(bArr, 4) << 4)) & 4294967295L;
        long jDecode322 = ((long) decode32(bArr, 7)) & 4294967295L;
        long jDecode242 = ((long) (decode24(bArr, 11) << 4)) & 4294967295L;
        long jDecode323 = ((long) decode32(bArr, 14)) & 4294967295L;
        long jDecode243 = ((long) (decode24(bArr, 18) << 4)) & 4294967295L;
        long jDecode324 = ((long) decode32(bArr, 21)) & 4294967295L;
        long jDecode244 = ((long) (decode24(bArr, 25) << 4)) & 4294967295L;
        long jDecode325 = ((long) decode32(bArr, 28)) & 4294967295L;
        long jDecode245 = ((long) (decode24(bArr, 32) << 4)) & 4294967295L;
        long jDecode326 = ((long) decode32(bArr, 35)) & 4294967295L;
        long jDecode246 = ((long) (decode24(bArr, 39) << 4)) & 4294967295L;
        long jDecode327 = ((long) decode32(bArr, 42)) & 4294967295L;
        long jDecode247 = ((long) (decode24(bArr, 46) << 4)) & 4294967295L;
        long jDecode328 = ((long) decode32(bArr, 49)) & 4294967295L;
        long jDecode248 = ((long) (decode24(bArr, 53) << 4)) & 4294967295L;
        long jDecode329 = ((long) decode32(bArr, 56)) & 4294967295L;
        long jDecode249 = ((long) (decode24(bArr, 60) << 4)) & 4294967295L;
        long jDecode3210 = ((long) decode32(bArr, 63)) & 4294967295L;
        long jDecode2410 = ((long) (decode24(bArr, 67) << 4)) & 4294967295L;
        long jDecode3211 = ((long) decode32(bArr, 70)) & 4294967295L;
        long jDecode2411 = ((long) (decode24(bArr, 74) << 4)) & 4294967295L;
        long jDecode3212 = ((long) decode32(bArr, 77)) & 4294967295L;
        long jDecode2412 = ((long) (decode24(bArr, 81) << 4)) & 4294967295L;
        long jDecode3213 = ((long) decode32(bArr, 84)) & 4294967295L;
        long jDecode2413 = ((long) (decode24(bArr, 88) << 4)) & 4294967295L;
        long jDecode3214 = ((long) decode32(bArr, 91)) & 4294967295L;
        long jDecode2414 = ((long) (decode24(bArr, 95) << 4)) & 4294967295L;
        long jDecode3215 = ((long) decode32(bArr, 98)) & 4294967295L;
        long jDecode2415 = ((long) (decode24(bArr, 102) << 4)) & 4294967295L;
        long jDecode3216 = ((long) decode32(bArr, 105)) & 4294967295L;
        long jDecode2416 = ((long) (decode24(bArr, 109) << 4)) & 4294967295L;
        long jDecode16 = ((long) decode16(bArr, 112)) & 4294967295L;
        long j = jDecode2416 + (jDecode3216 >>> 28);
        long j2 = jDecode3216 & 268435455;
        long j3 = jDecode2411 + (jDecode16 * 227822194) + (j * 149865618);
        long j4 = jDecode3212 + (jDecode16 * 149865618) + (j * 550336261);
        long j5 = jDecode328 + (j2 * 43969588);
        long j6 = jDecode248 + (j * 43969588) + (j2 * 30366549);
        long j7 = jDecode329 + (jDecode16 * 43969588) + (j * 30366549) + (j2 * 163752818);
        long j8 = jDecode249 + (jDecode16 * 30366549) + (j * 163752818) + (j2 * 258169998);
        long j9 = jDecode3210 + (jDecode16 * 163752818) + (j * 258169998) + (j2 * 96434764);
        long j10 = jDecode2410 + (jDecode16 * 258169998) + (j * 96434764) + (j2 * 227822194);
        long j11 = jDecode3211 + (jDecode16 * 96434764) + (j * 227822194) + (j2 * 149865618);
        long j12 = jDecode2415 + (jDecode3215 >>> 28);
        long j13 = jDecode3215 & 268435455;
        long j14 = jDecode247 + (j12 * 43969588);
        long j15 = j10 + (j12 * 149865618);
        long j16 = j11 + (j12 * 550336261);
        long j17 = jDecode327 + (j13 * 43969588);
        long j18 = j5 + (j12 * 30366549) + (j13 * 163752818);
        long j19 = j6 + (j12 * 163752818) + (j13 * 258169998);
        long j20 = j7 + (j12 * 258169998) + (j13 * 96434764);
        long j21 = j8 + (j12 * 96434764) + (j13 * 227822194);
        long j22 = j9 + (j12 * 227822194) + (j13 * 149865618);
        long j23 = jDecode2414 + (jDecode3214 >>> 28);
        long j24 = jDecode3214 & 268435455;
        long j25 = jDecode246 + (j23 * 43969588);
        long j26 = j22 + (j23 * 550336261);
        long j27 = jDecode326 + (j24 * 43969588);
        long j28 = j17 + (j23 * 30366549) + (j24 * 163752818);
        long j29 = j14 + (j13 * 30366549) + (j23 * 163752818) + (j24 * 258169998);
        long j30 = j18 + (j23 * 258169998) + (j24 * 96434764);
        long j31 = j19 + (j23 * 96434764) + (j24 * 227822194);
        long j32 = j20 + (j23 * 227822194) + (j24 * 149865618);
        long j33 = j21 + (j23 * 149865618) + (j24 * 550336261);
        long j34 = jDecode2413 + (jDecode3213 >>> 28);
        long j35 = j3 + (j2 * 550336261) + (j16 >>> 28);
        long j36 = j4 + (j35 >>> 28);
        long j37 = jDecode2412 + (jDecode16 * 550336261) + (j36 >>> 28);
        long j38 = j36 & 268435455;
        long j39 = (jDecode3213 & 268435455) + (j37 >>> 28);
        long j40 = j37 & 268435455;
        long j41 = jDecode244 + (j40 * 43969588);
        long j42 = jDecode325 + (j39 * 43969588) + (j40 * 30366549);
        long j43 = jDecode245 + (j34 * 43969588) + (j39 * 30366549) + (j40 * 163752818);
        long j44 = j27 + (j34 * 30366549) + (j39 * 163752818) + (j40 * 258169998);
        long j45 = j25 + (j24 * 30366549) + (j34 * 163752818) + (j39 * 258169998) + (j40 * 96434764);
        long j46 = j28 + (j34 * 258169998) + (j39 * 96434764) + (j40 * 227822194);
        long j47 = j29 + (j34 * 96434764) + (j39 * 227822194) + (j40 * 149865618);
        long j48 = j30 + (j34 * 227822194) + (j39 * 149865618) + (j40 * 550336261);
        long j49 = jDecode324 + (j38 * 43969588);
        long j50 = j26 + (j33 >>> 28);
        long j51 = j15 + (j13 * 550336261) + (j50 >>> 28);
        long j52 = (j16 & 268435455) + (j51 >>> 28);
        long j53 = j51 & 268435455;
        long j54 = (j35 & 268435455) + (j52 >>> 28);
        long j55 = j52 & 268435455;
        long j56 = jDecode323 + (j55 * 43969588);
        long j57 = jDecode243 + (j54 * 43969588) + (j55 * 30366549);
        long j58 = j49 + (j54 * 30366549) + (j55 * 163752818);
        long j59 = j41 + (j38 * 30366549) + (j54 * 163752818) + (j55 * 258169998);
        long j60 = j42 + (j38 * 163752818) + (j54 * 258169998) + (j55 * 96434764);
        long j61 = j43 + (j38 * 258169998) + (j54 * 96434764) + (j55 * 227822194);
        long j62 = j44 + (j38 * 96434764) + (j54 * 227822194) + (j55 * 149865618);
        long j63 = j45 + (j38 * 227822194) + (j54 * 149865618) + (j55 * 550336261);
        long j64 = j31 + (j34 * 149865618) + (j39 * 550336261) + (j48 >>> 28);
        long j65 = j32 + (j34 * 550336261) + (j64 >>> 28);
        long j66 = j64 & 268435455;
        long j67 = (j33 & 268435455) + (j65 >>> 28);
        long j68 = (j50 & 268435455) + (j67 >>> 28);
        long j69 = j67 & 268435455;
        long j70 = jDecode322 + (j68 * 43969588);
        long j71 = jDecode242 + (j53 * 43969588) + (j68 * 30366549);
        long j72 = j56 + (j53 * 30366549) + (j68 * 163752818);
        long j73 = j57 + (j53 * 163752818) + (j68 * 258169998);
        long j74 = j58 + (j53 * 258169998) + (j68 * 96434764);
        long j75 = j59 + (j53 * 96434764) + (j68 * 227822194);
        long j76 = j60 + (j53 * 227822194) + (j68 * 149865618);
        long j77 = j61 + (j53 * 149865618) + (j68 * 550336261);
        long j78 = j66 & M26L;
        long j79 = ((j65 & 268435455) * 4) + (j66 >>> 26) + 1;
        long j80 = jDecode32 + (78101261 * j79);
        long j81 = j70 + (30366549 * j69) + (175155932 * j79);
        long j82 = j71 + (163752818 * j69) + (64542499 * j79);
        long j83 = j72 + (258169998 * j69) + (158326419 * j79);
        long j84 = j73 + (96434764 * j69) + (191173276 * j79);
        long j85 = j74 + (227822194 * j69) + (104575268 * j79);
        long j86 = j75 + (149865618 * j69) + (j79 * 137584065);
        long j87 = jDecode24 + (43969588 * j69) + (141809365 * j79) + (j80 >>> 28);
        long j88 = j81 + (j87 >>> 28);
        long j89 = j82 + (j88 >>> 28);
        long j90 = j83 + (j89 >>> 28);
        long j91 = j84 + (j90 >>> 28);
        long j92 = j90 & 268435455;
        long j93 = j85 + (j91 >>> 28);
        long j94 = j91 & 268435455;
        long j95 = j86 + (j93 >>> 28);
        long j96 = j76 + (j69 * 550336261) + (j95 >>> 28);
        long j97 = j95 & 268435455;
        long j98 = j77 + (j96 >>> 28);
        long j99 = j96 & 268435455;
        long j100 = j62 + (j53 * 550336261) + (j98 >>> 28);
        long j101 = j63 + (j100 >>> 28);
        long j102 = j46 + (j38 * 149865618) + (j54 * 550336261) + (j101 >>> 28);
        long j103 = j101 & 268435455;
        long j104 = j47 + (j38 * 550336261) + (j102 >>> 28);
        long j105 = (j48 & 268435455) + (j104 >>> 28);
        long j106 = j78 + (j105 >>> 28);
        long j107 = (j106 >>> 26) - 1;
        long j108 = (j80 & 268435455) - (j107 & 78101261);
        long j109 = ((j87 & 268435455) - (j107 & 141809365)) + (j108 >> 28);
        long j110 = ((j88 & 268435455) - (j107 & 175155932)) + (j109 >> 28);
        long j111 = ((j89 & 268435455) - (j107 & 64542499)) + (j110 >> 28);
        long j112 = j110 & 268435455;
        long j113 = (j92 - (j107 & 158326419)) + (j111 >> 28);
        long j114 = (j94 - (j107 & 191173276)) + (j113 >> 28);
        long j115 = j113 & 268435455;
        long j116 = ((j93 & 268435455) - (j107 & 104575268)) + (j114 >> 28);
        long j117 = j114 & 268435455;
        long j118 = (j97 - (j107 & 137584065)) + (j116 >> 28);
        long j119 = j99 + (j118 >> 28);
        long j120 = j118 & 268435455;
        long j121 = (j98 & 268435455) + (j119 >> 28);
        long j122 = (j100 & 268435455) + (j121 >> 28);
        long j123 = j103 + (j122 >> 28);
        long j124 = (j102 & 268435455) + (j123 >> 28);
        long j125 = (j104 & 268435455) + (j124 >> 28);
        long j126 = (j105 & 268435455) + (j125 >> 28);
        long j127 = (j106 & M26L) + (j126 >> 28);
        byte[] bArr2 = new byte[57];
        encode56(((j109 & 268435455) << 28) | (j108 & 268435455), bArr2, 0);
        encode56(((j111 & 268435455) << 28) | j112, bArr2, 7);
        encode56(j115 | (j117 << 28), bArr2, 14);
        encode56((j116 & 268435455) | (j120 << 28), bArr2, 21);
        encode56((j119 & 268435455) | ((j121 & 268435455) << 28), bArr2, 28);
        encode56((j122 & 268435455) | ((j123 & 268435455) << 28), bArr2, 35);
        encode56((j124 & 268435455) | ((j125 & 268435455) << 28), bArr2, 42);
        encode56((j127 << 28) | (j126 & 268435455), bArr2, 49);
        return bArr2;
    }

    public static void scalarMult(byte[] bArr, PointExt pointExt, PointExt pointExt2) {
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(14, iArr, 2, 0);
        Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(14, iArr, 1);
        int[] iArrPointPrecomp = pointPrecomp(pointExt, 8);
        pointLookup(iArr, 111, iArrPointPrecomp, pointExt2);
        PointExt pointExt3 = new PointExt();
        for (int i = 110; i >= 0; i--) {
            for (int i2 = 0; i2 < 4; i2++) {
                pointDouble(pointExt2);
            }
            pointLookup(iArr, i, iArrPointPrecomp, pointExt3);
            pointAdd(pointExt3, pointExt2);
        }
        for (int i3 = 0; i3 < 2; i3++) {
            pointDouble(pointExt2);
        }
    }

    public static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        pointSetNeutral(pointExt);
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr) + 4;
        Nat.shiftDownBit(15, iArr, 0);
        PointPrecomp pointPrecomp = new PointPrecomp();
        int i = 17;
        while (true) {
            int i2 = i;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = 0;
                for (int i5 = 0; i5 < 5; i5++) {
                    i4 = (i4 & (~(1 << i5))) ^ ((iArr[i2 >>> 5] >>> (i2 & 31)) << i5);
                    i2 += 18;
                }
                int i6 = (i4 >>> 4) & 1;
                pointLookup(i3, ((-i6) ^ i4) & 15, pointPrecomp);
                X448Field.cnegate(i6, pointPrecomp.x);
                pointAddPrecomp(pointPrecomp, pointExt);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt);
            }
        }
    }

    public static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr, pointExt);
        if (encodePoint(pointExt, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X448");
        }
        byte[] bArr2 = new byte[57];
        pruneScalar(bArr, i, bArr2);
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr2, pointExt);
        if (checkPoint(pointExt.x, pointExt.y, pointExt.z) == 0) {
            throw new IllegalStateException();
        }
        X448Field.copy(pointExt.x, 0, iArr, 0);
        X448Field.copy(pointExt.y, 0, iArr2, 0);
    }

    public static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointExt pointExt, PointExt pointExt2) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointExtArrPointPrecompVar = pointPrecompVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> co.j;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointExt2);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> co.j;
                pointAddVar(i3 != 0, pointExtArrPointPrecompVar[(b2 ^ i3) >>> 1], pointExt2);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt2);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != xof.doFinal(bArr5, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Xof xof, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != xof.doFinal(bArr4, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
