package com.cmic.gen.sdk.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cmic.gen.sdk.auth.GenAuthnHelper;

/* JADX INFO: loaded from: classes.dex */
public class d extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WebView f1768a;
    public String b;
    public String c;
    public LinearLayout d;

    public d(Context context, int i, String str, String str2) {
        super(context, i);
        try {
            this.c = str;
            this.b = str2;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private ViewGroup c() {
        View viewFindViewById;
        try {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.d = linearLayout;
            linearLayout.setOrientation(1);
            this.d.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            GenAuthThemeConfig authThemeConfig = GenAuthnHelper.getInstance(getContext()).getAuthThemeConfig();
            int clauseLayoutResID = authThemeConfig.getClauseLayoutResID();
            String str = TextUtils.isEmpty(this.c) ? com.cmic.gen.sdk.c.d[authThemeConfig.getAppLanguageType()] : this.c;
            if (clauseLayoutResID != -1) {
                RelativeLayout relativeLayoutA = e.a(getContext(), getLayoutInflater().inflate(clauseLayoutResID, (ViewGroup) this.d, false), 1118481, 0, str, (View.OnClickListener) null);
                String clauseLayoutReturnID = authThemeConfig.getClauseLayoutReturnID();
                if (!TextUtils.isEmpty(clauseLayoutReturnID) && (viewFindViewById = relativeLayoutA.findViewById(c.a(getContext(), clauseLayoutReturnID))) != null) {
                    viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cmic.gen.sdk.view.d.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            d.this.f1768a.stopLoading();
                            d.this.b();
                        }
                    });
                }
                this.d.addView(relativeLayoutA);
            } else {
                this.d.addView(e.a(getContext(), (View) null, 1118481, 2236962, str, new View.OnClickListener() { // from class: com.cmic.gen.sdk.view.d.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        d.this.f1768a.stopLoading();
                        d.this.b();
                    }
                }));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this.d;
    }

    private void d() {
        GenAuthThemeConfig authThemeConfig = GenAuthnHelper.getInstance(getContext()).getAuthThemeConfig();
        WebView webView = new WebView(getContext());
        this.f1768a = webView;
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setSavePassword(false);
        settings.setDomStorageEnabled(authThemeConfig.getWebStorage());
        settings.setJavaScriptEnabled(true);
        this.d.addView(this.f1768a, new LinearLayout.LayoutParams(-1, -1));
        if (Build.VERSION.SDK_INT < 17) {
            this.f1768a.removeJavascriptInterface("searchBoxJavaBridge_");
            this.f1768a.removeJavascriptInterface("accessibility");
            this.f1768a.removeJavascriptInterface("accessibilityTraversal");
        }
        this.f1768a.setWebViewClient(new WebViewClient());
        this.f1768a.loadUrl(this.b);
    }

    public void a() {
        View decorView;
        requestWindowFeature(1);
        int i = 0;
        getWindow().setFeatureDrawableAlpha(0, 0);
        GenAuthThemeConfig authThemeConfig = GenAuthnHelper.getInstance(getContext()).getAuthThemeConfig();
        if (Build.VERSION.SDK_INT >= 21 && authThemeConfig.getStatusBarColor() != 0) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().clearFlags(67108864);
            getWindow().setStatusBarColor(authThemeConfig.getStatusBarColor());
            getWindow().setNavigationBarColor(authThemeConfig.getStatusBarColor());
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (authThemeConfig.isLightColor()) {
                decorView = getWindow().getDecorView();
                i = 8192;
            } else {
                decorView = getWindow().getDecorView();
            }
            decorView.setSystemUiVisibility(i);
        }
        setContentView(c());
    }

    public void b() {
        if (this.f1768a.canGoBack()) {
            this.f1768a.goBack();
        } else {
            dismiss();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        WebView webView = this.f1768a;
        if (webView != null) {
            webView.stopLoading();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        if (this.d == null) {
            a();
        }
        if (this.f1768a == null) {
            d();
        }
        super.show();
    }
}
