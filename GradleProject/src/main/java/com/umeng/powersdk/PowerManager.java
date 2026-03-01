package com.umeng.powersdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.protocol.record.EfsJSONLog;
import com.umeng.powersdk.c;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class PowerManager {
    public static final String TAG = "PowerManager";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f5509a = null;
    public static boolean b = false;
    public static EfsReporter c = null;
    public static PowerConfigManager d = null;
    public static boolean isDebug = true;

    public static Context getApplicationContext() {
        return f5509a;
    }

    public static PowerConfigManager getPowerConfigManager() {
        return d;
    }

    public static EfsReporter getReporter() {
        return c;
    }

    public static void init(Context context, EfsReporter efsReporter) {
        if (context == null || efsReporter == null) {
            try {
                if (isDebug) {
                    Log.e(TAG, "init power manager error! parameter is null!");
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        if (isInit()) {
            if (isDebug) {
                Log.e(TAG, "invalid init ！");
                return;
            }
            return;
        }
        f5509a = context.getApplicationContext();
        c = efsReporter;
        d = new PowerConfigManager(context, efsReporter);
        b = true;
        final c cVar = c.a.f5520a;
        try {
            if (getPowerConfigManager() == null || !getPowerConfigManager().enableTracer()) {
                return;
            }
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("efs_power", 0);
            if (sharedPreferences != null) {
                cVar.b = sharedPreferences.getInt("apm_powerperf_collect_interval", 5);
                cVar.c = sharedPreferences.getInt("apm_powerperf_collect_max_period_sec", 100);
            }
            final HandlerThread handlerThread = new HandlerThread("power-info");
            handlerThread.start();
            final Handler handler = new Handler(handlerThread.getLooper()) { // from class: com.umeng.powersdk.c.1
                @Override // android.os.Handler
                public final void handleMessage(Message message) {
                    super.handleMessage(message);
                    if (message.what == c.this.f5514a) {
                        try {
                            handlerThread.quit();
                        } catch (Throwable unused) {
                        }
                    }
                }
            };
            handler.post(new Runnable() { // from class: com.umeng.powersdk.c.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        final c cVar2 = c.this;
                        final Handler handler2 = handler;
                        final int i = c.this.b;
                        final int i2 = c.this.c;
                        final long jElapsedRealtime = SystemClock.elapsedRealtime();
                        handler2.post(new Runnable() { // from class: com.umeng.powersdk.c.3
                            @Override // java.lang.Runnable
                            public final void run() {
                                if (c.this.f5515e) {
                                    if (SystemClock.elapsedRealtime() - jElapsedRealtime > i2 * 1000) {
                                        handler2.sendEmptyMessage(c.this.f5514a);
                                        return;
                                    }
                                    try {
                                        EfsJSONLog efsJSONLog = new EfsJSONLog("powerperf");
                                        efsJSONLog.put("power", c.this.a());
                                        EfsReporter reporter = PowerManager.getReporter();
                                        if (reporter != null) {
                                            reporter.send(efsJSONLog);
                                        }
                                    } catch (Throwable unused) {
                                    }
                                }
                                handler2.postDelayed(this, i * 1000);
                            }
                        });
                    } catch (Throwable unused) {
                        handler.sendEmptyMessage(c.this.f5514a);
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    public static boolean isInit() {
        return b;
    }

    public static void onActivityResumed(Activity activity) {
        c cVar = c.a.f5520a;
        try {
            if (getPowerConfigManager() != null && getPowerConfigManager().enableTracer()) {
                cVar.d = new WeakReference<>(activity);
            }
        } catch (Throwable unused) {
        }
    }

    public static void onActivityStarted(Activity activity) {
        c cVar = c.a.f5520a;
        try {
            if (getPowerConfigManager() == null || !getPowerConfigManager().enableTracer() || activity == null) {
                return;
            }
            if (cVar.g) {
                cVar.g = false;
                return;
            }
            int i = cVar.f + 1;
            cVar.f = i;
            if (i == 1) {
                cVar.f5515e = true;
            }
        } catch (Throwable unused) {
        }
    }

    public static void onActivityStopped(Activity activity) {
        c cVar = c.a.f5520a;
        try {
            if (getPowerConfigManager() == null || !getPowerConfigManager().enableTracer() || activity == null) {
                return;
            }
            if (activity.isChangingConfigurations()) {
                cVar.g = true;
                return;
            }
            int i = cVar.f - 1;
            cVar.f = i;
            if (i == 0) {
                cVar.f5515e = false;
            }
        } catch (Throwable unused) {
        }
    }
}
