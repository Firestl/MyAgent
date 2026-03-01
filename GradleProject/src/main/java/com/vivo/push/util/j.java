package com.vivo.push.util;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.igexin.assist.util.AssistUtils;
import io.dcloud.common.DHInterface.IFeature;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: Device.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f5635a = z.b("ro.vivo.product.overseas", "no").equals("yes");
    public static final String b;
    public static final boolean c;
    public static final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f5636e;
    public static final boolean f;
    public static final boolean g;
    public static final boolean h;
    public static Method i;
    public static String j;
    public static String k;
    public static String l;
    public static String m;

    static {
        String strB = Build.VERSION.SDK_INT >= 26 ? z.b("ro.product.country.region", "N") : z.b("ro.product.customize.bbk", "N");
        b = strB;
        c = "RU".equals(strB);
        d = "IN".equals(b);
        f5636e = b("rom_1.0");
        f = b("rom_2.0");
        g = b("rom_2.5");
        h = b("rom_3.0");
        j = null;
        k = null;
        l = "";
        m = "";
    }

    public static String a(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = str2;
        }
        return (str3 == null || str3.length() == 0) ? str2 : str3;
    }

    public static boolean b(String str) {
        String strB = z.b("ro.vivo.rom", "");
        String strB2 = z.b("ro.vivo.rom.version", "");
        o.d(IFeature.F_DEVICE, "ro.vivo.rom = " + strB + " ; ro.vivo.rom.version = " + strB2);
        if (strB == null || !strB.contains(str)) {
            return strB2 != null && strB2.contains(str);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0083 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0085 A[Catch: all -> 0x0095, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000a, B:8:0x000e, B:10:0x0051, B:11:0x0058, B:15:0x0085), top: B:24:0x0005, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.lang.String a() {
        /*
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.Class<com.vivo.push.util.j> r1 = com.vivo.push.util.j.class
            monitor-enter(r1)
            java.lang.String r2 = com.vivo.push.util.j.j     // Catch: java.lang.Throwable -> L95
            r3 = 0
            if (r2 != 0) goto L58
            java.lang.String r2 = com.vivo.push.util.j.k     // Catch: java.lang.Throwable -> L95
            if (r2 != 0) goto L58
            java.lang.String r2 = "android.os.SystemProperties"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.String r4 = "get"
            r5 = 2
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            r7 = 0
            r6[r7] = r0     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            r8 = 1
            r6[r8] = r0     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.reflect.Method r0 = r2.getDeclaredMethod(r4, r6)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            com.vivo.push.util.j.i = r0     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            r0.setAccessible(r8)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.reflect.Method r0 = com.vivo.push.util.j.i     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.String r4 = "ro.vivo.rom"
            r2[r7] = r4     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.String r4 = "@><@"
            r2[r8] = r4     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.Object r0 = r0.invoke(r3, r2)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            com.vivo.push.util.j.j = r0     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.reflect.Method r0 = com.vivo.push.util.j.i     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.String r4 = "ro.vivo.rom.version"
            r2[r7] = r4     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.String r4 = "@><@"
            r2[r8] = r4     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.Object r0 = r0.invoke(r3, r2)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            com.vivo.push.util.j.k = r0     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L95
            goto L58
        L51:
            java.lang.String r0 = "Device"
            java.lang.String r2 = "getRomCode error"
            com.vivo.push.util.o.b(r0, r2)     // Catch: java.lang.Throwable -> L95
        L58:
            java.lang.String r0 = "Device"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L95
            java.lang.String r4 = "sRomProperty1 : "
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L95
            java.lang.String r4 = com.vivo.push.util.j.j     // Catch: java.lang.Throwable -> L95
            r2.append(r4)     // Catch: java.lang.Throwable -> L95
            java.lang.String r4 = " ; sRomProperty2 : "
            r2.append(r4)     // Catch: java.lang.Throwable -> L95
            java.lang.String r4 = com.vivo.push.util.j.k     // Catch: java.lang.Throwable -> L95
            r2.append(r4)     // Catch: java.lang.Throwable -> L95
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L95
            com.vivo.push.util.o.d(r0, r2)     // Catch: java.lang.Throwable -> L95
            java.lang.String r0 = com.vivo.push.util.j.j     // Catch: java.lang.Throwable -> L95
            java.lang.String r0 = a(r0)     // Catch: java.lang.Throwable -> L95
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L95
            if (r2 != 0) goto L85
            monitor-exit(r1)
            return r0
        L85:
            java.lang.String r0 = com.vivo.push.util.j.k     // Catch: java.lang.Throwable -> L95
            java.lang.String r0 = a(r0)     // Catch: java.lang.Throwable -> L95
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L95
            if (r2 != 0) goto L93
            monitor-exit(r1)
            return r0
        L93:
            monitor-exit(r1)
            return r3
        L95:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.j.a():java.lang.String");
    }

    public static boolean b() {
        if (TextUtils.isEmpty(Build.MANUFACTURER)) {
            o.d(IFeature.F_DEVICE, "Build.MANUFACTURER is null");
            return false;
        }
        o.d(IFeature.F_DEVICE, "Build.MANUFACTURER is " + Build.MANUFACTURER);
        return Build.MANUFACTURER.toLowerCase().contains("bbk") || Build.MANUFACTURER.toLowerCase().startsWith(AssistUtils.BRAND_VIVO);
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("rom_([\\d]*).?([\\d]*)", 2).matcher(str);
        if (!matcher.find()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(matcher.group(1));
        sb.append(TextUtils.isEmpty(matcher.group(2)) ? "0" : matcher.group(2).substring(0, 1));
        return sb.toString();
    }
}
