package supwisdom;

import android.util.Log;

/* JADX INFO: compiled from: TrayLog.java */
/* JADX INFO: loaded from: classes3.dex */
public class vr1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f9541a = "Tray";
    public static boolean b = Log.isLoggable("Tray", 2);

    public static void a(String str) {
        if (str == null) {
            str = "";
        }
        Log.d(f9541a, str);
    }

    public static void b(String str) {
        if (b) {
            if (str == null) {
                str = "";
            }
            Log.v(f9541a, str);
        }
    }

    public static void c(String str) {
        if (str == null) {
            str = "";
        }
        Log.w(f9541a, str);
    }
}
