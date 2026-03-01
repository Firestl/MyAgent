package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class w51 extends x51 {
    public static final ConcurrentMap<b61, w51> c = new ConcurrentHashMap(1000, 0.75f);
    public static final w51 d = new w51(b61.x);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final w51 f9589e = new w51(b61.B);
    public static final w51 f = new w51(b61.C);
    public static final w51 g = new w51(b61.D);
    public static final w51 h = new w51(b61.E);
    public static final w51 i = new w51(b61.F);
    public static final w51 j = new w51(b61.H);
    public static final w51 k = new w51(b61.G);
    public static final w51 l = new w51(b61.I);
    public static final w51 m = new w51(b61.J);
    public static final w51 n = new w51(b61.K);
    public static final w51 o = new w51(b61.L);
    public static final w51 p = new w51(b61.M);
    public static final w51 q = new w51(b61.N);
    public static final w51 r = new w51(b61.O);
    public static final w51 s = new w51(b61.Q);
    public static final w51 t = new w51(b61.P);
    public static final w51 u = new w51(b61.S);
    public static final w51 v = new w51(b61.u);
    public static final w51 w = new w51(b61.w);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b61 f9590a;
    public v51 b;

    static {
        g();
    }

    public w51(b61 b61Var) {
        if (b61Var == null) {
            throw new NullPointerException("type == null");
        }
        if (b61Var == b61.p) {
            throw new UnsupportedOperationException("KNOWN_NULL is not representable");
        }
        this.f9590a = b61Var;
        this.b = null;
    }

    public static w51 a(b61 b61Var) {
        w51 w51Var = new w51(b61Var);
        w51 w51VarPutIfAbsent = c.putIfAbsent(b61Var, w51Var);
        return w51VarPutIfAbsent != null ? w51VarPutIfAbsent : w51Var;
    }

    public static void g() {
        a(d);
        a(f9589e);
        a(f);
        a(g);
        a(h);
        a(i);
        a(j);
        a(k);
        a(l);
        a(m);
        a(n);
        a(o);
        a(p);
        a(q);
        a(r);
        a(s);
        a(t);
        a(u);
        a(v);
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        return this.f9590a.g().compareTo(((w51) u41Var).f9590a.g());
    }

    @Override // supwisdom.u41
    public String c() {
        return "type";
    }

    public b61 d() {
        return this.f9590a;
    }

    public v51 e() {
        if (this.b == null) {
            this.b = new v51(this.f9590a.g());
        }
        return this.b;
    }

    public boolean equals(Object obj) {
        return (obj instanceof w51) && this.f9590a == ((w51) obj).f9590a;
    }

    public String f() {
        String strE = e().e();
        int iLastIndexOf = strE.lastIndexOf(47);
        return iLastIndexOf == -1 ? "default" : strE.substring(strE.lastIndexOf(91) + 2, iLastIndexOf).replace('/', '.');
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.s;
    }

    public int hashCode() {
        return this.f9590a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.f9590a.toHuman();
    }

    public String toString() {
        return "type{" + toHuman() + Operators.BLOCK_END;
    }

    public static void a(w51 w51Var) {
        if (c.putIfAbsent(w51Var.d(), w51Var) == null) {
            return;
        }
        throw new IllegalStateException("Attempted re-init of " + w51Var);
    }
}
