package io.dcloud.feature.contacts;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.PdrUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsFeatureImpl implements IFeature {
    public JsContactsMgr mContactsMgr;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.mContactsMgr.dispose(str);
        if (PdrUtil.isEmpty(str)) {
            this.mContactsMgr = null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(final IWebview iWebview, final String str, final String[] strArr) {
        new Thread() { // from class: io.dcloud.feature.contacts.ContactsFeatureImpl.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                ContactsFeatureImpl.this.mContactsMgr.execute(iWebview, str, strArr);
            }
        }.start();
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mContactsMgr = new JsContactsMgr(absMgr.getContext());
    }
}
