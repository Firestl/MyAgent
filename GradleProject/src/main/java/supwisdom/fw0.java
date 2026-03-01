package supwisdom;

import android.util.Log;

/* JADX INFO: compiled from: OkLogger.java */
/* JADX INFO: loaded from: classes2.dex */
public class fw0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f7662a = true;

    public static void a(String str, String str2) {
        if (f7662a) {
            Log.v(str, str2);
        }
    }

    public static void a(Throwable th) {
        if (!f7662a || th == null) {
            return;
        }
        th.printStackTrace();
    }
}
