package com.cmic.gen.sdk.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1736a = Build.BRAND;
    public static final String b = Build.MODEL;
    public static final String c = "android" + Build.VERSION.RELEASE;
    public static final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f1737e;

    static {
        d = Build.VERSION.SDK_INT <= 28;
        f1737e = Build.MANUFACTURER;
    }

    public static int a(Context context, boolean z, com.cmic.gen.sdk.a aVar) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            int type = activeNetworkInfo.getType();
            if (type != 1) {
                if (type == 0) {
                    c.b("TelephonyUtils", "流量");
                    return 1;
                }
                return 0;
            }
            c.b("TelephonyUtils", "WIFI");
            boolean zA = g.a(context, "android.permission.CHANGE_NETWORK_STATE");
            c.a("TelephonyUtils", "CHANGE_NETWORK_STATE=" + zA);
            if (!zA || !z || !a(connectivityManager, context, aVar)) {
                return 2;
            }
            c.b("TelephonyUtils", "流量数据 WIFI 同开");
            return 3;
        }
        return 0;
    }

    public static String a() {
        return f1736a;
    }

    public static boolean a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager == null || 1 != telephonyManager.getSimState();
    }

    public static boolean a(ConnectivityManager connectivityManager, Context context, com.cmic.gen.sdk.a aVar) {
        String str;
        boolean zBooleanValue = false;
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            zBooleanValue = ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            c.b("TelephonyUtils", "data is on ---------" + zBooleanValue);
            if (Build.VERSION.SDK_INT >= 26) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                str = telephonyManager != null ? telephonyManager.createForSubscriptionId(SubscriptionManager.getDefaultSubscriptionId()).isDataEnabled() ? "1" : "0" : "-1";
                return zBooleanValue;
            }
            aVar.a("networkTypeByAPI", str);
            return zBooleanValue;
        } catch (Exception unused) {
            c.a("TelephonyUtils", "isMobileEnabled ----反射出错-----");
            return zBooleanValue;
        }
    }

    public static String b() {
        return b;
    }

    public static String c() {
        return c;
    }

    public static boolean d() {
        return d;
    }

    public static boolean e() {
        String str = f1737e;
        c.a(Constants.PHONE_BRAND, str);
        return "HUAWEI".equalsIgnoreCase(str);
    }
}
