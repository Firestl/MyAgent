package com.efs.sdk.h5pagesdk;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static final String TAG = "com.efs.sdk.h5pagesdk.a";
    public static volatile ScheduledThreadPoolExecutor i;
    public static ThreadFactory j = new ThreadFactory() { // from class: com.efs.sdk.h5pagesdk.a.1
        public AtomicInteger k = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("H5ThreadPoolExecutor" + this.k.addAndGet(1));
            return thread;
        }
    };

    public static ScheduledThreadPoolExecutor a() {
        if (i == null) {
            synchronized (a.class) {
                if (i == null) {
                    i = new ScheduledThreadPoolExecutor(4, j);
                }
            }
        }
        return i;
    }

    public static void execute(Runnable runnable) {
        try {
            a().execute(runnable);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
