package com.igexin.base.util;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class InvokeUtil {
    public static Context appContext;

    public static Context findAppContext() {
        Context context = appContext;
        if (context != null) {
            return context;
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Context context2 = (Context) cls.getMethod("getApplication", new Class[0]).invoke(cls.getDeclaredMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            appContext = context2;
            return context2;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
