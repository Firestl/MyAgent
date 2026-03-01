package io.dcloud.common.adapter.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.util.PdrUtil;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AndroidResources {
    public static String appName = null;
    public static PackageInfo mApplicationInfo = null;
    public static Resources mResources = null;
    public static String packageName = null;
    public static int sAppTargetSdkVersion = 0;
    public static AssetManager sAssetMgr = null;
    public static boolean sIMEAlive = false;
    public static Bundle sMetaDatas = null;
    public static boolean splashBacking = false;
    public static int versionCode;
    public static String versionName;

    public static void clearData() {
        CanvasHelper.clearData();
        AdaWebview.clearData();
        mResources = null;
        sAssetMgr = null;
        mApplicationInfo = null;
    }

    public static int getIdentifier(String str, String str2) {
        Resources resources = mResources;
        if (resources != null) {
            return resources.getIdentifier(str, str2, packageName);
        }
        return 0;
    }

    public static int getIdentifierFromApk(Context context, String str, String str2) {
        try {
            return context.createPackageContext(context.getPackageName(), 2).getResources().getIdentifier(str, str2, context.getPackageName());
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String getMetaValue(String str) {
        if (sMetaDatas == null) {
            try {
                sMetaDatas = DeviceInfo.sApplicationContext.getPackageManager().getApplicationInfo(packageName, 128).metaData;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        Bundle bundle = sMetaDatas;
        if (bundle == null || PdrUtil.isEmpty(bundle.get(str))) {
            return null;
        }
        return String.valueOf(sMetaDatas.get(str));
    }

    public static String getString(int i) {
        Resources resources = mResources;
        return resources != null ? resources.getString(i) : "";
    }

    public static void initAndroidResources(Context context) {
        if (mResources != null) {
            return;
        }
        mResources = context.getResources();
        DeviceInfo.sApplicationContext = context;
        sAssetMgr = context.getAssets();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        try {
            sAppTargetSdkVersion = applicationInfo.targetSdkVersion;
            packageName = applicationInfo.packageName;
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 1);
            mApplicationInfo = packageInfo;
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
            appName = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setMetaValue(String str, String str2) {
        if (sMetaDatas == null) {
            try {
                sMetaDatas = DeviceInfo.sApplicationContext.getPackageManager().getApplicationInfo(packageName, 128).metaData;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Bundle bundle = sMetaDatas;
        if (bundle != null) {
            bundle.putString(str, str2);
            System.out.println("meta data = " + sMetaDatas.get(str));
        }
    }
}
