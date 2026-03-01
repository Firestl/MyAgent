package io.dcloud.feature.barcode2;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes2.dex */
public class BarcodeFeatureImpl implements IFeature, IWaiter {
    public BarcodeProxyMgr mBProxyMgr;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.mBProxyMgr.onDestroy();
    }

    @Override // io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) {
        return this.mBProxyMgr.doForFeature(str, obj);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return this.mBProxyMgr.execute(iWebview, str, strArr);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        BarcodeProxyMgr barcodeProxyMgr = BarcodeProxyMgr.getBarcodeProxyMgr();
        this.mBProxyMgr = barcodeProxyMgr;
        barcodeProxyMgr.setFeatureMgr(absMgr);
    }
}
