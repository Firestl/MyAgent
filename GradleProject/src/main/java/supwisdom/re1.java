package supwisdom;

import com.huawei.secure.android.common.ssl.SSLUtil;
import com.squareup.okhttp.Protocol;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import supwisdom.oe1;

/* JADX INFO: compiled from: OkHttpClient.java */
/* JADX INFO: loaded from: classes2.dex */
public class re1 implements Cloneable {
    public static SSLSocketFactory A;
    public static final List<Protocol> y = gf1.a(Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1);
    public static final List<ke1> z = gf1.a(ke1.f8167e, ke1.f, ke1.g);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ff1 f9054a;
    public me1 b;
    public Proxy c;
    public List<Protocol> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<ke1> f9055e;
    public final List<pe1> f;
    public final List<pe1> g;
    public ProxySelector h;
    public CookieHandler i;
    public af1 j;
    public ce1 k;
    public SocketFactory l;
    public SSLSocketFactory m;
    public HostnameVerifier n;
    public ge1 o;
    public be1 p;
    public je1 q;
    public cf1 r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;
    public int w;
    public int x;

    /* JADX INFO: compiled from: OkHttpClient.java */
    public static class a extends ze1 {
        @Override // supwisdom.ze1
        public yf1 a(ie1 ie1Var, of1 of1Var) throws IOException {
            return ie1Var.a(of1Var);
        }

        @Override // supwisdom.ze1
        public void b(ie1 ie1Var, of1 of1Var) {
            ie1Var.a((Object) of1Var);
        }

        @Override // supwisdom.ze1
        public int c(ie1 ie1Var) {
            return ie1Var.m();
        }

        @Override // supwisdom.ze1
        public boolean a(ie1 ie1Var) {
            return ie1Var.a();
        }

        @Override // supwisdom.ze1
        public boolean b(ie1 ie1Var) {
            return ie1Var.k();
        }

        @Override // supwisdom.ze1
        public ff1 c(re1 re1Var) {
            return re1Var.x();
        }

        @Override // supwisdom.ze1
        public void a(ie1 ie1Var, Protocol protocol) {
            ie1Var.a(protocol);
        }

        @Override // supwisdom.ze1
        public cf1 b(re1 re1Var) {
            return re1Var.r;
        }

        @Override // supwisdom.ze1
        public void a(oe1.b bVar, String str) {
            bVar.a(str);
        }

        @Override // supwisdom.ze1
        public af1 a(re1 re1Var) {
            return re1Var.v();
        }

        @Override // supwisdom.ze1
        public void a(je1 je1Var, ie1 ie1Var) {
            je1Var.b(ie1Var);
        }

        @Override // supwisdom.ze1
        public void a(re1 re1Var, ie1 ie1Var, of1 of1Var, se1 se1Var) throws IOException {
            ie1Var.a(re1Var, of1Var, se1Var);
        }
    }

    static {
        ze1.b = new a();
    }

    public re1() {
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.s = true;
        this.t = true;
        this.u = true;
        this.f9054a = new ff1();
        this.b = new me1();
    }

    public final void b(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("Timeout too large.");
        }
        if (millis == 0 && j > 0) {
            throw new IllegalArgumentException("Timeout too small.");
        }
        this.w = (int) millis;
    }

    public final void c(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("Timeout too large.");
        }
        if (millis == 0 && j > 0) {
            throw new IllegalArgumentException("Timeout too small.");
        }
        this.x = (int) millis;
    }

    public final int d() {
        return this.v;
    }

    public final je1 e() {
        return this.q;
    }

    public final List<ke1> f() {
        return this.f9055e;
    }

    public final CookieHandler g() {
        return this.i;
    }

    public final synchronized SSLSocketFactory h() {
        if (A == null) {
            try {
                SSLContext sSLContext = SSLContext.getInstance(SSLUtil.d);
                sSLContext.init(null, null, null);
                A = sSLContext.getSocketFactory();
            } catch (GeneralSecurityException unused) {
                throw new AssertionError();
            }
        }
        return A;
    }

    public final me1 i() {
        return this.b;
    }

    public final boolean j() {
        return this.t;
    }

    public final boolean k() {
        return this.s;
    }

    public final HostnameVerifier l() {
        return this.n;
    }

    public final List<Protocol> m() {
        return this.d;
    }

    public final Proxy n() {
        return this.c;
    }

    public final ProxySelector o() {
        return this.h;
    }

    public final int p() {
        return this.w;
    }

    public final boolean q() {
        return this.u;
    }

    public final SocketFactory r() {
        return this.l;
    }

    public final SSLSocketFactory s() {
        return this.m;
    }

    public final int t() {
        return this.x;
    }

    public List<pe1> u() {
        return this.f;
    }

    public final af1 v() {
        return this.j;
    }

    public List<pe1> w() {
        return this.g;
    }

    public final ff1 x() {
        return this.f9054a;
    }

    public final void a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("Timeout too large.");
        }
        if (millis == 0 && j > 0) {
            throw new IllegalArgumentException("Timeout too small.");
        }
        this.v = (int) millis;
    }

    public final re1 clone() {
        try {
            return (re1) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public final be1 b() {
        return this.p;
    }

    public final ge1 c() {
        return this.o;
    }

    public final re1 a(ce1 ce1Var) {
        this.k = ce1Var;
        this.j = null;
        return this;
    }

    public re1(re1 re1Var) {
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.s = true;
        this.t = true;
        this.u = true;
        this.f9054a = re1Var.f9054a;
        this.b = re1Var.b;
        this.c = re1Var.c;
        this.d = re1Var.d;
        this.f9055e = re1Var.f9055e;
        this.f.addAll(re1Var.f);
        this.g.addAll(re1Var.g);
        this.h = re1Var.h;
        this.i = re1Var.i;
        ce1 ce1Var = re1Var.k;
        this.k = ce1Var;
        this.j = ce1Var != null ? ce1Var.f7180a : re1Var.j;
        this.l = re1Var.l;
        this.m = re1Var.m;
        this.n = re1Var.n;
        this.o = re1Var.o;
        this.p = re1Var.p;
        this.q = re1Var.q;
        this.r = re1Var.r;
        this.s = re1Var.s;
        this.t = re1Var.t;
        this.u = re1Var.u;
        this.v = re1Var.v;
        this.w = re1Var.w;
        this.x = re1Var.x;
    }

    public ee1 a(se1 se1Var) {
        return new ee1(this, se1Var);
    }

    public final re1 a() {
        re1 re1Var = new re1(this);
        if (re1Var.h == null) {
            re1Var.h = ProxySelector.getDefault();
        }
        if (re1Var.i == null) {
            re1Var.i = CookieHandler.getDefault();
        }
        if (re1Var.l == null) {
            re1Var.l = SocketFactory.getDefault();
        }
        if (re1Var.m == null) {
            re1Var.m = h();
        }
        if (re1Var.n == null) {
            re1Var.n = qg1.f8918a;
        }
        if (re1Var.o == null) {
            re1Var.o = ge1.b;
        }
        if (re1Var.p == null) {
            re1Var.p = if1.f7953a;
        }
        if (re1Var.q == null) {
            re1Var.q = je1.c();
        }
        if (re1Var.d == null) {
            re1Var.d = y;
        }
        if (re1Var.f9055e == null) {
            re1Var.f9055e = z;
        }
        if (re1Var.r == null) {
            re1Var.r = cf1.f7203a;
        }
        return re1Var;
    }
}
