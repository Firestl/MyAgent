package supwisdom;

import dc.squareup.okhttp3.internal.platform.AndroidPlatform;

/* JADX INFO: loaded from: classes.dex */
public class wt0 {
    public static void a(String str) {
        String strSubstring;
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                strSubstring = "";
                break;
            } else {
                if (!stackTrace[i].getClass().equals(wt0.class)) {
                    String className = stackTrace[i].getClassName();
                    strSubstring = className.substring(className.lastIndexOf(46) + 1);
                    break;
                }
                i++;
            }
        }
        a("HonorPush_" + strSubstring, str, null);
    }

    public static void a(String str, String str2, Throwable th) {
        if (str2.length() > 4000) {
            int i = 0;
            while (i < str2.length()) {
                int length = str2.length();
                int i2 = i + AndroidPlatform.MAX_LOG_LENGTH;
                if (length > i2) {
                    str2.substring(i, i2);
                } else {
                    str2.substring(i);
                }
                i = i2;
            }
        }
    }
}
