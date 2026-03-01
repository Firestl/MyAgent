package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.internal.ws.WebSocketProtocol;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Protocol;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import supwisdom.bt1;
import supwisdom.hv1;
import supwisdom.zs1;

/* JADX INFO: compiled from: RealWebSocket.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fv1 implements gt1, hv1.a {
    public static final List<Protocol> x = Collections.singletonList(Protocol.HTTP_1_1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final bt1 f7652a;
    public final ht1 b;
    public final Random c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f7653e;
    public gs1 f;
    public final Runnable g;
    public hv1 h;
    public iv1 i;
    public ScheduledExecutorService j;
    public g k;
    public long n;
    public boolean o;
    public ScheduledFuture<?> p;
    public String r;
    public boolean s;
    public int t;
    public int u;
    public int v;
    public boolean w;
    public final ArrayDeque<ByteString> l = new ArrayDeque<>();
    public final ArrayDeque<Object> m = new ArrayDeque<>();
    public int q = -1;

    /* JADX INFO: compiled from: RealWebSocket.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            do {
                try {
                } catch (IOException e2) {
                    fv1.this.a(e2, (dt1) null);
                    return;
                }
            } while (fv1.this.d());
        }
    }

    /* JADX INFO: compiled from: RealWebSocket.java */
    public class b implements hs1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ bt1 f7655a;

        public b(bt1 bt1Var) {
            this.f7655a = bt1Var;
        }

        @Override // supwisdom.hs1
        public void onFailure(gs1 gs1Var, IOException iOException) {
            fv1.this.a(iOException, (dt1) null);
        }

        @Override // supwisdom.hs1
        public void onResponse(gs1 gs1Var, dt1 dt1Var) {
            try {
                fv1.this.a(dt1Var);
                vt1 vt1VarA = it1.f7984a.a(gs1Var);
                vt1VarA.e();
                g gVarA = vt1VarA.c().a(vt1VarA);
                try {
                    fv1.this.b.a(fv1.this, dt1Var);
                    fv1.this.a("OkHttp WebSocket " + this.f7655a.g().m(), gVarA);
                    vt1VarA.c().socket().setSoTimeout(0);
                    fv1.this.b();
                } catch (Exception e2) {
                    fv1.this.a(e2, (dt1) null);
                }
            } catch (ProtocolException e3) {
                fv1.this.a(e3, dt1Var);
                kt1.a(dt1Var);
            }
        }
    }

    /* JADX INFO: compiled from: RealWebSocket.java */
    public final class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fv1.this.a();
        }
    }

    /* JADX INFO: compiled from: RealWebSocket.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7657a;
        public final ByteString b;
        public final long c;

        public d(int i, ByteString byteString, long j) {
            this.f7657a = i;
            this.b = byteString;
            this.c = j;
        }
    }

    /* JADX INFO: compiled from: RealWebSocket.java */
    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7658a;
        public final ByteString b;
    }

    /* JADX INFO: compiled from: RealWebSocket.java */
    public final class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fv1.this.e();
        }
    }

    /* JADX INFO: compiled from: RealWebSocket.java */
    public static abstract class g implements Closeable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f7660a;
        public final BufferedSource b;
        public final BufferedSink c;

        public g(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.f7660a = z;
            this.b = bufferedSource;
            this.c = bufferedSink;
        }
    }

    public fv1(bt1 bt1Var, ht1 ht1Var, Random random, long j) {
        if (!"GET".equals(bt1Var.e())) {
            throw new IllegalArgumentException("Request must be GET: " + bt1Var.e());
        }
        this.f7652a = bt1Var;
        this.b = ht1Var;
        this.c = random;
        this.d = j;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.f7653e = ByteString.of(bArr).base64();
        this.g = new a();
    }

    public void a() {
        this.f.cancel();
    }

    public void b() throws IOException {
        while (this.q == -1) {
            this.h.a();
        }
    }

    @Override // supwisdom.hv1.a
    public synchronized void c(ByteString byteString) {
        this.v++;
        this.w = false;
    }

    @Override // supwisdom.gt1
    public boolean close(int i, String str) {
        return a(i, str, 60000L);
    }

    public boolean d() throws IOException {
        g gVar;
        String str;
        synchronized (this) {
            if (this.s) {
                return false;
            }
            iv1 iv1Var = this.i;
            ByteString byteStringPoll = this.l.poll();
            int i = -1;
            Object obj = null;
            if (byteStringPoll == null) {
                Object objPoll = this.m.poll();
                if (objPoll instanceof d) {
                    int i2 = this.q;
                    str = this.r;
                    if (i2 != -1) {
                        g gVar2 = this.k;
                        this.k = null;
                        this.j.shutdown();
                        obj = objPoll;
                        i = i2;
                        gVar = gVar2;
                    } else {
                        this.p = this.j.schedule(new c(), ((d) objPoll).c, TimeUnit.MILLISECONDS);
                        i = i2;
                        gVar = null;
                    }
                } else {
                    if (objPoll == null) {
                        return false;
                    }
                    gVar = null;
                    str = null;
                }
                obj = objPoll;
            } else {
                gVar = null;
                str = null;
            }
            try {
                if (byteStringPoll != null) {
                    iv1Var.b(byteStringPoll);
                } else if (obj instanceof e) {
                    ByteString byteString = ((e) obj).b;
                    BufferedSink bufferedSinkBuffer = Okio.buffer(iv1Var.a(((e) obj).f7658a, byteString.size()));
                    bufferedSinkBuffer.write(byteString);
                    bufferedSinkBuffer.close();
                    synchronized (this) {
                        this.n -= (long) byteString.size();
                    }
                } else {
                    if (!(obj instanceof d)) {
                        throw new AssertionError();
                    }
                    d dVar = (d) obj;
                    iv1Var.a(dVar.f7657a, dVar.b);
                    if (gVar != null) {
                        this.b.a(this, i, str);
                    }
                }
                return true;
            } finally {
                kt1.a(gVar);
            }
        }
    }

    public void e() {
        synchronized (this) {
            if (this.s) {
                return;
            }
            iv1 iv1Var = this.i;
            int i = this.w ? this.t : -1;
            this.t++;
            this.w = true;
            if (i == -1) {
                try {
                    iv1Var.a(ByteString.EMPTY);
                    return;
                } catch (IOException e2) {
                    a(e2, (dt1) null);
                    return;
                }
            }
            a(new SocketTimeoutException("sent ping but didn't receive pong within " + this.d + "ms (after " + (i - 1) + " successful ping/pongs)"), (dt1) null);
        }
    }

    @Override // supwisdom.hv1.a
    public void onReadClose(int i, String str) {
        g gVar;
        if (i == -1) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            if (this.q != -1) {
                throw new IllegalStateException("already closed");
            }
            this.q = i;
            this.r = str;
            gVar = null;
            if (this.o && this.m.isEmpty()) {
                g gVar2 = this.k;
                this.k = null;
                if (this.p != null) {
                    this.p.cancel(false);
                }
                this.j.shutdown();
                gVar = gVar2;
            }
        }
        try {
            this.b.b(this, i, str);
            if (gVar != null) {
                this.b.a(this, i, str);
            }
        } finally {
            kt1.a(gVar);
        }
    }

    @Override // supwisdom.hv1.a
    public void onReadMessage(String str) throws IOException {
        this.b.a(this, str);
    }

    public void a(zs1 zs1Var) {
        zs1.b bVarQ = zs1Var.q();
        bVarQ.a(rs1.NONE);
        bVarQ.a(x);
        zs1 zs1VarA = bVarQ.a();
        bt1.a aVarF = this.f7652a.f();
        aVarF.b("Upgrade", "websocket");
        aVarF.b(HttpHeaders.HEAD_KEY_CONNECTION, "Upgrade");
        aVarF.b("Sec-WebSocket-Key", this.f7653e);
        aVarF.b("Sec-WebSocket-Version", "13");
        bt1 bt1VarA = aVarF.a();
        gs1 gs1VarA = it1.f7984a.a(zs1VarA, bt1VarA);
        this.f = gs1VarA;
        gs1VarA.timeout().clearTimeout();
        this.f.a(new b(bt1VarA));
    }

    @Override // supwisdom.hv1.a
    public synchronized void b(ByteString byteString) {
        if (!this.s && (!this.o || !this.m.isEmpty())) {
            this.l.add(byteString);
            c();
            this.u++;
        }
    }

    public final void c() {
        ScheduledExecutorService scheduledExecutorService = this.j;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.execute(this.g);
        }
    }

    public void a(dt1 dt1Var) throws ProtocolException {
        if (dt1Var.c() == 101) {
            String strA = dt1Var.a(HttpHeaders.HEAD_KEY_CONNECTION);
            if ("Upgrade".equalsIgnoreCase(strA)) {
                String strA2 = dt1Var.a("Upgrade");
                if ("websocket".equalsIgnoreCase(strA2)) {
                    String strA3 = dt1Var.a("Sec-WebSocket-Accept");
                    String strBase64 = ByteString.encodeUtf8(this.f7653e + WebSocketProtocol.ACCEPT_MAGIC).sha1().base64();
                    if (strBase64.equals(strA3)) {
                        return;
                    }
                    throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strBase64 + "' but was '" + strA3 + "'");
                }
                throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + strA2 + "'");
            }
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + strA + "'");
        }
        throw new ProtocolException("Expected HTTP 101 response but was '" + dt1Var.c() + Operators.SPACE_STR + dt1Var.g() + "'");
    }

    public void a(String str, g gVar) throws IOException {
        synchronized (this) {
            this.k = gVar;
            this.i = new iv1(gVar.f7660a, gVar.c, this.c);
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, kt1.a(str, false));
            this.j = scheduledThreadPoolExecutor;
            if (this.d != 0) {
                scheduledThreadPoolExecutor.scheduleAtFixedRate(new f(), this.d, this.d, TimeUnit.MILLISECONDS);
            }
            if (!this.m.isEmpty()) {
                c();
            }
        }
        this.h = new hv1(gVar.f7660a, gVar.b, this);
    }

    @Override // supwisdom.hv1.a
    public void a(ByteString byteString) throws IOException {
        this.b.a(this, byteString);
    }

    public synchronized boolean a(int i, String str, long j) {
        gv1.b(i);
        ByteString byteStringEncodeUtf8 = null;
        if (str != null) {
            byteStringEncodeUtf8 = ByteString.encodeUtf8(str);
            if (byteStringEncodeUtf8.size() > 123) {
                throw new IllegalArgumentException("reason.size() > 123: " + str);
            }
        }
        if (!this.s && !this.o) {
            this.o = true;
            this.m.add(new d(i, byteStringEncodeUtf8, j));
            c();
            return true;
        }
        return false;
    }

    public void a(Exception exc, @Nullable dt1 dt1Var) {
        synchronized (this) {
            if (this.s) {
                return;
            }
            this.s = true;
            g gVar = this.k;
            this.k = null;
            if (this.p != null) {
                this.p.cancel(false);
            }
            if (this.j != null) {
                this.j.shutdown();
            }
            try {
                this.b.a(this, exc, dt1Var);
            } finally {
                kt1.a(gVar);
            }
        }
    }
}
