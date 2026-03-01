package com.baidu.idl.license;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class LicenseReader {
    public static final String TAG = "License-SDK";
    public static final String URL = "https://aip.baidubce.com/public/2.0/license/face-api/app/querydevicelicense";
    public String path;

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0141: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:71:0x0141 */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0120 A[LOOP:1: B:66:0x011a->B:68:0x0120, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0144 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String[] get_local_license(android.content.Context r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.idl.license.LicenseReader.get_local_license(android.content.Context):java.lang.String[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01df A[Catch: IOException -> 0x01db, TryCatch #28 {IOException -> 0x01db, blocks: (B:98:0x01d7, B:102:0x01df, B:104:0x01e4), top: B:180:0x01d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01e4 A[Catch: IOException -> 0x01db, TRY_LEAVE, TryCatch #28 {IOException -> 0x01db, blocks: (B:98:0x01d7, B:102:0x01df, B:104:0x01e4), top: B:180:0x01d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0213 A[Catch: IOException -> 0x020f, TryCatch #19 {IOException -> 0x020f, blocks: (B:111:0x020b, B:115:0x0213, B:117:0x0218), top: B:174:0x020b }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0218 A[Catch: IOException -> 0x020f, TRY_LEAVE, TryCatch #19 {IOException -> 0x020f, blocks: (B:111:0x020b, B:115:0x0213, B:117:0x0218), top: B:174:0x020b }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0221 A[PHI: r5 r7 r8 r13
  0x0221: PHI (r5v9 java.net.HttpURLConnection) = (r5v6 java.net.HttpURLConnection), (r5v7 java.net.HttpURLConnection), (r5v10 java.net.HttpURLConnection) binds: [B:94:0x01b6, B:107:0x01eb, B:120:0x021f] A[DONT_GENERATE, DONT_INLINE]
  0x0221: PHI (r7v9 java.io.ByteArrayOutputStream) = (r7v6 java.io.ByteArrayOutputStream), (r7v7 java.io.ByteArrayOutputStream), (r7v10 java.io.ByteArrayOutputStream) binds: [B:94:0x01b6, B:107:0x01eb, B:120:0x021f] A[DONT_GENERATE, DONT_INLINE]
  0x0221: PHI (r8v6 java.lang.String) = (r8v3 java.lang.String), (r8v4 java.lang.String), (r8v7 java.lang.String) binds: [B:94:0x01b6, B:107:0x01eb, B:120:0x021f] A[DONT_GENERATE, DONT_INLINE]
  0x0221: PHI (r13v9 ??) = (r13v6 ??), (r13v7 ??), (r13v10 ??) binds: [B:94:0x01b6, B:107:0x01eb, B:120:0x021f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0277 A[LOOP:1: B:146:0x0271->B:148:0x0277, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x022b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x020b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x01a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x01d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:196:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01aa A[Catch: IOException -> 0x01a6, TryCatch #26 {IOException -> 0x01a6, blocks: (B:85:0x01a2, B:89:0x01aa, B:91:0x01af), top: B:178:0x01a2 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01af A[Catch: IOException -> 0x01a6, TRY_LEAVE, TryCatch #26 {IOException -> 0x01a6, blocks: (B:85:0x01a2, B:89:0x01aa, B:91:0x01af), top: B:178:0x01a2 }] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v30 */
    /* JADX WARN: Type inference failed for: r13v36 */
    /* JADX WARN: Type inference failed for: r13v37 */
    /* JADX WARN: Type inference failed for: r13v38 */
    /* JADX WARN: Type inference failed for: r13v39 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v40 */
    /* JADX WARN: Type inference failed for: r13v41 */
    /* JADX WARN: Type inference failed for: r13v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r13v7, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String[] get_remote_license(android.content.Context r12, java.lang.String r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 675
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.idl.license.LicenseReader.get_remote_license(android.content.Context, java.lang.String):java.lang.String[]");
    }

    public int init(String str) {
        this.path = str;
        return 0;
    }

    public int put_local_license(Context context, String[] strArr) throws Throwable {
        File dir = context.getDir(this.path, 0);
        Log.e("License-SDK", "put_local_license =" + dir.getAbsolutePath());
        if (dir != null) {
            dir.delete();
        }
        if (dir != null && !dir.exists()) {
            try {
                dir.createNewFile();
            } catch (IOException e2) {
                Log.e("License-SDK", "IOException");
                e2.printStackTrace();
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(dir);
                    try {
                        for (String str : strArr) {
                            fileOutputStream2.write(str.getBytes());
                            fileOutputStream2.write(10);
                        }
                        fileOutputStream2.close();
                        return 0;
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        fileOutputStream = fileOutputStream2;
                        Log.e("License-SDK", "FileNotFoundException");
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return -1;
                    } catch (IOException e4) {
                        e = e4;
                        fileOutputStream = fileOutputStream2;
                        Log.e("License-SDK", "IOException");
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return -1;
                    } catch (Exception e5) {
                        e = e5;
                        fileOutputStream = fileOutputStream2;
                        Log.e("License-SDK", "Exception");
                        e.printStackTrace();
                        if (fileOutputStream == null) {
                            return 0;
                        }
                        fileOutputStream.close();
                        return 0;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e7) {
                    e = e7;
                } catch (IOException e8) {
                    e = e8;
                } catch (Exception e9) {
                    e = e9;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e10) {
            e10.printStackTrace();
        }
    }
}
