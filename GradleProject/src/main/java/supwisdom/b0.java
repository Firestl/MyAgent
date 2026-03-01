package supwisdom;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.newcapec.smcrypto.NCPBase;
import com.newcapec.smcrypto.SMKeyAlg;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f6999a;
    public static Context b;
    public static String c;
    public static String d;

    public static byte a(char c2) {
        return (byte) "0123456789ABCDEF".indexOf(c2);
    }

    public static int a(byte[] bArr, String str, String str2, String str3, byte[] bArr2) {
        SMKeyAlg sMKeyAlg = new SMKeyAlg(Logger.getLogger("V8QRUtils"));
        byte[] bArr3 = new byte[5];
        int iGetQRDateAndDevCRC = sMKeyAlg.GetQRDateAndDevCRC(str, bArr3);
        d0.b("GetQRDateAndDevCRC ret=" + iGetQRDateAndDevCRC);
        if (iGetQRDateAndDevCRC != 8000) {
            return iGetQRDateAndDevCRC;
        }
        System.arraycopy(bArr, 0, bArr2, 0, 53);
        System.arraycopy(bArr3, 0, bArr2, 53, 5);
        byte[] bArr4 = new byte[64];
        int iSM2Sign = sMKeyAlg.SM2Sign("appsecretkeyforv8qrcode", str2, str3, bArr2, 58, bArr4);
        d0.b("SM2Sign ret=" + iSM2Sign);
        if (iSM2Sign != 8000) {
            return iSM2Sign;
        }
        System.arraycopy(bArr4, 0, bArr2, 58, 64);
        d0.b("生成的二维码:" + NCPBase.hexToString(bArr2));
        return iSM2Sign;
    }

    public static String a() {
        String string;
        String string2 = Settings.Secure.getString(f6999a.getContentResolver(), com.umeng.commonsdk.statistics.idtracking.b.f5435a);
        if (TextUtils.isEmpty(string2) || "9774d56d682e549c".equals(string2)) {
            string = null;
        } else {
            try {
                string = UUID.nameUUIDFromBytes(string2.getBytes("utf8")).toString();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                string = null;
            }
        }
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (Build.VERSION.SDK_INT > 28) {
            return b();
        }
        TelephonyManager telephonyManager = (TelephonyManager) f6999a.getApplicationContext().getSystemService("phone");
        String deviceId = y7.a(f6999a, "android.permission.READ_PHONE_STATE") == 0 ? telephonyManager.getDeviceId() : "";
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            String imei = y7.a(f6999a, "android.permission.READ_PHONE_STATE") == 0 ? telephonyManager.getImei() : "";
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }
            String meid = y7.a(f6999a, "android.permission.READ_PHONE_STATE") == 0 ? telephonyManager.getMeid() : "";
            if (!TextUtils.isEmpty(meid)) {
                return meid;
            }
        }
        return b();
    }

    public static String a(int i) {
        return i < 0 ? "" : b.getString(i);
    }

    public static String a(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (!inetAddressNextElement.isLoopbackAddress()) {
                            String hostAddress = inetAddressNextElement.getHostAddress();
                            boolean z2 = hostAddress.indexOf(58) < 0;
                            if (z) {
                                if (z2) {
                                    return hostAddress;
                                }
                            } else if (!z2) {
                                int iIndexOf = hostAddress.indexOf(37);
                                return iIndexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, iIndexOf).toUpperCase();
                            }
                        }
                    }
                }
            }
            return "";
        } catch (SocketException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || str.startsWith("当前帐号于")) {
            return;
        }
        l0.a(context, str);
    }

    public static boolean a(String str) throws Throwable {
        String upperCase;
        String str2 = c;
        if (str2 != null) {
            return str2.equals(str);
        }
        String strB = b("ro.miui.ui.version.name");
        d = strB;
        if (TextUtils.isEmpty(strB)) {
            String strB2 = b("ro.build.version.emui");
            d = strB2;
            if (TextUtils.isEmpty(strB2)) {
                String strB3 = b(com.umeng.analytics.pro.as.g);
                d = strB3;
                if (TextUtils.isEmpty(strB3)) {
                    String strB4 = b(com.umeng.analytics.pro.as.i);
                    d = strB4;
                    if (TextUtils.isEmpty(strB4)) {
                        String strB5 = b("ro.smartisan.version");
                        d = strB5;
                        if (TextUtils.isEmpty(strB5)) {
                            String str3 = Build.DISPLAY;
                            d = str3;
                            if (str3.toUpperCase().contains("FLYME")) {
                                c = "FLYME";
                                return c.equals(str);
                            }
                            d = "unknown";
                            upperCase = Build.MANUFACTURER.toUpperCase();
                        } else {
                            upperCase = "SMARTISAN";
                        }
                    } else {
                        upperCase = "VIVO";
                    }
                } else {
                    upperCase = "OPPO";
                }
            } else {
                upperCase = "EMUI";
            }
        } else {
            upperCase = "MIUI";
        }
        c = upperCase;
        return c.equals(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00ba A[Catch: Exception -> 0x00d0, TryCatch #0 {Exception -> 0x00d0, blocks: (B:10:0x00ab, B:12:0x00b5, B:14:0x00bc, B:13:0x00ba), top: B:20:0x00ab }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b() {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.b0.b():java.lang.String");
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0062: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:25:0x0062 */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r2.<init>()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r2.append(r6)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L61
            r2.close()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L61
            r2.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r6 = move-exception
            r6.printStackTrace()
        L39:
            return r1
        L3a:
            r1 = move-exception
            goto L40
        L3c:
            r6 = move-exception
            goto L63
        L3e:
            r1 = move-exception
            r2 = r0
        L40:
            java.lang.String r3 = "Rom"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61
            r4.<init>()     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = "Unable to read prop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L61
            r4.append(r6)     // Catch: java.lang.Throwable -> L61
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L61
            android.util.Log.e(r3, r6, r1)     // Catch: java.lang.Throwable -> L61
            if (r2 == 0) goto L60
            r2.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r6 = move-exception
            r6.printStackTrace()
        L60:
            return r0
        L61:
            r6 = move-exception
            r0 = r2
        L63:
            if (r0 == 0) goto L6d
            r0.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r0 = move-exception
            r0.printStackTrace()
        L6d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.b0.b(java.lang.String):java.lang.String");
    }

    public static int c(String str) {
        if (str != null && str.startsWith("#")) {
            try {
                if (str.length() == 4) {
                    str = "#" + str.charAt(1) + str.charAt(1) + str.charAt(2) + str.charAt(2) + str.charAt(3) + str.charAt(3);
                }
                return Color.parseColor(str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    public static String a(String str, String str2, String str3) throws Exception {
        byte[] bArr;
        SMKeyAlg sMKeyAlg = new SMKeyAlg(Logger.getLogger("SmcryptoUtil"));
        int[] iArr = new int[1];
        if (str == null || str.equals("")) {
            bArr = null;
        } else {
            String upperCase = str.toUpperCase();
            int length = upperCase.length() / 2;
            char[] charArray = upperCase.toCharArray();
            byte[] bArr2 = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr2[i] = (byte) (a(charArray[i2 + 1]) | (a(charArray[i2]) << 4));
            }
            bArr = bArr2;
        }
        int length2 = bArr.length;
        byte[] bArr3 = new byte[length2 - 177];
        int iSafetyRecv = sMKeyAlg.SafetyRecv("sersecretkeyforv8offlinecode", str2, str3, bArr, length2, bArr3, iArr);
        if (iSafetyRecv == 8000) {
            return new String(bArr3, 0, iArr[0]);
        }
        throw new Exception("调用SafetyRecv方法失败:" + iSafetyRecv);
    }

    public static String a(String str, String str2) {
        if ((str == null || str.length() == 0) || str2 == null) {
            return str;
        }
        if (str2.isEmpty()) {
            return "";
        }
        int iIndexOf = str.indexOf(str2);
        return iIndexOf == -1 ? str : str.substring(0, iIndexOf);
    }
}
