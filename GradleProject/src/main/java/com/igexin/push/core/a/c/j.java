package com.igexin.push.core.a.c;

import android.os.Bundle;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3331a = "ReportCidAction";
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3332e = 51688;
    public static final int f = 180000;
    public static j i;
    public Long g;
    public ServerSocket h;

    public static j a() {
        if (i == null) {
            i = new j();
        }
        return i;
    }

    public final void a(boolean z) {
        JSONArray jSONArray;
        if (z) {
            try {
                if (com.igexin.push.core.e.p && com.igexin.push.core.e.s) {
                    int i2 = 0;
                    try {
                        if (this.h == null) {
                            this.h = new ServerSocket(f3332e);
                        }
                    } catch (Exception unused) {
                        com.igexin.c.a.c.a.a("ReportCidAction|port 51688 has occupy by others", new Object[0]);
                    }
                    if (this.h != null) {
                        if (com.igexin.push.core.e.aG < 180000) {
                            com.igexin.push.core.e.aG = 180000L;
                        }
                        if (com.igexin.push.core.e.aF < 180000) {
                            com.igexin.push.core.e.aF = 180000L;
                        }
                        if (this.g == null) {
                            long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.aH;
                            if (jCurrentTimeMillis < com.igexin.push.core.e.aG) {
                                com.igexin.c.a.c.a.a("ReportCidAction|lastReportInterval < reportCidRestartThreshold not report", new Object[0]);
                                return;
                            } else if (jCurrentTimeMillis < com.igexin.push.core.e.aF) {
                                i2 = 2;
                            }
                        } else {
                            if (System.currentTimeMillis() - this.g.longValue() < com.igexin.push.core.e.aF) {
                                com.igexin.c.a.c.a.a("ReportCidAction|offline time < reportCidOfflineThreshold not report", new Object[0]);
                                return;
                            }
                            i2 = 1;
                        }
                        List<JSONObject> listH = com.igexin.push.g.j.h();
                        if (listH == null) {
                            jSONArray = new JSONArray();
                        } else if (listH.size() <= 0) {
                            return;
                        } else {
                            jSONArray = new JSONArray((Collection) listH);
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("appinfo", jSONArray);
                        jSONObject.put("deviceid", "ANDROID-" + com.igexin.push.core.e.H);
                        jSONObject.put("type", i2);
                        jSONObject.put("pkg", com.igexin.push.core.e.l.getPackageName());
                        Bundle bundle = new Bundle();
                        bundle.putString("action", "sendMessage");
                        StringBuilder sb = new StringBuilder(com.igexin.push.core.b.X);
                        sb.append(com.igexin.c.b.a.b(com.igexin.push.core.e.A + System.currentTimeMillis()));
                        bundle.putString("taskid", sb.toString());
                        bundle.putByteArray("extraData", jSONObject.toString().getBytes());
                        com.igexin.push.core.a.b.d();
                        com.igexin.push.core.a.b.a(bundle);
                        com.igexin.push.config.a.a().a(System.currentTimeMillis());
                    }
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
                return;
            }
        }
        if (z) {
            return;
        }
        this.g = Long.valueOf(System.currentTimeMillis());
    }
}
