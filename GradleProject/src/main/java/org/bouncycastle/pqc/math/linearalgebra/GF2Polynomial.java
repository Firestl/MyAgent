package org.bouncycastle.pqc.math.linearalgebra;

import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import com.tencent.connect.common.Constants;
import com.tencent.liteav.TXLiteAVCode;
import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.pqc.crypto.newhope.Reduce;
import org.bouncycastle.pqc.crypto.qtesla.QTesla1p;
import org.bouncycastle.pqc.crypto.qtesla.QTesla3p;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class GF2Polynomial {
    public int blocks;
    public int len;
    public int[] value;
    public static Random rand = new Random();
    public static final boolean[] parity = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    public static final short[] squaringTable = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, 1024, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, 16384, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    public static final int[] bitMask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, PKIFailureInfo.badSenderNonce, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, PKIFailureInfo.duplicateCertReq, 1073741824, Integer.MIN_VALUE, 0};
    public static final int[] reverseRightMask = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, TXLiteAVCode.EVT_CAMERA_REMOVED, 2047, 4095, 8191, 16383, 32767, 65535, 131071, Reduce.RMask, QTesla1p.PARAM_B, QTesla1p.maskb1, QTesla3p.PARAM_B, QTesla3p.maskb1, 8388607, X25519Field.M24, X25519Field.M25, X25519Field.M26, 134217727, X448Field.M28, 536870911, 1073741823, Integer.MAX_VALUE, -1};

    public GF2Polynomial(int i) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.blocks = i2;
        this.value = new int[i2];
        this.len = i;
    }

    public GF2Polynomial(int i, String str) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.blocks = i2;
        this.value = new int[i2];
        this.len = i;
        if (str.equalsIgnoreCase("ZERO")) {
            assignZero();
            return;
        }
        if (str.equalsIgnoreCase("ONE")) {
            assignOne();
            return;
        }
        if (str.equalsIgnoreCase("RANDOM")) {
            randomize();
            return;
        }
        if (str.equalsIgnoreCase("X")) {
            assignX();
            return;
        }
        if (str.equalsIgnoreCase("ALL")) {
            assignAll();
            return;
        }
        throw new IllegalArgumentException("Error: GF2Polynomial was called using " + str + " as value!");
    }

    public GF2Polynomial(int i, BigInteger bigInteger) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.blocks = i2;
        this.value = new int[i2];
        this.len = i;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            byteArray = bArr;
        }
        int length2 = byteArray.length & 3;
        int length3 = ((byteArray.length - 1) >> 2) + 1;
        for (int i3 = 0; i3 < length2; i3++) {
            int[] iArr = this.value;
            int i4 = length3 - 1;
            iArr[i4] = iArr[i4] | ((byteArray[i3] & 255) << (((length2 - 1) - i3) << 3));
        }
        for (int i5 = 0; i5 <= ((byteArray.length - 4) >> 2); i5++) {
            int length4 = (byteArray.length - 1) - (i5 << 2);
            int[] iArr2 = this.value;
            iArr2[i5] = byteArray[length4] & 255;
            iArr2[i5] = iArr2[i5] | ((byteArray[length4 - 1] << 8) & 65280);
            iArr2[i5] = iArr2[i5] | ((byteArray[length4 - 2] << 16) & 16711680);
            iArr2[i5] = ((byteArray[length4 - 3] << 24) & (-16777216)) | iArr2[i5];
        }
        int i6 = this.len;
        if ((i6 & 31) != 0) {
            int[] iArr3 = this.value;
            int i7 = this.blocks - 1;
            iArr3[i7] = reverseRightMask[i6 & 31] & iArr3[i7];
        }
        reduceN();
    }

    public GF2Polynomial(int i, Random random) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.blocks = i2;
        this.value = new int[i2];
        this.len = i;
        randomize(random);
    }

    public GF2Polynomial(int i, byte[] bArr) {
        int i2;
        i = i < 1 ? 1 : i;
        int i3 = ((i - 1) >> 5) + 1;
        this.blocks = i3;
        this.value = new int[i3];
        this.len = i;
        int iMin = Math.min(((bArr.length - 1) >> 2) + 1, i3);
        int i4 = 0;
        while (true) {
            i2 = iMin - 1;
            if (i4 >= i2) {
                break;
            }
            int length = (bArr.length - (i4 << 2)) - 1;
            int[] iArr = this.value;
            iArr[i4] = bArr[length] & 255;
            iArr[i4] = (65280 & (bArr[length - 1] << 8)) | iArr[i4];
            iArr[i4] = (16711680 & (bArr[length - 2] << 16)) | iArr[i4];
            iArr[i4] = ((bArr[length - 3] << 24) & (-16777216)) | iArr[i4];
            i4++;
        }
        int length2 = (bArr.length - (i2 << 2)) - 1;
        int[] iArr2 = this.value;
        iArr2[i2] = bArr[length2] & 255;
        if (length2 > 0) {
            iArr2[i2] = (65280 & (bArr[length2 - 1] << 8)) | iArr2[i2];
        }
        if (length2 > 1) {
            int[] iArr3 = this.value;
            iArr3[i2] = iArr3[i2] | (16711680 & (bArr[length2 - 2] << 16));
        }
        if (length2 > 2) {
            int[] iArr4 = this.value;
            iArr4[i2] = ((bArr[length2 - 3] << 24) & (-16777216)) | iArr4[i2];
        }
        zeroUnusedBits();
        reduceN();
    }

    public GF2Polynomial(int i, int[] iArr) {
        i = i < 1 ? 1 : i;
        int i2 = ((i - 1) >> 5) + 1;
        this.blocks = i2;
        this.value = new int[i2];
        this.len = i;
        System.arraycopy(iArr, 0, this.value, 0, Math.min(i2, iArr.length));
        zeroUnusedBits();
    }

    public GF2Polynomial(GF2Polynomial gF2Polynomial) {
        this.len = gF2Polynomial.len;
        this.blocks = gF2Polynomial.blocks;
        this.value = IntUtils.clone(gF2Polynomial.value);
    }

    private void doShiftBlocksLeft(int i) {
        int i2 = this.blocks;
        int[] iArr = this.value;
        if (i2 > iArr.length) {
            int[] iArr2 = new int[i2];
            System.arraycopy(iArr, 0, iArr2, i, i2 - i);
            this.value = null;
            this.value = iArr2;
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            int[] iArr3 = this.value;
            iArr3[i3] = iArr3[i3 - i];
        }
        for (int i4 = 0; i4 < i; i4++) {
            this.value[i4] = 0;
        }
    }

    private GF2Polynomial karaMult(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len << 1);
        int i = this.len;
        if (i <= 32) {
            gF2Polynomial2.value = mult32(this.value[0], gF2Polynomial.value[0]);
            return gF2Polynomial2;
        }
        if (i <= 64) {
            gF2Polynomial2.value = mult64(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        }
        if (i <= 128) {
            gF2Polynomial2.value = mult128(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        }
        if (i <= 256) {
            gF2Polynomial2.value = mult256(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        }
        if (i <= 512) {
            gF2Polynomial2.value = mult512(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        }
        int i2 = bitMask[IntegerFunctions.floorLog(i - 1)];
        int i3 = ((i2 - 1) >> 5) + 1;
        GF2Polynomial gF2PolynomialLower = lower(i3);
        GF2Polynomial gF2PolynomialUpper = upper(i3);
        GF2Polynomial gF2PolynomialLower2 = gF2Polynomial.lower(i3);
        GF2Polynomial gF2PolynomialUpper2 = gF2Polynomial.upper(i3);
        GF2Polynomial gF2PolynomialKaraMult = gF2PolynomialUpper.karaMult(gF2PolynomialUpper2);
        GF2Polynomial gF2PolynomialKaraMult2 = gF2PolynomialLower.karaMult(gF2PolynomialLower2);
        gF2PolynomialLower.addToThis(gF2PolynomialUpper);
        gF2PolynomialLower2.addToThis(gF2PolynomialUpper2);
        GF2Polynomial gF2PolynomialKaraMult3 = gF2PolynomialLower.karaMult(gF2PolynomialLower2);
        gF2Polynomial2.shiftLeftAddThis(gF2PolynomialKaraMult, i2 << 1);
        gF2Polynomial2.shiftLeftAddThis(gF2PolynomialKaraMult, i2);
        gF2Polynomial2.shiftLeftAddThis(gF2PolynomialKaraMult3, i2);
        gF2Polynomial2.shiftLeftAddThis(gF2PolynomialKaraMult2, i2);
        gF2Polynomial2.addToThis(gF2PolynomialKaraMult2);
        return gF2Polynomial2;
    }

    private GF2Polynomial lower(int i) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(i << 5);
        System.arraycopy(this.value, 0, gF2Polynomial.value, 0, Math.min(i, this.blocks));
        return gF2Polynomial;
    }

    public static int[] mult128(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[8];
        int[] iArr4 = new int[2];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(2, iArr.length));
        int[] iArr5 = new int[2];
        if (iArr.length > 2) {
            System.arraycopy(iArr, 2, iArr5, 0, Math.min(2, iArr.length - 2));
        }
        int[] iArr6 = new int[2];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(2, iArr2.length));
        int[] iArr7 = new int[2];
        if (iArr2.length > 2) {
            System.arraycopy(iArr2, 2, iArr7, 0, Math.min(2, iArr2.length - 2));
        }
        if (iArr5[1] != 0 || iArr7[1] != 0) {
            int[] iArrMult64 = mult64(iArr5, iArr7);
            iArr3[7] = iArr3[7] ^ iArrMult64[3];
            iArr3[6] = iArr3[6] ^ iArrMult64[2];
            iArr3[5] = iArr3[5] ^ (iArrMult64[1] ^ iArrMult64[3]);
            iArr3[4] = iArr3[4] ^ (iArrMult64[0] ^ iArrMult64[2]);
            iArr3[3] = iArr3[3] ^ iArrMult64[1];
            iArr3[2] = iArrMult64[0] ^ iArr3[2];
        } else if (iArr5[0] != 0 || iArr7[0] != 0) {
            int[] iArrMult32 = mult32(iArr5[0], iArr7[0]);
            iArr3[5] = iArr3[5] ^ iArrMult32[1];
            iArr3[4] = iArr3[4] ^ iArrMult32[0];
            iArr3[3] = iArr3[3] ^ iArrMult32[1];
            iArr3[2] = iArrMult32[0] ^ iArr3[2];
        }
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        if (iArr5[1] == 0 && iArr7[1] == 0) {
            int[] iArrMult322 = mult32(iArr5[0], iArr7[0]);
            iArr3[3] = iArr3[3] ^ iArrMult322[1];
            iArr3[2] = iArrMult322[0] ^ iArr3[2];
        } else {
            int[] iArrMult642 = mult64(iArr5, iArr7);
            iArr3[5] = iArr3[5] ^ iArrMult642[3];
            iArr3[4] = iArr3[4] ^ iArrMult642[2];
            iArr3[3] = iArr3[3] ^ iArrMult642[1];
            iArr3[2] = iArrMult642[0] ^ iArr3[2];
        }
        if (iArr4[1] == 0 && iArr6[1] == 0) {
            int[] iArrMult323 = mult32(iArr4[0], iArr6[0]);
            iArr3[3] = iArr3[3] ^ iArrMult323[1];
            iArr3[2] = iArr3[2] ^ iArrMult323[0];
            iArr3[1] = iArr3[1] ^ iArrMult323[1];
            iArr3[0] = iArrMult323[0] ^ iArr3[0];
        } else {
            int[] iArrMult643 = mult64(iArr4, iArr6);
            iArr3[5] = iArr3[5] ^ iArrMult643[3];
            iArr3[4] = iArr3[4] ^ iArrMult643[2];
            iArr3[3] = iArr3[3] ^ (iArrMult643[1] ^ iArrMult643[3]);
            iArr3[2] = iArr3[2] ^ (iArrMult643[0] ^ iArrMult643[2]);
            iArr3[1] = iArr3[1] ^ iArrMult643[1];
            iArr3[0] = iArrMult643[0] ^ iArr3[0];
        }
        return iArr3;
    }

    public static int[] mult256(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[4];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(4, iArr.length));
        int[] iArr5 = new int[4];
        if (iArr.length > 4) {
            System.arraycopy(iArr, 4, iArr5, 0, Math.min(4, iArr.length - 4));
        }
        int[] iArr6 = new int[4];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(4, iArr2.length));
        int[] iArr7 = new int[4];
        if (iArr2.length > 4) {
            System.arraycopy(iArr2, 4, iArr7, 0, Math.min(4, iArr2.length - 4));
        }
        if (iArr5[3] != 0 || iArr5[2] != 0 || iArr7[3] != 0 || iArr7[2] != 0) {
            int[] iArrMult128 = mult128(iArr5, iArr7);
            iArr3[15] = iArr3[15] ^ iArrMult128[7];
            iArr3[14] = iArr3[14] ^ iArrMult128[6];
            iArr3[13] = iArr3[13] ^ iArrMult128[5];
            iArr3[12] = iArr3[12] ^ iArrMult128[4];
            iArr3[11] = iArr3[11] ^ (iArrMult128[3] ^ iArrMult128[7]);
            iArr3[10] = iArr3[10] ^ (iArrMult128[2] ^ iArrMult128[6]);
            iArr3[9] = iArr3[9] ^ (iArrMult128[1] ^ iArrMult128[5]);
            iArr3[8] = iArr3[8] ^ (iArrMult128[0] ^ iArrMult128[4]);
            iArr3[7] = iArr3[7] ^ iArrMult128[3];
            iArr3[6] = iArr3[6] ^ iArrMult128[2];
            iArr3[5] = iArr3[5] ^ iArrMult128[1];
            iArr3[4] = iArrMult128[0] ^ iArr3[4];
        } else if (iArr5[1] != 0 || iArr7[1] != 0) {
            int[] iArrMult64 = mult64(iArr5, iArr7);
            iArr3[11] = iArr3[11] ^ iArrMult64[3];
            iArr3[10] = iArr3[10] ^ iArrMult64[2];
            iArr3[9] = iArr3[9] ^ iArrMult64[1];
            iArr3[8] = iArr3[8] ^ iArrMult64[0];
            iArr3[7] = iArr3[7] ^ iArrMult64[3];
            iArr3[6] = iArr3[6] ^ iArrMult64[2];
            iArr3[5] = iArr3[5] ^ iArrMult64[1];
            iArr3[4] = iArrMult64[0] ^ iArr3[4];
        } else if (iArr5[0] != 0 || iArr7[0] != 0) {
            int[] iArrMult32 = mult32(iArr5[0], iArr7[0]);
            iArr3[9] = iArr3[9] ^ iArrMult32[1];
            iArr3[8] = iArr3[8] ^ iArrMult32[0];
            iArr3[5] = iArr3[5] ^ iArrMult32[1];
            iArr3[4] = iArrMult32[0] ^ iArr3[4];
        }
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr5[2] = iArr5[2] ^ iArr4[2];
        iArr5[3] = iArr5[3] ^ iArr4[3];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        iArr7[2] = iArr7[2] ^ iArr6[2];
        iArr7[3] = iArr7[3] ^ iArr6[3];
        int[] iArrMult1282 = mult128(iArr5, iArr7);
        iArr3[11] = iArr3[11] ^ iArrMult1282[7];
        iArr3[10] = iArr3[10] ^ iArrMult1282[6];
        iArr3[9] = iArr3[9] ^ iArrMult1282[5];
        iArr3[8] = iArr3[8] ^ iArrMult1282[4];
        iArr3[7] = iArr3[7] ^ iArrMult1282[3];
        iArr3[6] = iArr3[6] ^ iArrMult1282[2];
        iArr3[5] = iArr3[5] ^ iArrMult1282[1];
        iArr3[4] = iArrMult1282[0] ^ iArr3[4];
        int[] iArrMult1283 = mult128(iArr4, iArr6);
        iArr3[11] = iArr3[11] ^ iArrMult1283[7];
        iArr3[10] = iArr3[10] ^ iArrMult1283[6];
        iArr3[9] = iArr3[9] ^ iArrMult1283[5];
        iArr3[8] = iArr3[8] ^ iArrMult1283[4];
        iArr3[7] = iArr3[7] ^ (iArrMult1283[3] ^ iArrMult1283[7]);
        iArr3[6] = iArr3[6] ^ (iArrMult1283[2] ^ iArrMult1283[6]);
        iArr3[5] = iArr3[5] ^ (iArrMult1283[1] ^ iArrMult1283[5]);
        iArr3[4] = iArr3[4] ^ (iArrMult1283[0] ^ iArrMult1283[4]);
        iArr3[3] = iArr3[3] ^ iArrMult1283[3];
        iArr3[2] = iArr3[2] ^ iArrMult1283[2];
        iArr3[1] = iArr3[1] ^ iArrMult1283[1];
        iArr3[0] = iArrMult1283[0] ^ iArr3[0];
        return iArr3;
    }

    public static int[] mult32(int i, int i2) {
        int[] iArr = new int[2];
        if (i != 0 && i2 != 0) {
            long j = ((long) i2) & 4294967295L;
            long j2 = 0;
            for (int i3 = 1; i3 <= 32; i3++) {
                if ((bitMask[i3 - 1] & i) != 0) {
                    j2 ^= j;
                }
                j <<= 1;
            }
            iArr[1] = (int) (j2 >>> 32);
            iArr[0] = (int) (j2 & 4294967295L);
        }
        return iArr;
    }

    public static int[] mult512(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[32];
        int[] iArr4 = new int[8];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(8, iArr.length));
        int[] iArr5 = new int[8];
        if (iArr.length > 8) {
            System.arraycopy(iArr, 8, iArr5, 0, Math.min(8, iArr.length - 8));
        }
        int[] iArr6 = new int[8];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(8, iArr2.length));
        int[] iArr7 = new int[8];
        if (iArr2.length > 8) {
            System.arraycopy(iArr2, 8, iArr7, 0, Math.min(8, iArr2.length - 8));
        }
        int[] iArrMult256 = mult256(iArr5, iArr7);
        iArr3[31] = iArr3[31] ^ iArrMult256[15];
        iArr3[30] = iArr3[30] ^ iArrMult256[14];
        iArr3[29] = iArr3[29] ^ iArrMult256[13];
        iArr3[28] = iArr3[28] ^ iArrMult256[12];
        iArr3[27] = iArr3[27] ^ iArrMult256[11];
        iArr3[26] = iArr3[26] ^ iArrMult256[10];
        iArr3[25] = iArr3[25] ^ iArrMult256[9];
        iArr3[24] = iArr3[24] ^ iArrMult256[8];
        iArr3[23] = iArr3[23] ^ (iArrMult256[7] ^ iArrMult256[15]);
        iArr3[22] = iArr3[22] ^ (iArrMult256[6] ^ iArrMult256[14]);
        iArr3[21] = iArr3[21] ^ (iArrMult256[5] ^ iArrMult256[13]);
        iArr3[20] = iArr3[20] ^ (iArrMult256[4] ^ iArrMult256[12]);
        iArr3[19] = iArr3[19] ^ (iArrMult256[3] ^ iArrMult256[11]);
        iArr3[18] = iArr3[18] ^ (iArrMult256[2] ^ iArrMult256[10]);
        iArr3[17] = iArr3[17] ^ (iArrMult256[1] ^ iArrMult256[9]);
        iArr3[16] = iArr3[16] ^ (iArrMult256[0] ^ iArrMult256[8]);
        iArr3[15] = iArr3[15] ^ iArrMult256[7];
        iArr3[14] = iArr3[14] ^ iArrMult256[6];
        iArr3[13] = iArr3[13] ^ iArrMult256[5];
        iArr3[12] = iArr3[12] ^ iArrMult256[4];
        iArr3[11] = iArr3[11] ^ iArrMult256[3];
        iArr3[10] = iArr3[10] ^ iArrMult256[2];
        iArr3[9] = iArr3[9] ^ iArrMult256[1];
        iArr3[8] = iArr3[8] ^ iArrMult256[0];
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr5[2] = iArr5[2] ^ iArr4[2];
        iArr5[3] = iArr5[3] ^ iArr4[3];
        iArr5[4] = iArr5[4] ^ iArr4[4];
        iArr5[5] = iArr5[5] ^ iArr4[5];
        iArr5[6] = iArr5[6] ^ iArr4[6];
        iArr5[7] = iArr5[7] ^ iArr4[7];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        iArr7[2] = iArr7[2] ^ iArr6[2];
        iArr7[3] = iArr7[3] ^ iArr6[3];
        iArr7[4] = iArr7[4] ^ iArr6[4];
        iArr7[5] = iArr7[5] ^ iArr6[5];
        iArr7[6] = iArr7[6] ^ iArr6[6];
        iArr7[7] = iArr7[7] ^ iArr6[7];
        int[] iArrMult2562 = mult256(iArr5, iArr7);
        iArr3[23] = iArr3[23] ^ iArrMult2562[15];
        iArr3[22] = iArr3[22] ^ iArrMult2562[14];
        iArr3[21] = iArr3[21] ^ iArrMult2562[13];
        iArr3[20] = iArr3[20] ^ iArrMult2562[12];
        iArr3[19] = iArr3[19] ^ iArrMult2562[11];
        iArr3[18] = iArr3[18] ^ iArrMult2562[10];
        iArr3[17] = iArr3[17] ^ iArrMult2562[9];
        iArr3[16] = iArr3[16] ^ iArrMult2562[8];
        iArr3[15] = iArr3[15] ^ iArrMult2562[7];
        iArr3[14] = iArr3[14] ^ iArrMult2562[6];
        iArr3[13] = iArr3[13] ^ iArrMult2562[5];
        iArr3[12] = iArr3[12] ^ iArrMult2562[4];
        iArr3[11] = iArr3[11] ^ iArrMult2562[3];
        iArr3[10] = iArr3[10] ^ iArrMult2562[2];
        iArr3[9] = iArr3[9] ^ iArrMult2562[1];
        iArr3[8] = iArrMult2562[0] ^ iArr3[8];
        int[] iArrMult2563 = mult256(iArr4, iArr6);
        iArr3[23] = iArr3[23] ^ iArrMult2563[15];
        iArr3[22] = iArr3[22] ^ iArrMult2563[14];
        iArr3[21] = iArr3[21] ^ iArrMult2563[13];
        iArr3[20] = iArr3[20] ^ iArrMult2563[12];
        iArr3[19] = iArr3[19] ^ iArrMult2563[11];
        iArr3[18] = iArr3[18] ^ iArrMult2563[10];
        iArr3[17] = iArr3[17] ^ iArrMult2563[9];
        iArr3[16] = iArr3[16] ^ iArrMult2563[8];
        iArr3[15] = iArr3[15] ^ (iArrMult2563[7] ^ iArrMult2563[15]);
        iArr3[14] = iArr3[14] ^ (iArrMult2563[6] ^ iArrMult2563[14]);
        iArr3[13] = iArr3[13] ^ (iArrMult2563[5] ^ iArrMult2563[13]);
        iArr3[12] = iArr3[12] ^ (iArrMult2563[4] ^ iArrMult2563[12]);
        iArr3[11] = iArr3[11] ^ (iArrMult2563[3] ^ iArrMult2563[11]);
        iArr3[10] = iArr3[10] ^ (iArrMult2563[2] ^ iArrMult2563[10]);
        iArr3[9] = iArr3[9] ^ (iArrMult2563[1] ^ iArrMult2563[9]);
        iArr3[8] = iArr3[8] ^ (iArrMult2563[0] ^ iArrMult2563[8]);
        iArr3[7] = iArr3[7] ^ iArrMult2563[7];
        iArr3[6] = iArr3[6] ^ iArrMult2563[6];
        iArr3[5] = iArr3[5] ^ iArrMult2563[5];
        iArr3[4] = iArr3[4] ^ iArrMult2563[4];
        iArr3[3] = iArr3[3] ^ iArrMult2563[3];
        iArr3[2] = iArr3[2] ^ iArrMult2563[2];
        iArr3[1] = iArr3[1] ^ iArrMult2563[1];
        iArr3[0] = iArrMult2563[0] ^ iArr3[0];
        return iArr3;
    }

    public static int[] mult64(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[4];
        int i = iArr[0];
        int i2 = iArr.length > 1 ? iArr[1] : 0;
        int i3 = iArr2[0];
        int i4 = iArr2.length > 1 ? iArr2[1] : 0;
        if (i2 != 0 || i4 != 0) {
            int[] iArrMult32 = mult32(i2, i4);
            iArr3[3] = iArr3[3] ^ iArrMult32[1];
            iArr3[2] = iArr3[2] ^ (iArrMult32[0] ^ iArrMult32[1]);
            iArr3[1] = iArrMult32[0] ^ iArr3[1];
        }
        int[] iArrMult322 = mult32(i2 ^ i, i4 ^ i3);
        iArr3[2] = iArr3[2] ^ iArrMult322[1];
        iArr3[1] = iArrMult322[0] ^ iArr3[1];
        int[] iArrMult323 = mult32(i, i3);
        iArr3[2] = iArr3[2] ^ iArrMult323[1];
        iArr3[1] = iArr3[1] ^ (iArrMult323[0] ^ iArrMult323[1]);
        iArr3[0] = iArrMult323[0] ^ iArr3[0];
        return iArr3;
    }

    private GF2Polynomial upper(int i) {
        int iMin = Math.min(i, this.blocks - i);
        GF2Polynomial gF2Polynomial = new GF2Polynomial(iMin << 5);
        if (this.blocks >= i) {
            System.arraycopy(this.value, i, gF2Polynomial.value, 0, iMin);
        }
        return gF2Polynomial;
    }

    private void zeroUnusedBits() {
        int i = this.len;
        if ((i & 31) != 0) {
            int[] iArr = this.value;
            int i2 = this.blocks - 1;
            iArr[i2] = reverseRightMask[i & 31] & iArr[i2];
        }
    }

    public GF2Polynomial add(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void addToThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.len);
        xorThisBy(gF2Polynomial);
    }

    public void assignAll() {
        for (int i = 0; i < this.blocks; i++) {
            this.value[i] = -1;
        }
        zeroUnusedBits();
    }

    public void assignOne() {
        for (int i = 1; i < this.blocks; i++) {
            this.value[i] = 0;
        }
        this.value[0] = 1;
    }

    public void assignX() {
        for (int i = 1; i < this.blocks; i++) {
            this.value[i] = 0;
        }
        this.value[0] = 2;
    }

    public void assignZero() {
        for (int i = 0; i < this.blocks; i++) {
            this.value[i] = 0;
        }
    }

    public Object clone() {
        return new GF2Polynomial(this);
    }

    public GF2Polynomial[] divide(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[2];
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int i = gF2Polynomial3.len;
        int i2 = gF2Polynomial4.len;
        if (i < i2) {
            gF2PolynomialArr[0] = new GF2Polynomial(0);
            gF2PolynomialArr[1] = gF2Polynomial3;
            return gF2PolynomialArr;
        }
        int i3 = i - i2;
        gF2Polynomial2.expandN(i3 + 1);
        while (i3 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(i3));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(i3);
            i3 = gF2Polynomial3.len - gF2Polynomial4.len;
        }
        gF2PolynomialArr[0] = gF2Polynomial2;
        gF2PolynomialArr[1] = gF2Polynomial3;
        return gF2PolynomialArr;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2Polynomial)) {
            return false;
        }
        GF2Polynomial gF2Polynomial = (GF2Polynomial) obj;
        if (this.len != gF2Polynomial.len) {
            return false;
        }
        for (int i = 0; i < this.blocks; i++) {
            if (this.value[i] != gF2Polynomial.value[i]) {
                return false;
            }
        }
        return true;
    }

    public void expandN(int i) {
        if (this.len >= i) {
            return;
        }
        this.len = i;
        int i2 = ((i - 1) >>> 5) + 1;
        int i3 = this.blocks;
        if (i3 >= i2) {
            return;
        }
        int[] iArr = this.value;
        if (iArr.length >= i2) {
            while (i3 < i2) {
                this.value[i3] = 0;
                i3++;
            }
            this.blocks = i2;
            return;
        }
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i3);
        this.blocks = i2;
        this.value = null;
        this.value = iArr2;
    }

    public GF2Polynomial gcd(GF2Polynomial gF2Polynomial) throws RuntimeException {
        if (isZero() && gF2Polynomial.isZero()) {
            throw new ArithmeticException("Both operands of gcd equal zero.");
        }
        if (isZero()) {
            return new GF2Polynomial(gF2Polynomial);
        }
        if (gF2Polynomial.isZero()) {
            return new GF2Polynomial(this);
        }
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
        GF2Polynomial gF2PolynomialRemainder = gF2Polynomial3;
        while (!gF2PolynomialRemainder.isZero()) {
            GF2Polynomial gF2Polynomial5 = gF2PolynomialRemainder;
            gF2PolynomialRemainder = gF2Polynomial4.remainder(gF2PolynomialRemainder);
            gF2Polynomial4 = gF2Polynomial5;
        }
        return gF2Polynomial4;
    }

    public int getBit(int i) {
        if (i < 0) {
            throw new RuntimeException();
        }
        if (i > this.len - 1) {
            return 0;
        }
        return (bitMask[i & 31] & this.value[i >>> 5]) != 0 ? 1 : 0;
    }

    public int getLength() {
        return this.len;
    }

    public int hashCode() {
        return this.len + Arrays.hashCode(this.value);
    }

    public GF2Polynomial increase() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.increaseThis();
        return gF2Polynomial;
    }

    public void increaseThis() {
        xorBit(0);
    }

    public boolean isIrreducible() {
        if (isZero()) {
            return false;
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.reduceN();
        int i = gF2Polynomial.len;
        int i2 = i - 1;
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(i, "X");
        for (int i3 = 1; i3 <= (i2 >> 1); i3++) {
            gF2Polynomial2.squareThisPreCalc();
            gF2Polynomial2 = gF2Polynomial2.remainder(gF2Polynomial);
            GF2Polynomial gF2PolynomialAdd = gF2Polynomial2.add(new GF2Polynomial(32, "X"));
            if (gF2PolynomialAdd.isZero() || !gF2Polynomial.gcd(gF2PolynomialAdd).isOne()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOne() {
        for (int i = 1; i < this.blocks; i++) {
            if (this.value[i] != 0) {
                return false;
            }
        }
        return this.value[0] == 1;
    }

    public boolean isZero() {
        if (this.len == 0) {
            return true;
        }
        for (int i = 0; i < this.blocks; i++) {
            if (this.value[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public GF2Polynomial multiply(GF2Polynomial gF2Polynomial) {
        int iMax = Math.max(this.len, gF2Polynomial.len);
        expandN(iMax);
        gF2Polynomial.expandN(iMax);
        return karaMult(gF2Polynomial);
    }

    public GF2Polynomial multiplyClassic(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(Math.max(this.len, gF2Polynomial.len) << 1);
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[32];
        gF2PolynomialArr[0] = new GF2Polynomial(this);
        for (int i = 1; i <= 31; i++) {
            gF2PolynomialArr[i] = gF2PolynomialArr[i - 1].shiftLeft();
        }
        for (int i2 = 0; i2 < gF2Polynomial.blocks; i2++) {
            for (int i3 = 0; i3 <= 31; i3++) {
                if ((gF2Polynomial.value[i2] & bitMask[i3]) != 0) {
                    gF2Polynomial2.xorThisBy(gF2PolynomialArr[i3]);
                }
            }
            for (int i4 = 0; i4 <= 31; i4++) {
                gF2PolynomialArr[i4].shiftBlocksLeft();
            }
        }
        return gF2Polynomial2;
    }

    public GF2Polynomial quotient(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int i = gF2Polynomial3.len;
        int i2 = gF2Polynomial4.len;
        if (i < i2) {
            return new GF2Polynomial(0);
        }
        int i3 = i - i2;
        gF2Polynomial2.expandN(i3 + 1);
        while (i3 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(i3));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(i3);
            i3 = gF2Polynomial3.len - gF2Polynomial4.len;
        }
        return gF2Polynomial2;
    }

    public void randomize() {
        for (int i = 0; i < this.blocks; i++) {
            this.value[i] = rand.nextInt();
        }
        zeroUnusedBits();
    }

    public void randomize(Random random) {
        for (int i = 0; i < this.blocks; i++) {
            this.value[i] = random.nextInt();
        }
        zeroUnusedBits();
    }

    public void reduceN() {
        int i = this.blocks;
        do {
            i--;
            if (this.value[i] != 0) {
                break;
            }
        } while (i > 0);
        int i2 = this.value[i];
        int i3 = 0;
        while (i2 != 0) {
            i2 >>>= 1;
            i3++;
        }
        this.len = (i << 5) + i3;
        this.blocks = i + 1;
    }

    public void reducePentanomial(int i, int[] iArr) {
        GF2Polynomial gF2Polynomial = this;
        int i2 = i >>> 5;
        int i3 = i & 31;
        int i4 = 32 - i3;
        int i5 = (i - iArr[0]) >>> 5;
        int i6 = 32 - ((i - iArr[0]) & 31);
        int i7 = (i - iArr[1]) >>> 5;
        int i8 = 32 - ((i - iArr[1]) & 31);
        int i9 = (i - iArr[2]) >>> 5;
        int i10 = 32 - ((i - iArr[2]) & 31);
        int i11 = ((i << 1) - 2) >>> 5;
        while (i11 > i2) {
            int[] iArr2 = gF2Polynomial.value;
            long j = ((long) iArr2[i11]) & 4294967295L;
            int i12 = i11 - i2;
            int i13 = i12 - 1;
            int i14 = i2;
            int i15 = i3;
            iArr2[i13] = iArr2[i13] ^ ((int) (j << i4));
            iArr2[i12] = (int) (((long) iArr2[i12]) ^ (j >>> (32 - i4)));
            int i16 = i11 - i5;
            int i17 = i16 - 1;
            iArr2[i17] = iArr2[i17] ^ ((int) (j << i6));
            iArr2[i16] = (int) (((long) iArr2[i16]) ^ (j >>> (32 - i6)));
            int i18 = i11 - i7;
            int i19 = i18 - 1;
            iArr2[i19] = iArr2[i19] ^ ((int) (j << i8));
            iArr2[i18] = (int) (((long) iArr2[i18]) ^ (j >>> (32 - i8)));
            int i20 = i11 - i9;
            int i21 = i20 - 1;
            iArr2[i21] = iArr2[i21] ^ ((int) (j << i10));
            iArr2[i20] = (int) ((j >>> (32 - i10)) ^ ((long) iArr2[i20]));
            iArr2[i11] = 0;
            i11--;
            gF2Polynomial = this;
            i2 = i14;
            i3 = i15;
        }
        int i22 = i2;
        int i23 = i3;
        int[] iArr3 = gF2Polynomial.value;
        long j2 = ((long) iArr3[i22]) & 4294967295L & (4294967295 << i23);
        iArr3[0] = (int) ((j2 >>> (32 - i4)) ^ ((long) iArr3[0]));
        int i24 = i22 - i5;
        int i25 = i24 - 1;
        if (i25 >= 0) {
            iArr3[i25] = iArr3[i25] ^ ((int) (j2 << i6));
        }
        int[] iArr4 = this.value;
        iArr4[i24] = (int) (((long) iArr4[i24]) ^ (j2 >>> (32 - i6)));
        int i26 = i22 - i7;
        int i27 = i26 - 1;
        if (i27 >= 0) {
            iArr4[i27] = iArr4[i27] ^ ((int) (j2 << i8));
        }
        int[] iArr5 = this.value;
        iArr5[i26] = (int) (((long) iArr5[i26]) ^ (j2 >>> (32 - i8)));
        int i28 = i22 - i9;
        int i29 = i28 - 1;
        if (i29 >= 0) {
            iArr5[i29] = iArr5[i29] ^ ((int) (j2 << i10));
        }
        int[] iArr6 = this.value;
        iArr6[i28] = (int) ((j2 >>> (32 - i10)) ^ ((long) iArr6[i28]));
        iArr6[i22] = iArr6[i22] & reverseRightMask[i23];
        this.blocks = ((i - 1) >>> 5) + 1;
        this.len = i;
    }

    public void reduceTrinomial(int i, int i2) {
        int i3 = i >>> 5;
        int i4 = i & 31;
        int i5 = 32 - i4;
        int i6 = i - i2;
        int i7 = i6 >>> 5;
        int i8 = 32 - (i6 & 31);
        int i9 = ((i << 1) - 2) >>> 5;
        while (i9 > i3) {
            int[] iArr = this.value;
            long j = 4294967295L & ((long) iArr[i9]);
            int i10 = i9 - i3;
            int i11 = i10 - 1;
            int i12 = i3;
            iArr[i11] = iArr[i11] ^ ((int) (j << i5));
            iArr[i10] = (int) (((long) iArr[i10]) ^ (j >>> (32 - i5)));
            int i13 = i9 - i7;
            int i14 = i13 - 1;
            iArr[i14] = iArr[i14] ^ ((int) (j << i8));
            iArr[i13] = (int) ((j >>> (32 - i8)) ^ ((long) iArr[i13]));
            iArr[i9] = 0;
            i9--;
            i3 = i12;
        }
        int i15 = i3;
        int[] iArr2 = this.value;
        long j2 = (4294967295 << i4) & ((long) iArr2[i15]) & 4294967295L;
        iArr2[0] = (int) (((long) iArr2[0]) ^ (j2 >>> (32 - i5)));
        int i16 = i15 - i7;
        int i17 = i16 - 1;
        if (i17 >= 0) {
            iArr2[i17] = iArr2[i17] ^ ((int) (j2 << i8));
        }
        int[] iArr3 = this.value;
        iArr3[i16] = (int) ((j2 >>> (32 - i8)) ^ ((long) iArr3[i16]));
        iArr3[i15] = iArr3[i15] & reverseRightMask[i4];
        this.blocks = ((i - 1) >>> 5) + 1;
        this.len = i;
    }

    public GF2Polynomial remainder(GF2Polynomial gF2Polynomial) throws RuntimeException {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial3.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial2.reduceN();
        gF2Polynomial3.reduceN();
        int i = gF2Polynomial2.len;
        int i2 = gF2Polynomial3.len;
        if (i < i2) {
            return gF2Polynomial2;
        }
        while (true) {
            int i3 = i - i2;
            if (i3 < 0) {
                return gF2Polynomial2;
            }
            gF2Polynomial2.subtractFromThis(gF2Polynomial3.shiftLeft(i3));
            gF2Polynomial2.reduceN();
            i = gF2Polynomial2.len;
            i2 = gF2Polynomial3.len;
        }
    }

    public void resetBit(int i) throws RuntimeException {
        if (i < 0) {
            throw new RuntimeException();
        }
        if (i > this.len - 1) {
            return;
        }
        int[] iArr = this.value;
        int i2 = i >>> 5;
        iArr[i2] = (~bitMask[i & 31]) & iArr[i2];
    }

    public void setBit(int i) throws RuntimeException {
        if (i < 0 || i > this.len - 1) {
            throw new RuntimeException();
        }
        int[] iArr = this.value;
        int i2 = i >>> 5;
        iArr[i2] = bitMask[i & 31] | iArr[i2];
    }

    public void shiftBlocksLeft() {
        int i = this.blocks + 1;
        this.blocks = i;
        this.len += 32;
        int[] iArr = this.value;
        if (i > iArr.length) {
            int[] iArr2 = new int[i];
            System.arraycopy(iArr, 0, iArr2, 1, i - 1);
            this.value = null;
            this.value = iArr2;
            return;
        }
        for (int i2 = i - 1; i2 >= 1; i2--) {
            int[] iArr3 = this.value;
            iArr3[i2] = iArr3[i2 - 1];
        }
        this.value[0] = 0;
    }

    public GF2Polynomial shiftLeft() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len + 1, this.value);
        for (int i = gF2Polynomial.blocks - 1; i >= 1; i--) {
            int[] iArr = gF2Polynomial.value;
            iArr[i] = iArr[i] << 1;
            iArr[i] = iArr[i] | (iArr[i - 1] >>> 31);
        }
        int[] iArr2 = gF2Polynomial.value;
        iArr2[0] = iArr2[0] << 1;
        return gF2Polynomial;
    }

    public GF2Polynomial shiftLeft(int i) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len + i, this.value);
        if (i >= 32) {
            gF2Polynomial.doShiftBlocksLeft(i >>> 5);
        }
        int i2 = i & 31;
        if (i2 != 0) {
            for (int i3 = gF2Polynomial.blocks - 1; i3 >= 1; i3--) {
                int[] iArr = gF2Polynomial.value;
                iArr[i3] = iArr[i3] << i2;
                iArr[i3] = iArr[i3] | (iArr[i3 - 1] >>> (32 - i2));
            }
            int[] iArr2 = gF2Polynomial.value;
            iArr2[0] = iArr2[0] << i2;
        }
        return gF2Polynomial;
    }

    public void shiftLeftAddThis(GF2Polynomial gF2Polynomial, int i) {
        int i2;
        if (i == 0) {
            addToThis(gF2Polynomial);
            return;
        }
        expandN(gF2Polynomial.len + i);
        int i3 = i >>> 5;
        for (int i4 = gF2Polynomial.blocks - 1; i4 >= 0; i4--) {
            int i5 = i4 + i3;
            int i6 = i5 + 1;
            if (i6 < this.blocks && (i2 = i & 31) != 0) {
                int[] iArr = this.value;
                iArr[i6] = (gF2Polynomial.value[i4] >>> (32 - i2)) ^ iArr[i6];
            }
            int[] iArr2 = this.value;
            iArr2[i5] = iArr2[i5] ^ (gF2Polynomial.value[i4] << (i & 31));
        }
    }

    public void shiftLeftThis() {
        int i = this.len;
        int i2 = i & 31;
        this.len = i + 1;
        int i3 = this.blocks;
        if (i2 != 0) {
            for (int i4 = i3 - 1; i4 >= 1; i4--) {
                int[] iArr = this.value;
                iArr[i4] = iArr[i4] << 1;
                iArr[i4] = iArr[i4] | (iArr[i4 - 1] >>> 31);
            }
            int[] iArr2 = this.value;
            iArr2[0] = iArr2[0] << 1;
            return;
        }
        int i5 = i3 + 1;
        this.blocks = i5;
        int[] iArr3 = this.value;
        if (i5 > iArr3.length) {
            int[] iArr4 = new int[i5];
            System.arraycopy(iArr3, 0, iArr4, 0, iArr3.length);
            this.value = null;
            this.value = iArr4;
        }
        for (int i6 = this.blocks - 1; i6 >= 1; i6--) {
            int[] iArr5 = this.value;
            int i7 = i6 - 1;
            iArr5[i6] = iArr5[i6] | (iArr5[i7] >>> 31);
            iArr5[i7] = iArr5[i7] << 1;
        }
    }

    public GF2Polynomial shiftRight() {
        int i;
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len - 1);
        int i2 = 0;
        System.arraycopy(this.value, 0, gF2Polynomial.value, 0, gF2Polynomial.blocks);
        while (true) {
            i = gF2Polynomial.blocks;
            if (i2 > i - 2) {
                break;
            }
            int[] iArr = gF2Polynomial.value;
            iArr[i2] = iArr[i2] >>> 1;
            int i3 = i2 + 1;
            iArr[i2] = iArr[i2] | (iArr[i3] << 31);
            i2 = i3;
        }
        int[] iArr2 = gF2Polynomial.value;
        int i4 = i - 1;
        iArr2[i4] = iArr2[i4] >>> 1;
        if (i < this.blocks) {
            int i5 = i - 1;
            iArr2[i5] = (this.value[i] << 31) | iArr2[i5];
        }
        return gF2Polynomial;
    }

    public void shiftRightThis() {
        int i;
        int i2 = this.len - 1;
        this.len = i2;
        this.blocks = ((i2 - 1) >>> 5) + 1;
        int i3 = 0;
        while (true) {
            i = this.blocks;
            if (i3 > i - 2) {
                break;
            }
            int[] iArr = this.value;
            iArr[i3] = iArr[i3] >>> 1;
            int i4 = i3 + 1;
            iArr[i3] = iArr[i3] | (iArr[i4] << 31);
            i3 = i4;
        }
        int[] iArr2 = this.value;
        int i5 = i - 1;
        iArr2[i5] = iArr2[i5] >>> 1;
        if ((this.len & 31) == 0) {
            int i6 = i - 1;
            iArr2[i6] = (iArr2[i] << 31) | iArr2[i6];
        }
    }

    public void squareThisBitwise() {
        if (isZero()) {
            return;
        }
        int i = this.blocks;
        int i2 = i << 1;
        int[] iArr = new int[i2];
        for (int i3 = i - 1; i3 >= 0; i3--) {
            int i4 = this.value[i3];
            int i5 = 1;
            for (int i6 = 0; i6 < 16; i6++) {
                if ((i4 & 1) != 0) {
                    int i7 = i3 << 1;
                    iArr[i7] = iArr[i7] | i5;
                }
                if ((65536 & i4) != 0) {
                    int i8 = (i3 << 1) + 1;
                    iArr[i8] = iArr[i8] | i5;
                }
                i5 <<= 2;
                i4 >>>= 1;
            }
        }
        this.value = null;
        this.value = iArr;
        this.blocks = i2;
        this.len = (this.len << 1) - 1;
    }

    public void squareThisPreCalc() {
        int i;
        int i2;
        if (isZero()) {
            return;
        }
        int length = this.value.length;
        int i3 = this.blocks;
        if (length >= (i3 << 1)) {
            for (int i4 = i3 - 1; i4 >= 0; i4--) {
                int[] iArr = this.value;
                int i5 = i4 << 1;
                short[] sArr = squaringTable;
                iArr[i5 + 1] = sArr[(iArr[i4] & 16711680) >>> 16] | (sArr[(iArr[i4] & (-16777216)) >>> 24] << 16);
                iArr[i5] = sArr[iArr[i4] & 255] | (sArr[(iArr[i4] & 65280) >>> 8] << 16);
            }
            i2 = this.blocks << 1;
        } else {
            int[] iArr2 = new int[i3 << 1];
            int i6 = 0;
            while (true) {
                i = this.blocks;
                if (i6 >= i) {
                    break;
                }
                int i7 = i6 << 1;
                short[] sArr2 = squaringTable;
                int[] iArr3 = this.value;
                iArr2[i7] = sArr2[iArr3[i6] & 255] | (sArr2[(iArr3[i6] & 65280) >>> 8] << 16);
                iArr2[i7 + 1] = (sArr2[(iArr3[i6] & (-16777216)) >>> 24] << 16) | sArr2[(iArr3[i6] & 16711680) >>> 16];
                i6++;
            }
            this.value = null;
            this.value = iArr2;
            i2 = i << 1;
        }
        this.blocks = i2;
        this.len = (this.len << 1) - 1;
    }

    public GF2Polynomial subtract(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void subtractFromThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.len);
        xorThisBy(gF2Polynomial);
    }

    public boolean testBit(int i) {
        if (i < 0) {
            throw new RuntimeException();
        }
        if (i > this.len - 1) {
            return false;
        }
        return (bitMask[i & 31] & this.value[i >>> 5]) != 0;
    }

    public byte[] toByteArray() {
        int i = ((this.len - 1) >> 3) + 1;
        int i2 = i & 3;
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < (i >> 2); i3++) {
            int i4 = (i - (i3 << 2)) - 1;
            int[] iArr = this.value;
            bArr[i4] = (byte) (255 & iArr[i3]);
            bArr[i4 - 1] = (byte) ((iArr[i3] & 65280) >>> 8);
            bArr[i4 - 2] = (byte) ((iArr[i3] & 16711680) >>> 16);
            bArr[i4 - 3] = (byte) ((iArr[i3] & (-16777216)) >>> 24);
        }
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i2 - i5) - 1) << 3;
            bArr[i5] = (byte) ((this.value[this.blocks - 1] & (255 << i6)) >>> i6);
        }
        return bArr;
    }

    public BigInteger toFlexiBigInt() {
        return (this.len == 0 || isZero()) ? new BigInteger(0, new byte[0]) : new BigInteger(1, toByteArray());
    }

    public int[] toIntegerArray() {
        int i = this.blocks;
        int[] iArr = new int[i];
        System.arraycopy(this.value, 0, iArr, 0, i);
        return iArr;
    }

    public String toString(int i) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f'};
        String[] strArr = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", Constants.DEFAULT_UIN, "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        String str = new String();
        if (i == 16) {
            for (int i2 = this.blocks - 1; i2 >= 0; i2--) {
                str = ((((((((str + cArr[(this.value[i2] >>> 28) & 15]) + cArr[(this.value[i2] >>> 24) & 15]) + cArr[(this.value[i2] >>> 20) & 15]) + cArr[(this.value[i2] >>> 16) & 15]) + cArr[(this.value[i2] >>> 12) & 15]) + cArr[(this.value[i2] >>> 8) & 15]) + cArr[(this.value[i2] >>> 4) & 15]) + cArr[this.value[i2] & 15]) + Operators.SPACE_STR;
            }
        } else {
            for (int i3 = this.blocks - 1; i3 >= 0; i3--) {
                str = ((((((((str + strArr[(this.value[i3] >>> 28) & 15]) + strArr[(this.value[i3] >>> 24) & 15]) + strArr[(this.value[i3] >>> 20) & 15]) + strArr[(this.value[i3] >>> 16) & 15]) + strArr[(this.value[i3] >>> 12) & 15]) + strArr[(this.value[i3] >>> 8) & 15]) + strArr[(this.value[i3] >>> 4) & 15]) + strArr[this.value[i3] & 15]) + Operators.SPACE_STR;
            }
        }
        return str;
    }

    public boolean vectorMult(GF2Polynomial gF2Polynomial) throws RuntimeException {
        if (this.len != gF2Polynomial.len) {
            throw new RuntimeException();
        }
        boolean z = false;
        for (int i = 0; i < this.blocks; i++) {
            int i2 = this.value[i] & gF2Polynomial.value[i];
            boolean[] zArr = parity;
            z = (((z ^ zArr[i2 & 255]) ^ zArr[(i2 >>> 8) & 255]) ^ zArr[(i2 >>> 16) & 255]) ^ zArr[(i2 >>> 24) & 255];
        }
        return z;
    }

    public GF2Polynomial xor(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2;
        int iMin = Math.min(this.blocks, gF2Polynomial.blocks);
        int i = 0;
        if (this.len >= gF2Polynomial.len) {
            gF2Polynomial2 = new GF2Polynomial(this);
            while (i < iMin) {
                int[] iArr = gF2Polynomial2.value;
                iArr[i] = iArr[i] ^ gF2Polynomial.value[i];
                i++;
            }
        } else {
            gF2Polynomial2 = new GF2Polynomial(gF2Polynomial);
            while (i < iMin) {
                int[] iArr2 = gF2Polynomial2.value;
                iArr2[i] = iArr2[i] ^ this.value[i];
                i++;
            }
        }
        gF2Polynomial2.zeroUnusedBits();
        return gF2Polynomial2;
    }

    public void xorBit(int i) throws RuntimeException {
        if (i < 0 || i > this.len - 1) {
            throw new RuntimeException();
        }
        int[] iArr = this.value;
        int i2 = i >>> 5;
        iArr[i2] = bitMask[i & 31] ^ iArr[i2];
    }

    public void xorThisBy(GF2Polynomial gF2Polynomial) {
        for (int i = 0; i < Math.min(this.blocks, gF2Polynomial.blocks); i++) {
            int[] iArr = this.value;
            iArr[i] = iArr[i] ^ gF2Polynomial.value[i];
        }
        zeroUnusedBits();
    }
}
