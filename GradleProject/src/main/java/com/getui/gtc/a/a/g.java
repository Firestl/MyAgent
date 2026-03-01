package com.getui.gtc.a.a;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static byte[] a(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = null;
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
                gZIPOutputStream = null;
            }
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.finish();
                byteArray = byteArrayOutputStream.toByteArray();
                try {
                    gZIPOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    byteArrayOutputStream.close();
                } finally {
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return byteArray;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:0|2|(3:73|3|(2:64|4))|(5:62|5|(1:7)(1:80)|72|39)|8|75|9|58|13|17|72|39|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:0|2|73|3|64|4|(5:62|5|(1:7)(1:80)|72|39)|8|75|9|58|13|17|72|39|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0024, code lost:
    
        r2.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
    
        r1.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] b(byte[] r5) {
        /*
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r5)
            r5 = 0
            java.util.zip.GZIPInputStream r1 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L3d
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L3d
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3a
            r2.<init>()     // Catch: java.lang.Throwable -> L3a
        L10:
            int r3 = r1.read()     // Catch: java.lang.Throwable -> L38
            r4 = -1
            if (r3 == r4) goto L1b
            r2.write(r3)     // Catch: java.lang.Throwable -> L38
            goto L10
        L1b:
            byte[] r5 = r2.toByteArray()     // Catch: java.lang.Throwable -> L38
            r2.close()     // Catch: java.lang.Exception -> L23
            goto L27
        L23:
            r2 = move-exception
            r2.printStackTrace()
        L27:
            r1.close()     // Catch: java.lang.Exception -> L2b
            goto L2f
        L2b:
            r1 = move-exception
            r1.printStackTrace()
        L2f:
            r0.close()     // Catch: java.lang.Exception -> L33
            goto L5a
        L33:
            r0 = move-exception
            r0.printStackTrace()
            goto L5a
        L38:
            r3 = move-exception
            goto L40
        L3a:
            r3 = move-exception
            r2 = r5
            goto L40
        L3d:
            r3 = move-exception
            r1 = r5
            r2 = r1
        L40:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L5b
            if (r2 == 0) goto L4d
            r2.close()     // Catch: java.lang.Exception -> L49
            goto L4d
        L49:
            r2 = move-exception
            r2.printStackTrace()
        L4d:
            if (r1 == 0) goto L57
            r1.close()     // Catch: java.lang.Exception -> L53
            goto L57
        L53:
            r1 = move-exception
            r1.printStackTrace()
        L57:
            r0.close()     // Catch: java.lang.Exception -> L33
        L5a:
            return r5
        L5b:
            r5 = move-exception
            if (r2 == 0) goto L66
            r2.close()     // Catch: java.lang.Exception -> L62
            goto L66
        L62:
            r2 = move-exception
            r2.printStackTrace()
        L66:
            if (r1 == 0) goto L70
            r1.close()     // Catch: java.lang.Exception -> L6c
            goto L70
        L6c:
            r1 = move-exception
            r1.printStackTrace()
        L70:
            r0.close()     // Catch: java.lang.Exception -> L74
            goto L78
        L74:
            r0 = move-exception
            r0.printStackTrace()
        L78:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.a.a.g.b(byte[]):byte[]");
    }
}
