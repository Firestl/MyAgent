package supwisdom;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: TtmlSubtitle.java */
/* JADX INFO: loaded from: classes.dex */
public final class l60 implements m60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h60 f8253a;
    public final long[] b;
    public final Map<String, k60> c;
    public final Map<String, i60> d;

    public l60(h60 h60Var, Map<String, k60> map, Map<String, i60> map2) {
        this.f8253a = h60Var;
        this.d = map2;
        this.c = map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap();
        this.b = h60Var.b();
    }

    @Override // supwisdom.m60
    public int a(long j) {
        int iB = x80.b(this.b, j, false, false);
        if (iB < this.b.length) {
            return iB;
        }
        return -1;
    }

    @Override // supwisdom.m60
    public int b() {
        return this.b.length;
    }

    @Override // supwisdom.m60
    public List<y50> b(long j) {
        return this.f8253a.a(j, this.c, this.d);
    }

    @Override // supwisdom.m60
    public long a(int i) {
        return this.b[i];
    }
}
