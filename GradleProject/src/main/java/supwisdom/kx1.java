package supwisdom;

import rx.exceptions.OnErrorThrowable;
import supwisdom.rw1;

/* JADX INFO: compiled from: OnSubscribeMap.java */
/* JADX INFO: loaded from: classes3.dex */
public final class kx1<T, R> implements rw1.a<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final rw1<T> f8218a;
    public final fx1<? super T, ? extends R> b;

    /* JADX INFO: compiled from: OnSubscribeMap.java */
    public static final class a<T, R> extends xw1<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final xw1<? super R> f8219a;
        public final fx1<? super T, ? extends R> b;
        public boolean c;

        public a(xw1<? super R> xw1Var, fx1<? super T, ? extends R> fx1Var) {
            this.f8219a = xw1Var;
            this.b = fx1Var;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            if (this.c) {
                return;
            }
            this.f8219a.onCompleted();
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            if (this.c) {
                iz1.a(th);
            } else {
                this.c = true;
                this.f8219a.onError(th);
            }
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            try {
                this.f8219a.onNext(this.b.call(t));
            } catch (Throwable th) {
                zw1.b(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        @Override // supwisdom.xw1
        public void setProducer(tw1 tw1Var) {
            this.f8219a.setProducer(tw1Var);
        }
    }

    public kx1(rw1<T> rw1Var, fx1<? super T, ? extends R> fx1Var) {
        this.f8218a = rw1Var;
        this.b = fx1Var;
    }

    @Override // supwisdom.bx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void call(xw1<? super R> xw1Var) {
        a aVar = new a(xw1Var, this.b);
        xw1Var.add(aVar);
        this.f8218a.b(aVar);
    }
}
