package com.unicom.xiaowo.account.shield.d;

import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.secure.android.common.ssl.SSLUtil;
import com.igexin.sdk.PushConsts;
import com.lzy.okgo.model.HttpHeaders;
import com.unicom.xiaowo.account.shield.UniAccountHelper;
import com.unicom.xiaowo.account.shield.e.d;
import com.unicom.xiaowo.account.shield.e.f;
import com.unicom.xiaowo.account.shield.e.g;
import com.unicom.xiaowo.account.shield.e.i;
import io.dcloud.common.adapter.util.DeviceInfo;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    private String a(InputStream inputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Exception unused) {
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            byteArrayOutputStream = null;
            th = th2;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            String str = new String(byteArrayOutputStream.toByteArray());
            try {
                byteArrayOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception unused2) {
            }
            return str;
        } catch (Exception unused3) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused4) {
                    return null;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused5) {
                    throw th;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public String a(String str, HashMap<String, String> map, Network network) {
        String str2;
        f.a("url:" + str);
        if (Build.VERSION.SDK_INT < 21) {
            return c(str, map, network);
        }
        try {
            str2 = DeviceInfo.HTTPS_PROTOCOL + new URL(str).getHost();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            str2 = "";
        }
        g.c(str);
        try {
            URL url = new URL(str);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) ((network == null || Build.VERSION.SDK_INT < 21) ? url.openConnection() : network.openConnection(url));
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(false);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setInstanceFollowRedirects(false);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.unicom.xiaowo.account.shield.d.b.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str3, SSLSession sSLSession) {
                    f.a("HostnameVerifier:" + str3);
                    if (TextUtils.isEmpty(str3)) {
                        return false;
                    }
                    return str3.equals("opencloud.wostore.cn") || str3.contains("10010.com");
                }
            });
            if (map != null) {
                for (String str3 : map.keySet()) {
                    httpsURLConnection.setRequestProperty(str3, map.get(str3));
                }
            }
            httpsURLConnection.addRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, str.startsWith("https://opencloud.wostore.cn/openapi/netauth/precheck/wp?") ? "Keep-Alive" : "close");
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200) {
                String strA = a(httpsURLConnection.getInputStream());
                if (!TextUtils.isEmpty(strA)) {
                    return strA;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", 10012);
                jSONObject.put("msg", "10012");
                jSONObject.put("data", str2);
                return jSONObject.toString();
            }
            if (httpsURLConnection.getResponseCode() == 302) {
                String headerField = httpsURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION);
                if (!TextUtils.isEmpty(headerField)) {
                    return headerField.startsWith("https") ? a(headerField, null, network) : b(headerField, null, network);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("code", PushConsts.GET_DEVICETOKEN);
                jSONObject2.put("msg", "无跳转地址");
                jSONObject2.put("data", str2);
                return jSONObject2.toString();
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("code", 10010);
            jSONObject3.put("msg", "https状态码" + httpsURLConnection.getResponseCode());
            jSONObject3.put("data", str2);
            return jSONObject3.toString();
        } catch (Exception e3) {
            e3.printStackTrace();
            try {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("code", 10011);
                jSONObject4.put("msg", "https异常" + e3.getMessage());
                jSONObject4.put("data", str2);
                return jSONObject4.toString();
            } catch (Exception unused) {
                return null;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x01b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String b(java.lang.String r10, java.util.HashMap<java.lang.String, java.lang.String> r11, android.net.Network r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.xiaowo.account.shield.d.b.b(java.lang.String, java.util.HashMap, android.net.Network):java.lang.String");
    }

    public String c(String str, HashMap<String, String> map, Network network) {
        String str2;
        f.a("url:" + str);
        if (Build.VERSION.SDK_INT < 21) {
            try {
                if (i.a(UniAccountHelper.getInstance().getApplicationContext()) == 1) {
                    new d().a(UniAccountHelper.getInstance().getApplicationContext(), str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            str2 = DeviceInfo.HTTPS_PROTOCOL + new URL(str).getHost();
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            str2 = "";
        }
        g.c(str);
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLUtil.d);
            sSLContext.init(null, null, new SecureRandom());
            c cVar = new c(sSLContext.getSocketFactory());
            URL url = new URL(str);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) ((network == null || Build.VERSION.SDK_INT < 21) ? url.openConnection() : network.openConnection(url));
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(false);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setInstanceFollowRedirects(false);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setSSLSocketFactory(cVar);
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.unicom.xiaowo.account.shield.d.b.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str3, SSLSession sSLSession) {
                    f.a("HostnameVerifier:" + str3);
                    if (TextUtils.isEmpty(str3)) {
                        return false;
                    }
                    return str3.equals("opencloud.wostore.cn") || str3.contains("10010.com");
                }
            });
            if (map != null) {
                for (String str3 : map.keySet()) {
                    httpsURLConnection.setRequestProperty(str3, map.get(str3));
                }
            }
            httpsURLConnection.addRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, str.startsWith("https://opencloud.wostore.cn/openapi/netauth/precheck/wp?") ? "Keep-Alive" : "close");
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200) {
                String strA = a(httpsURLConnection.getInputStream());
                if (!TextUtils.isEmpty(strA)) {
                    return strA;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", 10012);
                jSONObject.put("msg", "10012");
                jSONObject.put("data", str2);
                return jSONObject.toString();
            }
            if (httpsURLConnection.getResponseCode() == 302) {
                String headerField = httpsURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION);
                if (!TextUtils.isEmpty(headerField)) {
                    return headerField.startsWith("https") ? a(headerField, null, network) : b(headerField, null, network);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("code", PushConsts.GET_DEVICETOKEN);
                jSONObject2.put("msg", "无跳转地址");
                jSONObject2.put("data", str2);
                return jSONObject2.toString();
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("code", 10010);
            jSONObject3.put("msg", "https状态码" + httpsURLConnection.getResponseCode());
            jSONObject3.put("data", str2);
            return jSONObject3.toString();
        } catch (Exception e4) {
            e4.printStackTrace();
            try {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("code", 10011);
                jSONObject4.put("msg", "https异常" + e4.getMessage());
                jSONObject4.put("data", str2);
                return jSONObject4.toString();
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
