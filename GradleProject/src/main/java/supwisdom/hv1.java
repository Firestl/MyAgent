package supwisdom;

import dc.squareup.okhttp3.internal.ws.WebSocketProtocol;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: WebSocketReader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hv1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7880a;
    public final BufferedSource b;
    public final a c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7881e;
    public long f;
    public boolean g;
    public boolean h;
    public final Buffer i = new Buffer();
    public final Buffer j = new Buffer();
    public final byte[] k;
    public final Buffer.UnsafeCursor l;

    /* JADX INFO: compiled from: WebSocketReader.java */
    public interface a {
        void a(ByteString byteString) throws IOException;

        void b(ByteString byteString);

        void c(ByteString byteString);

        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;
    }

    public hv1(boolean z, BufferedSource bufferedSource, a aVar) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        }
        if (aVar == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.f7880a = z;
        this.b = bufferedSource;
        this.c = aVar;
        this.k = z ? null : new byte[4];
        this.l = z ? null : new Buffer.UnsafeCursor();
    }

    public void a() throws IOException {
        c();
        if (this.h) {
            b();
        } else {
            e();
        }
    }

    public final void b() throws IOException {
        String utf8;
        long j = this.f;
        if (j > 0) {
            this.b.readFully(this.i, j);
            if (!this.f7880a) {
                this.i.readAndWriteUnsafe(this.l);
                this.l.seek(0L);
                gv1.a(this.l, this.k);
                this.l.close();
            }
        }
        switch (this.f7881e) {
            case 8:
                short s = 1005;
                long size = this.i.size();
                if (size == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (size != 0) {
                    s = this.i.readShort();
                    utf8 = this.i.readUtf8();
                    String strA = gv1.a(s);
                    if (strA != null) {
                        throw new ProtocolException(strA);
                    }
                } else {
                    utf8 = "";
                }
                this.c.onReadClose(s, utf8);
                this.d = true;
                return;
            case 9:
                this.c.b(this.i.readByteString());
                return;
            case 10:
                this.c.c(this.i.readByteString());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.f7881e));
        }
    }

    /* JADX WARN: Finally extract failed */
    public final void c() throws IOException {
        if (this.d) {
            throw new IOException("closed");
        }
        long jTimeoutNanos = this.b.timeout().timeoutNanos();
        this.b.timeout().clearTimeout();
        try {
            int i = this.b.readByte() & 255;
            this.b.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            this.f7881e = i & 15;
            this.g = (i & 128) != 0;
            boolean z = (i & 8) != 0;
            this.h = z;
            if (z && !this.g) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z2 = (i & 64) != 0;
            boolean z3 = (i & 32) != 0;
            boolean z4 = (i & 16) != 0;
            if (z2 || z3 || z4) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            int i2 = this.b.readByte() & 255;
            boolean z5 = (i2 & 128) != 0;
            if (z5 == this.f7880a) {
                throw new ProtocolException(this.f7880a ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
            }
            long j = i2 & 127;
            this.f = j;
            if (j == 126) {
                this.f = ((long) this.b.readShort()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            } else if (j == 127) {
                long j2 = this.b.readLong();
                this.f = j2;
                if (j2 < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.f) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            if (this.h && this.f > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (z5) {
                this.b.readFully(this.k);
            }
        } catch (Throwable th) {
            this.b.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    public final void d() throws IOException {
        while (!this.d) {
            long j = this.f;
            if (j > 0) {
                this.b.readFully(this.j, j);
                if (!this.f7880a) {
                    this.j.readAndWriteUnsafe(this.l);
                    this.l.seek(this.j.size() - this.f);
                    gv1.a(this.l, this.k);
                    this.l.close();
                }
            }
            if (this.g) {
                return;
            }
            f();
            if (this.f7881e != 0) {
                throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.f7881e));
            }
        }
        throw new IOException("closed");
    }

    public final void e() throws IOException {
        int i = this.f7881e;
        if (i != 1 && i != 2) {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(i));
        }
        d();
        if (i == 1) {
            this.c.onReadMessage(this.j.readUtf8());
        } else {
            this.c.a(this.j.readByteString());
        }
    }

    public final void f() throws IOException {
        while (!this.d) {
            c();
            if (!this.h) {
                return;
            } else {
                b();
            }
        }
    }
}
