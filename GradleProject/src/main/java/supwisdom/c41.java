package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c41 extends e41 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u41 f7144e;

    public c41(o41 o41Var, r41 r41Var, l41 l41Var, m41 m41Var, u41 u41Var) {
        super(o41Var, r41Var, l41Var, m41Var);
        if (u41Var == null) {
            throw new NullPointerException("cst == null");
        }
        this.f7144e = u41Var;
    }

    @Override // supwisdom.e41
    public String e() {
        return this.f7144e.toHuman();
    }

    public u41 j() {
        return this.f7144e;
    }
}
