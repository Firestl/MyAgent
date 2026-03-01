package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class z11 extends h21 {
    public z11(f51 f51Var) {
        super(f51Var);
    }

    @Override // supwisdom.h21, supwisdom.d21, supwisdom.f21
    public void a(t11 t11Var) {
        super.a(t11Var);
        t11Var.p().b(j().getType());
    }

    @Override // supwisdom.h21
    public int b(t11 t11Var) {
        return t11Var.p().a(j().getType());
    }

    @Override // supwisdom.h21
    public String i() {
        return "type_idx";
    }

    public f51 j() {
        return (f51) h();
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_FIELD_ID_ITEM;
    }
}
