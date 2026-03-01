package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import supwisdom.tz1;
import supwisdom.yw1;

/* JADX INFO: loaded from: classes3.dex */
public final class SequentialSubscription extends AtomicReference<yw1> implements yw1 {
    public static final long serialVersionUID = 995205034283130269L;

    public SequentialSubscription() {
    }

    public yw1 current() {
        yw1 yw1Var = (yw1) super.get();
        return yw1Var == Unsubscribed.INSTANCE ? tz1.a() : yw1Var;
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return get() == Unsubscribed.INSTANCE;
    }

    public boolean replace(yw1 yw1Var) {
        yw1 yw1Var2;
        do {
            yw1Var2 = get();
            if (yw1Var2 == Unsubscribed.INSTANCE) {
                if (yw1Var == null) {
                    return false;
                }
                yw1Var.unsubscribe();
                return false;
            }
        } while (!compareAndSet(yw1Var2, yw1Var));
        return true;
    }

    public boolean replaceWeak(yw1 yw1Var) {
        yw1 yw1Var2 = get();
        if (yw1Var2 == Unsubscribed.INSTANCE) {
            if (yw1Var != null) {
                yw1Var.unsubscribe();
            }
            return false;
        }
        if (compareAndSet(yw1Var2, yw1Var) || get() != Unsubscribed.INSTANCE) {
            return true;
        }
        if (yw1Var != null) {
            yw1Var.unsubscribe();
        }
        return false;
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        yw1 andSet;
        yw1 yw1Var = get();
        Unsubscribed unsubscribed = Unsubscribed.INSTANCE;
        if (yw1Var == unsubscribed || (andSet = getAndSet(unsubscribed)) == null || andSet == Unsubscribed.INSTANCE) {
            return;
        }
        andSet.unsubscribe();
    }

    public boolean update(yw1 yw1Var) {
        yw1 yw1Var2;
        do {
            yw1Var2 = get();
            if (yw1Var2 == Unsubscribed.INSTANCE) {
                if (yw1Var == null) {
                    return false;
                }
                yw1Var.unsubscribe();
                return false;
            }
        } while (!compareAndSet(yw1Var2, yw1Var));
        if (yw1Var2 == null) {
            return true;
        }
        yw1Var2.unsubscribe();
        return true;
    }

    public boolean updateWeak(yw1 yw1Var) {
        yw1 yw1Var2 = get();
        if (yw1Var2 == Unsubscribed.INSTANCE) {
            if (yw1Var != null) {
                yw1Var.unsubscribe();
            }
            return false;
        }
        if (compareAndSet(yw1Var2, yw1Var)) {
            return true;
        }
        yw1 yw1Var3 = get();
        if (yw1Var != null) {
            yw1Var.unsubscribe();
        }
        return yw1Var3 == Unsubscribed.INSTANCE;
    }

    public SequentialSubscription(yw1 yw1Var) {
        lazySet(yw1Var);
    }
}
