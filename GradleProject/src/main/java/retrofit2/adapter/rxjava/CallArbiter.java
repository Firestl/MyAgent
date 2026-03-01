package retrofit2.adapter.rxjava;

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Response;
import rx.exceptions.CompositeException;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import supwisdom.lz1;
import supwisdom.tw1;
import supwisdom.xw1;
import supwisdom.yw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class CallArbiter<T> extends AtomicInteger implements yw1, tw1 {
    public static final int STATE_HAS_RESPONSE = 2;
    public static final int STATE_REQUESTED = 1;
    public static final int STATE_TERMINATED = 3;
    public static final int STATE_WAITING = 0;
    public final Call<T> call;
    public volatile Response<T> response;
    public final xw1<? super Response<T>> subscriber;
    public volatile boolean unsubscribed;

    public CallArbiter(Call<T> call, xw1<? super Response<T>> xw1Var) {
        super(0);
        this.call = call;
        this.subscriber = xw1Var;
    }

    private void deliverResponse(Response<T> response) {
        try {
            if (!isUnsubscribed()) {
                this.subscriber.onNext(response);
            }
            try {
                if (isUnsubscribed()) {
                    return;
                }
                this.subscriber.onCompleted();
            } catch (OnCompletedFailedException e2) {
                e = e2;
                lz1.f().b().a(e);
            } catch (OnErrorFailedException e3) {
                e = e3;
                lz1.f().b().a(e);
            } catch (OnErrorNotImplementedException e4) {
                e = e4;
                lz1.f().b().a(e);
            } catch (Throwable th) {
                zw1.b(th);
                lz1.f().b().a(th);
            }
        } catch (OnCompletedFailedException e5) {
            e = e5;
            lz1.f().b().a(e);
        } catch (OnErrorFailedException e6) {
            e = e6;
            lz1.f().b().a(e);
        } catch (OnErrorNotImplementedException e7) {
            e = e7;
            lz1.f().b().a(e);
        } catch (Throwable th2) {
            zw1.b(th2);
            try {
                this.subscriber.onError(th2);
            } catch (OnCompletedFailedException e8) {
                e = e8;
                lz1.f().b().a(e);
            } catch (OnErrorFailedException e9) {
                e = e9;
                lz1.f().b().a(e);
            } catch (OnErrorNotImplementedException e10) {
                e = e10;
                lz1.f().b().a(e);
            } catch (Throwable th3) {
                zw1.b(th3);
                lz1.f().b().a((Throwable) new CompositeException(th2, th3));
            }
        }
    }

    public void emitError(Throwable th) {
        set(3);
        if (isUnsubscribed()) {
            return;
        }
        try {
            this.subscriber.onError(th);
        } catch (OnCompletedFailedException e2) {
            e = e2;
            lz1.f().b().a(e);
        } catch (OnErrorFailedException e3) {
            e = e3;
            lz1.f().b().a(e);
        } catch (OnErrorNotImplementedException e4) {
            e = e4;
            lz1.f().b().a(e);
        } catch (Throwable th2) {
            zw1.b(th2);
            lz1.f().b().a((Throwable) new CompositeException(th, th2));
        }
    }

    public void emitResponse(Response<T> response) {
        while (true) {
            int i = get();
            if (i == 0) {
                this.response = response;
                if (compareAndSet(0, 2)) {
                    return;
                }
            } else {
                if (i != 1) {
                    if (i == 2 || i == 3) {
                        throw new AssertionError();
                    }
                    throw new IllegalStateException("Unknown state: " + i);
                }
                if (compareAndSet(1, 3)) {
                    deliverResponse(response);
                    return;
                }
            }
        }
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    @Override // supwisdom.tw1
    public void request(long j) {
        if (j == 0) {
            return;
        }
        while (true) {
            int i = get();
            if (i != 0) {
                if (i == 1) {
                    return;
                }
                if (i != 2) {
                    if (i == 3) {
                        return;
                    }
                    throw new IllegalStateException("Unknown state: " + i);
                }
                if (compareAndSet(2, 3)) {
                    deliverResponse(this.response);
                    return;
                }
            } else if (compareAndSet(0, 1)) {
                return;
            }
        }
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        this.unsubscribed = true;
        this.call.cancel();
    }
}
