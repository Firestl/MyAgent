package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.squareup.okhttp.Protocol;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import supwisdom.kf1;
import supwisdom.oe1;
import supwisdom.pe1;
import supwisdom.se1;
import supwisdom.ue1;

/* JADX INFO: compiled from: HttpEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class of1 {
    public static final ve1 u = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final re1 f8665a;
    public ie1 b;
    public ae1 c;
    public vf1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public we1 f8666e;
    public final ue1 f;
    public yf1 g;
    public long h = -1;
    public boolean i;
    public final boolean j;
    public final se1 k;
    public se1 l;
    public ue1 m;
    public ue1 n;
    public Sink o;
    public BufferedSink p;
    public final boolean q;
    public final boolean r;
    public jf1 s;
    public kf1 t;

    /* JADX INFO: compiled from: HttpEngine.java */
    public static class a extends ve1 {
        @Override // supwisdom.ve1
        public long b() {
            return 0L;
        }

        @Override // supwisdom.ve1
        public BufferedSource c() {
            return new Buffer();
        }
    }

    /* JADX INFO: compiled from: HttpEngine.java */
    public class b implements Source {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8667a;
        public final /* synthetic */ BufferedSource b;
        public final /* synthetic */ jf1 c;
        public final /* synthetic */ BufferedSink d;

        public b(of1 of1Var, BufferedSource bufferedSource, jf1 jf1Var, BufferedSink bufferedSink) {
            this.b = bufferedSource;
            this.c = jf1Var;
            this.d = bufferedSink;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.f8667a && !gf1.a(this, 100, TimeUnit.MILLISECONDS)) {
                this.f8667a = true;
                this.c.abort();
            }
            this.b.close();
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            try {
                long j2 = this.b.read(buffer, j);
                if (j2 != -1) {
                    buffer.copyTo(this.d.buffer(), buffer.size() - j2, j2);
                    this.d.emitCompleteSegments();
                    return j2;
                }
                if (!this.f8667a) {
                    this.f8667a = true;
                    this.d.close();
                }
                return -1L;
            } catch (IOException e2) {
                if (!this.f8667a) {
                    this.f8667a = true;
                    this.c.abort();
                }
                throw e2;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.b.timeout();
        }
    }

    /* JADX INFO: compiled from: HttpEngine.java */
    public class c implements pe1.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8668a;
        public int b;

        public c(int i, se1 se1Var) {
            this.f8668a = i;
        }

        public ie1 a() {
            return of1.this.b;
        }

        @Override // supwisdom.pe1.a
        public ue1 a(se1 se1Var) throws IOException {
            this.b++;
            if (this.f8668a > 0) {
                pe1 pe1Var = of1.this.f8665a.w().get(this.f8668a - 1);
                ae1 ae1VarA = a().e().a();
                if (!se1Var.h().getHost().equals(ae1VarA.d()) || gf1.a(se1Var.h()) != ae1VarA.e()) {
                    throw new IllegalStateException("network interceptor " + pe1Var + " must retain the same host and port");
                }
                if (this.b > 1) {
                    throw new IllegalStateException("network interceptor " + pe1Var + " must call proceed() exactly once");
                }
            }
            if (this.f8668a >= of1.this.f8665a.w().size()) {
                of1.this.g.a(se1Var);
                if (of1.this.k() && se1Var.a() != null) {
                    BufferedSink bufferedSinkBuffer = Okio.buffer(of1.this.g.a(se1Var, se1Var.a().a()));
                    se1Var.a().a(bufferedSinkBuffer);
                    bufferedSinkBuffer.close();
                }
                return of1.this.l();
            }
            c cVar = of1.this.new c(this.f8668a + 1, se1Var);
            pe1 pe1Var2 = of1.this.f8665a.w().get(this.f8668a);
            ue1 ue1VarA = pe1Var2.a(cVar);
            if (cVar.b == 1) {
                return ue1VarA;
            }
            throw new IllegalStateException("network interceptor " + pe1Var2 + " must call proceed() exactly once");
        }
    }

    public of1(re1 re1Var, se1 se1Var, boolean z, boolean z2, boolean z3, ie1 ie1Var, vf1 vf1Var, uf1 uf1Var, ue1 ue1Var) {
        this.f8665a = re1Var;
        this.k = se1Var;
        this.j = z;
        this.q = z2;
        this.r = z3;
        this.b = ie1Var;
        this.d = vf1Var;
        this.o = uf1Var;
        this.f = ue1Var;
        if (ie1Var == null) {
            this.f8666e = null;
        } else {
            ze1.b.b(ie1Var, this);
            this.f8666e = ie1Var.e();
        }
    }

    public se1 d() throws IOException {
        String strA;
        if (this.n == null) {
            throw new IllegalStateException();
        }
        Proxy proxyB = h() != null ? h().b() : this.f8665a.n();
        int iE = this.n.e();
        if (iE != 307 && iE != 308) {
            if (iE != 401) {
                if (iE != 407) {
                    switch (iE) {
                        case 300:
                        case 301:
                        case com.umeng.ccg.c.o /* 302 */:
                        case com.umeng.ccg.c.p /* 303 */:
                            break;
                        default:
                            return null;
                    }
                } else if (proxyB.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
            }
            return rf1.a(this.f8665a.b(), this.n, proxyB);
        }
        if (!this.k.e().equals("GET") && !this.k.e().equals("HEAD")) {
            return null;
        }
        if (!this.f8665a.j() || (strA = this.n.a(HttpHeaders.HEAD_KEY_LOCATION)) == null) {
            return null;
        }
        URL url = new URL(this.k.h(), strA);
        if (!url.getProtocol().equals("https") && !url.getProtocol().equals("http")) {
            return null;
        }
        if (!url.getProtocol().equals(this.k.h().getProtocol()) && !this.f8665a.k()) {
            return null;
        }
        se1.b bVarF = this.k.f();
        if (pf1.b(this.k.e())) {
            bVarF.a("GET", (te1) null);
            bVarF.a("Transfer-Encoding");
            bVarF.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            bVarF.a("Content-Type");
        }
        if (!a(url)) {
            bVarF.a("Authorization");
        }
        bVarF.a(url);
        return bVarF.a();
    }

    public ie1 e() {
        return this.b;
    }

    public se1 f() {
        return this.k;
    }

    public ue1 g() {
        ue1 ue1Var = this.n;
        if (ue1Var != null) {
            return ue1Var;
        }
        throw new IllegalStateException();
    }

    public we1 h() {
        return this.f8666e;
    }

    public final void i() throws IOException {
        af1 af1VarA = ze1.b.a(this.f8665a);
        if (af1VarA == null) {
            return;
        }
        if (kf1.a(this.n, this.l)) {
            this.s = af1VarA.a(c(this.n));
        } else if (pf1.a(this.l.e())) {
            try {
                af1VarA.b(this.l);
            } catch (IOException unused) {
            }
        }
    }

    public final ie1 j() throws IOException {
        ie1 ie1VarC = c();
        ze1.b.a(this.f8665a, ie1VarC, this, this.l);
        return ie1VarC;
    }

    public boolean k() {
        return pf1.b(this.k.e());
    }

    public final ue1 l() throws IOException {
        this.g.finishRequest();
        ue1.b bVarB = this.g.b();
        bVarB.a(this.l);
        bVarB.a(this.b.b());
        bVarB.b(rf1.c, Long.toString(this.h));
        bVarB.b(rf1.d, Long.toString(System.currentTimeMillis()));
        ue1 ue1VarA = bVarB.a();
        if (!this.r) {
            ue1.b bVarJ = ue1VarA.j();
            bVarJ.a(this.g.a(ue1VarA));
            ue1VarA = bVarJ.a();
        }
        ze1.b.a(this.b, ue1VarA.k());
        return ue1VarA;
    }

    public void m() throws IOException {
        ue1 ue1VarL;
        if (this.n != null) {
            return;
        }
        if (this.l == null && this.m == null) {
            throw new IllegalStateException("call sendRequest() first!");
        }
        se1 se1Var = this.l;
        if (se1Var == null) {
            return;
        }
        if (this.r) {
            this.g.a(se1Var);
            ue1VarL = l();
        } else if (this.q) {
            BufferedSink bufferedSink = this.p;
            if (bufferedSink != null && bufferedSink.buffer().size() > 0) {
                this.p.emit();
            }
            if (this.h == -1) {
                if (rf1.a(this.l) == -1) {
                    Sink sink = this.o;
                    if (sink instanceof uf1) {
                        long jA = ((uf1) sink).a();
                        se1.b bVarF = this.l.f();
                        bVarF.b(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, Long.toString(jA));
                        this.l = bVarF.a();
                    }
                }
                this.g.a(this.l);
            }
            Sink sink2 = this.o;
            if (sink2 != null) {
                BufferedSink bufferedSink2 = this.p;
                if (bufferedSink2 != null) {
                    bufferedSink2.close();
                } else {
                    sink2.close();
                }
                Sink sink3 = this.o;
                if (sink3 instanceof uf1) {
                    this.g.a((uf1) sink3);
                }
            }
            ue1VarL = l();
        } else {
            ue1VarL = new c(0, se1Var).a(this.l);
        }
        a(ue1VarL.g());
        ue1 ue1Var = this.m;
        if (ue1Var != null) {
            if (a(ue1Var, ue1VarL)) {
                ue1.b bVarJ = this.m.j();
                bVarJ.a(this.k);
                bVarJ.d(c(this.f));
                bVarJ.a(a(this.m.g(), ue1VarL.g()));
                bVarJ.a(c(this.m));
                bVarJ.c(c(ue1VarL));
                this.n = bVarJ.a();
                ue1VarL.a().close();
                n();
                af1 af1VarA = ze1.b.a(this.f8665a);
                af1VarA.trackConditionalCacheHit();
                af1VarA.a(this.m, c(this.n));
                this.n = a(this.n);
                return;
            }
            gf1.a(this.m.a());
        }
        ue1.b bVarJ2 = ue1VarL.j();
        bVarJ2.a(this.k);
        bVarJ2.d(c(this.f));
        bVarJ2.a(c(this.m));
        bVarJ2.c(c(ue1VarL));
        ue1 ue1VarA = bVarJ2.a();
        this.n = ue1VarA;
        if (b(ue1VarA)) {
            i();
            this.n = a(a(this.s, this.n));
        }
    }

    public void n() throws IOException {
        yf1 yf1Var = this.g;
        if (yf1Var != null && this.b != null) {
            yf1Var.a();
        }
        this.b = null;
    }

    public void o() throws IOException {
        if (this.t != null) {
            return;
        }
        if (this.g != null) {
            throw new IllegalStateException();
        }
        se1 se1VarA = a(this.k);
        af1 af1VarA = ze1.b.a(this.f8665a);
        ue1 ue1VarA = af1VarA != null ? af1VarA.a(se1VarA) : null;
        kf1 kf1VarC = new kf1.b(System.currentTimeMillis(), se1VarA, ue1VarA).c();
        this.t = kf1VarC;
        this.l = kf1VarC.f8170a;
        this.m = kf1VarC.b;
        if (af1VarA != null) {
            af1VarA.a(kf1VarC);
        }
        if (ue1VarA != null && this.m == null) {
            gf1.a(ue1VarA.a());
        }
        if (this.l != null) {
            if (this.b == null) {
                b();
            }
            this.g = ze1.b.a(this.b, this);
            if (this.q && k() && this.o == null) {
                long jA = rf1.a(se1VarA);
                if (!this.j) {
                    this.g.a(this.l);
                    this.o = this.g.a(this.l, jA);
                    return;
                } else {
                    if (jA > 2147483647L) {
                        throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                    }
                    if (jA == -1) {
                        this.o = new uf1();
                        return;
                    } else {
                        this.g.a(this.l);
                        this.o = new uf1((int) jA);
                        return;
                    }
                }
            }
            return;
        }
        if (this.b != null) {
            ze1.b.a(this.f8665a.e(), this.b);
            this.b = null;
        }
        ue1 ue1Var = this.m;
        if (ue1Var != null) {
            ue1.b bVarJ = ue1Var.j();
            bVarJ.a(this.k);
            bVarJ.d(c(this.f));
            bVarJ.a(c(this.m));
            this.n = bVarJ.a();
        } else {
            ue1.b bVar = new ue1.b();
            bVar.a(this.k);
            bVar.d(c(this.f));
            bVar.a(Protocol.HTTP_1_1);
            bVar.a(504);
            bVar.a("Unsatisfiable Request (only-if-cached)");
            bVar.a(u);
            this.n = bVar.a();
        }
        this.n = a(this.n);
    }

    public void p() {
        if (this.h != -1) {
            throw new IllegalStateException();
        }
        this.h = System.currentTimeMillis();
    }

    public static ue1 c(ue1 ue1Var) {
        if (ue1Var == null || ue1Var.a() == null) {
            return ue1Var;
        }
        ue1.b bVarJ = ue1Var.j();
        bVarJ.a((ve1) null);
        return bVarJ.a();
    }

    public of1 a(IOException iOException, Sink sink) {
        vf1 vf1Var = this.d;
        if (vf1Var != null && this.b != null) {
            a(vf1Var, iOException);
        }
        boolean z = sink == null || (sink instanceof uf1);
        if (this.d == null && this.b == null) {
            return null;
        }
        vf1 vf1Var2 = this.d;
        if ((vf1Var2 == null || vf1Var2.a()) && a(iOException) && z) {
            return new of1(this.f8665a, this.k, this.j, this.q, this.r, a(), this.d, (uf1) sink, this.f);
        }
        return null;
    }

    public final void b() throws IOException {
        if (this.b != null) {
            throw new IllegalStateException();
        }
        if (this.d == null) {
            ae1 ae1VarA = a(this.f8665a, this.l);
            this.c = ae1VarA;
            this.d = vf1.a(ae1VarA, this.l, this.f8665a);
        }
        ie1 ie1VarJ = j();
        this.b = ie1VarJ;
        this.f8666e = ie1VarJ.e();
    }

    public final ie1 c() throws IOException {
        ie1 ie1VarA;
        je1 je1VarE = this.f8665a.e();
        while (true) {
            ie1VarA = je1VarE.a(this.c);
            if (ie1VarA != null) {
                if (this.l.e().equals("GET") || ze1.b.b(ie1VarA)) {
                    break;
                }
                ie1VarA.f().close();
            } else {
                return new ie1(je1VarE, this.d.f());
            }
        }
        return ie1VarA;
    }

    public static boolean b(ue1 ue1Var) {
        if (ue1Var.l().e().equals("HEAD")) {
            return false;
        }
        int iE = ue1Var.e();
        return (((iE >= 100 && iE < 200) || iE == 204 || iE == 304) && rf1.a(ue1Var) == -1 && !"chunked".equalsIgnoreCase(ue1Var.a("Transfer-Encoding"))) ? false : true;
    }

    public final void a(vf1 vf1Var, IOException iOException) {
        if (ze1.b.c(this.b) > 0) {
            return;
        }
        vf1Var.a(this.b.e(), iOException);
    }

    public static String b(URL url) {
        if (gf1.a(url) != gf1.a(url.getProtocol())) {
            return url.getHost() + Constants.COLON_SEPARATOR + url.getPort();
        }
        return url.getHost();
    }

    public final boolean a(IOException iOException) {
        return (!this.f8665a.q() || (iOException instanceof SSLPeerUnverifiedException) || ((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) ? false : true;
    }

    public ie1 a() {
        BufferedSink bufferedSink = this.p;
        if (bufferedSink != null) {
            gf1.a(bufferedSink);
        } else {
            Sink sink = this.o;
            if (sink != null) {
                gf1.a(sink);
            }
        }
        ue1 ue1Var = this.n;
        if (ue1Var == null) {
            ie1 ie1Var = this.b;
            if (ie1Var != null) {
                gf1.a(ie1Var.f());
            }
            this.b = null;
            return null;
        }
        gf1.a(ue1Var.a());
        yf1 yf1Var = this.g;
        if (yf1Var != null && this.b != null && !yf1Var.c()) {
            gf1.a(this.b.f());
            this.b = null;
            return null;
        }
        ie1 ie1Var2 = this.b;
        if (ie1Var2 != null && !ze1.b.a(ie1Var2)) {
            this.b = null;
        }
        ie1 ie1Var3 = this.b;
        this.b = null;
        return ie1Var3;
    }

    public final ue1 a(ue1 ue1Var) throws IOException {
        if (!this.i || !com.efs.sdk.base.Constants.CP_GZIP.equalsIgnoreCase(this.n.a(HttpHeaders.HEAD_KEY_CONTENT_ENCODING)) || ue1Var.a() == null) {
            return ue1Var;
        }
        GzipSource gzipSource = new GzipSource(ue1Var.a().c());
        oe1.b bVarA = ue1Var.g().a();
        bVarA.b(HttpHeaders.HEAD_KEY_CONTENT_ENCODING);
        bVarA.b(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
        oe1 oe1VarA = bVarA.a();
        ue1.b bVarJ = ue1Var.j();
        bVarJ.a(oe1VarA);
        bVarJ.a(new sf1(oe1VarA, Okio.buffer(gzipSource)));
        return bVarJ.a();
    }

    public final se1 a(se1 se1Var) throws IOException {
        se1.b bVarF = se1Var.f();
        if (se1Var.a("Host") == null) {
            bVarF.b("Host", b(se1Var.h()));
        }
        ie1 ie1Var = this.b;
        if ((ie1Var == null || ie1Var.d() != Protocol.HTTP_1_0) && se1Var.a(HttpHeaders.HEAD_KEY_CONNECTION) == null) {
            bVarF.b(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
        }
        if (se1Var.a(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING) == null) {
            this.i = true;
            bVarF.b(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, com.efs.sdk.base.Constants.CP_GZIP);
        }
        CookieHandler cookieHandlerG = this.f8665a.g();
        if (cookieHandlerG != null) {
            rf1.a(bVarF, cookieHandlerG.get(se1Var.g(), rf1.b(bVarF.a().c(), null)));
        }
        if (se1Var.a("User-Agent") == null) {
            bVarF.b("User-Agent", hf1.a());
        }
        return bVarF.a();
    }

    public final ue1 a(jf1 jf1Var, ue1 ue1Var) throws IOException {
        Sink sinkBody;
        if (jf1Var == null || (sinkBody = jf1Var.body()) == null) {
            return ue1Var;
        }
        b bVar = new b(this, ue1Var.a().c(), jf1Var, Okio.buffer(sinkBody));
        ue1.b bVarJ = ue1Var.j();
        bVarJ.a(new sf1(ue1Var.g(), Okio.buffer(bVar)));
        return bVarJ.a();
    }

    public static boolean a(ue1 ue1Var, ue1 ue1Var2) {
        Date dateB;
        if (ue1Var2.e() == 304) {
            return true;
        }
        Date dateB2 = ue1Var.g().b(HttpHeaders.HEAD_KEY_LAST_MODIFIED);
        return (dateB2 == null || (dateB = ue1Var2.g().b(HttpHeaders.HEAD_KEY_LAST_MODIFIED)) == null || dateB.getTime() >= dateB2.getTime()) ? false : true;
    }

    public static oe1 a(oe1 oe1Var, oe1 oe1Var2) throws IOException {
        oe1.b bVar = new oe1.b();
        int iB = oe1Var.b();
        for (int i = 0; i < iB; i++) {
            String strA = oe1Var.a(i);
            String strB = oe1Var.b(i);
            if ((!"Warning".equalsIgnoreCase(strA) || !strB.startsWith("1")) && (!rf1.a(strA) || oe1Var2.a(strA) == null)) {
                bVar.a(strA, strB);
            }
        }
        int iB2 = oe1Var2.b();
        for (int i2 = 0; i2 < iB2; i2++) {
            String strA2 = oe1Var2.a(i2);
            if (!HttpHeaders.HEAD_KEY_CONTENT_LENGTH.equalsIgnoreCase(strA2) && rf1.a(strA2)) {
                bVar.a(strA2, oe1Var2.b(i2));
            }
        }
        return bVar.a();
    }

    public void a(oe1 oe1Var) throws IOException {
        CookieHandler cookieHandlerG = this.f8665a.g();
        if (cookieHandlerG != null) {
            cookieHandlerG.put(this.k.g(), rf1.b(oe1Var, null));
        }
    }

    public boolean a(URL url) {
        URL urlH = this.k.h();
        return urlH.getHost().equals(url.getHost()) && gf1.a(urlH) == gf1.a(url) && urlH.getProtocol().equals(url.getProtocol());
    }

    public static ae1 a(re1 re1Var, se1 se1Var) throws UnknownHostException {
        SSLSocketFactory sSLSocketFactoryS;
        HostnameVerifier hostnameVerifierL;
        ge1 ge1VarC;
        String host = se1Var.h().getHost();
        if (host != null && host.length() != 0) {
            if (se1Var.d()) {
                sSLSocketFactoryS = re1Var.s();
                hostnameVerifierL = re1Var.l();
                ge1VarC = re1Var.c();
            } else {
                sSLSocketFactoryS = null;
                hostnameVerifierL = null;
                ge1VarC = null;
            }
            return new ae1(host, gf1.a(se1Var.h()), re1Var.r(), sSLSocketFactoryS, hostnameVerifierL, ge1VarC, re1Var.b(), re1Var.n(), re1Var.m(), re1Var.f(), re1Var.o());
        }
        throw new UnknownHostException(se1Var.h().toString());
    }
}
