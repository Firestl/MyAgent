package supwisdom;

import rx.internal.producers.SingleDelayedProducer;
import supwisdom.rw1;

/* JADX INFO: compiled from: OperatorAny.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mx1<T> implements rw1.b<Boolean, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fx1<? super T, Boolean> f8449a;
    public final boolean b;

    /* JADX INFO: compiled from: OperatorAny.java */
    public class a extends xw1<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8450a;
        public boolean b;
        public final /* synthetic */ SingleDelayedProducer c;
        public final /* synthetic */ xw1 d;

        public a(SingleDelayedProducer singleDelayedProducer, xw1 xw1Var) {
            this.c = singleDelayedProducer;
            this.d = xw1Var;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            if (this.b) {
                return;
            }
            this.b = true;
            if (this.f8450a) {
                this.c.setValue(false);
            } else {
                this.c.setValue(Boolean.valueOf(mx1.this.b));
            }
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            if (this.b) {
                iz1.a(th);
            } else {
                this.b = true;
                this.d.onError(th);
            }
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            if (this.b) {
                return;
            }
            this.f8450a = true;
            try {
                if (mx1.this.f8449a.call(t).booleanValue()) {
                    this.b = true;
                    this.c.setValue(Boolean.valueOf(true ^ mx1.this.b));
                    unsubscribe();
                }
            } catch (Throwable th) {
                zw1.a(th, this, t);
            }
        }
    }

    public mx1(fx1<? super T, Boolean> fx1Var, boolean z) {
        this.f8449a = fx1Var;
        this.b = z;
    }

    @Override // supwisdom.fx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public xw1<? super T> call(xw1<? super Boolean> xw1Var) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(xw1Var);
        a aVar = new a(singleDelayedProducer, xw1Var);
        xw1Var.add(aVar);
        xw1Var.setProducer(singleDelayedProducer);
        return aVar;
    }
}
