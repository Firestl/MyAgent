package io.dcloud.common.util;

import io.dcloud.common.DHInterface.ISysEventListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class EventDispatchManager {
    public static EventDispatchManager instance;
    public List<ActivityEventDispatchListener> dispatchListeners = new ArrayList();

    public interface ActivityEventDispatchListener {
        boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj);
    }

    public static EventDispatchManager getInstance() {
        if (instance == null) {
            synchronized (EventDispatchManager.class) {
                if (instance == null) {
                    instance = new EventDispatchManager();
                }
            }
        }
        return instance;
    }

    public void addListener(ActivityEventDispatchListener activityEventDispatchListener) {
        if (activityEventDispatchListener != null) {
            this.dispatchListeners.add(activityEventDispatchListener);
        }
    }

    public boolean dispatchEvent(ISysEventListener.SysEventType sysEventType, Object obj) {
        boolean zOnExecute = false;
        for (int size = this.dispatchListeners.size() - 1; size >= 0; size--) {
            zOnExecute = this.dispatchListeners.get(size).onExecute(sysEventType, obj);
            if (zOnExecute) {
                break;
            }
        }
        return zOnExecute;
    }

    public void removeListener(ActivityEventDispatchListener activityEventDispatchListener) {
        if (activityEventDispatchListener != null) {
            this.dispatchListeners.remove(activityEventDispatchListener);
        }
    }
}
