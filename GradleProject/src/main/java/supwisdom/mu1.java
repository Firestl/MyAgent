package supwisdom;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import supwisdom.nu1;

/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mu1 implements Closeable {
    public static final ExecutorService u = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), kt1.a("OkHttp Http2Connection", true));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8431a;
    public final h b;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8432e;
    public int f;
    public boolean g;
    public final ScheduledExecutorService h;
    public final ExecutorService i;
    public final ru1 j;
    public boolean k;
    public long m;
    public final Socket q;
    public final pu1 r;
    public final j s;
    public final Map<Integer, ou1> c = new LinkedHashMap();
    public long l = 0;
    public su1 n = new su1();
    public final su1 o = new su1();
    public boolean p = false;
    public final Set<Integer> t = new LinkedHashSet();

    /* JADX INFO: compiled from: Http2Connection.java */
    public class a extends jt1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ ErrorCode c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Object[] objArr, int i, ErrorCode errorCode) {
            super(str, objArr);
            this.b = i;
            this.c = errorCode;
        }

        @Override // supwisdom.jt1
        public void a() {
            try {
                mu1.this.b(this.b, this.c);
            } catch (IOException unused) {
                mu1.this.a();
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class b extends jt1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ long c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Object[] objArr, int i, long j) {
            super(str, objArr);
            this.b = i;
            this.c = j;
        }

        @Override // supwisdom.jt1
        public void a() {
            try {
                mu1.this.r.windowUpdate(this.b, this.c);
            } catch (IOException unused) {
                mu1.this.a();
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class c extends jt1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ List c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, Object[] objArr, int i, List list) {
            super(str, objArr);
            this.b = i;
            this.c = list;
        }

        @Override // supwisdom.jt1
        public void a() {
            if (mu1.this.j.onRequest(this.b, this.c)) {
                try {
                    mu1.this.r.a(this.b, ErrorCode.CANCEL);
                    synchronized (mu1.this) {
                        mu1.this.t.remove(Integer.valueOf(this.b));
                    }
                } catch (IOException unused) {
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class d extends jt1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ List c;
        public final /* synthetic */ boolean d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, Object[] objArr, int i, List list, boolean z) {
            super(str, objArr);
            this.b = i;
            this.c = list;
            this.d = z;
        }

        @Override // supwisdom.jt1
        public void a() {
            boolean zOnHeaders = mu1.this.j.onHeaders(this.b, this.c, this.d);
            if (zOnHeaders) {
                try {
                    mu1.this.r.a(this.b, ErrorCode.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (zOnHeaders || this.d) {
                synchronized (mu1.this) {
                    mu1.this.t.remove(Integer.valueOf(this.b));
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class e extends jt1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ Buffer c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f8434e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(String str, Object[] objArr, int i, Buffer buffer, int i2, boolean z) {
            super(str, objArr);
            this.b = i;
            this.c = buffer;
            this.d = i2;
            this.f8434e = z;
        }

        @Override // supwisdom.jt1
        public void a() {
            try {
                boolean zA = mu1.this.j.a(this.b, this.c, this.d, this.f8434e);
                if (zA) {
                    mu1.this.r.a(this.b, ErrorCode.CANCEL);
                }
                if (zA || this.f8434e) {
                    synchronized (mu1.this) {
                        mu1.this.t.remove(Integer.valueOf(this.b));
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class f extends jt1 {
        public final /* synthetic */ int b;
        public final /* synthetic */ ErrorCode c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, Object[] objArr, int i, ErrorCode errorCode) {
            super(str, objArr);
            this.b = i;
            this.c = errorCode;
        }

        @Override // supwisdom.jt1
        public void a() {
            mu1.this.j.a(this.b, this.c);
            synchronized (mu1.this) {
                mu1.this.t.remove(Integer.valueOf(this.b));
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f8437a = new a();

        /* JADX INFO: compiled from: Http2Connection.java */
        public class a extends h {
            @Override // supwisdom.mu1.h
            public void a(ou1 ou1Var) throws IOException {
                ou1Var.a(ErrorCode.REFUSED_STREAM);
            }
        }

        public void a(mu1 mu1Var) {
        }

        public abstract void a(ou1 ou1Var) throws IOException;
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public final class i extends jt1 {
        public final boolean b;
        public final int c;
        public final int d;

        public i(boolean z, int i, int i2) {
            super("OkHttp %s ping %08x%08x", mu1.this.d, Integer.valueOf(i), Integer.valueOf(i2));
            this.b = z;
            this.c = i;
            this.d = i2;
        }

        @Override // supwisdom.jt1
        public void a() {
            mu1.this.a(this.b, this.c, this.d);
        }
    }

    public mu1(g gVar) {
        this.j = gVar.f;
        boolean z = gVar.g;
        this.f8431a = z;
        this.b = gVar.f8436e;
        int i2 = z ? 1 : 2;
        this.f = i2;
        if (gVar.g) {
            this.f = i2 + 2;
        }
        if (gVar.g) {
            this.n.a(7, 16777216);
        }
        this.d = gVar.b;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, kt1.a(kt1.a("OkHttp %s Writer", this.d), false));
        this.h = scheduledThreadPoolExecutor;
        if (gVar.h != 0) {
            i iVar = new i(false, 0, 0);
            int i3 = gVar.h;
            scheduledThreadPoolExecutor.scheduleAtFixedRate(iVar, i3, i3, TimeUnit.MILLISECONDS);
        }
        this.i = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), kt1.a(kt1.a("OkHttp %s Push Observer", this.d), true));
        this.o.a(7, 65535);
        this.o.a(5, 16384);
        this.m = this.o.c();
        this.q = gVar.f8435a;
        this.r = new pu1(gVar.d, this.f8431a);
        this.s = new j(new nu1(gVar.c, this.f8431a));
    }

    public boolean b(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    public synchronized ou1 c(int i2) {
        ou1 ou1VarRemove;
        ou1VarRemove = this.c.remove(Integer.valueOf(i2));
        notifyAll();
        return ou1VarRemove;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    public void d() throws IOException {
        a(true);
    }

    public void flush() throws IOException {
        this.r.flush();
    }

    public void b(int i2, ErrorCode errorCode) throws IOException {
        this.r.a(i2, errorCode);
    }

    public synchronized ou1 a(int i2) {
        return this.c.get(Integer.valueOf(i2));
    }

    public synchronized boolean b() {
        return this.g;
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Socket f8435a;
        public String b;
        public BufferedSource c;
        public BufferedSink d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public h f8436e = h.f8437a;
        public ru1 f = ru1.f9093a;
        public boolean g;
        public int h;

        public g(boolean z) {
            this.g = z;
        }

        public g a(Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.f8435a = socket;
            this.b = str;
            this.c = bufferedSource;
            this.d = bufferedSink;
            return this;
        }

        public g a(h hVar) {
            this.f8436e = hVar;
            return this;
        }

        public g a(int i) {
            this.h = i;
            return this;
        }

        public mu1 a() {
            return new mu1(this);
        }
    }

    public synchronized void a(long j2) {
        long j3 = this.l + j2;
        this.l = j3;
        if (j3 >= this.n.c() / 2) {
            a(0, this.l);
            this.l = 0L;
        }
    }

    public void b(int i2, List<iu1> list, boolean z) {
        try {
            a(new d("OkHttp %s Push Headers[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, list, z));
        } catch (RejectedExecutionException unused) {
        }
    }

    public synchronized int c() {
        return this.o.b(Integer.MAX_VALUE);
    }

    public void c(int i2, ErrorCode errorCode) {
        try {
            this.h.execute(new a("OkHttp %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}, i2, errorCode));
        } catch (RejectedExecutionException unused) {
        }
    }

    public ou1 a(List<iu1> list, boolean z) throws IOException {
        return a(0, list, z);
    }

    public final ou1 a(int i2, List<iu1> list, boolean z) throws IOException {
        int i3;
        ou1 ou1Var;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.r) {
            synchronized (this) {
                if (this.f > 1073741823) {
                    a(ErrorCode.REFUSED_STREAM);
                }
                if (!this.g) {
                    i3 = this.f;
                    this.f += 2;
                    ou1Var = new ou1(i3, this, z3, false, null);
                    z2 = !z || this.m == 0 || ou1Var.b == 0;
                    if (ou1Var.g()) {
                        this.c.put(Integer.valueOf(i3), ou1Var);
                    }
                } else {
                    throw new ConnectionShutdownException();
                }
            }
            if (i2 == 0) {
                this.r.a(z3, i3, i2, list);
            } else if (!this.f8431a) {
                this.r.pushPromise(i2, i3, list);
            } else {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            }
        }
        if (z2) {
            this.r.flush();
        }
        return ou1Var;
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class j extends jt1 implements nu1.b {
        public final nu1 b;

        /* JADX INFO: compiled from: Http2Connection.java */
        public class a extends jt1 {
            public final /* synthetic */ ou1 b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, Object[] objArr, ou1 ou1Var) {
                super(str, objArr);
                this.b = ou1Var;
            }

            @Override // supwisdom.jt1
            public void a() {
                try {
                    mu1.this.b.a(this.b);
                } catch (IOException e2) {
                    yu1.c().a(4, "Http2Connection.Listener failure for " + mu1.this.d, e2);
                    try {
                        this.b.a(ErrorCode.PROTOCOL_ERROR);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        public class b extends jt1 {
            public b(String str, Object... objArr) {
                super(str, objArr);
            }

            @Override // supwisdom.jt1
            public void a() {
                mu1 mu1Var = mu1.this;
                mu1Var.b.a(mu1Var);
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        public class c extends jt1 {
            public final /* synthetic */ su1 b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(String str, Object[] objArr, su1 su1Var) {
                super(str, objArr);
                this.b = su1Var;
            }

            @Override // supwisdom.jt1
            public void a() {
                try {
                    mu1.this.r.a(this.b);
                } catch (IOException unused) {
                    mu1.this.a();
                }
            }
        }

        public j(nu1 nu1Var) {
            super("OkHttp %s", mu1.this.d);
            this.b = nu1Var;
        }

        @Override // supwisdom.jt1
        public void a() throws Throwable {
            ErrorCode errorCode;
            mu1 mu1Var;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    try {
                        this.b.a(this);
                        while (this.b.a(false, (nu1.b) this)) {
                        }
                        errorCode = ErrorCode.NO_ERROR;
                    } catch (IOException unused) {
                    } catch (Throwable th) {
                        th = th;
                        errorCode = errorCode2;
                        try {
                            mu1.this.a(errorCode, errorCode2);
                        } catch (IOException unused2) {
                        }
                        kt1.a(this.b);
                        throw th;
                    }
                    try {
                        errorCode2 = ErrorCode.CANCEL;
                        mu1Var = mu1.this;
                    } catch (IOException unused3) {
                        errorCode = ErrorCode.PROTOCOL_ERROR;
                        errorCode2 = ErrorCode.PROTOCOL_ERROR;
                        mu1Var = mu1.this;
                    }
                    mu1Var.a(errorCode, errorCode2);
                } catch (IOException unused4) {
                }
                kt1.a(this.b);
            } catch (Throwable th2) {
                th = th2;
            }
        }

        @Override // supwisdom.nu1.b
        public void ackSettings() {
        }

        @Override // supwisdom.nu1.b
        public void headers(boolean z, int i, int i2, List<iu1> list) {
            if (mu1.this.b(i)) {
                mu1.this.b(i, list, z);
                return;
            }
            synchronized (mu1.this) {
                ou1 ou1VarA = mu1.this.a(i);
                if (ou1VarA != null) {
                    ou1VarA.a(list);
                    if (z) {
                        ou1VarA.i();
                        return;
                    }
                    return;
                }
                if (mu1.this.g) {
                    return;
                }
                if (i <= mu1.this.f8432e) {
                    return;
                }
                if (i % 2 == mu1.this.f % 2) {
                    return;
                }
                ou1 ou1Var = new ou1(i, mu1.this, false, z, kt1.b(list));
                mu1.this.f8432e = i;
                mu1.this.c.put(Integer.valueOf(i), ou1Var);
                mu1.u.execute(new a("OkHttp %s stream %d", new Object[]{mu1.this.d, Integer.valueOf(i)}, ou1Var));
            }
        }

        @Override // supwisdom.nu1.b
        public void ping(boolean z, int i, int i2) {
            if (!z) {
                try {
                    mu1.this.h.execute(mu1.this.new i(true, i, i2));
                } catch (RejectedExecutionException unused) {
                }
            } else {
                synchronized (mu1.this) {
                    mu1.this.k = false;
                    mu1.this.notifyAll();
                }
            }
        }

        @Override // supwisdom.nu1.b
        public void priority(int i, int i2, int i3, boolean z) {
        }

        @Override // supwisdom.nu1.b
        public void pushPromise(int i, int i2, List<iu1> list) {
            mu1.this.a(i2, list);
        }

        @Override // supwisdom.nu1.b
        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (mu1.this) {
                    mu1.this.m += j;
                    mu1.this.notifyAll();
                }
                return;
            }
            ou1 ou1VarA = mu1.this.a(i);
            if (ou1VarA != null) {
                synchronized (ou1VarA) {
                    ou1VarA.a(j);
                }
            }
        }

        @Override // supwisdom.nu1.b
        public void a(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            if (mu1.this.b(i)) {
                mu1.this.a(i, bufferedSource, i2, z);
                return;
            }
            ou1 ou1VarA = mu1.this.a(i);
            if (ou1VarA == null) {
                mu1.this.c(i, ErrorCode.PROTOCOL_ERROR);
                long j = i2;
                mu1.this.a(j);
                bufferedSource.skip(j);
                return;
            }
            ou1VarA.a(bufferedSource, i2);
            if (z) {
                ou1VarA.i();
            }
        }

        @Override // supwisdom.nu1.b
        public void a(int i, ErrorCode errorCode) {
            if (mu1.this.b(i)) {
                mu1.this.a(i, errorCode);
                return;
            }
            ou1 ou1VarC = mu1.this.c(i);
            if (ou1VarC != null) {
                ou1VarC.d(errorCode);
            }
        }

        @Override // supwisdom.nu1.b
        public void a(boolean z, su1 su1Var) {
            ou1[] ou1VarArr;
            long j;
            int i;
            synchronized (mu1.this) {
                int iC = mu1.this.o.c();
                if (z) {
                    mu1.this.o.a();
                }
                mu1.this.o.a(su1Var);
                a(su1Var);
                int iC2 = mu1.this.o.c();
                ou1VarArr = null;
                if (iC2 == -1 || iC2 == iC) {
                    j = 0;
                } else {
                    j = iC2 - iC;
                    if (!mu1.this.p) {
                        mu1.this.p = true;
                    }
                    if (!mu1.this.c.isEmpty()) {
                        ou1VarArr = (ou1[]) mu1.this.c.values().toArray(new ou1[mu1.this.c.size()]);
                    }
                }
                mu1.u.execute(new b("OkHttp %s settings", mu1.this.d));
            }
            if (ou1VarArr == null || j == 0) {
                return;
            }
            for (ou1 ou1Var : ou1VarArr) {
                synchronized (ou1Var) {
                    ou1Var.a(j);
                }
            }
        }

        public final void a(su1 su1Var) {
            try {
                mu1.this.h.execute(new c("OkHttp %s ACK Settings", new Object[]{mu1.this.d}, su1Var));
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // supwisdom.nu1.b
        public void a(int i, ErrorCode errorCode, ByteString byteString) {
            ou1[] ou1VarArr;
            byteString.size();
            synchronized (mu1.this) {
                ou1VarArr = (ou1[]) mu1.this.c.values().toArray(new ou1[mu1.this.c.size()]);
                mu1.this.g = true;
            }
            for (ou1 ou1Var : ou1VarArr) {
                if (ou1Var.c() > i && ou1Var.f()) {
                    ou1Var.d(ErrorCode.REFUSED_STREAM);
                    mu1.this.c(ou1Var.c());
                }
            }
        }
    }

    public void a(int i2, boolean z, Buffer buffer, long j2) throws IOException {
        int iMin;
        long j3;
        if (j2 == 0) {
            this.r.a(z, i2, buffer, 0);
            return;
        }
        while (j2 > 0) {
            synchronized (this) {
                while (this.m <= 0) {
                    try {
                        if (this.c.containsKey(Integer.valueOf(i2))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                iMin = Math.min((int) Math.min(j2, this.m), this.r.maxDataLength());
                j3 = iMin;
                this.m -= j3;
            }
            j2 -= j3;
            this.r.a(z && j2 == 0, i2, buffer, iMin);
        }
    }

    public void a(int i2, long j2) {
        try {
            this.h.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}, i2, j2));
        } catch (RejectedExecutionException unused) {
        }
    }

    public void a(boolean z, int i2, int i3) {
        boolean z2;
        if (!z) {
            synchronized (this) {
                z2 = this.k;
                this.k = true;
            }
            if (z2) {
                a();
                return;
            }
        }
        try {
            this.r.ping(z, i2, i3);
        } catch (IOException unused) {
            a();
        }
    }

    public void a(ErrorCode errorCode) throws IOException {
        synchronized (this.r) {
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                this.r.a(this.f8432e, errorCode, kt1.f8203a);
            }
        }
    }

    public void a(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        ou1[] ou1VarArr = null;
        try {
            a(errorCode);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (!this.c.isEmpty()) {
                ou1VarArr = (ou1[]) this.c.values().toArray(new ou1[this.c.size()]);
                this.c.clear();
            }
        }
        if (ou1VarArr != null) {
            for (ou1 ou1Var : ou1VarArr) {
                try {
                    ou1Var.a(errorCode2);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        try {
            this.r.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.q.close();
        } catch (IOException e5) {
            e = e5;
        }
        this.h.shutdown();
        this.i.shutdown();
        if (e != null) {
            throw e;
        }
    }

    public final void a() {
        try {
            a(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR);
        } catch (IOException unused) {
        }
    }

    public void a(boolean z) throws IOException {
        if (z) {
            this.r.connectionPreface();
            this.r.b(this.n);
            if (this.n.c() != 65535) {
                this.r.windowUpdate(0, r6 - 65535);
            }
        }
        new Thread(this.s).start();
    }

    public void a(int i2, List<iu1> list) {
        synchronized (this) {
            if (this.t.contains(Integer.valueOf(i2))) {
                c(i2, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.t.add(Integer.valueOf(i2));
            try {
                a(new c("OkHttp %s Push Request[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, list));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    public void a(int i2, BufferedSource bufferedSource, int i3, boolean z) throws IOException {
        Buffer buffer = new Buffer();
        long j2 = i3;
        bufferedSource.require(j2);
        bufferedSource.read(buffer, j2);
        if (buffer.size() == j2) {
            a(new e("OkHttp %s Push Data[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, buffer, i3, z));
            return;
        }
        throw new IOException(buffer.size() + " != " + i3);
    }

    public void a(int i2, ErrorCode errorCode) {
        a(new f("OkHttp %s Push Reset[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, errorCode));
    }

    public final synchronized void a(jt1 jt1Var) {
        if (!b()) {
            this.i.execute(jt1Var);
        }
    }
}
