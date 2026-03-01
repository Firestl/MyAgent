package org.bouncycastle.crypto.prng;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.igexin.c.a.d.g;
import com.taobao.weex.wson.Wson;
import com.tencent.rtmp.TXLivePusher;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public class VMPCRandomGenerator implements RandomGenerator {
    public byte n = 0;
    public byte[] P = {-69, 44, 98, 127, -75, -86, -44, 13, -127, -2, -78, co.h, -53, -96, -95, 8, 24, KeyFactorySpi.Ed448_type, 86, -24, 73, 2, 16, -60, -34, 53, -91, -20, g.n, SharedPreferencesNewImpl.FINISH_MARK, -72, Wson.NUMBER_INT_TYPE, -38, 47, 117, -52, -94, 9, 54, 3, 97, 45, -3, co.k, -35, 5, 67, -112, -83, -56, -31, -81, 87, -101, 76, -40, 81, -82, UTF8.S_P4A, -123, 60, 10, -28, -13, -100, 38, 35, 83, -55, -125, -105, Wson.NUMBER_FLOAT_TYPE, -79, -103, Wson.NUMBER_DOUBLE_TYPE, 49, 119, -43, 29, -42, 120, -67, 94, -80, -118, 34, 56, -8, 104, 43, 42, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, KeyFactorySpi.x448_type, -33, 4, -27, -107, 62, 37, -122, -90, 11, -113, -15, 36, db.l, -41, UTF8.S_P3B, -77, -49, 126, 6, 21, -102, 77, 28, -93, -37, 50, -110, 88, 17, 39, -12, 89, -48, 78, 106, 23, Wson.ARRAY_TYPE, -84, -1, 7, -64, Wson.NUMBER_BIG_DECIMAL_TYPE, 121, -4, -57, -51, 118, 66, 93, -25, 58, 52, 122, 48, 40, 15, Wson.STRING_TYPE, 1, -7, -47, -46, 25, -23, -111, -71, 90, -19, 65, 109, -76, -61, -98, -65, 99, -6, co.j, 51, UTF8.S_P4B, 71, -119, -16, -106, JSONLexer.EOI, 95, -109, 61, 55, 75, -39, -88, -63, 27, -10, 57, -117, -73, 12, 32, -50, -120, KeyFactorySpi.x25519_type, -74, Wson.BOOLEAN_TYPE_TRUE, -114, -115, 22, 41, TXLivePusher.SEI_MSG_TYPE, -121, -11, -21, KeyFactorySpi.Ed25519_type, -29, -5, 85, -97, -58, 68, 74, 69, 125, -30, 107, 92, Wson.NUMBER_LONG_TYPE, Wson.BOOLEAN_TYPE_FALSE, -87, -116, -18, -124, 19, -89, 30, -99, -36, Wson.NUMBER_BIG_INTEGER_TYPE, 72, -70, 46, -26, -92, -85, 124, -108, 0, PublicSuffixDatabase.EXCEPTION_MARKER, -17, -22, -66, -54, 114, 79, 82, -104, 63, -62, 20, Wson.MAP_TYPE, 59, 84};
    public byte s = -66;

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.P;
            byte b2 = this.s;
            byte b3 = this.n;
            byte b4 = bArr2[(b2 + bArr2[b3 & 255] + b) & 255];
            this.s = b4;
            byte b5 = bArr2[b3 & 255];
            bArr2[b3 & 255] = bArr2[b4 & 255];
            bArr2[b4 & 255] = b5;
            this.n = (byte) ((b3 + 1) & 255);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this.P) {
            int i3 = i2 + i;
            while (i != i3) {
                byte b = this.P[(this.s + this.P[this.n & 255]) & 255];
                this.s = b;
                bArr[i] = this.P[(this.P[this.P[b & 255] & 255] + 1) & 255];
                byte b2 = this.P[this.n & 255];
                this.P[this.n & 255] = this.P[b & 255];
                this.P[b & 255] = b2;
                this.n = (byte) ((this.n + 1) & 255);
                i++;
            }
        }
    }
}
