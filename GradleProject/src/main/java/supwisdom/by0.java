package supwisdom;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class by0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f7123a = false;

    public static void a(String str, Object... objArr) {
        a(3, null, str, objArr);
    }

    public static void b(String str, Object... objArr) {
        a(6, null, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        a(4, null, str, objArr);
    }

    public static void d(String str, Object... objArr) {
        a(5, null, str, objArr);
    }

    public static void a(Throwable th) {
        a(6, th, null, new Object[0]);
    }

    public static void a(int i, Throwable th, String str, Object... objArr) {
        if (f7123a) {
            return;
        }
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        if (th != null) {
            if (str == null) {
                str = th.getMessage();
            }
            str = String.format("%1$s\n%2$s", str, Log.getStackTraceString(th));
        }
        Log.println(i, zw0.d, str);
    }
}
