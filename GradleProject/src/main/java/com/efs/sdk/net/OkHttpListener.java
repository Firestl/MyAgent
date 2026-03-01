package com.efs.sdk.net;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.core.util.ProcessUtil;
import com.efs.sdk.base.integrationtesting.IntegrationTestingUtil;
import com.efs.sdk.base.protocol.record.EfsJSONLog;
import com.efs.sdk.base.samplingwhitelist.SamplingWhiteListUtil;
import com.efs.sdk.net.a.a;
import com.efs.sdk.net.a.b;
import com.efs.sdk.net.a.c;
import com.efs.sdk.net.a.d;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.gs1;
import supwisdom.ks1;
import supwisdom.rs1;
import supwisdom.ts1;

/* JADX INFO: loaded from: classes.dex */
public class OkHttpListener extends rs1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicInteger f1897a = new AtomicInteger(0);
    public String b;
    public boolean c;
    public List d = new ArrayList();

    private void a(String str) {
        Map<String, Long> map;
        try {
            d dVarC = a.a().c(this.b);
            if (dVarC == null || (map = dVarC.E) == null) {
                return;
            }
            map.put(str, Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b() {
        try {
            final d dVarC = a.a().c(this.b);
            final c cVarA = a.a().a(this.b);
            if (dVarC == null || cVarA == null) {
                return;
            }
            Map<String, Long> map = dVarC.E;
            Map<String, Long> map2 = dVarC.F;
            Log.i("NetTrace-Listener", cVarA.toString());
            if (TextUtils.isEmpty(dVarC.B)) {
                Log.d("NetTrace-Listener", "url is null.");
                return;
            }
            final EfsJSONLog efsJSONLog = new EfsJSONLog("netperf");
            if (map.containsKey(d.d)) {
                efsJSONLog.put("wd_dns", map.get(d.d));
            }
            if (map.containsKey(d.f1912e)) {
                efsJSONLog.put("wd_dnstm", map.get(d.f1912e));
            }
            if (map2.containsKey(d.t)) {
                efsJSONLog.put("wl_dns", map2.get(d.t));
            }
            if (map.containsKey(d.f)) {
                efsJSONLog.put("wd_tcp", map.get(d.f));
            }
            if (map.containsKey(d.i)) {
                efsJSONLog.put("wd_tcptm", map.get(d.i));
            }
            if (map2.containsKey(d.v)) {
                efsJSONLog.put("wl_tcp", map2.get(d.v));
            }
            if (map.containsKey(d.g)) {
                efsJSONLog.put("wd_ssl", map.get(d.g));
            }
            if (map.containsKey(d.h)) {
                efsJSONLog.put("wd_ssltm", map.get(d.h));
            }
            if (map2.containsKey(d.u)) {
                efsJSONLog.put("wl_ssl", map2.get(d.u));
            }
            if (map.containsKey(d.k)) {
                efsJSONLog.put("wd_ds", map.get(d.k));
            }
            if (map.containsKey(d.n)) {
                efsJSONLog.put("wd_dstm", map.get(d.n));
            }
            if (map2.containsKey(d.w) && map2.containsKey(d.x)) {
                efsJSONLog.put("wl_ds", Long.valueOf(map2.get(d.w).longValue() + map2.get(d.x).longValue()));
            }
            if (map.containsKey(d.o)) {
                efsJSONLog.put("wd_srt", map.get(d.o));
            }
            if (map.containsKey(d.r)) {
                efsJSONLog.put("wd_srttm", map.get(d.r));
            }
            if (map2.containsKey(d.y) && map2.containsKey(d.z)) {
                efsJSONLog.put("wl_srt", Long.valueOf(map2.get(d.y).longValue() + map2.get(d.z).longValue()));
            }
            String[] strArrSplit = dVarC.B.split("\\?");
            String str = strArrSplit != null ? strArrSplit[0] : null;
            if (this.d == null || str == null || this.d.contains(str)) {
                efsJSONLog.put("wd_ttfb", 0);
                efsJSONLog.put("wd_ttfbtm", 0);
                efsJSONLog.put("wl_ttfb", 0);
            } else {
                this.d.add(str);
                if (map.containsKey(d.n)) {
                    efsJSONLog.put("wd_ttfb", map.get(d.n));
                } else if (map.containsKey(d.l)) {
                    efsJSONLog.put("wd_ttfb", map.get(d.l));
                }
                if (map.containsKey(d.o)) {
                    efsJSONLog.put("wd_ttfbtm", map.get(d.o));
                }
                if (map.containsKey(d.o)) {
                    if (map.containsKey(d.n)) {
                        efsJSONLog.put("wl_ttfb", Long.valueOf(map.get(d.o).longValue() - map.get(d.n).longValue()));
                    } else if (map.containsKey(d.l)) {
                        efsJSONLog.put("wl_ttfb", Long.valueOf(map.get(d.o).longValue() - map.get(d.l).longValue()));
                    }
                }
            }
            if (map.containsKey(d.f1911a)) {
                efsJSONLog.put("wd_rt", map.get(d.f1911a));
            }
            if (map.containsKey(d.b)) {
                efsJSONLog.put("wd_rttm", map.get(d.b));
            }
            if (map2.containsKey(d.s)) {
                efsJSONLog.put("wl_rt", map2.get(d.s));
            }
            efsJSONLog.put("wk_res", dVarC.B);
            efsJSONLog.put("wk_ip", dVarC.C);
            efsJSONLog.put("wk_method", cVarA.f1910e);
            efsJSONLog.put("wk_rc", Integer.valueOf(cVarA.h));
            efsJSONLog.put("wl_up", Long.valueOf(cVarA.f));
            efsJSONLog.put("wl_down", Long.valueOf(cVarA.j));
            efsJSONLog.put("wl_total", Long.valueOf(cVarA.f + cVarA.j));
            b.a(new Runnable() { // from class: com.efs.sdk.net.OkHttpListener.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        try {
                            if (NetManager.getNetConfigManager().getNetRequestBodyCollectState() && !TextUtils.isEmpty(cVarA.g)) {
                                efsJSONLog.put("wk_bd", com.efs.sdk.net.b.a.a(com.efs.sdk.net.b.a.a(cVarA.g.getBytes(), com.efs.sdk.net.b.a.a((dVarC.E.containsKey(d.f1911a) ? String.valueOf(dVarC.E.get(d.f1911a)) : "") + ControllerCenter.getGlobalEnvStruct().getAppid() + ControllerCenter.getGlobalEnvStruct().getSecret()).getBytes())));
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        OkHttpListener.a(dVarC, cVarA, efsJSONLog);
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            });
            a.a().d(this.b);
            a.a().b(this.b);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static rs1.c get() {
        return new rs1.c() { // from class: com.efs.sdk.net.OkHttpListener.1
            @Override // supwisdom.rs1.c
            @NotNull
            public final rs1 create(@NotNull gs1 gs1Var) {
                return new OkHttpListener();
            }
        };
    }

    @Override // supwisdom.rs1
    public void callEnd(@NotNull gs1 gs1Var) {
        super.callEnd(gs1Var);
        try {
            Log.d("NetTrace-Listener", "callEnd");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "callEnd net enable false.");
                return;
            }
            a(d.b);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void callFailed(@NotNull gs1 gs1Var, @NotNull IOException iOException) {
        super.callFailed(gs1Var, iOException);
        try {
            Log.d("NetTrace-Listener", "callFailed");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "callFailed net enable false.");
                return;
            }
            a(d.c);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void callStart(@NotNull gs1 gs1Var) {
        super.callStart(gs1Var);
        try {
            Log.d("NetTrace-Listener", "callStart");
            if (NetManager.getNetConfigManager() != null && NetManager.getNetConfigManager().enableTracer()) {
                this.c = true;
            }
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "callStart net enable false.");
                return;
            }
            this.b = String.valueOf(f1897a.getAndIncrement());
            Log.i("NetTrace-Listener", "requestId is" + this.b);
            a(d.f1911a);
            String string = gs1Var.request().g().toString();
            try {
                d dVarC = a.a().c(this.b);
                if (dVarC != null) {
                    dVarC.B = string;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void connectEnd(@NotNull gs1 gs1Var, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol) {
        super.connectEnd(gs1Var, inetSocketAddress, proxy, protocol);
        try {
            Log.d("NetTrace-Listener", "connectEnd");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "connectEnd net enable false.");
                return;
            }
            a(d.i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void connectFailed(@NotNull gs1 gs1Var, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, @NotNull IOException iOException) {
        super.connectFailed(gs1Var, inetSocketAddress, proxy, protocol, iOException);
        try {
            Log.d("NetTrace-Listener", "connectFailed");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "connectFailed net enable false.");
                return;
            }
            a(d.j);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void connectStart(@NotNull gs1 gs1Var, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy) {
        super.connectStart(gs1Var, inetSocketAddress, proxy);
        try {
            Log.d("NetTrace-Listener", "connectStart");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "connectStart net enable false.");
                return;
            }
            a(d.f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void connectionAcquired(@NotNull gs1 gs1Var, @NotNull ks1 ks1Var) {
        super.connectionAcquired(gs1Var, ks1Var);
        try {
            Log.d("NetTrace-Listener", "connectionAcquired");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "callStart net enable false.");
                return;
            }
            InetAddress inetAddress = ks1Var.socket().getInetAddress();
            if (inetAddress != null) {
                String hostAddress = inetAddress.getHostAddress();
                try {
                    d dVarC = a.a().c(this.b);
                    if (dVarC != null) {
                        dVarC.C = hostAddress;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void dnsEnd(@NotNull gs1 gs1Var, @NotNull String str, @NotNull List<InetAddress> list) {
        super.dnsEnd(gs1Var, str, list);
        try {
            Log.d("NetTrace-Listener", "dnsEnd");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "dnsEnd net enable false.");
                return;
            }
            a(d.f1912e);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void dnsStart(@NotNull gs1 gs1Var, @NotNull String str) {
        super.dnsStart(gs1Var, str);
        try {
            Log.d("NetTrace-Listener", "dnsStart");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "dnsStart net enable false.");
                return;
            }
            a(d.d);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void requestBodyEnd(@NotNull gs1 gs1Var, long j) {
        super.requestBodyEnd(gs1Var, j);
        try {
            Log.d("NetTrace-Listener", "requestBodyEnd");
            gs1Var.request().a();
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "requestBodyEnd net enable false.");
                return;
            }
            a(d.n);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void requestBodyStart(@NotNull gs1 gs1Var) {
        super.requestBodyStart(gs1Var);
        try {
            Log.d("NetTrace-Listener", "requestBodyStart");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "requestBodyStart net enable false.");
                return;
            }
            a(d.m);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void requestHeadersEnd(@NotNull gs1 gs1Var, @NotNull bt1 bt1Var) {
        super.requestHeadersEnd(gs1Var, bt1Var);
        try {
            Log.d("NetTrace-Listener", "requestHeadersEnd");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "requestHeadersEnd net enable false.");
                return;
            }
            a(d.l);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void requestHeadersStart(@NotNull gs1 gs1Var) {
        super.requestHeadersStart(gs1Var);
        try {
            Log.d("NetTrace-Listener", "requestHeadersStart");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "requestHeadersStart net enable false.");
                return;
            }
            a(d.k);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void responseBodyEnd(@NotNull gs1 gs1Var, long j) {
        super.responseBodyEnd(gs1Var, j);
        try {
            Log.d("NetTrace-Listener", "responseBodyEnd");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "responseBodyEnd net enable false.");
                return;
            }
            a(d.r);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void responseBodyStart(@NotNull gs1 gs1Var) {
        super.responseBodyStart(gs1Var);
        try {
            Log.d("NetTrace-Listener", "responseBodyStart");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "responseBodyStart net enable false.");
                return;
            }
            a(d.q);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void responseHeadersEnd(@NotNull gs1 gs1Var, @NotNull dt1 dt1Var) {
        super.responseHeadersEnd(gs1Var, dt1Var);
        try {
            Log.d("NetTrace-Listener", "responseHeadersEnd");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "responseHeadersEnd net enable false.");
                return;
            }
            a(d.p);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void responseHeadersStart(@NotNull gs1 gs1Var) {
        super.responseHeadersStart(gs1Var);
        try {
            Log.d("NetTrace-Listener", "responseHeadersStart");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "responseHeadersStart net enable false.");
                return;
            }
            a(d.o);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void secureConnectEnd(@NotNull gs1 gs1Var, @Nullable ts1 ts1Var) {
        super.secureConnectEnd(gs1Var, ts1Var);
        try {
            Log.d("NetTrace-Listener", "secureConnectEnd");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "secureConnectEnd net enable false.");
                return;
            }
            a(d.h);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // supwisdom.rs1
    public void secureConnectStart(@NotNull gs1 gs1Var) {
        super.secureConnectStart(gs1Var);
        try {
            Log.d("NetTrace-Listener", "secureConnectStart");
            if (!this.c && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
                Log.d("NetTrace-Listener", "secureConnectStart net enable false.");
                return;
            }
            a(d.g);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() {
        try {
            d dVarC = a.a().c(this.b);
            if (dVarC != null) {
                Map<String, Long> map = dVarC.E;
                Map<String, Long> map2 = dVarC.F;
                map2.put(d.s, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.f1911a, d.b)));
                map2.put(d.t, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.d, d.f1912e)));
                map2.put(d.u, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.g, d.h)));
                map2.put(d.v, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.f, d.i)));
                map2.put(d.w, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.k, d.l)));
                map2.put(d.x, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.m, d.n)));
                map2.put(d.y, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.o, d.p)));
                map2.put(d.z, Long.valueOf(com.efs.sdk.net.b.a.a(map, d.q, d.r)));
                b();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(EfsJSONLog efsJSONLog) {
        try {
            EfsReporter reporter = NetManager.getReporter();
            if (reporter != null) {
                reporter.send(efsJSONLog);
                if (SamplingWhiteListUtil.isHitWL()) {
                    return;
                }
                SharedPreferences sharedPreferences = ControllerCenter.getGlobalEnvStruct().mAppContext.getSharedPreferences("net_launch" + ProcessUtil.getCurrentProcessName(), 0);
                String str = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date(System.currentTimeMillis()));
                if (sharedPreferences != null) {
                    int i = sharedPreferences.getInt(str, 0);
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    if (editorEdit != null) {
                        editorEdit.putInt(str, i + 1);
                        editorEdit.apply();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static String a(Map<String, String> map, boolean z, boolean z2) {
        try {
            StringBuilder sb = new StringBuilder();
            if (!SamplingWhiteListUtil.isHitWL()) {
                sb.append("0");
            } else {
                sb.append("1");
            }
            sb.append("|");
            sb.append(NetManager.getNetConfigManager().getExtraRateFlag());
            if (map.size() != 0 && !z && z2) {
                sb.append("|1");
            } else {
                sb.append("|0");
            }
            sb.append("|");
            sb.append(new JSONObject(map).toString());
            return com.efs.sdk.net.b.a.a(com.efs.sdk.net.b.a.a(sb.toString().getBytes(), ControllerCenter.getGlobalEnvStruct().getSecret().getBytes()));
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(com.efs.sdk.net.a.d r16, com.efs.sdk.net.a.c r17, com.efs.sdk.base.protocol.record.EfsJSONLog r18) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.efs.sdk.net.OkHttpListener.a(com.efs.sdk.net.a.d, com.efs.sdk.net.a.c, com.efs.sdk.base.protocol.record.EfsJSONLog):void");
    }
}
