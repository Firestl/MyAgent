package io.dcloud.common.util.net;

import android.content.IntentFilter;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.net.http.IServer;

/* JADX INFO: loaded from: classes2.dex */
public class NetMgr extends AbsMgr implements IMgr.NetEvent {
    public DownloadMgr mDownloadMgr;
    public IServer mLocalServer;
    public NetCheckReceiver mNetCheckReceiver;
    public UploadMgr mUploadMgr;

    public NetMgr(ICore iCore) {
        super(iCore, "netmgr", IMgr.MgrType.NetMgr);
        this.mNetCheckReceiver = null;
        this.mLocalServer = null;
        startMiniServer();
        this.mUploadMgr = UploadMgr.getUploadMgr();
        this.mDownloadMgr = DownloadMgr.getDownloadMgr();
        this.mNetCheckReceiver = new NetCheckReceiver(this);
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction(NetCheckReceiver.simACTION);
        getContext().registerReceiver(this.mNetCheckReceiver, intentFilter);
    }

    private IServer initLocalServer() {
        Object objNewInstance;
        if (BaseInfo.SyncDebug && (objNewInstance = PlatformUtil.newInstance("io.dcloud.common.util.net.http.LocalServer", new Class[]{AbsMgr.class, Integer.TYPE}, new Object[]{this, 13131})) != null && (objNewInstance instanceof IServer)) {
            return (IServer) objNewInstance;
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.AbsMgr
    public void dispose() {
        IServer iServer = this.mLocalServer;
        if (iServer != null) {
            iServer.stop();
        }
        UploadMgr uploadMgr = this.mUploadMgr;
        if (uploadMgr != null) {
            uploadMgr.dispose();
        }
        getContext().unregisterReceiver(this.mNetCheckReceiver);
    }

    @Override // io.dcloud.common.DHInterface.AbsMgr
    public void onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (sysEventType == ISysEventListener.SysEventType.onPause) {
            IServer iServer = this.mLocalServer;
            if (iServer != null) {
                iServer.stop();
                this.mLocalServer = null;
                return;
            }
            return;
        }
        if (sysEventType == ISysEventListener.SysEventType.onResume && this.mLocalServer == null && BaseInfo.ISDEBUG) {
            IServer iServerInitLocalServer = initLocalServer();
            this.mLocalServer = iServerInitLocalServer;
            if (iServerInitLocalServer != null) {
                iServerInitLocalServer.start();
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IMgr
    public Object processEvent(IMgr.MgrType mgrType, int i, Object obj) {
        try {
            if (!checkMgrId(mgrType)) {
                return this.mCore.dispatchEvent(mgrType, i, obj);
            }
        } catch (Throwable th) {
            Logger.w("NetMgr.processEvent", th);
        }
        return null;
    }

    public void startMiniServer() {
        if (BaseInfo.ISDEBUG) {
            IServer iServerInitLocalServer = initLocalServer();
            this.mLocalServer = iServerInitLocalServer;
            if (iServerInitLocalServer != null) {
                iServerInitLocalServer.start();
            }
        }
    }
}
