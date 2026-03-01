package com.umeng.analytics.pro;

import com.umeng.commonsdk.debug.UMRTLog;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: UMExecutor.java */
/* JADX INFO: loaded from: classes2.dex */
public class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5179a = "UMExecutor";
    public static volatile ScheduledThreadPoolExecutor b;
    public static final ThreadFactory c = new ThreadFactory() { // from class: com.umeng.analytics.pro.au.1

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f5180a = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ccg-" + this.f5180a.incrementAndGet());
        }
    };

    public static ScheduledThreadPoolExecutor a() {
        if (b == null) {
            synchronized (au.class) {
                if (b == null) {
                    b = new ScheduledThreadPoolExecutor(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors(), 4)), c);
                    b.setKeepAliveTime(3L, TimeUnit.SECONDS);
                    b.allowCoreThreadTimeOut(true);
                }
            }
        }
        return b;
    }

    public static void a(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            a().schedule(runnable, j, timeUnit);
        } catch (Throwable th) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "schedule error:" + th.getMessage());
        }
    }
}
