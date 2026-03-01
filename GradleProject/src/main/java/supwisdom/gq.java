package supwisdom;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class gq extends LinearLayout {
    public static Handler l = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ImageView f7756a;
    public TextView b;
    public ImageView c;
    public ProgressBar d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WebView f7757e;
    public final e f;
    public f g;
    public g h;
    public h i;
    public View.OnClickListener j;
    public final float k;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: supwisdom.gq$a$a, reason: collision with other inner class name */
        public class RunnableC0215a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f7759a;

            public RunnableC0215a(a aVar, View view) {
                this.f7759a = view;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f7759a.setEnabled(true);
            }
        }

        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h hVar = gq.this.i;
            if (hVar != null) {
                view.setEnabled(false);
                gq.l.postDelayed(new RunnableC0215a(this, view), 256L);
                if (view == gq.this.f7756a) {
                    hVar.a(gq.this);
                } else if (view == gq.this.c) {
                    hVar.b(gq.this);
                }
            }
        }
    }

    public class b implements DownloadListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7760a;

        public b(Context context) {
            this.f7760a = context;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setFlags(268435456);
                this.f7760a.startActivity(intent);
            } catch (Throwable unused) {
            }
        }
    }

    public class c extends WebChromeClient {
        public c() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return gq.this.g.a(gq.this, str, str2, str3, jsPromptResult);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (!gq.this.f.b) {
                gq.this.d.setVisibility(8);
            } else {
                if (i > 90) {
                    gq.this.d.setVisibility(4);
                    return;
                }
                if (gq.this.d.getVisibility() == 4) {
                    gq.this.d.setVisibility(0);
                }
                gq.this.d.setProgress(i);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            gq.this.g.c(gq.this, str);
        }
    }

    public class d extends WebViewClient {
        public d() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (gq.this.h.b(gq.this, str)) {
                return;
            }
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (gq.this.h.a(gq.this, str)) {
                return;
            }
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (gq.this.h.a(gq.this, i, str, str2)) {
                return;
            }
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (gq.this.h.a(gq.this, sslErrorHandler, sslError)) {
                return;
            }
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (gq.this.h.d(gq.this, str)) {
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7763a;
        public boolean b;

        public e(boolean z, boolean z2) {
            this.f7763a = z;
            this.b = z2;
        }
    }

    public interface f {
        boolean a(gq gqVar, String str, String str2, String str3, JsPromptResult jsPromptResult);

        void c(gq gqVar, String str);
    }

    public interface g {
        boolean a(gq gqVar, int i, String str, String str2);

        boolean a(gq gqVar, SslErrorHandler sslErrorHandler, SslError sslError);

        boolean a(gq gqVar, String str);

        boolean b(gq gqVar, String str);

        boolean d(gq gqVar, String str);
    }

    public interface h {
        void a(gq gqVar);

        void b(gq gqVar);
    }

    public gq(Context context, pp ppVar, e eVar) {
        this(context, null, ppVar, eVar);
    }

    public ImageView getBackButton() {
        return this.f7756a;
    }

    public ProgressBar getProgressbar() {
        return this.d;
    }

    public ImageView getRefreshButton() {
        return this.c;
    }

    public TextView getTitle() {
        return this.b;
    }

    public String getUrl() {
        return this.f7757e.getUrl();
    }

    public WebView getWebView() {
        return this.f7757e;
    }

    public void setChromeProxy(f fVar) {
        this.g = fVar;
        if (fVar == null) {
            this.f7757e.setWebChromeClient(null);
        } else {
            this.f7757e.setWebChromeClient(new c());
        }
    }

    public void setWebClientProxy(g gVar) {
        this.h = gVar;
        if (gVar == null) {
            this.f7757e.setWebViewClient(null);
        } else {
            this.f7757e.setWebViewClient(new d());
        }
    }

    public void setWebEventProxy(h hVar) {
        this.i = hVar;
    }

    public gq(Context context, AttributeSet attributeSet, pp ppVar, e eVar) {
        super(context, attributeSet);
        this.j = new a();
        this.f = eVar == null ? new e(false, false) : eVar;
        this.k = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        a(context);
        b(context);
        c(context);
    }

    public final void a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setVisibility(this.f.f7763a ? 0 : 8);
        ImageView imageView = new ImageView(context);
        this.f7756a = imageView;
        imageView.setOnClickListener(this.j);
        this.f7756a.setScaleType(ImageView.ScaleType.CENTER);
        this.f7756a.setImageDrawable(yp.a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABIBAMAAACnw650AAAAFVBMVEUAAAARjusRkOkQjuoRkeoRj+oQjunya570AAAABnRSTlMAinWeSkk7CjRNAAAAZElEQVRIx+3MOw6AIBQF0YsrMDGx1obaLeGH/S9BQgkJ82rypp4ceTN1ilvyKizmZIAyU7FML0JVYig55BBAfQ2EU4V4CpZJ+2AiSj11C6rUoTannBpRn4W6xNQjLBSI2+TN0w/+3HT2wPClrQAAAABJRU5ErkJggg==", context));
        this.f7756a.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.f7756a, new LinearLayout.LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LinearLayout.LayoutParams(a(1), a(25)));
        TextView textView = new TextView(context);
        this.b = textView;
        textView.setTextColor(-15658735);
        this.b.setTextSize(17.0f);
        this.b.setMaxLines(1);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(a(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.b, layoutParams);
        ImageView imageView2 = new ImageView(context);
        this.c = imageView2;
        imageView2.setOnClickListener(this.j);
        this.c.setScaleType(ImageView.ScaleType.CENTER);
        this.c.setImageDrawable(yp.a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAmVBMVEUAAAARj+oQjuoRkOsVk/AQj+oRjuoQj+oSkO3///8Rj+kRj+oQkOsTk+whm/8Qj+oRj+oQj+oSkus2p/8QjuoQj+oQj+oQj+oQj+oRj+oTkuwRj+oQj+oRj+oRj+oSkOsSkO0ZlfMbk+8XnPgQj+oRj+oQj+oQj+sSj+sRkOoSkescqv8Rj+oQj+oSj+sXku4Rj+kQjuoQjumXGBCVAAAAMnRSTlMAxPtPF8ry7CoB9npbGwe6lm0wBODazb1+aSejm5GEYjcTDwvls6uJc0g/CdWfRCF20AXrk5QAAAJqSURBVFjD7ZfXmpswEIUFphmDCxi3talurGvm/R8uYSDe5FNBwlzsxf6XmvFBmiaZ/PCdWDk9CWn61OhHCMAaXfoRAth7wx6EkMXnWyrho4yg4bDpquI8Jy78Q7eoj9cmUFijsaLM0JsD9CD0uQAa9aNdPuCFvbA7B9t/Becap8Pu6Q/2jcyH81VHc/WCHDQZXwbvtUhQ61iDlqadncU6Rp31yGkZIzOAu7AjtPpYGREzq/pY5DRFHS1siyO6HfkOKTrMjdb2qevV4zosK7MbkFY2LmYk55hL6juCIFWMOI2KGzblmho3b18EIbxL1hs6r5m2Q2WaEElwS3NW4xh6ZZJuzTtUsBKT4G0h35s4y1mNgkNoS6TZ8SKBXTZQGBNYdPTozXGYKoyLAmOasttjThT4xT6Ch+2qIjRhV9Ja3NC87Kyo5We1vCNEMW1T+j1VLZ9UhE54Q1DL52r5piJ0YxdegvWlHOwTu76uKkJX+MOTHno4YFSEbHYdhViojsLrCTg/MKnhKWaEYzvkZFM8aOkPH7iTSvoFZKD7jGEJbarkRaxQyOeWvGVIbsji152jK7TbDgRzcIuz7SGj89BFU8d30TqWeDtrILxyTkD1IXfvmHseuU3lVHDz607bw0f3xDqejm5ncd0j8VDwfoibRy8RcgTkWHBvocbDbMlJsQAkGnAOHwGy90kLmQY1Wkob07/GaCNRIzdoWK7/+6y/XkLDJCcynOGFuUrKIMuCMonNr9VpSOQoIxBgJ0SacGbzZNy4ICrkscvU2fpElYz+U3sd+aQThjfVmjNa5i15kLcojM3Gz8kP34jf4VaV3X55gNEAAAAASUVORK5CYII=", context));
        this.c.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.c, new LinearLayout.LayoutParams(-2, -2));
        addView(linearLayout, new LinearLayout.LayoutParams(-1, a(48)));
    }

    public final void c(Context context) {
        WebView webView = new WebView(context);
        this.f7757e = webView;
        webView.setVerticalScrollbarOverlay(true);
        a(this.f7757e, context);
        WebSettings settings = this.f7757e.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        this.f7757e.setVerticalScrollbarOverlay(true);
        this.f7757e.setDownloadListener(new b(context));
        try {
            try {
                this.f7757e.removeJavascriptInterface("searchBoxJavaBridge_");
                this.f7757e.removeJavascriptInterface("accessibility");
                this.f7757e.removeJavascriptInterface("accessibilityTraversal");
            } catch (Exception unused) {
                Method method = this.f7757e.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method != null) {
                    method.invoke(this.f7757e, "searchBoxJavaBridge_");
                    method.invoke(this.f7757e, "accessibility");
                    method.invoke(this.f7757e, "accessibilityTraversal");
                }
            }
        } catch (Throwable unused2) {
        }
        eq.a(this.f7757e);
        addView(this.f7757e, new LinearLayout.LayoutParams(-1, -1));
    }

    public final void b(Context context) {
        ProgressBar progressBar = new ProgressBar(context, null, R.style.Widget.ProgressBar.Horizontal);
        this.d = progressBar;
        progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_horizontal));
        this.d.setMax(100);
        this.d.setBackgroundColor(-218103809);
        addView(this.d, new LinearLayout.LayoutParams(-1, a(2)));
    }

    public void a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(userAgentString + bq.c(context));
    }

    public void a(String str) {
        this.f7757e.loadUrl(str);
        eq.a(this.f7757e);
    }

    public void a(String str, byte[] bArr) {
        this.f7757e.postUrl(str, bArr);
    }

    public void a() {
        removeAllViews();
        this.f7757e.removeAllViews();
        this.f7757e.setWebViewClient(null);
        this.f7757e.setWebChromeClient(null);
        this.f7757e.destroy();
    }

    public final int a(int i) {
        return (int) (i * this.k);
    }
}
