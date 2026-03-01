package supwisdom;

import com.taobao.weex.el.parse.Operators;
import supwisdom.e41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class t41 extends e41 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d61 f9251e;

    public t41(o41 o41Var, r41 r41Var, m41 m41Var, d61 d61Var) {
        super(o41Var, r41Var, null, m41Var);
        if (o41Var.b() == 6) {
            if (d61Var == null) {
                throw new NullPointerException("catches == null");
            }
            this.f9251e = d61Var;
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
        return this.f9251e;
    }

    @Override // supwisdom.e41
    public String e() {
        return a(this.f9251e);
    }

    public static String a(d61 d61Var) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("catch");
        int size = d61Var.size();
        for (int i = 0; i < size; i++) {
            sb.append(Operators.SPACE_STR);
            sb.append(d61Var.getType(i).toHuman());
        }
        return sb.toString();
    }
}
