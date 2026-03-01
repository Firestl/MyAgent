package supwisdom;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DefaultTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class y3 extends z3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f9841a = new Object();
    public final ExecutorService b = Executors.newFixedThreadPool(2, new a(this));
    public volatile Handler c;

    /* JADX INFO: compiled from: DefaultTaskExecutor.java */
    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f9842a = new AtomicInteger(0);

        public a(y3 y3Var) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f9842a.getAndIncrement())));
            return thread;
        }
    }

    @Override // supwisdom.z3
    public void a(Runnable runnable) {
        this.b.execute(runnable);
    }

    @Override // supwisdom.z3
    public void b(Runnable runnable) {
        if (this.c == null) {
            synchronized (this.f9841a) {
                if (this.c == null) {
                    this.c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.c.post(runnable);
    }

    @Override // supwisdom.z3
    public boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
