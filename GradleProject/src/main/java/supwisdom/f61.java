package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class f61 {
    public abstract int a();

    public abstract l41 a(l41 l41Var);

    public final m41 a(m41 m41Var) {
        int size = m41Var.size();
        m41 m41Var2 = new m41(size);
        for (int i = 0; i < size; i++) {
            m41Var2.a(i, a(m41Var.d(i)));
        }
        m41Var2.e();
        return m41Var2.equals(m41Var) ? m41Var : m41Var2;
    }

    public final n41 a(n41 n41Var) {
        int iH = n41Var.h();
        n41 n41Var2 = new n41(a());
        for (int i = 0; i < iH; i++) {
            l41 l41VarA = n41Var.a(i);
            if (l41VarA != null) {
                n41Var2.b(a(l41VarA));
            }
        }
        n41Var2.e();
        return n41Var2.equals(n41Var) ? n41Var : n41Var2;
    }
}
