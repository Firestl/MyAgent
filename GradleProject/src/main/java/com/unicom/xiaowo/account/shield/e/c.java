package com.unicom.xiaowo.account.shield.e;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static c f;
    public List<a> d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Network f5545a = null;
    public ConnectivityManager.NetworkCallback b = null;
    public ConnectivityManager c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Timer f5546e = null;

    public interface a {
        void a(boolean z, Network network);
    }

    public c() {
        this.d = null;
        this.d = new ArrayList();
    }

    public static c a() {
        if (f == null) {
            synchronized (c.class) {
                if (f == null) {
                    f = new c();
                }
            }
        }
        return f;
    }

    @TargetApi(21)
    private synchronized void a(Context context, a aVar) {
        if (this.f5545a != null) {
            aVar.a(true, this.f5545a);
            return;
        }
        a(aVar);
        if (this.b == null || this.d.size() < 2) {
            try {
                this.c = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(0);
                builder.addCapability(12);
                NetworkRequest networkRequestBuild = builder.build();
                this.b = new ConnectivityManager.NetworkCallback() { // from class: com.unicom.xiaowo.account.shield.e.c.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network) {
                        super.onAvailable(network);
                        f.a("Network onAvailable");
                        c.this.f5545a = network;
                        c.this.a(true, network);
                        try {
                            NetworkInfo networkInfo = c.this.c.getNetworkInfo(c.this.f5545a);
                            String extraInfo = networkInfo.getExtraInfo();
                            f.a("APN:" + networkInfo.toString());
                            if (TextUtils.isEmpty(extraInfo)) {
                                return;
                            }
                            g.d(extraInfo);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLost(Network network) {
                        super.onLost(network);
                        f.a("Network onLost");
                        c.this.b();
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onUnavailable() {
                        super.onUnavailable();
                        f.a("Network onUnavailable");
                        c.this.a(false, (Network) null);
                        c.this.b();
                    }
                };
                int i = 3000;
                if (g.c() < 3000) {
                    i = 2000;
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    this.c.requestNetwork(networkRequestBuild, this.b, i);
                } else {
                    Timer timer = new Timer();
                    this.f5546e = timer;
                    timer.schedule(new TimerTask() { // from class: com.unicom.xiaowo.account.shield.e.c.2
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            c.this.a(false, (Network) null);
                        }
                    }, i);
                    this.c.requestNetwork(networkRequestBuild, this.b);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                a(false, (Network) null);
            }
        }
    }

    private synchronized void a(a aVar) {
        try {
            this.d.add(aVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z, Network network) {
        try {
            if (this.f5546e != null) {
                this.f5546e.cancel();
                this.f5546e = null;
            }
            Iterator<a> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().a(z, network);
            }
            this.d.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(Context context, String str, a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            a(context, aVar);
        } else {
            aVar.a(true, null);
        }
    }

    public synchronized void b() {
        try {
            if (this.f5546e != null) {
                this.f5546e.cancel();
                this.f5546e = null;
            }
            if (Build.VERSION.SDK_INT >= 21 && this.c != null && this.b != null) {
                this.c.unregisterNetworkCallback(this.b);
            }
            this.c = null;
            this.b = null;
            this.f5545a = null;
            this.d.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
