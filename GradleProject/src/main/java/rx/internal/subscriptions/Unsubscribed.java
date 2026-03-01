package rx.internal.subscriptions;

import supwisdom.yw1;

/* JADX INFO: loaded from: classes3.dex */
public enum Unsubscribed implements yw1 {
    INSTANCE;

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return true;
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
    }
}
