package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class dz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final hz0[] f7400a;
    public final hz0[] b;
    public final hz0[] c;

    public dz0(p41 p41Var) {
        int iH = p41Var.b().h();
        this.f7400a = new hz0[iH];
        this.b = new hz0[iH];
        this.c = new hz0[iH];
        a(p41Var);
    }

    public hz0 a(a41 a41Var) {
        return this.c[a41Var.a()];
    }

    public hz0 b(a41 a41Var) {
        return this.b[a41Var.a()];
    }

    public hz0 c(a41 a41Var) {
        return this.f7400a[a41Var.a()];
    }

    public final void a(p41 p41Var) {
        b41 b41VarB = p41Var.b();
        int size = b41VarB.size();
        for (int i = 0; i < size; i++) {
            a41 a41VarF = b41VarB.f(i);
            int iA = a41VarF.a();
            this.f7400a[iA] = new hz0(a41VarF.c().d(0).g());
            r41 r41VarG = a41VarF.d().g();
            this.b[iA] = new hz0(r41VarG);
            this.c[iA] = new hz0(r41VarG);
        }
    }

    public hz0 a(int i) {
        return this.f7400a[i];
    }
}
