package supwisdom;

import supwisdom.e41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class j41 extends e41 {
    public j41(o41 o41Var, r41 r41Var, l41 l41Var, m41 m41Var) {
        super(o41Var, r41Var, l41Var, m41Var);
        int iB = o41Var.b();
        if (iB == 5 || iB == 6) {
            throw new IllegalArgumentException("opcode with invalid branchingness: " + o41Var.b());
        }
        if (l41Var != null && o41Var.b() != 1) {
            throw new IllegalArgumentException("can't mix branchingness with result");
        }
    }

    @Override // supwisdom.e41
    public void a(e41.b bVar) {
        bVar.a(this);
    }

    @Override // supwisdom.e41
    public d61 d() {
        return a61.c;
    }

    public j41(o41 o41Var, r41 r41Var, l41 l41Var, l41 l41Var2) {
        this(o41Var, r41Var, l41Var, m41.b(l41Var2));
    }
}
