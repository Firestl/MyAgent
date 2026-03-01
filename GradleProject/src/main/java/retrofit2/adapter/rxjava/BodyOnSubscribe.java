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
public final class BodyOnSubscribe<T> implements rw1.a<T> {
    public final rw1.a<Response<T>> upstream;

    public static class BodySubscriber<R> extends xw1<Response<R>> {
        public final xw1<? super R> subscriber;
        public boolean subscriberTerminated;

        public BodySubscriber(xw1<? super R> xw1Var) {
            super(xw1Var);
            this.subscriber = xw1Var;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            if (this.subscriberTerminated) {
                return;
            }
            this.subscriber.onCompleted();
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            if (!this.subscriberTerminated) {
                this.subscriber.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a Retrofit bug with the full stacktrace.");
            assertionError.initCause(th);
            lz1.f().b().a((Throwable) assertionError);
        }

        @Override // supwisdom.sw1
        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.subscriber.onNext(response.body());
                return;
            }
            this.subscriberTerminated = true;
            HttpException httpException = new HttpException(response);
            try {
                this.subscriber.onError(httpException);
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
                lz1.f().b().a((Throwable) new CompositeException(httpException, th));
            }
        }
    }

    public BodyOnSubscribe(rw1.a<Response<T>> aVar) {
        this.upstream = aVar;
    }

    @Override // supwisdom.bx1
    public void call(xw1<? super T> xw1Var) {
        this.upstream.call((Response<T>) new BodySubscriber(xw1Var));
    }
}
