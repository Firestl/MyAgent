package com.unicom.xiaowo.account.shield.e;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static void a(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                a(file2);
            }
        }
        file.delete();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0043 -> B:52:0x0046). Please report as a decompilation issue!!! */
    public static void a(byte[] bArr, String str) throws Throwable {
        BufferedOutputStream bufferedOutputStream = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                str = new FileOutputStream(new File((String) str));
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(str);
                    try {
                        bufferedOutputStream2.write(bArr);
                        bufferedOutputStream2.flush();
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        str.close();
                    } catch (Exception e4) {
                        e = e4;
                        bufferedOutputStream = bufferedOutputStream2;
                        e.printStackTrace();
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        if (str != 0) {
                            str.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (str == 0) {
                            throw th;
                        }
                        try {
                            str.close();
                            throw th;
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Exception e8) {
                    e = e8;
                }
            } catch (Exception e9) {
                e = e9;
                str = 0;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x002c -> B:69:0x0062). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(java.io.InputStream r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L39 java.io.FileNotFoundException -> L4e
            r2 = 8192(0x2000, float:1.148E-41)
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L39 java.io.FileNotFoundException -> L4e
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L30 java.io.FileNotFoundException -> L32 java.lang.Throwable -> L63
        La:
            int r3 = r6.read(r2)     // Catch: java.io.IOException -> L30 java.io.FileNotFoundException -> L32 java.lang.Throwable -> L63
            r4 = -1
            if (r3 == r4) goto L16
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.io.IOException -> L30 java.io.FileNotFoundException -> L32 java.lang.Throwable -> L63
            goto La
        L16:
            r1.flush()     // Catch: java.io.IOException -> L30 java.io.FileNotFoundException -> L32 java.lang.Throwable -> L63
            byte[] r0 = r1.toByteArray()     // Catch: java.io.IOException -> L30 java.io.FileNotFoundException -> L32 java.lang.Throwable -> L63
            if (r6 == 0) goto L27
            r6.close()     // Catch: java.io.IOException -> L23
            goto L27
        L23:
            r6 = move-exception
            r6.printStackTrace()
        L27:
            r1.close()     // Catch: java.io.IOException -> L2b
            goto L62
        L2b:
            r6 = move-exception
            r6.printStackTrace()
            goto L62
        L30:
            r2 = move-exception
            goto L3b
        L32:
            r2 = move-exception
            goto L50
        L34:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L64
        L39:
            r2 = move-exception
            r1 = r0
        L3b:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L63
            if (r6 == 0) goto L48
            r6.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r6 = move-exception
            r6.printStackTrace()
        L48:
            if (r1 == 0) goto L62
            r1.close()     // Catch: java.io.IOException -> L2b
            goto L62
        L4e:
            r2 = move-exception
            r1 = r0
        L50:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L63
            if (r6 == 0) goto L5d
            r6.close()     // Catch: java.io.IOException -> L59
            goto L5d
        L59:
            r6 = move-exception
            r6.printStackTrace()
        L5d:
            if (r1 == 0) goto L62
            r1.close()     // Catch: java.io.IOException -> L2b
        L62:
            return r0
        L63:
            r0 = move-exception
        L64:
            if (r6 == 0) goto L6e
            r6.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L6a:
            r6 = move-exception
            r6.printStackTrace()
        L6e:
            if (r1 == 0) goto L78
            r1.close()     // Catch: java.io.IOException -> L74
            goto L78
        L74:
            r6 = move-exception
            r6.printStackTrace()
        L78:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.xiaowo.account.shield.e.b.a(java.io.InputStream):byte[]");
    }
}
