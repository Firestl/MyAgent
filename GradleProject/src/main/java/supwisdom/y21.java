package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class y21 extends d21 {
    public y21(w51 w51Var) {
        super(w51Var);
    }

    @Override // supwisdom.d21, supwisdom.f21
    public void a(t11 t11Var) {
        t11Var.o().b(g().e());
    }

    @Override // supwisdom.f21
    public int c() {
        return 4;
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_TYPE_ID_ITEM;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var, h61 h61Var) {
        v51 v51VarE = g().e();
        int iA = t11Var.o().a(v51VarE);
        if (h61Var.e()) {
            h61Var.a(0, f() + ' ' + v51VarE.toHuman());
            StringBuilder sb = new StringBuilder();
            sb.append("  descriptor_idx: ");
            sb.append(m61.g(iA));
            h61Var.a(4, sb.toString());
        }
        h61Var.writeInt(iA);
    }
}
