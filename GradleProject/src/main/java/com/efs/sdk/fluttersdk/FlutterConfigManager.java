package com.efs.sdk.fluttersdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import androidtranscoder.format.MediaFormatExtraConstants;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.core.config.GlobalInfoManager;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.observer.IConfigCallback;
import com.efs.sdk.base.samplingwhitelist.SamplingWhiteListUtil;
import com.umeng.analytics.pro.bm;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class FlutterConfigManager {
    public EfsReporter c;
    public Context i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1867a = "FlutterConfigManager";
    public final int b = 0;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1868e = 0;
    public int f = 0;
    public int g = 0;
    public boolean h = false;
    public boolean j = false;

    public FlutterConfigManager(Context context, EfsReporter efsReporter) {
        this.i = context;
        this.c = efsReporter;
        efsReporter.getAllSdkConfig(new String[]{"flutter_pv_sampling_rate", "flutter_pv_max_count", "flutter_dart_exception_state", "flutter_dart_exception_max_count"}, new IConfigCallback() { // from class: com.efs.sdk.fluttersdk.FlutterConfigManager.1
            @Override // com.efs.sdk.base.observer.IConfigCallback
            public final void onChange(Map<String, Object> map) {
                try {
                    Object obj = map.get("flutter_pv_sampling_rate");
                    if (obj != null) {
                        FlutterConfigManager.this.d = Integer.parseInt(obj.toString());
                        FlutterConfigManager.this.h = FlutterConfigManager.a(FlutterConfigManager.this.d);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                try {
                    Object obj2 = map.get("flutter_pv_max_count");
                    if (obj2 != null) {
                        FlutterConfigManager.this.f1868e = Integer.parseInt(obj2.toString());
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                try {
                    Object obj3 = map.get("flutter_dart_exception_state");
                    if (obj3 != null) {
                        FlutterConfigManager.this.f = Integer.parseInt(obj3.toString());
                    }
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                try {
                    Object obj4 = map.get("flutter_dart_exception_max_count");
                    if (obj4 != null) {
                        FlutterConfigManager.this.g = Integer.parseInt(obj4.toString());
                    }
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                FlutterConfigManager.b(FlutterConfigManager.this);
            }
        });
    }

    public static /* synthetic */ boolean a(int i) {
        if (SamplingWhiteListUtil.isHitWL()) {
            return true;
        }
        if (i != 0) {
            return i == 100 || new Random().nextInt(100) <= i;
        }
        return false;
    }

    public static /* synthetic */ boolean b(FlutterConfigManager flutterConfigManager) {
        flutterConfigManager.j = true;
        return true;
    }

    public Map<String, Object> getCloudConfig() {
        if (!this.j) {
            return null;
        }
        HashMap map = new HashMap();
        map.put("flutter_pv_max_count", Integer.valueOf(this.f1868e));
        map.put("flutter_dart_exception_state", Integer.valueOf(this.f));
        map.put("flutter_dart_exception_max_count", Integer.valueOf(this.g));
        map.put("flutter_pv_sampling_hit", Boolean.valueOf(this.h));
        return map;
    }

    public Map<String, Object> getNativeParams() {
        HashMap map;
        long blockSize;
        long blockCount;
        long availableBlocks;
        HashMap map2 = null;
        if (!this.j) {
            return null;
        }
        try {
            map = new HashMap();
        } catch (Throwable unused) {
        }
        try {
            Intent intentRegisterReceiver = this.i.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            map.put(bm.Z, Integer.valueOf(intentRegisterReceiver.getIntExtra(MediaFormatExtraConstants.KEY_LEVEL, 0)));
            map.put("temperature", Integer.valueOf(intentRegisterReceiver.getIntExtra("temperature", 0)));
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                blockCount = statFs.getBlockCountLong() * blockSize;
                availableBlocks = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                blockCount = ((long) statFs.getBlockCount()) * blockSize;
                availableBlocks = statFs.getAvailableBlocks();
            }
            map.put("disk_ratio", String.format(Locale.getDefault(), "%.2f", Double.valueOf(((availableBlocks * blockSize) / blockCount) * 100.0d)));
            map.putAll(GlobalInfoManager.getInstance().getGlobalInfo().getGlobalInfoMap());
            map.putAll(ControllerCenter.getGlobalEnvStruct().getPublicParamMap());
            return map;
        } catch (Throwable unused2) {
            map2 = map;
            return map2;
        }
    }

    public boolean isFlutterEnable() {
        return this.h;
    }
}
