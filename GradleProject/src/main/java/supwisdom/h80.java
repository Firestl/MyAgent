package supwisdom;

/* JADX INFO: compiled from: ConditionVariable.java */
/* JADX INFO: loaded from: classes.dex */
public final class h80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f7816a;

    public synchronized boolean a() {
        if (this.f7816a) {
            return false;
        }
        this.f7816a = true;
        notifyAll();
        return true;
    }

    public synchronized boolean b() {
        boolean z;
        z = this.f7816a;
        this.f7816a = false;
        return z;
    }

    public synchronized void c() throws InterruptedException {
        while (!this.f7816a) {
            wait();
        }
    }
}
