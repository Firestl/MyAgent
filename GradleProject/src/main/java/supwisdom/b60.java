package supwisdom;

import java.util.List;

/* JADX INFO: compiled from: DvbSubtitle.java */
/* JADX INFO: loaded from: classes.dex */
public final class b60 implements m60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<y50> f7028a;

    public b60(List<y50> list) {
        this.f7028a = list;
    }

    @Override // supwisdom.m60
    public int a(long j) {
        return -1;
    }

    @Override // supwisdom.m60
    public long a(int i) {
        return 0L;
    }

    @Override // supwisdom.m60
    public int b() {
        return 1;
    }

    @Override // supwisdom.m60
    public List<y50> b(long j) {
        return this.f7028a;
    }
}
