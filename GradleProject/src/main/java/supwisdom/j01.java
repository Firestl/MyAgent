package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class j01 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f8007a = new j01();

    @Override // supwisdom.qz0
    public int a() {
        return 2;
    }

    @Override // supwisdom.qz0
    public boolean a(b01 b01Var) {
        int iP = b01Var.p();
        return iP != 0 && qz0.c(iP);
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        return qz0.e(kz0Var);
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        if (!(kz0Var instanceof b01) || kz0Var.j().size() != 0) {
            return false;
        }
        b01 b01Var = (b01) kz0Var;
        if (b01Var.q()) {
            return a(b01Var);
        }
        return true;
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return qz0.d(kz0Var);
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        qz0.a(h61Var, qz0.a(kz0Var, 0), (short) ((b01) kz0Var).p());
    }
}
