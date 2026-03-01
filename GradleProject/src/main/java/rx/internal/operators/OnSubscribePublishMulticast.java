package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.exceptions.MissingBackpressureException;
import supwisdom.cz1;
import supwisdom.hx1;
import supwisdom.jy1;
import supwisdom.rw1;
import supwisdom.sw1;
import supwisdom.tw1;
import supwisdom.uy1;
import supwisdom.xw1;
import supwisdom.yw1;

/* JADX INFO: loaded from: classes3.dex */
public final class OnSubscribePublishMulticast<T> extends AtomicInteger implements rw1.a<T>, sw1<T>, yw1 {
    public static final b<?>[] EMPTY = new b[0];
    public static final b<?>[] TERMINATED = new b[0];
    public static final long serialVersionUID = -3741892510772238743L;
    public final boolean delayError;
    public volatile boolean done;
    public Throwable error;
    public final a<T> parent;
    public final int prefetch;
    public volatile tw1 producer;
    public final Queue<T> queue;
    public volatile b<T>[] subscribers;

    public static final class a<T> extends xw1<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final OnSubscribePublishMulticast<T> f6819a;

        public a(OnSubscribePublishMulticast<T> onSubscribePublishMulticast) {
            this.f6819a = onSubscribePublishMulticast;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            this.f6819a.onCompleted();
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            this.f6819a.onError(th);
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            this.f6819a.onNext(t);
        }

        @Override // supwisdom.xw1
        public void setProducer(tw1 tw1Var) {
            this.f6819a.setProducer(tw1Var);
        }
    }

    public static final class b<T> extends AtomicLong implements tw1, yw1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final xw1<? super T> f6820a;
        public final OnSubscribePublishMulticast<T> b;
        public final AtomicBoolean c = new AtomicBoolean();

        public b(xw1<? super T> xw1Var, OnSubscribePublishMulticast<T> onSubscribePublishMulticast) {
            this.f6820a = xw1Var;
            this.b = onSubscribePublishMulticast;
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.c.get();
        }

        @Override // supwisdom.tw1
        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
            if (j != 0) {
                hx1.a(this, j);
                this.b.drain();
            }
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            if (this.c.compareAndSet(false, true)) {
                this.b.remove(this);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OnSubscribePublishMulticast(int i, boolean z) {
        if (i <= 0) {
            throw new IllegalArgumentException("prefetch > 0 required but it was " + i);
        }
        this.prefetch = i;
        this.delayError = z;
        if (cz1.a()) {
            this.queue = new uy1(i);
        } else {
            this.queue = new jy1(i);
        }
        this.subscribers = (b<T>[]) EMPTY;
        this.parent = new a<>(this);
    }

    public boolean add(b<T> bVar) {
        if (this.subscribers == TERMINATED) {
            return false;
        }
        synchronized (this) {
            b<T>[] bVarArr = this.subscribers;
            if (bVarArr == TERMINATED) {
                return false;
            }
            int length = bVarArr.length;
            b<T>[] bVarArr2 = new b[length + 1];
            System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
            bVarArr2[length] = bVar;
            this.subscribers = bVarArr2;
            return true;
        }
    }

    public boolean checkTerminated(boolean z, boolean z2) {
        int i = 0;
        if (z) {
            if (!this.delayError) {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    b<T>[] bVarArrTerminate = terminate();
                    int length = bVarArrTerminate.length;
                    while (i < length) {
                        bVarArrTerminate[i].f6820a.onError(th);
                        i++;
                    }
                    return true;
                }
                if (z2) {
                    b<T>[] bVarArrTerminate2 = terminate();
                    int length2 = bVarArrTerminate2.length;
                    while (i < length2) {
                        bVarArrTerminate2[i].f6820a.onCompleted();
                        i++;
                    }
                    return true;
                }
            } else if (z2) {
                b<T>[] bVarArrTerminate3 = terminate();
                Throwable th2 = this.error;
                if (th2 != null) {
                    int length3 = bVarArrTerminate3.length;
                    while (i < length3) {
                        bVarArrTerminate3[i].f6820a.onError(th2);
                        i++;
                    }
                } else {
                    int length4 = bVarArrTerminate3.length;
                    while (i < length4) {
                        bVarArrTerminate3[i].f6820a.onCompleted();
                        i++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void drain() {
        if (getAndIncrement() != 0) {
            return;
        }
        Queue<T> queue = this.queue;
        int iAddAndGet = 0;
        do {
            long jMin = Long.MAX_VALUE;
            b<T>[] bVarArr = this.subscribers;
            int length = bVarArr.length;
            for (b<T> bVar : bVarArr) {
                jMin = Math.min(jMin, bVar.get());
            }
            if (length != 0) {
                long j = 0;
                while (j != jMin) {
                    boolean z = this.done;
                    T tPoll = queue.poll();
                    boolean z2 = tPoll == null;
                    if (checkTerminated(z, z2)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    for (b<T> bVar2 : bVarArr) {
                        bVar2.f6820a.onNext(tPoll);
                    }
                    j++;
                }
                if (j == jMin && checkTerminated(this.done, queue.isEmpty())) {
                    return;
                }
                if (j != 0) {
                    tw1 tw1Var = this.producer;
                    if (tw1Var != null) {
                        tw1Var.request(j);
                    }
                    for (b<T> bVar3 : bVarArr) {
                        hx1.b(bVar3, j);
                    }
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
        } while (iAddAndGet != 0);
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return this.parent.isUnsubscribed();
    }

    @Override // supwisdom.sw1
    public void onCompleted() {
        this.done = true;
        drain();
    }

    @Override // supwisdom.sw1
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // supwisdom.sw1
    public void onNext(T t) {
        if (!this.queue.offer(t)) {
            this.parent.unsubscribe();
            this.error = new MissingBackpressureException("Queue full?!");
            this.done = true;
        }
        drain();
    }

    public void remove(b<T> bVar) {
        b[] bVarArr;
        b<T>[] bVarArr2 = this.subscribers;
        if (bVarArr2 == TERMINATED || bVarArr2 == EMPTY) {
            return;
        }
        synchronized (this) {
            b<T>[] bVarArr3 = this.subscribers;
            if (bVarArr3 != TERMINATED && bVarArr3 != EMPTY) {
                int i = -1;
                int length = bVarArr3.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (bVarArr3[i2] == bVar) {
                        i = i2;
                        break;
                    }
                    i2++;
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    bVarArr = EMPTY;
                } else {
                    b[] bVarArr4 = new b[length - 1];
                    System.arraycopy(bVarArr3, 0, bVarArr4, 0, i);
                    System.arraycopy(bVarArr3, i + 1, bVarArr4, i, (length - i) - 1);
                    bVarArr = bVarArr4;
                }
                this.subscribers = bVarArr;
            }
        }
    }

    public void setProducer(tw1 tw1Var) {
        this.producer = tw1Var;
        tw1Var.request(this.prefetch);
    }

    public xw1<T> subscriber() {
        return this.parent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b<T>[] terminate() {
        b<T>[] bVarArr = this.subscribers;
        if (bVarArr != TERMINATED) {
            synchronized (this) {
                bVarArr = this.subscribers;
                if (bVarArr != TERMINATED) {
                    this.subscribers = (b<T>[]) TERMINATED;
                }
            }
        }
        return bVarArr;
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        this.parent.unsubscribe();
    }

    @Override // supwisdom.bx1
    public void call(xw1<? super T> xw1Var) {
        b<T> bVar = new b<>(xw1Var, this);
        xw1Var.add(bVar);
        xw1Var.setProducer(bVar);
        if (add(bVar)) {
            if (bVar.isUnsubscribed()) {
                remove(bVar);
                return;
            } else {
                drain();
                return;
            }
        }
        Throwable th = this.error;
        if (th != null) {
            xw1Var.onError(th);
        } else {
            xw1Var.onCompleted();
        }
    }
}
