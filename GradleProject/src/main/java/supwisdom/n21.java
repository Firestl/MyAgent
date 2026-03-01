package supwisdom;

import java.util.Collection;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class n21 extends i21 {
    public final TreeMap<x41, m21> f;

    public n21(t11 t11Var) {
        super("method_ids", t11Var);
        this.f = new TreeMap<>();
    }

    public e21 a(u41 u41Var) {
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        g();
        m21 m21Var = this.f.get((x41) u41Var);
        if (m21Var != null) {
            return m21Var;
        }
        throw new IllegalArgumentException("not found");
    }

    public synchronized m21 b(x41 x41Var) {
        m21 m21Var;
        if (x41Var == null) {
            throw new NullPointerException("method == null");
        }
        h();
        m21Var = this.f.get(x41Var);
        if (m21Var == null) {
            m21Var = new m21(x41Var);
            this.f.put(x41Var, m21Var);
        }
        return m21Var;
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        return this.f.values();
    }

    public void d(h61 h61Var) {
        g();
        int size = this.f.size();
        int iC = size == 0 ? 0 : c();
        if (h61Var.e()) {
            h61Var.a(4, "method_ids_size: " + m61.g(size));
            h61Var.a(4, "method_ids_off:  " + m61.g(iC));
        }
        h61Var.writeInt(size);
        h61Var.writeInt(iC);
    }

    public int a(x41 x41Var) {
        if (x41Var != null) {
            g();
            m21 m21Var = this.f.get(x41Var);
            if (m21Var != null) {
                return m21Var.d();
            }
            throw new IllegalArgumentException("not found");
        }
        throw new NullPointerException("ref == null");
    }
}
