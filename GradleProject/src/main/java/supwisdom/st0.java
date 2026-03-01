package supwisdom;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class st0 {
    public static final st0 c = new st0();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Executor f9211a;
    public final Object b = new Object();

    public static class a implements Executor {
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static Executor b() {
        st0 st0Var = c;
        if (st0Var.f9211a == null) {
            synchronized (st0Var.b) {
                if (st0Var.f9211a == null) {
                    st0Var.f9211a = new a();
                }
            }
        }
        return st0Var.f9211a;
    }

    public static ExecutorService c() {
        return c.a();
    }

    public final ExecutorService a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}
