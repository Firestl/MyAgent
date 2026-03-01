package supwisdom;

import android.support.v4.media.session.PlaybackStateCompat;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.JSUtil;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import supwisdom.dt1;
import supwisdom.us1;

/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hu1 implements yt1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final zs1 f7870a;
    public final vt1 b;
    public final BufferedSource c;
    public final BufferedSink d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7871e = 0;
    public long f = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;

    /* JADX INFO: compiled from: Http1Codec.java */
    public abstract class b implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ForwardingTimeout f7872a;
        public boolean b;
        public long c;

        public b() {
            this.f7872a = new ForwardingTimeout(hu1.this.c.timeout());
            this.c = 0L;
        }

        public final void a(boolean z, IOException iOException) throws IOException {
            hu1 hu1Var = hu1.this;
            int i = hu1Var.f7871e;
            if (i == 6) {
                return;
            }
            if (i != 5) {
                throw new IllegalStateException("state: " + hu1.this.f7871e);
            }
            hu1Var.a(this.f7872a);
            hu1 hu1Var2 = hu1.this;
            hu1Var2.f7871e = 6;
            vt1 vt1Var = hu1Var2.b;
            if (vt1Var != null) {
                vt1Var.a(!z, hu1Var2, this.c, iOException);
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            try {
                long j2 = hu1.this.c.read(buffer, j);
                if (j2 > 0) {
                    this.c += j2;
                }
                return j2;
            } catch (IOException e2) {
                a(false, e2);
                throw e2;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.f7872a;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public final class c implements Sink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ForwardingTimeout f7873a;
        public boolean b;

        public c() {
            this.f7873a = new ForwardingTimeout(hu1.this.d.timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (this.b) {
                return;
            }
            this.b = true;
            hu1.this.d.writeUtf8("0\r\n\r\n");
            hu1.this.a(this.f7873a);
            hu1.this.f7871e = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (this.b) {
                return;
            }
            hu1.this.d.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.f7873a;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            hu1.this.d.writeHexadecimalUnsignedLong(j);
            hu1.this.d.writeUtf8(Base64.CRLF);
            hu1.this.d.write(buffer, j);
            hu1.this.d.writeUtf8(Base64.CRLF);
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class d extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final vs1 f7874e;
        public long f;
        public boolean g;

        public d(vs1 vs1Var) {
            super();
            this.f = -1L;
            this.g = true;
            this.f7874e = vs1Var;
        }

        public final void a() throws IOException {
            if (this.f != -1) {
                hu1.this.c.readUtf8LineStrict();
            }
            try {
                this.f = hu1.this.c.readHexadecimalUnsignedLong();
                String strTrim = hu1.this.c.readUtf8LineStrict().trim();
                if (this.f < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f + strTrim + JSUtil.QUOTE);
                }
                if (this.f == 0) {
                    this.g = false;
                    au1.a(hu1.this.f7870a.g(), this.f7874e, hu1.this.d());
                    a(true, null);
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            if (this.g && !kt1.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, null);
            }
            this.b = true;
        }

        @Override // supwisdom.hu1.b, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            if (!this.g) {
                return -1L;
            }
            long j2 = this.f;
            if (j2 == 0 || j2 == -1) {
                a();
                if (!this.g) {
                    return -1L;
                }
            }
            long j3 = super.read(buffer, Math.min(j, this.f));
            if (j3 != -1) {
                this.f -= j3;
                return j3;
            }
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            a(false, protocolException);
            throw protocolException;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public final class e implements Sink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ForwardingTimeout f7875a;
        public boolean b;
        public long c;

        public e(long j) {
            this.f7875a = new ForwardingTimeout(hu1.this.d.timeout());
            this.c = j;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            this.b = true;
            if (this.c > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            hu1.this.a(this.f7875a);
            hu1.this.f7871e = 3;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.b) {
                return;
            }
            hu1.this.d.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.f7875a;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            kt1.a(buffer.size(), 0L, j);
            if (j <= this.c) {
                hu1.this.d.write(buffer, j);
                this.c -= j;
                return;
            }
            throw new ProtocolException("expected " + this.c + " bytes but received " + j);
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class f extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f7876e;

        public f(hu1 hu1Var, long j) throws IOException {
            super();
            this.f7876e = j;
            if (j == 0) {
                a(true, null);
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            if (this.f7876e != 0 && !kt1.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, null);
            }
            this.b = true;
        }

        @Override // supwisdom.hu1.b, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.f7876e;
            if (j2 == 0) {
                return -1L;
            }
            long j3 = super.read(buffer, Math.min(j2, j));
            if (j3 == -1) {
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                a(false, protocolException);
                throw protocolException;
            }
            long j4 = this.f7876e - j3;
            this.f7876e = j4;
            if (j4 == 0) {
                a(true, null);
            }
            return j3;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class g extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f7877e;

        public g(hu1 hu1Var) {
            super();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.b) {
                return;
            }
            if (!this.f7877e) {
                a(false, null);
            }
            this.b = true;
        }

        @Override // supwisdom.hu1.b, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.b) {
                throw new IllegalStateException("closed");
            }
            if (this.f7877e) {
                return -1L;
            }
            long j2 = super.read(buffer, j);
            if (j2 != -1) {
                return j2;
            }
            this.f7877e = true;
            a(true, null);
            return -1L;
        }
    }

    public hu1(zs1 zs1Var, vt1 vt1Var, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.f7870a = zs1Var;
        this.b = vt1Var;
        this.c = bufferedSource;
        this.d = bufferedSink;
    }

    @Override // supwisdom.yt1
    public Sink a(bt1 bt1Var, long j) {
        if ("chunked".equalsIgnoreCase(bt1Var.a("Transfer-Encoding"))) {
            return a();
        }
        if (j != -1) {
            return a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public Source b(long j) throws IOException {
        if (this.f7871e == 4) {
            this.f7871e = 5;
            return new f(this, j);
        }
        throw new IllegalStateException("state: " + this.f7871e);
    }

    public final String c() throws IOException {
        String utf8LineStrict = this.c.readUtf8LineStrict(this.f);
        this.f -= (long) utf8LineStrict.length();
        return utf8LineStrict;
    }

    @Override // supwisdom.yt1
    public void cancel() {
        st1 st1VarC = this.b.c();
        if (st1VarC != null) {
            st1VarC.a();
        }
    }

    public us1 d() throws IOException {
        us1.a aVar = new us1.a();
        while (true) {
            String strC = c();
            if (strC.length() == 0) {
                return aVar.a();
            }
            it1.f7984a.a(aVar, strC);
        }
    }

    @Override // supwisdom.yt1
    public void finishRequest() throws IOException {
        this.d.flush();
    }

    @Override // supwisdom.yt1
    public void flushRequest() throws IOException {
        this.d.flush();
    }

    @Override // supwisdom.yt1
    public dt1.a readResponseHeaders(boolean z) throws IOException {
        int i = this.f7871e;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("state: " + this.f7871e);
        }
        try {
            gu1 gu1VarA = gu1.a(c());
            dt1.a aVar = new dt1.a();
            aVar.a(gu1VarA.f7773a);
            aVar.a(gu1VarA.b);
            aVar.a(gu1VarA.c);
            aVar.a(d());
            if (z && gu1VarA.b == 100) {
                return null;
            }
            if (gu1VarA.b == 100) {
                this.f7871e = 3;
                return aVar;
            }
            this.f7871e = 4;
            return aVar;
        } catch (EOFException e2) {
            IOException iOException = new IOException("unexpected end of stream on " + this.b);
            iOException.initCause(e2);
            throw iOException;
        }
    }

    @Override // supwisdom.yt1
    public void a(bt1 bt1Var) throws IOException {
        a(bt1Var.c(), eu1.a(bt1Var, this.b.c().e().b().type()));
    }

    public Source b() throws IOException {
        if (this.f7871e == 4) {
            vt1 vt1Var = this.b;
            if (vt1Var != null) {
                this.f7871e = 5;
                vt1Var.e();
                return new g(this);
            }
            throw new IllegalStateException("streamAllocation == null");
        }
        throw new IllegalStateException("state: " + this.f7871e);
    }

    @Override // supwisdom.yt1
    public et1 a(dt1 dt1Var) throws IOException {
        vt1 vt1Var = this.b;
        vt1Var.f.responseBodyStart(vt1Var.f9549e);
        String strA = dt1Var.a("Content-Type");
        if (!au1.b(dt1Var)) {
            return new du1(strA, 0L, Okio.buffer(b(0L)));
        }
        if ("chunked".equalsIgnoreCase(dt1Var.a("Transfer-Encoding"))) {
            return new du1(strA, -1L, Okio.buffer(a(dt1Var.k().g())));
        }
        long jA = au1.a(dt1Var);
        if (jA != -1) {
            return new du1(strA, jA, Okio.buffer(b(jA)));
        }
        return new du1(strA, -1L, Okio.buffer(b()));
    }

    public void a(us1 us1Var, String str) throws IOException {
        if (this.f7871e == 0) {
            this.d.writeUtf8(str).writeUtf8(Base64.CRLF);
            int iC = us1Var.c();
            for (int i = 0; i < iC; i++) {
                this.d.writeUtf8(us1Var.a(i)).writeUtf8(": ").writeUtf8(us1Var.b(i)).writeUtf8(Base64.CRLF);
            }
            this.d.writeUtf8(Base64.CRLF);
            this.f7871e = 1;
            return;
        }
        throw new IllegalStateException("state: " + this.f7871e);
    }

    public Sink a() {
        if (this.f7871e == 1) {
            this.f7871e = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.f7871e);
    }

    public Sink a(long j) {
        if (this.f7871e == 1) {
            this.f7871e = 2;
            return new e(j);
        }
        throw new IllegalStateException("state: " + this.f7871e);
    }

    public Source a(vs1 vs1Var) throws IOException {
        if (this.f7871e == 4) {
            this.f7871e = 5;
            return new d(vs1Var);
        }
        throw new IllegalStateException("state: " + this.f7871e);
    }

    public void a(ForwardingTimeout forwardingTimeout) {
        Timeout timeoutDelegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        timeoutDelegate.clearDeadline();
        timeoutDelegate.clearTimeout();
    }
}
