package io.dcloud.common.DHInterface;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes2.dex */
public interface IDCloudWebviewClientListener extends IReflectAble {
    void doUpdateVisitedHistory(WebView webView, String str, boolean z);

    void onPageFinished(WebView webView, String str);

    void onPageStarted(WebView webView, String str, Bitmap bitmap);

    void onReceivedError(WebView webView, int i, String str, String str2);

    void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError);

    WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest);

    WebResourceResponse shouldInterceptRequest(WebView webView, String str);

    boolean shouldOverrideUrlLoading(WebView webView, String str);
}
