package com.g.gysdk.a;

import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

/* JADX INFO: loaded from: classes.dex */
public class at {
    public static int a(Context context) {
        int iB;
        try {
            return (Build.VERSION.SDK_INT < 22 || context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) != 0 || (iB = b(context)) == -1) ? c(context) : iB;
        } catch (Exception unused) {
            return c(context);
        }
    }

    public static Object a(Context context, String str, int i) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            Object objInvoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            if (objInvoke != null) {
                return objInvoke;
            }
            return null;
        } catch (Throwable th) {
            aj.a(th);
            return null;
        }
    }

    public static boolean a(Context context, int i) {
        try {
            Object objA = a(context, "getSimState", i);
            if (objA != null) {
                return Integer.parseInt(objA.toString()) == 5;
            }
            return false;
        } catch (Throwable th) {
            aj.a(th);
            return false;
        }
    }

    public static int b(Context context) {
        SubscriptionManager subscriptionManager;
        try {
            if (Build.VERSION.SDK_INT >= 22 && context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0 && (subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service")) != null) {
                return subscriptionManager.getActiveSubscriptionInfoCount();
            }
        } catch (Throwable unused) {
        }
        return -1;
    }

    public static int c(Context context) {
        boolean zA = a(context, 0);
        boolean zA2 = a(context, 1);
        if (zA && zA2) {
            return 2;
        }
        return (zA || zA2 || ((TelephonyManager) context.getSystemService("phone")).getSimState() == 5) ? 1 : 0;
    }
}
