package com.baidu.speech.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.umeng.ccg.a;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class Utility {
    public static final int BYTES_PER_SAMPLE_16BIT = 2;
    public static final int BYTES_PER_SAMPLE_8BIT = 1;
    public static final int BYTES_PER_SHORT = 2;
    public static int EVR_NETWORK_TYPE_2G = 1;
    public static int EVR_NETWORK_TYPE_3G = 2;
    public static int EVR_NETWORK_TYPE_4G = 3;
    public static int EVR_NETWORK_TYPE_NO = 0;
    public static int EVR_NETWORK_TYPE_WIFI = 4;
    public static final String TAG = "Utility";
    public static final int THOUSAND_DIV = 1000;
    public static ConnectivityManager mConnManager;
    public static int maxCpuFreq;

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String fun(Exception exc) {
        StackTraceElement[] stackTrace = exc.getStackTrace();
        if (stackTrace == null) {
            return "";
        }
        return stackTrace[0].getMethodName() + "()";
    }

    public static String generatePlatformString() {
        StringBuilder sb = new StringBuilder("Android");
        sb.append('&');
        try {
            sb.append(URLEncoder.encode(Build.MODEL, "utf-8"));
            sb.append('&');
            sb.append(URLEncoder.encode(Build.VERSION.RELEASE, "utf-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        sb.append('&');
        sb.append(Build.VERSION.SDK_INT);
        return sb.toString();
    }

    public static int getCpuInfo() {
        try {
            String str = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 1024);
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.indexOf("BogoMIPS") != -1) {
                    str = line.split("\\s+")[2];
                    break;
                }
            }
            bufferedReader.close();
            return (int) (Float.parseFloat(str.trim()) * 1000.0f);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getFileName(Exception exc) {
        StackTraceElement[] stackTrace = exc.getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        }
        return stackTrace[0].getFileName();
    }

    public static String getLineNumber(Exception exc) {
        StackTraceElement[] stackTrace = exc.getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        }
        return stackTrace[0].getFileName() + Constants.COLON_SEPARATOR + stackTrace[0].getLineNumber();
    }

    public static int getMaxCpuFreq() {
        try {
            String str = "";
            if (maxCpuFreq != 0) {
                return maxCpuFreq;
            }
            if (isRunningEmulator()) {
                Process processStart = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start();
                InputStream inputStream = processStart.getInputStream();
                byte[] bArr = new byte[24];
                while (inputStream.read(bArr) != -1) {
                    str = str + new String(bArr);
                }
                inputStream.close();
                processStart.destroy();
            } else {
                FileReader fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                bufferedReader.close();
                fileReader.close();
                str = line;
            }
            int cpuInfo = getCpuInfo();
            if (TextUtils.isEmpty(str)) {
                return cpuInfo;
            }
            int i = Integer.parseInt(str.trim());
            if (i >= cpuInfo) {
                cpuInfo = i;
            }
            maxCpuFreq = cpuInfo;
            return cpuInfo;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static int getStatusType(int i) {
        return i & (-65536);
    }

    public static int getVoiceDataSizeInShort(int i, int i2, int i3) {
        if (i3 == 2 || i3 == 3) {
            return (((i * i2) * (i3 == 3 ? 1 : 2)) / 1000) / 2;
        }
        throw new IllegalArgumentException("audio format invalid");
    }

    @SuppressLint({"DefaultLocale"})
    public static int getWifiOr2gOr3G(Context context) {
        int i = EVR_NETWORK_TYPE_NO;
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
                if (!isNetworkPerission(context)) {
                    return i;
                }
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                    if (!activeNetworkInfo.getTypeName().toLowerCase().equals("wifi")) {
                        i = EVR_NETWORK_TYPE_2G;
                        switch (activeNetworkInfo.getSubtype()) {
                            case 3:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 12:
                            case 14:
                            case 15:
                                i = EVR_NETWORK_TYPE_3G;
                                break;
                            case 13:
                                i = EVR_NETWORK_TYPE_4G;
                                break;
                        }
                    } else {
                        i = EVR_NETWORK_TYPE_WIFI;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return i;
    }

    public static void init(Context context) {
        if (context != null) {
            mConnManager = (ConnectivityManager) context.getSystemService("connectivity");
        }
    }

    public static boolean is2G(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (!isNetworkPerission(context) || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting() || activeNetworkInfo.getTypeName().toLowerCase().equals("wifi")) {
                return false;
            }
            int subtype = activeNetworkInfo.getSubtype();
            if (subtype == 1 || subtype == 2 || subtype == 4 || subtype == 7 || subtype == 11 || subtype == 16) {
                return true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isNetworkPerission(Context context) {
        try {
            return context.getPackageManager().checkPermission(DefaultConnectivityMonitorFactory.NETWORK_PERMISSION, context.getPackageName()) == 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isRunningEmulator() {
        return Build.MODEL.equals(a.r) || Build.MODEL.equals("google_sdk");
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean isUsingWifi() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = mConnManager;
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        return "wifi".equals(activeNetworkInfo.getTypeName().toLowerCase());
    }

    public static boolean isUsingWifi(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (isNetworkPerission(context) && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                if ("wifi".equals(activeNetworkInfo.getTypeName().toLowerCase(Locale.US))) {
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 1;
    }

    public static String urlEncode(String str, String str2) {
        try {
            return !TextUtils.isEmpty(str) ? URLEncoder.encode(str, str2) : str;
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }
}
