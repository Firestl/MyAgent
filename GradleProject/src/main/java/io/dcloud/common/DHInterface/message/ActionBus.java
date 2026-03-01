package io.dcloud.common.DHInterface.message;

import io.dcloud.common.DHInterface.message.action.BadgeSyncAction;
import io.dcloud.common.DHInterface.message.action.IAction;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class ActionBus {
    public static ActionBus instance = new ActionBus();
    public HashSet<Class<? extends IAction>> supportMessageType = new HashSet<>();
    public HashMap<EnumUniqueID, AbsActionObserver> observers = new HashMap<>();

    public ActionBus() {
        this.supportMessageType.add(BadgeSyncAction.class);
    }

    public static ActionBus getInstance() {
        return instance;
    }

    public boolean observeAction(AbsActionObserver absActionObserver) {
        if (absActionObserver == null || this.observers.containsKey(absActionObserver.getObserverUniqueID())) {
            return false;
        }
        this.observers.put(absActionObserver.getObserverUniqueID(), absActionObserver);
        return true;
    }

    public boolean sendToBus(IAction iAction) {
        if (iAction == null || !this.supportMessageType.contains(iAction.getClass())) {
            return false;
        }
        for (AbsActionObserver absActionObserver : this.observers.values()) {
            if (absActionObserver != null) {
                absActionObserver.handleMessage(iAction);
            }
        }
        return true;
    }

    public void stopObserve(EnumUniqueID enumUniqueID) {
        this.observers.remove(enumUniqueID);
    }
}
