package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class l51 extends n51 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8252a;

    public l51(int i) {
        this.f8252a = i;
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        int i = ((l51) u41Var).f8252a;
        int i2 = this.f8252a;
        if (i2 < i) {
            return -1;
        }
        return i2 > i ? 1 : 0;
    }

    @Override // supwisdom.n51
    public final boolean d() {
        return true;
    }

    @Override // supwisdom.n51
    public final int e() {
        return this.f8252a;
    }

    public final boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass() && this.f8252a == ((l51) obj).f8252a;
    }

    @Override // supwisdom.n51
    public final long f() {
        return this.f8252a;
    }

    public final int hashCode() {
        return this.f8252a;
    }
}
