package cn.com.chinatelecom.account.api.c;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class d extends ThreadPoolExecutor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BlockingQueue<Runnable> f1497a = new LinkedBlockingQueue(256);
    public static final ThreadFactory b = new ThreadFactory() { // from class: cn.com.chinatelecom.account.api.c.d.1

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f1498a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable);
        }
    };

    public d() {
        this(5);
    }

    public d(int i) {
        this(i, i * 2, 1L, TimeUnit.SECONDS, f1497a, b);
    }

    public d(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory);
    }

    public void a(e eVar) {
        execute(eVar);
    }
}
