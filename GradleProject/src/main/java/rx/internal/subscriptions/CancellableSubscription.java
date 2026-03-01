package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import supwisdom.dx1;
import supwisdom.iz1;
import supwisdom.yw1;
import supwisdom.zw1;

/* JADX INFO: loaded from: classes3.dex */
public final class CancellableSubscription extends AtomicReference<dx1> implements yw1 {
    public static final long serialVersionUID = 5718521705281392066L;

    public CancellableSubscription(dx1 dx1Var) {
        super(dx1Var);
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return get() == null;
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        dx1 andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        try {
            andSet.cancel();
        } catch (Exception e2) {
            zw1.b(e2);
            iz1.a(e2);
        }
    }
}
