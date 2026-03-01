package com.uc.crashsdk;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.tencent.connect.common.Constants;
import com.tencent.stat.DeviceInfo;
import com.uc.crashsdk.a.h;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static final /* synthetic */ boolean i = !b.class.desiredAssertionStatus();
    public static String j = null;
    public static String k = null;
    public static String l = null;
    public static String m = null;
    public static String n = null;
    public static String o = null;
    public static String p = null;
    public static String q = null;
    public static String r = null;
    public static String s = null;
    public static String t = null;
    public static String u = null;
    public static String v = null;
    public static String w = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5119a = false;
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f5120e = new Object();
    public static boolean f = false;
    public static boolean g = true;
    public static boolean h = false;
    public static boolean x = false;
    public static boolean y = false;
    public static volatile boolean z = false;
    public static boolean A = false;
    public static boolean B = false;
    public static boolean C = false;
    public static boolean D = false;
    public static boolean E = false;
    public static boolean F = false;
    public static final Object G = new Object();
    public static String H = null;
    public static int I = 0;
    public static boolean J = false;
    public static boolean K = false;
    public static boolean L = true;
    public static RandomAccessFile M = null;
    public static boolean N = false;
    public static final Object O = new Object();
    public static String P = null;
    public static boolean Q = false;
    public static volatile Object[] R = null;
    public static Runnable S = new com.uc.crashsdk.a.e(101);
    public static boolean T = false;
    public static long U = 0;
    public static final Object V = new Object();
    public static long W = 0;
    public static boolean X = false;
    public static boolean Y = false;
    public static boolean Z = false;
    public static long aa = 0;
    public static final WeakHashMap<Activity, Integer> ab = new WeakHashMap<>();
    public static boolean ac = false;
    public static String ad = null;
    public static boolean ae = false;
    public static boolean af = false;
    public static boolean ag = false;
    public static boolean ah = false;
    public static boolean ai = false;
    public static final Object aj = new Object();
    public static PendingIntent ak = null;

    public static boolean A() {
        return Y || !ad();
    }

    public static boolean B() {
        return Y && !x;
    }

    public static void C() {
        com.uc.crashsdk.a.f.a(2, new com.uc.crashsdk.a.e(100));
    }

    public static void D() {
        String str;
        if (!d || (str = ad) == null) {
            return;
        }
        JNIBridge.set(129, str);
    }

    public static String E() {
        String str = ad;
        return str == null ? "" : str;
    }

    public static boolean F() {
        if (!ae) {
            if (!com.uc.crashsdk.a.g.a(a.f5104a) && a.f5104a.equals(e.h())) {
                af = true;
                if (d) {
                    JNIBridge.set(2, true);
                }
            }
            ae = true;
        }
        return af;
    }

    public static void G() {
        ag = true;
        if (d) {
            JNIBridge.set(34, true);
        }
    }

    public static boolean H() {
        return ag;
    }

    public static int I() {
        boolean zU = U();
        return t() ? zU ? 3 : 6 : s() ? zU ? 2 : 5 : zU ? 4 : 1;
    }

    public static int J() {
        boolean zV = V();
        boolean zW = W();
        boolean zX = X();
        if (t()) {
            if (zV) {
                return 12;
            }
            if (zW) {
                return 14;
            }
            return zX ? 16 : 98;
        }
        if (!s()) {
            return 1;
        }
        if (zV) {
            return 11;
        }
        if (zW) {
            return 13;
        }
        return zX ? 15 : 97;
    }

    public static void K() {
        if (d) {
            JNIBridge.nativeSet(27, I, Constants.VIA_REPORT_TYPE_SET_AVATAR, null);
            JNIBridge.set(30, L);
        }
    }

    public static boolean L() {
        if (!ai) {
            synchronized (aj) {
                if (!ai) {
                    ah = ae();
                    ai = true;
                }
            }
        }
        return ah;
    }

    public static void M() {
        if (e.F() || L() || ak != null || g.h() < 0) {
            return;
        }
        try {
            Context contextA = com.uc.crashsdk.a.g.a();
            Intent launchIntentForPackage = contextA.getPackageManager().getLaunchIntentForPackage(contextA.getPackageName());
            launchIntentForPackage.addFlags(335544320);
            ak = PendingIntent.getActivity(contextA, 0, launchIntentForPackage, 0);
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
        }
    }

    public static boolean N() {
        if (ak == null) {
            com.uc.crashsdk.a.a.b("Restart intent is null!");
            return false;
        }
        try {
            com.uc.crashsdk.a.a.a("crashsdk", "restarting ...");
            ((AlarmManager) com.uc.crashsdk.a.g.a().getSystemService("alarm")).set(1, System.currentTimeMillis() + 200, ak);
            return true;
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
            return false;
        }
    }

    public static /* synthetic */ boolean O() {
        ac = true;
        return true;
    }

    public static String Q() {
        if (j == null) {
            j = d("ss");
        }
        return j;
    }

    public static String R() {
        if (l == null) {
            l = d("ctn");
        }
        return l;
    }

    public static String S() {
        if (m == null) {
            m = d("cta");
        }
        return m;
    }

    public static void T() {
        if (z || y) {
            return;
        }
        synchronized (G) {
            if (z) {
                return;
            }
            f(g.W());
            String strP = p();
            File file = new File(b());
            File file2 = new File(R());
            A = cn.com.chinatelecom.account.api.e.f.f1517a.equals(strP);
            B = "b".equals(strP);
            D = file.exists();
            boolean zExists = file2.exists();
            E = zExists;
            boolean z2 = D || zExists;
            C = z2;
            if (!z2 && (A || B)) {
                boolean zR = r();
                F = zR;
                C = zR;
            }
            try {
                if (z()) {
                    Z();
                }
            } catch (Throwable th) {
                com.uc.crashsdk.a.g.a(th);
            }
            z = true;
        }
    }

    public static boolean U() {
        T();
        return C;
    }

    public static boolean V() {
        T();
        return D;
    }

    public static boolean W() {
        T();
        return E;
    }

    public static boolean X() {
        T();
        return F;
    }

    public static void Y() {
        if (d) {
            JNIBridge.set(26, x);
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0041 -> B:29:0x0044). Please report as a decompilation issue!!! */
    public static void Z() {
        if (!T) {
            T = true;
            try {
                new File(b()).delete();
            } catch (Throwable th) {
                com.uc.crashsdk.a.g.a(th);
            }
            try {
                new File(R()).delete();
            } catch (Throwable th2) {
                com.uc.crashsdk.a.g.a(th2);
            }
            try {
                if (d) {
                    JNIBridge.cmd(16);
                } else {
                    new File(S()).delete();
                }
            } catch (Throwable th3) {
                com.uc.crashsdk.a.g.a(th3);
            }
        }
        Object[] objArrAb = ab();
        if (!objArrAb[0].equals(P) && R == null) {
            a(objArrAb);
        } else {
            Q = true;
            aa();
        }
    }

    public static String a() {
        String str = H;
        if (str != null) {
            return str;
        }
        String strH = e.h();
        if (com.uc.crashsdk.a.g.a(strH)) {
            H = "LLUN";
        } else {
            int i2 = 0;
            if (strH.length() > 48) {
                int length = strH.length() - 48;
                strH = strH.substring(0, 48);
                i2 = length;
            }
            StringBuilder sb = new StringBuilder();
            byte[] bytes = strH.getBytes();
            for (int length2 = bytes.length - 1; length2 >= 0; length2--) {
                byte b2 = bytes[length2];
                if (b2 == 46) {
                    sb.append('0');
                } else if (b2 == 58) {
                    sb.append('1');
                } else if (b2 >= 97 && b2 <= 122) {
                    sb.append((char) ((b2 + 65) - 97));
                } else if (b2 >= 65 && b2 <= 90) {
                    sb.append((char) b2);
                } else if (b2 < 48 || b2 > 57) {
                    sb.append('2');
                } else {
                    sb.append((char) b2);
                }
            }
            if (i2 > 0) {
                sb.append(String.valueOf(i2));
            }
            H = sb.toString();
        }
        return H;
    }

    public static void aa() {
        if (!com.uc.crashsdk.a.f.b(S)) {
            com.uc.crashsdk.a.f.a(1, S);
            return;
        }
        Object[] objArr = R;
        if (objArr == null || !ab()[0].equals(objArr[0])) {
            com.uc.crashsdk.a.f.a(S);
            com.uc.crashsdk.a.f.a(1, S);
        }
    }

    public static Object[] ab() {
        synchronized (V) {
            long j2 = W + 1;
            W = j2;
            if (x) {
                return new Object[]{"e", Long.valueOf(j2)};
            }
            if (B()) {
                return new Object[]{cn.com.chinatelecom.account.api.e.f.f1517a, Long.valueOf(W)};
            }
            return new Object[]{"b", Long.valueOf(W)};
        }
    }

    public static Object ac() {
        Method declaredMethod;
        Object objA;
        Object objA2 = a((Application) com.uc.crashsdk.a.g.a(), (Class<?>) Application.class, "mLoadedApk");
        if (objA2 != null && (objA = a(objA2, (Class<?>) null, "mActivityThread")) != null) {
            return objA;
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            if (cls != null && (declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                return declaredMethod.invoke(null, new Object[0]);
            }
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
        }
        return null;
    }

    public static boolean ad() {
        String strA = com.uc.crashsdk.a.g.a(new File("/proc/self/cgroup"), 512, false);
        if (com.uc.crashsdk.a.g.a(strA)) {
            return false;
        }
        return strA.contains("/bg_non_interactive") || strA.contains("/background");
    }

    public static boolean ae() {
        try {
            Method declaredMethod = Process.class.getDeclaredMethod("isIsolated", new Class[0]);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(null, new Object[0]);
                if (objInvoke != null && (objInvoke instanceof Boolean)) {
                    return ((Boolean) objInvoke).booleanValue();
                }
            }
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
        }
        int iMyUid = Process.myUid() % 100000;
        return iMyUid >= 99000 && iMyUid <= 99999;
    }

    public static String b() {
        if (k == null) {
            k = d("ctj");
        }
        return k;
    }

    public static String d(String str) {
        return g.W() + a() + Operators.DOT_STR + str;
    }

    public static File[] e(String str) {
        if (!i && str.length() <= 0) {
            throw new AssertionError();
        }
        File[] fileArrListFiles = new File(g.W()).listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.getPath().endsWith(str)) {
                arrayList.add(file);
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public static File[] f() {
        return e(".stcb");
    }

    public static String g() {
        if (v == null) {
            v = d("bati");
        }
        return v;
    }

    public static String h() {
        if (w == null) {
            w = d("hdr");
        }
        return w;
    }

    public static String i() {
        if (q == null) {
            q = g.W() + "up";
        }
        return q;
    }

    public static String j() {
        if (r == null) {
            r = g.W() + "authu";
        }
        return r;
    }

    public static String k() {
        if (s == null) {
            s = g.W() + "statu";
        }
        return s;
    }

    public static String l() {
        if (t == null) {
            t = g.W() + "poli";
        }
        return t;
    }

    public static String m() {
        if (u == null) {
            u = g.W() + DeviceInfo.TAG_VERSION;
        }
        return u;
    }

    public static String n() {
        return g.W() + "bvu";
    }

    public static String o() {
        return g.W() + "fds";
    }

    public static String p() {
        return com.uc.crashsdk.a.g.a(new File(Q()), 8, false);
    }

    public static boolean q() {
        return y;
    }

    public static boolean r() {
        if (!J) {
            if (d) {
                K = JNIBridge.cmd(15) == 1;
            } else {
                K = new File(S()).exists();
            }
            J = true;
        }
        return K;
    }

    public static boolean s() {
        T();
        return A;
    }

    public static boolean t() {
        T();
        return B;
    }

    public static boolean u() {
        return x;
    }

    public static void v() {
        boolean z2;
        f(g.W());
        y = true;
        A = false;
        B = false;
        C = false;
        D = false;
        E = false;
        F = false;
        String[] strArr = {".st", ".wa", ".callback", ".ctn", ".ctj", ".cta", ".signal"};
        String[] strArr2 = {"up", "authu", "statu", "poli"};
        File[] fileArrListFiles = new File(g.W()).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                String name = file.getName();
                int i2 = 0;
                while (true) {
                    if (i2 >= 7) {
                        z2 = false;
                        break;
                    } else {
                        if (name.endsWith(strArr[i2])) {
                            z2 = true;
                            break;
                        }
                        i2++;
                    }
                }
                if (!z2) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= 4) {
                            break;
                        }
                        if (name.equals(strArr2[i3])) {
                            z2 = true;
                            break;
                        }
                        i3++;
                    }
                }
                if (z2) {
                    com.uc.crashsdk.a.a.a("crashsdk", "delete file: " + file.getPath());
                    com.uc.crashsdk.a.g.a(file);
                }
            }
        }
        Z();
    }

    public static void w() {
        if (x) {
            return;
        }
        x = true;
        if (L() || e.u()) {
            return;
        }
        f(g.W());
        Y();
        Z();
    }

    public static boolean x() {
        return f(g.W());
    }

    public static boolean y() {
        return f(g.X());
    }

    public static boolean z() {
        return X || !ad();
    }

    public static String c() {
        if (n == null) {
            n = d("st");
        }
        return n;
    }

    public static File[] d() {
        return e(".st");
    }

    public static boolean f(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.isDirectory()) {
            return true;
        }
        com.uc.crashsdk.a.a.a("crashsdk", "Crash log directory was placed by a file!", null);
        if (!file.delete()) {
            return false;
        }
        file.mkdirs();
        return true;
    }

    public static String b(String str) {
        return "debug.crs." + str;
    }

    public static void b(boolean z2) {
        if (e.u()) {
            return;
        }
        if (z2 && x) {
            if (g.O()) {
                Log.v("crashsdk", "setForeground, reset sExited to false!!!");
            }
            x = false;
            Y();
        }
        boolean z3 = e.F() || L();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (X && !Y && z2) {
            long j2 = aa;
            if (j2 != 0 && !z3 && jCurrentTimeMillis - j2 > 1800000) {
                com.uc.crashsdk.a.f.a(1, new com.uc.crashsdk.a.e(104), 1000L);
            }
        }
        aa = jCurrentTimeMillis;
        Y = z2;
        if (z2) {
            X = true;
        }
        if (d) {
            JNIBridge.nativeSetForeground(z2);
        }
        if (x || z3) {
            return;
        }
        T();
        Z();
        if (z2) {
            a.a(false);
            if (!Z) {
                e.B();
                Z = true;
            }
        }
        if (!N) {
            aa();
        }
        e.c(z2);
    }

    public static boolean c(int i2) {
        return (i2 & I) != 0;
    }

    public static String e() {
        if (o == null) {
            o = d("stcb");
        }
        return o;
    }

    public static String a(String str) {
        if (str == null || str.length() <= 0 || !str.endsWith(".st")) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(".st");
        if (!i && iLastIndexOf < 0) {
            throw new AssertionError();
        }
        String strSubstring = str.substring(0, iLastIndexOf);
        if (strSubstring.length() <= 0) {
            return null;
        }
        return strSubstring + ".stcb";
    }

    public static void a(boolean z2) {
        L = z2;
        if (d) {
            JNIBridge.set(30, z2);
        }
    }

    public static void b(int i2) {
        I = i2;
        K();
    }

    public static void a(Object[] objArr) {
        R = objArr;
        synchronized (O) {
            String str = (String) objArr[0];
            long jLongValue = ((Long) objArr[1]).longValue();
            if (jLongValue < U) {
                com.uc.crashsdk.a.a.c("crashsdk", String.format(Locale.US, "Update state generation %d, last is: %d", Long.valueOf(jLongValue), Long.valueOf(U)));
                return;
            }
            U = jLongValue;
            String strQ = Q();
            if (d) {
                if (M != null) {
                    com.uc.crashsdk.a.g.a(M);
                    M = null;
                }
                boolean zNativeChangeState = JNIBridge.nativeChangeState(strQ, str, N);
                N = false;
                if (!zNativeChangeState) {
                    com.uc.crashsdk.a.a.b("write state failed: " + str);
                }
            } else {
                if (M == null || N) {
                    if (M != null) {
                        com.uc.crashsdk.a.g.a(M);
                        M = null;
                    }
                    try {
                        RandomAccessFile randomAccessFile = new RandomAccessFile(strQ, "rw");
                        M = randomAccessFile;
                        randomAccessFile.seek(0L);
                        N = false;
                    } catch (Exception e2) {
                        com.uc.crashsdk.a.g.a(e2);
                    }
                }
                try {
                    M.write(str.getBytes());
                    M.seek(0L);
                } catch (Exception e3) {
                    com.uc.crashsdk.a.g.a(e3);
                }
            }
            P = str;
            R = null;
        }
    }

    public static void b(Context context) {
        com.uc.crashsdk.a.a.a("Restart APP");
        if (context == null) {
            return;
        }
        if (p == null) {
            p = d("rt");
        }
        File file = new File(p);
        long j2 = -1;
        try {
            j2 = Long.parseLong(com.uc.crashsdk.a.g.d(file));
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
        }
        boolean z2 = false;
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        if (g.h() >= 0 && (j2 <= 0 || jCurrentTimeMillis - j2 > g.h())) {
            f(g.W());
            com.uc.crashsdk.a.g.a(file, String.valueOf(jCurrentTimeMillis));
            z2 = true;
        }
        com.uc.crashsdk.a.a.a("lastTime: " + j2 + ", currentTime: " + jCurrentTimeMillis + ", needRestart: " + z2);
        if (z2) {
            try {
                d.a(true);
            } catch (Throwable th2) {
                com.uc.crashsdk.a.g.a(th2);
            }
            N();
        }
    }

    public static boolean a(Context context) {
        try {
            ((Application) context).registerActivityLifecycleCallbacks(new c());
            if (!g.L()) {
                return true;
            }
            C();
            return true;
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
            return false;
        }
    }

    public static void a(int i2) {
        Object objA;
        Activity activity;
        int i3;
        boolean z2 = false;
        boolean z3 = true;
        switch (i2) {
            case 100:
                Object objAc = ac();
                if (objAc == null || (objA = a(objAc, (Class<?>) null, "mActivities")) == null) {
                    return;
                }
                try {
                    Iterator it = ((Map) objA).entrySet().iterator();
                    boolean z4 = false;
                    while (it.hasNext()) {
                        Object value = ((Map.Entry) it.next()).getValue();
                        if (value != null && (activity = (Activity) a(value, (Class<?>) null, "activity")) != null) {
                            boolean zBooleanValue = ((Boolean) a(value, (Class<?>) null, "paused")).booleanValue();
                            boolean zBooleanValue2 = ((Boolean) a(value, (Class<?>) null, "stopped")).booleanValue();
                            synchronized (ab) {
                                if (zBooleanValue || zBooleanValue2) {
                                    i3 = 2;
                                } else {
                                    i3 = 1;
                                    z4 = true;
                                }
                                ab.put(activity, Integer.valueOf(i3));
                                break;
                            }
                        }
                        z2 = true;
                    }
                    if (z2) {
                        b(z4);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    com.uc.crashsdk.a.g.a(th);
                    return;
                }
            case 101:
                try {
                    if (new File(Q()).exists()) {
                        z3 = false;
                    }
                    N = z3;
                    if (z3 || Q) {
                        a(ab());
                        Q = false;
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    com.uc.crashsdk.a.g.a(th2);
                    return;
                }
            case 102:
                com.uc.crashsdk.a.f.a(1, new com.uc.crashsdk.a.e(103));
                return;
            case 103:
                try {
                    com.uc.crashsdk.a.g.a(new File(S()));
                    return;
                } catch (Throwable th3) {
                    com.uc.crashsdk.a.g.a(th3);
                    return;
                }
            case 104:
                h.d();
                f.a(102);
                if (F()) {
                    e.C();
                    return;
                }
                return;
            default:
                if (!i) {
                    throw new AssertionError();
                }
                return;
        }
    }

    public static Object a(Object obj, Class<?> cls, String str) {
        if (obj == null) {
            return null;
        }
        if (cls == null) {
            cls = obj.getClass();
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Throwable th) {
            com.uc.crashsdk.a.g.a(th);
            return null;
        }
    }

    public static boolean a(Object obj, String str, com.uc.crashsdk.a.e eVar) {
        FileChannel channel;
        boolean zA;
        synchronized (obj) {
            FileChannel fileChannel = null;
            fileLockLock = null;
            FileLock fileLockLock = null;
            FileChannel fileChannel2 = null;
            boolean zA2 = false;
            if (d) {
                int iNativeOpenFile = JNIBridge.nativeOpenFile(str);
                if (iNativeOpenFile < 0) {
                    com.uc.crashsdk.a.a.a("crashsdk", "Can not open file: " + str, null);
                    return false;
                }
                try {
                    boolean zNativeLockFile = JNIBridge.nativeLockFile(iNativeOpenFile, true);
                    try {
                        zA = eVar.a();
                        return zA;
                    } finally {
                        if (zNativeLockFile) {
                            JNIBridge.nativeLockFile(iNativeOpenFile, false);
                        }
                    }
                } finally {
                    JNIBridge.nativeCloseFile(iNativeOpenFile);
                }
            }
            File file = new File(str);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e2) {
                    com.uc.crashsdk.a.g.a(e2);
                }
            }
            try {
                try {
                    channel = new RandomAccessFile(file, "rw").getChannel();
                } catch (Exception e3) {
                    try {
                        com.uc.crashsdk.a.g.a(e3);
                        channel = null;
                    } catch (Exception e4) {
                        e = e4;
                        com.uc.crashsdk.a.g.a(e);
                        com.uc.crashsdk.a.g.a(fileChannel);
                        zA = zA2;
                        return zA;
                    }
                }
                if (channel != null) {
                    try {
                        try {
                            fileLockLock = channel.lock();
                        } catch (Throwable th) {
                            fileChannel2 = channel;
                            th = th;
                            com.uc.crashsdk.a.g.a(fileChannel2);
                            throw th;
                        }
                    } catch (Exception e5) {
                        try {
                            com.uc.crashsdk.a.g.a(e5);
                        } catch (Exception e6) {
                            e = e6;
                            fileChannel = channel;
                            com.uc.crashsdk.a.g.a(e);
                            com.uc.crashsdk.a.g.a(fileChannel);
                        }
                    }
                }
                try {
                    zA2 = eVar.a();
                    com.uc.crashsdk.a.g.a(channel);
                    zA = zA2;
                    return zA;
                } finally {
                    if (fileLockLock != null) {
                        try {
                            fileLockLock.release();
                        } catch (Exception e7) {
                            com.uc.crashsdk.a.g.a(e7);
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                com.uc.crashsdk.a.g.a(fileChannel2);
                throw th;
            }
        }
    }
}
