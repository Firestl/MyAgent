package supwisdom;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aw0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T f6989a;
    public gs1 b;
    public dt1 c;

    public static <T> aw0<T> a(boolean z, T t, gs1 gs1Var, dt1 dt1Var) {
        aw0<T> aw0Var = new aw0<>();
        aw0Var.a(z);
        aw0Var.a(t);
        aw0Var.a(gs1Var);
        aw0Var.a(dt1Var);
        return aw0Var;
    }

    public void a(Throwable th) {
    }

    public void a(boolean z) {
    }

    public gs1 b() {
        return this.b;
    }

    public dt1 c() {
        return this.c;
    }

    public String d() {
        dt1 dt1Var = this.c;
        if (dt1Var == null) {
            return null;
        }
        return dt1Var.g();
    }

    public static <T> aw0<T> a(boolean z, gs1 gs1Var, dt1 dt1Var, Throwable th) {
        aw0<T> aw0Var = new aw0<>();
        aw0Var.a(z);
        aw0Var.a(gs1Var);
        aw0Var.a(dt1Var);
        aw0Var.a(th);
        return aw0Var;
    }

    public int a() {
        dt1 dt1Var = this.c;
        if (dt1Var == null) {
            return -1;
        }
        return dt1Var.c();
    }

    public void a(T t) {
        this.f6989a = t;
    }

    public void a(gs1 gs1Var) {
        this.b = gs1Var;
    }

    public void a(dt1 dt1Var) {
        this.c = dt1Var;
    }
}
