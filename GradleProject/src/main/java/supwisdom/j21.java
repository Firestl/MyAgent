package supwisdom;

import org.bouncycastle.asn1.util.ASN1Dump;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class j21 implements t61, Comparable<j21> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r51 f8012a;
    public g11 b;

    public void a(t11 t11Var) {
        n21 n21VarK = t11Var.k();
        o21 o21VarR = t11Var.r();
        n21VarK.b(this.f8012a);
        this.b = (g11) o21VarR.b(this.b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof j21) {
            return this.f8012a.equals(((j21) obj).f8012a);
        }
        return false;
    }

    public int hashCode() {
        return this.f8012a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.f8012a.toHuman() + ": " + this.b;
    }

    public void a(t11 t11Var, h61 h61Var) {
        int iA = t11Var.k().a((x41) this.f8012a);
        int iD = this.b.d();
        if (h61Var.e()) {
            h61Var.a(0, ASN1Dump.TAB + this.f8012a.toHuman());
            h61Var.a(4, "      method_idx:      " + m61.g(iA));
            h61Var.a(4, "      annotations_off: " + m61.g(iD));
        }
        h61Var.writeInt(iA);
        h61Var.writeInt(iD);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(j21 j21Var) {
        return this.f8012a.compareTo(j21Var.f8012a);
    }
}
