package io.dcloud.net;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes3.dex */
public class DownloaderFeatureImpl implements IFeature {
    public DownloadJSMgr mDownloadMgr = null;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        DownloadJSMgr downloadJSMgr = this.mDownloadMgr;
        if (downloadJSMgr != null) {
            downloadJSMgr.dispose();
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return this.mDownloadMgr.execute(iWebview, str, strArr);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mDownloadMgr = DownloadJSMgr.getInstance();
    }
}
