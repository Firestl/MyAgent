package supwisdom;

import android.os.Handler;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: RequestExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class y9 {

    /* JADX INFO: compiled from: RequestExecutor.java */
    public static class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f9853a;
        public int b;

        /* JADX INFO: renamed from: supwisdom.y9$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: RequestExecutor.java */
        public static class C0235a extends Thread {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final int f9854a;

            public C0235a(Runnable runnable, String str, int i) {
                super(runnable, str);
                this.f9854a = i;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.f9854a);
                super.run();
            }
        }

        public a(String str, int i) {
            this.f9853a = str;
            this.b = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new C0235a(runnable, this.f9853a, this.b);
        }
    }

    /* JADX INFO: compiled from: RequestExecutor.java */
    public static class b<T> implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Callable<T> f9855a;
        public fa<T> b;
        public Handler c;

        /* JADX INFO: compiled from: RequestExecutor.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ fa f9856a;
            public final /* synthetic */ Object b;

            public a(b bVar, fa faVar, Object obj) {
                this.f9856a = faVar;
                this.b = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                this.f9856a.a(this.b);
            }
        }

        public b(Handler handler, Callable<T> callable, fa<T> faVar) {
            this.f9855a = callable;
            this.b = faVar;
            this.c = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            T tCall;
            try {
                tCall = this.f9855a.call();
            } catch (Exception unused) {
                tCall = null;
            }
            this.c.post(new a(this, this.b, tCall));
        }
    }

    public static <T> void a(Executor executor, Callable<T> callable, fa<T> faVar) {
        executor.execute(new b(t9.a(), callable, faVar));
    }

    public static <T> T a(ExecutorService executorService, Callable<T> callable, int i) throws InterruptedException {
        try {
            return executorService.submit(callable).get(i, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            throw e2;
        } catch (ExecutionException e3) {
            throw new RuntimeException(e3);
        } catch (TimeoutException unused) {
            throw new InterruptedException("timeout");
        }
    }

    public static ThreadPoolExecutor a(String str, int i, int i2) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, i2, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new a(str, i));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}
