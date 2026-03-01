package com.baidu.speech.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.speech.utils.cuid.util.DeviceId;

/* JADX INFO: loaded from: classes.dex */
public final class Util {
    public static final boolean DEBUG = false;
    public static final int SDK_ANDROID_M = 23;
    public static final String TAG = "DeviceId";

    public static boolean hasOtherServiceRuninMyPid(Context context, String str) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningServices(100)) {
            if (runningServiceInfo.pid == Process.myPid() && !TextUtils.equals(runningServiceInfo.service.getClassName(), str)) {
                return true;
            }
        }
        return false;
    }

    public static String pfm(Context context) throws SecurityException {
        StringBuilder sb;
        String str;
        boolean zIsUsingWifi = Utility.isUsingWifi(context);
        String strGeneratePlatformString = Utility.generatePlatformString();
        if (zIsUsingWifi) {
            sb = new StringBuilder();
            sb.append(strGeneratePlatformString);
            str = "&1";
        } else {
            sb = new StringBuilder();
            sb.append(strGeneratePlatformString);
            str = "&3";
        }
        sb.append(str);
        String string = sb.toString();
        String cuid = DeviceId.getCUID(context);
        if (TextUtils.isEmpty(cuid)) {
            return string;
        }
        return (string + "&") + cuid;
    }

    public static String toHexString(byte[] bArr, String str, boolean z) {
        return MD5Util.toHexString(bArr, str, z);
    }

    public static String toMd5(byte[] bArr, boolean z) {
        return MD5Util.toMd5(bArr, z);
    }
}
