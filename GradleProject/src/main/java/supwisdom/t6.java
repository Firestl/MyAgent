package supwisdom;

import supwisdom.s6;

/* JADX INFO: compiled from: DimensionDependency.java */
/* JADX INFO: loaded from: classes.dex */
public class t6 extends s6 {
    public int m;

    public t6(c7 c7Var) {
        super(c7Var);
        if (c7Var instanceof y6) {
            this.f9127e = s6.a.HORIZONTAL_DIMENSION;
        } else {
            this.f9127e = s6.a.VERTICAL_DIMENSION;
        }
    }

    @Override // supwisdom.s6
    public void a(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (q6 q6Var : this.k) {
            q6Var.a(q6Var);
        }
    }
}
