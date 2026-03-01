package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: RouteSelector.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ut1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cs1 f9442a;
    public final tt1 b;
    public final gs1 c;
    public final rs1 d;
    public int f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<Proxy> f9443e = Collections.emptyList();
    public List<InetSocketAddress> g = Collections.emptyList();
    public final List<ft1> h = new ArrayList();

    /* JADX INFO: compiled from: RouteSelector.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<ft1> f9444a;
        public int b = 0;

        public a(List<ft1> list) {
            this.f9444a = list;
        }

        public List<ft1> a() {
            return new ArrayList(this.f9444a);
        }

        public boolean b() {
            return this.b < this.f9444a.size();
        }

        public ft1 c() {
            if (!b()) {
                throw new NoSuchElementException();
            }
            List<ft1> list = this.f9444a;
            int i = this.b;
            this.b = i + 1;
            return list.get(i);
        }
    }

    public ut1(cs1 cs1Var, tt1 tt1Var, gs1 gs1Var, rs1 rs1Var) {
        this.f9442a = cs1Var;
        this.b = tt1Var;
        this.c = gs1Var;
        this.d = rs1Var;
        a(cs1Var.k(), cs1Var.f());
    }

    public boolean a() {
        return b() || !this.h.isEmpty();
    }

    public final boolean b() {
        return this.f < this.f9443e.size();
    }

    public a c() throws IOException {
        if (!a()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (b()) {
            Proxy proxyD = d();
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                ft1 ft1Var = new ft1(this.f9442a, proxyD, this.g.get(i));
                if (this.b.c(ft1Var)) {
                    this.h.add(ft1Var);
                } else {
                    arrayList.add(ft1Var);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.h);
            this.h.clear();
        }
        return new a(arrayList);
    }

    public final Proxy d() throws IOException {
        if (b()) {
            List<Proxy> list = this.f9443e;
            int i = this.f;
            this.f = i + 1;
            Proxy proxy = list.get(i);
            a(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f9442a.k().g() + "; exhausted proxy configurations: " + this.f9443e);
    }

    public void a(ft1 ft1Var, IOException iOException) {
        if (ft1Var.b().type() != Proxy.Type.DIRECT && this.f9442a.h() != null) {
            this.f9442a.h().connectFailed(this.f9442a.k().o(), ft1Var.b().address(), iOException);
        }
        this.b.b(ft1Var);
    }

    public final void a(vs1 vs1Var, Proxy proxy) {
        if (proxy != null) {
            this.f9443e = Collections.singletonList(proxy);
        } else {
            List<Proxy> listSelect = this.f9442a.h().select(vs1Var.o());
            this.f9443e = (listSelect == null || listSelect.isEmpty()) ? kt1.a(Proxy.NO_PROXY) : kt1.a(listSelect);
        }
        this.f = 0;
    }

    public final void a(Proxy proxy) throws IOException {
        String strG;
        int iK;
        this.g = new ArrayList();
        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) {
            SocketAddress socketAddressAddress = proxy.address();
            if (socketAddressAddress instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                strG = a(inetSocketAddress);
                iK = inetSocketAddress.getPort();
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
        } else {
            strG = this.f9442a.k().g();
            iK = this.f9442a.k().k();
        }
        if (iK >= 1 && iK <= 65535) {
            if (proxy.type() == Proxy.Type.SOCKS) {
                this.g.add(InetSocketAddress.createUnresolved(strG, iK));
                return;
            }
            this.d.dnsStart(this.c, strG);
            List<InetAddress> listLookup = this.f9442a.c().lookup(strG);
            if (!listLookup.isEmpty()) {
                this.d.dnsEnd(this.c, strG, listLookup);
                int size = listLookup.size();
                for (int i = 0; i < size; i++) {
                    this.g.add(new InetSocketAddress(listLookup.get(i), iK));
                }
                return;
            }
            throw new UnknownHostException(this.f9442a.c() + " returned no addresses for " + strG);
        }
        throw new SocketException("No route to " + strG + Constants.COLON_SEPARATOR + iK + "; port is out of range");
    }

    public static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }
}
