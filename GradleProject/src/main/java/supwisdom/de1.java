package supwisdom;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CacheControl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class de1 {
    public static final de1 m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7328a;
    public final boolean b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f7329e;
    public final boolean f;
    public final boolean g;
    public final int h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public String l;

    static {
        b bVar = new b();
        bVar.b();
        bVar.a();
        b bVar2 = new b();
        bVar2.d();
        bVar2.a(Integer.MAX_VALUE, TimeUnit.SECONDS);
        m = bVar2.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static supwisdom.de1 a(supwisdom.oe1 r21) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.de1.a(supwisdom.oe1):supwisdom.de1");
    }

    public boolean b() {
        return this.f7329e;
    }

    public boolean c() {
        return this.f;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.h;
    }

    public int f() {
        return this.i;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.f7328a;
    }

    public boolean i() {
        return this.b;
    }

    public boolean j() {
        return this.j;
    }

    public String toString() {
        String str = this.l;
        if (str != null) {
            return str;
        }
        String strA = a();
        this.l = strA;
        return strA;
    }

    public de1(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.f7328a = z;
        this.b = z2;
        this.c = i;
        this.d = i2;
        this.f7329e = z3;
        this.f = z4;
        this.g = z5;
        this.h = i3;
        this.i = i4;
        this.j = z6;
        this.k = z7;
        this.l = str;
    }

    /* JADX INFO: compiled from: CacheControl.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7330a;
        public boolean b;
        public int c = -1;
        public int d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7331e = -1;
        public boolean f;
        public boolean g;

        public b a(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds(i);
                this.d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i);
        }

        public b b() {
            this.f7330a = true;
            return this;
        }

        public b c() {
            this.b = true;
            return this;
        }

        public b d() {
            this.f = true;
            return this;
        }

        public de1 a() {
            return new de1(this);
        }
    }

    public de1(b bVar) {
        this.f7328a = bVar.f7330a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = -1;
        this.f7329e = false;
        this.f = false;
        this.g = false;
        this.h = bVar.d;
        this.i = bVar.f7331e;
        this.j = bVar.f;
        this.k = bVar.g;
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        if (this.f7328a) {
            sb.append("no-cache, ");
        }
        if (this.b) {
            sb.append("no-store, ");
        }
        if (this.c != -1) {
            sb.append("max-age=");
            sb.append(this.c);
            sb.append(", ");
        }
        if (this.d != -1) {
            sb.append("s-maxage=");
            sb.append(this.d);
            sb.append(", ");
        }
        if (this.f7329e) {
            sb.append("private, ");
        }
        if (this.f) {
            sb.append("public, ");
        }
        if (this.g) {
            sb.append("must-revalidate, ");
        }
        if (this.h != -1) {
            sb.append("max-stale=");
            sb.append(this.h);
            sb.append(", ");
        }
        if (this.i != -1) {
            sb.append("min-fresh=");
            sb.append(this.i);
            sb.append(", ");
        }
        if (this.j) {
            sb.append("only-if-cached, ");
        }
        if (this.k) {
            sb.append("no-transform, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
