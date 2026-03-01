package supwisdom;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class l21 extends b31 {
    public final TreeMap<q51, k21> f;

    public l21(t11 t11Var) {
        super("method_handles", t11Var, 8);
        this.f = new TreeMap<>();
    }

    public e21 a(u41 u41Var) {
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        g();
        k21 k21Var = this.f.get((q51) u41Var);
        if (k21Var != null) {
            return k21Var;
        }
        throw new IllegalArgumentException("not found");
    }

    public void b(q51 q51Var) {
        if (q51Var == null) {
            throw new NullPointerException("methodHandle == null");
        }
        h();
        if (this.f.get(q51Var) == null) {
            this.f.put(q51Var, new k21(q51Var));
        }
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        return this.f.values();
    }

    @Override // supwisdom.b31
    public void j() {
        Iterator<k21> it = this.f.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next().a(i);
            i++;
        }
    }

    public int a(q51 q51Var) {
        return this.f.get(q51Var).d();
    }
}
