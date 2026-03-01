package com.umeng.analytics.pro;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;

/* JADX INFO: compiled from: OpenDeviceId.java */
/* JADX INFO: loaded from: classes2.dex */
public class ay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ax f5184a = null;
    public static boolean b = false;
    public static String c = null;
    public static ax d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5185e = false;
    public static String f;

    public static synchronized String a(Context context) {
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>>*** real call OpenDeviceId.getOaid()");
        if (context == null) {
            return null;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return null;
        }
        a();
        if (f5184a != null) {
            try {
                String strA = f5184a.a(context);
                c = strA;
                return strA;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static synchronized String b(Context context) {
        if (c != null && !TextUtils.isEmpty(c)) {
            return c;
        }
        return a(context);
    }

    public static synchronized String c(Context context) {
        if (context == null) {
            return null;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return null;
        }
        if (bk.c()) {
            b();
            if (d != null) {
                try {
                    String strA = d.a(context);
                    f = strA;
                    return strA;
                } catch (Throwable unused) {
                }
            }
        }
        return null;
    }

    public static synchronized String d(Context context) {
        if (f != null && !TextUtils.isEmpty(f)) {
            return f;
        }
        return c(context);
    }

    public static void b() {
        if (d != null || f5185e) {
            return;
        }
        synchronized (ay.class) {
            if (d == null && !f5185e) {
                d = ba.b();
                f5185e = true;
            }
        }
    }

    public static void a() {
        if (f5184a != null || b) {
            return;
        }
        synchronized (ay.class) {
            if (f5184a == null && !b) {
                f5184a = ba.a();
                b = true;
            }
        }
    }
}
