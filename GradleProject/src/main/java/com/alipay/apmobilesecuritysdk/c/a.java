package com.alipay.apmobilesecuritysdk.c;

import android.content.Context;
import android.os.Build;
import io.dcloud.common.adapter.util.Logger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import supwisdom.ar;
import supwisdom.xq;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static synchronized void a(Context context, String str, String str2, String str3) {
        xq xqVarB = b(context, str, str2, str3);
        ar.a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat(Logger.TIMESTAMP_YYYY_MM_DD).format(Calendar.getInstance().getTime()) + ".log", xqVarB.toString());
    }

    public static synchronized void a(String str) {
        ar.a(str);
    }

    public static synchronized void a(Throwable th) {
        ar.a(th);
    }

    public static xq b(Context context, String str, String str2, String str3) {
        String packageName;
        try {
            packageName = context.getPackageName();
        } catch (Throwable unused) {
            packageName = "";
        }
        return new xq(Build.MODEL, packageName, "APPSecuritySDK-ALIPAYSDK", "3.4.0.201910161639", str, str2, str3);
    }
}
