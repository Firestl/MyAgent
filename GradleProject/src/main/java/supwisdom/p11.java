package supwisdom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class p11 extends b31 {
    public final TreeMap<b61, o11> f;
    public ArrayList<o11> g;

    public p11(t11 t11Var) {
        super("class_defs", t11Var, 4);
        this.f = new TreeMap<>();
        this.g = null;
    }

    public void a(o11 o11Var) {
        try {
            b61 b61VarD = o11Var.i().d();
            h();
            if (this.f.get(b61VarD) == null) {
                this.f.put(b61VarD, o11Var);
                return;
            }
            throw new IllegalArgumentException("already added: " + b61VarD);
        } catch (NullPointerException unused) {
            throw new NullPointerException("clazz == null");
        }
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        ArrayList<o11> arrayList = this.g;
        return arrayList != null ? arrayList : this.f.values();
    }

    @Override // supwisdom.b31
    public void j() {
        int size = this.f.size();
        this.g = new ArrayList<>(size);
        Iterator<b61> it = this.f.keySet().iterator();
        int iA = 0;
        while (it.hasNext()) {
            iA = a(it.next(), iA, size - iA);
        }
    }

    public void d(h61 h61Var) {
        g();
        int size = this.f.size();
        int iC = size == 0 ? 0 : c();
        if (h61Var.e()) {
            h61Var.a(4, "class_defs_size: " + m61.g(size));
            h61Var.a(4, "class_defs_off:  " + m61.g(iC));
        }
        h61Var.writeInt(size);
        h61Var.writeInt(iC);
    }

    public final int a(b61 b61Var, int i, int i2) {
        o11 o11Var = this.f.get(b61Var);
        if (o11Var == null || o11Var.e()) {
            return i;
        }
        if (i2 >= 0) {
            int i3 = i2 - 1;
            w51 w51VarH = o11Var.h();
            if (w51VarH != null) {
                i = a(w51VarH.d(), i, i3);
            }
            d61 d61VarG = o11Var.g();
            int size = d61VarG.size();
            for (int i4 = 0; i4 < size; i4++) {
                i = a(d61VarG.getType(i4), i, i3);
            }
            o11Var.a(i);
            this.g.add(o11Var);
            return i + 1;
        }
        throw new RuntimeException("class circularity with " + b61Var);
    }
}
