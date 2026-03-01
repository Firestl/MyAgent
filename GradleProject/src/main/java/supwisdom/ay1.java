package supwisdom;

/* JADX INFO: compiled from: ObserverSubscriber.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ay1<T> extends xw1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final sw1<? super T> f6996a;

    public ay1(sw1<? super T> sw1Var) {
        this.f6996a = sw1Var;
    }

    @Override // supwisdom.sw1
    public void onCompleted() {
        this.f6996a.onCompleted();
    }

    @Override // supwisdom.sw1
    public void onError(Throwable th) {
        this.f6996a.onError(th);
    }

    @Override // supwisdom.sw1
    public void onNext(T t) {
        this.f6996a.onNext(t);
    }
}
