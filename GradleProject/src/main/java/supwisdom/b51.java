package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class b51 extends u41 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j51 f7025a;
    public final int b;

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        b51 b51Var = (b51) u41Var;
        int iCompareTo = this.f7025a.compareTo(b51Var.f7025a);
        return iCompareTo != 0 ? iCompareTo : Integer.compare(this.b, b51Var.b);
    }

    @Override // supwisdom.u41
    public String c() {
        return "CallSiteRef";
    }

    public a51 d() {
        this.f7025a.d();
        throw null;
    }

    public z51 e() {
        this.f7025a.e();
        throw null;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        d();
        throw null;
    }

    public String toString() {
        d();
        throw null;
    }
}
