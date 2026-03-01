package supwisdom;

import android.os.Build;
import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class th0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Boolean f9292a;

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 20;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean f() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean g() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static boolean h() {
        boolean z = false;
        if (!g()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 30 && Build.VERSION.CODENAME.equals("REL")) {
            return true;
        }
        if (!(Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'R' && Build.VERSION.CODENAME.charAt(0) <= 'Z')) {
            return false;
        }
        Boolean bool = f9292a;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            if ("google".equals(Build.BRAND) && !Build.ID.startsWith("RPP1") && !Build.ID.startsWith("RPP2") && Integer.parseInt(Build.VERSION.INCREMENTAL) >= 6301457) {
                z = true;
            }
            f9292a = Boolean.valueOf(z);
        } catch (NumberFormatException unused) {
            f9292a = true;
        }
        if (!f9292a.booleanValue()) {
            Log.w("PlatformVersion", "Build version must be at least 6301457 to support R in gmscore");
        }
        return f9292a.booleanValue();
    }
}
