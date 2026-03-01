package supwisdom;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.ErrorCode;
import okio.Buffer;
import okio.BufferedSink;
import supwisdom.ju1;

/* JADX INFO: compiled from: Http2Writer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pu1 implements Closeable {
    public static final Logger g = Logger.getLogger(ku1.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BufferedSink f8834a;
    public final boolean b;
    public final Buffer c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8835e;
    public final ju1.b f;

    public pu1(BufferedSink bufferedSink, boolean z) {
        this.f8834a = bufferedSink;
        this.b = z;
        Buffer buffer = new Buffer();
        this.c = buffer;
        this.f = new ju1.b(buffer);
        this.d = 16384;
    }

    public synchronized void a(su1 su1Var) throws IOException {
        if (this.f8835e) {
            throw new IOException("closed");
        }
        this.d = su1Var.c(this.d);
        if (su1Var.b() != -1) {
            this.f.b(su1Var.b());
        }
        a(0, 0, (byte) 4, (byte) 1);
        this.f8834a.flush();
    }

    public synchronized void b(su1 su1Var) throws IOException {
        if (this.f8835e) {
            throw new IOException("closed");
        }
        int i = 0;
        a(0, su1Var.d() * 6, (byte) 4, (byte) 0);
        while (i < 10) {
            if (su1Var.d(i)) {
                this.f8834a.writeShort(i == 4 ? 3 : i == 7 ? 4 : i);
                this.f8834a.writeInt(su1Var.a(i));
            }
            i++;
        }
        this.f8834a.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.f8835e = true;
        this.f8834a.close();
    }

    public synchronized void connectionPreface() throws IOException {
        if (this.f8835e) {
            throw new IOException("closed");
        }
        if (this.b) {
            if (g.isLoggable(Level.FINE)) {
                g.fine(kt1.a(">> CONNECTION %s", ku1.f8208a.hex()));
            }
            this.f8834a.write(ku1.f8208a.toByteArray());
            this.f8834a.flush();
        }
    }

    public synchronized void flush() throws IOException {
        if (this.f8835e) {
            throw new IOException("closed");
        }
        this.f8834a.flush();
    }

    public int maxDataLength() {
        return this.d;
    }

    public synchronized void ping(boolean z, int i, int i2) throws IOException {
        if (this.f8835e) {
            throw new IOException("closed");
        }
        a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.f8834a.writeInt(i);
        this.f8834a.writeInt(i2);
        this.f8834a.flush();
    }

    public synchronized void pushPromise(int i, int i2, List<iu1> list) throws IOException {
        if (this.f8835e) {
            throw new IOException("closed");
        }
        this.f.a(list);
        long size = this.c.size();
        int iMin = (int) Math.min(this.d - 4, size);
        long j = iMin;
        a(i, iMin + 4, (byte) 5, size == j ? (byte) 4 : (byte) 0);
        this.f8834a.writeInt(i2 & Integer.MAX_VALUE);
        this.f8834a.write(this.c, j);
        if (size > j) {
            a(i, size - j);
        }
    }

    public synchronized void windowUpdate(int i, long j) throws IOException {
        if (this.f8835e) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            ku1.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            throw null;
        }
        a(i, 4, (byte) 8, (byte) 0);
        this.f8834a.writeInt((int) j);
        this.f8834a.flush();
    }

    public synchronized void a(boolean z, int i, int i2, List<iu1> list) throws IOException {
        if (!this.f8835e) {
            a(z, i, list);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(int i, ErrorCode errorCode) throws IOException {
        if (!this.f8835e) {
            if (errorCode.httpCode != -1) {
                a(i, 4, (byte) 3, (byte) 0);
                this.f8834a.writeInt(errorCode.httpCode);
                this.f8834a.flush();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(boolean z, int i, Buffer buffer, int i2) throws IOException {
        if (!this.f8835e) {
            a(i, z ? (byte) 1 : (byte) 0, buffer, i2);
        } else {
            throw new IOException("closed");
        }
    }

    public void a(int i, byte b, Buffer buffer, int i2) throws IOException {
        a(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.f8834a.write(buffer, i2);
        }
    }

    public synchronized void a(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (!this.f8835e) {
            if (errorCode.httpCode != -1) {
                a(0, bArr.length + 8, (byte) 7, (byte) 0);
                this.f8834a.writeInt(i);
                this.f8834a.writeInt(errorCode.httpCode);
                if (bArr.length > 0) {
                    this.f8834a.write(bArr);
                }
                this.f8834a.flush();
            } else {
                ku1.a("errorCode.httpCode == -1", new Object[0]);
                throw null;
            }
        } else {
            throw new IOException("closed");
        }
    }

    public void a(int i, int i2, byte b, byte b2) throws IOException {
        if (g.isLoggable(Level.FINE)) {
            g.fine(ku1.a(false, i, i2, b, b2));
        }
        int i3 = this.d;
        if (i2 > i3) {
            ku1.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
            throw null;
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            ku1.a("reserved bit set: %s", Integer.valueOf(i));
            throw null;
        }
        a(this.f8834a, i2);
        this.f8834a.writeByte(b & 255);
        this.f8834a.writeByte(b2 & 255);
        this.f8834a.writeInt(i & Integer.MAX_VALUE);
    }

    public static void a(BufferedSink bufferedSink, int i) throws IOException {
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }

    public final void a(int i, long j) throws IOException {
        while (j > 0) {
            int iMin = (int) Math.min(this.d, j);
            long j2 = iMin;
            j -= j2;
            a(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f8834a.write(this.c, j2);
        }
    }

    public void a(boolean z, int i, List<iu1> list) throws IOException {
        if (!this.f8835e) {
            this.f.a(list);
            long size = this.c.size();
            int iMin = (int) Math.min(this.d, size);
            long j = iMin;
            byte b = size == j ? (byte) 4 : (byte) 0;
            if (z) {
                b = (byte) (b | 1);
            }
            a(i, iMin, (byte) 1, b);
            this.f8834a.write(this.c, j);
            if (size > j) {
                a(i, size - j);
                return;
            }
            return;
        }
        throw new IOException("closed");
    }
}
