package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class mz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8456a;
    public final int b;
    public final int c;
    public final qz0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f8457e;

    public mz0(int i, int i2, int i3, qz0 qz0Var, boolean z) {
        if (!f31.a(i)) {
            throw new IllegalArgumentException("bogus opcode");
        }
        if (!f31.a(i2)) {
            throw new IllegalArgumentException("bogus family");
        }
        if (!f31.a(i3)) {
            throw new IllegalArgumentException("bogus nextOpcode");
        }
        if (qz0Var == null) {
            throw new NullPointerException("format == null");
        }
        this.f8456a = i;
        this.b = i2;
        this.c = i3;
        this.d = qz0Var;
        this.f8457e = z;
    }

    public int a() {
        return this.b;
    }

    public qz0 b() {
        return this.d;
    }

    public String c() {
        return e31.c(this.f8456a);
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.f8456a;
    }

    public mz0 f() {
        switch (this.f8456a) {
            case 50:
                return nz0.b0;
            case 51:
                return nz0.a0;
            case 52:
                return nz0.d0;
            case 53:
                return nz0.c0;
            case 54:
                return nz0.f0;
            case 55:
                return nz0.e0;
            case 56:
                return nz0.h0;
            case 57:
                return nz0.g0;
            case 58:
                return nz0.j0;
            case 59:
                return nz0.i0;
            case 60:
                return nz0.l0;
            case 61:
                return nz0.k0;
            default:
                throw new IllegalArgumentException("bogus opcode: " + this);
        }
    }

    public boolean g() {
        return this.f8457e;
    }

    public String toString() {
        return c();
    }
}
