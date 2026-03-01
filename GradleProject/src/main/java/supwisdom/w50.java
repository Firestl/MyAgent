package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: CeaSubtitle.java */
/* JADX INFO: loaded from: classes.dex */
public final class w50 implements m60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<y50> f9588a;

    public w50(List<y50> list) {
        this.f9588a = list;
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
        return j >= 0 ? this.f9588a : Collections.emptyList();
    }
}
