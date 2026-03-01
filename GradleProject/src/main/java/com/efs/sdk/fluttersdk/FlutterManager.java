package com.efs.sdk.fluttersdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.core.util.Log;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FlutterManager {
    public static final String TAG = "FlutterManager";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static EfsReporter f1870a = null;
    public static FlutterConfigManager b = null;
    public static Context c = null;
    public static boolean isDebug = true;

    public static Map<String, Object> getCloudConfig() {
        FlutterConfigManager flutterConfigManager = b;
        if (flutterConfigManager != null) {
            return flutterConfigManager.getCloudConfig();
        }
        return null;
    }

    public static FlutterConfigManager getFlutterConfigManager() {
        return b;
    }

    public static long getLongValue(String str) {
        Context context = c;
        if (context == null) {
            Log.e(TAG, "Flutter Manager not init!");
            return 0L;
        }
        try {
            return context.getSharedPreferences("efs_flutter_bridge", 0).getLong(str, 0L);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static Map<String, Object> getNativeParams() {
        FlutterConfigManager flutterConfigManager = b;
        if (flutterConfigManager != null) {
            return flutterConfigManager.getNativeParams();
        }
        return null;
    }

    public static EfsReporter getReporter() {
        return f1870a;
    }

    public static void init(Context context, EfsReporter efsReporter) {
        if (context == null || efsReporter == null) {
            Log.e(TAG, "init Flutter manager error! parameter is null!");
            return;
        }
        c = context;
        f1870a = efsReporter;
        b = new FlutterConfigManager(context, efsReporter);
    }

    public static boolean putLongValue(String str, long j) {
        Context context = c;
        if (context == null) {
            Log.e(TAG, "Flutter Manager not init!");
            return false;
        }
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("efs_flutter_bridge", 0).edit();
            editorEdit.putLong(str, j);
            editorEdit.apply();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
