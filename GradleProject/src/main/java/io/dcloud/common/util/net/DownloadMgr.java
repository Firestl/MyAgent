package io.dcloud.common.util.net;

import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadMgr {
    public static DownloadMgr mDownloadMgr;
    public NetWorkLoop mDownloadLoop;

    public DownloadMgr() {
        NetWorkLoop netWorkLoop = new NetWorkLoop();
        this.mDownloadLoop = netWorkLoop;
        netWorkLoop.startThreadPool();
    }

    public static DownloadMgr getDownloadMgr() {
        if (mDownloadMgr == null) {
            mDownloadMgr = new DownloadMgr();
        }
        return mDownloadMgr;
    }

    public void addQuestTask(NetWork netWork) {
        this.mDownloadLoop.addNetWork(netWork);
    }

    public void dispose() {
        Logger.d("DownloadMgr: dispose");
        this.mDownloadLoop.dispose();
        this.mDownloadLoop = null;
        mDownloadMgr = null;
    }

    public void removeTask(NetWork netWork) {
        this.mDownloadLoop.removeNetWork(netWork);
    }
}
