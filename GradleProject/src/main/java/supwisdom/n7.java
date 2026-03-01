package supwisdom;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.getui.gtc.base.util.BundleCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: BundleCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class n7 {
    public static IBinder a(Bundle bundle, String str) {
        return Build.VERSION.SDK_INT >= 18 ? bundle.getBinder(str) : a.a(bundle, str);
    }

    public static void a(Bundle bundle, String str, IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 18) {
            bundle.putBinder(str, iBinder);
        } else {
            a.a(bundle, str, iBinder);
        }
    }

    /* JADX INFO: compiled from: BundleCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Method f8480a;
        public static boolean b;
        public static Method c;
        public static boolean d;

        public static IBinder a(Bundle bundle, String str) {
            if (!b) {
                try {
                    Method method = Bundle.class.getMethod("getIBinder", String.class);
                    f8480a = method;
                    method.setAccessible(true);
                } catch (NoSuchMethodException e2) {
                    Log.i(BundleCompat.BundleCompatBaseImpl.TAG, "Failed to retrieve getIBinder method", e2);
                }
                b = true;
            }
            Method method2 = f8480a;
            if (method2 != null) {
                try {
                    return (IBinder) method2.invoke(bundle, str);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
                    Log.i(BundleCompat.BundleCompatBaseImpl.TAG, "Failed to invoke getIBinder via reflection", e3);
                    f8480a = null;
                }
            }
            return null;
        }

        public static void a(Bundle bundle, String str, IBinder iBinder) {
            if (!d) {
                try {
                    Method method = Bundle.class.getMethod("putIBinder", String.class, IBinder.class);
                    c = method;
                    method.setAccessible(true);
                } catch (NoSuchMethodException e2) {
                    Log.i(BundleCompat.BundleCompatBaseImpl.TAG, "Failed to retrieve putIBinder method", e2);
                }
                d = true;
            }
            Method method2 = c;
            if (method2 != null) {
                try {
                    method2.invoke(bundle, str, iBinder);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
                    Log.i(BundleCompat.BundleCompatBaseImpl.TAG, "Failed to invoke putIBinder via reflection", e3);
                    c = null;
                }
            }
        }
    }
}
