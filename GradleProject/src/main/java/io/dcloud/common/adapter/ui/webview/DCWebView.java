package io.dcloud.common.adapter.ui.webview;

import android.view.ViewGroup;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IDCloudWebviewClientListener;
import java.util.HashMap;
import org.json.JSONObject;
import supwisdom.vu;

/* JADX INFO: loaded from: classes2.dex */
public interface DCWebView {
    public static final String UserAgentExtInfo = " Html5Plus/1.0";
    public static final String UserAgentExtInfoForHBuilder = " StreamApp/1.0";
    public static final String UserAgentStreamApp = " StreamApp/1.0%s";

    void addJavascriptInterface(Object obj, String str);

    boolean canGoBack();

    boolean canGoBackOrForward(int i);

    boolean canGoForward();

    boolean checkOverrideUrl(JSONObject jSONObject, String str);

    boolean checkWhite(String str);

    void clearCache(boolean z);

    void clearHistory();

    void closeWap2AppBlockDialog(boolean z);

    String convertRelPath(String str);

    void destroy();

    void destroyWeb();

    void evalJSSync(String str, ICallBack iCallBack);

    String getBaseUrl();

    int getCacheMode();

    String getCookie(String str);

    String getPageTitle();

    vu.a getRefreshListener();

    float getScale();

    String getTitle();

    String getUrl();

    String getUrlStr();

    String getUserAgentString();

    ViewGroup getWebView();

    int getWebViewScrollY();

    void goBackOrForward(int i);

    void init();

    void initScalable(boolean z);

    boolean isDidTouch();

    void listenPageFinishTimeout(String str);

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5);

    void loadUrl(String str);

    void onPageStarted();

    void onPause();

    void onPreloadJSContent(String str);

    void onRefresh(int i);

    void onResume();

    void onUpdatePlusData(String str);

    void putHeads(String str, HashMap<String, String> map);

    void reload();

    void removeAllCookie();

    void removeSessionCookie();

    void setBlockNetworkImage(boolean z);

    void setCookie(String str, String str2);

    void setDcloudwebviewclientListener(IDCloudWebviewClientListener iDCloudWebviewClientListener);

    void setDidTouch(boolean z);

    void setHorizontalScrollBarEnabled(boolean z);

    void setPageTitle(String str);

    void setUrlStr(String str);

    void setUserAgentString(String str);

    void setVerticalScrollBarEnabled(boolean z);

    void setWebViewCacheMode(String str);

    void stopLoading();

    void webReload(boolean z);
}
