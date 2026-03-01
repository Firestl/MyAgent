package supwisdom;

/* JADX INFO: compiled from: SubtitleInputBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class a70 extends y10 implements Comparable<a70> {
    public long f;

    public a70() {
        super(1);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(a70 a70Var) {
        long j = this.d - a70Var.d;
        if (j == 0) {
            return 0;
        }
        return j > 0 ? 1 : -1;
    }
}
