package supwisdom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.schedulers.ScheduledAction;
import rx.internal.util.RxThreadFactory;
import supwisdom.uw1;

/* JADX INFO: compiled from: NewThreadWorker.java */
/* JADX INFO: loaded from: classes3.dex */
public class xx1 extends uw1.a implements yw1 {
    public static final boolean c;
    public static volatile Object g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ScheduledExecutorService f9824a;
    public volatile boolean b;
    public static final Object h = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> f9823e = new ConcurrentHashMap<>();
    public static final AtomicReference<ScheduledExecutorService> f = new AtomicReference<>();
    public static final int d = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();

    /* JADX INFO: compiled from: NewThreadWorker.java */
    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            xx1.b();
        }
    }

    static {
        boolean z = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int iA = cy1.a();
        c = !z && (iA == 0 || iA >= 21);
    }

    public xx1(ThreadFactory threadFactory) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!c(scheduledExecutorServiceNewScheduledThreadPool) && (scheduledExecutorServiceNewScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            a((ScheduledThreadPoolExecutor) scheduledExecutorServiceNewScheduledThreadPool);
        }
        this.f9824a = scheduledExecutorServiceNewScheduledThreadPool;
    }

    public static void a(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (true) {
            if (f.get() != null) {
                break;
            }
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
            if (f.compareAndSet(null, scheduledExecutorServiceNewScheduledThreadPool)) {
                a aVar = new a();
                int i = d;
                scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(aVar, i, i, TimeUnit.MILLISECONDS);
                break;
            }
            scheduledExecutorServiceNewScheduledThreadPool.shutdownNow();
        }
        f9823e.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    public static void b() {
        try {
            Iterator<ScheduledThreadPoolExecutor> it = f9823e.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor next = it.next();
                if (next.isShutdown()) {
                    it.remove();
                } else {
                    next.purge();
                }
            }
        } catch (Throwable th) {
            zw1.b(th);
            iz1.a(th);
        }
    }

    public static boolean c(ScheduledExecutorService scheduledExecutorService) {
        Method methodB;
        if (c) {
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = g;
                if (obj == h) {
                    return false;
                }
                if (obj == null) {
                    methodB = b(scheduledExecutorService);
                    g = methodB != null ? methodB : h;
                } else {
                    methodB = (Method) obj;
                }
            } else {
                methodB = b(scheduledExecutorService);
            }
            if (methodB != null) {
                try {
                    methodB.invoke(scheduledExecutorService, true);
                    return true;
                } catch (IllegalAccessException e2) {
                    iz1.a(e2);
                } catch (IllegalArgumentException e3) {
                    iz1.a(e3);
                } catch (InvocationTargetException e4) {
                    iz1.a(e4);
                }
            }
        }
        return false;
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return this.b;
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        this.b = true;
        this.f9824a.shutdownNow();
        a(this.f9824a);
    }

    public static void a(ScheduledExecutorService scheduledExecutorService) {
        f9823e.remove(scheduledExecutorService);
    }

    @Override // supwisdom.uw1.a
    public yw1 a(ax1 ax1Var) {
        return a(ax1Var, 0L, null);
    }

    public static Method b(ScheduledExecutorService scheduledExecutorService) {
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public yw1 a(ax1 ax1Var, long j, TimeUnit timeUnit) {
        if (this.b) {
            return tz1.a();
        }
        return b(ax1Var, j, timeUnit);
    }

    public ScheduledAction a(ax1 ax1Var, long j, TimeUnit timeUnit, fy1 fy1Var) {
        Future<?> futureSchedule;
        ScheduledAction scheduledAction = new ScheduledAction(iz1.a(ax1Var), fy1Var);
        fy1Var.a(scheduledAction);
        if (j <= 0) {
            futureSchedule = this.f9824a.submit(scheduledAction);
        } else {
            futureSchedule = this.f9824a.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(futureSchedule);
        return scheduledAction;
    }

    public ScheduledAction b(ax1 ax1Var, long j, TimeUnit timeUnit) {
        Future<?> futureSchedule;
        ScheduledAction scheduledAction = new ScheduledAction(iz1.a(ax1Var));
        if (j <= 0) {
            futureSchedule = this.f9824a.submit(scheduledAction);
        } else {
            futureSchedule = this.f9824a.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(futureSchedule);
        return scheduledAction;
    }
}
