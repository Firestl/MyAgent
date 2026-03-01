package supwisdom;

import supwisdom.rw1;

/* JADX INFO: compiled from: OnSubscribeLift.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jx1<T, R> implements rw1.a<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final rw1.a<T> f8096a;
    public final rw1.b<? extends R, ? super T> b;

    public jx1(rw1.a<T> aVar, rw1.b<? extends R, ? super T> bVar) {
        this.f8096a = aVar;
        this.b = bVar;
    }

    @Override // supwisdom.bx1
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void call(xw1<? super R> xw1Var) {
        try {
            xw1<? super T> xw1VarCall = iz1.a(this.b).call(xw1Var);
            try {
                xw1VarCall.onStart();
                this.f8096a.call(xw1VarCall);
            } catch (Throwable th) {
                zw1.b(th);
                xw1VarCall.onError(th);
            }
        } catch (Throwable th2) {
            zw1.b(th2);
            xw1Var.onError(th2);
        }
    }
}
