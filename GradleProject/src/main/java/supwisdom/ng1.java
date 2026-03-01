package supwisdom;

import com.squareup.okhttp.internal.spdy.ErrorCode;
import com.squareup.okhttp.internal.spdy.HeadersMode;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: SpdyStream.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ng1 {
    public long b;
    public final int c;
    public final mg1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<cg1> f8514e;
    public final c f;
    public final b g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f8513a = 0;
    public final d h = new d();
    public final d i = new d();
    public ErrorCode j = null;

    /* JADX INFO: compiled from: SpdyStream.java */
    public final class b implements Sink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Buffer f8515a = new Buffer();
        public boolean b;
        public boolean c;

        public b() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (ng1.this) {
                if (this.b) {
                    return;
                }
                if (!ng1.this.g.c) {
                    if (this.f8515a.size() > 0) {
                        while (this.f8515a.size() > 0) {
                            a(true);
                        }
                    } else {
                        ng1.this.d.a(ng1.this.c, true, (Buffer) null, 0L);
                    }
                }
                synchronized (ng1.this) {
                    this.b = true;
                }
                ng1.this.d.flush();
                ng1.this.a();
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (ng1.this) {
                ng1.this.b();
            }
            while (this.f8515a.size() > 0) {
                a(false);
            }
            ng1.this.d.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return ng1.this.i;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            this.f8515a.write(buffer, j);
            while (this.f8515a.size() >= 16384) {
                a(false);
            }
        }

        public final void a(boolean z) throws IOException {
            long jMin;
            synchronized (ng1.this) {
                ng1.this.i.enter();
                while (ng1.this.b <= 0 && !this.c && !this.b && ng1.this.j == null) {
                    try {
                        ng1.this.k();
                    } catch (Throwable th) {
                        ng1.this.i.a();
                        throw th;
                    }
                }
                ng1.this.i.a();
                ng1.this.b();
                jMin = Math.min(ng1.this.b, this.f8515a.size());
                ng1.this.b -= jMin;
            }
            ng1.this.d.a(ng1.this.c, z && jMin == this.f8515a.size(), this.f8515a, jMin);
        }
    }

    /* JADX INFO: compiled from: SpdyStream.java */
    public final class c implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Buffer f8516a;
        public final Buffer b;
        public final long c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8517e;

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (ng1.this) {
                this.d = true;
                this.b.clear();
                ng1.this.notifyAll();
            }
            ng1.this.a();
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (ng1.this) {
                b();
                a();
                if (this.b.size() == 0) {
                    return -1L;
                }
                long j2 = this.b.read(buffer, Math.min(j, this.b.size()));
                ng1.this.f8513a += j2;
                if (ng1.this.f8513a >= ng1.this.d.o.c(65536) / 2) {
                    ng1.this.d.a(ng1.this.c, ng1.this.f8513a);
                    ng1.this.f8513a = 0L;
                }
                synchronized (ng1.this.d) {
                    ng1.this.d.m += j2;
                    if (ng1.this.d.m >= ng1.this.d.o.c(65536) / 2) {
                        ng1.this.d.a(0, ng1.this.d.m);
                        ng1.this.d.m = 0L;
                    }
                }
                return j2;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return ng1.this.h;
        }

        public c(long j) {
            this.f8516a = new Buffer();
            this.b = new Buffer();
            this.c = j;
        }

        public final void b() throws IOException {
            ng1.this.h.enter();
            while (this.b.size() == 0 && !this.f8517e && !this.d && ng1.this.j == null) {
                try {
                    ng1.this.k();
                } finally {
                    ng1.this.h.a();
                }
            }
        }

        public void a(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (ng1.this) {
                    z = this.f8517e;
                    z2 = true;
                    z3 = this.b.size() + j > this.c;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    ng1.this.c(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    bufferedSource.skip(j);
                    return;
                }
                long j2 = bufferedSource.read(this.f8516a, j);
                if (j2 != -1) {
                    j -= j2;
                    synchronized (ng1.this) {
                        if (this.b.size() != 0) {
                            z2 = false;
                        }
                        this.b.writeAll(this.f8516a);
                        if (z2) {
                            ng1.this.notifyAll();
                        }
                    }
                } else {
                    throw new EOFException();
                }
            }
        }

        public final void a() throws IOException {
            if (!this.d) {
                if (ng1.this.j == null) {
                    return;
                }
                throw new IOException("stream was reset: " + ng1.this.j);
            }
            throw new IOException("stream closed");
        }
    }

    /* JADX INFO: compiled from: SpdyStream.java */
    public class d extends AsyncTimeout {
        public d() {
        }

        public void a() throws InterruptedIOException {
            if (exit()) {
                throw new InterruptedIOException("timeout");
            }
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            ng1.this.c(ErrorCode.CANCEL);
        }
    }

    public ng1(int i, mg1 mg1Var, boolean z, boolean z2, List<cg1> list) {
        if (mg1Var == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.c = i;
        this.d = mg1Var;
        this.b = mg1Var.p.c(65536);
        this.f = new c(mg1Var.o.c(65536));
        this.g = new b();
        this.f.f8517e = z2;
        this.g.c = z;
    }

    public Timeout i() {
        return this.h;
    }

    public void j() {
        boolean zH;
        synchronized (this) {
            this.f.f8517e = true;
            zH = h();
            notifyAll();
        }
        if (zH) {
            return;
        }
        this.d.d(this.c);
    }

    public final void k() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    public void a(ErrorCode errorCode) throws IOException {
        if (b(errorCode)) {
            this.d.c(this.c, errorCode);
        }
    }

    public final boolean b(ErrorCode errorCode) {
        synchronized (this) {
            if (this.j != null) {
                return false;
            }
            if (this.f.f8517e && this.g.c) {
                return false;
            }
            this.j = errorCode;
            notifyAll();
            this.d.d(this.c);
            return true;
        }
    }

    public int c() {
        return this.c;
    }

    public synchronized List<cg1> d() throws IOException {
        this.h.enter();
        while (this.f8514e == null && this.j == null) {
            try {
                k();
            } catch (Throwable th) {
                this.h.a();
                throw th;
            }
        }
        this.h.a();
        if (this.f8514e == null) {
            throw new IOException("stream was reset: " + this.j);
        }
        return this.f8514e;
    }

    public Sink e() {
        synchronized (this) {
            if (this.f8514e == null && !g()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.g;
    }

    public Source f() {
        return this.f;
    }

    public boolean g() {
        return this.d.b == ((this.c & 1) == 1);
    }

    public synchronized boolean h() {
        if (this.j != null) {
            return false;
        }
        if ((this.f.f8517e || this.f.d) && (this.g.c || this.g.b)) {
            if (this.f8514e != null) {
                return false;
            }
        }
        return true;
    }

    public void c(ErrorCode errorCode) {
        if (b(errorCode)) {
            this.d.d(this.c, errorCode);
        }
    }

    public void a(List<cg1> list, HeadersMode headersMode) {
        ErrorCode errorCode = null;
        boolean zH = true;
        synchronized (this) {
            if (this.f8514e == null) {
                if (headersMode.failIfHeadersAbsent()) {
                    errorCode = ErrorCode.PROTOCOL_ERROR;
                } else {
                    this.f8514e = list;
                    zH = h();
                    notifyAll();
                }
            } else if (headersMode.failIfHeadersPresent()) {
                errorCode = ErrorCode.STREAM_IN_USE;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f8514e);
                arrayList.addAll(list);
                this.f8514e = arrayList;
            }
        }
        if (errorCode != null) {
            c(errorCode);
        } else {
            if (zH) {
                return;
            }
            this.d.d(this.c);
        }
    }

    public synchronized void d(ErrorCode errorCode) {
        if (this.j == null) {
            this.j = errorCode;
            notifyAll();
        }
    }

    public final void b() throws IOException {
        if (!this.g.b) {
            if (!this.g.c) {
                if (this.j == null) {
                    return;
                }
                throw new IOException("stream was reset: " + this.j);
            }
            throw new IOException("stream finished");
        }
        throw new IOException("stream closed");
    }

    public void a(BufferedSource bufferedSource, int i) throws IOException {
        this.f.a(bufferedSource, i);
    }

    public final void a() throws IOException {
        boolean z;
        boolean zH;
        synchronized (this) {
            z = !this.f.f8517e && this.f.d && (this.g.c || this.g.b);
            zH = h();
        }
        if (z) {
            a(ErrorCode.CANCEL);
        } else {
            if (zH) {
                return;
            }
            this.d.d(this.c);
        }
    }

    public void a(long j) {
        this.b += j;
        if (j > 0) {
            notifyAll();
        }
    }
}
