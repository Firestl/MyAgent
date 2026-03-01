package com.efs.sdk.memoryinfo;

import android.os.Build;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static long a(Debug.MemoryInfo memoryInfo) {
        if (Build.VERSION.SDK_INT < 23) {
            return 0L;
        }
        String memoryStat = memoryInfo.getMemoryStat("summary.graphics");
        try {
            if (TextUtils.isEmpty(memoryStat)) {
                return 0L;
            }
            return Long.parseLong(memoryStat);
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0069, code lost:
    
        r5 = java.lang.Long.parseLong(r8.group());
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long a() {
        /*
            r0 = -1
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L9f
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L9f
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L9f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9f
            java.lang.String r6 = "/proc/"
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L9f
            int r6 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L9f
            r5.append(r6)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r6 = "/status"
            r5.append(r6)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L9f
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L9f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L9f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L9f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9d
            r3.<init>()     // Catch: java.lang.Throwable -> L9d
        L2d:
            java.lang.String r4 = r2.readLine()     // Catch: java.lang.Throwable -> L9d
            if (r4 == 0) goto L3c
            r3.append(r4)     // Catch: java.lang.Throwable -> L9d
            r4 = 10
            r3.append(r4)     // Catch: java.lang.Throwable -> L9d
            goto L2d
        L3c:
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r4 = "\n"
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Throwable -> L9d
            int r4 = r3.length     // Catch: java.lang.Throwable -> L9d
            int r5 = r3.length     // Catch: java.lang.Throwable -> L9d
            r6 = 0
        L4d:
            java.lang.String r7 = "\\d+"
            if (r6 >= r5) goto L75
            r8 = r3[r6]     // Catch: java.lang.Throwable -> L9d
            java.lang.String r9 = "VmSize"
            boolean r9 = r8.startsWith(r9)     // Catch: java.lang.Throwable -> L9d
            if (r9 == 0) goto L72
            java.util.regex.Pattern r9 = java.util.regex.Pattern.compile(r7)     // Catch: java.lang.Throwable -> L9d
            java.util.regex.Matcher r8 = r9.matcher(r8)     // Catch: java.lang.Throwable -> L9d
            boolean r9 = r8.find()     // Catch: java.lang.Throwable -> L9d
            if (r9 == 0) goto L72
            java.lang.String r5 = r8.group()     // Catch: java.lang.Throwable -> L9d
            long r5 = java.lang.Long.parseLong(r5)     // Catch: java.lang.Throwable -> L9d
            goto L76
        L72:
            int r6 = r6 + 1
            goto L4d
        L75:
            r5 = r0
        L76:
            int r8 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r8 != 0) goto L99
            r0 = 12
            if (r4 <= r0) goto L99
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r7)     // Catch: java.lang.Throwable -> L97
            r0 = r3[r0]     // Catch: java.lang.Throwable -> L97
            java.util.regex.Matcher r0 = r1.matcher(r0)     // Catch: java.lang.Throwable -> L97
            boolean r1 = r0.find()     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L99
            java.lang.String r0 = r0.group()     // Catch: java.lang.Throwable -> L97
            long r5 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Throwable -> L97
            goto L99
        L97:
            r0 = r5
            goto La0
        L99:
            r2.close()     // Catch: java.lang.Throwable -> La6
            goto La6
        L9d:
            goto La0
        L9f:
            r2 = 0
        La0:
            if (r2 == 0) goto La5
            r2.close()     // Catch: java.lang.Throwable -> La5
        La5:
            r5 = r0
        La6:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.efs.sdk.memoryinfo.f.a():long");
    }

    public static void a(String str, Throwable th) {
        if (a.DEBUG) {
            Log.e("MemoryCollect", str, th);
        }
    }
}
