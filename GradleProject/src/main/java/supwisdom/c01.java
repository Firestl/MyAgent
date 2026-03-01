package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c01 extends kz0 {
    public c01(r41 r41Var, m41 m41Var) {
        super(nz0.b, r41Var, m41Var);
    }

    @Override // supwisdom.kz0
    public final kz0 a(mz0 mz0Var) {
        throw new RuntimeException("unsupported");
    }

    @Override // supwisdom.kz0
    public final kz0 b(int i) {
        return a(j().e(i));
    }
}
