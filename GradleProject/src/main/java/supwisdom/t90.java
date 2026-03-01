package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: ContainerMediaChunk.java */
/* JADX INFO: loaded from: classes.dex */
public class t90 extends l90 {
    public final int l;
    public final long m;
    public final o90 n;
    public volatile int o;
    public volatile boolean p;
    public volatile boolean q;

    public t90(s70 s70Var, u70 u70Var, com.google.android.exoplayer2.j jVar, int i, Object obj, long j, long j2, int i2, int i3, long j3, o90 o90Var) {
        super(s70Var, u70Var, jVar, i, obj, j, j2, i2);
        this.l = i3;
        this.m = j3;
        this.n = o90Var;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final void a() {
        this.p = true;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final boolean b() {
        return this.p;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final void c() throws InterruptedException, IOException {
        u70 u70VarA = x80.a(this.f8485a, this.o);
        try {
            r20 r20Var = new r20(this.h, u70VarA.c, this.h.a(u70VarA));
            if (this.o == 0) {
                m90 m90VarG = g();
                m90VarG.a(this.m);
                this.n.a(m90VarG);
            }
            try {
                y30 y30Var = this.n.f8646a;
                int iA = 0;
                while (iA == 0 && !this.p) {
                    iA = y30Var.a(r20Var, (d50) null);
                }
                e80.b(iA != 1);
                x80.a(this.h);
                this.q = true;
            } finally {
                this.o = (int) (r20Var.c() - this.f8485a.c);
            }
        } catch (Throwable th) {
            x80.a(this.h);
            throw th;
        }
    }

    @Override // supwisdom.n90
    public final long d() {
        return this.o;
    }

    @Override // supwisdom.w90
    public int e() {
        return this.i + this.l;
    }

    @Override // supwisdom.w90
    public boolean f() {
        return this.q;
    }
}
