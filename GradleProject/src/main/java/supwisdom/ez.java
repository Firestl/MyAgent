package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class ez {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7550a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7551e = -1;

    public ez(int i, int i2, int i3, int i4) {
        this.f7550a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.f7551e;
    }

    public int d() {
        return this.f7550a;
    }

    public int e() {
        return this.d;
    }

    public int f() {
        return this.b - this.f7550a;
    }

    public boolean g() {
        return a(this.f7551e);
    }

    public void h() {
        this.f7551e = ((this.d / 30) * 3) + (this.c / 3);
    }

    public String toString() {
        return this.f7551e + "|" + this.d;
    }

    public boolean a(int i) {
        return i != -1 && this.c == (i % 3) * 3;
    }

    public void b(int i) {
        this.f7551e = i;
    }
}
