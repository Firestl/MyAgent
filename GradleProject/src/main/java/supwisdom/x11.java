package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class x11 extends w11 implements Comparable<x11> {
    public final r51 b;
    public final q11 c;

    public x11(r51 r51Var, int i, jz0 jz0Var, d61 d61Var) {
        super(i);
        if (r51Var == null) {
            throw new NullPointerException("method == null");
        }
        this.b = r51Var;
        if (jz0Var == null) {
            this.c = null;
        } else {
            this.c = new q11(r51Var, jz0Var, (i & 8) != 0, d61Var);
        }
    }

    public void a(t11 t11Var) {
        n21 n21VarK = t11Var.k();
        o21 o21VarR = t11Var.r();
        n21VarK.b(this.b);
        q11 q11Var = this.c;
        if (q11Var != null) {
            o21VarR.a((p21) q11Var);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof x11) && compareTo((x11) obj) == 0;
    }

    @Override // supwisdom.t61
    public final String toHuman() {
        return this.b.toHuman();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(x11.class.getName());
        sb.append(Operators.BLOCK_START);
        sb.append(m61.d(c()));
        sb.append(' ');
        sb.append(this.b);
        if (this.c != null) {
            sb.append(' ');
            sb.append(this.c);
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    @Override // supwisdom.w11
    public int a(t11 t11Var, h61 h61Var, int i, int i2) {
        int iA = t11Var.k().a((x41) this.b);
        int i3 = iA - i;
        int iC = c();
        int iC2 = p21.c(this.c);
        if ((iC2 != 0) == ((iC & 1280) == 0)) {
            if (h61Var.e()) {
                h61Var.a(0, String.format("  [%x] %s", Integer.valueOf(i2), this.b.toHuman()));
                h61Var.a(py0.a(i3), "    method_idx:   " + m61.g(iA));
                h61Var.a(py0.a(iC), "    access_flags: " + z31.c(iC));
                h61Var.a(py0.a(iC2), "    code_off:     " + m61.g(iC2));
            }
            h61Var.c(i3);
            h61Var.c(iC);
            h61Var.c(iC2);
            return iA;
        }
        throw new UnsupportedOperationException("code vs. access_flags mismatch");
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(x11 x11Var) {
        return this.b.compareTo(x11Var.b);
    }
}
