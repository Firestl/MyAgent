package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class r21 extends e21 {
    public final z51 b;
    public final v51 c;
    public a31 d;

    public r21(z51 z51Var) {
        if (z51Var == null) {
            throw new NullPointerException("prototype == null");
        }
        this.b = z51Var;
        this.c = a(z51Var);
        a61 a61VarC = z51Var.c();
        this.d = a61VarC.size() == 0 ? null : new a31(a61VarC);
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        x21 x21VarO = t11Var.o();
        z21 z21VarP = t11Var.p();
        o21 o21VarQ = t11Var.q();
        z21VarP.b(this.b.d());
        x21VarO.b(this.c);
        a31 a31Var = this.d;
        if (a31Var != null) {
            this.d = (a31) o21VarQ.b(a31Var);
        }
    }

    @Override // supwisdom.f21
    public int c() {
        return 12;
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_PROTO_ID_ITEM;
    }

    public static v51 a(z51 z51Var) {
        a61 a61VarC = z51Var.c();
        int size = a61VarC.size();
        StringBuilder sb = new StringBuilder(size + 1);
        sb.append(a(z51Var.d()));
        for (int i = 0; i < size; i++) {
            sb.append(a(a61VarC.getType(i)));
        }
        return new v51(sb.toString());
    }

    public static char a(b61 b61Var) {
        char cCharAt = b61Var.g().charAt(0);
        return cCharAt == '[' ? Matrix.MATRIX_TYPE_RANDOM_LT : cCharAt;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var, h61 h61Var) {
        int iA = t11Var.o().a(this.c);
        int iA2 = t11Var.p().a(this.b.d());
        int iC = p21.c(this.d);
        if (h61Var.e()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.d().toHuman());
            sb.append(" proto(");
            a61 a61VarC = this.b.c();
            int size = a61VarC.size();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(a61VarC.getType(i).toHuman());
            }
            sb.append(")");
            h61Var.a(0, f() + ' ' + sb.toString());
            h61Var.a(4, "  shorty_idx:      " + m61.g(iA) + " // " + this.c.h());
            h61Var.a(4, "  return_type_idx: " + m61.g(iA2) + " // " + this.b.d().toHuman());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  parameters_off:  ");
            sb2.append(m61.g(iC));
            h61Var.a(4, sb2.toString());
        }
        h61Var.writeInt(iA);
        h61Var.writeInt(iA2);
        h61Var.writeInt(iC);
    }
}
