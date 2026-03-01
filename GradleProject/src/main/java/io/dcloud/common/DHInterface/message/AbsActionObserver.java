package io.dcloud.common.DHInterface.message;

import io.dcloud.common.DHInterface.message.action.IAction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsActionObserver {
    public IObserveAble observeAble;

    public AbsActionObserver(IObserveAble iObserveAble) {
        this.observeAble = iObserveAble;
    }

    public EnumUniqueID getObserverUniqueID() {
        return this.observeAble.getActionObserverID();
    }

    public abstract boolean handleMessage(IAction iAction);
}
