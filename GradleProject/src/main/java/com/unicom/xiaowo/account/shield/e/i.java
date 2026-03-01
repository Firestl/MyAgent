package com.unicom.xiaowo.account.shield.e;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.taobao.weex.utils.FunctionParser;
import io.dcloud.common.util.Md5Utils;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f5554a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f'};

    public static int a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() == 1) {
                if (a(connectivityManager)) {
                    f.b("Data and WIFI");
                    return 1;
                }
                f.b("Only WIFI");
                return 2;
            }
            if (activeNetworkInfo.getType() == 0) {
                f.b("Only Data");
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (TextUtils.isEmpty(extraInfo)) {
                    return 0;
                }
                g.d(extraInfo);
                return 0;
            }
            return -1;
        }
        return -1;
    }

    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < bArrDigest.length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        PublicKey publicKeyE = e(str2);
        Cipher cipher = Cipher.getInstance(new String(com.unicom.xiaowo.account.shield.a.b.b("UlNBL0VDQi9QS0NTMVBhZGRpbmc=")));
        cipher.init(1, publicKeyE);
        return com.unicom.xiaowo.account.shield.a.b.a(cipher.doFinal(str.getBytes()));
    }

    public static String a(byte[] bArr) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM).digest(bArr);
            int length = bArrDigest.length;
            char[] cArr = new char[length * 2];
            for (int i = 0; i < length; i++) {
                int i2 = bArrDigest[i] & 15;
                int i3 = i * 2;
                cArr[i3] = f5554a[(bArrDigest[i] & 240) >> 4];
                cArr[i3 + 1] = f5554a[i2];
            }
            return new String(cArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean a(ConnectivityManager connectivityManager) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            boolean zBooleanValue = ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            f.a("data is on:" + zBooleanValue);
            return zBooleanValue;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static byte[] a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo.packageName.equals(str)) {
                return packageInfo.signatures[0].toByteArray();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(Context context) {
        try {
            return (String) context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context, String str) {
        try {
            return a(a(context, str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        int iIndexOf = str.indexOf("://");
        if (iIndexOf > 0) {
            str = str.substring(iIndexOf + 3);
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        int iIndexOf3 = str.indexOf(47);
        if (iIndexOf3 >= 0) {
            str = str.substring(0, iIndexOf3);
        }
        int iIndexOf4 = str.indexOf(63);
        return iIndexOf4 >= 0 ? str.substring(0, iIndexOf4) : str;
    }

    public static int c(String str) {
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            return (address[0] & 255) | ((address[3] & 255) << 24) | ((address[2] & 255) << 16) | ((address[1] & 255) << 8);
        } catch (UnknownHostException unused) {
            return -1;
        }
    }

    public static String c(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.getName().toLowerCase().contains("rmnet")) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (!inetAddressNextElement.isLoopbackAddress() && !inetAddressNextElement.isLinkLocalAddress()) {
                            if (inetAddressNextElement instanceof Inet4Address) {
                                sb.append(inetAddressNextElement.getHostAddress());
                                sb.append(",");
                            }
                            if (inetAddressNextElement instanceof Inet6Address) {
                                sb2.append(inetAddressNextElement.getHostAddress());
                                sb2.append(",");
                            }
                        }
                    }
                }
            }
            if (sb.length() > 0) {
                sb = sb.delete(sb.length() - 1, sb.length());
            }
            if (sb2.length() > 0) {
                sb2 = sb2.delete(sb2.length() - 1, sb2.length());
            }
            String str = sb.toString() + "|" + sb2.toString();
            f.b(str);
            if (!TextUtils.isEmpty(sb.toString())) {
                g.e(sb.toString());
            }
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String d(String str) {
        try {
            return a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbRkBR4leALApkWRp2ng8zJ2WgI7YEqtMwW9Q1tmRzDLPNhH0ugACfbiStBG4ybdYNHzRlxvOwQ7R0MeN56qEPsv6qieg/HiRXBnQ2hQ2hypo9JHqHx8BX54ESZ+BIf0imjGTcxtHvbzYA04ckmH5Enl2Pkd+R/RZuMK589C7KwQIDAQAB");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static PublicKey e(String str) {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.unicom.xiaowo.account.shield.a.b.b(str)));
    }
}
