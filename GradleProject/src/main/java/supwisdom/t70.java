package supwisdom;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: DataSourceInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class t70 extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s70 f9261a;
    public final u70 b;
    public long f;
    public boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9262e = false;
    public final byte[] c = new byte[1];

    public t70(s70 s70Var, u70 u70Var) {
        this.f9261a = s70Var;
        this.b = u70Var;
    }

    public long a() {
        return this.f;
    }

    public void b() throws IOException {
        c();
    }

    public final void c() throws IOException {
        if (this.d) {
            return;
        }
        this.f9261a.a(this.b);
        this.d = true;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f9262e) {
            return;
        }
        this.f9261a.a();
        this.f9262e = true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.c) == -1) {
            return -1;
        }
        return this.c[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        e80.b(!this.f9262e);
        c();
        int iA = this.f9261a.a(bArr, i, i2);
        if (iA == -1) {
            return -1;
        }
        this.f += (long) iA;
        return iA;
    }
}
