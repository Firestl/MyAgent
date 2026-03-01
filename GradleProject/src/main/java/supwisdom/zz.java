package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class zz extends yv {
    public final float c;

    public zz(float f, float f2, float f3) {
        super(f, f2);
        this.c = f3;
    }

    public boolean a(float f, float f2, float f3) {
        if (Math.abs(f2 - b()) > f || Math.abs(f3 - a()) > f) {
            return false;
        }
        float fAbs = Math.abs(f - this.c);
        return fAbs <= 1.0f || fAbs <= this.c;
    }

    public zz b(float f, float f2, float f3) {
        return new zz((a() + f2) / 2.0f, (b() + f) / 2.0f, (this.c + f3) / 2.0f);
    }
}
