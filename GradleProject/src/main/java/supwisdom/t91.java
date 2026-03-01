package supwisdom;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class t91 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static s91 f9268a;

    public static void a(String str, String str2, Throwable th) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.a(str, str2, th);
        } else {
            Log.e(str, a(str2, new Throwable().getStackTrace()[1]));
        }
    }

    public static void b(String str, String str2) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.d(str, str2);
        } else {
            Log.e(str, a(str2, new Throwable().getStackTrace()[1]));
        }
    }

    public static void c(String str, String str2) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.b(str, str2);
        } else {
            Log.i(str, a(str2, new Throwable().getStackTrace()[1]));
        }
    }

    public static void d(String str, String str2) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.a(str, str2);
        } else {
            Log.w(str, a(str2, new Throwable().getStackTrace()[1]));
        }
    }

    public static void a(String str, String str2, String str3) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.a(str, str2, str3);
        } else {
            Log.e(str, a(str2, str3, new Throwable().getStackTrace()[1]));
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.b(str, str2, objArr);
        } else {
            Log.w(str, a(String.format(str2, objArr), new Throwable().getStackTrace()[1]));
        }
    }

    public static void a(String str, String str2, String str3, Throwable th) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.a(str, str2, str3, th);
            return;
        }
        Log.e(str, a(str2, str3, new Throwable().getStackTrace()[1]) + a(th));
    }

    public static void b(String str, String str2, String str3) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.b(str, str2, str3);
        } else {
            Log.w(str, a(str2, str3, new Throwable().getStackTrace()[1]));
        }
    }

    public static void a(String str, String str2) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.c(str, str2);
        } else {
            Log.d(str, a(str2, new Throwable().getStackTrace()[1]));
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        s91 s91Var = f9268a;
        if (s91Var != null) {
            s91Var.a(str, str2, objArr);
        } else {
            Log.d(str, a(String.format(str2, objArr), new Throwable().getStackTrace()[1]));
        }
    }

    public static String a(String str, StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return String.format(Locale.CHINA, "[%s:%s:%d] %s", className.substring(className.lastIndexOf(46) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()), str);
    }

    public static String a(String str, String str2, StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return String.format(Locale.CHINA, "[%s:%s:%d] %s; Reason:%s", className.substring(className.lastIndexOf(46) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()), str, str2);
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
}
