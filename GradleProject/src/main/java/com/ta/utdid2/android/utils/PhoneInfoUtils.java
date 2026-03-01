package com.ta.utdid2.android.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneInfoUtils {
    public static String getImei(Context context) {
        String deviceId = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                }
            } catch (Exception unused) {
            }
        }
        return StringUtils.isEmpty(deviceId) ? getUniqueID() : deviceId;
    }

    public static String getImsi(Context context) {
        String subscriberId = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                }
            } catch (Exception unused) {
            }
        }
        return StringUtils.isEmpty(subscriberId) ? getUniqueID() : subscriberId;
    }

    public static final String getUniqueID() {
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int iNanoTime = (int) System.nanoTime();
        int iNextInt = new Random().nextInt();
        int iNextInt2 = new Random().nextInt();
        byte[] bytes = IntUtils.getBytes(iCurrentTimeMillis);
        byte[] bytes2 = IntUtils.getBytes(iNanoTime);
        byte[] bytes3 = IntUtils.getBytes(iNextInt);
        byte[] bytes4 = IntUtils.getBytes(iNextInt2);
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(bytes3, 0, bArr, 8, 4);
        System.arraycopy(bytes4, 0, bArr, 12, 4);
        return Base64.encodeToString(bArr, 2);
    }
}
