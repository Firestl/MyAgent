package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class p51 extends x51 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w51 f8773a;
    public final s51 b;

    public p51(w51 w51Var, s51 s51Var) {
        if (w51Var == null) {
            throw new NullPointerException("definingClass == null");
        }
        if (s51Var == null) {
            throw new NullPointerException("nat == null");
        }
        this.f8773a = w51Var;
        this.b = s51Var;
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        p51 p51Var = (p51) u41Var;
        int iCompareTo = this.f8773a.compareTo((u41) p51Var.f8773a);
        return iCompareTo != 0 ? iCompareTo : this.b.f().compareTo(p51Var.b.f());
    }

    public final w51 d() {
        return this.f8773a;
    }

    public final s51 e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        p51 p51Var = (p51) obj;
        return this.f8773a.equals(p51Var.f8773a) && this.b.equals(p51Var.b);
    }

    public final int hashCode() {
        return (this.f8773a.hashCode() * 31) ^ this.b.hashCode();
    }

    @Override // supwisdom.t61
    public final String toHuman() {
        return this.f8773a.toHuman() + '.' + this.b.toHuman();
    }

    public final String toString() {
        return c() + Operators.BLOCK_START + toHuman() + Operators.BLOCK_END;
    }
}
