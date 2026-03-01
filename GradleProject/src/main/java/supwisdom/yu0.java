package supwisdom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import supwisdom.ev0;

/* JADX INFO: compiled from: CommonWebViewJavaScriptBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public class yu0 extends WebViewClient implements ev0.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<WebView> f9917a;
    public WeakReference<WebViewClient> b;
    public ev0 c;

    /* JADX INFO: compiled from: CommonWebViewJavaScriptBridge.java */
    public class a implements ev0.d {
        public a() {
        }

        @Override // supwisdom.ev0.d
        public void callback(String str) {
            yu0.this.c.c(str);
        }
    }

    public static yu0 b(Context context, WebView webView) {
        yu0 yu0Var = new yu0();
        yu0Var.a(context, webView);
        return yu0Var;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String c(String str) {
        if (!str.contains(Operators.DOT_STR)) {
            return "text/plain";
        }
        int iLastIndexOf = str.lastIndexOf(Operators.DOT_STR);
        byte b = -1;
        if (iLastIndexOf <= -1) {
            return "text/plain";
        }
        int iIndexOf = str.indexOf(Operators.CONDITION_IF_STRING);
        int i = iLastIndexOf + 1;
        if (iIndexOf == -1) {
            iIndexOf = str.length();
        }
        String strSubstring = str.substring(i, iIndexOf);
        switch (strSubstring.hashCode()) {
            case 3401:
                if (strSubstring.equals("js")) {
                    b = 0;
                }
                break;
            case 98819:
                if (strSubstring.equals("css")) {
                    b = 1;
                }
                break;
            case 102340:
                if (strSubstring.equals("gif")) {
                    b = 5;
                }
                break;
            case 105441:
                if (strSubstring.equals(BitmapUtils.IMAGE_KEY_SUFFIX)) {
                    b = 4;
                }
                break;
            case 111145:
                if (strSubstring.equals("png")) {
                    b = 3;
                }
                break;
            case 3213227:
                if (strSubstring.equals("html")) {
                    b = 2;
                }
                break;
        }
        return b != 0 ? b != 1 ? b != 2 ? b != 3 ? b != 4 ? b != 5 ? "text/plain" : "image/gif" : "image/jpg" : "image/png" : "text/html" : "text/css" : "text/javascript";
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        WeakReference<WebViewClient> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.b.get().onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        WeakReference<WebViewClient> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.b.get().onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        WeakReference<WebViewClient> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.b.get().onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(21)
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        String string = webResourceRequest.getUrl() != null ? webResourceRequest.getUrl().toString() : null;
        if (!TextUtils.isEmpty(string) && string.startsWith("wjbfile://")) {
            return b(string);
        }
        WeakReference<WebViewClient> weakReference = this.b;
        return (weakReference == null || weakReference.get() == null) ? super.shouldInterceptRequest(webView, webResourceRequest) : this.b.get().shouldInterceptRequest(webView, webResourceRequest);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Uri uri = Uri.parse(str);
        if (!this.c.b(uri)) {
            WeakReference<WebViewClient> weakReference = this.b;
            return (weakReference == null || weakReference.get() == null) ? super.shouldOverrideUrlLoading(webView, str) : this.b.get().shouldOverrideUrlLoading(webView, str);
        }
        if (this.c.a(uri)) {
            this.c.a(webView.getContext());
            return true;
        }
        if (this.c.c(uri)) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                return true;
            }
            a(this.c.a());
            this.c.c.put("__WVJB_QUEUE_MESSAGE__", new a());
            return true;
        }
        if (!this.c.d(uri)) {
            this.c.e(uri);
            return true;
        }
        String strReplace = uri.toString().replace("wvjbscheme://__WVJB_RETURN_MESSAGE__/", "");
        ev0.d dVar = this.c.c.get("__WVJB_QUEUE_MESSAGE__");
        if (dVar == null) {
            return true;
        }
        dVar.callback(strReplace);
        this.c.c.remove("__WVJB_QUEUE_MESSAGE__");
        return true;
    }

    public void a(WebViewClient webViewClient) {
        this.b = new WeakReference<>(webViewClient);
    }

    public void a(String str, ev0.c cVar) {
        this.c.d.put(str, cVar);
    }

    @TargetApi(11)
    public final WebResourceResponse b(String str) {
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        File file = new File(str.replace("wjbfile://", ""));
        if (file.exists()) {
            try {
                return new WebResourceResponse(c(str), "UTF-8", new FileInputStream(file));
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } else {
            Log.e("shouldInterceptRequest", "file is not exists!");
        }
        return null;
    }

    @Override // supwisdom.ev0.e
    public void a(String str) {
        Log.d("webview1", str);
        WeakReference<WebView> weakReference = this.f9917a;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.f9917a.get().loadUrl(str);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        WeakReference<WebViewClient> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null || Build.VERSION.SDK_INT < 23) {
            return;
        }
        this.b.get().onReceivedError(webView, webResourceRequest, webResourceError);
    }

    public final void a(Context context, WebView webView) {
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(this);
        zu0 zu0Var = new zu0();
        zu0Var.a(context);
        this.f9917a = new WeakReference<>(webView);
        ev0 ev0Var = new ev0();
        this.c = ev0Var;
        ev0Var.a(context, zu0Var.a());
        this.c.a(this);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith("wjbfile://")) {
            return b(str);
        }
        WeakReference<WebViewClient> weakReference = this.b;
        if (weakReference != null && weakReference.get() != null) {
            return this.b.get().shouldInterceptRequest(webView, str);
        }
        return super.shouldInterceptRequest(webView, str);
    }
}
