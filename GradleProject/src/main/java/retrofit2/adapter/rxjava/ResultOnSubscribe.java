package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.exceptions.CompositeException;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import supwisdom.lz1;
import supwisdom.rw1;
import supwisdom.xw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class ResultOnSubscribe<T> implements rw1.a<Result<T>> {
    public final rw1.a<Response<T>> upstream;

    public static class ResultSubscriber<R> extends xw1<Response<R>> {
        public final xw1<? super Result<R>> subscriber;

        public ResultSubscriber(xw1<? super Result<R>> xw1Var) {
            super(xw1Var);
            this.subscriber = xw1Var;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            this.subscriber.onCompleted();
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            try {
                this.subscriber.onNext(Result.error(th));
                this.subscriber.onCompleted();
            } catch (Throwable th2) {
                try {
                    this.subscriber.onError(th2);
                } catch (OnCompletedFailedException e2) {
                    e = e2;
                    lz1.f().b().a(e);
                } catch (OnErrorFailedException e3) {
                    e = e3;
                    lz1.f().b().a(e);
                } catch (OnErrorNotImplementedException e4) {
                    e = e4;
                    lz1.f().b().a(e);
                } catch (Throwable th3) {
                    zw1.b(th3);
                    lz1.f().b().a((Throwable) new CompositeException(th2, th3));
                }
            }
        }

        @Override // supwisdom.sw1
        public void onNext(Response<R> response) {
            this.subscriber.onNext(Result.response(response));
        }
    }

    public ResultOnSubscribe(rw1.a<Response<T>> aVar) {
        this.upstream = aVar;
    }

    @Override // supwisdom.bx1
    public void call(xw1<? super Result<T>> xw1Var) {
        this.upstream.call((Response<T>) new ResultSubscriber(xw1Var));
    }
}
