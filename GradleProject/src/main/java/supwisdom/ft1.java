package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ft1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cs1 f7647a;
    public final Proxy b;
    public final InetSocketAddress c;

    public ft1(cs1 cs1Var, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (cs1Var == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.f7647a = cs1Var;
        this.b = proxy;
        this.c = inetSocketAddress;
    }

    public cs1 a() {
        return this.f7647a;
    }

    public Proxy b() {
        return this.b;
    }

    public boolean c() {
        return this.f7647a.i != null && this.b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.c;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ft1) {
            ft1 ft1Var = (ft1) obj;
            if (ft1Var.f7647a.equals(this.f7647a) && ft1Var.b.equals(this.b) && ft1Var.c.equals(this.c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f7647a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "Route{" + this.c + Operators.BLOCK_END_STR;
    }
}
