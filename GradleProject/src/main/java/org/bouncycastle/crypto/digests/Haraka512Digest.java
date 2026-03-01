package org.bouncycastle.crypto.digests;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.igexin.c.a.d.g;
import com.taobao.weex.wson.Wson;
import com.tencent.rtmp.TXLivePusher;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public class Haraka512Digest extends HarakaBase {
    public static byte[][] RC = {new byte[]{6, -124, KeyFactorySpi.Ed25519_type, 76, -26, 32, -64, 10, -78, -59, -2, -16, 117, -127, Wson.MAP_TYPE, -99}, new byte[]{-117, Wson.BOOLEAN_TYPE_FALSE, -76, -31, -120, -13, -96, 107, Wson.NUMBER_DOUBLE_TYPE, 15, 107, -92, 47, 8, -9, 23}, new byte[]{52, 2, -34, 45, 83, TXLivePusher.SEI_MSG_TYPE, -124, -104, -49, 2, -99, UTF8.S_P4B, -97, 2, -111, 20}, new byte[]{db.l, -42, -22, -26, 46, Wson.MAP_TYPE, 79, 8, -69, -13, PSSSigner.TRAILER_IMPLICIT, -81, -3, Wson.ARRAY_TYPE, 79, 121}, new byte[]{-53, -49, -80, -53, 72, 114, 68, -117, 121, -18, -51, 28, -66, 57, KeyFactorySpi.Ed25519_type, 68}, new byte[]{126, -22, -51, -18, KeyFactorySpi.x25519_type, -112, 50, -73, -115, 83, 53, -19, 43, -118, 5, Wson.MAP_TYPE}, new byte[]{Wson.NUMBER_BIG_INTEGER_TYPE, -62, -113, 67, 94, 46, 124, -48, -30, 65, 39, 97, -38, 79, -17, 27}, new byte[]{41, 36, -39, -80, -81, -54, -52, 7, Wson.NUMBER_BIG_INTEGER_TYPE, 95, -3, -30, co.j, -57, 11, 59}, new byte[]{-85, 77, 99, -15, -26, -122, 127, -23, -20, -37, -113, -54, -71, -44, Wson.NUMBER_BIG_DECIMAL_TYPE, -18}, new byte[]{28, 48, -65, -124, -44, -73, -51, Wson.NUMBER_DOUBLE_TYPE, Wson.ARRAY_TYPE, 42, UTF8.S_P3B, 79, -83, 3, 126, 51}, new byte[]{-78, -52, 11, -71, -108, 23, 35, -65, Wson.NUMBER_INT_TYPE, 2, -117, 46, -115, -10, -104, 0}, new byte[]{-6, 4, 120, -90, -34, KeyFactorySpi.x448_type, 85, 114, 74, -86, -98, -56, 92, -99, 45, -118}, new byte[]{-33, -76, -97, 43, 107, 119, 42, SharedPreferencesNewImpl.FINISH_MARK, db.l, -6, 79, 46, 41, SharedPreferencesNewImpl.FINISH_MARK, -97, -44}, new byte[]{30, -95, 3, 68, -12, 73, -94, 54, 50, -42, 17, -82, -69, 106, SharedPreferencesNewImpl.FINISH_MARK, -18}, new byte[]{-81, 4, 73, -120, 75, 5, 0, -124, 95, -106, 0, -55, -100, -88, -20, -90}, new byte[]{PublicSuffixDatabase.EXCEPTION_MARKER, 2, 94, -40, -99, 25, -100, 79, 120, -94, -57, -29, 39, -27, -109, -20}, new byte[]{-65, 58, -86, -8, -89, 89, -55, -73, -71, 40, 46, -51, co.h, -44, 1, Wson.STRING_TYPE}, new byte[]{98, UTF8.S_P4B, KeyFactorySpi.Ed25519_type, 13, 97, -122, -80, 23, 55, TXLivePusher.SEI_MSG_TYPE, -17, -39, 16, 48, 125, 107}, new byte[]{90, -54, 69, -62, PublicSuffixDatabase.EXCEPTION_MARKER, 48, 4, 67, -127, -62, -111, 83, -10, -4, -102, -58}, new byte[]{-110, 35, -105, 60, 34, 107, 104, -69, 44, -81, -110, -24, 54, -47, -108, 58}, new byte[]{-45, -65, -110, 56, 34, 88, -122, -21, Wson.NUMBER_LONG_TYPE, -70, -71, 88, -27, 16, KeyFactorySpi.Ed448_type, -76}, new byte[]{-37, -122, 60, -27, -82, -16, -58, 119, -109, 61, -3, -35, 36, -31, SharedPreferencesNewImpl.FINISH_MARK, -115}, new byte[]{-69, UTF8.S_P4B, 98, 104, -1, -21, -96, -100, -125, -28, -115, -29, -53, 34, SharedPreferencesNewImpl.FINISH_MARK, -79}, new byte[]{Wson.STRING_TYPE, 75, -45, -36, -30, -28, -47, -100, 45, -71, JSONLexer.EOI, 78, -57, 43, -9, 125}, new byte[]{67, -69, 71, -61, 97, 48, 27, 67, 75, 20, 21, -60, 44, -77, -110, 78}, new byte[]{-37, -89, 117, -88, -25, 7, -17, -10, 3, -78, 49, -35, 22, -21, 104, -103}, new byte[]{109, -13, 97, 75, 60, 117, 89, 119, -114, 94, 35, 2, 126, -54, 71, 44}, new byte[]{-51, -89, 90, 23, -42, -34, 125, 119, 109, 27, -27, -71, -72, -122, 23, -7}, new byte[]{-20, 107, 67, -16, 107, -88, -23, -86, -99, Wson.NUMBER_LONG_TYPE, 6, -99, -87, Wson.NUMBER_FLOAT_TYPE, -18, 93}, new byte[]{-53, 30, Wson.NUMBER_INT_TYPE, UTF8.S_P4A, -7, 87, 51, 43, -94, 83, 17, 89, 59, -13, 39, -63}, new byte[]{44, -18, 12, 117, 0, -38, 97, -100, -28, -19, 3, 83, UTF8.S_P4B, db.l, -48, -39}, new byte[]{-16, -79, -91, -95, -106, -23, 12, -85, g.n, -69, -70, PSSSigner.TRAILER_IMPLICIT, 99, -92, -93, UTF8.S_P4A}, new byte[]{-82, 61, -79, 2, 94, -106, 41, -120, -85, 13, -34, 48, -109, -115, -54, 57}, new byte[]{23, -69, -113, 56, -43, 84, -92, 11, -120, 20, -13, -88, 46, 117, -76, 66}, new byte[]{52, -69, -118, Wson.ARRAY_TYPE, 95, 66, 127, -41, -82, -74, -73, 121, 54, 10, 22, -10}, new byte[]{38, -10, 82, 65, -53, -27, 84, 56, 67, -50, 89, 24, -1, -70, -81, -34}, new byte[]{76, -23, -102, 84, -71, -13, 2, 106, -94, -54, -100, -9, -125, -98, -55, 120}, new byte[]{-82, 81, -91, JSONLexer.EOI, 27, -33, -9, -66, UTF8.S_P3B, -64, KeyFactorySpi.x25519_type, 40, 34, -112, SharedPreferencesNewImpl.FINISH_MARK, 53}, new byte[]{-96, -63, 97, 60, -70, 126, -46, 43, -63, Wson.STRING_TYPE, PSSSigner.TRAILER_IMPLICIT, 15, 72, -90, 89, -49}, new byte[]{117, 106, -52, 3, 2, 40, co.h, -120, 74, -42, -67, -3, -23, -59, -99, -95}};
    public final byte[] buffer;
    public int off;

    public Haraka512Digest() {
        this.buffer = new byte[64];
    }

    public Haraka512Digest(Haraka512Digest haraka512Digest) {
        this.buffer = Arrays.clone(haraka512Digest.buffer);
        this.off = haraka512Digest.off;
    }

    private int haraka512256(byte[] bArr, byte[] bArr2, int i) {
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 16);
        byte[][] bArr4 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 16);
        System.arraycopy(bArr, 0, bArr3[0], 0, 16);
        System.arraycopy(bArr, 16, bArr3[1], 0, 16);
        System.arraycopy(bArr, 32, bArr3[2], 0, 16);
        System.arraycopy(bArr, 48, bArr3[3], 0, 16);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[0]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[1]);
        bArr3[2] = HarakaBase.aesEnc(bArr3[2], RC[2]);
        bArr3[3] = HarakaBase.aesEnc(bArr3[3], RC[3]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[4]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[5]);
        bArr3[2] = HarakaBase.aesEnc(bArr3[2], RC[6]);
        bArr3[3] = HarakaBase.aesEnc(bArr3[3], RC[7]);
        mix512(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[8]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[9]);
        bArr3[2] = HarakaBase.aesEnc(bArr4[2], RC[10]);
        bArr3[3] = HarakaBase.aesEnc(bArr4[3], RC[11]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[12]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[13]);
        bArr3[2] = HarakaBase.aesEnc(bArr3[2], RC[14]);
        bArr3[3] = HarakaBase.aesEnc(bArr3[3], RC[15]);
        mix512(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[16]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[17]);
        bArr3[2] = HarakaBase.aesEnc(bArr4[2], RC[18]);
        bArr3[3] = HarakaBase.aesEnc(bArr4[3], RC[19]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[20]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[21]);
        bArr3[2] = HarakaBase.aesEnc(bArr3[2], RC[22]);
        bArr3[3] = HarakaBase.aesEnc(bArr3[3], RC[23]);
        mix512(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[24]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[25]);
        bArr3[2] = HarakaBase.aesEnc(bArr4[2], RC[26]);
        bArr3[3] = HarakaBase.aesEnc(bArr4[3], RC[27]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[28]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[29]);
        bArr3[2] = HarakaBase.aesEnc(bArr3[2], RC[30]);
        bArr3[3] = HarakaBase.aesEnc(bArr3[3], RC[31]);
        mix512(bArr3, bArr4);
        bArr3[0] = HarakaBase.aesEnc(bArr4[0], RC[32]);
        bArr3[1] = HarakaBase.aesEnc(bArr4[1], RC[33]);
        bArr3[2] = HarakaBase.aesEnc(bArr4[2], RC[34]);
        bArr3[3] = HarakaBase.aesEnc(bArr4[3], RC[35]);
        bArr3[0] = HarakaBase.aesEnc(bArr3[0], RC[36]);
        bArr3[1] = HarakaBase.aesEnc(bArr3[1], RC[37]);
        bArr3[2] = HarakaBase.aesEnc(bArr3[2], RC[38]);
        bArr3[3] = HarakaBase.aesEnc(bArr3[3], RC[39]);
        mix512(bArr3, bArr4);
        bArr3[0] = HarakaBase.xor(bArr4[0], bArr, 0);
        bArr3[1] = HarakaBase.xor(bArr4[1], bArr, 16);
        bArr3[2] = HarakaBase.xor(bArr4[2], bArr, 32);
        bArr3[3] = HarakaBase.xor(bArr4[3], bArr, 48);
        System.arraycopy(bArr3[0], 8, bArr2, i, 8);
        System.arraycopy(bArr3[1], 8, bArr2, i + 8, 8);
        System.arraycopy(bArr3[2], 0, bArr2, i + 16, 8);
        System.arraycopy(bArr3[3], 0, bArr2, i + 24, 8);
        return 32;
    }

    private void mix512(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 12, bArr2[0], 0, 4);
        System.arraycopy(bArr[2], 12, bArr2[0], 4, 4);
        System.arraycopy(bArr[1], 12, bArr2[0], 8, 4);
        System.arraycopy(bArr[3], 12, bArr2[0], 12, 4);
        System.arraycopy(bArr[2], 0, bArr2[1], 0, 4);
        System.arraycopy(bArr[0], 0, bArr2[1], 4, 4);
        System.arraycopy(bArr[3], 0, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 0, bArr2[1], 12, 4);
        System.arraycopy(bArr[2], 4, bArr2[2], 0, 4);
        System.arraycopy(bArr[0], 4, bArr2[2], 4, 4);
        System.arraycopy(bArr[3], 4, bArr2[2], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[2], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[3], 0, 4);
        System.arraycopy(bArr[2], 8, bArr2[3], 4, 4);
        System.arraycopy(bArr[1], 8, bArr2[3], 8, 4);
        System.arraycopy(bArr[3], 8, bArr2[3], 12, 4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        if (this.off != 64) {
            throw new IllegalStateException("input must be exactly 64 bytes");
        }
        if (bArr.length - i < 32) {
            throw new IllegalArgumentException("output too short to receive digest");
        }
        int iHaraka512256 = haraka512256(this.buffer, bArr, i);
        reset();
        return iHaraka512256;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Haraka-512";
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.off;
        if (i + 1 > 64) {
            throw new IllegalArgumentException("total input cannot be more than 64 bytes");
        }
        byte[] bArr = this.buffer;
        this.off = i + 1;
        bArr[i] = b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 + i2 > 64) {
            throw new IllegalArgumentException("total input cannot be more than 64 bytes");
        }
        System.arraycopy(bArr, i, this.buffer, i3, i2);
        this.off += i2;
    }
}
