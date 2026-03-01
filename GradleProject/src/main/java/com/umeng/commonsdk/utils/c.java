package com.umeng.commonsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.pro.aw;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: compiled from: SLModeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static final String b = "lastReqTime";
    public static final int c = 48;
    public static final int d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f5484e = 720;
    public static final String f = "iss";
    public static final String g = "sinr";
    public static final String h = "clean";
    public static boolean i;
    public static int j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5483a = aw.b().b(aw.z);
    public static Object k = new Object();

    static {
        i = false;
        j = 720;
        Context appContext = UMGlobalContext.getAppContext();
        if (appContext != null) {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(appContext, f, "");
            if (TextUtils.isEmpty(strImprintProperty) || !"1".equals(strImprintProperty)) {
                return;
            }
            synchronized (k) {
                i = true;
            }
            String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(appContext, g, "");
            if (TextUtils.isEmpty(strImprintProperty)) {
                j = 48;
                return;
            }
            try {
                j = a(Integer.parseInt(strImprintProperty2));
            } catch (Throwable unused) {
                j = 48;
            }
        }
    }

    public static int a(int i2) {
        if (i2 > 720) {
            return 720;
        }
        if (i2 < 1) {
            return 1;
        }
        return i2;
    }

    public static boolean a() {
        boolean z;
        synchronized (k) {
            z = i;
        }
        return z;
    }

    public static long b(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f5483a, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getLong(b, 0L);
        }
        return 0L;
    }

    public static void c(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f5483a, 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(h, true).commit();
        }
    }

    public static void d(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f5483a, 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(h, false).commit();
        }
    }

    public static boolean e(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f5483a, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(h, false);
        }
        return false;
    }

    public static int a(Context context) {
        int i2;
        synchronized (k) {
            i2 = j;
        }
        return i2;
    }

    public static boolean a(long j2, long j3, int i2) {
        Date date = new Date(j3);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j2));
        calendar.add(10, i2);
        return date.after(calendar.getTime());
    }

    public static void a(Context context, long j2) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f5483a, 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(b, j2).commit();
        }
    }
}
