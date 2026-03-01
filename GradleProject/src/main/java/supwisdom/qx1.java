package supwisdom;

import java.util.NoSuchElementException;
import supwisdom.rw1;
import supwisdom.vw1;

/* JADX INFO: compiled from: SingleFromObservable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qx1<T> implements vw1.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final rw1.a<T> f8969a;

    /* JADX INFO: compiled from: SingleFromObservable.java */
    public static final class a<T> extends xw1<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ww1<? super T> f8970a;
        public T b;
        public int c;

        public a(ww1<? super T> ww1Var) {
            this.f8970a = ww1Var;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            int i = this.c;
            if (i == 0) {
                this.f8970a.a((Throwable) new NoSuchElementException());
            } else if (i == 1) {
                this.c = 2;
                T t = this.b;
                this.b = null;
                this.f8970a.a(t);
            }
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            if (this.c == 2) {
                iz1.a(th);
            } else {
                this.b = null;
                this.f8970a.a(th);
            }
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            int i = this.c;
            if (i == 0) {
                this.c = 1;
                this.b = t;
            } else if (i == 1) {
                this.c = 2;
                this.f8970a.a((Throwable) new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }
    }

    public qx1(rw1.a<T> aVar) {
        this.f8969a = aVar;
    }

    @Override // supwisdom.bx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void call(ww1<? super T> ww1Var) {
        a aVar = new a(ww1Var);
        ww1Var.a((yw1) aVar);
        this.f8969a.call(aVar);
    }
}
