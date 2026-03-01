package supwisdom;

/* JADX INFO: compiled from: MediaChunk.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class w90 extends n90 {
    public final int i;

    public w90(s70 s70Var, u70 u70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, long j, long j2, int i2) {
        super(s70Var, u70Var, 1, jVar, i, obj, j, j2);
        e80.a(jVar);
        this.i = i2;
    }

    public int e() {
        return this.i + 1;
    }

    public abstract boolean f();
}
