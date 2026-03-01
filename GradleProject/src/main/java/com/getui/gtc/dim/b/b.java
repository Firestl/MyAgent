package com.getui.gtc.dim.b;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import com.efs.sdk.pa.config.ConfigManager;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.dim.b.d;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final b i = new b();
    public long j = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f2154a = 10;
    public long b = -1;
    public long c = 3600;
    public long d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f2155e = -1;
    public long l = -1;
    public boolean f = false;
    public boolean g = false;
    public boolean h = true;
    public boolean m = true;
    public final Map<String, a> k = new HashMap(4);

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2156a;
        public long b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2157e;
        public int f;
        public int g;
        public int h;

        public final String toString() {
            return "Config{key='" + this.f2156a + Operators.SINGLE_QUOTE + ", sdkTotalRuntimeCondition=" + this.b + ", timeRangeStart='" + this.c + Operators.SINGLE_QUOTE + ", timeRangeEnd='" + this.d + Operators.SINGLE_QUOTE + ", timeRangeState=" + this.f2157e + ", weekState=" + this.f + ", sdkAccessCountCondition=" + this.g + ", installDurationDayCondition=" + this.h + Operators.BLOCK_END;
        }
    }

    /* JADX INFO: renamed from: com.getui.gtc.dim.b.b$b, reason: collision with other inner class name */
    public static class C0037b {
        public static Calendar a(long j, String str) {
            String[] strArrSplit = str.split(Constants.COLON_SEPARATOR);
            int i = Integer.parseInt(strArrSplit[0]);
            int i2 = Integer.parseInt(strArrSplit[1]);
            int i3 = Integer.parseInt(strArrSplit[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            calendar.set(11, i);
            calendar.set(12, i2);
            calendar.set(13, i3);
            calendar.set(14, 0);
            return calendar;
        }

        public static boolean a(long j) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            int i = calendar.get(7);
            return i == 1 || i == 7;
        }
    }

    public static b a() {
        return i;
    }

    public static boolean a(Context context) {
        try {
            if (!CommonUtil.isAppForeground()) {
                Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                return intentRegisterReceiver == null || intentRegisterReceiver.getExtras() == null || intentRegisterReceiver.getExtras().getInt("plugged") == 2;
            }
            boolean z = Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
            Intent intentRegisterReceiver2 = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intentRegisterReceiver2 != null && intentRegisterReceiver2.getExtras() != null) {
                return z && intentRegisterReceiver2.getExtras().getInt("plugged") == 2;
            }
            return true;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.b(th);
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(boolean r2, boolean r3) {
        /*
            r0 = 0
            if (r2 == 0) goto L12
            boolean r1 = com.getui.gtc.base.util.CommonUtil.isAppDebugEnable()     // Catch: java.lang.Throwable -> Lf
            if (r1 == 0) goto L12
            java.lang.String r2 = "check safe f: debuggable"
            com.getui.gtc.dim.e.b.b(r2)     // Catch: java.lang.Throwable -> Lf
            return r0
        Lf:
            r2 = move-exception
            goto La0
        L12:
            if (r2 == 0) goto L24
            android.content.Context r2 = com.getui.gtc.base.GtcProvider.context()     // Catch: java.lang.Throwable -> Lf
            boolean r2 = a(r2)     // Catch: java.lang.Throwable -> Lf
            if (r2 == 0) goto L24
            java.lang.String r2 = "check safe f: u-model"
            com.getui.gtc.dim.e.b.b(r2)     // Catch: java.lang.Throwable -> Lf
            return r0
        L24:
            if (r3 == 0) goto L32
            boolean r2 = c()     // Catch: java.lang.Throwable -> Lf
            if (r2 == 0) goto L32
            java.lang.String r2 = "check safe f: xp"
            com.getui.gtc.dim.e.b.b(r2)     // Catch: java.lang.Throwable -> Lf
            return r0
        L32:
            java.lang.String r2 = android.os.Build.FINGERPRINT     // Catch: java.lang.Throwable -> Lf
            java.lang.String r3 = "generic"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            r1 = 1
            if (r3 != 0) goto L91
            java.lang.String r3 = "unknown"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            if (r3 != 0) goto L91
            java.lang.String r3 = "generic_x86"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            if (r3 != 0) goto L91
            java.lang.String r3 = "vbox"
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            if (r2 == 0) goto L56
            goto L91
        L56:
            java.lang.String r2 = android.os.Build.MODEL     // Catch: java.lang.Throwable -> Lf
            java.lang.String r3 = "google_sdk"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            if (r3 != 0) goto L91
            java.lang.String r3 = "Emulator"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            if (r3 != 0) goto L91
            java.lang.String r3 = "Android SDK built for x86"
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            if (r2 == 0) goto L71
            goto L91
        L71:
            java.lang.String r2 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> Lf
            java.lang.String r3 = "Genymotion"
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> Lf
            if (r2 == 0) goto L7c
            goto L91
        L7c:
            java.lang.String r2 = android.os.Build.HARDWARE     // Catch: java.lang.Throwable -> Lf
            java.lang.String r3 = "goldfish"
            boolean r3 = r3.equals(r2)     // Catch: java.lang.Throwable -> Lf
            if (r3 != 0) goto L91
            java.lang.String r3 = "ranchu"
            boolean r2 = r3.equals(r2)     // Catch: java.lang.Throwable -> Lf
            if (r2 == 0) goto L8f
            goto L91
        L8f:
            r2 = 0
            goto L92
        L91:
            r2 = 1
        L92:
            if (r2 == 0) goto L9a
            java.lang.String r2 = "check safe f: emulator"
            com.getui.gtc.dim.e.b.b(r2)     // Catch: java.lang.Throwable -> Lf
            return r0
        L9a:
            java.lang.String r2 = "check safe s"
            com.getui.gtc.dim.e.b.a(r2)     // Catch: java.lang.Throwable -> Lf
            return r1
        La0:
            com.getui.gtc.dim.e.b.b(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.b.b.a(boolean, boolean):boolean");
    }

    private long b() {
        long j;
        synchronized (this) {
            if (this.l < 0) {
                this.l = b("dim-key-sdk-sync-install-time");
            }
            j = this.l;
        }
        return j;
    }

    public static long b(String str) {
        try {
            d unused = d.a.f2161a;
            h hVarA = d.a(str);
            Object obj = hVarA != null ? hVarA.f2169a : null;
            j = obj instanceof String ? Long.parseLong((String) obj) : -1L;
            com.getui.gtc.dim.e.b.a("dim sys get " + str + " from db: " + j);
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.b(th);
        }
        if (j < 0) {
            return 0L;
        }
        return j;
    }

    public static boolean c() {
        HashSet<String> hashSet;
        FileReader fileReader;
        BufferedReader bufferedReader;
        if (e(f("ZGUucm9idi5hbmRyb2lkLnhwb3NlZC5pbnN0YWxsZXI=")) || e(f("Y29tLnNhdXJpay5zdWJzdHJhdGU="))) {
            return true;
        }
        try {
            ClassLoader.getSystemClassLoader().loadClass(f("ZGUucm9idi5hbmRyb2lkLnhwb3NlZC5YcG9zZWRIZWxwZXJz"));
            return true;
        } catch (Exception e2) {
            String strF = f("ZGUucm9idi5hbmRyb2lkLnhwb3NlZC5YcG9zZWRCcmlkZ2U=");
            String strF2 = f("bWFpbg==");
            String strF3 = f("aGFuZGxlSG9va2VkTWV0aG9k");
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if (stackTraceElement.getClassName().equals(strF) && (stackTraceElement.getMethodName().equals(strF2) || stackTraceElement.getMethodName().equals(strF3))) {
                    return true;
                }
            }
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter((Writer) stringWriter, true);
            e2.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
            if (stringWriter.toString().contains(f("eHBvc2Vk"))) {
                return true;
            }
            try {
                hashSet = new HashSet();
                fileReader = new FileReader("/proc/" + Process.myPid() + "/maps");
                bufferedReader = new BufferedReader(fileReader);
            } catch (Throwable th) {
                com.getui.gtc.dim.e.b.b(th);
                return false;
            }
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.endsWith(".so") || line.endsWith(".jar")) {
                        hashSet.add(line.substring(line.lastIndexOf(Operators.SPACE_STR) + 1));
                    }
                } catch (Throwable th2) {
                    try {
                        com.getui.gtc.dim.e.b.b(th2);
                        fileReader.close();
                    } finally {
                        fileReader.close();
                        bufferedReader.close();
                    }
                }
                bufferedReader.close();
                return false;
            }
            String strF4 = f("Y29tLnNhdXJpay5zdWJzdHJhdGU=");
            String strF5 = f("WHBvc2VkQnJpZGdlLmphcg==");
            for (String str : hashSet) {
                if (str.contains(strF4)) {
                    return true;
                }
                if (str.contains(strF5)) {
                    return true;
                }
            }
            fileReader.close();
            bufferedReader.close();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.getui.gtc.dim.b.b.a> d(java.lang.String r18) {
        /*
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r0 = ","
            r2 = r18
            java.lang.String[] r2 = r2.split(r0)     // Catch: java.lang.Throwable -> Lbc
            int r3 = r2.length     // Catch: java.lang.Throwable -> Lbc
            r4 = 0
            r5 = 0
        L10:
            if (r5 >= r3) goto Lc0
            r0 = r2[r5]     // Catch: java.lang.Throwable -> Lbc
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lbc
            if (r6 != 0) goto Laf
            java.lang.String r6 = "\\|"
            java.lang.String[] r0 = r0.split(r6)     // Catch: java.lang.Throwable -> Lbc
            int r6 = r0.length     // Catch: java.lang.Throwable -> Lbc
            r7 = 6
            if (r6 != r7) goto Laf
            r6 = r0[r4]     // Catch: java.lang.Throwable -> La6
            r7 = 1
            r8 = r0[r7]     // Catch: java.lang.Throwable -> La6
            long r8 = java.lang.Long.parseLong(r8)     // Catch: java.lang.Throwable -> La6
            r10 = 2
            r10 = r0[r10]     // Catch: java.lang.Throwable -> La6
            java.lang.String r11 = "^\\d{2}:\\d{2}:\\d{2}-\\d{2}:\\d{2}:\\d{2}#[0-3]$"
            java.util.regex.Pattern r11 = java.util.regex.Pattern.compile(r11)     // Catch: java.lang.Throwable -> La6
            java.util.regex.Matcher r11 = r11.matcher(r10)     // Catch: java.lang.Throwable -> La6
            boolean r11 = r11.matches()     // Catch: java.lang.Throwable -> La6
            if (r11 != 0) goto L41
            goto Laf
        L41:
            java.lang.String r11 = "#"
            java.lang.String[] r10 = r10.split(r11)     // Catch: java.lang.Throwable -> La6
            r11 = r10[r4]     // Catch: java.lang.Throwable -> La6
            java.lang.String r12 = "-"
            java.lang.String[] r11 = r11.split(r12)     // Catch: java.lang.Throwable -> La6
            r12 = r11[r4]     // Catch: java.lang.Throwable -> La6
            r11 = r11[r7]     // Catch: java.lang.Throwable -> La6
            r7 = r10[r7]     // Catch: java.lang.Throwable -> La6
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Throwable -> La6
            r10 = 3
            r10 = r0[r10]     // Catch: java.lang.Throwable -> La6
            int r10 = java.lang.Integer.parseInt(r10)     // Catch: java.lang.Throwable -> La6
            r13 = 4
            r13 = r0[r13]     // Catch: java.lang.Throwable -> La6
            int r13 = java.lang.Integer.parseInt(r13)     // Catch: java.lang.Throwable -> La6
            r14 = 5
            r0 = r0[r14]     // Catch: java.lang.Throwable -> La6
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> La6
            long r14 = (long) r13     // Catch: java.lang.Throwable -> La6
            com.getui.gtc.dim.b.b r4 = com.getui.gtc.dim.b.b.i     // Catch: java.lang.Throwable -> La6
            r16 = r2
            r17 = r3
            long r2 = r4.f2154a     // Catch: java.lang.Throwable -> La4
            int r4 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r4 <= 0) goto L7f
            com.getui.gtc.dim.b.b r2 = com.getui.gtc.dim.b.b.i     // Catch: java.lang.Throwable -> La4
            r2.f2154a = r14     // Catch: java.lang.Throwable -> La4
        L7f:
            com.getui.gtc.dim.b.b r2 = com.getui.gtc.dim.b.b.i     // Catch: java.lang.Throwable -> La4
            long r2 = r2.c     // Catch: java.lang.Throwable -> La4
            int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r4 <= 0) goto L8b
            com.getui.gtc.dim.b.b r2 = com.getui.gtc.dim.b.b.i     // Catch: java.lang.Throwable -> La4
            r2.c = r8     // Catch: java.lang.Throwable -> La4
        L8b:
            com.getui.gtc.dim.b.b$a r2 = new com.getui.gtc.dim.b.b$a     // Catch: java.lang.Throwable -> La4
            r2.<init>()     // Catch: java.lang.Throwable -> La4
            r2.f2156a = r6     // Catch: java.lang.Throwable -> La4
            r2.b = r8     // Catch: java.lang.Throwable -> La4
            r2.c = r12     // Catch: java.lang.Throwable -> La4
            r2.d = r11     // Catch: java.lang.Throwable -> La4
            r2.f2157e = r7     // Catch: java.lang.Throwable -> La4
            r2.f = r10     // Catch: java.lang.Throwable -> La4
            r2.g = r13     // Catch: java.lang.Throwable -> La4
            r2.h = r0     // Catch: java.lang.Throwable -> La4
            r1.add(r2)     // Catch: java.lang.Throwable -> La4
            goto Lb3
        La4:
            r0 = move-exception
            goto Lab
        La6:
            r0 = move-exception
            r16 = r2
            r17 = r3
        Lab:
            com.getui.gtc.dim.e.b.b(r0)     // Catch: java.lang.Throwable -> Lbc
            goto Lb3
        Laf:
            r16 = r2
            r17 = r3
        Lb3:
            int r5 = r5 + 1
            r2 = r16
            r3 = r17
            r4 = 0
            goto L10
        Lbc:
            r0 = move-exception
            com.getui.gtc.dim.e.b.b(r0)
        Lc0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.b.b.d(java.lang.String):java.util.List");
    }

    public static boolean e(String str) {
        try {
            com.getui.gtc.dim.e.d.a(str, 0);
            com.getui.gtc.dim.e.b.a("specific " + str + " p info hit success");
            return true;
        } catch (Throwable unused) {
            com.getui.gtc.dim.e.b.a("specific " + str + " p info hit failed");
            return false;
        }
    }

    public static String f(String str) {
        return new String(Base64.decode(str, 0));
    }

    public final void a(long j) {
        if (this.m) {
            this.j = j;
            synchronized (this) {
                if (b() == 0) {
                    try {
                        this.l = System.currentTimeMillis() + j;
                        d unused = d.a.f2161a;
                        d.a("dim-key-sdk-sync-install-time", (Object) String.valueOf(this.l));
                        com.getui.gtc.dim.e.b.a("dim sys server install time set: " + this.l);
                    } catch (Throwable th) {
                        com.getui.gtc.dim.e.b.b(th);
                    }
                }
            }
        }
        com.getui.gtc.dim.e.b.a("dim sys syncTime set: " + j + ", syncTime: " + this.m);
    }

    public final void a(String str) {
        try {
            for (String str2 : str.split(",")) {
                String[] strArrSplit = str2.split(Constants.COLON_SEPARATOR);
                String str3 = strArrSplit[0];
                boolean z = true;
                if (Integer.parseInt(strArrSplit[1]) != 1) {
                    z = false;
                }
                if ("dim".equals(str3)) {
                    this.f = z;
                } else if ("xp".equals(str3)) {
                    this.g = z;
                } else if (com.umeng.analytics.pro.f.ac.equals(str3)) {
                    this.h = z;
                } else if ("st".equals(str3)) {
                    this.m = z;
                }
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.b(th);
        }
        com.getui.gtc.dim.e.b.a("dim sys globalHC policy set: ".concat(String.valueOf(str)));
    }

    public final void a(String str, a aVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.k.put(str, aVar);
        com.getui.gtc.dim.e.b.a("dim sys globalHC set: " + str + " : " + aVar);
    }

    public final boolean c(String str) {
        boolean z;
        try {
            a aVar = this.k.get(str);
            if (aVar == null) {
                aVar = this.k.get("dim-2-2-0-1");
            }
            if (aVar == null) {
                com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , config is null");
                return false;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() + this.j;
            if (aVar.b > 0 && this.d < aVar.b * 1000) {
                com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , 1 not passed, " + this.d);
                return false;
            }
            if (aVar.f2157e == 1) {
                String str2 = aVar.c;
                String str3 = aVar.d;
                Calendar calendarA = C0037b.a(jCurrentTimeMillis, str2);
                Calendar calendarA2 = C0037b.a(jCurrentTimeMillis, str3);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(jCurrentTimeMillis);
                if (calendarA2.before(calendarA)) {
                    z = calendar.after(calendarA) || calendar.before(calendarA2);
                } else {
                    if (calendar.after(calendarA) && calendar.before(calendarA2)) {
                    }
                }
                if (z) {
                    if (aVar.f > 0 && aVar.f <= 3 && ((aVar.f != 1 || !C0037b.a(jCurrentTimeMillis)) && (aVar.f != 2 || C0037b.a(jCurrentTimeMillis)))) {
                        if (aVar.g > 0 && this.b < aVar.g) {
                            com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , 4 not passed, " + this.b);
                            return false;
                        }
                        long jB = b();
                        if (jB == 0) {
                            jB = com.getui.gtc.dim.e.d.a(GtcProvider.context().getPackageName(), 0).firstInstallTime;
                        }
                        if (aVar.h <= 0 || jCurrentTimeMillis - jB >= aVar.h * ConfigManager.A_DAY) {
                            com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , passed");
                            return true;
                        }
                        com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , 5 not passed, " + jB);
                        return false;
                    }
                    com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , 3 not passed");
                    return false;
                }
            }
            com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , 2 not passed");
            return false;
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("check filed condition : " + str + " , catch exception, not passed", th);
            return false;
        }
    }
}
