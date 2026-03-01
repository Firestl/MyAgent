package supwisdom;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b31 extends t21 {
    public b31(String str, t11 t11Var, int i) {
        super(str, t11Var, i);
    }

    @Override // supwisdom.t21
    public final int a(f21 f21Var) {
        e21 e21Var = (e21) f21Var;
        return a(e21Var.d() * e21Var.c());
    }

    @Override // supwisdom.t21
    public final void c(h61 h61Var) {
        t11 t11VarB = b();
        int iA = a();
        Iterator<? extends f21> it = d().iterator();
        while (it.hasNext()) {
            it.next().a(t11VarB, h61Var);
            h61Var.d(iA);
        }
    }

    @Override // supwisdom.t21
    public final void f() {
        t11 t11VarB = b();
        j();
        Iterator<? extends f21> it = d().iterator();
        while (it.hasNext()) {
            it.next().a(t11VarB);
        }
    }

    @Override // supwisdom.t21
    public final int i() {
        Collection<? extends f21> collectionD = d();
        int size = collectionD.size();
        if (size == 0) {
            return 0;
        }
        return size * collectionD.iterator().next().c();
    }

    public abstract void j();
}
