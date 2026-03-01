package supwisdom;

import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class i01 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f7895a = new i01();

    @Override // supwisdom.qz0
    public int a() {
        return 1;
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return "";
    }

    @Override // supwisdom.qz0
    public BitSet a(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        BitSet bitSet = new BitSet(2);
        int iF = m41VarJ.d(0).f();
        int iF2 = m41VarJ.d(1).f();
        int size = m41VarJ.size();
        if (size == 2) {
            bitSet.set(0, qz0.e(iF));
            bitSet.set(1, qz0.e(iF2));
        } else {
            if (size != 3) {
                throw new AssertionError();
            }
            if (iF != iF2) {
                bitSet.set(0, false);
                bitSet.set(1, false);
            } else {
                boolean zE = qz0.e(iF2);
                bitSet.set(0, zE);
                bitSet.set(1, zE);
            }
            bitSet.set(2, qz0.e(m41VarJ.d(2).f()));
        }
        return bitSet;
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        int size = m41VarJ.size();
        return m41VarJ.d(size - 2).i() + ", " + m41VarJ.d(size - 1).i();
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        l41 l41VarD;
        l41 l41VarD2;
        if (!(kz0Var instanceof zz0)) {
            return false;
        }
        m41 m41VarJ = kz0Var.j();
        int size = m41VarJ.size();
        if (size == 2) {
            l41VarD = m41VarJ.d(0);
            l41VarD2 = m41VarJ.d(1);
        } else {
            if (size != 3) {
                return false;
            }
            l41VarD = m41VarJ.d(1);
            l41VarD2 = m41VarJ.d(2);
            if (l41VarD.f() != m41VarJ.d(0).f()) {
                return false;
            }
        }
        return qz0.e(l41VarD.f()) && qz0.e(l41VarD2.f());
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        int size = m41VarJ.size();
        qz0.a(h61Var, qz0.a(kz0Var, qz0.b(m41VarJ.d(size - 2).f(), m41VarJ.d(size - 1).f())));
    }
}
