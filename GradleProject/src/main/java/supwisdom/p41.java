package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class p41 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b41 f8766a;
    public final int b;
    public o61[] c;
    public o61 d;

    public p41(b41 b41Var, int i) {
        if (b41Var == null) {
            throw new NullPointerException("blocks == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("firstLabel < 0");
        }
        this.f8766a = b41Var;
        this.b = i;
        this.c = null;
        this.d = null;
    }

    public final void a() {
        int iH = this.f8766a.h();
        o61[] o61VarArr = new o61[iH];
        o61 o61Var = new o61(10);
        int size = this.f8766a.size();
        for (int i = 0; i < size; i++) {
            a41 a41VarF = this.f8766a.f(i);
            int iA = a41VarF.a();
            o61 o61VarG = a41VarF.g();
            int size2 = o61VarG.size();
            if (size2 == 0) {
                o61Var.a(iA);
            } else {
                for (int i2 = 0; i2 < size2; i2++) {
                    int iD = o61VarG.d(i2);
                    o61 o61Var2 = o61VarArr[iD];
                    if (o61Var2 == null) {
                        o61Var2 = new o61(10);
                        o61VarArr[iD] = o61Var2;
                    }
                    o61Var2.a(iA);
                }
            }
        }
        for (int i3 = 0; i3 < iH; i3++) {
            o61 o61Var3 = o61VarArr[i3];
            if (o61Var3 != null) {
                o61Var3.i();
                o61Var3.e();
            }
        }
        o61Var.i();
        o61Var.e();
        int i4 = this.b;
        if (o61VarArr[i4] == null) {
            o61VarArr[i4] = o61.f8634e;
        }
        this.c = o61VarArr;
        this.d = o61Var;
    }

    public b41 b() {
        return this.f8766a;
    }

    public int c() {
        return this.b;
    }

    public o61 a(int i) {
        if (this.d == null) {
            a();
        }
        o61 o61Var = this.c[i];
        if (o61Var != null) {
            return o61Var;
        }
        throw new RuntimeException("no such block: " + m61.d(i));
    }
}
