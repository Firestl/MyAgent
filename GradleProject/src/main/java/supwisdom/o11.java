package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import org.bouncycastle.asn1.util.ASN1Dump;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class o11 extends e21 {
    public final w51 b;
    public final int c;
    public final w51 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a31 f8600e;
    public final v51 f;
    public final n11 g;
    public u11 h;
    public i11 i;

    public o11(w51 w51Var, int i, w51 w51Var2, d61 d61Var, v51 v51Var) {
        if (w51Var == null) {
            throw new NullPointerException("thisClass == null");
        }
        if (d61Var == null) {
            throw new NullPointerException("interfaces == null");
        }
        this.b = w51Var;
        this.c = i;
        this.d = w51Var2;
        this.f8600e = d61Var.size() == 0 ? null : new a31(d61Var);
        this.f = v51Var;
        this.g = new n11(w51Var);
        this.h = null;
        this.i = new i11();
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        z21 z21VarP = t11Var.p();
        o21 o21VarA = t11Var.a();
        o21 o21VarR = t11Var.r();
        o21 o21VarQ = t11Var.q();
        x21 x21VarO = t11Var.o();
        z21VarP.b(this.b);
        if (!this.g.i()) {
            t11Var.b().a((p21) this.g);
            w41 w41VarH = this.g.h();
            if (w41VarH != null) {
                this.h = (u11) o21VarA.b(new u11(w41VarH));
            }
        }
        w51 w51Var = this.d;
        if (w51Var != null) {
            z21VarP.b(w51Var);
        }
        a31 a31Var = this.f8600e;
        if (a31Var != null) {
            this.f8600e = (a31) o21VarQ.b(a31Var);
        }
        v51 v51Var = this.f;
        if (v51Var != null) {
            x21VarO.b(v51Var);
        }
        if (this.i.h()) {
            return;
        }
        if (this.i.i()) {
            this.i = (i11) o21VarR.b(this.i);
        } else {
            o21VarR.a((p21) this.i);
        }
    }

    public void b(x11 x11Var) {
        this.g.b(x11Var);
    }

    @Override // supwisdom.f21
    public int c() {
        return 32;
    }

    public d61 g() {
        a31 a31Var = this.f8600e;
        return a31Var == null ? a61.c : a31Var.h();
    }

    public w51 h() {
        return this.d;
    }

    public w51 i() {
        return this.b;
    }

    public void a(x11 x11Var) {
        this.g.a(x11Var);
    }

    public void a(v11 v11Var) {
        this.g.a(v11Var);
    }

    public void a(v11 v11Var, u41 u41Var) {
        this.g.a(v11Var, u41Var);
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_CLASS_DEF_ITEM;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var, h61 h61Var) {
        boolean zE = h61Var.e();
        z21 z21VarP = t11Var.p();
        int iA = z21VarP.a(this.b);
        w51 w51Var = this.d;
        int iA2 = w51Var == null ? -1 : z21VarP.a(w51Var);
        int iC = p21.c(this.f8600e);
        int iD = this.i.h() ? 0 : this.i.d();
        int iA3 = this.f != null ? t11Var.o().a(this.f) : -1;
        int iD2 = this.g.i() ? 0 : this.g.d();
        int iC2 = p21.c(this.h);
        if (zE) {
            h61Var.a(0, f() + ' ' + this.b.toHuman());
            StringBuilder sb = new StringBuilder();
            sb.append("  class_idx:           ");
            sb.append(m61.g(iA));
            h61Var.a(4, sb.toString());
            h61Var.a(4, "  access_flags:        " + z31.a(this.c));
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  superclass_idx:      ");
            sb2.append(m61.g(iA2));
            sb2.append(" // ");
            w51 w51Var2 = this.d;
            sb2.append(w51Var2 == null ? "<none>" : w51Var2.toHuman());
            h61Var.a(4, sb2.toString());
            h61Var.a(4, "  interfaces_off:      " + m61.g(iC));
            if (iC != 0) {
                d61 d61VarH = this.f8600e.h();
                int size = d61VarH.size();
                for (int i = 0; i < size; i++) {
                    h61Var.a(0, ASN1Dump.TAB + d61VarH.getType(i).toHuman());
                }
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("  source_file_idx:     ");
            sb3.append(m61.g(iA3));
            sb3.append(" // ");
            v51 v51Var = this.f;
            sb3.append(v51Var != null ? v51Var.toHuman() : "<none>");
            h61Var.a(4, sb3.toString());
            h61Var.a(4, "  annotations_off:     " + m61.g(iD));
            h61Var.a(4, "  class_data_off:      " + m61.g(iD2));
            h61Var.a(4, "  static_values_off:   " + m61.g(iC2));
        }
        h61Var.writeInt(iA);
        h61Var.writeInt(this.c);
        h61Var.writeInt(iA2);
        h61Var.writeInt(iC);
        h61Var.writeInt(iA3);
        h61Var.writeInt(iD);
        h61Var.writeInt(iD2);
        h61Var.writeInt(iC2);
    }
}
