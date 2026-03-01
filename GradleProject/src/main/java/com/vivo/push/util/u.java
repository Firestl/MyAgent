package com.vivo.push.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: compiled from: SdcardCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5643a = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".vivo/pushsdk/config";
    public static final String b = f5643a + File.separator + "config.txt";
    public static String c = "SdcardCache";
    public File d;

    @Override // com.vivo.push.util.d
    public final boolean a(Context context) {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return false;
        }
        File file = new File(f5643a);
        boolean zMkdirs = !file.exists() ? file.mkdirs() : true;
        if (zMkdirs) {
            File file2 = new File(b);
            this.d = file2;
            if (!file2.exists()) {
                try {
                    this.d.createNewFile();
                    return true;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
        }
        return zMkdirs;
    }

    @Override // com.vivo.push.util.d
    public final void b(String str, String str2) throws Throwable {
        Properties propertiesA = a();
        String str3 = b;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                propertiesA.setProperty(str, str2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(str3);
                try {
                    propertiesA.store(fileOutputStream2, (String) null);
                    fileOutputStream2.flush();
                    try {
                        fileOutputStream2.close();
                    } catch (Exception unused) {
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    @Override // com.vivo.push.util.d
    public final String a(String str, String str2) {
        return a().getProperty(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x002c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Properties a() throws java.lang.Throwable {
        /*
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            java.lang.String r4 = com.vivo.push.util.u.b     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1d
            r0.load(r2)     // Catch: java.lang.Exception -> L19 java.lang.Throwable -> L28
        L15:
            r2.close()     // Catch: java.io.IOException -> L27
            goto L27
        L19:
            r1 = move-exception
            goto L21
        L1b:
            r0 = move-exception
            goto L2a
        L1d:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L21:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L28
            if (r2 == 0) goto L27
            goto L15
        L27:
            return r0
        L28:
            r0 = move-exception
            r1 = r2
        L2a:
            if (r1 == 0) goto L2f
            r1.close()     // Catch: java.io.IOException -> L2f
        L2f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.u.a():java.util.Properties");
    }
}
