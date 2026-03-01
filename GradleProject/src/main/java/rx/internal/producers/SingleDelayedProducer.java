package rx.internal.producers;

import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.tw1;
import supwisdom.xw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class SingleDelayedProducer<T> extends AtomicInteger implements tw1 {
    public static final int HAS_REQUEST_HAS_VALUE = 3;
    public static final int HAS_REQUEST_NO_VALUE = 2;
    public static final int NO_REQUEST_HAS_VALUE = 1;
    public static final int NO_REQUEST_NO_VALUE = 0;
    public static final long serialVersionUID = -2873467947112093874L;
    public final xw1<? super T> child;
    public T value;

    public SingleDelayedProducer(xw1<? super T> xw1Var) {
        this.child = xw1Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void emit(xw1<? super T> xw1Var, T t) {
        if (xw1Var.isUnsubscribed()) {
            return;
        }
        try {
            xw1Var.onNext(t);
            if (xw1Var.isUnsubscribed()) {
                return;
            }
            xw1Var.onCompleted();
        } catch (Throwable th) {
            zw1.a(th, xw1Var, t);
        }
    }

    @Override // supwisdom.tw1
    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (j == 0) {
            return;
        }
        do {
            int i = get();
            if (i != 0) {
                if (i == 1 && compareAndSet(1, 3)) {
                    emit(this.child, this.value);
                    return;
                }
                return;
            }
        } while (!compareAndSet(0, 2));
    }

    public void setValue(T t) {
        do {
            int i = get();
            if (i != 0) {
                if (i == 2 && compareAndSet(2, 3)) {
                    emit(this.child, t);
                    return;
                }
                return;
            }
            this.value = t;
        } while (!compareAndSet(0, 1));
    }
}
