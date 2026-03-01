package supwisdom;

/* JADX INFO: loaded from: classes3.dex */
public class xv1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9816a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f9817e;

    public xv1(int i, int i2, int i3, int i4, int[] iArr) {
        this.f9816a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.f9817e = (int[]) iArr.clone();
    }

    public static int a(int i, int i2) {
        return i | (i2 << 16);
    }

    public static int a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return a(i | (i2 << 4), (i4 << 4) | i3, (i6 << 4) | i5, (i8 << 4) | i7);
    }

    public static int a(int i, int i2, int i3, int i4) {
        return a(i | (i2 << 8), (i4 << 8) | i3);
    }

    public int a(int i) {
        return (this.f9817e[i >> this.f9816a] >> ((i & this.b) << this.c)) & this.d;
    }
}
