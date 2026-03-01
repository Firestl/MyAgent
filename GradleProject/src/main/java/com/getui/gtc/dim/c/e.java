package com.getui.gtc.dim.c;

import android.annotation.SuppressLint;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import io.dcloud.common.util.JSUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static Object a(Parcel parcel) {
        try {
            int iDataPosition = parcel.dataPosition();
            int i = parcel.readInt();
            parcel.setDataPosition(iDataPosition);
            if (i > 100) {
                return null;
            }
            Class<?> cls = Class.forName("android.net.wifi.WifiSsid");
            return ((Parcelable.Creator) cls.getDeclaredField("CREATOR").get(cls)).createFromParcel(parcel);
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("getWifiSsid", th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x008e, code lost:
    
        throw new java.lang.UnsupportedOperationException("cannot read wifiInfo,API>33");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.net.wifi.WifiInfo r8) throws org.json.JSONException {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 >= r1) goto Lb
            org.json.JSONObject r0 = b(r8)
            goto L70
        Lb:
            android.os.Parcel r2 = android.os.Parcel.obtain()
            r3 = 0
            r8.writeToParcel(r2, r3)     // Catch: java.lang.Throwable -> L8f
            r4 = 1
            r5 = 0
        L15:
            r6 = 0
            if (r0 < r1) goto L87
            r7 = 33
            if (r0 > r7) goto L87
            r2.setDataPosition(r3)     // Catch: java.lang.Throwable -> L8f
            r2.readInt()     // Catch: java.lang.Throwable -> L8f
            r2.readInt()     // Catch: java.lang.Throwable -> L8f
            r2.readInt()     // Catch: java.lang.Throwable -> L8f
            r7 = 29
            if (r0 < r7) goto L32
            r2.readInt()     // Catch: java.lang.Throwable -> L8f
            r2.readInt()     // Catch: java.lang.Throwable -> L8f
        L32:
            if (r5 == 0) goto L37
            r2.readInt()     // Catch: java.lang.Throwable -> L8f
        L37:
            r2.readInt()     // Catch: java.lang.Throwable -> L8f
            byte r7 = r2.readByte()     // Catch: java.lang.Throwable -> L8f
            if (r7 != r4) goto L43
            r2.createByteArray()     // Catch: java.lang.Throwable -> L8f
        L43:
            int r7 = r2.readInt()     // Catch: java.lang.Throwable -> L8f
            if (r7 != r4) goto L4d
            java.lang.Object r6 = a(r2)     // Catch: java.lang.Throwable -> L8f
        L4d:
            if (r5 != 0) goto L54
            if (r6 == 0) goto L52
            goto L54
        L52:
            r5 = 1
            goto L15
        L54:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L8f
            r0.<init>()     // Catch: java.lang.Throwable -> L8f
            if (r6 == 0) goto L64
            java.lang.String r1 = r2.readString()     // Catch: java.lang.Throwable -> L8f
            java.lang.String r3 = "BSSID"
            r0.put(r3, r1)     // Catch: java.lang.Throwable -> L8f
        L64:
            java.lang.String r1 = "SSID"
            java.lang.String r3 = a(r6)     // Catch: java.lang.Throwable -> L8f
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L8f
            r2.recycle()
        L70:
            int r1 = r8.getRssi()
            java.lang.String r2 = "rssi"
            r0.put(r2, r1)
            java.lang.String r8 = r8.toString()
            java.lang.String r1 = "toString"
            r0.put(r1, r8)
            java.lang.String r8 = r0.toString()
            return r8
        L87:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException     // Catch: java.lang.Throwable -> L8f
            java.lang.String r0 = "cannot read wifiInfo,API>33"
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L8f
            throw r8     // Catch: java.lang.Throwable -> L8f
        L8f:
            r8 = move-exception
            r2.recycle()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.e.a(android.net.wifi.WifiInfo):java.lang.String");
    }

    public static String a(Object obj) {
        if (obj != null) {
            String string = obj.toString();
            if (!TextUtils.isEmpty(string)) {
                return JSUtil.QUOTE + string + JSUtil.QUOTE;
            }
            String strB = b(obj);
            if (strB != null) {
                return strB;
            }
        }
        return "<unknown ssid>";
    }

    public static String b(Object obj) {
        Class<?> cls = obj.getClass();
        try {
            if (Build.VERSION.SDK_INT < 28) {
                Method declaredMethod = cls.getDeclaredMethod("getHexString", new Class[0]);
                declaredMethod.setAccessible(true);
                return (String) declaredMethod.invoke(obj, new Object[0]);
            }
            Method declaredMethod2 = cls.getDeclaredMethod("getOctets", new Class[0]);
            declaredMethod2.setAccessible(true);
            byte[] bArr = (byte[]) declaredMethod2.invoke(obj, new Object[0]);
            String str = "0x";
            for (byte b : bArr) {
                str = str + String.format(Locale.US, "%02x", Byte.valueOf(b));
            }
            return bArr.length > 0 ? str : "<unknown ssid>";
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("getHexString ", th);
            return "<unknown ssid>";
        }
    }

    @SuppressLint({"SoonBlockedPrivateApi", "DiscouragedPrivateApi"})
    public static JSONObject b(WifiInfo wifiInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            Field declaredField = WifiInfo.class.getDeclaredField("mBSSID");
            declaredField.setAccessible(true);
            Field declaredField2 = WifiInfo.class.getDeclaredField("mWifiSsid");
            declaredField2.setAccessible(true);
            jSONObject.put("BSSID", declaredField.get(wifiInfo));
            jSONObject.put("SSID", a(declaredField2.get(wifiInfo)));
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.a("wifiInfo getBelow28", th);
        }
        return jSONObject;
    }
}
