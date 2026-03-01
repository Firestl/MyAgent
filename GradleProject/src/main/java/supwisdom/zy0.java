package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class zy0<D, R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final az0<D> f10047a;
    public final az0<R> b;
    public final String c;
    public final bz0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r51 f10048e;

    public zy0(az0<D> az0Var, az0<R> az0Var2, String str, bz0 bz0Var) {
        if (az0Var == null || az0Var2 == null || str == null || bz0Var == null) {
            throw null;
        }
        this.f10047a = az0Var;
        this.b = az0Var2;
        this.c = str;
        this.d = bz0Var;
        this.f10048e = new r51(az0Var.c, new s51(new v51(str), new v51(a(false))));
    }

    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (z) {
            sb.append(this.f10047a.f6998a);
        }
        for (az0<?> az0Var : this.d.f7128a) {
            sb.append(az0Var.f6998a);
        }
        sb.append(")");
        sb.append(this.b.f6998a);
        return sb.toString();
    }

    public boolean b() {
        return this.c.equals("<clinit>");
    }

    public boolean equals(Object obj) {
        if (obj instanceof zy0) {
            zy0 zy0Var = (zy0) obj;
            if (zy0Var.f10047a.equals(this.f10047a) && zy0Var.c.equals(this.c) && zy0Var.d.equals(this.d) && zy0Var.b.equals(this.b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f10047a.hashCode() + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.b.hashCode();
    }

    public String toString() {
        return this.f10047a + Operators.DOT_STR + this.c + "(" + this.d + ")";
    }

    public z51 b(boolean z) {
        return z51.b(a(z));
    }

    public boolean a() {
        return this.c.equals("<init>");
    }
}
