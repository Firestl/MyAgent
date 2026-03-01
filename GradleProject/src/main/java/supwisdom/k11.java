package supwisdom;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class k11 extends b31 {
    public final TreeMap<b51, j11> f;
    public final TreeMap<a51, l11> g;

    public k11(t11 t11Var) {
        super("call_site_ids", t11Var, 4);
        this.f = new TreeMap<>();
        this.g = new TreeMap<>();
    }

    public e21 a(u41 u41Var) {
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        g();
        j11 j11Var = this.f.get((b51) u41Var);
        if (j11Var != null) {
            return j11Var;
        }
        throw new IllegalArgumentException("not found");
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        return this.f.values();
    }

    @Override // supwisdom.b31
    public void j() {
        Iterator<j11> it = this.f.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next().a(i);
            i++;
        }
    }
}
