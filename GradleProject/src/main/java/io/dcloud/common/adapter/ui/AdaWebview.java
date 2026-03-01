package io.dcloud.common.adapter.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.taobao.weex.common.Constants;
import dc.squareup.okhttp3.internal.http2.Http2Codec;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IDCloudWebviewClientListener;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IJsInterface;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import io.dcloud.common.adapter.ui.RecordView;
import io.dcloud.common.adapter.ui.webview.DCWebView;
import io.dcloud.common.adapter.ui.webview.OnPageFinishedCallack;
import io.dcloud.common.adapter.ui.webview.WebResUtil;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.net.http.WebkitCookieManagerProxy;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.CookieHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.json.JSONArray;
import org.json.JSONObject;
import supwisdom.av;
import supwisdom.bv;
import supwisdom.cx0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdaWebview extends AdaContainerFrameItem implements IWebview {
    public static boolean ScreemOrientationChangedNeedLayout = false;
    public static RecordView mRecordView = null;
    public static String sCustomUserAgent = null;
    public static CustomeizedInputConnection sCustomeizedInputConnection = null;
    public static String sDefalutUserAgent = null;
    public static boolean setedWebViewData = false;
    public String errorPageUrl;
    public MessageHandler.IMessages executeScriptListener;
    public String favoriteOptions;
    public boolean hasErrorPage;
    public boolean isDisposed;
    public boolean isPause;
    public boolean isStart;
    public boolean justClearOption;
    public String mAppid;
    public String mCssString;
    public String mEncoding;
    public String[] mEvalJsOptionStack;
    public int mFixBottomHeight;
    public Object mFlag;
    public String mForceAHeadJsFile;
    public boolean mForceAHeadJsFileLoaded;
    public String mFrameId;
    public AdaFrameView mFrameView;
    public String mInjectGEO;
    public boolean mInjectGeoLoaded;
    public String mInjectPlusLoadedUrl;
    public String mInjectPlusWidthJs;
    public boolean mIsAdvanceCss;
    public IJsInterface mJsInterfaces;
    public JSONObject mListenResourceLoadingOptions;
    public boolean mLoadCompleted;
    public boolean mLoaded;
    public boolean mLoading;
    public MessageHandler.IMessages mMesssageListener;
    public boolean mNeedInjection;
    public boolean mNeedSitemapJson;
    public IWebview mOpener;
    public JSONArray mOverrideResourceRequestOptions;
    public JSONObject mOverrideUrlLoadingDataOptions;
    public String mPlusInjectTag;
    public boolean mPlusLoaded;
    public boolean mPlusLoading;
    public String mPlusrequire;
    public ArrayList<String> mPreloadJsFile;
    public boolean mPreloadJsLoaded;
    public boolean mPreloadJsLoading;
    public int mProgress;
    public int mProgressIntValue;
    public View mProgressView;
    public ReceiveJSValue mReceiveJSValue_android42;
    public String mRecordLastUrl;
    public boolean mShareable;
    public ArrayList<IWebviewStateListener> mStateListeners;
    public String mVideoFullscreen;
    public bv mWebProgressView;
    public DCWebView mWebViewImpl;
    public AdaWebViewParent mWebViewParent;
    public String mWebviewANID;
    public String mWebviewUUID;
    public String needTouchEvent;
    public String originalUrl;
    public String shareOptions;
    public boolean unReceiveTitle;

    public interface IFExecutePreloadJSContentCallBack {
        void callback(String str, String str2);
    }

    public class OverrideResourceRequestItem {
        public String redirect = null;
        public String mime = null;
        public String encoding = null;
        public JSONObject headerJson = null;

        public OverrideResourceRequestItem() {
        }
    }

    public class WebProgressView extends av {
        public WebProgressView(Context context) {
            super(context);
        }
    }

    public AdaWebview(Context context) {
        super(context);
        this.unReceiveTitle = true;
        this.mWebViewParent = null;
        this.mRecordLastUrl = null;
        this.mWebViewImpl = null;
        this.mFrameView = null;
        this.mLoaded = false;
        this.mLoadCompleted = false;
        this.mPreloadJsLoaded = false;
        this.mPreloadJsLoading = false;
        this.mPlusLoaded = false;
        this.mPlusLoading = false;
        this.mPlusInjectTag = "page_finished";
        this.mIsAdvanceCss = false;
        this.mNeedInjection = true;
        this.mEncoding = null;
        this.mWebviewUUID = null;
        this.mWebviewANID = null;
        this.mFrameId = null;
        this.mJsInterfaces = null;
        this.hasErrorPage = false;
        this.errorPageUrl = null;
        this.originalUrl = null;
        this.mVideoFullscreen = "auto";
        this.needTouchEvent = "";
        this.favoriteOptions = "";
        this.shareOptions = "";
        this.mShareable = true;
        this.mPlusrequire = "normal";
        this.mInjectGEO = "none";
        this.mInjectGeoLoaded = false;
        this.mProgressIntValue = 0;
        this.isDisposed = false;
        this.mReceiveJSValue_android42 = null;
        this.isPause = false;
        this.mFlag = null;
        this.mInjectPlusLoadedUrl = null;
        this.mEvalJsOptionStack = null;
        this.mForceAHeadJsFileLoaded = false;
        this.mForceAHeadJsFile = null;
        this.mPreloadJsFile = new ArrayList<>(2);
        this.mCssString = "";
        this.executeScriptListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.4
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                String str = (String) obj;
                DCWebView dCWebView = AdaWebview.this.mWebViewImpl;
                if (dCWebView != null) {
                    if (!str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
                        str = AbsoluteConst.PROTOCOL_JAVASCRIPT + str;
                    }
                    dCWebView.loadUrl(str);
                }
            }
        };
        this.mMesssageListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.5
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                Object[] objArr = (Object[]) obj;
                AdaWebview.this.mJsInterfaces.exec(String.valueOf(objArr[0]), String.valueOf(objArr[1]), (JSONArray) objArr[2]);
            }
        };
        this.mStateListeners = null;
        this.mProgress = 0;
        this.isStart = false;
        this.justClearOption = false;
        this.mLoading = false;
        this.mNeedSitemapJson = false;
        this.mOverrideResourceRequestOptions = null;
        this.mOverrideUrlLoadingDataOptions = null;
        this.mListenResourceLoadingOptions = null;
        initANID();
    }

    private String checkRedCssline(String str) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return stringBuffer.substring(0, stringBuffer.length() - 1);
                }
                stringBuffer.append(JSUtil.QUOTE + line + "\"\n+");
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static void clearData() {
        setedWebViewData = false;
        sCustomUserAgent = null;
        sDefalutUserAgent = null;
    }

    public static IJsInterface[] combineObj2Array(IJsInterface[] iJsInterfaceArr, IJsInterface iJsInterface) {
        IJsInterface[] iJsInterfaceArr2;
        if (iJsInterfaceArr == null) {
            iJsInterfaceArr2 = new IJsInterface[1];
        } else {
            int length = iJsInterfaceArr.length;
            IJsInterface[] iJsInterfaceArr3 = new IJsInterface[length + 1];
            System.arraycopy(iJsInterfaceArr, 0, iJsInterfaceArr3, 0, length);
            iJsInterfaceArr2 = iJsInterfaceArr3;
        }
        iJsInterfaceArr2[iJsInterfaceArr2.length] = iJsInterface;
        return iJsInterfaceArr2;
    }

    private void pushEvalJsOption(String str) {
        String[] strArr = this.mEvalJsOptionStack;
        if (strArr == null) {
            this.mEvalJsOptionStack = new String[1];
        } else {
            String[] strArr2 = new String[strArr.length + 1];
            this.mEvalJsOptionStack = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        }
        String[] strArr3 = this.mEvalJsOptionStack;
        strArr3[strArr3.length - 1] = str;
        Logger.d("adawebview", "webviewimp=(" + this.mWebViewImpl + ");pushEvalJs=" + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseConfigCallback() {
        try {
            if (Build.VERSION.SDK_INT < 16) {
                Field declaredField = WebView.class.getDeclaredField("mWebViewCore").getType().getDeclaredField("mBrowserFrame").getType().getDeclaredField("sConfigCallback");
                declaredField.setAccessible(true);
                declaredField.set(null, null);
            } else {
                Field declaredField2 = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (declaredField2 != null) {
                    declaredField2.setAccessible(true);
                    declaredField2.set(null, null);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    private void startWebProgress() {
        int iStringToColor;
        if (this.mFrameView.obtainFrameOptions().mProgressJson == null) {
            bv bvVar = this.mWebProgressView;
            if (bvVar != null) {
                bvVar.setVisibility(8);
                if (this.mWebProgressView.getParent() != null) {
                    ((ViewGroup) this.mWebProgressView.getParent()).removeView(this.mWebProgressView);
                }
                this.mWebProgressView = null;
                return;
            }
            return;
        }
        if (this.mWebProgressView == null) {
            this.mWebProgressView = new bv(getContext());
        }
        JSONObject jSONObject = this.mFrameView.obtainFrameOptions().mProgressJson;
        String strOptString = jSONObject.optString("color", "#00FF00");
        String strOptString2 = jSONObject.optString("height", "2px");
        try {
            iStringToColor = Color.parseColor(strOptString);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(strOptString);
        }
        this.mWebProgressView.setColorInt(iStringToColor);
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(strOptString2, obtainWindowView().getMeasuredWidth(), 0, getScale());
        this.mWebProgressView.setVisibility(0);
        this.mWebProgressView.setAlphaInt(255);
        if (this.mWebProgressView.getParent() == null && getWebviewParent() != null) {
            ViewGroup viewGroup = (ViewGroup) getWebviewParent().obtainMainView();
            if (viewGroup == null) {
                return;
            } else {
                viewGroup.addView(this.mWebProgressView, new ViewGroup.LayoutParams(-1, iConvertToScreenInt));
            }
        }
        this.mWebProgressView.c();
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.DHInterface.IContainerView
    public void addFrameItem(AdaFrameItem adaFrameItem) {
        super.addFrameItem(adaFrameItem);
    }

    public void addJsInterface(String str, String str2) {
        this.mWebViewImpl.addJavascriptInterface(str2, str);
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void addStateListener(IWebviewStateListener iWebviewStateListener) {
        if (this.mStateListeners == null) {
            this.mStateListeners = new ArrayList<>();
        }
        if (iWebviewStateListener != null) {
            this.mStateListeners.add(iWebviewStateListener);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void appendPreloadJsFile(String str) {
        this.mPreloadJsFile.add(str);
        Logger.d("AdaWebview", "appendPreloadJsFile mPreloadJsFile=" + this.mPreloadJsFile + ";this=" + this);
        if (this.mPlusLoaded) {
            Log.d("AdaWebview", "appendPreloadJsFile---=" + str);
            String strLoadFileContent = loadFileContent(str, this.mFrameView.obtainApp().obtainRunningAppMode() == 1 ? 0 : 2);
            if (TextUtils.isEmpty(strLoadFileContent)) {
                return;
            }
            loadUrl(AbsoluteConst.PROTOCOL_JAVASCRIPT + strLoadFileContent + ";");
        }
    }

    public boolean backOrForward(int i) {
        return this.mWebViewImpl.canGoBackOrForward(i);
    }

    public boolean canGoBack() {
        boolean z = !this.justClearOption && this.mWebViewImpl.canGoBack();
        Logger.d(AdaFrameItem.TAG, "canGoBack" + this.mWebViewImpl.getUrlStr() + ";" + this.justClearOption + ";" + z);
        return z;
    }

    public boolean canGoForward() {
        return !this.justClearOption && this.mWebViewImpl.canGoForward();
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void checkIfNeedLoadOriginalUrl() {
        if (this.mLoading || this.mLoaded) {
            return;
        }
        loadUrl(getOriginalUrl());
    }

    public void checkInjectSitemap() {
        if (this.mNeedSitemapJson && this.mLoaded && this.mPreloadJsLoaded) {
            StringBuffer stringBuffer = new StringBuffer();
            File file = new File(BaseInfo.sBaseFsSitMapPath + File.separator + obtainApp().obtainAppId() + "_sitemap.json");
            if (file.exists()) {
                try {
                    stringBuffer.append(";window.__wap2app_sitemap=");
                    stringBuffer.append(IOUtil.toString(new FileInputStream(file)));
                    stringBuffer.append(";wap2app&wap2app.initSitemap();\n");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                executeScript(stringBuffer.toString());
                this.mNeedSitemapJson = false;
            }
        }
    }

    public boolean checkOverrideUrl(String str) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView == null) {
            return false;
        }
        return dCWebView.checkOverrideUrl(this.mOverrideUrlLoadingDataOptions, str);
    }

    public void checkPreLoadJsContent() {
        this.mWebViewImpl.onPreloadJSContent("checkPreLoadJsContent " + this);
    }

    public boolean checkResourceLoading(String str) {
        JSONObject jSONObject = this.mListenResourceLoadingOptions;
        if (jSONObject == null || !jSONObject.has("match")) {
            return true;
        }
        try {
            return Pattern.compile(this.mListenResourceLoadingOptions.optString("match")).matcher(str).matches();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public OverrideResourceRequestItem checkResourceRequestUrl(String str) {
        if (this.mOverrideResourceRequestOptions == null || Build.VERSION.SDK_INT < 15) {
            return null;
        }
        for (int i = 0; i < this.mOverrideResourceRequestOptions.length(); i++) {
            try {
                JSONObject jSONObjectOptJSONObject = this.mOverrideResourceRequestOptions.optJSONObject(i);
                String strOptString = jSONObjectOptJSONObject.optString("match", "");
                if (!TextUtils.isEmpty(strOptString) && Pattern.compile(strOptString).matcher(str).matches()) {
                    String strConvert2AbsFullPath = obtainApp().convert2AbsFullPath(jSONObjectOptJSONObject.optString("redirect"));
                    String strOptString2 = jSONObjectOptJSONObject.optString("mime", PdrUtil.getMimeType(strConvert2AbsFullPath));
                    String strOptString3 = jSONObjectOptJSONObject.optString(Http2Codec.ENCODING, "utf-8");
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("header");
                    OverrideResourceRequestItem overrideResourceRequestItem = new OverrideResourceRequestItem();
                    overrideResourceRequestItem.redirect = strConvert2AbsFullPath;
                    overrideResourceRequestItem.encoding = strOptString3;
                    overrideResourceRequestItem.mime = strOptString2;
                    overrideResourceRequestItem.headerJson = jSONObjectOptJSONObject2;
                    return overrideResourceRequestItem;
                }
            } catch (Exception e2) {
                Logger.e("AdaWebview", "checkResourceRequestUrl e==" + e2.getMessage());
                return null;
            }
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public boolean checkWhite(String str) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            return dCWebView.checkWhite(str);
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void clearHistory() {
        if (this.mWebViewImpl == null) {
            return;
        }
        Logger.d(AdaFrameItem.TAG, "clearHistory url=" + this.mWebViewImpl.getUrlStr());
        this.justClearOption = true;
        this.mWebViewImpl.loadData("<html><head><meta charset=\"utf-8\"></head><body></body><html>", "text/html", "utf-8");
        this.mWebViewImpl.setUrlStr("");
    }

    public void dispatchWebviewStateEvent(int i, Object obj) {
        if (i != 1) {
            if (i == 3) {
                int i2 = Integer.parseInt(String.valueOf(obj));
                this.mProgress = i2;
                if (!this.isStart && i2 < 100) {
                    startWebProgress();
                    this.isStart = true;
                }
                if (this.mProgress >= 100 && this.isStart) {
                    this.isStart = false;
                    bv bvVar = this.mWebProgressView;
                    if (bvVar != null) {
                        bvVar.a();
                    }
                }
            }
        } else if (this.isStart) {
            this.isStart = false;
            bv bvVar2 = this.mWebProgressView;
            if (bvVar2 != null) {
                bvVar2.a();
            }
        }
        ArrayList<IWebviewStateListener> arrayList = this.mStateListeners;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                this.mStateListeners.get(size).onCallBack(i, obj);
            }
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        if (this.isDisposed) {
            return;
        }
        this.isDisposed = true;
        this.mProgressView = null;
        if (this.mFrameView.getFrameType() == 5 && !this.mLoaded) {
            if (this.mProgressIntValue < 50) {
                Intent intent = new Intent();
                intent.setAction(getActivity().getPackageName() + ".streamdownload.downloadfinish." + this.mFrameView.obtainApp().obtainAppId());
                intent.putExtra("appid", this.mFrameView.obtainApp().obtainAppId());
                intent.putExtra(AbsoluteConst.JSON_KEY_PROGRESS, this.mProgressIntValue);
                intent.putExtra("flag", AbsoluteConst.STREAMAPP_KEY_DIRECT_PAGE_PROGRESSED);
                getActivity().sendBroadcast(intent);
            } else {
                this.mFrameView.obtainApp().checkOrLoadlaunchWebview();
            }
        }
        BaseInfo.s_Webview_Count--;
        try {
            DCWebView dCWebView = this.mWebViewImpl;
            if (dCWebView != null) {
                dCWebView.stopLoading();
            }
        } catch (Exception unused) {
        }
        MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.3
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                try {
                    AdaWebview adaWebview = AdaWebview.this;
                    if (adaWebview.mFrameView != null) {
                        adaWebview.mFrameView = null;
                    }
                    DCWebView dCWebView2 = adaWebview.mWebViewImpl;
                    if (dCWebView2 != null) {
                        dCWebView2.clearCache(false);
                        if (AdaWebview.this.mWebViewImpl.getWebView().getParent() != null) {
                            ((ViewGroup) AdaWebview.this.mWebViewImpl.getWebView().getParent()).removeView(AdaWebview.this.mWebViewImpl.getWebView());
                        }
                        AdaWebview.this.mWebViewImpl.destroyWeb();
                        AdaWebview.this.releaseConfigCallback();
                        AdaWebview.this.mWebViewImpl = null;
                    }
                    AdaWebview adaWebview2 = AdaWebview.this;
                    adaWebview2.mJsInterfaces = null;
                    adaWebview2.mMesssageListener = null;
                    adaWebview2.executeScriptListener = null;
                    adaWebview2.mWebViewParent = null;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (AdaWebview.this.mStateListeners != null) {
                    AdaWebview.this.mStateListeners.clear();
                }
                AdaWebview.this.mStateListeners = null;
                System.gc();
            }
        }, null);
    }

    public void endWebViewEvent(String str) {
        if (this.mWebViewParent == null) {
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.PULL_DOWN_REFRESH)) {
            this.mWebViewParent.endPullRefresh();
        } else if (PdrUtil.isEquals(str, AbsoluteConst.BOUNCE_REGISTER)) {
            this.mWebViewParent.resetBounce();
        }
    }

    public void evalJS(String str) {
        if (this.mPlusLoaded) {
            executeScript(str);
        } else {
            pushEvalJsOption(str);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void evalJSSync(String str, ICallBack iCallBack) {
        if (this.mWebViewImpl != null) {
            if (!str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
                str = AbsoluteConst.PROTOCOL_JAVASCRIPT + str;
            }
            this.mWebViewImpl.evalJSSync(str, iCallBack);
        }
    }

    public String execScript(String str, String str2, JSONArray jSONArray, boolean z) {
        if (!z) {
            return this.mJsInterfaces.exec(str, str2, jSONArray);
        }
        MessageHandler.IMessages iMessages = this.mMesssageListener;
        if (iMessages == null) {
            return null;
        }
        MessageHandler.sendMessage(iMessages, new Object[]{str, str2, jSONArray});
        return null;
    }

    public void executeScript(String str) {
        MessageHandler.IMessages iMessages;
        if (str == null || (iMessages = this.executeScriptListener) == null) {
            return;
        }
        MessageHandler.sendMessage(iMessages, str);
    }

    public void execute_eval_js_stack() {
        if (this.mEvalJsOptionStack == null) {
            return;
        }
        Logger.d("adawebview", "webviewimp=" + this.mWebViewImpl + ";execute_eval_js_stack count=" + this.mEvalJsOptionStack.length);
        int i = 0;
        while (true) {
            String[] strArr = this.mEvalJsOptionStack;
            if (i >= strArr.length) {
                this.mEvalJsOptionStack = null;
                return;
            } else {
                executeScript(strArr[i]);
                i++;
            }
        }
    }

    public String getAppName() {
        AdaFrameView adaFrameView = this.mFrameView;
        return (adaFrameView == null || adaFrameView.obtainApp() == null) ? "" : this.mFrameView.obtainApp().obtainAppName();
    }

    public String getCookie(String str) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            return dCWebView.getCookie(str);
        }
        return null;
    }

    public String getCssString() {
        return this.mCssString;
    }

    public DCWebView getDCWebView() {
        return this.mWebViewImpl;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public int getFixBottom() {
        return this.mFixBottomHeight;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public Object getFlag() {
        return this.mFlag;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public IWebview getOpener() {
        return this.mOpener;
    }

    public String getOriginalUrl() {
        return this.originalUrl;
    }

    public String getPreLoadJsString() {
        IApp iAppObtainApp;
        if (PdrUtil.isEmpty(this.mPreloadJsFile) || (iAppObtainApp = this.mFrameView.obtainApp()) == null || this.mPreloadJsFile.size() <= 0) {
            return "";
        }
        int i = iAppObtainApp.obtainRunningAppMode() == 1 ? 0 : 2;
        String str = ";";
        for (String str2 : this.mPreloadJsFile) {
            if (!this.mPlusrequire.equals("none") || (!str2.contains("__wap2app.js") && !str2.contains("__wap2appconfig.js"))) {
                String strWrapAppendJsFile = wrapAppendJsFile(str2, i);
                if (!TextUtils.isEmpty(strWrapAppendJsFile)) {
                    str = str + strWrapAppendJsFile + "\n";
                }
            }
        }
        return str + "\n";
    }

    public float getScale() {
        return this.mWebViewImpl.getScale();
    }

    public float getScaleOfOpenerWebview() {
        return getScale();
    }

    public String getScreenAndDisplayJson(IWebview iWebview) {
        float scale = iWebview.getScale();
        IApp iAppObtainApp = iWebview.obtainApp();
        int i = iAppObtainApp.getInt(2);
        int i2 = iAppObtainApp.getInt(0);
        int i3 = (int) (i2 / scale);
        return StringUtil.format("(function(p){p.screen.scale=%f;p.screen.resolutionHeight=%d;p.screen.resolutionWidth=%d;p.screen.height=%d;p.screen.width=%d;p.screen.dpiX=%f;p.screen.dpiY=%f;p.display.resolutionHeight=%d;p.display.resolutionWidth=%d;})(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus));", Float.valueOf(scale), Integer.valueOf((int) (i / scale)), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(DeviceInfo.dpiX), Float.valueOf(DeviceInfo.dpiY), Integer.valueOf((int) (iAppObtainApp.getInt(1) / scale)), Integer.valueOf(i3));
    }

    public String getTitle() {
        return this.mWebViewImpl.getTitle();
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public String getWebviewANID() {
        return this.mWebviewANID;
    }

    public AdaWebViewParent getWebviewParent() {
        return this.mWebViewParent;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public String getWebviewProperty(String str) {
        if ("getShareOptions".equals(str)) {
            return this.shareOptions;
        }
        if ("getFavoriteOptions".equals(str)) {
            return this.favoriteOptions;
        }
        if ("needTouchEvent".equals(str)) {
            return String.valueOf(this.needTouchEvent);
        }
        if ("User-Agent".equals(str)) {
            DCWebView dCWebView = this.mWebViewImpl;
            if (dCWebView != null) {
                return dCWebView.getUserAgentString();
            }
            return null;
        }
        if (AbsoluteConst.JSON_KEY_VIDEO_FULL_SCREEN.equals(str)) {
            return this.mVideoFullscreen;
        }
        if ("plusrequire".equals(str)) {
            return this.mPlusrequire;
        }
        if (!AbsoluteConst.JSON_KEY_SHAREABLE.equals(str)) {
            return null;
        }
        return this.mShareable + "";
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public final String getWebviewUUID() {
        return this.mWebviewUUID;
    }

    public String get_eval_js_stack() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.mEvalJsOptionStack != null) {
            int i = 0;
            while (true) {
                String[] strArr = this.mEvalJsOptionStack;
                if (i >= strArr.length) {
                    break;
                }
                String str = strArr[i];
                if (str.endsWith(";")) {
                    stringBuffer.append(str);
                } else {
                    stringBuffer.append(str);
                    stringBuffer.append(";");
                }
                i++;
            }
            this.mEvalJsOptionStack = null;
        }
        return stringBuffer.toString();
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void goBackOrForward(int i) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.goBackOrForward(i);
        }
    }

    public boolean hadClearHistory(String str) {
        return this.justClearOption && PdrUtil.isEquals(str, "data:text/html,<html><head><meta charset=\"utf-8\"></head><body></body><html>");
    }

    public boolean hasPreLoadJsFile() {
        return this.mPreloadJsFile.size() > 0;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public boolean hasWebViewEvent(String str) {
        AdaWebViewParent adaWebViewParent;
        if (!PdrUtil.isEquals(str, AbsoluteConst.PULL_DOWN_REFRESH) || (adaWebViewParent = this.mWebViewParent) == null) {
            return false;
        }
        return adaWebViewParent.isSetPull2Refresh;
    }

    public void init() {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.init();
        }
    }

    public void initANID() {
        if (TextUtils.isEmpty(this.mWebviewANID)) {
            this.mWebviewANID = "AD_Webview" + System.currentTimeMillis();
        }
    }

    public void initSitemapState() {
        this.mNeedSitemapJson = (BaseInfo.isWap2AppAppid(this.mAppid) && this.mFrameView.getFrameType() == 2) || this.mFrameView.getFrameType() == 4;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public final void initWebviewUUID(String str) {
        this.mWebviewUUID = str;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public boolean isDisposed() {
        return this.isDisposed;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public boolean isIWebViewFocusable() {
        return obtainWindowView().isFocusable();
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public boolean isLoaded() {
        return this.mLoaded;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public boolean isPause() {
        return this.isPause;
    }

    public boolean isRealInject(String str) {
        return this.mPlusLoaded && TextUtils.equals(PdrUtil.getUrlPathName(str), PdrUtil.getUrlPathName(this.mInjectPlusLoadedUrl));
    }

    public boolean isUniService() {
        return false;
    }

    public boolean isUniWebView() {
        return false;
    }

    public void loadContentData(String str, String str2, String str3, String str4) {
        this.mWebViewImpl.loadDataWithBaseURL(str, str2, str3, str4, str);
    }

    public boolean loadCssFile() {
        if (PdrUtil.isEmpty(this.mCssString)) {
            return false;
        }
        String strReplaceAll = this.mCssString.replaceAll(JSUtil.QUOTE, "'");
        this.mCssString = strReplaceAll;
        loadUrl("javascript:var container = document.getElementsByTagName('head')[0];\nvar addStyle = document.createElement('style');\naddStyle.rel = 'stylesheet';\naddStyle.type = 'text/css';\naddStyle.innerHTML = " + checkRedCssline(strReplaceAll) + ";\ncontainer.appendChild(addStyle);\nfirstNode = container.children[0];\n    container.appendChild(addStyle);\n");
        return true;
    }

    public void loadFileContent(IFExecutePreloadJSContentCallBack iFExecutePreloadJSContentCallBack) {
        if (PdrUtil.isEmpty(this.mPreloadJsFile)) {
            return;
        }
        try {
            IApp iAppObtainApp = this.mFrameView.obtainApp();
            if (iAppObtainApp != null) {
                int i = iAppObtainApp.obtainRunningAppMode() == 1 ? 0 : 2;
                for (String str : this.mPreloadJsFile) {
                    String strWrapAppendJsFile = wrapAppendJsFile(str, i);
                    if (!TextUtils.isEmpty(strWrapAppendJsFile)) {
                        String str2 = AbsoluteConst.PROTOCOL_JAVASCRIPT + strWrapAppendJsFile + ";";
                        if (iFExecutePreloadJSContentCallBack == null) {
                            loadUrl(str2);
                        } else {
                            iFExecutePreloadJSContentCallBack.callback(this.mWebViewImpl.convertRelPath(str), str2);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void loadForceAHeadJs() {
        if (PdrUtil.isEmpty(this.mFrameView.obtainApp()) || this.mForceAHeadJsFileLoaded || TextUtils.isEmpty(this.mForceAHeadJsFile)) {
            return;
        }
        String strLoadFileContent = loadFileContent(this.mForceAHeadJsFile, this.mFrameView.obtainApp().obtainRunningAppMode() == 1 ? 0 : 2);
        if (TextUtils.isEmpty(strLoadFileContent)) {
            return;
        }
        loadUrl(AbsoluteConst.PROTOCOL_JAVASCRIPT + strLoadFileContent + ";");
        this.mForceAHeadJsFileLoaded = true;
    }

    public void loadUrl(String str) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null && PdrUtil.isEmpty(dCWebView.getUrlStr()) && str != null && !str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
            this.mWebViewImpl.setUrlStr(str);
            this.hasErrorPage = false;
            this.errorPageUrl = null;
        }
        DCWebView dCWebView2 = this.mWebViewImpl;
        if (dCWebView2 != null) {
            dCWebView2.loadUrl(str);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public String obtainFrameId() {
        return this.mFrameId;
    }

    public IFrameView obtainFrameView() {
        return this.mFrameView;
    }

    public String obtainFullUrl() {
        return (Build.VERSION.SDK_INT < 14 || TextUtils.isEmpty(this.mWebViewImpl.getUrlStr())) ? this.mWebViewImpl.getUrl() : this.mWebViewImpl.getUrlStr();
    }

    public String obtainPageTitle() {
        try {
            if (Looper.myLooper() != null && Looper.getMainLooper() == Looper.myLooper()) {
                String title = this.mWebViewImpl.getTitle();
                return TextUtils.isEmpty(title) ? this.mWebViewImpl.getPageTitle() : title;
            }
        } catch (Exception e2) {
            Logger.e("AdaWebview", "e.getMessage()==" + e2.getMessage());
        }
        return this.mWebViewImpl.getPageTitle();
    }

    public String obtainUrl() {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView == null) {
            return "";
        }
        if (dCWebView.getUrlStr() == null) {
            return this.mWebViewImpl.getUrl();
        }
        int iIndexOf = this.mWebViewImpl.getUrlStr().indexOf(this.mWebViewImpl.getBaseUrl());
        String urlStr = this.mWebViewImpl.getUrlStr();
        return iIndexOf >= 0 ? urlStr.substring(this.mWebViewImpl.getBaseUrl().length()) : urlStr;
    }

    public WebView obtainWebview() {
        if (this.mWebViewImpl.getWebView() instanceof WebView) {
            return (WebView) this.mWebViewImpl.getWebView();
        }
        return null;
    }

    public ViewGroup obtainWindowView() {
        return this.mWebViewImpl.getWebView();
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public boolean onDispose() {
        View view = this.mProgressView;
        if (view != null && view.getParent() != null) {
            ((ViewGroup) this.mProgressView.getParent()).removeView(this.mProgressView);
        }
        return super.onDispose();
    }

    public void onPageStarted() {
        this.mLoading = true;
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.onPageStarted();
        }
        try {
            if (this.mFrameView.getFrameType() == 5 && TextUtils.equals(this.mFrameView.obtainApp().obtainWebAppIntent().getStringExtra(IntentConst.DIRECT_PAGE), obtainUrl())) {
                obtainWindowView().postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaWebview.6
                    @Override // java.lang.Runnable
                    public void run() {
                        AdaWebview adaWebview = AdaWebview.this;
                        if (adaWebview.mLoaded || adaWebview.mFrameView.obtainApp() == null) {
                            return;
                        }
                        AdaWebview.this.mFrameView.obtainApp().checkOrLoadlaunchWebview();
                    }
                }, 6000L);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void onRootViewGlobalLayout(View view) {
        try {
            IApp iAppObtainApp = obtainApp();
            if (iAppObtainApp != null) {
                iAppObtainApp.obtainWebAppRootView().onRootViewGlobalLayout(view);
            }
        } catch (Exception unused) {
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void pause() {
        if (this.mWebViewImpl == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        this.isPause = true;
        obtainMainView().post(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaWebview.1
            @Override // java.lang.Runnable
            public void run() {
                AdaWebview.this.mWebViewImpl.onPause();
            }
        });
        AdaFrameView adaFrameView = this.mFrameView;
        if (adaFrameView != null) {
            adaFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_WEBVIEW_PAUSE, this);
        }
    }

    public void reload() {
        if (PdrUtil.isEmpty(this.mWebViewImpl.getUrlStr())) {
            return;
        }
        removeAllFrameItem();
        try {
            if (BaseInfo.ISDEBUG) {
                this.mWebViewImpl.clearCache(false);
            }
        } catch (Exception unused) {
        }
        this.mWebViewImpl.setDidTouch(false);
        this.mWebViewImpl.reload();
        StringBuilder sb = new StringBuilder();
        sb.append("reload url=");
        DCWebView dCWebView = this.mWebViewImpl;
        sb.append(dCWebView.convertRelPath(dCWebView.getUrlStr()));
        Logger.d("webview", sb.toString());
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void removeAllCookie() {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.removeAllCookie();
            return;
        }
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (cookieHandler instanceof WebkitCookieManagerProxy) {
            ((WebkitCookieManagerProxy) cookieHandler).removeAllCookie();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void removeSessionCookie() {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.removeSessionCookie();
            return;
        }
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (cookieHandler instanceof WebkitCookieManagerProxy) {
            ((WebkitCookieManagerProxy) cookieHandler).removeSessionCookie();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void removeStateListener(IWebviewStateListener iWebviewStateListener) {
        ArrayList<IWebviewStateListener> arrayList = this.mStateListeners;
        if (arrayList != null) {
            arrayList.remove(iWebviewStateListener);
        }
    }

    public void resetPlusLoadSaveData() {
        this.mPlusLoaded = false;
        this.mPlusLoading = false;
        this.mPreloadJsLoaded = false;
        this.mPreloadJsLoading = false;
        this.mInjectPlusWidthJs = null;
        this.mLoaded = false;
        this.mIsAdvanceCss = false;
        this.mInjectGeoLoaded = false;
        this.mForceAHeadJsFileLoaded = false;
        this.mInjectPlusLoadedUrl = null;
        initSitemapState();
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void resume() {
        if (this.mWebViewImpl == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        this.isPause = false;
        obtainMainView().post(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaWebview.2
            @Override // java.lang.Runnable
            public void run() {
                AdaWebview.this.mWebViewImpl.onResume();
            }
        });
        AdaFrameView adaFrameView = this.mFrameView;
        if (adaFrameView != null) {
            adaFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_WEBVIEW_RESUME, this);
        }
    }

    public void saveWebViewData(String str) {
        DCWebView dCWebView;
        if (!this.mPlusLoading || (dCWebView = this.mWebViewImpl) == null) {
            return;
        }
        if (TextUtils.isEmpty(dCWebView.getUrlStr())) {
            this.mWebViewImpl.setUrlStr(str);
        } else if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, "about:blank")) {
            this.mWebViewImpl.setUrlStr(str);
        }
        Logger.i(AdaFrameItem.TAG, "saveWebViewData url=" + str);
        this.mPlusLoaded = true;
        this.mInjectPlusLoadedUrl = this.mWebViewImpl.getUrlStr();
        this.mPreloadJsLoaded = this.mPreloadJsLoading;
        this.mWebViewImpl.onUpdatePlusData("saveWebViewData");
        this.mWebViewImpl.listenPageFinishTimeout("saveWebViewData");
        if (this.mFrameView.getCircleRefreshView() == null || !this.mFrameView.getCircleRefreshView().b()) {
            return;
        }
        this.mFrameView.getCircleRefreshView().a();
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setAssistantType(String str) {
        int i;
        Logger.e("AssistantInput", "setAssistantType type=" + str);
        int iConvertInt = RecordView.Utils.convertInt(str);
        CustomeizedInputConnection.sDefaultInputType = iConvertInt;
        CustomeizedInputConnection customeizedInputConnection = sCustomeizedInputConnection;
        boolean z = (customeizedInputConnection == null || (i = customeizedInputConnection.mInputType) == iConvertInt || i == 1 || i == 2) ? false : true;
        if (mRecordView != null && z && AndroidResources.sIMEAlive) {
            RecordView recordView = mRecordView;
            recordView.update(recordView.mAnchorY, iConvertInt);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setCookie(String str, String str2) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.setCookie(str, str2);
            return;
        }
        try {
            CookieHandler cookieHandler = CookieHandler.getDefault();
            if (cookieHandler != null) {
                HashMap map = new HashMap();
                ArrayList arrayList = new ArrayList();
                arrayList.add(str2);
                map.put("Set-Cookie", arrayList);
                cookieHandler.put(URI.create(str), map);
            }
        } catch (Exception unused) {
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setCssFile(String str, String str2) {
        if (PdrUtil.isEmpty(str)) {
            this.mCssString = str2;
        } else {
            this.mCssString = loadFileContent(str, this.mFrameView.obtainApp().obtainRunningAppMode() == 1 ? 0 : 2);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setFixBottom(int i) {
        this.mFixBottomHeight = i;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setFlag(Object obj) {
        this.mFlag = obj;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setFrameId(String str) {
        this.mFrameId = str;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setIWebViewFocusable(boolean z) {
        ViewGroup viewGroupObtainWindowView = obtainWindowView();
        if (viewGroupObtainWindowView != null) {
            boolean zIsFocusable = viewGroupObtainWindowView.isFocusable();
            if (z && !zIsFocusable) {
                viewGroupObtainWindowView.setFocusable(true);
                viewGroupObtainWindowView.setFocusableInTouchMode(true);
            } else {
                if (z || !zIsFocusable) {
                    return;
                }
                viewGroupObtainWindowView.setFocusable(false);
                viewGroupObtainWindowView.setFocusableInTouchMode(false);
            }
        }
    }

    public void setJsInterface(IJsInterface iJsInterface) {
        if (this.mJsInterfaces == null) {
            this.mJsInterfaces = iJsInterface;
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setListenResourceLoading(JSONObject jSONObject) {
        this.mListenResourceLoadingOptions = jSONObject;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setLoadURLHeads(String str, HashMap<String, String> map) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.putHeads(str, map);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setOpener(IWebview iWebview) {
        this.mOpener = iWebview;
    }

    public void setOriginalUrl(String str) {
        this.originalUrl = str;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setOverrideResourceRequest(JSONArray jSONArray) {
        this.mOverrideResourceRequestOptions = jSONArray;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setOverrideUrlLoadingData(JSONObject jSONObject) {
        this.mOverrideUrlLoadingDataOptions = jSONObject;
        Logger.d(AdaFrameItem.TAG, "setOverrideUrlLoadingData=" + jSONObject);
        if (this.mFrameView.getFrameType() == 2 || this.mFrameView.getFrameType() == 5) {
            this.mFrameView.obtainApp().setConfigProperty("wap2app_running_mode", AbsoluteConst.FALSE);
            DCWebView dCWebView = this.mWebViewImpl;
            if (dCWebView != null) {
                dCWebView.closeWap2AppBlockDialog(true);
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setPreloadJsFile(String str, boolean z) {
        if (z) {
            this.mForceAHeadJsFileLoaded = false;
            this.mForceAHeadJsFile = str;
            if (this.mPlusLoaded) {
                loadForceAHeadJs();
                return;
            }
            return;
        }
        this.mPreloadJsFile.clear();
        this.mPreloadJsFile.add(str);
        Logger.d("AdaWebview", "setPreloadJsFile mPreloadJsFile=" + this.mPreloadJsFile);
        if (this.mPlusLoaded) {
            Log.d("AdaWebview", "setPreloadJsFile---=" + str);
            loadFileContent(null);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setProgressView(View view) {
        this.mProgressView = view;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setScrollIndicator(String str) {
        if (this.mWebViewImpl != null) {
            if (PdrUtil.isEquals(str, "none")) {
                this.mWebViewImpl.setHorizontalScrollBarEnabled(false);
                this.mWebViewImpl.setVerticalScrollBarEnabled(false);
            } else if (PdrUtil.isEquals(str, "vertical")) {
                this.mWebViewImpl.setHorizontalScrollBarEnabled(false);
                this.mWebViewImpl.setVerticalScrollBarEnabled(true);
            } else if (PdrUtil.isEquals(str, Constants.Value.HORIZONTAL)) {
                this.mWebViewImpl.setHorizontalScrollBarEnabled(true);
                this.mWebViewImpl.setVerticalScrollBarEnabled(false);
            } else {
                this.mWebViewImpl.setHorizontalScrollBarEnabled(true);
                this.mWebViewImpl.setVerticalScrollBarEnabled(true);
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setWebViewCacheMode(String str) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.setWebViewCacheMode(str);
        }
    }

    public void setWebViewEvent(String str, Object obj) {
        if (this.mWebViewParent == null) {
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.PULL_DOWN_REFRESH)) {
            this.mWebViewParent.parsePullToReFresh((JSONObject) obj);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.PULL_REFRESH_BEGIN)) {
            this.mWebViewParent.beginPullRefresh();
        } else if (PdrUtil.isEquals(str, AbsoluteConst.BOUNCE_REGISTER)) {
            this.mWebViewParent.parseBounce((JSONObject) obj);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setWebviewProperty(String str, String str2) {
        if ("setShareOptions".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                this.shareOptions = "";
                return;
            }
            this.shareOptions = str2;
            try {
                JSONObject jSONObject = new JSONObject(this.shareOptions);
                if (jSONObject.has("icon")) {
                    String string = jSONObject.getString("icon");
                    if (TextUtils.isEmpty(string)) {
                        return;
                    }
                    cx0.f().a(string);
                    return;
                }
                return;
            } catch (Exception unused) {
                return;
            }
        }
        if ("setFavoriteOptions".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                this.favoriteOptions = "";
                return;
            } else {
                this.favoriteOptions = str2;
                return;
            }
        }
        if ("needTouchEvent".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                this.needTouchEvent = "";
                return;
            } else {
                this.needTouchEvent = str2;
                return;
            }
        }
        if (AbsoluteConst.JSON_KEY_SCALABLE.equals(str)) {
            if (this.mWebViewImpl != null) {
                this.mWebViewImpl.initScalable(PdrUtil.parseBoolean(str2, this.mFrameView.obtainFrameOptions().scalable, false));
                return;
            }
            return;
        }
        if ("User-Agent".equals(str)) {
            if (this.mWebViewImpl != null) {
                if (Boolean.parseBoolean(this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_H5PLUS)) && str2.indexOf(" Html5Plus/") < 0) {
                    str2 = str2 + DCWebView.UserAgentExtInfo;
                }
                sCustomUserAgent = str2;
                this.mWebViewImpl.getWebView().post(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaWebview.7
                    @Override // java.lang.Runnable
                    public void run() {
                        AdaWebview.this.mWebViewImpl.setUserAgentString(AdaWebview.sCustomUserAgent);
                    }
                });
                return;
            }
            return;
        }
        if (AbsoluteConst.JSON_KEY_BLOCK_NETWORK_IMAGE.equals(str)) {
            if (this.mWebViewImpl != null) {
                this.mWebViewImpl.setBlockNetworkImage(PdrUtil.parseBoolean(str2, false, false));
                return;
            }
            return;
        }
        if ("injection".equals(str)) {
            this.mNeedInjection = PdrUtil.parseBoolean(str2, true, false);
            return;
        }
        if ("bounce".equals(str)) {
            if (this.mWebViewImpl == null || DeviceInfo.sDeviceSdkVer < 9) {
                return;
            }
            JSONObject jSONObject2 = this.mFrameView.obtainFrameOptions().titleNView;
            if (("vertical".equalsIgnoreCase(str2) || Constants.Value.HORIZONTAL.equalsIgnoreCase(str2) || ProviderConfigurationPermission.ALL_STR.equalsIgnoreCase(str2)) && (jSONObject2 == null || !"transparent".equals(jSONObject2.optString("type")))) {
                this.mWebViewImpl.getWebView().setOverScrollMode(0);
                return;
            } else {
                this.mWebViewImpl.getWebView().setOverScrollMode(2);
                return;
            }
        }
        if (AbsoluteConst.JSON_KEY_VIDEO_FULL_SCREEN.equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.mVideoFullscreen = str2;
        } else if ("plusrequire".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.mPlusrequire = str2;
        } else if ("geolocation".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.mInjectGEO = str2;
        } else {
            if (!AbsoluteConst.JSON_KEY_SHAREABLE.equals(str) || TextUtils.isEmpty(str2)) {
                return;
            }
            this.mShareable = PdrUtil.parseBoolean(str2, true, false);
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void setWebviewclientListener(IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        this.mWebViewImpl.setDcloudwebviewclientListener(iDCloudWebviewClientListener);
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void stopLoading() {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.stopLoading();
        }
    }

    public String syncUpdateWebViewData(String str) {
        if (Build.VERSION.SDK_INT <= 19) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        String webviewUUID = getWebviewUUID();
        if (PdrUtil.isEmpty(webviewUUID)) {
            webviewUUID = String.valueOf(this.mFrameView.hashCode());
        }
        stringBuffer.append("window.__HtMl_Id__= '" + webviewUUID + "';");
        if (PdrUtil.isEmpty(obtainFrameId())) {
            stringBuffer.append("window.__WebVieW_Id__= undefined;");
        } else {
            stringBuffer.append("window.__WebVieW_Id__= '" + obtainFrameId() + "';");
        }
        Logger.e("WebViewData", "syncUpdateWebViewData url=" + this.mRecordLastUrl);
        stringBuffer.append("try{window.plus.__tag__='" + this.mPlusInjectTag + "';location.__plusready__=true;/*console.log(location);window.plus.__url__='" + str + "';*/}catch(e){console.log(e)}");
        return AbsoluteConst.PROTOCOL_JAVASCRIPT + stringBuffer.toString();
    }

    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<UUID=");
            sb.append(this.mWebviewUUID);
            sb.append(">;");
            sb.append(obtainMainView() != null ? obtainMainView().toString() : "view = null");
            return sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return super.toString();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public boolean unReceiveTitle() {
        return this.unReceiveTitle;
    }

    public String wrapAppendJsFile(String str, int i) {
        if (!BaseInfo.isWap2AppAppid(this.mAppid) || str.endsWith("wap2app.js") || (!BaseInfo.SyncDebug && !BaseInfo.isBase(getContext()))) {
            return loadFileContent(str, i);
        }
        if (str.startsWith("file://")) {
            str = str.substring(7);
        }
        if (PdrUtil.isNetPath(this.mWebViewImpl.getUrlStr())) {
            str = "/h5pscript://" + str;
        } else if (!str.startsWith("h5pscript://")) {
            str = "h5pscript://" + str;
        }
        return "javascript:(function(){var container = document.getElementsByTagName('head')[0];\nvar script = document.createElement('script');\nscript.type = 'text/javascript';\nscript.src = '" + str + "';\ncontainer.appendChild(script);\nfirstNode = container.children[0];\nif(firstNode == null || firstNode==undefined)\n{    container.appendChild(script);}\nelse{\n\tcontainer.insertBefore(script,container.children[0]);\n}})();";
    }

    public void addJsInterface(String str, Object obj) {
        this.mWebViewImpl.addJavascriptInterface(obj, str);
    }

    public void addJsInterface(String str, IJsInterface iJsInterface) {
        if (Build.VERSION.SDK_INT > 17) {
            this.mWebViewImpl.addJavascriptInterface(iJsInterface, str);
        }
        setJsInterface(iJsInterface);
    }

    public void evalJS(String str, ReceiveJSValue.ReceiveJSValueCallback receiveJSValueCallback) {
        if (receiveJSValueCallback != null) {
            str = ReceiveJSValue.registerCallback(str, receiveJSValueCallback);
        }
        evalJS(str);
    }

    public void reload(String str) {
        Logger.d("webview", "reload loadUrl url=" + str);
        this.mLoaded = false;
        this.mWebViewImpl.setUrlStr(str);
        this.mWebViewImpl.loadUrl(str);
    }

    private String loadFileContent(String str, int i) {
        byte[] fileContent;
        StringBuffer stringBuffer = new StringBuffer();
        InputStream encryptionInputStream = WebResUtil.getEncryptionInputStream(str, this.mFrameView.obtainApp());
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            IOUtil.close(encryptionInputStream);
        }
        if (encryptionInputStream != null) {
            stringBuffer.append(IOUtil.toString(encryptionInputStream));
        } else {
            str = this.mFrameView.obtainApp().convert2AbsFullPath(obtainFullUrl(), str);
            try {
                if (DHFile.isExist(str) && (fileContent = PlatformUtil.getFileContent(str, i)) != null) {
                    stringBuffer.append(new String(fileContent));
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        if (this.mNeedSitemapJson && str.endsWith("__wap2app.js")) {
            File file = new File(BaseInfo.sBaseFsSitMapPath + File.separator + obtainApp().obtainAppId() + "_sitemap.json");
            if (file.exists()) {
                try {
                    stringBuffer.insert(0, IOUtil.toString(new FileInputStream(file)) + ";\n");
                    stringBuffer.insert(0, ";window.__wap2app_sitemap=");
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                this.mNeedSitemapJson = false;
            }
        }
        return stringBuffer.toString();
    }

    public void reload(boolean z) {
        DCWebView dCWebView = this.mWebViewImpl;
        if (dCWebView != null) {
            dCWebView.webReload(z);
        }
        reload();
    }

    public AdaWebview(Context context, AdaFrameView adaFrameView) {
        super(context);
        this.unReceiveTitle = true;
        this.mWebViewParent = null;
        this.mRecordLastUrl = null;
        this.mWebViewImpl = null;
        this.mFrameView = null;
        this.mLoaded = false;
        this.mLoadCompleted = false;
        this.mPreloadJsLoaded = false;
        this.mPreloadJsLoading = false;
        this.mPlusLoaded = false;
        this.mPlusLoading = false;
        this.mPlusInjectTag = "page_finished";
        this.mIsAdvanceCss = false;
        this.mNeedInjection = true;
        this.mEncoding = null;
        this.mWebviewUUID = null;
        this.mWebviewANID = null;
        this.mFrameId = null;
        this.mJsInterfaces = null;
        this.hasErrorPage = false;
        this.errorPageUrl = null;
        this.originalUrl = null;
        this.mVideoFullscreen = "auto";
        this.needTouchEvent = "";
        this.favoriteOptions = "";
        this.shareOptions = "";
        this.mShareable = true;
        this.mPlusrequire = "normal";
        this.mInjectGEO = "none";
        this.mInjectGeoLoaded = false;
        this.mProgressIntValue = 0;
        this.isDisposed = false;
        this.mReceiveJSValue_android42 = null;
        this.isPause = false;
        this.mFlag = null;
        this.mInjectPlusLoadedUrl = null;
        this.mEvalJsOptionStack = null;
        this.mForceAHeadJsFileLoaded = false;
        this.mForceAHeadJsFile = null;
        this.mPreloadJsFile = new ArrayList<>(2);
        this.mCssString = "";
        this.executeScriptListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.4
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                String str = (String) obj;
                DCWebView dCWebView = AdaWebview.this.mWebViewImpl;
                if (dCWebView != null) {
                    if (!str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
                        str = AbsoluteConst.PROTOCOL_JAVASCRIPT + str;
                    }
                    dCWebView.loadUrl(str);
                }
            }
        };
        this.mMesssageListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.5
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                Object[] objArr = (Object[]) obj;
                AdaWebview.this.mJsInterfaces.exec(String.valueOf(objArr[0]), String.valueOf(objArr[1]), (JSONArray) objArr[2]);
            }
        };
        this.mStateListeners = null;
        this.mProgress = 0;
        this.isStart = false;
        this.justClearOption = false;
        this.mLoading = false;
        this.mNeedSitemapJson = false;
        this.mOverrideResourceRequestOptions = null;
        this.mOverrideUrlLoadingDataOptions = null;
        this.mListenResourceLoadingOptions = null;
        initANID();
        this.mFrameView = adaFrameView;
        this.mAppid = adaFrameView.obtainApp().obtainAppId();
        initSitemapState();
        Logger.d("AdaWebview");
        try {
            this.mWebViewImpl = WebViewFactory.getWebView(getActivity(), this);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mWebViewImpl = WebViewFactory.getWebView(getActivity(), this);
        }
        setMainView(this.mWebViewImpl.getWebView());
        AdaWebViewParent adaWebViewParent = new AdaWebViewParent(context);
        this.mWebViewParent = adaWebViewParent;
        adaWebViewParent.fillsWithWebview(this);
        if (adaFrameView.getFrameType() == 2) {
            this.mOverrideUrlLoadingDataOptions = this.mFrameView.obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.OverrideUrlJsonData);
            JSONObject jSONObjectObtainThridInfo = this.mFrameView.obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.OverrideResourceJsonData);
            if (jSONObjectObtainThridInfo != null) {
                this.mOverrideResourceRequestOptions = jSONObjectObtainThridInfo.optJSONArray("0");
            }
            this.mNeedInjection = PdrUtil.parseBoolean(this.mFrameView.obtainApp().obtainConfigProperty("injection"), this.mNeedInjection, false);
            String strObtainConfigProperty = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_LPLUSERQUIRE);
            if (!TextUtils.isEmpty(strObtainConfigProperty)) {
                this.mPlusrequire = strObtainConfigProperty;
            }
            String strObtainConfigProperty2 = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_LGEOLOCATION);
            if (TextUtils.isEmpty(strObtainConfigProperty2)) {
                return;
            }
            this.mInjectGEO = strObtainConfigProperty2;
            return;
        }
        if (adaFrameView.getFrameType() == 4) {
            String strObtainConfigProperty3 = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_SPLUSERQUIRE);
            if (!TextUtils.isEmpty(strObtainConfigProperty3)) {
                this.mPlusrequire = strObtainConfigProperty3;
            }
            String strObtainConfigProperty4 = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_SGEOLOCATION);
            if (TextUtils.isEmpty(strObtainConfigProperty4)) {
                return;
            }
            this.mInjectGEO = strObtainConfigProperty4;
        }
    }

    public AdaWebview(Context context, AdaFrameView adaFrameView, IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        super(context);
        this.unReceiveTitle = true;
        this.mWebViewParent = null;
        this.mRecordLastUrl = null;
        this.mWebViewImpl = null;
        this.mFrameView = null;
        this.mLoaded = false;
        this.mLoadCompleted = false;
        this.mPreloadJsLoaded = false;
        this.mPreloadJsLoading = false;
        this.mPlusLoaded = false;
        this.mPlusLoading = false;
        this.mPlusInjectTag = "page_finished";
        this.mIsAdvanceCss = false;
        this.mNeedInjection = true;
        this.mEncoding = null;
        this.mWebviewUUID = null;
        this.mWebviewANID = null;
        this.mFrameId = null;
        this.mJsInterfaces = null;
        this.hasErrorPage = false;
        this.errorPageUrl = null;
        this.originalUrl = null;
        this.mVideoFullscreen = "auto";
        this.needTouchEvent = "";
        this.favoriteOptions = "";
        this.shareOptions = "";
        this.mShareable = true;
        this.mPlusrequire = "normal";
        this.mInjectGEO = "none";
        this.mInjectGeoLoaded = false;
        this.mProgressIntValue = 0;
        this.isDisposed = false;
        this.mReceiveJSValue_android42 = null;
        this.isPause = false;
        this.mFlag = null;
        this.mInjectPlusLoadedUrl = null;
        this.mEvalJsOptionStack = null;
        this.mForceAHeadJsFileLoaded = false;
        this.mForceAHeadJsFile = null;
        this.mPreloadJsFile = new ArrayList<>(2);
        this.mCssString = "";
        this.executeScriptListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.4
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                String str = (String) obj;
                DCWebView dCWebView = AdaWebview.this.mWebViewImpl;
                if (dCWebView != null) {
                    if (!str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
                        str = AbsoluteConst.PROTOCOL_JAVASCRIPT + str;
                    }
                    dCWebView.loadUrl(str);
                }
            }
        };
        this.mMesssageListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.5
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                Object[] objArr = (Object[]) obj;
                AdaWebview.this.mJsInterfaces.exec(String.valueOf(objArr[0]), String.valueOf(objArr[1]), (JSONArray) objArr[2]);
            }
        };
        this.mStateListeners = null;
        this.mProgress = 0;
        this.isStart = false;
        this.justClearOption = false;
        this.mLoading = false;
        this.mNeedSitemapJson = false;
        this.mOverrideResourceRequestOptions = null;
        this.mOverrideUrlLoadingDataOptions = null;
        this.mListenResourceLoadingOptions = null;
        initANID();
        this.mFrameView = adaFrameView;
        this.mAppid = adaFrameView.obtainApp().obtainAppId();
        initSitemapState();
        Logger.d("AdaWebview");
        try {
            this.mWebViewImpl = WebViewFactory.getWebView(getActivity(), this, iDCloudWebviewClientListener);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mWebViewImpl = WebViewFactory.getWebView(getActivity(), this, iDCloudWebviewClientListener);
        }
        setMainView(this.mWebViewImpl.getWebView());
        AdaWebViewParent adaWebViewParent = new AdaWebViewParent(context);
        this.mWebViewParent = adaWebViewParent;
        adaWebViewParent.fillsWithWebview(this);
        if (adaFrameView.getFrameType() == 2) {
            this.mOverrideUrlLoadingDataOptions = this.mFrameView.obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.OverrideUrlJsonData);
            JSONObject jSONObjectObtainThridInfo = this.mFrameView.obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.OverrideResourceJsonData);
            if (jSONObjectObtainThridInfo != null) {
                this.mOverrideResourceRequestOptions = jSONObjectObtainThridInfo.optJSONArray("0");
            }
            this.mNeedInjection = PdrUtil.parseBoolean(this.mFrameView.obtainApp().obtainConfigProperty("injection"), this.mNeedInjection, false);
            String strObtainConfigProperty = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_LPLUSERQUIRE);
            if (!TextUtils.isEmpty(strObtainConfigProperty)) {
                this.mPlusrequire = strObtainConfigProperty;
            }
            String strObtainConfigProperty2 = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_LGEOLOCATION);
            if (TextUtils.isEmpty(strObtainConfigProperty2)) {
                return;
            }
            this.mInjectGEO = strObtainConfigProperty2;
            return;
        }
        if (adaFrameView.getFrameType() == 4) {
            String strObtainConfigProperty3 = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_SPLUSERQUIRE);
            if (!TextUtils.isEmpty(strObtainConfigProperty3)) {
                this.mPlusrequire = strObtainConfigProperty3;
            }
            String strObtainConfigProperty4 = this.mFrameView.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_SGEOLOCATION);
            if (TextUtils.isEmpty(strObtainConfigProperty4)) {
                return;
            }
            this.mInjectGEO = strObtainConfigProperty4;
        }
    }

    public AdaWebview(Context context, AdaFrameView adaFrameView, OnPageFinishedCallack onPageFinishedCallack) {
        super(context);
        this.unReceiveTitle = true;
        this.mWebViewParent = null;
        this.mRecordLastUrl = null;
        this.mWebViewImpl = null;
        this.mFrameView = null;
        this.mLoaded = false;
        this.mLoadCompleted = false;
        this.mPreloadJsLoaded = false;
        this.mPreloadJsLoading = false;
        this.mPlusLoaded = false;
        this.mPlusLoading = false;
        this.mPlusInjectTag = "page_finished";
        this.mIsAdvanceCss = false;
        this.mNeedInjection = true;
        this.mEncoding = null;
        this.mWebviewUUID = null;
        this.mWebviewANID = null;
        this.mFrameId = null;
        this.mJsInterfaces = null;
        this.hasErrorPage = false;
        this.errorPageUrl = null;
        this.originalUrl = null;
        this.mVideoFullscreen = "auto";
        this.needTouchEvent = "";
        this.favoriteOptions = "";
        this.shareOptions = "";
        this.mShareable = true;
        this.mPlusrequire = "normal";
        this.mInjectGEO = "none";
        this.mInjectGeoLoaded = false;
        this.mProgressIntValue = 0;
        this.isDisposed = false;
        this.mReceiveJSValue_android42 = null;
        this.isPause = false;
        this.mFlag = null;
        this.mInjectPlusLoadedUrl = null;
        this.mEvalJsOptionStack = null;
        this.mForceAHeadJsFileLoaded = false;
        this.mForceAHeadJsFile = null;
        this.mPreloadJsFile = new ArrayList<>(2);
        this.mCssString = "";
        this.executeScriptListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.4
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                String str = (String) obj;
                DCWebView dCWebView = AdaWebview.this.mWebViewImpl;
                if (dCWebView != null) {
                    if (!str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
                        str = AbsoluteConst.PROTOCOL_JAVASCRIPT + str;
                    }
                    dCWebView.loadUrl(str);
                }
            }
        };
        this.mMesssageListener = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaWebview.5
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                Object[] objArr = (Object[]) obj;
                AdaWebview.this.mJsInterfaces.exec(String.valueOf(objArr[0]), String.valueOf(objArr[1]), (JSONArray) objArr[2]);
            }
        };
        this.mStateListeners = null;
        this.mProgress = 0;
        this.isStart = false;
        this.justClearOption = false;
        this.mLoading = false;
        this.mNeedSitemapJson = false;
        this.mOverrideResourceRequestOptions = null;
        this.mOverrideUrlLoadingDataOptions = null;
        this.mListenResourceLoadingOptions = null;
        initANID();
        this.mFrameView = adaFrameView;
        Logger.d("AdaWebview");
        try {
            this.mWebViewImpl = WebViewFactory.getWebView(getActivity(), this, onPageFinishedCallack);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mWebViewImpl = WebViewFactory.getWebView(getActivity(), this, onPageFinishedCallack);
        }
        setMainView(this.mWebViewImpl.getWebView());
        AdaWebViewParent adaWebViewParent = new AdaWebViewParent(context);
        this.mWebViewParent = adaWebViewParent;
        adaWebViewParent.fillsWithWebview(this);
    }
}
