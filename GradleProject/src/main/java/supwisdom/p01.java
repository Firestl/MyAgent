package supwisdom;

import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class p01 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f8757a = new p01();

    @Override // supwisdom.qz0
    public int a() {
        return 2;
    }

    @Override // supwisdom.qz0
    public BitSet a(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, qz0.e(m41VarJ.d(0).f()));
        bitSet.set(1, qz0.e(m41VarJ.d(1).f()));
        return bitSet;
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        return m41VarJ.d(0).i() + ", " + m41VarJ.d(1).i() + ", " + kz0Var.d();
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        if (!(kz0Var instanceof iz0) || m41VarJ.size() != 2 || !qz0.e(m41VarJ.d(0).f()) || !qz0.e(m41VarJ.d(1).f())) {
            return false;
        }
        iz0 iz0Var = (iz0) kz0Var;
        if (!qz0.f(iz0Var.o())) {
            return false;
        }
        u41 u41VarN = iz0Var.n();
        return (u41VarN instanceof w51) || (u41VarN instanceof f51);
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return z ? kz0Var.c() : "";
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        qz0.a(h61Var, qz0.a(kz0Var, qz0.b(m41VarJ.d(0).f(), m41VarJ.d(1).f())), (short) ((iz0) kz0Var).o());
    }
}
