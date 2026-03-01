package supwisdom;

import io.dcloud.common.util.JSUtil;
import java.nio.ByteBuffer;
import org.bouncycastle.math.ec.rfc7748.X25519Field;

/* JADX INFO: compiled from: IsoTypeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class ks {
    public static void a(ByteBuffer byteBuffer, long j) {
        byteBuffer.putInt((int) j);
    }

    public static void b(ByteBuffer byteBuffer, long j) {
        byteBuffer.putLong(j);
    }

    public static void c(ByteBuffer byteBuffer, int i) {
        byteBuffer.put((byte) (i & 255));
    }

    public static void a(ByteBuffer byteBuffer, int i) {
        int i2 = i & 65535;
        c(byteBuffer, i2 >> 8);
        c(byteBuffer, i2 & 255);
    }

    public static void b(ByteBuffer byteBuffer, int i) {
        int i2 = i & X25519Field.M24;
        a(byteBuffer, i2 >> 8);
        c(byteBuffer, i2);
    }

    public static void c(ByteBuffer byteBuffer, double d) {
        short s = (short) (d * 256.0d);
        byteBuffer.put((byte) ((65280 & s) >> 8));
        byteBuffer.put((byte) (s & 255));
    }

    public static void a(ByteBuffer byteBuffer, double d) {
        int i = (int) (d * 1.073741824E9d);
        byteBuffer.put((byte) (((-16777216) & i) >> 24));
        byteBuffer.put((byte) ((16711680 & i) >> 16));
        byteBuffer.put((byte) ((65280 & i) >> 8));
        byteBuffer.put((byte) (i & 255));
    }

    public static void b(ByteBuffer byteBuffer, double d) {
        int i = (int) (d * 65536.0d);
        byteBuffer.put((byte) (((-16777216) & i) >> 24));
        byteBuffer.put((byte) ((16711680 & i) >> 16));
        byteBuffer.put((byte) ((65280 & i) >> 8));
        byteBuffer.put((byte) (i & 255));
    }

    public static void a(ByteBuffer byteBuffer, String str) {
        if (str.getBytes().length == 3) {
            int i = 0;
            for (int i2 = 0; i2 < 3; i2++) {
                i += (str.getBytes()[i2] - 96) << ((2 - i2) * 5);
            }
            a(byteBuffer, i);
            return;
        }
        throw new IllegalArgumentException(JSUtil.QUOTE + str + "\" language string isn't exactly 3 characters long!");
    }

    public static void b(ByteBuffer byteBuffer, String str) {
        byteBuffer.put(ms.a(str));
        c(byteBuffer, 0);
    }
}
