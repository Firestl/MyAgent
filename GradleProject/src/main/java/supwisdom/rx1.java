package supwisdom;

import rx.internal.producers.SingleProducer;

/* JADX INFO: compiled from: SingleLiftObservableOperator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rx1<T> extends ww1<T> {
    public final xw1<? super T> b;

    public rx1(xw1<? super T> xw1Var) {
        this.b = xw1Var;
    }

    @Override // supwisdom.ww1
    public void a(T t) {
        this.b.setProducer(new SingleProducer(this.b, t));
    }

    @Override // supwisdom.ww1
    public void a(Throwable th) {
        this.b.onError(th);
    }
}
