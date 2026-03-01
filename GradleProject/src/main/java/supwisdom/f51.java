package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class f51 extends p51 {
    public f51(w51 w51Var, s51 s51Var) {
        super(w51Var, s51Var);
    }

    @Override // supwisdom.p51, supwisdom.u41
    public int b(u41 u41Var) {
        int iB = super.b(u41Var);
        return iB != 0 ? iB : e().d().compareTo(((f51) u41Var).e().d());
    }

    @Override // supwisdom.u41
    public String c() {
        return "field";
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return e().e();
    }
}
