package supwisdom;

/* JADX INFO: loaded from: classes3.dex */
public abstract class yv1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public xv1 f9922a;
    public int b;
    public xv1 c;
    public int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9923e;

    public yv1(xv1 xv1Var, int i, xv1 xv1Var2, int[] iArr, String str) {
        this.f9922a = xv1Var;
        this.b = i;
        this.c = xv1Var2;
        this.d = (int[]) iArr.clone();
        this.f9923e = str;
    }

    public int a(int i) {
        return this.d[i];
    }

    public int a(byte b) {
        return this.f9922a.a(b & 255);
    }

    public String a() {
        return this.f9923e;
    }

    public int a(int i, int i2) {
        return this.c.a((i2 * this.b) + i);
    }
}
