package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.igexin.c.a.d.g;
import com.taobao.weex.wson.Wson;
import com.tencent.rtmp.TXLivePusher;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public class ARIAEngine implements BlockCipher {
    public static final int BLOCK_SIZE = 16;
    public static final byte[][] C = {Hex.decodeStrict("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decodeStrict("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decodeStrict("db92371d2126e9700324977504e8c90e")};
    public static final byte[] SB1_sbox = {99, 124, 119, Wson.MAP_TYPE, TXLivePusher.SEI_MSG_TYPE, 107, KeyFactorySpi.x448_type, -59, 48, 1, Wson.NUMBER_BIG_INTEGER_TYPE, 43, -2, -41, -85, 118, -54, co.h, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, KeyFactorySpi.Ed448_type, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, SharedPreferencesNewImpl.FINISH_MARK, g.n, -30, -21, 39, -78, 117, 9, -125, 44, JSONLexer.EOI, 27, KeyFactorySpi.x25519_type, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, Wson.ARRAY_TYPE, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, UTF8.S_P4A, 60, -97, -88, 81, -93, UTF8.S_P3B, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, PublicSuffixDatabase.EXCEPTION_MARKER, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, Wson.NUMBER_DOUBLE_TYPE, 93, 25, Wson.STRING_TYPE, UTF8.S_P4B, -127, 79, -36, 34, 42, -112, -120, Wson.NUMBER_FLOAT_TYPE, -18, -72, 20, -34, 94, 11, -37, co.k, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, Wson.NUMBER_LONG_TYPE, 86, -12, -22, Wson.NUMBER_BIG_DECIMAL_TYPE, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, Wson.BOOLEAN_TYPE_TRUE, co.j, 75, -67, -117, -118, KeyFactorySpi.Ed25519_type, 62, -75, Wson.BOOLEAN_TYPE_FALSE, 72, 3, -10, db.l, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, Wson.NUMBER_INT_TYPE, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    public static final byte[] SB2_sbox = {-30, 78, 84, -4, -108, -62, 74, -52, 98, 13, 106, Wson.NUMBER_FLOAT_TYPE, 60, 77, -117, -47, 94, -6, Wson.NUMBER_DOUBLE_TYPE, -53, -76, -105, -66, 43, PSSSigner.TRAILER_IMPLICIT, 119, 46, 3, -45, 25, 89, -63, 29, 6, 65, 107, 85, -16, -103, Wson.NUMBER_INT_TYPE, -22, -100, 24, -82, 99, -33, -25, -69, 0, Wson.STRING_TYPE, Wson.BOOLEAN_TYPE_FALSE, -5, -106, 76, -123, -28, 58, 9, 69, -86, 15, -18, 16, -21, 45, 127, -12, 41, -84, -49, -83, -111, -115, 120, -56, -107, -7, 47, -50, -51, 8, 122, -120, 56, 92, -125, 42, 40, 71, -37, -72, -57, -109, -92, SharedPreferencesNewImpl.FINISH_MARK, 83, -1, -121, db.l, 49, 54, PublicSuffixDatabase.EXCEPTION_MARKER, 88, 72, 1, -114, 55, Wson.BOOLEAN_TYPE_TRUE, 50, -54, -23, -79, -73, -85, 12, -41, -60, 86, 66, 38, 7, -104, UTF8.S_P4B, -39, -74, -71, 17, UTF8.S_P3B, -20, 32, -116, -67, -96, -55, -124, 4, 73, 35, -15, 79, UTF8.S_P4A, co.j, 19, -36, -40, -64, -98, 87, -29, -61, Wson.MAP_TYPE, Wson.NUMBER_BIG_DECIMAL_TYPE, 59, 2, -113, 62, -24, 37, -110, -27, 21, -35, -3, 23, -87, -65, -44, -102, 126, -59, 57, Wson.NUMBER_BIG_INTEGER_TYPE, -2, 118, -99, 67, -89, -31, -48, -11, 104, TXLivePusher.SEI_MSG_TYPE, 27, 52, KeyFactorySpi.Ed25519_type, 5, -93, -118, -43, 121, -122, -88, 48, -58, 81, 75, 30, -90, 39, -10, 53, -46, KeyFactorySpi.x25519_type, 36, 22, co.h, 95, -38, -26, 117, -94, -17, 44, -78, 28, -97, 93, KeyFactorySpi.x448_type, g.n, 10, 114, 68, -101, Wson.NUMBER_LONG_TYPE, -112, 11, Wson.ARRAY_TYPE, 51, 125, 90, 82, -13, 97, -95, -9, -80, -42, 63, 124, 109, -19, 20, co.k, -91, 61, 34, -77, -8, -119, -34, KeyFactorySpi.Ed448_type, JSONLexer.EOI, -81, -70, -75, -127};
    public static final byte[] SB3_sbox = {82, 9, 106, -43, 48, 54, -91, 56, -65, UTF8.S_P3B, -93, -98, -127, -13, -41, -5, 124, -29, 57, co.h, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, Wson.MAP_TYPE, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, Wson.BOOLEAN_TYPE_FALSE, 40, -39, 36, -78, 118, Wson.ARRAY_TYPE, -94, 73, 109, -117, -47, 37, 114, -8, -10, Wson.NUMBER_DOUBLE_TYPE, -122, 104, -104, 22, -44, -92, 92, -52, 93, Wson.NUMBER_BIG_DECIMAL_TYPE, -74, -110, Wson.NUMBER_LONG_TYPE, KeyFactorySpi.Ed25519_type, 72, UTF8.S_P4A, -3, -19, -71, -38, 94, 21, Wson.NUMBER_FLOAT_TYPE, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, Wson.NUMBER_BIG_INTEGER_TYPE, -36, -22, -105, TXLivePusher.SEI_MSG_TYPE, -49, -50, -16, -76, -26, Wson.STRING_TYPE, -106, -84, Wson.BOOLEAN_TYPE_TRUE, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, KeyFactorySpi.x25519_type, 71, -15, JSONLexer.EOI, KeyFactorySpi.Ed448_type, 29, 41, -59, -119, KeyFactorySpi.x448_type, -73, 98, db.l, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, co.j, -35, -88, 51, -120, 7, -57, 49, -79, SharedPreferencesNewImpl.FINISH_MARK, 16, 89, 39, g.n, -20, 95, UTF8.S_P4B, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, co.k, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, Wson.NUMBER_INT_TYPE, 20, 99, 85, PublicSuffixDatabase.EXCEPTION_MARKER, 12, 125};
    public static final byte[] SB4_sbox = {48, 104, -103, 27, -121, -71, PublicSuffixDatabase.EXCEPTION_MARKER, 120, UTF8.S_P4A, 57, -37, -31, 114, 9, 98, 60, 62, 126, 94, -114, -15, -96, -52, -93, 42, 29, -5, -74, -42, 32, -60, -115, -127, Wson.NUMBER_BIG_DECIMAL_TYPE, -11, -119, -53, -99, 119, -58, 87, 67, 86, 23, -44, UTF8.S_P3B, JSONLexer.EOI, 77, -64, 99, Wson.NUMBER_LONG_TYPE, -29, -73, -56, Wson.NUMBER_DOUBLE_TYPE, 106, 83, -86, 56, -104, 12, -12, -101, -19, 127, 34, 118, -81, -35, 58, 11, 88, Wson.NUMBER_BIG_INTEGER_TYPE, -120, 6, -61, 53, 13, 1, -117, -116, -62, -26, 95, 2, 36, 117, -109, Wson.BOOLEAN_TYPE_FALSE, 30, -27, -30, 84, -40, 16, -50, 122, -24, 8, 44, SharedPreferencesNewImpl.FINISH_MARK, -105, 50, -85, -76, 39, 10, 35, -33, -17, -54, -39, -72, -6, -36, 49, 107, -47, -83, 25, 73, -67, 81, -106, -18, -28, -88, 65, -38, -1, -51, 85, -122, 54, -66, 97, 82, -8, -69, db.l, co.h, 72, Wson.NUMBER_INT_TYPE, -102, co.k, 71, -98, 92, 4, 75, 52, 21, 121, 38, -89, -34, 41, -82, -110, -41, -124, -23, -46, -70, 93, -13, -59, -80, -65, -92, 59, KeyFactorySpi.Ed448_type, 68, Wson.NUMBER_FLOAT_TYPE, 43, -4, -21, KeyFactorySpi.x448_type, -43, -10, 20, -2, 124, KeyFactorySpi.Ed25519_type, 90, 125, -3, 47, 24, -125, 22, -91, -111, co.j, 5, -107, Wson.BOOLEAN_TYPE_TRUE, -87, -63, Wson.ARRAY_TYPE, 74, -123, 109, 19, 7, 79, 78, 69, -78, 15, -55, 28, -90, PSSSigner.TRAILER_IMPLICIT, -20, Wson.STRING_TYPE, -112, Wson.MAP_TYPE, -49, 89, -113, -95, -7, 45, TXLivePusher.SEI_MSG_TYPE, -79, 0, -108, 55, -97, -48, 46, -100, KeyFactorySpi.x25519_type, 40, 63, g.n, -16, 61, -45, 37, -118, -75, -25, 66, -77, -57, -22, -9, 76, 17, 51, 3, -94, -84, UTF8.S_P4B};
    public byte[][] roundKeys;

    public static void A(byte[] bArr) {
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        byte b6 = bArr[5];
        byte b7 = bArr[6];
        byte b8 = bArr[7];
        byte b9 = bArr[8];
        byte b10 = bArr[9];
        byte b11 = bArr[10];
        byte b12 = bArr[11];
        byte b13 = bArr[12];
        byte b14 = bArr[13];
        byte b15 = bArr[14];
        byte b16 = bArr[15];
        bArr[0] = (byte) ((((((b4 ^ b5) ^ b7) ^ b9) ^ b10) ^ b14) ^ b15);
        bArr[1] = (byte) ((((((b3 ^ b6) ^ b8) ^ b9) ^ b10) ^ b13) ^ b16);
        bArr[2] = (byte) ((((((b2 ^ b5) ^ b7) ^ b11) ^ b12) ^ b13) ^ b16);
        bArr[3] = (byte) ((((((b ^ b6) ^ b8) ^ b11) ^ b12) ^ b14) ^ b15);
        int i = b ^ b3;
        bArr[4] = (byte) (((((i ^ b6) ^ b9) ^ b12) ^ b15) ^ b16);
        int i2 = b2 ^ b4;
        bArr[5] = (byte) (((((i2 ^ b5) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[6] = (byte) (((((i ^ b8) ^ b10) ^ b11) ^ b13) ^ b14);
        bArr[7] = (byte) (((((i2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b14);
        int i3 = b ^ b2;
        bArr[8] = (byte) (((((i3 ^ b5) ^ b8) ^ b11) ^ b14) ^ b16);
        bArr[9] = (byte) (((((i3 ^ b6) ^ b7) ^ b12) ^ b13) ^ b15);
        int i4 = b3 ^ b4;
        bArr[10] = (byte) (((((i4 ^ b6) ^ b7) ^ b9) ^ b14) ^ b16);
        bArr[11] = (byte) (((((i4 ^ b5) ^ b8) ^ b10) ^ b13) ^ b15);
        int i5 = b2 ^ b3;
        bArr[12] = (byte) (((((i5 ^ b7) ^ b8) ^ b10) ^ b12) ^ b13);
        int i6 = b ^ b4;
        bArr[13] = (byte) (((((i6 ^ b7) ^ b8) ^ b9) ^ b11) ^ b14);
        bArr[14] = (byte) (((((i6 ^ b5) ^ b6) ^ b10) ^ b12) ^ b15);
        bArr[15] = (byte) (((((i5 ^ b5) ^ b6) ^ b9) ^ b11) ^ b16);
    }

    public static void FE(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL2(bArr);
        A(bArr);
    }

    public static void FO(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL1(bArr);
        A(bArr);
    }

    public static byte SB1(byte b) {
        return SB1_sbox[b & 255];
    }

    public static byte SB2(byte b) {
        return SB2_sbox[b & 255];
    }

    public static byte SB3(byte b) {
        return SB3_sbox[b & 255];
    }

    public static byte SB4(byte b) {
        return SB4_sbox[b & 255];
    }

    public static void SL1(byte[] bArr) {
        bArr[0] = SB1(bArr[0]);
        bArr[1] = SB2(bArr[1]);
        bArr[2] = SB3(bArr[2]);
        bArr[3] = SB4(bArr[3]);
        bArr[4] = SB1(bArr[4]);
        bArr[5] = SB2(bArr[5]);
        bArr[6] = SB3(bArr[6]);
        bArr[7] = SB4(bArr[7]);
        bArr[8] = SB1(bArr[8]);
        bArr[9] = SB2(bArr[9]);
        bArr[10] = SB3(bArr[10]);
        bArr[11] = SB4(bArr[11]);
        bArr[12] = SB1(bArr[12]);
        bArr[13] = SB2(bArr[13]);
        bArr[14] = SB3(bArr[14]);
        bArr[15] = SB4(bArr[15]);
    }

    public static void SL2(byte[] bArr) {
        bArr[0] = SB3(bArr[0]);
        bArr[1] = SB4(bArr[1]);
        bArr[2] = SB1(bArr[2]);
        bArr[3] = SB2(bArr[3]);
        bArr[4] = SB3(bArr[4]);
        bArr[5] = SB4(bArr[5]);
        bArr[6] = SB1(bArr[6]);
        bArr[7] = SB2(bArr[7]);
        bArr[8] = SB3(bArr[8]);
        bArr[9] = SB4(bArr[9]);
        bArr[10] = SB1(bArr[10]);
        bArr[11] = SB2(bArr[11]);
        bArr[12] = SB3(bArr[12]);
        bArr[13] = SB4(bArr[13]);
        bArr[14] = SB1(bArr[14]);
        bArr[15] = SB2(bArr[15]);
    }

    public static byte[][] keySchedule(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = (length >>> 3) - 2;
        byte[][] bArr2 = C;
        byte[] bArr3 = bArr2[i];
        byte[] bArr4 = bArr2[(i + 1) % 3];
        byte[] bArr5 = bArr2[(i + 2) % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        FO(bArr9, bArr3);
        xor(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        FE(bArr10, bArr4);
        xor(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        FO(bArr11, bArr5);
        xor(bArr11, bArr9);
        int i2 = (i * 2) + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance((Class<?>) byte.class, i2 + 1, 16);
        keyScheduleRound(bArr12[0], bArr8, bArr9, 19);
        keyScheduleRound(bArr12[1], bArr9, bArr10, 19);
        keyScheduleRound(bArr12[2], bArr10, bArr11, 19);
        keyScheduleRound(bArr12[3], bArr11, bArr8, 19);
        keyScheduleRound(bArr12[4], bArr8, bArr9, 31);
        keyScheduleRound(bArr12[5], bArr9, bArr10, 31);
        keyScheduleRound(bArr12[6], bArr10, bArr11, 31);
        keyScheduleRound(bArr12[7], bArr11, bArr8, 31);
        keyScheduleRound(bArr12[8], bArr8, bArr9, 67);
        keyScheduleRound(bArr12[9], bArr9, bArr10, 67);
        keyScheduleRound(bArr12[10], bArr10, bArr11, 67);
        keyScheduleRound(bArr12[11], bArr11, bArr8, 67);
        keyScheduleRound(bArr12[12], bArr8, bArr9, 97);
        if (i2 > 12) {
            keyScheduleRound(bArr12[13], bArr9, bArr10, 97);
            keyScheduleRound(bArr12[14], bArr10, bArr11, 97);
            if (i2 > 14) {
                keyScheduleRound(bArr12[15], bArr11, bArr8, 97);
                keyScheduleRound(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            reverseKeys(bArr12);
            for (int i3 = 1; i3 < i2; i3++) {
                A(bArr12[i3]);
            }
        }
        return bArr12;
    }

    public static void keyScheduleRound(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        int i4 = 8 - i3;
        int i5 = bArr3[15 - i2] & 255;
        int i6 = 0;
        while (i6 < 16) {
            int i7 = bArr3[(i6 - i2) & 15] & 255;
            bArr[i6] = (byte) (((i5 << i4) | (i7 >>> i3)) ^ (bArr2[i6] & 255));
            i6++;
            i5 = i7;
        }
    }

    public static void reverseKeys(byte[][] bArr) {
        int length = bArr.length;
        int i = length / 2;
        int i2 = length - 1;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2 - i3;
            bArr[i3] = bArr[i4];
            bArr[i4] = bArr2;
        }
    }

    public static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.roundKeys = keySchedule(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException, DataLengthException {
        if (this.roundKeys == null) {
            throw new IllegalStateException("ARIA engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, i, bArr3, 0, 16);
        int length = this.roundKeys.length - 3;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            FO(bArr3, this.roundKeys[i3]);
            FE(bArr3, this.roundKeys[i4]);
            i3 = i4 + 1;
        }
        int i5 = i3 + 1;
        FO(bArr3, this.roundKeys[i3]);
        xor(bArr3, this.roundKeys[i5]);
        SL2(bArr3);
        xor(bArr3, this.roundKeys[i5 + 1]);
        System.arraycopy(bArr3, 0, bArr2, i2, 16);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
