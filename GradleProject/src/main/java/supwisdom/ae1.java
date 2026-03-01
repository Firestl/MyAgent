package supwisdom;

import com.squareup.okhttp.Protocol;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: Address.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ae1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Proxy f6890a;
    public final String b;
    public final int c;
    public final SocketFactory d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SSLSocketFactory f6891e;
    public final HostnameVerifier f;
    public final ge1 g;
    public final be1 h;
    public final List<Protocol> i;
    public final List<ke1> j;
    public final ProxySelector k;

    public ae1(String str, int i, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, ge1 ge1Var, be1 be1Var, Proxy proxy, List<Protocol> list, List<ke1> list2, ProxySelector proxySelector) {
        if (str == null) {
            throw new NullPointerException("uriHost == null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("uriPort <= 0: " + i);
        }
        if (be1Var == null) {
            throw new IllegalArgumentException("authenticator == null");
        }
        if (list == null) {
            throw new IllegalArgumentException("protocols == null");
        }
        if (proxySelector == null) {
            throw new IllegalArgumentException("proxySelector == null");
        }
        this.f6890a = proxy;
        this.b = str;
        this.c = i;
        this.d = socketFactory;
        this.f6891e = sSLSocketFactory;
        this.f = hostnameVerifier;
        this.g = ge1Var;
        this.h = be1Var;
        this.i = gf1.a(list);
        this.j = gf1.a(list2);
        this.k = proxySelector;
    }

    public List<ke1> a() {
        return this.j;
    }

    public Proxy b() {
        return this.f6890a;
    }

    public ProxySelector c() {
        return this.k;
    }

    public String d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ae1)) {
            return false;
        }
        ae1 ae1Var = (ae1) obj;
        return gf1.a(this.f6890a, ae1Var.f6890a) && this.b.equals(ae1Var.b) && this.c == ae1Var.c && gf1.a(this.f6891e, ae1Var.f6891e) && gf1.a(this.f, ae1Var.f) && gf1.a(this.g, ae1Var.g) && gf1.a(this.h, ae1Var.h) && gf1.a(this.i, ae1Var.i) && gf1.a(this.j, ae1Var.j) && gf1.a(this.k, ae1Var.k);
    }

    public int hashCode() {
        Proxy proxy = this.f6890a;
        int iHashCode = (((((527 + (proxy != null ? proxy.hashCode() : 0)) * 31) + this.b.hashCode()) * 31) + this.c) * 31;
        SSLSocketFactory sSLSocketFactory = this.f6891e;
        int iHashCode2 = (iHashCode + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.f;
        int iHashCode3 = (iHashCode2 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        ge1 ge1Var = this.g;
        return ((((((((iHashCode3 + (ge1Var != null ? ge1Var.hashCode() : 0)) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode()) * 31) + this.k.hashCode();
    }
}
