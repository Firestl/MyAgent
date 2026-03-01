package io.dcloud.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.StringConst;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public class NetTool {
    public static final int DEFAULT_TIME_OUT_TIMES = 5000;
    public static String TAG = "NetTool";
    public static HostnameVerifier sCustomeHostnameVerifier;

    public static HttpURLConnection createConnection(URL url, String str, int i, boolean z, boolean z2) {
        try {
            if (BaseInfo.isUniStatistics) {
                z2 = false;
            }
            HttpURLConnection httpURLConnection = (!z2 || DHFile.hasFile()) ? (HttpURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            if ((httpURLConnection instanceof HttpsURLConnection) && z) {
                try {
                    SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
                    if (sSLSocketFactory != null) {
                        ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLSocketFactory);
                    }
                    ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(getDefaultHostnameVerifier());
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setReadTimeout(i);
            httpURLConnection.setRequestMethod(str);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static HostnameVerifier getDefaultHostnameVerifier() {
        HostnameVerifier hostnameVerifier = sCustomeHostnameVerifier;
        return hostnameVerifier != null ? hostnameVerifier : DCloudTrustManager.getHostnameVerifier(true);
    }

    public static byte[] httpGet(String str, HashMap<String, String> map) throws Exception {
        return request(str, null, map, "GET", 5000, true);
    }

    public static byte[] httpGetThrows(String str) throws Exception {
        return httpGet(str, (HashMap<String, String>) null);
    }

    public static byte[] httpPost(String str, String str2, HashMap<String, String> map) {
        return request(str, str2, map, "POST", 5000, true);
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
            for (int i = 0; i < allNetworkInfo.length; i++) {
                if (allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED || allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTING) {
                    return true;
                }
            }
        }
        return false;
    }

    public static byte[] read(InputStream inputStream) {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            int i = 10240;
            int iMin = Math.min(10240, inputStream.available());
            if (iMin > 0) {
                i = iMin;
            }
            bArr = new byte[i];
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i2);
            return byteArrayOutputStream.toByteArray();
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] request(String str, String str2, HashMap<String, String> map, String str3, int i, boolean z) {
        return request(str, str2, map, str3, i, z, false, null);
    }

    public static void write(OutputStream outputStream, String str) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    outputStream.write(str.getBytes("UTF-8"));
                }
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] httpGet(String str, HashMap<String, String> map, boolean z) throws Exception {
        return request(str, null, map, "GET", 5000, z);
    }

    public static byte[] httpGetThrows(String str, boolean z) throws Exception {
        return httpGet(str, (HashMap<String, String>) null, z);
    }

    public static byte[] httpPost(String str, String str2, HashMap<String, String> map, boolean z) {
        return request(str, str2, map, "POST", 5000, z);
    }

    public static byte[] request(String str, String str2, HashMap<String, String> map, String str3, int i, boolean z, boolean z2) {
        return request(str, str2, map, str3, i, z, z2, null);
    }

    public static byte[] httpGet(String str, HashMap<String, String> map, int i) throws Exception {
        return request(str, null, map, "GET", i, true);
    }

    public static byte[] httpPost(String str, String str2, HashMap<String, String> map, boolean z, boolean z2) {
        return request(str, str2, map, "POST", 5000, z, z2);
    }

    public static byte[] request(String str, String str2, HashMap<String, String> map, String str3, int i, boolean z, boolean z2, String[] strArr) {
        if (str != null && str.length() != 0) {
            try {
                try {
                    HttpURLConnection httpURLConnectionCreateConnection = createConnection(new URL(str), str3, i, z, z2);
                    if (map != null && !map.isEmpty()) {
                        for (String str4 : map.keySet()) {
                            httpURLConnectionCreateConnection.setRequestProperty(str4, map.get(str4));
                        }
                    }
                    if (!TextUtils.isEmpty(str3) && TextUtils.equals(str3.toLowerCase(), "post")) {
                        try {
                            write(httpURLConnectionCreateConnection.getOutputStream(), str2);
                        } catch (Exception e2) {
                            e = e2;
                            if (((e instanceof SocketTimeoutException) || (e instanceof UnknownHostException)) && StringConst.canChangeHost(str)) {
                                return request(StringConst.changeHost(str), str2, map, str3, i, z, z2, strArr);
                            }
                            Logger.p(TAG, "httpPost exception, e = " + e.getMessage());
                            if (strArr != null) {
                                strArr[0] = e.getMessage();
                            }
                            return null;
                        }
                    }
                    int responseCode = httpURLConnectionCreateConnection.getResponseCode();
                    if (responseCode != 200) {
                        Logger.p(TAG, "httpGet fail, status code = " + responseCode);
                        if (strArr != null) {
                            strArr[0] = String.valueOf(responseCode);
                        }
                        return null;
                    }
                    return read(httpURLConnectionCreateConnection.getInputStream());
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } else {
            Logger.p(TAG, "httpPost, url is null");
            return null;
        }
    }

    public static byte[] httpGet(String str, HashMap<String, String> map, int i, boolean z) throws Exception {
        return request(str, null, map, "GET", i, z);
    }

    public static byte[] httpPost(String str, String str2, HashMap<String, String> map, boolean z, String[] strArr) {
        return request(str, str2, map, "POST", 5000, z, false, strArr);
    }

    public static byte[] httpGet(String str) {
        return httpGet(str, true);
    }

    public static byte[] httpPost(String str, String str2, HashMap<String, String> map, boolean z, boolean z2, String[] strArr) {
        return request(str, str2, map, "POST", 5000, z, z2, strArr);
    }

    public static byte[] httpGet(String str, boolean z) {
        try {
            return httpGet(str, (HashMap<String, String>) null, z);
        } catch (Exception e2) {
            if (((e2 instanceof SocketTimeoutException) || (e2 instanceof UnknownHostException)) && StringConst.canChangeHost(str)) {
                return httpGet(StringConst.changeHost(str));
            }
            return null;
        }
    }

    public static byte[] httpPost(String str, String str2, HashMap<String, String> map, int i) {
        return request(str, str2, map, "POST", i, true);
    }

    public static byte[] httpPost(String str, String str2, HashMap<String, String> map, int i, boolean z) {
        return request(str, str2, map, "POST", i, z);
    }

    public static byte[] httpGet(String str, int i) {
        return httpGet(str, i, true);
    }

    public static byte[] httpGet(String str, int i, boolean z) {
        try {
            return httpGet(str, null, i, z);
        } catch (Exception e2) {
            if (((e2 instanceof SocketTimeoutException) || (e2 instanceof UnknownHostException)) && StringConst.canChangeHost(str)) {
                return httpGet(StringConst.changeHost(str), i);
            }
            return null;
        }
    }
}
