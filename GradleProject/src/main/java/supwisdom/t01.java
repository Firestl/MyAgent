package supwisdom;

import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class t01 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f9225a = new t01();

    @Override // supwisdom.qz0
    public int a() {
        return 2;
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return "";
    }

    @Override // supwisdom.qz0
    public BitSet a(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        BitSet bitSet = new BitSet(3);
        bitSet.set(0, qz0.d(m41VarJ.d(0).f()));
        bitSet.set(1, qz0.d(m41VarJ.d(1).f()));
        bitSet.set(2, qz0.d(m41VarJ.d(2).f()));
        return bitSet;
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        return m41VarJ.d(0).i() + ", " + m41VarJ.d(1).i() + ", " + m41VarJ.d(2).i();
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        return (kz0Var instanceof zz0) && m41VarJ.size() == 3 && qz0.d(m41VarJ.d(0).f()) && qz0.d(m41VarJ.d(1).f()) && qz0.d(m41VarJ.d(2).f());
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        qz0.a(h61Var, qz0.a(kz0Var, m41VarJ.d(0).f()), qz0.a(m41VarJ.d(1).f(), m41VarJ.d(2).f()));
    }
}
