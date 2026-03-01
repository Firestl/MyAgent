package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: SingleSampleMediaChunk.java */
/* JADX INFO: loaded from: classes.dex */
public final class x90 extends l90 {
    public final int l;
    public final com.google.android.exoplayer2.j m;
    public volatile int n;
    public volatile boolean o;
    public volatile boolean p;

    public x90(s70 s70Var, u70 u70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, long j, long j2, int i2, int i3, com.google.android.exoplayer2.j jVar2) {
        super(s70Var, u70Var, jVar, i, obj, j, j2, i2);
        this.l = i3;
        this.m = jVar2;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public void a() {
        this.o = true;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public boolean b() {
        return this.o;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public void c() throws InterruptedException, IOException {
        try {
            long jA = this.h.a(x80.a(this.f8485a, this.n));
            if (jA != -1) {
                jA += (long) this.n;
            }
            r20 r20Var = new r20(this.h, this.n, jA);
            m90 m90VarG = g();
            m90VarG.a(0L);
            f50 f50VarA = m90VarG.a(0, this.l);
            f50VarA.a(this.m);
            for (int iA = 0; iA != -1; iA = f50VarA.a(r20Var, Integer.MAX_VALUE, true)) {
                this.n += iA;
            }
            f50VarA.a(this.f, 1, this.n, 0, null);
            x80.a(this.h);
            this.p = true;
        } catch (Throwable th) {
            x80.a(this.h);
            throw th;
        }
    }

    @Override // supwisdom.n90
    public long d() {
        return this.n;
    }

    @Override // supwisdom.w90
    public boolean f() {
        return this.p;
    }
}
