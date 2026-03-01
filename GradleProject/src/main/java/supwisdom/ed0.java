package supwisdom;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ed0<L> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f7476a;
    public final String b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ed0)) {
            return false;
        }
        ed0 ed0Var = (ed0) obj;
        return this.f7476a == ed0Var.f7476a && this.b.equals(ed0Var.b);
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f7476a) * 31) + this.b.hashCode();
    }
}
