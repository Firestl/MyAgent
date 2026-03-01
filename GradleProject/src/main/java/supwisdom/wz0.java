package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class wz0 extends l61 {
    public static final wz0 c = new wz0(0);

    /* JADX INFO: compiled from: Proguard */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9685a;
        public final r41 b;

        public a(int i, r41 r41Var) {
            if (i < 0) {
                throw new IllegalArgumentException("address < 0");
            }
            if (r41Var == null) {
                throw new NullPointerException("position == null");
            }
            this.f9685a = i;
            this.b = r41Var;
        }

        public int a() {
            return this.f9685a;
        }

        public r41 b() {
            return this.b;
        }
    }

    public wz0(int i) {
        super(i);
    }

    public static wz0 a(lz0 lz0Var, int i) {
        if (i == 1) {
            return c;
        }
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException("bogus howMuch");
        }
        r41 r41Var = r41.d;
        int size = lz0Var.size();
        a[] aVarArr = new a[size];
        r41 r41Var2 = r41Var;
        int i2 = 0;
        boolean z = false;
        for (int i3 = 0; i3 < size; i3++) {
            kz0 kz0VarD = lz0Var.d(i3);
            if (kz0VarD instanceof hz0) {
                z = true;
            } else {
                r41 r41VarI = kz0VarD.i();
                if (!r41VarI.equals(r41Var) && !r41VarI.a(r41Var2) && (i != 3 || z)) {
                    aVarArr[i2] = new a(kz0VarD.e(), r41VarI);
                    i2++;
                    r41Var2 = r41VarI;
                    z = false;
                }
            }
        }
        wz0 wz0Var = new wz0(i2);
        for (int i4 = 0; i4 < i2; i4++) {
            wz0Var.a(i4, aVarArr[i4]);
        }
        wz0Var.e();
        return wz0Var;
    }

    public a d(int i) {
        return (a) a(i);
    }

    public void a(int i, a aVar) {
        a(i, (Object) aVar);
    }
}
