package supwisdom;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes2.dex */
public final class we1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ae1 f9616a;
    public final Proxy b;
    public final InetSocketAddress c;
    public final ke1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f9617e;

    public we1(ae1 ae1Var, Proxy proxy, InetSocketAddress inetSocketAddress, ke1 ke1Var, boolean z) {
        if (ae1Var == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        if (ke1Var == null) {
            throw new NullPointerException("connectionConfiguration == null");
        }
        this.f9616a = ae1Var;
        this.b = proxy;
        this.c = inetSocketAddress;
        this.d = ke1Var;
        this.f9617e = z;
    }

    public ae1 a() {
        return this.f9616a;
    }

    public Proxy b() {
        return this.b;
    }

    public boolean c() {
        return this.f9616a.f6891e != null && this.b.type() == Proxy.Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof we1)) {
            return false;
        }
        we1 we1Var = (we1) obj;
        return this.f9616a.equals(we1Var.f9616a) && this.b.equals(we1Var.b) && this.c.equals(we1Var.c) && this.d.equals(we1Var.d) && this.f9617e == we1Var.f9617e;
    }

    public int hashCode() {
        return ((((((((527 + this.f9616a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + (this.f9617e ? 1 : 0);
    }
}
