package com.vivo.push.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.speech.utils.Utility;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: Utility.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z {
    public static Boolean c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String[] f5648a = {"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};
    public static String[] b = {"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", DefaultConnectivityMonitorFactory.NETWORK_PERMISSION, "android.permission.WRITE_SETTINGS", "android.permission.VIBRATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WAKE_LOCK", "android.permission.GET_ACCOUNTS", "com.bbk.account.permission.READ_ACCOUNTINFO", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.GET_TASKS"};
    public static String[] d = {"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String[] f5649e = {"com.vivo.push.sdk.RegistrationReceiver"};
    public static String[] f = new String[0];
    public static Map<String, Bundle> g = new ConcurrentHashMap();

    public static boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        Boolean bool = c;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null) {
            o.d(Utility.TAG, "isPushProcess context is null");
            return false;
        }
        String strB = s.b(context);
        if (context != null && context.getPackageName() != null && context.getPackageName().equals(strB)) {
            Boolean bool2 = Boolean.TRUE;
            c = bool2;
            return bool2.booleanValue();
        }
        int iMyPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String str = null;
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == iMyPid) {
                    str = next.processName;
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Boolean boolValueOf = Boolean.valueOf(str.contains(":pushservice"));
        c = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static long b(Context context) {
        String strB = s.b(context);
        if (!TextUtils.isEmpty(strB)) {
            return a(context, strB);
        }
        o.a(Utility.TAG, "systemPushPkgName is null");
        return -1L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ec, code lost:
    
        r12 = r12 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(android.content.Context r23) throws com.vivo.push.util.VivoPushException {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.z.c(android.content.Context):void");
    }

    public static void d(Context context, String str) throws VivoPushException {
        if (f.length <= 0) {
            return;
        }
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            if (activityInfoArr == null) {
                throw new VivoPushException("activityInfos is null");
            }
            for (String str2 : f) {
                a(str2, activityInfoArr, str);
            }
        } catch (Exception e2) {
            throw new VivoPushException("error " + e2.getMessage());
        }
    }

    public static void e(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
            if (activityInfoArr == null) {
                throw new VivoPushException("receivers is null");
            }
            for (String str2 : f5649e) {
                a(str2, activityInfoArr, str);
            }
        } catch (Exception e2) {
            throw new VivoPushException(e2.getMessage());
        }
    }

    public static String b(Context context, String str) {
        Object objB = b(context, str, "com.vivo.push.app_id");
        if (objB != null) {
            return objB.toString();
        }
        Object objB2 = b(context, str, "app_id");
        return objB2 != null ? objB2.toString() : "";
    }

    public static boolean e(Context context) {
        Cursor cursor = null;
        try {
            try {
                try {
                } catch (Exception e2) {
                    o.a(Utility.TAG, "isSupport", e2);
                    if (0 != 0) {
                        cursor.close();
                    }
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        cursor.close();
                    } catch (Exception e3) {
                        o.a(Utility.TAG, "close", e3);
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            o.a(Utility.TAG, "close", e4);
        }
        if (context == null) {
            o.a(Utility.TAG, "context is null");
            return false;
        }
        String packageName = context.getPackageName();
        Cursor cursorQuery = context.getContentResolver().query(com.vivo.push.p.b, null, "pushVersion = ? and appPkgName = ? and appCode = ? ", new String[]{"800", packageName, String.valueOf(context.getPackageManager().getPackageInfo(packageName, 0).versionCode)}, null);
        if (cursorQuery == null) {
            o.a(Utility.TAG, "cursor is null");
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Exception e5) {
                    o.a(Utility.TAG, "close", e5);
                }
            }
            return false;
        }
        if (!cursorQuery.moveToFirst() || (cursorQuery.getInt(cursorQuery.getColumnIndex("permission")) & 1) == 0) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return false;
        }
        if (cursorQuery != null) {
            try {
                cursorQuery.close();
            } catch (Exception e6) {
                o.a(Utility.TAG, "close", e6);
            }
        }
        return true;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public static Object b(Context context, String str, String str2) {
        Bundle bundle;
        Object obj;
        Bundle bundle2;
        Object obj2 = null;
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Object obj3 = (g == null || g.size() <= 0 || (bundle2 = g.get(str)) == null) ? null : bundle2.get(str2);
            if (obj3 != null) {
                return obj3;
            }
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
                bundle = applicationInfo != null ? applicationInfo.metaData : null;
                obj = bundle != null ? bundle.get(str2) : obj3;
            } catch (Exception e2) {
                e = e2;
                obj2 = obj3;
            }
            try {
                if (g.size() > 300) {
                    return obj;
                }
                g.put(str, bundle);
                return obj;
            } catch (Exception e3) {
                obj2 = obj;
                e = e3;
                o.a(Utility.TAG, "getMetaValue::".concat(String.valueOf(e)));
                return obj2;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static PublicKey d(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(com.vivo.push.p.f5622a, null, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        while (cursorQuery.moveToNext()) {
            try {
                try {
                    if ("pushkey".equals(cursorQuery.getString(cursorQuery.getColumnIndex("name")))) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
                        o.d(Utility.TAG, "result key : ".concat(String.valueOf(string)));
                        return t.a(string);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                try {
                    cursorQuery.close();
                } catch (Exception unused) {
                }
            }
        }
        try {
            cursorQuery.close();
        } catch (Exception unused2) {
        }
        return null;
    }

    public static long a(Context context, String str) {
        Object objB = b(context, str, "com.vivo.push.sdk_version");
        if (objB == null) {
            objB = b(context, str, "sdk_version");
        }
        if (objB != null) {
            try {
                return Long.parseLong(objB.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
                o.a(Utility.TAG, "getSdkVersionCode error ", e2);
                return -1L;
            }
        }
        o.a(Utility.TAG, "getSdkVersionCode sdk version is null");
        return -1L;
    }

    public static String b(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = str2;
        }
        return (str3 == null || str3.length() == 0) ? str2 : str3;
    }

    public static Object a(String str, String str2) throws Exception {
        Class<?> cls = Class.forName(str);
        return cls.getField(str2).get(cls);
    }

    public static void a(String str, ComponentInfo[] componentInfoArr, String str2) throws VivoPushException {
        for (ComponentInfo componentInfo : componentInfoArr) {
            if (str.equals(componentInfo.name)) {
                if (componentInfo.enabled) {
                    a(componentInfo, str2);
                    return;
                }
                throw new VivoPushException(componentInfo.name + " module Push-SDK need is illegitmacy !");
            }
        }
        throw new VivoPushException(str + " module Push-SDK need is not exist");
    }

    public static void a(ComponentInfo componentInfo, String str) throws VivoPushException {
        if (componentInfo.applicationInfo.packageName.equals(str)) {
            return;
        }
        for (String str2 : f5648a) {
            if (str2.equals(componentInfo.name) && !componentInfo.processName.contains(":pushservice")) {
                throw new VivoPushException("module : " + componentInfo.name + " process :" + componentInfo.processName + "  check process fail");
            }
        }
    }

    public static void a(Context context, String str, String str2, boolean z) throws VivoPushException {
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            if (z) {
                List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 576);
                if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0) {
                    Iterator<ResolveInfo> it = listQueryBroadcastReceivers.iterator();
                    while (it.hasNext()) {
                        if (str2.equals(it.next().activityInfo.name)) {
                            return;
                        }
                    }
                    throw new VivoPushException(str2 + " is missing");
                }
                throw new VivoPushException("checkModule " + intent + " has no receivers");
            }
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 576);
            if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
                for (ResolveInfo resolveInfo : listQueryIntentServices) {
                    if (str2.equals(resolveInfo.serviceInfo.name)) {
                        if (resolveInfo.serviceInfo.exported) {
                            return;
                        }
                        throw new VivoPushException(resolveInfo.serviceInfo.name + " exported is false");
                    }
                }
                throw new VivoPushException(str2 + " is missing");
            }
            throw new VivoPushException("checkModule " + intent + " has no services");
        } catch (Exception e2) {
            o.a(Utility.TAG, "error  " + e2.getMessage());
            throw new VivoPushException("checkModule error" + e2.getMessage());
        }
    }

    public static void c(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() != null) {
                ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
                if (serviceInfoArr != null) {
                    for (String str2 : d) {
                        a(str2, serviceInfoArr, str);
                    }
                    return;
                }
                throw new VivoPushException("serviceInfos is null");
            }
            throw new VivoPushException("localPackageManager is null");
        } catch (Exception e2) {
            throw new VivoPushException("error " + e2.getMessage());
        }
    }

    public static void a(Context context, Intent intent) {
        String strB = s.b(context);
        String stringExtra = intent.getStringExtra("client_pkgname");
        if (TextUtils.isEmpty(strB)) {
            o.a(Utility.TAG, "illegality abe adapter : push pkg is null");
            return;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            o.a(Utility.TAG, "illegality abe adapter : src pkg is null");
            return;
        }
        if (strB.equals(context.getPackageName())) {
            o.a(Utility.TAG, "illegality abe adapter : abe is not pushservice");
            return;
        }
        if (!strB.equals(stringExtra)) {
            o.d(Utility.TAG, "proxy to core : intent pkg : " + intent.getPackage() + " ; src pkg : " + stringExtra + " ; push pkg : " + strB);
            intent.setPackage(strB);
            intent.setClassName(strB, "com.vivo.push.sdk.service.PushService");
            context.startService(intent);
            return;
        }
        o.a(Utility.TAG, "illegality abe adapter : pushPkg = " + strB + " ; srcPkg = " + stringExtra);
    }

    public static boolean a(Context context, String str, String str2) {
        Cursor cursor = null;
        try {
            try {
                try {
                } catch (Exception e2) {
                    o.a(Utility.TAG, "isOverdue", e2);
                    if (0 != 0) {
                        cursor.close();
                    }
                }
            } catch (Exception e3) {
                o.a(Utility.TAG, "close", e3);
            }
            if (context == null) {
                o.a(Utility.TAG, "context is null");
                return false;
            }
            Cursor cursorQuery = context.getContentResolver().query(com.vivo.push.p.c, null, "appPkgName = ? and regId = ? sdkVersion = ? ", new String[]{str, str2, "800"}, null);
            if (cursorQuery == null) {
                o.a(Utility.TAG, "cursor is null");
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Exception e4) {
                        o.a(Utility.TAG, "close", e4);
                    }
                }
                return false;
            }
            if (!cursorQuery.moveToFirst()) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            }
            boolean z = Boolean.parseBoolean(cursorQuery.getString(cursorQuery.getColumnIndex("clientState")));
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Exception e5) {
                    o.a(Utility.TAG, "close", e5);
                }
            }
            return z;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    cursor.close();
                } catch (Exception e6) {
                    o.a(Utility.TAG, "close", e6);
                }
            }
            throw th;
        }
    }
}
