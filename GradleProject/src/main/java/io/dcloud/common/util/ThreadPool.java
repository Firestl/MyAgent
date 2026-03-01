package io.dcloud.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class ThreadPool {
    public static final int MAX_COUNT = 3;
    public ExecutorService newFixedThreadPool;
    public ExecutorService singleThreadPool;
    public ThreadPoolExecutor threadPool;

    public static class ThreadPoolHolder {
        public static ThreadPool mInstance = new ThreadPool();
    }

    public static ThreadPool self() {
        return ThreadPoolHolder.mInstance;
    }

    public synchronized void addSingleThreadTask(Runnable runnable) {
        ExecutorService executorService = this.singleThreadPool;
        if (executorService != null) {
            executorService.execute(runnable);
        }
    }

    public synchronized void addThreadTask(Runnable runnable) {
        addThreadTask(runnable, false);
    }

    public ThreadPool() {
        this.threadPool = null;
        this.singleThreadPool = null;
        this.newFixedThreadPool = null;
        this.threadPool = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.newFixedThreadPool = new ThreadPoolExecutor(3, 50, 300L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.singleThreadPool = Executors.newSingleThreadExecutor();
    }

    public synchronized void addThreadTask(Runnable runnable, boolean z) {
        if (z) {
            this.newFixedThreadPool.execute(runnable);
        } else {
            this.threadPool.execute(runnable);
        }
    }
}
