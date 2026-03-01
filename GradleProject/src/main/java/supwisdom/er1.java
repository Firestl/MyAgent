package supwisdom;

import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class er1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ScheduledExecutorService f7518a = new a(1);
    public static final ScheduledThreadPoolExecutor b = new a(1);

    public static class a extends ScheduledThreadPoolExecutor {
        public a(int i) {
            super(i);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void afterExecute(Runnable runnable, Throwable th) {
            super.afterExecute(runnable, th);
            try {
                ((Future) runnable).get();
                if (th == null) {
                    return;
                }
                Log.e("TimerUtil", "afterExecute t=" + th.fillInStackTrace());
                throw new RuntimeException("Exception: afterExecute:" + th);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                Log.e("TimerUtil", "---InterruptedException：线程池中发现异常，被中断---" + e2.getMessage() + "--");
                throw new IllegalStateException("InterruptedException：线程池中发现异常，被中断");
            } catch (ExecutionException e3) {
                e3.printStackTrace();
                Log.e("TimerUtil", "---ExecutionException：线程池中发现异常，被中断---" + e3.getMessage() + "--");
                throw new IllegalStateException("ExecutionException：线程池中发现异常，被中断");
            }
        }
    }

    public static void a(Runnable runnable, long j) {
        ((ScheduledThreadPoolExecutor) f7518a).schedule(new cr1(runnable), j, TimeUnit.MILLISECONDS);
    }
}
