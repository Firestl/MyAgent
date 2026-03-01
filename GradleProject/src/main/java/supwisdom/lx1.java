package supwisdom;

import java.util.NoSuchElementException;
import supwisdom.vw1;

/* JADX INFO: compiled from: OnSubscribeSingle.java */
/* JADX INFO: loaded from: classes3.dex */
public class lx1<T> implements vw1.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final rw1<T> f8328a;

    /* JADX INFO: compiled from: OnSubscribeSingle.java */
    public class a extends xw1<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8329a;
        public boolean b;
        public T c;
        public final /* synthetic */ ww1 d;

        public a(lx1 lx1Var, ww1 ww1Var) {
            this.d = ww1Var;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            if (this.f8329a) {
                return;
            }
            if (this.b) {
                this.d.a(this.c);
            } else {
                this.d.a((Throwable) new NoSuchElementException("Observable emitted no items"));
            }
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            this.d.a(th);
            unsubscribe();
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            if (!this.b) {
                this.b = true;
                this.c = t;
            } else {
                this.f8329a = true;
                this.d.a((Throwable) new IllegalArgumentException("Observable emitted too many elements"));
                unsubscribe();
            }
        }

        @Override // supwisdom.xw1
        public void onStart() {
            request(2L);
        }
    }

    public lx1(rw1<T> rw1Var) {
        this.f8328a = rw1Var;
    }

    @Override // supwisdom.bx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void call(ww1<? super T> ww1Var) {
        a aVar = new a(this, ww1Var);
        ww1Var.a((yw1) aVar);
        this.f8328a.b(aVar);
    }

    public static <T> lx1<T> a(rw1<T> rw1Var) {
        return new lx1<>(rw1Var);
    }
}
