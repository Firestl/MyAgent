package supwisdom;

import android.os.Build;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.util.Locale;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ky0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f8220a = 4;
    public static b b = new a();

    /* JADX INFO: compiled from: Proguard */
    public static class a implements b {
        @Override // supwisdom.ky0.b
        public void a(int i, String str, String str2) {
            if (ky0.f8220a > i) {
                return;
            }
            if (i == 2) {
                Log.v(str, str2);
                return;
            }
            if (i == 3) {
                Log.d(str, str2);
                return;
            }
            if (i == 4) {
                Log.i(str, str2);
            } else if (i == 5) {
                Log.w(str, str2);
            } else {
                if (i != 6) {
                    return;
                }
                Log.e(str, str2);
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public interface b {
        void a(int i, String str, String str2);
    }

    static {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("VERSION.RELEASE:[");
            sb.append(Build.VERSION.RELEASE);
            sb.append("] VERSION.CODENAME:[");
            sb.append(Build.VERSION.CODENAME);
            sb.append("] VERSION.INCREMENTAL:[");
            sb.append(Build.VERSION.INCREMENTAL);
            sb.append("] BOARD:[");
            sb.append(Build.BOARD);
            sb.append("] DEVICE:[");
            sb.append(Build.DEVICE);
            sb.append("] DISPLAY:[");
            sb.append(Build.DISPLAY);
            sb.append("] FINGERPRINT:[");
            sb.append(Build.FINGERPRINT);
            sb.append("] HOST:[");
            sb.append(Build.HOST);
            sb.append("] MANUFACTURER:[");
            sb.append(Build.MANUFACTURER);
            sb.append("] MODEL:[");
            sb.append(Build.MODEL);
            sb.append("] PRODUCT:[");
            sb.append(Build.PRODUCT);
            sb.append("] TAGS:[");
            sb.append(Build.TAGS);
            sb.append("] TYPE:[");
            sb.append(Build.TYPE);
            sb.append("] USER:[");
            sb.append(Build.USER);
            sb.append(Operators.ARRAY_END_STR);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        sb.toString();
    }

    public static void a(String str, String str2) {
        if (b == null) {
            return;
        }
        b.a(4, str, a(str2, new Throwable().getStackTrace()[1]));
    }

    public static String a(String str, StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return String.format(Locale.CHINA, "[%s:%s:%d] %s", className.substring(className.lastIndexOf(46) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()), str);
    }
}
