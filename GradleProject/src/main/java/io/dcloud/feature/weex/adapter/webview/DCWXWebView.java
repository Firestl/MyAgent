package io.dcloud.feature.weex.adapter.webview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.core.content.FileProvider;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.common.StringUtils;
import com.taobao.weex.ui.view.IWebView;
import com.taobao.weex.utils.WXLogUtils;
import io.dcloud.common.DHInterface.IActivityDelegate;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.adapter.ui.FileChooseDialog;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.nativeObj.photoview.subscaleview.ImageSource;
import io.dcloud.feature.weex.adapter.webview.WXDCWeb;
import io.dcloud.feature.weex.adapter.webview.video.FullscreenHolder;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import supwisdom.bv;

/* JADX INFO: loaded from: classes3.dex */
public class DCWXWebView implements IDCWebView {
    public static final String BRIDGE_NAME = "__dcloud_weex_";
    public static final int POST_MESSAGE = 1;
    public static final int POST_MESSAGE_TO_CONTROL = 2;
    public static final int SDK_VERSION = Build.VERSION.SDK_INT;
    public DCWXChromeClient chromeClient;
    public View customView;
    public WebChromeClient.CustomViewCallback customViewCallback;
    public FrameLayout fullscreenContainer;
    public Context mContext;
    public WeakReference<WXDCWeb> mDCWeb;
    public Handler mMessageHandler;
    public IWebView.OnErrorListener mOnErrorListener;
    public WXDCWeb.OnDCMessageListener mOnMessageListener;
    public IWebView.OnPageListener mOnPageListener;
    public String mOrigin;
    public FrameLayout mRootView;
    public bv mWebProgressView;
    public WebView mWebView;
    public boolean mShowLoading = true;
    public int mProgress = 0;
    public boolean isStart = false;
    public int defaultSystemUI = 0;

    public class DCWXChromeClient extends WebChromeClient {
        public FileChooseDialog dialog;
        public ValueCallback<Uri> mUploadMessage;
        public ValueCallback<Uri[]> mUploadMessage21Level;

        public DCWXChromeClient() {
        }

        private void openFileChooserLogic(final WebView webView, final ValueCallback<Uri> valueCallback, final ValueCallback<Uri[]> valueCallback2, final String str, final String str2) {
            if (DCWXWebView.this.mContext == null) {
                return;
            }
            PermissionUtil.usePermission((Activity) DCWXWebView.this.mContext, "dc_weexsdk", PermissionUtil.PMS_STORAGE, 2, new PermissionUtil.Request() { // from class: io.dcloud.feature.weex.adapter.webview.DCWXWebView.DCWXChromeClient.2
                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onDenied(String str3) {
                }

                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onGranted(String str3) {
                    DCWXChromeClient.this.showOpenFileChooser(webView, valueCallback, valueCallback2, str, str2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showOpenFileChooser(WebView webView, ValueCallback<Uri> valueCallback, ValueCallback<Uri[]> valueCallback2, String str, String str2) {
            this.mUploadMessage = valueCallback;
            this.mUploadMessage21Level = valueCallback2;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            if (PdrUtil.isEmpty(str)) {
                intent.setType("*/*");
            } else {
                intent.setType(str);
            }
            FileChooseDialog fileChooseDialog = new FileChooseDialog(webView.getContext(), (Activity) webView.getContext(), intent);
            this.dialog = fileChooseDialog;
            try {
                fileChooseDialog.show();
                this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: io.dcloud.feature.weex.adapter.webview.DCWXWebView.DCWXChromeClient.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        try {
                            if (DCWXChromeClient.this.mUploadMessage21Level != null) {
                                DCWXChromeClient.this.mUploadMessage21Level.onReceiveValue(null);
                            } else if (DCWXChromeClient.this.mUploadMessage != null) {
                                DCWXChromeClient.this.mUploadMessage.onReceiveValue(null);
                            }
                        } catch (Exception unused) {
                        }
                    }
                });
            } catch (Exception unused) {
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(final String str, final GeolocationPermissions.Callback callback) {
            if (DCWXWebView.this.mContext instanceof Activity) {
                PermissionUtil.usePermission((Activity) DCWXWebView.this.mContext, "dc_weexsdk", PermissionUtil.PMS_LOCATION, 2, new PermissionUtil.Request() { // from class: io.dcloud.feature.weex.adapter.webview.DCWXWebView.DCWXChromeClient.1
                    @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                    public void onDenied(String str2) {
                        callback.invoke(str, false, false);
                    }

                    @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                    public void onGranted(String str2) {
                        callback.invoke(str, true, false);
                    }
                });
            } else {
                super.onGeolocationPermissionsShowPrompt(str, callback);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            DCWXWebView.this.hideCustomView();
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            DCWXWebView dCWXWebView = DCWXWebView.this;
            dCWXWebView.mProgress = i;
            if (!dCWXWebView.isStart) {
                DCWXWebView dCWXWebView2 = DCWXWebView.this;
                if (dCWXWebView2.mProgress < 100) {
                    dCWXWebView2.startWebProgress();
                    DCWXWebView.this.isStart = true;
                }
            }
            DCWXWebView dCWXWebView3 = DCWXWebView.this;
            if (dCWXWebView3.mProgress >= 100 && dCWXWebView3.isStart) {
                DCWXWebView.this.isStart = false;
                if (DCWXWebView.this.mWebProgressView != null) {
                    DCWXWebView.this.mWebProgressView.a();
                }
            }
            WXLogUtils.v("tag", "onPageProgressChanged " + i);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (DCWXWebView.this.mOnPageListener != null) {
                DCWXWebView.this.mOnPageListener.onReceivedTitle(webView.getTitle());
            }
        }

        public void onResult(int i, int i2, Intent intent) {
            Uri uriForFile;
            FileChooseDialog fileChooseDialog;
            List<File> list;
            Cursor cursorQuery;
            FileChooseDialog fileChooseDialog2 = this.dialog;
            if (fileChooseDialog2 != null) {
                fileChooseDialog2.dismiss();
                if (i2 != 0) {
                    if (i == 1) {
                        if (intent == null) {
                            ValueCallback<Uri[]> valueCallback = this.mUploadMessage21Level;
                            if (valueCallback != null) {
                                valueCallback.onReceiveValue(null);
                            } else {
                                ValueCallback<Uri> valueCallback2 = this.mUploadMessage;
                                if (valueCallback2 != null) {
                                    valueCallback2.onReceiveValue(null);
                                }
                            }
                            this.dialog = null;
                            return;
                        }
                        uriForFile = intent.getData();
                        if (uriForFile != null && "content".equals(uriForFile.getScheme()) && (cursorQuery = DCWXWebView.this.mContext.getContentResolver().query(uriForFile, new String[]{"_data"}, null, null, null)) != null) {
                            if (cursorQuery.moveToFirst()) {
                                try {
                                    int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
                                    if (columnIndexOrThrow > -1) {
                                        String string = cursorQuery.getString(columnIndexOrThrow);
                                        uriForFile = Uri.parse(string);
                                        if (PdrUtil.isEmpty(uriForFile.getScheme())) {
                                            uriForFile = Uri.parse((string.startsWith("/") ? "file://" : ImageSource.FILE_SCHEME) + string);
                                        }
                                    }
                                } catch (Exception unused) {
                                }
                            }
                            cursorQuery.close();
                        }
                    } else if (i != 2 || (fileChooseDialog = this.dialog) == null || (list = fileChooseDialog.uris) == null) {
                        uriForFile = null;
                    } else {
                        for (File file : list) {
                            if (file.exists()) {
                                uriForFile = FileProvider.getUriForFile(DCWXWebView.this.mContext, DCWXWebView.this.mContext.getPackageName() + ".dc.fileprovider", file);
                                break;
                            }
                        }
                        uriForFile = null;
                    }
                    Uri[] uriArr = uriForFile != null ? new Uri[]{uriForFile} : null;
                    ValueCallback<Uri[]> valueCallback3 = this.mUploadMessage21Level;
                    if (valueCallback3 != null) {
                        valueCallback3.onReceiveValue(uriArr);
                    } else {
                        ValueCallback<Uri> valueCallback4 = this.mUploadMessage;
                        if (valueCallback4 != null) {
                            valueCallback4.onReceiveValue(uriForFile);
                        }
                    }
                } else {
                    ValueCallback<Uri[]> valueCallback5 = this.mUploadMessage21Level;
                    if (valueCallback5 != null) {
                        valueCallback5.onReceiveValue(null);
                    } else {
                        ValueCallback<Uri> valueCallback6 = this.mUploadMessage;
                        if (valueCallback6 != null) {
                            valueCallback6.onReceiveValue(null);
                        }
                    }
                }
                this.dialog = null;
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            DCWXWebView.this.showCustomView(view, customViewCallback);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            openFileChooserLogic(webView, null, valueCallback, fileChooserParams.getAcceptTypes() != null ? fileChooserParams.getAcceptTypes()[0] : null, "");
            return true;
        }
    }

    public static class MessageHandler extends Handler {
        public final WeakReference<DCWXWebView> mWv;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                if (this.mWv.get() == null || this.mWv.get().mOnMessageListener == null) {
                    return;
                }
                HashMap map = new HashMap();
                map.put("detail", message.obj);
                this.mWv.get().mOnMessageListener.onMessage(map, 1);
                return;
            }
            if (i != 2 || this.mWv.get() == null || this.mWv.get().mOnMessageListener == null) {
                return;
            }
            HashMap map2 = new HashMap();
            map2.put("data", message.obj);
            this.mWv.get().mOnMessageListener.onMessage(map2, 2);
        }

        public MessageHandler(DCWXWebView dCWXWebView) {
            this.mWv = new WeakReference<>(dCWXWebView);
        }
    }

    public DCWXWebView(Context context, String str, WXDCWeb wXDCWeb) {
        this.mContext = context;
        this.mOrigin = str;
        this.mDCWeb = new WeakReference<>(wXDCWeb);
    }

    private void evaluateJS(String str) {
        if (SDK_VERSION < 19) {
            this.mWebView.loadUrl(str);
        } else {
            this.mWebView.evaluateJavascript(str, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideCustomView() {
        Context context;
        if (this.customView == null || (context = this.mContext) == null || !(context instanceof Activity)) {
            return;
        }
        setStatusBarVisibility((Activity) context, true);
        if (this.fullscreenContainer.getParent() != null) {
            ((ViewGroup) this.fullscreenContainer.getParent()).removeView(this.fullscreenContainer);
            this.fullscreenContainer.removeAllViews();
        }
        this.fullscreenContainer = null;
        this.customView = null;
        WebChromeClient.CustomViewCallback customViewCallback = this.customViewCallback;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.customViewCallback = null;
        }
        this.mWebView.setVisibility(0);
    }

    private void initWebView(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setAllowFileAccess(false);
        settings.setBuiltInZoomControls(false);
        settings.setAllowContentAccess(true);
        settings.setSavePassword(false);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibilityTraversal");
        webView.removeJavascriptInterface("accessibility");
        WebViewFactory.setFileAccess(settings, true);
        settings.setDefaultTextEncodingName(StringUtils.GB2312);
        webView.setWebViewClient(new WebViewClient() { // from class: io.dcloud.feature.weex.adapter.webview.DCWXWebView.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                super.onPageFinished(webView2, str);
                WXLogUtils.v("tag", "onPageFinished " + str);
                if (DCWXWebView.this.isStart) {
                    DCWXWebView.this.isStart = false;
                    if (DCWXWebView.this.mWebProgressView != null) {
                        DCWXWebView.this.mWebProgressView.a();
                    }
                }
                if (DCWXWebView.this.mOnPageListener != null) {
                    DCWXWebView.this.mOnPageListener.onPageFinish(str, webView2.canGoBack(), webView2.canGoForward());
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                super.onPageStarted(webView2, str, bitmap);
                WXLogUtils.v("tag", "onPageStarted " + str);
                if (DCWXWebView.this.mOnPageListener != null) {
                    DCWXWebView.this.mOnPageListener.onPageStart(str);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView2, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView2, webResourceRequest, webResourceError);
                if (DCWXWebView.this.mOnErrorListener != null) {
                    DCWXWebView.this.mOnErrorListener.onError("error", "page error");
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedHttpError(WebView webView2, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                super.onReceivedHttpError(webView2, webResourceRequest, webResourceResponse);
                if (DCWXWebView.this.mOnErrorListener != null) {
                    DCWXWebView.this.mOnErrorListener.onError("error", "http error");
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView2, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.onReceivedSslError(webView2, sslErrorHandler, sslError);
                if (DCWXWebView.this.mOnErrorListener != null) {
                    DCWXWebView.this.mOnErrorListener.onError("error", "ssl error");
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                if (PdrUtil.isDeviceRootDir(str) || PdrUtil.isNetPath(str) || str.startsWith("file://")) {
                    return false;
                }
                try {
                    if (str.startsWith("intent://")) {
                        Intent uri = Intent.parseUri(str, 1);
                        uri.addCategory("android.intent.category.BROWSABLE");
                        uri.setComponent(null);
                        if (Build.VERSION.SDK_INT >= 15) {
                            uri.setSelector(null);
                        }
                        if (DCWXWebView.this.mContext.getPackageManager().queryIntentActivities(uri, 0).size() > 0) {
                            ((Activity) DCWXWebView.this.mContext).startActivityIfNeeded(uri, -1);
                        }
                    } else {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                        intent.setFlags(268435456);
                        DCWXWebView.this.mContext.startActivity(intent);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return true;
            }
        });
        DCWXChromeClient dCWXChromeClient = new DCWXChromeClient();
        this.chromeClient = dCWXChromeClient;
        webView.setWebChromeClient(dCWXChromeClient);
        if (Build.VERSION.SDK_INT > 18) {
            webView.addJavascriptInterface(new Object() { // from class: io.dcloud.feature.weex.adapter.webview.DCWXWebView.2
                @JavascriptInterface
                public void postMessage(String str) {
                    DCWXWebView.this.onMessage(str, 1);
                }

                @JavascriptInterface
                public void postMessageToService(String str) {
                    DCWXWebView.this.onMessage(str, 2);
                }
            }, BRIDGE_NAME);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object] */
    public void onMessage(String str, int i) {
        if (str == 0 || this.mOnMessageListener == null) {
            return;
        }
        try {
            str = JSON.parse(str);
        } catch (Exception unused) {
        }
        Message message = new Message();
        message.what = i;
        message.obj = str;
        this.mMessageHandler.sendMessage(message);
    }

    private void setStatusBarVisibility(Activity activity, boolean z) {
        if (z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(this.defaultSystemUI);
        } else {
            this.defaultSystemUI = activity.getWindow().getDecorView().getSystemUiVisibility();
            activity.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void showCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.customView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        Context context = this.mContext;
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) context;
        FrameLayout frameLayoutObtainActivityContentView = null;
        IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(activity);
        if (iActivityHandler != null) {
            frameLayoutObtainActivityContentView = iActivityHandler.obtainActivityContentView();
            iActivityHandler.closeSideBar();
            iActivityHandler.setSideBarVisibility(8);
        } else if (activity instanceof IActivityDelegate) {
            frameLayoutObtainActivityContentView = ((IActivityDelegate) activity).obtainActivityContentView();
        }
        this.mWebView.setVisibility(8);
        this.fullscreenContainer = new FullscreenHolder(this.mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.fullscreenContainer.addView(view, layoutParams);
        frameLayoutObtainActivityContentView.addView(this.fullscreenContainer, layoutParams);
        this.customView = view;
        setStatusBarVisibility(activity, false);
        this.customViewCallback = customViewCallback;
    }

    private void showWebView(boolean z) {
        this.mWebView.setVisibility(z ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWebProgress() {
        if (this.mDCWeb.get() == null || this.mDCWeb.get().getHostView() == null) {
            return;
        }
        if (this.mDCWeb.get() == null || this.mDCWeb.get().getWebStyles() == null || this.mDCWeb.get().getWebStyles().getBooleanValue("isProgress")) {
            String url = getWebView().getUrl();
            if (TextUtils.isEmpty(url) || url.startsWith("file")) {
                return;
            }
            if (this.mWebProgressView == null) {
                this.mWebProgressView = new bv(this.mWebView.getContext());
            }
            int color = Color.parseColor(this.mDCWeb.get().getWebStyles().containsKey("progressColor") ? this.mDCWeb.get().getWebStyles().getString("progressColor") : "#00FF00");
            int iConvertToScreenInt = PdrUtil.convertToScreenInt("2px", this.mWebView.getMeasuredWidth(), 0, this.mWebView.getScale());
            this.mWebProgressView.setColorInt(color);
            this.mWebProgressView.setVisibility(0);
            this.mWebProgressView.setAlphaInt(255);
            if (this.mWebProgressView.getParent() == null) {
                this.mRootView.addView(this.mWebProgressView, new ViewGroup.LayoutParams(-1, iConvertToScreenInt));
            }
            this.mWebProgressView.c();
        }
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void destroy() {
        if (getWebView() != null) {
            hideCustomView();
            getWebView().setWebViewClient(null);
            getWebView().setWebChromeClient(null);
            getWebView().removeAllViews();
            getWebView().destroy();
            this.mWebView = null;
            this.mDCWeb.clear();
            this.mOnMessageListener = null;
            this.mWebProgressView = null;
            this.mOnPageListener = null;
            this.mOnErrorListener = null;
        }
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public View getView() {
        FrameLayout frameLayout = new FrameLayout(this.mContext);
        this.mRootView = frameLayout;
        frameLayout.setBackgroundColor(-1);
        this.mWebView = new WebView(this.mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.mWebView.setLayoutParams(layoutParams);
        this.mRootView.addView(this.mWebView);
        this.mWebView.setBackgroundColor(0);
        initWebView(this.mWebView);
        this.mMessageHandler = new MessageHandler();
        return this.mRootView;
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void goBack() {
        if (getWebView() == null) {
            return;
        }
        getWebView().goBack();
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void goForward() {
        if (getWebView() == null) {
            return;
        }
        getWebView().goForward();
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void loadDataWithBaseURL(String str) {
        if (getWebView() == null) {
            return;
        }
        getWebView().loadDataWithBaseURL(this.mOrigin, str, "text/html", "utf-8", null);
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void loadUrl(String str) {
        if (getWebView() == null) {
            return;
        }
        getWebView().loadUrl(str);
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void onActivityResult(int i, int i2, Intent intent) {
        DCWXChromeClient dCWXChromeClient = this.chromeClient;
        if (dCWXChromeClient != null) {
            dCWXChromeClient.onResult(i, i2, intent);
        }
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void postMessage(Object obj) {
        if (getWebView() == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", (Object) "message");
            jSONObject.put("data", obj);
            evaluateJS("javascript:(function () {var initData = " + jSONObject.toString() + ";try {var event = new MessageEvent('onPostMessage', initData);window.dispatchEvent(event);} catch (e) {}})();");
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void reload() {
        if (getWebView() == null) {
            return;
        }
        getWebView().reload();
    }

    public void setOnDCMessageListener(WXDCWeb.OnDCMessageListener onDCMessageListener) {
        this.mOnMessageListener = onDCMessageListener;
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void setOnErrorListener(IWebView.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void setOnMessageListener(IWebView.OnMessageListener onMessageListener) {
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void setOnPageListener(IWebView.OnPageListener onPageListener) {
        this.mOnPageListener = onPageListener;
    }

    @Override // com.taobao.weex.ui.view.IWebView
    public void setShowLoading(boolean z) {
        this.mShowLoading = z;
    }

    @Override // io.dcloud.feature.weex.adapter.webview.IDCWebView
    public WebView getWebView() {
        return this.mWebView;
    }
}
