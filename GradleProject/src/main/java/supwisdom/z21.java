package supwisdom;

import com.sangfor.dex.DexIndexOverflowException;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class z21 extends b31 {
    public final TreeMap<b61, y21> f;

    public z21(t11 t11Var) {
        super("type_ids", t11Var, 4);
        this.f = new TreeMap<>();
    }

    public e21 a(u41 u41Var) {
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        g();
        y21 y21Var = this.f.get(((w51) u41Var).d());
        if (y21Var != null) {
            return y21Var;
        }
        throw new IllegalArgumentException("not found: " + u41Var);
    }

    public synchronized y21 b(b61 b61Var) {
        y21 y21Var;
        if (b61Var == null) {
            throw new NullPointerException("type == null");
        }
        h();
        y21Var = this.f.get(b61Var);
        if (y21Var == null) {
            y21Var = new y21(new w51(b61Var));
            this.f.put(b61Var, y21Var);
        }
        return y21Var;
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        return this.f.values();
    }

    @Override // supwisdom.b31
    public void j() {
        Iterator<? extends f21> it = d().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((y21) it.next()).a(i);
            i++;
        }
    }

    public void d(h61 h61Var) {
        g();
        int size = this.f.size();
        int iC = size == 0 ? 0 : c();
        if (size > 65536) {
            throw new DexIndexOverflowException(String.format("Too many type identifiers to fit in one dex file: %1$d; max is %2$d.%nYou may try using multi-dex. If multi-dex is enabled then the list of classes for the main dex list is too large.", Integer.valueOf(d().size()), 65536));
        }
        if (h61Var.e()) {
            h61Var.a(4, "type_ids_size:   " + m61.g(size));
            h61Var.a(4, "type_ids_off:    " + m61.g(iC));
        }
        h61Var.writeInt(size);
        h61Var.writeInt(iC);
    }

    public int a(b61 b61Var) {
        if (b61Var != null) {
            g();
            y21 y21Var = this.f.get(b61Var);
            if (y21Var != null) {
                return y21Var.d();
            }
            throw new IllegalArgumentException("not found: " + b61Var);
        }
        throw new NullPointerException("type == null");
    }

    public synchronized y21 b(w51 w51Var) {
        y21 y21Var;
        if (w51Var != null) {
            h();
            b61 b61VarD = w51Var.d();
            y21Var = this.f.get(b61VarD);
            if (y21Var == null) {
                y21Var = new y21(w51Var);
                this.f.put(b61VarD, y21Var);
            }
        } else {
            throw new NullPointerException("type == null");
        }
        return y21Var;
    }

    public int a(w51 w51Var) {
        if (w51Var != null) {
            return a(w51Var.d());
        }
        throw new NullPointerException("type == null");
    }
}
