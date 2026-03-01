package io.dcloud.common.adapter.ui.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.zxing.common.StringUtils;
import com.taobao.weex.el.parse.Operators;
import com.tencent.open.SocialConstants;
import dc.squareup.HttpConstants;
import io.dcloud.a;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IDCloudWebviewClientListener;
import io.dcloud.common.DHInterface.ILoadCallBack;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.ITitleNView;
import io.dcloud.common.DHInterface.IVideoPlayer;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.AdaContainerFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.ui.CustomeizedInputConnection;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.DownloadUtil;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.DialogUtil;
import io.dcloud.common.util.LoadAppUtils;
import io.dcloud.common.util.NotificationUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.e;
import io.dcloud.feature.internal.sdk.SDK;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONObject;
import supwisdom.cv;
import supwisdom.vu;

/* JADX INFO: loaded from: classes2.dex */
public class SysWebView extends WebView implements DCWebView, DownloadListener, vu.a {
    public static final String PLUSSCROLLBOTTOM_JS_TEMPLATE = "(function(){var e = document.createEvent('HTMLEvents');var evt = 'plusscrollbottom';e.initEvent(evt, false, true);document.dispatchEvent(e);})();";
    public static final String TAG = "webview";
    public CookieManager cm;
    public boolean didTouch;
    public boolean isToInvalidate;
    public AdaWebview mAdaWebview;
    public String mBaseUrl;
    public int mCacheMode;
    public int mContentHeight;
    public Context mContext;
    public IDCloudWebviewClientListener mDcloudwebviewclientListener;
    public int mDeafaltOverScrollMode;
    public int mEventX;
    public int mEventY;
    public boolean mIsBeingDragged;
    public float mLastMotionX;
    public float mLastMotionY;
    public long mLastScrollTimestamp;
    public int mLastScrollY;
    public OnPageFinishedCallack mPageFinishedCallack;
    public String mPageTitle;
    public float mScale;
    public int mThreshold;
    public int mThresholdTime;
    public int mTouchSlop;
    public String mUrl;
    public HashMap<String, HashMap<String, String>> mUrlHeads;
    public String mUserAgent;
    public WebJsEvent mWebJsEvent;
    public WebLoadEvent mWebLoadEvent;
    public WebSettings webSettings;

    public static class BorderDrawable extends Drawable {
        public int mBackgroundColor;
        public Paint mPaint;

        public BorderDrawable(int i, int i2) {
            Paint paint = new Paint();
            this.mPaint = paint;
            this.mBackgroundColor = i;
            paint.setColor(i2);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.drawColor(this.mBackgroundColor);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(3.0f);
            canvas.drawRect(getBounds(), this.mPaint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.mPaint.getAlpha();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.mPaint.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
        }
    }

    @TargetApi(11)
    public class CustomizedSelectActionModeCallback implements ActionMode.Callback {
        public ActionMode.Callback callback;

        public CustomizedSelectActionModeCallback(ActionMode.Callback callback) {
            this.callback = callback;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.callback.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.callback.onCreateActionMode(actionMode, menu);
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = menu.getItem(i);
                String string = item.getTitle().toString();
                if (string.contains("搜索") || string.toLowerCase().contains("search")) {
                    menu.removeItem(item.getItemId());
                    return true;
                }
            }
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.callback.onDestroyActionMode(actionMode);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.callback.onPrepareActionMode(actionMode, menu);
        }
    }

    public SysWebView(Context context, AdaWebview adaWebview) {
        super(context);
        this.mUserAgent = null;
        this.mAdaWebview = null;
        this.mWebLoadEvent = null;
        this.mWebJsEvent = null;
        this.mUrl = null;
        this.mScale = 0.0f;
        this.mContext = null;
        this.mBaseUrl = null;
        this.webSettings = getSettings();
        this.cm = null;
        this.mLastScrollY = 0;
        this.mContentHeight = 0;
        this.mThreshold = 2;
        this.mThresholdTime = 15;
        this.mLastScrollTimestamp = 0L;
        this.mPageTitle = null;
        this.mDeafaltOverScrollMode = 0;
        this.mCacheMode = -1;
        this.didTouch = false;
        this.isToInvalidate = false;
        this.mUrlHeads = new HashMap<>();
        this.mEventY = 0;
        this.mEventX = 0;
        this.mTouchSlop = -1;
        this.mIsBeingDragged = true;
        this.mScale = getContext().getResources().getDisplayMetrics().density;
        Logger.d("WebViewImpl");
        this.mContext = context.getApplicationContext();
        BaseInfo.s_Webview_Count++;
        this.mAdaWebview = adaWebview;
    }

    private Bitmap captureWebView(WebView webView, Rect rect) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.translate(-rect.left, -rect.top);
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview != null && adaWebview.obtainFrameView() != null && (this.mAdaWebview.obtainFrameView() instanceof AdaContainerFrameItem) && ((AdaContainerFrameItem) this.mAdaWebview.obtainFrameView()).getChilds().size() > 1) {
            if (this.mAdaWebview.obtainFrameView().obtainMainView() != null) {
                this.mAdaWebview.obtainFrameView().obtainMainView().draw(canvas);
            }
            return bitmapCreateBitmap;
        }
        if (Build.VERSION.SDK_INT < 19) {
            ((View) webView.getParent().getParent()).draw(canvas);
        } else {
            webView.draw(canvas);
        }
        return bitmapCreateBitmap;
    }

    public static String getStreamAppFlag() {
        String str;
        Object[] objArr = new Object[1];
        if (TextUtils.isEmpty(BaseInfo.sChannel)) {
            str = "";
        } else {
            str = " (" + BaseInfo.sChannel + ") ";
        }
        objArr[0] = str;
        return StringUtil.format(DCWebView.UserAgentStreamApp, objArr);
    }

    private void removeUnSafeJavascriptInterface() {
        try {
            int i = Build.VERSION.SDK_INT;
            if (i < 11 || i >= 17) {
                return;
            }
            Method method = getClass().getMethod("removeJavascriptInterface", String.class);
            String[] strArr = {"searchBoxJavaBridge_", "accessibility", "ccessibilityaversal"};
            for (int i2 = 0; i2 < 3; i2++) {
                method.invoke(this, strArr[i2]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void setWebViewData() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class[] clsArr = new Class[0];
        Class<?>[] clsArr2 = {Boolean.TYPE};
        try {
            Method declaredMethod = WebView.class.getDeclaredMethod("getFactory", clsArr);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                Object[] objArr = new Object[0];
                Object[] objArr2 = {Boolean.FALSE};
                Object objInvoke = declaredMethod.invoke(null, objArr);
                Method declaredMethod2 = objInvoke.getClass().getDeclaredMethod("setWebContentsDebuggingEnabled", clsArr2);
                if (declaredMethod2 != null) {
                    declaredMethod2.setAccessible(true);
                    declaredMethod2.invoke(objInvoke, objArr2);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException | Exception unused) {
        }
    }

    public boolean checkApkUrl(String str, String str2) {
        return (!TextUtils.isEmpty(str2) && str2.toLowerCase().contains(".apk")) || str.toLowerCase().contains(".apk");
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public boolean checkOverrideUrl(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (AbsoluteConst.EVENTS_WEBVIEW_ONTOUCH_START.equals(jSONObject.optString("effect", "instant")) && !isDidTouch()) {
                    return false;
                }
                int type = getHitTestResult().getType();
                if ("redirect".equalsIgnoreCase(jSONObject.optString(SocialConstants.PARAM_EXCLUDE)) && type == 0) {
                    return false;
                }
                String strOptString = jSONObject.optString("mode");
                boolean zMatches = jSONObject.has("match") ? Pattern.compile(jSONObject.optString("match")).matcher(str).matches() : true;
                if ("allow".equals(strOptString)) {
                    if (!zMatches) {
                        return true;
                    }
                } else if (zMatches) {
                    return true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public boolean checkWhite(String str) {
        Rect rect;
        if (getWidth() <= 0) {
            return true;
        }
        if (str.equals("center")) {
            int height = getHeight() / 2;
            rect = new Rect(0, height, getWidth(), height + 1);
        } else if (str.equals("top")) {
            int deivceSuitablePixel = DeviceInfo.getDeivceSuitablePixel(this.mAdaWebview.getActivity(), 20);
            rect = new Rect(0, deivceSuitablePixel, getWidth(), deivceSuitablePixel + 1);
        } else if (str.equals("bottom")) {
            int deivceSuitablePixel2 = DeviceInfo.getDeivceSuitablePixel(this.mAdaWebview.getActivity(), 25);
            rect = new Rect(0, (getHeight() - deivceSuitablePixel2) + 1, getWidth(), getHeight() - deivceSuitablePixel2);
        } else {
            int width = getWidth() / 2;
            rect = new Rect(width, 0, width + 5, getHeight());
        }
        Bitmap bitmapCaptureWebView = captureWebView(this, rect);
        if (bitmapCaptureWebView == null) {
            return false;
        }
        boolean zIsWhiteBitmap = str.equals("auto") ? PlatformUtil.isWhiteBitmap(bitmapCaptureWebView, !this.mAdaWebview.isLoaded(), true) : PlatformUtil.isLineWhiteBitmap(bitmapCaptureWebView, !this.mAdaWebview.isLoaded());
        bitmapCaptureWebView.recycle();
        return zIsWhiteBitmap;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void closeWap2AppBlockDialog(boolean z) {
        this.mWebLoadEvent.closeWap2AppBlockDialog(z);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public String convertRelPath(String str) {
        if (str.indexOf(this.mBaseUrl) >= 0) {
            return str.substring(this.mBaseUrl.length());
        }
        String strSubstring = this.mBaseUrl.substring(7);
        return str.indexOf(strSubstring) >= 0 ? str.substring(strSubstring.length()) : str;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void destroyWeb() {
        setWebChromeClient(null);
        setWebViewClient(null);
        setDownloadListener(null);
        destroy();
        destroyDrawingCache();
        clearDisappearingChildren();
        if (this.mAdaWebview != null) {
            this.cm = null;
            setOnLongClickListener(null);
            this.mAdaWebview = null;
            this.mWebLoadEvent.destroy();
            this.mWebLoadEvent = null;
            this.mWebJsEvent.destroy();
            this.mWebJsEvent = null;
            this.webSettings = null;
            this.mContext = null;
        }
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        for (int i = 0; i < getChildCount(); i++) {
            boolean zDispatchKeyEvent = getChildAt(i).dispatchKeyEvent(keyEvent);
            if (zDispatchKeyEvent) {
                return zDispatchKeyEvent;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean doKeyDownAction(int i, KeyEvent keyEvent) {
        e eVar = (e) this.mAdaWebview.getActivity();
        boolean zOnKeyEventExecute = keyEvent.getRepeatCount() == 0 ? eVar.onKeyEventExecute(ISysEventListener.SysEventType.onKeyDown, i, keyEvent) : eVar.onKeyEventExecute(ISysEventListener.SysEventType.onKeyDown, i, keyEvent);
        return zOnKeyEventExecute ? zOnKeyEventExecute : super.onKeyDown(i, keyEvent);
    }

    public boolean doKeyUpAction(int i, KeyEvent keyEvent) {
        boolean zOnKeyEventExecute = ((e) this.mAdaWebview.getActivity()).onKeyEventExecute(ISysEventListener.SysEventType.onKeyUp, i, keyEvent);
        return zOnKeyEventExecute ? zOnKeyEventExecute : super.onKeyUp(i, keyEvent);
    }

    public long downloadFile(final Context context, final String str, final String str2, String str3, String str4, final ILoadCallBack iLoadCallBack) {
        return DownloadUtil.getInstance(context).startRequest(context, str3, str4, DeviceInfo.sDeviceRootDir + "/Download/", str, new ILoadCallBack() { // from class: io.dcloud.common.adapter.ui.webview.SysWebView.3
            @Override // io.dcloud.common.DHInterface.ILoadCallBack
            public Object onCallBack(int i, Context context2, Object obj) {
                SDK.IntegratedMode integratedMode = BaseInfo.sRuntimeMode;
                if (obj == null && i == -1 && context2 == null) {
                    Intent intent = new Intent();
                    NotificationUtil.showNotification(context, str2, context.getString(R.string.dcloud_common_download_failed) + Operators.SPACE_STR + str, intent, -1, -1, intent.hashCode(), true);
                    return null;
                }
                if (iLoadCallBack != null) {
                    String strValueOf = String.valueOf(obj);
                    String mimeType = PdrUtil.getMimeType(strValueOf);
                    if (strValueOf.startsWith("file://")) {
                        strValueOf = strValueOf.substring(7);
                    }
                    if (strValueOf.startsWith("content://")) {
                        strValueOf = PlatformUtil.getFilePathFromContentUri(Uri.parse(strValueOf), context.getContentResolver());
                        mimeType = PdrUtil.getMimeType(strValueOf);
                    }
                    if ("true".equals(String.valueOf(iLoadCallBack.onCallBack(0, context2, LoadAppUtils.getDataAndTypeIntent(context, strValueOf, mimeType))))) {
                        return null;
                    }
                }
                if (integratedMode == null) {
                    String strValueOf2 = String.valueOf(obj);
                    String mimeType2 = PdrUtil.getMimeType(strValueOf2);
                    if (strValueOf2.startsWith("file://")) {
                        strValueOf2 = strValueOf2.substring(7);
                    }
                    if (strValueOf2.startsWith("content://")) {
                        strValueOf2 = PlatformUtil.getFilePathFromContentUri(Uri.parse(strValueOf2), context.getContentResolver());
                        mimeType2 = PdrUtil.getMimeType(strValueOf2);
                    }
                    File file = new File(strValueOf2);
                    Intent dataAndTypeIntent = LoadAppUtils.getDataAndTypeIntent(context, strValueOf2, mimeType2);
                    if (file.exists() && file.getName().toLowerCase().endsWith(".apk")) {
                        try {
                            PlatformUtil.APKInfo apkFileInfo = PlatformUtil.getApkFileInfo(context, strValueOf2);
                            Drawable drawable = apkFileInfo.mIcon;
                            String str5 = apkFileInfo.mAppName;
                            if (drawable instanceof BitmapDrawable) {
                                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                                String name = file.getName();
                                PendingIntent activity = Build.VERSION.SDK_INT >= 23 ? PendingIntent.getActivity(context, dataAndTypeIntent.hashCode(), dataAndTypeIntent, 1140850688) : PendingIntent.getActivity(context, dataAndTypeIntent.hashCode(), dataAndTypeIntent, 1073741824);
                                NotificationUtil.createCustomNotification(context, str5 + Operators.SPACE_STR + context.getString(R.string.dcloud_common_download_complete), bitmap, str5, name, dataAndTypeIntent.hashCode(), activity);
                                return null;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    NotificationUtil.showNotification(context, str2, str + Operators.SPACE_STR + context.getString(R.string.dcloud_common_download_complete), dataAndTypeIntent, -1, -1, dataAndTypeIntent.hashCode(), true);
                }
                return null;
            }
        });
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void evalJSSync(String str, final ICallBack iCallBack) {
        if (!str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT) || Build.VERSION.SDK_INT < 19) {
            return;
        }
        try {
            evaluateJavascript(str, new ValueCallback<String>() { // from class: io.dcloud.common.adapter.ui.webview.SysWebView.1
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(String str2) {
                    ICallBack iCallBack2 = iCallBack;
                    if (iCallBack2 != null) {
                        iCallBack2.onCallBack(1, str2);
                    }
                }
            });
        } catch (Throwable th) {
            Logger.e("webview", "e.getMessage()==" + th.getMessage());
            super.loadUrl(str);
            if (iCallBack != null) {
                iCallBack.onCallBack(1, null);
            }
        }
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public String getBaseUrl() {
        return this.mBaseUrl;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public int getCacheMode() {
        return this.mCacheMode;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public String getCookie(String str) {
        CookieManager cookieManager = this.cm;
        if (cookieManager != null) {
            return cookieManager.getCookie(str);
        }
        return null;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public String getPageTitle() {
        return this.mPageTitle;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public vu.a getRefreshListener() {
        return this;
    }

    @Override // android.webkit.WebView, io.dcloud.common.adapter.ui.webview.DCWebView
    public float getScale() {
        return this.mScale;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public String getUrlStr() {
        return this.mUrl;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public String getUserAgentString() {
        return this.webSettings.getUserAgentString();
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public ViewGroup getWebView() {
        return this;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public int getWebViewScrollY() {
        return getScrollY();
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void init() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        setOnLongClickListener(new View.OnLongClickListener() { // from class: io.dcloud.common.adapter.ui.webview.SysWebView.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                try {
                    AdaWebview adaWebview = SysWebView.this.mAdaWebview;
                    if (adaWebview == null) {
                        return true;
                    }
                    if (adaWebview.mFrameView.getCircleRefreshView() == null || !SysWebView.this.mAdaWebview.mFrameView.getCircleRefreshView().e()) {
                        return true ^ SysWebView.this.mAdaWebview.mFrameView.obtainFrameOptions().isUserSelect;
                    }
                    return true;
                } catch (Exception unused) {
                    return true;
                }
            }
        });
        if (!AdaWebview.setedWebViewData) {
            boolean zHasFile = DHFile.hasFile();
            boolean z = Boolean.parseBoolean(this.mAdaWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_USE_ENCRYPTION));
            boolean zIsUniAppAppid = BaseInfo.isUniAppAppid(this.mAdaWebview.obtainApp());
            String strObtainConfigProperty = this.mAdaWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_UNIAPP_CONTROL);
            boolean z2 = !zIsUniAppAppid;
            if (!TextUtils.isEmpty(strObtainConfigProperty) && zIsUniAppAppid && strObtainConfigProperty.equals(AbsoluteConst.UNI_V3)) {
                z2 = true;
            }
            if (!zHasFile && (!BaseInfo.ISDEBUG || z || !z2)) {
                setWebViewData();
            } else if (Build.VERSION.SDK_INT >= 19) {
                PlatformUtil.invokeMethod("android.webkit.WebView", "setWebContentsDebuggingEnabled", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.TRUE});
            }
            AdaWebview.setedWebViewData = true;
        }
        setDownloadListener(this);
        if (DeviceInfo.sDeviceSdkVer >= 9) {
            this.mDeafaltOverScrollMode = getOverScrollMode();
        }
        try {
            CookieSyncManager.createInstance(this.mContext);
            CookieManager cookieManager = CookieManager.getInstance();
            this.cm = cookieManager;
            if (cookieManager != null) {
                PlatformUtil.invokeMethod(CookieManager.class.getName(), "setAcceptThirdPartyCookies", this.cm, new Class[]{WebView.class, Boolean.TYPE}, new Object[]{this, Boolean.TRUE});
                this.cm.setAcceptCookie(true);
                this.cm.removeExpiredCookie();
                CookieSyncManager.getInstance().sync();
            }
        } catch (Throwable th) {
            Logger.e("WebViewImpl CookieManager.getInstance Exception =" + th);
        }
        this.mAdaWebview.obtainFrameView().onInit();
        IApp iAppObtainApp = this.mAdaWebview.obtainFrameView().obtainApp();
        this.mBaseUrl = iAppObtainApp.obtainWebviewBaseUrl();
        setScrollBarStyle(33554432);
        String str = AdaWebview.sCustomUserAgent;
        if (str != null) {
            this.webSettings.setUserAgentString(str);
        } else {
            initUserAgent(iAppObtainApp);
        }
        WebViewFactory.setFileAccess(this.webSettings, iAppObtainApp, true);
        this.webSettings.setDefaultTextEncodingName(StringUtils.GB2312);
        this.webSettings.setDisplayZoomControls(false);
        this.webSettings.setCacheMode(getCacheMode());
        this.webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        this.webSettings.setSavePassword(false);
        this.webSettings.setSaveFormData(false);
        this.webSettings.setJavaScriptEnabled(true);
        this.webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        boolean z3 = this.mAdaWebview.mFrameView.obtainFrameOptions().scalable;
        this.webSettings.supportZoom();
        this.webSettings.setBuiltInZoomControls(z3);
        this.webSettings.setSupportZoom(z3);
        this.webSettings.setUseWideViewPort(true);
        if (!Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
            this.webSettings.setLoadWithOverviewMode(true);
        }
        this.webSettings.setDatabasePath(this.mAdaWebview.obtainFrameView().obtainApp().obtainAppWebCachePath());
        this.webSettings.setAppCacheEnabled(true);
        this.webSettings.setAppCachePath(this.mAdaWebview.obtainFrameView().obtainApp().obtainAppWebCachePath());
        this.webSettings.setDatabaseEnabled(true);
        if (DeviceInfo.sDeviceSdkVer >= 7) {
            long j = this.mContext.getSharedPreferences(this.mAdaWebview.obtainFrameView().obtainApp().obtainAppId(), 0).getLong("maxSize", 0L);
            this.webSettings.setDomStorageEnabled(true);
            if (j != 0) {
                this.webSettings.setAppCacheMaxSize(j);
            }
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 17) {
            this.webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        this.webSettings.setGeolocationEnabled(true);
        this.webSettings.setGeolocationDatabasePath(this.mAdaWebview.obtainFrameView().obtainApp().obtainAppWebCachePath());
        if (i >= 21) {
            PlatformUtil.invokeMethod("android.webkit.WebSettings", "setMixedContentMode", this.webSettings, new Class[]{Integer.TYPE}, new Object[]{0});
        }
        WebJsEvent webJsEvent = new WebJsEvent(this.mAdaWebview);
        this.mWebJsEvent = webJsEvent;
        setWebChromeClient(webJsEvent);
        WebLoadEvent webLoadEvent = new WebLoadEvent(this.mAdaWebview);
        this.mWebLoadEvent = webLoadEvent;
        webLoadEvent.setPageFinishedCallack(this.mPageFinishedCallack);
        if (!PdrUtil.isEmpty(this.mDcloudwebviewclientListener)) {
            webLoadEvent.setDcloudwebviewclientListener(this.mDcloudwebviewclientListener);
        }
        setWebViewClient(webLoadEvent);
        ReceiveJSValue.addJavascriptInterface(this.mAdaWebview);
        requestFocus();
        setClickable(true);
        removeUnSafeJavascriptInterface();
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void initScalable(boolean z) {
        this.webSettings.supportZoom();
        this.webSettings.setBuiltInZoomControls(z);
        this.webSettings.setSupportZoom(z);
        this.webSettings.setUseWideViewPort(true);
        if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
            return;
        }
        this.webSettings.setLoadWithOverviewMode(true);
    }

    public void initUserAgent(IApp iApp) {
        String str;
        String strObtainConfigProperty = iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_USER_AGENT);
        boolean z = Boolean.parseBoolean(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_CONCATENATE));
        if (Boolean.parseBoolean(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_funSetUA))) {
            z = false;
        }
        boolean z2 = Boolean.parseBoolean(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_H5PLUS));
        if (PdrUtil.isEmpty(AdaWebview.sDefalutUserAgent)) {
            AdaWebview.sDefalutUserAgent = this.webSettings.getUserAgentString();
            HashMap map = new HashMap(1);
            map.put("ua", AdaWebview.sDefalutUserAgent);
            a.a(getContext(), iApp.obtainAppId(), "save", map);
        }
        this.mUserAgent = AdaWebview.sDefalutUserAgent;
        if (!z && !PdrUtil.isEmpty(strObtainConfigProperty)) {
            this.mUserAgent = strObtainConfigProperty;
        } else if (!PdrUtil.isEmpty(strObtainConfigProperty)) {
            this.mUserAgent += Operators.SPACE_STR + strObtainConfigProperty.trim();
        }
        boolean zBooleanValue = Boolean.valueOf(iApp.obtainConfigProperty(AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)).booleanValue();
        if (iApp.obtainStatusBarMgr() == null || !iApp.obtainStatusBarMgr().checkImmersedStatusBar(this.mAdaWebview.getActivity(), zBooleanValue)) {
            str = "";
        } else {
            str = " (Immersed/" + (DeviceInfo.sStatusBarHeight / this.mScale) + ")";
        }
        if (z2 && this.mUserAgent.indexOf(DCWebView.UserAgentExtInfo) < 0) {
            if (BaseInfo.ISAMU && BaseInfo.isBase(getContext())) {
                this.mUserAgent += " Html5Plus/1.0 StreamApp/1.0" + str;
            } else {
                this.mUserAgent += DCWebView.UserAgentExtInfo + str;
            }
        }
        Logger.d("webview", "userAgent=" + this.mUserAgent);
        if (this.mAdaWebview.obtainFrameView().getFrameType() != 6) {
            this.webSettings.setUserAgentString(this.mUserAgent);
        }
        HttpConstants.setUA(this.mUserAgent);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        try {
            if (getParent() != null) {
                float contentHeight = getContentHeight() * this.mScale;
                if (contentHeight > 0.0f) {
                    if ((contentHeight > getHeight() || (this.mAdaWebview.mProgress > 60 && contentHeight >= getHeight())) && !this.isToInvalidate) {
                        this.mAdaWebview.dispatchWebviewStateEvent(6, Integer.valueOf(getContentHeight()));
                        this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_RENDERING, Integer.valueOf(getContentHeight()));
                        this.isToInvalidate = true;
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public boolean isChildSpeciaView(float f, float f2) {
        if (this.mAdaWebview.mFrameView.checkITypeofAble()) {
            return false;
        }
        for (int i = 0; i < getChildCount(); i++) {
            KeyEvent.Callback childAt = getChildAt(i);
            if (childAt instanceof cv) {
                return false;
            }
            if (childAt instanceof INativeView) {
                return true;
            }
            if (childAt instanceof IVideoPlayer) {
                IVideoPlayer iVideoPlayer = (IVideoPlayer) childAt;
                if (!iVideoPlayer.isVideoHandleTouch() || iVideoPlayer.isPointInRect(f, f2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public boolean isDidTouch() {
        return this.didTouch;
    }

    public boolean isReadyForPullUp(int i) {
        int iFloor = ((int) Math.floor(getContentHeight() * this.mScale)) - getHeight();
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = (i >= iFloor || (i >= iFloor - this.mThreshold && jCurrentTimeMillis - this.mLastScrollTimestamp > ((long) this.mThresholdTime))) && this.mLastScrollY < this.mContentHeight;
        this.mLastScrollY = i;
        this.mContentHeight = iFloor;
        long j = jCurrentTimeMillis - this.mLastScrollTimestamp;
        if (j <= 500) {
            this.mLastScrollTimestamp = j;
        }
        return z;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void listenPageFinishTimeout(String str) {
        this.mWebLoadEvent.listenPageFinishTimeout(this, getUrlStr(), str);
    }

    @Override // android.webkit.WebView, io.dcloud.common.adapter.ui.webview.DCWebView
    public void loadUrl(String str) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null || adaWebview.isDisposed()) {
            return;
        }
        if (!str.startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
            this.didTouch = false;
        } else if (Build.VERSION.SDK_INT >= 19) {
            try {
                evaluateJavascript(str, null);
                return;
            } catch (Throwable th) {
                Logger.e("webview", "e.getMessage()==" + th.getMessage());
                super.loadUrl(str);
                return;
            }
        }
        HashMap<String, String> map = this.mUrlHeads.get(str);
        if (map != null) {
            super.loadUrl(str, map);
        } else {
            super.loadUrl(str);
        }
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (((AdaFrameView) this.mAdaWebview.obtainFrameView()).getCircleRefreshView() != null) {
            invalidate();
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection;
        Logger.e("AssistantInput", "onCreateInputConnection 00");
        try {
            inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            try {
                if (!BaseInfo.AuxiliaryInput || inputConnectionOnCreateInputConnection == null) {
                    return inputConnectionOnCreateInputConnection;
                }
                CustomeizedInputConnection customeizedInputConnection = new CustomeizedInputConnection(this.mAdaWebview, inputConnectionOnCreateInputConnection, editorInfo, AdaWebview.sCustomeizedInputConnection);
                try {
                    AdaWebview.sCustomeizedInputConnection = customeizedInputConnection;
                    return customeizedInputConnection;
                } catch (Throwable th) {
                    th = th;
                    inputConnectionOnCreateInputConnection = customeizedInputConnection;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputConnectionOnCreateInputConnection = null;
        }
        th.printStackTrace();
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WebJsEvent webJsEvent = this.mWebJsEvent;
        if (webJsEvent != null) {
            webJsEvent.releaseDefaultVideoPoster();
        }
        if (((AdaFrameView) this.mAdaWebview.obtainFrameView()).getCircleRefreshView() == null || !((AdaFrameView) this.mAdaWebview.obtainFrameView()).getCircleRefreshView().d()) {
            return;
        }
        ((AdaFrameView) this.mAdaWebview.obtainFrameView()).getCircleRefreshView().a();
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(final String str, String str2, String str3, final String str4, long j) {
        Logger.i("webview", "onDownloadStart " + str + "userAgent= " + str2 + "contentDisposition= " + str3 + "mimetype= " + str4 + "contentLength= " + j);
        try {
            Context context = getContext();
            if (DeviceInfo.sDeviceSdkVer <= 8) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                context.startActivity(intent);
                return;
            }
            if (context == null || !(context instanceof Activity)) {
                return;
            }
            final String downloadFilename = PdrUtil.getDownloadFilename(str3, str4, str);
            String str5 = context.getString(R.string.dcloud_common_download_do_file) + downloadFilename;
            if (0 < j) {
                str5 = str5 + "【" + new BigDecimal(j).divide(new BigDecimal(1048576L), 2, 4).floatValue() + "MB】";
            }
            DialogUtil.showAlertDialog((Activity) context, str5, context.getString(R.string.dcloud_common_download), context.getString(R.string.dcloud_common_cancel), new View.OnClickListener() { // from class: io.dcloud.common.adapter.ui.webview.SysWebView.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SysWebView sysWebView = SysWebView.this;
                    sysWebView.downloadFile(sysWebView.getContext(), downloadFilename, SysWebView.this.mAdaWebview.getAppName(), str, str4, null);
                }
            }, null, null, null, false, 0, 80, (int) (((double) context.getResources().getDisplayMetrics().widthPixels) * 0.9d));
        } catch (Exception e2) {
            Logger.w("webview onDownloadStart", e2);
            Logger.e("webview", "browser will download url=" + str);
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(str));
                this.mAdaWebview.getActivity().startActivity(intent2);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!isChildSpeciaView(motionEvent.getX(), motionEvent.getY())) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (actionMasked == 0) {
            this.mLastMotionY = rawY;
            this.mLastMotionX = rawX;
            this.mIsBeingDragged = false;
        } else if (actionMasked == 2) {
            float f = rawX - this.mLastMotionX;
            float f2 = rawY - this.mLastMotionY;
            if (Math.abs(f2) > Math.abs(f) && Math.abs(f2) > 20.0f) {
                motionEvent.setAction(0);
                onTouchEvent(motionEvent);
                this.mIsBeingDragged = true;
            }
        }
        boolean z = this.mIsBeingDragged;
        return z ? z : super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT ? super.onKeyDown(i, keyEvent) : doKeyDownAction(i, keyEvent);
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT ? super.onKeyUp(i, keyEvent) : doKeyUpAction(i, keyEvent);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void onPageStarted() {
        this.isToInvalidate = false;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void onPreloadJSContent(String str) {
        this.mWebLoadEvent.onPreloadJSContent(this, this.mUrl, str);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView, supwisdom.vu.a
    public void onRefresh(int i) {
        this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_PULL_DOWN_EVENT, Integer.valueOf(i));
        this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_PULL_TO_REFRESH, Integer.valueOf(i));
    }

    @Override // android.webkit.WebView, android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if ((i == i3 && i2 == i4) || this.mAdaWebview == null) {
            return;
        }
        if (!AndroidResources.sIMEAlive && isReadyForPullUp(i2)) {
            Logger.d("onPlusScrollBottom", "上拉事件  url=" + this.mAdaWebview.obtainUrl());
            this.mAdaWebview.executeScript(PLUSSCROLLBOTTOM_JS_TEMPLATE);
        }
        JSONObject jSONObject = this.mAdaWebview.mFrameView.obtainFrameOptions().titleNView;
        if (jSONObject != null && jSONObject.has("type") && "transparent".equals(jSONObject.optString("type"))) {
            int i5 = this.mAdaWebview.mFrameView.obtainFrameOptions().coverage;
            if (i5 >= i4 || i5 >= i2) {
                Object titleNView = TitleNViewUtil.getTitleNView(this.mAdaWebview.obtainFrameView().obtainWindowMgr(), this.mAdaWebview.obtainFrameView().obtainWebView(), this.mAdaWebview.obtainFrameView(), TitleNViewUtil.getTitleNViewId(this.mAdaWebview.obtainFrameView()));
                if (titleNView instanceof ITitleNView) {
                    TitleNViewUtil.updateTitleNViewStatus((ITitleNView) titleNView, this.mAdaWebview.obtainFrameView().obtainWebView(), i2, jSONObject, i5);
                }
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.didTouch = true;
        if (Build.VERSION.SDK_INT == 16 && !PdrUtil.isEquals(Build.BRAND, "samsung") && motionEvent.getAction() == 0) {
            int scrollY = getScrollY();
            scrollTo(getScrollX(), scrollY + 1);
            scrollTo(getScrollX(), scrollY);
        }
        if (motionEvent.getAction() == 0) {
            this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_WEBVIEW_ONTOUCH_START, Integer.valueOf(getContentHeight()));
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void onUpdatePlusData(String str) {
        this.mWebLoadEvent.onUpdatePlusData(this, getUrlStr(), str);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void putHeads(String str, HashMap<String, String> map) {
        this.mUrlHeads.put(str, map);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void removeAllCookie() {
        CookieManager cookieManager = this.cm;
        if (cookieManager != null) {
            cookieManager.removeAllCookie();
        }
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        try {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = getChildAt(childCount);
                if (childAt != ((AdaFrameView) this.mAdaWebview.obtainFrameView()).getCircleRefreshView()) {
                    removeView(childAt);
                }
            }
        } catch (Exception unused) {
            super.removeAllViews();
            if (((AdaFrameView) this.mAdaWebview.obtainFrameView()).getCircleRefreshView() != null) {
                addView((View) ((AdaFrameView) this.mAdaWebview.obtainFrameView()).getCircleRefreshView(), -1, -1);
            }
        }
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void removeSessionCookie() {
        CookieManager cookieManager = this.cm;
        if (cookieManager != null) {
            cookieManager.removeSessionCookie();
        }
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setBlockNetworkImage(boolean z) {
        this.webSettings.setBlockNetworkImage(z);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setCookie(String str, String str2) {
        CookieManager cookieManager = this.cm;
        if (cookieManager != null) {
            cookieManager.setAcceptCookie(true);
            this.cm.setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setDcloudwebviewclientListener(IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        setDcloudwebviewclientListener(iDCloudWebviewClientListener);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setDidTouch(boolean z) {
        this.didTouch = z;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setPageTitle(String str) {
        this.mPageTitle = str;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setUrlStr(String str) {
        this.mUrl = str;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setUserAgentString(String str) {
        this.webSettings.setUserAgentString(str);
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void setWebViewCacheMode(String str) {
        if (str.equals("default")) {
            this.mCacheMode = -1;
        } else if (str.equals("cacheElseNetwork")) {
            this.mCacheMode = 1;
        } else if (str.equals("noCache")) {
            this.mCacheMode = 2;
        } else if (str.equals("cacheOnly")) {
            this.mCacheMode = 3;
        }
        this.webSettings.setCacheMode(this.mCacheMode);
    }

    @Override // android.webkit.WebView
    public void setWebViewClient(WebViewClient webViewClient) {
        if ((webViewClient instanceof WebLoadEvent) || webViewClient == null) {
            super.setWebViewClient(webViewClient);
        }
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return Build.VERSION.SDK_INT >= 11 ? super.startActionMode(new CustomizedSelectActionModeCallback(callback)) : super.startActionMode(callback);
    }

    @Override // android.view.View
    public String toString() {
        String str;
        String str2 = this.mUrl;
        if (str2 == null || (str = this.mBaseUrl) == null) {
            return super.toString();
        }
        int iIndexOf = str2.indexOf(str);
        String strSubstring = this.mUrl;
        if (iIndexOf >= 0) {
            strSubstring = strSubstring.substring(this.mBaseUrl.length());
        }
        return "<url=" + strSubstring + ">;<hashcode=" + hashCode() + Operators.G;
    }

    @Override // io.dcloud.common.adapter.ui.webview.DCWebView
    public void webReload(boolean z) {
        if (!z) {
            this.webSettings.setCacheMode(this.mCacheMode);
            return;
        }
        WebLoadEvent webLoadEvent = this.mWebLoadEvent;
        if (webLoadEvent != null) {
            webLoadEvent.reset();
        }
        this.webSettings.setCacheMode(2);
    }

    public SysWebView(Context context, AdaWebview adaWebview, IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        super(context);
        this.mUserAgent = null;
        this.mAdaWebview = null;
        this.mWebLoadEvent = null;
        this.mWebJsEvent = null;
        this.mUrl = null;
        this.mScale = 0.0f;
        this.mContext = null;
        this.mBaseUrl = null;
        this.webSettings = getSettings();
        this.cm = null;
        this.mLastScrollY = 0;
        this.mContentHeight = 0;
        this.mThreshold = 2;
        this.mThresholdTime = 15;
        this.mLastScrollTimestamp = 0L;
        this.mPageTitle = null;
        this.mDeafaltOverScrollMode = 0;
        this.mCacheMode = -1;
        this.didTouch = false;
        this.isToInvalidate = false;
        this.mUrlHeads = new HashMap<>();
        this.mEventY = 0;
        this.mEventX = 0;
        this.mTouchSlop = -1;
        this.mIsBeingDragged = true;
        this.mScale = getContext().getResources().getDisplayMetrics().density;
        Logger.d("WebViewImpl");
        this.mContext = context.getApplicationContext();
        BaseInfo.s_Webview_Count++;
        this.mAdaWebview = adaWebview;
        this.mDcloudwebviewclientListener = iDCloudWebviewClientListener;
    }

    public SysWebView(Context context, AdaWebview adaWebview, OnPageFinishedCallack onPageFinishedCallack) {
        super(context);
        this.mUserAgent = null;
        this.mAdaWebview = null;
        this.mWebLoadEvent = null;
        this.mWebJsEvent = null;
        this.mUrl = null;
        this.mScale = 0.0f;
        this.mContext = null;
        this.mBaseUrl = null;
        this.webSettings = getSettings();
        this.cm = null;
        this.mLastScrollY = 0;
        this.mContentHeight = 0;
        this.mThreshold = 2;
        this.mThresholdTime = 15;
        this.mLastScrollTimestamp = 0L;
        this.mPageTitle = null;
        this.mDeafaltOverScrollMode = 0;
        this.mCacheMode = -1;
        this.didTouch = false;
        this.isToInvalidate = false;
        this.mUrlHeads = new HashMap<>();
        this.mEventY = 0;
        this.mEventX = 0;
        this.mTouchSlop = -1;
        this.mIsBeingDragged = true;
        Logger.d("WebViewImpl");
        this.mContext = context.getApplicationContext();
        this.mAdaWebview = adaWebview;
        this.mPageFinishedCallack = onPageFinishedCallack;
        this.mScale = getContext().getResources().getDisplayMetrics().density;
    }
}
