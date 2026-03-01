package supwisdom;

/* JADX INFO: compiled from: DefaultLoadControl.java */
/* JADX INFO: loaded from: classes.dex */
public final class f20 implements f90 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v70 f7560a;
    public final long b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f7561e;
    public final r80 f;
    public int g;
    public boolean h;

    public f20() {
        this(new v70(true, 65536));
    }

    @Override // supwisdom.f90
    public void a() {
        a(false);
    }

    @Override // supwisdom.f90
    public void b() {
        a(true);
    }

    @Override // supwisdom.f90
    public void c() {
        a(true);
    }

    @Override // supwisdom.f90
    public q70 d() {
        return this.f7560a;
    }

    public f20(v70 v70Var) {
        this(v70Var, 15000, 30000, 2500L, 5000L);
    }

    @Override // supwisdom.f90
    public void a(h90[] h90VarArr, qb0 qb0Var, l70 l70Var) {
        this.g = 0;
        for (int i = 0; i < h90VarArr.length; i++) {
            if (l70Var.a(i) != null) {
                this.g += x80.c(h90VarArr[i].a());
            }
        }
        this.f7560a.a(this.g);
    }

    public final int b(long j) {
        if (j > this.c) {
            return 0;
        }
        return j < this.b ? 2 : 1;
    }

    public f20(v70 v70Var, int i, int i2, long j, long j2) {
        this(v70Var, i, i2, j, j2, null);
    }

    public f20(v70 v70Var, int i, int i2, long j, long j2, r80 r80Var) {
        this.f7560a = v70Var;
        this.b = ((long) i) * 1000;
        this.c = ((long) i2) * 1000;
        this.d = j * 1000;
        this.f7561e = j2 * 1000;
        this.f = r80Var;
    }

    @Override // supwisdom.f90
    public boolean a(long j, boolean z) {
        long j2 = z ? this.f7561e : this.d;
        return j2 <= 0 || j >= j2;
    }

    @Override // supwisdom.f90
    public boolean a(long j) {
        int iB = b(j);
        boolean z = true;
        boolean z2 = this.f7560a.e() >= this.g;
        boolean z3 = this.h;
        if (iB != 2 && (iB != 1 || !z3 || z2)) {
            z = false;
        }
        this.h = z;
        r80 r80Var = this.f;
        if (r80Var == null || z == z3) {
            return this.h;
        }
        if (z) {
            r80Var.a(0);
            throw null;
        }
        r80Var.b(0);
        throw null;
    }

    public final void a(boolean z) {
        this.g = 0;
        r80 r80Var = this.f;
        if (r80Var != null && this.h) {
            r80Var.b(0);
            throw null;
        }
        this.h = false;
        if (z) {
            this.f7560a.d();
        }
    }
}
