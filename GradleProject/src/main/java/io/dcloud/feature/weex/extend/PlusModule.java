package io.dcloud.feature.weex.extend;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.R;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.SimpleJSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.DCWXInput;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaUniWebView;
import io.dcloud.common.adapter.ui.webview.WebResUtil;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.EventActionInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.weex.WXBaseWrapper;
import io.dcloud.feature.weex.WXServiceWrapper;
import io.dcloud.feature.weex.WeexInstanceMgr;
import io.dcloud.weex.WXDotDataUtil;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import supwisdom.ne;

/* JADX INFO: loaded from: classes3.dex */
public class PlusModule extends WXModule {
    public String EVENTS_DOCUMENT_EXECUTE = "javascript:!function(){(window.__html5plus__&&__html5plus__.isReady?__html5plus__:navigator.plus&&navigator.plus.isReady?navigator.plus:window.plus)||window.__load__plus__&&window.__load__plus__();var _=document.createEvent(\"HTMLEvents\");_.initEvent(\"%s\",!1,!0),_.targetId=\"%s\",_.originId=\"%s\",_.data=%s,document.dispatchEvent(_)}();";
    public ArrayList<JsData> chs = new ArrayList<>();

    public class JsData {
        public String data;
        public String value;

        public JsData(String str, String str2) {
            this.data = str;
            this.value = str2;
        }
    }

    private void runChData() {
        if (this.chs.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (JsData jsData : this.chs) {
            exec(jsData.data, jsData.value);
            arrayList.add(jsData);
        }
        this.chs.removeAll(arrayList);
    }

    @JSMethod(uiThread = true)
    public void evalJSFiles(String str, final JSCallback jSCallback) {
        final HashMap map = new HashMap();
        if (str == null) {
            if (jSCallback != null) {
                map.put("type", -1);
                map.put("msg", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_msg_param_empty));
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        final JSONArray array = JSON.parseArray(str);
        if (array != null) {
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.weex.extend.PlusModule.1
                @Override // java.lang.Runnable
                public void run() {
                    IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(PlusModule.this.mWXSDKInstance);
                    if (iWebviewFindWebview == null) {
                        if (jSCallback != null) {
                            map.put("type", -1);
                            map.put("msg", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_msg_page_destroyed));
                            jSCallback.invoke(map);
                            return;
                        }
                        return;
                    }
                    byte bObtainRunningAppMode = iWebviewFindWebview.obtainApp().obtainRunningAppMode();
                    String str2 = "";
                    for (int i = 0; i < array.size(); i++) {
                        String string = array.getString(i);
                        File file = new File(string);
                        InputStream fileInputStream = null;
                        if (file.exists()) {
                            try {
                                fileInputStream = new FileInputStream(file);
                            } catch (FileNotFoundException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            if (string.startsWith("/storage") || bObtainRunningAppMode != 1) {
                                string = iWebviewFindWebview.obtainApp().convert2WebviewFullPath(iWebviewFindWebview.obtainFullUrl(), string);
                            } else {
                                string = iWebviewFindWebview.obtainApp().convert2AbsFullPath(iWebviewFindWebview.obtainFullUrl(), string);
                                if (string.startsWith("/")) {
                                    string = string.substring(1, string.length());
                                }
                            }
                            fileInputStream = WebResUtil.getEncryptionInputStream(string, iWebviewFindWebview.obtainApp());
                        }
                        if (fileInputStream != null) {
                            try {
                                String str3 = new String(IOUtil.toString(fileInputStream));
                                if (!TextUtils.isEmpty(str3)) {
                                    str2 = str2 + str3;
                                }
                            } catch (Exception unused) {
                            }
                        } else if (jSCallback != null) {
                            map.put("type", -1);
                            map.put("msg", string + DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_msg_cannot_find_file_by_path));
                            jSCallback.invoke(map);
                        }
                    }
                    if (TextUtils.isEmpty(str2)) {
                        return;
                    }
                    WXBridgeManager.getInstance().syncExecJsOnInstanceWithResult(PlusModule.this.mWXSDKInstance.getInstanceId(), str2, -1);
                    if (jSCallback != null) {
                        map.put("type", 1);
                        jSCallback.invoke(map);
                    }
                }
            }, true);
        } else if (jSCallback != null) {
            map.put("type", -1);
            map.put("msg", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_msg_param_invalid));
            jSCallback.invoke(map);
        }
    }

    @JSMethod(uiThread = true)
    public void exec(String str, String str2) {
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        if (wXSDKInstance == null || !wXSDKInstance.isDestroy()) {
            IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
            if (iWebviewFindWebview instanceof AdaUniWebView) {
                ((AdaUniWebView) iWebviewFindWebview).prompt(str, str2);
            }
        }
    }

    @JSMethod(uiThread = false)
    public String execSync(String str, String str2) {
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        if (wXSDKInstance != null && wXSDKInstance.isDestroy()) {
            return "";
        }
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        return iWebviewFindWebview instanceof AdaUniWebView ? ((AdaUniWebView) iWebviewFindWebview).prompt(str, str2) : "";
    }

    @JSMethod(uiThread = false)
    public int getAppState() {
        IActivityHandler iActivityHandler;
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview == null || (iActivityHandler = DCloudAdapterUtil.getIActivityHandler(iWebviewFindWebview.getActivity())) == null) {
            return 0;
        }
        return iActivityHandler.getActivityState();
    }

    @JSMethod(uiThread = false)
    public Object getConfigInfo() {
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview instanceof AdaUniWebView) {
            return ((AdaUniWebView) iWebviewFindWebview).getConfigInfo();
        }
        return null;
    }

    @JSMethod(uiThread = false)
    public JSONObject getDotData() {
        JSONObject deviceInfo = WXDotDataUtil.getDeviceInfo();
        if (BaseInfo.SyncDebug) {
            deviceInfo.put("maxMemory", (Object) ((Runtime.getRuntime().maxMemory() / 1048576) + "M"));
            deviceInfo.put("totalMemory", (Object) ((Runtime.getRuntime().totalMemory() / 1048576) + "M"));
            deviceInfo.put("appRuningTitme", (Object) Long.valueOf(BaseInfo.splashCloseTime - BaseInfo.startTime));
        }
        return deviceInfo;
    }

    @JSMethod
    public void getHostInfo(JSCallback jSCallback) {
        JSONObject object;
        if (jSCallback == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview == null || iWebviewFindWebview.obtainApp() == null) {
            jSCallback.invoke(jSONObject);
            return;
        }
        String strObtainAppInfo = iWebviewFindWebview.obtainApp().obtainAppInfo();
        if (PdrUtil.isEmpty(strObtainAppInfo)) {
            jSCallback.invoke(jSONObject);
            return;
        }
        if (PdrUtil.isUniMPHostForUniApp()) {
            if (!SDK.isUniMPSdk) {
                JSONObject object2 = JSON.parseObject(strObtainAppInfo);
                if (object2 != null) {
                    jSONObject.putAll(object2);
                }
            } else if (!PdrUtil.isEmpty(SDK.mHostInfo) && (object = JSON.parseObject(SDK.mHostInfo)) != null) {
                jSONObject.putAll(object);
            }
        }
        jSONObject.put("nativeName", (Object) AndroidResources.appName);
        jSONObject.put("nativeAppid", (Object) AndroidResources.packageName);
        jSONObject.put("nativeVersionName", (Object) AndroidResources.versionName);
        jSONObject.put("nativeVersionCode", (Object) Integer.valueOf(AndroidResources.versionCode));
        jSCallback.invoke(jSONObject);
    }

    @JSMethod(uiThread = false)
    public String getLanguage() {
        WXSDKInstance wXSDKInstance;
        return (Build.VERSION.SDK_INT < 21 || (wXSDKInstance = this.mWXSDKInstance) == null || wXSDKInstance.getContext() == null) ? "" : LanguageUtil.getCurrentLocaleLanguage(this.mWXSDKInstance.getContext());
    }

    @JSMethod(uiThread = false)
    public Object getRedirectInfo() {
        JSONObject object;
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview == null || !Boolean.valueOf(iWebviewFindWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.UNI_RESTART_TO_DIRECT)).booleanValue() || (object = JSON.parseObject(iWebviewFindWebview.obtainApp().obtainConfigProperty(AbsoluteConst.JSON_KEY_DEBUG_REFRESH))) == null || !object.containsKey("arguments")) {
            return null;
        }
        try {
            return JSON.parseObject(object.getString("arguments"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JSMethod(uiThread = false)
    public String getValue(String str) {
        return ((DCWXInput) WXSDKManager.getInstance().getWXRenderManager().getWXComponent(this.mWXSDKInstance.getInstanceId(), str)).getValue();
    }

    @JSMethod(uiThread = true)
    public void log(String str) {
        Logger.d("console", "[LOG] " + str);
    }

    @JSMethod
    public void postMessage(String str, String str2) {
        WXServiceWrapper wXServiceWrapperFindWXServiceWrapper;
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        String str3 = this.EVENTS_DOCUMENT_EXECUTE;
        Object[] objArr = new Object[4];
        objArr[0] = "uniNViewMessage";
        objArr[1] = "";
        objArr[2] = iWebviewFindWebview != null ? iWebviewFindWebview.obtainFrameId() : "";
        objArr[3] = str;
        String str4 = StringUtil.format(str3, objArr);
        if (iWebviewFindWebview == null && (wXServiceWrapperFindWXServiceWrapper = WeexInstanceMgr.self().findWXServiceWrapper(this.mWXSDKInstance)) != null && !BaseInfo.isWeexUniJs(wXServiceWrapperFindWXServiceWrapper.obtanApp())) {
            wXServiceWrapperFindWXServiceWrapper.findWebViewToLoadUrL(str4, str2);
            return;
        }
        if (iWebviewFindWebview != null) {
            IWebview iWebviewFindWebview2 = PdrUtil.isEmpty(str2) ? iWebviewFindWebview : WeexInstanceMgr.self().findWebview(iWebviewFindWebview, iWebviewFindWebview.obtainApp(), iWebviewFindWebview.obtainApp().obtainAppId(), str2);
            if (iWebviewFindWebview2 != null) {
                if (!(iWebviewFindWebview2 instanceof AdaUniWebView)) {
                    iWebviewFindWebview2.loadUrl(str4);
                    return;
                }
                HashMap map = new HashMap();
                map.put("targetId", str2);
                map.put("originId", iWebviewFindWebview != null ? iWebviewFindWebview.obtainFrameId() : "");
                try {
                    map.put("data", JSON.parseObject(str));
                } catch (Exception unused) {
                    map.put("data", str);
                }
                ((AdaUniWebView) iWebviewFindWebview2).fireEvent(new EventActionInfo("uniNViewMessage", map));
            }
        }
    }

    @JSMethod
    public void preloadReady(String str) {
        ICallBack preUniMPCallBack = WeexInstanceMgr.self().getPreUniMPCallBack(str);
        if (preUniMPCallBack != null) {
            preUniMPCallBack.onCallBack(1, str);
        }
    }

    @JSMethod(uiThread = false)
    public JSONObject pushDebugData(Object obj) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", obj);
        return jSONObject;
    }

    @JSMethod(uiThread = true)
    public void sendNativeEvent(String str, Object obj, JSCallback jSCallback) {
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview == null || iWebviewFindWebview.getActivity() == null || !(iWebviewFindWebview.getActivity() instanceof IActivityHandler)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("event", str);
        bundle.putString("dataType", "String");
        if (obj instanceof String) {
            bundle.putString("data", String.valueOf(obj));
        } else if (obj instanceof JSON) {
            bundle.putString("data", ((JSON) obj).toJSONString());
            bundle.putString("dataType", "JSON");
        }
        if (jSCallback instanceof SimpleJSCallback) {
            bundle.putString("instanceId", this.mWXSDKInstance.getInstanceId());
            bundle.putString(SDK.UNIMP_EVENT_CALLBACKID, ((SimpleJSCallback) jSCallback).getCallbackId());
        }
        ((IActivityHandler) iWebviewFindWebview.getActivity()).callBack(SDK.UNIMP_JS_TO_NATIVE, bundle);
    }

    @JSMethod(uiThread = false)
    public void setDefaultFontSize(String str) {
        int iIntValue = Integer.valueOf(str).intValue();
        if (iIntValue > 0) {
            this.mWXSDKInstance.setDefaultFontSize(iIntValue);
        }
    }

    @JSMethod(uiThread = false)
    public void setLanguage(String str) {
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        if (wXSDKInstance == null || wXSDKInstance.getContext() == null || SDK.isUniMPSDK() || this.mWXSDKInstance.isDestroy() || Build.VERSION.SDK_INT < 21) {
            return;
        }
        if ("auto".equalsIgnoreCase(str)) {
            str = "";
        }
        LanguageUtil.updateLanguage(this.mWXSDKInstance.getContext(), str);
        ne.a(this.mWXSDKInstance.getContext()).a(new Intent(LanguageUtil.LanguageBroadCastIntent));
    }

    @JSMethod(uiThread = true)
    public void uniReady() {
        WXBaseWrapper wXBaseWrapperFindWXBaseWrapper = WeexInstanceMgr.self().findWXBaseWrapper(this.mWXSDKInstance);
        if (wXBaseWrapperFindWXBaseWrapper != null) {
            wXBaseWrapperFindWXBaseWrapper.onReady();
        }
    }
}
