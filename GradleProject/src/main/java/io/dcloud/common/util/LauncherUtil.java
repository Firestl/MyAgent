package io.dcloud.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class LauncherUtil {
    public static String getAuthorityFromPermission(Context context, String str) {
        TextUtils.isEmpty(str);
        return "";
    }

    public static String getAuthorityFromPermissionDefault(Context context) {
        String authorityFromPermission = getAuthorityFromPermission(context, "com.android.launcher.permission.READ_SETTINGS");
        return TextUtils.isEmpty(authorityFromPermission) ? Build.VERSION.SDK_INT < 19 ? getAuthorityFromPermission(context, "com.android.launcher2.permission.READ_SETTINGS") : getAuthorityFromPermission(context, "com.android.launcher3.permission.READ_SETTINGS") : authorityFromPermission;
    }

    public static String getLauncherPackageName(Context context) {
        ActivityInfo activityInfo;
        Context applicationContext = context.getApplicationContext();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = applicationContext.getPackageManager().resolveActivity(intent, 0);
        return (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null || activityInfo.packageName.equals("android")) ? "" : resolveInfoResolveActivity.activityInfo.packageName;
    }
}
