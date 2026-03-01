package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class k21 extends e21 {
    public final q51 b;

    public k21(q51 q51Var) {
        this.b = q51Var;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        t11Var.j().b(this.b);
    }

    public final int b(t11 t11Var) {
        u41 u41VarE = this.b.e();
        if (this.b.f()) {
            return t11Var.e().a((f51) u41VarE);
        }
        if (!this.b.g()) {
            throw new IllegalStateException("Unhandled invocation type");
        }
        if (u41VarE instanceof i51) {
            u41VarE = ((i51) u41VarE).h();
        }
        return t11Var.k().a((x41) u41VarE);
    }

    @Override // supwisdom.f21
    public int c() {
        return 8;
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_METHOD_HANDLE_ITEM;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var, h61 h61Var) {
        int iB = b(t11Var);
        int iD = this.b.d();
        if (h61Var.e()) {
            h61Var.a(0, f() + ' ' + this.b.toString());
            h61Var.a(2, "type:     " + m61.d(iD) + (" // " + q51.a(iD)));
            h61Var.a(2, "reserved: " + m61.d(0));
            String str = " // " + this.b.e().toString();
            if (this.b.f()) {
                h61Var.a(2, "fieldId:  " + m61.d(iB) + str);
            } else {
                h61Var.a(2, "methodId: " + m61.d(iB) + str);
            }
            h61Var.a(2, "reserved: " + m61.d(0));
        }
        h61Var.writeShort(iD);
        h61Var.writeShort(0);
        h61Var.writeShort(b(t11Var));
        h61Var.writeShort(0);
    }
}
