package supwisdom;

import android.os.Build;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class mb1 {
    public static int a() {
        if (Build.VERSION.SDK_INT < 23) {
            return 0;
        }
        try {
            return Build.VERSION.PREVIEW_SDK_INT;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean c() {
        int i = Build.VERSION.SDK_INT;
        return (i == 25 && a() > 0) || i > 25;
    }
}
