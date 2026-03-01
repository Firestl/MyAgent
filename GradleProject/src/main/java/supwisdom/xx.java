package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class xx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9821a;
    public final int[] b;
    public final yv[] c;

    public xx(int i, int[] iArr, int i2, int i3, int i4) {
        this.f9821a = i;
        this.b = iArr;
        float f = i4;
        this.c = new yv[]{new yv(i2, f), new yv(i3, f)};
    }

    public yv[] a() {
        return this.c;
    }

    public int[] b() {
        return this.b;
    }

    public int c() {
        return this.f9821a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof xx) && this.f9821a == ((xx) obj).f9821a;
    }

    public int hashCode() {
        return this.f9821a;
    }
}
