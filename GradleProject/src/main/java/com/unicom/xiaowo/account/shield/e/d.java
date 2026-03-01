package com.unicom.xiaowo.account.shield.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class d {
    public boolean a(Context context, String str) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo.State state = connectivityManager.getNetworkInfo(5).getState();
            f.a("TYPE_MOBILE_HIPRI network state: " + state);
            if (state.compareTo(NetworkInfo.State.CONNECTED) != 0 && state.compareTo(NetworkInfo.State.CONNECTING) != 0) {
                Method method = ConnectivityManager.class.getMethod("startUsingNetworkFeature", Integer.TYPE, String.class);
                method.setAccessible(true);
                int iIntValue = ((Integer) method.invoke(connectivityManager, 0, "enableHIPRI")).intValue();
                f.a("startUsingNetworkFeature for enableHIPRI result: " + iIntValue);
                if (-1 == iIntValue) {
                    f.a("Wrong result of startUsingNetworkFeature, maybe problems");
                    return false;
                }
                if (iIntValue == 0) {
                    f.a("No need to perform additional network settings");
                    return true;
                }
                int iC = i.c(i.b(str));
                if (-1 == iC) {
                    f.a("Wrong host address transformation, result was -1");
                    return false;
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        return false;
                    }
                }
                Method method2 = ConnectivityManager.class.getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE);
                method2.setAccessible(true);
                boolean zBooleanValue = ((Boolean) method2.invoke(connectivityManager, 5, Integer.valueOf(iC))).booleanValue();
                f.a("requestRouteToHost result: " + zBooleanValue);
                return zBooleanValue;
            }
            return true;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }
}
