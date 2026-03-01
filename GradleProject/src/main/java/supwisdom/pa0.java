package supwisdom;

/* JADX INFO: compiled from: DashWrappingSegmentIndex.java */
/* JADX INFO: loaded from: classes.dex */
public final class pa0 implements oa0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k20 f8782a;

    public pa0(k20 k20Var) {
        this.f8782a = k20Var;
    }

    @Override // supwisdom.oa0
    public int a() {
        return 0;
    }

    @Override // supwisdom.oa0
    public int a(long j) {
        return this.f8782a.f8113a;
    }

    @Override // supwisdom.oa0
    public fa0 b(int i) {
        return new fa0(null, this.f8782a.c[i], r0.b[i]);
    }

    @Override // supwisdom.oa0
    public boolean b() {
        return true;
    }

    @Override // supwisdom.oa0
    public long a(int i) {
        return this.f8782a.f8114e[i];
    }

    @Override // supwisdom.oa0
    public long a(int i, long j) {
        return this.f8782a.d[i];
    }

    @Override // supwisdom.oa0
    public int a(long j, long j2) {
        return this.f8782a.a(j);
    }
}
