package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import org.bouncycastle.asn1.util.ASN1Dump;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class g11 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final x31 f7677e;
    public final f11[] f;

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        o21 o21VarA = t11Var.a();
        int length = this.f.length;
        for (int i = 0; i < length; i++) {
            f11[] f11VarArr = this.f;
            f11VarArr[i] = (f11) o21VarA.b(f11VarArr[i]);
        }
    }

    @Override // supwisdom.p21
    public int b(p21 p21Var) {
        return this.f7677e.compareTo(((g11) p21Var).f7677e);
    }

    @Override // supwisdom.p21
    public String g() {
        return this.f7677e.toString();
    }

    public int hashCode() {
        return this.f7677e.hashCode();
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        f11.a(this.f);
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_ANNOTATION_SET_ITEM;
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        boolean zE = h61Var.e();
        int length = this.f.length;
        if (zE) {
            h61Var.a(0, f() + " annotation set");
            h61Var.a(4, "  size: " + m61.g(length));
        }
        h61Var.writeInt(length);
        for (int i = 0; i < length; i++) {
            int iD = this.f[i].d();
            if (zE) {
                h61Var.a(4, "  entries[" + Integer.toHexString(i) + "]: " + m61.g(iD));
                this.f[i].a(h61Var, ASN1Dump.TAB);
            }
            h61Var.writeInt(iD);
        }
    }
}
