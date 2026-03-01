package supwisdom;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class c21 extends b31 {
    public final List<b21> f;

    public c21(t11 t11Var) {
        super(null, t11Var, 4);
        b21 b21Var = new b21();
        b21Var.a(0);
        this.f = Collections.singletonList(b21Var);
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        return this.f;
    }

    @Override // supwisdom.b31
    public void j() {
    }
}
