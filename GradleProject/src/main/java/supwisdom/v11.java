package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class v11 extends w11 implements Comparable<v11> {
    public final f51 b;

    public v11(f51 f51Var, int i) {
        super(i);
        if (f51Var == null) {
            throw new NullPointerException("field == null");
        }
        this.b = f51Var;
    }

    public void a(t11 t11Var) {
        t11Var.e().b(this.b);
    }

    public f51 d() {
        return this.b;
    }

    public boolean equals(Object obj) {
        return (obj instanceof v11) && compareTo((v11) obj) == 0;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.b.toHuman();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(v11.class.getName());
        sb.append(Operators.BLOCK_START);
        sb.append(m61.d(c()));
        sb.append(' ');
        sb.append(this.b);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    @Override // supwisdom.w11
    public int a(t11 t11Var, h61 h61Var, int i, int i2) {
        int iA = t11Var.e().a(this.b);
        int i3 = iA - i;
        int iC = c();
        if (h61Var.e()) {
            h61Var.a(0, String.format("  [%x] %s", Integer.valueOf(i2), this.b.toHuman()));
            h61Var.a(py0.a(i3), "    field_idx:    " + m61.g(iA));
            h61Var.a(py0.a(iC), "    access_flags: " + z31.b(iC));
        }
        h61Var.c(i3);
        h61Var.c(iC);
        return iA;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(v11 v11Var) {
        return this.b.compareTo(v11Var.b);
    }
}
