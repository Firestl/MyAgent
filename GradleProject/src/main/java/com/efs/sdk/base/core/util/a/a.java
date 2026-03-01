package com.efs.sdk.base.core.util.a;

import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.http.HttpResponse;
import com.efs.sdk.base.http.IHttpUtil;
import com.lzy.okgo.model.HttpHeaders;
import io.dcloud.common.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a implements IHttpUtil {

    /* JADX INFO: renamed from: com.efs.sdk.base.core.util.a.a$a, reason: collision with other inner class name */
    public static class C0021a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1856a = new a(0);
    }

    public /* synthetic */ a(byte b) {
        this();
    }

    public static a a() {
        return C0021a.f1856a;
    }

    public static void b(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            try {
                com.efs.sdk.base.core.util.b.a(httpURLConnection.getInputStream());
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.efs.sdk.base.http.IHttpUtil
    public final HttpResponse get(String str, Map<String, String> map) {
        HttpURLConnection httpURLConnectionA;
        HttpResponse httpResponse = new HttpResponse();
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            httpURLConnectionA = null;
            try {
                try {
                    httpURLConnectionA = a(str, map);
                    httpURLConnectionA.setRequestMethod("GET");
                    httpURLConnectionA.setInstanceFollowRedirects(true);
                    httpURLConnectionA.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "close");
                    httpURLConnectionA.connect();
                    httpResponse = a(httpURLConnectionA);
                    b(httpURLConnectionA);
                    break;
                } catch (SocketTimeoutException e2) {
                    httpResponse.setHttpCode(-3);
                    Log.e("efs.util.http", "post file '" + str + "' error", e2);
                } catch (UnknownHostException e3) {
                    httpResponse.setHttpCode(-2);
                    Log.e("efs.util.http", "get request '" + str + "' error， maybe network is disconnect", e3);
                }
            } finally {
                try {
                } catch (Throwable th) {
                    b(httpURLConnectionA);
                }
            }
            i++;
            b(httpURLConnectionA);
        }
        httpResponse.setReqUrl(str);
        return httpResponse;
    }

    @Override // com.efs.sdk.base.http.IHttpUtil
    public final HttpResponse post(String str, Map<String, String> map, File file) {
        return a(str, map, file, null);
    }

    @Override // com.efs.sdk.base.http.IHttpUtil
    public final HttpResponse postAsFile(String str, Map<String, String> map, byte[] bArr) {
        return a(str, map, null, bArr);
    }

    public a() {
    }

    public static HttpURLConnection a(String str, Map<String, String> map) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        if (map == null) {
            map = Collections.emptyMap();
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        return httpURLConnection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.util.Map, java.util.Map<java.lang.String, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.net.HttpURLConnection] */
    @Override // com.efs.sdk.base.http.IHttpUtil
    public final HttpResponse post(String str, Map<String, String> map, byte[] bArr) {
        HttpResponse httpResponse = new HttpResponse();
        OutputStream outputStream = null;
        try {
            try {
                map = a(str, map);
            } catch (SocketTimeoutException e2) {
                e = e2;
                map = 0;
            } catch (UnknownHostException e3) {
                e = e3;
                map = 0;
            } catch (Throwable th) {
                th = th;
                map = 0;
            }
            try {
                map.setRequestMethod("POST");
                map.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "close");
                outputStream = map.getOutputStream();
                outputStream.write(bArr);
                httpResponse = a(map);
            } catch (SocketTimeoutException e4) {
                e = e4;
                httpResponse.setHttpCode(-3);
                Log.e("efs.util.http", "post file '" + str + "' error", e);
                map = map;
            } catch (UnknownHostException e5) {
                e = e5;
                httpResponse.setHttpCode(-2);
                Log.e("efs.util.http", "post data to '" + str + "' error， maybe network is disconnect", e);
                map = map;
            } catch (Throwable th2) {
                th = th2;
                Log.e("efs.util.http", "post data '" + str + "' error", th);
                map = map;
            }
            httpResponse.setReqUrl(str);
            return httpResponse;
        } finally {
            com.efs.sdk.base.core.util.b.a(outputStream);
            b(map);
        }
    }

    public static HttpResponse a(HttpURLConnection httpURLConnection) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        InputStream inputStream;
        byte[] bArr;
        HttpResponse httpResponse = new HttpResponse();
        if (httpURLConnection == null) {
            return httpResponse;
        }
        try {
            httpResponse.setHttpCode(httpURLConnection.getResponseCode());
            inputStream = httpURLConnection.getInputStream();
            try {
                bArr = new byte[1024];
                byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
            } catch (Throwable th2) {
                byteArrayOutputStream = null;
                th = th2;
            }
        } catch (Throwable th3) {
            byteArrayOutputStream = null;
            th = th3;
            inputStream = null;
        }
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } catch (Throwable th4) {
                th = th4;
                try {
                    Log.e("efs.util.http", "get response error", th);
                } finally {
                    com.efs.sdk.base.core.util.b.a(inputStream);
                    com.efs.sdk.base.core.util.b.a(byteArrayOutputStream);
                }
            }
            return httpResponse;
        }
        httpResponse.data = byteArrayOutputStream.toString();
        return httpResponse;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r13v7, types: [java.io.Closeable] */
    public static HttpResponse a(String str, Map<String, String> map, File file, byte[] bArr) {
        FileInputStream fileInputStream;
        OutputStream outputStream;
        DataOutputStream dataOutputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        FileInputStream fileInputStream4;
        HttpResponse httpResponse = new HttpResponse();
        HttpURLConnection httpURLConnection = null;
        fileInputStream = null;
        fileInputStream = null;
        fileInputStream = null;
        FileInputStream fileInputStream5 = null;
        httpURLConnection = null;
        httpURLConnection = null;
        httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnectionA = a(str, map);
                try {
                    httpURLConnectionA.setRequestMethod("POST");
                    httpURLConnectionA.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "close");
                    httpURLConnectionA.setRequestProperty("Content-Type", "multipart/form-data;boundary=----WebKitFormBoundaryP0Rfzlf32iRoMhmb");
                    outputStream = httpURLConnectionA.getOutputStream();
                    try {
                        dataOutputStream = new DataOutputStream(outputStream);
                        try {
                            dataOutputStream.writeBytes("------WebKitFormBoundaryP0Rfzlf32iRoMhmb\r\n");
                            if (bArr == 0) {
                                if (file != null && file.exists()) {
                                    dataOutputStream.writeBytes("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
                                    dataOutputStream.writeBytes(Base64.CRLF);
                                    fileInputStream4 = new FileInputStream(file);
                                    try {
                                        byte[] bArr2 = new byte[4096];
                                        while (true) {
                                            int i = fileInputStream4.read(bArr2);
                                            if (i == -1) {
                                                break;
                                            }
                                            dataOutputStream.write(bArr2, 0, i);
                                        }
                                        fileInputStream5 = fileInputStream4;
                                    } catch (SocketTimeoutException e2) {
                                        e = e2;
                                        httpURLConnection = httpURLConnectionA;
                                        fileInputStream3 = fileInputStream4;
                                        httpResponse.setHttpCode(-3);
                                        Log.e("efs.util.http", "post file '" + str + "' error", e);
                                        bArr = fileInputStream3;
                                    } catch (UnknownHostException e3) {
                                        e = e3;
                                        httpURLConnection = httpURLConnectionA;
                                        fileInputStream2 = fileInputStream4;
                                        httpResponse.setHttpCode(-2);
                                        Log.e("efs.util.http", "post file '" + str + "' error， maybe network is disconnect", e);
                                        bArr = fileInputStream2;
                                    } catch (Throwable th) {
                                        th = th;
                                        httpURLConnection = httpURLConnectionA;
                                        fileInputStream = fileInputStream4;
                                        Log.e("efs.util.http", "post file '" + str + "' error", th);
                                        bArr = fileInputStream;
                                    }
                                }
                                b(httpURLConnectionA);
                                com.efs.sdk.base.core.util.b.a(outputStream);
                                com.efs.sdk.base.core.util.b.a(dataOutputStream);
                                com.efs.sdk.base.core.util.b.a((Closeable) null);
                                return httpResponse;
                            }
                            dataOutputStream.writeBytes("Content-Disposition: form-data;name=\"file\";filename=\"f\"\r\n");
                            dataOutputStream.writeBytes(Base64.CRLF);
                            dataOutputStream.write(bArr, 0, bArr.length);
                            dataOutputStream.writeBytes(Base64.CRLF);
                            dataOutputStream.writeBytes("------WebKitFormBoundaryP0Rfzlf32iRoMhmb--\r\n");
                            httpResponse = a(httpURLConnectionA);
                            b(httpURLConnectionA);
                            com.efs.sdk.base.core.util.b.a(outputStream);
                            com.efs.sdk.base.core.util.b.a(dataOutputStream);
                            com.efs.sdk.base.core.util.b.a(fileInputStream5);
                        } catch (SocketTimeoutException e4) {
                            e = e4;
                            fileInputStream4 = fileInputStream5;
                        } catch (UnknownHostException e5) {
                            e = e5;
                            fileInputStream4 = fileInputStream5;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream4 = fileInputStream5;
                        }
                    } catch (SocketTimeoutException e6) {
                        e = e6;
                        fileInputStream4 = null;
                        dataOutputStream = null;
                    } catch (UnknownHostException e7) {
                        e = e7;
                        fileInputStream4 = null;
                        dataOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream4 = null;
                        dataOutputStream = null;
                    }
                } catch (SocketTimeoutException e8) {
                    e = e8;
                    fileInputStream4 = null;
                    outputStream = null;
                    dataOutputStream = null;
                } catch (UnknownHostException e9) {
                    e = e9;
                    fileInputStream4 = null;
                    outputStream = null;
                    dataOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    fileInputStream4 = null;
                    outputStream = null;
                    dataOutputStream = null;
                }
            } catch (SocketTimeoutException e10) {
                e = e10;
                fileInputStream3 = null;
                outputStream = null;
                dataOutputStream = null;
            } catch (UnknownHostException e11) {
                e = e11;
                fileInputStream2 = null;
                outputStream = null;
                dataOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                fileInputStream = null;
                outputStream = null;
                dataOutputStream = null;
            }
            httpResponse.setReqUrl(str);
            return httpResponse;
        } finally {
            b(httpURLConnection);
            com.efs.sdk.base.core.util.b.a(outputStream);
            com.efs.sdk.base.core.util.b.a(dataOutputStream);
            com.efs.sdk.base.core.util.b.a((Closeable) bArr);
        }
    }
}
