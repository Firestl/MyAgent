package com.igexin.base.scheduler;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends ScheduledThreadPoolExecutor {
    public static final AtomicLong b = new AtomicLong();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public InterfaceC0072b f3150a;

    public class a<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
        public BaseTask b;
        public long c;
        public volatile long d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long f3152e;
        public final int f;

        public a(BaseTask baseTask, long j) {
            super(baseTask, null);
            this.b = baseTask;
            this.d = b.a(b.this, baseTask.getInitDelay(), TimeUnit.MILLISECONDS);
            this.f3152e = baseTask.getPeriod();
            this.f = baseTask.getTaskLevel();
            this.c = j;
        }

        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Delayed delayed) {
            Delayed delayed2 = delayed;
            if (delayed2 == this) {
                return 0;
            }
            long delay = getDelay(TimeUnit.NANOSECONDS);
            long delay2 = delayed2.getDelay(TimeUnit.NANOSECONDS);
            if (delayed2 instanceof a) {
                a aVar = (a) delayed2;
                int i = this.f - aVar.f;
                if (delay <= 0 && delay2 <= 0) {
                    if (i > 0) {
                        return -1;
                    }
                    if (i < 0) {
                        return 1;
                    }
                }
                long j = delay - delay2;
                if (j > 0) {
                    return 1;
                }
                if (j < 0 || i > 0) {
                    return -1;
                }
                if (i < 0) {
                    return 1;
                }
                long j2 = this.c - aVar.c;
                if (j2 < 0) {
                    return -1;
                }
                if (j2 > 0) {
                    return 1;
                }
            }
            long j3 = delay - delay2;
            if (j3 < 0) {
                return -1;
            }
            return j3 > 0 ? 1 : 0;
        }

        @Override // java.util.concurrent.FutureTask
        public final void done() {
            this.b.setIsRunning(false);
            b.a(b.this, this.b);
            try {
                get();
                this.b.done();
            } catch (Throwable th) {
                if (th instanceof CancellationException) {
                    this.b.onCancel();
                } else {
                    this.b.onException(th);
                }
            }
        }

        @Override // java.util.concurrent.Delayed
        public final long getDelay(TimeUnit timeUnit) {
            return timeUnit.convert(this.d - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public final boolean isPeriodic() {
            return this.f3152e != 0;
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            boolean zIsPeriodic = isPeriodic();
            if (b.super.isShutdown()) {
                cancel(false);
                return;
            }
            if (!zIsPeriodic) {
                super.run();
                return;
            }
            if (super.runAndReset()) {
                long jConvert = TimeUnit.NANOSECONDS.convert(this.f3152e, TimeUnit.MILLISECONDS);
                if (jConvert > 0) {
                    this.d += jConvert;
                } else {
                    this.d = b.this.a(-jConvert);
                }
                b.super.getQueue().add(this);
            }
        }
    }

    /* JADX INFO: renamed from: com.igexin.base.scheduler.b$b, reason: collision with other inner class name */
    public interface InterfaceC0072b {
        void a(BaseTask baseTask);
    }

    public b() {
        super(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a(long j) {
        Delayed delayed;
        long jNanoTime = System.nanoTime();
        if (j >= 4611686018427387903L && (delayed = (Delayed) super.getQueue().peek()) != null) {
            long delay = delayed.getDelay(TimeUnit.NANOSECONDS);
            if (delay < 0 && j - delay < 0) {
                j = delay + Long.MAX_VALUE;
            }
        }
        return jNanoTime + j;
    }

    public static /* synthetic */ long a(b bVar, long j, TimeUnit timeUnit) {
        if (j < 0) {
            j = 0;
        }
        return bVar.a(timeUnit.toNanos(j));
    }

    public static /* synthetic */ void a(b bVar, BaseTask baseTask) {
        InterfaceC0072b interfaceC0072b = bVar.f3150a;
        if (interfaceC0072b != null) {
            interfaceC0072b.a(baseTask);
        }
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor
    public final <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> runnableScheduledFuture) {
        BaseTask baseTask = (BaseTask) runnable;
        a aVar = new a(baseTask, b.getAndIncrement());
        baseTask.bind(aVar);
        return aVar;
    }
}
