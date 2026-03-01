package supwisdom;

import android.util.Log;
import com.taobao.weex.wson.Wson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes.dex */
public final class e10 {
    public static final byte[] b = {71, Wson.NUMBER_BIG_DECIMAL_TYPE, KeyFactorySpi.x25519_type, 117, Wson.NUMBER_INT_TYPE, KeyFactorySpi.x25519_type, Wson.NUMBER_BIG_DECIMAL_TYPE, 32, 65, Wson.NUMBER_DOUBLE_TYPE, KeyFactorySpi.x448_type, 98, Wson.NUMBER_BIG_DECIMAL_TYPE, 32, Wson.NUMBER_FLOAT_TYPE, Wson.NUMBER_LONG_TYPE, 97, Wson.STRING_TYPE, 104, 32, UTF8.S_P4A, Wson.NUMBER_LONG_TYPE, 97, 121, Wson.NUMBER_BIG_DECIMAL_TYPE, 114, 32, 48, 48, 49, -16, -18, -62, 74, com.igexin.c.a.d.g.n, 104, -66, -24, 46, 0, -48, -47, 2, -98, 126, 87, KeyFactorySpi.x25519_type, -20, 93, 45, 41, com.igexin.c.a.d.g.n, KeyFactorySpi.x448_type, -85, -109, -72, -26, 54, -49, -21, 49, -82};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f7405a;

    public final void a(OutputStream outputStream) throws IOException {
        Log.d("Handshake", "writeC0");
        outputStream.write(3);
    }

    public final void b(OutputStream outputStream) throws IOException {
        Log.d("Handshake", "writeC1");
        Log.d("Handshake", "writeC1(): Calculating digest offset");
        Random random = new Random();
        int iNextInt = random.nextInt(720);
        int i = (iNextInt % 728) + 772 + 4;
        Log.d("Handshake", "writeC1(): (real value of) digestOffset: " + iNextInt);
        Log.d("Handshake", "writeC1(): recalculated digestOffset: " + i);
        byte[] bArr = new byte[4];
        for (int i2 = 3; i2 >= 0; i2--) {
            if (iNextInt > 255) {
                bArr[i2] = -1;
                iNextInt -= 255;
            } else {
                bArr[i2] = (byte) iNextInt;
                iNextInt -= iNextInt;
            }
        }
        Log.d("Handshake", "writeC1(): digestOffsetBytes: " + j00.a(bArr));
        byte[] bArr2 = new byte[i];
        Log.d("Handshake", "partBeforeDigest(): size: " + i);
        random.nextBytes(bArr2);
        Log.d("Handshake", "writeC1(): Writing timestamp and Flash Player version");
        System.arraycopy(j00.a((int) (System.currentTimeMillis() / 1000)), 0, bArr2, 0, 4);
        System.arraycopy(new byte[]{com.igexin.c.a.d.g.n, 0, 7, 2}, 0, bArr2, 4, 4);
        int i3 = (1536 - i) - 32;
        byte[] bArr3 = new byte[i3];
        Log.d("Handshake", "partAfterDigest(): size: " + i3);
        random.nextBytes(bArr3);
        Log.d("Handshake", "copying digest offset bytes in partBeforeDigest");
        System.arraycopy(bArr, 0, bArr2, 772, 4);
        Log.d("Handshake", "writeC1(): Calculating digest");
        byte[] bArr4 = new byte[1504];
        System.arraycopy(bArr2, 0, bArr4, 0, i);
        System.arraycopy(bArr3, 0, bArr4, i, i3);
        byte[] bArrA = new g00().a(bArr4, b, 30);
        Log.d("Handshake", "writeC1(): writing C1 packet");
        outputStream.write(bArr2);
        outputStream.write(bArrA);
        outputStream.write(bArr3);
    }

    public final void c(OutputStream outputStream) throws IOException {
        Log.d("Handshake", "writeC2");
        byte[] bArr = this.f7405a;
        if (bArr == null) {
            throw new IllegalStateException("C2 cannot be written without S1 being read first");
        }
        outputStream.write(bArr);
    }

    public final void a(InputStream inputStream) throws IOException {
        Log.d("Handshake", "readS0");
        byte b2 = (byte) inputStream.read();
        if (b2 != 3) {
            if (b2 == -1) {
                throw new IOException("InputStream closed");
            }
            throw new IOException("Invalid RTMP protocol version; expected 3, got " + ((int) b2));
        }
    }

    public final void c(InputStream inputStream) throws IOException {
        int i;
        Log.d("Handshake", "readS2");
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[1528];
        int i2 = 0;
        int i3 = 0;
        do {
            int i4 = inputStream.read(bArr, i3, 4 - i3);
            if (i4 == -1) {
                throw new IOException("Unexpected EOF while reading S2 bytes 0-3");
            }
            i3 += i4;
        } while (i3 < 4);
        int i5 = 0;
        do {
            int i6 = inputStream.read(bArr2, i5, 4 - i5);
            if (i6 == -1) {
                throw new IOException("Unexpected EOF while reading S2 bytes 4-7");
            }
            i5 += i6;
        } while (i5 < 4);
        do {
            i = inputStream.read(bArr3, i2, 1528 - i2);
            if (i != -1) {
                i2 += i;
            }
            if (i2 >= 1528) {
                break;
            }
        } while (i != -1);
        if (i2 == 1528) {
            Log.d("Handshake", "readS2(): S2 total bytes read OK");
            return;
        }
        throw new IOException("Unexpected EOF while reading remainder of S2, expected 1528 bytes, but only read " + i2 + " bytes");
    }

    public final void b(InputStream inputStream) throws IOException {
        Log.d("Handshake", "readS1");
        this.f7405a = new byte[1536];
        int i = 0;
        do {
            int i2 = inputStream.read(this.f7405a, i, 1536 - i);
            if (i2 != -1) {
                i += i2;
            }
        } while (i < 1536);
        if (i == 1536) {
            Log.d("Handshake", "readS1(): S1 total bytes read OK");
            return;
        }
        throw new IOException("Unexpected EOF while reading S1, expected 1536 bytes, but only read " + i + " bytes");
    }
}
