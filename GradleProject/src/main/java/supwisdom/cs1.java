package supwisdom;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Protocol;
import supwisdom.vs1;

/* JADX INFO: compiled from: Address.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cs1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final vs1 f7251a;
    public final qs1 b;
    public final SocketFactory c;
    public final ds1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<Protocol> f7252e;
    public final List<ms1> f;
    public final ProxySelector g;

    @Nullable
    public final Proxy h;

    @Nullable
    public final SSLSocketFactory i;

    @Nullable
    public final HostnameVerifier j;

    @Nullable
    public final is1 k;

    public cs1(String str, int i, qs1 qs1Var, SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable is1 is1Var, ds1 ds1Var, @Nullable Proxy proxy, List<Protocol> list, List<ms1> list2, ProxySelector proxySelector) {
        vs1.a aVar = new vs1.a();
        aVar.g(sSLSocketFactory != null ? "https" : "http");
        aVar.c(str);
        aVar.a(i);
        this.f7251a = aVar.a();
        if (qs1Var == null) {
            throw new NullPointerException("dns == null");
        }
        this.b = qs1Var;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.c = socketFactory;
        if (ds1Var == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.d = ds1Var;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.f7252e = kt1.a(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f = kt1.a(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.g = proxySelector;
        this.h = proxy;
        this.i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = is1Var;
    }

    @Nullable
    public is1 a() {
        return this.k;
    }

    public List<ms1> b() {
        return this.f;
    }

    public qs1 c() {
        return this.b;
    }

    @Nullable
    public HostnameVerifier d() {
        return this.j;
    }

    public List<Protocol> e() {
        return this.f7252e;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof cs1) {
            cs1 cs1Var = (cs1) obj;
            if (this.f7251a.equals(cs1Var.f7251a) && a(cs1Var)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Proxy f() {
        return this.h;
    }

    public ds1 g() {
        return this.d;
    }

    public ProxySelector h() {
        return this.g;
    }

    public int hashCode() {
        int iHashCode = (((((((((((527 + this.f7251a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.f7252e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31;
        Proxy proxy = this.h;
        int iHashCode2 = (iHashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.i;
        int iHashCode3 = (iHashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.j;
        int iHashCode4 = (iHashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        is1 is1Var = this.k;
        return iHashCode4 + (is1Var != null ? is1Var.hashCode() : 0);
    }

    public SocketFactory i() {
        return this.c;
    }

    @Nullable
    public SSLSocketFactory j() {
        return this.i;
    }

    public vs1 k() {
        return this.f7251a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f7251a.g());
        sb.append(Constants.COLON_SEPARATOR);
        sb.append(this.f7251a.k());
        if (this.h != null) {
            sb.append(", proxy=");
            sb.append(this.h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.g);
        }
        sb.append(Operators.BLOCK_END_STR);
        return sb.toString();
    }

    public boolean a(cs1 cs1Var) {
        return this.b.equals(cs1Var.b) && this.d.equals(cs1Var.d) && this.f7252e.equals(cs1Var.f7252e) && this.f.equals(cs1Var.f) && this.g.equals(cs1Var.g) && kt1.a(this.h, cs1Var.h) && kt1.a(this.i, cs1Var.i) && kt1.a(this.j, cs1Var.j) && kt1.a(this.k, cs1Var.k) && k().k() == cs1Var.k().k();
    }
}
