package supwisdom;

import android.util.Log;
import java.util.Map;

/* JADX INFO: compiled from: LogProxy.java */
/* JADX INFO: loaded from: classes.dex */
public final class xi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f9767a = false;

    public static void a(Map<String, Object> map) {
        Object obj;
        if (map == null || (obj = map.get("debug")) == null) {
            return;
        }
        boolean zEquals = false;
        if (obj instanceof Boolean) {
            zEquals = ((Boolean) obj).booleanValue();
        } else if (obj instanceof String) {
            zEquals = "true".equals((String) obj);
        }
        f9767a = zEquals;
    }

    public static void b(String str) {
        if (f9767a) {
            Log.e("BindingX", str);
        }
    }

    public static void c(String str) {
        if (f9767a) {
            Log.w("BindingX", str);
        }
    }

    public static void a(String str) {
        if (f9767a) {
            Log.d("BindingX", str);
        }
    }

    public static void a(String str, Throwable th) {
        if (f9767a) {
            Log.e("BindingX", str, th);
        }
    }
}
