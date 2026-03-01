package supwisdom;

import com.google.android.exoplayer2.i.r;

/* JADX INFO: compiled from: Chunk.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n90 implements r.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u70 f8485a;
    public final int b;
    public final com.google.android.exoplayer2.j c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f8486e;
    public final long f;
    public final long g;
    public final s70 h;

    public n90(s70 s70Var, u70 u70Var, int i, com.google.android.exoplayer2.j jVar, int i2, Object obj, long j, long j2) {
        e80.a(s70Var);
        this.h = s70Var;
        e80.a(u70Var);
        this.f8485a = u70Var;
        this.b = i;
        this.c = jVar;
        this.d = i2;
        this.f8486e = obj;
        this.f = j;
        this.g = j2;
    }

    public abstract long d();
}
