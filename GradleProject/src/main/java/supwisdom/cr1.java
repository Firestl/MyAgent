package supwisdom;

/* JADX INFO: loaded from: classes3.dex */
public class cr1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Runnable f7250a;

    public cr1(Runnable runnable) {
        this.f7250a = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Runnable runnable = this.f7250a;
            if (runnable != null) {
                runnable.run();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            throw new IllegalStateException("thread pool failed:" + e2.toString());
        }
    }
}
