package java9.util.concurrent;

import sun.misc.Unsafe;
import supwisdom.mq1;
import supwisdom.nq1;
import supwisdom.qq1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CountedCompleter<T> extends ForkJoinTask<T> {
    public static final long PENDING;
    public static final Unsafe U;
    public static final long serialVersionUID = 5232453752276485070L;
    public final CountedCompleter<?> completer;
    public volatile int pending;

    static {
        Unsafe unsafe = qq1.f8946a;
        U = unsafe;
        try {
            PENDING = unsafe.objectFieldOffset(CountedCompleter.class.getDeclaredField("pending"));
        } catch (Exception e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    public CountedCompleter(CountedCompleter<?> countedCompleter, int i) {
        this.completer = countedCompleter;
        this.pending = i;
    }

    public final void addToPendingCount(int i) {
        Unsafe unsafe;
        long j;
        int i2;
        do {
            unsafe = U;
            j = PENDING;
            i2 = this.pending;
        } while (!unsafe.compareAndSwapInt(this, j, i2, i2 + i));
    }

    public final boolean compareAndSetPendingCount(int i, int i2) {
        return U.compareAndSwapInt(this, PENDING, i, i2);
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public void complete(T t) {
        setRawResult(t);
        onCompletion(this);
        quietlyComplete();
        CountedCompleter<?> countedCompleter = this.completer;
        if (countedCompleter != null) {
            countedCompleter.tryComplete();
        }
    }

    public abstract void compute();

    public final int decrementPendingCountUnlessZero() {
        int i;
        do {
            i = this.pending;
            if (i == 0) {
                break;
            }
        } while (!U.compareAndSwapInt(this, PENDING, i, i - 1));
        return i;
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public final boolean exec() {
        compute();
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final CountedCompleter<?> firstComplete() {
        int i;
        do {
            i = this.pending;
            if (i == 0) {
                return this;
            }
        } while (!U.compareAndSwapInt(this, PENDING, i, i - 1));
        return null;
    }

    public final CountedCompleter<?> getCompleter() {
        return this.completer;
    }

    public final int getPendingCount() {
        return this.pending;
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public T getRawResult() {
        return null;
    }

    public final CountedCompleter<?> getRoot() {
        CountedCompleter countedCompleter = this;
        while (true) {
            CountedCompleter countedCompleter2 = countedCompleter.completer;
            if (countedCompleter2 == null) {
                return countedCompleter;
            }
            countedCompleter = countedCompleter2;
        }
    }

    public final void helpComplete(int i) {
        if (i <= 0 || this.status < 0) {
            return;
        }
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof nq1)) {
            mq1.n.a((CountedCompleter<?>) this, i);
        } else {
            nq1 nq1Var = (nq1) threadCurrentThread;
            nq1Var.f8555a.a(nq1Var.b, (CountedCompleter<?>) this, i);
        }
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public void internalPropagateException(Throwable th) {
        CountedCompleter<?> countedCompleter;
        CountedCompleter countedCompleter2 = this;
        CountedCompleter countedCompleter3 = countedCompleter2;
        while (countedCompleter2.onExceptionalCompletion(th, countedCompleter3) && (countedCompleter = countedCompleter2.completer) != null && countedCompleter.status >= 0 && countedCompleter.recordExceptionalCompletion(th) == Integer.MIN_VALUE) {
            countedCompleter3 = countedCompleter2;
            countedCompleter2 = countedCompleter;
        }
    }

    public final CountedCompleter<?> nextComplete() {
        CountedCompleter<?> countedCompleter = this.completer;
        if (countedCompleter != null) {
            return countedCompleter.firstComplete();
        }
        quietlyComplete();
        return null;
    }

    public void onCompletion(CountedCompleter<?> countedCompleter) {
    }

    public boolean onExceptionalCompletion(Throwable th, CountedCompleter<?> countedCompleter) {
        return true;
    }

    public final void propagateCompletion() {
        CountedCompleter countedCompleter = this;
        while (true) {
            int i = countedCompleter.pending;
            if (i == 0) {
                CountedCompleter<?> countedCompleter2 = countedCompleter.completer;
                if (countedCompleter2 == null) {
                    countedCompleter.quietlyComplete();
                    return;
                }
                countedCompleter = countedCompleter2;
            } else {
                if (U.compareAndSwapInt(countedCompleter, PENDING, i, i - 1)) {
                    return;
                }
            }
        }
    }

    public final void quietlyCompleteRoot() {
        CountedCompleter countedCompleter = this;
        while (true) {
            CountedCompleter<?> countedCompleter2 = countedCompleter.completer;
            if (countedCompleter2 == null) {
                countedCompleter.quietlyComplete();
                return;
            }
            countedCompleter = countedCompleter2;
        }
    }

    public final void setPendingCount(int i) {
        this.pending = i;
    }

    @Override // java9.util.concurrent.ForkJoinTask
    public void setRawResult(T t) {
    }

    public final void tryComplete() {
        CountedCompleter countedCompleter = this;
        CountedCompleter countedCompleter2 = countedCompleter;
        while (true) {
            int i = countedCompleter.pending;
            if (i == 0) {
                countedCompleter.onCompletion(countedCompleter2);
                CountedCompleter<?> countedCompleter3 = countedCompleter.completer;
                if (countedCompleter3 == null) {
                    countedCompleter.quietlyComplete();
                    return;
                } else {
                    countedCompleter2 = countedCompleter;
                    countedCompleter = countedCompleter3;
                }
            } else {
                if (U.compareAndSwapInt(countedCompleter, PENDING, i, i - 1)) {
                    return;
                }
            }
        }
    }

    public CountedCompleter(CountedCompleter<?> countedCompleter) {
        this.completer = countedCompleter;
    }

    public CountedCompleter() {
        this.completer = null;
    }
}
