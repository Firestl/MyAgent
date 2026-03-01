package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: BaseRenderer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t10 implements h90, i90 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9232a;
    public j90 b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public mb0 f9233e;
    public long f;
    public boolean g = true;
    public boolean h;

    public t10(int i) {
        this.f9232a = i;
    }

    @Override // supwisdom.h90, supwisdom.i90
    public final int a() {
        return this.f9232a;
    }

    @Override // supwisdom.j50.b
    public void a(int i, Object obj) throws com.google.android.exoplayer2.e {
    }

    public abstract void a(long j, boolean z) throws com.google.android.exoplayer2.e;

    public void a(boolean z) throws com.google.android.exoplayer2.e {
    }

    public void a(com.google.android.exoplayer2.j[] jVarArr) throws com.google.android.exoplayer2.e {
    }

    @Override // supwisdom.h90
    public final i90 b() {
        return this;
    }

    public void b(long j) {
        this.f9233e.c(j - this.f);
    }

    @Override // supwisdom.h90
    public k80 c() {
        return null;
    }

    @Override // supwisdom.h90
    public final int d() {
        return this.d;
    }

    @Override // supwisdom.h90
    public final void e() throws com.google.android.exoplayer2.e {
        e80.b(this.d == 1);
        this.d = 2;
        q();
    }

    @Override // supwisdom.h90
    public final mb0 f() {
        return this.f9233e;
    }

    @Override // supwisdom.h90
    public final boolean g() {
        return this.g;
    }

    @Override // supwisdom.h90
    public final void h() {
        this.h = true;
    }

    @Override // supwisdom.h90
    public final boolean i() {
        return this.h;
    }

    @Override // supwisdom.h90
    public final void j() throws IOException {
        this.f9233e.b();
    }

    @Override // supwisdom.h90
    public final void k() throws com.google.android.exoplayer2.e {
        e80.b(this.d == 2);
        this.d = 1;
        r();
    }

    @Override // supwisdom.h90
    public final void l() {
        e80.b(this.d == 1);
        this.d = 0;
        this.f9233e = null;
        this.h = false;
        s();
    }

    public int m() throws com.google.android.exoplayer2.e {
        return 0;
    }

    public void q() throws com.google.android.exoplayer2.e {
    }

    public void r() throws com.google.android.exoplayer2.e {
    }

    public abstract void s();

    public final j90 t() {
        return this.b;
    }

    public final int v() {
        return this.c;
    }

    public final boolean w() {
        return this.g ? this.h : this.f9233e.a();
    }

    @Override // supwisdom.h90
    public final void a(int i) {
        this.c = i;
    }

    @Override // supwisdom.h90
    public final void a(j90 j90Var, com.google.android.exoplayer2.j[] jVarArr, mb0 mb0Var, long j, boolean z, long j2) throws com.google.android.exoplayer2.e {
        e80.b(this.d == 0);
        this.b = j90Var;
        this.d = 1;
        a(z);
        a(jVarArr, mb0Var, j2);
        a(j, z);
    }

    @Override // supwisdom.h90
    public final void a(com.google.android.exoplayer2.j[] jVarArr, mb0 mb0Var, long j) throws com.google.android.exoplayer2.e {
        e80.b(!this.h);
        this.f9233e = mb0Var;
        this.g = false;
        this.f = j;
        a(jVarArr);
    }

    @Override // supwisdom.h90
    public final void a(long j) throws com.google.android.exoplayer2.e {
        this.h = false;
        this.g = false;
        a(j, false);
    }

    public final int a(e90 e90Var, y10 y10Var, boolean z) {
        int iA = this.f9233e.a(e90Var, y10Var, z);
        if (iA == -4) {
            if (y10Var.d()) {
                this.g = true;
                return this.h ? -4 : -3;
            }
            y10Var.d += this.f;
        } else if (iA == -5) {
            com.google.android.exoplayer2.j jVar = e90Var.f7455a;
            long j = jVar.w;
            if (j != Long.MAX_VALUE) {
                e90Var.f7455a = jVar.a(j + this.f);
            }
        }
        return iA;
    }
}
