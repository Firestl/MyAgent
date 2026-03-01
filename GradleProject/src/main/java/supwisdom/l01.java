package supwisdom;

import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class l01 extends qz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final qz0 f8227a = new l01();

    @Override // supwisdom.qz0
    public int a() {
        return 2;
    }

    @Override // supwisdom.qz0
    public BitSet a(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, qz0.d(m41VarJ.d(0).f()));
        return bitSet;
    }

    @Override // supwisdom.qz0
    public String b(kz0 kz0Var) {
        return kz0Var.j().d(0).i() + ", " + qz0.a((n51) ((iz0) kz0Var).n());
    }

    @Override // supwisdom.qz0
    public boolean c(kz0 kz0Var) {
        m41 m41VarJ = kz0Var.j();
        if (!(kz0Var instanceof iz0) || m41VarJ.size() != 1 || !qz0.d(m41VarJ.d(0).f())) {
            return false;
        }
        u41 u41VarN = ((iz0) kz0Var).n();
        if (!(u41VarN instanceof n51)) {
            return false;
        }
        n51 n51Var = (n51) u41VarN;
        return m41VarJ.d(0).c() == 1 ? (n51Var.e() & 65535) == 0 : (n51Var.f() & 281474976710655L) == 0;
    }

    @Override // supwisdom.qz0
    public String a(kz0 kz0Var, boolean z) {
        return qz0.a((n51) ((iz0) kz0Var).n(), kz0Var.j().d(0).c() == 1 ? 32 : 64);
    }

    @Override // supwisdom.qz0
    public void a(h61 h61Var, kz0 kz0Var) {
        int iF;
        m41 m41VarJ = kz0Var.j();
        n51 n51Var = (n51) ((iz0) kz0Var).n();
        if (m41VarJ.d(0).c() == 1) {
            iF = n51Var.e() >>> 16;
        } else {
            iF = (int) (n51Var.f() >>> 48);
        }
        qz0.a(h61Var, qz0.a(kz0Var, m41VarJ.d(0).f()), (short) iF);
    }
}
