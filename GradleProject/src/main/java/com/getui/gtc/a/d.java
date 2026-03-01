package com.getui.gtc.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.getui.gtc.BuildConfig;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.e.c;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2111a;
    public boolean b = true;
    public long c = 43200000;

    public static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(BuildConfig.VERSION_NAME);
        sb.append(",");
        try {
            Class<?> cls = Class.forName("com.igexin.sdk.PushManager");
            Method declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            Method declaredMethod2 = cls.getDeclaredMethod("getVersion", Context.class);
            declaredMethod2.setAccessible(true);
            sb.append("GT-".concat(String.valueOf((String) declaredMethod2.invoke(objInvoke, GtcProvider.context()))));
            sb.append(",");
        } catch (Throwable unused) {
        }
        try {
            Class<?> cls2 = Class.forName("com.getui.gis.sdk.GInsightManager");
            Method declaredMethod3 = cls2.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod3.setAccessible(true);
            Object objInvoke2 = declaredMethod3.invoke(null, new Object[0]);
            Method declaredMethod4 = cls2.getDeclaredMethod("version", new Class[0]);
            declaredMethod4.setAccessible(true);
            sb.append((String) declaredMethod4.invoke(objInvoke2, new Object[0]));
            sb.append(",");
        } catch (Throwable unused2) {
        }
        try {
            Class<?> cls3 = Class.forName("com.getui.gs.sdk.GsManager");
            Method declaredMethod5 = cls3.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod5.setAccessible(true);
            Object objInvoke3 = declaredMethod5.invoke(null, new Object[0]);
            Method declaredMethod6 = cls3.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod6.setAccessible(true);
            sb.append((String) declaredMethod6.invoke(objInvoke3, new Object[0]));
            sb.append(",");
        } catch (Throwable unused3) {
        }
        try {
            Class<?> cls4 = Class.forName("com.g.gysdk.GYManager");
            Method declaredMethod7 = cls4.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod7.setAccessible(true);
            Object objInvoke4 = declaredMethod7.invoke(null, new Object[0]);
            Method declaredMethod8 = cls4.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod8.setAccessible(true);
            sb.append((String) declaredMethod8.invoke(objInvoke4, new Object[0]));
            sb.append(",");
        } catch (Throwable unused4) {
        }
        try {
            Class<?> cls5 = Class.forName("com.getui.ctid.CTIDManager");
            Method declaredMethod9 = cls5.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod9.setAccessible(true);
            Object objInvoke5 = declaredMethod9.invoke(null, new Object[0]);
            Method declaredMethod10 = cls5.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod10.setAccessible(true);
            sb.append((String) declaredMethod10.invoke(objInvoke5, new Object[0]));
            sb.append(",");
        } catch (Throwable unused5) {
        }
        try {
            Method declaredMethod11 = Class.forName("com.getui.iop.IopManager").getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod11.setAccessible(true);
            sb.append((String) declaredMethod11.invoke(null, new Object[0]));
            sb.append(",");
        } catch (Throwable unused6) {
        }
        try {
            Class<?> cls6 = Class.forName("com.sdk.plus.WusManager");
            Method declaredMethod12 = cls6.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod12.setAccessible(true);
            Object objInvoke6 = declaredMethod12.invoke(null, new Object[0]);
            Method declaredMethod13 = cls6.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod13.setAccessible(true);
            sb.append((String) declaredMethod13.invoke(objInvoke6, new Object[0]));
            sb.append(",");
        } catch (Throwable unused7) {
        }
        try {
            Class<?> cls7 = Class.forName("com.getui.oneid.OneIDManager");
            Method declaredMethod14 = cls7.getDeclaredMethod("getInstance", new Class[0]);
            declaredMethod14.setAccessible(true);
            Object objInvoke7 = declaredMethod14.invoke(null, new Object[0]);
            Method declaredMethod15 = cls7.getDeclaredMethod("getVersion", new Class[0]);
            declaredMethod15.setAccessible(true);
            sb.append((String) declaredMethod15.invoke(objInvoke7, new Object[0]));
            sb.append(",");
        } catch (Throwable unused8) {
        }
        String string = sb.toString();
        return string.endsWith(",") ? string.substring(0, string.length() - 1) : string;
    }

    public static void a(Bundle bundle, StringBuilder sb, String str) {
        String string = bundle.getString(str);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        sb.append(str);
        sb.append(Constants.COLON_SEPARATOR);
        sb.append(string);
        sb.append(",");
    }

    public static String b() {
        try {
            StringBuilder sb = new StringBuilder();
            Bundle bundle = CommonUtil.getAppInfoForSelf(GtcProvider.context()).metaData;
            if (bundle == null) {
                return "";
            }
            a(bundle, sb, "GETUI_APPID");
            a(bundle, sb, "GETUI_APP_ID");
            a(bundle, sb, com.igexin.push.core.b.b);
            a(bundle, sb, "GI_APPID");
            a(bundle, sb, "GI_APP_ID");
            a(bundle, sb, "GS_APPID");
            a(bundle, sb, "GS_APP_ID");
            a(bundle, sb, "GY_APPID");
            a(bundle, sb, "GY_APP_ID");
            a(bundle, sb, "com.sdk.plus.appid");
            String string = sb.toString();
            return string.endsWith(",") ? string.substring(0, string.length() - 1) : string;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return "";
        }
    }

    public static String c() {
        try {
            if (!d()) {
                return "0,-1";
            }
            return "1," + e();
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return "0,-1";
        }
    }

    public static boolean d() {
        String str;
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return "harmony".equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (ClassNotFoundException unused) {
            str = "occured ClassNotFoundException";
            com.getui.gtc.i.c.a.c(str);
            return false;
        } catch (NoSuchMethodException unused2) {
            str = "occured NoSuchMethodException";
            com.getui.gtc.i.c.a.c(str);
            return false;
        } catch (Exception unused3) {
            str = "occur other problem";
            com.getui.gtc.i.c.a.c(str);
            return false;
        }
    }

    public static String e() {
        try {
            Method method = null;
            for (Method method2 : Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getMethods()) {
                if (method2.getName().equals("get")) {
                    method = method2;
                }
            }
            return method != null ? (String) method.invoke(null, "hw_sc.build.platform.version", "error") : "error";
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return "error";
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        PackageInfo packageInfoA;
        String strValueOf = "";
        Map<String, String> mapA = com.getui.gtc.f.b.a(43200000L, (com.getui.gtc.f.d) null);
        if (mapA != null && mapA.size() > 0) {
            try {
                if (mapA.containsKey("sdk.gtc.type302.enable")) {
                    this.b = Boolean.parseBoolean(mapA.get("sdk.gtc.type302.enable"));
                }
            } catch (Exception e2) {
                com.getui.gtc.i.c.a.a(e2);
            }
            try {
                if (mapA.containsKey("sdk.gtc.type302.interval")) {
                    this.c = Long.parseLong(mapA.get("sdk.gtc.type302.interval")) * 1000;
                }
            } catch (Exception e3) {
                com.getui.gtc.i.c.a.a(e3);
            }
        }
        if (!this.b) {
            com.getui.gtc.i.c.a.b("type 302 is not enabled");
            return;
        }
        if (CommonUtil.isAppDebugEnable()) {
            com.getui.gtc.i.c.a.b("type 302 is debug, disallow");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String packageName = GtcProvider.context().getPackageName();
        try {
            packageInfoA = com.getui.gtc.i.d.a.a(packageName);
            str = packageInfoA.versionName;
        } catch (Throwable th) {
            th = th;
            str = "";
        }
        try {
            strValueOf = String.valueOf(packageInfoA.versionCode);
        } catch (Throwable th2) {
            th = th2;
            com.getui.gtc.i.c.a.a(th);
        }
        this.f2111a = a.a(simpleDateFormat.format(new Date())) + "|" + a.a(com.getui.gtc.c.b.d) + "|" + a.a(com.getui.gtc.c.b.f2140a) + "|" + a.a(packageName) + "|" + a.a(str) + "|" + a.a(strValueOf) + "|android|" + a.a(a()) + "|" + a.a(b()) + "|" + c();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - c.a.f2213a.f2212a.j < this.c) {
                return;
            }
            com.getui.gtc.h.a.a(this.f2111a, com.umeng.ccg.c.o);
            com.getui.gtc.e.d dVar = c.a.f2213a.f2212a;
            if (dVar.a(15, jCurrentTimeMillis)) {
                dVar.j = jCurrentTimeMillis;
            }
        } catch (Exception e4) {
            com.getui.gtc.i.c.a.c("type 302 report error: " + e4.toString());
        }
    }
}
