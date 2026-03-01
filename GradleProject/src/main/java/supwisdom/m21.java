package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class m21 extends h21 {
    public m21(x41 x41Var) {
        super(x41Var);
    }

    @Override // supwisdom.h21, supwisdom.d21, supwisdom.f21
    public void a(t11 t11Var) {
        super.a(t11Var);
        t11Var.l().b(j().f());
    }

    @Override // supwisdom.h21
    public int b(t11 t11Var) {
        return t11Var.l().a(j().f());
    }

    @Override // supwisdom.h21
    public String i() {
        return "proto_idx";
    }

    public x41 j() {
        return (x41) h();
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_METHOD_ID_ITEM;
    }
}
