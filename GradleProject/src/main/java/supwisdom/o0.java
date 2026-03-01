package supwisdom;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: OnBackPressedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8594a;
    public CopyOnWriteArrayList<n0> b = new CopyOnWriteArrayList<>();

    public o0(boolean z) {
        this.f8594a = z;
    }

    public abstract void a();

    public final void a(boolean z) {
        this.f8594a = z;
    }

    public final boolean b() {
        return this.f8594a;
    }

    public final void c() {
        Iterator<n0> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    public void a(n0 n0Var) {
        this.b.add(n0Var);
    }

    public void b(n0 n0Var) {
        this.b.remove(n0Var);
    }
}
