package supwisdom;

import io.dcloud.common.util.Base64;
import io.dcloud.common.util.JSUtil;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import supwisdom.oe1;
import supwisdom.ue1;

/* JADX INFO: compiled from: HttpConnection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mf1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final je1 f8380a;
    public final ie1 b;
    public final Socket c;
    public final BufferedSource d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final BufferedSink f8381e;
    public int f = 0;
    public int g = 0;

    /* JADX INFO: compiled from: HttpConnection.java */
    public final class c implements Sink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8383a;

        public c() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (this.f8383a) {
                return;
            }
            this.f8383a = true;
            mf1.this.f8381e.writeUtf8("0\r\n\r\n");
            mf1.this.f = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (this.f8383a) {
                return;
            }
            mf1.this.f8381e.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return mf1.this.f8381e.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.f8383a) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            mf1.this.f8381e.writeHexadecimalUnsignedLong(j);
            mf1.this.f8381e.writeUtf8(Base64.CRLF);
            mf1.this.f8381e.write(buffer, j);
            mf1.this.f8381e.writeUtf8(Base64.CRLF);
        }
    }

    /* JADX INFO: compiled from: HttpConnection.java */
    public class d extends b {
        public long c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final of1 f8384e;

        public d(of1 of1Var) throws IOException {
            super();
            this.c = -1L;
            this.d = true;
            this.f8384e = of1Var;
        }

        public final void b() throws IOException {
            if (this.c != -1) {
                mf1.this.d.readUtf8LineStrict();
            }
            try {
                this.c = mf1.this.d.readHexadecimalUnsignedLong();
                String strTrim = mf1.this.d.readUtf8LineStrict().trim();
                if (this.c < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.c + strTrim + JSUtil.QUOTE);
                }
                if (this.c == 0) {
                    this.d = false;
                    oe1.b bVar = new oe1.b();
                    mf1.this.a(bVar);
                    this.f8384e.a(bVar.a());
                    a(true);
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f8382a) {
                return;
            }
            if (this.d && !gf1.a(this, 100, TimeUnit.MILLISECONDS)) {
                a();
            }
            this.f8382a = true;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f8382a) {
                throw new IllegalStateException("closed");
            }
            if (!this.d) {
                return -1L;
            }
            long j2 = this.c;
            if (j2 == 0 || j2 == -1) {
                b();
                if (!this.d) {
                    return -1L;
                }
            }
            long j3 = mf1.this.d.read(buffer, Math.min(j, this.c));
            if (j3 != -1) {
                this.c -= j3;
                return j3;
            }
            a();
            throw new IOException("unexpected end of stream");
        }
    }

    /* JADX INFO: compiled from: HttpConnection.java */
    public final class e implements Sink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8385a;
        public long b;

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f8385a) {
                return;
            }
            this.f8385a = true;
            if (this.b > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            mf1.this.f = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.f8385a) {
                return;
            }
            mf1.this.f8381e.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return mf1.this.f8381e.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.f8385a) {
                throw new IllegalStateException("closed");
            }
            gf1.a(buffer.size(), 0L, j);
            if (j <= this.b) {
                mf1.this.f8381e.write(buffer, j);
                this.b -= j;
                return;
            }
            throw new ProtocolException("expected " + this.b + " bytes but received " + j);
        }

        public e(long j) {
            this.b = j;
        }
    }

    /* JADX INFO: compiled from: HttpConnection.java */
    public class f extends b {
        public long c;

        public f(long j) throws IOException {
            super();
            this.c = j;
            if (j == 0) {
                a(true);
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f8382a) {
                return;
            }
            if (this.c != 0 && !gf1.a(this, 100, TimeUnit.MILLISECONDS)) {
                a();
            }
            this.f8382a = true;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f8382a) {
                throw new IllegalStateException("closed");
            }
            if (this.c == 0) {
                return -1L;
            }
            long j2 = mf1.this.d.read(buffer, Math.min(this.c, j));
            if (j2 == -1) {
                a();
                throw new ProtocolException("unexpected end of stream");
            }
            long j3 = this.c - j2;
            this.c = j3;
            if (j3 == 0) {
                a(true);
            }
            return j2;
        }
    }

    /* JADX INFO: compiled from: HttpConnection.java */
    public class g extends b {
        public boolean c;

        public g() {
            super();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f8382a) {
                return;
            }
            if (!this.c) {
                a();
            }
            this.f8382a = true;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f8382a) {
                throw new IllegalStateException("closed");
            }
            if (this.c) {
                return -1L;
            }
            long j2 = mf1.this.d.read(buffer, j);
            if (j2 != -1) {
                return j2;
            }
            this.c = true;
            a(false);
            return -1L;
        }
    }

    public mf1(je1 je1Var, ie1 ie1Var, Socket socket) throws IOException {
        this.f8380a = je1Var;
        this.b = ie1Var;
        this.c = socket;
        this.d = Okio.buffer(Okio.source(socket));
        this.f8381e = Okio.buffer(Okio.sink(socket));
    }

    public Source g() throws IOException {
        if (this.f == 4) {
            this.f = 5;
            return new g();
        }
        throw new IllegalStateException("state: " + this.f);
    }

    public void h() {
        this.g = 1;
        if (this.f == 0) {
            this.g = 0;
            ze1.b.a(this.f8380a, this.b);
        }
    }

    public ue1.b i() throws IOException {
        xf1 xf1VarA;
        ue1.b bVar;
        int i = this.f;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("state: " + this.f);
        }
        do {
            try {
                xf1VarA = xf1.a(this.d.readUtf8LineStrict());
                bVar = new ue1.b();
                bVar.a(xf1VarA.f9762a);
                bVar.a(xf1VarA.b);
                bVar.a(xf1VarA.c);
                oe1.b bVar2 = new oe1.b();
                a(bVar2);
                bVar2.a(rf1.f9058e, xf1VarA.f9762a.toString());
                bVar.a(bVar2.a());
            } catch (EOFException e2) {
                IOException iOException = new IOException("unexpected end of stream on " + this.b + " (recycle count=" + ze1.b.c(this.b) + ")");
                iOException.initCause(e2);
                throw iOException;
            }
        } while (xf1VarA.b == 100);
        this.f = 4;
        return bVar;
    }

    public void c() throws IOException {
        this.f8381e.flush();
    }

    public boolean d() {
        return this.f == 6;
    }

    public boolean e() {
        try {
            int soTimeout = this.c.getSoTimeout();
            try {
                this.c.setSoTimeout(1);
                return !this.d.exhausted();
            } finally {
                this.c.setSoTimeout(soTimeout);
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public Sink f() {
        if (this.f == 1) {
            this.f = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.f);
    }

    public void a(int i, int i2) {
        if (i != 0) {
            this.d.timeout().timeout(i, TimeUnit.MILLISECONDS);
        }
        if (i2 != 0) {
            this.f8381e.timeout().timeout(i2, TimeUnit.MILLISECONDS);
        }
    }

    public void b() throws IOException {
        this.g = 2;
        if (this.f == 0) {
            this.f = 6;
            this.b.f().close();
        }
    }

    public long a() {
        return this.d.buffer().size();
    }

    public void a(oe1 oe1Var, String str) throws IOException {
        if (this.f == 0) {
            this.f8381e.writeUtf8(str).writeUtf8(Base64.CRLF);
            int iB = oe1Var.b();
            for (int i = 0; i < iB; i++) {
                this.f8381e.writeUtf8(oe1Var.a(i)).writeUtf8(": ").writeUtf8(oe1Var.b(i)).writeUtf8(Base64.CRLF);
            }
            this.f8381e.writeUtf8(Base64.CRLF);
            this.f = 1;
            return;
        }
        throw new IllegalStateException("state: " + this.f);
    }

    public Source b(long j) throws IOException {
        if (this.f == 4) {
            this.f = 5;
            return new f(j);
        }
        throw new IllegalStateException("state: " + this.f);
    }

    /* JADX INFO: compiled from: HttpConnection.java */
    public abstract class b implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8382a;

        public b() {
        }

        public final void a(boolean z) throws IOException {
            if (mf1.this.f != 5) {
                throw new IllegalStateException("state: " + mf1.this.f);
            }
            mf1.this.f = 0;
            if (z && mf1.this.g == 1) {
                mf1.this.g = 0;
                ze1.b.a(mf1.this.f8380a, mf1.this.b);
            } else if (mf1.this.g == 2) {
                mf1.this.f = 6;
                mf1.this.b.f().close();
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return mf1.this.d.timeout();
        }

        public final void a() {
            gf1.a(mf1.this.b.f());
            mf1.this.f = 6;
        }
    }

    public void a(oe1.b bVar) throws IOException {
        while (true) {
            String utf8LineStrict = this.d.readUtf8LineStrict();
            if (utf8LineStrict.length() == 0) {
                return;
            } else {
                ze1.b.a(bVar, utf8LineStrict);
            }
        }
    }

    public Sink a(long j) {
        if (this.f == 1) {
            this.f = 2;
            return new e(j);
        }
        throw new IllegalStateException("state: " + this.f);
    }

    public void a(uf1 uf1Var) throws IOException {
        if (this.f == 1) {
            this.f = 3;
            uf1Var.a(this.f8381e);
        } else {
            throw new IllegalStateException("state: " + this.f);
        }
    }

    public Source a(of1 of1Var) throws IOException {
        if (this.f == 4) {
            this.f = 5;
            return new d(of1Var);
        }
        throw new IllegalStateException("state: " + this.f);
    }
}
