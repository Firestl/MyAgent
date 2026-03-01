package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class w21 extends e21 implements Comparable {
    public final v51 b;
    public v21 c;

    public w21(v51 v51Var) {
        if (v51Var == null) {
            throw new NullPointerException("value == null");
        }
        this.b = v51Var;
        this.c = null;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        if (this.c == null) {
            o21 o21VarN = t11Var.n();
            v21 v21Var = new v21(this.b);
            this.c = v21Var;
            o21VarN.a((p21) v21Var);
        }
    }

    @Override // supwisdom.f21
    public int c() {
        return 4;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return this.b.compareTo(((w21) obj).b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof w21) {
            return this.b.equals(((w21) obj).b);
        }
        return false;
    }

    public v51 g() {
        return this.b;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_STRING_ID_ITEM;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var, h61 h61Var) {
        int iD = this.c.d();
        if (h61Var.e()) {
            h61Var.a(0, f() + ' ' + this.b.a(100));
            StringBuilder sb = new StringBuilder();
            sb.append("  string_data_off: ");
            sb.append(m61.g(iD));
            h61Var.a(4, sb.toString());
        }
        h61Var.writeInt(iD);
    }
}
