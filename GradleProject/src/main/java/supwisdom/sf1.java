package supwisdom;

import okio.BufferedSource;

/* JADX INFO: compiled from: RealResponseBody.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sf1 extends ve1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final oe1 f9166a;
    public final BufferedSource b;

    public sf1(oe1 oe1Var, BufferedSource bufferedSource) {
        this.f9166a = oe1Var;
        this.b = bufferedSource;
    }

    @Override // supwisdom.ve1
    public long b() {
        return rf1.a(this.f9166a);
    }

    @Override // supwisdom.ve1
    public BufferedSource c() {
        return this.b;
    }
}
