package supwisdom;

import android.content.Context;
import android.util.SparseIntArray;
import supwisdom.mc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class fg0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseIntArray f7602a = new SparseIntArray();
    public hc0 b;

    public fg0(hc0 hc0Var) {
        pf0.a(hc0Var);
        this.b = hc0Var;
    }

    public final int a(Context context, mc0.f fVar) {
        pf0.a(context);
        pf0.a(fVar);
        int iA = 0;
        if (!fVar.c()) {
            return 0;
        }
        int iD = fVar.d();
        int iA2 = a(context, iD);
        if (iA2 != -1) {
            return iA2;
        }
        int i = 0;
        while (true) {
            if (i >= this.f7602a.size()) {
                iA = iA2;
                break;
            }
            int iKeyAt = this.f7602a.keyAt(i);
            if (iKeyAt > iD && this.f7602a.get(iKeyAt) == 0) {
                break;
            }
            i++;
        }
        if (iA == -1) {
            iA = this.b.a(context, iD);
        }
        this.f7602a.put(iD, iA);
        return iA;
    }

    public final int a(Context context, int i) {
        return this.f7602a.get(i, -1);
    }

    public final void a() {
        this.f7602a.clear();
    }
}
