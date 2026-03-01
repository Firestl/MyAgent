package supwisdom;

/* JADX INFO: compiled from: VelocityMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public class y4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f9844a;
    public float b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9845e;
    public float f;

    public void a() {
        this.f9845e = 0.0f;
        this.d = 0.0f;
        this.c = 0.0f;
        this.b = 0.0f;
        this.f9844a = 0.0f;
    }

    public void b(q5 q5Var, q5 q5Var2, float f) {
        if (q5Var != null) {
            this.c = q5Var.b(f);
        }
        if (q5Var2 != null) {
            this.d = q5Var2.b(f);
        }
    }

    public void a(q5 q5Var, float f) {
        if (q5Var != null) {
            this.f9845e = q5Var.b(f);
            this.f = q5Var.a(f);
        }
    }

    public void b(f5 f5Var, f5 f5Var2, float f) {
        if (f5Var != null) {
            this.c = f5Var.b(f);
        }
        if (f5Var2 != null) {
            this.d = f5Var2.b(f);
        }
    }

    public void a(q5 q5Var, q5 q5Var2, float f) {
        if (q5Var != null) {
            this.f9844a = q5Var.b(f);
        }
        if (q5Var2 != null) {
            this.b = q5Var2.b(f);
        }
    }

    public void a(f5 f5Var, float f) {
        if (f5Var != null) {
            this.f9845e = f5Var.b(f);
        }
    }

    public void a(f5 f5Var, f5 f5Var2, float f) {
        if (f5Var == null && f5Var2 == null) {
            return;
        }
        if (f5Var == null) {
            this.f9844a = f5Var.b(f);
        }
        if (f5Var2 == null) {
            this.b = f5Var2.b(f);
        }
    }

    public void a(float f, float f2, int i, int i2, float[] fArr) {
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = (f - 0.5f) * 2.0f;
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = f3 + this.c;
        float f8 = f4 + this.d;
        float f9 = f7 + (this.f9844a * f5);
        float f10 = f8 + (this.b * f6);
        float radians = (float) Math.toRadians(this.f);
        float radians2 = (float) Math.toRadians(this.f9845e);
        double d = radians;
        double d2 = i2 * f6;
        float fSin = f9 + (((float) ((((double) ((-i) * f5)) * Math.sin(d)) - (Math.cos(d) * d2))) * radians2);
        float fCos = f10 + (radians2 * ((float) ((((double) (i * f5)) * Math.cos(d)) - (d2 * Math.sin(d)))));
        fArr[0] = fSin;
        fArr[1] = fCos;
    }
}
