package supwisdom;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class ol0 extends zm0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f8684a;
    public float b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8685e;

    public ol0(float f, float f2, float f3) {
        this.b = f;
        this.f8684a = f2;
        this.d = f3;
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f8685e = 0.0f;
    }

    @Override // supwisdom.zm0
    public void a(float f, float f2, bn0 bn0Var) {
        float f3 = this.c;
        if (f3 == 0.0f) {
            bn0Var.a(f, 0.0f);
            return;
        }
        float f4 = ((this.b * 2.0f) + f3) / 2.0f;
        float f5 = f2 * this.f8684a;
        float f6 = (f / 2.0f) + this.f8685e;
        float f7 = (this.d * f2) + ((1.0f - f2) * f4);
        if (f7 / f4 >= 1.0f) {
            bn0Var.a(f, 0.0f);
            return;
        }
        float f8 = f4 + f5;
        float f9 = f7 + f5;
        float fSqrt = (float) Math.sqrt((f8 * f8) - (f9 * f9));
        float f10 = f6 - fSqrt;
        float f11 = f6 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f9));
        float f12 = 90.0f - degrees;
        float f13 = f10 - f5;
        bn0Var.a(f13, 0.0f);
        float f14 = f5 * 2.0f;
        bn0Var.a(f13, 0.0f, f10 + f5, f14, 270.0f, degrees);
        bn0Var.a(f6 - f4, (-f4) - f7, f6 + f4, f4 - f7, 180.0f - f12, (f12 * 2.0f) - 180.0f);
        bn0Var.a(f11 - f5, 0.0f, f11 + f5, f14, 270.0f - degrees, degrees);
        bn0Var.a(f, 0.0f);
    }

    public float b() {
        return this.b;
    }

    public float c() {
        return this.f8684a;
    }

    public float d() {
        return this.c;
    }

    public void e(float f) {
        this.f8685e = f;
    }

    public void b(float f) {
        this.b = f;
    }

    public void c(float f) {
        this.f8684a = f;
    }

    public void d(float f) {
        this.c = f;
    }

    public float e() {
        return this.f8685e;
    }

    public float a() {
        return this.d;
    }

    public void a(float f) {
        this.d = f;
    }
}
