package supwisdom;

/* JADX INFO: compiled from: TrackSelectorResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class n70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final qb0 f8481a;
    public final l70 b;
    public final Object c;
    public final j90[] d;

    public n70(qb0 qb0Var, l70 l70Var, Object obj, j90[] j90VarArr) {
        this.f8481a = qb0Var;
        this.b = l70Var;
        this.c = obj;
        this.d = j90VarArr;
    }

    public boolean a(n70 n70Var) {
        if (n70Var == null) {
            return false;
        }
        for (int i = 0; i < this.b.f8261a; i++) {
            if (!a(n70Var, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean a(n70 n70Var, int i) {
        return n70Var != null && x80.a(this.b.a(i), n70Var.b.a(i)) && x80.a(this.d[i], n70Var.d[i]);
    }
}
