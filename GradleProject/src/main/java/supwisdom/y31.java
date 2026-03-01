package supwisdom;

import com.xiaomi.mipush.sdk.Constants;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class y31 implements Comparable<y31> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v51 f9843a;
    public final u41 b;

    public v51 a() {
        return this.f9843a;
    }

    public u41 b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof y31)) {
            return false;
        }
        y31 y31Var = (y31) obj;
        return this.f9843a.equals(y31Var.f9843a) && this.b.equals(y31Var.b);
    }

    public int hashCode() {
        return (this.f9843a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return this.f9843a.toHuman() + Constants.COLON_SEPARATOR + this.b;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(y31 y31Var) {
        int iCompareTo = this.f9843a.compareTo(y31Var.f9843a);
        return iCompareTo != 0 ? iCompareTo : this.b.compareTo(y31Var.b);
    }
}
