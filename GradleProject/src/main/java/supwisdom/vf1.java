package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;

/* JADX INFO: compiled from: RouteSelector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vf1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ae1 f9506a;
    public final URI b;
    public final cf1 c;
    public final re1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ff1 f9507e;
    public final se1 f;
    public Proxy g;
    public InetSocketAddress h;
    public ke1 i;
    public int k;
    public int m;
    public int o;
    public List<Proxy> j = Collections.emptyList();
    public List<InetSocketAddress> l = Collections.emptyList();
    public List<ke1> n = Collections.emptyList();
    public final List<we1> p = new ArrayList();

    public vf1(ae1 ae1Var, URI uri, re1 re1Var, se1 se1Var) {
        this.f9506a = ae1Var;
        this.b = uri;
        this.d = re1Var;
        this.f9507e = ze1.b.c(re1Var);
        this.c = ze1.b.b(re1Var);
        this.f = se1Var;
        a(uri, ae1Var.b());
    }

    public static vf1 a(ae1 ae1Var, se1 se1Var, re1 re1Var) throws IOException {
        return new vf1(ae1Var, se1Var.g(), re1Var, se1Var);
    }

    public final boolean b() {
        return this.o < this.n.size();
    }

    public final boolean c() {
        return this.m < this.l.size();
    }

    public final boolean d() {
        return !this.p.isEmpty();
    }

    public final boolean e() {
        return this.k < this.j.size();
    }

    public we1 f() throws IOException {
        if (!b()) {
            if (!c()) {
                if (!e()) {
                    if (d()) {
                        return i();
                    }
                    throw new NoSuchElementException();
                }
                this.g = j();
            }
            this.h = h();
        }
        ke1 ke1VarG = g();
        this.i = ke1VarG;
        we1 we1Var = new we1(this.f9506a, this.g, this.h, this.i, a(ke1VarG));
        if (!this.f9507e.c(we1Var)) {
            return we1Var;
        }
        this.p.add(we1Var);
        return f();
    }

    public final ke1 g() throws IOException {
        String str = "//";
        if (this.n.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("No route to ");
            if (this.b.getScheme() != null) {
                str = this.b.getScheme() + "://";
            }
            sb.append(str);
            sb.append(this.f9506a.d());
            sb.append("; no connection specs");
            throw new UnknownServiceException(sb.toString());
        }
        if (b()) {
            List<ke1> list = this.n;
            int i = this.o;
            this.o = i + 1;
            return list.get(i);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("No route to ");
        if (this.b.getScheme() != null) {
            str = this.b.getScheme() + "://";
        }
        sb2.append(str);
        sb2.append(this.f9506a.d());
        sb2.append("; exhausted connection specs: ");
        sb2.append(this.n);
        throw new SocketException(sb2.toString());
    }

    public final InetSocketAddress h() throws IOException {
        if (c()) {
            List<InetSocketAddress> list = this.l;
            int i = this.m;
            this.m = i + 1;
            InetSocketAddress inetSocketAddress = list.get(i);
            k();
            return inetSocketAddress;
        }
        throw new SocketException("No route to " + this.f9506a.d() + "; exhausted inet socket addresses: " + this.l);
    }

    public final we1 i() {
        return this.p.remove(0);
    }

    public final Proxy j() throws IOException {
        if (e()) {
            List<Proxy> list = this.j;
            int i = this.k;
            this.k = i + 1;
            Proxy proxy = list.get(i);
            a(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f9506a.d() + "; exhausted proxy configurations: " + this.j);
    }

    public final void k() {
        this.n = new ArrayList();
        List<ke1> listA = this.f9506a.a();
        int size = listA.size();
        for (int i = 0; i < size; i++) {
            ke1 ke1Var = listA.get(i);
            if (this.f.d() == ke1Var.b()) {
                this.n.add(ke1Var);
            }
        }
        this.o = 0;
    }

    public boolean a() {
        return b() || c() || e() || d();
    }

    public final boolean a(ke1 ke1Var) {
        return ke1Var != this.n.get(0) && ke1Var.b();
    }

    public void a(we1 we1Var, IOException iOException) {
        if (we1Var.b().type() != Proxy.Type.DIRECT && this.f9506a.c() != null) {
            this.f9506a.c().connectFailed(this.b, we1Var.b().address(), iOException);
        }
        this.f9507e.b(we1Var);
        if ((iOException instanceof SSLHandshakeException) || (iOException instanceof SSLProtocolException)) {
            return;
        }
        while (this.o < this.n.size()) {
            List<ke1> list = this.n;
            int i = this.o;
            this.o = i + 1;
            ke1 ke1Var = list.get(i);
            this.f9507e.b(new we1(this.f9506a, this.g, this.h, ke1Var, a(ke1Var)));
        }
    }

    public final void a(URI uri, Proxy proxy) {
        if (proxy != null) {
            this.j = Collections.singletonList(proxy);
        } else {
            this.j = new ArrayList();
            List<Proxy> listSelect = this.d.o().select(uri);
            if (listSelect != null) {
                this.j.addAll(listSelect);
            }
            this.j.removeAll(Collections.singleton(Proxy.NO_PROXY));
            this.j.add(Proxy.NO_PROXY);
        }
        this.k = 0;
    }

    public final void a(Proxy proxy) throws IOException {
        String strD;
        int iA;
        this.l = new ArrayList();
        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) {
            SocketAddress socketAddressAddress = proxy.address();
            if (socketAddressAddress instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                strD = a(inetSocketAddress);
                iA = inetSocketAddress.getPort();
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
        } else {
            strD = this.f9506a.d();
            iA = gf1.a(this.b);
        }
        if (iA >= 1 && iA <= 65535) {
            for (InetAddress inetAddress : this.c.a(strD)) {
                this.l.add(new InetSocketAddress(inetAddress, iA));
            }
            this.m = 0;
            return;
        }
        throw new SocketException("No route to " + strD + Constants.COLON_SEPARATOR + iA + "; port is out of range");
    }

    public static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }
}
