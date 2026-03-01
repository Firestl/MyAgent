package supwisdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public class j00 {
    public static void a(OutputStream outputStream, int i) throws IOException {
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static byte[] a(int i) throws IOException {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static int b(InputStream inputStream) throws IOException {
        return (inputStream.read() & 255) | ((inputStream.read() & 255) << 8);
    }

    public static void c(OutputStream outputStream, int i) throws IOException {
        outputStream.write((byte) (i >>> 24));
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static int d(InputStream inputStream) throws IOException {
        return (inputStream.read() & 255) | ((inputStream.read() & 255) << 24) | ((inputStream.read() & 255) << 16) | ((inputStream.read() & 255) << 8);
    }

    public static void b(OutputStream outputStream, int i) throws IOException {
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static void d(OutputStream outputStream, int i) throws IOException {
        outputStream.write((byte) i);
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 24));
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }

    public static int b(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8);
    }

    public static int c(InputStream inputStream) throws IOException {
        return (inputStream.read() & 255) | ((inputStream.read() & 255) << 16) | ((inputStream.read() & 255) << 8);
    }

    public static String a(byte b) {
        StringBuilder sb = new StringBuilder();
        sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
        sb.append("0123456789ABCDEF".charAt(b & 15));
        return sb.toString();
    }

    public static void a(InputStream inputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        do {
            int i2 = inputStream.read(bArr, i, length - i);
            if (i2 == -1) {
                throw new IOException("Unexpected EOF reached before read buffer was filled");
            }
            i += i2;
        } while (i < length);
    }

    public static double a(InputStream inputStream) throws IOException {
        return Double.longBitsToDouble((((long) (inputStream.read() & 255)) << 56) | (((long) (inputStream.read() & 255)) << 48) | (((long) (inputStream.read() & 255)) << 40) | (((long) (inputStream.read() & 255)) << 32) | ((long) ((inputStream.read() & 255) << 24)) | ((long) ((inputStream.read() & 255) << 16)) | ((long) ((inputStream.read() & 255) << 8)) | ((long) (inputStream.read() & 255)));
    }

    public static void a(OutputStream outputStream, double d) throws IOException {
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d);
        outputStream.write(new byte[]{(byte) ((jDoubleToRawLongBits >> 56) & 255), (byte) ((jDoubleToRawLongBits >> 48) & 255), (byte) ((jDoubleToRawLongBits >> 40) & 255), (byte) ((jDoubleToRawLongBits >> 32) & 255), (byte) ((jDoubleToRawLongBits >> 24) & 255), (byte) ((jDoubleToRawLongBits >> 16) & 255), (byte) ((jDoubleToRawLongBits >> 8) & 255), (byte) (jDoubleToRawLongBits & 255)});
    }
}
