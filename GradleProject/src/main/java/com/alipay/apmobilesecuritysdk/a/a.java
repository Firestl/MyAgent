package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.c;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import supwisdom.er;
import supwisdom.hr;
import supwisdom.ir;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1580a;
    public com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    public int c = 4;

    public a(Context context) {
        this.f1580a = context;
    }

    public static String a(Context context) {
        String strB = b(context);
        return lq.a(strB) ? h.f(context) : strB;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String strA = i.a(str);
            if (!lq.a(strA)) {
                return strA;
            }
            String strA2 = g.a(context, str);
            i.a(str, strA2);
            return !lq.a(strA2) ? strA2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int iRandom = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 3; i++) {
            try {
                String[] strArrSplit = strArr[i].split(Operators.SPACE_STR);
                if (strArrSplit != null && strArrSplit.length == 2) {
                    Date date = new Date();
                    Date date2 = simpleDateFormat.parse(strArrSplit[0] + " 00:00:00");
                    Date date3 = simpleDateFormat.parse(strArrSplit[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date3);
                    calendar.add(13, iRandom);
                    Date time = calendar.getTime();
                    if (date.after(date2) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static String b(Context context) {
        try {
            String strB = i.b();
            if (!lq.a(strB)) {
                return strB;
            }
            c cVarB = d.b(context);
            if (cVarB != null) {
                i.a(cVarB);
                String str = cVarB.f1584a;
                if (lq.b(str)) {
                    return str;
                }
            }
            b bVarB = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (bVarB == null) {
                return "";
            }
            i.a(bVarB);
            String str2 = bVarB.f1583a;
            return lq.b(str2) ? str2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private hr b(Map<String, String> map) {
        String str;
        b bVarB;
        b bVarC;
        String str2 = "";
        try {
            Context context = this.f1580a;
            ir irVar = new ir();
            String strA = lq.a(map, "appName", "");
            String strA2 = lq.a(map, "sessionId", "");
            String strA3 = lq.a(map, "rpcVersion", "");
            String strA4 = a(context, strA);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String strD = h.d(context);
            if (lq.b(strA2)) {
                irVar.c = strA2;
            } else {
                irVar.c = strA4;
            }
            irVar.d = securityToken;
            irVar.f7975e = strD;
            irVar.f7974a = "android";
            c cVarC = d.c(context);
            if (cVarC != null) {
                str = cVarC.f1584a;
                String str3 = cVarC.c;
            } else {
                str = "";
            }
            if (lq.a(str) && (bVarC = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str = bVarC.f1583a;
                String str4 = bVarC.c;
            }
            c cVarB = d.b();
            if (cVarB != null) {
                str2 = cVarB.f1584a;
                String str5 = cVarB.c;
            }
            if (lq.a(str2) && (bVarB = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str2 = bVarB.f1583a;
                String str6 = bVarB.c;
            }
            irVar.g = strA3;
            if (lq.a(str)) {
                irVar.b = str2;
            } else {
                irVar.b = str;
            }
            irVar.f = e.a(context, map);
            return er.b(this.f1580a, this.b.c()).a(irVar);
        } catch (Throwable th) {
            th.printStackTrace();
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    public static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File file = new File(Environment.getExternalStorageDirectory(), ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x01ff A[Catch: Exception -> 0x023c, TryCatch #0 {Exception -> 0x023c, blocks: (B:3:0x0006, B:5:0x0037, B:8:0x0040, B:37:0x00be, B:68:0x01e4, B:70:0x01ff, B:72:0x0205, B:74:0x020b, B:78:0x0214, B:80:0x021a, B:40:0x00d3, B:42:0x00eb, B:48:0x00f8, B:49:0x0108, B:51:0x010f, B:55:0x0121, B:57:0x0171, B:59:0x017b, B:61:0x0183, B:63:0x0190, B:65:0x019a, B:67:0x01a2, B:66:0x019e, B:60:0x017f, B:11:0x0055, B:13:0x0063, B:16:0x006e, B:18:0x0074, B:21:0x007f, B:24:0x0088, B:27:0x0095, B:30:0x00a2, B:33:0x00b0), top: B:86:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0205 A[Catch: Exception -> 0x023c, TryCatch #0 {Exception -> 0x023c, blocks: (B:3:0x0006, B:5:0x0037, B:8:0x0040, B:37:0x00be, B:68:0x01e4, B:70:0x01ff, B:72:0x0205, B:74:0x020b, B:78:0x0214, B:80:0x021a, B:40:0x00d3, B:42:0x00eb, B:48:0x00f8, B:49:0x0108, B:51:0x010f, B:55:0x0121, B:57:0x0171, B:59:0x017b, B:61:0x0183, B:63:0x0190, B:65:0x019a, B:67:0x01a2, B:66:0x019e, B:60:0x017f, B:11:0x0055, B:13:0x0063, B:16:0x006e, B:18:0x0074, B:21:0x007f, B:24:0x0088, B:27:0x0095, B:30:0x00a2, B:33:0x00b0), top: B:86:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0214 A[Catch: Exception -> 0x023c, TryCatch #0 {Exception -> 0x023c, blocks: (B:3:0x0006, B:5:0x0037, B:8:0x0040, B:37:0x00be, B:68:0x01e4, B:70:0x01ff, B:72:0x0205, B:74:0x020b, B:78:0x0214, B:80:0x021a, B:40:0x00d3, B:42:0x00eb, B:48:0x00f8, B:49:0x0108, B:51:0x010f, B:55:0x0121, B:57:0x0171, B:59:0x017b, B:61:0x0183, B:63:0x0190, B:65:0x019a, B:67:0x01a2, B:66:0x019e, B:60:0x017f, B:11:0x0055, B:13:0x0063, B:16:0x006e, B:18:0x0074, B:21:0x007f, B:24:0x0088, B:27:0x0095, B:30:0x00a2, B:33:0x00b0), top: B:86:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
            Method dump skipped, instruction units count: 579
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }
}
