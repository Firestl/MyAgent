package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.TypedValue;
import java.io.File;

/* JADX INFO: compiled from: ContextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class y7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f9850a = new Object();
    public static TypedValue b;

    /* JADX INFO: compiled from: ContextCompat.java */
    public static class a {
        public static void a(Context context, Intent[] intentArr, Bundle bundle) {
            context.startActivities(intentArr, bundle);
        }

        public static void a(Context context, Intent intent, Bundle bundle) {
            context.startActivity(intent, bundle);
        }
    }

    /* JADX INFO: compiled from: ContextCompat.java */
    public static class b {
        public static File[] a(Context context) {
            return context.getExternalCacheDirs();
        }

        public static File[] b(Context context) {
            return context.getObbDirs();
        }

        public static File[] a(Context context, String str) {
            return context.getExternalFilesDirs(str);
        }
    }

    /* JADX INFO: compiled from: ContextCompat.java */
    public static class c {
        public static Drawable a(Context context, int i) {
            return context.getDrawable(i);
        }

        public static File b(Context context) {
            return context.getNoBackupFilesDir();
        }

        public static File a(Context context) {
            return context.getCodeCacheDir();
        }
    }

    /* JADX INFO: compiled from: ContextCompat.java */
    public static class d {
        public static int a(Context context, int i) {
            return context.getColor(i);
        }

        public static ColorStateList b(Context context, int i) {
            return context.getColorStateList(i);
        }

        public static <T> T a(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        public static String b(Context context, Class<?> cls) {
            return context.getSystemServiceName(cls);
        }
    }

    public static boolean a(Context context, Intent[] intentArr, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            a.a(context, intentArr, bundle);
            return true;
        }
        context.startActivities(intentArr);
        return true;
    }

    public static File[] b(Context context, String str) {
        return Build.VERSION.SDK_INT >= 19 ? b.a(context, str) : new File[]{context.getExternalFilesDir(str)};
    }

    public static Drawable c(Context context, int i) {
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            return c.a(context, i);
        }
        if (i3 >= 16) {
            return context.getResources().getDrawable(i);
        }
        synchronized (f9850a) {
            if (b == null) {
                b = new TypedValue();
            }
            context.getResources().getValue(i, b, true);
            i2 = b.resourceId;
        }
        return context.getResources().getDrawable(i2);
    }

    public static File[] a(Context context) {
        return Build.VERSION.SDK_INT >= 19 ? b.a(context) : new File[]{context.getExternalCacheDir()};
    }

    public static ColorStateList b(Context context, int i) {
        return k8.a(context.getResources(), i, context.getTheme());
    }

    public static int a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return d.a(context, i);
        }
        return context.getResources().getColor(i);
    }

    public static int a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }
}
