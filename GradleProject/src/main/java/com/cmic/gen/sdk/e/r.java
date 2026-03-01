package com.cmic.gen.sdk.e;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static r f1743a;
    public ConnectivityManager b;
    public Network c;
    public ConnectivityManager.NetworkCallback d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1744e;

    public interface a {
        void a(Network network);
    }

    public r(Context context) {
        try {
            this.b = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static r a(Context context) {
        if (f1743a == null) {
            synchronized (r.class) {
                if (f1743a == null) {
                    f1743a = new r(context);
                }
            }
        }
        return f1743a;
    }

    @TargetApi(21)
    public synchronized void a(final a aVar) {
        NetworkInfo networkInfo;
        if (this.b == null) {
            c.a("WifiNetworkUtils", "mConnectivityManager 为空");
            aVar.a(null);
            return;
        }
        if (this.c != null && !this.f1744e && (networkInfo = this.b.getNetworkInfo(this.c)) != null && networkInfo.isAvailable()) {
            c.a("HttpUtils", "reuse network: ");
            aVar.a(this.c);
            return;
        }
        if (this.d == null) {
            NetworkRequest networkRequestBuild = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.cmic.gen.sdk.e.r.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    try {
                        if (r.this.b.getNetworkCapabilities(network).hasTransport(0)) {
                            r.this.c = network;
                            aVar.a(network);
                            r.this.f1744e = false;
                        } else {
                            c.a("WifiNetworkUtils", "切换失败，未开启数据网络");
                            r.this.c = null;
                            aVar.a(null);
                            r.this.b.unregisterNetworkCallback(r.this.d);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        r.this.c = null;
                        aVar.a(null);
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    r.this.f1744e = true;
                }
            };
            this.d = networkCallback;
            this.b.requestNetwork(networkRequestBuild, networkCallback);
            return;
        }
        try {
            this.b.unregisterNetworkCallback(this.d);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.d = null;
        }
        c.a("HttpUtils", "clear: ");
        NetworkRequest networkRequestBuild2 = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
        ConnectivityManager.NetworkCallback networkCallback2 = new ConnectivityManager.NetworkCallback() { // from class: com.cmic.gen.sdk.e.r.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                try {
                    if (r.this.b.getNetworkCapabilities(network).hasTransport(0)) {
                        r.this.c = network;
                        aVar.a(network);
                        r.this.f1744e = false;
                    } else {
                        c.a("WifiNetworkUtils", "切换失败，未开启数据网络");
                        r.this.c = null;
                        aVar.a(null);
                        r.this.b.unregisterNetworkCallback(r.this.d);
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                    r.this.c = null;
                    aVar.a(null);
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                r.this.f1744e = true;
            }
        };
        this.d = networkCallback2;
        try {
            this.b.requestNetwork(networkRequestBuild2, networkCallback2);
        } catch (Exception e3) {
            e3.printStackTrace();
            aVar.a(null);
        }
        return;
    }

    public boolean a() {
        return Build.VERSION.SDK_INT >= 21 && this.c != null;
    }

    public void b() {
        ConnectivityManager connectivityManager = this.b;
        if (connectivityManager == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT < 21 || this.d == null) {
                return;
            }
            connectivityManager.unregisterNetworkCallback(this.d);
            this.d = null;
            this.c = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
