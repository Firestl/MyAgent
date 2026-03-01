package supwisdom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.RecentlyNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class qh0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Boolean f8924a;
    public static Boolean b;
    public static Boolean c;
    public static Boolean d;

    public static boolean a(@RecentlyNonNull Context context) {
        return a(context.getPackageManager());
    }

    @TargetApi(20)
    public static boolean b(@RecentlyNonNull Context context) {
        return b(context.getPackageManager());
    }

    @TargetApi(26)
    public static boolean c(@RecentlyNonNull Context context) {
        if (!b(context)) {
            return false;
        }
        if (th0.e()) {
            return e(context) && !th0.f();
        }
        return true;
    }

    public static boolean d(@RecentlyNonNull Context context) {
        if (c == null) {
            c = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return c.booleanValue();
    }

    @TargetApi(21)
    public static boolean e(Context context) {
        if (b == null) {
            b = Boolean.valueOf(th0.d() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return b.booleanValue();
    }

    public static boolean a(@RecentlyNonNull PackageManager packageManager) {
        if (d == null) {
            d = Boolean.valueOf(th0.f() && packageManager.hasSystemFeature("android.hardware.type.automotive"));
        }
        return d.booleanValue();
    }

    @TargetApi(20)
    public static boolean b(@RecentlyNonNull PackageManager packageManager) {
        if (f8924a == null) {
            f8924a = Boolean.valueOf(th0.c() && packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        return f8924a.booleanValue();
    }
}
