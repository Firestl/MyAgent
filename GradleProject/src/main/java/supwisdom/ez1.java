package supwisdom;

import java.util.Arrays;
import rx.exceptions.CompositeException;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.exceptions.UnsubscribeFailedException;

/* JADX INFO: compiled from: SafeSubscriber.java */
/* JADX INFO: loaded from: classes3.dex */
public class ez1<T> extends xw1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final xw1<? super T> f7552a;
    public boolean b;

    public ez1(xw1<? super T> xw1Var) {
        super(xw1Var);
        this.f7552a = xw1Var;
    }

    public void a(Throwable th) {
        lz1.f().b().a(th);
        try {
            this.f7552a.onError(th);
            try {
                unsubscribe();
            } catch (Throwable th2) {
                iz1.a(th2);
                throw new OnErrorFailedException(th2);
            }
        } catch (OnErrorNotImplementedException e2) {
            try {
                unsubscribe();
                throw e2;
            } catch (Throwable th3) {
                iz1.a(th3);
                throw new OnErrorNotImplementedException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(th, th3)));
            }
        } catch (Throwable th4) {
            iz1.a(th4);
            try {
                unsubscribe();
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(th, th4)));
            } catch (Throwable th5) {
                iz1.a(th5);
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(th, th4, th5)));
            }
        }
    }

    @Override // supwisdom.sw1
    public void onCompleted() {
        UnsubscribeFailedException unsubscribeFailedException;
        if (this.b) {
            return;
        }
        this.b = true;
        try {
            this.f7552a.onCompleted();
            try {
                unsubscribe();
            } finally {
            }
        } catch (Throwable th) {
            try {
                zw1.b(th);
                iz1.a(th);
                throw new OnCompletedFailedException(th.getMessage(), th);
            } catch (Throwable th2) {
                try {
                    unsubscribe();
                    throw th2;
                } finally {
                }
            }
        }
    }

    @Override // supwisdom.sw1
    public void onError(Throwable th) {
        zw1.b(th);
        if (this.b) {
            return;
        }
        this.b = true;
        a(th);
    }

    @Override // supwisdom.sw1
    public void onNext(T t) {
        try {
            if (this.b) {
                return;
            }
            this.f7552a.onNext(t);
        } catch (Throwable th) {
            zw1.a(th, this);
        }
    }
}
