package supwisdom;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: Ping.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ig1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CountDownLatch f7954a = new CountDownLatch(1);
    public long b = -1;
    public long c = -1;

    public void a() {
        if (this.c == -1) {
            long j = this.b;
            if (j != -1) {
                this.c = j - 1;
                this.f7954a.countDown();
                return;
            }
        }
        throw new IllegalStateException();
    }

    public void b() {
        if (this.c != -1 || this.b == -1) {
            throw new IllegalStateException();
        }
        this.c = System.nanoTime();
        this.f7954a.countDown();
    }

    public void c() {
        if (this.b != -1) {
            throw new IllegalStateException();
        }
        this.b = System.nanoTime();
    }
}
