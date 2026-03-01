package supwisdom;

import java.util.Collection;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class a21 extends i21 {
    public final TreeMap<f51, z11> f;

    public a21(t11 t11Var) {
        super("field_ids", t11Var);
        this.f = new TreeMap<>();
    }

    public e21 a(u41 u41Var) {
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        g();
        z11 z11Var = this.f.get((f51) u41Var);
        if (z11Var != null) {
            return z11Var;
        }
        throw new IllegalArgumentException("not found");
    }

    public synchronized z11 b(f51 f51Var) {
        z11 z11Var;
        if (f51Var == null) {
            throw new NullPointerException("field == null");
        }
        h();
        z11Var = this.f.get(f51Var);
        if (z11Var == null) {
            z11Var = new z11(f51Var);
            this.f.put(f51Var, z11Var);
        }
        return z11Var;
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
            h61Var.a(4, "field_ids_size:  " + m61.g(size));
            h61Var.a(4, "field_ids_off:   " + m61.g(iC));
        }
        h61Var.writeInt(size);
        h61Var.writeInt(iC);
    }

    public int a(f51 f51Var) {
        if (f51Var != null) {
            g();
            z11 z11Var = this.f.get(f51Var);
            if (z11Var != null) {
                return z11Var.d();
            }
            throw new IllegalArgumentException("not found");
        }
        throw new NullPointerException("ref == null");
    }
}
