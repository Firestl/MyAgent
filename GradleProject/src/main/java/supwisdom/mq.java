package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class mq {
    /* JADX WARN: Removed duplicated region for block: B:22:0x0040 A[Catch: all -> 0x0043, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0043, blocks: (B:11:0x002d, B:22:0x0040), top: B:28:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            r2.<init>(r4, r5)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            boolean r4 = r2.exists()     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            if (r4 != 0) goto L12
            return r1
        L12:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            java.lang.String r2 = "UTF-8"
            r5.<init>(r3, r2)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L3d
        L23:
            java.lang.String r5 = r4.readLine()     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L34
            if (r5 == 0) goto L2d
            r0.append(r5)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L34
            goto L23
        L2d:
            r4.close()     // Catch: java.lang.Throwable -> L43
            goto L43
        L31:
            r5 = move-exception
            r1 = r4
            goto L37
        L34:
            r1 = r4
            goto L3e
        L36:
            r5 = move-exception
        L37:
            if (r1 == 0) goto L3c
            r1.close()     // Catch: java.lang.Throwable -> L3c
        L3c:
            throw r5
        L3d:
        L3e:
            if (r1 == 0) goto L43
            r1.close()     // Catch: java.lang.Throwable -> L43
        L43:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.mq.a(java.lang.String, java.lang.String):java.lang.String");
    }
}
