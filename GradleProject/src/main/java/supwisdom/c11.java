package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class c11 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f7135a = new c11();

    @Override // supwisdom.qz0
    public int a() {
        return 4;
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return z ? kz0Var.c() : "";
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        return qz0.c(kz0Var.j()) + ", " + kz0Var.d();
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        if (!(kz0Var instanceof tz0)) {
            return false;
        }
        tz0 tz0Var = (tz0) kz0Var;
        int iD = tz0Var.d(0);
        int iD2 = tz0Var.d(1);
        if (!qz0.f(iD) || !qz0.f(iD2) || !(tz0Var.c(0) instanceof r51) || !(tz0Var.c(1) instanceof t51)) {
            return false;
        }
        m41 m41VarJ = tz0Var.j();
        int size = m41VarJ.size();
        if (size == 0) {
            return true;
        }
        return qz0.d(m41VarJ.h()) && qz0.f(size) && qz0.f(m41VarJ.d(0).f()) && qz0.a(m41VarJ);
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        tz0 tz0Var = (tz0) kz0Var;
        short sD = (short) tz0Var.d(0);
        short sD2 = (short) tz0Var.d(1);
        m41 m41VarJ = kz0Var.j();
        qz0.a(h61Var, qz0.a(kz0Var, m41VarJ.h()), sD, m41VarJ.size() > 0 ? (short) m41VarJ.d(0).f() : (short) 0, sD2);
    }
}
