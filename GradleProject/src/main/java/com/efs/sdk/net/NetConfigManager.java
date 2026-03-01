package com.efs.sdk.net;

import android.content.Context;
import android.content.SharedPreferences;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.observer.IConfigCallback;
import com.efs.sdk.base.samplingwhitelist.SamplingWhiteListUtil;
import com.efs.sdk.pa.config.ConfigManager;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class NetConfigManager {
    public EfsReporter c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1889e;
    public int f;
    public int g;
    public int h;
    public int i;
    public boolean j;
    public Context k;
    public int l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1888a = "NetConfigManager";
    public final int b = 0;
    public boolean m = false;

    public NetConfigManager(Context context, EfsReporter efsReporter) {
        int i;
        boolean z;
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorEdit2;
        SharedPreferences.Editor editorEdit3;
        SharedPreferences.Editor editorEdit4;
        SharedPreferences.Editor editorEdit5;
        this.d = 0;
        this.f1889e = 0;
        this.h = 100;
        this.i = 10;
        this.j = false;
        this.l = -1;
        Context applicationContext = context.getApplicationContext();
        this.k = applicationContext;
        this.c = efsReporter;
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("net_launch", 0);
        if (sharedPreferences != null) {
            this.f = sharedPreferences.getInt("apm_netperf_sampling_rate_last", 0);
            this.g = sharedPreferences.getInt("apm_netperf_extra_last", 0);
        }
        SharedPreferences sharedPreferences2 = this.k.getSharedPreferences("net_launch", 0);
        if (sharedPreferences2 != null) {
            i = sharedPreferences2.getInt("apm_netperf_sampling_rate", -1);
            this.f1889e = sharedPreferences2.getInt("apm_netperf_extra", -1);
        } else {
            i = -1;
        }
        this.c.getAllSdkConfig(new String[]{"apm_netperf_sampling_rate", "apm_netperf_day_limit", "apm_netperf_data_rate", "apm_netperf_extra"}, new IConfigCallback() { // from class: com.efs.sdk.net.NetConfigManager.1
            @Override // com.efs.sdk.base.observer.IConfigCallback
            public final void onChange(Map<String, Object> map) {
                SharedPreferences sharedPreferences3;
                final SharedPreferences.Editor editorEdit6;
                SharedPreferences sharedPreferences4;
                final SharedPreferences.Editor editorEdit7;
                try {
                    final Object obj = map.get("apm_netperf_sampling_rate");
                    if (obj != null && (sharedPreferences4 = NetConfigManager.this.k.getSharedPreferences("net_launch", 0)) != null && (editorEdit7 = sharedPreferences4.edit()) != null) {
                        new Thread(new Runnable() { // from class: com.efs.sdk.net.NetConfigManager.1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                editorEdit7.putInt("apm_netperf_sampling_rate", Integer.parseInt(obj.toString()));
                                editorEdit7.commit();
                            }
                        }).start();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                try {
                    final Object obj2 = map.get("apm_netperf_extra");
                    if (obj2 != null && (sharedPreferences3 = NetConfigManager.this.k.getSharedPreferences("net_launch", 0)) != null && (editorEdit6 = sharedPreferences3.edit()) != null) {
                        new Thread(new Runnable() { // from class: com.efs.sdk.net.NetConfigManager.1.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                editorEdit6.putInt("apm_netperf_extra", Integer.parseInt(obj2.toString()));
                                editorEdit6.commit();
                            }
                        }).start();
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                try {
                    NetConfigManager.this.h = Integer.parseInt(map.get("apm_netperf_day_limit").toString());
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                try {
                    Object obj3 = map.get("apm_netperf_data_rate");
                    if (obj3 != null) {
                        NetConfigManager.this.i = Integer.parseInt(obj3.toString());
                    }
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        });
        if (i != -1) {
            this.d = i;
        }
        if (SamplingWhiteListUtil.isHitWL()) {
            z = true;
        } else {
            SharedPreferences sharedPreferences3 = this.k.getSharedPreferences("net_launch", 0);
            long j = sharedPreferences3 != null ? sharedPreferences3.getLong(ConfigManager.FLAG_PA_FORE_CHECK_TIME, 0L) : 0L;
            boolean z2 = sharedPreferences3 != null ? sharedPreferences3.getBoolean(ConfigManager.FLAG_PA_CHECK_IN_STATE, false) : false;
            int i2 = this.d;
            if (i2 == 0) {
                if (z2 && sharedPreferences3 != null && (editorEdit4 = sharedPreferences3.edit()) != null) {
                    editorEdit4.putBoolean(ConfigManager.FLAG_PA_CHECK_IN_STATE, false);
                    editorEdit4.commit();
                }
                if (j != 0 && sharedPreferences3 != null && (editorEdit3 = sharedPreferences3.edit()) != null) {
                    editorEdit3.putLong(ConfigManager.FLAG_PA_FORE_CHECK_TIME, 0L);
                    editorEdit3.commit();
                }
            } else {
                boolean z3 = Math.max(i2, this.f1889e) != Math.max(this.f, this.g);
                Long lValueOf = Long.valueOf(j);
                int iMax = Math.max(this.d, this.f1889e);
                Long lValueOf2 = Long.valueOf(System.currentTimeMillis());
                Long lValueOf3 = Long.valueOf(lValueOf2.longValue() - lValueOf.longValue());
                if (z2 && lValueOf3.longValue() < 86400000 && !z3) {
                    Log.d("NetConfigManager", " check in allready");
                    z = true;
                } else if (lValueOf3.longValue() >= 86400000 || z3) {
                    if (a(iMax)) {
                        Log.d("NetConfigManager", "random check in");
                        z = true;
                    } else {
                        Log.d("NetConfigManager", "random not check in!");
                        z = false;
                    }
                    SharedPreferences sharedPreferences4 = this.k.getSharedPreferences("net_launch", 0);
                    if (sharedPreferences4 != null && (editorEdit2 = sharedPreferences4.edit()) != null) {
                        editorEdit2.putBoolean(ConfigManager.FLAG_PA_CHECK_IN_STATE, z);
                        editorEdit2.commit();
                    }
                    if (sharedPreferences4 != null && (editorEdit = sharedPreferences4.edit()) != null) {
                        editorEdit.putLong(ConfigManager.FLAG_PA_FORE_CHECK_TIME, lValueOf2.longValue());
                        editorEdit.commit();
                    }
                } else {
                    Log.d("NetConfigManager", "un repeat check in 24 hour!");
                }
            }
            z = false;
        }
        this.j = z;
        SharedPreferences sharedPreferences5 = this.k.getSharedPreferences("net_launch", 0);
        if (sharedPreferences5 != null && (editorEdit5 = sharedPreferences5.edit()) != null) {
            editorEdit5.putInt("apm_netperf_sampling_rate_last", this.d);
            editorEdit5.putInt("apm_netperf_extra_last", this.f1889e);
            editorEdit5.commit();
        }
        SharedPreferences sharedPreferences6 = this.k.getSharedPreferences("net_launch", 0);
        if (sharedPreferences6 != null) {
            this.h = sharedPreferences6.getInt("apm_netperf_day_limit", 0);
            this.i = sharedPreferences6.getInt("apm_netperf_data_rate", 0);
        }
        int i3 = this.d;
        int i4 = this.f1889e;
        if (i3 >= i4) {
            this.l = 0;
            return;
        }
        if (i4 == 0) {
            this.l = 0;
        } else if (a((i3 * 100) / i4)) {
            this.l = 0;
        } else {
            this.l = 1;
        }
    }

    public boolean enableTracer() {
        return this.j;
    }

    public int getDataRate() {
        return this.i;
    }

    public int getDayLimit() {
        return this.h;
    }

    public int getExtraRateFlag() {
        return this.l;
    }

    public boolean getNetRequestBodyCollectState() {
        return this.m;
    }

    public void setNetRequestBodyCollectState(boolean z) {
        this.m = z;
    }

    public static boolean a(int i) {
        if (i == 0) {
            return false;
        }
        return i == 100 || new Random().nextInt(100) <= i;
    }
}
