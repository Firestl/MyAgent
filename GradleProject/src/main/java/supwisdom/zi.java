package supwisdom;

import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: WeakRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public class zi implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference<Runnable> f10005a;

    public zi(Runnable runnable) {
        this.f10005a = new WeakReference<>(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.f10005a.get();
        if (runnable != null) {
            runnable.run();
        }
    }
}
