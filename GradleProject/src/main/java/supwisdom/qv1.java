package supwisdom;

/* JADX INFO: loaded from: classes3.dex */
public abstract class qv1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8962a;
    public int b;
    public int[] c;
    public float d;

    public qv1() {
        c();
    }

    public float a() {
        int i;
        int i2 = this.b;
        if (i2 <= 0 || (i = this.f8962a) <= 4) {
            return 0.01f;
        }
        if (i2 != i) {
            float f = i / ((i2 - i) * this.d);
            if (f < 0.99f) {
                return f;
            }
        }
        return 0.99f;
    }

    public abstract int a(byte[] bArr, int i);

    public boolean b() {
        return this.b > 1024;
    }

    public void c() {
        this.b = 0;
        this.f8962a = 0;
    }

    public void a(byte[] bArr, int i, int i2) {
        int iA = i2 == 2 ? a(bArr, i) : -1;
        if (iA >= 0) {
            this.b++;
            int[] iArr = this.c;
            if (iA >= iArr.length || 512 <= iArr[iA]) {
                return;
            }
            this.f8962a++;
        }
    }
}
