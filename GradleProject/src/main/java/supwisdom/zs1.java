package supwisdom;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import supwisdom.dt1;
import supwisdom.gs1;
import supwisdom.rs1;
import supwisdom.us1;

/* JADX INFO: compiled from: OkHttpClient.java */
/* JADX INFO: loaded from: classes3.dex */
public class zs1 implements Cloneable, gs1.a {
    public static final List<Protocol> C = kt1.a(Protocol.HTTP_2, Protocol.HTTP_1_1);
    public static final List<ms1> D = kt1.a(ms1.g, ms1.h);
    public final int A;
    public final int B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ps1 f10023a;

    @Nullable
    public final Proxy b;
    public final List<Protocol> c;
    public final List<ms1> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<ws1> f10024e;
    public final List<ws1> f;
    public final rs1.c g;
    public final ProxySelector h;
    public final os1 i;

    @Nullable
    public final es1 j;

    @Nullable
    public final pt1 k;
    public final SocketFactory l;
    public final SSLSocketFactory m;
    public final cv1 n;
    public final HostnameVerifier o;
    public final is1 p;
    public final ds1 q;
    public final ds1 r;
    public final ls1 s;
    public final qs1 t;
    public final boolean u;
    public final boolean v;
    public final boolean w;
    public final int x;
    public final int y;
    public final int z;

    /* JADX INFO: compiled from: OkHttpClient.java */
    public class a extends it1 {
        @Override // supwisdom.it1
        public void a(us1.a aVar, String str) {
            aVar.a(str);
        }

        @Override // supwisdom.it1
        public void b(ls1 ls1Var, st1 st1Var) {
            ls1Var.b(st1Var);
        }

        @Override // supwisdom.it1
        public void a(us1.a aVar, String str, String str2) {
            aVar.b(str, str2);
        }

        @Override // supwisdom.it1
        public boolean a(ls1 ls1Var, st1 st1Var) {
            return ls1Var.a(st1Var);
        }

        @Override // supwisdom.it1
        public st1 a(ls1 ls1Var, cs1 cs1Var, vt1 vt1Var, ft1 ft1Var) {
            return ls1Var.a(cs1Var, vt1Var, ft1Var);
        }

        @Override // supwisdom.it1
        public boolean a(cs1 cs1Var, cs1 cs1Var2) {
            return cs1Var.a(cs1Var2);
        }

        @Override // supwisdom.it1
        public Socket a(ls1 ls1Var, cs1 cs1Var, vt1 vt1Var) {
            return ls1Var.a(cs1Var, vt1Var);
        }

        @Override // supwisdom.it1
        public tt1 a(ls1 ls1Var) {
            return ls1Var.f8313e;
        }

        @Override // supwisdom.it1
        public int a(dt1.a aVar) {
            return aVar.c;
        }

        @Override // supwisdom.it1
        public void a(ms1 ms1Var, SSLSocket sSLSocket, boolean z) {
            ms1Var.a(sSLSocket, z);
        }

        @Override // supwisdom.it1
        public vt1 a(gs1 gs1Var) {
            return ((at1) gs1Var).d();
        }

        @Override // supwisdom.it1
        @Nullable
        public IOException a(gs1 gs1Var, @Nullable IOException iOException) {
            return ((at1) gs1Var).a(iOException);
        }

        @Override // supwisdom.it1
        public gs1 a(zs1 zs1Var, bt1 bt1Var) {
            return at1.a(zs1Var, bt1Var, true);
        }
    }

    /* JADX INFO: compiled from: OkHttpClient.java */
    public static final class b {
        public int A;
        public int B;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ps1 f10025a;

        @Nullable
        public Proxy b;
        public List<Protocol> c;
        public List<ms1> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final List<ws1> f10026e;
        public final List<ws1> f;
        public rs1.c g;
        public ProxySelector h;
        public os1 i;

        @Nullable
        public es1 j;

        @Nullable
        public pt1 k;
        public SocketFactory l;

        @Nullable
        public SSLSocketFactory m;

        @Nullable
        public cv1 n;
        public HostnameVerifier o;
        public is1 p;
        public ds1 q;
        public ds1 r;
        public ls1 s;
        public qs1 t;
        public boolean u;
        public boolean v;
        public boolean w;
        public int x;
        public int y;
        public int z;

        public b() {
            this.f10026e = new ArrayList();
            this.f = new ArrayList();
            this.f10025a = new ps1();
            this.c = zs1.C;
            this.d = zs1.D;
            this.g = rs1.factory(rs1.NONE);
            ProxySelector proxySelector = ProxySelector.getDefault();
            this.h = proxySelector;
            if (proxySelector == null) {
                this.h = new zu1();
            }
            this.i = os1.f8708a;
            this.l = SocketFactory.getDefault();
            this.o = dv1.f7386a;
            this.p = is1.c;
            ds1 ds1Var = ds1.f7376a;
            this.q = ds1Var;
            this.r = ds1Var;
            this.s = new ls1();
            this.t = qs1.f8950a;
            this.u = true;
            this.v = true;
            this.w = true;
            this.x = 0;
            this.y = 10000;
            this.z = 10000;
            this.A = 10000;
            this.B = 0;
        }

        public b a(long j, TimeUnit timeUnit) {
            this.y = kt1.a("timeout", j, timeUnit);
            return this;
        }

        public b b(long j, TimeUnit timeUnit) {
            this.B = kt1.a("interval", j, timeUnit);
            return this;
        }

        public b c(long j, TimeUnit timeUnit) {
            this.z = kt1.a("timeout", j, timeUnit);
            return this;
        }

        public b d(long j, TimeUnit timeUnit) {
            this.A = kt1.a("timeout", j, timeUnit);
            return this;
        }

        public b a(os1 os1Var) {
            if (os1Var == null) {
                throw new NullPointerException("cookieJar == null");
            }
            this.i = os1Var;
            return this;
        }

        public b b(ws1 ws1Var) {
            if (ws1Var == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f.add(ws1Var);
            return this;
        }

        public b a(qs1 qs1Var) {
            if (qs1Var != null) {
                this.t = qs1Var;
                return this;
            }
            throw new NullPointerException("dns == null");
        }

        public b a(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory != null) {
                this.m = sSLSocketFactory;
                this.n = yu1.c().a(sSLSocketFactory);
                return this;
            }
            throw new NullPointerException("sslSocketFactory == null");
        }

        public b a(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            if (x509TrustManager != null) {
                this.m = sSLSocketFactory;
                this.n = cv1.a(x509TrustManager);
                return this;
            }
            throw new NullPointerException("trustManager == null");
        }

        public b a(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier != null) {
                this.o = hostnameVerifier;
                return this;
            }
            throw new NullPointerException("hostnameVerifier == null");
        }

        public b a(boolean z) {
            this.w = z;
            return this;
        }

        public b a(List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list);
            if (!arrayList.contains(Protocol.H2_PRIOR_KNOWLEDGE) && !arrayList.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols must contain h2_prior_knowledge or http/1.1: " + arrayList);
            }
            if (arrayList.contains(Protocol.H2_PRIOR_KNOWLEDGE) && arrayList.size() > 1) {
                throw new IllegalArgumentException("protocols containing h2_prior_knowledge cannot use other protocols: " + arrayList);
            }
            if (!arrayList.contains(Protocol.HTTP_1_0)) {
                if (!arrayList.contains(null)) {
                    arrayList.remove(Protocol.SPDY_3);
                    this.c = Collections.unmodifiableList(arrayList);
                    return this;
                }
                throw new IllegalArgumentException("protocols must not contain null");
            }
            throw new IllegalArgumentException("protocols must not contain http/1.0: " + arrayList);
        }

        public b(zs1 zs1Var) {
            this.f10026e = new ArrayList();
            this.f = new ArrayList();
            this.f10025a = zs1Var.f10023a;
            this.b = zs1Var.b;
            this.c = zs1Var.c;
            this.d = zs1Var.d;
            this.f10026e.addAll(zs1Var.f10024e);
            this.f.addAll(zs1Var.f);
            this.g = zs1Var.g;
            this.h = zs1Var.h;
            this.i = zs1Var.i;
            this.k = zs1Var.k;
            this.j = zs1Var.j;
            this.l = zs1Var.l;
            this.m = zs1Var.m;
            this.n = zs1Var.n;
            this.o = zs1Var.o;
            this.p = zs1Var.p;
            this.q = zs1Var.q;
            this.r = zs1Var.r;
            this.s = zs1Var.s;
            this.t = zs1Var.t;
            this.u = zs1Var.u;
            this.v = zs1Var.v;
            this.w = zs1Var.w;
            this.x = zs1Var.x;
            this.y = zs1Var.y;
            this.z = zs1Var.z;
            this.A = zs1Var.A;
            this.B = zs1Var.B;
        }

        public b a(ws1 ws1Var) {
            if (ws1Var != null) {
                this.f10026e.add(ws1Var);
                return this;
            }
            throw new IllegalArgumentException("interceptor == null");
        }

        public b a(rs1 rs1Var) {
            if (rs1Var != null) {
                this.g = rs1.factory(rs1Var);
                return this;
            }
            throw new NullPointerException("eventListener == null");
        }

        public b a(rs1.c cVar) {
            if (cVar != null) {
                this.g = cVar;
                return this;
            }
            throw new NullPointerException("eventListenerFactory == null");
        }

        public zs1 a() {
            return new zs1(this);
        }
    }

    static {
        it1.f7984a = new a();
    }

    public zs1() {
        this(new b());
    }

    public static SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContextA = yu1.c().a();
            sSLContextA.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContextA.getSocketFactory();
        } catch (GeneralSecurityException e2) {
            throw kt1.a("No System TLS", (Exception) e2);
        }
    }

    public int A() {
        return this.A;
    }

    public int b() {
        return this.x;
    }

    public is1 c() {
        return this.p;
    }

    public int d() {
        return this.y;
    }

    public ls1 e() {
        return this.s;
    }

    public List<ms1> f() {
        return this.d;
    }

    public os1 g() {
        return this.i;
    }

    public ps1 h() {
        return this.f10023a;
    }

    public qs1 i() {
        return this.t;
    }

    public rs1.c j() {
        return this.g;
    }

    public boolean k() {
        return this.v;
    }

    public boolean l() {
        return this.u;
    }

    public HostnameVerifier m() {
        return this.o;
    }

    public List<ws1> n() {
        return this.f10024e;
    }

    public pt1 o() {
        es1 es1Var = this.j;
        return es1Var != null ? es1Var.f7521a : this.k;
    }

    public List<ws1> p() {
        return this.f;
    }

    public b q() {
        return new b(this);
    }

    public int r() {
        return this.B;
    }

    public List<Protocol> s() {
        return this.c;
    }

    @Nullable
    public Proxy t() {
        return this.b;
    }

    public ds1 u() {
        return this.q;
    }

    public ProxySelector v() {
        return this.h;
    }

    public int w() {
        return this.z;
    }

    public boolean x() {
        return this.w;
    }

    public SocketFactory y() {
        return this.l;
    }

    public SSLSocketFactory z() {
        return this.m;
    }

    public zs1(b bVar) {
        boolean z;
        this.f10023a = bVar.f10025a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.f10024e = kt1.a(bVar.f10026e);
        this.f = kt1.a(bVar.f);
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = bVar.k;
        this.l = bVar.l;
        Iterator<ms1> it = this.d.iterator();
        loop0: while (true) {
            while (it.hasNext()) {
                z = z || it.next().b();
            }
        }
        if (bVar.m == null && z) {
            X509TrustManager x509TrustManagerA = kt1.a();
            this.m = a(x509TrustManagerA);
            this.n = cv1.a(x509TrustManagerA);
        } else {
            this.m = bVar.m;
            this.n = bVar.n;
        }
        if (this.m != null) {
            yu1.c().b(this.m);
        }
        this.o = bVar.o;
        this.p = bVar.p.a(this.n);
        this.q = bVar.q;
        this.r = bVar.r;
        this.s = bVar.s;
        this.t = bVar.t;
        this.u = bVar.u;
        this.v = bVar.v;
        this.w = bVar.w;
        this.x = bVar.x;
        this.y = bVar.y;
        this.z = bVar.z;
        this.A = bVar.A;
        this.B = bVar.B;
        if (this.f10024e.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + this.f10024e);
        }
        if (this.f.contains(null)) {
            throw new IllegalStateException("Null network interceptor: " + this.f);
        }
    }

    public ds1 a() {
        return this.r;
    }

    @Override // supwisdom.gs1.a
    public gs1 a(bt1 bt1Var) {
        return at1.a(this, bt1Var, false);
    }

    public gt1 a(bt1 bt1Var, ht1 ht1Var) {
        fv1 fv1Var = new fv1(bt1Var, ht1Var, new Random(), this.B);
        fv1Var.a(this);
        return fv1Var;
    }
}
