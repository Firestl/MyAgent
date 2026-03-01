package com.zx.a.I8b7;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.umeng.analytics.pro.bm;
import com.zx.a.I8b7.c0;
import com.zx.a.I8b7.s0;
import com.zx.a.I8b7.t1;
import com.zx.a.I8b7.v1;
import com.zx.a.I8b7.y1;
import com.zx.module.annotation.Java2C;
import io.dcloud.common.adapter.ui.webview.WebLoadEvent;
import io.dcloud.common.util.Md5Utils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Locale;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static v1 f6300a;
    public static final String[] b;

    public class a implements c0 {
        @Override // com.zx.a.I8b7.c0
        public d1 a(c0.a aVar) throws IOException {
            if (!d3.a(t2.f6286a, true)) {
                throw new IllegalStateException("network is not available");
            }
            u0 u0Var = (u0) aVar;
            return u0Var.a(u0Var.c, u0Var.d);
        }
    }

    static {
        g();
        b = h();
    }

    public static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer);
        stringBuffer.append("BE");
        stringBuffer.append("GIN ");
        stringBuffer.append("CE");
        stringBuffer.append("RT");
        stringBuffer.append("IFIC");
        stringBuffer.append("ATE");
        a(stringBuffer);
        stringBuffer.append("\n");
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (!line.trim().equals("")) {
                    sb.append(line.substring(0, line.length() - 5));
                }
            }
        } catch (Exception e2) {
            y1.a(e2);
        }
        stringBuffer.append(sb.toString());
        stringBuffer.append("\n");
        a(stringBuffer);
        stringBuffer.append("EN");
        stringBuffer.append("D ");
        stringBuffer.append("CE");
        stringBuffer.append("RTI");
        stringBuffer.append("FIC");
        stringBuffer.append("ATE");
        a(stringBuffer);
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    @Java2C.Method2C
    public static native void a(HttpURLConnection httpURLConnection) throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException, KeyManagementException;

    public static HashMap<String, String> b(String str) {
        HashMap<String, String> map = new HashMap<>();
        map.put("UDID-LID", t2.a(t2.h));
        String str2 = t2.i;
        if (str2 == null) {
            str2 = "";
        }
        map.put("UDID-ZID", str2);
        try {
            String str3 = new String(Base64.encode(a().toString().getBytes(StandardCharsets.UTF_8), 2), StandardCharsets.UTF_8);
            y1.a("ZXID 请求 header 中的 appInfo: " + str3);
            map.put("UDID-APP-INFO", str3);
            String str4 = new String(Base64.encode(e().toString().getBytes(StandardCharsets.UTF_8), 2), StandardCharsets.UTF_8);
            y1.a("ZXID 请求 header 中的 sdkInfoBase: " + str4);
            map.put("UDID-SDK-INFO-BASE", str4);
        } catch (Exception e2) {
            StringBuilder sbA = m2.a("ZXID 请求 header 创建异常: ");
            sbA.append(e2.getMessage());
            y1.b(sbA.toString());
        }
        map.put("UDID-PROTOCOL", "v3.0.0");
        map.put("UDID-KEY", str);
        return map;
    }

    @Java2C.Method2C
    public static native SSLSocketFactory c() throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException, KeyManagementException;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    public static JSONObject d() {
        ?? r2;
        ?? E = e();
        try {
            b3 b3Var = t1.a.f6285a.f6284a;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("debug", d3.i() ? 1 : 0);
            jSONObject.put("permission", b3Var.h());
            jSONObject.put(WebLoadEvent.ENABLE, b3Var.f());
            jSONObject.put("showPermissionDialog", b3Var.e());
            E.put("userSettings", jSONObject);
            s0 s0Var = s0.a.f6280a;
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("pts", s0Var.f6279a);
                jSONObject2.put("pid", s0Var.b);
                jSONObject2.put("rc", s0Var.c);
            } catch (Exception e2) {
                y1.a(e2);
            }
            E.put("processInfo", jSONObject2);
            try {
                r2 = h1.b(t2.f6286a).getBoolean("ZX_IS_PRIVACY");
            } catch (PackageManager.NameNotFoundException e3) {
                y1.a(e3);
                r2 = 0;
            }
            E.put("privacy", r2);
            E.put("appIds", h1.a());
        } catch (JSONException e4) {
            StringBuilder sbA = m2.a("ZXID 构建SDKInfo异常:");
            sbA.append(e4.getMessage());
            y1.b(sbA.toString());
        }
        return E;
    }

    public static JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", t2.b);
            jSONObject.put("configVersion", t2.n);
            if (TextUtils.equals("core-d", t2.c)) {
                jSONObject.put("versiond", t2.d);
            }
            jSONObject.put(RemoteMessageConst.Notification.CHANNEL_ID, t2.f6287e);
            jSONObject.put("arch", Build.CPU_ABI);
        } catch (JSONException e2) {
            StringBuilder sbA = m2.a("ZXID 构建SDKInfoBase异常:");
            sbA.append(e2.getMessage());
            y1.b(sbA.toString());
        }
        return jSONObject;
    }

    public static X509Certificate f() throws CertificateException {
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(a(b[0]).getBytes()));
    }

    @Java2C.Method2C
    public static native String[] g();

    @Java2C.Method2C
    public static native String[] h();

    public static void i() {
        try {
            v1.a aVar = new v1.a();
            aVar.b.add(new h0(y1.a.f6306a.f6305a, 5));
            aVar.b.add(new a());
            SSLSocketFactory sSLSocketFactoryC = c();
            if (sSLSocketFactoryC == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            aVar.c = sSLSocketFactoryC;
            f6300a = new v1(aVar);
        } catch (Throwable th) {
            y1.a(th);
            th.printStackTrace();
        }
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("os", "Android");
            jSONObject.put("applicationId", t2.g);
            PackageManager packageManager = d3.f6211a;
            jSONObject.put(bm.O, Locale.getDefault().getCountry());
            jSONObject.put("language", Locale.getDefault().getLanguage());
            jSONObject.put("model", Build.MODEL);
            jSONObject.put("arch", d3.c());
            jSONObject.put("androidVersion", d3.a("59"));
        } catch (JSONException e2) {
            StringBuilder sbA = m2.a("ZXID 构建deviceInfo异常:");
            sbA.append(e2.getMessage());
            y1.b(sbA.toString());
        }
        return jSONObject;
    }

    public static void a(StringBuffer stringBuffer) {
        for (int i = 0; i < 5; i++) {
            stringBuffer.append("-");
        }
    }

    public static JSONObject a() throws JSONException {
        String string;
        Exception e2;
        Signature[] signatureArrA;
        MessageDigest messageDigest;
        String str = "error!";
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appPkg", t2.g);
        try {
            signatureArrA = d.a(t2.g);
        } catch (Exception e3) {
            string = "error!";
            e2 = e3;
        }
        if (signatureArrA != null) {
            string = "error!";
            for (Signature signature : signatureArrA) {
                try {
                    byte[] byteArray = signature.toByteArray();
                    try {
                        messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
                    } catch (NoSuchAlgorithmException e4) {
                        y1.a(e4);
                    }
                    if (messageDigest != null) {
                        byte[] bArrDigest = messageDigest.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b2 : bArrDigest) {
                            sb.append(Integer.toHexString((b2 & 255) | 256).substring(1, 3));
                        }
                        string = sb.toString();
                    } else {
                        string = "error!";
                    }
                } catch (Exception e5) {
                    e2 = e5;
                }
            }
            str = string;
            jSONObject.put("appSign", str);
            jSONObject.put("appId", t2.f);
            return jSONObject;
        }
        jSONObject.put("appSign", str);
        jSONObject.put("appId", t2.f);
        return jSONObject;
        y1.a(e2);
        str = string;
        jSONObject.put("appSign", str);
        jSONObject.put("appId", t2.f);
        return jSONObject;
    }
}
