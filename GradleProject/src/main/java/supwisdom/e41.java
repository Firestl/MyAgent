package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class e41 implements t61 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o41 f7423a;
    public final r41 b;
    public final l41 c;
    public final m41 d;

    /* JADX INFO: compiled from: Proguard */
    public static class a implements b {
        @Override // supwisdom.e41.b
        public void a(j41 j41Var) {
        }

        @Override // supwisdom.e41.b
        public void a(s41 s41Var) {
        }

        @Override // supwisdom.e41.b
        public void a(t41 t41Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public interface b {
        void a(i41 i41Var);

        void a(j41 j41Var);

        void a(s41 s41Var);

        void a(t41 t41Var);
    }

    public e41(o41 o41Var, r41 r41Var, l41 l41Var, m41 m41Var) {
        if (o41Var == null) {
            throw new NullPointerException("opcode == null");
        }
        if (r41Var == null) {
            throw new NullPointerException("position == null");
        }
        if (m41Var == null) {
            throw new NullPointerException("sources == null");
        }
        this.f7423a = o41Var;
        this.b = r41Var;
        this.c = l41Var;
        this.d = m41Var;
    }

    public final String a(String str) {
        StringBuilder sb = new StringBuilder(80);
        sb.append(this.b);
        sb.append(": ");
        sb.append(this.f7423a.c());
        if (str != null) {
            sb.append("(");
            sb.append(str);
            sb.append(")");
        }
        if (this.c == null) {
            sb.append(" .");
        } else {
            sb.append(Operators.SPACE_STR);
            sb.append(this.c.toHuman());
        }
        sb.append(" <-");
        int size = this.d.size();
        if (size == 0) {
            sb.append(" .");
        } else {
            for (int i = 0; i < size; i++) {
                sb.append(Operators.SPACE_STR);
                sb.append(this.d.d(i).toHuman());
            }
        }
        return sb.toString();
    }

    public abstract void a(b bVar);

    public final String b(String str) {
        StringBuilder sb = new StringBuilder(80);
        sb.append("Insn{");
        sb.append(this.b);
        sb.append(' ');
        sb.append(this.f7423a);
        if (str != null) {
            sb.append(' ');
            sb.append(str);
        }
        sb.append(" :: ");
        l41 l41Var = this.c;
        if (l41Var != null) {
            sb.append(l41Var);
            sb.append(" <- ");
        }
        sb.append(this.d);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public final boolean c() {
        return this.f7423a.a();
    }

    public abstract d61 d();

    public String e() {
        return null;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final o41 f() {
        return this.f7423a;
    }

    public final r41 g() {
        return this.b;
    }

    public final l41 h() {
        return this.c;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final m41 i() {
        return this.d;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return a(e());
    }

    public String toString() {
        return b(e());
    }
}
