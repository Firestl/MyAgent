package com.efs.sdk.base.core.a;

import android.content.Context;
import android.text.TextUtils;
import com.efs.sdk.base.BuildConfig;
import com.efs.sdk.base.EfsConstant;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.core.util.PackageUtil;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.stat.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1801a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1802e;
    public String f;
    public byte g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public long m = 0;

    public static c a() {
        c cVar = new c();
        cVar.f1801a = ControllerCenter.getGlobalEnvStruct().getAppid();
        cVar.b = ControllerCenter.getGlobalEnvStruct().getSecret();
        cVar.l = ControllerCenter.getGlobalEnvStruct().getUid();
        cVar.j = BuildConfig.VERSION_NAME;
        cVar.c = PackageUtil.getAppVersionName(ControllerCenter.getGlobalEnvStruct().mAppContext);
        cVar.i = String.valueOf(com.efs.sdk.base.core.config.a.c.a().d.f1827a);
        cVar.k = EfsConstant.UM_SDK_VERSION;
        return cVar;
    }

    public final String b() {
        a.a();
        String strValueOf = String.valueOf(a.b() / 1000);
        String strA = com.efs.sdk.base.core.util.b.b.a(com.efs.sdk.base.core.util.b.a.a(this.l + strValueOf, this.b));
        TreeMap treeMap = new TreeMap();
        treeMap.put(AbsoluteConst.XML_APP, this.f1801a);
        treeMap.put("sd", strA);
        String strA2 = a(ControllerCenter.getGlobalEnvStruct().mAppContext);
        if (!TextUtils.isEmpty(strA2)) {
            treeMap.put("wl_dd", com.efs.sdk.base.core.util.b.b.a(com.efs.sdk.base.core.util.b.a.a(strA2 + "_" + strValueOf, this.b)));
        }
        if (!TextUtils.isEmpty(this.d)) {
            treeMap.put(com.umeng.analytics.c.f5149e, this.d);
        }
        if (this.g != 0) {
            treeMap.put("de", String.valueOf(this.f1802e));
            treeMap.put("type", this.h);
            String str = this.f;
            if (TextUtils.isEmpty(str)) {
                a.a();
                long jB = a.b();
                str = String.format(Locale.SIMPLIFIED_CHINESE, "%d%04d", Long.valueOf(jB), Integer.valueOf(new Random(jB).nextInt(10000)));
            }
            treeMap.put("seq", str);
        }
        treeMap.put("cver", this.i);
        treeMap.put("os", "android");
        treeMap.put("sver", this.i);
        treeMap.put("tm", strValueOf);
        treeMap.put(DeviceInfo.TAG_VERSION, this.c);
        treeMap.put("um_sdk_ver", this.k);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            String str2 = ((String) entry.getKey()) + ContainerUtils.KEY_VALUE_DELIMITER + ((String) entry.getValue());
            sb2.append(str2);
            sb.append(str2);
            sb.append("&");
        }
        String strA3 = com.efs.sdk.base.core.util.b.b.a(sb2.toString() + this.b);
        sb.append("sign=");
        sb.append(strA3);
        Log.d("efs.config", sb.toString());
        return com.efs.sdk.base.core.util.b.b.b(sb.toString());
    }

    public static String a(Context context) {
        Class<?> cls;
        Method method;
        if (context == null) {
            return null;
        }
        try {
            cls = Class.forName("com.umeng.commonsdk.UMConfigure");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            method = cls.getMethod("getUMIDString", Context.class);
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
}
