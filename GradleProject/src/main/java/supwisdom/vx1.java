package supwisdom;

import supwisdom.uw1;

/* JADX INFO: compiled from: ImmediateScheduler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vx1 extends uw1 {

    /* JADX INFO: compiled from: ImmediateScheduler.java */
    public final class a extends uw1.a implements yw1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final rz1 f9559a = new rz1();

        public a(vx1 vx1Var) {
        }

        @Override // supwisdom.uw1.a
        public yw1 a(ax1 ax1Var) {
            ax1Var.call();
            return tz1.a();
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.f9559a.isUnsubscribed();
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            this.f9559a.unsubscribe();
        }
    }

    static {
        new vx1();
    }

    @Override // supwisdom.uw1
    public uw1.a a() {
        return new a(this);
    }
}
