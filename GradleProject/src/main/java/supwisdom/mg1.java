package supwisdom;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.spdy.ErrorCode;
import com.squareup.okhttp.internal.spdy.HeadersMode;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import supwisdom.ag1;

/* JADX INFO: compiled from: SpdyConnection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mg1 implements Closeable {
    public static final ExecutorService w = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), gf1.a("OkHttp SpdyConnection", true));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Protocol f8387a;
    public final boolean b;
    public final gg1 c;
    public final Map<Integer, ng1> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f8388e;
    public int f;
    public int g;
    public boolean h;
    public long i;
    public final ExecutorService j;
    public Map<Integer, ig1> k;
    public final jg1 l;
    public long m;
    public long n;
    public final kg1 o;
    public final kg1 p;
    public boolean q;
    public final og1 r;
    public final Socket s;
    public final bg1 t;
    public final i u;
    public final Set<Integer> v;

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class a extends bf1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ ErrorCode c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Object[] objArr, int i, ErrorCode errorCode) {
            super(str, objArr);
            this.b = i;
            this.c = errorCode;
        }

        @Override // supwisdom.bf1
        public void a() {
            try {
                mg1.this.c(this.b, this.c);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class b extends bf1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ long c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Object[] objArr, int i, long j) {
            super(str, objArr);
            this.b = i;
            this.c = j;
        }

        @Override // supwisdom.bf1
        public void a() {
            try {
                mg1.this.t.windowUpdate(this.b, this.c);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class c extends bf1 {
        public final /* synthetic */ boolean b;
        public final /* synthetic */ int c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ig1 f8389e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, Object[] objArr, boolean z, int i, int i2, ig1 ig1Var) {
            super(str, objArr);
            this.b = z;
            this.c = i;
            this.d = i2;
            this.f8389e = ig1Var;
        }

        @Override // supwisdom.bf1
        public void a() {
            try {
                mg1.this.a(this.b, this.c, this.d, this.f8389e);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class d extends bf1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ List c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, Object[] objArr, int i, List list) {
            super(str, objArr);
            this.b = i;
            this.c = list;
        }

        @Override // supwisdom.bf1
        public void a() {
            if (mg1.this.l.onRequest(this.b, this.c)) {
                try {
                    mg1.this.t.a(this.b, ErrorCode.CANCEL);
                    synchronized (mg1.this) {
                        mg1.this.v.remove(Integer.valueOf(this.b));
                    }
                } catch (IOException unused) {
                }
            }
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class e extends bf1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ List c;
        public final /* synthetic */ boolean d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(String str, Object[] objArr, int i, List list, boolean z) {
            super(str, objArr);
            this.b = i;
            this.c = list;
            this.d = z;
        }

        @Override // supwisdom.bf1
        public void a() {
            boolean zOnHeaders = mg1.this.l.onHeaders(this.b, this.c, this.d);
            if (zOnHeaders) {
                try {
                    mg1.this.t.a(this.b, ErrorCode.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (zOnHeaders || this.d) {
                synchronized (mg1.this) {
                    mg1.this.v.remove(Integer.valueOf(this.b));
                }
            }
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class f extends bf1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ Buffer c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f8391e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, Object[] objArr, int i, Buffer buffer, int i2, boolean z) {
            super(str, objArr);
            this.b = i;
            this.c = buffer;
            this.d = i2;
            this.f8391e = z;
        }

        @Override // supwisdom.bf1
        public void a() {
            try {
                boolean zA = mg1.this.l.a(this.b, this.c, this.d, this.f8391e);
                if (zA) {
                    mg1.this.t.a(this.b, ErrorCode.CANCEL);
                }
                if (zA || this.f8391e) {
                    synchronized (mg1.this) {
                        mg1.this.v.remove(Integer.valueOf(this.b));
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class g extends bf1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ ErrorCode c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, Object[] objArr, int i, ErrorCode errorCode) {
            super(str, objArr);
            this.b = i;
            this.c = errorCode;
        }

        @Override // supwisdom.bf1
        public void a() {
            mg1.this.l.a(this.b, this.c);
            synchronized (mg1.this) {
                mg1.this.v.remove(Integer.valueOf(this.b));
            }
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f8392a;
        public Socket b;
        public gg1 c = gg1.f7733a;
        public Protocol d = Protocol.SPDY_3;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public jg1 f8393e = jg1.f8059a;
        public boolean f;

        public h(String str, boolean z, Socket socket) throws IOException {
            this.f8392a = str;
            this.f = z;
            this.b = socket;
        }

        public h a(Protocol protocol) {
            this.d = protocol;
            return this;
        }

        public mg1 a() throws IOException {
            return new mg1(this, null);
        }
    }

    /* JADX INFO: compiled from: SpdyConnection.java */
    public class i extends bf1 implements ag1.a {
        public ag1 b;

        /* JADX INFO: compiled from: SpdyConnection.java */
        public class a extends bf1 {
            public final /* synthetic */ ng1 b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, Object[] objArr, ng1 ng1Var) {
                super(str, objArr);
                this.b = ng1Var;
            }

            @Override // supwisdom.bf1
            public void a() {
                try {
                    mg1.this.c.a(this.b);
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }

        /* JADX INFO: compiled from: SpdyConnection.java */
        public class b extends bf1 {
            public final /* synthetic */ kg1 b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(String str, Object[] objArr, kg1 kg1Var) {
                super(str, objArr);
                this.b = kg1Var;
            }

            @Override // supwisdom.bf1
            public void a() {
                try {
                    mg1.this.t.a(this.b);
                } catch (IOException unused) {
                }
            }
        }

        public /* synthetic */ i(mg1 mg1Var, a aVar) {
            this();
        }

        @Override // supwisdom.bf1
        public void a() throws Throwable {
            ErrorCode errorCode;
            ErrorCode errorCode2;
            ErrorCode errorCode3;
            mg1 mg1Var;
            ErrorCode errorCode4 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    ag1 ag1VarA = mg1.this.r.a(Okio.buffer(Okio.source(mg1.this.s)), mg1.this.b);
                    this.b = ag1VarA;
                    if (!mg1.this.b) {
                        ag1VarA.m();
                    }
                    while (this.b.a(this)) {
                    }
                    errorCode2 = ErrorCode.NO_ERROR;
                } catch (IOException unused) {
                } catch (Throwable th) {
                    th = th;
                    errorCode = errorCode4;
                    try {
                        mg1.this.a(errorCode, errorCode4);
                    } catch (IOException unused2) {
                    }
                    gf1.a(this.b);
                    throw th;
                }
                try {
                    try {
                        errorCode3 = ErrorCode.CANCEL;
                        mg1Var = mg1.this;
                    } catch (Throwable th2) {
                        errorCode = errorCode2;
                        th = th2;
                        mg1.this.a(errorCode, errorCode4);
                        gf1.a(this.b);
                        throw th;
                    }
                } catch (IOException unused3) {
                    errorCode2 = ErrorCode.PROTOCOL_ERROR;
                    errorCode3 = ErrorCode.PROTOCOL_ERROR;
                    mg1Var = mg1.this;
                }
                mg1Var.a(errorCode2, errorCode3);
            } catch (IOException unused4) {
            }
            gf1.a(this.b);
        }

        @Override // supwisdom.ag1.a
        public void ackSettings() {
        }

        @Override // supwisdom.ag1.a
        public void ping(boolean z, int i, int i2) {
            if (!z) {
                mg1.this.b(true, i, i2, null);
                return;
            }
            ig1 ig1VarC = mg1.this.c(i);
            if (ig1VarC != null) {
                ig1VarC.b();
            }
        }

        @Override // supwisdom.ag1.a
        public void priority(int i, int i2, int i3, boolean z) {
        }

        @Override // supwisdom.ag1.a
        public void pushPromise(int i, int i2, List<cg1> list) {
            mg1.this.a(i2, list);
        }

        @Override // supwisdom.ag1.a
        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (mg1.this) {
                    mg1.this.n += j;
                    mg1.this.notifyAll();
                }
                return;
            }
            ng1 ng1VarA = mg1.this.a(i);
            if (ng1VarA != null) {
                synchronized (ng1VarA) {
                    ng1VarA.a(j);
                }
            }
        }

        public i() {
            super("OkHttp %s", mg1.this.f8388e);
        }

        @Override // supwisdom.ag1.a
        public void a(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            if (mg1.this.b(i)) {
                mg1.this.a(i, bufferedSource, i2, z);
                return;
            }
            ng1 ng1VarA = mg1.this.a(i);
            if (ng1VarA == null) {
                mg1.this.d(i, ErrorCode.INVALID_STREAM);
                bufferedSource.skip(i2);
            } else {
                ng1VarA.a(bufferedSource, i2);
                if (z) {
                    ng1VarA.j();
                }
            }
        }

        @Override // supwisdom.ag1.a
        public void a(boolean z, boolean z2, int i, int i2, List<cg1> list, HeadersMode headersMode) {
            if (mg1.this.b(i)) {
                mg1.this.a(i, list, z2);
                return;
            }
            synchronized (mg1.this) {
                if (mg1.this.h) {
                    return;
                }
                ng1 ng1VarA = mg1.this.a(i);
                if (ng1VarA == null) {
                    if (!headersMode.failIfStreamAbsent()) {
                        if (i <= mg1.this.f) {
                            return;
                        }
                        if (i % 2 == mg1.this.g % 2) {
                            return;
                        }
                        ng1 ng1Var = new ng1(i, mg1.this, z, z2, list);
                        mg1.this.f = i;
                        mg1.this.d.put(Integer.valueOf(i), ng1Var);
                        mg1.w.execute(new a("OkHttp %s stream %d", new Object[]{mg1.this.f8388e, Integer.valueOf(i)}, ng1Var));
                        return;
                    }
                    mg1.this.d(i, ErrorCode.INVALID_STREAM);
                    return;
                }
                if (headersMode.failIfStreamPresent()) {
                    ng1VarA.c(ErrorCode.PROTOCOL_ERROR);
                    mg1.this.d(i);
                } else {
                    ng1VarA.a(list, headersMode);
                    if (z2) {
                        ng1VarA.j();
                    }
                }
            }
        }

        @Override // supwisdom.ag1.a
        public void a(int i, ErrorCode errorCode) {
            if (mg1.this.b(i)) {
                mg1.this.b(i, errorCode);
                return;
            }
            ng1 ng1VarD = mg1.this.d(i);
            if (ng1VarD != null) {
                ng1VarD.d(errorCode);
            }
        }

        @Override // supwisdom.ag1.a
        public void a(boolean z, kg1 kg1Var) {
            ng1[] ng1VarArr;
            long j;
            synchronized (mg1.this) {
                int iC = mg1.this.p.c(65536);
                if (z) {
                    mg1.this.p.a();
                }
                mg1.this.p.a(kg1Var);
                if (mg1.this.b() == Protocol.HTTP_2) {
                    a(kg1Var);
                }
                int iC2 = mg1.this.p.c(65536);
                ng1VarArr = null;
                if (iC2 == -1 || iC2 == iC) {
                    j = 0;
                } else {
                    j = iC2 - iC;
                    if (!mg1.this.q) {
                        mg1.this.a(j);
                        mg1.this.q = true;
                    }
                    if (!mg1.this.d.isEmpty()) {
                        ng1VarArr = (ng1[]) mg1.this.d.values().toArray(new ng1[mg1.this.d.size()]);
                    }
                }
            }
            if (ng1VarArr == null || j == 0) {
                return;
            }
            for (ng1 ng1Var : ng1VarArr) {
                synchronized (ng1Var) {
                    ng1Var.a(j);
                }
            }
        }

        public final void a(kg1 kg1Var) {
            mg1.w.execute(new b("OkHttp %s ACK Settings", new Object[]{mg1.this.f8388e}, kg1Var));
        }

        @Override // supwisdom.ag1.a
        public void a(int i, ErrorCode errorCode, ByteString byteString) {
            ng1[] ng1VarArr;
            byteString.size();
            synchronized (mg1.this) {
                ng1VarArr = (ng1[]) mg1.this.d.values().toArray(new ng1[mg1.this.d.size()]);
                mg1.this.h = true;
            }
            for (ng1 ng1Var : ng1VarArr) {
                if (ng1Var.c() > i && ng1Var.g()) {
                    ng1Var.d(ErrorCode.REFUSED_STREAM);
                    mg1.this.d(ng1Var.c());
                }
            }
        }
    }

    public /* synthetic */ mg1(h hVar, a aVar) throws IOException {
        this(hVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    public void flush() throws IOException {
        this.t.flush();
    }

    public mg1(h hVar) throws IOException {
        this.d = new HashMap();
        this.i = System.nanoTime();
        this.m = 0L;
        this.o = new kg1();
        this.p = new kg1();
        this.q = false;
        this.v = new LinkedHashSet();
        this.f8387a = hVar.d;
        this.l = hVar.f8393e;
        this.b = hVar.f;
        this.c = hVar.c;
        this.g = hVar.f ? 1 : 2;
        if (hVar.f && this.f8387a == Protocol.HTTP_2) {
            this.g += 2;
        }
        boolean unused = hVar.f;
        if (hVar.f) {
            this.o.a(7, 0, 16777216);
        }
        this.f8388e = hVar.f8392a;
        Protocol protocol = this.f8387a;
        a aVar = null;
        if (protocol == Protocol.HTTP_2) {
            this.r = new eg1();
            this.j = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), gf1.a(String.format("OkHttp %s Push Observer", this.f8388e), true));
            this.p.a(7, 0, 65535);
            this.p.a(5, 0, 16384);
        } else {
            if (protocol != Protocol.SPDY_3) {
                throw new AssertionError(this.f8387a);
            }
            this.r = new lg1();
            this.j = null;
        }
        this.n = this.p.c(65536);
        this.s = hVar.b;
        this.t = this.r.a(Okio.buffer(Okio.sink(hVar.b)), this.b);
        this.u = new i(this, aVar);
        new Thread(this.u).start();
    }

    public synchronized ng1 d(int i2) {
        ng1 ng1VarRemove;
        ng1VarRemove = this.d.remove(Integer.valueOf(i2));
        if (ng1VarRemove != null && this.d.isEmpty()) {
            a(true);
        }
        return ng1VarRemove;
    }

    public synchronized boolean c() {
        return this.i != Long.MAX_VALUE;
    }

    public void c(int i2, ErrorCode errorCode) throws IOException {
        this.t.a(i2, errorCode);
    }

    public Protocol b() {
        return this.f8387a;
    }

    public final synchronized ig1 c(int i2) {
        return this.k != null ? this.k.remove(Integer.valueOf(i2)) : null;
    }

    public final void b(boolean z, int i2, int i3, ig1 ig1Var) {
        w.execute(new c("OkHttp %s ping %08x%08x", new Object[]{this.f8388e, Integer.valueOf(i2), Integer.valueOf(i3)}, z, i2, i3, ig1Var));
    }

    public void d(int i2, ErrorCode errorCode) {
        w.submit(new a("OkHttp %s stream %d", new Object[]{this.f8388e, Integer.valueOf(i2)}, i2, errorCode));
    }

    public void d() throws IOException {
        this.t.connectionPreface();
        this.t.b(this.o);
        if (this.o.c(65536) != 65536) {
            this.t.windowUpdate(0, r0 - 65536);
        }
    }

    public final boolean b(int i2) {
        return this.f8387a == Protocol.HTTP_2 && i2 != 0 && (i2 & 1) == 0;
    }

    public synchronized ng1 a(int i2) {
        return this.d.get(Integer.valueOf(i2));
    }

    public final void b(int i2, ErrorCode errorCode) {
        this.j.execute(new g("OkHttp %s Push Reset[%s]", new Object[]{this.f8388e, Integer.valueOf(i2)}, i2, errorCode));
    }

    public final synchronized void a(boolean z) {
        long jNanoTime;
        if (z) {
            try {
                jNanoTime = System.nanoTime();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            jNanoTime = Long.MAX_VALUE;
        }
        this.i = jNanoTime;
    }

    public synchronized long a() {
        return this.i;
    }

    public ng1 a(List<cg1> list, boolean z, boolean z2) throws IOException {
        return a(0, list, z, z2);
    }

    public final ng1 a(int i2, List<cg1> list, boolean z, boolean z2) throws IOException {
        int i3;
        ng1 ng1Var;
        boolean z3 = !z;
        boolean z4 = !z2;
        synchronized (this.t) {
            synchronized (this) {
                if (!this.h) {
                    i3 = this.g;
                    this.g += 2;
                    ng1Var = new ng1(i3, this, z3, z4, list);
                    if (ng1Var.h()) {
                        this.d.put(Integer.valueOf(i3), ng1Var);
                        a(false);
                    }
                } else {
                    throw new IOException("shutdown");
                }
            }
            if (i2 == 0) {
                this.t.a(z3, z4, i3, i2, list);
            } else if (!this.b) {
                this.t.pushPromise(i2, i3, list);
            } else {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            }
        }
        if (!z) {
            this.t.flush();
        }
        return ng1Var;
    }

    public void a(int i2, boolean z, Buffer buffer, long j) throws IOException {
        int iMin;
        long j2;
        if (j == 0) {
            this.t.a(z, i2, buffer, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (this.n <= 0) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        throw new InterruptedIOException();
                    }
                }
                iMin = Math.min((int) Math.min(j, this.n), this.t.maxDataLength());
                j2 = iMin;
                this.n -= j2;
            }
            j -= j2;
            this.t.a(z && j == 0, i2, buffer, iMin);
        }
    }

    public void a(long j) {
        this.n += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void a(int i2, long j) {
        w.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.f8388e, Integer.valueOf(i2)}, i2, j));
    }

    public final void a(boolean z, int i2, int i3, ig1 ig1Var) throws IOException {
        synchronized (this.t) {
            if (ig1Var != null) {
                ig1Var.c();
                this.t.ping(z, i2, i3);
            } else {
                this.t.ping(z, i2, i3);
            }
        }
    }

    public void a(ErrorCode errorCode) throws IOException {
        synchronized (this.t) {
            synchronized (this) {
                if (this.h) {
                    return;
                }
                this.h = true;
                this.t.a(this.f, errorCode, gf1.f7729a);
            }
        }
    }

    public final void a(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        int i2;
        ng1[] ng1VarArr;
        ig1[] ig1VarArr = null;
        try {
            a(errorCode);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (this.d.isEmpty()) {
                ng1VarArr = null;
            } else {
                ng1VarArr = (ng1[]) this.d.values().toArray(new ng1[this.d.size()]);
                this.d.clear();
                a(false);
            }
            if (this.k != null) {
                ig1[] ig1VarArr2 = (ig1[]) this.k.values().toArray(new ig1[this.k.size()]);
                this.k = null;
                ig1VarArr = ig1VarArr2;
            }
        }
        if (ng1VarArr != null) {
            for (ng1 ng1Var : ng1VarArr) {
                try {
                    ng1Var.a(errorCode2);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        if (ig1VarArr != null) {
            for (ig1 ig1Var : ig1VarArr) {
                ig1Var.a();
            }
        }
        try {
            this.t.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.s.close();
        } catch (IOException e5) {
            e = e5;
        }
        if (e != null) {
            throw e;
        }
    }

    public final void a(int i2, List<cg1> list) {
        synchronized (this) {
            if (this.v.contains(Integer.valueOf(i2))) {
                d(i2, ErrorCode.PROTOCOL_ERROR);
            } else {
                this.v.add(Integer.valueOf(i2));
                this.j.execute(new d("OkHttp %s Push Request[%s]", new Object[]{this.f8388e, Integer.valueOf(i2)}, i2, list));
            }
        }
    }

    public final void a(int i2, List<cg1> list, boolean z) {
        this.j.execute(new e("OkHttp %s Push Headers[%s]", new Object[]{this.f8388e, Integer.valueOf(i2)}, i2, list, z));
    }

    public final void a(int i2, BufferedSource bufferedSource, int i3, boolean z) throws IOException {
        Buffer buffer = new Buffer();
        long j = i3;
        bufferedSource.require(j);
        bufferedSource.read(buffer, j);
        if (buffer.size() == j) {
            this.j.execute(new f("OkHttp %s Push Data[%s]", new Object[]{this.f8388e, Integer.valueOf(i2)}, i2, buffer, i3, z));
            return;
        }
        throw new IOException(buffer.size() + " != " + i3);
    }
}
