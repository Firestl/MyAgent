package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class u11 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w41 f9353e;
    public byte[] f;

    public u11(w41 w41Var) {
        super(1, -1);
        if (w41Var == null) {
            throw new NullPointerException("array == null");
        }
        this.f9353e = w41Var;
        this.f = null;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        d31.a(t11Var, this.f9353e);
    }

    @Override // supwisdom.p21
    public int b(p21 p21Var) {
        return this.f9353e.compareTo(((u11) p21Var).f9353e);
    }

    @Override // supwisdom.p21
    public String g() {
        return this.f9353e.toHuman();
    }

    public int hashCode() {
        return this.f9353e.hashCode();
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_ENCODED_ARRAY_ITEM;
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        k61 k61Var = new k61();
        new d31(t21Var.b(), k61Var).a(this.f9353e, false);
        byte[] bArrH = k61Var.h();
        this.f = bArrH;
        a(bArrH.length);
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        if (h61Var.e()) {
            h61Var.a(0, f() + " encoded array");
            new d31(t11Var, h61Var).a(this.f9353e, true);
            return;
        }
        h61Var.write(this.f);
    }
}
