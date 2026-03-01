package supwisdom;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes2.dex */
public class jn1 {
    static {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new SimpleDateFormat("yyyy-MM-dd");
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void a() {
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().removeAllCookies(null);
        } else {
            CookieManager.getInstance().removeAllCookie();
        }
    }
}
