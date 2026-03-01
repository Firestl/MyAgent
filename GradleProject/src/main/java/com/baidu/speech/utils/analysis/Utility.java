package com.baidu.speech.utils.analysis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.baidu.speech.VoiceRecognitionService;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.Md5Utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class Utility {
    public static final String KEY_LSAT_DOWNLOAD_STAT_PERIOD = "last_download_stat_period";
    public static final String KEY_LSAT_UPLOAD_STAT_TIME = "last_upload_stat_time";
    public static final String SDK_VERSION_NAME = "1.0.0-20140804";
    public static final String SHARED_PREFERENCE_NAME = "tts";

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String encryptBASE64(String str) {
        if (str != null && str.length() != 0) {
            try {
                byte[] bytes = str.getBytes("utf-8");
                return new String(Base64.encode(bytes, 0, bytes.length, 0), "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String encryptBASE64(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            try {
                return new String(Base64.encode(bArr, 0, bArr.length, 0), "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] encryptGZIP(String str) {
        if (str != null && str.length() != 0) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(str.getBytes("utf-8"));
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    public static String getAppName(Context context) {
        return getApplicationName(context);
    }

    public static String getApplicationName(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    public static int getLastDownloadStatPeriod(Context context) {
        return context.getSharedPreferences("tts", 0).getInt(KEY_LSAT_DOWNLOAD_STAT_PERIOD, 0);
    }

    public static long getLastUploadStatTime(Context context) {
        return context.getSharedPreferences("tts", 0).getLong(KEY_LSAT_UPLOAD_STAT_TIME, 0L);
    }

    public static int getLicenseDataFromFile(String str, byte[] bArr) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                } catch (FileNotFoundException e2) {
                    e = e2;
                } catch (IOException e3) {
                    e = e3;
                }
                try {
                    int i = fileInputStream.read(bArr);
                    try {
                        fileInputStream.close();
                        return i;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        return i;
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return -1;
                } catch (IOException e6) {
                    e = e6;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return -1;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e8) {
                e8.printStackTrace();
                return -1;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static int getNetType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || !"wifi".equals(activeNetworkInfo.getTypeName().toLowerCase())) ? 3 : 1;
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static String getOS() {
        return "Android";
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getPlatform(Context context) {
        return getOS() + "&" + Build.MODEL + "&" + Build.VERSION.RELEASE + "&" + Build.VERSION.SDK_INT + "&" + getNetType(context);
    }

    public static String getScreen(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + Operators.MUL + displayMetrics.heightPixels;
    }

    public static String getSdkName() {
        return "离线TTS SDK";
    }

    public static String getSdkVersion() {
        return VoiceRecognitionService.getSdkVersion();
    }

    public static String getSignatureMD5(Context context) {
        try {
            return parseSignature(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static String parseSignature(byte[] bArr) {
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(x509Certificate.getEncoded());
            return toHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (CertificateException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void setLastDownloadStatPeriod(Context context, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("tts", 0).edit();
        editorEdit.putInt(KEY_LSAT_DOWNLOAD_STAT_PERIOD, i);
        editorEdit.commit();
    }

    public static void setLastUploadStatTime(Context context, long j) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("tts", 0).edit();
        editorEdit.putLong(KEY_LSAT_UPLOAD_STAT_TIME, j);
        editorEdit.commit();
    }

    public static String toHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }
}
