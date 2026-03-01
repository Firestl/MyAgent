package rx.internal.operators;

import supwisdom.rw1;
import supwisdom.xw1;

/* JADX INFO: loaded from: classes3.dex */
public enum NeverObservableHolder implements rw1.a<Object> {
    INSTANCE;

    public static final rw1<Object> NEVER = rw1.b(INSTANCE);

    public static <T> rw1<T> instance() {
        return (rw1<T>) NEVER;
    }

    @Override // supwisdom.bx1
    public void call(xw1<? super Object> xw1Var) {
    }
}
