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
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public class RijndaelEngine implements BlockCipher {
    public static final int MAXKC = 64;
    public static final int MAXROUNDS = 14;
    public long A0;
    public long A1;
    public long A2;
    public long A3;
    public int BC;
    public long BC_MASK;
    public int ROUNDS;
    public int blockBits;
    public boolean forEncryption;
    public byte[] shifts0SC;
    public byte[] shifts1SC;
    public long[][] workingKey;
    public static final byte[] logtable = {0, 0, 25, 1, 50, 2, JSONLexer.EOI, -58, 75, -57, 27, 104, 51, -18, -33, 3, Wson.NUMBER_DOUBLE_TYPE, 4, co.k, db.l, 52, -115, -127, -17, 76, KeyFactorySpi.Ed448_type, 8, -56, -8, Wson.NUMBER_INT_TYPE, 28, -63, 125, -62, 29, -75, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, 120, Wson.NUMBER_BIG_DECIMAL_TYPE, 47, -118, 5, PublicSuffixDatabase.EXCEPTION_MARKER, 15, -31, 36, SharedPreferencesNewImpl.FINISH_MARK, -16, co.h, 69, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, 19, 92, -46, -15, UTF8.S_P3B, Wson.NUMBER_FLOAT_TYPE, -125, 56, Wson.BOOLEAN_TYPE_FALSE, -35, -3, 48, -65, 6, -117, 98, -77, 37, -30, -104, 34, -120, -111, 16, 126, KeyFactorySpi.x25519_type, 72, -61, -93, -74, 30, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, 121, 10, 21, -101, -97, 94, -54, 78, -44, -84, -27, -13, Wson.STRING_TYPE, -89, 87, -81, 88, -88, UTF8.S_P4A, -12, -22, -42, Wson.BOOLEAN_TYPE_TRUE, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, 22, 11, -11, 89, -53, 95, -80, -100, -87, 81, -96, 127, 12, -10, KeyFactorySpi.x448_type, 23, -60, 73, -20, -40, 67, co.j, 45, -92, 118, Wson.MAP_TYPE, -73, -52, -69, 62, 90, -5, UTF8.S_P4B, -79, -122, 59, 82, -95, Wson.NUMBER_LONG_TYPE, -86, 85, 41, -99, -105, -78, -121, -112, 97, -66, -36, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, 63, Wson.ARRAY_TYPE, -47, 83, 57, -124, 60, 65, -94, 109, 71, 20, 42, -98, 93, 86, TXLivePusher.SEI_MSG_TYPE, -45, -85, 68, 17, -110, -39, 35, 32, 46, -119, -76, 124, -72, 38, 119, -103, -29, -91, Wson.NUMBER_BIG_INTEGER_TYPE, 74, -19, -34, -59, 49, -2, 24, 13, 99, -116, g.n, -64, -9, KeyFactorySpi.Ed25519_type, 7};
    public static final byte[] aLogtable = {0, 3, 5, 15, 17, 51, 85, -1, JSONLexer.EOI, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, Wson.STRING_TYPE, -107, -92, -9, 2, 6, 10, 30, 34, Wson.BOOLEAN_TYPE_FALSE, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, KeyFactorySpi.Ed25519_type, -112, -85, -26, 49, 83, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, KeyFactorySpi.x25519_type, -78, -51, 76, -44, Wson.NUMBER_BIG_INTEGER_TYPE, -87, co.k, 59, 77, -41, 98, -90, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, 127, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, UTF8.S_P4A, -16, 11, 29, 39, Wson.NUMBER_INT_TYPE, -69, -42, 97, -93, -2, 25, 43, 125, -121, -110, -83, -20, 47, KeyFactorySpi.Ed448_type, -109, -82, -23, 32, UTF8.S_P4B, -96, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, UTF8.S_P3B, -64, Wson.ARRAY_TYPE, -19, 44, Wson.BOOLEAN_TYPE_TRUE, -100, -65, -38, 117, -97, -70, -43, Wson.NUMBER_DOUBLE_TYPE, -84, -17, 42, 126, co.h, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, g.n, -101, -74, -63, 88, -24, 35, Wson.NUMBER_BIG_DECIMAL_TYPE, -81, -22, 37, KeyFactorySpi.x448_type, -79, -56, 67, -59, 84, -4, co.j, PublicSuffixDatabase.EXCEPTION_MARKER, 99, -91, -12, 7, 9, 27, 45, 119, -103, -80, -53, Wson.NUMBER_FLOAT_TYPE, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, db.l, SharedPreferencesNewImpl.FINISH_MARK, 54, 90, -18, 41, Wson.MAP_TYPE, -115, -116, -113, -118, -123, -108, -89, TXLivePusher.SEI_MSG_TYPE, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, 28, 36, Wson.NUMBER_LONG_TYPE, -76, -57, 82, -10, 1, 3, 5, 15, 17, 51, 85, -1, JSONLexer.EOI, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, Wson.STRING_TYPE, -107, -92, -9, 2, 6, 10, 30, 34, Wson.BOOLEAN_TYPE_FALSE, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, KeyFactorySpi.Ed25519_type, -112, -85, -26, 49, 83, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, KeyFactorySpi.x25519_type, -78, -51, 76, -44, Wson.NUMBER_BIG_INTEGER_TYPE, -87, co.k, 59, 77, -41, 98, -90, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, 127, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, UTF8.S_P4A, -16, 11, 29, 39, Wson.NUMBER_INT_TYPE, -69, -42, 97, -93, -2, 25, 43, 125, -121, -110, -83, -20, 47, KeyFactorySpi.Ed448_type, -109, -82, -23, 32, UTF8.S_P4B, -96, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, UTF8.S_P3B, -64, Wson.ARRAY_TYPE, -19, 44, Wson.BOOLEAN_TYPE_TRUE, -100, -65, -38, 117, -97, -70, -43, Wson.NUMBER_DOUBLE_TYPE, -84, -17, 42, 126, co.h, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, g.n, -101, -74, -63, 88, -24, 35, Wson.NUMBER_BIG_DECIMAL_TYPE, -81, -22, 37, KeyFactorySpi.x448_type, -79, -56, 67, -59, 84, -4, co.j, PublicSuffixDatabase.EXCEPTION_MARKER, 99, -91, -12, 7, 9, 27, 45, 119, -103, -80, -53, Wson.NUMBER_FLOAT_TYPE, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, db.l, SharedPreferencesNewImpl.FINISH_MARK, 54, 90, -18, 41, Wson.MAP_TYPE, -115, -116, -113, -118, -123, -108, -89, TXLivePusher.SEI_MSG_TYPE, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, 28, 36, Wson.NUMBER_LONG_TYPE, -76, -57, 82, -10, 1};
    public static final byte[] S = {99, 124, 119, Wson.MAP_TYPE, TXLivePusher.SEI_MSG_TYPE, 107, KeyFactorySpi.x448_type, -59, 48, 1, Wson.NUMBER_BIG_INTEGER_TYPE, 43, -2, -41, -85, 118, -54, co.h, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, KeyFactorySpi.Ed448_type, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, SharedPreferencesNewImpl.FINISH_MARK, g.n, -30, -21, 39, -78, 117, 9, -125, 44, JSONLexer.EOI, 27, KeyFactorySpi.x25519_type, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, Wson.ARRAY_TYPE, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, UTF8.S_P4A, 60, -97, -88, 81, -93, UTF8.S_P3B, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, PublicSuffixDatabase.EXCEPTION_MARKER, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, Wson.NUMBER_DOUBLE_TYPE, 93, 25, Wson.STRING_TYPE, UTF8.S_P4B, -127, 79, -36, 34, 42, -112, -120, Wson.NUMBER_FLOAT_TYPE, -18, -72, 20, -34, 94, 11, -37, co.k, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, Wson.NUMBER_LONG_TYPE, 86, -12, -22, Wson.NUMBER_BIG_DECIMAL_TYPE, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, Wson.BOOLEAN_TYPE_TRUE, co.j, 75, -67, -117, -118, KeyFactorySpi.Ed25519_type, 62, -75, Wson.BOOLEAN_TYPE_FALSE, 72, 3, -10, db.l, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, Wson.NUMBER_INT_TYPE, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    public static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, UTF8.S_P3B, -93, -98, -127, -13, -41, -5, 124, -29, 57, co.h, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, Wson.MAP_TYPE, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, Wson.BOOLEAN_TYPE_FALSE, 40, -39, 36, -78, 118, Wson.ARRAY_TYPE, -94, 73, 109, -117, -47, 37, 114, -8, -10, Wson.NUMBER_DOUBLE_TYPE, -122, 104, -104, 22, -44, -92, 92, -52, 93, Wson.NUMBER_BIG_DECIMAL_TYPE, -74, -110, Wson.NUMBER_LONG_TYPE, KeyFactorySpi.Ed25519_type, 72, UTF8.S_P4A, -3, -19, -71, -38, 94, 21, Wson.NUMBER_FLOAT_TYPE, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, Wson.NUMBER_BIG_INTEGER_TYPE, -36, -22, -105, TXLivePusher.SEI_MSG_TYPE, -49, -50, -16, -76, -26, Wson.STRING_TYPE, -106, -84, Wson.BOOLEAN_TYPE_TRUE, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, KeyFactorySpi.x25519_type, 71, -15, JSONLexer.EOI, KeyFactorySpi.Ed448_type, 29, 41, -59, -119, KeyFactorySpi.x448_type, -73, 98, db.l, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, co.j, -35, -88, 51, -120, 7, -57, 49, -79, SharedPreferencesNewImpl.FINISH_MARK, 16, 89, 39, g.n, -20, 95, UTF8.S_P4B, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, co.k, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, Wson.NUMBER_INT_TYPE, 20, 99, 85, PublicSuffixDatabase.EXCEPTION_MARKER, 12, 125};
    public static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 179, 125, 250, 239, 197, c.G};
    public static byte[][] shifts0 = {new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, 24, 32}};
    public static byte[][] shifts1 = {new byte[]{0, 24, 16, 8}, new byte[]{0, 32, 24, 16}, new byte[]{0, 40, 32, 24}, new byte[]{0, 48, 40, 24}, new byte[]{0, 56, 40, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.BC = 32;
            this.BC_MASK = 4294967295L;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (i == 160) {
            this.BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (i == 192) {
            this.BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (i == 224) {
            this.BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else {
            if (i != 256) {
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
            }
            this.BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        }
        this.blockBits = i;
    }

    private void InvMixColumn() {
        long jMul0xe = 0;
        long jMul0xe2 = 0;
        long jMul0xe3 = 0;
        long jMul0xe4 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            long j = jMul0xe4;
            int i5 = (int) ((this.A3 >> i) & 255);
            int i6 = -1;
            int i7 = i2 != 0 ? logtable[i2 & 255] & 255 : -1;
            int i8 = i3 != 0 ? logtable[i3 & 255] & 255 : -1;
            int i9 = i4 != 0 ? logtable[i4 & 255] & 255 : -1;
            if (i5 != 0) {
                i6 = logtable[i5 & 255] & 255;
            }
            jMul0xe |= ((long) ((((mul0xe(i7) ^ mul0xb(i8)) ^ mul0xd(i9)) ^ mul0x9(i6)) & 255)) << i;
            jMul0xe2 |= ((long) ((((mul0xe(i8) ^ mul0xb(i9)) ^ mul0xd(i6)) ^ mul0x9(i7)) & 255)) << i;
            jMul0xe3 |= ((long) ((((mul0xe(i9) ^ mul0xb(i6)) ^ mul0xd(i7)) ^ mul0x9(i8)) & 255)) << i;
            jMul0xe4 = j | (((long) ((((mul0xe(i6) ^ mul0xb(i7)) ^ mul0xd(i8)) ^ mul0x9(i9)) & 255)) << i);
        }
        this.A0 = jMul0xe;
        this.A1 = jMul0xe2;
        this.A2 = jMul0xe3;
        this.A3 = jMul0xe4;
    }

    private void KeyAddition(long[] jArr) {
        this.A0 ^= jArr[0];
        this.A1 ^= jArr[1];
        this.A2 ^= jArr[2];
        this.A3 ^= jArr[3];
    }

    private void MixColumn() {
        long jMul0x2 = 0;
        long jMul0x22 = 0;
        long jMul0x23 = 0;
        long jMul0x24 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            int i2 = (int) ((this.A0 >> i) & 255);
            int i3 = (int) ((this.A1 >> i) & 255);
            int i4 = (int) ((this.A2 >> i) & 255);
            int i5 = (int) ((this.A3 >> i) & 255);
            jMul0x2 |= ((long) ((((mul0x2(i2) ^ mul0x3(i3)) ^ i4) ^ i5) & 255)) << i;
            jMul0x22 |= ((long) ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i2) & 255)) << i;
            jMul0x23 |= ((long) ((((mul0x2(i4) ^ mul0x3(i5)) ^ i2) ^ i3) & 255)) << i;
            jMul0x24 |= ((long) ((((mul0x2(i5) ^ mul0x3(i2)) ^ i3) ^ i4) & 255)) << i;
        }
        this.A0 = jMul0x2;
        this.A1 = jMul0x22;
        this.A2 = jMul0x23;
        this.A3 = jMul0x24;
    }

    private void ShiftRow(byte[] bArr) {
        this.A1 = shift(this.A1, bArr[1]);
        this.A2 = shift(this.A2, bArr[2]);
        this.A3 = shift(this.A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.A0 = applyS(this.A0, bArr);
        this.A1 = applyS(this.A1, bArr);
        this.A2 = applyS(this.A2, bArr);
        this.A3 = applyS(this.A3, bArr);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.BC; i += 8) {
            j2 |= ((long) (bArr[(int) ((j >> i) & 255)] & 255)) << i;
        }
        return j2;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(Si);
        ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; i--) {
            KeyAddition(jArr[i]);
            InvMixColumn();
            Substitution(Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i = 1; i < this.ROUNDS; i++) {
            Substitution(S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i]);
        }
        Substitution(S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i;
        int i2;
        int i3 = 8;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance((Class<?>) long.class, 15, 4);
        int i4 = 4;
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
            }
            i = 8;
        }
        this.ROUNDS = length >= this.blockBits ? i + 6 : (this.BC / 8) + 6;
        char c = 0;
        int i5 = 0;
        int i6 = 0;
        while (i5 < bArr.length) {
            bArr2[i5 % 4][i5 / 4] = bArr[i6];
            i5++;
            i6++;
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < i && i8 < (this.ROUNDS + 1) * (this.BC / 8)) {
            int i9 = 0;
            while (i9 < i4) {
                int i10 = this.BC;
                long[] jArr2 = jArr[i8 / (i10 / 8)];
                jArr2[i9] = (((long) (bArr2[i9][i7] & 255)) << ((i8 * 8) % i10)) | jArr2[i9];
                i9++;
                i4 = 4;
            }
            i7++;
            i8++;
            i4 = 4;
        }
        int i11 = 0;
        while (i8 < (this.ROUNDS + 1) * (this.BC / i3)) {
            int i12 = 0;
            while (i12 < 4) {
                byte[] bArr3 = bArr2[i12];
                i12++;
                bArr3[c] = (byte) (bArr3[c] ^ S[bArr2[i12 % 4][i - 1] & 255]);
            }
            byte[] bArr4 = bArr2[c];
            int i13 = i11 + 1;
            bArr4[c] = (byte) (rcon[i11] ^ bArr4[c]);
            int i14 = 1;
            if (i <= 6) {
                while (i14 < i) {
                    for (int i15 = 0; i15 < 4; i15++) {
                        byte[] bArr5 = bArr2[i15];
                        bArr5[i14] = (byte) (bArr5[i14] ^ bArr2[i15][i14 - 1]);
                    }
                    i14++;
                }
            } else {
                while (true) {
                    i2 = 4;
                    if (i14 >= 4) {
                        break;
                    }
                    int i16 = 0;
                    while (i16 < i2) {
                        byte[] bArr6 = bArr2[i16];
                        bArr6[i14] = (byte) (bArr6[i14] ^ bArr2[i16][i14 - 1]);
                        i16++;
                        i2 = 4;
                    }
                    i14++;
                }
                for (int i17 = 0; i17 < 4; i17++) {
                    byte[] bArr7 = bArr2[i17];
                    bArr7[4] = (byte) (bArr7[4] ^ S[bArr2[i17][3] & 255]);
                }
                int i18 = 5;
                while (i18 < i) {
                    int i19 = 0;
                    while (i19 < i2) {
                        byte[] bArr8 = bArr2[i19];
                        bArr8[i18] = (byte) (bArr8[i18] ^ bArr2[i19][i18 - 1]);
                        i19++;
                        i2 = 4;
                    }
                    i18++;
                    i2 = 4;
                }
            }
            int i20 = 0;
            while (i20 < i && i8 < (this.ROUNDS + 1) * (this.BC / i3)) {
                for (int i21 = 0; i21 < 4; i21++) {
                    int i22 = this.BC;
                    long[] jArr3 = jArr[i8 / (i22 / 8)];
                    jArr3[i21] = (((long) (bArr2[i21][i20] & 255)) << ((i8 * 8) % i22)) | jArr3[i21];
                }
                i20++;
                i8++;
                i3 = 8;
            }
            i11 = i13;
            c = 0;
            i3 = 8;
        }
        return jArr;
    }

    private byte mul0x2(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int i) {
        if (i >= 0) {
            return aLogtable[i + 199];
        }
        return (byte) 0;
    }

    private byte mul0xb(int i) {
        if (i >= 0) {
            return aLogtable[i + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int i) {
        if (i >= 0) {
            return aLogtable[i + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int i) {
        if (i >= 0) {
            return aLogtable[i + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.BC; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) (this.A0 >> i2);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (this.A1 >> i2);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.A2 >> i2);
            i = i5 + 1;
            bArr[i5] = (byte) (this.A3 >> i2);
        }
    }

    private long shift(long j, int i) {
        return ((j << (this.BC - i)) | (j >>> i)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int i) {
        this.A0 = bArr[i] & 255;
        this.A1 = bArr[r0] & 255;
        this.A2 = bArr[r8] & 255;
        int i2 = i + 1 + 1 + 1 + 1;
        this.A3 = bArr[r0] & 255;
        for (int i3 = 8; i3 != this.BC; i3 += 8) {
            int i4 = i2 + 1;
            this.A0 |= ((long) (bArr[i2] & 255)) << i3;
            int i5 = i4 + 1;
            this.A1 |= ((long) (bArr[i4] & 255)) << i3;
            int i6 = i5 + 1;
            this.A2 |= ((long) (bArr[i5] & 255)) << i3;
            i2 = i6 + 1;
            this.A3 |= ((long) (bArr[i6] & 255)) << i3;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
        int i3 = this.BC;
        if ((i3 / 2) + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if ((i3 / 2) + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        long[][] jArr = this.workingKey;
        if (z) {
            encryptBlock(jArr);
        } else {
            decryptBlock(jArr);
        }
        packBlock(bArr2, i2);
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
