package io.dcloud.share;

import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes3.dex */
public interface IWeiXinFShareApi extends IFShareApi {
    void launchMiniProgram(IWebview iWebview, String str, String str2);

    void openCustomerServiceChat(IWebview iWebview, String str, String str2);
}
