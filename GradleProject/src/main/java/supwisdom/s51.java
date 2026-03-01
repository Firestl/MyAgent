package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class s51 extends u41 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v51 f9125a;
    public final v51 b;

    static {
        new s51(new v51("TYPE"), new v51("Ljava/lang/Class;"));
    }

    public s51(v51 v51Var, v51 v51Var2) {
        if (v51Var == null) {
            throw new NullPointerException("name == null");
        }
        if (v51Var2 == null) {
            throw new NullPointerException("descriptor == null");
        }
        this.f9125a = v51Var;
        this.b = v51Var2;
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        s51 s51Var = (s51) u41Var;
        int iCompareTo = this.f9125a.compareTo(s51Var.f9125a);
        return iCompareTo != 0 ? iCompareTo : this.b.compareTo(s51Var.b);
    }

    @Override // supwisdom.u41
    public String c() {
        return "nat";
    }

    public v51 d() {
        return this.b;
    }

    public b61 e() {
        return b61.a(this.b.e());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s51)) {
            return false;
        }
        s51 s51Var = (s51) obj;
        return this.f9125a.equals(s51Var.f9125a) && this.b.equals(s51Var.b);
    }

    public v51 f() {
        return this.f9125a;
    }

    public int hashCode() {
        return (this.f9125a.hashCode() * 31) ^ this.b.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.f9125a.toHuman() + Operators.CONDITION_IF_MIDDLE + this.b.toHuman();
    }

    public String toString() {
        return "nat{" + toHuman() + Operators.BLOCK_END;
    }
}
