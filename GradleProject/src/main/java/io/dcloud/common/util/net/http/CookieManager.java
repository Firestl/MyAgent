package io.dcloud.common.util.net.http;

import android.content.Context;
import android.webkit.CookieSyncManager;
import java.net.CookieHandler;
import java.net.CookiePolicy;

/* JADX INFO: loaded from: classes2.dex */
public class CookieManager {
    public static void initCookieConfig(Context context) {
        CookieSyncManager.createInstance(context);
        android.webkit.CookieManager.getInstance().setAcceptCookie(true);
        CookieHandler.setDefault(new WebkitCookieManagerProxy(null, CookiePolicy.ACCEPT_ALL));
    }
}
