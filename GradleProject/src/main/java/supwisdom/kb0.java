package supwisdom;

import android.util.SparseArray;

/* JADX INFO: compiled from: TimestampAdjusterProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class kb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray<u80> f8156a = new SparseArray<>();

    public u80 a(int i) {
        u80 u80Var = this.f8156a.get(i);
        if (u80Var != null) {
            return u80Var;
        }
        u80 u80Var2 = new u80(Long.MAX_VALUE);
        this.f8156a.put(i, u80Var2);
        return u80Var2;
    }

    public void a() {
        this.f8156a.clear();
    }
}
