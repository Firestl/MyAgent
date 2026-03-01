package supwisdom;

import java.util.concurrent.ThreadFactory;
import rx.internal.util.RxThreadFactory;

/* JADX INFO: compiled from: RxJavaSchedulersHook.java */
/* JADX INFO: loaded from: classes3.dex */
public class mz1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final mz1 f8458a = new mz1();

    public static uw1 a(ThreadFactory threadFactory) {
        if (threadFactory != null) {
            return new ux1(threadFactory);
        }
        throw new NullPointerException("threadFactory == null");
    }

    public static uw1 b(ThreadFactory threadFactory) {
        if (threadFactory != null) {
            return new tx1(threadFactory);
        }
        throw new NullPointerException("threadFactory == null");
    }

    public static uw1 c(ThreadFactory threadFactory) {
        if (threadFactory != null) {
            return new wx1(threadFactory);
        }
        throw new NullPointerException("threadFactory == null");
    }

    public static uw1 d() {
        return a(new RxThreadFactory("RxComputationScheduler-"));
    }

    public static uw1 e() {
        return b(new RxThreadFactory("RxIoScheduler-"));
    }

    public static uw1 f() {
        return c(new RxThreadFactory("RxNewThreadScheduler-"));
    }

    public static mz1 g() {
        return f8458a;
    }

    @Deprecated
    public ax1 a(ax1 ax1Var) {
        return ax1Var;
    }

    public uw1 a() {
        return null;
    }

    public uw1 b() {
        return null;
    }

    public uw1 c() {
        return null;
    }
}
