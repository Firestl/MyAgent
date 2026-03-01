package io.dcloud.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkTypeUtil {
    public static final int NETWORK_TYPE_2G = 4;
    public static final int NETWORK_TYPE_3G = 5;
    public static final int NETWORK_TYPE_4G = 6;
    public static final int NETWORK_TYPE_DISABLED = 1;
    public static final int NETWORK_TYPE_LINE = 2;
    public static final int NETWORK_TYPE_UNKONWN = 0;
    public static final int NETWORK_TYPE_WIFI = 3;

    public static String getCurrentAPN(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) ? "" : activeNetworkInfo.getExtraInfo();
    }

    public static int getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return 1;
        }
        if (activeNetworkInfo.getType() != 0) {
            return activeNetworkInfo.getType() == 1 ? 3 : 0;
        }
        switch (activeNetworkInfo.getSubtype()) {
        }
        return 0;
    }

    public static boolean isWifiProxy(Context context) {
        int port;
        String property;
        if (Build.VERSION.SDK_INT >= 14) {
            property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property2 == null) {
                property2 = "-1";
            }
            port = Integer.parseInt(property2);
        } else {
            String host = Proxy.getHost(context);
            port = Proxy.getPort(context);
            property = host;
        }
        return (TextUtils.isEmpty(property) || port == -1) ? false : true;
    }
}
