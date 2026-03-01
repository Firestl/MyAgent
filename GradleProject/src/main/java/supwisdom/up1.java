package supwisdom;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes2.dex */
public final class up1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    public static Application f9429a;

    public static Application a() {
        Application application = f9429a;
        if (application != null) {
            return application;
        }
        return null;
    }

    public static String b() {
        return f9429a == null ? "" : a().getPackageName();
    }

    public static int c() {
        Application application = f9429a;
        if (application == null) {
            return 0;
        }
        try {
            return application.getPackageManager().getPackageInfo(f9429a.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
