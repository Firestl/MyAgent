package cn.com.chinatelecom.account.api.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
public abstract class f implements e {
    public static final String b = "f";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1505a;

    public f(Context context) {
        this.f1505a = context;
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                ((Boolean) Class.forName("android.net.ConnectivityManager").getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE).invoke(connectivityManager, 5, Integer.valueOf(cn.com.chinatelecom.account.api.c.c.a(cn.com.chinatelecom.account.api.c.c.b(str))))).booleanValue();
            }
        } catch (Throwable th) {
            CtAuth.warn(b, "http doPost > requestUrlToRoute error", th);
        }
    }

    private boolean b() {
        return cn.com.chinatelecom.account.api.e.g.c(this.f1505a);
    }

    public boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public boolean a(String str) {
        return str.startsWith("https");
    }

    public boolean a(boolean z, String str) {
        return z && str != null && Build.VERSION.SDK_INT >= 17;
    }

    public HttpURLConnection d(String str, String str2, int i, g gVar) throws IOException {
        URL url = new URL(str);
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((gVar.f1506a == null || !a()) ? url.openConnection() : gVar.f1506a.openConnection(url));
        httpURLConnection.setRequestProperty("accept", "*/*");
        if (i == 0) {
            httpURLConnection.setRequestMethod("GET");
        } else {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
        httpURLConnection.setConnectTimeout(gVar.a());
        httpURLConnection.setReadTimeout(gVar.b());
        httpURLConnection.setUseCaches(false);
        if (!b() && !a()) {
            httpURLConnection.setInstanceFollowRedirects(false);
        }
        httpURLConnection.addRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.addRequestProperty("reqId", gVar.d);
        httpURLConnection.addRequestProperty("deviceId", cn.com.chinatelecom.account.api.e.d.a(this.f1505a));
        if (TextUtils.isEmpty(str2)) {
            httpURLConnection.connect();
        } else {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream()));
            dataOutputStream.write(str2.getBytes("UTF-8"));
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        return httpURLConnection;
    }

    public HttpsURLConnection e(String str, String str2, int i, g gVar) throws IOException {
        URL url = new URL(str);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) ((gVar.f1506a == null || !a()) ? url.openConnection() : gVar.f1506a.openConnection(url));
        httpsURLConnection.setRequestProperty("accept", "*/*");
        if (i == 0) {
            httpsURLConnection.setRequestMethod("GET");
        } else {
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
        }
        httpsURLConnection.setConnectTimeout(gVar.a());
        httpsURLConnection.setReadTimeout(gVar.b());
        httpsURLConnection.setUseCaches(false);
        if (!b() && !a()) {
            httpsURLConnection.setInstanceFollowRedirects(false);
        }
        httpsURLConnection.addRequestProperty("Accept-Charset", "UTF-8");
        httpsURLConnection.addRequestProperty("reqId", gVar.d);
        httpsURLConnection.addRequestProperty("deviceId", cn.com.chinatelecom.account.api.e.d.a(this.f1505a));
        Map<String, String> map = gVar.i;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : gVar.i.entrySet()) {
                httpsURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (TextUtils.isEmpty(str2)) {
            httpsURLConnection.connect();
        } else {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(httpsURLConnection.getOutputStream()));
            dataOutputStream.write(str2.getBytes("UTF-8"));
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        return httpsURLConnection;
    }
}
