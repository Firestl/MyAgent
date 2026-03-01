package io.dcloud.f.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.constant.DataInterface;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.DataUtil;
import io.dcloud.common.util.ErrorDialogUtil;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.internal.sdk.SDK;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends AbsMgr implements IMgr.AppEvent {
    public static String i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public io.dcloud.f.a.d f6463a;
    public ArrayList<String> b;
    public ArrayList<io.dcloud.f.a.e> c;
    public io.dcloud.f.a.c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AlertDialog f6464e;
    public JSONObject f;
    public AlertDialog g;
    public AlertDialog h;

    /* JADX INFO: renamed from: io.dcloud.f.a.a$a, reason: collision with other inner class name */
    public class C0159a implements ICallBack {
        public C0159a(a aVar) {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            String unused = a.i = String.valueOf(obj);
            return null;
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6465a;

        public b(a aVar, Context context) {
            this.f6465a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                BaseInfo.uniVersionV3 = new JSONObject(new BufferedReader(new InputStreamReader(this.f6465a.getAssets().open((!BaseInfo.SyncDebug || PlatformUtil.getResInputStream("uni-jsframework-dev.js") == null || SDK.isUniMPSDK()) ? "uni-jsframework.js" : "uni-jsframework-dev.js"))).readLine().substring(2)).optString("version");
            } catch (Exception unused) {
            }
        }
    }

    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f6466a;
        public final /* synthetic */ String b;
        public final /* synthetic */ io.dcloud.f.a.e c;
        public final /* synthetic */ CheckBox d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6467e;
        public final /* synthetic */ io.dcloud.f.a.e f;
        public final /* synthetic */ io.dcloud.f.a.e g;
        public final /* synthetic */ boolean h;

        public c(Activity activity, String str, io.dcloud.f.a.e eVar, CheckBox checkBox, String str2, io.dcloud.f.a.e eVar2, io.dcloud.f.a.e eVar3, boolean z) {
            this.f6466a = activity;
            this.b = str;
            this.c = eVar;
            this.d = checkBox;
            this.f6467e = str2;
            this.f = eVar2;
            this.g = eVar3;
            this.h = z;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i != -2) {
                if (i != -3 && i == -1) {
                    if (this.d.isChecked()) {
                        SP.setBundleData(this.f6466a, "pdr", AbsoluteConst.TEST_RUN + this.b, "__am=t");
                    }
                    a.this.a(this.f6466a, this.b, this.f6467e, this.c, this.f, this.g, this.h);
                    a.this.f6464e.dismiss();
                    return;
                }
                return;
            }
            a.this.f6464e.dismiss();
            IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(this.f6466a);
            if (iActivityHandler != null) {
                iActivityHandler.closeAppStreamSplash(this.b);
                BaseInfo.setLoadingLaunchePage(false, "closeSplashScreen0");
                if (a.this.d.e() == 0) {
                    this.f6466a.finish();
                    return;
                }
                io.dcloud.f.a.e eVar = this.c;
                if (eVar != null) {
                    eVar.x();
                }
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                this.f6466a.startActivity(intent);
            }
        }
    }

    public class d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f6468a;

        public d(a aVar, Activity activity) {
            this.f6468a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://ask.dcloud.net.cn/article/35627"));
            this.f6468a.startActivity(intent);
        }
    }

    public class e implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f6469a;

        public e(a aVar, Activity activity) {
            this.f6469a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://ask.dcloud.net.cn/article/35877"));
            this.f6469a.startActivity(intent);
        }
    }

    public class f implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.f.a.e f6470a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ String c;

        public f(a aVar, io.dcloud.f.a.e eVar, boolean z, String str) {
            this.f6470a = eVar;
            this.b = z;
            this.c = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (this.f6470a.a(this.b)) {
                return null;
            }
            Logger.e(Logger.AppMgr_TAG, "reboot " + this.c + " app failed !!!");
            return null;
        }
    }

    public class g implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.f.a.e f6471a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ boolean d;

        /* JADX INFO: renamed from: io.dcloud.f.a.a$g$a, reason: collision with other inner class name */
        public class C0160a implements ICallBack {
            public C0160a() {
            }

            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                g gVar = g.this;
                a.this.a(gVar.f6471a, gVar.b, gVar.c, gVar.d);
                return null;
            }
        }

        public g(io.dcloud.f.a.e eVar, String str, String str2, boolean z) {
            this.f6471a = eVar;
            this.b = str;
            this.c = str2;
            this.d = z;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (!WebViewFactory.isOther() || WebViewFactory.isOtherInitialised() || WebViewFactory.isIsLoadOtherTimeOut()) {
                a.this.a(this.f6471a, this.b, this.c, this.d);
                return null;
            }
            WebViewFactory.setOtherCallBack(new C0160a());
            return null;
        }
    }

    public class h implements Runnable {
        public h(a aVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(AbsoluteConst.STREAMAPP_KEY_ROOTPATH + "splash_temp/");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public a(ICore iCore) {
        super(iCore, Logger.AppMgr_TAG, IMgr.MgrType.AppMgr);
        this.f6463a = null;
        this.b = new ArrayList<>(1);
        this.c = new ArrayList<>(1);
        this.d = null;
        this.f = null;
        if (iCore != null) {
            a(iCore.obtainContext());
        }
        c();
        b();
        d();
        a();
        this.d = new io.dcloud.f.a.c(this);
    }

    private void d(io.dcloud.f.a.e eVar) {
        Object objNewInstance = PlatformUtil.newInstance("android.app.ActivityManager$TaskDescription", new Class[]{String.class, Bitmap.class}, new Object[]{eVar.obtainAppName(), BitmapFactory.decodeResource(getContext().getResources(), getContext().getApplicationInfo().icon)});
        PlatformUtil.invokeMethod(eVar.getActivity(), "setTaskDescription", new Class[]{objNewInstance.getClass()}, objNewInstance);
    }

    public void b() {
        io.dcloud.f.a.g gVar;
        HashMap<String, BaseInfo.BaseAppInfo> map = BaseInfo.mBaseAppInfoSet;
        if (map == null || map.isEmpty()) {
            return;
        }
        Set<String> setKeySet = BaseInfo.mBaseAppInfoSet.keySet();
        int size = setKeySet.size();
        String[] strArr = new String[size];
        setKeySet.toArray(strArr);
        for (int i2 = 0; i2 < size; i2++) {
            String str = strArr[i2];
            BaseInfo.BaseAppInfo baseAppInfo = BaseInfo.mBaseAppInfoSet.get(str);
            if (!BaseInfo.mUnInstalledAppInfoSet.containsKey(str) && !b(str)) {
                io.dcloud.f.a.e eVarB = b(BaseInfo.sBaseResAppsPath + str, str);
                if (eVarB != null && (gVar = eVarB.o) != null) {
                    if (gVar.f6490a) {
                        Logger.e("AppMgr", str + "  app error," + eVarB.o);
                    } else {
                        eVarB.p = baseAppInfo;
                        c(eVarB);
                    }
                }
            }
        }
    }

    public io.dcloud.f.a.e c(String str) {
        return a((Activity) null, str);
    }

    @Override // io.dcloud.common.DHInterface.AbsMgr
    public void dispose() {
        ArrayList<io.dcloud.f.a.e> arrayList = this.c;
        if (arrayList != null) {
            Iterator<io.dcloud.f.a.e> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().g();
            }
        }
        this.c.clear();
        this.b.clear();
        io.dcloud.f.a.c cVar = this.d;
        if (cVar != null) {
            cVar.a();
        }
        this.d = null;
        ThreadPool.self().addThreadTask(new h(this));
    }

    public void e(io.dcloud.f.a.e eVar) {
        this.d.b(eVar.m);
        b(eVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:173:0x043b  */
    @Override // io.dcloud.common.DHInterface.IMgr
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object processEvent(io.dcloud.common.DHInterface.IMgr.MgrType r24, int r25, java.lang.Object r26) {
        /*
            Method dump skipped, instruction units count: 1766
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.f.a.a.processEvent(io.dcloud.common.DHInterface.IMgr$MgrType, int, java.lang.Object):java.lang.Object");
    }

    public void c(io.dcloud.f.a.e eVar) {
        this.b.add(eVar.obtainAppId());
        this.c.add(eVar);
    }

    private void a() {
        if (PdrUtil.isEmpty(i)) {
            DataUtil.datToJsString(BaseInfo.sUniNViewServiceJsPath, new C0159a(this));
        }
    }

    public void c() {
        io.dcloud.f.a.g gVar;
        HashMap<String, BaseInfo.BaseAppInfo> map = BaseInfo.mInstalledAppInfoSet;
        if (map == null || map.isEmpty()) {
            return;
        }
        Set<String> setKeySet = BaseInfo.mInstalledAppInfoSet.keySet();
        int size = setKeySet.size();
        String[] strArr = new String[size];
        setKeySet.toArray(strArr);
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            String str = strArr[i2];
            if (!BaseInfo.mUnInstalledAppInfoSet.containsKey(str) && !b(str)) {
                io.dcloud.f.a.e eVarB = b(BaseInfo.sCacheFsAppsPath + str, str);
                if (eVarB != null && (gVar = eVarB.o) != null && !gVar.f6490a) {
                    eVarB.deleteAppTemp();
                    if (SDK.isUniMPSDK()) {
                        eVarB.f0 = true;
                    } else {
                        eVarB.f0 = false;
                    }
                    c(eVarB);
                } else {
                    BaseInfo.mInstalledAppInfoSet.get(str).clearBundleData();
                    BaseInfo.mInstalledAppInfoSet.remove(str);
                    z = true;
                }
            }
        }
        if (z) {
            BaseInfo.saveInstalledAppInfo(getContext());
        }
    }

    public void d() {
        File file = new File(BaseInfo.sURDFilePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            DHFile.copyAssetsFile("data/dcloud_url.json", file.getAbsolutePath());
        }
        if (file.exists()) {
            try {
                this.f = new JSONObject(new String(DHFile.readAll(file)));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void a(Context context) {
        if (TextUtils.isEmpty(BaseInfo.uniVersionV3)) {
            ThreadPool.self().addThreadTask(new b(this, context), true);
        }
    }

    private void a(io.dcloud.f.a.e eVar, String str, boolean z) {
        if (eVar != null) {
            String strObtainConfigProperty = eVar.obtainConfigProperty(IApp.ConfigProperty.CONFIG_UNIAPP_CONTROL);
            if (!TextUtils.isEmpty(strObtainConfigProperty) && strObtainConfigProperty.equals(AbsoluteConst.UNI_V3)) {
                BaseInfo.isFirstRun = false;
                eVar.showSplash();
                AppRuntime.restartWeex(eVar.getActivity().getApplication(), new f(this, eVar, z, str), eVar.m);
                return;
            } else {
                if (eVar.a(z)) {
                    return;
                }
                Logger.e(Logger.AppMgr_TAG, "reboot " + str + " app failed !!!");
                return;
            }
        }
        Logger.e(Logger.AppMgr_TAG, "not found " + str + " app!!!");
    }

    public void b(io.dcloud.f.a.e eVar) {
        this.b.remove(eVar.m);
        this.c.remove(eVar);
    }

    private void a(io.dcloud.f.a.e eVar) {
        if (SDK.isUniMPSDK() && SDK.isEnableBackground) {
            d(eVar);
        }
    }

    private boolean b(String str) {
        return this.b.contains(str);
    }

    private io.dcloud.f.a.e b(String str, String str2) {
        return a(str, str2);
    }

    public void a(Activity activity, String str, String str2, io.dcloud.f.a.e eVar, io.dcloud.f.a.e eVar2, io.dcloud.f.a.e eVar3, boolean z) {
        Log.i("ylyl", "startOneApp " + str);
        BaseInfo.sLastRunApp = str;
        BaseInfo.CmtInfo cmitInfo = BaseInfo.getCmitInfo(str);
        if (cmitInfo.needUpdate) {
            cmitInfo.templateVersion = eVar2.z;
            cmitInfo.rptCrs = eVar2.I;
            cmitInfo.rptJse = eVar2.J;
            cmitInfo.plusLauncher = BaseInfo.getLaunchType(eVar2.obtainWebAppIntent());
            cmitInfo.sfd = DataInterface.getStreamappFrom(eVar2.obtainWebAppIntent());
            cmitInfo.needUpdate = false;
        }
        if (!io.dcloud.f.b.g.a() && eVar2.q == 4) {
            ErrorDialogUtil.checkAppKeyErrorTips(activity);
            return;
        }
        if (eVar2.q == 3) {
            eVar2.q = eVar2.q() ? eVar2.q : (byte) 2;
        }
        if (eVar != null && eVar != eVar2 && eVar != eVar3) {
            eVar.x();
        }
        byte b2 = eVar2.q;
        if (b2 == 1 || ((z && !eVar2.s) || ((eVar2.t && eVar2.r) || !z))) {
            Logger.d(Logger.AppMgr_TAG, str + " will unrunning change to active");
            eVar2.a(activity);
            processEvent(IMgr.MgrType.WindowMgr, 4, new Object[]{eVar2, str});
            eVar2.a(new g(eVar2, str, str2, z));
        } else if (b2 == 2) {
            Logger.d(Logger.AppMgr_TAG, str + " will unactive change to active");
            eVar2.c();
        } else {
            Logger.d(Logger.AppMgr_TAG, str + " is active");
        }
        if (SDK.isUniMPSDK() && Build.VERSION.SDK_INT >= 21) {
            a(eVar2);
        }
        if (eVar3 == null || eVar3 == eVar2) {
            return;
        }
        eVar3.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(io.dcloud.f.a.e eVar, String str, String str2, boolean z) {
        if (this.d == null) {
            return;
        }
        boolean zF = z ? eVar.f(str2) : eVar.g(str2);
        if (!eVar.s && eVar.r) {
            eVar.f(str2);
        }
        if (zF) {
            this.d.a(str, eVar);
            return;
        }
        Logger.e(Logger.AppMgr_TAG, str + " run failed!!!");
    }

    public io.dcloud.f.a.e a(Activity activity, String str) {
        return a(activity, str, true);
    }

    private io.dcloud.f.a.e a(String str, boolean z) {
        return a((Activity) null, str, z);
    }

    private io.dcloud.f.a.e a(Activity activity, String str, boolean z) {
        int iIndexOf;
        io.dcloud.f.a.e eVar = (!this.b.contains(str) || (iIndexOf = this.b.indexOf(str)) < 0) ? null : this.c.get(iIndexOf);
        if (eVar == null && z) {
            eVar = new io.dcloud.f.a.e(this, str, (byte) 0);
            eVar.setAppDataPath(BaseInfo.sCacheFsAppsPath + str + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
            if (eVar.f6487a == null) {
                eVar.f6487a = activity;
            }
            if (activity != null) {
                eVar.setWebAppIntent(activity.getIntent());
            }
            eVar.b(str, null);
            if (eVar.o.f6490a) {
                eVar.m = str;
            }
            c(eVar);
        } else if (eVar != null && activity != null) {
            if (eVar.f6487a == null) {
                eVar.f6487a = activity;
            }
            if (eVar.f6487a.getIntent() != null) {
                if (eVar.manifestBeParsed()) {
                    eVar.setWebAppIntent(eVar.f6487a.getIntent());
                }
            } else {
                eVar.f6487a.setIntent(eVar.obtainWebAppIntent());
            }
            if (!eVar.r) {
                eVar.b(str, null);
            }
        }
        return eVar;
    }

    public io.dcloud.f.a.e a(String str, String str2, String str3, byte b2) {
        io.dcloud.f.a.e eVarA = a(str, false);
        if (eVarA == null) {
            eVarA = new io.dcloud.f.a.e(this, str, b2);
            eVarA.q = (byte) 3;
            eVarA.m = str;
            if (!PdrUtil.isEmpty(str2)) {
                eVarA.setAppDataPath(str2);
            }
            eVarA.h0 = str3;
            c(eVarA);
            this.d.a(str, eVarA);
        }
        return eVarA;
    }

    public io.dcloud.f.a.e a(String str, String str2) {
        return a(str, str2, (JSONObject) null);
    }

    public io.dcloud.f.a.e a(String str, String str2, JSONObject jSONObject) throws Throwable {
        Exception e2;
        PackageInfo apkInfo;
        io.dcloud.f.a.e eVarA = a(str2, false);
        if (eVarA != null) {
            try {
                eVarA.o.a();
            } catch (Exception e3) {
                e2 = e3;
                e2.printStackTrace();
                Logger.e(Logger.AppMgr_TAG, "installWebApp " + str + " is Illegal path");
            }
        }
        boolean z = true;
        if (!DHFile.isExist(str) && !PdrUtil.isDeviceRootDir(str)) {
            boolean zContains = str.substring(str.lastIndexOf(47)).contains(".wgt");
            resInputStream = zContains ? PlatformUtil.getResInputStream(str) : null;
            if (eVarA == null) {
                eVarA = new io.dcloud.f.a.e(this, str2, (byte) 1);
            }
            if (!zContains && resInputStream == null) {
                eVarA.setAppDataPath(str + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
                eVarA.b(str2, jSONObject);
            } else {
                eVarA.b(resInputStream);
            }
        } else {
            boolean zIsFile = new File(str).isFile();
            if (zIsFile) {
                if (zIsFile && str.toLowerCase().endsWith(".wgtu")) {
                    if (eVarA != null) {
                        z = false;
                    }
                    if (z) {
                        eVarA = new io.dcloud.f.a.e(this, str2, (byte) 0);
                    }
                    eVarA.a(str, jSONObject);
                    io.dcloud.f.a.g gVar = eVarA.o;
                    gVar.c = false;
                    gVar.d = false;
                } else if (zIsFile && str.toLowerCase().endsWith(".wgt")) {
                    boolean z2 = eVarA == null;
                    eVarA.o.d = true;
                    if (z2) {
                        io.dcloud.f.a.e eVar = new io.dcloud.f.a.e(this, str2, (byte) 0);
                        try {
                            eVar.m = str2;
                            eVar.setAppDataPath(BaseInfo.sCacheFsAppsPath + str2 + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
                            eVarA = eVar;
                        } catch (Exception e4) {
                            e2 = e4;
                            eVarA = eVar;
                            e2.printStackTrace();
                            Logger.e(Logger.AppMgr_TAG, "installWebApp " + str + " is Illegal path");
                        }
                    }
                    boolean zC = eVarA.c(str, jSONObject);
                    eVarA.o.d = false;
                    if (zC && z2) {
                        c(eVarA);
                    }
                } else if (zIsFile && str.toLowerCase().endsWith(".apk")) {
                    try {
                        apkInfo = PlatformUtil.parseApkInfo(getContext(), str);
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        eVarA.o.b = StringUtil.format(DOMException.JSON_ERROR_INFO, 10, e5.getMessage());
                        apkInfo = null;
                    }
                    if (apkInfo == null) {
                        eVarA.o.f6490a = true;
                    } else {
                        eVarA.o.f6490a = false;
                        String str3 = apkInfo.versionName;
                        String str4 = apkInfo.packageName;
                        String string = getContext().getPackageManager().getApplicationLabel(apkInfo.applicationInfo).toString();
                        if (string == null) {
                            string = "";
                        }
                        eVarA.o.b = StringUtil.format("{pname:'%s',version:'%s',name:'%s'}", str4, str3, string);
                        PlatformUtil.openFileBySystem(getContext(), str, null, null, null);
                    }
                } else {
                    io.dcloud.f.a.g gVar2 = eVarA.o;
                    gVar2.f6490a = true;
                    gVar2.b = StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(DOMException.CODE_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED), DOMException.MSG_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED);
                }
            } else {
                if (eVarA != null) {
                    eVarA.b((byte) 0);
                } else {
                    eVarA = new io.dcloud.f.a.e(this, str2, (byte) 0);
                }
                eVarA.setAppDataPath(str + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
                eVarA.b(str2, jSONObject);
            }
        }
        IOUtil.close(resInputStream);
        return eVarA;
    }
}
