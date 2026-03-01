package io.dcloud.common.DHInterface;

import android.content.Context;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsMgr implements IMgr {
    public Context mContextWrapper;
    public ICore mCore;
    public boolean mIsStreamAppMode = false;
    public String mMgrName;
    public IMgr.MgrType mMgrType;

    public AbsMgr(ICore iCore, String str, IMgr.MgrType mgrType) {
        this.mContextWrapper = null;
        this.mCore = null;
        this.mMgrName = null;
        this.mCore = iCore;
        this.mMgrName = str;
        this.mMgrType = mgrType;
        this.mContextWrapper = iCore.obtainContext();
    }

    public final boolean checkMgrId(IMgr.MgrType mgrType) {
        return this.mMgrType == mgrType;
    }

    public void dispose() {
    }

    public final Context getContext() {
        return this.mContextWrapper;
    }

    public final boolean isStreamAppMode() {
        return this.mIsStreamAppMode;
    }

    public void onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
    }
}
