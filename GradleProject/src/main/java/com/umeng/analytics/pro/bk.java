package com.umeng.analytics.pro;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.igexin.assist.util.AssistUtils;

/* JADX INFO: compiled from: DeviceUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5201a = "ro.build.version.emui";
    public static final String b = "hw_sc.build.platform.version";

    public static boolean a() {
        try {
            Class<?> cls = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP);
            return !TextUtils.isEmpty((String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.build.flyme.version", ""));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b() {
        return f() && !g();
    }

    public static boolean c() {
        return f() && g();
    }

    public static boolean d() {
        String str = Build.BRAND;
        if (!str.equalsIgnoreCase(AssistUtils.BRAND_HW) && !str.equalsIgnoreCase(AssistUtils.BRAND_HON) && !str.equalsIgnoreCase("华为")) {
            String strA = a("ro.build.version.emui");
            String strA2 = a("hw_sc.build.platform.version");
            if (TextUtils.isEmpty(strA) && TextUtils.isEmpty(strA2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean e() {
        return !TextUtils.isEmpty(a("ro.coolos.version"));
    }

    public static boolean f() {
        return Build.MANUFACTURER.equalsIgnoreCase("HONOR");
    }

    public static boolean g() {
        return !TextUtils.isEmpty(a("ro.build.version.emui"));
    }

    public static String a(String str) {
        try {
            return (String) Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getDeclaredMethod("get", String.class).invoke(null, str);
        } catch (Throwable unused) {
            return "";
        }
    }
}
