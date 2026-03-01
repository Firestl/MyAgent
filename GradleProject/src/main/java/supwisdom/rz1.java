package supwisdom;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: BooleanSubscription.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rz1 implements yw1 {
    public static final ax1 b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference<ax1> f9102a;

    /* JADX INFO: compiled from: BooleanSubscription.java */
    public static class a implements ax1 {
        @Override // supwisdom.ax1
        public void call() {
        }
    }

    public rz1() {
        this.f9102a = new AtomicReference<>();
    }

    public static rz1 a(ax1 ax1Var) {
        return new rz1(ax1Var);
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return this.f9102a.get() == b;
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        ax1 andSet;
        ax1 ax1Var = this.f9102a.get();
        ax1 ax1Var2 = b;
        if (ax1Var == ax1Var2 || (andSet = this.f9102a.getAndSet(ax1Var2)) == null || andSet == b) {
            return;
        }
        andSet.call();
    }

    public rz1(ax1 ax1Var) {
        this.f9102a = new AtomicReference<>(ax1Var);
    }
}
