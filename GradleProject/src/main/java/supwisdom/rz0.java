package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class rz0 extends d01 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n41 f9101e;

    public rz0(r41 r41Var, n41 n41Var) {
        super(r41Var);
        if (n41Var == null) {
            throw new NullPointerException("locals == null");
        }
        this.f9101e = n41Var;
    }

    @Override // supwisdom.kz0
    public String a() {
        return this.f9101e.toString();
    }

    @Override // supwisdom.d01, supwisdom.kz0
    public kz0 b(int i) {
        return new rz0(i(), this.f9101e.b(i));
    }

    public n41 n() {
        return this.f9101e;
    }

    @Override // supwisdom.kz0
    public String a(boolean z) {
        int size = this.f9101e.size();
        int iH = this.f9101e.h();
        StringBuilder sb = new StringBuilder((size * 40) + 100);
        sb.append("local-snapshot");
        for (int i = 0; i < iH; i++) {
            l41 l41VarA = this.f9101e.a(i);
            if (l41VarA != null) {
                sb.append("\n  ");
                sz0.a(l41VarA);
                throw null;
            }
        }
        return sb.toString();
    }

    @Override // supwisdom.kz0
    public kz0 a(f61 f61Var) {
        return new rz0(i(), f61Var.a(this.f9101e));
    }

    @Override // supwisdom.kz0
    public kz0 a(m41 m41Var) {
        return new rz0(i(), this.f9101e);
    }
}
