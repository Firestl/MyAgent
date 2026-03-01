package supwisdom;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f7130a;
    public static Field b;
    public static int c;

    static {
        try {
            Activity.class.getMethod("setStatusBarDarkIcon", Integer.TYPE);
        } catch (NoSuchMethodException unused) {
        }
        try {
            f7130a = Activity.class.getMethod("setStatusBarDarkIcon", Boolean.TYPE);
        } catch (NoSuchMethodException unused2) {
        }
        try {
            b = WindowManager.LayoutParams.class.getField("statusBarColor");
        } catch (NoSuchFieldException unused3) {
        }
        try {
            c = View.class.getField("SYSTEM_UI_FLAG_LIGHT_STATUS_BAR").getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException unused4) {
        }
    }

    public static void a(Activity activity, boolean z) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Method method = f7130a;
        try {
            if (method != null) {
                method.invoke(activity, Boolean.valueOf(z));
                return;
            }
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT < 23) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = attributes.getClass().getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                declaredField.setAccessible(true);
                int i = declaredField.getInt(attributes);
                Field declaredField2 = attributes.getClass().getDeclaredField("meizuFlags");
                declaredField2.setAccessible(true);
                int i2 = declaredField2.getInt(attributes);
                int i3 = z ? i2 | i : (~i) & i2;
                if (i2 != i3) {
                    declaredField2.setInt(attributes, i3);
                    return;
                }
                return;
            }
            View decorView = window.getDecorView();
            if (decorView != null) {
                int systemUiVisibility = decorView.getSystemUiVisibility();
                int i4 = z ? c | systemUiVisibility : (~c) & systemUiVisibility;
                if (i4 != systemUiVisibility) {
                    decorView.setSystemUiVisibility(i4);
                }
                WindowManager.LayoutParams attributes2 = window.getAttributes();
                Field field = b;
                if (field == null || field.getInt(attributes2) == 0) {
                    return;
                }
                b.set(attributes2, 0);
                window.setAttributes(attributes2);
            }
        } catch (IllegalAccessException unused) {
        }
    }

    public static boolean b(Activity activity, boolean z) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        boolean z2 = true;
        if (activity != null) {
            try {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i | i2 : (~i) & i2);
                activity.getWindow().setAttributes(attributes);
            } catch (Exception unused) {
                z2 = false;
            }
        } else {
            z2 = false;
        }
        if (z2) {
            a(activity, z);
        }
        return z2;
    }
}
