package io.dcloud.f.b;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.zxing.client.android.ScanFromWebPageManager;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IBoot;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.AsyncTaskHandler;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.DownloadUtil;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.ui.l;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.common.util.db.DCStorage;
import io.dcloud.common.util.net.NetMgr;
import io.dcloud.feature.internal.sdk.SDK;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c implements ICore {
    public static c i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f6496a = false;
    public Context b = null;
    public AbsMgr c = null;
    public AbsMgr d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbsMgr f6497e = null;
    public AbsMgr f = null;
    public ICore.ICoreStatusListener g = null;
    public HashMap<String, IBoot> h = null;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6498a;

        public a(c cVar, Context context) {
            this.f6498a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JSONObject jSONObject = new JSONObject(new String(NetTool.httpGet(StringUtil.format("https://service.dcloud.net.cn/sta/so?p=a&pn=%s&ver=%s&appid=%s", this.f6498a.getPackageName(), this.f6498a.getPackageManager().getPackageInfo(this.f6498a.getPackageName(), 0).versionName, BaseInfo.sDefaultBootApp), false), "utf-8"));
                if (jSONObject.has(ScanFromWebPageManager.RETURN_URL_PARAM) && jSONObject.optInt(ScanFromWebPageManager.RETURN_URL_PARAM) == 0) {
                    SP.setBundleData(this.f6498a, BaseInfo.PDR, AbsoluteConst.XML_NS, "true");
                }
            } catch (Exception unused) {
            }
        }
    }

    public class b implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IApp f6499a;
        public final /* synthetic */ Activity b;
        public final /* synthetic */ String c;

        public b(IApp iApp, Activity activity, String str) {
            this.f6499a = iApp;
            this.b = activity;
            this.c = str;
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
        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            IApp iApp = this.f6499a;
            if (iApp != null) {
                c.this.a(this.b, iApp);
            }
            Object objDispatchEvent = c.this.dispatchEvent(IMgr.MgrType.WindowMgr, 32, new Object[]{this.b, this.c});
            if ((objDispatchEvent instanceof Boolean ? ((Boolean) objDispatchEvent).booleanValue() : false) || !TextUtils.equals(BaseInfo.sLastRunApp, this.c)) {
                return;
            }
            BaseInfo.sLastRunApp = null;
            this.b.finish();
        }
    }

    /* JADX INFO: renamed from: io.dcloud.f.b.c$c, reason: collision with other inner class name */
    public class C0164c implements AsyncTaskHandler.IAsyncTaskListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6500a;
        public final /* synthetic */ Activity b;
        public final /* synthetic */ String c;

        public C0164c(String str, Activity activity, String str2) {
            this.f6500a = str;
            this.b = activity;
            this.c = str2;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        /* JADX WARN: Multi-variable type inference failed */
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
        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
            c cVar = c.this;
            Activity activity = this.b;
            cVar.a(activity, this.f6500a, this.c, activity instanceof IOnCreateSplashView ? (IOnCreateSplashView) activity : null);
            if (!SDK.isUniMPSDK() || obj == null) {
                return;
            }
            ComponentCallbacks2 componentCallbacks2 = this.b;
            if (componentCallbacks2 instanceof IActivityHandler) {
                ((IActivityHandler) componentCallbacks2).onAsyncStartAppEnd(this.f6500a, obj);
            }
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public Object onExecuting() {
            try {
                String str = TextUtils.isEmpty(this.f6500a) ? BaseInfo.sDefaultBootApp : this.f6500a;
                DCStorage dCStorage = DCStorage.getDCStorage(this.b);
                if (dCStorage != null) {
                    dCStorage.checkSPstorageToDB(this.b, str);
                }
            } catch (Exception unused) {
            }
            if (SDK.isUniMPSDK()) {
                ComponentCallbacks2 componentCallbacks2 = this.b;
                if (componentCallbacks2 instanceof IActivityHandler) {
                    return ((IActivityHandler) componentCallbacks2).onAsyncStartAppStart(this.f6500a);
                }
            }
            return 0;
        }
    }

    public static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6501a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[IMgr.MgrType.values().length];
            b = iArr;
            try {
                iArr[IMgr.MgrType.AppMgr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[IMgr.MgrType.NetMgr.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[IMgr.MgrType.FeatureMgr.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[IMgr.MgrType.WindowMgr.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ISysEventListener.SysEventType.values().length];
            f6501a = iArr2;
            try {
                iArr2[ISysEventListener.SysEventType.onStart.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6501a[ISysEventListener.SysEventType.onStop.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6501a[ISysEventListener.SysEventType.onPause.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f6501a[ISysEventListener.SysEventType.onResume.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void b(Context context) {
        TestUtil.record("core", Thread.currentThread());
        this.f = new io.dcloud.h.b(this);
        this.h = (HashMap) dispatchEvent(IMgr.MgrType.FeatureMgr, 0, this.b);
        BaseInfo.parseControl(context, this, this.g);
        Logger.initLogger(context);
        DeviceInfo.init(context);
        this.d = new io.dcloud.f.a.a(this);
        this.c = new l(this);
        this.f6497e = new NetMgr(this);
        this.f6496a = true;
        try {
            a(context);
        } catch (Exception e2) {
            Logger.e("initSDKData " + e2.getLocalizedMessage());
        }
    }

    public boolean c(Activity activity) {
        DownloadUtil.getInstance(activity).stop();
        PlatformUtil.invokeMethod("io.dcloud.feature.apsqh.QHNotifactionReceiver", "doSaveNotifications", null, new Class[]{Context.class}, new Object[]{activity.getBaseContext()});
        try {
            ISysEventListener.SysEventType sysEventType = ISysEventListener.SysEventType.onStop;
            onActivityExecute(activity, sysEventType, null);
            this.f6496a = false;
            BaseInfo.setLoadingLaunchePage(false, "onStop");
            a(activity, sysEventType, (Object) null);
            i = null;
            this.c.dispose();
            this.c = null;
            this.d.dispose();
            this.d = null;
            this.f6497e.dispose();
            this.f6497e = null;
            this.f.dispose();
            this.f = null;
            Logger.e(Logger.MAIN_TAG, "core exit");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return true;
    }

    @Override // io.dcloud.common.DHInterface.ICore
    public Object dispatchEvent(IMgr.MgrType mgrType, int i2, Object obj) {
        AbsMgr absMgr;
        if (mgrType == null) {
            return a(i2, obj);
        }
        try {
            if (i == null) {
                i = this;
            }
            int i3 = d.b[mgrType.ordinal()];
            if (i3 == 1) {
                AbsMgr absMgr2 = i.d;
                if (absMgr2 != null) {
                    return absMgr2.processEvent(mgrType, i2, obj);
                }
            } else if (i3 == 2) {
                AbsMgr absMgr3 = i.f6497e;
                if (absMgr3 != null) {
                    return absMgr3.processEvent(mgrType, i2, obj);
                }
            } else if (i3 == 3) {
                AbsMgr absMgr4 = i.f;
                if (absMgr4 != null) {
                    return absMgr4.processEvent(mgrType, i2, obj);
                }
            } else if (i3 == 4 && (absMgr = i.c) != null) {
                return absMgr.processEvent(mgrType, i2, obj);
            }
        } catch (Exception e2) {
            Logger.w("Core.dispatchEvent", e2);
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.ICore
    public Context obtainContext() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.common.DHInterface.ICore
    public boolean onActivityExecute(Activity activity, ISysEventListener.SysEventType sysEventType, Object obj) {
        String string;
        Bundle extras;
        if (obj instanceof IApp) {
            string = ((IApp) obj).obtainAppId();
        } else {
            string = BaseInfo.sRuntimeMode != null ? null : BaseInfo.sDefaultBootApp;
            Intent intent = activity.getIntent();
            if (intent != null && (extras = intent.getExtras()) != null && extras.containsKey("appid")) {
                string = extras.getString("appid");
            }
        }
        Object objDispatchEvent = dispatchEvent(IMgr.MgrType.AppMgr, 1, new Object[]{sysEventType, obj, string});
        ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onKeyUp;
        if (!sysEventType.equals(sysEventType2) || objDispatchEvent == null || ((Boolean) objDispatchEvent).booleanValue() || ((Integer) ((Object[]) obj)[0]).intValue() != 4) {
            return Boolean.parseBoolean(String.valueOf(objDispatchEvent));
        }
        if (sysEventType.equals(sysEventType2)) {
            if (activity instanceof IActivityHandler) {
                ((IActivityHandler) activity).closeAppStreamSplash(string);
            }
            a(activity, activity.getIntent(), (IApp) null, string);
        }
        return true;
    }

    @Override // io.dcloud.common.DHInterface.ICore
    public void setmCoreListener(ICore.ICoreStatusListener iCoreStatusListener) {
        this.g = iCoreStatusListener;
    }

    public static c a(Context context, ICore.ICoreStatusListener iCoreStatusListener) {
        if (i == null) {
            i = new c();
        }
        c cVar = i;
        cVar.b = context;
        cVar.g = iCoreStatusListener;
        SDK.initSDK(cVar);
        return i;
    }

    public void a(Activity activity, Bundle bundle, SDK.IntegratedMode integratedMode) {
        DownloadUtil.getInstance(activity);
        WebViewFactory.resetSysWebViewState();
        a();
        BaseInfo.sRuntimeMode = integratedMode;
        if (integratedMode != null) {
            BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT = true;
        }
        if (this.f6496a) {
            return;
        }
        DCloudAdapterUtil.getIActivityHandler(activity);
        b(activity.getApplicationContext());
        Logger.i("Core onInit mode=" + integratedMode);
        a(activity, ISysEventListener.SysEventType.onStart, bundle);
        Logger.i("Core onInit mCoreListener=" + this.g);
        try {
            SDK.IntegratedMode integratedMode2 = BaseInfo.sRuntimeMode;
            if (integratedMode2 != null && integratedMode2 != SDK.IntegratedMode.RUNTIME) {
                ICore.ICoreStatusListener iCoreStatusListener = this.g;
                if (iCoreStatusListener != null) {
                    iCoreStatusListener.onCoreInitEnd(this);
                }
            } else {
                ICore.ICoreStatusListener iCoreStatusListener2 = this.g;
                if (iCoreStatusListener2 != null) {
                    iCoreStatusListener2.onCoreInitEnd(this);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b(Activity activity) {
        ISysEventListener.SysEventType sysEventType = ISysEventListener.SysEventType.onResume;
        a(activity, sysEventType, (Object) null);
        this.f6497e.onExecute(sysEventType, null);
        onActivityExecute(activity, sysEventType, null);
    }

    private void a(Context context) {
        if (BaseInfo.sRuntimeMode == null || BaseInfo.s_Is_DCloud_Packaged || !BaseInfo.existsBase() || !TextUtils.isEmpty(SP.getBundleData(context, BaseInfo.PDR, AbsoluteConst.XML_NS)) || SDK.isUniMPSDK()) {
            return;
        }
        ThreadPool.self().addThreadTask(new a(this, context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, IApp iApp) {
        onActivityExecute(activity, ISysEventListener.SysEventType.onWebAppStop, iApp);
    }

    public void a(Activity activity, Intent intent, IApp iApp, String str) {
        boolean z = this.g != null ? !r6.onCoreStop() : true;
        if (iApp != null) {
            dispatchEvent(IMgr.MgrType.AppMgr, 13, iApp);
        }
        if (z) {
            IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(activity);
            TextUtils.equals(activity.getIntent().getStringExtra("appid"), str);
            if (iActivityHandler != null && iApp == null) {
                if (MobilePhoneModel.HUAWEI.equalsIgnoreCase(Build.MANUFACTURER) && Build.VERSION.SDK_INT >= 24) {
                    if (iApp != null) {
                        a(activity, iApp);
                    }
                    Object objDispatchEvent = dispatchEvent(IMgr.MgrType.WindowMgr, 32, new Object[]{activity, str});
                    if (!(objDispatchEvent instanceof Boolean ? ((Boolean) objDispatchEvent).booleanValue() : false)) {
                        activity.finish();
                    }
                } else {
                    MessageHandler.sendMessage(new b(iApp, activity, str), 10, null);
                }
            } else {
                activity.finish();
            }
        }
        BaseInfo.sGlobalFullScreen = false;
    }

    public IApp a(Activity activity, String str, String str2, IOnCreateSplashView iOnCreateSplashView) {
        return a(activity, str, str2, iOnCreateSplashView, false);
    }

    public boolean a(Intent intent, String str) {
        String str2 = BaseInfo.sCacheFsAppsPath + str + "/www/";
        if (new File(str2).exists()) {
            File file = new File(str2 + "/manifest.json");
            if (file.exists() && file.length() > 0) {
                return true;
            }
        }
        if (intent.hasExtra(IntentConst.DIRECT_PAGE) && BaseInfo.isWap2AppAppid(str)) {
            return true;
        }
        return !intent.getBooleanExtra(IntentConst.WEBAPP_ACTIVITY_HAS_STREAM_SPLASH, false);
    }

    public IApp a(Activity activity, String str, String str2, IOnCreateSplashView iOnCreateSplashView, boolean z) {
        TestUtil.record("GET_STATUS_BY_APPID");
        Logger.d("syncStartApp " + str);
        IMgr.MgrType mgrType = IMgr.MgrType.AppMgr;
        Object objDispatchEvent = dispatchEvent(mgrType, 12, str);
        byte b2 = objDispatchEvent != null ? Byte.parseByte(String.valueOf(objDispatchEvent)) : (byte) 1;
        TestUtil.print("GET_STATUS_BY_APPID");
        boolean zA = a(activity.getIntent(), str);
        if (1 == b2) {
            Logger.d("syncStartApp " + str + " STATUS_UN_RUNNING");
            if (iOnCreateSplashView != null) {
                Logger.d("syncStartApp " + str + " ShowSplash");
                iOnCreateSplashView.onCreateSplash(activity);
            }
        }
        if (!zA) {
            return null;
        }
        try {
            TestUtil.record(TestUtil.START_APP_SET_ROOTVIEW, "启动" + str);
            IApp iApp = (IApp) dispatchEvent(mgrType, 0, new Object[]{activity, str, str2});
            iApp.setOnCreateSplashView(iOnCreateSplashView);
            if (z && (3 == b2 || 2 == b2)) {
                onActivityExecute(activity, ISysEventListener.SysEventType.onNewIntent, str2);
            }
            return iApp;
        } catch (Exception unused) {
            Logger.d("syncStartApp appid=" + str);
            return null;
        }
    }

    public void a(Activity activity, String str, String str2) {
        AsyncTaskHandler.executeThreadTask(new C0164c(str, activity, str2));
    }

    private void a(Context context, ISysEventListener.SysEventType sysEventType, Object obj) {
        Collection<IBoot> collectionValues;
        HashMap<String, IBoot> map = this.h;
        if (map == null || (collectionValues = map.values()) == null) {
            return;
        }
        for (IBoot iBoot : collectionValues) {
            if (iBoot != null) {
                try {
                    int i2 = d.f6501a[sysEventType.ordinal()];
                    if (i2 == 1) {
                        iBoot.onStart(context, (Bundle) obj, new String[]{BaseInfo.sDefaultBootApp});
                    } else if (i2 == 2) {
                        iBoot.onStop();
                    } else if (i2 == 3) {
                        iBoot.onPause();
                    } else if (i2 == 4) {
                        iBoot.onResume();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void a(Activity activity) {
        ISysEventListener.SysEventType sysEventType = ISysEventListener.SysEventType.onPause;
        a(activity, sysEventType, (Object) null);
        if (this.f6496a) {
            AbsMgr absMgr = this.f6497e;
            if (absMgr != null) {
                absMgr.onExecute(sysEventType, null);
            }
            onActivityExecute(activity, sysEventType, null);
        }
        System.gc();
    }

    private Object a(int i2, Object obj) {
        Activity activity;
        IApp iApp;
        Intent intentObtainWebAppIntent;
        String strObtainAppId;
        if (i2 == -1) {
            return BaseInfo.sRuntimeMode;
        }
        if (i2 != 0) {
            if (i2 == 1) {
                return Boolean.valueOf(this.h.containsValue((IBoot) obj));
            }
            if (i2 != 2) {
                return null;
            }
            Object[] objArr = (Object[]) obj;
            a((Activity) objArr[0], (String) objArr[1], (String) objArr[2], (IOnCreateSplashView) objArr[3]);
            return null;
        }
        if (obj instanceof IApp) {
            iApp = (IApp) obj;
            activity = iApp.getActivity();
            intentObtainWebAppIntent = iApp.obtainWebAppIntent();
            strObtainAppId = iApp.obtainAppId();
        } else if (obj instanceof Object[]) {
            Object[] objArr2 = (Object[]) obj;
            activity = (Activity) objArr2[0];
            intentObtainWebAppIntent = (Intent) objArr2[1];
            strObtainAppId = (String) objArr2[2];
            iApp = null;
        } else {
            activity = null;
            iApp = null;
            intentObtainWebAppIntent = null;
            strObtainAppId = null;
        }
        if (!SDK.isUniMPSDK()) {
            io.dcloud.f.b.a.f().g();
        }
        a(activity, intentObtainWebAppIntent, iApp, strObtainAppId);
        return null;
    }

    private void a() {
        String metaValue = AndroidResources.getMetaValue("DCLOUD_LOCALE");
        if (PdrUtil.isEmpty(metaValue) || metaValue.equalsIgnoreCase("default")) {
            return;
        }
        String[] strArrSplit = metaValue.split("_");
        String upperCase = "";
        String lowerCase = (strArrSplit.length <= 0 || strArrSplit[0] == null) ? "" : strArrSplit[0].toLowerCase();
        if (strArrSplit.length > 1 && strArrSplit[1] != null) {
            upperCase = strArrSplit[1].toUpperCase();
        }
        Locale.setDefault(new Locale(lowerCase, upperCase));
    }
}
