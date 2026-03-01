package io.dcloud.common.adapter.ui;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.CookieManager;
import android.webkit.WebView;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.baidu.speech.utils.analysis.Analysis;
import com.heytap.mcssdk.d;
import com.huawei.hms.framework.common.ContainerUtils;
import com.igexin.push.core.b;
import com.umeng.commonsdk.statistics.idtracking.g;
import dc.squareup.cookie.CookieCenter;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IJsInterface;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IUniNView;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.EventActionInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.language.LanguageUtil;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AdaUniWebView extends AdaWebview {
    public MessageHandler.IMessages iMessages;
    public boolean isUniService;
    public IApp mApp;
    public String mFullUrl;
    public float mScale;
    public IUniNView mUniNView;
    public String mUrl;
    public ViewGroup mViewImpl;
    public AbsMgr mWinMgr;

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
    public AdaUniWebView(Context context, IApp iApp, AdaFrameView adaFrameView, String str, String str2, JSONObject jSONObject, boolean z) {
        super(context);
        this.mWinMgr = null;
        this.mScale = 3.0f;
        this.isUniService = false;
        this.iMessages = new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaUniWebView.1
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                Object[] objArr = (Object[]) obj;
                AdaUniWebView.this.exec(String.valueOf(objArr[0]), String.valueOf(objArr[1]), (JSONArray) objArr[2]);
            }
        };
        str = str == null ? "" : str;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("js", str);
            jSONObject2.put("data", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.isUniService = z;
        this.mApp = iApp;
        this.mFrameView = adaFrameView;
        this.mAppid = iApp.obtainAppId();
        initSitemapState();
        this.mUrl = str;
        this.mFullUrl = TextUtils.isEmpty(str) ? this.mUrl : this.mApp.convert2WebviewFullPath(null, this.mUrl);
        this.mWinMgr = adaFrameView.obtainWindowMgr();
        this.mScale = context.getResources().getDisplayMetrics().density;
        AdaWebViewParent adaWebViewParent = new AdaWebViewParent(context, false);
        this.mWebViewParent = adaWebViewParent;
        IUniNView iUniNView = (IUniNView) this.mWinMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iApp, "weex,io.dcloud.feature.weex.WeexFeature", "createUniNView", new Object[]{this, adaWebViewParent.obtainMainViewGroup(), jSONObject2, str2}});
        this.mUniNView = iUniNView;
        if (iUniNView != null) {
            ViewGroup viewGroupObtainMainView = iUniNView.obtainMainView();
            this.mViewImpl = viewGroupObtainMainView;
            setMainView(viewGroupObtainMainView);
        }
        this.mWebViewParent.fillsWithWebview(this);
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void addJsInterface(String str, IJsInterface iJsInterface) {
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview
    public void addJsInterface(String str, Object obj) {
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void addJsInterface(String str, String str2) {
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public boolean canGoBack() {
        return false;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public boolean canGoForward() {
        return false;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        this.mUniNView = null;
        this.mViewImpl = null;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void endWebViewEvent(String str) {
        if (!PdrUtil.isEquals(str, AbsoluteConst.PULL_DOWN_REFRESH)) {
            super.endWebViewEvent(str);
            return;
        }
        IUniNView iUniNView = this.mUniNView;
        if (iUniNView != null) {
            iUniNView.endPullToRefresh();
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void evalJS(String str) {
        evalJSToUniNative(str);
    }

    public void evalJSToUniNative(String str) {
        if (this.mUniNView == null || PdrUtil.isEmpty(str)) {
            return;
        }
        HashMap map = new HashMap();
        map.put("data", str);
        this.mUniNView.fireGlobalEvent("nativeToUniJs", map);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public String exec(String str, String str2, JSONArray jSONArray) {
        if (getContext() == null) {
            return "";
        }
        try {
            if ("syncExecMethod".equalsIgnoreCase(str2)) {
                str2 = AbsoluteConst.UNI_SYNC_EXEC_METHOD;
            }
            return String.valueOf(this.mWinMgr.processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{this, str, str2, jSONArray}));
        } catch (Exception e2) {
            Logger.w("JsInterfaceImpl.exec pApiFeatureName=" + str + ";pActionName=" + str2 + ";pArgs=" + String.valueOf(jSONArray), e2);
            return null;
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void executeScript(String str) {
        evalJS(str);
    }

    public void fireEvent(EventActionInfo eventActionInfo) {
        if (this.mUniNView == null || eventActionInfo == null) {
            return;
        }
        if (!TextUtils.isEmpty(eventActionInfo.getEvalJs())) {
            evalJSToUniNative(eventActionInfo.getEvalJs());
        }
        this.mUniNView.fireGlobalEvent(eventActionInfo.getEventAction(), eventActionInfo.getParams());
    }

    public Object getConfigInfo() {
        if (TextUtils.isEmpty(DeviceInfo.sIMEI)) {
            DeviceInfo.initGsmCdmaCell();
        }
        HashMap map = new HashMap();
        map.put("__HtMl_Id__", getWebviewUUID());
        if (PermissionControler.checkPermission(this.mApp.obtainAppId(), IFeature.F_DEVICE.toLowerCase()) || !this.mApp.manifestBeParsed()) {
            HashMap map2 = new HashMap();
            map2.put(g.f5442a, DeviceInfo.sIMEI);
            map2.put("imsi", DeviceInfo.sIMSI);
            map2.put("model", DeviceInfo.sModel);
            map2.put("vendor", DeviceInfo.sVendor);
            map2.put("uuid", DeviceInfo.sIMEI);
            map.put(ConstantHelper.LOG_DE, map2);
            HashMap map3 = new HashMap();
            map3.put("language", LanguageUtil.getDeviceDefLocalLanguage());
            map3.put("version", Build.VERSION.RELEASE);
            map3.put("name", "Android");
            map3.put("vendor", "Google");
            map.put("os", map3);
            int i = this.mApp.getInt(2);
            int i2 = this.mApp.getInt(0);
            int i3 = this.mApp.getInt(1);
            HashMap map4 = new HashMap();
            float scale = getScale();
            map4.put("resolutionHeight", Integer.valueOf((int) (i / scale)));
            int i4 = (int) (i2 / scale);
            map4.put("resolutionWidth", Integer.valueOf(i4));
            map4.put("scale", Float.valueOf(scale));
            map4.put("dpiX", Float.valueOf(DeviceInfo.dpiX));
            map4.put("dpiY", Float.valueOf(DeviceInfo.dpiY));
            map4.put("height", Integer.valueOf(i));
            map4.put("width", Integer.valueOf(i2));
            map.put(Analysis.KEY_SCREEN, map4);
            HashMap map5 = new HashMap();
            map5.put("resolutionHeight", Integer.valueOf((int) (i3 / scale)));
            map5.put("resolutionWidth", Integer.valueOf(i4));
            map.put("display", map5);
        }
        if (PermissionControler.checkPermission(this.mApp.obtainAppId(), IFeature.F_RUNTIME) || !this.mApp.manifestBeParsed()) {
            String strObtainConfigProperty = this.mApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_LOADED_TIME);
            HashMap map6 = new HashMap();
            map6.put("innerVersion", "1.9.9.81096");
            map6.put("appid", this.mApp.obtainAppId());
            map6.put("launchLoadedTime", strObtainConfigProperty);
            if (BaseInfo.ISAMU) {
                map6.put("version", this.mApp.obtainAppVersionName());
            } else {
                map6.put("version", AndroidResources.mApplicationInfo.versionName);
            }
            map6.put("arguments", this.mApp.obtainRuntimeArgs(false));
            map6.put("launcher", BaseInfo.getLauncherData(this.mApp.obtainAppId()));
            map6.put("channel", BaseInfo.getAnalysisChannel());
            map6.put("startupTime", String.valueOf(BaseInfo.getStartupTimeData(this.mApp.obtainAppId())));
            map6.put("processId", Long.valueOf(BaseInfo.sProcessId));
            map6.put("uniVersion", BaseInfo.uniVersionV3);
            map6.put(d.q, Integer.valueOf(AndroidResources.versionCode));
            String bundleData = SP.getBundleData(this.mApp.getActivity(), "pdr", this.mApp.obtainAppId() + AbsoluteConst.LAUNCHTYPE);
            if (TextUtils.isEmpty(bundleData)) {
                bundleData = "default";
            }
            map6.put("origin", bundleData);
            map.put(b.Z, map6);
        }
        HashMap map7 = new HashMap();
        map7.put("__isUniPush__", Boolean.valueOf(AndroidResources.getMetaValue("DCLOUD_UNIPUSH")));
        map.put("push", map7);
        return map;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public String getCookie(String str) {
        return WebViewFactory.isIsOtherInitSuccess() ? CookieCenter.getCookies(str) : CookieManager.getInstance().getCookie(str);
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public String getOriginalUrl() {
        return this.mUrl;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public float getScale() {
        return this.mScale;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public float getScaleOfOpenerWebview() {
        return 0.0f;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public String getTitle() {
        return "";
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public boolean isUniService() {
        return this.isUniService;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public boolean isUniWebView() {
        return true;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void loadContentData(String str, String str2, String str3, String str4) {
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void loadUrl(String str) {
        if (PdrUtil.isNetPath(str) || PdrUtil.isFilePath(str)) {
            return;
        }
        evalJS(str);
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public IApp obtainApp() {
        return this.mApp;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public IFrameView obtainFrameView() {
        return this.mFrameView;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public String obtainFullUrl() {
        return this.mFullUrl;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public String obtainPageTitle() {
        return "";
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public String obtainUrl() {
        return this.mUrl;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public WebView obtainWebview() {
        return null;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public ViewGroup obtainWindowView() {
        return this.mViewImpl;
    }

    public String prompt(String str, String str2) {
        if (str2 != null && str2.length() > 3 && str2.substring(0, 4).equals("pdr:")) {
            try {
                JSONArray jSONArray = new JSONArray(str2.substring(4));
                String string = jSONArray.getString(0);
                String string2 = jSONArray.getString(1);
                boolean z = jSONArray.getBoolean(2);
                JSONArray jSONArrayCreateJSONArray = JSONUtil.createJSONArray(str);
                if (!z) {
                    return exec(string, string2, jSONArrayCreateJSONArray);
                }
                MessageHandler.sendMessage(this.iMessages, new Object[]{string, string2, jSONArrayCreateJSONArray});
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void reload() {
        IUniNView iUniNView = this.mUniNView;
        if (iUniNView != null) {
            iUniNView.reload();
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void reload(String str) {
    }

    public void setNVuePath(String str) {
        this.mUrl = str;
        this.mFullUrl = TextUtils.isEmpty(str) ? this.mUrl : this.mApp.convert2WebviewFullPath(null, this.mUrl);
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void setOriginalUrl(String str) {
        this.mUrl = str;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void setWebViewEvent(String str, Object obj) {
        if (PdrUtil.isEquals(str, AbsoluteConst.PULL_DOWN_REFRESH)) {
            JSONObject jSONObject = (JSONObject) obj;
            IUniNView iUniNView = this.mUniNView;
            if (iUniNView != null) {
                iUniNView.initRefresh(jSONObject);
                return;
            }
            return;
        }
        if (!PdrUtil.isEquals(str, AbsoluteConst.PULL_REFRESH_BEGIN)) {
            super.setWebViewEvent(str, obj);
            return;
        }
        IUniNView iUniNView2 = this.mUniNView;
        if (iUniNView2 != null) {
            iUniNView2.beginPullRefresh();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void show(Animation animation) {
    }

    public void titleNViewRefresh() {
        IUniNView iUniNView = this.mUniNView;
        if (iUniNView != null) {
            iUniNView.titleNViewRefresh();
        }
    }

    public void updateScreenAndDisplay() {
        if (obtainApp() == null || this.mUniNView == null) {
            return;
        }
        int i = obtainApp().getInt(2);
        int i2 = obtainApp().getInt(0);
        int i3 = obtainApp().getInt(1);
        HashMap map = new HashMap();
        float scale = getScale();
        map.put("resolutionHeight", Double.valueOf(Math.ceil(i / scale)));
        double d = i2 / scale;
        map.put("resolutionWidth", Double.valueOf(Math.ceil(d)));
        map.put("dpiX", Float.valueOf(DeviceInfo.dpiX));
        map.put("dpiY", Float.valueOf(DeviceInfo.dpiY));
        HashMap map2 = new HashMap();
        map2.put("resolutionHeight", Double.valueOf(Math.ceil(i3 / scale)));
        map2.put("resolutionWidth", Double.valueOf(Math.ceil(d)));
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            sb.append("plus.screen.");
            sb.append(str);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(map.get(str));
            sb.append(";");
        }
        for (String str2 : map2.keySet()) {
            sb.append("plus.display.");
            sb.append(str2);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(map2.get(str2));
            sb.append(";");
        }
        evalJSToUniNative(sb.toString());
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void evalJS(String str, ReceiveJSValue.ReceiveJSValueCallback receiveJSValueCallback) {
        evalJS(str);
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void reload(boolean z) {
        reload();
    }
}
