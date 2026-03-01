package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class b01 extends oz0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public hz0 f7001e;

    public b01(mz0 mz0Var, r41 r41Var, m41 m41Var, hz0 hz0Var) {
        super(mz0Var, r41Var, m41Var);
        if (hz0Var == null) {
            throw new NullPointerException("target == null");
        }
        this.f7001e = hz0Var;
    }

    @Override // supwisdom.kz0
    public String a() {
        hz0 hz0Var = this.f7001e;
        return hz0Var == null ? "????" : hz0Var.m();
    }

    public hz0 n() {
        return this.f7001e;
    }

    public int o() {
        return this.f7001e.e();
    }

    public int p() {
        return this.f7001e.e() - e();
    }

    public boolean q() {
        return k() && this.f7001e.k();
    }

    public b01 a(hz0 hz0Var) {
        return new b01(h().f(), i(), j(), hz0Var);
    }

    @Override // supwisdom.kz0
    public kz0 a(mz0 mz0Var) {
        return new b01(mz0Var, i(), j(), this.f7001e);
    }

    @Override // supwisdom.kz0
    public kz0 a(m41 m41Var) {
        return new b01(h(), i(), m41Var, this.f7001e);
    }
}
