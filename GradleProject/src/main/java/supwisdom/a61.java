package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class a61 extends l61 implements d61 {
    public static final a61 A;
    public static final a61 B;
    public static final a61 C;
    public static final a61 D;
    public static final a61 E;
    public static final a61 F;
    public static final a61 G;
    public static final a61 H;
    public static final a61 I;
    public static final a61 J;
    public static final a61 K;
    public static final a61 L;
    public static final a61 c = new a61(0);
    public static final a61 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a61 f6871e;
    public static final a61 f;
    public static final a61 g;
    public static final a61 h;
    public static final a61 i;
    public static final a61 j;
    public static final a61 k;
    public static final a61 l;
    public static final a61 m;
    public static final a61 n;
    public static final a61 o;
    public static final a61 p;
    public static final a61 q;
    public static final a61 r;
    public static final a61 s;
    public static final a61 t;
    public static final a61 u;
    public static final a61 v;
    public static final a61 w;
    public static final a61 x;
    public static final a61 y;
    public static final a61 z;

    static {
        b61 b61Var = b61.l;
        d = b(b61Var);
        b61 b61Var2 = b61.m;
        f6871e = b(b61Var2);
        b61 b61Var3 = b61.k;
        f = b(b61Var3);
        b61 b61Var4 = b61.j;
        g = b(b61Var4);
        b61 b61Var5 = b61.x;
        h = b(b61Var5);
        i = b(b61.q);
        j = b(b61.A);
        k = a(b61Var, b61Var);
        l = a(b61Var2, b61Var2);
        m = a(b61Var3, b61Var3);
        n = a(b61Var4, b61Var4);
        o = a(b61Var5, b61Var5);
        p = a(b61Var, b61Var5);
        q = a(b61Var2, b61Var5);
        r = a(b61Var3, b61Var5);
        s = a(b61Var4, b61Var5);
        t = a(b61Var2, b61Var);
        b61 b61Var6 = b61.P;
        u = a(b61Var6, b61Var);
        b61 b61Var7 = b61.Q;
        v = a(b61Var7, b61Var);
        b61 b61Var8 = b61.O;
        w = a(b61Var8, b61Var);
        b61 b61Var9 = b61.N;
        x = a(b61Var9, b61Var);
        b61 b61Var10 = b61.R;
        y = a(b61Var10, b61Var);
        b61 b61Var11 = b61.K;
        z = a(b61Var11, b61Var);
        b61 b61Var12 = b61.L;
        A = a(b61Var12, b61Var);
        b61 b61Var13 = b61.M;
        B = a(b61Var13, b61Var);
        b61 b61Var14 = b61.S;
        C = a(b61Var14, b61Var);
        D = a(b61Var, b61Var6, b61Var);
        E = a(b61Var2, b61Var7, b61Var);
        F = a(b61Var3, b61Var8, b61Var);
        G = a(b61Var4, b61Var9, b61Var);
        H = a(b61Var5, b61Var10, b61Var);
        I = a(b61Var, b61Var11, b61Var);
        J = a(b61Var, b61Var12, b61Var);
        K = a(b61Var, b61Var13, b61Var);
        L = a(b61Var, b61Var14, b61Var);
    }

    public a61(int i2) {
        super(i2);
    }

    public static int a(d61 d61Var, d61 d61Var2) {
        int size = d61Var.size();
        int size2 = d61Var2.size();
        int iMin = Math.min(size, size2);
        for (int i2 = 0; i2 < iMin; i2++) {
            int iCompareTo = d61Var.getType(i2).compareTo(d61Var2.getType(i2));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public static a61 b(b61 b61Var) {
        a61 a61Var = new a61(1);
        a61Var.a(0, b61Var);
        return a61Var;
    }

    public b61 d(int i2) {
        return (b61) a(i2);
    }

    @Override // supwisdom.d61
    public b61 getType(int i2) {
        return d(i2);
    }

    public int h() {
        int size = size();
        int iD = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iD += d(i2).d();
        }
        return iD;
    }

    public static String b(d61 d61Var) {
        int size = d61Var.size();
        if (size == 0) {
            return "<empty>";
        }
        StringBuilder sb = new StringBuilder(100);
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 != 0) {
                sb.append(", ");
            }
            sb.append(d61Var.getType(i2).toHuman());
        }
        return sb.toString();
    }

    public static int a(d61 d61Var) {
        int size = d61Var.size();
        int iHashCode = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iHashCode = (iHashCode * 31) + d61Var.getType(i2).hashCode();
        }
        return iHashCode;
    }

    public void a(int i2, b61 b61Var) {
        a(i2, (Object) b61Var);
    }

    public a61 a(b61 b61Var) {
        int size = size();
        a61 a61Var = new a61(size + 1);
        int i2 = 0;
        a61Var.a(0, (Object) b61Var);
        while (i2 < size) {
            int i3 = i2 + 1;
            a61Var.a(i3, b(i2));
            i2 = i3;
        }
        return a61Var;
    }

    public static a61 a(b61 b61Var, b61 b61Var2) {
        a61 a61Var = new a61(2);
        a61Var.a(0, b61Var);
        a61Var.a(1, b61Var2);
        return a61Var;
    }

    public static a61 a(b61 b61Var, b61 b61Var2, b61 b61Var3) {
        a61 a61Var = new a61(3);
        a61Var.a(0, b61Var);
        a61Var.a(1, b61Var2);
        a61Var.a(2, b61Var3);
        return a61Var;
    }

    public static a61 a(b61 b61Var, b61 b61Var2, b61 b61Var3, b61 b61Var4) {
        a61 a61Var = new a61(4);
        a61Var.a(0, b61Var);
        a61Var.a(1, b61Var2);
        a61Var.a(2, b61Var3);
        a61Var.a(3, b61Var4);
        return a61Var;
    }
}
