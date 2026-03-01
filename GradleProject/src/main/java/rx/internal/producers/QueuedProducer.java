package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.exceptions.MissingBackpressureException;
import supwisdom.bz1;
import supwisdom.cz1;
import supwisdom.hx1;
import supwisdom.ky1;
import supwisdom.sw1;
import supwisdom.tw1;
import supwisdom.xw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class QueuedProducer<T> extends AtomicLong implements tw1, sw1<T> {
    public static final Object NULL_SENTINEL = new Object();
    public static final long serialVersionUID = 7277121710709137047L;
    public final xw1<? super T> child;
    public volatile boolean done;
    public Throwable error;
    public final Queue<Object> queue;
    public final AtomicInteger wip;

    public QueuedProducer(xw1<? super T> xw1Var) {
        this(xw1Var, cz1.a() ? new bz1() : new ky1());
    }

    private boolean checkTerminated(boolean z, boolean z2) {
        if (this.child.isUnsubscribed()) {
            return true;
        }
        if (!z) {
            return false;
        }
        Throwable th = this.error;
        if (th != null) {
            this.queue.clear();
            this.child.onError(th);
            return true;
        }
        if (!z2) {
            return false;
        }
        this.child.onCompleted();
        return true;
    }

    private void drain() {
        if (this.wip.getAndIncrement() == 0) {
            xw1<? super T> xw1Var = this.child;
            Queue<Object> queue = this.queue;
            while (!checkTerminated(this.done, queue.isEmpty())) {
                this.wip.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0) {
                    boolean z = this.done;
                    Object objPoll = queue.poll();
                    if (checkTerminated(z, objPoll == null)) {
                        return;
                    }
                    if (objPoll == null) {
                        break;
                    }
                    try {
                        if (objPoll == NULL_SENTINEL) {
                            xw1Var.onNext(null);
                        } else {
                            xw1Var.onNext(objPoll);
                        }
                        j--;
                        j2++;
                    } catch (Throwable th) {
                        if (objPoll == NULL_SENTINEL) {
                            objPoll = null;
                        }
                        zw1.a(th, xw1Var, objPoll);
                        return;
                    }
                }
                if (j2 != 0 && get() != Long.MAX_VALUE) {
                    addAndGet(-j2);
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }

    public boolean offer(T t) {
        if (t == null) {
            if (!this.queue.offer(NULL_SENTINEL)) {
                return false;
            }
        } else if (!this.queue.offer(t)) {
            return false;
        }
        drain();
        return true;
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
        if (offer(t)) {
            return;
        }
        onError(new MissingBackpressureException());
    }

    @Override // supwisdom.tw1
    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (j > 0) {
            hx1.a(this, j);
            drain();
        }
    }

    public QueuedProducer(xw1<? super T> xw1Var, Queue<Object> queue) {
        this.child = xw1Var;
        this.queue = queue;
        this.wip = new AtomicInteger();
    }
}
