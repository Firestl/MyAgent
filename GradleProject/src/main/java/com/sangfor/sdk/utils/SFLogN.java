package com.sangfor.sdk.utils;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import supwisdom.o81;
import supwisdom.yb1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SFLogN {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3960a = 1;
    public static boolean b;

    static {
        new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        b = false;
    }

    public static void a(int i, String str, String str2) {
        if (yb1.a() || !b) {
            Log.i(str, str2);
        } else {
            logNative(i, str, str2);
        }
    }

    @Deprecated
    public static void b(String str, String str2) {
        if (f3960a > 4) {
            return;
        }
        a(4, str, a(str2, new Throwable().getStackTrace()[1]));
    }

    public static void c(String str, String str2) {
        if (f3960a > 2) {
            return;
        }
        a(2, str, a(str2, new Throwable().getStackTrace()[1]));
    }

    public static native void closeLogNative();

    @Deprecated
    public static void d(String str, String str2) {
        if (f3960a > 3) {
            return;
        }
        a(3, str, a(str2, new Throwable().getStackTrace()[1]));
    }

    public static native String exportLogsNative(String str, String str2);

    public static native void flushLogNative();

    public static native boolean initLogFileNative(String str, int i);

    public static native boolean initXLogFileNative(String str, String str2, int i);

    public static native void logNative(int i, String str, String str2);

    public static native void setLogLevelNative(int i);

    public static native void setLogModeNative(int i);

    public static native void uploadLogsNative(String str, o81 o81Var);

    public static void a(String str, String str2) {
        if (f3960a > 1) {
            return;
        }
        a(1, str, a(str2, new Throwable().getStackTrace()[1]));
    }

    public static void b(String str, String str2, String str3) {
        if (f3960a > 3) {
            return;
        }
        a(3, str, a(str2, str3, new Throwable().getStackTrace()[1]));
    }

    public static void a(String str, String str2, String str3) {
        if (f3960a > 4) {
            return;
        }
        a(4, str, a(str2, str3, new Throwable().getStackTrace()[1]));
    }

    public static void b(String str, String str2, String str3, Throwable th) {
        if (f3960a > 3) {
            return;
        }
        a(3, str, a(str2, str3, new Throwable().getStackTrace()[1]) + a(th));
    }

    public static void a(String str, String str2, String str3, String str4, String str5) {
        if (f3960a > 4) {
            return;
        }
        a(4, str, a(str2, str3, str4, str5, new Throwable().getStackTrace()[1]));
    }

    @Deprecated
    public static void b(String str, String str2, Throwable th) {
        if (f3960a > 3) {
            return;
        }
        a(3, str, a(str2 + a(th), new Throwable().getStackTrace()[1]));
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "unknown exception";
        }
        StringBuilder sb = new StringBuilder();
        while (th != null) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            sb.append(String.format("%s%s", "\n", stringWriter.toString()));
            th = th.getCause();
        }
        return sb.toString();
    }

    public static int a() {
        return f3960a;
    }

    public static String a(String str, StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return String.format(Locale.CHINA, "[%s:%s:%d] %s", className.substring(className.lastIndexOf(46) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()), str);
    }

    public static void a(String str, String str2, Object... objArr) {
        if (f3960a > 2) {
            return;
        }
        a(2, str, a(String.format(str2, objArr), new Throwable().getStackTrace()[1]));
    }

    public static void a(String str, String str2, String str3, Throwable th) {
        if (f3960a > 4) {
            return;
        }
        a(4, str, a(str2, str3, new Throwable().getStackTrace()[1]) + a(th));
    }

    public static String a(String str, String str2, StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return String.format(Locale.CHINA, "[%s:%s:%d] %s; Reason:%s", className.substring(className.lastIndexOf(46) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()), str, str2);
    }

    @Deprecated
    public static void a(String str, String str2, Throwable th) {
        if (f3960a > 4) {
            return;
        }
        a(4, str, a(str2 + a(th), new Throwable().getStackTrace()[1]));
    }

    public static String a(String str, String str2, String str3, String str4, StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return String.format(Locale.CHINA, "[%s:%s:%d] %s; Reason:%s; Will:%s; HowTo:%s", className.substring(className.lastIndexOf(46) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()), str, str2, str3, str4);
    }
}
