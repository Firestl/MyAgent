package com.igexin.push.g;

import android.content.Context;
import com.igexin.assist.sdk.AssistPushConsts;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3579a = "com.igexin.assist.control.stp.ManufacturePushManager";

    public static boolean a(Context context) {
        try {
            Method declaredMethod = Class.forName(f3579a).getDeclaredMethod("checkDevice", Context.class);
            declaredMethod.setAccessible(true);
            boolean zBooleanValue = ((Boolean) declaredMethod.invoke(null, context)).booleanValue();
            com.igexin.c.a.c.a.b("Assist_UPS", "isSupportStp: ".concat(String.valueOf(zBooleanValue)));
            return zBooleanValue;
        } catch (Exception e2) {
            e2.getMessage();
            try {
                Class<?> cls = Class.forName(f3579a);
                Object objNewInstance = cls.getConstructor(Context.class).newInstance(context);
                Method declaredMethod2 = cls.getDeclaredMethod("isSupport", new Class[0]);
                declaredMethod2.setAccessible(true);
                com.igexin.c.a.c.a.b("Assist_UPS", "isSupportStp: ".concat(String.valueOf(((Boolean) declaredMethod2.invoke(objNewInstance, new Object[0])).booleanValue())));
            } catch (Exception unused) {
            }
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        String strConcat = AssistPushConsts.LOG_TAG.concat(String.valueOf(str));
        boolean zBooleanValue = false;
        try {
            Class<?> cls = Class.forName("com.igexin.assist.control." + str + ".ManufacturePushManager");
            Object objNewInstance = cls.getConstructor(Context.class).newInstance(context);
            Field declaredField = cls.getDeclaredField(com.umeng.analytics.pro.f.X);
            boolean zIsAccessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            declaredField.set(objNewInstance, context);
            declaredField.setAccessible(zIsAccessible);
            zBooleanValue = ((Boolean) cls.getDeclaredMethod("isSupport", new Class[0]).invoke(objNewInstance, new Object[0])).booleanValue();
            com.igexin.c.a.c.a.e.a(strConcat, "isSupport " + str + " = " + zBooleanValue);
            return zBooleanValue;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.e.a(strConcat, "class non-existent  " + th.getMessage());
            return zBooleanValue;
        }
    }
}
