package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class v21 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final v51 f9473e;

    public v21(v51 v51Var) {
        super(1, a(v51Var));
        this.f9473e = v51Var;
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_STRING_DATA_ITEM;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
    }

    @Override // supwisdom.p21
    public int b(p21 p21Var) {
        return this.f9473e.compareTo(((v21) p21Var).f9473e);
    }

    @Override // supwisdom.p21
    public String g() {
        return this.f9473e.h();
    }

    public static int a(v51 v51Var) {
        return py0.a(v51Var.f()) + v51Var.g() + 1;
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        j61 j61VarD = this.f9473e.d();
        int iF = this.f9473e.f();
        if (h61Var.e()) {
            h61Var.a(py0.a(iF), "utf16_size: " + m61.g(iF));
            h61Var.a(j61VarD.a() + 1, this.f9473e.h());
        }
        h61Var.c(iF);
        h61Var.a(j61VarD);
        h61Var.writeByte(0);
    }
}
