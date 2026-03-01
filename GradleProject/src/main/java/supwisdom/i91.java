package supwisdom;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sangfor.sdk.utils.SFLogN;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class i91 {
    public static NetworkInfo a(Context context) {
        return a();
    }

    public static String b(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        try {
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            return dhcpInfo != null ? a(dhcpInfo.gateway) : "";
        } catch (Exception e2) {
            SFLogN.a("NetworkInfoHelper", "getGatewayIP failed", "getDhcpInfo exception occured", e2);
            return "";
        }
    }

    public static String c(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        return telephonyManager != null ? telephonyManager.getNetworkOperatorName() : "";
    }

    public static String d(Context context) {
        NetworkInfo networkInfoA = a(context);
        return (networkInfoA == null || !networkInfoA.isAvailable()) ? "" : networkInfoA.getSubtypeName();
    }

    public static String e(Context context) {
        WifiInfo wifiInfoG = g(context);
        if (wifiInfoG != null) {
            return a(wifiInfoG.getIpAddress());
        }
        return null;
    }

    public static String f(Context context) {
        WifiInfo wifiInfoG;
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        if (wifiManager == null || (wifiInfoG = g(context)) == null) {
            return "";
        }
        String ssid = wifiInfoG.getSSID();
        if (!TextUtils.isEmpty(ssid) && !ssid.equalsIgnoreCase("<unknown ssid>")) {
            int length = ssid.length();
            if (length < 2 || ssid.charAt(0) != '\"') {
                return ssid;
            }
            int i = length - 1;
            return ssid.charAt(i) == '\"' ? ssid.substring(1, i) : ssid;
        }
        int networkId = wifiInfoG.getNetworkId();
        if (networkId == -1) {
            return "";
        }
        List<WifiConfiguration> configuredNetworks = null;
        try {
            configuredNetworks = wifiManager.getConfiguredNetworks();
            if (configuredNetworks == null) {
                return "";
            }
        } catch (Exception e2) {
            SFLogN.a("NetworkInfoHelper", "getWiFiSSID failed.", "getConfiguredNetworks exception occured", e2);
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (wifiConfiguration.networkId == networkId) {
                if (TextUtils.isEmpty(wifiConfiguration.SSID)) {
                    return "";
                }
                String str = wifiConfiguration.SSID;
                int length2 = str.length();
                if (length2 < 2 || str.charAt(0) != '\"') {
                    return str;
                }
                int i2 = length2 - 1;
                return str.charAt(i2) == '\"' ? str.substring(1, i2) : str;
            }
        }
        return ssid;
    }

    public static WifiInfo g(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            SFLogN.a("NetworkInfoHelper", "getWifiInfo failed.", "getSystemService WIFI_SERVICE ret null.");
            return null;
        }
        try {
            return wifiManager.getConnectionInfo();
        } catch (Exception e2) {
            SFLogN.a("NetworkInfoHelper", "getWifiInfo failed", "getConnectionInfo failed,exception occured.", e2);
            return null;
        }
    }

    public static NetworkInfo a() {
        Context contextB = w61.b();
        if (contextB != null) {
            return ((ConnectivityManager) contextB.getSystemService("connectivity")).getActiveNetworkInfo();
        }
        SFLogN.b("NetworkInfoHelper", "you have to call SangforCore init func");
        throw new RuntimeException("you have to call SangforCore init func");
    }

    public static String a(int i) {
        return String.format(Locale.getDefault(), "%d.%d.%d.%d", Integer.valueOf(i & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 24) & 255));
    }
}
