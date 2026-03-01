package supwisdom;

import android.os.Build;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class qq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static qq f8945a = new qq();

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static qq a() {
        return f8945a;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0055 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r7) {
        /*
            r0 = 0
            java.lang.String r1 = android.os.Build.HARDWARE     // Catch: java.lang.Exception -> L66
            java.lang.String r2 = "goldfish"
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Exception -> L66
            r2 = 1
            if (r1 != 0) goto L65
            java.lang.String r1 = android.os.Build.PRODUCT     // Catch: java.lang.Exception -> L66
            java.lang.String r3 = "sdk"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L66
            if (r1 != 0) goto L65
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch: java.lang.Exception -> L66
            java.lang.String r3 = "generic"
            boolean r1 = r1.contains(r3)     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L21
            goto L65
        L21:
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r7.getSystemService(r1)     // Catch: java.lang.Exception -> L66
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L56
            java.lang.String r1 = r1.getDeviceId()     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L52
            int r3 = r1.length()     // Catch: java.lang.Exception -> L66
            if (r3 != 0) goto L38
            goto L52
        L38:
            r4 = 0
        L39:
            if (r4 >= r3) goto L52
            char r5 = r1.charAt(r4)     // Catch: java.lang.Exception -> L66
            boolean r5 = java.lang.Character.isWhitespace(r5)     // Catch: java.lang.Exception -> L66
            if (r5 != 0) goto L4f
            char r5 = r1.charAt(r4)     // Catch: java.lang.Exception -> L66
            r6 = 48
            if (r5 == r6) goto L4f
            r1 = 0
            goto L53
        L4f:
            int r4 = r4 + 1
            goto L39
        L52:
            r1 = 1
        L53:
            if (r1 == 0) goto L56
            return r2
        L56:
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch: java.lang.Exception -> L66
            java.lang.String r1 = "android_id"
            java.lang.String r7 = android.provider.Settings.Secure.getString(r7, r1)     // Catch: java.lang.Exception -> L66
            boolean r7 = supwisdom.lq.a(r7)     // Catch: java.lang.Exception -> L66
            return r7
        L65:
            return r2
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.qq.a(android.content.Context):boolean");
    }

    public static String b() {
        return "android";
    }

    public static boolean c() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static String d() {
        return Build.BOARD;
    }

    public static String e() {
        return Build.BRAND;
    }

    public static String f() {
        return Build.DEVICE;
    }

    public static String g() {
        return Build.DISPLAY;
    }

    public static String h() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String i() {
        return Build.MANUFACTURER;
    }

    public static String j() {
        return Build.MODEL;
    }

    public static String k() {
        return Build.PRODUCT;
    }

    public static String l() {
        return Build.VERSION.RELEASE;
    }

    public static String m() {
        return Build.VERSION.SDK;
    }

    public static String n() {
        return Build.TAGS;
    }

    public static String o() {
        return a("ro.kernel.qemu", "0");
    }
}
