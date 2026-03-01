package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.ws1;

/* JADX INFO: compiled from: RetryAndFollowUpInterceptor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fu1 implements ws1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final zs1 f7648a;
    public volatile vt1 b;
    public Object c;
    public volatile boolean d;

    public fu1(zs1 zs1Var, boolean z) {
        this.f7648a = zs1Var;
    }

    public void a() {
        this.d = true;
        vt1 vt1Var = this.b;
        if (vt1Var != null) {
            vt1Var.a();
        }
    }

    public boolean b() {
        return this.d;
    }

    public vt1 c() {
        return this.b;
    }

    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) throws IOException {
        dt1 dt1VarA;
        bt1 bt1VarRequest = aVar.request();
        cu1 cu1Var = (cu1) aVar;
        gs1 gs1VarA = cu1Var.a();
        rs1 rs1VarB = cu1Var.b();
        vt1 vt1Var = new vt1(this.f7648a.e(), a(bt1VarRequest.g()), gs1VarA, rs1VarB, this.c);
        this.b = vt1Var;
        dt1 dt1Var = null;
        int i = 0;
        while (!this.d) {
            try {
                try {
                    dt1VarA = cu1Var.a(bt1VarRequest, vt1Var, null, null);
                    if (dt1Var != null) {
                        dt1.a aVarH = dt1VarA.h();
                        dt1.a aVarH2 = dt1Var.h();
                        aVarH2.a((et1) null);
                        aVarH.d(aVarH2.a());
                        dt1VarA = aVarH.a();
                    }
                } catch (IOException e2) {
                    if (!a(e2, vt1Var, !(e2 instanceof ConnectionShutdownException), bt1VarRequest)) {
                        throw e2;
                    }
                } catch (RouteException e3) {
                    if (!a(e3.getLastConnectException(), vt1Var, false, bt1VarRequest)) {
                        throw e3.getFirstConnectException();
                    }
                }
                try {
                    bt1 bt1VarA = a(dt1VarA, vt1Var.h());
                    if (bt1VarA == null) {
                        vt1Var.f();
                        return dt1VarA;
                    }
                    kt1.a(dt1VarA.a());
                    int i2 = i + 1;
                    if (i2 > 20) {
                        vt1Var.f();
                        throw new ProtocolException("Too many follow-up requests: " + i2);
                    }
                    bt1VarA.a();
                    if (!a(dt1VarA, bt1VarA.g())) {
                        vt1Var.f();
                        vt1Var = new vt1(this.f7648a.e(), a(bt1VarA.g()), gs1VarA, rs1VarB, this.c);
                        this.b = vt1Var;
                    } else if (vt1Var.b() != null) {
                        throw new IllegalStateException("Closing the body of " + dt1VarA + " didn't close its backing stream. Bad interceptor?");
                    }
                    dt1Var = dt1VarA;
                    bt1VarRequest = bt1VarA;
                    i = i2;
                } catch (IOException e4) {
                    vt1Var.f();
                    throw e4;
                }
            } catch (Throwable th) {
                vt1Var.a((IOException) null);
                vt1Var.f();
                throw th;
            }
        }
        vt1Var.f();
        throw new IOException("Canceled");
    }

    public void a(Object obj) {
        this.c = obj;
    }

    public final cs1 a(vs1 vs1Var) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierM;
        is1 is1VarC;
        if (vs1Var.h()) {
            SSLSocketFactory sSLSocketFactoryZ = this.f7648a.z();
            hostnameVerifierM = this.f7648a.m();
            sSLSocketFactory = sSLSocketFactoryZ;
            is1VarC = this.f7648a.c();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierM = null;
            is1VarC = null;
        }
        return new cs1(vs1Var.g(), vs1Var.k(), this.f7648a.i(), this.f7648a.y(), sSLSocketFactory, hostnameVerifierM, is1VarC, this.f7648a.u(), this.f7648a.t(), this.f7648a.s(), this.f7648a.f(), this.f7648a.v());
    }

    public final boolean a(IOException iOException, vt1 vt1Var, boolean z, bt1 bt1Var) {
        vt1Var.a(iOException);
        if (!this.f7648a.x()) {
            return false;
        }
        if (z) {
            bt1Var.a();
        }
        return a(iOException, z) && vt1Var.d();
    }

    public final boolean a(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    public final bt1 a(dt1 dt1Var, ft1 ft1Var) throws IOException {
        String strA;
        vs1 vs1VarB;
        Proxy proxyT;
        if (dt1Var != null) {
            int iC = dt1Var.c();
            String strE = dt1Var.k().e();
            if (iC == 307 || iC == 308) {
                if (!strE.equals("GET") && !strE.equals("HEAD")) {
                    return null;
                }
            } else {
                if (iC == 401) {
                    return this.f7648a.a().a(ft1Var, dt1Var);
                }
                if (iC == 503) {
                    if ((dt1Var.i() == null || dt1Var.i().c() != 503) && a(dt1Var, Integer.MAX_VALUE) == 0) {
                        return dt1Var.k();
                    }
                    return null;
                }
                if (iC == 407) {
                    if (ft1Var != null) {
                        proxyT = ft1Var.b();
                    } else {
                        proxyT = this.f7648a.t();
                    }
                    if (proxyT.type() == Proxy.Type.HTTP) {
                        return this.f7648a.u().a(ft1Var, dt1Var);
                    }
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                if (iC == 408) {
                    if (!this.f7648a.x()) {
                        return null;
                    }
                    dt1Var.k().a();
                    if ((dt1Var.i() == null || dt1Var.i().c() != 408) && a(dt1Var, 0) <= 0) {
                        return dt1Var.k();
                    }
                    return null;
                }
                switch (iC) {
                    case 300:
                    case 301:
                    case com.umeng.ccg.c.o /* 302 */:
                    case com.umeng.ccg.c.p /* 303 */:
                        break;
                    default:
                        return null;
                }
            }
            if (!this.f7648a.k() || (strA = dt1Var.a(HttpHeaders.HEAD_KEY_LOCATION)) == null || (vs1VarB = dt1Var.k().g().b(strA)) == null) {
                return null;
            }
            if (!vs1VarB.n().equals(dt1Var.k().g().n()) && !this.f7648a.l()) {
                return null;
            }
            bt1.a aVarF = dt1Var.k().f();
            if (bu1.b(strE)) {
                boolean zD = bu1.d(strE);
                if (bu1.c(strE)) {
                    aVarF.a("GET", (ct1) null);
                } else {
                    aVarF.a(strE, zD ? dt1Var.k().a() : null);
                }
                if (!zD) {
                    aVarF.a("Transfer-Encoding");
                    aVarF.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
                    aVarF.a("Content-Type");
                }
            }
            if (!a(dt1Var, vs1VarB)) {
                aVarF.a("Authorization");
            }
            aVarF.a(vs1VarB);
            return aVarF.a();
        }
        throw new IllegalStateException();
    }

    public final int a(dt1 dt1Var, int i) {
        String strA = dt1Var.a("Retry-After");
        if (strA == null) {
            return i;
        }
        if (strA.matches("\\d+")) {
            return Integer.valueOf(strA).intValue();
        }
        return Integer.MAX_VALUE;
    }

    public final boolean a(dt1 dt1Var, vs1 vs1Var) {
        vs1 vs1VarG = dt1Var.k().g();
        return vs1VarG.g().equals(vs1Var.g()) && vs1VarG.k() == vs1Var.k() && vs1VarG.n().equals(vs1Var.n());
    }
}
