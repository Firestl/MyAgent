package com.g.gysdk.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.g.gysdk.R;
import com.g.gysdk.a.ap;
import com.g.gysdk.cta.ELoginThemeConfig;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ELoginWebActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RelativeLayout f2068a;
    public RelativeLayout b;
    public ImageButton c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WebView f2069e;
    public ELoginThemeConfig.Builder f;
    public boolean g = false;
    public int h = 0;
    public Resources.Theme i = null;

    private void a() {
        ELoginThemeConfig eLoginThemeConfigG = com.g.gysdk.cta.b.a().g();
        this.f = eLoginThemeConfigG == null ? new ELoginThemeConfig.Builder() : eLoginThemeConfigG.getBuilder();
        try {
            this.f2068a = (RelativeLayout) findViewById(R.id.gy_e_login_web_bg_layout);
            this.b = (RelativeLayout) findViewById(R.id.gy_e_login_nav_layout);
            this.c = (ImageButton) findViewById(R.id.gy_e_login_nav_back);
            this.d = (TextView) findViewById(R.id.gy_e_login_nav_title);
            this.f2069e = (WebView) findViewById(R.id.gy_e_login_web);
        } catch (Throwable th) {
            ap.b("页面元素加载异常");
            ap.b(th.toString());
            ap.a(th);
            finish();
        }
        b();
        c();
    }

    private void b() {
        TextView textView;
        String stringExtra;
        try {
            if (this.f.isNavTextNormal()) {
                textView = this.d;
                stringExtra = this.f.getNavWebViewText();
            } else {
                textView = this.d;
                stringExtra = getIntent().getStringExtra("title");
            }
            textView.setText(stringExtra);
            this.d.setTextColor(this.f.getNavWebViewTextColor());
            this.d.setTextSize(this.f.getNavWebViewTextSize());
            this.d.setTypeface(this.f.getNavWebViewTextTypeface());
            this.f2068a.setBackgroundResource(com.g.gysdk.cta.a.a(getApplicationContext(), this.f.getAuthBGImgPath()));
            if (this.f.isPrivacyNavGone()) {
                this.b.setVisibility(8);
            } else {
                this.b.setBackgroundColor(this.f.getPrivacyNavColor());
                if (this.f.isPrivacyNavTransparent()) {
                    this.b.getBackground().setAlpha(0);
                }
                ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
                layoutParams.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.f.getPrivacyNavHeight());
                this.b.setLayoutParams(layoutParams);
            }
            this.c.setBackgroundColor(0);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.c.getLayoutParams();
            layoutParams2.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.f.getPrivacyNavReturnWidth());
            layoutParams2.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.f.getPrivacyNavReturnHeight());
            layoutParams2.leftMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.f.getPrivacyNavReturnImgOffsetX());
            if (this.f.isPrivacyReturnImgCenterInVertical()) {
                layoutParams2.gravity = 17;
            } else {
                layoutParams2.topMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.f.getPrivacyNavReturnImgOffsetY());
            }
            this.c.setLayoutParams(layoutParams2);
            this.c.setImageResource(com.g.gysdk.cta.a.a(getApplicationContext(), this.f.getPrivacyNavReturnImgPath()));
            this.c.setOnClickListener(new View.OnClickListener() { // from class: com.g.gysdk.view.ELoginWebActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        if (ELoginWebActivity.this.f2069e != null && ELoginWebActivity.this.f2069e.canGoBack()) {
                            ELoginWebActivity.this.f2069e.goBack();
                            return;
                        }
                    } catch (Throwable th) {
                        ap.a(th);
                    }
                    ELoginWebActivity.this.finish();
                }
            });
        } catch (Throwable th) {
            ap.a(th);
            ap.a((Object) ("协议界面加载动态配置异常:" + th));
        }
    }

    private void c() {
        try {
            WebSettings settings = this.f2069e.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setSavePassword(false);
            this.f2069e.setWebViewClient(new WebViewClient());
            settings.setAllowFileAccess(false);
            settings.setAllowContentAccess(true);
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setAppCacheEnabled(true);
            settings.setUseWideViewPort(true);
            String stringExtra = getIntent().getStringExtra("url");
            this.f2069e.removeJavascriptInterface("searchBoxJavaBridge_");
            this.f2069e.removeJavascriptInterface("accessibility");
            this.f2069e.removeJavascriptInterface("accessibilityTraversal");
            this.f2069e.loadUrl(stringExtra);
        } catch (Throwable th) {
            ap.a(th);
            ap.a((Object) ("加载协议异常:" + th));
            finish();
        }
    }

    public static void start(Context context, String str, String str2) {
        try {
            Intent intent = new Intent(context, (Class<?>) ELoginWebActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("url", str);
            intent.putExtra("title", str2);
            context.startActivity(intent);
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.g.gysdk.cta.b.a().onAuthWebActivityCreate(this);
        this.g = true;
        int i = this.h;
        if (i != 0) {
            setTheme(i);
            this.h = 0;
        }
        Resources.Theme theme = this.i;
        if (theme != null) {
            setTheme(theme);
            this.i = null;
        }
        setContentView(R.layout.gy_activity_e_login_web);
        setFinishOnTouchOutside(false);
        a();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        try {
            ViewGroup viewGroup = (ViewGroup) this.f2069e.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.f2069e);
            }
            this.f2069e.removeAllViews();
            this.f2069e.destroy();
            this.f2069e = null;
        } catch (Throwable th) {
            ap.a(th);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            try {
                if (this.f2069e != null && this.f2069e.canGoBack()) {
                    this.f2069e.goBack();
                    return true;
                }
                if (i == 4 && keyEvent.getRepeatCount() == 0) {
                    finish();
                    return true;
                }
            } catch (Throwable th) {
                ap.a(th);
            }
        } else if (i == 4) {
            finish();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                getWindow().clearFlags(67108864);
                getWindow().clearFlags(134217728);
            }
            if (this.f != null && this.f.isDialogTheme() && this.f.isWebViewDialogTheme()) {
                com.g.gysdk.cta.a.a(this, this.f.getDialogWidth(), this.f.getDialogHeight(), this.f.getDialogX(), this.f.getDialogY(), this.f.isDialogBottom());
            }
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        try {
            com.g.gysdk.cta.a.a(this.f.getPrivacyStatusBarColor(), this.f.getPrivacyNavigationBarColor(), this);
            com.g.gysdk.cta.a.a(this.f.isPrivacyisLightColor(), this);
        } catch (Exception e2) {
            ap.a((Throwable) e2);
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.g) {
            super.setTheme(i);
            i = 0;
        }
        this.h = i;
    }

    @Override // android.view.ContextThemeWrapper
    public void setTheme(Resources.Theme theme) {
        if (this.g) {
            super.setTheme(theme);
            theme = null;
        }
        this.i = theme;
    }
}
