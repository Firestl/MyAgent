package com.newcapec.smcrypto;

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/* JADX INFO: loaded from: classes2.dex */
public class NCPBase {
    public static final int NCPSM_ERR_AUTHDATEOUT = 8009;
    public static final int NCPSM_ERR_BLOCKSIZE = 8023;
    public static final int NCPSM_ERR_BUFFSMALL = 8002;
    public static final int NCPSM_ERR_COMPUBKEY = 8003;
    public static final int NCPSM_ERR_CRC = 8006;
    public static final int NCPSM_ERR_CRYPTO = 8018;
    public static final int NCPSM_ERR_DEVCRC = 8007;
    public static final int NCPSM_ERR_GENKEYPAIR = 8010;
    public static final int NCPSM_ERR_INVAKEY = 8004;
    public static final int NCPSM_ERR_INVAKEYSPEC = 8016;
    public static final int NCPSM_ERR_INVALGPARAM = 8015;
    public static final int NCPSM_ERR_IO = 8019;
    public static final int NCPSM_ERR_IVALCIPHER = 8017;
    public static final int NCPSM_ERR_MALLOC = 8012;
    public static final int NCPSM_ERR_NOALG = 8014;
    public static final int NCPSM_ERR_NOPADDING = 8022;
    public static final int NCPSM_ERR_NOPROVIDER = 8021;
    public static final int NCPSM_ERR_OK = 8000;
    public static final int NCPSM_ERR_PADDING = 8024;
    public static final int NCPSM_ERR_PARAM = 8001;
    public static final int NCPSM_ERR_PUBENC = 8013;
    public static final int NCPSM_ERR_QRDATEOUT = 8008;
    public static final int NCPSM_ERR_SIGN = 8011;
    public static final int NCPSM_ERR_SIGNVERFITY = 8020;
    public static final int NCPSM_ERR_UNKNOWN = 8025;
    public static final int NCPSM_ERR_VERIFY = 8005;

    public static long byte8ToInt64(byte[] bArr, int i) {
        long j = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j = (j << 8) | ((long) (bArr[i2 + i] & 255));
        }
        return j;
    }

    public static String hexToString(byte[] bArr) {
        return ByteUtils.toHexString(bArr);
    }

    public static byte[] int64ToByte8(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((j >> (64 - (r3 * 8))) & 255);
        }
        return bArr;
    }

    public static byte[] stringToHex(String str) {
        return ByteUtils.fromHexString(str);
    }
}
