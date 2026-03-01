package supwisdom;

import android.os.Build;
import android.os.Process;
import com.sangfor.sdk.utils.SFLogN;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class yb1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f9868a;

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Method declaredMethod = Process.class.getDeclaredMethod("isIsolated", null);
                f9868a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                SFLogN.a("ProcessCompat", "can't find isIsolated function", e2);
            }
        }
    }

    public static boolean a() {
        Method method = f9868a;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(null, new Object[0])).booleanValue();
            } catch (Exception e2) {
                SFLogN.a("ProcessCompat", "call method isIsolated fail", e2);
            }
        }
        return false;
    }
}
