package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class ry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9098a = 0;
    public a b = a.NUMERIC;

    public enum a {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    public int a() {
        return this.f9098a;
    }

    public boolean b() {
        return this.b == a.ALPHA;
    }

    public boolean c() {
        return this.b == a.ISO_IEC_646;
    }

    public void d() {
        this.b = a.ALPHA;
    }

    public void e() {
        this.b = a.ISO_IEC_646;
    }

    public void f() {
        this.b = a.NUMERIC;
    }

    public void a(int i) {
        this.f9098a += i;
    }

    public void b(int i) {
        this.f9098a = i;
    }
}
