package supwisdom;

/* JADX INFO: compiled from: WavHeader.java */
/* JADX INFO: loaded from: classes.dex */
public final class x40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9709a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9710e;
    public final int f;
    public long g;
    public long h;

    public x40(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f9709a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.f9710e = i5;
        this.f = i6;
    }

    public long a() {
        return ((this.h / ((long) this.d)) * 1000000) / ((long) this.b);
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.b * this.f9710e * this.f9709a;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.f9709a;
    }

    public boolean f() {
        return (this.g == 0 || this.h == 0) ? false : true;
    }

    public int g() {
        return this.f;
    }

    public long b(long j) {
        return (j * 1000000) / ((long) this.c);
    }

    public long a(long j) {
        long j2 = (j * ((long) this.c)) / 1000000;
        int i = this.d;
        return Math.min((j2 / ((long) i)) * ((long) i), this.h - ((long) i)) + this.g;
    }

    public void a(long j, long j2) {
        this.g = j;
        this.h = j2;
    }
}
