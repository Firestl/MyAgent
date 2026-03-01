package supwisdom;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: ByteBufferWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class pp0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<SoftReference<byte[]>> f8818a = new ThreadLocal<>();
    public static final Class<?> b;
    public static final long c;

    static {
        Class<?> clsA = a("java.io.FileOutputStream");
        b = clsA;
        c = a(clsA);
    }

    public static void a(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        int iPosition = byteBuffer.position();
        try {
            if (byteBuffer.hasArray()) {
                outputStream.write(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            } else if (!b(byteBuffer, outputStream)) {
                byte[] bArrA = a(byteBuffer.remaining());
                while (byteBuffer.hasRemaining()) {
                    int iMin = Math.min(byteBuffer.remaining(), bArrA.length);
                    byteBuffer.get(bArrA, 0, iMin);
                    outputStream.write(bArrA, 0, iMin);
                }
            }
        } finally {
            byteBuffer.position(iPosition);
        }
    }

    public static boolean a(int i, int i2) {
        return i2 < i && ((float) i2) < ((float) i) * 0.5f;
    }

    public static boolean b(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        if (c < 0 || !b.isInstance(outputStream)) {
            return false;
        }
        WritableByteChannel writableByteChannel = null;
        try {
            writableByteChannel = (WritableByteChannel) vr0.n(outputStream, c);
        } catch (ClassCastException unused) {
        }
        if (writableByteChannel == null) {
            return false;
        }
        writableByteChannel.write(byteBuffer);
        return true;
    }

    public static byte[] a(int i) {
        int iMax = Math.max(i, 1024);
        byte[] bArrA = a();
        if (bArrA == null || a(iMax, bArrA.length)) {
            bArrA = new byte[iMax];
            if (iMax <= 16384) {
                a(bArrA);
            }
        }
        return bArrA;
    }

    public static byte[] a() {
        SoftReference<byte[]> softReference = f8818a.get();
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    public static void a(byte[] bArr) {
        f8818a.set(new SoftReference<>(bArr));
    }

    public static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static long a(Class<?> cls) {
        if (cls == null) {
            return -1L;
        }
        try {
            if (vr0.d()) {
                return vr0.b(cls.getDeclaredField("channel"));
            }
            return -1L;
        } catch (Throwable unused) {
            return -1L;
        }
    }
}
