package com.sangfor.sdk.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import supwisdom.f91;
import supwisdom.g91;
import supwisdom.h91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class NetworkMonitorReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<g91> f3927a = new HashSet();
    public Set<h91> b = new HashSet();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("previous_wifi_state", 4);
                int intExtra2 = intent.getIntExtra("wifi_state", 4);
                NetworkInfo networkInfoA = f91.a(context);
                Iterator<h91> it = this.b.iterator();
                while (it.hasNext()) {
                    it.next().a(intExtra2, intExtra, networkInfoA);
                }
                return;
            }
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (networkInfo == null) {
            return;
        }
        int type = networkInfo.getType();
        SFLogN.a("NetworkMonitorReceiver", "Connectivity changed: {%s[%s]:%s/%s}", networkInfo.getTypeName(), networkInfo.getSubtypeName(), networkInfo.getState(), networkInfo.getDetailedState());
        if (type == 0) {
            SFLogN.a("NetworkMonitorReceiver", "Current Network Info:[Mobile]%s[%s]", f91.c(context), f91.d(context));
            SFLogN.c("NetworkMonitorReceiver", "Network registers, size:" + this.f3927a.size() + ", " + this.f3927a.toString());
            Iterator<g91> it2 = this.f3927a.iterator();
            while (it2.hasNext()) {
                it2.next().a(networkInfo, booleanExtra);
            }
        }
        if (1 == type) {
            SFLogN.a("NetworkMonitorReceiver", "Current Network Info:[WiFi]%s[ip:%s gateway:%s]", f91.f(context), f91.e(context), f91.b(context));
            SFLogN.c("NetworkMonitorReceiver", "Network registers, size:" + this.f3927a.size() + ", " + this.f3927a.toString());
            Iterator<g91> it3 = this.f3927a.iterator();
            while (it3.hasNext()) {
                it3.next().a(networkInfo, booleanExtra);
            }
        }
    }
}
