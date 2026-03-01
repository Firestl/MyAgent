package com.baidu.idl.license;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.statistics.idtracking.b;
import io.dcloud.common.util.Md5Utils;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class LicenseDevice {
    public static final String KEY_DEVICE = "com.baidu.face.deviceid";
    public static final String KEY_FACE = "com.baidu.face";
    public static final String KEY_IMEI = "com.baidu.face.i";

    public static String decrypt(String str, String str2) throws Exception {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("ASCII"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec("01251500ascfacei".getBytes()));
            try {
                return new String(cipher.doFinal(Base64.decode(str, 2)));
            } catch (Exception unused) {
                return "";
            }
        } catch (Exception e2) {
            System.out.println(e2.toString());
            return "";
        }
    }

    public static String encrypt(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec("01251500ascfacei".getBytes()));
        return Base64.encodeToString(cipher.doFinal(str.getBytes()), 2);
    }

    public static String getAndroidID(Context context) {
        return Settings.System.getString(context.getContentResolver(), b.f5435a);
    }

    public static String getDeviceCode(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (telephonyManager == null || context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) ? "" : telephonyManager.getDeviceId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getDeviceID(Context context) {
        String androidID = getAndroidID(context);
        if (Build.VERSION.SDK_INT >= 23) {
            return md5((KEY_FACE + androidID).getBytes());
        }
        try {
            String imei = getImei(context);
            if (TextUtils.isEmpty(imei)) {
                return md5((getMacAddr() + androidID).getBytes());
            }
            String string = Settings.System.getString(context.getContentResolver(), KEY_DEVICE);
            if (!TextUtils.isEmpty(string)) {
                try {
                    String strDecrypt = decrypt(string, imei + 1);
                    if (!TextUtils.isEmpty(strDecrypt)) {
                        return strDecrypt;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            String strMd5 = md5((imei + androidID + UUID.randomUUID()).getBytes());
            StringBuilder sb = new StringBuilder();
            sb.append(imei);
            sb.append(1);
            Settings.System.putString(context.getContentResolver(), KEY_DEVICE, encrypt(strMd5, sb.toString()));
            return strMd5;
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public static String getImei(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), KEY_IMEI);
        if (!TextUtils.isEmpty(string)) {
            try {
                String strDecrypt = decrypt(string, "01251500ascfacei");
                if (!TextUtils.isEmpty(strDecrypt)) {
                    if (strDecrypt.length() == 15) {
                        return strDecrypt;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String deviceCode = getDeviceCode(context);
        if (!TextUtils.isEmpty(deviceCode)) {
            try {
                Settings.System.putString(context.getContentResolver(), KEY_IMEI, encrypt(deviceCode, "01251500ascfacei"));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return deviceCode;
    }

    public static String getMacAddr() {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress == null) {
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                for (byte b : hardwareAddress) {
                    sb.append(String.format("%02X:", Byte.valueOf(b)));
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            return "";
        }
        return "";
    }

    public static String md5(byte[] bArr) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM).digest(bArr);
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }
}
