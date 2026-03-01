package supwisdom;

import java.io.IOException;

/* JADX INFO: compiled from: HlsSampleStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class ib0 implements mb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7935a;
    public final jb0 b;

    public ib0(jb0 jb0Var, int i) {
        this.b = jb0Var;
        this.f7935a = i;
    }

    @Override // supwisdom.mb0
    public boolean a() {
        return this.b.a(this.f7935a);
    }

    @Override // supwisdom.mb0
    public void b() throws IOException {
        this.b.g();
    }

    @Override // supwisdom.mb0
    public void c(long j) {
        this.b.a(this.f7935a, j);
    }

    @Override // supwisdom.mb0
    public int a(e90 e90Var, y10 y10Var, boolean z) {
        return this.b.a(this.f7935a, e90Var, y10Var, z);
    }
}
