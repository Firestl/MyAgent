package io.src.dcloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import io.dcloud.PdrR;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.util.BaseInfo;

/* JADX INFO: loaded from: classes3.dex */
public class DCloudAdapterUtil implements IReflectAble {
    public static void Plugin2Host_closeAppStreamSplash(String str) {
    }

    public static void Plugin2Host_finishActivity(String str) {
    }

    public static String getDcloudDownloadService() {
        return "io.dcloud.streamdownload.DownloadService";
    }

    public static Class<?> getDownloadServiceClass() {
        try {
            return Class.forName(getDcloudDownloadService());
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static IActivityHandler getIActivityHandler(Activity activity) {
        if (activity instanceof IActivityHandler) {
            return (IActivityHandler) activity;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static IOnCreateSplashView getIOnCreateSplashView(Activity activity) {
        if (activity instanceof IOnCreateSplashView) {
            return (IOnCreateSplashView) activity;
        }
        return null;
    }

    public static int getImageOnLoadingId(Context context) {
        return PdrR.STREAMAPP_DRAWABLE_APPDEFULTICON;
    }

    public static String getPageName() {
        return "";
    }

    public static String getRuntimeJsPath() {
        return BaseInfo.sRuntimeJsPath;
    }

    public static SharedPreferences getSharedPreferences(Context context, String str, int i) {
        return null;
    }

    public static boolean isAutoCreateShortCut(Context context) {
        return true;
    }

    public static boolean isPlugin() {
        return false;
    }
}
