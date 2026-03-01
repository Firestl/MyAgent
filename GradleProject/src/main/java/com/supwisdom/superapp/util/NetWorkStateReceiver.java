package com.supwisdom.superapp.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class NetWorkStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 21) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            Network[] allNetworks = connectivityManager.getAllNetworks();
            StringBuilder sb = new StringBuilder();
            for (Network network : allNetworks) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            }
            Toast.makeText(context, sb.toString(), 0).show();
            return;
        }
        ConnectivityManager connectivityManager2 = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo2 = connectivityManager2.getNetworkInfo(1);
        NetworkInfo networkInfo3 = connectivityManager2.getNetworkInfo(0);
        if (networkInfo2.isConnected() && networkInfo3.isConnected()) {
            Toast.makeText(context, "WIFI已连接,移动数据已连接", 0).show();
            return;
        }
        if (networkInfo2.isConnected() && !networkInfo3.isConnected()) {
            Toast.makeText(context, "WIFI已连接,移动数据已断开", 0).show();
        } else if (networkInfo2.isConnected() || !networkInfo3.isConnected()) {
            Toast.makeText(context, "WIFI已断开,移动数据已断开", 0).show();
        } else {
            Toast.makeText(context, "WIFI已断开,移动数据已连接", 0).show();
        }
    }
}
