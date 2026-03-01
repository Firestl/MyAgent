package com.cmic.gen.sdk.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class j {

    @SuppressLint({"StaticFieldLeak"})
    public static j b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1733a;

    public j(Context context) {
        this.f1733a = context;
    }

    public static j a() {
        return b;
    }

    public static void a(Context context) {
        b = new j(context);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = 49679479(0x2f60c77, float:3.6153606E-37)
            if (r0 == r1) goto L6f
            r1 = 49679502(0x2f60c8e, float:3.6153657E-37)
            if (r0 == r1) goto L64
            switch(r0) {
                case 49679470: goto L5a;
                case 49679471: goto L50;
                case 49679472: goto L46;
                case 49679473: goto L3c;
                case 49679474: goto L32;
                case 49679475: goto L27;
                case 49679476: goto L1d;
                case 49679477: goto L13;
                default: goto L11;
            }
        L11:
            goto L79
        L13:
            java.lang.String r0 = "46007"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 2
            goto L7a
        L1d:
            java.lang.String r0 = "46006"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 5
            goto L7a
        L27:
            java.lang.String r0 = "46005"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 8
            goto L7a
        L32:
            java.lang.String r0 = "46004"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 3
            goto L7a
        L3c:
            java.lang.String r0 = "46003"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 7
            goto L7a
        L46:
            java.lang.String r0 = "46002"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 1
            goto L7a
        L50:
            java.lang.String r0 = "46001"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 4
            goto L7a
        L5a:
            java.lang.String r0 = "46000"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 0
            goto L7a
        L64:
            java.lang.String r0 = "46011"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 9
            goto L7a
        L6f:
            java.lang.String r0 = "46009"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 6
            goto L7a
        L79:
            r3 = -1
        L7a:
            java.lang.String r0 = "SIMUtils"
            switch(r3) {
                case 0: goto L92;
                case 1: goto L92;
                case 2: goto L92;
                case 3: goto L92;
                case 4: goto L8a;
                case 5: goto L8a;
                case 6: goto L8a;
                case 7: goto L82;
                case 8: goto L82;
                case 9: goto L82;
                default: goto L7f;
            }
        L7f:
            java.lang.String r3 = "0"
            return r3
        L82:
            java.lang.String r3 = "中国电信"
            com.cmic.gen.sdk.e.c.a(r0, r3)
            java.lang.String r3 = "3"
            return r3
        L8a:
            java.lang.String r3 = "中国联通"
            com.cmic.gen.sdk.e.c.a(r0, r3)
            java.lang.String r3 = "2"
            return r3
        L92:
            java.lang.String r3 = "中国移动"
            com.cmic.gen.sdk.e.c.a(r0, r3)
            java.lang.String r3 = "1"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.gen.sdk.e.j.b(java.lang.String):java.lang.String");
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = c();
        }
        return b(str);
    }

    public String b() {
        try {
            int iA = com.cmic.gen.sdk.b.a.a().b().a();
            return iA >= 0 ? Integer.toString(iA) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String c() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f1733a.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        String simOperator = telephonyManager.getSimOperator();
        c.b("SIMUtils", "SysOperator= " + simOperator);
        return simOperator;
    }
}
