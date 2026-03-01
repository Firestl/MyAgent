package supwisdom;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class x21 extends b31 {
    public final TreeMap<v51, w21> f;

    public x21(t11 t11Var) {
        super("string_ids", t11Var, 4);
        this.f = new TreeMap<>();
    }

    public e21 a(u41 u41Var) {
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        g();
        w21 w21Var = this.f.get((v51) u41Var);
        if (w21Var != null) {
            return w21Var;
        }
        throw new IllegalArgumentException("not found");
    }

    public w21 b(v51 v51Var) {
        return a(new w21(v51Var));
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        return this.f.values();
    }

    @Override // supwisdom.b31
    public void j() {
        Iterator<w21> it = this.f.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next().a(i);
            i++;
        }
    }

    public void d(h61 h61Var) {
        g();
        int size = this.f.size();
        int iC = size == 0 ? 0 : c();
        if (h61Var.e()) {
            h61Var.a(4, "string_ids_size: " + m61.g(size));
            h61Var.a(4, "string_ids_off:  " + m61.g(iC));
        }
        h61Var.writeInt(size);
        h61Var.writeInt(iC);
    }

    public int a(v51 v51Var) {
        if (v51Var != null) {
            g();
            w21 w21Var = this.f.get(v51Var);
            if (w21Var != null) {
                return w21Var.d();
            }
            throw new IllegalArgumentException("not found");
        }
        throw new NullPointerException("string == null");
    }

    public synchronized w21 a(w21 w21Var) {
        if (w21Var != null) {
            h();
            v51 v51VarG = w21Var.g();
            w21 w21Var2 = this.f.get(v51VarG);
            if (w21Var2 != null) {
                return w21Var2;
            }
            this.f.put(v51VarG, w21Var);
            return w21Var;
        }
        throw new NullPointerException("string == null");
    }
}
