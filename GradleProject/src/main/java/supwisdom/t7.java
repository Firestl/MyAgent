package supwisdom;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

/* JADX INFO: compiled from: NotificationManagerCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class t7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9260a;
    public final NotificationManager b;

    static {
        new HashSet();
    }

    public t7(Context context) {
        this.f9260a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }

    public static t7 a(Context context) {
        return new t7(context);
    }

    public boolean a() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return this.b.areNotificationsEnabled();
        }
        if (i < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.f9260a.getSystemService("appops");
        ApplicationInfo applicationInfo = this.f9260a.getApplicationInfo();
        String packageName = this.f9260a.getApplicationContext().getPackageName();
        int i2 = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod(com.igexin.push.g.c.c, Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField(com.igexin.push.g.c.d).get(Integer.class)).intValue()), Integer.valueOf(i2), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }
}
