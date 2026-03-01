package supwisdom;

import com.squareup.okhttp.Protocol;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okio.Source;
import supwisdom.mg1;
import supwisdom.se1;
import supwisdom.ue1;

/* JADX INFO: compiled from: Connection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ie1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final je1 f7950a;
    public final we1 b;
    public Socket c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public mf1 f7951e;
    public mg1 f;
    public long h;
    public ne1 i;
    public int j;
    public Object k;
    public boolean d = false;
    public Protocol g = Protocol.HTTP_1_1;

    public ie1(je1 je1Var, we1 we1Var) {
        this.f7950a = je1Var;
        this.b = we1Var;
    }

    public void a(Object obj) {
        if (l()) {
            return;
        }
        synchronized (this.f7950a) {
            if (this.k != null) {
                throw new IllegalStateException("Connection already has an owner!");
            }
            this.k = obj;
        }
    }

    public final void b(se1 se1Var, int i, int i2) throws IOException {
        String strB;
        ef1 ef1VarC = ef1.c();
        if (se1Var != null) {
            a(se1Var, i, i2);
        }
        ae1 ae1Var = this.b.f9616a;
        Socket socketCreateSocket = ae1Var.f6891e.createSocket(this.c, ae1Var.b, ae1Var.c, true);
        this.c = socketCreateSocket;
        SSLSocket sSLSocket = (SSLSocket) socketCreateSocket;
        we1 we1Var = this.b;
        we1Var.d.a(sSLSocket, we1Var);
        try {
            sSLSocket.startHandshake();
            if (this.b.d.c() && (strB = ef1VarC.b(sSLSocket)) != null) {
                this.g = Protocol.get(strB);
            }
            ef1VarC.a(sSLSocket);
            this.i = ne1.a(sSLSocket.getSession());
            ae1 ae1Var2 = this.b.f9616a;
            if (!ae1Var2.f.verify(ae1Var2.b, sSLSocket.getSession())) {
                X509Certificate x509Certificate = (X509Certificate) sSLSocket.getSession().getPeerCertificates()[0];
                throw new SSLPeerUnverifiedException("Hostname " + this.b.f9616a.b + " not verified:\n    certificate: " + ge1.a((Certificate) x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + qg1.a(x509Certificate));
            }
            ae1 ae1Var3 = this.b.f9616a;
            ae1Var3.g.a(ae1Var3.b, this.i.c());
            Protocol protocol = this.g;
            if (protocol != Protocol.SPDY_3 && protocol != Protocol.HTTP_2) {
                this.f7951e = new mf1(this.f7950a, this, this.c);
                return;
            }
            sSLSocket.setSoTimeout(0);
            mg1.h hVar = new mg1.h(this.b.f9616a.d(), true, this.c);
            hVar.a(this.g);
            mg1 mg1VarA = hVar.a();
            this.f = mg1VarA;
            mg1VarA.d();
        } catch (Throwable th) {
            ef1VarC.a(sSLSocket);
            throw th;
        }
    }

    public long c() {
        mg1 mg1Var = this.f;
        return mg1Var == null ? this.h : mg1Var.a();
    }

    public Protocol d() {
        return this.g;
    }

    public we1 e() {
        return this.b;
    }

    public Socket f() {
        return this.c;
    }

    public void g() {
        this.j++;
    }

    public boolean h() {
        return (this.c.isClosed() || this.c.isInputShutdown() || this.c.isOutputShutdown()) ? false : true;
    }

    public boolean i() {
        return this.d;
    }

    public boolean j() {
        mg1 mg1Var = this.f;
        return mg1Var == null || mg1Var.c();
    }

    public boolean k() {
        mf1 mf1Var = this.f7951e;
        if (mf1Var != null) {
            return mf1Var.e();
        }
        return true;
    }

    public boolean l() {
        return this.f != null;
    }

    public int m() {
        return this.j;
    }

    public void n() {
        if (this.f != null) {
            throw new IllegalStateException("spdyConnection != null");
        }
        this.h = System.nanoTime();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.b.f9616a.b);
        sb.append(Constants.COLON_SEPARATOR);
        sb.append(this.b.f9616a.c);
        sb.append(", proxy=");
        sb.append(this.b.b);
        sb.append(" hostAddress=");
        sb.append(this.b.c.getAddress().getHostAddress());
        sb.append(" cipherSuite=");
        ne1 ne1Var = this.i;
        sb.append(ne1Var != null ? ne1Var.a() : "none");
        sb.append(" protocol=");
        sb.append(this.g);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public boolean a() {
        synchronized (this.f7950a) {
            if (this.k == null) {
                return false;
            }
            this.k = null;
            return true;
        }
    }

    public void a(int i, int i2, int i3, se1 se1Var) throws IOException {
        if (!this.d) {
            if (this.b.b.type() != Proxy.Type.DIRECT && this.b.b.type() != Proxy.Type.HTTP) {
                this.c = new Socket(this.b.b);
            } else {
                this.c = this.b.f9616a.d.createSocket();
            }
            this.c.setSoTimeout(i2);
            ef1.c().a(this.c, this.b.c, i);
            if (this.b.f9616a.f6891e != null) {
                b(se1Var, i2, i3);
            } else {
                this.f7951e = new mf1(this.f7950a, this, this.c);
            }
            this.d = true;
            return;
        }
        throw new IllegalStateException("already connected");
    }

    public void a(re1 re1Var, Object obj, se1 se1Var) throws IOException {
        a(obj);
        if (!i()) {
            a(re1Var.d(), re1Var.p(), re1Var.t(), a(se1Var));
            if (l()) {
                re1Var.e().c(this);
            }
            re1Var.x().a(e());
        }
        a(re1Var.p(), re1Var.t());
    }

    public ne1 b() {
        return this.i;
    }

    public final se1 a(se1 se1Var) throws IOException {
        String str;
        if (!this.b.c()) {
            return null;
        }
        String host = se1Var.h().getHost();
        int iA = gf1.a(se1Var.h());
        if (iA == gf1.a("https")) {
            str = host;
        } else {
            str = host + Constants.COLON_SEPARATOR + iA;
        }
        se1.b bVar = new se1.b();
        bVar.a(new URL("https", host, iA, "/"));
        bVar.b("Host", str);
        bVar.b("Proxy-Connection", "Keep-Alive");
        String strA = se1Var.a("User-Agent");
        if (strA != null) {
            bVar.b("User-Agent", strA);
        }
        String strA2 = se1Var.a("Proxy-Authorization");
        if (strA2 != null) {
            bVar.b("Proxy-Authorization", strA2);
        }
        return bVar.a();
    }

    public yf1 a(of1 of1Var) throws IOException {
        return this.f != null ? new wf1(of1Var, this.f) : new qf1(of1Var, this.f7951e);
    }

    public void a(Protocol protocol) {
        if (protocol != null) {
            this.g = protocol;
            return;
        }
        throw new IllegalArgumentException("protocol == null");
    }

    public void a(int i, int i2) throws IOException {
        if (this.d) {
            if (this.f7951e != null) {
                this.c.setSoTimeout(i);
                this.f7951e.a(i, i2);
                return;
            }
            return;
        }
        throw new IllegalStateException("setTimeouts - not connected");
    }

    public final void a(se1 se1Var, int i, int i2) throws IOException {
        mf1 mf1Var = new mf1(this.f7950a, this, this.c);
        mf1Var.a(i, i2);
        URL urlH = se1Var.h();
        String str = "CONNECT " + urlH.getHost() + Constants.COLON_SEPARATOR + urlH.getPort() + " HTTP/1.1";
        do {
            mf1Var.a(se1Var.c(), str);
            mf1Var.c();
            ue1.b bVarI = mf1Var.i();
            bVarI.a(se1Var);
            ue1 ue1VarA = bVarI.a();
            long jA = rf1.a(ue1VarA);
            if (jA == -1) {
                jA = 0;
            }
            Source sourceB = mf1Var.b(jA);
            gf1.b(sourceB, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            sourceB.close();
            int iE = ue1VarA.e();
            if (iE == 200) {
                if (mf1Var.a() > 0) {
                    throw new IOException("TLS tunnel buffered too many bytes!");
                }
                return;
            } else if (iE == 407) {
                we1 we1Var = this.b;
                se1Var = rf1.a(we1Var.f9616a.h, ue1VarA, we1Var.b);
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + ue1VarA.e());
            }
        } while (se1Var != null);
        throw new IOException("Failed to authenticate with proxy");
    }
}
