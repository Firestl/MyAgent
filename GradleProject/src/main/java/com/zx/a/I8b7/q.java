package com.zx.a.I8b7;

import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class q implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AtomicInteger f6265a = new AtomicInteger(0);

    public class a implements Thread.UncaughtExceptionHandler {
        public a(q qVar) {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
        }
    }

    public q(r rVar) {
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        StringBuilder sbA = m2.a("ZXHttpClient dispatcher's thread");
        sbA.append(this.f6265a.getAndIncrement());
        thread.setName(sbA.toString());
        thread.setUncaughtExceptionHandler(new a(this));
        return thread;
    }
}
