package supwisdom;

import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Window;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class e0 {
    public static void a(Activity activity, boolean z, int i, boolean z2) {
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        int i4 = 1280;
        String property = "";
        boolean z3 = false;
        if (i3 < 23) {
            if (i3 >= 21) {
                activity.getWindow().clearFlags(67108864);
                if (z) {
                    activity.getWindow().addFlags(Integer.MIN_VALUE);
                    activity.getWindow().getDecorView().setSystemUiVisibility(1280);
                    activity.getWindow().setStatusBarColor(0);
                } else {
                    activity.getWindow().addFlags(Integer.MIN_VALUE);
                    activity.getWindow().setStatusBarColor(i);
                }
                if (b0.a("OPPO") && (i2 = Build.VERSION.SDK_INT) < 23 && i2 >= 22) {
                    if (b0.d == null) {
                        b0.a("");
                    }
                    if ("V3.0".equals(b0.d)) {
                        activity.getWindow().addFlags(Integer.MIN_VALUE);
                        int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
                        activity.getWindow().getDecorView().setSystemUiVisibility(z2 ? systemUiVisibility | 16 : systemUiVisibility & (-17));
                    }
                }
                c0.a(activity, z2);
                return;
            }
            return;
        }
        if (z) {
            activity.getWindow().setStatusBarColor(0);
        } else {
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().setStatusBarColor(i);
            i4 = 0;
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                property = properties.getProperty("ro.miui.version.code_time", "");
            } catch (Exception unused) {
            }
            if (TextUtils.isEmpty(property) || Build.VERSION.SDK_INT != 23 || Long.parseLong(property) < 1499904000) {
                Class<?> cls = activity.getWindow().getClass();
                try {
                    Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                    int i5 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                    Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                    Window window = activity.getWindow();
                    Object[] objArr = new Object[2];
                    objArr[0] = Integer.valueOf(z2 ? i5 : 0);
                    objArr[1] = Integer.valueOf(i5);
                    method.invoke(window, objArr);
                    z3 = true;
                } catch (Exception unused2) {
                }
            }
        }
        if (z3 || c0.b(activity, z2)) {
            return;
        }
        if (z2) {
            i4 |= 8192;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(i4);
    }
}
