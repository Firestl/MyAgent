package supwisdom;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class tp {
    public static tp b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9309a;

    public tp(Context context) {
        String macAddress;
        try {
            try {
                macAddress = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
                this.f9309a = macAddress;
            } catch (Exception e2) {
                vp.a(e2);
                if (TextUtils.isEmpty(this.f9309a)) {
                }
            }
            if (TextUtils.isEmpty(macAddress)) {
                this.f9309a = "00:00:00:00:00:00";
            }
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.f9309a)) {
                this.f9309a = "00:00:00:00:00:00";
            }
            throw th;
        }
    }

    public static tp a(Context context) {
        if (b == null) {
            b = new tp(context);
        }
        return b;
    }

    public static com.alipay.sdk.util.e b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) ? (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) ? com.alipay.sdk.util.e.NONE : com.alipay.sdk.util.e.WIFI : com.alipay.sdk.util.e.a(activeNetworkInfo.getSubtype());
        } catch (Exception unused) {
            return com.alipay.sdk.util.e.NONE;
        }
    }

    public String a() {
        return "000000000000000";
    }

    public String b() {
        return "000000000000000";
    }

    public String c() {
        return this.f9309a;
    }

    public static String c(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
