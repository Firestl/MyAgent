package org.bouncycastle.crypto.digests;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.igexin.c.a.d.g;
import com.taobao.weex.wson.Wson;
import com.tencent.rtmp.TXLivePusher;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public abstract class HarakaBase implements Digest {
    public static final int DIGEST_SIZE = 32;
    public static final byte[][] S = {new byte[]{99, 124, 119, Wson.MAP_TYPE, TXLivePusher.SEI_MSG_TYPE, 107, KeyFactorySpi.x448_type, -59, 48, 1, Wson.NUMBER_BIG_INTEGER_TYPE, 43, -2, -41, -85, 118}, new byte[]{-54, co.h, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64}, new byte[]{-73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, KeyFactorySpi.Ed448_type, -40, 49, 21}, new byte[]{4, -57, 35, -61, 24, -106, 5, -102, 7, SharedPreferencesNewImpl.FINISH_MARK, g.n, -30, -21, 39, -78, 117}, new byte[]{9, -125, 44, JSONLexer.EOI, 27, KeyFactorySpi.x25519_type, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124}, new byte[]{83, -47, 0, -19, 32, -4, -79, Wson.ARRAY_TYPE, 106, -53, -66, 57, 74, 76, 88, -49}, new byte[]{-48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, UTF8.S_P4A, 60, -97, -88}, new byte[]{81, -93, UTF8.S_P3B, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, PublicSuffixDatabase.EXCEPTION_MARKER, 16, -1, -13, -46}, new byte[]{-51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, Wson.NUMBER_DOUBLE_TYPE, 93, 25, Wson.STRING_TYPE}, new byte[]{UTF8.S_P4B, -127, 79, -36, 34, 42, -112, -120, Wson.NUMBER_FLOAT_TYPE, -18, -72, 20, -34, 94, 11, -37}, new byte[]{co.k, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121}, new byte[]{-25, -56, 55, 109, -115, -43, 78, -87, Wson.NUMBER_LONG_TYPE, 86, -12, -22, Wson.NUMBER_BIG_DECIMAL_TYPE, 122, -82, 8}, new byte[]{-70, 120, 37, 46, 28, -90, -76, -58, -24, -35, Wson.BOOLEAN_TYPE_TRUE, co.j, 75, -67, -117, -118}, new byte[]{KeyFactorySpi.Ed25519_type, 62, -75, Wson.BOOLEAN_TYPE_FALSE, 72, 3, -10, db.l, 97, 53, 87, -71, -122, -63, 29, -98}, new byte[]{-31, -8, -104, 17, Wson.NUMBER_INT_TYPE, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33}, new byte[]{-116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22}};

    public static byte[] aesEnc(byte[] bArr, byte[] bArr2) {
        byte[] bArrMixColumns = mixColumns(shiftRows(subBytes(bArr)));
        xorReverse(bArrMixColumns, bArr2);
        return bArrMixColumns;
    }

    public static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i + 1;
            int i4 = i2 * 4;
            int i5 = i4 + 1;
            int i6 = i4 + 2;
            int i7 = i4 + 3;
            bArr2[i] = (byte) ((((xTime(bArr[i4]) ^ xTime(bArr[i5])) ^ bArr[i5]) ^ bArr[i6]) ^ bArr[i7]);
            int i8 = i3 + 1;
            bArr2[i3] = (byte) ((((bArr[i4] ^ xTime(bArr[i5])) ^ xTime(bArr[i6])) ^ bArr[i6]) ^ bArr[i7]);
            int i9 = i8 + 1;
            bArr2[i8] = (byte) ((((bArr[i4] ^ bArr[i5]) ^ xTime(bArr[i6])) ^ xTime(bArr[i7])) ^ bArr[i7]);
            i = i9 + 1;
            bArr2[i9] = (byte) ((((bArr[i4] ^ xTime(bArr[i4])) ^ bArr[i5]) ^ bArr[i6]) ^ xTime(bArr[i7]));
        }
        return bArr2;
    }

    public static byte sBox(byte b) {
        return S[(b & 255) >>> 4][b & 15];
    }

    public static byte[] shiftRows(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    public static byte[] subBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = sBox(bArr[0]);
        bArr2[1] = sBox(bArr[1]);
        bArr2[2] = sBox(bArr[2]);
        bArr2[3] = sBox(bArr[3]);
        bArr2[4] = sBox(bArr[4]);
        bArr2[5] = sBox(bArr[5]);
        bArr2[6] = sBox(bArr[6]);
        bArr2[7] = sBox(bArr[7]);
        bArr2[8] = sBox(bArr[8]);
        bArr2[9] = sBox(bArr[9]);
        bArr2[10] = sBox(bArr[10]);
        bArr2[11] = sBox(bArr[11]);
        bArr2[12] = sBox(bArr[12]);
        bArr2[13] = sBox(bArr[13]);
        bArr2[14] = sBox(bArr[14]);
        bArr2[15] = sBox(bArr[15]);
        return bArr2;
    }

    public static byte xTime(byte b) {
        int i = b >>> 7;
        int i2 = b << 1;
        if (i > 0) {
            i2 ^= 27;
        }
        return (byte) (i2 & 255);
    }

    public static byte[] xor(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[16];
        int i2 = 0;
        while (i2 < 16) {
            bArr3[i2] = (byte) (bArr2[i] ^ bArr[i2]);
            i2++;
            i++;
        }
        return bArr3;
    }

    public static void xorReverse(byte[] bArr, byte[] bArr2) {
        bArr[0] = (byte) (bArr[0] ^ bArr2[15]);
        bArr[1] = (byte) (bArr[1] ^ bArr2[14]);
        bArr[2] = (byte) (bArr[2] ^ bArr2[13]);
        bArr[3] = (byte) (bArr[3] ^ bArr2[12]);
        bArr[4] = (byte) (bArr[4] ^ bArr2[11]);
        bArr[5] = (byte) (bArr[5] ^ bArr2[10]);
        bArr[6] = (byte) (bArr[6] ^ bArr2[9]);
        bArr[7] = (byte) (bArr[7] ^ bArr2[8]);
        bArr[8] = (byte) (bArr2[7] ^ bArr[8]);
        bArr[9] = (byte) (bArr2[6] ^ bArr[9]);
        bArr[10] = (byte) (bArr2[5] ^ bArr[10]);
        bArr[11] = (byte) (bArr2[4] ^ bArr[11]);
        bArr[12] = (byte) (bArr2[3] ^ bArr[12]);
        bArr[13] = (byte) (bArr2[2] ^ bArr[13]);
        bArr[14] = (byte) (bArr2[1] ^ bArr[14]);
        bArr[15] = (byte) (bArr2[0] ^ bArr[15]);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }
}
