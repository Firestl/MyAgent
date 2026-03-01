package supwisdom;

import org.bouncycastle.asn1.util.ASN1Dump;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class y11 implements t61, Comparable<y11> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f51 f9837a;
    public g11 b;

    public void a(t11 t11Var) {
        a21 a21VarE = t11Var.e();
        o21 o21VarR = t11Var.r();
        a21VarE.b(this.f9837a);
        this.b = (g11) o21VarR.b(this.b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof y11) {
            return this.f9837a.equals(((y11) obj).f9837a);
        }
        return false;
    }

    public int hashCode() {
        return this.f9837a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.f9837a.toHuman() + ": " + this.b;
    }

    public void a(t11 t11Var, h61 h61Var) {
        int iA = t11Var.e().a(this.f9837a);
        int iD = this.b.d();
        if (h61Var.e()) {
            h61Var.a(0, ASN1Dump.TAB + this.f9837a.toHuman());
            h61Var.a(4, "      field_idx:       " + m61.g(iA));
            h61Var.a(4, "      annotations_off: " + m61.g(iD));
        }
        h61Var.writeInt(iA);
        h61Var.writeInt(iD);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(y11 y11Var) {
        return this.f9837a.compareTo(y11Var.f9837a);
    }
}
