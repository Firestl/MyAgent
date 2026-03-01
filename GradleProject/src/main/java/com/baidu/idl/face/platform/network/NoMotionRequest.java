package com.baidu.idl.face.platform.network;

import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class NoMotionRequest extends BaseRequest {
    public static final String TAG = "NoMotionRequest";
    public static final String URL_POST_NOMOTION_LIVENESS = "http://face.baidu.com/gate/api/userverifydemo";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0159 A[Catch: IOException -> 0x0155, TryCatch #22 {IOException -> 0x0155, blocks: (B:97:0x0151, B:101:0x0159, B:103:0x015e, B:105:0x0163), top: B:205:0x0151 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x015e A[Catch: IOException -> 0x0155, TryCatch #22 {IOException -> 0x0155, blocks: (B:97:0x0151, B:101:0x0159, B:103:0x015e, B:105:0x0163), top: B:205:0x0151 }] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0163 A[Catch: IOException -> 0x0155, TRY_LEAVE, TryCatch #22 {IOException -> 0x0155, blocks: (B:97:0x0151, B:101:0x0159, B:103:0x015e, B:105:0x0163), top: B:205:0x0151 }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0182 A[Catch: IOException -> 0x017e, TryCatch #16 {IOException -> 0x017e, blocks: (B:115:0x017a, B:119:0x0182, B:121:0x0187, B:123:0x018c), top: B:203:0x017a }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0187 A[Catch: IOException -> 0x017e, TryCatch #16 {IOException -> 0x017e, blocks: (B:115:0x017a, B:119:0x0182, B:121:0x0187, B:123:0x018c), top: B:203:0x017a }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x018c A[Catch: IOException -> 0x017e, TRY_LEAVE, TryCatch #16 {IOException -> 0x017e, blocks: (B:115:0x017a, B:119:0x0182, B:121:0x0187, B:123:0x018c), top: B:203:0x017a }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ab A[Catch: IOException -> 0x01a7, TryCatch #34 {IOException -> 0x01a7, blocks: (B:133:0x01a3, B:137:0x01ab, B:139:0x01b0, B:141:0x01b5), top: B:209:0x01a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01b0 A[Catch: IOException -> 0x01a7, TryCatch #34 {IOException -> 0x01a7, blocks: (B:133:0x01a3, B:137:0x01ab, B:139:0x01b0, B:141:0x01b5), top: B:209:0x01a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01b5 A[Catch: IOException -> 0x01a7, TRY_LEAVE, TryCatch #34 {IOException -> 0x01a7, blocks: (B:133:0x01a3, B:137:0x01ab, B:139:0x01b0, B:141:0x01b5), top: B:209:0x01a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d3 A[Catch: IOException -> 0x01cf, TryCatch #29 {IOException -> 0x01cf, blocks: (B:151:0x01cb, B:155:0x01d3, B:157:0x01d8, B:159:0x01dd), top: B:207:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01d8 A[Catch: IOException -> 0x01cf, TryCatch #29 {IOException -> 0x01cf, blocks: (B:151:0x01cb, B:155:0x01d3, B:157:0x01d8, B:159:0x01dd), top: B:207:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01dd A[Catch: IOException -> 0x01cf, TRY_LEAVE, TryCatch #29 {IOException -> 0x01cf, blocks: (B:151:0x01cb, B:155:0x01d3, B:157:0x01d8, B:159:0x01dd), top: B:207:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01fb A[Catch: IOException -> 0x01f7, TryCatch #2 {IOException -> 0x01f7, blocks: (B:169:0x01f3, B:173:0x01fb, B:175:0x0200, B:177:0x0205), top: B:199:0x01f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0200 A[Catch: IOException -> 0x01f7, TryCatch #2 {IOException -> 0x01f7, blocks: (B:169:0x01f3, B:173:0x01fb, B:175:0x0200, B:177:0x0205), top: B:199:0x01f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0205 A[Catch: IOException -> 0x01f7, TRY_LEAVE, TryCatch #2 {IOException -> 0x01f7, blocks: (B:169:0x01f3, B:173:0x01fb, B:175:0x0200, B:177:0x0205), top: B:199:0x01f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0229 A[Catch: IOException -> 0x0225, TryCatch #35 {IOException -> 0x0225, blocks: (B:185:0x0221, B:189:0x0229, B:191:0x022e, B:193:0x0233), top: B:211:0x0221 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x022e A[Catch: IOException -> 0x0225, TryCatch #35 {IOException -> 0x0225, blocks: (B:185:0x0221, B:189:0x0229, B:191:0x022e, B:193:0x0233), top: B:211:0x0221 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0233 A[Catch: IOException -> 0x0225, TRY_LEAVE, TryCatch #35 {IOException -> 0x0225, blocks: (B:185:0x0221, B:189:0x0229, B:191:0x022e, B:193:0x0233), top: B:211:0x0221 }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x01f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x017a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0151 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x01cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x01a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0221 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:227:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:228:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:229:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:230:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:231:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:232:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v35, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19, types: [int] */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26, types: [int] */
    /* JADX WARN: Type inference failed for: r9v43, types: [int] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void httpUrlConnectionPost(java.lang.String r9, android.os.Handler r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.idl.face.platform.network.NoMotionRequest.httpUrlConnectionPost(java.lang.String, android.os.Handler):void");
    }

    public static void sendMessage(final String str, final Handler handler) {
        if (str == null || str.length() <= 0) {
            return;
        }
        new Thread(new Runnable() { // from class: com.baidu.idl.face.platform.network.NoMotionRequest.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                NoMotionRequest.httpUrlConnectionPost(str, handler);
            }
        }).start();
    }
}
