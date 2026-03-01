package supwisdom;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: MarkableInputStream.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dh1 extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InputStream f7342a;
    public long b;
    public long c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f7343e;

    public dh1(InputStream inputStream) {
        this(inputStream, 4096);
    }

    public long a(int i) {
        long j = this.b + ((long) i);
        if (this.d < j) {
            b(j);
        }
        return this.b;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f7342a.available();
    }

    public final void b(long j) {
        try {
            if (this.c >= this.b || this.b > this.d) {
                this.c = this.b;
                this.f7342a.mark((int) (j - this.b));
            } else {
                this.f7342a.reset();
                this.f7342a.mark((int) (j - this.c));
                a(this.c, this.b);
            }
            this.d = j;
        } catch (IOException e2) {
            throw new IllegalStateException("Unable to mark: " + e2);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f7342a.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f7343e = a(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f7342a.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.f7342a.read();
        if (i != -1) {
            this.b++;
        }
        return i;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        a(this.f7343e);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long jSkip = this.f7342a.skip(j);
        this.b += jSkip;
        return jSkip;
    }

    public dh1(InputStream inputStream, int i) {
        this.f7343e = -1L;
        this.f7342a = inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream, i);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int i = this.f7342a.read(bArr);
        if (i != -1) {
            this.b += (long) i;
        }
        return i;
    }

    public void a(long j) throws IOException {
        if (this.b <= this.d && j >= this.c) {
            this.f7342a.reset();
            a(this.c, j);
            this.b = j;
            return;
        }
        throw new IOException("Cannot reset");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.f7342a.read(bArr, i, i2);
        if (i3 != -1) {
            this.b += (long) i3;
        }
        return i3;
    }

    public final void a(long j, long j2) throws IOException {
        while (j < j2) {
            long jSkip = this.f7342a.skip(j2 - j);
            if (jSkip == 0) {
                if (read() == -1) {
                    return;
                } else {
                    jSkip = 1;
                }
            }
            j += jSkip;
        }
    }
}
