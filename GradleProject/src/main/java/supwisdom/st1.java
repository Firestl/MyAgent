package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import dc.squareup.okhttp3.internal.connection.RealConnection;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.http2.ErrorCode;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.fv1;
import supwisdom.mu1;
import supwisdom.ws1;

/* JADX INFO: compiled from: RealConnection.java */
/* JADX INFO: loaded from: classes3.dex */
public final class st1 extends mu1.h implements ks1 {
    public final ls1 b;
    public final ft1 c;
    public Socket d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Socket f9212e;
    public ts1 f;
    public Protocol g;
    public mu1 h;
    public BufferedSource i;
    public BufferedSink j;
    public boolean k;
    public int l;
    public int m = 1;
    public final List<Reference<vt1>> n = new ArrayList();
    public long o = Long.MAX_VALUE;

    /* JADX INFO: compiled from: RealConnection.java */
    public class a extends fv1.g {
        public final /* synthetic */ vt1 d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(st1 st1Var, boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink, vt1 vt1Var) {
            super(z, bufferedSource, bufferedSink);
            this.d = vt1Var;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            vt1 vt1Var = this.d;
            vt1Var.a(true, vt1Var.b(), -1L, null);
        }
    }

    public st1(ls1 ls1Var, ft1 ft1Var) {
        this.b = ls1Var;
        this.c = ft1Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f4 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0142 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r17, int r18, int r19, int r20, boolean r21, supwisdom.gs1 r22, supwisdom.rs1 r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.st1.a(int, int, int, int, boolean, supwisdom.gs1, supwisdom.rs1):void");
    }

    public final bt1 b() throws IOException {
        bt1.a aVar = new bt1.a();
        aVar.a(this.c.a().k());
        aVar.a("CONNECT", (ct1) null);
        aVar.b("Host", kt1.a(this.c.a().k(), true));
        aVar.b("Proxy-Connection", "Keep-Alive");
        aVar.b("User-Agent", lt1.a());
        bt1 bt1VarA = aVar.a();
        dt1.a aVar2 = new dt1.a();
        aVar2.a(bt1VarA);
        aVar2.a(Protocol.HTTP_1_1);
        aVar2.a(407);
        aVar2.a("Preemptive Authenticate");
        aVar2.a(kt1.c);
        aVar2.b(-1L);
        aVar2.a(-1L);
        aVar2.b("Proxy-Authenticate", "OkHttp-Preemptive");
        bt1 bt1VarA2 = this.c.a().g().a(this.c, aVar2.a());
        return bt1VarA2 != null ? bt1VarA2 : bt1VarA;
    }

    public ts1 c() {
        return this.f;
    }

    public boolean d() {
        return this.h != null;
    }

    public ft1 e() {
        return this.c;
    }

    @Override // supwisdom.ks1
    public Protocol protocol() {
        return this.g;
    }

    @Override // supwisdom.ks1
    public Socket socket() {
        return this.f9212e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.c.a().k().g());
        sb.append(Constants.COLON_SEPARATOR);
        sb.append(this.c.a().k().k());
        sb.append(", proxy=");
        sb.append(this.c.b());
        sb.append(" hostAddress=");
        sb.append(this.c.d());
        sb.append(" cipherSuite=");
        ts1 ts1Var = this.f;
        sb.append(ts1Var != null ? ts1Var.a() : "none");
        sb.append(" protocol=");
        sb.append(this.g);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public final void a(int i, int i2, int i3, gs1 gs1Var, rs1 rs1Var) throws IOException {
        bt1 bt1VarB = b();
        vs1 vs1VarG = bt1VarB.g();
        for (int i4 = 0; i4 < 21; i4++) {
            a(i, i2, gs1Var, rs1Var);
            bt1VarB = a(i2, i3, bt1VarB, vs1VarG);
            if (bt1VarB == null) {
                return;
            }
            kt1.a(this.d);
            this.d = null;
            this.j = null;
            this.i = null;
            rs1Var.connectEnd(gs1Var, this.c.d(), this.c.b(), null);
        }
    }

    public final void a(int i, int i2, gs1 gs1Var, rs1 rs1Var) throws IOException {
        Socket socketCreateSocket;
        Proxy proxyB = this.c.b();
        cs1 cs1VarA = this.c.a();
        if (proxyB.type() != Proxy.Type.DIRECT && proxyB.type() != Proxy.Type.HTTP) {
            socketCreateSocket = new Socket(proxyB);
        } else {
            socketCreateSocket = cs1VarA.i().createSocket();
        }
        this.d = socketCreateSocket;
        rs1Var.connectStart(gs1Var, this.c.d(), proxyB);
        this.d.setSoTimeout(i2);
        try {
            yu1.c().a(this.d, this.c.d(), i);
            try {
                this.i = Okio.buffer(Okio.source(this.d));
                this.j = Okio.buffer(Okio.sink(this.d));
            } catch (NullPointerException e2) {
                if (RealConnection.NPE_THROW_WITH_NULL.equals(e2.getMessage())) {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.c.d());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    public final void a(rt1 rt1Var, int i, gs1 gs1Var, rs1 rs1Var) throws Throwable {
        if (this.c.a().j() == null) {
            if (this.c.a().e().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
                this.f9212e = this.d;
                this.g = Protocol.H2_PRIOR_KNOWLEDGE;
                a(i);
                return;
            } else {
                this.f9212e = this.d;
                this.g = Protocol.HTTP_1_1;
                return;
            }
        }
        rs1Var.secureConnectStart(gs1Var);
        a(rt1Var);
        rs1Var.secureConnectEnd(gs1Var, this.f);
        if (this.g == Protocol.HTTP_2) {
            a(i);
        }
    }

    public final void a(int i) throws IOException {
        this.f9212e.setSoTimeout(0);
        mu1.g gVar = new mu1.g(true);
        gVar.a(this.f9212e, this.c.a().k().g(), this.i, this.j);
        gVar.a(this);
        gVar.a(i);
        mu1 mu1VarA = gVar.a();
        this.h = mu1VarA;
        mu1VarA.d();
    }

    public final void a(rt1 rt1Var) throws Throwable {
        Protocol protocol;
        cs1 cs1VarA = this.c.a();
        SSLSocket sSLSocket = null;
        try {
            try {
                SSLSocket sSLSocket2 = (SSLSocket) cs1VarA.j().createSocket(this.d, cs1VarA.k().g(), cs1VarA.k().k(), true);
                try {
                    ms1 ms1VarA = rt1Var.a(sSLSocket2);
                    if (ms1VarA.c()) {
                        yu1.c().a(sSLSocket2, cs1VarA.k().g(), cs1VarA.e());
                    }
                    sSLSocket2.startHandshake();
                    SSLSession session = sSLSocket2.getSession();
                    ts1 ts1VarA = ts1.a(session);
                    if (cs1VarA.d().verify(cs1VarA.k().g(), session)) {
                        cs1VarA.a().a(cs1VarA.k().g(), ts1VarA.b());
                        String strB = ms1VarA.c() ? yu1.c().b(sSLSocket2) : null;
                        this.f9212e = sSLSocket2;
                        this.i = Okio.buffer(Okio.source(sSLSocket2));
                        this.j = Okio.buffer(Okio.sink(this.f9212e));
                        this.f = ts1VarA;
                        if (strB != null) {
                            protocol = Protocol.get(strB);
                        } else {
                            protocol = Protocol.HTTP_1_1;
                        }
                        this.g = protocol;
                        if (sSLSocket2 != null) {
                            yu1.c().a(sSLSocket2);
                            return;
                        }
                        return;
                    }
                    X509Certificate x509Certificate = (X509Certificate) ts1VarA.b().get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + cs1VarA.k().g() + " not verified:\n    certificate: " + is1.a((Certificate) x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + dv1.a(x509Certificate));
                } catch (AssertionError e2) {
                    e = e2;
                    if (!kt1.a(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        yu1.c().a(sSLSocket);
                    }
                    kt1.a((Socket) sSLSocket);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (AssertionError e3) {
            e = e3;
        }
    }

    public final bt1 a(int i, int i2, bt1 bt1Var, vs1 vs1Var) throws IOException {
        String str = "CONNECT " + kt1.a(vs1Var, true) + " HTTP/1.1";
        while (true) {
            hu1 hu1Var = new hu1(null, null, this.i, this.j);
            this.i.timeout().timeout(i, TimeUnit.MILLISECONDS);
            this.j.timeout().timeout(i2, TimeUnit.MILLISECONDS);
            hu1Var.a(bt1Var.c(), str);
            hu1Var.finishRequest();
            dt1.a responseHeaders = hu1Var.readResponseHeaders(false);
            responseHeaders.a(bt1Var);
            dt1 dt1VarA = responseHeaders.a();
            long jA = au1.a(dt1VarA);
            if (jA == -1) {
                jA = 0;
            }
            Source sourceB = hu1Var.b(jA);
            kt1.b(sourceB, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            sourceB.close();
            int iC = dt1VarA.c();
            if (iC == 200) {
                if (this.i.buffer().exhausted() && this.j.buffer().exhausted()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iC == 407) {
                bt1 bt1VarA = this.c.a().g().a(this.c, dt1VarA);
                if (bt1VarA != null) {
                    if ("close".equalsIgnoreCase(dt1VarA.a(HttpHeaders.HEAD_KEY_CONNECTION))) {
                        return bt1VarA;
                    }
                    bt1Var = bt1VarA;
                } else {
                    throw new IOException("Failed to authenticate with proxy");
                }
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + dt1VarA.c());
            }
        }
    }

    public boolean a(cs1 cs1Var, @Nullable ft1 ft1Var) {
        if (this.n.size() >= this.m || this.k || !it1.f7984a.a(this.c.a(), cs1Var)) {
            return false;
        }
        if (cs1Var.k().g().equals(e().a().k().g())) {
            return true;
        }
        if (this.h == null || ft1Var == null || ft1Var.b().type() != Proxy.Type.DIRECT || this.c.b().type() != Proxy.Type.DIRECT || !this.c.d().equals(ft1Var.d()) || ft1Var.a().d() != dv1.f7386a || !a(cs1Var.k())) {
            return false;
        }
        try {
            cs1Var.a().a(cs1Var.k().g(), c().b());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean a(vs1 vs1Var) {
        if (vs1Var.k() != this.c.a().k().k()) {
            return false;
        }
        if (vs1Var.g().equals(this.c.a().k().g())) {
            return true;
        }
        return this.f != null && dv1.f7386a.a(vs1Var.g(), (X509Certificate) this.f.b().get(0));
    }

    public yt1 a(zs1 zs1Var, ws1.a aVar, vt1 vt1Var) throws SocketException {
        if (this.h != null) {
            return new lu1(zs1Var, aVar, vt1Var, this.h);
        }
        this.f9212e.setSoTimeout(aVar.readTimeoutMillis());
        this.i.timeout().timeout(aVar.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.j.timeout().timeout(aVar.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new hu1(zs1Var, vt1Var, this.i, this.j);
    }

    public fv1.g a(vt1 vt1Var) {
        return new a(this, true, this.i, this.j, vt1Var);
    }

    public void a() {
        kt1.a(this.d);
    }

    public boolean a(boolean z) {
        if (this.f9212e.isClosed() || this.f9212e.isInputShutdown() || this.f9212e.isOutputShutdown()) {
            return false;
        }
        if (this.h != null) {
            return !r0.b();
        }
        if (z) {
            try {
                int soTimeout = this.f9212e.getSoTimeout();
                try {
                    this.f9212e.setSoTimeout(1);
                    return !this.i.exhausted();
                } finally {
                    this.f9212e.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    @Override // supwisdom.mu1.h
    public void a(ou1 ou1Var) throws IOException {
        ou1Var.a(ErrorCode.REFUSED_STREAM);
    }

    @Override // supwisdom.mu1.h
    public void a(mu1 mu1Var) {
        synchronized (this.b) {
            this.m = mu1Var.c();
        }
    }
}
