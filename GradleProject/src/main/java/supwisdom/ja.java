package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class ja<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f8034a;
    public final S b;

    public ja(F f, S s) {
        this.f8034a = f;
        this.b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ja)) {
            return false;
        }
        ja jaVar = (ja) obj;
        return ia.a(jaVar.f8034a, this.f8034a) && ia.a(jaVar.b, this.b);
    }

    public int hashCode() {
        F f = this.f8034a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.f8034a + Operators.SPACE_STR + this.b + Operators.BLOCK_END_STR;
    }
}
