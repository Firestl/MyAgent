package com.newcapec.virtualcard.net;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.efs.sdk.base.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import supwisdom.d0;

/* JADX INFO: loaded from: classes2.dex */
public class HttpUtil {
    public static final String TAG = "HttpUtil";

    public static class TrustAllManager implements X509TrustManager {
        public TrustAllManager() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    static {
        try {
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: com.newcapec.virtualcard.net.HttpUtil.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static HttpURLConnection getConnection(String str, String str2) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        return httpURLConnection;
    }

    public static String getKeyWords(String str, String str2) {
        int iIndexOf;
        if (TextUtils.isEmpty(str) || -1 == (iIndexOf = str.indexOf(str2))) {
            return "";
        }
        int iIndexOf2 = str.indexOf("&", iIndexOf);
        int length = str2.length() + iIndexOf;
        String strSubstring = -1 == iIndexOf2 ? str.substring(length) : str.substring(length, iIndexOf2);
        return TextUtils.isEmpty(strSubstring) ? "" : strSubstring;
    }

    public static String operateErrorRes(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("success", (Object) false);
            jSONObject.put("code", (Object) str);
            jSONObject.put("message", (Object) str2);
            return jSONObject.toJSONString();
        } catch (Exception e2) {
            Log.d(TAG, "operateErrorRes: " + e2.getMessage());
            return "";
        }
    }

    public static String sendGet(String str) {
        String strOperateErrorRes;
        StringBuilder sb;
        try {
            HttpURLConnection connection = getConnection(str, "GET");
            connection.setDoOutput(false);
            connection.connect();
            int responseCode = connection.getResponseCode();
            String str2 = "";
            if (200 != responseCode) {
                if (responseCode == 503) {
                    strOperateErrorRes = operateErrorRes(responseCode + "", "请求超时，参考码:" + responseCode);
                    sb = new StringBuilder();
                    sb.append("---sendPost---");
                } else {
                    strOperateErrorRes = operateErrorRes(responseCode + "", "请求失败，参考码:" + responseCode);
                    sb = new StringBuilder();
                    sb.append("---sendPost---");
                }
                sb.append(responseCode);
                d0.c(sb.toString());
            } else {
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = (connection.getContentEncoding() == null || !connection.getContentEncoding().contains(Constants.CP_GZIP)) ? new InputStreamReader(inputStream, "UTF-8") : new InputStreamReader(new GZIPInputStream(inputStream), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    str2 = str2 + line;
                }
                bufferedReader.close();
                inputStreamReader.close();
                strOperateErrorRes = str2;
            }
            connection.disconnect();
            return strOperateErrorRes;
        } catch (ConnectException | UnknownHostException unused) {
            return operateErrorRes("0", "请求超时，参考码:0");
        }
    }

    public static NetResponse sendPost(String str, Map<String, String> map, String str2) {
        d0.c("----sendPost----url----" + str);
        d0.c("----sendPost----params----" + str2);
        NetResponse netResponse = new NetResponse(false, -1, "网络异常，请稍候重试", null);
        byte[] bytes = str2.getBytes();
        HttpURLConnection connection = getConnection(str, "POST");
        if (map != null && !map.isEmpty()) {
            for (String str3 : map.keySet()) {
                connection.setRequestProperty(str3, map.get(str3));
            }
        }
        try {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            int responseCode = connection.getResponseCode();
            d0.b(TAG, "---sendPost---" + str + "，code: " + responseCode);
            if (responseCode != 200) {
                netResponse = responseCode == 503 ? new NetResponse(false, responseCode, String.format("%s(%s)", "请求超时", Integer.valueOf(responseCode)), null) : new NetResponse(false, responseCode, String.format("%s(%s)", "请求失败", Integer.valueOf(responseCode)), null);
            } else {
                InputStream inputStream = connection.getInputStream();
                if (inputStream != null) {
                    byte[] bArrStreamToByteArray = streamToByteArray(inputStream);
                    inputStream.close();
                    NetResponse netResponse2 = new NetResponse(true, 200, "", new String(bArrStreamToByteArray));
                    d0.c("-------sendPost------response---" + new String(bArrStreamToByteArray));
                    netResponse = netResponse2;
                }
            }
            outputStream.close();
            connection.disconnect();
            return netResponse;
        } catch (ConnectException unused) {
            return new NetResponse(false, 0, "无法连接服务器，请稍后再试", null);
        } catch (NoRouteToHostException unused2) {
            return new NetResponse(false, -3, "无法连接服务器(NoRouteToHost), 请稍后再试", null);
        } catch (SocketTimeoutException unused3) {
            return new NetResponse(false, -1, "无法连接服务器(Timeout), 请检查网络连接", null);
        } catch (UnknownHostException unused4) {
            return new NetResponse(false, -2, "无法连接服务器(UnknownHost), 请检查网络连接", null);
        }
    }

    public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static void trustAllHttpsCertificates() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustManagerArr = {new TrustAllManager()};
        SSLContext sSLContext = SSLContext.getInstance("SSL");
        sSLContext.init(null, trustManagerArr, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
    }
}
