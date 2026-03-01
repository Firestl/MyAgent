package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: SubripSubtitle.java */
/* JADX INFO: loaded from: classes.dex */
public final class e60 implements m60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y50[] f7428a;
    public final long[] b;

    public e60(y50[] y50VarArr, long[] jArr) {
        this.f7428a = y50VarArr;
        this.b = jArr;
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
        int iA = x80.a(this.b, j, true, false);
        if (iA != -1) {
            y50[] y50VarArr = this.f7428a;
            if (y50VarArr[iA] != null) {
                return Collections.singletonList(y50VarArr[iA]);
            }
        }
        return Collections.emptyList();
    }

    @Override // supwisdom.m60
    public long a(int i) {
        e80.a(i >= 0);
        e80.a(i < this.b.length);
        return this.b[i];
    }
}
