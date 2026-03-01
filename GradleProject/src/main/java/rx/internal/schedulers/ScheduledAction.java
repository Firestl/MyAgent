package rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.exceptions.OnErrorNotImplementedException;
import supwisdom.ax1;
import supwisdom.fy1;
import supwisdom.iz1;
import supwisdom.sz1;
import supwisdom.yw1;

/* JADX INFO: loaded from: classes3.dex */
public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, yw1 {
    public static final long serialVersionUID = -3962399486978279857L;
    public final ax1 action;
    public final fy1 cancel;

    public final class a implements yw1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Future<?> f6821a;

        public a(Future<?> future) {
            this.f6821a = future;
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.f6821a.isCancelled();
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.f6821a.cancel(true);
            } else {
                this.f6821a.cancel(false);
            }
        }
    }

    public static final class b extends AtomicBoolean implements yw1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ScheduledAction f6822a;
        public final fy1 b;

        public b(ScheduledAction scheduledAction, fy1 fy1Var) {
            this.f6822a = scheduledAction;
            this.b = fy1Var;
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.f6822a.isUnsubscribed();
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.b.b(this.f6822a);
            }
        }
    }

    public static final class c extends AtomicBoolean implements yw1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ScheduledAction f6823a;
        public final sz1 b;

        public c(ScheduledAction scheduledAction, sz1 sz1Var) {
            this.f6823a = scheduledAction;
            this.b = sz1Var;
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.f6823a.isUnsubscribed();
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.b.b(this.f6823a);
            }
        }
    }

    public ScheduledAction(ax1 ax1Var) {
        this.action = ax1Var;
        this.cancel = new fy1();
    }

    public void add(yw1 yw1Var) {
        this.cancel.a(yw1Var);
    }

    public void addParent(sz1 sz1Var) {
        this.cancel.a(new c(this, sz1Var));
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                lazySet(Thread.currentThread());
                this.action.call();
            } catch (OnErrorNotImplementedException e2) {
                signalError(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e2));
            } catch (Throwable th) {
                signalError(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th));
            }
        } finally {
            unsubscribe();
        }
    }

    public void signalError(Throwable th) {
        iz1.a(th);
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        if (this.cancel.isUnsubscribed()) {
            return;
        }
        this.cancel.unsubscribe();
    }

    public void add(Future<?> future) {
        this.cancel.a(new a(future));
    }

    public void addParent(fy1 fy1Var) {
        this.cancel.a(new b(this, fy1Var));
    }

    public ScheduledAction(ax1 ax1Var, sz1 sz1Var) {
        this.action = ax1Var;
        this.cancel = new fy1(new c(this, sz1Var));
    }

    public ScheduledAction(ax1 ax1Var, fy1 fy1Var) {
        this.action = ax1Var;
        this.cancel = new fy1(new b(this, fy1Var));
    }
}
