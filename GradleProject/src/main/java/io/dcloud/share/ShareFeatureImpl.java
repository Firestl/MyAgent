package io.dcloud.share;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes3.dex */
public class ShareFeatureImpl implements IFeature {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f6768a;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        a aVar;
        if (str != null || (aVar = this.f6768a) == null) {
            return;
        }
        aVar.a();
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return this.f6768a.a(iWebview, str, strArr);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.f6768a = new a(absMgr, str);
    }
}
