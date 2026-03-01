package io.dcloud.common.DHInterface;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IWebview extends IContainerView {
    public static final String COOKIE = "Cookie";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String USER_AGENT = "User-Agent";
    public static final int WEBVIEW_PROGRESS_TAP = 50;

    void addJsInterface(String str, IJsInterface iJsInterface);

    void addJsInterface(String str, String str2);

    void addStateListener(IWebviewStateListener iWebviewStateListener);

    void appendPreloadJsFile(String str);

    boolean canGoBack();

    boolean canGoForward();

    void checkIfNeedLoadOriginalUrl();

    boolean checkWhite(String str);

    void clearHistory();

    void endWebViewEvent(String str);

    void evalJS(String str);

    void evalJS(String str, ReceiveJSValue.ReceiveJSValueCallback receiveJSValueCallback);

    void evalJSSync(String str, ICallBack iCallBack);

    void executeScript(String str);

    Activity getActivity();

    Context getContext();

    String getCookie(String str);

    int getFixBottom();

    Object getFlag();

    IWebview getOpener();

    String getOriginalUrl();

    float getScale();

    float getScaleOfOpenerWebview();

    String getTitle();

    String getWebviewANID();

    String getWebviewProperty(String str);

    String getWebviewUUID();

    void goBackOrForward(int i);

    boolean hasWebViewEvent(String str);

    void initWebviewUUID(String str);

    boolean isIWebViewFocusable();

    boolean isLoaded();

    boolean isPause();

    boolean isUniService();

    boolean isUniWebView();

    void loadContentData(String str, String str2, String str3, String str4);

    void loadUrl(String str);

    IApp obtainApp();

    String obtainFrameId();

    IFrameView obtainFrameView();

    String obtainFullUrl();

    String obtainPageTitle();

    String obtainUrl();

    @Deprecated
    WebView obtainWebview();

    ViewGroup obtainWindowView();

    void onRootViewGlobalLayout(View view);

    void pause();

    void reload();

    void reload(String str);

    void reload(boolean z);

    void removeAllCookie();

    void removeSessionCookie();

    void removeStateListener(IWebviewStateListener iWebviewStateListener);

    void resume();

    void setAssistantType(String str);

    void setCookie(String str, String str2);

    void setCssFile(String str, String str2);

    void setFixBottom(int i);

    void setFlag(Object obj);

    void setFrameId(String str);

    void setIWebViewFocusable(boolean z);

    void setListenResourceLoading(JSONObject jSONObject);

    void setLoadURLHeads(String str, HashMap<String, String> map);

    void setOpener(IWebview iWebview);

    void setOriginalUrl(String str);

    void setOverrideResourceRequest(JSONArray jSONArray);

    void setOverrideUrlLoadingData(JSONObject jSONObject);

    void setPreloadJsFile(String str, boolean z);

    void setProgressView(View view);

    void setScrollIndicator(String str);

    void setWebViewCacheMode(String str);

    void setWebViewEvent(String str, Object obj);

    void setWebviewProperty(String str, String str2);

    void setWebviewclientListener(IDCloudWebviewClientListener iDCloudWebviewClientListener);

    void show(Animation animation);

    void stopLoading();

    boolean unReceiveTitle();
}
