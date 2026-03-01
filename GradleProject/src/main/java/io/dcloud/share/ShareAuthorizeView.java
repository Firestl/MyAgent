package io.dcloud.share;

import android.os.Build;
import android.webkit.WebView;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.util.Deprecated_JSUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ShareAuthorizeView extends AdaFrameItem implements IReflectAble {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6767a;
    public WebView b;
    public IWebview c;

    public ShareAuthorizeView(IWebview iWebview, String str) {
        super(iWebview.getContext());
        this.c = iWebview;
        this.f6767a = str;
        WebView webView = new WebView(iWebview.getContext());
        this.b = webView;
        a(webView);
        setMainView(this.b);
    }

    private void a(WebView webView) {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i < 11 || i >= 17) {
                return;
            }
            webView.getClass().getMethod("removeJavascriptInterface", String.class).invoke(webView, "searchBoxJavaBridge_");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void load(a aVar, String str) {
        AbsWebviewClient absWebviewClient = (AbsWebviewClient) PlatformUtil.invokeMethod(aVar.a(str), "getWebviewClient", null, new Class[]{ShareAuthorizeView.class}, new Object[]{this});
        this.b.setWebViewClient(absWebviewClient);
        this.b.loadUrl(absWebviewClient.getInitUrl());
    }

    public void onauthenticated(String str) {
        Deprecated_JSUtil.execCallback(this.c, this.f6767a, "{type:'" + str + "'}", 1, true, true);
    }

    public void onerror(String str) {
        Deprecated_JSUtil.execCallback(this.c, this.f6767a, str, 9, false, true);
    }

    public void onloaded() {
        Deprecated_JSUtil.execCallback(this.c, this.f6767a, "{evt:'load'}", 1, true, true);
    }
}
