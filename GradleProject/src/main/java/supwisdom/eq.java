package supwisdom;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class eq extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f7515a;
    public final String b;

    public eq(Activity activity, String str) {
        super(activity);
        this.f7515a = activity;
        this.b = str;
    }

    public abstract void a();

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CookieSyncManager.createInstance(this.f7515a.getApplicationContext()).sync();
        CookieManager.getInstance().setCookie(str, str2);
        CookieSyncManager.getInstance().sync();
    }

    public abstract boolean b();

    public boolean c() {
        return "v1".equals(this.b);
    }

    public static void a(WebView webView) {
        if (webView != null) {
            try {
                webView.resumeTimers();
            } catch (Throwable unused) {
            }
        }
    }
}
