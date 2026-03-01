package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class a11 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f6845a = new a11();

    @Override // supwisdom.qz0
    public int a() {
        return 3;
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
        if (!(kz0Var instanceof iz0)) {
            return false;
        }
        iz0 iz0Var = (iz0) kz0Var;
        int iO = iz0Var.o();
        u41 u41VarN = iz0Var.n();
        if (!qz0.f(iO)) {
            return false;
        }
        if (!(u41VarN instanceof r51) && !(u41VarN instanceof w51) && !(u41VarN instanceof b51)) {
            return false;
        }
        m41 m41VarJ = iz0Var.j();
        m41VarJ.size();
        return m41VarJ.size() == 0 || (qz0.a(m41VarJ) && qz0.f(m41VarJ.d(0).f()) && qz0.d(m41VarJ.h()));
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        qz0.a(h61Var, qz0.a(kz0Var, m41VarJ.h()), (short) ((iz0) kz0Var).o(), (short) (m41VarJ.size() != 0 ? m41VarJ.d(0).f() : 0));
    }
}
