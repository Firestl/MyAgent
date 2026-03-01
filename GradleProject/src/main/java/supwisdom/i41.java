package supwisdom;

import supwisdom.e41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class i41 extends c41 {
    public i41(o41 o41Var, r41 r41Var, l41 l41Var, m41 m41Var, u41 u41Var) {
        super(o41Var, r41Var, l41Var, m41Var, u41Var);
        if (o41Var.b() == 1) {
            return;
        }
        throw new IllegalArgumentException("opcode with invalid branchingness: " + o41Var.b());
    }

    @Override // supwisdom.e41
    public void a(e41.b bVar) {
        bVar.a(this);
    }

    @Override // supwisdom.e41
    public d61 d() {
        return a61.c;
    }
}
