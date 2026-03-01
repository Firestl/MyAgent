package supwisdom;

import com.taobao.weex.el.parse.Operators;
import supwisdom.e41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class s41 extends c41 {
    public final d61 f;

    public s41(o41 o41Var, r41 r41Var, m41 m41Var, d61 d61Var, u41 u41Var) {
        super(o41Var, r41Var, null, m41Var, u41Var);
        if (o41Var.b() == 6) {
            if (d61Var == null) {
                throw new NullPointerException("catches == null");
            }
            this.f = d61Var;
        } else {
            throw new IllegalArgumentException("opcode with invalid branchingness: " + o41Var.b());
        }
    }

    @Override // supwisdom.e41
    public void a(e41.b bVar) {
        bVar.a(this);
    }

    @Override // supwisdom.e41
    public d61 d() {
        return this.f;
    }

    @Override // supwisdom.c41, supwisdom.e41
    public String e() {
        u41 u41VarJ = j();
        String human = u41VarJ.toHuman();
        if (u41VarJ instanceof v51) {
            human = ((v51) u41VarJ).h();
        }
        return human + Operators.SPACE_STR + t41.a(this.f);
    }
}
