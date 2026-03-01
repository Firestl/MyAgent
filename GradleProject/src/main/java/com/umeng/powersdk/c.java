package com.umeng.powersdk;

import android.app.Activity;
import android.os.BatteryManager;
import android.os.Build;
import com.efs.sdk.base.core.config.GlobalInfoManager;
import dc.squareup.okhttp3.internal.http2.Http2Codec;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5514a;
    public int b;
    public int c;
    public WeakReference<Activity> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5515e;
    public int f;
    public boolean g;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5520a = new c(0);
    }

    public c() {
        this.f5514a = 1;
        this.b = 0;
        this.c = 0;
        this.f5515e = true;
    }

    public /* synthetic */ c(byte b) {
        this();
    }

    public final JSONObject a() {
        BatteryManager batteryManager;
        JSONObject jSONObject = new JSONObject();
        try {
            if (Build.VERSION.SDK_INT >= 21 && (batteryManager = (BatteryManager) PowerManager.getApplicationContext().getSystemService("batterymanager")) != null) {
                float longProperty = batteryManager.getLongProperty(2);
                if (longProperty >= 10000.0f || longProperty <= -10000.0f) {
                    longProperty /= 1000.0f;
                }
                jSONObject.put("ci", Math.abs(longProperty));
            }
        } catch (Throwable unused) {
        }
        try {
            com.umeng.powersdk.a aVarA = b.a(PowerManager.getApplicationContext()).a();
            jSONObject.put("le", aVarA.f5510a);
            jSONObject.put("vo", aVarA.b);
            jSONObject.put(Http2Codec.TE, aVarA.c);
            jSONObject.put("st", aVarA.d);
            jSONObject.put("ch", aVarA.f5511e);
            jSONObject.put("ts", aVarA.f);
            try {
                long jLongValue = ((Long) GlobalInfoManager.getInstance().getGlobalInfo().getGlobalInfoMap().get("stime")).longValue();
                if (jLongValue > 0) {
                    jSONObject.put("ptime", System.currentTimeMillis() - jLongValue);
                }
            } catch (Throwable unused2) {
            }
            if (this.d != null) {
                jSONObject.put("c_act", this.d.get().getClass().getName());
            }
        } catch (Throwable unused3) {
        }
        return jSONObject;
    }
}
