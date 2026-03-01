package dc.squareup.okhttp3.internal.cache;

import dc.squareup.okio.Buffer;
import dc.squareup.okio.ForwardingSink;
import dc.squareup.okio.Sink;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class FaultHidingSink extends ForwardingSink {
    public boolean hasErrors;

    public FaultHidingSink(Sink sink) {
        super(sink);
    }

    @Override // dc.squareup.okio.ForwardingSink, dc.squareup.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }

    @Override // dc.squareup.okio.ForwardingSink, dc.squareup.okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }

    public void onException(IOException iOException) {
    }

    @Override // dc.squareup.okio.ForwardingSink, dc.squareup.okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (this.hasErrors) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }
}
