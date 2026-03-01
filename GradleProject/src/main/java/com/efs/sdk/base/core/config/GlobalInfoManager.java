package com.efs.sdk.base.core.config;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.efs.sdk.base.BuildConfig;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.core.util.NetworkUtil;
import com.efs.sdk.base.core.util.PackageUtil;
import com.efs.sdk.base.core.util.ProcessUtil;
import com.efs.sdk.base.core.util.d;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.taobao.weex.el.parse.Operators;
import com.tencent.stat.DeviceInfo;
import com.umeng.umcrash.UMCrash;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class GlobalInfoManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public GlobalInfo f1824a;
    public Context b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final GlobalInfoManager f1825a = new GlobalInfoManager(0);
    }

    public /* synthetic */ GlobalInfoManager(byte b) {
        this();
    }

    public static String a(Context context) {
        Class<?> cls;
        Method method;
        if (context == null) {
            return null;
        }
        try {
            cls = Class.forName("com.umeng.commonsdk.statistics.common.DeviceConfig");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            method = cls.getMethod("getSid", Context.class);
        } catch (NoSuchMethodException unused2) {
            method = null;
        }
        if (method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(null, context);
            if (objInvoke != null) {
                return objInvoke.toString();
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused3) {
            return null;
        }
    }

    public static GlobalInfoManager getInstance() {
        return a.f1825a;
    }

    public GlobalInfo getGlobalInfo() {
        try {
            String strA = a(this.b);
            if (!TextUtils.isEmpty(strA)) {
                this.f1824a.a(UMCrash.KEY_CALLBACK_SESSION_ID, strA);
            }
        } catch (Throwable th) {
            Log.d("efs.info.manager", "refreshSessionId caused error: " + th.getMessage());
        }
        return this.f1824a;
    }

    public String getNetStatus() {
        return this.f1824a.b("net", NetworkUtil.NETWORK_CLASS_DISCONNECTED).toString();
    }

    public void initGlobalInfo() {
        Log.e("ballack", "initGlobalInfo called once.");
        GlobalInfo globalInfo = new GlobalInfo();
        this.f1824a = globalInfo;
        globalInfo.a("appid", ControllerCenter.getGlobalEnvStruct().getAppid());
        int iMyPid = ProcessUtil.myPid();
        this.f1824a.a("pid", Integer.valueOf(iMyPid));
        this.f1824a.a("ps", ProcessUtil.getProcessName(iMyPid));
        String strA = d.a(this.b);
        this.f1824a.a("wid", strA);
        if (TextUtils.isEmpty(ControllerCenter.getGlobalEnvStruct().getUid())) {
            this.f1824a.a("uid", strA);
        } else {
            this.f1824a.a("uid", ControllerCenter.getGlobalEnvStruct().getUid());
        }
        GlobalInfo globalInfo2 = this.f1824a;
        com.efs.sdk.base.core.a.a.a();
        globalInfo2.a("stime", Long.valueOf(com.efs.sdk.base.core.a.a.b() - Process.getElapsedCpuTime()));
        this.f1824a.a("pkg", PackageUtil.getPackageName(this.b));
        this.f1824a.a(DeviceInfo.TAG_VERSION, PackageUtil.getAppVersionName(this.b));
        this.f1824a.a("vcode", PackageUtil.getAppVersionCode(this.b));
        this.f1824a.a(HiAnalyticsConstant.BI_KEY_SDK_VER, BuildConfig.VERSION_NAME);
        this.f1824a.a(Constants.PHONE_BRAND, Build.BRAND.toLowerCase());
        GlobalInfo globalInfo3 = this.f1824a;
        String str = Build.MODEL;
        globalInfo3.a("model", str == null ? "unknown" : str.replace(Operators.SPACE_STR, "-").replace("_", "-").toLowerCase());
        this.f1824a.a("build_model", Build.MODEL);
        DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
        this.f1824a.a("dsp_w", Integer.valueOf(displayMetrics.widthPixels));
        this.f1824a.a("dsp_h", Integer.valueOf(displayMetrics.heightPixels));
        this.f1824a.a("fr", "android");
        this.f1824a.a("rom", Build.VERSION.RELEASE);
        this.f1824a.a(com.umeng.ccg.a.r, Integer.valueOf(Build.VERSION.SDK_INT));
        this.f1824a.a(AbsoluteConst.JSON_KEY_LANG, Locale.getDefault().getLanguage());
        this.f1824a.a("tzone", TimeZone.getDefault().getID());
        this.f1824a.a("net", NetworkUtil.getNetworkType(this.b));
        try {
            String[] networkAccessMode = NetworkUtil.getNetworkAccessMode(this.b);
            if ("Wi-Fi".equals(networkAccessMode[0])) {
                this.f1824a.a(UMCrash.KEY_HEADER_ACCESS, "wifi");
            } else if ("2G/3G".equals(networkAccessMode[0])) {
                this.f1824a.a(UMCrash.KEY_HEADER_ACCESS, "2G/3G");
            } else {
                this.f1824a.a(UMCrash.KEY_HEADER_ACCESS, "unknow");
            }
            if (!"".equals(networkAccessMode[1])) {
                this.f1824a.a(UMCrash.KEY_HEADER_ACCESS_SUBTYPE, networkAccessMode[1]);
            }
            this.f1824a.a(UMCrash.KEY_HEADER_NETWORK_TYPE, Integer.valueOf(NetworkUtil.getNetworkTypeUmeng(this.b)));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void refreshNetStatus() {
        String networkType = NetworkUtil.getNetworkType(ControllerCenter.getGlobalEnvStruct().mAppContext);
        Log.w("efs.info.manager", "network change: ".concat(String.valueOf(networkType)));
        this.f1824a.a("net", networkType);
    }

    public GlobalInfoManager() {
        this.b = ControllerCenter.getGlobalEnvStruct().mAppContext;
    }
}
