package io.dcloud.share;

import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWebview;

/* JADX INFO: loaded from: classes3.dex */
public interface IFShareApi extends IReflectAble {
    public static final String mLink = "http://ask.dcloud.net.cn/article/287";

    void authorize(IWebview iWebview, String str, String str2);

    void dispose();

    void forbid(IWebview iWebview);

    String getId();

    String getJsonObject(IWebview iWebview);

    void initConfig();

    void send(IWebview iWebview, String str, String str2);
}
