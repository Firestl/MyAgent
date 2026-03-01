package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class v41 extends u41 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w31 f9479a;

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        return this.f9479a.compareTo(((v41) u41Var).f9479a);
    }

    @Override // supwisdom.u41
    public String c() {
        return "annotation";
    }

    public w31 d() {
        return this.f9479a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof v41) {
            return this.f9479a.equals(((v41) obj).f9479a);
        }
        return false;
    }

    public int hashCode() {
        return this.f9479a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.f9479a.toString();
    }

    public String toString() {
        return this.f9479a.toString();
    }
}
