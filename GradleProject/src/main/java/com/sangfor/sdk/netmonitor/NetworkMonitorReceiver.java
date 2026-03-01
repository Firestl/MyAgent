package com.sangfor.sdk.netmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import supwisdom.i91;
import supwisdom.j91;
import supwisdom.k91;
import supwisdom.xb1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class NetworkMonitorReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<j91> f3928a = new HashSet();
    public Set<k91> b = new HashSet();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        SFLogN.c("NetworkMonitorReceiver", "onReceive networkchanged event: " + intent.getAction());
        if (xb1.c().a()) {
            SFLogN.b("NetworkMonitorReceiver", "onReceive  networkchanged intercepted", "only onReceive after agree privacy policy");
            return;
        }
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("previous_wifi_state", 4);
                int intExtra2 = intent.getIntExtra("wifi_state", 4);
                NetworkInfo networkInfoA = i91.a(context);
                Iterator<k91> it = this.b.iterator();
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
            SFLogN.a("NetworkMonitorReceiver", "Current Network Info:[Mobile]%s[%s]", i91.c(context), i91.d(context));
            SFLogN.c("NetworkMonitorReceiver", "Network registers, size:" + this.f3928a.size() + ", " + this.f3928a.toString());
            Iterator<j91> it2 = this.f3928a.iterator();
            while (it2.hasNext()) {
                it2.next().a(networkInfo, booleanExtra);
            }
        }
        if (1 == type) {
            SFLogN.a("NetworkMonitorReceiver", "Current Network Info:[WiFi]%s[ip:%s gateway:%s]", i91.f(context), i91.e(context), i91.b(context));
            SFLogN.c("NetworkMonitorReceiver", "Network registers, size:" + this.f3928a.size() + ", " + this.f3928a.toString());
            Iterator<j91> it3 = this.f3928a.iterator();
            while (it3.hasNext()) {
                it3.next().a(networkInfo, booleanExtra);
            }
        }
        if (17 == type) {
            Iterator<j91> it4 = this.f3928a.iterator();
            while (it4.hasNext()) {
                it4.next().a(networkInfo, booleanExtra);
            }
        }
    }
}
