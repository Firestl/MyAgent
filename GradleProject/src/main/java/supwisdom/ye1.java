package supwisdom;

import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

/* JADX INFO: compiled from: FaultHidingSink.java */
/* JADX INFO: loaded from: classes2.dex */
public class ye1 extends ForwardingSink {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9882a;

    public ye1(Sink sink) {
        super(sink);
    }

    public void a(IOException iOException) {
        throw null;
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f9882a) {
            return;
        }
        try {
            super.close();
        } catch (IOException e2) {
            this.f9882a = true;
            a(e2);
        }
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (this.f9882a) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e2) {
            this.f9882a = true;
            a(e2);
        }
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (this.f9882a) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e2) {
            this.f9882a = true;
            a(e2);
        }
    }
}
