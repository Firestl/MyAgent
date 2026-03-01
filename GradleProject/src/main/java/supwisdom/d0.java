package supwisdom;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f7277a = false;

    public static String a(String str) {
        String str2;
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = "<unknown>";
                break;
            }
            if (!stackTrace[i].getClass().equals(d0.class)) {
                String className = stackTrace[i].getClassName();
                String strSubstring = className.substring(className.lastIndexOf(46) + 1);
                str2 = strSubstring.substring(strSubstring.lastIndexOf(36) + 1) + ".class(" + stackTrace[i].getLineNumber() + ")";
                break;
            }
            i++;
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str2, str);
    }

    public static void a(String str, String str2) {
        if (f7277a) {
            Log.d("virtual-card", str + " - " + str2);
        }
    }

    public static void b(String str) {
        if (f7277a) {
            Log.d("virtual-card", a(str));
        }
    }

    public static void b(String str, String str2) {
        if (f7277a) {
            Log.i("virtual-card", str + " - " + str2);
        }
    }

    public static void c(String str) {
        if (f7277a) {
            Log.i("virtual-card", a(str));
        }
    }
}
