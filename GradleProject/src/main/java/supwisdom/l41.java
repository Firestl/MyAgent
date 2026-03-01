package supwisdom;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class l41 implements c61, t61, Comparable<l41> {
    public static final ConcurrentHashMap<Object, l41> d = new ConcurrentHashMap<>(10000, 0.75f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ThreadLocal<b> f8245e = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8246a;
    public final c61 b;
    public final g41 c;

    /* JADX INFO: compiled from: Proguard */
    public static class a extends ThreadLocal<b> {
        @Override // java.lang.ThreadLocal
        public b initialValue() {
            return new b(null);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8247a;
        public c61 b;
        public g41 c;

        public b() {
        }

        public boolean equals(Object obj) {
            if (obj instanceof l41) {
                return ((l41) obj).a(this.f8247a, this.b, this.c);
            }
            return false;
        }

        public int hashCode() {
            return l41.c(this.f8247a, this.b, this.c);
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        public void a(int i, c61 c61Var, g41 g41Var) {
            this.f8247a = i;
            this.b = c61Var;
            this.c = g41Var;
        }

        public l41 a() {
            return new l41(this.f8247a, this.b, this.c, null);
        }
    }

    public /* synthetic */ l41(int i, c61 c61Var, g41 g41Var, a aVar) {
        this(i, c61Var, g41Var);
    }

    public int c() {
        return this.b.getType().d();
    }

    public g41 d() {
        return this.c;
    }

    public int e() {
        return this.f8246a + c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof l41) {
            l41 l41Var = (l41) obj;
            return a(l41Var.f8246a, l41Var.b, l41Var.c);
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return a(bVar.f8247a, bVar.b, bVar.c);
    }

    public int f() {
        return this.f8246a;
    }

    public boolean g() {
        return this.b.getType().i();
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return this.b.getType();
    }

    public boolean h() {
        return (f() & 1) == 0;
    }

    public int hashCode() {
        return c(this.f8246a, this.b, this.c);
    }

    public String i() {
        return c(this.f8246a);
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return a(true);
    }

    public String toString() {
        return a(false);
    }

    public l41(int i, c61 c61Var, g41 g41Var) {
        if (i < 0) {
            throw new IllegalArgumentException("reg < 0");
        }
        if (c61Var == null) {
            throw new NullPointerException("type == null");
        }
        this.f8246a = i;
        this.b = c61Var;
        this.c = g41Var;
    }

    public static l41 d(int i, c61 c61Var, g41 g41Var) {
        l41 l41VarPutIfAbsent;
        b bVar = f8245e.get();
        bVar.a(i, c61Var, g41Var);
        ConcurrentHashMap<Object, l41> concurrentHashMap = d;
        l41 l41VarA = concurrentHashMap.get(bVar);
        return (l41VarA != null || (l41VarPutIfAbsent = concurrentHashMap.putIfAbsent((l41VarA = bVar.a()), l41VarA)) == null) ? l41VarA : l41VarPutIfAbsent;
    }

    public static l41 e(int i, c61 c61Var, g41 g41Var) {
        return d(i, c61Var, g41Var);
    }

    @Override // supwisdom.c61
    public final int a() {
        return this.b.a();
    }

    public boolean b(l41 l41Var) {
        return c(l41Var) && this.f8246a == l41Var.f8246a;
    }

    public boolean c(l41 l41Var) {
        if (l41Var == null || !this.b.getType().equals(l41Var.b.getType())) {
            return false;
        }
        g41 g41Var = this.c;
        g41 g41Var2 = l41Var.c;
        if (g41Var == g41Var2) {
            return true;
        }
        if (g41Var == null) {
            return false;
        }
        g41Var.equals(g41Var2);
        throw null;
    }

    public static l41 a(int i, c61 c61Var) {
        return d(i, c61Var, null);
    }

    public static String c(int i) {
        return "v" + i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(boolean r4) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 40
            r0.<init>(r1)
            java.lang.String r1 = r3.i()
            r0.append(r1)
            java.lang.String r1 = ":"
            r0.append(r1)
            supwisdom.g41 r1 = r3.c
            if (r1 != 0) goto L55
            supwisdom.c61 r1 = r3.b
            supwisdom.b61 r1 = r1.getType()
            r0.append(r1)
            supwisdom.c61 r2 = r3.b
            if (r1 == r2) goto L50
            java.lang.String r1 = "="
            r0.append(r1)
            if (r4 == 0) goto L3b
            supwisdom.c61 r1 = r3.b
            boolean r2 = r1 instanceof supwisdom.v51
            if (r2 == 0) goto L3b
            supwisdom.v51 r1 = (supwisdom.v51) r1
            java.lang.String r4 = r1.h()
            r0.append(r4)
            goto L50
        L3b:
            if (r4 == 0) goto L4b
            supwisdom.c61 r4 = r3.b
            boolean r1 = r4 instanceof supwisdom.u41
            if (r1 == 0) goto L4b
            java.lang.String r4 = r4.toHuman()
            r0.append(r4)
            goto L50
        L4b:
            supwisdom.c61 r4 = r3.b
            r0.append(r4)
        L50:
            java.lang.String r4 = r0.toString()
            return r4
        L55:
            r1.toString()
            r4 = 0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.l41.a(boolean):java.lang.String");
    }

    @Override // supwisdom.c61
    public final int b() {
        return this.b.b();
    }

    public static int c(int i, c61 c61Var, g41 g41Var) {
        if (g41Var == null) {
            return (((0 * 31) + c61Var.hashCode()) * 31) + i;
        }
        g41Var.hashCode();
        throw null;
    }

    public l41 b(int i) {
        return this.f8246a == i ? this : e(i, this.b, this.c);
    }

    public l41 a(int i) {
        return i == 0 ? this : b(this.f8246a + i);
    }

    public l41 a(c61 c61Var) {
        return e(this.f8246a, c61Var, this.c);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(l41 l41Var) {
        int i = this.f8246a;
        int i2 = l41Var.f8246a;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        if (this == l41Var) {
            return 0;
        }
        int iCompareTo = this.b.getType().compareTo(l41Var.b.getType());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        g41 g41Var = this.c;
        if (g41Var == null) {
            return l41Var.c == null ? 0 : -1;
        }
        g41 g41Var2 = l41Var.c;
        if (g41Var2 == null) {
            return 1;
        }
        g41Var.a(g41Var2);
        throw null;
    }

    public final boolean a(int i, c61 c61Var, g41 g41Var) {
        if (this.f8246a == i && this.b.equals(c61Var)) {
            g41 g41Var2 = this.c;
            if (g41Var2 == g41Var) {
                return true;
            }
            if (g41Var2 != null) {
                g41Var2.equals(g41Var);
                throw null;
            }
        }
        return false;
    }
}
