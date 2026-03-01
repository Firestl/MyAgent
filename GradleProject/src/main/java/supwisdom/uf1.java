package supwisdom;

import java.io.IOException;
import java.net.ProtocolException;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

/* JADX INFO: compiled from: RetryableSink.java */
/* JADX INFO: loaded from: classes2.dex */
public final class uf1 implements Sink {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9408a;
    public final int b;
    public final Buffer c;

    public uf1(int i) {
        this.c = new Buffer();
        this.b = i;
    }

    public long a() throws IOException {
        return this.c.size();
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f9408a) {
            return;
        }
        this.f9408a = true;
        if (this.c.size() >= this.b) {
            return;
        }
        throw new ProtocolException("content-length promised " + this.b + " bytes, but received " + this.c.size());
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (this.f9408a) {
            throw new IllegalStateException("closed");
        }
        gf1.a(buffer.size(), 0L, j);
        if (this.b == -1 || this.c.size() <= ((long) this.b) - j) {
            this.c.write(buffer, j);
            return;
        }
        throw new ProtocolException("exceeded content-length limit of " + this.b + " bytes");
    }

    public void a(Sink sink) throws IOException {
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.c;
        buffer2.copyTo(buffer, 0L, buffer2.size());
        sink.write(buffer, buffer.size());
    }

    public uf1() {
        this(-1);
    }
}
