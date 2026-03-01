package com.loc;

import android.content.Context;
import android.os.Build;
import io.dcloud.common.util.JSUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ErrorLogManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static WeakReference<ax> f3628a = null;
    public static boolean b = true;
    public static WeakReference<bq> c = null;
    public static WeakReference<bq> d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String[] f3629e = new String[10];
    public static int f = 0;
    public static boolean g = false;
    public static int h;
    public static t i;

    public static t a(String str) {
        List<t> listA = z.a();
        if (listA == null) {
            listA = new ArrayList();
        }
        if (str != null && !"".equals(str)) {
            for (t tVar : listA) {
                if (z.a(tVar.f(), str)) {
                    return tVar;
                }
            }
            if (str.contains("com.amap.api.col")) {
                try {
                    return u.a();
                } catch (j e2) {
                    e2.printStackTrace();
                }
            }
            if (str.contains("com.amap.co") || str.contains("com.amap.opensdk.co") || str.contains("com.amap.location")) {
                try {
                    t tVarB = u.b();
                    tVarB.a(true);
                    return tVarB;
                } catch (j e3) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0102 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x00f6 -> B:107:0x00f9). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.util.List<com.loc.t> r11) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ac.a(java.util.List):java.lang.String");
    }

    public static void a(Context context) {
        String strA;
        t tVar;
        List<t> listA = z.a();
        if (listA == null || listA.size() == 0 || (strA = a(listA)) == null || "".equals(strA) || (tVar = i) == null) {
            return;
        }
        a(context, tVar, 2, "ANR", strA);
    }

    public static void a(final Context context, final bq bqVar, final String str) {
        ab.d().submit(new Runnable() { // from class: com.loc.ac.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    synchronized (ac.class) {
                        ax axVarA = bd.a(ac.f3628a);
                        bd.a(context, axVarA, str, 1000, 4096000, "1");
                        axVarA.f = bqVar;
                        if (axVarA.g == null) {
                            axVarA.g = new bh(new bg(context, new bl(), new aj(new al(new an())), "QImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJhZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMi", k.f(context), n.x(context), n.w(context), k.c(context), Build.MODEL, k.b(context), k.d(context), Build.VERSION.RELEASE));
                        }
                        axVarA.h = 3600000;
                        ay.a(axVarA);
                    }
                } catch (Throwable th) {
                    ab.b(th, "lg", "pul");
                }
            }
        });
    }

    public static void a(Context context, t tVar, int i2, String str, String str2) {
        String str3;
        String strA = u.a(System.currentTimeMillis());
        String strA2 = bd.a(context, tVar);
        k.a(context);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(strA2);
        stringBuffer.append(",\"timestamp\":\"");
        stringBuffer.append(strA);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i2);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str2);
        stringBuffer.append(JSUtil.QUOTE);
        String string = stringBuffer.toString();
        if (string == null || "".equals(string)) {
            return;
        }
        String strB = r.b(str2);
        if (i2 == 1) {
            str3 = z.b;
        } else if (i2 == 2) {
            str3 = z.d;
        } else if (i2 != 0) {
            return;
        } else {
            str3 = z.c;
        }
        String str4 = str3;
        ax axVarA = bd.a(f3628a);
        bd.a(context, axVarA, str4, 1000, 4096000, "1");
        if (axVarA.f3665e == null) {
            axVarA.f3665e = new ai(new aj(new al(new an())));
        }
        try {
            ay.a(strB, u.a(string.replaceAll("\n", "<br/>")), axVarA);
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, Throwable th, int i2, String str, String str2) {
        String strA = u.a(th);
        t tVarA = a(strA);
        if (a(tVarA)) {
            String strReplaceAll = strA.replaceAll("\n", "<br/>");
            String string = th.toString();
            if (string == null || "".equals(string)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (str != null) {
                sb.append("class:");
                sb.append(str);
            }
            if (str2 != null) {
                sb.append(" method:");
                sb.append(str2);
                sb.append("$<br/>");
            }
            sb.append(strReplaceAll);
            a(context, tVarA, i2, string, sb.toString());
        }
    }

    public static void a(t tVar, Context context, String str, String str2) {
        if (!a(tVar) || str == null || "".equals(str)) {
            return;
        }
        a(context, tVar, 1, str, str2);
    }

    public static boolean a(t tVar) {
        return tVar != null && tVar.e();
    }

    public static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i2 = f; i2 < 10 && i2 <= 9; i2++) {
                sb.append(f3629e[i2]);
            }
            for (int i3 = 0; i3 < f; i3++) {
                sb.append(f3629e[i3]);
            }
        } catch (Throwable th) {
            ab.b(th, "alg", "gLI");
        }
        return sb.toString();
    }

    public static void b(Context context) {
        bo boVar = new bo(b);
        b = false;
        a(context, boVar, z.c);
    }

    public static void c(Context context) {
        WeakReference<bq> weakReference = c;
        if (weakReference == null || weakReference.get() == null) {
            c = new WeakReference<>(new bp(context, 3600000, "hKey", new br(context)));
        }
        a(context, c.get(), z.d);
    }

    public static void d(Context context) {
        WeakReference<bq> weakReference = d;
        if (weakReference == null || weakReference.get() == null) {
            d = new WeakReference<>(new bp(context, 3600000, "gKey", new br(context)));
        }
        a(context, d.get(), z.b);
    }
}
