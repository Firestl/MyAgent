package supwisdom;

import java.util.concurrent.TimeUnit;
import rx.exceptions.OnErrorFailedException;

/* JADX INFO: compiled from: Observable.java */
/* JADX INFO: loaded from: classes3.dex */
public class rw1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a<T> f9096a;

    /* JADX INFO: compiled from: Observable.java */
    public interface a<T> extends bx1<xw1<? super T>> {
    }

    /* JADX INFO: compiled from: Observable.java */
    public interface b<R, T> extends fx1<xw1<? super R>, xw1<? super T>> {
    }

    public rw1(a<T> aVar) {
        this.f9096a = aVar;
    }

    @Deprecated
    public static <T> rw1<T> a(a<T> aVar) {
        return new rw1<>(iz1.a(aVar));
    }

    public static <T> rw1<T> b(a<T> aVar) {
        return new rw1<>(iz1.a(aVar));
    }

    public vw1<T> c() {
        return new vw1<>(lx1.a(this));
    }

    public final <R> rw1<R> a(b<? extends R, ? super T> bVar) {
        return b(new jx1(this.f9096a, bVar));
    }

    public pw1 b() {
        return pw1.a((rw1<?>) this);
    }

    public final <R> rw1<R> a(fx1<? super T, ? extends R> fx1Var) {
        return b(new kx1(this, fx1Var));
    }

    public final yw1 b(xw1<? super T> xw1Var) {
        try {
            xw1Var.onStart();
            iz1.a(this, this.f9096a).call(xw1Var);
            return iz1.a(xw1Var);
        } catch (Throwable th) {
            zw1.b(th);
            try {
                xw1Var.onError(iz1.b(th));
                return tz1.a();
            } catch (Throwable th2) {
                zw1.b(th2);
                OnErrorFailedException onErrorFailedException = new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                iz1.b(onErrorFailedException);
                throw onErrorFailedException;
            }
        }
    }

    public final rw1<T> a(uw1 uw1Var) {
        return a(uw1Var, dy1.f7397a);
    }

    public final rw1<T> a(uw1 uw1Var, int i) {
        return a(uw1Var, false, i);
    }

    public final rw1<T> a(uw1 uw1Var, boolean z, int i) {
        if (this instanceof ey1) {
            return ((ey1) this).c(uw1Var);
        }
        return (rw1<T>) a((b) new nx1(uw1Var, z, i));
    }

    public final dz1<T> a() {
        return ox1.a(this);
    }

    public final dz1<T> a(int i) {
        return ox1.a(this, i);
    }

    public final dz1<T> a(int i, long j, TimeUnit timeUnit, uw1 uw1Var) {
        if (i >= 0) {
            return ox1.a(this, j, timeUnit, uw1Var, i);
        }
        throw new IllegalArgumentException("bufferSize < 0");
    }

    public final dz1<T> a(long j, TimeUnit timeUnit, uw1 uw1Var) {
        return ox1.a(this, j, timeUnit, uw1Var);
    }

    public final rw1<T> b(uw1 uw1Var) {
        return a(uw1Var, true);
    }

    public final yw1 a(sw1<? super T> sw1Var) {
        if (sw1Var instanceof xw1) {
            return a((xw1) sw1Var);
        }
        if (sw1Var != null) {
            return a((xw1) new ay1(sw1Var));
        }
        throw new NullPointerException("observer is null");
    }

    public final yw1 a(xw1<? super T> xw1Var) {
        return a(xw1Var, this);
    }

    public static <T> yw1 a(xw1<? super T> xw1Var, rw1<T> rw1Var) {
        if (xw1Var != null) {
            if (rw1Var.f9096a != null) {
                xw1Var.onStart();
                if (!(xw1Var instanceof ez1)) {
                    xw1Var = new ez1(xw1Var);
                }
                try {
                    iz1.a(rw1Var, rw1Var.f9096a).call(xw1Var);
                    return iz1.a(xw1Var);
                } catch (Throwable th) {
                    zw1.b(th);
                    if (xw1Var.isUnsubscribed()) {
                        iz1.a(iz1.b(th));
                    } else {
                        try {
                            xw1Var.onError(iz1.b(th));
                        } catch (Throwable th2) {
                            zw1.b(th2);
                            OnErrorFailedException onErrorFailedException = new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                            iz1.b(onErrorFailedException);
                            throw onErrorFailedException;
                        }
                    }
                    return tz1.a();
                }
            }
            throw new IllegalStateException("onSubscribe function can not be null.");
        }
        throw new IllegalArgumentException("subscriber can not be null");
    }

    public final rw1<T> a(uw1 uw1Var, boolean z) {
        if (this instanceof ey1) {
            return ((ey1) this).c(uw1Var);
        }
        return b(new px1(this, uw1Var, z));
    }
}
