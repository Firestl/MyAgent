package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class h21 extends d21 {
    public final p51 c;

    public h21(p51 p51Var) {
        super(p51Var.d());
        this.c = p51Var;
    }

    @Override // supwisdom.d21, supwisdom.f21
    public void a(t11 t11Var) {
        super.a(t11Var);
        t11Var.o().b(h().e().f());
    }

    public abstract int b(t11 t11Var);

    @Override // supwisdom.f21
    public int c() {
        return 8;
    }

    public final p51 h() {
        return this.c;
    }

    public abstract String i();

    @Override // supwisdom.f21
    public final void a(t11 t11Var, h61 h61Var) {
        z21 z21VarP = t11Var.p();
        x21 x21VarO = t11Var.o();
        s51 s51VarE = this.c.e();
        int iA = z21VarP.a(g());
        int iA2 = x21VarO.a(s51VarE.f());
        int iB = b(t11Var);
        if (h61Var.e()) {
            h61Var.a(0, f() + ' ' + this.c.toHuman());
            StringBuilder sb = new StringBuilder();
            sb.append("  class_idx: ");
            sb.append(m61.d(iA));
            h61Var.a(2, sb.toString());
            h61Var.a(2, String.format("  %-10s %s", i() + Operators.CONDITION_IF_MIDDLE, m61.d(iB)));
            h61Var.a(4, "  name_idx:  " + m61.g(iA2));
        }
        h61Var.writeShort(iA);
        h61Var.writeShort(iB);
        h61Var.writeInt(iA2);
    }
}
