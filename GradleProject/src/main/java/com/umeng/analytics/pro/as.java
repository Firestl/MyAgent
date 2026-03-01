package com.umeng.analytics.pro;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: RomUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5175a = "";
    public static String b = "";
    public static final String c = "hw_sc.build.platform.version";
    public static final String d = "ro.build.version.emui";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f5176e = "ro.build.version.magic";
    public static final String f = "ro.miui.ui.version.name";
    public static final String g = "ro.build.version.opporom";
    public static final String h = "ro.vivo.os.name";
    public static final String i = "ro.vivo.os.version";
    public static final String j = "ro.build.version.oplusrom";
    public static final String k = "ro.rom.version";

    public static boolean a() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return !TextUtils.isEmpty((String) cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(f5175a)) {
            e(str);
        }
        return b;
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll(Operators.SPACE_STR, "").toUpperCase();
    }

    public static String d(String str) {
        try {
            Class<?> cls = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP);
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void e(String str) {
        try {
            switch (c(str)) {
                case "HUAWEI":
                    if (!a()) {
                        f5175a = "EMUI";
                        b = d("ro.build.version.emui");
                        break;
                    } else {
                        b = d("hw_sc.build.platform.version");
                        f5175a = "HarmonyOS";
                        break;
                    }
                    break;
                case "HONOR":
                    if (!TextUtils.isEmpty(d(f5176e))) {
                        f5175a = "MagicUI";
                        b = d(f5176e);
                        break;
                    } else {
                        f5175a = "EMUI";
                        b = d("ro.build.version.emui");
                        break;
                    }
                    break;
                case "XIAOMI":
                case "REDMI":
                    f5175a = "MIUI";
                    b = d("ro.miui.ui.version.name");
                    break;
                case "REALME":
                case "OPPO":
                    f5175a = "ColorOS";
                    b = d(g);
                    break;
                case "VIVO":
                    f5175a = "Funtouch";
                    b = d(i);
                    break;
                case "ONEPLUS":
                    f5175a = "HydrogenOS";
                    String strD = d(k);
                    if (TextUtils.isEmpty(strD)) {
                        f5175a = "ColorOS";
                        strD = d(j);
                    }
                    b = strD;
                    break;
                default:
                    f5175a = "Android";
                    b = Build.VERSION.RELEASE;
                    break;
            }
        } catch (Throwable unused) {
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(f5175a)) {
            e(str);
        }
        return f5175a;
    }
}
