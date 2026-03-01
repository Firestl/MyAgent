package io.dcloud.common.util;

import com.igexin.c.a.d.g;
import com.taobao.weex.utils.FunctionParser;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class Md5 {
    public static final int BUFFERSIZE = 51200;
    public static final byte[] PADDING = {g.n, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final int S11 = 7;
    public static final int S12 = 12;
    public static final int S13 = 17;
    public static final int S14 = 22;
    public static final int S21 = 5;
    public static final int S22 = 9;
    public static final int S23 = 14;
    public static final int S24 = 20;
    public static final int S31 = 4;
    public static final int S32 = 11;
    public static final int S33 = 16;
    public static final int S34 = 23;
    public static final int S41 = 6;
    public static final int S42 = 10;
    public static final int S43 = 15;
    public static final int S44 = 21;
    public String digestHexStr;
    public long[] state = new long[4];
    public long[] count = new long[2];
    public byte[] buffer = new byte[64];
    public byte[] digest = new byte[16];

    public Md5() {
        md5Init();
    }

    private void Decode(long[] jArr, byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3 += 4) {
            jArr[i2] = b2iu(bArr[i3]) | (b2iu(bArr[i3 + 1]) << 8) | (b2iu(bArr[i3 + 2]) << 16) | (b2iu(bArr[i3 + 3]) << 24);
            i2++;
        }
    }

    private void Encode(byte[] bArr, long[] jArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3 += 4) {
            bArr[i3] = (byte) (jArr[i2] & 255);
            bArr[i3 + 1] = (byte) ((jArr[i2] >>> 8) & 255);
            bArr[i3 + 2] = (byte) ((jArr[i2] >>> 16) & 255);
            bArr[i3 + 3] = (byte) ((jArr[i2] >>> 24) & 255);
            i2++;
        }
    }

    private long F(long j, long j2, long j3) {
        return ((~j) & j3) | (j2 & j);
    }

    private long FF(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        int iF = (int) (F(j2, j3, j4) + j5 + j7 + j);
        return ((long) ((iF >>> ((int) (32 - j6))) | (iF << ((int) j6)))) + j2;
    }

    private long G(long j, long j2, long j3) {
        return (j & j3) | (j2 & (~j3));
    }

    private long GG(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        int iG = (int) (G(j2, j3, j4) + j5 + j7 + j);
        return ((long) ((iG >>> ((int) (32 - j6))) | (iG << ((int) j6)))) + j2;
    }

    private long H(long j, long j2, long j3) {
        return (j ^ j2) ^ j3;
    }

    private long HH(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        int iH = (int) (H(j2, j3, j4) + j5 + j7 + j);
        return ((long) ((iH >>> ((int) (32 - j6))) | (iH << ((int) j6)))) + j2;
    }

    private long I(long j, long j2, long j3) {
        return (j | (~j3)) ^ j2;
    }

    private long II(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        int I = (int) (I(j2, j3, j4) + j5 + j7 + j);
        return ((long) ((I >>> ((int) (32 - j6))) | (I << ((int) j6)))) + j2;
    }

    public static long b2iu(byte b) {
        int i = b;
        if (b < 0) {
            i = b & 255;
        }
        return i;
    }

    public static String byteHEX(byte b) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F'};
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]});
    }

    private void md5Final() {
        byte[] bArr = new byte[8];
        Encode(bArr, this.count, 8);
        int i = ((int) (this.count[0] >>> 3)) & 63;
        md5Update(PADDING, i < 56 ? 56 - i : 120 - i);
        md5Update(bArr, 8);
        Encode(this.digest, this.state, 16);
    }

    private void md5Init() {
        long[] jArr = this.count;
        jArr[0] = 0;
        jArr[1] = 0;
        long[] jArr2 = this.state;
        jArr2[0] = 1732584193;
        jArr2[1] = 4023233417L;
        jArr2[2] = 2562383102L;
        jArr2[3] = 271733878;
    }

    private void md5Memcpy(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[i + i4] = bArr2[i2 + i4];
        }
    }

    private void md5Transform(byte[] bArr) {
        long[] jArr = this.state;
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long[] jArr2 = new long[16];
        Decode(jArr2, bArr, 64);
        long jFF = FF(j, j2, j3, j4, jArr2[0], 7L, 3614090360L);
        long jFF2 = FF(j4, jFF, j2, j3, jArr2[1], 12L, 3905402710L);
        long jFF3 = FF(j3, jFF2, jFF, j2, jArr2[2], 17L, 606105819L);
        long jFF4 = FF(j2, jFF3, jFF2, jFF, jArr2[3], 22L, 3250441966L);
        long jFF5 = FF(jFF, jFF4, jFF3, jFF2, jArr2[4], 7L, 4118548399L);
        long jFF6 = FF(jFF2, jFF5, jFF4, jFF3, jArr2[5], 12L, 1200080426L);
        long jFF7 = FF(jFF3, jFF6, jFF5, jFF4, jArr2[6], 17L, 2821735955L);
        long jFF8 = FF(jFF4, jFF7, jFF6, jFF5, jArr2[7], 22L, 4249261313L);
        long jFF9 = FF(jFF5, jFF8, jFF7, jFF6, jArr2[8], 7L, 1770035416L);
        long jFF10 = FF(jFF6, jFF9, jFF8, jFF7, jArr2[9], 12L, 2336552879L);
        long jFF11 = FF(jFF7, jFF10, jFF9, jFF8, jArr2[10], 17L, 4294925233L);
        long jFF12 = FF(jFF8, jFF11, jFF10, jFF9, jArr2[11], 22L, 2304563134L);
        long jFF13 = FF(jFF9, jFF12, jFF11, jFF10, jArr2[12], 7L, 1804603682L);
        long jFF14 = FF(jFF10, jFF13, jFF12, jFF11, jArr2[13], 12L, 4254626195L);
        long jFF15 = FF(jFF11, jFF14, jFF13, jFF12, jArr2[14], 17L, 2792965006L);
        long jFF16 = FF(jFF12, jFF15, jFF14, jFF13, jArr2[15], 22L, 1236535329L);
        long jGG = GG(jFF13, jFF16, jFF15, jFF14, jArr2[1], 5L, 4129170786L);
        long jGG2 = GG(jFF14, jGG, jFF16, jFF15, jArr2[6], 9L, 3225465664L);
        long jGG3 = GG(jFF15, jGG2, jGG, jFF16, jArr2[11], 14L, 643717713L);
        long jGG4 = GG(jFF16, jGG3, jGG2, jGG, jArr2[0], 20L, 3921069994L);
        long jGG5 = GG(jGG, jGG4, jGG3, jGG2, jArr2[5], 5L, 3593408605L);
        long jGG6 = GG(jGG2, jGG5, jGG4, jGG3, jArr2[10], 9L, 38016083L);
        long jGG7 = GG(jGG3, jGG6, jGG5, jGG4, jArr2[15], 14L, 3634488961L);
        long jGG8 = GG(jGG4, jGG7, jGG6, jGG5, jArr2[4], 20L, 3889429448L);
        long jGG9 = GG(jGG5, jGG8, jGG7, jGG6, jArr2[9], 5L, 568446438L);
        long jGG10 = GG(jGG6, jGG9, jGG8, jGG7, jArr2[14], 9L, 3275163606L);
        long jGG11 = GG(jGG7, jGG10, jGG9, jGG8, jArr2[3], 14L, 4107603335L);
        long jGG12 = GG(jGG8, jGG11, jGG10, jGG9, jArr2[8], 20L, 1163531501L);
        long jGG13 = GG(jGG9, jGG12, jGG11, jGG10, jArr2[13], 5L, 2850285829L);
        long jGG14 = GG(jGG10, jGG13, jGG12, jGG11, jArr2[2], 9L, 4243563512L);
        long jGG15 = GG(jGG11, jGG14, jGG13, jGG12, jArr2[7], 14L, 1735328473L);
        long jGG16 = GG(jGG12, jGG15, jGG14, jGG13, jArr2[12], 20L, 2368359562L);
        long jHH = HH(jGG13, jGG16, jGG15, jGG14, jArr2[5], 4L, 4294588738L);
        long jHH2 = HH(jGG14, jHH, jGG16, jGG15, jArr2[8], 11L, 2272392833L);
        long jHH3 = HH(jGG15, jHH2, jHH, jGG16, jArr2[11], 16L, 1839030562L);
        long jHH4 = HH(jGG16, jHH3, jHH2, jHH, jArr2[14], 23L, 4259657740L);
        long jHH5 = HH(jHH, jHH4, jHH3, jHH2, jArr2[1], 4L, 2763975236L);
        long jHH6 = HH(jHH2, jHH5, jHH4, jHH3, jArr2[4], 11L, 1272893353L);
        long jHH7 = HH(jHH3, jHH6, jHH5, jHH4, jArr2[7], 16L, 4139469664L);
        long jHH8 = HH(jHH4, jHH7, jHH6, jHH5, jArr2[10], 23L, 3200236656L);
        long jHH9 = HH(jHH5, jHH8, jHH7, jHH6, jArr2[13], 4L, 681279174L);
        long jHH10 = HH(jHH6, jHH9, jHH8, jHH7, jArr2[0], 11L, 3936430074L);
        long jHH11 = HH(jHH7, jHH10, jHH9, jHH8, jArr2[3], 16L, 3572445317L);
        long jHH12 = HH(jHH8, jHH11, jHH10, jHH9, jArr2[6], 23L, 76029189L);
        long jHH13 = HH(jHH9, jHH12, jHH11, jHH10, jArr2[9], 4L, 3654602809L);
        long jHH14 = HH(jHH10, jHH13, jHH12, jHH11, jArr2[12], 11L, 3873151461L);
        long jHH15 = HH(jHH11, jHH14, jHH13, jHH12, jArr2[15], 16L, 530742520L);
        long jHH16 = HH(jHH12, jHH15, jHH14, jHH13, jArr2[2], 23L, 3299628645L);
        long jII = II(jHH13, jHH16, jHH15, jHH14, jArr2[0], 6L, 4096336452L);
        long jII2 = II(jHH14, jII, jHH16, jHH15, jArr2[7], 10L, 1126891415L);
        long jII3 = II(jHH15, jII2, jII, jHH16, jArr2[14], 15L, 2878612391L);
        long jII4 = II(jHH16, jII3, jII2, jII, jArr2[5], 21L, 4237533241L);
        long jII5 = II(jII, jII4, jII3, jII2, jArr2[12], 6L, 1700485571L);
        long jII6 = II(jII2, jII5, jII4, jII3, jArr2[3], 10L, 2399980690L);
        long jII7 = II(jII3, jII6, jII5, jII4, jArr2[10], 15L, 4293915773L);
        long jII8 = II(jII4, jII7, jII6, jII5, jArr2[1], 21L, 2240044497L);
        long jII9 = II(jII5, jII8, jII7, jII6, jArr2[8], 6L, 1873313359L);
        long jII10 = II(jII6, jII9, jII8, jII7, jArr2[15], 10L, 4264355552L);
        long jII11 = II(jII7, jII10, jII9, jII8, jArr2[6], 15L, 2734768916L);
        long jII12 = II(jII8, jII11, jII10, jII9, jArr2[13], 21L, 1309151649L);
        long jII13 = II(jII9, jII12, jII11, jII10, jArr2[4], 6L, 4149444226L);
        long jII14 = II(jII10, jII13, jII12, jII11, jArr2[11], 10L, 3174756917L);
        long jII15 = II(jII11, jII14, jII13, jII12, jArr2[2], 15L, 718787259L);
        long jII16 = II(jII12, jII15, jII14, jII13, jArr2[9], 21L, 3951481745L);
        long[] jArr3 = this.state;
        jArr3[0] = jArr3[0] + jII13;
        jArr3[1] = jArr3[1] + jII16;
        jArr3[2] = jArr3[2] + jII15;
        jArr3[3] = jArr3[3] + jII14;
    }

    private void md5Update(byte[] bArr, int i) {
        int i2;
        byte[] bArr2 = new byte[64];
        long[] jArr = this.count;
        int i3 = ((int) (jArr[0] >>> 3)) & 63;
        long j = i << 3;
        long j2 = jArr[0] + j;
        jArr[0] = j2;
        if (j2 < j) {
            jArr[1] = jArr[1] + 1;
        }
        jArr[1] = jArr[1] + ((long) (i >>> 29));
        int i4 = 64 - i3;
        if (i >= i4) {
            md5Memcpy(this.buffer, bArr, i3, 0, i4);
            md5Transform(this.buffer);
            while (i4 + 63 < i) {
                md5Memcpy(bArr2, bArr, 0, i4, 64);
                md5Transform(bArr2);
                i4 += 64;
            }
            i2 = i4;
            i3 = 0;
        } else {
            i2 = 0;
        }
        md5Memcpy(this.buffer, bArr, i3, i2, i - i2);
    }

    public byte[] getMD5ofBytes(String str) {
        md5Init();
        byte[] bytes = str.getBytes();
        md5Update(bytes, bytes.length);
        md5Final();
        return this.digest;
    }

    public String getMD5ofStr(String str) {
        md5Init();
        md5Update(str.getBytes(), str.length());
        md5Final();
        this.digestHexStr = "";
        for (int i = 0; i < 16; i++) {
            this.digestHexStr += byteHEX(this.digest[i]);
        }
        return this.digestHexStr;
    }

    public byte[] getMD5ofStream(InputStream inputStream) {
        md5Init();
        byte[] bArr = new byte[51200];
        while (true) {
            try {
                int i = inputStream.read(bArr, 0, 51200);
                if (i == -1) {
                    md5Final();
                    return this.digest;
                }
                md5Update(bArr, i);
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }
}
