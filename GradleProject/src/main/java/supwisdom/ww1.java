package supwisdom;

/* JADX INFO: compiled from: SingleSubscriber.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ww1<T> implements yw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fy1 f9678a = new fy1();

    public abstract void a(T t);

    public abstract void a(Throwable th);

    public final void a(yw1 yw1Var) {
        this.f9678a.a(yw1Var);
    }

    @Override // supwisdom.yw1
    public final boolean isUnsubscribed() {
        return this.f9678a.isUnsubscribed();
    }

    @Override // supwisdom.yw1
    public final void unsubscribe() {
        this.f9678a.unsubscribe();
    }
}
