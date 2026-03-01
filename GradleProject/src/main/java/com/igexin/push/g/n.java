package com.igexin.push.g;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaDrm;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.dim.Caller;
import com.getui.gtc.dim.DimManager;
import com.getui.gtc.dim.DimRequest;
import com.getui.gtc.dim.bean.GtWifiInfo;
import com.igexin.assist.util.AssistUtils;
import com.igexin.push.g.f;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.as;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3601a = "PhoneInfoUtils";
    public static final String b = "";
    public static volatile PackageInfo c;
    public static String d;

    public static int a(Context context) {
        try {
            return c(context).applicationInfo.targetSdkVersion;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return 0;
        }
    }

    public static String a(String str, String str2) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop" + Operators.SPACE_STR + str).getInputStream()));
            String str3 = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return str3;
                }
                str3 = str3 + line;
            }
        } catch (Exception unused) {
            return str2;
        }
    }

    public static List<PackageInfo> a() {
        List<PackageInfo> list;
        try {
            list = (List) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.O).caller(Caller.PUSH).build());
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            list = null;
        }
        return list == null ? Collections.emptyList() : list;
    }

    public static ApplicationInfo b(Context context) {
        try {
            return c(context).applicationInfo;
        } catch (PackageManager.NameNotFoundException e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    public static Pair<String, String> b() {
        try {
            if (!com.igexin.push.config.d.X || d.b("3.1.12.0")) {
                com.igexin.c.a.c.a.b(f3601a, "use wf");
                WifiInfo wifiInfo = (WifiInfo) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.F).caller(Caller.PUSH).build());
                if (wifiInfo == null) {
                    return null;
                }
                return Pair.create(wifiInfo.getSSID(), wifiInfo.getBSSID());
            }
            com.igexin.c.a.c.a.b(f3601a, "use gt wf");
            GtWifiInfo json = GtWifiInfo.parseJson((String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.I).caller(Caller.PUSH).build()));
            if (json == null) {
                return null;
            }
            return Pair.create(json.getSSID(), json.getBSSID());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    public static PackageInfo c(Context context) throws PackageManager.NameNotFoundException {
        if (c != null) {
            com.igexin.c.a.c.a.b(f3601a, "getSelfPackageInfo cache");
            return c;
        }
        synchronized (n.class) {
            if (c == null) {
                c = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
                com.igexin.c.a.c.a.b(f3601a, "getSelfPackageInfo");
            }
        }
        return c;
    }

    public static String c() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.j).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }

    public static String d() {
        return Build.BRAND;
    }

    public static String e() {
        return Build.MODEL;
    }

    public static String f() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.f).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }

    public static String g() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.b).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }

    public static String h() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.q).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }

    public static String i() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.r).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }

    public static List<ScanResult> j() {
        try {
            return (List) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.G).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    public static String k() {
        try {
            return Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0];
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return "";
        }
    }

    public static boolean l() {
        try {
            if (!com.igexin.push.config.d.G.contains(Operators.MUL)) {
                return Arrays.asList(com.igexin.push.config.d.G.toUpperCase().split(",")).contains(Build.BRAND.toUpperCase());
            }
            com.igexin.c.a.c.a.a("PhoneInfoUtils|delAlarm all", new Object[0]);
            return true;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            com.igexin.c.a.c.a.a("PhoneInfoUtils|delAlarm " + com.igexin.push.config.d.G + " err " + e2.toString(), new Object[0]);
            return false;
        }
    }

    public static String m() {
        String str;
        try {
            str = (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.l).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            str = null;
        }
        if (!TextUtils.isEmpty(str) && !str.equals(com.igexin.push.core.e.h)) {
            com.igexin.push.core.e.h = str;
        }
        return str;
    }

    public static String n() {
        try {
            return c(com.igexin.push.core.e.l).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }

    public static long o() {
        try {
            return c(com.igexin.push.core.e.l).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            com.igexin.c.a.c.a.a(e2);
            return 0L;
        }
    }

    public static String p() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.H).caller(Caller.PUSH).build());
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a("PhoneInfoUtils|".concat(String.valueOf(th)), new Object[0]);
            return "";
        }
    }

    public static String q() {
        byte[] propertyByteArray;
        try {
            if (Build.VERSION.SDK_INT < 23 || (propertyByteArray = new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L)).getPropertyByteArray("deviceUniqueId")) == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (byte b2 : propertyByteArray) {
                sb.append(String.format("%02x", Byte.valueOf(b2)));
            }
            return sb.toString();
        } catch (Error | Exception unused) {
            return "";
        }
    }

    public static Location r() {
        try {
            return (Location) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.B).caller(Caller.PUSH).build());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Location s() {
        try {
            return (Location) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.C).caller(Caller.PUSH).build());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String t() {
        try {
            Context context = com.igexin.push.core.e.l;
            if (!CommonUtil.hasPermission(context, "android.permission.READ_PHONE_STATE", false)) {
                return "";
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            switch (telephonyManager != null ? Build.VERSION.SDK_INT >= 24 ? telephonyManager.getDataNetworkType() : telephonyManager.getNetworkType() : 0) {
            }
            return "";
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return "";
        }
    }

    public static String u() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.f3587e).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }

    public static String v() {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        try {
            String str = Build.BRAND;
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String lowerCase = str.toLowerCase();
            HashMap map = new HashMap();
            map.put(AssistUtils.BRAND_HW, "ro.build.version.emui");
            map.put("blackshark", "ro.build.version.incremental");
            map.put("redmi", "ro.build.version.incremental");
            map.put(AssistUtils.BRAND_XIAOMI, "ro.build.version.incremental");
            map.put("samsang", "ro.build.version.incremental");
            map.put(AssistUtils.BRAND_VIVO, as.i);
            map.put(AssistUtils.BRAND_OPPO, as.g);
            map.put(AssistUtils.BRAND_MZ, "ro.build.display.id");
            map.put("lenovo", "ro.build.version.incremental");
            map.put("smartisan", "ro.modversion");
            map.put("htc", "ro.build.sense.version");
            map.put("oneplus", as.k);
            map.put("yunos", "ro.cta.yunos.version");
            map.put("360", "ro.build.uiversion");
            map.put("nubia", "ro.build.rom.internal.id");
            if (map.containsKey(lowerCase)) {
                String strA = a((String) map.get(lowerCase), "");
                d = strA;
                return strA;
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
        return "";
    }

    public static boolean w() {
        return Build.VERSION.SDK_INT > 28;
    }

    public static String x() {
        return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.u).caller(Caller.PUSH).build());
    }

    public static String y() {
        try {
            return (String) DimManager.getInstance().get(new DimRequest.Builder().key(f.a.J).caller(Caller.PUSH).build());
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return "";
        }
    }
}
