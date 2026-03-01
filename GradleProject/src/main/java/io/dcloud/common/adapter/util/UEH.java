package io.dcloud.common.adapter.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.constant.StringConst;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.NetworkTypeUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.TelephonyUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.common.util.net.NetWork;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class UEH {
    public static final String CRASH_DIRECTORY = "crash/";
    public static final boolean SAVE_CRASH_LOG = true;
    public static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINESE);
    public static boolean sInited = false;

    public static void catchUncaughtException(final Context context) {
        if (sInited) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: io.dcloud.common.adapter.util.UEH.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                UEH.handleUncaughtException(context, th);
                try {
                    if (BaseInfo.getCmitInfo(BaseInfo.sLastRunApp).rptCrs) {
                        UEH.commitUncatchException(context, th);
                    }
                    Thread.sleep(3000L);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Logger.e("UncaughtExceptionHandler", "commitUncatchException");
                }
                th.printStackTrace();
                Logger.e("UncaughtExceptionHandler", th.toString());
                Process.killProcess(Process.myPid());
            }
        });
        sInited = true;
    }

    public static void commitBaseUncatchInfo(Context context, StringBuffer stringBuffer) {
        String strEncode;
        String imei = TelephonyUtil.getIMEI(context);
        int networkType = NetworkTypeUtil.getNetworkType(context);
        try {
            strEncode = URLEncoder.encode(Build.MODEL, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            strEncode = null;
        }
        int i = Build.VERSION.SDK_INT;
        stringBuffer.append("s=99");
        stringBuffer.append("&imei=" + imei);
        stringBuffer.append("&md=" + strEncode);
        stringBuffer.append("&os=" + i);
        stringBuffer.append("&appid=" + BaseInfo.sLastRunApp);
        stringBuffer.append("&net=" + networkType);
        stringBuffer.append("&vb=1.9.9.81096");
        stringBuffer.append("&appcount=" + BaseInfo.s_Runing_App_Count);
        stringBuffer.append("&wvcount=" + BaseInfo.s_Webview_Count);
        stringBuffer.append("&pn=" + context.getPackageName());
        stringBuffer.append("&mem=" + getAppUseMem(context));
        stringBuffer.append("&vd=" + PdrUtil.encodeURL(Build.MANUFACTURER));
    }

    public static void commitErrorLog(final String str) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.adapter.util.UEH.2
            @Override // java.lang.Runnable
            public void run() {
                HashMap map = new HashMap();
                map.put("Content-Type", NetWork.CONTENT_TYPE_UPLOAD);
                NetTool.httpPost(StringConst.STREAMAPP_KEY_BASESERVICEURL() + "collect/crash", str, map);
            }
        });
    }

    public static void commitUncatchException(Context context, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        commitBaseUncatchInfo(context, stringBuffer);
        stringBuffer.append("etype=2");
        stringBuffer.append("&log=" + PdrUtil.encodeURL(str2));
        stringBuffer.append("&eurl=" + PdrUtil.encodeURL(str));
        commitErrorLog(stringBuffer.toString());
    }

    public static void commitUncatchException(File file) {
    }

    public static String getAppUseMem(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (context.getApplicationInfo().uid == runningAppProcessInfo.uid) {
                    int i = runningAppProcessInfo.pid;
                    String str = runningAppProcessInfo.processName;
                    return activityManager.getProcessMemoryInfo(new int[]{i})[0].dalvikPrivateDirty + "kb";
                }
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static File handleUncaughtException(Context context, Throwable th) {
        StringBuffer stringBuffer;
        String string;
        File file;
        File file2 = null;
        try {
            Field[] declaredFields = Build.class.getDeclaredFields();
            stringBuffer = new StringBuffer();
            for (Field field : declaredFields) {
                try {
                    field.setAccessible(true);
                    stringBuffer.append(field.getName() + Constants.COLON_SEPARATOR + field.get(null) + "\n");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            string = stringWriter.toString();
            File file3 = "mounted".equalsIgnoreCase(Environment.getExternalStorageState()) ? new File(BaseInfo.getCrashLogsPath(context) + CRASH_DIRECTORY) : new File(context.getCacheDir().getAbsolutePath() + CRASH_DIRECTORY);
            if (!file3.exists()) {
                file3.mkdirs();
            }
            file = new File(file3.getAbsolutePath(), "crash_" + System.currentTimeMillis() + "_" + formatter.format(new Date()) + ".log");
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return file2;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            stringBuffer.append(string);
            fileOutputStream.write(stringBuffer.toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (Exception e4) {
            e = e4;
            file2 = file;
            e.printStackTrace();
            return file2;
        }
    }

    public static void commitUncatchException(Context context, Throwable th) {
        if (AppRuntime.hasPrivacyForNotShown(context)) {
            return;
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        StringBuffer stringBuffer = new StringBuffer();
        commitBaseUncatchInfo(context, stringBuffer);
        stringBuffer.append("etype=1");
        stringBuffer.append("&log=" + PdrUtil.encodeURL(string));
        commitErrorLog(stringBuffer.toString());
    }
}
