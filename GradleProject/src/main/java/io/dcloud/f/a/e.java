package io.dcloud.f.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.hms.actions.SearchIntents;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.WebAppActivity;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IBoot;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IConfusionMgr;
import io.dcloud.common.DHInterface.IDCloudWebviewClientListener;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.IPdrModule;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.common.DHInterface.ReceiveSystemEventVoucher;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.io.UnicodeInputStream;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.webview.WebResUtil;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.util.ADUtils;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.AppStatus;
import io.dcloud.common.util.AppStatusBarManager;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.NetworkTypeUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.common.util.ZipUtils;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.nativeObj.photoview.subscaleview.ImageSource;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class e extends io.dcloud.f.a.f implements IApp, ISysEventListener {
    public static String B1 = "webapp";
    public String M0;
    public String b0;
    public String c0;
    public String d0;
    public String e0;
    public boolean n1;
    public g o;
    public IConfusionMgr p1;
    public io.dcloud.f.a.a u;
    public ArrayList<String> x0;
    public BaseInfo.BaseAppInfo p = null;
    public byte q = 1;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    public String v = null;
    public String w = "";
    public String x = "";
    public String y = "";
    public String z = null;
    public String A = "";
    public String B = null;
    public String C = null;
    public String D = null;
    public String E = null;
    public String F = null;
    public String G = null;
    public String H = null;
    public boolean I = true;
    public boolean J = true;
    public boolean K = true;
    public boolean L = false;
    public boolean M = false;
    public boolean N = true;
    public boolean O = false;
    public String P = null;
    public boolean Q = false;
    public byte R = 1;
    public boolean S = false;
    public boolean T = false;
    public boolean U = true;
    public boolean V = true;
    public int W = 10000;
    public int X = 0;
    public int Y = 0;
    public String Z = null;
    public String a0 = null;
    public boolean f0 = false;
    public String g0 = null;
    public String h0 = null;
    public String i0 = null;
    public String j0 = null;
    public boolean k0 = false;
    public String l0 = "accept";
    public String m0 = "file:///android_asset/data/dcloud_error.html";
    public String n0 = null;
    public String o0 = null;
    public String p0 = null;
    public String q0 = "-1";
    public JSONObject r0 = null;
    public String s0 = "";
    public boolean t0 = true;
    public boolean u0 = false;
    public String v0 = AbsoluteConst.UNI_V3;
    public String w0 = "fast";
    public HashMap<ISysEventListener.SysEventType, ArrayList<ISysEventListener>> y0 = null;
    public JSONObject z0 = null;
    public JSONObject A0 = null;
    public JSONObject B0 = null;
    public JSONObject C0 = null;
    public JSONObject D0 = null;
    public JSONObject E0 = null;
    public JSONObject F0 = null;
    public JSONObject G0 = null;
    public String H0 = null;
    public String I0 = null;
    public Intent J0 = null;
    public IApp.IAppStatusListener K0 = null;
    public String L0 = null;
    public String N0 = "none";
    public boolean O0 = false;
    public boolean P0 = false;
    public boolean Q0 = false;
    public String R0 = "default";
    public String S0 = null;
    public String T0 = null;
    public String U0 = null;
    public String V0 = "";
    public boolean W0 = false;
    public boolean X0 = false;
    public boolean Y0 = false;
    public String Z0 = null;
    public String a1 = null;
    public boolean b1 = false;
    public long c1 = 0;
    public boolean d1 = true;
    public boolean e1 = false;
    public boolean f1 = false;
    public ArrayList<String> g1 = new ArrayList<>();
    public ArrayList<String> h1 = new ArrayList<>();
    public String i1 = null;
    public String j1 = null;
    public String k1 = "";
    public boolean l1 = false;
    public String m1 = null;
    public int o1 = 1;
    public IWebviewStateListener q1 = null;
    public JSONObject r1 = null;
    public boolean s1 = false;
    public String t1 = "none";
    public String u1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
    public String v1 = null;
    public String w1 = null;
    public boolean x1 = true;
    public HashMap<String, Integer> y1 = null;
    public String z1 = null;
    public boolean A1 = false;

    public class a implements Runnable {
        public a(e eVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class b implements Runnable {
        public b(e eVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
                DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ICallBack f6482a;

        public class a implements MessageHandler.IMessages {
            public a() {
            }

            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                c.this.f6482a.onCallBack(0, null);
            }
        }

        public c(ICallBack iCallBack) {
            this.f6482a = iCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = BaseInfo.sCacheFsAppsPath + e.this.m + DeviceInfo.sSeparatorChar + BaseInfo.APP_WWW_FS_DIR;
            long jCurrentTimeMillis = System.currentTimeMillis();
            Logger.d(e.B1, e.this.m + " copy resoure begin!!!");
            DHFile.delete(str);
            DHFile.copyDir(e.this.g0, str);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            Logger.d(e.B1, e.this.m + " copy resoure end!!! useTime=" + (jCurrentTimeMillis2 - jCurrentTimeMillis));
            e.this.R = (byte) 0;
            e.this.setAppDataPath(str);
            e eVar = e.this;
            BaseInfo.BaseAppInfo baseAppInfo = eVar.p;
            if (baseAppInfo != null) {
                baseAppInfo.saveToBundleData(eVar.getActivity());
            }
            MessageHandler.sendMessage(new a(), null);
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6484a;

        public d(String str) {
            this.f6484a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TestUtil.PointTime.commitTid(e.this.getActivity(), this.f6484a, null, e.this.I0, 1);
        }
    }

    /* JADX INFO: renamed from: io.dcloud.f.a.e$e, reason: collision with other inner class name */
    public class RunnableC0162e implements Runnable {
        public RunnableC0162e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(e.this.obtainAppTempPath());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static /* synthetic */ class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6486a;

        static {
            int[] iArr = new int[IApp.ConfigProperty.ThridInfo.values().length];
            f6486a = iArr;
            try {
                iArr[IApp.ConfigProperty.ThridInfo.OverrideUrlJsonData.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.OverrideResourceJsonData.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.SecondWebviewJsonData.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.LaunchWebviewJsonData.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.TitleNViewJsonData.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.SitemapJsonData.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.URDJsonData.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.DirectPageJsonData.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f6486a[IApp.ConfigProperty.ThridInfo.Tabbar.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public e(io.dcloud.f.a.a aVar, String str, byte b2) {
        this.o = null;
        this.u = null;
        this.x0 = null;
        this.n1 = false;
        this.u = aVar;
        this.m = str;
        b(b2);
        this.p1 = io.dcloud.f.b.b.c();
        this.o = new g();
        this.x0 = new ArrayList<>(2);
        this.n1 = AppRuntime.isUniApp(str);
    }

    private void d() {
    }

    private void e() {
        JSONObject jSONObject = this.G0;
        if (jSONObject != null) {
            try {
                String strOptString = jSONObject.optString("webviewid");
                if (TextUtils.isEmpty(strOptString)) {
                    this.G0.put("webviewid", IntentConst.DIRECT_PAGE);
                }
                if (this.m.equals(strOptString)) {
                    this.F0 = this.G0.optJSONObject("titleNView");
                    return;
                }
                JSONObject jSONObjectOptJSONObject = this.G0.has("titleNView") ? this.G0.optJSONObject("titleNView") : null;
                if (jSONObjectOptJSONObject == null) {
                    jSONObjectOptJSONObject = new JSONObject();
                    this.G0.put("titleNView", jSONObjectOptJSONObject);
                }
                jSONObjectOptJSONObject.put("autoBackButton", true);
                if (jSONObjectOptJSONObject.has("homeButton")) {
                    return;
                }
                jSONObjectOptJSONObject.put("homeButton", true);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean e(String str) {
        return false;
    }

    private void f() {
        try {
            int iRename = DHFile.rename(BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT, BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
            DHFile.copyDir("data/wap2app", BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT);
            if (iRename == 1) {
                ThreadPool.self().addThreadTask(new a(this));
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private String h() {
        return "UWV/BnpHVVhMahB0EU1XA15hAEFOAWlGVHBkcgluSF0HFhlQZx15Yhhjb3xCHgRfWxV+cQhPS1ICFxRzdkUfeyo2YTNkODhmYS00YmEwLTQ3OWYtOTQyMi1lNWFhYmUxNTg5N2IxMjQ=";
    }

    private JSONObject i() {
        JSONObject jSONObjectA = null;
        try {
            InputStream inputStreamObtainResInStream = obtainResInStream("_www/__template.json");
            if (inputStreamObtainResInStream == null) {
                return null;
            }
            jSONObjectA = a(inputStreamObtainResInStream);
            IOUtil.close(inputStreamObtainResInStream);
            return jSONObjectA;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObjectA;
        }
    }

    private JSONObject j() {
        ThreadPool threadPoolSelf;
        b bVar;
        JSONObject jSONObject = null;
        try {
            boolean z = true;
            boolean z2 = false;
            if (BaseInfo.sCoverApkRuning) {
                if (new File(BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT + "__template.json").exists()) {
                    InputStream inputStream = PlatformUtil.getInputStream(BaseInfo.sBaseConfigTemplatePath);
                    JSONObject jSONObjectA = a(inputStream);
                    String strOptString = jSONObjectA.optString("version");
                    IOUtil.close(inputStream);
                    InputStream inputStream2 = DHFile.getInputStream(BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT + "__template.json");
                    JSONObject jSONObjectA2 = a(inputStream2);
                    String strOptString2 = jSONObjectA2.optString("version");
                    IOUtil.close(inputStream2);
                    BaseInfo.mWap2appTemplateFiles.clear();
                    BaseInfo.mW2AE.clear();
                    if (BaseInfo.BaseAppInfo.compareVersion(strOptString, strOptString2)) {
                        f();
                        jSONObject = jSONObjectA;
                        z2 = true;
                    } else {
                        jSONObject = jSONObjectA2;
                    }
                }
            }
            if (DHFile.isExist(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip")) {
                DHFile.rename(BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT, BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
                try {
                    try {
                        ZipUtils.upZipFile(new File(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip"), BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT);
                        threadPoolSelf = ThreadPool.self();
                        bVar = new b(this);
                    } catch (Throwable th) {
                        ThreadPool.self().addThreadTask(new b(this));
                        throw th;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    threadPoolSelf = ThreadPool.self();
                    bVar = new b(this);
                    z = z2;
                }
                threadPoolSelf.addThreadTask(bVar);
            } else {
                if (new File(BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT + "__template.json").exists()) {
                    z = z2;
                } else {
                    f();
                }
            }
            if (!z && !TextUtils.isEmpty(BaseInfo.sWap2AppTemplateVersion) && BaseInfo.mWap2appTemplateFiles.size() != 0 && this.p1.getData("__w2a__template__") != null) {
                return jSONObject;
            }
            if (!DHFile.isExist(BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT + "__template.json")) {
                return jSONObject;
            }
            InputStream inputStream3 = DHFile.getInputStream(BaseInfo.sBaseWap2AppTemplatePath + WebResUtil.F_WT + "__template.json");
            JSONObject jSONObjectA3 = a(inputStream3);
            IOUtil.close(inputStream3);
            BaseInfo.mWap2appTemplateFiles.clear();
            BaseInfo.mW2AE.clear();
            this.p1.removeData("__w2a__template__");
            return jSONObjectA3;
        } catch (Exception e3) {
            e3.printStackTrace();
            return jSONObject;
        }
    }

    private void m() {
        String[] strArrSplit;
        this.y1 = new HashMap<>();
        String string = SP.getOrCreateBundle(getActivity(), this.m + "_1").getString("Authorize", null);
        this.z1 = string;
        if (string == null || (strArrSplit = string.split("&")) == null || strArrSplit.length <= 0) {
            return;
        }
        for (String str : strArrSplit) {
            if (!TextUtils.isEmpty(str)) {
                String[] strArrSplit2 = str.split(ContainerUtils.KEY_VALUE_DELIMITER);
                this.y1.put(strArrSplit2[0], Integer.valueOf(Integer.parseInt(strArrSplit2[1])));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void y() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 503
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.f.a.e.y():void");
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void addAllFeaturePermission() {
        PermissionControler.registerRootPermission(this.m);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void addFeaturePermission(String str) {
        this.x0.add(str.toLowerCase());
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void applyMani() {
        try {
            a(DHFile.getInputStream(DHFile.createFileHandler(a(BaseInfo.sConfigXML))), this.m, null);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void applySmartUpdate() {
        a(false);
    }

    public void b(InputStream inputStream) {
    }

    public boolean b(String str, JSONObject jSONObject) {
        boolean z;
        HashMap<String, BaseInfo.BaseAppInfo> map;
        boolean zA = false;
        InputStream inputStream = null;
        try {
            try {
                this.m = str;
                this.p1.removeData(str);
                l();
                if (this.R == 0 || !((map = BaseInfo.mBaseAppInfoSet) == null || map.containsKey(this.m))) {
                    inputStream = DHFile.getInputStream(DHFile.createFileHandler(a(BaseInfo.sConfigXML)));
                    if (inputStream == null && (inputStream = PlatformUtil.getResInputStream(a(BaseInfo.sConfigXML))) != null) {
                        this.R = (byte) 1;
                    }
                } else if (this.R == 1) {
                    inputStream = PlatformUtil.getResInputStream(a(BaseInfo.sConfigXML));
                }
                if (BaseInfo.isWap2AppAppid(this.m)) {
                    y();
                    z = false;
                } else {
                    z = true;
                }
            } catch (Exception e2) {
                Logger.w("parseConfig", e2);
            }
            if (inputStream != null) {
                zA = a(inputStream, str, jSONObject);
                if (z) {
                    y();
                }
                IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(getActivity());
                if (iActivityHandler != null) {
                    iActivityHandler.updateSplash(this.p0);
                }
                this.r = true;
                return zA;
            }
            if (BaseInfo.isWap2AppAppid(this.m) && !TextUtils.isEmpty(this.i1)) {
                return true;
            }
            if (r() && !TextUtils.isEmpty(this.n0)) {
                return true;
            }
            g gVar = this.o;
            gVar.f6490a = true;
            if (gVar.c) {
                gVar.b = DOMException.toJSON(DOMException.CODE_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST, DOMException.MSG_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST);
            } else {
                gVar.b = DOMException.toJSON(DOMException.CODE_RUNTIME_WGT_MANIFEST_NOT_EXIST, DOMException.MSG_RUNTIME_WGT_MANIFEST_NOT_EXIST);
            }
            return false;
        } finally {
            IOUtil.close((InputStream) null);
        }
    }

    public void c() {
        Activity activity = this.f6487a;
        if (activity != null && (activity instanceof WebAppActivity)) {
            ((WebAppActivity) activity).onAppActive(this.m);
            ((WebAppActivity) this.f6487a).onAppActive(this);
        }
        diyStatusBarState();
        setStatus((byte) 3);
        this.b.onAppActive(this);
        callSysEventListener(ISysEventListener.SysEventType.onWebAppForeground, IntentConst.obtainArgs(obtainWebAppIntent(), this.m));
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean callSysEventListener(ISysEventListener.SysEventType sysEventType, Object obj) {
        HashMap<ISysEventListener.SysEventType, ArrayList<ISysEventListener>> map = this.y0;
        boolean zOnExecute = false;
        if (map == null) {
            return false;
        }
        ArrayList<ISysEventListener> arrayList = map.get(sysEventType);
        ArrayList<ISysEventListener> arrayList2 = this.y0.get(ISysEventListener.SysEventType.AllSystemEvent);
        ArrayList arrayList3 = new ArrayList();
        if (arrayList != null) {
            arrayList3.addAll(arrayList);
        }
        if (arrayList2 != null) {
            arrayList3.addAll(arrayList2);
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            ISysEventListener iSysEventListener = (ISysEventListener) arrayList3.get(size);
            if (a(iSysEventListener, sysEventType) && (zOnExecute || iSysEventListener.onExecute(sysEventType, obj)) && !a(sysEventType)) {
                break;
            }
        }
        return zOnExecute;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkIsCustomPath() {
        return this.k0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void checkOrLoadlaunchWebview() {
        io.dcloud.f.a.a aVar = this.u;
        if (aVar != null) {
            AdaFrameView adaFrameView = (AdaFrameView) aVar.processEvent(IMgr.MgrType.WindowMgr, 46, obtainAppId());
            Logger.d("Direct_page", "checkOrLoadlaunchWebview " + manifestBeParsed() + ";adaFrameView=" + adaFrameView);
            manifestBeParsed();
            if (adaFrameView == null || !manifestBeParsed()) {
                return;
            }
            adaFrameView.obtainWebView().checkIfNeedLoadOriginalUrl();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkPrivateDir(String str) {
        return str.startsWith(obtainAppDataPath()) || str.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String checkPrivateDirAndCopy2Temp(String str) {
        if (obtainRunningAppMode() == 1 && checkPrivateDir(str)) {
            String str2 = "/" + BaseInfo.APP_WWW_FS_DIR;
            String strSubstring = str.substring(str.indexOf(str2) + str2.length());
            String str3 = this.g0 + strSubstring;
            str = obtainAppTempPath() + strSubstring;
            if (!DHFile.exists(str)) {
                DHFile.copyAssetsFile(str3, str);
            }
        }
        return str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkSchemeWhite(String str) {
        if (!r()) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : this.h1) {
            if (!TextUtils.equals(str2, Operators.MUL)) {
                if (str.startsWith(str2 + Constants.COLON_SEPARATOR)) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkWhiteUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.g1.contains(Operators.MUL) || this.g1.contains(str);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void clearRuntimeArgs() {
        this.A = "";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2AbsFullPath(String str, String str2) {
        boolean z = true;
        try {
            if (!PdrUtil.isEmpty(str2)) {
                if (this.R == 1 && PlatformUtil.isResFileExists(str2)) {
                    return str2;
                }
                if (DHFile.isExist(str2)) {
                    return str2;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (PdrUtil.isEmpty(str2)) {
            return str2;
        }
        int iIndexOf = str2.indexOf(Operators.CONDITION_IF_STRING);
        if (iIndexOf > 0) {
            str2 = str2.substring(0, iIndexOf);
        }
        if (str2.startsWith("_documents/")) {
            return BaseInfo.sDocumentFullPath + str2.substring(11);
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR)) {
            return BaseInfo.sDocumentFullPath + str2.substring(10);
        }
        if (str2.startsWith(AbsoluteConst.MINI_SERVER_APP_DOC)) {
            return obtainAppDocPath() + str2.substring(5);
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_DOC_DIR)) {
            return obtainAppDocPath() + str2.substring(4);
        }
        if (str2.startsWith("_downloads/")) {
            return BaseInfo.sDownloadFullPath + str2.substring(11);
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR)) {
            return BaseInfo.sDownloadFullPath + str2.substring(10);
        }
        if (str2.startsWith(AbsoluteConst.MINI_SERVER_APP_WWW)) {
            byte b2 = this.R;
            if (b2 != 1) {
                if (b2 != 0) {
                    return str2;
                }
                return this.g0 + str2.substring(5);
            }
            return BaseInfo.sBaseResAppsPath + this.m + "/" + BaseInfo.APP_WWW_FS_DIR + str2.substring(5);
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR)) {
            byte b3 = this.R;
            if (b3 != 1) {
                if (b3 != 0) {
                    return str2;
                }
                return this.g0 + str2.substring(4);
            }
            return BaseInfo.sBaseResAppsPath + this.m + "/" + BaseInfo.APP_WWW_FS_DIR + str2.substring(4);
        }
        if (str2.startsWith("file://")) {
            return str2.substring(7);
        }
        if (str2.startsWith("content://") || str2.startsWith(DeviceInfo.sDeviceRootDir)) {
            return str2;
        }
        if (str2.startsWith("http://localhost")) {
            String strSubstring = str2.substring(16);
            return convert2AbsFullPath(null, strSubstring.substring(strSubstring.indexOf("/") + 1));
        }
        if (!str2.startsWith("/") && str != null) {
            z = false;
        } else if (str2.startsWith("/")) {
            str2 = str2.substring(1);
        }
        if (str != null) {
            if (str.startsWith("file:///android_asset/")) {
                str = str.substring(22);
            } else if (str.startsWith("file://")) {
                str = str.substring(7);
            }
        }
        if (str != null && !z) {
            return PdrUtil.standardizedURL(str, str2);
        }
        if (!z) {
            return str2;
        }
        String strObtainAppDataPath = obtainAppDataPath();
        if (str != null && !PdrUtil.isEquals(str, strObtainAppDataPath) && str.contains("/www/")) {
            strObtainAppDataPath = str.substring(0, str.indexOf("/www/") + 5);
        }
        return strObtainAppDataPath + b(str2);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2LocalFullPath(String str, String str2) throws Throwable {
        String strConvert2AbsFullPath = convert2AbsFullPath(str, str2);
        byte b2 = this.R;
        if (b2 == 1 || DeviceInfo.isPrivateDirectory) {
            InputStream resInputStream = b2 == 1 ? PlatformUtil.getResInputStream(strConvert2AbsFullPath) : PlatformUtil.getInputStream(strConvert2AbsFullPath);
            if (resInputStream != null) {
                strConvert2AbsFullPath = obtainAppTempPath() + System.currentTimeMillis();
                try {
                    DHFile.writeFile(resInputStream, strConvert2AbsFullPath);
                    resInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (b2 == 0) {
            return strConvert2AbsFullPath;
        }
        return strConvert2AbsFullPath;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2RelPath(String str) {
        try {
            int length = obtainAppDataPath().length();
            int length2 = obtainAppDocPath().length();
            int length3 = BaseInfo.sDocumentFullPath.length();
            int length4 = BaseInfo.sDownloadFullPath.length();
            if (str.startsWith(obtainAppDataPath())) {
                str = BaseInfo.REL_PRIVATE_WWW_DIR + str.substring(length - 1);
            } else {
                int i = length - 1;
                if (str.startsWith(obtainAppDataPath().substring(0, i))) {
                    str = BaseInfo.REL_PRIVATE_WWW_DIR + str.substring(i, str.length());
                } else if (str.startsWith(obtainAppDocPath())) {
                    str = BaseInfo.REL_PRIVATE_DOC_DIR + str.substring(length2 - 1);
                } else {
                    int i2 = length2 - 1;
                    if (str.startsWith(obtainAppDocPath().substring(0, i2))) {
                        str = BaseInfo.REL_PRIVATE_DOC_DIR + str.substring(i2);
                    } else if (str.startsWith(BaseInfo.sDocumentFullPath)) {
                        str = BaseInfo.REL_PUBLIC_DOCUMENTS_DIR + str.substring(length3 - 1);
                    } else {
                        int i3 = length3 - 1;
                        if (str.startsWith(BaseInfo.sDocumentFullPath.substring(0, i3))) {
                            str = BaseInfo.REL_PUBLIC_DOCUMENTS_DIR + str.substring(i3);
                        } else if (str.startsWith(BaseInfo.sDownloadFullPath)) {
                            str = BaseInfo.REL_PUBLIC_DOWNLOADS_DIR + str.substring(length4 - 1);
                        } else {
                            int i4 = length4 - 1;
                            if (str.startsWith(BaseInfo.sDownloadFullPath.substring(0, i4))) {
                                str = BaseInfo.REL_PUBLIC_DOWNLOADS_DIR + str.substring(i4);
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2WebviewFullPath(String str, String str2) {
        if (PdrUtil.isEmpty(str2)) {
            return str2;
        }
        if (this.Q) {
            if (str2.startsWith(DeviceInfo.HTTP_PROTOCOL)) {
                return str2;
            }
            return this.o0 + str2;
        }
        if (str2.startsWith("file://") || str2.startsWith(DeviceInfo.HTTP_PROTOCOL) || str2.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            return str2;
        }
        try {
            if (DHFile.isExist(str2)) {
                return ImageSource.FILE_SCHEME + b(str2);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (str2.startsWith(DeviceInfo.sDeviceRootDir)) {
            return "file://" + str2;
        }
        boolean z = true;
        if (str2.startsWith("/")) {
            str2 = str2.substring(1);
        } else {
            z = false;
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR)) {
            return obtainWebviewBaseUrl() + b(str2.substring(4));
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR)) {
            return "file://" + BaseInfo.sDocumentFullPath + b(str2.substring(10));
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_DOC_DIR)) {
            return "file://" + obtainAppDocPath() + b(str2.substring(4));
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR)) {
            return "file://" + BaseInfo.sDownloadFullPath + b(str2.substring(10));
        }
        if (str != null && !z) {
            return PdrUtil.standardizedURL(str, str2);
        }
        String strObtainWebviewBaseUrl = obtainWebviewBaseUrl();
        if (str != null && !PdrUtil.isEquals(str, strObtainWebviewBaseUrl) && str.contains("/www/")) {
            strObtainWebviewBaseUrl = str.substring(0, str.indexOf("/www/") + 5);
        }
        return strObtainWebviewBaseUrl + b(str2);
    }

    public JSONObject d(String str) {
        if (this.r1 == null) {
            n();
        }
        if (this.r1 == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return PdrUtil.getSitemapParameters(this.r1, obtainAppId(), str);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void deleteAppTemp() {
        ThreadPool.self().addThreadTask(new RunnableC0162e(), true);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void diyStatusBarState() {
        AppStatusBarManager appStatusBarManager = this.k;
        if (appStatusBarManager == null) {
            return;
        }
        appStatusBarManager.setFullScreen(getActivity(), isFullScreen());
        if (this.k.checkImmersedStatusBar(getActivity(), this.W0)) {
            BaseInfo.isImmersive = true;
            this.k.setImmersive(getActivity(), true);
        } else {
            BaseInfo.isImmersive = false;
            this.k.setImmersive(getActivity(), false);
        }
        if (Build.VERSION.SDK_INT >= 21 && getActivity() != null) {
            if (PdrUtil.isEmpty(this.S0)) {
                this.k.setStatusBarColor(getActivity(), BaseInfo.mDeStatusBarBackground);
            } else {
                this.k.setStatusBarColor(getActivity(), this.S0.startsWith("#") ? PdrUtil.stringToColor(this.S0) : 0);
            }
        }
        this.k.setStatusBarMode(getActivity(), this.V0);
        if (this.k.isFullScreenOrImmersive()) {
            updateScreenInfo(2);
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String forceShortCut() {
        return this.u1;
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
    public boolean g(String str) {
        AppStatus.setAppStatus(this.m, 2);
        setRuntimeArgs(str);
        setStatus((byte) 3);
        Object objProcessEvent = this.u.processEvent(IMgr.MgrType.WindowMgr, 41, new Object[]{this, convert2WebviewFullPath(null, this.n0), Boolean.valueOf(this.O0)});
        if (objProcessEvent == null) {
            return true;
        }
        return Boolean.parseBoolean(String.valueOf(objProcessEvent));
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public IConfusionMgr getConfusionMgr() {
        return this.p1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getDirectPage() {
        return this.i1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public IApp.IAppStatusListener getIAppStatusListener() {
        return this.K0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getOriginalDirectPage() {
        return this.j1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getPathByType(byte b2) {
        if (b2 == 0) {
            return obtainAppDataPath();
        }
        if (b2 == 1) {
            return obtainAppDocPath();
        }
        if (b2 == 2) {
            return BaseInfo.sDocumentFullPath;
        }
        if (b2 == 3) {
            return BaseInfo.sDownloadFullPath;
        }
        if (b2 != -1) {
            return null;
        }
        return BaseInfo.sBaseResAppsPath + this.m + "/" + BaseInfo.APP_WWW_FS_DIR;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getPopGesture() {
        return this.N0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public int getQuitModel() {
        return this.o1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean isOnAppRunningMode() {
        return this.R == 1;
    }

    public float k() {
        return (PermissionControler.checkPermission(this.m, IFeature.F_DEVICE.toLowerCase()) && getActivity() != null && NetworkTypeUtil.getNetworkType(getActivity()) == 4) ? 1000.0f : 0.0f;
    }

    public void l() {
        if (PdrUtil.isEmpty(this.g0) || !DeviceInfo.startsWithSdcard(this.g0)) {
            setAppDataPath(BaseInfo.sCacheFsAppsPath + this.m + "/" + BaseInfo.REAL_PRIVATE_WWW_DIR);
        }
        if (PdrUtil.isEmpty(this.v1) || !DeviceInfo.startsWithSdcard(this.v1)) {
            setAppDocPath(BaseInfo.sBaseFsAppsPath + this.m + "/" + BaseInfo.REAL_PRIVATE_DOC_DIR);
        }
        if (PdrUtil.isEmpty(this.w1) || !DeviceInfo.startsWithSdcard(this.w1)) {
            this.w1 = BaseInfo.sCacheFsAppsPath + this.m + "/" + BaseInfo.APP_WEB_CHACHE;
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean manifestBeParsed() {
        return this.r || SDK.IntegratedMode.WEBVIEW == BaseInfo.sRuntimeMode;
    }

    public void n() {
        File file = new File(c(this.m));
        if (file.exists()) {
            try {
                JSONObject jSONObject = new JSONObject(IOUtil.toString(new FileInputStream(file)));
                this.r1 = jSONObject;
                this.y = jSONObject.optString("version");
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        byte[] fileContent = PlatformUtil.getFileContent("data/sitemap/" + this.m + ".json", 0);
        if (fileContent != null) {
            DHFile.writeFile(fileContent, 0, c(this.m));
            n();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean needRefreshApp() {
        return this.f1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean needReload() {
        return this.e1;
    }

    public boolean o() {
        if (r() && this.r) {
            return this.L;
        }
        return true;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAdaptationJs() {
        if (this.P == null && !PdrUtil.isEmpty(this.G)) {
            byte[] fileContent = PlatformUtil.getFileContent(a(this.G), obtainRunningAppMode() == 1 ? 0 : 2);
            if (fileContent != null) {
                this.P = new String(fileContent);
            } else {
                this.P = "";
            }
        }
        return this.P;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppDataPath() {
        String str = this.g0;
        if (str != null) {
            return str;
        }
        return this.m + "/www/";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppDocPath() {
        return this.v1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppId() {
        return this.m;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", this.m);
            jSONObject.put(com.heytap.mcssdk.d.p, this.w);
            jSONObject.put("name", this.p0);
            jSONObject.put(com.heytap.mcssdk.d.q, this.x);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppLog() {
        return BaseInfo.sBaseFsAppsPath + this.m + "/log/";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppName() {
        return this.p0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public byte obtainAppStatus() {
        return this.q;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppTempPath() {
        return BaseInfo.sBaseFsAppsPath + this.m + "/temp/";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppVersionName() {
        return this.w;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppWebCachePath() {
        return this.w1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAuthority(String str) {
        String str2 = BaseInfo.sGlobalAuthority;
        if ((str2 != null && TextUtils.equals(Operators.MUL, str2)) || !r() || TextUtils.isEmpty(str) || e(this.m)) {
            return IApp.AUTHORITY_AUTHORIZED;
        }
        JSONObject jSONObject = this.E0;
        if (jSONObject == null) {
            return IApp.AUTHORITY_UNDETERMINED;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (str.equalsIgnoreCase(next)) {
                return this.E0.optString(next, IApp.AUTHORITY_UNDETERMINED);
            }
        }
        return IApp.AUTHORITY_UNDETERMINED;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainConfigProperty(String str) {
        String strValueOf;
        if (PdrUtil.isEquals(str, "adid")) {
            strValueOf = this.I0;
        } else if (PdrUtil.isEquals(str, "launchError")) {
            strValueOf = this.H0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_AUTOCLOSE)) {
            strValueOf = String.valueOf(this.U);
        } else if (PdrUtil.isEquals(str, "timeout")) {
            strValueOf = String.valueOf(this.W);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_DELAY)) {
            strValueOf = String.valueOf(this.X);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SPLASHSCREEN)) {
            strValueOf = String.valueOf(this.S);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_WAITING)) {
            strValueOf = String.valueOf(this.T);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_H5PLUS)) {
            strValueOf = String.valueOf(this.N);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_funSetUA)) {
            strValueOf = String.valueOf(this.O);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USER_AGENT)) {
            strValueOf = this.H;
        } else if (PdrUtil.isEquals(str, "error")) {
            strValueOf = this.m0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_FULLSCREEN)) {
            strValueOf = String.valueOf(this.h);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_UNTRUSTEDCA)) {
            strValueOf = this.l0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LOADED_TIME)) {
            strValueOf = this.L0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_RAM_CACHE_MODE)) {
            strValueOf = this.M0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_JSERROR)) {
            strValueOf = this.J + "";
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_CRASH)) {
            strValueOf = this.I + "";
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USE_ENCRYPTION)) {
            strValueOf = this.P0 + "";
        } else if (PdrUtil.isEquals(str, "w2a_delay")) {
            strValueOf = String.valueOf(this.Y);
        } else if (PdrUtil.isEquals(str, "w2a_autoclose")) {
            strValueOf = String.valueOf(this.V);
        } else if (PdrUtil.isEquals(str, "wap2app_running_mode")) {
            strValueOf = this.K + "";
        } else if (PdrUtil.isEquals(str, "injection")) {
            strValueOf = this.d1 + "";
        } else if (PdrUtil.isEquals(str, "event")) {
            strValueOf = this.Z;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_TARGET)) {
            strValueOf = this.a0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LPLUSERQUIRE)) {
            strValueOf = this.b0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SPLUSERQUIRE)) {
            strValueOf = this.c0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LGEOLOCATION)) {
            strValueOf = this.d0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SGEOLOCATION)) {
            strValueOf = this.e0;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_BC)) {
            strValueOf = this.S0 + "";
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_MODE)) {
            strValueOf = this.V0;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)) {
            strValueOf = this.W0 + "";
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_ISSTATUS)) {
            strValueOf = String.valueOf(this.X0);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_STATUSBAR_COLOR)) {
            strValueOf = this.Z0;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_ISATATUS)) {
            strValueOf = String.valueOf(this.Y0);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_STATUSBAR_COLOR)) {
            strValueOf = this.a1;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_MAP_COORD_TYPE)) {
            strValueOf = this.k1;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.UNIAPP_WEEX_JS_SERVICE)) {
            strValueOf = String.valueOf(this.b1);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.APP_UNIAPP_VERSION)) {
            strValueOf = this.q0;
        } else {
            if (!PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_UNIAPP_CONTROL)) {
                if (PdrUtil.isEquals(str, IApp.ConfigProperty.UNI_NVUE_DATA)) {
                    JSONObject jSONObject = this.r0;
                    if (jSONObject == null) {
                        return null;
                    }
                    return jSONObject.toString();
                }
                if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_CONCATENATE)) {
                    return this.M + "";
                }
                if (PdrUtil.isEquals(str, AbsoluteConst.NVUE_LAUNCH_MODE)) {
                    return this.w0;
                }
                if (PdrUtil.isEquals(str, AbsoluteConst.JSON_KEY_DEBUG_REFRESH)) {
                    return this.m1;
                }
                if (PdrUtil.isEquals(str, IApp.ConfigProperty.UNI_RESTART_TO_DIRECT)) {
                    return String.valueOf(this.l1);
                }
                if (PdrUtil.isEquals(str, AbsoluteConst.APP_IS_UNIAPP)) {
                    return String.valueOf(this.n1);
                }
                if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USE_V3_ENCRYPTION)) {
                    return String.valueOf(this.Q0);
                }
                return null;
            }
            strValueOf = this.n1 ? this.v0 : "h5+";
        }
        return strValueOf;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public IWebviewStateListener obtainLaunchPageStateListener() {
        return this.q1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public Object obtainMgrData(IMgr.MgrType mgrType, int i, Object[] objArr) {
        return this.u.processEvent(mgrType, i, objArr);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainOriginalAppId() {
        return this.v;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public InputStream obtainResInStream(String str, String str2) {
        String strConvert2AbsFullPath = convert2AbsFullPath(str, str2);
        byte b2 = this.R;
        if (b2 == 1) {
            if (!PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
                return PlatformUtil.getResInputStream(strConvert2AbsFullPath);
            }
            try {
                return DHFile.getInputStream(DHFile.createFileHandler(strConvert2AbsFullPath));
            } catch (IOException e2) {
                Logger.w("WebApp.obtainResInStream", e2);
            }
        } else if (b2 == 0) {
            try {
                return DHFile.getInputStream(DHFile.createFileHandler(strConvert2AbsFullPath));
            } catch (IOException e3) {
                Logger.w("WebApp.obtainResInStream", e3);
            }
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public byte obtainRunningAppMode() {
        return this.R;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainRuntimeArgs(boolean z) {
        return z ? JSONObject.quote(this.A) : this.A;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public AppStatusBarManager obtainStatusBarMgr() {
        return this.k;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public JSONObject obtainThridInfo(IApp.ConfigProperty.ThridInfo thridInfo) {
        switch (f.f6486a[thridInfo.ordinal()]) {
            case 1:
                return this.z0;
            case 2:
                return this.B0;
            case 3:
                return this.C0;
            case 4:
                return this.D0;
            case 5:
                return this.F0;
            case 6:
                n();
                return this.r1;
            case 7:
                return this.u.f;
            case 8:
                return this.G0;
            case 9:
                return this.A0;
            default:
                return null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainVersionSitemap() {
        return this.y;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public Intent obtainWebAppIntent() {
        return this.J0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainWebviewBaseUrl() {
        return a(this.R);
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        byte b2 = this.q;
        if (b2 == 3) {
            return callSysEventListener(sysEventType, obj);
        }
        if (b2 == 1 && (sysEventType == ISysEventListener.SysEventType.onWebAppStop || sysEventType == ISysEventListener.SysEventType.onStop)) {
            t();
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void onSplashClosed() {
        diyStatusBarState();
    }

    public boolean p() {
        String strOptString;
        if (PdrUtil.isEmpty(BaseInfo.uniVersionV3)) {
            try {
                strOptString = new JSONObject(new BufferedReader(new InputStreamReader(getActivity().getAssets().open((!BaseInfo.SyncDebug || PlatformUtil.getResInputStream("uni-jsframework-dev.js") == null || SDK.isUniMPSDK()) ? "uni-jsframework.js" : "uni-jsframework-dev.js"))).readLine().substring(2)).optString("version");
                BaseInfo.uniVersionV3 = strOptString;
            } catch (IOException | JSONException unused) {
                strOptString = "";
            }
        } else {
            strOptString = BaseInfo.uniVersionV3;
        }
        return ((!PdrUtil.isEmpty(this.s0) && this.s0.contains(strOptString) && !this.t0) || this.u0 || !this.r || this.q0.equals("-1") || this.q0.trim().equals(strOptString) || PdrUtil.isEmpty(strOptString) || !this.n1) ? false : true;
    }

    public boolean q() {
        return this.q == 3;
    }

    public boolean r() {
        Intent intentObtainWebAppIntent = obtainWebAppIntent();
        if (intentObtainWebAppIntent != null) {
            boolean z = this.A1;
            this.A1 = intentObtainWebAppIntent.getBooleanExtra(IntentConst.IS_STREAM_APP, z) | z;
        }
        return this.A1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void registerSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType) {
        if (this.y0 == null) {
            this.y0 = new HashMap<>(1);
        }
        ArrayList<ISysEventListener> arrayList = this.y0.get(sysEventType);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.y0.put(sysEventType, arrayList);
        }
        arrayList.add(iSysEventListener);
    }

    public boolean s() {
        Logger.d(Logger.AppMgr_TAG, this.m + " onStop");
        IApp.IAppStatusListener iAppStatusListener = this.K0;
        if (iAppStatusListener != null) {
            return iAppStatusListener.onStop();
        }
        return true;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setAppDataPath(String str) {
        if (this.R == 1) {
            if (str.startsWith(BaseInfo.sBaseResAppsPath)) {
                this.g0 = str;
                return;
            }
            this.g0 = BaseInfo.sBaseResAppsPath + this.m + "/" + BaseInfo.APP_WWW_FS_DIR;
            return;
        }
        if (new File(str).exists()) {
            this.g0 = str;
            return;
        }
        if (str.startsWith(DeviceInfo.sCacheRootDir)) {
            this.g0 = str;
            return;
        }
        this.g0 = DeviceInfo.sCacheRootDir + "/" + str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setAppDocPath(String str) {
        this.v1 = PdrUtil.appendByDeviceRootDir(str);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setConfigProperty(String str, String str2) {
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_AUTOCLOSE)) {
            this.U = PdrUtil.parseBoolean(str2, this.U, false);
            return;
        }
        if (PdrUtil.isEquals(str, "commit")) {
            a();
            return;
        }
        if (PdrUtil.isEquals(str, "timeout")) {
            this.W = PdrUtil.parseInt(str2, this.W);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_DELAY)) {
            this.X = PdrUtil.parseInt(str2, this.X);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SPLASHSCREEN)) {
            this.S = PdrUtil.parseBoolean(str2, this.S, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_WAITING)) {
            this.T = PdrUtil.parseBoolean(str2, this.T, false);
            return;
        }
        if (PdrUtil.isEquals(str, "name")) {
            this.p0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "name")) {
            this.C = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "email")) {
            this.D = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "url")) {
            this.F = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "name")) {
            this.w = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "code")) {
            this.x = str2;
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_RUNMODE_LIBERATE)) {
            this.f0 = PdrUtil.parseBoolean(str2, this.S, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_H5PLUS)) {
            this.N = PdrUtil.parseBoolean(str2, true, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_funSetUA)) {
            this.O = PdrUtil.parseBoolean(str2, true, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USER_AGENT)) {
            this.H = str2;
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_FULLSCREEN)) {
            this.h = PdrUtil.parseBoolean(str2, this.h, false);
            return;
        }
        if (PdrUtil.isEquals(str, "webcache_path")) {
            this.w1 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "wap2app_running_mode")) {
            this.K = PdrUtil.parseBoolean(str2, false, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LOADED_TIME)) {
            this.L0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_BC)) {
            this.S0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_MODE)) {
            this.V0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)) {
            this.W0 = Boolean.valueOf(str2).booleanValue();
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_ISSTATUS)) {
            this.X0 = Boolean.valueOf(str2).booleanValue();
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_STATUSBAR_COLOR)) {
            this.Z0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_ISATATUS)) {
            this.Y0 = Boolean.valueOf(str2).booleanValue();
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_STATUSBAR_COLOR)) {
            this.a1 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.UNIAPP_WEEX_JS_SERVICE)) {
            this.b1 = Boolean.valueOf(str2).booleanValue();
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSON_KEY_DEBUG_REFRESH)) {
            this.m1 = str2;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.UNI_RESTART_TO_DIRECT)) {
            this.l1 = Boolean.valueOf(str2).booleanValue();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setDirectPage(String str) {
        this.i1 = str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setHideNavBarState(boolean z) {
        this.n = z;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setIAppStatusListener(IApp.IAppStatusListener iAppStatusListener) {
        this.K0 = iAppStatusListener;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setLaunchPageStateListener(IWebviewStateListener iWebviewStateListener) {
        this.q1 = iWebviewStateListener;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setNeedRefreshApp(boolean z) {
        this.f1 = z;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setQuitModel(int i) {
        this.o1 = i;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setRuntimeArgs(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        this.A = str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setStatus(byte b2) {
        this.q = b2;
        if (b2 == 3) {
            this.c1 = System.currentTimeMillis();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setWebAppActivity(Activity activity) {
        this.f6487a = activity;
        a(activity);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0118 A[Catch: Exception -> 0x01b9, MalformedURLException -> 0x01be, TryCatch #4 {MalformedURLException -> 0x01be, Exception -> 0x01b9, blocks: (B:14:0x0058, B:16:0x0063, B:19:0x006b, B:21:0x0071, B:24:0x0078, B:27:0x00a7, B:29:0x00aa, B:31:0x00b8, B:42:0x0111, B:41:0x010e, B:46:0x011b, B:48:0x012e, B:50:0x0134, B:51:0x013c, B:52:0x0143, B:54:0x0154, B:56:0x0165, B:58:0x0173, B:59:0x0175, B:60:0x0177, B:62:0x017f, B:64:0x0187, B:66:0x0190, B:68:0x0198, B:69:0x01a0, B:70:0x01b0, B:73:0x01b5, B:44:0x0118, B:34:0x00c1, B:38:0x00ee, B:35:0x00d8, B:37:0x00db), top: B:93:0x0058, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0143 A[Catch: JSONException -> 0x01b4, Exception -> 0x01b9, MalformedURLException -> 0x01be, TryCatch #0 {JSONException -> 0x01b4, blocks: (B:48:0x012e, B:50:0x0134, B:51:0x013c, B:52:0x0143, B:54:0x0154, B:56:0x0165, B:58:0x0173, B:59:0x0175, B:60:0x0177, B:62:0x017f, B:64:0x0187, B:66:0x0190, B:68:0x0198, B:69:0x01a0, B:70:0x01b0), top: B:90:0x012e, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0154 A[Catch: JSONException -> 0x01b4, Exception -> 0x01b9, MalformedURLException -> 0x01be, TryCatch #0 {JSONException -> 0x01b4, blocks: (B:48:0x012e, B:50:0x0134, B:51:0x013c, B:52:0x0143, B:54:0x0154, B:56:0x0165, B:58:0x0173, B:59:0x0175, B:60:0x0177, B:62:0x017f, B:64:0x0187, B:66:0x0190, B:68:0x0198, B:69:0x01a0, B:70:0x01b0), top: B:90:0x012e, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b0 A[Catch: JSONException -> 0x01b4, Exception -> 0x01b9, MalformedURLException -> 0x01be, TRY_LEAVE, TryCatch #0 {JSONException -> 0x01b4, blocks: (B:48:0x012e, B:50:0x0134, B:51:0x013c, B:52:0x0143, B:54:0x0154, B:56:0x0165, B:58:0x0173, B:59:0x0175, B:60:0x0177, B:62:0x017f, B:64:0x0187, B:66:0x0190, B:68:0x0198, B:69:0x01a0, B:70:0x01b0), top: B:90:0x012e, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // io.dcloud.common.DHInterface.IApp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setWebAppIntent(android.content.Intent r18) {
        /*
            Method dump skipped, instruction units count: 490
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.f.a.e.setWebAppIntent(android.content.Intent):void");
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String shortcutQuit() {
        return this.t1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.common.DHInterface.IApp
    public void showSplash() {
        Activity activity = getActivity();
        if (activity instanceof IOnCreateSplashView) {
            activity.setIntent(this.J0);
            ((IOnCreateSplashView) activity).onCreateSplash(activity);
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean startFromShortCut() {
        return this.s1;
    }

    public void t() {
        this.g1.clear();
        this.h1.clear();
        Activity activity = this.f6487a;
        if (activity != null && (activity instanceof WebAppActivity)) {
            ((WebAppActivity) activity).onAppStop(this.m);
        }
        Logger.d(Logger.AppMgr_TAG, "webapp.onStoped");
        BaseInfo.s_Runing_App_Count--;
        callSysEventListener(ISysEventListener.SysEventType.onWebAppStop, this);
        d();
        PermissionUtil.removeTempPermission(this.f6487a, this.m);
        PlatformUtil.invokeMethod("io.dcloud.feature.apsqh.QHNotifactionReceiver", "doSaveNotifications", null, new Class[]{Context.class}, new Object[]{this.f6487a.getBaseContext()});
        PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "streamappStop", null, new Class[]{Activity.class, String.class}, new Object[]{getActivity(), obtainAppId()});
        b();
        deleteAppTemp();
        PermissionControler.unregisterRootPermission(this.m);
        this.u.e(this);
        if (getIAppStatusListener() != null) {
            getIAppStatusListener().onStoped(false, null);
        }
        this.u.processEvent(IMgr.MgrType.WindowMgr, 25, this);
    }

    public String toString() {
        return this.p0 + "-" + this.m + "-" + super.toString();
    }

    public void u() {
        PermissionControler.registerPermission(this.m, this.x0);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void unregisterSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType) {
        ArrayList<ISysEventListener> arrayList;
        HashMap<ISysEventListener.SysEventType, ArrayList<ISysEventListener>> map = this.y0;
        if (map == null || (arrayList = map.get(sysEventType)) == null) {
            return;
        }
        arrayList.remove(iSysEventListener);
        if (arrayList.isEmpty()) {
            this.y0.remove(sysEventType);
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void updateDirectPage(String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.i1;
        }
        JSONObject jSONObjectD = d(str);
        if (jSONObjectD != null) {
            this.G0 = jSONObjectD;
            if (jSONObjectD != null) {
                e();
                this.u.processEvent(IMgr.MgrType.WindowMgr, 48, this);
            }
        }
    }

    public void v() {
        b(false);
        setStatus((byte) 1);
        this.u.processEvent(IMgr.MgrType.FeatureMgr, 3, this.m);
        Logger.d(Logger.AppMgr_TAG, this.m + " will active change to unrunning");
        this.u.processEvent(null, 0, this);
        WebViewFactory.sUsePermissionWebviews.clear();
    }

    public String w() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", this.m);
            jSONObject.put("version", this.w);
            jSONObject.put("name", this.p0);
            jSONObject.put(com.heytap.mcssdk.d.q, this.x);
            jSONObject.put("description", this.B);
            jSONObject.put("author", this.C);
            jSONObject.put("email", this.D);
            jSONObject.put(IApp.ConfigProperty.CONFIG_LICENSE, this.E);
            jSONObject.put("licensehref", this.F);
            jSONObject.put(IApp.ConfigProperty.CONFIG_FEATURES, new JSONArray((Collection) this.x0));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void x() {
        b(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:283:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x06c1  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x06d9  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0726  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x074d  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0769  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0874  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(java.io.InputStream r61, java.lang.String r62, org.json.JSONObject r63) {
        /*
            Method dump skipped, instruction units count: 2834
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.f.a.e.a(java.io.InputStream, java.lang.String, org.json.JSONObject):boolean");
    }

    public boolean f(String str) {
        if (!this.r && this.t) {
            return false;
        }
        Logger.e("Webapp start " + this.m);
        Activity activity = this.f6487a;
        if (activity != null && (activity instanceof WebAppActivity)) {
            ((WebAppActivity) activity).onAppStart(this.m);
            ((WebAppActivity) this.f6487a).onAppStart(this);
        }
        AppStatus.setAppStatus(this.m, 2);
        BaseInfo.s_Runing_App_Count++;
        this.s = true;
        this.t = !this.r;
        setRuntimeArgs(str);
        return a(5);
    }

    public void g() {
        ArrayList<String> arrayList = this.x0;
        if (arrayList != null) {
            arrayList.clear();
            this.x0 = null;
        }
        HashMap<ISysEventListener.SysEventType, ArrayList<ISysEventListener>> map = this.y0;
        if (map != null) {
            map.clear();
            this.y0 = null;
        }
        this.p1.removeData(this.m);
        this.u = null;
        this.p = null;
        this.b1 = false;
    }

    public boolean c(String str, JSONObject jSONObject) {
        String str2;
        String strObtainAppDataPath = obtainAppDataPath();
        StringBuilder sb = new StringBuilder();
        boolean zB = false;
        sb.append(strObtainAppDataPath.substring(0, strObtainAppDataPath.length() - 1));
        sb.append("_backup");
        String string = sb.toString();
        byte b2 = this.R;
        try {
            if (b2 == 1) {
                str2 = BaseInfo.sCacheFsAppsPath + this.m + File.separatorChar + BaseInfo.APP_WWW_FS_DIR;
                try {
                    DHFile.deleteFile(str2);
                    string = str2.substring(0, str2.length() - 1) + "_backup";
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    g gVar = this.o;
                    gVar.f6490a = true;
                    gVar.b = StringUtil.format(DOMException.JSON_ERROR_INFO, 10, e.getMessage());
                }
            } else {
                if (b2 == 0) {
                    new File(strObtainAppDataPath).renameTo(new File(string));
                }
                str2 = strObtainAppDataPath;
            }
            ZipUtils.upZipFile(new File(str), str2);
            b((byte) 0);
            setAppDataPath(str2);
            zB = b(this.m, jSONObject);
        } catch (Exception e3) {
            e = e3;
            str2 = strObtainAppDataPath;
        }
        if (!zB) {
            Logger.e(Logger.AppMgr_TAG, "unZipWebApp failed pFilePath=" + str);
            b(b2);
            setAppDataPath(strObtainAppDataPath);
            try {
                DHFile.deleteFile(str2);
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            if (b2 == 0) {
                new File(string).renameTo(new File(str2));
            }
        } else {
            if (b2 == 0) {
                try {
                    DHFile.deleteFile(string);
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            BaseInfo.BaseAppInfo baseAppInfo = new BaseInfo.BaseAppInfo(this.m, this.w);
            this.p = baseAppInfo;
            baseAppInfo.saveToBundleData(getActivity());
        }
        return zB;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public InputStream obtainResInStream(String str) {
        return obtainResInStream(null, str);
    }

    public void b(byte b2) {
        this.R = b2;
    }

    public static String b(String str) {
        return (str == null || str.length() <= 0 || str.charAt(0) != '/') ? str : b(str.substring(1));
    }

    public void b(boolean z) {
        this.b.onAppUnActive(this);
        if (z) {
            callSysEventListener(ISysEventListener.SysEventType.onWebAppPause, this);
            callSysEventListener(ISysEventListener.SysEventType.onWebAppBackground, this);
        }
        IApp.IAppStatusListener iAppStatusListener = this.K0;
        if (iAppStatusListener != null) {
            iAppStatusListener.onPause(this, null);
        }
        setStatus((byte) 2);
    }

    private String c(String str) {
        return BaseInfo.sBaseFsSitMapPath + str + "/_sitemap.json";
    }

    private void b() {
        if (r()) {
            return;
        }
        ThreadPool.self().addThreadTask(new d(obtainAppId()));
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2AbsFullPath(String str) {
        return convert2AbsFullPath(null, str);
    }

    private JSONObject a(InputStream inputStream) {
        try {
            if (!this.n1) {
                inputStream = new UnicodeInputStream(inputStream, Charset.defaultCharset().name());
            }
            return new JSONObject(new String(IOUtil.getBytes(inputStream)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // io.dcloud.f.a.f
    public void a(Activity activity) {
        super.a(activity);
        AppStatusBarManager appStatusBarManager = this.k;
        if (appStatusBarManager != null) {
            appStatusBarManager.checkImmersedStatusBar(activity, this.W0);
            this.k.isFullScreen = isFullScreen();
        }
        this.j.mJsonViewOption = JSONUtil.createJSONObject("{}");
        this.f = PdrUtil.parseInt(SP.getBundleData(getActivity(), BaseInfo.PDR, "StatusBarHeight"), 0);
        updateScreenInfo(4);
        this.s1 = false;
        IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(getActivity());
        if (!r() && iActivityHandler != null) {
            HashMap map = new HashMap();
            map.put("v", this.w);
            map.put("appid", this.m);
            map.put("name", this.p0);
            map.put("adid", this.I0);
            map.put("bg", this.T0);
            map.put("img", convert2AbsFullPath(this.U0));
            io.dcloud.a.a(getActivity(), this.m, "save", map);
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey(IntentConst.FROM_SHORT_CUT_STRAT) && extras.getBoolean(IntentConst.FROM_SHORT_CUT_STRAT)) {
                this.s1 = true;
            }
            if (extras != null && extras.containsKey(IntentConst.WEBAPP_ACTIVITY_CREATE_SHORTCUT)) {
                this.u1 = extras.getString(IntentConst.WEBAPP_ACTIVITY_CREATE_SHORTCUT);
            }
            if (extras != null && extras.containsKey("shortcutQuit")) {
                this.t1 = extras.getString("shortcutQuit");
            }
            if (extras != null && extras.containsKey(IntentConst.START_FORCE_SHORT_QUIT)) {
                this.t1 = extras.getString(IntentConst.START_FORCE_SHORT_QUIT);
            }
            if (intent.hasExtra(IntentConst.START_FORCE_SHORT)) {
                this.u1 = intent.getStringExtra(IntentConst.START_FORCE_SHORT);
            }
            if (TextUtils.isEmpty(this.u1)) {
                String launchType = BaseInfo.getLaunchType(intent);
                this.u1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
                if (launchType.equals("scheme")) {
                    this.u1 = SearchIntents.EXTRA_QUERY;
                } else if (this.L) {
                    this.u1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
                } else {
                    String string = SP.getOrCreateBundle(activity, "pdr").getString(AbsoluteConst.TEST_RUN + this.m, null);
                    if (!TextUtils.isEmpty(string) && string.equals("__am=t")) {
                        this.u1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
                    } else {
                        this.u1 = "none";
                    }
                }
            }
        }
        try {
            ADUtils.runThreadCheckADDownload(activity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String a(String str) {
        return this.g0 + str;
    }

    public void a(ICallBack iCallBack) {
        if ((BaseInfo.ISDEBUG || this.f0) && this.R == 1) {
            ThreadPool.self().addThreadTask(new c(iCallBack), true);
        } else {
            iCallBack.onCallBack(0, null);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private boolean a(int i) {
        PermissionUtil.sUseStreamAppPermissionDialogCount = 0;
        WebViewFactory.sUsePermissionWebviews.clear();
        PermissionUtil.removeTempPermission(this.f6487a, this.m);
        Logger.e(B1, "start0 mAppid===" + this.m);
        BaseInfo.sCurrentAppOriginalAppid = this.v;
        BaseInfo.putStartupTimeData(this.m, String.valueOf(System.currentTimeMillis()));
        BaseInfo.sProcessId = (long) Process.myPid();
        String str = B1;
        StringBuilder sb = new StringBuilder();
        sb.append(this.m);
        sb.append(this.R == 1 ? " APP_RUNNING_MODE" : " FS_RUNNING_MODE");
        Logger.i(str, sb.toString());
        u();
        setStatus((byte) 3);
        IApp.IAppStatusListener iAppStatusListener = this.K0;
        if (iAppStatusListener != null) {
            iAppStatusListener.onStart();
        }
        Logger.i(B1, "mLaunchPath=" + this.h0);
        Logger.i("download_manager", "webapp start task begin success appid=" + this.m + " mLaunchPath=" + this.h0);
        String str2 = TestUtil.START_STREAM_APP;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("webapp start appid=");
        sb2.append(this.m);
        TestUtil.print(str2, sb2.toString());
        BaseInfo.setLoadingLaunchePage(true, "start0");
        String stringExtra = getActivity().getIntent().getStringExtra(IntentConst.WEBAPP_ACTIVITY_LAUNCH_PATH);
        if (stringExtra != null && !"".equals(stringExtra.trim())) {
            getActivity().getIntent().removeExtra(IntentConst.WEBAPP_ACTIVITY_LAUNCH_PATH);
            if (!"about:blank".equals(stringExtra)) {
                stringExtra = convert2WebviewFullPath(null, stringExtra);
            }
            this.k0 = true;
        } else if (BaseInfo.isWap2AppAppid(this.m) && !TextUtils.isEmpty(this.j0)) {
            stringExtra = convert2WebviewFullPath(null, this.j0);
        } else {
            stringExtra = convert2WebviewFullPath(null, this.h0);
        }
        if (a((IApp) this) && !new File(a(BaseInfo.sConfigXML)).exists()) {
            stringExtra = TextUtils.isEmpty(this.i0) ? this.j1 : this.i0;
        }
        Uri data = getActivity().getIntent().getData();
        if (data != null && data.toString().endsWith(".html")) {
            stringExtra = data.toString();
        }
        if (this.l1) {
            stringExtra = convert2WebviewFullPath(null, "__uniappview.html");
        }
        Object objProcessEvent = this.u.processEvent(IMgr.MgrType.WindowMgr, i, new Object[]{this, stringExtra, Boolean.valueOf(this.O0), this.R0});
        if (objProcessEvent == null) {
            return true;
        }
        return Boolean.parseBoolean(String.valueOf(objProcessEvent));
    }

    private boolean a(IApp iApp) {
        return (TextUtils.isEmpty(iApp.getOriginalDirectPage()) || iApp.obtainWebAppIntent().hasExtra(IntentConst.DIRECT_PAGE)) ? false : true;
    }

    public boolean a(boolean z) {
        if (z) {
            this.m1 = null;
            this.u.processEvent(IMgr.MgrType.WindowMgr, 76, this);
        }
        setAppDataPath(BaseInfo.sCacheFsAppsPath + this.m + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
        boolean zB = b(this.m, null);
        if (!zB) {
            return zB;
        }
        PermissionUtil.clearUseRejectedCache();
        showSplash();
        this.u.processEvent(IMgr.MgrType.FeatureMgr, 3, this.m);
        callSysEventListener(ISysEventListener.SysEventType.onWebAppReStart, null);
        this.b1 = false;
        TestUtil.record(AbsoluteConst.RUN_5AP_TIME_KEY);
        return a(10);
    }

    private void a() {
        IPdrModule iPdrModuleA;
        if (this.n1) {
            return;
        }
        if (!SDK.isUniMPSDK()) {
            io.dcloud.f.b.a.f().a(this.f6487a);
        }
        if (r() || (iPdrModuleA = io.dcloud.f.b.e.a().a("commit")) == null) {
            return;
        }
        Object[] objArr = new Object[3];
        objArr[0] = this;
        objArr[1] = this.I0;
        JSONObject jSONObject = this.u.f;
        objArr[2] = jSONObject != null ? jSONObject.optString("version") : "0.1";
        iPdrModuleA.execute("start_up", objArr);
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
    public IFrameView a(IWebviewStateListener iWebviewStateListener) {
        u();
        return (IFrameView) this.u.processEvent(IMgr.MgrType.WindowMgr, 17, new Object[]{this, convert2WebviewFullPath(null, this.h0), iWebviewStateListener});
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
    public IFrameView a(IWebviewStateListener iWebviewStateListener, IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        u();
        return (IFrameView) this.u.processEvent(IMgr.MgrType.WindowMgr, 17, new Object[]{this, convert2WebviewFullPath(null, this.h0), iWebviewStateListener, iDCloudWebviewClientListener});
    }

    private boolean a(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType) {
        if (!(iSysEventListener instanceof IBoot) || PdrUtil.parseBoolean(String.valueOf(this.u.processEvent(null, 1, iSysEventListener)), false, false)) {
            return true;
        }
        return !(sysEventType == ISysEventListener.SysEventType.onStart || sysEventType == ISysEventListener.SysEventType.onStop || sysEventType == ISysEventListener.SysEventType.onPause || sysEventType == ISysEventListener.SysEventType.onResume) || (iSysEventListener instanceof ReceiveSystemEventVoucher);
    }

    public static boolean a(ISysEventListener.SysEventType sysEventType) {
        return (sysEventType == ISysEventListener.SysEventType.onKeyDown || sysEventType == ISysEventListener.SysEventType.onKeyUp || sysEventType == ISysEventListener.SysEventType.onKeyLongPress) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0240 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0209 A[Catch: Exception -> 0x0290, LOOP:0: B:72:0x0207->B:73:0x0209, LOOP_END, TryCatch #0 {Exception -> 0x0290, blocks: (B:61:0x01df, B:63:0x01e6, B:67:0x01f4, B:69:0x01fc, B:71:0x0202, B:73:0x0209, B:74:0x022a, B:76:0x0230, B:78:0x0240, B:80:0x0245, B:81:0x025c, B:83:0x0263, B:84:0x0266, B:86:0x0280, B:65:0x01ee), top: B:106:0x01df, inners: #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0230 A[Catch: Exception -> 0x0290, TRY_LEAVE, TryCatch #0 {Exception -> 0x0290, blocks: (B:61:0x01df, B:63:0x01e6, B:67:0x01f4, B:69:0x01fc, B:71:0x0202, B:73:0x0209, B:74:0x022a, B:76:0x0230, B:78:0x0240, B:80:0x0245, B:81:0x025c, B:83:0x0263, B:84:0x0266, B:86:0x0280, B:65:0x01ee), top: B:106:0x01df, inners: #11 }] */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v27, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(java.lang.String r14, org.json.JSONObject r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.f.a.e.a(java.lang.String, org.json.JSONObject):boolean");
    }

    private String a(byte b2) {
        byte b3 = this.R;
        if (b3 == 1) {
            return BaseInfo.sBaseResAppsFullPath + this.m + "/" + BaseInfo.APP_WWW_FS_DIR;
        }
        if (b3 != 0) {
            return null;
        }
        return "file://" + this.g0;
    }

    public void a(String str, int i) {
        this.y1.put(str, Integer.valueOf(i));
        if (TextUtils.isEmpty(this.z1)) {
            this.z1 = str + ContainerUtils.KEY_VALUE_DELIMITER + i;
        } else {
            this.z1 += "&" + str + ContainerUtils.KEY_VALUE_DELIMITER + i;
        }
        SP.getOrCreateBundle(getActivity(), this.m + "_1").edit().putString("Authorize", this.z1).commit();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(org.json.JSONObject r4, io.dcloud.f.a.g r5) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L1e
            java.lang.String r1 = "control"
            boolean r2 = r4.has(r1)
            if (r2 == 0) goto L1e
            r2 = 0
            java.lang.String r4 = r4.optString(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto L1f
            java.lang.String r1 = "uni-v3"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L1f
        L1e:
            r2 = 1
        L1f:
            if (r2 != 0) goto L2d
            r5.f6490a = r0
            java.lang.String r4 = io.dcloud.common.constant.DOMException.MSG_RUNTIME_COMPONENTS_MODE_NOT_SUPPORT
            r0 = 1250(0x4e2, float:1.752E-42)
            java.lang.String r4 = io.dcloud.common.constant.DOMException.toJSON(r0, r4)
            r5.b = r4
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.f.a.e.a(org.json.JSONObject, io.dcloud.f.a.g):boolean");
    }
}
