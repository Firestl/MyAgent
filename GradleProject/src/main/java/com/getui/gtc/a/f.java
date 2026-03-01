package com.getui.gtc.a;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.text.TextUtils;
import com.getui.gtc.a.a.h;
import com.getui.gtc.a.a.i;
import com.getui.gtc.a.a.l;
import com.getui.gtc.a.a.n;
import com.getui.gtc.a.a.o;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.base.util.ScheduleQueue;
import com.getui.gtc.e.c;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class f implements b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f2114e = 4;
    public static long i = 86400000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2115a;
    public int b;
    public boolean h = false;
    public long j = 604800000;
    public String k = "none";
    public boolean c = true;
    public boolean d = true;
    public boolean f = false;
    public boolean g = false;
    public final AtomicBoolean l = new AtomicBoolean(true);

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i2) {
        if (i2 == -1) {
            com.getui.gtc.i.c.a.d("not integrate ct.");
            a(3, new o(-3));
            return;
        }
        if (!l.c()) {
            com.getui.gtc.i.c.a.d("not init ct.");
            a(3, new o(-4));
            return;
        }
        o oVarB = l.b("");
        boolean zIsEmpty = TextUtils.isEmpty(oVarB.c);
        if (this.l.getAndSet(false) && zIsEmpty) {
            ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.a.f.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        com.getui.gtc.i.c.a.d("ct retry.");
                        f.this.a(i2);
                    } catch (Throwable th) {
                        com.getui.gtc.i.c.a.b(th);
                    }
                }
            }, 5000L);
        } else if (zIsEmpty && (this.b & 1) == 1) {
            l.g();
        } else {
            a(3, oVarB);
        }
    }

    public static void a(int i2, o oVar) {
        String str;
        try {
            String str2 = oVar.c;
            int i3 = 0;
            String strA = "";
            if (TextUtils.isEmpty(str2)) {
                str = "";
            } else {
                if (i2 != 1) {
                    String strA2 = n.a(str2);
                    com.getui.gtc.i.c.a.d("305 * PM: ".concat(String.valueOf(str2)));
                    strA = strA2;
                } else {
                    strA = n.a(h.a(str2));
                    i3 = 1;
                }
                str = "2#" + n.a();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            a(a.a(simpleDateFormat.format(new Date())) + "|" + a.a(com.getui.gtc.c.b.d) + "|" + a.a(com.getui.gtc.c.b.f2140a) + "|android|" + GtcProvider.context().getPackageName() + "|GTC-3.2.13.0|" + i2 + "|" + oVar.f2108a + "|" + strA + "|" + oVar.b + "|" + str + "|" + i3);
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.c("type 305 report error: " + th.toString());
        }
    }

    public static void a(final String str) {
        boolean z;
        try {
            final JSONObject jSONObjectB = c.a.f2213a.f2212a.b();
            if (str == null) {
                str = jSONObjectB.optString("content");
                z = true;
            } else {
                jSONObjectB.put("collectTime", System.currentTimeMillis());
                jSONObjectB.put("content", str);
                c.a.f2213a.f2212a.b(jSONObjectB);
                com.getui.gtc.i.c.a.a("type 305 save content");
                z = false;
            }
            if (TextUtils.isEmpty(str)) {
                com.getui.gtc.i.c.a.c("type 305 no content report");
                return;
            }
            boolean z2 = str.endsWith("|1") ? false : true;
            if (System.currentTimeMillis() - jSONObjectB.optLong("reportTime") < i) {
                com.getui.gtc.i.c.a.c("type 305 report not expired");
                return;
            }
            if (z2) {
                com.getui.gtc.h.a.a(str, 305);
                jSONObjectB.put("reportTime", System.currentTimeMillis());
                c.a.f2213a.f2212a.b(jSONObjectB);
            } else {
                if (!z) {
                    ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.a.f.3
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                com.getui.gtc.h.a.a(str, 305);
                                jSONObjectB.put("reportTime", System.currentTimeMillis());
                                jSONObjectB.put("content", "");
                                c.a.f2213a.f2212a.b(jSONObjectB);
                                com.getui.gtc.i.c.a.a("type 305 delete content");
                            } catch (Throwable th) {
                                com.getui.gtc.i.c.a.b(th);
                            }
                        }
                    }, 300000L);
                    return;
                }
                com.getui.gtc.h.a.a(str, 305);
                jSONObjectB.put("reportTime", System.currentTimeMillis());
                jSONObjectB.put("content", "");
                c.a.f2213a.f2212a.b(jSONObjectB);
                com.getui.gtc.i.c.a.a("type 305 delete content");
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (!z) {
            com.getui.gtc.i.c.a.d("not integrate cu.");
            a(2, new o(-3));
            return;
        }
        if (!l.d()) {
            com.getui.gtc.i.c.a.d("not init cu.");
            a(2, new o(-4));
            return;
        }
        o oVarE = l.e();
        boolean zIsEmpty = TextUtils.isEmpty(oVarE.c);
        if (this.l.getAndSet(false) && zIsEmpty) {
            ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.a.f.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        com.getui.gtc.i.c.a.d("cu retry.");
                        f.this.a(true);
                    } catch (Throwable th) {
                        com.getui.gtc.i.c.a.b(th);
                    }
                }
            }, 5000L);
        } else if (zIsEmpty && (this.b & 2) == 2) {
            l.f();
        } else {
            a(2, oVarE);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map<String, String> mapA = com.getui.gtc.f.b.a(43200000L, (com.getui.gtc.f.d) null);
        if (mapA != null && mapA.size() > 0) {
            try {
                String str = mapA.get("sdk.gtc.type305.enable");
                if (str != null) {
                    this.h = Boolean.parseBoolean(str);
                }
            } catch (Exception e2) {
                com.getui.gtc.i.c.a.a(e2);
            }
            try {
                String str2 = mapA.get("sdk.gtc.type305.interval");
                if (str2 != null) {
                    i = Long.parseLong(str2) * 1000;
                }
            } catch (Exception e3) {
                com.getui.gtc.i.c.a.a(e3);
            }
            try {
                String str3 = mapA.get("sdk.gtc.type305.collect_interval");
                if (str3 != null) {
                    this.j = Long.parseLong(str3) * 1000;
                }
            } catch (Exception e4) {
                com.getui.gtc.i.c.a.a(e4);
            }
            try {
                String str4 = mapA.get("sdk.gtc.type305.cu_path_list");
                if (str4 != null) {
                    this.k = str4;
                }
            } catch (Exception e5) {
                com.getui.gtc.i.c.a.a(e5);
            }
            try {
                String str5 = mapA.get("sdk.gtc.type305.s_pm_enable");
                if (str5 != null) {
                    this.f2115a = Integer.parseInt(str5);
                }
            } catch (Exception e6) {
                com.getui.gtc.i.c.a.a(e6);
            }
            try {
                String str6 = mapA.get("sdk.gtc.type305.pl_enable");
                if (str6 != null) {
                    this.b = Integer.parseInt(str6);
                }
            } catch (Exception e7) {
                com.getui.gtc.i.c.a.a(e7);
            }
            try {
                String str7 = mapA.get("sdk.gtc.type305.first_call");
                if (str7 != null) {
                    f2114e = Integer.parseInt(str7);
                }
            } catch (Exception e8) {
                com.getui.gtc.i.c.a.a(e8);
            }
            try {
                String str8 = mapA.get("sdk.gtc.type305.n_pm_enable");
                if (str8 != null) {
                    this.f = Boolean.parseBoolean(str8);
                }
            } catch (Exception e9) {
                com.getui.gtc.i.c.a.a(e9);
            }
            try {
                String str9 = mapA.get("sdk.gtc.type305.cl_enable");
                if (str9 != null) {
                    this.g = Boolean.parseBoolean(str9);
                }
            } catch (Exception e10) {
                com.getui.gtc.i.c.a.a(e10);
            }
            try {
                String str10 = mapA.get("sdk.gtc.type305.sf_enable");
                if (str10 != null) {
                    this.d = Boolean.parseBoolean(str10);
                }
            } catch (Exception e11) {
                com.getui.gtc.i.c.a.a(e11);
            }
            try {
                String str11 = mapA.get("sdk.gtc.type305.gbd.check");
                if (str11 != null) {
                    this.c = Boolean.parseBoolean(str11);
                }
            } catch (Exception e12) {
                com.getui.gtc.i.c.a.a(e12);
            }
        }
        if (!this.h) {
            com.getui.gtc.i.c.a.b("type 305 is not enabled");
            return;
        }
        try {
            if (this.c) {
                Bundle bundle = new Bundle();
                bundle.putString(CountryCodeBean.SPECIAL_COUNTRYCODE_CN, "com.getui.gtc.extension.distribution.gbd.stub.PushExtension");
                if (com.getui.gtc.g.b.a(bundle) != null) {
                    com.getui.gtc.i.c.a.a("type 305 exist gbd pm");
                    return;
                }
            }
            a((String) null);
            JSONObject jSONObjectB = c.a.f2213a.f2212a.b();
            if (System.currentTimeMillis() - jSONObjectB.optLong("collectTime") < this.j) {
                com.getui.gtc.i.c.a.c("type 305 collect time not expired");
                return;
            }
            int iOptInt = jSONObjectB.optInt("accessCount") + 1;
            jSONObjectB.put("accessCount", iOptInt);
            c.a.f2213a.f2212a.b(jSONObjectB);
            if (iOptInt < f2114e) {
                com.getui.gtc.i.c.a.d("accessCount:" + iOptInt + " < starPmFirstCall:" + f2114e + ", ignored");
                return;
            }
            try {
                int iA = l.a(GtcProvider.context());
                if (this.d && !l.b(GtcProvider.context())) {
                    com.getui.gtc.i.c.a.d("type 305 report not sf.");
                    a(iA, new o(-1));
                    return;
                }
                if (!l.a()) {
                    com.getui.gtc.i.c.a.d("type 305 report not net.");
                    a(iA, new o(-2));
                    return;
                }
                com.getui.gtc.i.c.a.d("type 305 pmEnable: " + this.f2115a);
                if (iA != 1) {
                    if (iA == 2) {
                        if ((this.f2115a & 2) != 2) {
                            com.getui.gtc.i.c.a.d("type 305 starPm disable.");
                            return;
                        } else {
                            com.getui.gtc.i.c.a.d("type 305 cu fetch.");
                            a(l.a(this.k));
                            return;
                        }
                    }
                    if (iA != 3) {
                        com.getui.gtc.i.c.a.d("type 305 no mc or error.");
                        return;
                    } else if ((this.f2115a & 1) != 1) {
                        com.getui.gtc.i.c.a.d("type 305 starPm disable.");
                        return;
                    } else {
                        com.getui.gtc.i.c.a.d("type 305 ct fetch.");
                        a(l.b());
                        return;
                    }
                }
                if (!this.f) {
                    com.getui.gtc.i.c.a.d("type 305 cm vd disable.");
                    return;
                }
                com.getui.gtc.i.c.a.d("type 305 cm vd fetch.");
                final i iVar = new i(GtcProvider.context());
                boolean z = this.g;
                final ConnectivityManager connectivityManager = (ConnectivityManager) iVar.f2094a.getSystemService("connectivity");
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    com.getui.gtc.i.c.a.d("net info is null");
                    return;
                }
                int type = activeNetworkInfo.getType();
                if (type == 0) {
                    com.getui.gtc.i.c.a.d("in cl.");
                    iVar.a((Network) null);
                    return;
                }
                if (type == 1) {
                    com.getui.gtc.i.c.a.d("in wf.");
                    if (!z) {
                        com.getui.gtc.i.c.a.d("sc dy not allow");
                        a(1, new o(-2, "", ""));
                        return;
                    }
                    try {
                        if (!CommonUtil.hasPermission(iVar.f2094a, "android.permission.CHANGE_NETWORK_STATE", false)) {
                            com.getui.gtc.i.c.a.d("sc dy not allow or no net perm.");
                            a(1, new o(-2, "", ""));
                            return;
                        }
                        if (!i.a(connectivityManager)) {
                            com.getui.gtc.i.c.a.d("sc  cl closed.");
                            a(1, new o(-5, "", ""));
                            return;
                        }
                        com.getui.gtc.i.c.a.d("in wf mode, attempt 2 cl.");
                        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                        NetworkRequest.Builder builder = new NetworkRequest.Builder();
                        builder.addCapability(12);
                        builder.addTransportType(0);
                        NetworkRequest networkRequestBuild = builder.build();
                        final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.getui.gtc.a.a.i.1
                            @Override // android.net.ConnectivityManager.NetworkCallback
                            public final void onAvailable(final Network network) {
                                super.onAvailable(network);
                                com.getui.gtc.i.c.a.d("w2cl su.");
                                if (atomicBoolean.getAndSet(false)) {
                                    ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.a.a.i.1.1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            i.this.a(network);
                                        }
                                    });
                                }
                            }

                            @Override // android.net.ConnectivityManager.NetworkCallback
                            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                                super.onCapabilitiesChanged(network, networkCapabilities);
                            }

                            @Override // android.net.ConnectivityManager.NetworkCallback
                            public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                                super.onLinkPropertiesChanged(network, linkProperties);
                                com.getui.gtc.i.c.a.d("onLPChanged, if name = " + linkProperties.getInterfaceName());
                            }

                            @Override // android.net.ConnectivityManager.NetworkCallback
                            public final void onLosing(Network network, int i2) {
                                super.onLosing(network, i2);
                            }

                            @Override // android.net.ConnectivityManager.NetworkCallback
                            public final void onLost(Network network) {
                                super.onLost(network);
                            }

                            @Override // android.net.ConnectivityManager.NetworkCallback
                            public final void onUnavailable() {
                                super.onUnavailable();
                                com.getui.gtc.i.c.a.d("w2cu f.");
                            }
                        };
                        connectivityManager.requestNetwork(networkRequestBuild, networkCallback);
                        iVar.b = new Runnable() { // from class: com.getui.gtc.a.a.i.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                try {
                                    i.this.b = null;
                                    connectivityManager.unregisterNetworkCallback(networkCallback);
                                } catch (Throwable th) {
                                    com.getui.gtc.i.c.a.b(th);
                                }
                            }
                        };
                        ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.a.a.i.3
                            @Override // java.lang.Runnable
                            public final void run() {
                                Runnable runnable = i.this.b;
                                if (runnable != null) {
                                    runnable.run();
                                }
                            }
                        }, 15000L);
                    } catch (Throwable th) {
                        com.getui.gtc.i.c.a.b(th);
                    }
                }
            } catch (Throwable th2) {
                com.getui.gtc.i.c.a.b(th2);
            }
        } catch (Throwable th3) {
            com.getui.gtc.i.c.a.c("type 305 report error: " + th3.toString());
        }
    }
}
