package com.g.gysdk.a;

import android.os.Handler;
import android.os.Looper;
import java.lang.Thread;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class al {
    public static final AtomicInteger d = new AtomicInteger(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f1962a;
    public final ThreadPoolExecutor b;
    public final ThreadPoolExecutor c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final al f1968a = new al();
    }

    public enum b {
        Unknown,
        UI,
        Queue,
        Work,
        Current
    }

    public al() {
        this.f1962a = new Handler(Looper.getMainLooper());
        this.b = new ThreadPoolExecutor(4, 4, 3L, TimeUnit.MINUTES, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.g.gysdk.a.al.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("Gy-Work-Thread-" + al.d.getAndIncrement());
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.g.gysdk.a.al.1.1
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public void uncaughtException(Thread thread2, Throwable th) {
                        ak.e("caught an exception from " + thread2.getName(), th);
                    }
                });
                return thread;
            }
        });
        this.c = new ThreadPoolExecutor(1, 1, 3L, TimeUnit.MINUTES, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.g.gysdk.a.al.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("Gy-Queue-Thread");
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.g.gysdk.a.al.2.1
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public void uncaughtException(Thread thread2, Throwable th) {
                        ak.e("caught an exception from " + thread2.getName(), th);
                    }
                });
                return thread;
            }
        });
    }

    public static void a(b bVar, Runnable runnable) {
        a(bVar, runnable, false);
    }

    public static void a(final b bVar, final Runnable runnable, long j) {
        if (j <= 0) {
            a(bVar, runnable, false);
        } else {
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() { // from class: com.g.gysdk.a.al.3
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    timer.cancel();
                    al.a(bVar, runnable, true);
                }
            }, j);
        }
    }

    public static void a(b bVar, Runnable runnable, boolean z) {
        try {
            a.f1968a.b(bVar, runnable, z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private b b() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return b.UI;
        }
        String name = Thread.currentThread().getName();
        if (name.equals("Gy-Queue-Thread")) {
            return b.Queue;
        }
        if (name.startsWith("Gy-Work-Thread-")) {
            return b.Work;
        }
        return b.Unknown;
    }

    private void b(b bVar, Runnable runnable, boolean z) {
        ThreadPoolExecutor threadPoolExecutor;
        Handler handler;
        try {
            b bVarB = b();
            if (!z && (bVar == b.Current || bVar == bVarB)) {
                runnable.run();
                return;
            }
            if (bVar != b.UI) {
                if (bVar == b.Queue) {
                    threadPoolExecutor = this.c;
                } else if (bVar == b.Work) {
                    threadPoolExecutor = this.b;
                } else if (bVarB == b.UI) {
                    handler = this.f1962a;
                } else {
                    threadPoolExecutor = bVarB == b.Queue ? this.c : this.b;
                }
                threadPoolExecutor.execute(runnable);
                return;
            }
            handler = this.f1962a;
            handler.post(runnable);
        } catch (Throwable th) {
            aj.a("runOnThreadInner exception:", th);
        }
    }
}
