package org.bouncycastle.math.ec.rfc8032;

import com.igexin.c.a.d.g;
import com.igexin.push.config.c;
import com.umeng.analytics.pro.co;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.ec.rfc7748.X25519;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Ed25519 {
    public static final int L0 = -50998291;
    public static final int L1 = 19280294;
    public static final int L2 = 127719000;
    public static final int L3 = -6428113;
    public static final int L4 = 5343;
    public static final long M28L = 268435455;
    public static final long M32L = 4294967295L;
    public static final int POINT_BYTES = 32;
    public static final int PRECOMP_BLOCKS = 8;
    public static final int PRECOMP_MASK = 7;
    public static final int PRECOMP_POINTS = 8;
    public static final int PRECOMP_SPACING = 8;
    public static final int PRECOMP_TEETH = 4;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 32;
    public static final int SCALAR_BYTES = 32;
    public static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    public static final int WNAF_WIDTH_BASE = 7;
    public static final byte[] DOM2_PREFIX = Strings.toByteArray("SigEd25519 no Ed25519 collisions");
    public static final int[] P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    public static final int[] L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    public static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    public static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    public static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    public static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    public static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    public static final Object precompLock = new Object();
    public static PointExt[] precompBaseTable = null;
    public static int[] precompBase = null;

    public static final class Algorithm {
        public static final int Ed25519 = 0;
        public static final int Ed25519ctx = 1;
        public static final int Ed25519ph = 2;
    }

    public static class PointAccum {
        public int[] u;
        public int[] v;
        public int[] x;
        public int[] y;
        public int[] z;

        public PointAccum() {
            this.x = X25519Field.create();
            this.y = X25519Field.create();
            this.z = X25519Field.create();
            this.u = X25519Field.create();
            this.v = X25519Field.create();
        }
    }

    public static class PointAffine {
        public int[] x;
        public int[] y;

        public PointAffine() {
            this.x = X25519Field.create();
            this.y = X25519Field.create();
        }
    }

    public static class PointExt {
        public int[] t;
        public int[] x;
        public int[] y;
        public int[] z;

        public PointExt() {
            this.x = X25519Field.create();
            this.y = X25519Field.create();
            this.z = X25519Field.create();
            this.t = X25519Field.create();
        }
    }

    public static class PointPrecomp {
        public int[] xyd;
        public int[] ymx_h;
        public int[] ypx_h;

        public PointPrecomp() {
            this.ypx_h = X25519Field.create();
            this.ymx_h = X25519Field.create();
            this.xyd = X25519Field.create();
        }
    }

    public static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[16];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[8];
        decodeScalar(bArr3, 0, iArr3);
        Nat256.mulAddTo(iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < 16; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    public static boolean checkContextVar(byte[] bArr, byte b) {
        return (bArr == null && b == 0) || (bArr != null && bArr.length < 256);
    }

    public static int checkPoint(int[] iArr, int[] iArr2) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        X25519Field.sqr(iArr, iArrCreate2);
        X25519Field.sqr(iArr2, iArrCreate3);
        X25519Field.mul(iArrCreate2, iArrCreate3, iArrCreate);
        X25519Field.sub(iArrCreate3, iArrCreate2, iArrCreate3);
        X25519Field.mul(iArrCreate, C_d, iArrCreate);
        X25519Field.addOne(iArrCreate);
        X25519Field.sub(iArrCreate, iArrCreate3, iArrCreate);
        X25519Field.normalize(iArrCreate);
        return X25519Field.isZero(iArrCreate);
    }

    public static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        X25519Field.sqr(iArr, iArrCreate2);
        X25519Field.sqr(iArr2, iArrCreate3);
        X25519Field.sqr(iArr3, iArrCreate4);
        X25519Field.mul(iArrCreate2, iArrCreate3, iArrCreate);
        X25519Field.sub(iArrCreate3, iArrCreate2, iArrCreate3);
        X25519Field.mul(iArrCreate3, iArrCreate4, iArrCreate3);
        X25519Field.sqr(iArrCreate4, iArrCreate4);
        X25519Field.mul(iArrCreate, C_d, iArrCreate);
        X25519Field.add(iArrCreate, iArrCreate4, iArrCreate);
        X25519Field.sub(iArrCreate, iArrCreate3, iArrCreate);
        X25519Field.normalize(iArrCreate);
        return X25519Field.isZero(iArrCreate);
    }

    public static boolean checkPointVar(byte[] bArr) {
        int[] iArr = new int[8];
        decode32(bArr, 0, iArr, 0, 8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        return !Nat256.gte(iArr, P);
    }

    public static boolean checkScalarVar(byte[] bArr) {
        decodeScalar(bArr, 0, new int[8]);
        return !Nat256.gte(r0, L);
    }

    public static Digest createDigest() {
        return new SHA512Digest();
    }

    public static Digest createPrehash() {
        return createDigest();
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

    public static boolean decodePointVar(byte[] bArr, int i, boolean z, PointAffine pointAffine) {
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i + 32);
        if (!checkPointVar(bArrCopyOfRange)) {
            return false;
        }
        int i2 = (bArrCopyOfRange[31] & g.n) >>> 7;
        bArrCopyOfRange[31] = (byte) (bArrCopyOfRange[31] & 127);
        X25519Field.decode(bArrCopyOfRange, 0, pointAffine.y);
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        X25519Field.sqr(pointAffine.y, iArrCreate);
        X25519Field.mul(C_d, iArrCreate, iArrCreate2);
        X25519Field.subOne(iArrCreate);
        X25519Field.addOne(iArrCreate2);
        if (!X25519Field.sqrtRatioVar(iArrCreate, iArrCreate2, pointAffine.x)) {
            return false;
        }
        X25519Field.normalize(pointAffine.x);
        if (i2 == 1 && X25519Field.isZeroVar(pointAffine.x)) {
            return false;
        }
        if (z ^ (i2 != (pointAffine.x[0] & 1))) {
            int[] iArr = pointAffine.x;
            X25519Field.negate(iArr, iArr);
        }
        return true;
    }

    public static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 8);
    }

    public static void dom2(Digest digest, byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = DOM2_PREFIX;
            digest.update(bArr2, 0, bArr2.length);
            digest.update(b);
            digest.update((byte) bArr.length);
            digest.update(bArr, 0, bArr.length);
        }
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

    public static int encodePoint(PointAccum pointAccum, byte[] bArr, int i) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        X25519Field.inv(pointAccum.z, iArrCreate2);
        X25519Field.mul(pointAccum.x, iArrCreate2, iArrCreate);
        X25519Field.mul(pointAccum.y, iArrCreate2, iArrCreate2);
        X25519Field.normalize(iArrCreate);
        X25519Field.normalize(iArrCreate2);
        int iCheckPoint = checkPoint(iArrCreate, iArrCreate2);
        X25519Field.encode(iArrCreate2, bArr, i);
        int i2 = (i + 32) - 1;
        bArr[i2] = (byte) (((iArrCreate[0] & 1) << 7) | bArr[i2]);
        return iCheckPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Digest digestCreateDigest = createDigest();
        byte[] bArr3 = new byte[digestCreateDigest.getDigestSize()];
        digestCreateDigest.update(bArr, i, 32);
        digestCreateDigest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    public static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    public static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[16];
        int i2 = 0;
        int i3 = 8;
        int i4 = 16;
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
        byte[] bArr = new byte[c.E];
        int i8 = 1 << i;
        int i9 = i8 - 1;
        int i10 = i8 >>> 1;
        int i11 = 0;
        int i12 = 0;
        while (i2 < 16) {
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

    public static void implSign(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom2(digest, b, bArr4);
        digest.update(bArr, 32, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] bArrReduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArrReduceScalar, bArr7, 0);
        dom2(digest, b, bArr4);
        digest.update(bArr7, 0, 32);
        digest.update(bArr3, i, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] bArrCalculateS = calculateS(bArrReduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 32);
        System.arraycopy(bArrCalculateS, 0, bArr6, i4 + 32, 32);
    }

    public static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest digestCreateDigest = createDigest();
        byte[] bArr5 = new byte[digestCreateDigest.getDigestSize()];
        digestCreateDigest.update(bArr, i, 32);
        digestCreateDigest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(digestCreateDigest, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    public static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest digestCreateDigest = createDigest();
        byte[] bArr6 = new byte[digestCreateDigest.getDigestSize()];
        digestCreateDigest.update(bArr, i, 32);
        digestCreateDigest.doFinal(bArr6, 0);
        byte[] bArr7 = new byte[32];
        pruneScalar(bArr6, 0, bArr7);
        implSign(digestCreateDigest, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    public static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        int i5 = i + 32;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i5);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, i5, i + 64);
        if (!checkPointVar(bArrCopyOfRange) || !checkScalarVar(bArrCopyOfRange2)) {
            return false;
        }
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(bArr2, i2, true, pointAffine)) {
            return false;
        }
        Digest digestCreateDigest = createDigest();
        byte[] bArr5 = new byte[digestCreateDigest.getDigestSize()];
        dom2(digestCreateDigest, b, bArr3);
        digestCreateDigest.update(bArrCopyOfRange, 0, 32);
        digestCreateDigest.update(bArr2, i2, 32);
        digestCreateDigest.update(bArr4, i3, i4);
        digestCreateDigest.doFinal(bArr5, 0);
        byte[] bArrReduceScalar = reduceScalar(bArr5);
        int[] iArr = new int[8];
        decodeScalar(bArrCopyOfRange2, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArrReduceScalar, 0, iArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultStrausVar(iArr, iArr2, pointAffine, pointAccum);
        byte[] bArr6 = new byte[32];
        return encodePoint(pointAccum, bArr6, 0) != 0 && Arrays.areEqual(bArr6, bArrCopyOfRange);
    }

    public static void pointAdd(PointExt pointExt, PointAccum pointAccum) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        int[] iArr = pointAccum.u;
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArr2 = pointAccum.v;
        X25519Field.apm(pointAccum.y, pointAccum.x, iArrCreate2, iArrCreate);
        X25519Field.apm(pointExt.y, pointExt.x, iArrCreate4, iArrCreate3);
        X25519Field.mul(iArrCreate, iArrCreate3, iArrCreate);
        X25519Field.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        X25519Field.mul(pointAccum.u, pointAccum.v, iArrCreate3);
        X25519Field.mul(iArrCreate3, pointExt.t, iArrCreate3);
        X25519Field.mul(iArrCreate3, C_d2, iArrCreate3);
        X25519Field.mul(pointAccum.z, pointExt.z, iArrCreate4);
        X25519Field.add(iArrCreate4, iArrCreate4, iArrCreate4);
        X25519Field.apm(iArrCreate2, iArrCreate, iArr2, iArr);
        X25519Field.apm(iArrCreate4, iArrCreate3, iArrCreate6, iArrCreate5);
        X25519Field.carry(iArrCreate6);
        X25519Field.mul(iArr, iArrCreate5, pointAccum.x);
        X25519Field.mul(iArrCreate6, iArr2, pointAccum.y);
        X25519Field.mul(iArrCreate5, iArrCreate6, pointAccum.z);
    }

    public static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArrCreate7 = X25519Field.create();
        int[] iArrCreate8 = X25519Field.create();
        X25519Field.apm(pointExt.y, pointExt.x, iArrCreate2, iArrCreate);
        X25519Field.apm(pointExt2.y, pointExt2.x, iArrCreate4, iArrCreate3);
        X25519Field.mul(iArrCreate, iArrCreate3, iArrCreate);
        X25519Field.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        X25519Field.mul(pointExt.t, pointExt2.t, iArrCreate3);
        X25519Field.mul(iArrCreate3, C_d2, iArrCreate3);
        X25519Field.mul(pointExt.z, pointExt2.z, iArrCreate4);
        X25519Field.add(iArrCreate4, iArrCreate4, iArrCreate4);
        X25519Field.apm(iArrCreate2, iArrCreate, iArrCreate8, iArrCreate5);
        X25519Field.apm(iArrCreate4, iArrCreate3, iArrCreate7, iArrCreate6);
        X25519Field.carry(iArrCreate7);
        X25519Field.mul(iArrCreate5, iArrCreate6, pointExt2.x);
        X25519Field.mul(iArrCreate7, iArrCreate8, pointExt2.y);
        X25519Field.mul(iArrCreate6, iArrCreate7, pointExt2.z);
        X25519Field.mul(iArrCreate5, iArrCreate8, pointExt2.t);
    }

    public static void pointAddPrecomp(PointPrecomp pointPrecomp, PointAccum pointAccum) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArr = pointAccum.u;
        int[] iArrCreate4 = X25519Field.create();
        int[] iArrCreate5 = X25519Field.create();
        int[] iArr2 = pointAccum.v;
        X25519Field.apm(pointAccum.y, pointAccum.x, iArrCreate2, iArrCreate);
        X25519Field.mul(iArrCreate, pointPrecomp.ymx_h, iArrCreate);
        X25519Field.mul(iArrCreate2, pointPrecomp.ypx_h, iArrCreate2);
        X25519Field.mul(pointAccum.u, pointAccum.v, iArrCreate3);
        X25519Field.mul(iArrCreate3, pointPrecomp.xyd, iArrCreate3);
        X25519Field.apm(iArrCreate2, iArrCreate, iArr2, iArr);
        X25519Field.apm(pointAccum.z, iArrCreate3, iArrCreate5, iArrCreate4);
        X25519Field.carry(iArrCreate5);
        X25519Field.mul(iArr, iArrCreate4, pointAccum.x);
        X25519Field.mul(iArrCreate5, iArr2, pointAccum.y);
        X25519Field.mul(iArrCreate4, iArrCreate5, pointAccum.z);
    }

    public static void pointAddVar(boolean z, PointExt pointExt, PointAccum pointAccum) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        int[] iArr5 = pointAccum.u;
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArr6 = pointAccum.v;
        if (z) {
            iArr2 = iArrCreate3;
            iArr = iArrCreate4;
            iArr4 = iArrCreate5;
            iArr3 = iArrCreate6;
        } else {
            iArr = iArrCreate3;
            iArr2 = iArrCreate4;
            iArr3 = iArrCreate5;
            iArr4 = iArrCreate6;
        }
        X25519Field.apm(pointAccum.y, pointAccum.x, iArrCreate2, iArrCreate);
        X25519Field.apm(pointExt.y, pointExt.x, iArr2, iArr);
        X25519Field.mul(iArrCreate, iArrCreate3, iArrCreate);
        X25519Field.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        X25519Field.mul(pointAccum.u, pointAccum.v, iArrCreate3);
        X25519Field.mul(iArrCreate3, pointExt.t, iArrCreate3);
        X25519Field.mul(iArrCreate3, C_d2, iArrCreate3);
        X25519Field.mul(pointAccum.z, pointExt.z, iArrCreate4);
        X25519Field.add(iArrCreate4, iArrCreate4, iArrCreate4);
        X25519Field.apm(iArrCreate2, iArrCreate, iArr6, iArr5);
        X25519Field.apm(iArrCreate4, iArrCreate3, iArr4, iArr3);
        X25519Field.carry(iArr4);
        X25519Field.mul(iArr5, iArrCreate5, pointAccum.x);
        X25519Field.mul(iArrCreate6, iArr6, pointAccum.y);
        X25519Field.mul(iArrCreate5, iArrCreate6, pointAccum.z);
    }

    public static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2, PointExt pointExt3) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArrCreate4 = X25519Field.create();
        int[] iArrCreate5 = X25519Field.create();
        int[] iArrCreate6 = X25519Field.create();
        int[] iArrCreate7 = X25519Field.create();
        int[] iArrCreate8 = X25519Field.create();
        if (z) {
            iArr2 = iArrCreate3;
            iArr = iArrCreate4;
            iArr4 = iArrCreate6;
            iArr3 = iArrCreate7;
        } else {
            iArr = iArrCreate3;
            iArr2 = iArrCreate4;
            iArr3 = iArrCreate6;
            iArr4 = iArrCreate7;
        }
        X25519Field.apm(pointExt.y, pointExt.x, iArrCreate2, iArrCreate);
        X25519Field.apm(pointExt2.y, pointExt2.x, iArr2, iArr);
        X25519Field.mul(iArrCreate, iArrCreate3, iArrCreate);
        X25519Field.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        X25519Field.mul(pointExt.t, pointExt2.t, iArrCreate3);
        X25519Field.mul(iArrCreate3, C_d2, iArrCreate3);
        X25519Field.mul(pointExt.z, pointExt2.z, iArrCreate4);
        X25519Field.add(iArrCreate4, iArrCreate4, iArrCreate4);
        X25519Field.apm(iArrCreate2, iArrCreate, iArrCreate8, iArrCreate5);
        X25519Field.apm(iArrCreate4, iArrCreate3, iArr4, iArr3);
        X25519Field.carry(iArr4);
        X25519Field.mul(iArrCreate5, iArrCreate6, pointExt3.x);
        X25519Field.mul(iArrCreate7, iArrCreate8, pointExt3.y);
        X25519Field.mul(iArrCreate6, iArrCreate7, pointExt3.z);
        X25519Field.mul(iArrCreate5, iArrCreate8, pointExt3.t);
    }

    public static PointExt pointCopy(PointAccum pointAccum) {
        PointExt pointExt = new PointExt();
        X25519Field.copy(pointAccum.x, 0, pointExt.x, 0);
        X25519Field.copy(pointAccum.y, 0, pointExt.y, 0);
        X25519Field.copy(pointAccum.z, 0, pointExt.z, 0);
        X25519Field.mul(pointAccum.u, pointAccum.v, pointExt.t);
        return pointExt;
    }

    public static PointExt pointCopy(PointAffine pointAffine) {
        PointExt pointExt = new PointExt();
        X25519Field.copy(pointAffine.x, 0, pointExt.x, 0);
        X25519Field.copy(pointAffine.y, 0, pointExt.y, 0);
        pointExtendXY(pointExt);
        return pointExt;
    }

    public static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    public static void pointCopy(PointAffine pointAffine, PointAccum pointAccum) {
        X25519Field.copy(pointAffine.x, 0, pointAccum.x, 0);
        X25519Field.copy(pointAffine.y, 0, pointAccum.y, 0);
        pointExtendXY(pointAccum);
    }

    public static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X25519Field.copy(pointExt.x, 0, pointExt2.x, 0);
        X25519Field.copy(pointExt.y, 0, pointExt2.y, 0);
        X25519Field.copy(pointExt.z, 0, pointExt2.z, 0);
        X25519Field.copy(pointExt.t, 0, pointExt2.t, 0);
    }

    public static void pointDouble(PointAccum pointAccum) {
        int[] iArrCreate = X25519Field.create();
        int[] iArrCreate2 = X25519Field.create();
        int[] iArrCreate3 = X25519Field.create();
        int[] iArr = pointAccum.u;
        int[] iArrCreate4 = X25519Field.create();
        int[] iArrCreate5 = X25519Field.create();
        int[] iArr2 = pointAccum.v;
        X25519Field.sqr(pointAccum.x, iArrCreate);
        X25519Field.sqr(pointAccum.y, iArrCreate2);
        X25519Field.sqr(pointAccum.z, iArrCreate3);
        X25519Field.add(iArrCreate3, iArrCreate3, iArrCreate3);
        X25519Field.apm(iArrCreate, iArrCreate2, iArr2, iArrCreate5);
        X25519Field.add(pointAccum.x, pointAccum.y, iArr);
        X25519Field.sqr(iArr, iArr);
        X25519Field.sub(iArr2, iArr, iArr);
        X25519Field.add(iArrCreate3, iArrCreate5, iArrCreate4);
        X25519Field.carry(iArrCreate4);
        X25519Field.mul(iArr, iArrCreate4, pointAccum.x);
        X25519Field.mul(iArrCreate5, iArr2, pointAccum.y);
        X25519Field.mul(iArrCreate4, iArrCreate5, pointAccum.z);
    }

    public static void pointExtendXY(PointAccum pointAccum) {
        X25519Field.one(pointAccum.z);
        X25519Field.copy(pointAccum.x, 0, pointAccum.u, 0);
        X25519Field.copy(pointAccum.y, 0, pointAccum.v, 0);
    }

    public static void pointExtendXY(PointExt pointExt) {
        X25519Field.one(pointExt.z);
        X25519Field.mul(pointExt.x, pointExt.y, pointExt.t);
    }

    public static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 8 * 3 * 10;
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            X25519Field.cmov(i5, precompBase, i3, pointPrecomp.ypx_h, 0);
            int i6 = i3 + 10;
            X25519Field.cmov(i5, precompBase, i6, pointPrecomp.ymx_h, 0);
            int i7 = i6 + 10;
            X25519Field.cmov(i5, precompBase, i7, pointPrecomp.xyd, 0);
            i3 = i7 + 10;
        }
    }

    public static void pointLookup(int[] iArr, int i, PointExt pointExt) {
        int i2 = i * 40;
        X25519Field.copy(iArr, i2, pointExt.x, 0);
        int i3 = i2 + 10;
        X25519Field.copy(iArr, i3, pointExt.y, 0);
        int i4 = i3 + 10;
        X25519Field.copy(iArr, i4, pointExt.z, 0);
        X25519Field.copy(iArr, i4 + 10, pointExt.t, 0);
    }

    public static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X25519Field.cmov(i6, iArr2, i4, pointExt.x, 0);
            int i7 = i4 + 10;
            X25519Field.cmov(i6, iArr2, i7, pointExt.y, 0);
            int i8 = i7 + 10;
            X25519Field.cmov(i6, iArr2, i8, pointExt.z, 0);
            int i9 = i8 + 10;
            X25519Field.cmov(i6, iArr2, i9, pointExt.t, 0);
            i4 = i9 + 10;
        }
        X25519Field.cnegate(i2, pointExt.x);
        X25519Field.cnegate(i2, pointExt.t);
    }

    public static int[] pointPrecomp(PointAffine pointAffine, int i) {
        PointExt pointExtPointCopy = pointCopy(pointAffine);
        PointExt pointExtPointCopy2 = pointCopy(pointExtPointCopy);
        pointAdd(pointExtPointCopy, pointExtPointCopy2);
        int[] iArrCreateTable = X25519Field.createTable(i * 4);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            X25519Field.copy(pointExtPointCopy.x, 0, iArrCreateTable, i2);
            int i4 = i2 + 10;
            X25519Field.copy(pointExtPointCopy.y, 0, iArrCreateTable, i4);
            int i5 = i4 + 10;
            X25519Field.copy(pointExtPointCopy.z, 0, iArrCreateTable, i5);
            int i6 = i5 + 10;
            X25519Field.copy(pointExtPointCopy.t, 0, iArrCreateTable, i6);
            i2 = i6 + 10;
            i3++;
            if (i3 == i) {
                return iArrCreateTable;
            }
            pointAdd(pointExtPointCopy2, pointExtPointCopy);
        }
    }

    public static PointExt[] pointPrecompVar(PointExt pointExt, int i) {
        PointExt pointExt2 = new PointExt();
        pointAddVar(false, pointExt, pointExt, pointExt2);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            PointExt pointExt3 = pointExtArr[i2 - 1];
            PointExt pointExt4 = new PointExt();
            pointExtArr[i2] = pointExt4;
            pointAddVar(false, pointExt3, pointExt2, pointExt4);
        }
        return pointExtArr;
    }

    public static void pointSetNeutral(PointAccum pointAccum) {
        X25519Field.zero(pointAccum.x);
        X25519Field.one(pointAccum.y);
        X25519Field.one(pointAccum.z);
        X25519Field.zero(pointAccum.u);
        X25519Field.one(pointAccum.v);
    }

    public static void pointSetNeutral(PointExt pointExt) {
        X25519Field.zero(pointExt.x);
        X25519Field.one(pointExt.y);
        X25519Field.one(pointExt.z);
        X25519Field.zero(pointExt.t);
    }

    public static void precompute() {
        int i;
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            X25519Field.copy(B_x, 0, pointExt.x, 0);
            X25519Field.copy(B_y, 0, pointExt.y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecompVar(pointExt, 32);
            PointAccum pointAccum = new PointAccum();
            X25519Field.copy(B_x, 0, pointAccum.x, 0);
            X25519Field.copy(B_y, 0, pointAccum.y, 0);
            pointExtendXY(pointAccum);
            precompBase = X25519Field.createTable(192);
            int i2 = 0;
            for (int i3 = 0; i3 < 8; i3++) {
                PointExt[] pointExtArr = new PointExt[4];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i4 = 0;
                while (true) {
                    i = 1;
                    if (i4 >= 4) {
                        break;
                    }
                    pointAddVar(true, pointExt2, pointCopy(pointAccum), pointExt2);
                    pointDouble(pointAccum);
                    pointExtArr[i4] = pointCopy(pointAccum);
                    if (i3 + i4 != 10) {
                        while (i < 8) {
                            pointDouble(pointAccum);
                            i++;
                        }
                    }
                    i4++;
                }
                PointExt[] pointExtArr2 = new PointExt[8];
                pointExtArr2[0] = pointExt2;
                int i5 = 0;
                int i6 = 1;
                while (i5 < 3) {
                    int i7 = i << i5;
                    int i8 = 0;
                    while (i8 < i7) {
                        PointExt pointExt3 = pointExtArr2[i6 - i7];
                        PointExt pointExt4 = pointExtArr[i5];
                        PointExt pointExt5 = new PointExt();
                        pointExtArr2[i6] = pointExt5;
                        pointAddVar(false, pointExt3, pointExt4, pointExt5);
                        i8++;
                        i6++;
                    }
                    i5++;
                    i = 1;
                }
                for (int i9 = 0; i9 < 8; i9++) {
                    PointExt pointExt6 = pointExtArr2[i9];
                    int[] iArrCreate = X25519Field.create();
                    int[] iArrCreate2 = X25519Field.create();
                    X25519Field.add(pointExt6.z, pointExt6.z, iArrCreate);
                    X25519Field.inv(iArrCreate, iArrCreate2);
                    X25519Field.mul(pointExt6.x, iArrCreate2, iArrCreate);
                    X25519Field.mul(pointExt6.y, iArrCreate2, iArrCreate2);
                    PointPrecomp pointPrecomp = new PointPrecomp();
                    X25519Field.apm(iArrCreate2, iArrCreate, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                    X25519Field.mul(iArrCreate, iArrCreate2, pointPrecomp.xyd);
                    X25519Field.mul(pointPrecomp.xyd, C_d4, pointPrecomp.xyd);
                    X25519Field.normalize(pointPrecomp.ypx_h);
                    X25519Field.normalize(pointPrecomp.ymx_h);
                    X25519Field.copy(pointPrecomp.ypx_h, 0, precompBase, i2);
                    int i10 = i2 + 10;
                    X25519Field.copy(pointPrecomp.ymx_h, 0, precompBase, i10);
                    int i11 = i10 + 10;
                    X25519Field.copy(pointPrecomp.xyd, 0, precompBase, i11);
                    i2 = i11 + 10;
                }
            }
        }
    }

    public static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        bArr2[31] = (byte) (bArr2[31] & 127);
        bArr2[31] = (byte) (bArr2[31] | UTF8.S_P3B);
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
        long j = ((long) bArr[63]) & 255;
        long j2 = jDecode249 + (jDecode329 >> 28);
        long j3 = jDecode329 & 268435455;
        long j4 = jDecode325 - (j2 * (-50998291));
        long j5 = (jDecode245 - (j * (-50998291))) - (j2 * 19280294);
        long j6 = (jDecode326 - (j * 19280294)) - (j2 * 127719000);
        long j7 = (jDecode246 - (j * 127719000)) - (j2 * (-6428113));
        long j8 = (jDecode327 - (j * (-6428113))) - (j2 * 5343);
        long j9 = j7 - (j3 * 5343);
        long j10 = jDecode248 + (jDecode328 >> 28);
        long j11 = jDecode328 & 268435455;
        long j12 = (j6 - (j3 * (-6428113))) - (j10 * 5343);
        long j13 = ((jDecode244 - (j3 * (-50998291))) - (j10 * 19280294)) - (j11 * 127719000);
        long j14 = ((j5 - (j3 * 127719000)) - (j10 * (-6428113))) - (j11 * 5343);
        long j15 = (jDecode247 - (j * 5343)) + (j8 >> 28);
        long j16 = (j8 & 268435455) + (j9 >> 28);
        long j17 = jDecode242 - (j16 * (-50998291));
        long j18 = (jDecode323 - (j15 * (-50998291))) - (j16 * 19280294);
        long j19 = ((jDecode243 - (j11 * (-50998291))) - (j15 * 19280294)) - (j16 * 127719000);
        long j20 = (((jDecode324 - (j10 * (-50998291))) - (j11 * 19280294)) - (j15 * 127719000)) - (j16 * (-6428113));
        long j21 = (j13 - (j15 * (-6428113))) - (j16 * 5343);
        long j22 = (j9 & 268435455) + (j12 >> 28);
        long j23 = j12 & 268435455;
        long j24 = jDecode322 - (j22 * (-50998291));
        long j25 = j17 - (j22 * 19280294);
        long j26 = j18 - (j22 * 127719000);
        long j27 = j19 - (j22 * (-6428113));
        long j28 = j20 - (j22 * 5343);
        long j29 = j23 + (j14 >> 28);
        long j30 = j14 & 268435455;
        long j31 = jDecode24 - (j29 * (-50998291));
        long j32 = j24 - (j29 * 19280294);
        long j33 = j25 - (j29 * 127719000);
        long j34 = j26 - (j29 * (-6428113));
        long j35 = j27 - (j29 * 5343);
        long j36 = ((((j4 - (j3 * 19280294)) - (j10 * 127719000)) - (j11 * (-6428113))) - (j15 * 5343)) + (j21 >> 28);
        long j37 = j36 & 268435455;
        long j38 = j37 >>> 27;
        long j39 = j30 + (j36 >> 28) + j38;
        long j40 = jDecode32 - (j39 * (-50998291));
        long j41 = (j31 - (j39 * 19280294)) + (j40 >> 28);
        long j42 = j40 & 268435455;
        long j43 = (j32 - (j39 * 127719000)) + (j41 >> 28);
        long j44 = (j33 - (j39 * (-6428113))) + (j43 >> 28);
        long j45 = (j34 - (j39 * 5343)) + (j44 >> 28);
        long j46 = j35 + (j45 >> 28);
        long j47 = j45 & 268435455;
        long j48 = j28 + (j46 >> 28);
        long j49 = (j21 & 268435455) + (j48 >> 28);
        long j50 = j37 + (j49 >> 28);
        long j51 = (j50 >> 28) - j38;
        long j52 = j42 + (j51 & (-50998291));
        long j53 = (j41 & 268435455) + (j51 & 19280294) + (j52 >> 28);
        long j54 = (j43 & 268435455) + (j51 & 127719000) + (j53 >> 28);
        long j55 = (j44 & 268435455) + (j51 & (-6428113)) + (j54 >> 28);
        long j56 = j47 + (j51 & 5343) + (j55 >> 28);
        long j57 = (j46 & 268435455) + (j56 >> 28);
        long j58 = (j48 & 268435455) + (j57 >> 28);
        long j59 = (j49 & 268435455) + (j58 >> 28);
        byte[] bArr2 = new byte[32];
        encode56((j52 & 268435455) | ((j53 & 268435455) << 28), bArr2, 0);
        encode56(((j55 & 268435455) << 28) | (j54 & 268435455), bArr2, 7);
        encode56((j56 & 268435455) | ((j57 & 268435455) << 28), bArr2, 14);
        encode56((j58 & 268435455) | ((j59 & 268435455) << 28), bArr2, 21);
        encode32((int) ((j50 & 268435455) + (j59 >> 28)), bArr2, 28);
        return bArr2;
    }

    public static void scalarMult(byte[] bArr, PointAffine pointAffine, PointAccum pointAccum) {
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(8, iArr, 3, 1);
        Nat.cadd(8, 1 & (~iArr[0]), iArr, L, iArr);
        Nat.shiftDownBit(8, iArr, 0);
        pointCopy(pointAffine, pointAccum);
        int[] iArrPointPrecomp = pointPrecomp(pointAffine, 8);
        PointExt pointExt = new PointExt();
        pointLookup(iArrPointPrecomp, 7, pointExt);
        pointAdd(pointExt, pointAccum);
        int i = 62;
        while (true) {
            pointLookup(iArr, i, iArrPointPrecomp, pointExt);
            pointAdd(pointExt, pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    public static void scalarMultBase(byte[] bArr, PointAccum pointAccum) {
        precompute();
        pointSetNeutral(pointAccum);
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        for (int i = 0; i < 8; i++) {
            iArr[i] = Interleave.shuffle2(iArr[i]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        int i2 = 28;
        while (true) {
            for (int i3 = 0; i3 < 8; i3++) {
                int i4 = iArr[i3] >>> i2;
                int i5 = (i4 >>> 3) & 1;
                pointLookup(i3, (i4 ^ (-i5)) & 7, pointPrecomp);
                X25519Field.cswap(i5, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                X25519Field.cnegate(i5, pointPrecomp.xyd);
                pointAddPrecomp(pointPrecomp, pointAccum);
            }
            i2 -= 4;
            if (i2 < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    public static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr, pointAccum);
        if (encodePoint(pointAccum, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseYZ(X25519.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X25519");
        }
        byte[] bArr2 = new byte[32];
        pruneScalar(bArr, i, bArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr2, pointAccum);
        if (checkPoint(pointAccum.x, pointAccum.y, pointAccum.z) == 0) {
            throw new IllegalStateException();
        }
        X25519Field.copy(pointAccum.y, 0, iArr, 0);
        X25519Field.copy(pointAccum.z, 0, iArr2, 0);
    }

    public static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointExtArrPointPrecompVar = pointPrecompVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i = 252;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> co.j;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointAccum);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> co.j;
                pointAddVar(i3 != 0, pointExtArrPointPrecompVar[(b2 ^ i3) >>> 1], pointAccum);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        implSign(bArr, i, null, (byte) 0, bArr2, i2, i3, bArr3, i4);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        implSign(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4, bArr4, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != digest.doFinal(bArr5, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Digest digest, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != digest.doFinal(bArr4, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest) {
        byte[] bArr4 = new byte[64];
        if (64 == digest.doFinal(bArr4, 0)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
