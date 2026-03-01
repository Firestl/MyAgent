package supwisdom;

import supwisdom.rw1;
import supwisdom.uw1;

/* JADX INFO: compiled from: OperatorSubscribeOn.java */
/* JADX INFO: loaded from: classes3.dex */
public final class px1<T> implements rw1.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final uw1 f8846a;
    public final rw1<T> b;
    public final boolean c;

    /* JADX INFO: compiled from: OperatorSubscribeOn.java */
    public static final class a<T> extends xw1<T> implements ax1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final xw1<? super T> f8847a;
        public final boolean b;
        public final uw1.a c;
        public rw1<T> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Thread f8848e;

        /* JADX INFO: renamed from: supwisdom.px1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: OperatorSubscribeOn.java */
        public class C0226a implements tw1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ tw1 f8849a;

            /* JADX INFO: renamed from: supwisdom.px1$a$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: OperatorSubscribeOn.java */
            public class C0227a implements ax1 {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ long f8850a;

                public C0227a(long j) {
                    this.f8850a = j;
                }

                @Override // supwisdom.ax1
                public void call() {
                    C0226a.this.f8849a.request(this.f8850a);
                }
            }

            public C0226a(tw1 tw1Var) {
                this.f8849a = tw1Var;
            }

            @Override // supwisdom.tw1
            public void request(long j) {
                if (a.this.f8848e != Thread.currentThread()) {
                    a aVar = a.this;
                    if (aVar.b) {
                        aVar.c.a(new C0227a(j));
                        return;
                    }
                }
                this.f8849a.request(j);
            }
        }

        public a(xw1<? super T> xw1Var, boolean z, uw1.a aVar, rw1<T> rw1Var) {
            this.f8847a = xw1Var;
            this.b = z;
            this.c = aVar;
            this.d = rw1Var;
        }

        @Override // supwisdom.ax1
        public void call() {
            rw1<T> rw1Var = this.d;
            this.d = null;
            this.f8848e = Thread.currentThread();
            rw1Var.b(this);
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            try {
                this.f8847a.onCompleted();
            } finally {
                this.c.unsubscribe();
            }
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            try {
                this.f8847a.onError(th);
            } finally {
                this.c.unsubscribe();
            }
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            this.f8847a.onNext(t);
        }

        @Override // supwisdom.xw1
        public void setProducer(tw1 tw1Var) {
            this.f8847a.setProducer(new C0226a(tw1Var));
        }
    }

    public px1(rw1<T> rw1Var, uw1 uw1Var, boolean z) {
        this.f8846a = uw1Var;
        this.b = rw1Var;
        this.c = z;
    }

    @Override // supwisdom.bx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void call(xw1<? super T> xw1Var) {
        uw1.a aVarA = this.f8846a.a();
        a aVar = new a(xw1Var, this.c, aVarA, this.b);
        xw1Var.add(aVar);
        xw1Var.add(aVarA);
        aVarA.a(aVar);
    }
}
