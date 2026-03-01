package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class yy0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ty0 f9933a;
    public final az0<T> b;
    public int c = -1;
    public l41 d;

    public yy0(ty0 ty0Var, az0<T> az0Var) {
        this.f9933a = ty0Var;
        this.b = az0Var;
    }

    public static <T> yy0<T> a(ty0 ty0Var, az0<T> az0Var) {
        return new yy0<>(ty0Var, az0Var);
    }

    public int b() {
        return this.b.b.d();
    }

    public l41 c() {
        if (this.d == null) {
            this.f9933a.b();
            if (this.d == null) {
                throw new AssertionError();
            }
        }
        return this.d;
    }

    public String toString() {
        return "v" + this.c + "(" + this.b + ")";
    }

    public az0 a() {
        return this.b;
    }

    public int a(int i) {
        this.c = i;
        this.d = l41.a(i, this.b.b);
        return b();
    }
}
