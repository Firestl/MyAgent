package com.supwisdom.superapp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.supwisdom.zueb.R;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class BrowserActivity extends Activity {
    public static String g = "http://app.html5.qq.com/navi/index";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WebView f4054a;
    public ValueCallback<Uri> c;
    public URL d;
    public boolean b = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4055e = 0;
    public Handler f = new d();

    public class a extends WebViewClient {
        public a() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            BrowserActivity.this.f.sendEmptyMessageDelayed(0, 5000L);
            if (Integer.parseInt(Build.VERSION.SDK) >= 16) {
                BrowserActivity.this.a(webView);
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }
    }

    public class b extends WebChromeClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f4057a;
        public View b;
        public WebChromeClient.CustomViewCallback c;

        public b() {
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            WebChromeClient.CustomViewCallback customViewCallback = this.c;
            if (customViewCallback != null) {
                customViewCallback.onCustomViewHidden();
                this.c = null;
            }
            View view = this.f4057a;
            if (view != null) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                viewGroup.removeView(this.f4057a);
                viewGroup.addView(this.b);
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return super.onJsAlert(null, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            return super.onJsConfirm(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        }
    }

    public class c implements DownloadListener {

        public class a implements DialogInterface.OnCancelListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(BrowserActivity.this, "fake message: refuse download...", 0).show();
            }
        }

        public class b implements DialogInterface.OnClickListener {
            public b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(BrowserActivity.this, "fake message: refuse download...", 0).show();
            }
        }

        /* JADX INFO: renamed from: com.supwisdom.superapp.ui.activity.BrowserActivity$c$c, reason: collision with other inner class name */
        public class DialogInterfaceOnClickListenerC0084c implements DialogInterface.OnClickListener {
            public DialogInterfaceOnClickListenerC0084c(c cVar) {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        public c() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            new AlertDialog.Builder(BrowserActivity.this).setTitle("allow to download？").setPositiveButton("yes", new DialogInterfaceOnClickListenerC0084c(this)).setNegativeButton("no", new b()).setOnCancelListener(new a()).show();
        }
    }

    public class d extends Handler {
        public d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    BrowserActivity.this.a();
                }
            } else {
                if (!BrowserActivity.this.b) {
                    return;
                }
                String str = "file:///sdcard/outputHtml/html/" + Integer.toString(BrowserActivity.this.f4055e) + ".html";
                if (BrowserActivity.this.f4054a != null) {
                    BrowserActivity.this.f4054a.loadUrl(str);
                }
                BrowserActivity.d(BrowserActivity.this);
            }
            super.handleMessage(message);
        }
    }

    public static /* synthetic */ int d(BrowserActivity browserActivity) {
        int i = browserActivity.f4055e;
        browserActivity.f4055e = i + 1;
        return i;
    }

    public final void a(WebView webView) {
    }

    public final void b() {
    }

    public final void c() {
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ValueCallback<Uri> valueCallback;
        if (i2 == -1) {
            if (i == 0 && this.c != null) {
                this.c.onReceiveValue((intent == null || i2 != -1) ? null : intent.getData());
                this.c = null;
                return;
            }
            return;
        }
        if (i2 != 0 || (valueCallback = this.c) == null) {
            return;
        }
        valueCallback.onReceiveValue(null);
        this.c = null;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFormat(-3);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.d = new URL(intent.getData().toString());
            } catch (NullPointerException | Exception unused) {
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
                getWindow().setFlags(16777216, 16777216);
            }
        } catch (Exception unused2) {
        }
        setContentView(R.layout.x5_activity);
        b();
        this.f.sendEmptyMessageDelayed(1, 10L);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        WebView webView = this.f4054a;
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        WebView webView = this.f4054a;
        if (webView == null || !webView.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.f4054a.goBack();
        if (Integer.parseInt(Build.VERSION.SDK) < 16) {
            return true;
        }
        a(this.f4054a);
        return true;
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        if (intent == null || this.f4054a == null || intent.getData() == null) {
            return;
        }
        this.f4054a.loadUrl(intent.getData().toString());
    }

    public final void a() {
        this.f4054a = (WebView) findViewById(R.id.webView);
        c();
        this.f4054a.setWebViewClient(new a());
        this.f4054a.setWebChromeClient(new b());
        this.f4054a.setDownloadListener(new c());
        WebSettings settings = this.f4054a.getSettings();
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(false);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setAppCachePath(getDir("appcache", 0).getPath());
        settings.setDatabasePath(getDir("databases", 0).getPath());
        settings.setGeolocationDatabasePath(getDir("geolocation", 0).getPath());
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        System.currentTimeMillis();
        URL url = this.d;
        if (url == null) {
            this.f4054a.loadUrl(g);
        } else {
            this.f4054a.loadUrl(url.toString());
        }
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }
}
