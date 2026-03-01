package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class oz0 extends kz0 {
    public oz0(mz0 mz0Var, r41 r41Var, m41 m41Var) {
        super(mz0Var, r41Var, m41Var);
    }

    @Override // supwisdom.kz0
    public final String a(boolean z) {
        return h().b().b(this, z);
    }

    @Override // supwisdom.kz0
    public final int b() {
        return h().b().a();
    }

    @Override // supwisdom.kz0
    public final void a(h61 h61Var) {
        h().b().a(h61Var, this);
    }

    @Override // supwisdom.kz0
    public final kz0 b(int i) {
        return a(j().e(i));
    }
}
