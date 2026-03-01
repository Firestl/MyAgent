package com.igexin.push.g;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.loopj.android.http.RequestParams;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3606a = "com.igexin.push.g.r";
    public static final String b = "utf-8";
    public static final String c = "POST";
    public static final String d = "GET";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3607e = "GETUI";
    public static final int f = 30000;

    public static String a(InputStream inputStream, String str) throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[256];
            while (true) {
                int i = bufferedReader.read(cArr);
                if (i <= 0) {
                    break;
                }
                stringWriter.write(cArr, 0, i);
            }
            return stringWriter.toString();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] strArrSplit = str.split(";");
            int length = strArrSplit.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String strTrim = strArrSplit[i].trim();
                if (strTrim.startsWith("charset")) {
                    String[] strArrSplit2 = strTrim.split(ContainerUtils.KEY_VALUE_DELIMITER, 2);
                    if (strArrSplit2.length == 2 && !TextUtils.isEmpty(strArrSplit2[1])) {
                        return strArrSplit2[1].trim();
                    }
                } else {
                    i++;
                }
            }
        }
        return "utf-8";
    }

    public static String a(Map<String, String> map, String str) throws Exception {
        if (map == null || map.isEmpty()) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            str = "utf-8";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                if (z) {
                    sb.append("&");
                } else {
                    z = true;
                }
                sb.append(key);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(URLEncoder.encode(value, str));
            }
        }
        return sb.toString();
    }

    public static HttpURLConnection a(URL url, String str, String str2) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(str);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setRequestProperty("User-Agent", f3607e);
        httpURLConnection.setRequestProperty("Content-Type", str2);
        httpURLConnection.setRequestProperty("HOST", url.getHost() + Constants.COLON_SEPARATOR + url.getPort());
        return httpURLConnection;
    }

    public static URL a(String str, String str2) throws Exception {
        String str3;
        StringBuilder sb;
        StringBuilder sb2;
        String string;
        URL url = new URL(str);
        if (TextUtils.isEmpty(str2)) {
            return url;
        }
        if (TextUtils.isEmpty(url.getQuery())) {
            str3 = Operators.CONDITION_IF_STRING;
            if (str.endsWith(Operators.CONDITION_IF_STRING)) {
                sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(str2);
                string = sb2.toString();
            } else {
                sb = new StringBuilder();
                sb.append(str);
                sb.append(str3);
                sb.append(str2);
                string = sb.toString();
            }
        } else {
            str3 = "&";
            if (str.endsWith("&")) {
                sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(str2);
                string = sb2.toString();
            } else {
                sb = new StringBuilder();
                sb.append(str);
                sb.append(str3);
                sb.append(str2);
                string = sb.toString();
            }
        }
        return new URL(string);
    }

    public static URL a(String str, Map<String, String> map, String str2) throws Exception {
        return a(str, a(map, str2));
    }

    public static byte[] a(InputStream inputStream) throws Throwable {
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
        } catch (Exception e2) {
            e = e2;
            bufferedInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream(1024);
                bArr = new byte[1024];
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
            }
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e3) {
                        com.igexin.c.a.c.a.a(e3);
                    }
                }
                throw th;
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                bufferedInputStream.close();
            } catch (IOException e4) {
                com.igexin.c.a.c.a.a(e4);
            }
            return byteArray;
        } catch (Exception e5) {
            e = e5;
            com.igexin.c.a.c.a.a(e);
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e6) {
                    com.igexin.c.a.c.a.a(e6);
                }
            }
            return null;
        }
    }

    public static byte[] a(String str, String str2, String str3) throws IOException {
        return ("Content-Disposition:form-data;name=\"" + str + "\"\r\nContent-Type:text/plain\r\n\r\n" + str2).getBytes(str3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0040  */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(java.lang.String r2, java.lang.String r3, byte[] r4, int r5, int r6) throws java.lang.Exception {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L32
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L32
            java.lang.String r2 = "POST"
            java.net.HttpURLConnection r2 = a(r1, r2, r3)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L32
            r2.setConnectTimeout(r5)     // Catch: java.io.IOException -> L2d java.lang.Throwable -> L38
            r2.setReadTimeout(r6)     // Catch: java.io.IOException -> L2d java.lang.Throwable -> L38
            java.io.OutputStream r0 = r2.getOutputStream()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L38
            r0.write(r4)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L38
            byte[] r3 = a(r2)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L38
            if (r0 == 0) goto L22
            r0.close()
        L22:
            if (r2 == 0) goto L27
            r2.disconnect()
        L27:
            return r3
        L28:
            r3 = move-exception
            com.igexin.c.a.c.a.a(r3)     // Catch: java.lang.Throwable -> L38
            throw r3     // Catch: java.lang.Throwable -> L38
        L2d:
            r3 = move-exception
            goto L34
        L2f:
            r3 = move-exception
            r2 = r0
            goto L39
        L32:
            r3 = move-exception
            r2 = r0
        L34:
            com.igexin.c.a.c.a.a(r3)     // Catch: java.lang.Throwable -> L38
            throw r3     // Catch: java.lang.Throwable -> L38
        L38:
            r3 = move-exception
        L39:
            if (r0 == 0) goto L3e
            r0.close()
        L3e:
            if (r2 == 0) goto L43
            r2.disconnect()
        L43:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.g.r.a(java.lang.String, java.lang.String, byte[], int, int):byte[]");
    }

    public static byte[] a(String str, Map<String, String> map, int i, int i2) throws Exception {
        return a(str, map, "utf-8", i, i2);
    }

    public static byte[] a(String str, Map<String, String> map, int i, int i2, String str2) throws Exception {
        HttpURLConnection httpURLConnectionB = b(str, map, i, i2, str2);
        try {
            try {
                return a(httpURLConnectionB);
            } catch (Exception e2) {
                throw e2;
            }
        } finally {
            if (httpURLConnectionB != null) {
                httpURLConnectionB.disconnect();
            }
        }
    }

    public static byte[] a(String str, Map<String, String> map, String str2, int i, int i2) throws Exception {
        String strConcat = "application/x-www-form-urlencoded;charset=".concat(String.valueOf(str2));
        String strA = a(map, str2);
        byte[] bytes = new byte[0];
        if (strA != null) {
            bytes = strA.getBytes(str2);
        }
        return a(str, strConcat, bytes, i, i2);
    }

    public static byte[] a(String str, Map<String, String> map, Map<String, i> map2, int i, int i2) throws Exception {
        return (map2 == null || map2.isEmpty()) ? a(str, map, "utf-8", i, i2) : a(str, map, map2, "utf-8", i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015b A[Catch: all -> 0x01f1, Exception -> 0x01f3, TryCatch #10 {Exception -> 0x01f3, all -> 0x01f1, blocks: (B:61:0x0152, B:74:0x0178, B:76:0x017f, B:79:0x019b, B:81:0x01bf, B:64:0x015b, B:67:0x0164, B:70:0x016d, B:33:0x0101, B:35:0x0107, B:38:0x010d, B:40:0x0113, B:42:0x0119, B:45:0x0122, B:47:0x0129, B:49:0x012e, B:51:0x0136, B:54:0x013e, B:56:0x0145, B:83:0x01c8), top: B:125:0x0152 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(java.lang.String r19, java.util.Map<java.lang.String, java.lang.String> r20, java.util.Map<java.lang.String, com.igexin.push.g.i> r21, java.lang.String r22, int r23, int r24) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 548
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.g.r.a(java.lang.String, java.util.Map, java.util.Map, java.lang.String, int, int):byte[]");
    }

    public static byte[] a(String str, byte[] bArr) throws Exception {
        return a(str, RequestParams.APPLICATION_OCTET_STREAM, bArr, 10000, 10000);
    }

    public static byte[] a(HttpURLConnection httpURLConnection) throws Exception {
        return httpURLConnection.getErrorStream() == null ? a(httpURLConnection.getInputStream()) : b(httpURLConnection).getBytes();
    }

    public static String b(HttpURLConnection httpURLConnection) throws Exception {
        String strA = a(httpURLConnection.getErrorStream(), a(httpURLConnection.getContentType()));
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        throw new IOException(httpURLConnection.getResponseCode() + Constants.COLON_SEPARATOR + httpURLConnection.getResponseMessage());
    }

    public static HttpURLConnection b(String str, Map<String, String> map, int i, int i2, String str2) throws Exception {
        HttpURLConnection httpURLConnectionA = a(a(str, a(map, str2)), "GET", "application/x-www-form-urlencoded;charset=".concat(String.valueOf(str2)));
        httpURLConnectionA.setConnectTimeout(i);
        httpURLConnectionA.setReadTimeout(i2);
        return httpURLConnectionA;
    }

    public static byte[] b(String str, String str2, String str3) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Disposition:form-data;name=\"");
        sb.append("dp_data");
        sb.append("\";filename=\"");
        if (TextUtils.isEmpty(str)) {
            str = AbsoluteConst.JSON_KEY_FILENAME;
        }
        sb.append(str);
        sb.append("\"\r\nContent-Type:");
        sb.append(str2);
        sb.append("\r\n\r\n");
        return sb.toString().getBytes(str3);
    }

    public static byte[] b(String str, Map<String, String> map, int i, int i2) throws Exception {
        return a(str, map, i, i2, "utf-8");
    }

    public static byte[] c(String str, Map<String, String> map, int i, int i2) throws Exception {
        HttpURLConnection httpURLConnectionB = b(str, map, i, i2, "utf-8");
        try {
            try {
                return a(httpURLConnectionB);
            } catch (Exception e2) {
                throw e2;
            }
        } finally {
            if (httpURLConnectionB != null) {
                httpURLConnectionB.disconnect();
            }
        }
    }
}
