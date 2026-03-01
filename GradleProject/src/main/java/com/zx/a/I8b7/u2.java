package com.zx.a.I8b7;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.Network;
import android.text.TextUtils;
import com.zx.module.annotation.Java2C;
import com.zx.module.base.Callback;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class u2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[] f6290a = h();
    public final String[] b = f();
    public final String[] c = d();

    @SuppressLint({"NewApi"})
    public class a extends ConnectivityManager.NetworkCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConnectivityManager f6291a;
        public Callback b;
        public TimerTask d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public JSONObject f6292e;
        public int f;
        public final AtomicBoolean g = new AtomicBoolean(false);
        public Timer c = new Timer();

        /* JADX INFO: renamed from: com.zx.a.I8b7.u2$a$a, reason: collision with other inner class name */
        public class C0141a extends TimerTask {
            public C0141a(u2 u2Var) {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                try {
                    a aVar = a.this;
                    Callback callback = aVar.b;
                    if (callback != null) {
                        callback.callback(u2.this.a("wifi 情况下切换数据网络超时, 检查是否打开数据网络!", 1));
                    }
                } catch (JSONException e2) {
                    y1.a(e2);
                }
            }
        }

        public a(JSONObject jSONObject, ConnectivityManager connectivityManager, Callback callback, int i) {
            this.f6292e = jSONObject;
            this.f = i;
            this.f6291a = connectivityManager;
            this.b = callback;
            C0141a c0141a = new C0141a(u2.this);
            this.d = c0141a;
            this.c.schedule(c0141a, 7000L);
        }

        @Java2C.Method2C
        private native void a(Network network, String str);

        public final void a(Network network) throws Exception {
            HttpURLConnection httpURLConnection = (HttpURLConnection) network.openConnection(new URL(u2.this.b[0]));
            httpURLConnection.setConnectTimeout(7000);
            httpURLConnection.setReadTimeout(7000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            JSONObject jSONObjectA = u2.this.a();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            bufferedWriter.write(jSONObjectA.toString());
            bufferedWriter.close();
            JSONObject jSONObject = new JSONObject(e1.a(m0.b("text/json; charset=utf-8"), httpURLConnection.getContentLength(), httpURLConnection.getResponseCode() == 200 ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()).b()).getJSONObject("body");
            String string = jSONObject.getString("resultCode");
            String string2 = jSONObject.getString("resultDesc");
            httpURLConnection.disconnect();
            if ("103000".equals(string) && "成功".equals(string2)) {
                u2.this.a(this.f6292e, this.b, this.f, "cmcc", jSONObject.getString("token"));
            } else {
                this.b.callback(u2.this.a(jSONObject.toString(), 1));
            }
        }

        public final void b(Network network) throws Exception {
            HttpURLConnection httpURLConnection = (HttpURLConnection) network.openConnection(new URL(u2.this.c[0]));
            httpURLConnection.setConnectTimeout(7000);
            httpURLConnection.setReadTimeout(7000);
            httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            String strSubstring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
            String strA = u2.this.a(strSubstring);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            bufferedWriter.write(strA);
            bufferedWriter.close();
            JSONObject jSONObject = new JSONObject(e1.a(m0.b(""), httpURLConnection.getContentLength(), httpURLConnection.getResponseCode() == 200 ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()).b());
            httpURLConnection.disconnect();
            String string = jSONObject.getString("msg");
            int i = jSONObject.getInt("result");
            String strOptString = jSONObject.optString("data");
            if (i == 0 && "success".equals(string) && !TextUtils.isEmpty(strOptString)) {
                u2.this.a(this.f6292e, this.b, this.f, "ct", u2.this.a(strSubstring, strOptString));
            } else {
                this.b.callback(u2.this.a(jSONObject.toString(), 1));
            }
        }

        public final void c(Network network) throws Exception {
            u2 u2Var = u2.this;
            String strB = u2Var.b(u2Var.f6290a[0]);
            y1.a("unicomUAIDNisportalUrl: " + strB);
            HttpURLConnection httpURLConnection = (HttpURLConnection) network.openConnection(new URL(strB));
            httpURLConnection.setConnectTimeout(7000);
            httpURLConnection.setReadTimeout(7000);
            httpURLConnection.connect();
            JSONObject jSONObject = new JSONObject(e1.a(m0.b("text/json; charset=utf-8"), httpURLConnection.getContentLength(), httpURLConnection.getResponseCode() == 200 ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()).b());
            String strOptString = jSONObject.optString("authurl");
            if (TextUtils.isEmpty(strOptString)) {
                throw new RuntimeException(jSONObject.toString());
            }
            String strB2 = u2.this.b(strOptString);
            y1.a("unicomUAIDAuthUrl: " + strB2);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) network.openConnection(new URL(strB2));
            httpURLConnection2.setConnectTimeout(7000);
            httpURLConnection2.setReadTimeout(7000);
            httpURLConnection2.connect();
            String string = new JSONObject(e1.a(m0.b("text/json; charset=utf-8"), httpURLConnection2.getContentLength(), httpURLConnection2.getResponseCode() == 200 ? httpURLConnection2.getInputStream() : httpURLConnection2.getErrorStream()).b()).getString("code");
            y1.a("unicomUAID code: " + string);
            a(network, string);
            httpURLConnection.disconnect();
            httpURLConnection2.disconnect();
            u2.this.a(this.f6292e, this.b, this.f, "unicom", string);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            try {
                if (this.g.getAndSet(true)) {
                    return;
                }
                y1.a("zx 网络切换: 使用移动网络访问");
                this.d.cancel();
                this.c.cancel();
                String strB = d3.b(t2.f6286a);
                if ("中国联通".equals(strB)) {
                    c(network);
                } else if ("中国移动".equals(strB)) {
                    a(network);
                } else if ("中国电信".equals(strB)) {
                    b(network);
                } else {
                    this.b.callback(u2.this.a("暂不支持该运营商", 1));
                }
                this.f6291a.unregisterNetworkCallback(this);
            } catch (Throwable th) {
                y1.a(th);
                Callback callback = this.b;
                if (callback != null) {
                    try {
                        callback.callback(u2.this.a(th.getMessage(), 1));
                        this.f6291a.unregisterNetworkCallback(this);
                    } catch (JSONException e2) {
                        y1.a(e2);
                    }
                }
            }
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final u2 f6294a = new u2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Java2C.Method2C
    public native String a(String str) throws Exception;

    /* JADX INFO: Access modifiers changed from: private */
    @Java2C.Method2C
    public native String a(String str, String str2) throws BadPaddingException, JSONException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException;

    /* JADX INFO: Access modifiers changed from: private */
    @Java2C.Method2C
    public native JSONObject a() throws JSONException;

    @Java2C.Method2C
    private native void a(JSONObject jSONObject, Callback callback, int i) throws Exception;

    /* JADX INFO: Access modifiers changed from: private */
    @Java2C.Method2C
    public native String b(String str);

    @Java2C.Method2C
    private final native String[] b();

    @Java2C.Method2C
    private native String c() throws Exception;

    @Java2C.Method2C
    private final native String[] d();

    @Java2C.Method2C
    private native String e() throws Exception;

    @Java2C.Method2C
    private final native String[] f();

    @Java2C.Method2C
    private native String g();

    @Java2C.Method2C
    private final native String[] h();

    @Java2C.Method2C
    private native String i() throws Exception;

    /* JADX WARN: Removed duplicated region for block: B:27:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(org.json.JSONObject r10, com.zx.module.base.Callback r11, int r12) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.u2.b(org.json.JSONObject, com.zx.module.base.Callback, int):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:9|(2:26|10)|(4:11|(3:13|(2:15|32)(2:16|31)|17)(1:30)|22|34)|18|28|19|22|34) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(org.json.JSONObject r11, com.zx.module.base.Callback r12, int r13, java.lang.String r14, java.lang.String r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.u2.a(org.json.JSONObject, com.zx.module.base.Callback, int, java.lang.String, java.lang.String):void");
    }

    public final String a(String str, int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", str);
        jSONObject.put("code", i);
        return jSONObject.toString();
    }
}
