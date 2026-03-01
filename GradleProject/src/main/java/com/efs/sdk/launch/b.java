package com.efs.sdk.launch;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.efs.sdk.base.Constants;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.protocol.record.EfsJSONLog;
import com.umeng.umcrash.UMCrash;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f1881a;
    public static long b;
    public static long c;
    public static long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f1882e;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public static long i;
    public static long j;
    public static int k;
    public static List<EfsJSONLog> l = new ArrayList();
    public static Map<String, Long[]> m = new HashMap();

    public static void a(Activity activity, String str, boolean z) {
        long jCurrentTimeMillis;
        Context applicationContext;
        String name;
        int i2;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long jCurrentTimeMillis2;
        if (TextUtils.equals(str, LaunchManager.PAGE_ON_CREATE)) {
            if (z) {
                if (LaunchManager.isDebug) {
                    Log.i("LaunchTrace", "onCreate");
                }
                d = System.currentTimeMillis();
                return;
            }
            return;
        }
        if (TextUtils.equals(str, LaunchManager.PAGE_ON_RE_START)) {
            if (z && k == 0) {
                if (LaunchManager.isDebug) {
                    Log.i("LaunchTrace", "onRestart");
                }
                f1882e = System.currentTimeMillis();
                g = true;
                return;
            }
            return;
        }
        if (TextUtils.equals(str, LaunchManager.PAGE_ON_START)) {
            if (z) {
                if (LaunchManager.isDebug) {
                    Log.i("LaunchTrace", "onStart");
                }
                k++;
                h = true;
                return;
            }
            return;
        }
        if (!TextUtils.equals(str, LaunchManager.PAGE_ON_RESUME)) {
            if (TextUtils.equals(str, LaunchManager.PAGE_ON_STOP) && z) {
                if (LaunchManager.isDebug) {
                    Log.i("LaunchTrace", "onStop");
                }
                k--;
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        if (LaunchManager.isDebug) {
            Log.i("LaunchTrace", "onResume");
        }
        if (f) {
            f = false;
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            long j10 = jCurrentTimeMillis3 - c;
            if (LaunchManager.isDebug) {
                Log.i("LaunchTrace", "loadTime is ".concat(String.valueOf(j10)));
            }
            long j11 = jCurrentTimeMillis3 - f1881a;
            if (LaunchManager.isDebug) {
                Log.i("LaunchTrace", "======>>>>>> coldTime is ".concat(String.valueOf(j11)));
            }
            int i3 = !c.d(activity.getApplicationContext()) ? 1 : 0;
            if (LaunchManager.isDebug) {
                Log.i("LaunchTrace", "type is ".concat(String.valueOf(i3)));
            }
            a(activity.getApplicationContext(), i3, activity.getClass().getName(), j11, f1881a, b, i, c, j, jCurrentTimeMillis3, j10, 0L, 0L, m);
        } else if (k == 1) {
            if (g) {
                g = false;
                jCurrentTimeMillis2 = System.currentTimeMillis() - f1882e;
                if (LaunchManager.isDebug) {
                    Log.i("LaunchTrace", "======>>>>>> hotTime is ".concat(String.valueOf(jCurrentTimeMillis2)));
                }
                applicationContext = activity.getApplicationContext();
                name = activity.getClass().getName();
                i2 = 2;
                j2 = 0;
                j3 = 0;
                j4 = 0;
                j5 = 0;
                j6 = 0;
                j7 = 0;
                j8 = 0;
                j9 = 0;
                jCurrentTimeMillis = 0;
            } else if (h) {
                jCurrentTimeMillis = System.currentTimeMillis() - d;
                if (LaunchManager.isDebug) {
                    Log.i("LaunchTrace", "======>>>>>> warmTime is ".concat(String.valueOf(jCurrentTimeMillis)));
                }
                applicationContext = activity.getApplicationContext();
                name = activity.getClass().getName();
                i2 = 3;
                j2 = 0;
                j3 = 0;
                j4 = 0;
                j5 = 0;
                j6 = 0;
                j7 = 0;
                j8 = 0;
                j9 = 0;
                jCurrentTimeMillis2 = 0;
            }
            a(applicationContext, i2, name, j2, j3, j4, j5, j6, j7, j8, j9, jCurrentTimeMillis2, jCurrentTimeMillis, m);
        }
        h = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:135:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r24, int r25, java.lang.String r26, long r27, long r29, long r31, long r33, long r35, long r37, long r39, long r41, long r43, long r45, java.util.Map<java.lang.String, java.lang.Long[]> r47) {
        /*
            Method dump skipped, instruction units count: 879
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.efs.sdk.launch.b.a(android.content.Context, int, java.lang.String, long, long, long, long, long, long, long, long, long, long, java.util.Map):void");
    }

    public static void a(Context context, String str) {
        if (LaunchManager.isDebug) {
            Log.i("LaunchTrace", "begin sendLaunchCache");
        }
        if (str == null || TextUtils.isEmpty(str)) {
            str = c.a(context);
        }
        if (str == null || TextUtils.isEmpty(str)) {
            return;
        }
        HashMap map = new HashMap(1);
        map.put(UMCrash.KEY_HEADER_UMID, str);
        if (LaunchManager.getReporter() != null) {
            LaunchManager.getReporter().addPublicParams(map);
        }
        String strB = c.b(context);
        if (strB != null && !TextUtils.isEmpty(strB)) {
            try {
                JSONObject jSONObject = new JSONObject(strB);
                jSONObject.put(UMCrash.KEY_HEADER_UMID, str);
                if (a(jSONObject)) {
                    c.c(context);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        List<EfsJSONLog> list = l;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (EfsJSONLog efsJSONLog : l) {
            if (efsJSONLog != null) {
                if (LaunchManager.isDebug) {
                    Log.i("LaunchTrace", "send cache launch report --->>> " + efsJSONLog.generateString());
                }
                EfsReporter reporter = LaunchManager.getReporter();
                if (reporter != null) {
                    reporter.send(efsJSONLog);
                }
            }
        }
        l.clear();
        l = null;
    }

    public static void a(String str, long j2) {
        Map<String, Long[]> map = m;
        if (map == null || map.containsKey(str) || m.size() >= 10) {
            if (LaunchManager.isDebug) {
                Log.e("LaunchTrace", "--->>> method name already exists or over quantity !");
            }
        } else {
            Long[] lArr = new Long[2];
            lArr[0] = Long.valueOf(j2);
            m.put(str, lArr);
        }
    }

    public static void a(String str, boolean z) {
        if (TextUtils.equals(str, LaunchManager.APP_CONSTRUCT)) {
            return;
        }
        if (!TextUtils.equals(str, LaunchManager.APP_ATTACH_BASE_CONTEXT)) {
            if (!TextUtils.equals(str, LaunchManager.APP_ON_CREATE) || z) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            c = jCurrentTimeMillis;
            j = jCurrentTimeMillis - b;
            if (LaunchManager.isDebug) {
                Log.i("LaunchTrace", "buildTime is " + j);
                return;
            }
            return;
        }
        if (z) {
            f1881a = System.currentTimeMillis();
            f = true;
            return;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        b = jCurrentTimeMillis2;
        i = jCurrentTimeMillis2 - f1881a;
        if (LaunchManager.isDebug) {
            Log.i("LaunchTrace", "initTime is " + i);
        }
    }

    public static boolean a(JSONObject jSONObject) {
        try {
            EfsJSONLog efsJSONLog = new EfsJSONLog(Constants.LOG_TYPE_STARTPERF);
            efsJSONLog.put("w_type", jSONObject.opt("w_type"));
            efsJSONLog.put("w_url", jSONObject.opt("w_url"));
            efsJSONLog.put("l_version", jSONObject.opt("l_version"));
            efsJSONLog.put("wl_avgv", jSONObject.opt("wl_avgv"));
            efsJSONLog.put("wd_init", jSONObject.opt("wd_init"));
            efsJSONLog.put("wd_inittm", jSONObject.opt("wd_inittm"));
            efsJSONLog.put("wl_init", jSONObject.opt("wl_init"));
            efsJSONLog.put("wd_build", jSONObject.opt("wd_build"));
            efsJSONLog.put("wd_buildtm", jSONObject.opt("wd_buildtm"));
            efsJSONLog.put("wl_build", jSONObject.opt("wl_build"));
            efsJSONLog.put("wd_page", jSONObject.opt("wd_page"));
            efsJSONLog.put("wd_pagetm", jSONObject.opt("wd_pagetm"));
            efsJSONLog.put("wl_page", jSONObject.opt("wl_page"));
            efsJSONLog.put("userExtra", jSONObject.opt("userExtra"));
            if (LaunchManager.isDebug) {
                Log.i("LaunchTrace", "send cache cold launch report --->>> " + efsJSONLog.generateString());
            }
            EfsReporter reporter = LaunchManager.getReporter();
            if (reporter == null) {
                return false;
            }
            reporter.send(efsJSONLog);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static void b(String str, long j2) {
        Map<String, Long[]> map = m;
        if (map == null || !map.containsKey(str)) {
            if (LaunchManager.isDebug) {
                Log.e("LaunchTrace", "--->>> method name non-existent or over quantity !");
            }
        } else {
            Long[] lArr = m.get(str);
            lArr[1] = Long.valueOf(j2);
            m.put(str, lArr);
        }
    }
}
