package supwisdom;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import supwisdom.iu1;

/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ou1 {
    public long b;
    public final int c;
    public final mu1 d;
    public iu1.a f;
    public boolean g;
    public final b h;
    public final a i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f8714a = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Deque<us1> f8715e = new ArrayDeque();
    public final c j = new c();
    public final c k = new c();
    public ErrorCode l = null;

    /* JADX INFO: compiled from: Http2Stream.java */
    public final class a implements Sink {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Buffer f8716a = new Buffer();
        public boolean b;
        public boolean c;

        public a() {
        }

        public final void a(boolean z) throws IOException {
            long jMin;
            synchronized (ou1.this) {
                ou1.this.k.enter();
                while (ou1.this.b <= 0 && !this.c && !this.b && ou1.this.l == null) {
                    try {
                        ou1.this.k();
                    } finally {
                    }
                }
                ou1.this.k.a();
                ou1.this.b();
                jMin = Math.min(ou1.this.b, this.f8716a.size());
                ou1.this.b -= jMin;
            }
            ou1.this.k.enter();
            try {
                ou1.this.d.a(ou1.this.c, z && jMin == this.f8716a.size(), this.f8716a, jMin);
            } finally {
            }
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (ou1.this) {
                if (this.b) {
                    return;
                }
                if (!ou1.this.i.c) {
                    if (this.f8716a.size() > 0) {
                        while (this.f8716a.size() > 0) {
                            a(true);
                        }
                    } else {
                        ou1 ou1Var = ou1.this;
                        ou1Var.d.a(ou1Var.c, true, (Buffer) null, 0L);
                    }
                }
                synchronized (ou1.this) {
                    this.b = true;
                }
                ou1.this.d.flush();
                ou1.this.a();
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (ou1.this) {
                ou1.this.b();
            }
            while (this.f8716a.size() > 0) {
                a(false);
                ou1.this.d.flush();
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return ou1.this.k;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            this.f8716a.write(buffer, j);
            while (this.f8716a.size() >= 16384) {
                a(false);
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    public final class b implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Buffer f8717a = new Buffer();
        public final Buffer b = new Buffer();
        public final long c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8718e;

        public b(long j) {
            this.c = j;
        }

        public final void a(long j) {
            ou1.this.d.a(j);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            long size;
            iu1.a aVar;
            ArrayList arrayList;
            synchronized (ou1.this) {
                this.d = true;
                size = this.b.size();
                this.b.clear();
                aVar = null;
                if (ou1.this.f8715e.isEmpty() || ou1.this.f == null) {
                    arrayList = null;
                } else {
                    ArrayList arrayList2 = new ArrayList(ou1.this.f8715e);
                    ou1.this.f8715e.clear();
                    aVar = ou1.this.f;
                    arrayList = arrayList2;
                }
                ou1.this.notifyAll();
            }
            if (size > 0) {
                a(size);
            }
            ou1.this.a();
            if (aVar != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    aVar.a((us1) it.next());
                }
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            ErrorCode errorCode;
            long j2;
            us1 us1Var;
            iu1.a aVar;
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            while (true) {
                synchronized (ou1.this) {
                    ou1.this.j.enter();
                    try {
                        errorCode = ou1.this.l != null ? ou1.this.l : null;
                        if (this.d) {
                            throw new IOException("stream closed");
                        }
                        if (ou1.this.f8715e.isEmpty() || ou1.this.f == null) {
                            if (this.b.size() > 0) {
                                j2 = this.b.read(buffer, Math.min(j, this.b.size()));
                                ou1.this.f8714a += j2;
                                if (errorCode == null && ou1.this.f8714a >= ou1.this.d.n.c() / 2) {
                                    ou1.this.d.a(ou1.this.c, ou1.this.f8714a);
                                    ou1.this.f8714a = 0L;
                                }
                            } else if (this.f8718e || errorCode != null) {
                                j2 = -1;
                            } else {
                                ou1.this.k();
                                ou1.this.j.a();
                            }
                            us1Var = null;
                            aVar = null;
                        } else {
                            us1Var = (us1) ou1.this.f8715e.removeFirst();
                            aVar = ou1.this.f;
                            j2 = -1;
                        }
                        if (us1Var == null || aVar == null) {
                            break;
                        }
                        aVar.a(us1Var);
                    } finally {
                    }
                }
            }
            if (j2 != -1) {
                a(j2);
                return j2;
            }
            if (errorCode == null) {
                return -1L;
            }
            throw new StreamResetException(errorCode);
        }

        @Override // okio.Source
        public Timeout timeout() {
            return ou1.this.j;
        }

        public void a(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (ou1.this) {
                    z = this.f8718e;
                    z2 = true;
                    z3 = this.b.size() + j > this.c;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    ou1.this.c(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    bufferedSource.skip(j);
                    return;
                }
                long j2 = bufferedSource.read(this.f8717a, j);
                if (j2 == -1) {
                    throw new EOFException();
                }
                j -= j2;
                synchronized (ou1.this) {
                    if (this.b.size() != 0) {
                        z2 = false;
                    }
                    this.b.writeAll(this.f8717a);
                    if (z2) {
                        ou1.this.notifyAll();
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    public class c extends AsyncTimeout {
        public c() {
        }

        public void a() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }

        @Override // okio.AsyncTimeout
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            ou1.this.c(ErrorCode.CANCEL);
        }
    }

    public ou1(int i, mu1 mu1Var, boolean z, boolean z2, @Nullable us1 us1Var) {
        if (mu1Var == null) {
            throw new NullPointerException("connection == null");
        }
        this.c = i;
        this.d = mu1Var;
        this.b = mu1Var.o.c();
        this.h = new b(mu1Var.n.c());
        a aVar = new a();
        this.i = aVar;
        this.h.f8718e = z2;
        aVar.c = z;
        if (us1Var != null) {
            this.f8715e.add(us1Var);
        }
        if (f() && us1Var != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!f() && us1Var == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public int c() {
        return this.c;
    }

    public Sink d() {
        synchronized (this) {
            if (!this.g && !f()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.i;
    }

    public Source e() {
        return this.h;
    }

    public boolean f() {
        return this.d.f8431a == ((this.c & 1) == 1);
    }

    public synchronized boolean g() {
        if (this.l != null) {
            return false;
        }
        if ((this.h.f8718e || this.h.d) && (this.i.c || this.i.b)) {
            if (this.g) {
                return false;
            }
        }
        return true;
    }

    public Timeout h() {
        return this.j;
    }

    public void i() {
        boolean zG;
        synchronized (this) {
            this.h.f8718e = true;
            zG = g();
            notifyAll();
        }
        if (zG) {
            return;
        }
        this.d.c(this.c);
    }

    public synchronized us1 j() throws IOException {
        this.j.enter();
        while (this.f8715e.isEmpty() && this.l == null) {
            try {
                k();
            } catch (Throwable th) {
                this.j.a();
                throw th;
            }
        }
        this.j.a();
        if (this.f8715e.isEmpty()) {
            throw new StreamResetException(this.l);
        }
        return this.f8715e.removeFirst();
    }

    public void k() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public Timeout l() {
        return this.k;
    }

    public void a(ErrorCode errorCode) throws IOException {
        if (b(errorCode)) {
            this.d.b(this.c, errorCode);
        }
    }

    public final boolean b(ErrorCode errorCode) {
        synchronized (this) {
            if (this.l != null) {
                return false;
            }
            if (this.h.f8718e && this.i.c) {
                return false;
            }
            this.l = errorCode;
            notifyAll();
            this.d.c(this.c);
            return true;
        }
    }

    public void c(ErrorCode errorCode) {
        if (b(errorCode)) {
            this.d.c(this.c, errorCode);
        }
    }

    public void a(List<iu1> list) {
        boolean zG;
        synchronized (this) {
            this.g = true;
            this.f8715e.add(kt1.b(list));
            zG = g();
            notifyAll();
        }
        if (zG) {
            return;
        }
        this.d.c(this.c);
    }

    public synchronized void d(ErrorCode errorCode) {
        if (this.l == null) {
            this.l = errorCode;
            notifyAll();
        }
    }

    public void a(BufferedSource bufferedSource, int i) throws IOException {
        this.h.a(bufferedSource, i);
    }

    public void b() throws IOException {
        a aVar = this.i;
        if (!aVar.b) {
            if (!aVar.c) {
                if (this.l != null) {
                    throw new StreamResetException(this.l);
                }
                return;
            }
            throw new IOException("stream finished");
        }
        throw new IOException("stream closed");
    }

    public void a() throws IOException {
        boolean z;
        boolean zG;
        synchronized (this) {
            z = !this.h.f8718e && this.h.d && (this.i.c || this.i.b);
            zG = g();
        }
        if (z) {
            a(ErrorCode.CANCEL);
        } else {
            if (zG) {
                return;
            }
            this.d.c(this.c);
        }
    }

    public void a(long j) {
        this.b += j;
        if (j > 0) {
            notifyAll();
        }
    }
}
