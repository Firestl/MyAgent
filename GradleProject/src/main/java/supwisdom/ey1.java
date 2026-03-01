package supwisdom;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.concurrent.atomic.AtomicBoolean;
import supwisdom.rw1;
import supwisdom.uw1;

/* JADX INFO: compiled from: ScalarSynchronousObservable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ey1<T> extends rw1<T> {
    public final T b;

    /* JADX INFO: compiled from: ScalarSynchronousObservable.java */
    public class a implements fx1<ax1, yw1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ux1 f7545a;

        public a(ey1 ey1Var, ux1 ux1Var) {
            this.f7545a = ux1Var;
        }

        @Override // supwisdom.fx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public yw1 call(ax1 ax1Var) {
            return this.f7545a.a(ax1Var);
        }
    }

    /* JADX INFO: compiled from: ScalarSynchronousObservable.java */
    public class b implements fx1<ax1, yw1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ uw1 f7546a;

        /* JADX INFO: compiled from: ScalarSynchronousObservable.java */
        public class a implements ax1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ax1 f7547a;
            public final /* synthetic */ uw1.a b;

            public a(b bVar, ax1 ax1Var, uw1.a aVar) {
                this.f7547a = ax1Var;
                this.b = aVar;
            }

            @Override // supwisdom.ax1
            public void call() {
                try {
                    this.f7547a.call();
                } finally {
                    this.b.unsubscribe();
                }
            }
        }

        public b(ey1 ey1Var, uw1 uw1Var) {
            this.f7546a = uw1Var;
        }

        @Override // supwisdom.fx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public yw1 call(ax1 ax1Var) {
            uw1.a aVarA = this.f7546a.a();
            aVarA.a(new a(this, ax1Var, aVarA));
            return aVarA;
        }
    }

    /* JADX INFO: compiled from: ScalarSynchronousObservable.java */
    public static final class c<T> implements rw1.a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final T f7548a;
        public final fx1<ax1, yw1> b;

        public c(T t, fx1<ax1, yw1> fx1Var) {
            this.f7548a = t;
            this.b = fx1Var;
        }

        @Override // supwisdom.bx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void call(xw1<? super T> xw1Var) {
            xw1Var.setProducer(new d(xw1Var, this.f7548a, this.b));
        }
    }

    /* JADX INFO: compiled from: ScalarSynchronousObservable.java */
    public static final class d<T> extends AtomicBoolean implements tw1, ax1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final xw1<? super T> f7549a;
        public final T b;
        public final fx1<ax1, yw1> c;

        public d(xw1<? super T> xw1Var, T t, fx1<ax1, yw1> fx1Var) {
            this.f7549a = xw1Var;
            this.b = t;
            this.c = fx1Var;
        }

        @Override // supwisdom.ax1
        public void call() {
            xw1<? super T> xw1Var = this.f7549a;
            if (xw1Var.isUnsubscribed()) {
                return;
            }
            T t = this.b;
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
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
            if (j == 0 || !compareAndSet(false, true)) {
                return;
            }
            this.f7549a.add(this.c.call(this));
        }

        @Override // java.util.concurrent.atomic.AtomicBoolean
        public String toString() {
            return "ScalarAsyncProducer[" + this.b + ", " + get() + Operators.ARRAY_END_STR;
        }
    }

    static {
        Boolean.valueOf(System.getProperty("rx.just.strong-mode", AbsoluteConst.FALSE)).booleanValue();
    }

    public rw1<T> c(uw1 uw1Var) {
        return rw1.b(new c(this.b, uw1Var instanceof ux1 ? new a(this, (ux1) uw1Var) : new b(this, uw1Var)));
    }
}
