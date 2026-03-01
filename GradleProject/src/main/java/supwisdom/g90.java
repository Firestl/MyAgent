package supwisdom;

/* JADX INFO: compiled from: PlaybackParameters.java */
/* JADX INFO: loaded from: classes.dex */
public final class g90 {
    public static final g90 d = new g90(1.0f, 1.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f7706a;
    public final float b;
    public final int c;

    public g90(float f, float f2) {
        this.f7706a = f;
        this.b = f2;
        this.c = Math.round(f * 1000.0f);
    }

    public long a(long j) {
        return j * ((long) this.c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g90.class != obj.getClass()) {
            return false;
        }
        g90 g90Var = (g90) obj;
        return this.f7706a == g90Var.f7706a && this.b == g90Var.b;
    }

    public int hashCode() {
        return ((527 + Float.floatToRawIntBits(this.f7706a)) * 31) + Float.floatToRawIntBits(this.b);
    }
}
