package supwisdom;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.exceptions.MissingBackpressureException;
import supwisdom.rw1;
import supwisdom.uw1;

/* JADX INFO: compiled from: OperatorObserveOn.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nx1<T> implements rw1.b<T, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final uw1 f8586a;
    public final boolean b;
    public final int c;

    public nx1(uw1 uw1Var, boolean z, int i) {
        this.f8586a = uw1Var;
        this.b = z;
        this.c = i <= 0 ? dy1.f7397a : i;
    }

    @Override // supwisdom.fx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public xw1<? super T> call(xw1<? super T> xw1Var) {
        uw1 uw1Var = this.f8586a;
        if ((uw1Var instanceof vx1) || (uw1Var instanceof zx1)) {
            return xw1Var;
        }
        a aVar = new a(uw1Var, xw1Var, this.b, this.c);
        aVar.a();
        return aVar;
    }

    /* JADX INFO: compiled from: OperatorObserveOn.java */
    public static final class a<T> extends xw1<T> implements ax1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final xw1<? super T> f8587a;
        public final uw1.a b;
        public final boolean c;
        public final Queue<Object> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f8588e;
        public volatile boolean f;
        public final AtomicLong g = new AtomicLong();
        public final AtomicLong h = new AtomicLong();
        public Throwable i;
        public long j;

        /* JADX INFO: renamed from: supwisdom.nx1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: OperatorObserveOn.java */
        public class C0222a implements tw1 {
            public C0222a() {
            }

            @Override // supwisdom.tw1
            public void request(long j) {
                if (j > 0) {
                    hx1.a(a.this.g, j);
                    a.this.b();
                }
            }
        }

        public a(uw1 uw1Var, xw1<? super T> xw1Var, boolean z, int i) {
            this.f8587a = xw1Var;
            this.b = uw1Var.a();
            this.c = z;
            i = i <= 0 ? dy1.f7397a : i;
            this.f8588e = i - (i >> 2);
            if (cz1.a()) {
                this.d = new uy1(i);
            } else {
                this.d = new jy1(i);
            }
            request(i);
        }

        public void a() {
            xw1<? super T> xw1Var = this.f8587a;
            xw1Var.setProducer(new C0222a());
            xw1Var.add(this.b);
            xw1Var.add(this);
        }

        public void b() {
            if (this.h.getAndIncrement() == 0) {
                this.b.a(this);
            }
        }

        @Override // supwisdom.ax1
        public void call() {
            long j = this.j;
            Queue<Object> queue = this.d;
            xw1<? super T> xw1Var = this.f8587a;
            long jAddAndGet = 1;
            do {
                long jB = this.g.get();
                while (jB != j) {
                    boolean z = this.f;
                    Object objPoll = queue.poll();
                    boolean z2 = objPoll == null;
                    if (a(z, z2, xw1Var, queue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    xw1Var.onNext((Object) ix1.a(objPoll));
                    j++;
                    if (j == this.f8588e) {
                        jB = hx1.b(this.g, j);
                        request(j);
                        j = 0;
                    }
                }
                if (jB == j && a(this.f, queue.isEmpty(), xw1Var, queue)) {
                    return;
                }
                this.j = j;
                jAddAndGet = this.h.addAndGet(-jAddAndGet);
            } while (jAddAndGet != 0);
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            if (isUnsubscribed() || this.f) {
                return;
            }
            this.f = true;
            b();
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            if (isUnsubscribed() || this.f) {
                iz1.a(th);
                return;
            }
            this.i = th;
            this.f = true;
            b();
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            if (isUnsubscribed() || this.f) {
                return;
            }
            if (this.d.offer(ix1.d(t))) {
                b();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        public boolean a(boolean z, boolean z2, xw1<? super T> xw1Var, Queue<Object> queue) {
            if (xw1Var.isUnsubscribed()) {
                queue.clear();
                return true;
            }
            if (!z) {
                return false;
            }
            if (this.c) {
                if (!z2) {
                    return false;
                }
                Throwable th = this.i;
                try {
                    if (th != null) {
                        xw1Var.onError(th);
                    } else {
                        xw1Var.onCompleted();
                    }
                    return false;
                } finally {
                }
            }
            Throwable th2 = this.i;
            if (th2 != null) {
                queue.clear();
                try {
                    xw1Var.onError(th2);
                    return true;
                } finally {
                }
            }
            if (!z2) {
                return false;
            }
            try {
                xw1Var.onCompleted();
                return true;
            } finally {
            }
        }
    }
}
