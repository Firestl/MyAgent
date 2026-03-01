package supwisdom;

import dc.squareup.okhttp3.internal.ws.WebSocketProtocol;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

/* JADX INFO: compiled from: WebSocketWriter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class iv1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7989a;
    public final Random b;
    public final BufferedSink c;
    public final Buffer d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7990e;
    public final Buffer f = new Buffer();
    public final a g = new a();
    public boolean h;
    public final byte[] i;
    public final Buffer.UnsafeCursor j;

    /* JADX INFO: compiled from: WebSocketWriter.java */
    public final class a implements Sink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7991a;
        public long b;
        public boolean c;
        public boolean d;

        public a() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            iv1 iv1Var = iv1.this;
            iv1Var.a(this.f7991a, iv1Var.f.size(), this.c, true);
            this.d = true;
            iv1.this.h = false;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            iv1 iv1Var = iv1.this;
            iv1Var.a(this.f7991a, iv1Var.f.size(), this.c, false);
            this.c = false;
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return iv1.this.c.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            iv1.this.f.write(buffer, j);
            boolean z = this.c && this.b != -1 && iv1.this.f.size() > this.b - 8192;
            long jCompleteSegmentByteCount = iv1.this.f.completeSegmentByteCount();
            if (jCompleteSegmentByteCount <= 0 || z) {
                return;
            }
            iv1.this.a(this.f7991a, jCompleteSegmentByteCount, this.c, false);
            this.c = false;
        }
    }

    public iv1(boolean z, BufferedSink bufferedSink, Random random) {
        if (bufferedSink == null) {
            throw new NullPointerException("sink == null");
        }
        if (random == null) {
            throw new NullPointerException("random == null");
        }
        this.f7989a = z;
        this.c = bufferedSink;
        this.d = bufferedSink.buffer();
        this.b = random;
        this.i = z ? new byte[4] : null;
        this.j = z ? new Buffer.UnsafeCursor() : null;
    }

    public void a(ByteString byteString) throws IOException {
        b(9, byteString);
    }

    public void b(ByteString byteString) throws IOException {
        b(10, byteString);
    }

    public void a(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (i != 0 || byteString != null) {
            if (i != 0) {
                gv1.b(i);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            b(8, byteString2);
        } finally {
            this.f7990e = true;
        }
    }

    public final void b(int i, ByteString byteString) throws IOException {
        if (this.f7990e) {
            throw new IOException("closed");
        }
        int size = byteString.size();
        if (size > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.d.writeByte(i | 128);
        if (this.f7989a) {
            this.d.writeByte(size | 128);
            this.b.nextBytes(this.i);
            this.d.write(this.i);
            if (size > 0) {
                long size2 = this.d.size();
                this.d.write(byteString);
                this.d.readAndWriteUnsafe(this.j);
                this.j.seek(size2);
                gv1.a(this.j, this.i);
                this.j.close();
            }
        } else {
            this.d.writeByte(size);
            this.d.write(byteString);
        }
        this.c.flush();
    }

    public Sink a(int i, long j) {
        if (!this.h) {
            this.h = true;
            a aVar = this.g;
            aVar.f7991a = i;
            aVar.b = j;
            aVar.c = true;
            aVar.d = false;
            return aVar;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }

    public void a(int i, long j, boolean z, boolean z2) throws IOException {
        if (!this.f7990e) {
            if (!z) {
                i = 0;
            }
            if (z2) {
                i |= 128;
            }
            this.d.writeByte(i);
            int i2 = this.f7989a ? 128 : 0;
            if (j <= 125) {
                this.d.writeByte(((int) j) | i2);
            } else if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                this.d.writeByte(i2 | 126);
                this.d.writeShort((int) j);
            } else {
                this.d.writeByte(i2 | 127);
                this.d.writeLong(j);
            }
            if (this.f7989a) {
                this.b.nextBytes(this.i);
                this.d.write(this.i);
                if (j > 0) {
                    long size = this.d.size();
                    this.d.write(this.f, j);
                    this.d.readAndWriteUnsafe(this.j);
                    this.j.seek(size);
                    gv1.a(this.j, this.i);
                    this.j.close();
                }
            } else {
                this.d.write(this.f, j);
            }
            this.c.emit();
            return;
        }
        throw new IOException("closed");
    }
}
