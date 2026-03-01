package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class m51 extends n51 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f8361a;

    public m51(long j) {
        this.f8361a = j;
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        long j = ((m51) u41Var).f8361a;
        long j2 = this.f8361a;
        if (j2 < j) {
            return -1;
        }
        return j2 > j ? 1 : 0;
    }

    @Override // supwisdom.n51
    public final boolean d() {
        long j = this.f8361a;
        return ((long) ((int) j)) == j;
    }

    @Override // supwisdom.n51
    public final int e() {
        return (int) this.f8361a;
    }

    public final boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass() && this.f8361a == ((m51) obj).f8361a;
    }

    @Override // supwisdom.n51
    public final long f() {
        return this.f8361a;
    }

    public final int hashCode() {
        long j = this.f8361a;
        return ((int) j) ^ ((int) (j >> 32));
    }
}
