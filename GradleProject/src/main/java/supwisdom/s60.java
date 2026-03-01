package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Mp4WebvttSubtitle.java */
/* JADX INFO: loaded from: classes.dex */
public final class s60 implements m60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<y50> f9130a;

    public s60(List<y50> list) {
        this.f9130a = Collections.unmodifiableList(list);
    }

    @Override // supwisdom.m60
    public int a(long j) {
        return j < 0 ? 0 : -1;
    }

    @Override // supwisdom.m60
    public long a(int i) {
        e80.a(i == 0);
        return 0L;
    }

    @Override // supwisdom.m60
    public int b() {
        return 1;
    }

    @Override // supwisdom.m60
    public List<y50> b(long j) {
        return j >= 0 ? this.f9130a : Collections.emptyList();
    }
}
