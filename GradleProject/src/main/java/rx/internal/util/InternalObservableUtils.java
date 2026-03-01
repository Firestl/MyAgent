package rx.internal.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.exceptions.OnErrorNotImplementedException;
import supwisdom.bx1;
import supwisdom.cx1;
import supwisdom.dz1;
import supwisdom.ex1;
import supwisdom.fx1;
import supwisdom.gx1;
import supwisdom.gy1;
import supwisdom.mx1;
import supwisdom.rw1;
import supwisdom.uw1;

/* JADX INFO: loaded from: classes3.dex */
public enum InternalObservableUtils {
    ;

    public static final h LONG_COUNTER = new gx1<Long, Object, Long>() { // from class: rx.internal.util.InternalObservableUtils.h
        @Override // supwisdom.gx1
        public Long a(Long l2, Object obj) {
            return Long.valueOf(l2.longValue() + 1);
        }
    };
    public static final f OBJECT_EQUALS = new gx1<Object, Object, Boolean>() { // from class: rx.internal.util.InternalObservableUtils.f
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.gx1
        public Boolean a(Object obj, Object obj2) {
            return Boolean.valueOf(obj == obj2 || (obj != null && obj.equals(obj2)));
        }
    };
    public static final q TO_ARRAY = new fx1<List<? extends rw1<?>>, rw1<?>[]>() { // from class: rx.internal.util.InternalObservableUtils.q
        @Override // supwisdom.fx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public rw1<?>[] call(List<? extends rw1<?>> list) {
            return (rw1[]) list.toArray(new rw1[list.size()]);
        }
    };
    public static final o RETURNS_VOID = new o();
    public static final g COUNTER = new gx1<Integer, Object, Integer>() { // from class: rx.internal.util.InternalObservableUtils.g
        @Override // supwisdom.gx1
        public Integer a(Integer num, Object obj) {
            return Integer.valueOf(num.intValue() + 1);
        }
    };
    public static final e ERROR_EXTRACTOR = new e();
    public static final bx1<Throwable> ERROR_NOT_IMPLEMENTED = new bx1<Throwable>() { // from class: rx.internal.util.InternalObservableUtils.c
        public void a(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }

        @Override // supwisdom.bx1
        public /* bridge */ /* synthetic */ void call(Throwable th) {
            a(th);
            throw null;
        }
    };
    public static final rw1.b<Boolean, Object> IS_EMPTY = new mx1(gy1.a(), true);

    public static final class a<T, R> implements gx1<R, T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final cx1<R, ? super T> f6824a;

        public a(cx1<R, ? super T> cx1Var) {
            this.f6824a = cx1Var;
        }

        @Override // supwisdom.gx1
        public R a(R r, T t) {
            this.f6824a.a(r, t);
            return r;
        }
    }

    public static final class b implements fx1<Object, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f6825a;

        public b(Object obj) {
            this.f6825a = obj;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.fx1
        public Boolean call(Object obj) {
            Object obj2 = this.f6825a;
            return Boolean.valueOf(obj == obj2 || (obj != null && obj.equals(obj2)));
        }
    }

    public static final class d implements fx1<Object, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Class<?> f6826a;

        public d(Class<?> cls) {
            this.f6826a = cls;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // supwisdom.fx1
        public Boolean call(Object obj) {
            return Boolean.valueOf(this.f6826a.isInstance(obj));
        }
    }

    public static final class e implements fx1<Notification<?>, Throwable> {
        @Override // supwisdom.fx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Throwable call(Notification<?> notification) {
            return notification.b();
        }
    }

    public static final class i implements fx1<rw1<? extends Notification<?>>, rw1<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final fx1<? super rw1<? extends Void>, ? extends rw1<?>> f6827a;

        public i(fx1<? super rw1<? extends Void>, ? extends rw1<?>> fx1Var) {
            this.f6827a = fx1Var;
        }

        @Override // supwisdom.fx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public rw1<?> call(rw1<? extends Notification<?>> rw1Var) {
            return this.f6827a.call(rw1Var.a(InternalObservableUtils.RETURNS_VOID));
        }
    }

    public static final class j<T> implements ex1<dz1<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final rw1<T> f6828a;
        public final int b;

        public j(rw1<T> rw1Var, int i) {
            this.f6828a = rw1Var;
            this.b = i;
        }

        @Override // supwisdom.ex1
        public dz1<T> call() {
            return this.f6828a.a(this.b);
        }
    }

    public static final class k<T> implements ex1<dz1<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TimeUnit f6829a;
        public final rw1<T> b;
        public final long c;
        public final uw1 d;

        public k(rw1<T> rw1Var, long j, TimeUnit timeUnit, uw1 uw1Var) {
            this.f6829a = timeUnit;
            this.b = rw1Var;
            this.c = j;
            this.d = uw1Var;
        }

        @Override // supwisdom.ex1
        public dz1<T> call() {
            return this.b.a(this.c, this.f6829a, this.d);
        }
    }

    public static final class l<T> implements ex1<dz1<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final rw1<T> f6830a;

        public l(rw1<T> rw1Var) {
            this.f6830a = rw1Var;
        }

        @Override // supwisdom.ex1
        public dz1<T> call() {
            return this.f6830a.a();
        }
    }

    public static final class m<T> implements ex1<dz1<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f6831a;
        public final TimeUnit b;
        public final uw1 c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final rw1<T> f6832e;

        public m(rw1<T> rw1Var, int i, long j, TimeUnit timeUnit, uw1 uw1Var) {
            this.f6831a = j;
            this.b = timeUnit;
            this.c = uw1Var;
            this.d = i;
            this.f6832e = rw1Var;
        }

        @Override // supwisdom.ex1
        public dz1<T> call() {
            return this.f6832e.a(this.d, this.f6831a, this.b, this.c);
        }
    }

    public static final class n implements fx1<rw1<? extends Notification<?>>, rw1<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final fx1<? super rw1<? extends Throwable>, ? extends rw1<?>> f6833a;

        public n(fx1<? super rw1<? extends Throwable>, ? extends rw1<?>> fx1Var) {
            this.f6833a = fx1Var;
        }

        @Override // supwisdom.fx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public rw1<?> call(rw1<? extends Notification<?>> rw1Var) {
            return this.f6833a.call(rw1Var.a(InternalObservableUtils.ERROR_EXTRACTOR));
        }
    }

    public static final class o implements fx1<Object, Void> {
        @Override // supwisdom.fx1
        public Void call(Object obj) {
            return null;
        }
    }

    public static final class p<T, R> implements fx1<rw1<T>, rw1<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final fx1<? super rw1<T>, ? extends rw1<R>> f6834a;
        public final uw1 b;

        public p(fx1<? super rw1<T>, ? extends rw1<R>> fx1Var, uw1 uw1Var) {
            this.f6834a = fx1Var;
            this.b = uw1Var;
        }

        @Override // supwisdom.fx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public rw1<R> call(rw1<T> rw1Var) {
            return this.f6834a.call(rw1Var).a(this.b);
        }
    }

    public static <T, R> gx1<R, T, R> createCollectorCaller(cx1<R, ? super T> cx1Var) {
        return new a(cx1Var);
    }

    public static fx1<rw1<? extends Notification<?>>, rw1<?>> createRepeatDematerializer(fx1<? super rw1<? extends Void>, ? extends rw1<?>> fx1Var) {
        return new i(fx1Var);
    }

    public static <T, R> fx1<rw1<T>, rw1<R>> createReplaySelectorAndObserveOn(fx1<? super rw1<T>, ? extends rw1<R>> fx1Var, uw1 uw1Var) {
        return new p(fx1Var, uw1Var);
    }

    public static <T> ex1<dz1<T>> createReplaySupplier(rw1<T> rw1Var) {
        return new l(rw1Var);
    }

    public static fx1<rw1<? extends Notification<?>>, rw1<?>> createRetryDematerializer(fx1<? super rw1<? extends Throwable>, ? extends rw1<?>> fx1Var) {
        return new n(fx1Var);
    }

    public static fx1<Object, Boolean> equalsWith(Object obj) {
        return new b(obj);
    }

    public static fx1<Object, Boolean> isInstanceOf(Class<?> cls) {
        return new d(cls);
    }

    public static <T> ex1<dz1<T>> createReplaySupplier(rw1<T> rw1Var, int i2) {
        return new j(rw1Var, i2);
    }

    public static <T> ex1<dz1<T>> createReplaySupplier(rw1<T> rw1Var, long j2, TimeUnit timeUnit, uw1 uw1Var) {
        return new k(rw1Var, j2, timeUnit, uw1Var);
    }

    public static <T> ex1<dz1<T>> createReplaySupplier(rw1<T> rw1Var, int i2, long j2, TimeUnit timeUnit, uw1 uw1Var) {
        return new m(rw1Var, i2, j2, timeUnit, uw1Var);
    }
}
