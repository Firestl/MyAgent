package supwisdom;

import android.content.Context;
import io.dcloud.common.constant.AbsoluteConst;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes2.dex */
public class dv0 {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r3, java.lang.String r4) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            java.io.InputStream r3 = r3.open(r4)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            r1.<init>(r3)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            r4.<init>(r1)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            r1.<init>()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
        L18:
            java.lang.String r2 = r4.readLine()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            r1.append(r2)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            if (r2 != 0) goto L18
            r4.close()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            r3.close()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L40
            if (r3 == 0) goto L30
            r3.close()     // Catch: java.io.IOException -> L30
        L30:
            return r4
        L31:
            r4 = move-exception
            goto L37
        L33:
            r4 = move-exception
            goto L42
        L35:
            r4 = move-exception
            r3 = r0
        L37:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L40
            if (r3 == 0) goto L3f
            r3.close()     // Catch: java.io.IOException -> L3f
        L3f:
            return r0
        L40:
            r4 = move-exception
            r0 = r3
        L42:
            if (r0 == 0) goto L47
            r0.close()     // Catch: java.io.IOException -> L47
        L47:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.dv0.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static String b(Context context, String str) throws Throwable {
        return AbsoluteConst.PROTOCOL_JAVASCRIPT + a(context, str);
    }
}
