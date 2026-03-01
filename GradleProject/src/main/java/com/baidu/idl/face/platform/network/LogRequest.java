package com.baidu.idl.face.platform.network;

/* JADX INFO: loaded from: classes.dex */
public class LogRequest extends BaseRequest {
    public static final String URL_GET_LOG = "http://face.baidu.com/openapi/v2/stat/sdkdata";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x012b A[Catch: IOException -> 0x0184, TRY_ENTER, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0130 A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0135 A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013a A[Catch: IOException -> 0x0184, TRY_LEAVE, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0148 A[Catch: IOException -> 0x0184, TRY_ENTER, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x014d A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0152 A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0157 A[Catch: IOException -> 0x0184, TRY_LEAVE, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0164 A[Catch: IOException -> 0x0184, TRY_ENTER, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0169 A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x016e A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0173 A[Catch: IOException -> 0x0184, TRY_LEAVE, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0180 A[Catch: IOException -> 0x0184, TRY_ENTER, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0188 A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x018d A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0192 A[Catch: IOException -> 0x0184, TRY_LEAVE, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01a5 A[Catch: IOException -> 0x01a1, TryCatch #23 {IOException -> 0x01a1, blocks: (B:153:0x019d, B:157:0x01a5, B:159:0x01aa, B:161:0x01af), top: B:166:0x019d }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01aa A[Catch: IOException -> 0x01a1, TryCatch #23 {IOException -> 0x01a1, blocks: (B:153:0x019d, B:157:0x01a5, B:159:0x01aa, B:161:0x01af), top: B:166:0x019d }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01af A[Catch: IOException -> 0x01a1, TRY_LEAVE, TryCatch #23 {IOException -> 0x01a1, blocks: (B:153:0x019d, B:157:0x01a5, B:159:0x01aa, B:161:0x01af), top: B:166:0x019d }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x019d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010e A[Catch: IOException -> 0x0184, TRY_ENTER, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0113 A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0118 A[Catch: IOException -> 0x0184, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x011d A[Catch: IOException -> 0x0184, TRY_LEAVE, TryCatch #7 {IOException -> 0x0184, blocks: (B:41:0x00b4, B:43:0x00b9, B:45:0x00be, B:47:0x00c3, B:87:0x010e, B:89:0x0113, B:91:0x0118, B:93:0x011d, B:100:0x012b, B:102:0x0130, B:104:0x0135, B:106:0x013a, B:113:0x0148, B:115:0x014d, B:117:0x0152, B:119:0x0157, B:126:0x0164, B:128:0x0169, B:130:0x016e, B:132:0x0173, B:139:0x0180, B:143:0x0188, B:145:0x018d, B:147:0x0192), top: B:165:0x000a }] */
    /* JADX WARN: Type inference failed for: r3v15, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.io.ByteArrayOutputStream] */
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
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /* JADX WARN: Type inference failed for: r7v35 */
    /* JADX WARN: Type inference failed for: r7v36 */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v18, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v19, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v20, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v25, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v36 */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v39 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v40 */
    /* JADX WARN: Type inference failed for: r9v41 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void httpUrlConnectionPost(java.lang.String r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.idl.face.platform.network.LogRequest.httpUrlConnectionPost(java.lang.String):void");
    }

    public static void sendLogMessage(final String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        new Thread(new Runnable() { // from class: com.baidu.idl.face.platform.network.LogRequest.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                LogRequest.httpUrlConnectionPost(str);
            }
        }).start();
    }
}
