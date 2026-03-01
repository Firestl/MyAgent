package com.igexin.push.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.push.g.o;
import com.igexin.sdk.main.SdkInitSwitch;
import com.igexin.sdk.main.SdkPushSwitch;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class e {
    public static String A = null;
    public static String B = null;
    public static String C = null;
    public static String D = null;
    public static String E = null;
    public static String F = null;
    public static String G = null;
    public static String H = null;
    public static String I = null;
    public static String K = null;
    public static String L = null;
    public static String M = null;
    public static String Z = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3403a = "";
    public static int aA = 0;
    public static byte[] aB = null;
    public static long aH = 0;
    public static final String aM = "CoreRuntimeInfo";
    public static Map<String, Integer> aN = null;
    public static String ac = null;
    public static byte[] ad = null;
    public static boolean ae = false;
    public static boolean af = false;
    public static boolean ag = false;
    public static Map<String, PushTaskBean> ah = null;
    public static Map<String, Integer> ai = null;
    public static Map<String, HashSet<String>> aj = null;
    public static Map<String, Integer> ak = null;
    public static HashMap<String, Long> al = null;
    public static String an = null;
    public static long ao = 0;
    public static String ap = null;
    public static String aq = null;
    public static String ar = null;
    public static String as = null;
    public static String at = null;
    public static String au = null;
    public static long av = 0;
    public static long aw = 0;
    public static volatile long ax = 0;
    public static long ay = 0;
    public static boolean az = false;
    public static String b = "";
    public static long c = 0;
    public static String d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f3404e = null;
    public static String f = "";
    public static String g = "";
    public static String h = null;
    public static String i = "";
    public static int j;
    public static int k;
    public static Context l;
    public static volatile boolean s;
    public static volatile boolean u;
    public static volatile boolean v;
    public static AtomicBoolean m = new AtomicBoolean(false);
    public static boolean n = true;
    public static HashMap<String, ClassLoader> o = new HashMap<>();
    public static volatile boolean p = true;
    public static volatile boolean q = false;
    public static int r = 0;
    public static boolean t = true;
    public static AtomicBoolean w = new AtomicBoolean(true);
    public static int x = 0;
    public static int y = 0;
    public static long z = 0;
    public static int J = -1;
    public static String N = "";
    public static long O = -1;
    public static long P = -1;
    public static long Q = 0;
    public static long R = 0;
    public static long S = 0;
    public static long T = 0;
    public static long U = 0;
    public static String V = null;
    public static boolean W = false;
    public static long X = 0;
    public static long Y = 0;
    public static long aa = 0;
    public static int ab = 0;
    public static int am = 0;
    public static String aC = null;
    public static int aD = 3600;
    public static boolean aE = false;
    public static long aF = 7200000;
    public static long aG = 7200000;
    public static String aI = "oppo r9";
    public static int aJ = 200;
    public static String aK = "";
    public static String aL = "";
    public static String aO = "";

    public static int a(String str) {
        int iIntValue;
        synchronized (e.class) {
            if (aN.get(str) == null) {
                aN.put(str, 0);
            }
            iIntValue = aN.get(str).intValue() - 1;
            aN.put(str, Integer.valueOf(iIntValue));
            if (iIntValue == 0) {
                aN.remove(str);
            }
        }
        return iIntValue;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a() {
        /*
            com.igexin.push.core.ServiceManager.getInstance()
            android.content.Context r0 = com.igexin.push.core.e.l
            java.lang.String r0 = com.igexin.push.core.ServiceManager.d(r0)
            com.igexin.push.core.ServiceManager.getInstance()
            android.content.Context r1 = com.igexin.push.core.e.l
            java.lang.String r1 = com.igexin.push.core.ServiceManager.e(r1)
            r2 = 0
            r3 = 1
            boolean r4 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L32
            if (r4 != 0) goto L36
            java.lang.Class r4 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L32
            java.lang.Class<com.igexin.sdk.PushService> r5 = com.igexin.sdk.PushService.class
            if (r4 == r5) goto L2d
            java.lang.Class<com.igexin.sdk.PushService> r5 = com.igexin.sdk.PushService.class
            boolean r4 = r5.isAssignableFrom(r4)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L2b
            goto L2d
        L2b:
            r4 = 0
            goto L2e
        L2d:
            r4 = 1
        L2e:
            if (r4 == 0) goto L36
            r1 = 0
            goto L36
        L32:
            r4 = move-exception
            com.igexin.c.a.c.a.a(r4)
        L36:
            r4 = 4
            java.lang.String[] r5 = new java.lang.String[r4]
            r5[r2] = r0
            r5[r3] = r1
            java.lang.Class<com.igexin.sdk.GActivity> r0 = com.igexin.sdk.GActivity.class
            java.lang.String r0 = r0.getName()
            r1 = 2
            r5[r1] = r0
            r0 = 3
            java.lang.Class<com.igexin.sdk.GService> r6 = com.igexin.sdk.GService.class
            java.lang.String r6 = r6.getName()
            r5[r0] = r6
            int r0 = com.igexin.push.config.e.b()
        L53:
            if (r2 >= r4) goto L7e
            r6 = r5[r2]
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L7b
            android.content.ComponentName r7 = new android.content.ComponentName     // Catch: java.lang.Throwable -> L77
            android.content.Context r8 = com.igexin.push.core.e.l     // Catch: java.lang.Throwable -> L77
            r7.<init>(r8, r6)     // Catch: java.lang.Throwable -> L77
            android.content.Context r6 = com.igexin.push.core.e.l     // Catch: java.lang.Throwable -> L77
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch: java.lang.Throwable -> L77
            r8 = -1
            if (r0 == r8) goto L72
            if (r0 != r3) goto L70
            goto L72
        L70:
            r8 = 2
            goto L73
        L72:
            r8 = 1
        L73:
            r6.setComponentEnabledSetting(r7, r8, r3)     // Catch: java.lang.Throwable -> L77
            goto L7b
        L77:
            r6 = move-exception
            com.igexin.c.a.c.a.a(r6)
        L7b:
            int r2 = r2 + 1
            goto L53
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.a():void");
    }

    public static void a(long j2) {
        z = j2;
        A = com.igexin.c.b.a.b(String.valueOf(j2));
    }

    public static boolean a(Context context) {
        l = context;
        g = context.getPackageName();
        i = o.b(context, o.f3603e, "").toString();
        ac = "getui.permission.GetuiService." + g;
        if (!e()) {
            com.igexin.c.a.c.a.a(aM, "parseManifests failed");
            com.igexin.c.a.c.a.a("CoreRuntimeInfo|parseManifests failed", new Object[0]);
            throw new IllegalArgumentException("parseManifests failed");
        }
        ad = com.igexin.c.b.a.b(f3403a + context.getPackageName()).getBytes();
        com.igexin.push.g.j.a();
        com.igexin.push.config.e.a();
        a();
        if (Build.VERSION.SDK_INT < 29) {
            System.currentTimeMillis();
            D = com.igexin.push.g.n.g();
            System.currentTimeMillis();
            E = com.igexin.push.g.n.f();
            System.currentTimeMillis();
        }
        F = com.igexin.push.g.n.e();
        G = com.igexin.push.g.n.d();
        n = com.igexin.push.g.c.e();
        ah = new ConcurrentHashMap();
        ai = new ConcurrentHashMap();
        aj = new HashMap();
        ak = new HashMap();
        al = new HashMap<>();
        s = com.igexin.push.core.d.d.a().b("p");
        aN = new HashMap();
        az = true;
        com.igexin.c.a.c.a.a("CoreRuntimeInfo|getui sdk init success ##########", new Object[0]);
        if (new SdkInitSwitch(l).isSwitchOn()) {
            com.igexin.push.core.d.d.a().a("i", Boolean.TRUE);
            new SdkInitSwitch(l).delete();
        }
        if (new SdkPushSwitch(l).isSwitchOn()) {
            s = true;
            com.igexin.push.core.d.d.a().a("p", Boolean.TRUE);
            new SdkPushSwitch(l).delete();
        }
        return true;
    }

    public static boolean a(String str, Integer num) {
        synchronized (e.class) {
            int iIntValue = num.intValue();
            if (aN.get(str) == null || (iIntValue = aN.get(str).intValue() + num.intValue()) != 0) {
                aN.put(str, Integer.valueOf(iIntValue));
                return true;
            }
            aN.remove(str);
            return false;
        }
    }

    public static Boolean b() {
        return Boolean.valueOf(aO.equals(Operators.MUL));
    }

    public static ClassLoader b(String str) {
        String str2 = str.split("_")[0];
        if (o.containsKey(str2)) {
            return o.get(str2);
        }
        return null;
    }

    public static void b(long j2) {
        O = j2;
    }

    public static void c() {
        if (new SdkInitSwitch(l).isSwitchOn()) {
            com.igexin.push.core.d.d.a().a("i", Boolean.TRUE);
            new SdkInitSwitch(l).delete();
        }
        if (new SdkPushSwitch(l).isSwitchOn()) {
            s = true;
            com.igexin.push.core.d.d.a().a("p", Boolean.TRUE);
            new SdkPushSwitch(l).delete();
        }
    }

    public static String d() {
        return SDKUrlConfig.getConfigServiceUrl();
    }

    public static boolean e() {
        try {
            ApplicationInfo applicationInfoB = com.igexin.push.g.n.b(l);
            if (applicationInfoB == null || applicationInfoB.metaData == null) {
                return false;
            }
            String strA = com.igexin.push.g.d.a(applicationInfoB);
            if (TextUtils.isEmpty(strA)) {
                strA = applicationInfoB.metaData.getString(b.b);
            }
            if (TextUtils.isEmpty(strA)) {
                strA = applicationInfoB.metaData.getString("GETUI_APPID");
            }
            if (strA != null) {
                strA = strA.trim();
            }
            b = applicationInfoB.metaData.getString(b.d);
            String string = applicationInfoB.metaData.getString(b.f3338e);
            if (string != null) {
                aO = string;
            }
            if (TextUtils.isEmpty(strA)) {
                com.igexin.c.a.c.a.a(aM, "getui sdk init error, missing parm ######");
                com.igexin.c.a.c.a.a("CoreRuntimeInfo|getui sdk init error, missing parm #####", new Object[0]);
                return false;
            }
            f3403a = strA;
            f = SDKUrlConfig.getLocation();
            return true;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return false;
        }
    }

    public static void f() {
        if (Build.VERSION.SDK_INT < 29) {
            System.currentTimeMillis();
            D = com.igexin.push.g.n.g();
            System.currentTimeMillis();
            E = com.igexin.push.g.n.f();
            System.currentTimeMillis();
        }
        F = com.igexin.push.g.n.e();
        G = com.igexin.push.g.n.d();
    }
}
