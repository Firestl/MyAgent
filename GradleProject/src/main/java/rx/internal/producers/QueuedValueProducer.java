package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import supwisdom.bz1;
import supwisdom.cz1;
import supwisdom.hx1;
import supwisdom.ky1;
import supwisdom.tw1;
import supwisdom.xw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class QueuedValueProducer<T> extends AtomicLong implements tw1 {
    public static final Object NULL_SENTINEL = new Object();
    public static final long serialVersionUID = 7277121710709137047L;
    public final xw1<? super T> child;
    public final Queue<Object> queue;
    public final AtomicInteger wip;

    public QueuedValueProducer(xw1<? super T> xw1Var) {
        this(xw1Var, cz1.a() ? new bz1() : new ky1());
    }

    private void drain() {
        Object objPoll;
        if (this.wip.getAndIncrement() == 0) {
            xw1<? super T> xw1Var = this.child;
            Queue<Object> queue = this.queue;
            while (!xw1Var.isUnsubscribed()) {
                this.wip.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0 && (objPoll = queue.poll()) != null) {
                    try {
                        if (objPoll == NULL_SENTINEL) {
                            xw1Var.onNext(null);
                        } else {
                            xw1Var.onNext(objPoll);
                        }
                        if (xw1Var.isUnsubscribed()) {
                            return;
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

    public QueuedValueProducer(xw1<? super T> xw1Var, Queue<Object> queue) {
        this.child = xw1Var;
        this.queue = queue;
        this.wip = new AtomicInteger();
    }
}
