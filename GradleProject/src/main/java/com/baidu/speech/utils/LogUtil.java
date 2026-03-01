package com.baidu.speech.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtil {
    public static final int DEBUG = 3;
    public static final boolean DEBUG_MODE = false;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final String LOGTAG = "BDSPEECH";
    public static final int OFF = 7;
    public static final String PREFIX = "[BDASR_LOG] ";
    public static final String TAG = "LogUtil";
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public static int logLevel = 7;

    static {
        setLogLevel(7);
    }

    public static void Test(String str) {
        if (3 < logLevel) {
            return;
        }
        Log.d(TAG, str);
    }

    public static String argsToString(String... strArr) {
        if (strArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strArr) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static void d(String str, String str2, Object... objArr) {
        if (Log.isLoggable(LOGTAG, 3) || Log.isLoggable(str, 3) || 3 >= logLevel) {
            Log.d(PREFIX + str, String.format(str2, objArr));
        }
    }

    public static void d(String str, Throwable th, String... strArr) {
        if (Log.isLoggable(LOGTAG, 3) || Log.isLoggable(str, 3) || 3 >= logLevel) {
            Log.d(PREFIX + str, argsToString(strArr), th);
        }
    }

    public static void d(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            strArr = new String[]{str};
            str = TAG;
        }
        if (Log.isLoggable(LOGTAG, 3) || Log.isLoggable(str, 3) || 3 >= logLevel) {
            Log.d(PREFIX + str, argsToString(strArr));
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (Log.isLoggable(LOGTAG, 6) || Log.isLoggable(str, 6) || 6 >= logLevel) {
            Log.e(PREFIX + str, String.format(str2, objArr));
        }
    }

    public static void e(String str, Throwable th, String... strArr) {
        if (Log.isLoggable(LOGTAG, 6) || Log.isLoggable(str, 6) || 6 >= logLevel) {
            Log.e(PREFIX + str, argsToString(strArr), th);
        }
    }

    public static void e(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            strArr = new String[]{str};
            str = TAG;
        }
        if (Log.isLoggable(LOGTAG, 6) || Log.isLoggable(str, 6) || 6 >= logLevel) {
            Log.e(PREFIX + str, argsToString(strArr));
        }
    }

    public static void e(Throwable th) {
        if (Log.isLoggable(LOGTAG, 6) || 6 >= logLevel) {
            printStrackTrace(th);
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (Log.isLoggable(LOGTAG, 4) || Log.isLoggable(str, 4) || 4 >= logLevel) {
            Log.i(PREFIX + str, String.format(str2, objArr));
        }
    }

    public static void i(String str, Throwable th, String... strArr) {
        if (Log.isLoggable(LOGTAG, 4) || Log.isLoggable(str, 4) || 4 >= logLevel) {
            Log.i(PREFIX + str, argsToString(strArr), th);
        }
    }

    public static void i(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            strArr = new String[]{str};
            str = TAG;
        }
        if (Log.isLoggable(LOGTAG, 4) || Log.isLoggable(str, 4) || 4 >= logLevel) {
            Log.i(PREFIX + str, argsToString(strArr));
        }
    }

    public static boolean isFilteredLog(int i, String str) {
        return str.contains("") && i == 3;
    }

    public static boolean isLoggable(int i) {
        return logLevel >= i;
    }

    public static void printStrackTrace(Throwable th) {
        if (th == null || th.getStackTrace() == null) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append(th.getLocalizedMessage());
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            stringBuffer.append("\n");
            stringBuffer.append(stackTraceElement.toString());
        }
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }

    public static void setLogLevel(String str) {
        int i;
        if ("VERBOSE".equals(str)) {
            i = 2;
        } else if ("DEBUG".equals(str)) {
            i = 3;
        } else if ("INFO".equals(str)) {
            i = 4;
        } else if ("WARN".equals(str)) {
            i = 5;
        } else {
            if (!"ERROR".equals(str)) {
                if ("OFF".equals(str)) {
                    i = 7;
                }
                Log.i(TAG, "Changing log level to " + logLevel + "(" + str + ")");
            }
            i = 6;
        }
        logLevel = i;
        Log.i(TAG, "Changing log level to " + logLevel + "(" + str + ")");
    }

    public static void v(String str, String str2, Object... objArr) {
        if (Log.isLoggable(LOGTAG, 2) || Log.isLoggable(str, 2) || 2 >= logLevel) {
            Log.v(PREFIX + str, String.format(str2, objArr));
        }
    }

    public static void v(String str, Throwable th, String... strArr) {
        if (Log.isLoggable(LOGTAG, 2) || Log.isLoggable(str, 2) || 2 >= logLevel) {
            Log.v(PREFIX + str, argsToString(strArr), th);
        }
    }

    public static void v(String str, String... strArr) {
        if (Log.isLoggable(LOGTAG, 2) || Log.isLoggable(str, 2) || 2 >= logLevel) {
            Log.v(PREFIX + str, argsToString(strArr));
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (Log.isLoggable(LOGTAG, 5) || Log.isLoggable(str, 5) || 5 >= logLevel) {
            Log.w(PREFIX + str, String.format(str2, objArr));
        }
    }

    public static void w(String str, Throwable th, String... strArr) {
        if (Log.isLoggable(LOGTAG, 5) || Log.isLoggable(str, 5) || 5 >= logLevel) {
            Log.w(PREFIX + str, argsToString(strArr), th);
        }
    }

    public static void w(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            strArr = new String[]{str};
            str = TAG;
        }
        if (Log.isLoggable(LOGTAG, 5) || Log.isLoggable(str, 5) || 5 >= logLevel) {
            Log.w(PREFIX + str, argsToString(strArr));
        }
    }
}
