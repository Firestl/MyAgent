package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class sz0 extends d01 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l41 f9221e;

    public sz0(r41 r41Var, l41 l41Var) {
        super(r41Var);
        if (l41Var == null) {
            throw new NullPointerException("local == null");
        }
        this.f9221e = l41Var;
    }

    @Override // supwisdom.kz0
    public String a() {
        return this.f9221e.toString();
    }

    @Override // supwisdom.d01, supwisdom.kz0
    public kz0 b(int i) {
        return new sz0(i(), this.f9221e.a(i));
    }

    public l41 n() {
        return this.f9221e;
    }

    @Override // supwisdom.kz0
    public String a(boolean z) {
        new StringBuilder().append("local-start ");
        a(this.f9221e);
        throw null;
    }

    public static String a(l41 l41Var) {
        StringBuilder sb = new StringBuilder();
        sb.append(l41Var.i());
        sb.append(' ');
        l41Var.d().toString();
        throw null;
    }

    @Override // supwisdom.kz0
    public kz0 a(f61 f61Var) {
        return new sz0(i(), f61Var.a(this.f9221e));
    }

    @Override // supwisdom.kz0
    public kz0 a(m41 m41Var) {
        return new sz0(i(), this.f9221e);
    }
}
