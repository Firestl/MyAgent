package io.dcloud.common.adapter.ui.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.MimeTypeMap;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IDCloudWebviewClientListener;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.ITitleNView;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import io.dcloud.common.adapter.ui.WaitingView;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.InvokeExecutorHelper;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DataInterface;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.DLGeolocation;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.ImageLoaderUtil;
import io.dcloud.common.util.Md5Utils;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.NetworkTypeUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TelephonyUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.feature.nativeObj.photoview.subscaleview.ImageSource;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.cx0;

/* JADX INFO: loaded from: classes2.dex */
public final class WebLoadEvent extends WebViewClient {
    public static final String DIFFERENT_VERSION_JS = "window.plus && (plus.android.import=plus.android.importClass);";
    public static final String ENABLE = "enable";
    public static final String ERROR_TEMPLATE = "javascript:(function(){var b=document.createEvent('HTMLEvents');var a='%s';b.url='%s';b.href='%s';b.initEvent(a,false,true);console.error(a);document.dispatchEvent(b);})();";
    public static final String IF_LOAD_TEMPLATE = "(function(){/*console.log('eval js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(location.__plusready__||window.__html5plus__){return 1}return 0})();";
    public static final String IF_PLUSREADY_EVENT_TEMPLATE = "(function(){/*console.log('plusready event loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(location.__plusready__||window.__html5plus__){if(!location.__plusready__event__){location.__plusready__event__=true;return 1}else{return 2}}return 0})();";
    public static final String IF_PLUSREADY_TEMPLATE = "(function(){/*console.log('all.js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(!location.__plusready__){location.__plusready__=true;return 1}else{return 2}return 0})();";
    public static final String IF_PRELOAD_TEMPLATE = "(function(){/*console.log( 'preload js loading href=' + location.href);*/if(location.__page__load__over__){return 2}var jsfile='%s';if(location.__plusready__||window.__html5plus__){location.__preload__=location.__preload__||[];if(location.__preload__.indexOf(jsfile)<0){location.__preload__.push(jsfile);return 1}else{return 2}}return 0})();";
    public static final int LOADABLE = 1;
    public static final int LOADED = 2;
    public static final int NOLOAD = 0;
    public static String PAGE_FINISHED_FLAG = "javascript:setTimeout(function(){location.__page__load__over__ = true;},2000);";
    public static final String PLUSREADY = "html5plus://ready";
    public static final String TAG = "WebLoadEvent";
    public static final int Timeout_Page_Finish = 6000;
    public static final int Timeout_Plus_Inject = 3000;
    public static final String UNIAPP_READY = "uniapp://ready";
    public boolean isInitAmapGEO;
    public AdaWebview mAdaWebview;
    public String mAppid;
    public boolean mClearCache;
    public long mShowLoadingTime;
    public boolean printLog = true;
    public OnPageFinishedCallack mPageFinishedCallack = null;
    public IDCloudWebviewClientListener mdcloudwebviewclientlister = null;
    public String mLastPageUrl = "";
    public WaitingView mWap2AppBlockDialog = null;
    public ISysEventListener mWap2AppBlockDialogSysEventListener = null;
    public String mPlusJS = null;
    public String TYPE_JS = "type_js";
    public String TYPE_CSS = "type_css";
    public Runnable mTitleNViewProgressStop = null;
    public boolean needResponseRedirect = true;
    public ProgressBar mWaitingForWapPage = null;
    public Runnable Timeout_Plus_Inject_Runnable = null;
    public Runnable Timeout_Page_Finish_Runnable = null;

    public class CatchFile {
        public File mFile = null;
        public String mEncoding = null;
        public String mContentType = null;
        public boolean mExist = false;

        public CatchFile() {
        }
    }

    public static class TitleNViewProgressStop implements Runnable {
        public WeakReference<AdaWebview> mAdaWebview;

        public TitleNViewProgressStop(AdaWebview adaWebview) {
            this.mAdaWebview = new WeakReference<>(adaWebview);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<AdaWebview> weakReference = this.mAdaWebview;
            if (weakReference == null || weakReference.get() == null || this.mAdaWebview.get().obtainFrameView() == null) {
                return;
            }
            Object titleNView = TitleNViewUtil.getTitleNView(this.mAdaWebview.get().obtainFrameView().obtainWindowMgr(), this.mAdaWebview.get().obtainFrameView().obtainWebView(), this.mAdaWebview.get().obtainFrameView(), TitleNViewUtil.getTitleNViewId(this.mAdaWebview.get().obtainFrameView()));
            if (titleNView instanceof ITitleNView) {
                TitleNViewUtil.stopProcess((ITitleNView) titleNView);
            }
        }
    }

    public WebLoadEvent(AdaWebview adaWebview) {
        this.mAdaWebview = null;
        this.mClearCache = false;
        this.mAppid = null;
        this.isInitAmapGEO = false;
        this.mAdaWebview = adaWebview;
        this.mAppid = adaWebview.obtainApp().obtainAppId();
        String strObtainConfigProperty = adaWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_RAM_CACHE_MODE);
        if (BaseInfo.isBase(adaWebview.getContext()) && !ENABLE.equalsIgnoreCase(strObtainConfigProperty)) {
            this.mClearCache = true;
        }
        reset();
        this.isInitAmapGEO = DLGeolocation.checkGeo(adaWebview.getContext());
    }

    private boolean checkCssFile(String str) {
        return !TextUtils.isEmpty(str) && str.contains(".css");
    }

    private boolean checkJsFile(String str) {
        return (TextUtils.isEmpty(str) || !str.contains(".js") || str.contains(".jsp")) ? false : true;
    }

    private WebResourceResponse checkWebResourceResponseRedirect(WebView webView, String str) {
        AdaWebview adaWebview;
        JSONObject jSONObjectObtainThridInfo;
        String strOptString;
        if (!this.needResponseRedirect) {
            return null;
        }
        try {
            if (!URLUtil.isNetworkUrl(str) || !BaseInfo.existsStreamEnv() || (adaWebview = this.mAdaWebview) == null || adaWebview.obtainFrameView().obtainApp() == null || (jSONObjectObtainThridInfo = this.mAdaWebview.obtainFrameView().obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.URDJsonData)) == null) {
                return null;
            }
            JSONArray jSONArrayOptJSONArray = jSONObjectObtainThridInfo.optJSONObject("data").optJSONArray(InvokeExecutorHelper.create("io.dcloud.appstream.rules.util.Tools").invoke("getTopDomainInHost", new URL(str).getHost()));
            if (jSONArrayOptJSONArray == null) {
                return null;
            }
            int i = 0;
            boolean z = false;
            while (true) {
                if (i >= jSONArrayOptJSONArray.length()) {
                    strOptString = null;
                    break;
                }
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("match");
                strOptString = jSONObjectOptJSONObject.optString("redirect");
                int i2 = 0;
                while (true) {
                    if (i2 >= jSONArrayOptJSONArray2.length()) {
                        break;
                    }
                    if (Pattern.compile(jSONArrayOptJSONArray2.optString(i)).matcher(str).matches()) {
                        z = true;
                        break;
                    }
                    i2++;
                }
                if (z) {
                    break;
                }
                i++;
            }
            return downloadResponse(webView, strOptString);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void completeLoadJs(final WebView webView, final String str, final String str2, final String[] strArr, final String str3, final Object... objArr) {
        final SoftReference softReference = new SoftReference(this.mAdaWebview);
        this.mAdaWebview.executeScript(ReceiveJSValue.registerCallback((AdaWebview) softReference.get(), StringUtil.format(str3, objArr), new ReceiveJSValue.ReceiveJSValueCallback() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.9
            @Override // io.dcloud.common.adapter.ui.ReceiveJSValue.ReceiveJSValueCallback
            public String callback(JSONArray jSONArray) {
                try {
                    int i = jSONArray.getInt(1);
                    if (i == 0 && !PdrUtil.isEquals(str2, "onPageFinished")) {
                        WebLoadEvent.this.completeLoadJs(webView, str, str2, strArr, str3, objArr);
                        return null;
                    }
                    if (1 != i) {
                        return null;
                    }
                    for (String str4 : strArr) {
                        if (softReference.get() != null) {
                            ((AdaWebview) softReference.get()).executeScript(str4);
                        }
                    }
                    return null;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        }));
    }

    private boolean directPageIsLaunchPage(IApp iApp) {
        return (TextUtils.isEmpty(iApp.getOriginalDirectPage()) || iApp.obtainWebAppIntent().hasExtra(IntentConst.DIRECT_PAGE)) ? false : true;
    }

    private WebResourceResponse downloadResponse(final WebView webView, final String str) {
        if (!URLUtil.isNetworkUrl(str)) {
            return null;
        }
        try {
            MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.1
                @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                public void execute(Object obj) {
                    webView.stopLoading();
                    WebLoadEvent.this.needResponseRedirect = false;
                    webView.loadUrl(str);
                }
            }, null);
            return new WebResourceResponse(null, null, new ByteArrayInputStream("".getBytes()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x01bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01b1 A[Catch: Exception -> 0x01eb, TRY_LEAVE, TryCatch #1 {Exception -> 0x01eb, blocks: (B:21:0x005f, B:23:0x0065, B:25:0x006b, B:28:0x0071, B:30:0x0077, B:32:0x007e, B:33:0x0085, B:36:0x008f, B:38:0x0099, B:39:0x00a8, B:41:0x00ae, B:43:0x00b8, B:44:0x00d7, B:46:0x00db, B:48:0x00e5, B:50:0x00ef, B:51:0x00fe, B:53:0x0102, B:54:0x010b, B:56:0x0111, B:57:0x0115, B:61:0x011d, B:63:0x0125, B:65:0x012a, B:67:0x0132, B:69:0x0137, B:71:0x014a, B:84:0x01b1, B:60:0x011a, B:73:0x0163, B:75:0x016b, B:78:0x0175, B:79:0x01a0, B:81:0x01a6), top: B:128:0x005f, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.webkit.WebResourceResponse downloadResponseInjection(android.webkit.WebResourceResponse r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 657
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.ui.webview.WebLoadEvent.downloadResponseInjection(android.webkit.WebResourceResponse, java.lang.String, java.lang.String, java.lang.String, java.lang.String):android.webkit.WebResourceResponse");
    }

    private String getCacheLocalFilePath(String str, String str2) {
        IApp iAppObtainApp;
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null || (iAppObtainApp = adaWebview.obtainApp()) == null) {
            return null;
        }
        if (this.TYPE_JS.equals(str2)) {
            return iAppObtainApp.obtainAppTempPath() + "__plus__cache__/" + Md5Utils.md5(str) + ".js";
        }
        return iAppObtainApp.obtainAppTempPath() + "__plus__cache__/" + Md5Utils.md5(str) + ".css";
    }

    public static String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        String mimeTypeFromExtension = fileExtensionFromUrl != null ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl) : null;
        return TextUtils.isEmpty(mimeTypeFromExtension) ? "text/plain" : mimeTypeFromExtension;
    }

    private String getUrlByFilePath(String str) {
        try {
            Activity activity = this.mAdaWebview.obtainApp().getActivity();
            if (activity != null) {
                return DCloudAdapterUtil.getIActivityHandler(activity).getUrlByFilePath(this.mAppid, str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    private CatchFile getUrlFile(String str, String str2) throws Exception {
        String cacheLocalFilePath = getCacheLocalFilePath(str, str2);
        try {
            if (DHFile.isExist(cacheLocalFilePath)) {
                CatchFile catchFile = new CatchFile();
                File file = new File(cacheLocalFilePath);
                catchFile.mFile = file;
                catchFile.mExist = file.exists();
                return catchFile;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection instanceof HttpsURLConnection) {
                try {
                    SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
                    if (sSLSocketFactory != null) {
                        ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLSocketFactory);
                    }
                    ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(DCloudTrustManager.getHostnameVerifier(true));
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            int responseCode = httpURLConnection.getResponseCode();
            String contentType = httpURLConnection.getContentType();
            if (!TextUtils.isEmpty(contentType) && (((str2.equals(this.TYPE_JS) && contentType.contains("javascript")) || (str2.equals(this.TYPE_CSS) && (contentType.contains("text/css") || url.getPath().endsWith(".css")))) && (responseCode == 200 || responseCode == 206))) {
                InputStream inputStream = httpURLConnection.getInputStream();
                boolean zWriteFile = DHFile.writeFile(inputStream, cacheLocalFilePath);
                IOUtil.close(inputStream);
                if (zWriteFile) {
                    CatchFile catchFile2 = new CatchFile();
                    File file2 = new File(cacheLocalFilePath);
                    catchFile2.mFile = file2;
                    catchFile2.mExist = file2.exists();
                    catchFile2.mEncoding = httpURLConnection.getContentEncoding();
                    catchFile2.mContentType = contentType;
                    return catchFile2;
                }
                File file3 = new File(cacheLocalFilePath);
                if (file3.exists()) {
                    file3.delete();
                }
            }
            return null;
        } catch (IOException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private WebResourceResponse handleDecode(String str, WebResourceResponse webResourceResponse) {
        InputStream encryptionInputStream;
        return (TextUtils.isEmpty(str) || (encryptionInputStream = WebResUtil.getEncryptionInputStream(str, this.mAdaWebview.obtainApp())) == null) ? webResourceResponse : new WebResourceResponse(getMimeType(str), "UTF-8", encryptionInputStream);
    }

    private void hideLoading() {
        this.mAdaWebview.obtainMainView().post(new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.12
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                if (WebLoadEvent.this.mAdaWebview == null) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                WebLoadEvent webLoadEvent = WebLoadEvent.this;
                if (jCurrentTimeMillis - webLoadEvent.mShowLoadingTime < 1000) {
                    webLoadEvent.mAdaWebview.getDCWebView().getWebView().postDelayed(this, jCurrentTimeMillis - WebLoadEvent.this.mShowLoadingTime);
                } else {
                    AdaFrameView adaFrameView = webLoadEvent.mAdaWebview.mFrameView;
                    adaFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_HIDE_LOADING, adaFrameView);
                }
            }
        });
    }

    private void listenPlusInjectTimeout(final WebView webView, final String str, final String str2) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null && adaWebview.mPlusrequire.equals("none")) {
            return;
        }
        Runnable runnable = this.Timeout_Plus_Inject_Runnable;
        if (runnable != null) {
            MessageHandler.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.7
            @Override // java.lang.Runnable
            public void run() {
                AdaWebview adaWebview2 = WebLoadEvent.this.mAdaWebview;
                if (adaWebview2 == null || adaWebview2.isRealInject(str)) {
                    return;
                }
                Logger.i("WebViewData", "listenPlusInjectTimeout url=" + str);
                WebLoadEvent.this.onLoadPlusJSContent(webView, str, "plus_inject_timeout_" + str2);
                WebLoadEvent webLoadEvent = WebLoadEvent.this;
                webLoadEvent.mAdaWebview.mPreloadJsLoading = false;
                webLoadEvent.Timeout_Plus_Inject_Runnable = null;
            }
        };
        this.Timeout_Plus_Inject_Runnable = runnable2;
        MessageHandler.postDelayed(runnable2, 3000L);
    }

    private void loadAllJSContent(WebView webView, String str, String str2) {
        if (onLoadPlusJSContent(webView, str, str2)) {
            injectScript(webView, str, str2);
        }
    }

    private void onExecuteEvalJSStatck(WebView webView, String str, String str2) {
        String str3 = this.mAdaWebview.get_eval_js_stack();
        if (PdrUtil.isEmpty(str3)) {
            return;
        }
        completeLoadJs(webView, str, str2, new String[]{str3}, IF_LOAD_TEMPLATE, str);
    }

    private void onLoadCssContent() {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview.mIsAdvanceCss) {
            Logger.i(TAG, "已经提前注入CSS完成。不需要再注入了" + this.mAdaWebview.getOriginalUrl());
            return;
        }
        if (adaWebview.loadCssFile()) {
            Logger.i(TAG, "提前注入CSS完成" + this.mAdaWebview.getOriginalUrl());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onLoadPlusJSContent(final WebView webView, final String str, final String str2) {
        if (this.mAdaWebview.mPlusrequire.equals("none")) {
            return false;
        }
        if (this.mAdaWebview.isRealInject(str)) {
            Logger.i(TAG, "all.js已经注入完成。不需要再注入了" + this.mAdaWebview.getOriginalUrl());
            return true;
        }
        Logger.i(TAG, "onLoadPlusJSContent all.js注入 " + this.mAdaWebview.getOriginalUrl() + ";tag=" + str2 + ";mAdaWebview.mPlusrequire=" + this.mAdaWebview.mPlusrequire);
        if (this.mAdaWebview.mPlusrequire.equals("later") && str2.equals("onPageFinished")) {
            webView.postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.6
                @Override // java.lang.Runnable
                public void run() {
                    WebLoadEvent webLoadEvent = WebLoadEvent.this;
                    AdaWebview adaWebview = webLoadEvent.mAdaWebview;
                    if (adaWebview != null) {
                        String str3 = str2;
                        adaWebview.mPlusInjectTag = str3;
                        adaWebview.mPlusLoading = true;
                        WebView webView2 = webView;
                        String str4 = str;
                        webLoadEvent.completeLoadJs(webView2, str4, str3, new String[]{webLoadEvent.mPlusJS, WebLoadEvent.DIFFERENT_VERSION_JS}, WebLoadEvent.IF_PLUSREADY_TEMPLATE, str4);
                    }
                }
            }, 2000L);
        } else {
            AdaWebview adaWebview = this.mAdaWebview;
            adaWebview.mPlusInjectTag = str2;
            adaWebview.mPlusLoading = true;
            completeLoadJs(webView, str, str2, new String[]{this.mPlusJS, DIFFERENT_VERSION_JS}, IF_PLUSREADY_TEMPLATE, str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlusreadyEvent(WebView webView, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_PLUSREADY));
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(StringUtil.format(AbsoluteConst.EVENTS_IFRAME_DOUCMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_PLUSREADY));
        completeLoadJs(webView, str, str2, new String[]{stringBuffer.toString(), stringBuffer2.toString(), "plus.webview.currentWebview().__needTouchEvent__()"}, IF_PLUSREADY_EVENT_TEMPLATE, str);
    }

    private void printOpenLog(WebView webView, String str) {
        IApp iAppObtainApp;
        String url = webView.getUrl();
        if (!BaseInfo.isBase(webView.getContext()) || TextUtils.isEmpty(str) || TextUtils.isEmpty(url) || (iAppObtainApp = this.mAdaWebview.mFrameView.obtainApp()) == null || str.startsWith(DeviceInfo.HTTP_PROTOCOL) || url.startsWith(DeviceInfo.HTTP_PROTOCOL) || str.startsWith(DeviceInfo.HTTPS_PROTOCOL) || url.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            return;
        }
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.OPENLOG, WebResUtil.getHBuilderPrintUrl(iAppObtainApp.convert2RelPath(WebResUtil.getOriginalUrl(url))), WebResUtil.getHBuilderPrintUrl(iAppObtainApp.convert2RelPath(WebResUtil.getOriginalUrl(str)))));
    }

    private void printResourceLog(WebView webView, IApp iApp, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || webView == null || iApp == null || !BaseInfo.isBase(webView.getContext()) || str.equalsIgnoreCase(str2) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (this.mClearCache && !this.mLastPageUrl.equalsIgnoreCase(str)) {
            webView.clearCache(true);
        }
        this.mLastPageUrl = str;
        String originalUrl = WebResUtil.getOriginalUrl(str);
        if (str2.startsWith(DeviceInfo.HTTP_PROTOCOL) || str2.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            return;
        }
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.RESOURCELOG, WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(originalUrl)), WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(WebResUtil.getOriginalUrl(str2)))));
    }

    private void sendStatistics(String str, Context context, String str2, String str3, IApp iApp) {
        String strEncode;
        try {
            strEncode = URLEncoder.encode(Build.MODEL, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            strEncode = "";
        }
        String statisticsUrl = DataInterface.getStatisticsUrl(str, TelephonyUtil.getIMEI(context), 9, TestUtil.PointTime.getBaseVer(context), strEncode);
        String originalUrl = WebResUtil.getOriginalUrl(str3);
        if (str2.startsWith(DeviceInfo.HTTP_PROTOCOL) || str2.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            return;
        }
        String originalUrl2 = WebResUtil.getOriginalUrl(str2);
        String hBuilderPrintUrl = WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(originalUrl));
        String hBuilderPrintUrl2 = WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(originalUrl2));
        StringBuilder sb = new StringBuilder();
        sb.append(statisticsUrl);
        sb.append("&net=");
        sb.append(NetworkTypeUtil.getNetworkType(context));
        sb.append("&sr=");
        sb.append(PdrUtil.encodeURL(hBuilderPrintUrl2));
        sb.append("&sh=");
        sb.append(PdrUtil.encodeURL(hBuilderPrintUrl));
        sb.append("&pn=");
        String str4 = DeviceInfo.sPackageName;
        if (str4 == null) {
            str4 = AndroidResources.packageName;
        }
        sb.append(str4);
        NetTool.httpGet(sb.toString());
    }

    private boolean shouldRuntimeHandle(String str) {
        return PdrUtil.isDeviceRootDir(str) || PdrUtil.isNetPath(str) || str.startsWith("file://");
    }

    private void showLoading() {
        this.mAdaWebview.getDCWebView().getWebView().post(new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.11
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                AdaWebview adaWebview = WebLoadEvent.this.mAdaWebview;
                if (adaWebview != null) {
                    AdaFrameView adaFrameView = adaWebview.mFrameView;
                    adaFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_SHOW_LOADING, adaFrameView);
                }
            }
        });
        this.mShowLoadingTime = System.currentTimeMillis();
    }

    private void startTryLoadAllJSContent(WebView webView, String str, String str2) {
        loadAllJSContent(webView, str, str2);
    }

    public void closeWap2AppBlockDialog(boolean z) {
        WaitingView waitingView = this.mWap2AppBlockDialog;
        if (waitingView != null) {
            waitingView.close();
            this.mAdaWebview.obtainApp().unregisterSysEventListener(this.mWap2AppBlockDialogSysEventListener, ISysEventListener.SysEventType.onKeyUp);
            this.mWap2AppBlockDialog = null;
            this.mWap2AppBlockDialogSysEventListener = null;
            if (z) {
                AdaWebview adaWebview = this.mAdaWebview;
                adaWebview.loadUrl(adaWebview.mRecordLastUrl);
            }
        }
    }

    public void destroy() {
        this.mAdaWebview = null;
        this.mPlusJS = null;
        this.mTitleNViewProgressStop = null;
        this.mWap2AppBlockDialog = null;
        this.mWaitingForWapPage = null;
    }

    @Override // android.webkit.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        if (PdrUtil.isEmpty(this.mdcloudwebviewclientlister)) {
            return;
        }
        this.mdcloudwebviewclientlister.doUpdateVisitedHistory(webView, str, z);
    }

    public String getErrorPage() {
        String strHandleWap2appTemplateFilePath = this.mAdaWebview.mFrameView.obtainFrameOptions().errorPage;
        if (URLUtil.isNetworkUrl(strHandleWap2appTemplateFilePath)) {
            return strHandleWap2appTemplateFilePath;
        }
        if (TextUtils.isEmpty(strHandleWap2appTemplateFilePath)) {
            String strObtainConfigProperty = this.mAdaWebview.obtainApp().obtainConfigProperty("error");
            if (!"none".equals(strObtainConfigProperty)) {
                return this.mAdaWebview.obtainApp().convert2WebviewFullPath(null, strObtainConfigProperty);
            }
        } else {
            IApp iAppObtainApp = this.mAdaWebview.obtainApp();
            if ("none".equals(strHandleWap2appTemplateFilePath)) {
                return strHandleWap2appTemplateFilePath;
            }
            String strConvert2AbsFullPath = iAppObtainApp.convert2AbsFullPath(this.mAdaWebview.obtainFullUrl(), strHandleWap2appTemplateFilePath);
            File file = new File(strConvert2AbsFullPath);
            if (file.exists()) {
                return iAppObtainApp.convert2WebviewFullPath(this.mAdaWebview.obtainFullUrl(), strHandleWap2appTemplateFilePath);
            }
            if (BaseInfo.isWap2AppAppid(iAppObtainApp.obtainAppId())) {
                String relPath = WebResUtil.getRelPath(PdrUtil.stripQuery(PdrUtil.stripAnchor(strConvert2AbsFullPath)), iAppObtainApp);
                if (WebResUtil.isWap2appTemplateFile(iAppObtainApp, relPath)) {
                    strHandleWap2appTemplateFilePath = WebResUtil.handleWap2appTemplateFilePath(relPath);
                    file = new File(strHandleWap2appTemplateFilePath);
                }
            }
            if (file.exists()) {
                return "file://" + strHandleWap2appTemplateFilePath;
            }
            String strObtainConfigProperty2 = iAppObtainApp.obtainConfigProperty("error");
            if (!"none".equals(strObtainConfigProperty2)) {
                return iAppObtainApp.convert2WebviewFullPath(null, strObtainConfigProperty2);
            }
        }
        return "none";
    }

    public void injectScript(final WebView webView, final String str, final String str2) {
        if (str2.equals("onPageFinished") && this.mAdaWebview.mPlusrequire.equals("later")) {
            webView.postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.10
                @Override // java.lang.Runnable
                public void run() {
                    WebLoadEvent webLoadEvent = WebLoadEvent.this;
                    if (webLoadEvent.mAdaWebview != null) {
                        webLoadEvent.onPreloadJSContent(webView, str, str2);
                        WebLoadEvent.this.onPlusreadyEvent(webView, str, str2);
                    }
                }
            }, 2000L);
        } else {
            onPreloadJSContent(webView, str, str2);
            onPlusreadyEvent(webView, str, str2);
        }
        onLoadCssContent();
    }

    public void listenPageFinishTimeout(final WebView webView, final String str, final String str2) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview.mLoaded && adaWebview.isRealInject(str)) {
            injectScript(webView, str, str2);
            return;
        }
        Runnable runnable = this.Timeout_Page_Finish_Runnable;
        if (runnable != null) {
            MessageHandler.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.8
            @Override // java.lang.Runnable
            public void run() {
                AdaWebview adaWebview2 = WebLoadEvent.this.mAdaWebview;
                if (adaWebview2 == null || adaWebview2.mLoaded || !adaWebview2.isRealInject(str)) {
                    return;
                }
                WebLoadEvent.this.injectScript(webView, str, "page_finished_timeout_" + str2);
                WebLoadEvent.this.Timeout_Page_Finish_Runnable = null;
            }
        };
        this.Timeout_Page_Finish_Runnable = runnable2;
        MessageHandler.postDelayed(runnable2, 6000L);
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        if (this.mAdaWebview == null) {
            return;
        }
        if (this.printLog) {
            Logger.i(TAG, "onLoadResource url=" + str);
        }
        this.needResponseRedirect = true;
        printResourceLog(webView, this.mAdaWebview.mFrameView.obtainApp(), webView.getUrl(), str);
        IFrameView iFrameViewObtainFrameView = this.mAdaWebview.obtainFrameView();
        if (iFrameViewObtainFrameView.obtainStatus() != 3) {
            iFrameViewObtainFrameView.onLoading();
        }
        if (this.mAdaWebview.checkResourceLoading(str)) {
            this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_LISTEN_RESOURCE_LOADING, "{url:'" + str + "'}");
        }
        this.mAdaWebview.dispatchWebviewStateEvent(2, str);
        super.onLoadResource(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        boolean z;
        if (this.mAdaWebview == null) {
            return;
        }
        Logger.d(TAG, "onPageFinished=" + str);
        if (PdrUtil.isEmpty(this.mAdaWebview.mFrameView.obtainApp())) {
            Logger.e(TAG, "mAdaWebview.mFrameView.obtainApp()===null");
            return;
        }
        if (this.mAdaWebview.hadClearHistory(str)) {
            this.mAdaWebview.hasErrorPage = false;
            return;
        }
        if (this.mAdaWebview.hasErrorPage) {
            String errorPage = getErrorPage();
            if (!PdrUtil.isEquals(str, errorPage) && (!"data:text/html,chromewebdata".equals(str) || !"none".equals(errorPage))) {
                return;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        if (this.mAdaWebview.unReceiveTitle) {
            Logger.i(TAG, "onPageFinished will exe titleUpdate =" + str);
            AdaWebview adaWebview = this.mAdaWebview;
            adaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_TITLE_UPDATE, adaWebview.getDCWebView().getTitle());
            this.mAdaWebview.unReceiveTitle = false;
        }
        CookieSyncManager.getInstance().sync();
        Logger.i(TAG, "onPageFinished" + this.mAdaWebview.getOriginalUrl());
        this.mAdaWebview.dispatchWebviewStateEvent(1, str);
        this.mAdaWebview.loadForceAHeadJs();
        onLoadPlusJSContent(webView, str, "onPageFinished");
        if (this.mAdaWebview.isRealInject(str)) {
            injectScript(webView, str, "onPageFinished");
        }
        AdaWebview adaWebview2 = this.mAdaWebview;
        adaWebview2.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_LOADED, adaWebview2);
        if (z) {
            this.mAdaWebview.executeScript(StringUtil.format(ERROR_TEMPLATE, "error", this.mAdaWebview.getOriginalUrl(), this.mAdaWebview.errorPageUrl));
            AdaWebview adaWebview3 = this.mAdaWebview;
            adaWebview3.errorPageUrl = null;
            adaWebview3.hasErrorPage = false;
        }
        AdaFrameView adaFrameView = this.mAdaWebview.mFrameView;
        if (adaFrameView.obtainStatus() != 3) {
            adaFrameView.onPreShow(null);
        }
        AdaWebview adaWebview4 = this.mAdaWebview;
        if (!adaWebview4.mLoaded) {
            adaWebview4.mLoaded = true;
            adaWebview4.mPlusLoaded = true;
        }
        super.onPageFinished(webView, str);
        if (this.mAdaWebview.justClearOption && !str.startsWith("data:")) {
            Logger.d(TAG, "onPageFinished mWebViewImpl.clearHistory url=" + str);
            this.mAdaWebview.getDCWebView().clearHistory();
            this.mAdaWebview.justClearOption = false;
        }
        this.mAdaWebview.getDCWebView().webReload(false);
        OnPageFinishedCallack onPageFinishedCallack = this.mPageFinishedCallack;
        if (onPageFinishedCallack != null) {
            onPageFinishedCallack.onLoad();
        }
        if (this.mWaitingForWapPage != null) {
            try {
                ((ViewGroup) this.mAdaWebview.obtainFrameView().obtainMainView()).removeView(this.mWaitingForWapPage);
                this.mWaitingForWapPage = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.mAdaWebview.checkInjectSitemap();
        if (PdrUtil.isEmpty(this.mdcloudwebviewclientlister)) {
            return;
        }
        this.mdcloudwebviewclientlister.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null) {
            return;
        }
        if (adaWebview.hasErrorPage) {
            String errorPage = getErrorPage();
            if (!PdrUtil.isEquals(str, errorPage) && ((!"data:text/html,chromewebdata".equals(str) || !"none".equals(errorPage)) && (PdrUtil.isEmpty(this.mAdaWebview.errorPageUrl) || !this.mAdaWebview.errorPageUrl.equals(str)))) {
                AdaWebview adaWebview2 = this.mAdaWebview;
                adaWebview2.hasErrorPage = false;
                adaWebview2.errorPageUrl = null;
            }
        }
        Logger.i(TAG, "onPageStarted url=" + str);
        this.mAdaWebview.onPageStarted();
        printOpenLog(webView, str);
        if (this.mAdaWebview.hadClearHistory(str)) {
            return;
        }
        if (this.mAdaWebview.mPlusrequire.equals("ahead")) {
            listenPlusInjectTimeout(webView, str, "onPageStarted");
        }
        if (!str.startsWith("data:")) {
            this.mAdaWebview.getDCWebView().setUrlStr(str);
        }
        this.mAdaWebview.resetPlusLoadSaveData();
        if (!PdrUtil.isEmpty(this.mAdaWebview.getDCWebView().getUrlStr())) {
            AdaWebview adaWebview3 = this.mAdaWebview;
            adaWebview3.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_WINDOW_CLOSE, adaWebview3);
        }
        this.mAdaWebview.dispatchWebviewStateEvent(0, str);
        AdaWebview adaWebview4 = this.mAdaWebview;
        AdaFrameView adaFrameView = adaWebview4.mFrameView;
        adaFrameView.dispatchFrameViewEvents("loading", adaWebview4);
        if (adaFrameView.obtainStatus() != 3) {
            adaFrameView.onPreLoading();
        }
        super.onPageStarted(webView, str, bitmap);
        if (this.mAdaWebview.mFrameView.getFrameType() == 3) {
            try {
                if (this.mWaitingForWapPage == null) {
                    this.mWaitingForWapPage = new ProgressBar(this.mAdaWebview.getContext());
                    int i = AndroidResources.mResources.getDisplayMetrics().widthPixels;
                    int i2 = AndroidResources.mResources.getDisplayMetrics().heightPixels;
                    int i3 = PdrUtil.parseInt("7%", i, -1);
                    ((ViewGroup) this.mAdaWebview.obtainFrameView().obtainMainView()).addView(this.mWaitingForWapPage, new AbsoluteLayout.LayoutParams(i3, i3, (i - i3) / 2, (i2 - i3) / 2));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Object titleNView = TitleNViewUtil.getTitleNView(this.mAdaWebview.obtainFrameView().obtainWindowMgr(), this.mAdaWebview.obtainFrameView().obtainWebView(), this.mAdaWebview.obtainFrameView(), TitleNViewUtil.getTitleNViewId(this.mAdaWebview.obtainFrameView()));
        if (titleNView instanceof ITitleNView) {
            if (this.mTitleNViewProgressStop != null) {
                TitleNViewUtil.stopProcess((ITitleNView) titleNView);
                this.mAdaWebview.obtainWindowView().removeCallbacks(this.mTitleNViewProgressStop);
                this.mTitleNViewProgressStop = null;
            }
            this.mTitleNViewProgressStop = new TitleNViewProgressStop(this.mAdaWebview);
            TitleNViewUtil.startProcess((ITitleNView) titleNView);
            this.mAdaWebview.obtainWindowView().postDelayed(this.mTitleNViewProgressStop, 6000L);
        }
        if (PdrUtil.isEmpty(this.mdcloudwebviewclientlister)) {
            return;
        }
        this.mdcloudwebviewclientlister.onPageStarted(webView, str, bitmap);
    }

    public void onPreloadJSContent(WebView webView, String str, String str2) {
        if (this.mAdaWebview.obtainFrameView().obtainApp() == null || this.mAdaWebview.obtainFrameView().obtainApp().manifestBeParsed()) {
            AdaWebview adaWebview = this.mAdaWebview;
            if (adaWebview.mPreloadJsLoaded) {
                Logger.i(TAG, "mPreloadJs 已经提前注入JS完成。不需要再注入了" + this.mAdaWebview.getOriginalUrl());
                return;
            }
            String preLoadJsString = adaWebview.getPreLoadJsString();
            if (PdrUtil.isEmpty(preLoadJsString)) {
                return;
            }
            this.mAdaWebview.mPreloadJsLoading = true;
            Logger.i(TAG, " tag=" + str2 + ";url=" + str);
            completeLoadJs(webView, str, str2, new String[]{preLoadJsString}, IF_PRELOAD_TEMPLATE, this.mAdaWebview.mPreloadJsFile);
            this.mAdaWebview.mPreloadJsLoaded = true;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, final String str2) {
        if (this.mAdaWebview == null) {
            return;
        }
        Logger.e(TAG, "onReceivedError description=" + str + ";failingUrl=" + str2 + ";errorCode=" + i);
        this.mAdaWebview.dispatchWebviewStateEvent(5, str);
        AdaWebview adaWebview = this.mAdaWebview;
        adaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_FAILED, adaWebview);
        AdaWebview adaWebview2 = this.mAdaWebview;
        adaWebview2.hasErrorPage = true;
        adaWebview2.errorPageUrl = str2;
        final IApp iAppObtainApp = adaWebview2.mFrameView.obtainApp();
        if (iAppObtainApp != null) {
            try {
                if (BaseInfo.isWap2AppAppid(iAppObtainApp.obtainAppId()) && this.mAdaWebview.mFrameView.getFrameType() == 2 && !TextUtils.equals("none", iAppObtainApp.obtainConfigProperty("launchError"))) {
                    Context context = this.mAdaWebview.getContext();
                    final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
                    alertDialogCreate.setTitle(R.string.dcloud_common_tips);
                    alertDialogCreate.setCanceledOnTouchOutside(false);
                    alertDialogCreate.setMessage(context.getString(R.string.dcloud_common_no_network_tips));
                    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            if (i2 == -2) {
                                WebLoadEvent.this.mAdaWebview.getActivity().startActivity(new Intent("android.settings.SETTINGS"));
                            } else if (i2 == -3) {
                                Logger.e(WebLoadEvent.TAG, "onReceivedError try again");
                                DCloudAdapterUtil.getIActivityHandler(WebLoadEvent.this.mAdaWebview.getActivity());
                                WebLoadEvent.this.mAdaWebview.loadUrl(str2);
                            } else if (i2 == -1) {
                                Activity activity = WebLoadEvent.this.mAdaWebview.getActivity();
                                DCloudAdapterUtil.getIActivityHandler(activity).updateParam("closewebapp", activity);
                            }
                            alertDialogCreate.dismiss();
                        }
                    };
                    alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.4
                        @Override // android.content.DialogInterface.OnKeyListener
                        public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                            if (i2 != 4) {
                                return false;
                            }
                            alertDialogCreate.dismiss();
                            Activity activity = WebLoadEvent.this.mAdaWebview.getActivity();
                            DCloudAdapterUtil.getIActivityHandler(activity).updateParam("closewebapp", activity);
                            return false;
                        }
                    });
                    alertDialogCreate.setButton(-2, context.getString(R.string.dcloud_common_set_network), onClickListener);
                    alertDialogCreate.setButton(-3, context.getString(R.string.dcloud_common_retry), onClickListener);
                    alertDialogCreate.setButton(-1, context.getString(R.string.dcloud_common_exit), onClickListener);
                    alertDialogCreate.show();
                    iAppObtainApp.registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.5
                        @Override // io.dcloud.common.DHInterface.ISysEventListener
                        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                            AdaWebview adaWebview3;
                            if (ISysEventListener.SysEventType.onResume != sysEventType || (adaWebview3 = WebLoadEvent.this.mAdaWebview) == null) {
                                return false;
                            }
                            adaWebview3.obtainMainView().postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.5.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Logger.e(WebLoadEvent.TAG, "onReceivedError 500ms retry after the onResume");
                                    DCloudAdapterUtil.getIActivityHandler(WebLoadEvent.this.mAdaWebview.getActivity());
                                    AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                                    WebLoadEvent.this.mAdaWebview.loadUrl(str2);
                                }
                            }, 500L);
                            iAppObtainApp.unregisterSysEventListener(this, sysEventType);
                            return false;
                        }
                    }, ISysEventListener.SysEventType.onResume);
                    Logger.e(TAG, "onReceivedError do clearHistory");
                    this.mAdaWebview.clearHistory();
                } else {
                    String errorPage = getErrorPage();
                    if ("none".equals(errorPage)) {
                        this.mAdaWebview.hasErrorPage = false;
                    } else {
                        Logger.e(TAG, "onReceivedError  load errorPage " + errorPage);
                        this.mAdaWebview.loadUrl(errorPage);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (PdrUtil.isEmpty(this.mdcloudwebviewclientlister)) {
            return;
        }
        this.mdcloudwebviewclientlister.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null || adaWebview.obtainApp() == null) {
            return;
        }
        String strObtainConfigProperty = this.mAdaWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_UNTRUSTEDCA);
        Logger.i("onReceivedSslError", "onReceivedSslError++type====" + strObtainConfigProperty);
        if (PdrUtil.isEquals(strObtainConfigProperty, "refuse")) {
            sslErrorHandler.cancel();
        } else if (PdrUtil.isEquals(strObtainConfigProperty, "warning")) {
            Context context = webView.getContext();
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
            alertDialogCreate.setIcon(android.R.drawable.ic_secure);
            alertDialogCreate.setTitle(R.string.dcloud_common_safety_warning);
            alertDialogCreate.setCanceledOnTouchOutside(false);
            String url = Build.VERSION.SDK_INT >= 14 ? sslError.getUrl() : null;
            String string = context.getString(R.string.dcloud_common_certificate_continue);
            if (!TextUtils.isEmpty(url)) {
                string = url + "\n" + string;
            }
            alertDialogCreate.setMessage(string);
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: io.dcloud.common.adapter.ui.webview.WebLoadEvent.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == -2) {
                        alertDialogCreate.cancel();
                        alertDialogCreate.dismiss();
                    } else if (i == -3) {
                        sslError.getCertificate().getIssuedBy();
                    } else if (i == -1) {
                        WebViewFactory.setSslHandlerState(sslErrorHandler, 1);
                        alertDialogCreate.dismiss();
                    }
                }
            };
            alertDialogCreate.setButton(-2, context.getResources().getString(android.R.string.cancel), onClickListener);
            alertDialogCreate.setButton(-1, context.getResources().getString(android.R.string.ok), onClickListener);
            alertDialogCreate.show();
        } else {
            WebViewFactory.setSslHandlerState(sslErrorHandler, 1);
        }
        if (PdrUtil.isEmpty(this.mdcloudwebviewclientlister)) {
            return;
        }
        this.mdcloudwebviewclientlister.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    @Override // android.webkit.WebViewClient
    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        super.onUnhandledKeyEvent(webView, keyEvent);
        if (webView instanceof SysWebView) {
            SysWebView sysWebView = (SysWebView) webView;
            if (keyEvent.getAction() == 0) {
                sysWebView.doKeyDownAction(keyEvent.getKeyCode(), keyEvent);
            } else if (keyEvent.getAction() == 1) {
                sysWebView.doKeyUpAction(keyEvent.getKeyCode(), keyEvent);
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void onUpdatePlusData(WebView webView, String str, String str2) {
        AdaWebview adaWebview = this.mAdaWebview;
        adaWebview.executeScript(adaWebview.getScreenAndDisplayJson(adaWebview));
        onExecuteEvalJSStatck(webView, str, str2);
    }

    public void reset() {
        this.mPlusJS = "(function(){/*console.log('all.js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(!location.__plusready__){location.__plusready__=true;return 1}else{return 2}return 0})();\n" + this.mAdaWebview.mFrameView.obtainPrePlusreadyJs() + "\n" + DIFFERENT_VERSION_JS;
    }

    public void setDcloudwebviewclientListener(IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        this.mdcloudwebviewclientlister = iDCloudWebviewClientListener;
    }

    public void setPageFinishedCallack(OnPageFinishedCallack onPageFinishedCallack) {
        this.mPageFinishedCallack = onPageFinishedCallack;
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        String str2;
        JSONObject jSONObject;
        String str3;
        File fileA;
        File file;
        if (this.mAdaWebview == null) {
            return null;
        }
        WebResourceResponse webResourceResponseShouldInterceptRequest = super.shouldInterceptRequest(webView, str);
        WebResourceResponse webResourceResponseShouldInterceptRequest2 = super.shouldInterceptRequest(webView, str);
        if (!PdrUtil.isEmpty(this.mdcloudwebviewclientlister)) {
            webResourceResponseShouldInterceptRequest2 = this.mdcloudwebviewclientlister.shouldInterceptRequest(webView, str);
        }
        WebResourceResponse webResourceResponseCheckWebResourceResponseRedirect = checkWebResourceResponseRedirect(webView, str);
        if (webResourceResponseCheckWebResourceResponseRedirect != null) {
            return webResourceResponseCheckWebResourceResponseRedirect;
        }
        String str4 = "image/gif";
        if (!PdrUtil.isEmpty(str)) {
            if (str.startsWith("plusfile://")) {
                String strReplace = str.replace("plusfile://", "");
                if (strReplace.startsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR) || strReplace.startsWith(BaseInfo.REL_PRIVATE_DOC_DIR) || strReplace.startsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR)) {
                    String strConvert2WebviewFullPath = this.mAdaWebview.obtainApp().convert2WebviewFullPath(null, strReplace);
                    if (strConvert2WebviewFullPath.startsWith(ImageSource.FILE_SCHEME)) {
                        strConvert2WebviewFullPath = strConvert2WebviewFullPath.substring(7);
                    }
                    if (strConvert2WebviewFullPath.startsWith("file://")) {
                        strConvert2WebviewFullPath = strConvert2WebviewFullPath.substring(6);
                    }
                    file = new File(strConvert2WebviewFullPath);
                } else {
                    file = new File(strReplace);
                }
                if (file.exists()) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        String mimeType = PdrUtil.getMimeType(str);
                        if (str.contains(FileUtils.JPEG_FILE_SUFFIX)) {
                            mimeType = "image/jpeg";
                        } else if (str.contains(".png")) {
                            mimeType = "image/png";
                        } else if (str.contains(".gif")) {
                            mimeType = "image/gif";
                        }
                        return new WebResourceResponse(mimeType, null, fileInputStream);
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (str.contains("h5pscript://")) {
                InputStream encryptionInputStream = WebResUtil.getEncryptionInputStream(str.substring(str.indexOf("h5pscript://") + 12), this.mAdaWebview.obtainApp());
                String mimeType2 = PdrUtil.getMimeType(str);
                if (encryptionInputStream != null) {
                    if (str.contains(FileUtils.JPEG_FILE_SUFFIX)) {
                        str4 = "image/jpeg";
                    } else if (str.contains(".png")) {
                        str4 = "image/png";
                    } else if (!str.contains(".gif")) {
                        str4 = mimeType2;
                    }
                    return new WebResourceResponse(str4, null, encryptionInputStream);
                }
            } else if (str.startsWith("plus-confusion://")) {
                InputStream encryptionInputStream2 = WebResUtil.getEncryptionInputStream(this.mAdaWebview.obtainApp().convert2WebviewFullPath(this.mAdaWebview.obtainFullUrl(), str.substring(17)), this.mAdaWebview.obtainApp());
                String mimeType3 = PdrUtil.getMimeType(str);
                if (encryptionInputStream2 != null) {
                    return new WebResourceResponse(mimeType3, null, encryptionInputStream2);
                }
            }
        }
        if (ImageLoaderUtil.isDownload(str) && (fileA = cx0.f().c().a(str)) != null && fileA.exists()) {
            String mimeType4 = PdrUtil.getMimeType(str);
            if (str.contains(FileUtils.JPEG_FILE_SUFFIX)) {
                str4 = "image/jpeg";
            } else if (str.contains(".png")) {
                str4 = "image/png";
            } else if (!str.contains(".gif")) {
                str4 = mimeType4;
            }
            try {
                return new WebResourceResponse(str4, null, new FileInputStream(fileA));
            } catch (FileNotFoundException e3) {
                e3.printStackTrace();
            }
        }
        AdaWebview adaWebview = this.mAdaWebview;
        AdaWebview.OverrideResourceRequestItem overrideResourceRequestItemCheckResourceRequestUrl = adaWebview != null ? adaWebview.checkResourceRequestUrl(str) : null;
        AdaWebview adaWebview2 = this.mAdaWebview;
        if (adaWebview2 == null) {
            return webResourceResponseShouldInterceptRequest;
        }
        String str5 = adaWebview2.mEncoding;
        if (overrideResourceRequestItemCheckResourceRequestUrl != null) {
            str = overrideResourceRequestItemCheckResourceRequestUrl.redirect;
            str5 = overrideResourceRequestItemCheckResourceRequestUrl.encoding;
            str2 = overrideResourceRequestItemCheckResourceRequestUrl.mime;
        } else {
            str2 = "application/x-javascript";
        }
        try {
            Logger.i(TAG, "shouldInterceptRequest url=" + str + ";withJs=" + this.mAdaWebview.mInjectPlusWidthJs);
            webResourceResponseShouldInterceptRequest = handleDecode(str, webResourceResponseShouldInterceptRequest);
            if (webResourceResponseShouldInterceptRequest == null) {
                if (this.mAdaWebview.mPlusrequire.equals("ahead") && this.mAdaWebview.hasPreLoadJsFile() && (((str3 = this.mAdaWebview.mInjectPlusWidthJs) == null || TextUtils.equals(str3, str)) && PdrUtil.isNetPath(str) && checkJsFile(str))) {
                    webResourceResponseShouldInterceptRequest = downloadResponseInjection(webResourceResponseShouldInterceptRequest, str, str2, str5, this.TYPE_JS);
                    if (webResourceResponseShouldInterceptRequest != null) {
                        this.mAdaWebview.mInjectPlusWidthJs = str;
                    }
                } else if (!TextUtils.isEmpty(this.mAdaWebview.getCssString()) && !this.mAdaWebview.mIsAdvanceCss && PdrUtil.isNetPath(str) && checkCssFile(str)) {
                    str2 = "text/css";
                    webResourceResponseShouldInterceptRequest = downloadResponseInjection(webResourceResponseShouldInterceptRequest, str, "text/css", str5, this.TYPE_CSS);
                } else if (this.isInitAmapGEO) {
                    AdaWebview adaWebview3 = this.mAdaWebview;
                    if (!adaWebview3.mInjectGeoLoaded && DLGeolocation.checkInjectGeo(adaWebview3.mInjectGEO)) {
                        webResourceResponseShouldInterceptRequest = downloadResponseInjection(webResourceResponseShouldInterceptRequest, str, str2, str5, this.TYPE_JS);
                    }
                }
            }
            if (webResourceResponseShouldInterceptRequest == null && !BaseInfo.isWap2AppAppid(this.mAppid) && PLUSREADY.equals(str) && !this.mAdaWebview.mPlusLoaded) {
                webResourceResponseShouldInterceptRequest = downloadResponseInjection(webResourceResponseShouldInterceptRequest, str, str2, str5, this.TYPE_JS);
            }
            if (webResourceResponseShouldInterceptRequest == null) {
                BaseInfo.isUniAppAppid(this.mAdaWebview.obtainApp());
            }
            if (webResourceResponseShouldInterceptRequest == null && overrideResourceRequestItemCheckResourceRequestUrl != null) {
                try {
                    webResourceResponseShouldInterceptRequest = new WebResourceResponse(str2, str5, new FileInputStream(str));
                } catch (FileNotFoundException e4) {
                    e4.printStackTrace();
                }
            }
            if (webResourceResponseShouldInterceptRequest != null && Build.VERSION.SDK_INT >= 21) {
                Map<String, String> responseHeaders = webResourceResponseShouldInterceptRequest.getResponseHeaders();
                if (responseHeaders == null) {
                    responseHeaders = new HashMap<>();
                }
                responseHeaders.put("Access-Control-Allow-Credentials", "true");
                responseHeaders.put("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                responseHeaders.put("Access-Control-Allow-Origin", Operators.MUL);
                if (overrideResourceRequestItemCheckResourceRequestUrl != null && (jSONObject = overrideResourceRequestItemCheckResourceRequestUrl.headerJson) != null) {
                    Iterator<String> itKeys = jSONObject.keys();
                    if (overrideResourceRequestItemCheckResourceRequestUrl.headerJson.length() > 0) {
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            responseHeaders.put(next, overrideResourceRequestItemCheckResourceRequestUrl.headerJson.opt(next).toString());
                        }
                    }
                }
                webResourceResponseShouldInterceptRequest.setResponseHeaders(responseHeaders);
                return webResourceResponseShouldInterceptRequest;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
            Logger.e(this.mAppid + ";url=" + str);
        }
        return (PdrUtil.isEmpty(this.mdcloudwebviewclientlister) || PdrUtil.isEmpty(webResourceResponseShouldInterceptRequest2)) ? webResourceResponseShouldInterceptRequest : webResourceResponseShouldInterceptRequest2;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return false;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.mAdaWebview == null) {
            return false;
        }
        Logger.e(TAG, "shouldOverrideUrlLoading url=" + str);
        AdaWebview adaWebview = this.mAdaWebview;
        adaWebview.mProgressIntValue = 0;
        adaWebview.mRecordLastUrl = str;
        if (adaWebview.checkOverrideUrl(str)) {
            Logger.e(TAG, "检测拦截回调shouldOverrideUrlLoading url=" + str);
            this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_OVERRIDE_URL_LOADING, "{url:'" + str + "'}");
            return true;
        }
        if (this.mAdaWebview.mFrameView.getFrameType() == 5 || (this.mAdaWebview.mFrameView.getFrameType() == 2 && directPageIsLaunchPage(this.mAdaWebview.obtainApp()))) {
            this.mAdaWebview.obtainApp().updateDirectPage(str);
        }
        if (shouldRuntimeHandle(str) || this.mAdaWebview.mFrameView.getFrameType() == 6) {
            if (PdrUtil.isEmpty(this.mdcloudwebviewclientlister)) {
                return false;
            }
            return this.mdcloudwebviewclientlister.shouldOverrideUrlLoading(webView, str);
        }
        try {
            if (str.startsWith("sms:")) {
                int iIndexOf = str.indexOf("sms:");
                int iIndexOf2 = str.indexOf(Operators.CONDITION_IF_STRING);
                if (iIndexOf2 == -1) {
                    this.mAdaWebview.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                }
                String strSubstring = str.substring(iIndexOf + 4, iIndexOf2);
                String strSubstring2 = str.substring(iIndexOf2 + 1);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + strSubstring));
                intent.putExtra("address", strSubstring);
                intent.putExtra("sms_body", strSubstring2);
                this.mAdaWebview.getActivity().startActivity(intent);
            } else if (str.startsWith("intent://")) {
                Intent uri = Intent.parseUri(str, 1);
                uri.addCategory("android.intent.category.BROWSABLE");
                uri.setComponent(null);
                if (Build.VERSION.SDK_INT >= 15) {
                    uri.setSelector(null);
                }
                if (this.mAdaWebview.getActivity().getPackageManager().queryIntentActivities(uri, 0).size() > 0) {
                    this.mAdaWebview.getActivity().startActivityIfNeeded(uri, -1);
                }
            } else {
                AdaWebview adaWebview2 = this.mAdaWebview;
                if (adaWebview2 != null && adaWebview2.getActivity() != null && this.mAdaWebview.obtainApp().checkSchemeWhite(str)) {
                    this.mAdaWebview.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        } catch (Exception unused) {
            Logger.e(TAG, "ActivityNotFoundException url=" + str);
        }
        return true;
    }

    private WebResourceResponse downloadResponse(WebView webView, String str, String str2, WebResourceResponse webResourceResponse, File file, boolean z) throws Throwable {
        HttpURLConnection httpURLConnection;
        if (!URLUtil.isNetworkUrl(str2) || file == null) {
            return webResourceResponse;
        }
        showLoading();
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                try {
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setDoInput(true);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200 || responseCode == 206) {
                        if (!DHFile.writeFile(httpURLConnection.getInputStream(), file.getAbsolutePath()) && z) {
                            downloadResponse(webView, str, str2, webResourceResponse, file, false);
                        } else {
                            hideLoading();
                        }
                    } else if ((responseCode < 400 || responseCode >= 500) && z) {
                        downloadResponse(webView, str, str2, webResourceResponse, file, false);
                    } else {
                        hideLoading();
                    }
                } catch (Exception e2) {
                    e = e2;
                    httpURLConnection2 = httpURLConnection;
                    e.printStackTrace();
                    if (httpURLConnection2 != null) {
                        httpURLConnection = httpURLConnection2;
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    hideLoading();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        hideLoading();
        return handleDecode(str, webResourceResponse);
    }
}
