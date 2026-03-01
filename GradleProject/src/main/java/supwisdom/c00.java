package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class c00 extends yv {
    public final float c;
    public final int d;

    public c00(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    public boolean a(float f, float f2, float f3) {
        if (Math.abs(f2 - b()) > f || Math.abs(f3 - a()) > f) {
            return false;
        }
        float fAbs = Math.abs(f - this.c);
        return fAbs <= 1.0f || fAbs <= this.c;
    }

    public c00 b(float f, float f2, float f3) {
        int i = this.d;
        int i2 = i + 1;
        float fA = (i * a()) + f2;
        float f4 = i2;
        return new c00(fA / f4, ((this.d * b()) + f) / f4, ((this.d * this.c) + f3) / f4, i2);
    }

    public int c() {
        return this.d;
    }

    public float d() {
        return this.c;
    }

    public c00(float f, float f2, float f3, int i) {
        super(f, f2);
        this.c = f3;
        this.d = i;
    }
}
