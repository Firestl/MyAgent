package cn.com.chinatelecom.account.api;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public class CtAccountJsBridge implements cn.com.chinatelecom.account.api.b.a {
    public static final String TAG = "CtAccountJsBridge";
    public static Handler mHandler = new Handler(Looper.getMainLooper());
    public a callback;
    public WebView mWebView;

    public interface a {
        void a(String str);
    }

    public CtAccountJsBridge(WebView webView) {
        this.mWebView = webView;
    }

    public CtAccountJsBridge(a aVar) {
        this.callback = aVar;
    }

    @Override // cn.com.chinatelecom.account.api.b.a
    public void callbackPreCode(final String str) {
        CtAuth.info(TAG, "callbackPreCode:" + str);
        mHandler.post(new Runnable() { // from class: cn.com.chinatelecom.account.api.CtAccountJsBridge.1
            @Override // java.lang.Runnable
            public void run() {
                if (CtAccountJsBridge.this.mWebView != null) {
                    CtAccountJsBridge.this.mWebView.loadUrl("javascript:ejsBridge.callbackPreCode('" + str + "')");
                    return;
                }
                if (CtAccountJsBridge.this.callback != null) {
                    CtAccountJsBridge.this.callback.a("javascript:ejsBridge.callbackPreCode('" + str + "')");
                }
            }
        });
    }

    @Override // cn.com.chinatelecom.account.api.b.a
    public void callbackPreCodeParams(final String str) {
        CtAuth.info(TAG, "callbackPreCodeParams:" + str);
        mHandler.post(new Runnable() { // from class: cn.com.chinatelecom.account.api.CtAccountJsBridge.2
            @Override // java.lang.Runnable
            public void run() {
                if (CtAccountJsBridge.this.mWebView != null) {
                    CtAccountJsBridge.this.mWebView.loadUrl("javascript:ejsBridge.callbackPreCodeParams('" + str + "')");
                    return;
                }
                if (CtAccountJsBridge.this.callback != null) {
                    CtAccountJsBridge.this.callback.a("javascript:ejsBridge.callbackPreCodeParams('" + str + "')");
                }
            }
        });
    }

    @JavascriptInterface
    public void getPreCodeParams(String str) {
        CtAuth.info(TAG, "getPreCodeParams:" + str);
        CtAuth.getInstance().getPreCodeParamsByJs(str, this);
    }

    @JavascriptInterface
    public void requestPreCode(String str) {
        CtAuth.info(TAG, "requestPreCode:" + str);
        CtAuth.getInstance().requestPreCodeByJs(str, this);
    }
}
