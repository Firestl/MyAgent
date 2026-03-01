package io.dcloud.h;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.BaseFeature;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IBoot;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.DataUtil;
import io.dcloud.common.util.ErrorDialogUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class b extends AbsMgr implements IMgr.FeatureEvent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, HashMap<String, String>> f6702a;
    public HashMap<String, String> b;
    public HashMap<String, IFeature> c;
    public HashMap<String, String> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashMap<String, IBoot> f6703e;
    public String f;
    public ArrayList<String> g;
    public HashMap<String, String> h;
    public c i;

    public b(ICore iCore) {
        super(iCore, "featuremgr", IMgr.MgrType.FeatureMgr);
        this.f6702a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f6703e = null;
        this.f = null;
        this.g = new ArrayList<>();
        this.h = new HashMap<>();
        this.i = null;
        b();
        if (c()) {
            return;
        }
        c();
    }

    private String a(IApp iApp, IFrameView iFrameView) {
        String strA = this.h.get(iApp.obtainAppId() + "_" + iApp.obtainAppVersionName());
        if (strA == null) {
            strA = a(iApp, iFrameView.obtainWebView());
            if (Build.VERSION.SDK_INT > 19) {
                this.h.put(iApp.obtainAppId() + "_" + iApp.obtainAppVersionName(), strA);
            }
        }
        return strA;
    }

    private String b(IApp iApp) {
        String metaValue = AndroidResources.getMetaValue("DCLOUD_UNIPUSH");
        return (PdrUtil.isEmpty(metaValue) || !Boolean.valueOf(metaValue).booleanValue()) ? "" : "window.__isUniPush__ = true;";
    }

    private void c(String str) {
        if (this.c.get(str) == null) {
            try {
                IFeature iFeature = (IFeature) Class.forName(str).newInstance();
                this.c.put(str, iFeature);
                iFeature.init(this, str);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            }
        }
    }

    public HashMap<String, IBoot> d() {
        IBoot iBoot;
        InstantiationException e2;
        IllegalAccessException e3;
        ClassNotFoundException e4;
        ClassCastException e5;
        HashMap<String, IBoot> map = this.f6703e;
        HashMap<String, String> map2 = this.d;
        PdrUtil.loadProperties2HashMap(map2, this.b, this.f6702a, BaseInfo.s_properties);
        Set<String> setKeySet = map2.keySet();
        if (setKeySet != null) {
            map = new HashMap<>(2);
            this.f6703e = map;
            for (String str : setKeySet) {
                IBoot iBootB = a.b(this, str);
                if (iBootB == null) {
                    try {
                        iBoot = (IBoot) Class.forName(map2.get(str)).newInstance();
                    } catch (ClassCastException e6) {
                        iBoot = iBootB;
                        e5 = e6;
                    } catch (ClassNotFoundException e7) {
                        iBoot = iBootB;
                        e4 = e7;
                    } catch (IllegalAccessException e8) {
                        iBoot = iBootB;
                        e3 = e8;
                    } catch (InstantiationException e9) {
                        iBoot = iBootB;
                        e2 = e9;
                    }
                    try {
                        if (iBoot instanceof BaseFeature) {
                            ((BaseFeature) iBoot).init(this, str);
                        }
                    } catch (ClassCastException e10) {
                        e5 = e10;
                        Logger.e(Logger.MAIN_TAG, "FeatureMgr.loadBootOptions " + e5.getMessage());
                    } catch (ClassNotFoundException e11) {
                        e4 = e11;
                        Logger.e(Logger.MAIN_TAG, "FeatureMgr.loadBootOptions " + e4.getMessage());
                    } catch (IllegalAccessException e12) {
                        e3 = e12;
                        Logger.d(Logger.MAIN_TAG, "FeatureMgr.loadBootOptions " + e3.getMessage());
                    } catch (InstantiationException e13) {
                        e2 = e13;
                        Logger.d(Logger.MAIN_TAG, "FeatureMgr.loadBootOptions " + e2.getMessage());
                    }
                    iBootB = iBoot;
                }
                if (iBootB != null) {
                    map.put(str, iBootB);
                }
            }
        }
        HashMap<String, IBoot> mapA = a(map);
        a(this.b.values().iterator());
        Iterator<HashMap<String, String>> it = this.f6702a.values().iterator();
        while (it.hasNext()) {
            a(it.next().values().iterator());
        }
        return mapA;
    }

    @Override // io.dcloud.common.DHInterface.AbsMgr
    public void dispose() {
        HashMap<String, IFeature> map = this.c;
        if (map != null) {
            Collection<IFeature> collectionValues = map.values();
            if (collectionValues != null) {
                Iterator<IFeature> it = collectionValues.iterator();
                while (it.hasNext()) {
                    it.next().dispose(null);
                }
            }
            this.c.clear();
            this.c = null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IMgr
    public Object processEvent(IMgr.MgrType mgrType, int i, Object obj) {
        Object objDoForFeature;
        try {
        } catch (Throwable th) {
            Logger.w("FeatureMgr.processEvent", th);
            return null;
        }
        if (checkMgrId(mgrType)) {
            switch (i) {
                case 0:
                    this.c = new HashMap<>(1);
                    this.b = new HashMap<>(1);
                    this.f6702a = new HashMap<>(1);
                    this.f6703e = new HashMap<>();
                    this.d = new HashMap<>(1);
                    break;
                case 1:
                    Object[] objArr = (Object[]) obj;
                    objDoForFeature = !AbsoluteConst.UNI_SYNC_EXEC_METHOD.equalsIgnoreCase(String.valueOf(objArr[2])) ? b(objArr) : a(objArr);
                    break;
                case 2:
                    Object[] objArr2 = (Object[]) obj;
                    String strA = a((IApp) objArr2[0], (IFrameView) objArr2[1]);
                    if (strA == null) {
                        Process.killProcess(Process.myPid());
                    }
                    break;
                case 3:
                    a(String.valueOf(obj));
                    break;
                case 4:
                    objDoForFeature = this.f6702a.get(String.valueOf(obj));
                    break;
                case 5:
                    String[] strArr = (String[]) obj;
                    String str = strArr[0];
                    String str2 = strArr[1];
                    String str3 = strArr[2];
                    if (!PdrUtil.isEmpty(str) && !PdrUtil.isEmpty(str2)) {
                        this.b.put(str.toLowerCase(), str2);
                    }
                    if (!PdrUtil.isEmpty(str3)) {
                        this.g.add(str3);
                    }
                    break;
                case 8:
                    String[] strArr2 = (String[]) obj;
                    objDoForFeature = Boolean.valueOf(PermissionControler.checkPermission(strArr2[0], strArr2[1]));
                    break;
                case 9:
                    objDoForFeature = Boolean.valueOf(b(String.valueOf(obj)));
                    break;
                case 10:
                    try {
                        Object[] objArr3 = (Object[]) obj;
                        IFeature iFeatureB = b(String.valueOf(objArr3[1]), objArr3[0] instanceof IWebview ? ((IWebview) objArr3[0]).getActivity() : objArr3[0] instanceof IApp ? ((IApp) objArr3[0]).getActivity() : null);
                        if (iFeatureB instanceof IWaiter) {
                            objDoForFeature = ((IWaiter) iFeatureB).doForFeature(String.valueOf(objArr3[2]), objArr3[3]);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return null;
                    }
                    break;
                case 11:
                    c((String) ((Object[]) obj)[0]);
                    break;
            }
            return null;
        }
        objDoForFeature = this.mCore.dispatchEvent(mgrType, i, obj);
        return objDoForFeature;
    }

    private synchronized String b(Object[] objArr) {
        return a(objArr);
    }

    public IFeature b(String str, Activity activity) {
        String str2;
        ClassNotFoundException e2;
        IFeature iFeature;
        IFeature iFeature2;
        if (str.contains(",")) {
            String[] strArrSplit = str.split(",");
            String str3 = strArrSplit[0];
            str2 = strArrSplit[1];
            str = str3;
        } else {
            str2 = null;
        }
        IFeature iFeatureA = this.c.get(str);
        if (iFeatureA != null) {
            return iFeatureA;
        }
        String str4 = this.b.get(str);
        if (!PdrUtil.isEmpty(str4)) {
            str2 = str4;
        }
        if (str2 != null && this.f6703e.containsKey(str)) {
            IBoot iBoot = this.f6703e.get(str);
            if ((iBoot instanceof BaseFeature) && str2.equals(iBoot.getClass().getName())) {
                iFeatureA = (BaseFeature) iBoot;
            }
        }
        if (iFeatureA != null) {
            this.c.put(str, iFeatureA);
            return iFeatureA;
        }
        if (str2 != null) {
            try {
                iFeatureA = a.a(this, str);
                if (iFeatureA == null) {
                    iFeature = (IFeature) Class.forName(str2).newInstance();
                    try {
                        this.c.put(str, iFeature);
                        iFeature.init(this, str);
                        iFeatureA = iFeature;
                    } catch (ClassNotFoundException e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        try {
                            iFeature2 = (IFeature) a(str, activity);
                            if (iFeature2 == null) {
                                return iFeature2;
                            }
                            try {
                                this.c.put(str, iFeature2);
                                iFeature2.init(this, str);
                                return iFeature2;
                            } catch (Exception e4) {
                                e = e4;
                                e.printStackTrace();
                                return iFeature2;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            iFeature2 = iFeature;
                        }
                    } catch (IllegalAccessException e6) {
                        e = e6;
                        iFeatureA = iFeature;
                        e.printStackTrace();
                        return iFeatureA;
                    } catch (InstantiationException e7) {
                        e = e7;
                        iFeatureA = iFeature;
                        e.printStackTrace();
                        return iFeatureA;
                    }
                } else {
                    this.c.put(str, iFeatureA);
                }
                return iFeatureA;
            } catch (ClassNotFoundException e8) {
                IFeature iFeature3 = iFeatureA;
                e2 = e8;
                iFeature = iFeature3;
            } catch (IllegalAccessException e9) {
                e = e9;
            } catch (InstantiationException e10) {
                e = e10;
            }
        } else {
            try {
                IFeature iFeature4 = (IFeature) a(str, activity);
                if (iFeature4 != null) {
                    try {
                        this.c.put(str, iFeature4);
                        iFeature4.init(this, str);
                    } catch (Exception e11) {
                        e = e11;
                        iFeatureA = iFeature4;
                        e.printStackTrace();
                        return iFeatureA;
                    }
                }
                return iFeature4;
            } catch (Exception e12) {
                e = e12;
            }
        }
    }

    public String a(IWebview iWebview) {
        StringBuffer stringBuffer = new StringBuffer();
        String webviewUUID = iWebview.getWebviewUUID();
        if (PdrUtil.isEmpty(webviewUUID)) {
            webviewUUID = String.valueOf(iWebview.obtainFrameView().hashCode());
        }
        stringBuffer.append("window.__HtMl_Id__= '" + webviewUUID + "';");
        if (PdrUtil.isEmpty(iWebview.obtainFrameId())) {
            stringBuffer.append("window.__WebVieW_Id__= undefined;");
        } else {
            stringBuffer.append("window.__WebVieW_Id__= '" + iWebview.obtainFrameId() + "';");
        }
        stringBuffer.append("try{window.plus.__tag__='_plus_all_'}catch(e){}");
        return stringBuffer.toString();
    }

    public boolean c() {
        try {
            if (BaseInfo.ISDEBUG && DHFile.isExist("/sdcard/dcloud/all.js")) {
                this.f = new String(PlatformUtil.getFileContent("/sdcard/dcloud/all.js", 2));
            } else {
                this.f = new String(PlatformUtil.getFileContent(DCloudAdapterUtil.getRuntimeJsPath(), 1));
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private String a(IApp iApp, IWebview iWebview) {
        StringBuffer stringBuffer = new StringBuffer(AbsoluteConst.PROTOCOL_JAVASCRIPT);
        stringBuffer.append("function ");
        stringBuffer.append(AbsoluteConst.LOAD_PLUS_FUN_NAME);
        stringBuffer.append("(){try{");
        int i = Build.VERSION.SDK_INT;
        if (i <= 19) {
            stringBuffer.append(a(iWebview));
        }
        if (TextUtils.isEmpty(this.f) || this.f.length() < 400) {
            return null;
        }
        stringBuffer.append("window._____isDebug_____=" + BaseInfo.ISDEBUG + ";");
        stringBuffer.append("window._____platform_____=1;");
        stringBuffer.append("window._____platform_os_version_____=" + i + ";");
        stringBuffer.append(this.f);
        if (PermissionControler.checkPermission(iApp.obtainAppId(), IFeature.F_DEVICE.toLowerCase()) || !iApp.manifestBeParsed()) {
            if (PdrUtil.isEmpty(DeviceInfo.DEVICESTATUS_JS)) {
                try {
                    DeviceInfo.initGsmCdmaCell();
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                }
                DeviceInfo.getDevicestatus_js(iApp);
            }
            stringBuffer.append(DeviceInfo.DEVICESTATUS_JS);
        }
        SDK.IntegratedMode integratedMode = BaseInfo.sRuntimeMode;
        StringBuilder sb = new StringBuilder();
        sb.append("window.__NWin_Enable__=");
        sb.append(integratedMode == SDK.IntegratedMode.WEBVIEW ? String.valueOf(false) : String.valueOf(true));
        sb.append(";");
        stringBuffer.append(sb.toString());
        if (PermissionControler.checkPermission(iApp.obtainAppId(), IFeature.F_RUNTIME) || !iApp.manifestBeParsed()) {
            String strObtainConfigProperty = iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_LOADED_TIME);
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, BaseInfo.ISAMU ? StringUtil.format(AbsoluteConst.JS_RUNTIME_VERSIONs, iApp.obtainAppVersionName(), "1.9.9.81096", iApp.obtainAppId(), strObtainConfigProperty) : StringUtil.format(AbsoluteConst.JS_RUNTIME_VERSIONs, AndroidResources.mApplicationInfo.versionName, "1.9.9.81096", iApp.obtainAppId(), strObtainConfigProperty)));
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_ARGUMENTS, DataUtil.utf8ToUnicode(iApp.obtainRuntimeArgs(true)))));
            String launcherData = BaseInfo.getLauncherData(iApp.obtainAppId());
            if (!PdrUtil.isEmpty(iApp.obtainWebAppIntent())) {
                String stringExtra = iApp.obtainWebAppIntent().getStringExtra(IntentConst.FROM_STREAM_OPEN_FLAG);
                if (!TextUtils.isEmpty(stringExtra) && !TextUtils.equals(stringExtra, iApp.obtainAppId())) {
                    launcherData = launcherData + Constants.COLON_SEPARATOR + stringExtra;
                }
            }
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_LAUNCHER, launcherData)));
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_CHANNEL, BaseInfo.getAnalysisChannel())));
            String bundleData = SP.getBundleData(iApp.getActivity(), "pdr", iApp.obtainAppId() + AbsoluteConst.LAUNCHTYPE);
            if (TextUtils.isEmpty(bundleData)) {
                bundleData = "default";
            }
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_ORIGIN, bundleData)));
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_STARTUPTIME, String.valueOf(BaseInfo.getStartupTimeData(iApp.obtainAppId())))));
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_PROCESSID, Long.valueOf(BaseInfo.sProcessId))));
            stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format("p.runtime.versionCode = %d;", Integer.valueOf(AndroidResources.versionCode))));
            if (BaseInfo.isUniAppAppid(iApp)) {
                stringBuffer.append(StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format("p.runtime.uniVersion = '%s';", BaseInfo.uniVersionV3)));
            }
        }
        if (PermissionControler.checkPermission(iApp.obtainAppId(), "Navigator")) {
            DeviceInfo.updateStatusBarHeight(iWebview.getActivity());
            stringBuffer.append(String.format(Locale.UK, AbsoluteConst.JS_NAVIGATOR_STATUSBAR_HEIGHT, Float.valueOf(DeviceInfo.sStatusBarHeight / iWebview.getScale())));
        }
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            stringBuffer.append(this.g.get(i2));
        }
        stringBuffer.append(a());
        stringBuffer.append("}catch(e){console.log('__load__plus__ function error=' + e);}}");
        stringBuffer.append(AbsoluteConst.EXECUTE_LOAD_FUNS_FUN);
        stringBuffer.append(a(iApp));
        stringBuffer.append(b(iApp));
        return stringBuffer.toString();
    }

    private void b() {
        try {
            this.i = (c) Class.forName("io.dcloud.feature.d").getConstructor(Context.class).newInstance(getContext());
        } catch (Exception unused) {
            Logger.e("fmgr no dp");
        }
    }

    private boolean b(String str) {
        return this.b.containsKey(str);
    }

    private String a(IApp iApp) {
        if (BaseInfo.isUniNViewBackgroud()) {
            String str = (String) processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iApp, "weex,io.dcloud.feature.weex.WeexFeature", "getUniNViewModules", null});
            if (!PdrUtil.isEmpty(str)) {
                return "window.__NATIVE_PLUGINS__ = " + str + ";window.__NATIVE_PLUGINS_REGISTERED__ = true;";
            }
        }
        return "";
    }

    private String a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(";Object.defineProperty(plus.screen,\"resolutionHeight\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"s.resolutionHeight\",[])}}),Object.defineProperty(plus.screen,\"resolutionWidth\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"s.resolutionWidth\",[])}}),Object.defineProperty(plus.display,\"resolutionHeight\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"d.resolutionHeight\",[])}}),Object.defineProperty(plus.display,\"resolutionWidth\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"d.resolutionWidth\",[])}});");
        stringBuffer.append(";plus.webview.__test__('save');");
        if (Build.VERSION.SDK_INT > 19) {
            stringBuffer.append("plus.webview.__test__('update');");
        }
        return stringBuffer.toString();
    }

    private String a(Object[] objArr) {
        IWebview iWebview = (IWebview) objArr[0];
        String lowerCase = String.valueOf(objArr[1]).toLowerCase();
        String strValueOf = String.valueOf(objArr[2]);
        JSONArray jSONArray = (JSONArray) objArr[3];
        String str = null;
        strArrJsonArrayToStringArr = null;
        String[] strArrJsonArrayToStringArr = null;
        str = null;
        str = null;
        str = null;
        if (iWebview != null && iWebview.obtainApp() != null && iWebview.obtainFrameView() != null && iWebview.obtainFrameView().obtainWebView() != null) {
            IFeature iFeatureB = b(lowerCase, iWebview.getActivity());
            if (iFeatureB != null) {
                if (iFeatureB instanceof BaseFeature) {
                    BaseFeature baseFeature = (BaseFeature) iFeatureB;
                    if (!baseFeature.isOldMode()) {
                        return baseFeature.execute(iWebview, strValueOf, jSONArray);
                    }
                }
                if (jSONArray != null) {
                    try {
                        strArrJsonArrayToStringArr = JSUtil.jsonArrayToStringArr(jSONArray);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                return iFeatureB.execute(iWebview, strValueOf, strArrJsonArrayToStringArr);
            }
            str = "";
            if (iWebview.obtainFrameView().getFrameType() != 3 && !PermissionControler.checkSafePermission(iWebview.obtainApp().obtainAppId(), lowerCase)) {
                Logger.e("featuremgr", "not found " + lowerCase + " feature plugin ; action=" + strValueOf + ";" + StringUtil.format(getContext().getString(R.string.dcloud_feature_error_tips), lowerCase));
                a(iWebview, lowerCase);
            }
        }
        return str;
    }

    private Object a(String str, Activity activity) {
        c cVar = this.i;
        if (cVar != null) {
            return cVar.a(str, activity);
        }
        return null;
    }

    private HashMap<String, IBoot> a(HashMap<String, IBoot> map) {
        if (this.i == null) {
            return map;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        Set<String> setKeySet = this.i.a().keySet();
        if (setKeySet != null) {
            for (String str : setKeySet) {
                Object objA = this.i.a(str);
                if (objA != null) {
                    if (objA instanceof BaseFeature) {
                        ((BaseFeature) objA).init(this, str);
                    }
                    map.put(str, (IBoot) objA);
                }
            }
        }
        return map;
    }

    private void a(Iterator<String> it) {
        if (SDK.isUniMPSDK()) {
            return;
        }
        while (it.hasNext()) {
            String strValueOf = String.valueOf(PlatformUtil.invokeMethod(it.next(), "getJsContent"));
            if (!PdrUtil.isEmpty(strValueOf)) {
                this.g.add(strValueOf);
            }
        }
    }

    public void a(String str) {
        Collection<IFeature> collectionValues;
        HashMap<String, IFeature> map = this.c;
        if (map != null && (collectionValues = map.values()) != null) {
            Iterator<IFeature> it = collectionValues.iterator();
            while (it.hasNext()) {
                try {
                    it.next().dispose(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        HashMap<String, String> map2 = this.h;
        if (map2 != null) {
            String str2 = null;
            Iterator<String> it2 = map2.keySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                String next = it2.next();
                if (next.startsWith(str + "_")) {
                    str2 = next;
                    break;
                }
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.h.remove(str2);
        }
    }

    public void a(IWebview iWebview, String str) {
        Dialog lossDialog = ErrorDialogUtil.getLossDialog(iWebview, StringUtil.format(iWebview.getContext().getString(R.string.dcloud_feature_error_tips2) + "http://ask.dcloud.net.cn/article/283", str), "http://ask.dcloud.net.cn/article/283", str);
        if (lossDialog != null) {
            lossDialog.show();
        }
    }
}
