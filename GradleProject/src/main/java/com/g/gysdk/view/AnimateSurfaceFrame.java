package com.g.gysdk.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.g.gysdk.cta.ELoginThemeConfig;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class AnimateSurfaceFrame extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WebView f2056a;
    public a b;

    public AnimateSurfaceFrame(Context context) {
        super(context);
    }

    public AnimateSurfaceFrame(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimateSurfaceFrame(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a() {
        try {
            if (this.b != null) {
                this.b.a();
                this.b = null;
            }
            if (this.f2056a != null) {
                this.f2056a.destroy();
                this.f2056a = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(ELoginThemeConfig eLoginThemeConfig) {
        if (eLoginThemeConfig == null || (TextUtils.isEmpty(eLoginThemeConfig.getBuilder().getAuthGifBGUriPath()) && TextUtils.isEmpty(eLoginThemeConfig.getBuilder().getAuthVideoBGPath()))) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (!TextUtils.isEmpty(eLoginThemeConfig.getBuilder().getAuthVideoBGPath())) {
            a aVar = new a(getContext());
            this.b = aVar;
            aVar.a(eLoginThemeConfig);
            addView(this.b, new FrameLayout.LayoutParams(-1, -1));
            return;
        }
        if (TextUtils.isEmpty(eLoginThemeConfig.getBuilder().getAuthGifBGUriPath())) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        WebView webView = new WebView(getContext());
        this.f2056a = webView;
        addView(webView, layoutParams);
        this.f2056a.setVerticalScrollBarEnabled(false);
        this.f2056a.setHorizontalScrollBarEnabled(false);
        WebSettings settings = this.f2056a.getSettings();
        settings.setSavePassword(false);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(false);
        this.f2056a.setWebViewClient(new WebViewClient());
        this.f2056a.setBackgroundColor(0);
        String authGifBGUriPath = eLoginThemeConfig.getBuilder().getAuthGifBGUriPath();
        this.f2056a.loadDataWithBaseURL(authGifBGUriPath, "<HTML><body style=\"margin:0;\"><div align=\"center\" style=\"margin:0;\"><img border=\"none\" width=\"100%\" height=\"100%\" src= " + authGifBGUriPath + " margin=\"0px\"></div></body>", "text/html", "utf-8", null);
    }
}
