package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class h11 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g11 f7789e;

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        this.f7789e = (g11) t11Var.r().b(this.f7789e);
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        int iD = this.f7789e.d();
        if (h61Var.e()) {
            h61Var.a(4, "  annotations_off: " + m61.g(iD));
        }
        h61Var.writeInt(iD);
    }

    @Override // supwisdom.p21
    public String g() {
        return this.f7789e.g();
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_ANNOTATION_SET_REF_ITEM;
    }
}
