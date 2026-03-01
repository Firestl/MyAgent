package io.dcloud.common.util.net.http;

import android.text.TextUtils;
import com.lzy.okgo.model.HttpHeaders;
import dc.squareup.cookie.CookieCenter;
import java.io.IOException;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class WebkitCookieManagerProxy extends java.net.CookieManager {
    public android.webkit.CookieManager webkitCookieManager;

    public WebkitCookieManagerProxy() {
        this(null, null);
    }

    @Override // java.net.CookieManager, java.net.CookieHandler
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        if (uri == null || map == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        String string = uri.toString();
        HashMap map2 = new HashMap();
        String cookie = this.webkitCookieManager.getCookie(string);
        String cookies = CookieCenter.getCookies(string);
        if (!TextUtils.isEmpty(cookies)) {
            cookie = cookie + "; " + cookies;
        }
        if (cookie != null) {
            map2.put("Cookie", Arrays.asList(cookie));
        }
        return map2;
    }

    @Override // java.net.CookieManager
    public CookieStore getCookieStore() {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.CookieManager, java.net.CookieHandler
    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        if (uri == null || map == null) {
            return;
        }
        String string = uri.toString();
        for (String str : map.keySet()) {
            if (str != null && (str.equalsIgnoreCase(HttpHeaders.HEAD_KEY_SET_COOKIE2) || str.equalsIgnoreCase("Set-Cookie"))) {
                for (String str2 : map.get(str)) {
                    this.webkitCookieManager.setCookie(string, str2);
                    CookieCenter.putCookies(string, str2);
                }
            }
        }
    }

    public boolean removeAllCookie() {
        this.webkitCookieManager.removeAllCookie();
        CookieCenter.removeAllCookie();
        return true;
    }

    public boolean removeSessionCookie() {
        this.webkitCookieManager.removeSessionCookie();
        CookieCenter.removeSessionCookie();
        return true;
    }

    public WebkitCookieManagerProxy(CookieStore cookieStore, CookiePolicy cookiePolicy) {
        super(null, cookiePolicy);
        this.webkitCookieManager = android.webkit.CookieManager.getInstance();
    }
}
