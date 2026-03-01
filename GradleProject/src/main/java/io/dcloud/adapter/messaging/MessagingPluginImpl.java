package io.dcloud.adapter.messaging;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes2.dex */
public class MessagingPluginImpl implements IFeature {
    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        if ("sendMessage".equals(str)) {
            DHMessaging.parseMessage(iWebview, strArr[0], strArr[1]);
            return null;
        }
        if (!"listenMessage".equals(str)) {
            return null;
        }
        new SMSListener(iWebview, strArr[0]).listen();
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        DHMessagCenter.initDHMessaging(absMgr.getContext());
    }
}
