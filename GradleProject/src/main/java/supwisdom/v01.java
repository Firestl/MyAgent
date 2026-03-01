package supwisdom;

import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class v01 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f9463a = new v01();

    @Override // supwisdom.qz0
    public int a() {
        return 3;
    }

    @Override // supwisdom.qz0
    public BitSet a(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        int size = m41VarJ.size();
        BitSet bitSet = new BitSet(size);
        boolean zD = qz0.d(m41VarJ.d(0).f());
        if (size == 1) {
            bitSet.set(0, zD);
        } else if (m41VarJ.d(0).f() == m41VarJ.d(1).f()) {
            bitSet.set(0, zD);
            bitSet.set(1, zD);
        }
        return bitSet;
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        return kz0Var.j().d(0).i() + ", " + kz0Var.d();
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        l41 l41VarD;
        if (!(kz0Var instanceof iz0)) {
            return false;
        }
        m41 m41VarJ = kz0Var.j();
        int size = m41VarJ.size();
        if (size == 1) {
            l41VarD = m41VarJ.d(0);
        } else {
            if (size != 2) {
                return false;
            }
            l41VarD = m41VarJ.d(0);
            if (l41VarD.f() != m41VarJ.d(1).f()) {
                return false;
            }
        }
        if (!qz0.d(l41VarD.f())) {
            return false;
        }
        u41 u41VarN = ((iz0) kz0Var).n();
        return (u41VarN instanceof w51) || (u41VarN instanceof f51) || (u41VarN instanceof v51);
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return z ? kz0Var.c() : "";
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        qz0.a(h61Var, qz0.a(kz0Var, m41VarJ.d(0).f()), ((iz0) kz0Var).o());
    }
}
