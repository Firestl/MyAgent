package supwisdom;

/* JADX INFO: compiled from: BaseMediaChunk.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l90 extends w90 {
    public m90 j;
    public int[] k;

    public l90(s70 s70Var, u70 u70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, long j, long j2, int i2) {
        super(s70Var, u70Var, jVar, i, obj, j, j2, i2);
    }

    public void a(m90 m90Var) {
        this.j = m90Var;
        this.k = m90Var.a();
    }

    public final m90 g() {
        return this.j;
    }

    public final int a(int i) {
        return this.k[i];
    }
}
