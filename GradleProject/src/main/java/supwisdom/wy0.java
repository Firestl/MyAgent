package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class wy0<D, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final az0<D> f9683a;
    public final String b;
    public final f51 c;

    public wy0(az0<D> az0Var, az0<V> az0Var2, String str) {
        if (az0Var == null || az0Var2 == null || str == null) {
            throw null;
        }
        this.f9683a = az0Var;
        this.b = str;
        this.c = new f51(az0Var.c, new s51(new v51(str), new v51(az0Var2.f6998a)));
    }

    public boolean equals(Object obj) {
        if (obj instanceof wy0) {
            wy0 wy0Var = (wy0) obj;
            if (wy0Var.f9683a.equals(this.f9683a) && wy0Var.b.equals(this.b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.f9683a.hashCode() + (this.b.hashCode() * 37);
    }

    public String toString() {
        return this.f9683a + Operators.DOT_STR + this.b;
    }
}
