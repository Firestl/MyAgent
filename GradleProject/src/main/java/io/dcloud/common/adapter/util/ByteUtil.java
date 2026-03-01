package io.dcloud.common.adapter.util;

/* JADX INFO: loaded from: classes2.dex */
public class ByteUtil {
    public static float bytesToFloat(byte[] bArr) {
        return Float.parseFloat(new String(bArr));
    }

    public static long bytesToLong(byte[] bArr) {
        return Long.parseLong(new String(bArr));
    }

    public static byte[] floatToBytes(float f) {
        return String.valueOf(f).getBytes();
    }

    public static byte[] longToBytes(long j) {
        return String.valueOf(j).getBytes();
    }

    public static byte[] toBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static int toInt(byte[] bArr) {
        return ((bArr[0] & 255) << 24) | (bArr[3] & 255) | ((bArr[2] & 255) << 8) | ((bArr[1] & 255) << 16);
    }
}
