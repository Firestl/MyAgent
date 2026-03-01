package cn.com.chinatelecom.account.a;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/* JADX INFO: loaded from: classes.dex */
public class b {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0069 A[Catch: Exception -> 0x006d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x006d, blocks: (B:23:0x003d, B:46:0x0069), top: B:76:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x006e -> B:75:0x0071). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r5) {
        /*
            java.io.File r5 = c(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            if (r5 == 0) goto L96
            boolean r1 = r5.exists()
            if (r1 != 0) goto L13
            goto L96
        L13:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4b
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L4b
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L48
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L48
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L43
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L43
        L23:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L2d
            r0.append(r1)     // Catch: java.lang.Throwable -> L41
            goto L23
        L2d:
            r3.close()     // Catch: java.lang.Exception -> L31
            goto L35
        L31:
            r1 = move-exception
            r1.printStackTrace()
        L35:
            r5.close()     // Catch: java.lang.Exception -> L39
            goto L3d
        L39:
            r5 = move-exception
            r5.printStackTrace()
        L3d:
            r2.close()     // Catch: java.lang.Exception -> L6d
            goto L71
        L41:
            r1 = move-exception
            goto L50
        L43:
            r3 = move-exception
            r4 = r3
            r3 = r1
            r1 = r4
            goto L50
        L48:
            r5 = move-exception
            r3 = r1
            goto L4e
        L4b:
            r5 = move-exception
            r2 = r1
            r3 = r2
        L4e:
            r1 = r5
            r5 = r3
        L50:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L76
            if (r3 == 0) goto L5d
            r3.close()     // Catch: java.lang.Exception -> L59
            goto L5d
        L59:
            r1 = move-exception
            r1.printStackTrace()
        L5d:
            if (r5 == 0) goto L67
            r5.close()     // Catch: java.lang.Exception -> L63
            goto L67
        L63:
            r5 = move-exception
            r5.printStackTrace()
        L67:
            if (r2 == 0) goto L71
            r2.close()     // Catch: java.lang.Exception -> L6d
            goto L71
        L6d:
            r5 = move-exception
            r5.printStackTrace()
        L71:
            java.lang.String r5 = r0.toString()
            return r5
        L76:
            r0 = move-exception
            if (r3 == 0) goto L81
            r3.close()     // Catch: java.lang.Exception -> L7d
            goto L81
        L7d:
            r1 = move-exception
            r1.printStackTrace()
        L81:
            if (r5 == 0) goto L8b
            r5.close()     // Catch: java.lang.Exception -> L87
            goto L8b
        L87:
            r5 = move-exception
            r5.printStackTrace()
        L8b:
            if (r2 == 0) goto L95
            r2.close()     // Catch: java.lang.Exception -> L91
            goto L95
        L91:
            r5 = move-exception
            r5.printStackTrace()
        L95:
            throw r0
        L96:
            java.lang.String r5 = ""
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.b.a(android.content.Context):java.lang.String");
    }

    public static void a(Context context, String str) throws Throwable {
        File fileC = c(context);
        if (fileC == null || !fileC.exists()) {
            a(b(context), str);
        } else {
            a(fileC, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x004f -> B:52:0x0069). Please report as a decompilation issue!!! */
    public static void a(File file, String str) throws Throwable {
        FileWriter fileWriter;
        if (file == null || !file.exists()) {
            return;
        }
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                try {
                    fileWriter = new FileWriter(file, false);
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        try {
                            boolean zIsEmpty = TextUtils.isEmpty(str);
                            if (zIsEmpty) {
                                str = "";
                            }
                            bufferedWriter.write(str);
                            bufferedWriter.flush();
                            try {
                                bufferedWriter.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            fileWriter.close();
                            r0 = zIsEmpty;
                        } catch (Exception e3) {
                            e = e3;
                            r0 = bufferedWriter;
                            e.printStackTrace();
                            if (r0 != 0) {
                                try {
                                    r0.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (fileWriter != null) {
                                fileWriter.close();
                                r0 = r0;
                            }
                        } catch (Throwable th) {
                            th = th;
                            r0 = bufferedWriter;
                            if (r0 != 0) {
                                try {
                                    r0.close();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (fileWriter == null) {
                                throw th;
                            }
                            try {
                                fileWriter.close();
                                throw th;
                            } catch (Exception e6) {
                                e6.printStackTrace();
                                throw th;
                            }
                        }
                    } catch (Exception e7) {
                        e = e7;
                    }
                } catch (Exception e8) {
                    e = e8;
                    fileWriter = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileWriter = null;
                }
            } catch (Exception e9) {
                e9.printStackTrace();
                r0 = r0;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static File b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            File file = new File(context.getFilesDir() + "/eAccount/Log/");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, "ipa_ol.ds");
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            return file2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static File c(Context context) {
        if (context != null) {
            try {
                File file = new File(context.getFilesDir() + "/eAccount/Log/");
                if (!file.exists()) {
                    return null;
                }
                File file2 = new File(file, "ipa_ol.ds");
                if (file2.exists()) {
                    return file2;
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
