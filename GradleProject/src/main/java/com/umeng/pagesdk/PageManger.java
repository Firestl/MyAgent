package com.umeng.pagesdk;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.efs.sdk.base.EfsReporter;

/* JADX INFO: loaded from: classes2.dex */
public class PageManger {
    public static final String TAG = "PageManger";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f5494a = null;
    public static boolean b = false;
    public static EfsReporter c = null;
    public static PageConfigManger d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5495e = true;
    public static float f = 0.0f;
    public static boolean isDebug = true;

    public static Context getApplicationContext() {
        return f5494a;
    }

    public static PageConfigManger getPageConfigManger() {
        return d;
    }

    public static float getRefreshRate() {
        return f;
    }

    public static EfsReporter getReporter() {
        return c;
    }

    public static void init(Context context, EfsReporter efsReporter) {
        if (context == null || efsReporter == null) {
            try {
                if (isDebug) {
                    Log.e(TAG, "init page manager error! parameter is null!");
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
            }
        } else {
            f5494a = context.getApplicationContext();
            c = efsReporter;
            d = new PageConfigManger(context, efsReporter);
            b = true;
        }
    }

    public static boolean isControlMainThread() {
        return f5495e;
    }

    public static boolean isInit() {
        return b;
    }

    public static void onTracePageBegin(Activity activity, String str) {
        try {
            onTracePageBegin(activity, str, false);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void onTracePageBegin(Activity activity, String str, boolean z) {
        if (activity != null) {
            try {
                if (activity.getClass() != null && activity.getClass().getName() != null && !TextUtils.isEmpty(str)) {
                    if (!z && (str.equals("onCreate") || str.equals("onStart") || str.equals("onResume") || str.equals("onPause"))) {
                        if (isDebug) {
                            Log.e(TAG, "tracePageBegin. parameter illegality!");
                            return;
                        }
                        return;
                    }
                    if (str.length() > 10) {
                        if (isDebug) {
                            Log.e(TAG, "tracePageBegin. method name is " + str + "method name over length !");
                            return;
                        }
                        return;
                    }
                    if (f5495e && !e.a(activity.getApplicationContext()) && isDebug) {
                        Log.e(TAG, "tracePageBegin. Non main process !");
                    }
                    String name = activity.getClass().getName();
                    if (f <= 0.0f) {
                        f = Build.VERSION.SDK_INT >= 30 ? activity.getDisplay().getRefreshRate() : activity.getWindowManager().getDefaultDisplay().getRefreshRate();
                    }
                    d.a(name, str, z);
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        if (isDebug) {
            Log.e(TAG, "tracePageBegin. parameter null!");
        }
    }

    public static void onTracePageEnd(Activity activity, String str) {
        try {
            onTracePageEnd(activity, str, false);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void onTracePageEnd(Activity activity, String str, boolean z) {
        if (activity != null) {
            try {
                if (activity.getClass() != null && activity.getClass().getName() != null && !TextUtils.isEmpty(str)) {
                    if (!z && (str.equals("onCreate") || str.equals("onStart") || str.equals("onResume") || str.equals("onPause"))) {
                        if (isDebug) {
                            Log.e(TAG, "tracePageEnd. parameter illegality!");
                            return;
                        }
                        return;
                    } else {
                        if (str.length() <= 10) {
                            if (f5495e && !e.a(activity.getApplicationContext()) && isDebug) {
                                Log.e(TAG, "tracePageBegin. Non main process !");
                            }
                            d.b(activity.getClass().getName(), str, z);
                            return;
                        }
                        if (isDebug) {
                            Log.e(TAG, "tracePageEnd. method name is " + str + "method name over length !");
                            return;
                        }
                        return;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        if (isDebug) {
            Log.e(TAG, "tracePageEnd. parameter null!");
        }
    }

    public static void setControlMainThread(boolean z) {
        f5495e = z;
    }
}
