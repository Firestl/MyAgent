package supwisdom;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public class x4 extends n5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f9707a;
    public float b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9708e;
    public float f;
    public float g;
    public float h;
    public float i;
    public int j;
    public boolean k = false;
    public float l;
    public float m;

    public final float a(float f) {
        float f2 = this.d;
        if (f <= f2) {
            float f3 = this.f9707a;
            return (f3 * f) + ((((this.b - f3) * f) * f) / (f2 * 2.0f));
        }
        int i = this.j;
        if (i == 1) {
            return this.g;
        }
        float f4 = f - f2;
        float f5 = this.f9708e;
        if (f4 < f5) {
            float f6 = this.g;
            float f7 = this.b;
            return f6 + (f7 * f4) + ((((this.c - f7) * f4) * f4) / (f5 * 2.0f));
        }
        if (i == 2) {
            return this.h;
        }
        float f8 = f4 - f5;
        float f9 = this.f;
        if (f8 >= f9) {
            return this.i;
        }
        float f10 = this.h;
        float f11 = this.c;
        return (f10 + (f11 * f8)) - (((f11 * f8) * f8) / (f9 * 2.0f));
    }

    public float b(float f) {
        float f2 = this.d;
        if (f <= f2) {
            float f3 = this.f9707a;
            return f3 + (((this.b - f3) * f) / f2);
        }
        int i = this.j;
        if (i == 1) {
            return 0.0f;
        }
        float f4 = f - f2;
        float f5 = this.f9708e;
        if (f4 < f5) {
            float f6 = this.b;
            return f6 + (((this.c - f6) * f4) / f5);
        }
        if (i == 2) {
            return this.h;
        }
        float f7 = f4 - f5;
        float f8 = this.f;
        if (f7 >= f8) {
            return this.i;
        }
        float f9 = this.c;
        return f9 - ((f7 * f9) / f8);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        float fA = a(f);
        this.m = f;
        return this.k ? this.l - fA : this.l + fA;
    }

    public void a(float f, float f2, float f3, float f4, float f5, float f6) {
        this.l = f;
        boolean z = f > f2;
        this.k = z;
        if (z) {
            a(-f3, f - f2, f5, f6, f4);
        } else {
            a(f3, f2 - f, f5, f6, f4);
        }
    }

    @Override // supwisdom.n5
    public float a() {
        return this.k ? -b(this.m) : b(this.m);
    }

    public final void a(float f, float f2, float f3, float f4, float f5) {
        if (f == 0.0f) {
            f = 1.0E-4f;
        }
        this.f9707a = f;
        float f6 = f / f3;
        float f7 = (f6 * f) / 2.0f;
        if (f < 0.0f) {
            float fSqrt = (float) Math.sqrt((f2 - ((((-f) / f3) * f) / 2.0f)) * f3);
            if (fSqrt < f4) {
                this.j = 2;
                this.f9707a = f;
                this.b = fSqrt;
                this.c = 0.0f;
                float f8 = (fSqrt - f) / f3;
                this.d = f8;
                this.f9708e = fSqrt / f3;
                this.g = ((f + fSqrt) * f8) / 2.0f;
                this.h = f2;
                this.i = f2;
                return;
            }
            this.j = 3;
            this.f9707a = f;
            this.b = f4;
            this.c = f4;
            float f9 = (f4 - f) / f3;
            this.d = f9;
            float f10 = f4 / f3;
            this.f = f10;
            float f11 = ((f + f4) * f9) / 2.0f;
            float f12 = (f10 * f4) / 2.0f;
            this.f9708e = ((f2 - f11) - f12) / f4;
            this.g = f11;
            this.h = f2 - f12;
            this.i = f2;
            return;
        }
        if (f7 >= f2) {
            this.j = 1;
            this.f9707a = f;
            this.b = 0.0f;
            this.g = f2;
            this.d = (2.0f * f2) / f;
            return;
        }
        float f13 = f2 - f7;
        float f14 = f13 / f;
        if (f14 + f6 < f5) {
            this.j = 2;
            this.f9707a = f;
            this.b = f;
            this.c = 0.0f;
            this.g = f13;
            this.h = f2;
            this.d = f14;
            this.f9708e = f6;
            return;
        }
        float fSqrt2 = (float) Math.sqrt((f3 * f2) + ((f * f) / 2.0f));
        float f15 = (fSqrt2 - f) / f3;
        this.d = f15;
        float f16 = fSqrt2 / f3;
        this.f9708e = f16;
        if (fSqrt2 < f4) {
            this.j = 2;
            this.f9707a = f;
            this.b = fSqrt2;
            this.c = 0.0f;
            this.d = f15;
            this.f9708e = f16;
            this.g = ((f + fSqrt2) * f15) / 2.0f;
            this.h = f2;
            return;
        }
        this.j = 3;
        this.f9707a = f;
        this.b = f4;
        this.c = f4;
        float f17 = (f4 - f) / f3;
        this.d = f17;
        float f18 = f4 / f3;
        this.f = f18;
        float f19 = ((f + f4) * f17) / 2.0f;
        float f20 = (f18 * f4) / 2.0f;
        this.f9708e = ((f2 - f19) - f20) / f4;
        this.g = f19;
        this.h = f2 - f20;
        this.i = f2;
    }
}
