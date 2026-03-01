package supwisdom;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.schedulers.ScheduledAction;
import rx.internal.util.RxThreadFactory;
import supwisdom.uw1;

/* JADX INFO: compiled from: CachedThreadScheduler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class tx1 extends uw1 implements yx1 {
    public static final long c;
    public static final TimeUnit d = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f9330e;
    public static final a f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadFactory f9331a;
    public final AtomicReference<a> b = new AtomicReference<>(f);

    /* JADX INFO: compiled from: CachedThreadScheduler.java */
    public static final class b extends uw1.a implements ax1 {
        public final a b;
        public final c c;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final sz1 f9336a = new sz1();
        public final AtomicBoolean d = new AtomicBoolean();

        /* JADX INFO: compiled from: CachedThreadScheduler.java */
        public class a implements ax1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ax1 f9337a;

            public a(ax1 ax1Var) {
                this.f9337a = ax1Var;
            }

            @Override // supwisdom.ax1
            public void call() {
                if (b.this.isUnsubscribed()) {
                    return;
                }
                this.f9337a.call();
            }
        }

        public b(a aVar) {
            this.b = aVar;
            this.c = aVar.b();
        }

        @Override // supwisdom.uw1.a
        public yw1 a(ax1 ax1Var) {
            return a(ax1Var, 0L, null);
        }

        @Override // supwisdom.ax1
        public void call() {
            this.b.a(this.c);
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.f9336a.isUnsubscribed();
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            if (this.d.compareAndSet(false, true)) {
                this.c.a(this);
            }
            this.f9336a.unsubscribe();
        }

        public yw1 a(ax1 ax1Var, long j, TimeUnit timeUnit) {
            if (this.f9336a.isUnsubscribed()) {
                return tz1.a();
            }
            ScheduledAction scheduledActionB = this.c.b(new a(ax1Var), j, timeUnit);
            this.f9336a.a(scheduledActionB);
            scheduledActionB.addParent(this.f9336a);
            return scheduledActionB;
        }
    }

    /* JADX INFO: compiled from: CachedThreadScheduler.java */
    public static final class c extends xx1 {
        public long i;

        public c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.i = 0L;
        }

        public void a(long j) {
            this.i = j;
        }

        public long c() {
            return this.i;
        }
    }

    static {
        c cVar = new c(RxThreadFactory.NONE);
        f9330e = cVar;
        cVar.unsubscribe();
        a aVar = new a(null, 0L, null);
        f = aVar;
        aVar.d();
        c = Integer.getInteger("rx.io-scheduler.keepalive", 60).intValue();
    }

    public tx1(ThreadFactory threadFactory) {
        this.f9331a = threadFactory;
        c();
    }

    @Override // supwisdom.uw1
    public uw1.a a() {
        return new b(this.b.get());
    }

    public void c() {
        a aVar = new a(this.f9331a, c, d);
        if (this.b.compareAndSet(f, aVar)) {
            return;
        }
        aVar.d();
    }

    @Override // supwisdom.yx1
    public void shutdown() {
        a aVar;
        a aVar2;
        do {
            aVar = this.b.get();
            aVar2 = f;
            if (aVar == aVar2) {
                return;
            }
        } while (!this.b.compareAndSet(aVar, aVar2));
        aVar.d();
    }

    /* JADX INFO: compiled from: CachedThreadScheduler.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ThreadFactory f9332a;
        public final long b;
        public final ConcurrentLinkedQueue<c> c;
        public final sz1 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ScheduledExecutorService f9333e;
        public final Future<?> f;

        /* JADX INFO: renamed from: supwisdom.tx1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CachedThreadScheduler.java */
        public class ThreadFactoryC0232a implements ThreadFactory {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ThreadFactory f9334a;

            public ThreadFactoryC0232a(a aVar, ThreadFactory threadFactory) {
                this.f9334a = threadFactory;
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread threadNewThread = this.f9334a.newThread(runnable);
                threadNewThread.setName(threadNewThread.getName() + " (Evictor)");
                return threadNewThread;
            }
        }

        /* JADX INFO: compiled from: CachedThreadScheduler.java */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a();
            }
        }

        public a(ThreadFactory threadFactory, long j, TimeUnit timeUnit) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            this.f9332a = threadFactory;
            this.b = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.c = new ConcurrentLinkedQueue<>();
            this.d = new sz1();
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactoryC0232a(this, threadFactory));
                xx1.c(scheduledExecutorServiceNewScheduledThreadPool);
                b bVar = new b();
                long j2 = this.b;
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(bVar, j2, j2, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f9333e = scheduledExecutorServiceNewScheduledThreadPool;
            this.f = scheduledFutureScheduleWithFixedDelay;
        }

        public void a(c cVar) {
            cVar.a(c() + this.b);
            this.c.offer(cVar);
        }

        public c b() {
            if (this.d.isUnsubscribed()) {
                return tx1.f9330e;
            }
            while (!this.c.isEmpty()) {
                c cVarPoll = this.c.poll();
                if (cVarPoll != null) {
                    return cVarPoll;
                }
            }
            c cVar = new c(this.f9332a);
            this.d.a(cVar);
            return cVar;
        }

        public long c() {
            return System.nanoTime();
        }

        public void d() {
            try {
                if (this.f != null) {
                    this.f.cancel(true);
                }
                if (this.f9333e != null) {
                    this.f9333e.shutdownNow();
                }
            } finally {
                this.d.unsubscribe();
            }
        }

        public void a() {
            if (this.c.isEmpty()) {
                return;
            }
            long jC = c();
            for (c cVar : this.c) {
                if (cVar.c() > jC) {
                    return;
                }
                if (this.c.remove(cVar)) {
                    this.d.b(cVar);
                }
            }
        }
    }
}
