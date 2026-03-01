package com.supwisdom.superapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes2.dex */
public class CommonWebView extends WebView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WebViewClient f4393a;

    public class a extends WebViewClient {
        public a() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl("https://www.baidu.com");
            return true;
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public CommonWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a aVar = new a();
        this.f4393a = aVar;
        setWebViewClient(aVar);
        getRootView().setClickable(true);
    }
}
