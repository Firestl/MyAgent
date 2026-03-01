package supwisdom;

/* JADX INFO: compiled from: Buffer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u10 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9352a;

    public void a() {
        this.f9352a = 0;
    }

    public final void b(int i) {
        this.f9352a = i;
    }

    public final boolean c() {
        return e(Integer.MIN_VALUE);
    }

    public final boolean d() {
        return e(4);
    }

    public final boolean e() {
        return e(1);
    }

    public final void c(int i) {
        this.f9352a = i | this.f9352a;
    }

    public final void d(int i) {
        this.f9352a = (~i) & this.f9352a;
    }

    public final boolean e(int i) {
        return (this.f9352a & i) == i;
    }
}
