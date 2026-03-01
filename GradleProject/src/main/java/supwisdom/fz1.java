package supwisdom;

/* JADX INFO: compiled from: Subscribers.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fz1 {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Subscribers.java */
    public static class a<T> extends xw1<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ xw1 f7672a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(xw1 xw1Var, xw1 xw1Var2) {
            super(xw1Var);
            this.f7672a = xw1Var2;
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            this.f7672a.onCompleted();
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            this.f7672a.onError(th);
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            this.f7672a.onNext(t);
        }
    }

    public static <T> xw1<T> a(xw1<? super T> xw1Var) {
        return new a(xw1Var, xw1Var);
    }
}
