package io.dcloud.share;

import android.webkit.WebViewClient;
import io.dcloud.common.DHInterface.IReflectAble;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsWebviewClient extends WebViewClient implements IReflectAble {
    public abstract String getInitUrl();
}
