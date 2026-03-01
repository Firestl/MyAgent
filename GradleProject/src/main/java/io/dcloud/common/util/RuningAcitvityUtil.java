package io.dcloud.common.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import io.dcloud.application.DCLoudApplicationImpl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RuningAcitvityUtil {
    public static HashMap<String, Activity> RuningActivitys = new HashMap<>();
    public static boolean isRuningActivity = false;

    public static void StartActivity(Context context, Intent intent) {
        Intent intent2 = new Intent(intent);
        intent2.setAction("android.intent.action.VIEW");
        context.startActivity(intent2);
    }

    public static String getAppName(Context context) {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
            return context.getPackageName();
        }
        String str = "";
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid && !TextUtils.isEmpty(runningAppProcessInfo.processName)) {
                str = runningAppProcessInfo.processName;
            }
        }
        return str;
    }

    public static Activity getTopRuningActivity(Activity activity) {
        Activity activity2;
        ActivityManager.RunningTaskInfo runningTaskInfo = ((ActivityManager) activity.getSystemService("activity")).getRunningTasks(1).get(0);
        return RuningActivitys.containsKey(runningTaskInfo.topActivity.getClassName()) ? RuningActivitys.get(runningTaskInfo.topActivity.getClassName()) : (!"com.g.gysdk.view.ELoginActivity".equals(runningTaskInfo.topActivity.getClassName()) || (activity2 = DCLoudApplicationImpl.self().topActiveMap.get(runningTaskInfo.topActivity.getClassName()).get()) == null) ? activity : activity2;
    }

    public static boolean isRunningProcess(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null && !TextUtils.isEmpty(str) && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().processName, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void putRuningActivity(Activity activity) {
        if (RuningActivitys.containsValue(activity)) {
            return;
        }
        RuningActivitys.put(activity.getComponentName().getClassName(), activity);
    }

    public static void removeRuningActivity(String str) {
        if (RuningActivitys.containsKey(str)) {
            RuningActivitys.remove(str);
        }
    }
}
