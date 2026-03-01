package com.igexin.push.config;

import com.huawei.hms.framework.common.ContainerUtils;
import com.igexin.push.g.j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3290a = "FileConfig";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054 A[Catch: all -> 0x00cf, Exception -> 0x00e5, TryCatch #15 {Exception -> 0x00e5, all -> 0x00cf, blocks: (B:23:0x0047, B:25:0x0054, B:28:0x007e, B:29:0x0085, B:30:0x0086), top: B:101:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009f A[Catch: all -> 0x00c1, Exception -> 0x00c6, TryCatch #18 {Exception -> 0x00c6, all -> 0x00c1, blocks: (B:32:0x0099, B:34:0x009f, B:36:0x00a7), top: B:95:0x0099 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00b0 A[EDGE_INSN: B:83:0x00b0->B:38:0x00b0 BREAK  A[LOOP:0: B:95:0x0099->B:104:0x0099], EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.content.res.AssetManager] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a() {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.config.e.a():void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.io.InputStream r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 620
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.config.e.a(java.io.InputStream):void");
    }

    public static void a(Boolean bool) {
        try {
            j.m();
            if (new File(j.f3596e).exists()) {
                b(bool);
                return;
            }
            byte[] bytes = "sdk.debug=".concat(String.valueOf(bool)).getBytes();
            if (bytes != null) {
                j.a(bytes, j.f3596e);
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    public static void a(boolean z, boolean z2) {
        try {
            j.m();
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.f3388a, Boolean.valueOf(z));
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.b, Boolean.valueOf(z2));
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    public static int b() {
        try {
            j.m();
            Boolean boolA = com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.f3388a);
            int i = boolA == null ? -1 : boolA.booleanValue() ? 1 : 0;
            com.igexin.c.a.c.a.a(f3290a + "|getGuardMeFromFile gm= " + i, new Object[0]);
            return i;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return -1;
        }
    }

    public static void b(Boolean bool) throws Throwable {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            fileInputStream = new FileInputStream(j.f3596e);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("#")) {
                    sb.append(line);
                } else {
                    String[] strArrSplit = line.split(ContainerUtils.KEY_VALUE_DELIMITER);
                    if (strArrSplit.length < 2) {
                        sb.append(line);
                    } else {
                        String strTrim = strArrSplit[0].trim();
                        strArrSplit[1].trim();
                        if (!strTrim.equals("sdk.debug")) {
                            sb.append(line);
                        }
                    }
                }
                sb.append("\n");
            }
            sb.append("sdk.debug=".concat(String.valueOf(bool)));
            byte[] bytes = sb.toString().getBytes();
            if (bytes != null) {
                j.a(bytes, j.f3596e);
            }
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            try {
                fileInputStream.close();
            } catch (Exception e3) {
                com.igexin.c.a.c.a.a(e3);
            }
        } catch (Exception unused3) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    com.igexin.c.a.c.a.a(e4);
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e5) {
                    com.igexin.c.a.c.a.a(e5);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e6) {
                    com.igexin.c.a.c.a.a(e6);
                }
            }
            if (fileInputStream == null) {
                throw th;
            }
            try {
                fileInputStream.close();
                throw th;
            } catch (Exception e7) {
                com.igexin.c.a.c.a.a(e7);
                throw th;
            }
        }
    }

    public static int c() {
        try {
            Boolean boolA = com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.b);
            int i = boolA == null ? -1 : boolA.booleanValue() ? 1 : 0;
            com.igexin.c.a.c.a.a(f3290a + "|getGuardOthersFromFile gm= " + i, new Object[0]);
            return i;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return -1;
        }
    }
}
