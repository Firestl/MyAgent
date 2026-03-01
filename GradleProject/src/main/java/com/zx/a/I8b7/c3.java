package com.zx.a.I8b7;

import java.lang.Thread;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class c3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ThreadPoolExecutor f6203a;
    public ThreadPoolExecutor b;
    public ThreadPoolExecutor c;
    public ThreadPoolExecutor d;

    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: com.zx.a.I8b7.c3$a$a, reason: collision with other inner class name */
        public class C0140a implements Thread.UncaughtExceptionHandler {
            public C0140a(a aVar) {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                StringBuilder sbA = m2.a("caught an exception from ");
                sbA.append(thread.getName());
                y1.a(sbA.toString(), th);
            }
        }

        public a(c3 c3Var) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ZX-Api-Thread");
            thread.setUncaughtExceptionHandler(new C0140a(this));
            return thread;
        }
    }

    public class b implements ThreadFactory {

        public class a implements Thread.UncaughtExceptionHandler {
            public a(b bVar) {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                StringBuilder sbA = m2.a("caught an exception from ");
                sbA.append(thread.getName());
                y1.a(sbA.toString(), th);
            }
        }

        public b(c3 c3Var) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ZX-Api-ThreadV2");
            thread.setUncaughtExceptionHandler(new a(this));
            return thread;
        }
    }

    public class c implements ThreadFactory {

        public class a implements Thread.UncaughtExceptionHandler {
            public a(c cVar) {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                StringBuilder sbA = m2.a("caught an exception from ");
                sbA.append(thread.getName());
                y1.a(sbA.toString(), th);
            }
        }

        public c(c3 c3Var) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ZX-Api-ThreadV3");
            thread.setUncaughtExceptionHandler(new a(this));
            return thread;
        }
    }

    public class d implements ThreadFactory {

        public class a implements Thread.UncaughtExceptionHandler {
            public a(d dVar) {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                StringBuilder sbA = m2.a("caught an exception from ");
                sbA.append(thread.getName());
                y1.a(sbA.toString(), th);
            }
        }

        public d(c3 c3Var) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ZX-Api-ThreadV4");
            thread.setUncaughtExceptionHandler(new a(this));
            return thread;
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c3 f6204a = new c3();
    }

    public c3() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.f6203a = new ThreadPoolExecutor(1, 1, 0L, timeUnit, new LinkedBlockingQueue(), new a(this));
        this.b = new ThreadPoolExecutor(1, 1, 0L, timeUnit, new LinkedBlockingQueue(), new b(this));
        this.c = new ThreadPoolExecutor(1, 1, 0L, timeUnit, new LinkedBlockingQueue(), new c(this));
        this.d = new ThreadPoolExecutor(1, 1, 0L, timeUnit, new LinkedBlockingQueue(), new d(this));
    }
}
