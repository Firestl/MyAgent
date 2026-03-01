package supwisdom;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class s21 extends b31 {
    public final TreeMap<z51, r21> f;

    public s21(t11 t11Var) {
        super("proto_ids", t11Var, 4);
        this.f = new TreeMap<>();
    }

    public e21 a(u41 u41Var) {
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        if (!(u41Var instanceof t51)) {
            throw new IllegalArgumentException("cst not instance of CstProtoRef");
        }
        g();
        r21 r21Var = this.f.get(((t51) u41Var).d());
        if (r21Var != null) {
            return r21Var;
        }
        throw new IllegalArgumentException("not found");
    }

    public synchronized r21 b(z51 z51Var) {
        r21 r21Var;
        if (z51Var == null) {
            throw new NullPointerException("prototype == null");
        }
        h();
        r21Var = this.f.get(z51Var);
        if (r21Var == null) {
            r21Var = new r21(z51Var);
            this.f.put(z51Var, r21Var);
        }
        return r21Var;
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
            ((r21) it.next()).a(i);
            i++;
        }
    }

    public void d(h61 h61Var) {
        g();
        int size = this.f.size();
        int iC = size == 0 ? 0 : c();
        if (size > 65536) {
            throw new UnsupportedOperationException("too many proto ids");
        }
        if (h61Var.e()) {
            h61Var.a(4, "proto_ids_size:  " + m61.g(size));
            h61Var.a(4, "proto_ids_off:   " + m61.g(iC));
        }
        h61Var.writeInt(size);
        h61Var.writeInt(iC);
    }

    public int a(z51 z51Var) {
        if (z51Var != null) {
            g();
            r21 r21Var = this.f.get(z51Var);
            if (r21Var != null) {
                return r21Var.d();
            }
            throw new IllegalArgumentException("not found");
        }
        throw new NullPointerException("prototype == null");
    }
}
