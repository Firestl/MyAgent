package com.g.gysdk.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.d;
import com.getui.gtc.base.util.CommonUtil;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class ar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static as f1979a = as.UNKNOWN;

    public static GyErrorCode a(Context context) {
        try {
            return !b(context) ? GyErrorCode.NO_NET : !d(context) ? GyErrorCode.NO_MOBILE_DATA : GyErrorCode.SUCCESS;
        } catch (Throwable th) {
            aj.b(th);
            return GyErrorCode.UNKNOWN_ERROR;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static as a() {
        try {
            String simOperator = ((TelephonyManager) d.c().getSystemService("phone")).getSimOperator();
            if (!TextUtils.isEmpty(simOperator)) {
                byte b = -1;
                int iHashCode = simOperator.hashCode();
                if (iHashCode != 49679479) {
                    if (iHashCode != 49679502) {
                        switch (iHashCode) {
                            case 49679470:
                                if (simOperator.equals("46000")) {
                                    b = 0;
                                }
                                break;
                            case 49679471:
                                if (simOperator.equals("46001")) {
                                    b = 4;
                                }
                                break;
                            case 49679472:
                                if (simOperator.equals("46002")) {
                                    b = 1;
                                }
                                break;
                            case 49679473:
                                if (simOperator.equals("46003")) {
                                    b = 7;
                                }
                                break;
                            case 49679474:
                                if (simOperator.equals("46004")) {
                                    b = 2;
                                }
                                break;
                            case 49679475:
                                if (simOperator.equals("46005")) {
                                    b = 8;
                                }
                                break;
                            case 49679476:
                                if (simOperator.equals("46006")) {
                                    b = 5;
                                }
                                break;
                            case 49679477:
                                if (simOperator.equals("46007")) {
                                    b = 3;
                                }
                                break;
                        }
                    } else if (simOperator.equals("46011")) {
                        b = 9;
                    }
                } else if (simOperator.equals("46009")) {
                    b = 6;
                }
                switch (b) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        return as.CM;
                    case 4:
                    case 5:
                    case 6:
                        return as.CU;
                    case 7:
                    case 8:
                    case 9:
                        return as.CT;
                    default:
                        as.UNKNOWN.f1981e = simOperator;
                        break;
                }
            }
        } catch (Throwable th) {
            ak.e(th);
        }
        return as.UNKNOWN;
    }

    public static as a(boolean z, int i) {
        if (d.a(d.a.DEBUG_UI)) {
            aj.c("DEBUG_UI模式");
            return as.CU;
        }
        if (z) {
            as asVarA = a();
            ak.a("lastOperatorType:" + f1979a + " getOperatorType(" + z + ", " + i + "):" + asVarA);
            f1979a = asVarA;
            return asVarA;
        }
        if (i >= 0) {
            return as.a(i);
        }
        ak.a("lastOperatorType:" + f1979a + " getOperatorType(" + z + ", " + i + "):" + f1979a);
        return f1979a;
    }

    public static boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            aj.b(th);
            return true;
        }
    }

    public static boolean c(Context context) {
        int simState;
        try {
            simState = ((TelephonyManager) context.getSystemService("phone")).getSimState();
        } catch (Throwable th) {
            aj.b(th);
        }
        return (simState == 0 || simState == 1) ? false : true;
    }

    public static boolean d(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Throwable th) {
            aj.b(th);
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int e(android.content.Context r4) {
        /*
            r0 = 0
            r1 = 1
            java.lang.String r2 = "connectivity"
            java.lang.Object r2 = r4.getSystemService(r2)     // Catch: java.lang.Throwable -> L19
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2     // Catch: java.lang.Throwable -> L19
            android.net.NetworkInfo r2 = r2.getNetworkInfo(r1)     // Catch: java.lang.Throwable -> L19
            if (r2 == 0) goto L17
            boolean r2 = r2.isConnectedOrConnecting()     // Catch: java.lang.Throwable -> L19
            if (r2 == 0) goto L17
            goto L1d
        L17:
            r2 = 0
            goto L1e
        L19:
            r2 = move-exception
            com.g.gysdk.a.ak.e(r2)
        L1d:
            r2 = 1
        L1e:
            com.g.gysdk.GyErrorCode r4 = a(r4)
            com.g.gysdk.GyErrorCode r3 = com.g.gysdk.GyErrorCode.SUCCESS
            if (r4 != r3) goto L28
            r4 = 1
            goto L29
        L28:
            r4 = 0
        L29:
            if (r4 == 0) goto L2c
            r0 = 1
        L2c:
            if (r2 == 0) goto L30
            int r0 = r0 + 2
        L30:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = "getNetworkType:"
            r4.append(r1)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.g.gysdk.a.ak.a(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.g.gysdk.a.ar.e(android.content.Context):int");
    }

    @SuppressLint({"MissingPermission"})
    public static String f(Context context) {
        String str = "";
        try {
        } catch (Throwable th) {
            ak.e(th);
        }
        if (!CommonUtil.hasPermission(context, "android.permission.READ_PHONE_STATE", false)) {
            ak.c("getRadioType: hasPermission(READ_PHONE_STATE) return false");
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        switch (telephonyManager != null ? Build.VERSION.SDK_INT >= 24 ? telephonyManager.getDataNetworkType() : telephonyManager.getNetworkType() : 0) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                str = "2G";
                break;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                str = "3G";
                break;
            case 13:
            case 18:
            case 19:
                str = "4G";
                break;
            case 20:
                str = "5G";
                break;
        }
        ak.a("getRadioType:" + str);
        return str;
    }
}
