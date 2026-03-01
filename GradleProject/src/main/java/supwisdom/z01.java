package supwisdom;

import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class z01 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f9947a = new z01();

    public static m41 d(m41 m41Var) {
        int iE = e(m41Var);
        int size = m41Var.size();
        if (iE == size) {
            return m41Var;
        }
        m41 m41Var2 = new m41(iE);
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            l41 l41VarD = m41Var.d(i2);
            m41Var2.a(i, l41VarD);
            if (l41VarD.c() == 2) {
                m41Var2.a(i + 1, l41.a(l41VarD.f() + 1, b61.o));
                i += 2;
            } else {
                i++;
            }
        }
        m41Var2.e();
        return m41Var2;
    }

    public static int e(m41 m41Var) {
        int size = m41Var.size();
        if (size > 5) {
            return -1;
        }
        int iC = 0;
        for (int i = 0; i < size; i++) {
            iC += m41Var.d(i).c();
            if (!qz0.e((r5.f() + r5.c()) - 1)) {
                return -1;
            }
        }
        if (iC <= 5) {
            return iC;
        }
        return -1;
    }

    @Override // supwisdom.qz0
    public int a() {
        return 3;
    }

    @Override // supwisdom.qz0
    public BitSet a(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        int size = m41VarJ.size();
        BitSet bitSet = new BitSet(size);
        for (int i = 0; i < size; i++) {
            l41 l41VarD = m41VarJ.d(i);
            bitSet.set(i, qz0.e((l41VarD.f() + l41VarD.c()) - 1));
        }
        return bitSet;
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        return qz0.b(d(kz0Var.j())) + ", " + kz0Var.d();
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        if (!(kz0Var instanceof iz0)) {
            return false;
        }
        iz0 iz0Var = (iz0) kz0Var;
        if (!qz0.f(iz0Var.o())) {
            return false;
        }
        u41 u41VarN = iz0Var.n();
        return ((u41VarN instanceof r51) || (u41VarN instanceof w51) || (u41VarN instanceof b51)) && e(iz0Var.j()) >= 0;
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return z ? kz0Var.c() : "";
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        int iO = ((iz0) kz0Var).o();
        m41 m41VarD = d(kz0Var.j());
        int size = m41VarD.size();
        qz0.a(h61Var, qz0.a(kz0Var, qz0.b(size > 4 ? m41VarD.d(4).f() : 0, size)), (short) iO, qz0.a(size > 0 ? m41VarD.d(0).f() : 0, size > 1 ? m41VarD.d(1).f() : 0, size > 2 ? m41VarD.d(2).f() : 0, size > 3 ? m41VarD.d(3).f() : 0));
    }
}
