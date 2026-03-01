package com.efs.sdk.net.a;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1907a = "com.efs.sdk.net.a.b";
    public static volatile ScheduledThreadPoolExecutor b;
    public static ThreadFactory c = new ThreadFactory() { // from class: com.efs.sdk.net.a.b.1

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AtomicInteger f1908a = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("NetThreadPoolExecutor" + this.f1908a.addAndGet(1));
            return thread;
        }
    };

    public static ScheduledThreadPoolExecutor a() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new ScheduledThreadPoolExecutor(4, c);
                }
            }
        }
        return b;
    }

    public static void a(Runnable runnable) {
        try {
            a().execute(runnable);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
