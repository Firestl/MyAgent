package supwisdom;

import java.io.IOException;
import java.util.List;
import supwisdom.ws1;

/* JADX INFO: compiled from: RealInterceptorChain.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cu1 implements ws1.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<ws1> f7257a;
    public final vt1 b;
    public final yt1 c;
    public final st1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f7258e;
    public final bt1 f;
    public final gs1 g;
    public final rs1 h;
    public final int i;
    public final int j;
    public final int k;
    public int l;

    public cu1(List<ws1> list, vt1 vt1Var, yt1 yt1Var, st1 st1Var, int i, bt1 bt1Var, gs1 gs1Var, rs1 rs1Var, int i2, int i3, int i4) {
        this.f7257a = list;
        this.d = st1Var;
        this.b = vt1Var;
        this.c = yt1Var;
        this.f7258e = i;
        this.f = bt1Var;
        this.g = gs1Var;
        this.h = rs1Var;
        this.i = i2;
        this.j = i3;
        this.k = i4;
    }

    public gs1 a() {
        return this.g;
    }

    public rs1 b() {
        return this.h;
    }

    public yt1 c() {
        return this.c;
    }

    @Override // supwisdom.ws1.a
    public int connectTimeoutMillis() {
        return this.i;
    }

    @Override // supwisdom.ws1.a
    public ks1 connection() {
        return this.d;
    }

    public vt1 d() {
        return this.b;
    }

    @Override // supwisdom.ws1.a
    public int readTimeoutMillis() {
        return this.j;
    }

    @Override // supwisdom.ws1.a
    public bt1 request() {
        return this.f;
    }

    @Override // supwisdom.ws1.a
    public int writeTimeoutMillis() {
        return this.k;
    }

    @Override // supwisdom.ws1.a
    public dt1 a(bt1 bt1Var) throws IOException {
        return a(bt1Var, this.b, this.c, this.d);
    }

    public dt1 a(bt1 bt1Var, vt1 vt1Var, yt1 yt1Var, st1 st1Var) throws IOException {
        if (this.f7258e < this.f7257a.size()) {
            this.l++;
            if (this.c != null && !this.d.a(bt1Var.g())) {
                throw new IllegalStateException("network interceptor " + this.f7257a.get(this.f7258e - 1) + " must retain the same host and port");
            }
            if (this.c != null && this.l > 1) {
                throw new IllegalStateException("network interceptor " + this.f7257a.get(this.f7258e - 1) + " must call proceed() exactly once");
            }
            cu1 cu1Var = new cu1(this.f7257a, vt1Var, yt1Var, st1Var, this.f7258e + 1, bt1Var, this.g, this.h, this.i, this.j, this.k);
            ws1 ws1Var = this.f7257a.get(this.f7258e);
            dt1 dt1VarIntercept = ws1Var.intercept(cu1Var);
            if (yt1Var != null && this.f7258e + 1 < this.f7257a.size() && cu1Var.l != 1) {
                throw new IllegalStateException("network interceptor " + ws1Var + " must call proceed() exactly once");
            }
            if (dt1VarIntercept != null) {
                if (dt1VarIntercept.a() != null) {
                    return dt1VarIntercept;
                }
                throw new IllegalStateException("interceptor " + ws1Var + " returned a response with no body");
            }
            throw new NullPointerException("interceptor " + ws1Var + " returned null");
        }
        throw new AssertionError();
    }
}
