package io.dcloud.common.DHInterface;

import io.dcloud.common.DHInterface.ISysEventListener;

/* JADX INFO: loaded from: classes2.dex */
public interface ISysEventDispatch {
    boolean callSysEventListener(ISysEventListener.SysEventType sysEventType, Object obj);

    void registerSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType);

    void unRegisterSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType);
}
