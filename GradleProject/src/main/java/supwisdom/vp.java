package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class vp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static cp f9537a;

    public static void a(String str) {
        try {
            cp cpVar = f9537a;
            if (cpVar != null) {
                cpVar.a(String.format("[AlipaySDK] %s %s", new SimpleDateFormat("hh:mm:ss.SSS", Locale.getDefault()).format(new Date()), str));
            }
        } catch (Throwable unused) {
        }
    }

    public static void b(String str, String str2) {
        a(e(str, str2));
    }

    public static void c(String str, String str2) {
        a(e(str, str2));
    }

    public static void d(String str, String str2) {
        a(e(str, str2));
    }

    public static String e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return String.format("[%s][%s]", str, str2);
    }

    public static String b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void a(String str, String str2) {
        a(e(str, str2));
    }

    public static void a(String str, String str2, Throwable th) {
        a(e(str, str2) + Operators.SPACE_STR + b(th));
    }

    public static void a(Throwable th) {
        if (th == null) {
            return;
        }
        try {
            a(b(th));
        } catch (Throwable unused) {
        }
    }
}
