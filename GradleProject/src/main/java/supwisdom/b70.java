package supwisdom;

import java.util.List;

/* JADX INFO: compiled from: SubtitleOutputBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b70 extends z10 implements m60 {
    public m60 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f7033e;

    public void a(long j, m60 m60Var, long j2) {
        this.b = j;
        this.d = m60Var;
        if (j2 != Long.MAX_VALUE) {
            j = j2;
        }
        this.f7033e = j;
    }

    @Override // supwisdom.m60
    public int b() {
        return this.d.b();
    }

    public abstract void f();

    @Override // supwisdom.m60
    public List<y50> b(long j) {
        return this.d.b(j - this.f7033e);
    }

    @Override // supwisdom.m60
    public long a(int i) {
        return this.d.a(i) + this.f7033e;
    }

    @Override // supwisdom.m60
    public int a(long j) {
        return this.d.a(j - this.f7033e);
    }

    @Override // supwisdom.u10
    public void a() {
        super.a();
        this.d = null;
    }
}
