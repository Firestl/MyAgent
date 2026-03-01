package rx.internal.producers;

import java.util.concurrent.atomic.AtomicBoolean;
import supwisdom.tw1;
import supwisdom.xw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class SingleProducer<T> extends AtomicBoolean implements tw1 {
    public static final long serialVersionUID = -3353584923995471404L;
    public final xw1<? super T> child;
    public final T value;

    public SingleProducer(xw1<? super T> xw1Var, T t) {
        this.child = xw1Var;
        this.value = t;
    }

    @Override // supwisdom.tw1
    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (j != 0 && compareAndSet(false, true)) {
            xw1<? super T> xw1Var = this.child;
            if (xw1Var.isUnsubscribed()) {
                return;
            }
            T t = this.value;
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
    }
}
