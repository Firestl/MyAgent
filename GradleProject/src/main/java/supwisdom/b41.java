package supwisdom;

import supwisdom.e41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class b41 extends q61 {
    public int d;

    /* JADX INFO: compiled from: Proguard */
    public static class a implements e41.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7021a = 0;

        public int a() {
            return this.f7021a;
        }

        public final void a(l41 l41Var) {
            int iE = l41Var.e();
            if (iE > this.f7021a) {
                this.f7021a = iE;
            }
        }

        public final void a(e41 e41Var) {
            l41 l41VarH = e41Var.h();
            if (l41VarH != null) {
                a(l41VarH);
            }
            m41 m41VarI = e41Var.i();
            int size = m41VarI.size();
            for (int i = 0; i < size; i++) {
                a(m41VarI.d(i));
            }
        }

        @Override // supwisdom.e41.b
        public void a(i41 i41Var) {
            a((e41) i41Var);
        }

        @Override // supwisdom.e41.b
        public void a(j41 j41Var) {
            a((e41) j41Var);
        }

        @Override // supwisdom.e41.b
        public void a(s41 s41Var) {
            a((e41) s41Var);
        }

        @Override // supwisdom.e41.b
        public void a(t41 t41Var) {
            a((e41) t41Var);
        }
    }

    public b41(int i) {
        super(i);
        this.d = -1;
    }

    public void a(e41.b bVar) {
        int size = size();
        for (int i = 0; i < size; i++) {
            f(i).c().a(bVar);
        }
    }

    public a41 f(int i) {
        return (a41) a(i);
    }

    public a41 g(int i) {
        int iD = d(i);
        if (iD >= 0) {
            return f(iD);
        }
        throw new IllegalArgumentException("no such label: " + m61.d(i));
    }

    public int i() {
        int size = size();
        int size2 = 0;
        for (int i = 0; i < size; i++) {
            a41 a41Var = (a41) b(i);
            if (a41Var != null) {
                size2 += a41Var.c().size();
            }
        }
        return size2;
    }

    public int j() {
        if (this.d == -1) {
            a aVar = new a();
            a(aVar);
            this.d = aVar.a();
        }
        return this.d;
    }

    public a41 a(a41 a41Var) {
        int iE = a41Var.e();
        o61 o61VarG = a41Var.g();
        int size = o61VarG.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return g(o61VarG.d(0));
        }
        if (iE != -1) {
            return g(iE);
        }
        return g(o61VarG.d(0));
    }

    public void a(int i, a41 a41Var) {
        super.a(i, (p61) a41Var);
        this.d = -1;
    }
}
