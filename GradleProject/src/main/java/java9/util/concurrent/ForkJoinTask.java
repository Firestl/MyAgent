package java9.util.concurrent;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;
import supwisdom.jq1;
import supwisdom.mq1;
import supwisdom.nq1;
import supwisdom.qq1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ForkJoinTask<V> implements Future<V>, Serializable {
    public static final int CANCELLED = -1073741824;
    public static final int DONE_MASK = -268435456;
    public static final int EXCEPTIONAL = Integer.MIN_VALUE;
    public static final int NORMAL = -268435456;
    public static final int SIGNAL = 65536;
    public static final int SMASK = 65535;
    public static final long STATUS;
    public static final Unsafe U;
    public static final d[] exceptionTable = new d[32];
    public static final ReentrantLock exceptionTableLock = new ReentrantLock();
    public static final ReferenceQueue<ForkJoinTask<?>> exceptionTableRefQueue = new ReferenceQueue<>();
    public static final long serialVersionUID = -7721805057305804111L;
    public volatile int status;

    public static final class a<T> extends ForkJoinTask<T> implements RunnableFuture<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Callable<? extends T> f6776a;
        public T b;

        public a(Callable<? extends T> callable) {
            jq1.a(callable);
            this.f6776a = callable;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            try {
                this.b = this.f6776a.call();
                return true;
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final T getRawResult() {
            return this.b;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() throws Throwable {
            invoke();
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(T t) {
            this.b = t;
        }

        public String toString() {
            return super.toString() + "[Wrapped task = " + this.f6776a + Operators.ARRAY_END_STR;
        }
    }

    public static final class b<T> extends ForkJoinTask<T> implements RunnableFuture<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f6777a;
        public T b;

        public b(Runnable runnable, T t) {
            jq1.a(runnable);
            this.f6777a = runnable;
            this.b = t;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.f6777a.run();
            return true;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final T getRawResult() {
            return this.b;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() throws Throwable {
            invoke();
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(T t) {
            this.b = t;
        }

        public String toString() {
            return super.toString() + "[Wrapped task = " + this.f6777a + Operators.ARRAY_END_STR;
        }
    }

    public static final class c extends ForkJoinTask<Void> implements RunnableFuture<Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f6778a;

        public c(Runnable runnable) {
            jq1.a(runnable);
            this.f6778a = runnable;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.f6778a.run();
            return true;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() throws Throwable {
            invoke();
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(Void r1) {
        }

        public String toString() {
            return super.toString() + "[Wrapped task = " + this.f6778a + Operators.ARRAY_END_STR;
        }
    }

    public static final class d extends WeakReference<ForkJoinTask<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Throwable f6779a;
        public d b;
        public final long c;
        public final int d;

        public d(ForkJoinTask<?> forkJoinTask, Throwable th, d dVar, ReferenceQueue<ForkJoinTask<?>> referenceQueue) {
            super(forkJoinTask, referenceQueue);
            this.f6779a = th;
            this.b = dVar;
            this.c = Thread.currentThread().getId();
            this.d = System.identityHashCode(forkJoinTask);
        }
    }

    public static final class e extends ForkJoinTask<Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f6780a;

        public e(Runnable runnable) {
            jq1.a(runnable);
            this.f6780a = runnable;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.f6780a.run();
            return true;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public void internalPropagateException(Throwable th) throws Throwable {
            ForkJoinTask.rethrow(th);
        }

        @Override // java9.util.concurrent.ForkJoinTask
        public final void setRawResult(Void r1) {
        }
    }

    static {
        Unsafe unsafe = qq1.f8946a;
        U = unsafe;
        try {
            STATUS = unsafe.objectFieldOffset(ForkJoinTask.class.getDeclaredField("status"));
        } catch (Exception e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    public static ForkJoinTask<?> adapt(Runnable runnable) {
        return new c(runnable);
    }

    public static final void cancelIgnoringExceptions(ForkJoinTask<?> forkJoinTask) {
        if (forkJoinTask == null || forkJoinTask.status < 0) {
            return;
        }
        try {
            forkJoinTask.cancel(false);
        } catch (Throwable unused) {
        }
    }

    private void clearExceptionalCompletion() {
        int iIdentityHashCode = System.identityHashCode(this);
        ReentrantLock reentrantLock = exceptionTableLock;
        reentrantLock.lock();
        try {
            d[] dVarArr = exceptionTable;
            int length = iIdentityHashCode & (dVarArr.length - 1);
            d dVar = dVarArr[length];
            d dVar2 = null;
            while (true) {
                if (dVar == null) {
                    break;
                }
                d dVar3 = dVar.b;
                if (dVar.get() != this) {
                    dVar2 = dVar;
                    dVar = dVar3;
                } else if (dVar2 == null) {
                    dVarArr[length] = dVar3;
                } else {
                    dVar2.b = dVar3;
                }
            }
            expungeStaleExceptions();
            this.status = 0;
        } finally {
            reentrantLock.unlock();
        }
    }

    private int doInvoke() {
        int iDoExec = doExec();
        if (iDoExec < 0) {
            return iDoExec;
        }
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof nq1)) {
            return externalAwaitDone();
        }
        nq1 nq1Var = (nq1) threadCurrentThread;
        return nq1Var.f8555a.a(nq1Var.b, (ForkJoinTask<?>) this, 0L);
    }

    private int doJoin() {
        int iDoExec;
        int i = this.status;
        if (i < 0) {
            return i;
        }
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof nq1)) {
            return externalAwaitDone();
        }
        nq1 nq1Var = (nq1) threadCurrentThread;
        mq1.f fVar = nq1Var.b;
        return (!fVar.d(this) || (iDoExec = doExec()) >= 0) ? nq1Var.f8555a.a(fVar, (ForkJoinTask<?>) this, 0L) : iDoExec;
    }

    public static void expungeStaleExceptions() {
        while (true) {
            Reference<? extends ForkJoinTask<?>> referencePoll = exceptionTableRefQueue.poll();
            if (referencePoll == null) {
                return;
            }
            if (referencePoll instanceof d) {
                d[] dVarArr = exceptionTable;
                int length = ((d) referencePoll).d & (dVarArr.length - 1);
                d dVar = dVarArr[length];
                d dVar2 = null;
                while (true) {
                    if (dVar != null) {
                        d dVar3 = dVar.b;
                        if (dVar != referencePoll) {
                            dVar2 = dVar;
                            dVar = dVar3;
                        } else if (dVar2 == null) {
                            dVarArr[length] = dVar3;
                        } else {
                            dVar2.b = dVar3;
                        }
                    }
                }
            }
        }
    }

    private int externalAwaitDone() {
        boolean z = false;
        int iA = this instanceof CountedCompleter ? mq1.n.a((CountedCompleter<?>) this, 0) : mq1.n.c((ForkJoinTask<?>) this) ? doExec() : 0;
        if (iA < 0) {
            return iA;
        }
        int i = this.status;
        if (i < 0) {
            return i;
        }
        int i2 = i;
        do {
            if (U.compareAndSwapInt(this, STATUS, i2, i2 | 65536)) {
                synchronized (this) {
                    if (this.status >= 0) {
                        try {
                            wait(0L);
                        } catch (InterruptedException unused) {
                            z = true;
                        }
                    } else {
                        notifyAll();
                    }
                }
            }
            i2 = this.status;
        } while (i2 >= 0);
        if (z) {
            Thread.currentThread().interrupt();
        }
        return i2;
    }

    private int externalInterruptibleAwaitDone() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int i = this.status;
        if (i < 0) {
            return i;
        }
        int iA = this instanceof CountedCompleter ? mq1.n.a((CountedCompleter<?>) this, 0) : mq1.n.c((ForkJoinTask<?>) this) ? doExec() : 0;
        if (iA < 0) {
            return iA;
        }
        while (true) {
            int i2 = this.status;
            if (i2 < 0) {
                return i2;
            }
            if (U.compareAndSwapInt(this, STATUS, i2, i2 | 65536)) {
                synchronized (this) {
                    if (this.status >= 0) {
                        wait(0L);
                    } else {
                        notifyAll();
                    }
                }
            }
        }
    }

    public static mq1 getPool() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof nq1) {
            return ((nq1) threadCurrentThread).f8555a;
        }
        return null;
    }

    public static int getQueuedTaskCount() {
        Thread threadCurrentThread = Thread.currentThread();
        mq1.f fVarF = threadCurrentThread instanceof nq1 ? ((nq1) threadCurrentThread).b : mq1.f();
        if (fVarF == null) {
            return 0;
        }
        return fVarF.h();
    }

    public static int getSurplusQueuedTaskCount() {
        return mq1.g();
    }

    private Throwable getThrowableException() {
        Throwable th;
        int iIdentityHashCode = System.identityHashCode(this);
        ReentrantLock reentrantLock = exceptionTableLock;
        reentrantLock.lock();
        try {
            expungeStaleExceptions();
            d[] dVarArr = exceptionTable;
            d dVar = dVarArr[iIdentityHashCode & (dVarArr.length - 1)];
            while (dVar != null) {
                if (dVar.get() == this) {
                    break;
                }
                dVar = dVar.b;
            }
            reentrantLock.unlock();
            Constructor<?> constructor = null;
            if (dVar == null || (th = dVar.f6779a) == null) {
                return null;
            }
            if (dVar.c != Thread.currentThread().getId()) {
                try {
                    for (Constructor<?> constructor2 : th.getClass().getConstructors()) {
                        Class<?>[] parameterTypes = constructor2.getParameterTypes();
                        if (parameterTypes.length == 0) {
                            constructor = constructor2;
                        } else if (parameterTypes.length == 1 && parameterTypes[0] == Throwable.class) {
                            return (Throwable) constructor2.newInstance(th);
                        }
                    }
                    if (constructor != null) {
                        Throwable th2 = (Throwable) constructor.newInstance(new Object[0]);
                        th2.initCause(th);
                        return th2;
                    }
                } catch (Exception unused) {
                }
            }
            return th;
        } catch (Throwable th3) {
            reentrantLock.unlock();
            throw th3;
        }
    }

    public static final void helpExpungeStaleExceptions() {
        ReentrantLock reentrantLock = exceptionTableLock;
        if (reentrantLock.tryLock()) {
            try {
                expungeStaleExceptions();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void helpQuiesce() {
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof nq1)) {
            mq1.i();
        } else {
            nq1 nq1Var = (nq1) threadCurrentThread;
            nq1Var.f8555a.a(nq1Var.b);
        }
    }

    public static boolean inForkJoinPool() {
        return Thread.currentThread() instanceof nq1;
    }

    public static void invokeAll(ForkJoinTask<?> forkJoinTask, ForkJoinTask<?> forkJoinTask2) throws Throwable {
        forkJoinTask2.fork();
        int iDoInvoke = forkJoinTask.doInvoke() & (-268435456);
        if (iDoInvoke != -268435456) {
            forkJoinTask.reportException(iDoInvoke);
        }
        int iDoJoin = forkJoinTask2.doJoin() & (-268435456);
        if (iDoJoin != -268435456) {
            forkJoinTask2.reportException(iDoJoin);
        }
    }

    public static ForkJoinTask<?> peekNextLocalTask() {
        Thread threadCurrentThread = Thread.currentThread();
        mq1.f fVarF = threadCurrentThread instanceof nq1 ? ((nq1) threadCurrentThread).b : mq1.f();
        if (fVarF == null) {
            return null;
        }
        return fVarF.e();
    }

    public static ForkJoinTask<?> pollNextLocalTask() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof nq1) {
            return ((nq1) threadCurrentThread).b.d();
        }
        return null;
    }

    public static ForkJoinTask<?> pollSubmission() {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof nq1) {
            return ((nq1) threadCurrentThread).f8555a.c();
        }
        return null;
    }

    public static ForkJoinTask<?> pollTask() {
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof nq1)) {
            return null;
        }
        nq1 nq1Var = (nq1) threadCurrentThread;
        return nq1Var.f8555a.b(nq1Var.b);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        Object object = objectInputStream.readObject();
        if (object != null) {
            setExceptionalCompletion((Throwable) object);
        }
    }

    private void reportException(int i) throws Throwable {
        if (i == -1073741824) {
            throw new CancellationException();
        }
        if (i == Integer.MIN_VALUE) {
            rethrow(getThrowableException());
        }
    }

    public static void rethrow(Throwable th) throws Throwable {
        uncheckedThrow(th);
    }

    private int setCompletion(int i) {
        int i2;
        do {
            i2 = this.status;
            if (i2 < 0) {
                return i2;
            }
        } while (!U.compareAndSwapInt(this, STATUS, i2, i2 | i));
        if ((i2 >>> 16) != 0) {
            synchronized (this) {
                notifyAll();
            }
        }
        return i;
    }

    private int setExceptionalCompletion(Throwable th) {
        int iRecordExceptionalCompletion = recordExceptionalCompletion(th);
        if (((-268435456) & iRecordExceptionalCompletion) == Integer.MIN_VALUE) {
            internalPropagateException(th);
        }
        return iRecordExceptionalCompletion;
    }

    public static <T extends Throwable> void uncheckedThrow(Throwable th) throws Throwable {
        if (th == null) {
            throw new Error("Unknown Exception");
        }
        throw th;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getException());
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return (setCompletion(CANCELLED) & (-268435456)) == -1073741824;
    }

    public final boolean compareAndSetForkJoinTaskTag(short s, short s2) {
        int i;
        do {
            i = this.status;
            if (((short) i) != s) {
                return false;
            }
        } while (!U.compareAndSwapInt(this, STATUS, i, (65535 & s2) | ((-65536) & i)));
        return true;
    }

    public void complete(V v) {
        try {
            setRawResult(v);
            setCompletion(-268435456);
        } catch (Throwable th) {
            setExceptionalCompletion(th);
        }
    }

    public void completeExceptionally(Throwable th) {
        if (!(th instanceof RuntimeException) && !(th instanceof Error)) {
            th = new RuntimeException(th);
        }
        setExceptionalCompletion(th);
    }

    public final int doExec() {
        int i = this.status;
        if (i < 0) {
            return i;
        }
        try {
            return exec() ? setCompletion(-268435456) : i;
        } catch (Throwable th) {
            return setExceptionalCompletion(th);
        }
    }

    public abstract boolean exec();

    public final ForkJoinTask<V> fork() throws Throwable {
        Thread threadCurrentThread = Thread.currentThread();
        if (threadCurrentThread instanceof nq1) {
            ((nq1) threadCurrentThread).b.a((ForkJoinTask<?>) this);
        } else {
            mq1.n.a((ForkJoinTask<?>) this);
        }
        return this;
    }

    @Override // java.util.concurrent.Future
    public final V get() throws ExecutionException, InterruptedException {
        int iDoJoin = (Thread.currentThread() instanceof nq1 ? doJoin() : externalInterruptibleAwaitDone()) & (-268435456);
        if (iDoJoin == -1073741824) {
            throw new CancellationException();
        }
        if (iDoJoin != Integer.MIN_VALUE) {
            return getRawResult();
        }
        throw new ExecutionException(getThrowableException());
    }

    public final Throwable getException() {
        int i = this.status & (-268435456);
        if (i >= -268435456) {
            return null;
        }
        return i == -1073741824 ? new CancellationException() : getThrowableException();
    }

    public final short getForkJoinTaskTag() {
        return (short) this.status;
    }

    public abstract V getRawResult();

    public void internalPropagateException(Throwable th) {
    }

    public final void internalWait(long j) {
        int i = this.status;
        if (i < 0 || !U.compareAndSwapInt(this, STATUS, i, i | 65536)) {
            return;
        }
        synchronized (this) {
            if (this.status >= 0) {
                try {
                    wait(j);
                } catch (InterruptedException unused) {
                }
            } else {
                notifyAll();
            }
        }
    }

    public final V invoke() throws Throwable {
        int iDoInvoke = doInvoke() & (-268435456);
        if (iDoInvoke != -268435456) {
            reportException(iDoInvoke);
        }
        return getRawResult();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return (this.status & (-268435456)) == -1073741824;
    }

    public final boolean isCompletedAbnormally() {
        return this.status < -268435456;
    }

    public final boolean isCompletedNormally() {
        return (this.status & (-268435456)) == -268435456;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.status < 0;
    }

    public final V join() throws Throwable {
        int iDoJoin = doJoin() & (-268435456);
        if (iDoJoin != -268435456) {
            reportException(iDoJoin);
        }
        return getRawResult();
    }

    public final void quietlyComplete() {
        setCompletion(-268435456);
    }

    public final void quietlyInvoke() {
        doInvoke();
    }

    public final void quietlyJoin() {
        doJoin();
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
    
        r2[r0] = new java9.util.concurrent.ForkJoinTask.d(r6, r7, r2[r0], java9.util.concurrent.ForkJoinTask.exceptionTableRefQueue);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int recordExceptionalCompletion(java.lang.Throwable r7) {
        /*
            r6 = this;
            int r0 = r6.status
            if (r0 < 0) goto L3e
            int r0 = java.lang.System.identityHashCode(r6)
            java.util.concurrent.locks.ReentrantLock r1 = java9.util.concurrent.ForkJoinTask.exceptionTableLock
            r1.lock()
            expungeStaleExceptions()     // Catch: java.lang.Throwable -> L39
            java9.util.concurrent.ForkJoinTask$d[] r2 = java9.util.concurrent.ForkJoinTask.exceptionTable     // Catch: java.lang.Throwable -> L39
            int r3 = r2.length     // Catch: java.lang.Throwable -> L39
            int r3 = r3 + (-1)
            r0 = r0 & r3
            r3 = r2[r0]     // Catch: java.lang.Throwable -> L39
        L18:
            if (r3 != 0) goto L26
            java9.util.concurrent.ForkJoinTask$d r3 = new java9.util.concurrent.ForkJoinTask$d     // Catch: java.lang.Throwable -> L39
            r4 = r2[r0]     // Catch: java.lang.Throwable -> L39
            java.lang.ref.ReferenceQueue<java9.util.concurrent.ForkJoinTask<?>> r5 = java9.util.concurrent.ForkJoinTask.exceptionTableRefQueue     // Catch: java.lang.Throwable -> L39
            r3.<init>(r6, r7, r4, r5)     // Catch: java.lang.Throwable -> L39
            r2[r0] = r3     // Catch: java.lang.Throwable -> L39
            goto L2c
        L26:
            java.lang.Object r4 = r3.get()     // Catch: java.lang.Throwable -> L39
            if (r4 != r6) goto L36
        L2c:
            r1.unlock()
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            int r0 = r6.setCompletion(r7)
            goto L3e
        L36:
            java9.util.concurrent.ForkJoinTask$d r3 = r3.b     // Catch: java.lang.Throwable -> L39
            goto L18
        L39:
            r7 = move-exception
            r1.unlock()
            throw r7
        L3e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinTask.recordExceptionalCompletion(java.lang.Throwable):int");
    }

    public void reinitialize() {
        if ((this.status & (-268435456)) == Integer.MIN_VALUE) {
            clearExceptionalCompletion();
        } else {
            this.status = 0;
        }
    }

    public final short setForkJoinTaskTag(short s) {
        Unsafe unsafe;
        long j;
        int i;
        do {
            unsafe = U;
            j = STATUS;
            i = this.status;
        } while (!unsafe.compareAndSwapInt(this, j, i, ((-65536) & i) | (65535 & s)));
        return (short) i;
    }

    public abstract void setRawResult(V v);

    public boolean tryUnfork() {
        Thread threadCurrentThread = Thread.currentThread();
        return threadCurrentThread instanceof nq1 ? ((nq1) threadCurrentThread).b.d(this) : mq1.n.c((ForkJoinTask<?>) this);
    }

    public static <T> ForkJoinTask<T> adapt(Runnable runnable, T t) {
        return new b(runnable, t);
    }

    public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable) {
        return new a(callable);
    }

    public static void invokeAll(ForkJoinTask<?>... forkJoinTaskArr) throws Throwable {
        int length = forkJoinTaskArr.length - 1;
        Throwable exception = null;
        for (int i = length; i >= 0; i--) {
            ForkJoinTask<?> forkJoinTask = forkJoinTaskArr[i];
            if (forkJoinTask == null) {
                if (exception == null) {
                    exception = new NullPointerException();
                }
            } else if (i != 0) {
                forkJoinTask.fork();
            } else if (forkJoinTask.doInvoke() < -268435456 && exception == null) {
                exception = forkJoinTask.getException();
            }
        }
        for (int i2 = 1; i2 <= length; i2++) {
            ForkJoinTask<?> forkJoinTask2 = forkJoinTaskArr[i2];
            if (forkJoinTask2 != null) {
                if (exception != null) {
                    forkJoinTask2.cancel(false);
                } else if (forkJoinTask2.doJoin() < -268435456) {
                    exception = forkJoinTask2.getException();
                }
            }
        }
        if (exception != null) {
            rethrow(exception);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[LOOP:0: B:23:0x0052->B:66:0x0052, LOOP_START] */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V get(long r11, java.util.concurrent.TimeUnit r13) throws java.lang.Throwable {
        /*
            r10 = this;
            long r11 = r13.toNanos(r11)
            boolean r13 = java.lang.Thread.interrupted()
            if (r13 != 0) goto Lb8
            int r13 = r10.status
            if (r13 < 0) goto L8b
            r0 = 0
            int r2 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r2 <= 0) goto L8b
            long r2 = java.lang.System.nanoTime()
            long r2 = r2 + r11
            int r11 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r11 != 0) goto L1f
            r2 = 1
        L1f:
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            boolean r12 = r11 instanceof supwisdom.nq1
            if (r12 == 0) goto L32
            supwisdom.nq1 r11 = (supwisdom.nq1) r11
            supwisdom.mq1 r12 = r11.f8555a
            supwisdom.mq1$f r11 = r11.b
            int r13 = r12.a(r11, r10, r2)
            goto L8b
        L32:
            boolean r11 = r10 instanceof java9.util.concurrent.CountedCompleter
            r12 = 0
            if (r11 == 0) goto L42
            supwisdom.mq1 r11 = supwisdom.mq1.n
            r13 = r10
            java9.util.concurrent.CountedCompleter r13 = (java9.util.concurrent.CountedCompleter) r13
            int r11 = r11.a(r13, r12)
        L40:
            r13 = r11
            goto L50
        L42:
            supwisdom.mq1 r11 = supwisdom.mq1.n
            boolean r11 = r11.c(r10)
            if (r11 == 0) goto L4f
            int r11 = r10.doExec()
            goto L40
        L4f:
            r13 = 0
        L50:
            if (r13 < 0) goto L8b
        L52:
            int r8 = r10.status
            if (r8 < 0) goto L8a
            long r11 = java.lang.System.nanoTime()
            long r11 = r2 - r11
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 <= 0) goto L8a
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r11 = r13.toMillis(r11)
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 <= 0) goto L52
            sun.misc.Unsafe r4 = java9.util.concurrent.ForkJoinTask.U
            long r6 = java9.util.concurrent.ForkJoinTask.STATUS
            r13 = 65536(0x10000, float:9.1835E-41)
            r9 = r8 | r13
            r5 = r10
            boolean r13 = r4.compareAndSwapInt(r5, r6, r8, r9)
            if (r13 == 0) goto L52
            monitor-enter(r10)
            int r13 = r10.status     // Catch: java.lang.Throwable -> L87
            if (r13 < 0) goto L82
            r10.wait(r11)     // Catch: java.lang.Throwable -> L87
            goto L85
        L82:
            r10.notifyAll()     // Catch: java.lang.Throwable -> L87
        L85:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L87
            goto L52
        L87:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L87
            throw r11
        L8a:
            r13 = r8
        L8b:
            if (r13 < 0) goto L8f
            int r13 = r10.status
        L8f:
            r11 = -268435456(0xfffffffff0000000, float:-1.5845633E29)
            r12 = r13 & r11
            if (r12 == r11) goto Lb3
            r11 = -1073741824(0xffffffffc0000000, float:-2.0)
            if (r12 == r11) goto Lad
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 == r11) goto La3
            java.util.concurrent.TimeoutException r11 = new java.util.concurrent.TimeoutException
            r11.<init>()
            throw r11
        La3:
            java.util.concurrent.ExecutionException r11 = new java.util.concurrent.ExecutionException
            java.lang.Throwable r12 = r10.getThrowableException()
            r11.<init>(r12)
            throw r11
        Lad:
            java.util.concurrent.CancellationException r11 = new java.util.concurrent.CancellationException
            r11.<init>()
            throw r11
        Lb3:
            java.lang.Object r11 = r10.getRawResult()
            return r11
        Lb8:
            java.lang.InterruptedException r11 = new java.lang.InterruptedException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.concurrent.ForkJoinTask.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> collection) throws Throwable {
        if ((collection instanceof RandomAccess) && (collection instanceof List)) {
            List list = (List) collection;
            Throwable exception = null;
            int size = list.size() - 1;
            for (int i = size; i >= 0; i--) {
                ForkJoinTask forkJoinTask = (ForkJoinTask) list.get(i);
                if (forkJoinTask == null) {
                    if (exception == null) {
                        exception = new NullPointerException();
                    }
                } else if (i != 0) {
                    forkJoinTask.fork();
                } else if (forkJoinTask.doInvoke() < -268435456 && exception == null) {
                    exception = forkJoinTask.getException();
                }
            }
            for (int i2 = 1; i2 <= size; i2++) {
                ForkJoinTask forkJoinTask2 = (ForkJoinTask) list.get(i2);
                if (forkJoinTask2 != null) {
                    if (exception != null) {
                        forkJoinTask2.cancel(false);
                    } else if (forkJoinTask2.doJoin() < -268435456) {
                        exception = forkJoinTask2.getException();
                    }
                }
            }
            if (exception != null) {
                rethrow(exception);
            }
            return collection;
        }
        invokeAll((ForkJoinTask<?>[]) collection.toArray(new ForkJoinTask[0]));
        return collection;
    }
}
