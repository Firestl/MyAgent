package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.facebook.imageutils.JfifUtil;
import com.igexin.c.a.d.g;
import com.igexin.push.config.c;
import com.taobao.weex.wson.Wson;
import com.tencent.ijk.media.player.IjkMediaPlayer;
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
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public class AESLightEngine implements BlockCipher {
    public static final int BLOCK_SIZE = 16;
    public static final int m1 = -2139062144;
    public static final int m2 = 2139062143;
    public static final int m3 = 27;
    public static final int m4 = -1061109568;
    public static final int m5 = 1061109567;
    public int C0;
    public int C1;
    public int C2;
    public int C3;
    public int ROUNDS;
    public int[][] WorkingKey = null;
    public boolean forEncryption;
    public static final byte[] S = {99, 124, 119, Wson.MAP_TYPE, TXLivePusher.SEI_MSG_TYPE, 107, KeyFactorySpi.x448_type, -59, 48, 1, Wson.NUMBER_BIG_INTEGER_TYPE, 43, -2, -41, -85, 118, -54, co.h, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, KeyFactorySpi.Ed448_type, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, SharedPreferencesNewImpl.FINISH_MARK, g.n, -30, -21, 39, -78, 117, 9, -125, 44, JSONLexer.EOI, 27, KeyFactorySpi.x25519_type, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, Wson.ARRAY_TYPE, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, UTF8.S_P4A, 60, -97, -88, 81, -93, UTF8.S_P3B, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, PublicSuffixDatabase.EXCEPTION_MARKER, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, Wson.NUMBER_DOUBLE_TYPE, 93, 25, Wson.STRING_TYPE, UTF8.S_P4B, -127, 79, -36, 34, 42, -112, -120, Wson.NUMBER_FLOAT_TYPE, -18, -72, 20, -34, 94, 11, -37, co.k, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, Wson.NUMBER_LONG_TYPE, 86, -12, -22, Wson.NUMBER_BIG_DECIMAL_TYPE, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, Wson.BOOLEAN_TYPE_TRUE, co.j, 75, -67, -117, -118, KeyFactorySpi.Ed25519_type, 62, -75, Wson.BOOLEAN_TYPE_FALSE, 72, 3, -10, db.l, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, Wson.NUMBER_INT_TYPE, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    public static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, UTF8.S_P3B, -93, -98, -127, -13, -41, -5, 124, -29, 57, co.h, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, Wson.MAP_TYPE, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, Wson.BOOLEAN_TYPE_FALSE, 40, -39, 36, -78, 118, Wson.ARRAY_TYPE, -94, 73, 109, -117, -47, 37, 114, -8, -10, Wson.NUMBER_DOUBLE_TYPE, -122, 104, -104, 22, -44, -92, 92, -52, 93, Wson.NUMBER_BIG_DECIMAL_TYPE, -74, -110, Wson.NUMBER_LONG_TYPE, KeyFactorySpi.Ed25519_type, 72, UTF8.S_P4A, -3, -19, -71, -38, 94, 21, Wson.NUMBER_FLOAT_TYPE, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, Wson.NUMBER_BIG_INTEGER_TYPE, -36, -22, -105, TXLivePusher.SEI_MSG_TYPE, -49, -50, -16, -76, -26, Wson.STRING_TYPE, -106, -84, Wson.BOOLEAN_TYPE_TRUE, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, KeyFactorySpi.x25519_type, 71, -15, JSONLexer.EOI, KeyFactorySpi.Ed448_type, 29, 41, -59, -119, KeyFactorySpi.x448_type, -73, 98, db.l, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, co.j, -35, -88, 51, -120, 7, -57, 49, -79, SharedPreferencesNewImpl.FINISH_MARK, 16, 89, 39, g.n, -20, 95, UTF8.S_P4B, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, co.k, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, Wson.NUMBER_INT_TYPE, 20, 99, 85, PublicSuffixDatabase.EXCEPTION_MARKER, 12, 125};
    public static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 179, 125, 250, 239, 197, c.G};

    public static int FFmulX(int i) {
        return (((i & (-2139062144)) >>> 7) * 27) ^ ((2139062143 & i) << 1);
    }

    public static int FFmulX2(int i) {
        int i2 = (1061109567 & i) << 2;
        int i3 = i & (-1061109568);
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.C0;
        int i2 = this.ROUNDS;
        int i3 = i ^ iArr[i2][0];
        int i4 = this.C1 ^ iArr[i2][1];
        int i5 = this.C2 ^ iArr[i2][2];
        int i6 = i2 - 1;
        int i7 = iArr[i2][3] ^ this.C3;
        while (true) {
            byte[] bArr = Si;
            int i8 = i3 & 255;
            if (i6 <= 1) {
                int iInv_mcol = inv_mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
                byte[] bArr2 = Si;
                int iInv_mcol2 = inv_mcol((bArr2[(i5 >> 24) & 255] << 24) ^ (((bArr2[i4 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
                byte[] bArr3 = Si;
                int iInv_mcol3 = inv_mcol((bArr3[(i7 >> 24) & 255] << 24) ^ (((bArr3[i5 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
                byte[] bArr4 = Si;
                int iInv_mcol4 = inv_mcol((bArr4[(i3 >> 24) & 255] << 24) ^ (((bArr4[i7 & 255] & 255) ^ ((bArr4[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
                byte[] bArr5 = Si;
                this.C0 = ((((bArr5[iInv_mcol & 255] & 255) ^ ((bArr5[(iInv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(iInv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr5[(iInv_mcol2 >> 24) & 255] << 24)) ^ iArr[0][0];
                this.C1 = ((((bArr5[iInv_mcol2 & 255] & 255) ^ ((bArr5[(iInv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr5[(iInv_mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr5[(iInv_mcol3 >> 24) & 255] << 24)) ^ iArr[0][1];
                this.C2 = ((((bArr5[iInv_mcol3 & 255] & 255) ^ ((bArr5[(iInv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(iInv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr5[(iInv_mcol4 >> 24) & 255] << 24)) ^ iArr[0][2];
                this.C3 = iArr[0][3] ^ ((((bArr5[iInv_mcol4 & 255] & 255) ^ ((bArr5[(iInv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(iInv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr5[(iInv_mcol >> 24) & 255] << 24));
                return;
            }
            int iInv_mcol5 = inv_mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
            byte[] bArr6 = Si;
            int iInv_mcol6 = inv_mcol((bArr6[(i5 >> 24) & 255] << 24) ^ (((bArr6[i4 & 255] & 255) ^ ((bArr6[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
            byte[] bArr7 = Si;
            int iInv_mcol7 = inv_mcol((bArr7[(i7 >> 24) & 255] << 24) ^ (((bArr7[i5 & 255] & 255) ^ ((bArr7[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
            byte[] bArr8 = Si;
            int iInv_mcol8 = inv_mcol((bArr8[(i3 >> 24) & 255] << 24) ^ (((bArr8[i7 & 255] & 255) ^ ((bArr8[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr8[(i4 >> 16) & 255] & 255) << 16)));
            int i9 = i6 - 1;
            int i10 = iInv_mcol8 ^ iArr[i6][3];
            byte[] bArr9 = Si;
            int iInv_mcol9 = inv_mcol((bArr9[(iInv_mcol6 >> 24) & 255] << 24) ^ (((bArr9[iInv_mcol5 & 255] & 255) ^ ((bArr9[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(iInv_mcol7 >> 16) & 255] & 255) << 16))) ^ iArr[i9][0];
            byte[] bArr10 = Si;
            int iInv_mcol10 = inv_mcol((bArr10[(iInv_mcol7 >> 24) & 255] << 24) ^ (((bArr10[iInv_mcol6 & 255] & 255) ^ ((bArr10[(iInv_mcol5 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(i10 >> 16) & 255] & 255) << 16))) ^ iArr[i9][1];
            byte[] bArr11 = Si;
            int iInv_mcol11 = inv_mcol((bArr11[(i10 >> 24) & 255] << 24) ^ (((bArr11[iInv_mcol7 & 255] & 255) ^ ((bArr11[(iInv_mcol6 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(iInv_mcol5 >> 16) & 255] & 255) << 16))) ^ iArr[i9][2];
            byte[] bArr12 = Si;
            int iInv_mcol12 = inv_mcol((((bArr12[i10 & 255] & 255) ^ ((bArr12[(iInv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr12[(iInv_mcol6 >> 16) & 255] & 255) << 16)) ^ (bArr12[(iInv_mcol5 >> 24) & 255] << 24));
            int i11 = i9 - 1;
            i7 = iArr[i9][3] ^ iInv_mcol12;
            i3 = iInv_mcol9;
            i4 = iInv_mcol10;
            i5 = iInv_mcol11;
            i6 = i11;
        }
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.C0 ^ iArr[0][0];
        int i2 = this.C1 ^ iArr[0][1];
        int i3 = this.C2 ^ iArr[0][2];
        int i4 = this.C3 ^ iArr[0][3];
        int i5 = 1;
        while (i5 < this.ROUNDS - 1) {
            byte[] bArr = S;
            int iMcol = mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i & 255] & 255) ^ ((bArr[(i2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i5][0];
            byte[] bArr2 = S;
            int iMcol2 = mcol((bArr2[(i >> 24) & 255] << 24) ^ (((bArr2[i2 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i5][1];
            byte[] bArr3 = S;
            int iMcol3 = mcol((bArr3[(i2 >> 24) & 255] << 24) ^ (((bArr3[i3 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i >> 16) & 255] & 255) << 16))) ^ iArr[i5][2];
            byte[] bArr4 = S;
            int iMcol4 = mcol(((((bArr4[(i >> 8) & 255] & 255) << 8) ^ (bArr4[i4 & 255] & 255)) ^ ((bArr4[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i3 >> 24) & 255] << 24));
            int i6 = i5 + 1;
            int i7 = iMcol4 ^ iArr[i5][3];
            byte[] bArr5 = S;
            int iMcol5 = mcol((bArr5[(i7 >> 24) & 255] << 24) ^ (((bArr5[iMcol & 255] & 255) ^ ((bArr5[(iMcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(iMcol3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
            byte[] bArr6 = S;
            int iMcol6 = mcol((bArr6[(iMcol >> 24) & 255] << 24) ^ (((bArr6[iMcol2 & 255] & 255) ^ ((bArr6[(iMcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
            byte[] bArr7 = S;
            int iMcol7 = mcol((bArr7[(iMcol2 >> 24) & 255] << 24) ^ (((bArr7[iMcol3 & 255] & 255) ^ ((bArr7[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(iMcol >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
            byte[] bArr8 = S;
            int iMcol8 = mcol((((bArr8[i7 & 255] & 255) ^ ((bArr8[(iMcol >> 8) & 255] & 255) << 8)) ^ ((bArr8[(iMcol2 >> 16) & 255] & 255) << 16)) ^ (bArr8[(iMcol3 >> 24) & 255] << 24));
            int i8 = i6 + 1;
            int i9 = iMcol8 ^ iArr[i6][3];
            i2 = iMcol6;
            i4 = i9;
            i = iMcol5;
            i3 = iMcol7;
            i5 = i8;
        }
        byte[] bArr9 = S;
        int iMcol9 = mcol((bArr9[(i4 >> 24) & 255] << 24) ^ (((bArr9[i & 255] & 255) ^ ((bArr9[(i2 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i5][0];
        byte[] bArr10 = S;
        int iMcol10 = mcol((bArr10[(i >> 24) & 255] << 24) ^ (((bArr10[i2 & 255] & 255) ^ ((bArr10[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i5][1];
        byte[] bArr11 = S;
        int iMcol11 = mcol((bArr11[(i2 >> 24) & 255] << 24) ^ (((bArr11[i3 & 255] & 255) ^ ((bArr11[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(i >> 16) & 255] & 255) << 16))) ^ iArr[i5][2];
        byte[] bArr12 = S;
        int iMcol12 = mcol(((((bArr12[(i >> 8) & 255] & 255) << 8) ^ (bArr12[i4 & 255] & 255)) ^ ((bArr12[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr12[(i3 >> 24) & 255] << 24));
        int i10 = i5 + 1;
        int i11 = iMcol12 ^ iArr[i5][3];
        byte[] bArr13 = S;
        this.C0 = iArr[i10][0] ^ ((((bArr13[iMcol9 & 255] & 255) ^ ((bArr13[(iMcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(iMcol11 >> 16) & 255] & 255) << 16)) ^ (bArr13[(i11 >> 24) & 255] << 24));
        this.C1 = ((((bArr13[iMcol10 & 255] & 255) ^ ((bArr13[(iMcol11 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(i11 >> 16) & 255] & 255) << 16)) ^ (bArr13[(iMcol9 >> 24) & 255] << 24)) ^ iArr[i10][1];
        this.C2 = ((((bArr13[iMcol11 & 255] & 255) ^ ((bArr13[(i11 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(iMcol9 >> 16) & 255] & 255) << 16)) ^ (bArr13[(iMcol10 >> 24) & 255] << 24)) ^ iArr[i10][2];
        this.C3 = iArr[i10][3] ^ ((((bArr13[i11 & 255] & 255) ^ ((bArr13[(iMcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(iMcol10 >> 16) & 255] & 255) << 16)) ^ (bArr13[(iMcol11 >> 24) & 255] << 24));
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >> 2;
        int i2 = i + 6;
        this.ROUNDS = i2;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, i2 + 1, 4);
        if (i == 4) {
            int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt;
            int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt2;
            int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt3;
            int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt4;
            for (int i3 = 1; i3 <= 10; i3++) {
                iLittleEndianToInt ^= subWord(shift(iLittleEndianToInt4, 8)) ^ rcon[i3 - 1];
                iArr[i3][0] = iLittleEndianToInt;
                iLittleEndianToInt2 ^= iLittleEndianToInt;
                iArr[i3][1] = iLittleEndianToInt2;
                iLittleEndianToInt3 ^= iLittleEndianToInt2;
                iArr[i3][2] = iLittleEndianToInt3;
                iLittleEndianToInt4 ^= iLittleEndianToInt3;
                iArr[i3][3] = iLittleEndianToInt4;
            }
        } else if (i == 6) {
            int iLittleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt5;
            int iLittleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt6;
            int iLittleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt7;
            int iLittleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt8;
            int iLittleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt9;
            int iLittleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt10;
            int iSubWord = iLittleEndianToInt5 ^ (subWord(shift(iLittleEndianToInt10, 8)) ^ 1);
            iArr[1][2] = iSubWord;
            int i4 = iLittleEndianToInt6 ^ iSubWord;
            iArr[1][3] = i4;
            int i5 = iLittleEndianToInt7 ^ i4;
            iArr[2][0] = i5;
            int i6 = iLittleEndianToInt8 ^ i5;
            iArr[2][1] = i6;
            int i7 = iLittleEndianToInt9 ^ i6;
            iArr[2][2] = i7;
            int i8 = iLittleEndianToInt10 ^ i7;
            iArr[2][3] = i8;
            int i9 = 2;
            for (int i10 = 3; i10 < 12; i10 += 3) {
                int iSubWord2 = subWord(shift(i8, 8)) ^ i9;
                int i11 = i9 << 1;
                int i12 = iSubWord ^ iSubWord2;
                iArr[i10][0] = i12;
                int i13 = i4 ^ i12;
                iArr[i10][1] = i13;
                int i14 = i5 ^ i13;
                iArr[i10][2] = i14;
                int i15 = i6 ^ i14;
                iArr[i10][3] = i15;
                int i16 = i7 ^ i15;
                int i17 = i10 + 1;
                iArr[i17][0] = i16;
                int i18 = i8 ^ i16;
                iArr[i17][1] = i18;
                int iSubWord3 = subWord(shift(i18, 8)) ^ i11;
                i9 = i11 << 1;
                iSubWord = i12 ^ iSubWord3;
                iArr[i17][2] = iSubWord;
                i4 = i13 ^ iSubWord;
                iArr[i17][3] = i4;
                i5 = i14 ^ i4;
                int i19 = i10 + 2;
                iArr[i19][0] = i5;
                i6 = i15 ^ i5;
                iArr[i19][1] = i6;
                i7 = i16 ^ i6;
                iArr[i19][2] = i7;
                i8 = i18 ^ i7;
                iArr[i19][3] = i8;
            }
            int iSubWord4 = (subWord(shift(i8, 8)) ^ i9) ^ iSubWord;
            iArr[12][0] = iSubWord4;
            int i20 = iSubWord4 ^ i4;
            iArr[12][1] = i20;
            int i21 = i20 ^ i5;
            iArr[12][2] = i21;
            iArr[12][3] = i21 ^ i6;
        } else {
            if (i != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int iLittleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt11;
            int iLittleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt12;
            int iLittleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt13;
            int iLittleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt14;
            int iLittleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt15;
            int iLittleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt16;
            int iLittleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = iLittleEndianToInt17;
            int iLittleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = iLittleEndianToInt18;
            int i22 = 1;
            for (int i23 = 2; i23 < 14; i23 += 2) {
                int iSubWord5 = subWord(shift(iLittleEndianToInt18, 8)) ^ i22;
                i22 <<= 1;
                iLittleEndianToInt11 ^= iSubWord5;
                iArr[i23][0] = iLittleEndianToInt11;
                iLittleEndianToInt12 ^= iLittleEndianToInt11;
                iArr[i23][1] = iLittleEndianToInt12;
                iLittleEndianToInt13 ^= iLittleEndianToInt12;
                iArr[i23][2] = iLittleEndianToInt13;
                iLittleEndianToInt14 ^= iLittleEndianToInt13;
                iArr[i23][3] = iLittleEndianToInt14;
                iLittleEndianToInt15 ^= subWord(iLittleEndianToInt14);
                int i24 = i23 + 1;
                iArr[i24][0] = iLittleEndianToInt15;
                iLittleEndianToInt16 ^= iLittleEndianToInt15;
                iArr[i24][1] = iLittleEndianToInt16;
                iLittleEndianToInt17 ^= iLittleEndianToInt16;
                iArr[i24][2] = iLittleEndianToInt17;
                iLittleEndianToInt18 ^= iLittleEndianToInt17;
                iArr[i24][3] = iLittleEndianToInt18;
            }
            int iSubWord6 = (subWord(shift(iLittleEndianToInt18, 8)) ^ i22) ^ iLittleEndianToInt11;
            iArr[14][0] = iSubWord6;
            int i25 = iSubWord6 ^ iLittleEndianToInt12;
            iArr[14][1] = i25;
            int i26 = i25 ^ iLittleEndianToInt13;
            iArr[14][2] = i26;
            iArr[14][3] = i26 ^ iLittleEndianToInt14;
        }
        if (!z) {
            for (int i27 = 1; i27 < this.ROUNDS; i27++) {
                for (int i28 = 0; i28 < 4; i28++) {
                    iArr[i27][i28] = inv_mcol(iArr[i27][i28]);
                }
            }
        }
        return iArr;
    }

    public static int inv_mcol(int i) {
        int iShift = shift(i, 8) ^ i;
        int iFFmulX = i ^ FFmulX(iShift);
        int iFFmulX2 = iShift ^ FFmulX2(iFFmulX);
        return iFFmulX ^ (iFFmulX2 ^ shift(iFFmulX2, 16));
    }

    public static int mcol(int i) {
        int iShift = shift(i, 8);
        int i2 = i ^ iShift;
        return FFmulX(i2) ^ (iShift ^ shift(i2, 16));
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i19 + 1] = (byte) (i18 >> 24);
    }

    public static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    public static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = bArr[i] & 255;
        this.C0 = i3;
        int i4 = i2 + 1;
        int i5 = i3 | ((bArr[i2] & 255) << 8);
        this.C0 = i5;
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 16);
        this.C0 = i7;
        int i8 = i6 + 1;
        this.C0 = i7 | (bArr[i6] << 24);
        int i9 = i8 + 1;
        int i10 = bArr[i8] & 255;
        this.C1 = i10;
        int i11 = i9 + 1;
        int i12 = ((bArr[i9] & 255) << 8) | i10;
        this.C1 = i12;
        int i13 = i11 + 1;
        int i14 = i12 | ((bArr[i11] & 255) << 16);
        this.C1 = i14;
        int i15 = i13 + 1;
        this.C1 = i14 | (bArr[i13] << 24);
        int i16 = i15 + 1;
        int i17 = bArr[i15] & 255;
        this.C2 = i17;
        int i18 = i16 + 1;
        int i19 = ((bArr[i16] & 255) << 8) | i17;
        this.C2 = i19;
        int i20 = i18 + 1;
        int i21 = i19 | ((bArr[i18] & 255) << 16);
        this.C2 = i21;
        int i22 = i20 + 1;
        this.C2 = i21 | (bArr[i20] << 24);
        int i23 = i22 + 1;
        int i24 = bArr[i22] & 255;
        this.C3 = i24;
        int i25 = i23 + 1;
        int i26 = ((bArr[i23] & 255) << 8) | i24;
        this.C3 = i26;
        int i27 = i26 | ((bArr[i25] & 255) << 16);
        this.C3 = i27;
        this.C3 = (bArr[i25 + 1] << 24) | i27;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        int[][] iArr = this.WorkingKey;
        if (z) {
            encryptBlock(iArr);
        } else {
            decryptBlock(iArr);
        }
        packBlock(bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
