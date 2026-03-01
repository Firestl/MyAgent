package com.zx.a.I8b7;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.zx.a.I8b7.y1;
import com.zx.module.annotation.Java2C;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class d3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static PackageManager f6211a;
    public static i1 b;
    public static HashMap<String, String> c = new HashMap<>();

    @TargetApi(26)
    @Java2C.Method2C
    public static native String a();

    @Java2C.Method2C
    public static native String a(Context context);

    @Java2C.Method2C
    public static native String a(String str);

    public static boolean a(Context context, String str, boolean z) {
        try {
            return c(context).checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            try {
                y1.a.f6306a.f6305a.f6221a.a(4, null, null, th);
                return z;
            } catch (Throwable th2) {
                th2.printStackTrace();
                return z;
            }
        }
    }

    @Java2C.Method2C
    public static native PackageInfo b(String str);

    public static String b() {
        try {
            String str = Build.BRAND;
            return (TextUtils.isEmpty(str) || str.equals("unknown")) ? Build.MANUFACTURER : str;
        } catch (Throwable th) {
            y1.a(th);
            return "";
        }
    }

    @Java2C.Method2C
    public static native String b(Context context);

    public static PackageManager c(Context context) {
        if (f6211a == null) {
            f6211a = context.getPackageManager();
        }
        return f6211a;
    }

    @Java2C.Method2C
    public static native i1 d(Context context);

    @Java2C.Method2C
    public static native String d();

    @Java2C.Method2C
    public static native long e(Context context);

    @Java2C.Method2C
    public static native String e();

    @Java2C.Method2C
    public static native String f();

    @Java2C.Method2C
    public static native boolean f(Context context);

    @Java2C.Method2C
    public static native String g();

    @Java2C.Method2C
    public static native long h();

    @Java2C.Method2C
    public static native boolean i();

    public static String c() {
        String[] strArr = new String[0];
        if (Build.VERSION.SDK_INT >= 21) {
            strArr = Build.SUPPORTED_ABIS;
        }
        String str = "";
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                str = str + str2 + ",";
            }
        }
        return str;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean a(Context context, boolean z) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return z;
        }
    }

    public static String a(HashMap<String, String> map, String str) {
        return map.containsKey(str) ? map.get(str) : "";
    }
}
