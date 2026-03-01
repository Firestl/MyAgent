package supwisdom;

/* JADX INFO: compiled from: RendererConfiguration.java */
/* JADX INFO: loaded from: classes.dex */
public final class j90 {
    public static final j90 b = new j90(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8033a;

    public j90(int i) {
        this.f8033a = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && j90.class == obj.getClass() && this.f8033a == ((j90) obj).f8033a;
    }

    public int hashCode() {
        return this.f8033a;
    }
}
