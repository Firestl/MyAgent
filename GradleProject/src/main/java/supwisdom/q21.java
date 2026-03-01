package supwisdom;

import org.bouncycastle.asn1.util.ASN1Dump;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class q21 implements t61, Comparable<q21> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r51 f8866a;
    public final c31<h11> b;

    public void a(t11 t11Var) {
        n21 n21VarK = t11Var.k();
        o21 o21VarR = t11Var.r();
        n21VarK.b(this.f8866a);
        o21VarR.a((p21) this.b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof q21) {
            return this.f8866a.equals(((q21) obj).f8866a);
        }
        return false;
    }

    public int hashCode() {
        return this.f8866a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8866a.toHuman());
        sb.append(": ");
        boolean z = true;
        for (T t : this.b.h()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(t.g());
        }
        return sb.toString();
    }

    public void a(t11 t11Var, h61 h61Var) {
        int iA = t11Var.k().a((x41) this.f8866a);
        int iD = this.b.d();
        if (h61Var.e()) {
            h61Var.a(0, ASN1Dump.TAB + this.f8866a.toHuman());
            h61Var.a(4, "      method_idx:      " + m61.g(iA));
            h61Var.a(4, "      annotations_off: " + m61.g(iD));
        }
        h61Var.writeInt(iA);
        h61Var.writeInt(iD);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(q21 q21Var) {
        return this.f8866a.compareTo(q21Var.f8866a);
    }
}
