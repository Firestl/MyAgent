package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Tx3gSubtitle.java */
/* JADX INFO: loaded from: classes.dex */
public final class o60 implements m60 {
    public static final o60 b = new o60();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<y50> f8633a;

    public o60(y50 y50Var) {
        this.f8633a = Collections.singletonList(y50Var);
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
        return j >= 0 ? this.f8633a : Collections.emptyList();
    }

    public o60() {
        this.f8633a = Collections.emptyList();
    }
}
