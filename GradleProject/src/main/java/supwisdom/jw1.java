package supwisdom;

import android.content.Context;
import android.util.Log;
import com.bun.miitmdid.provider.xiaomi.IdentifierManager;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: IdentifierManager.java */
/* JADX INFO: loaded from: classes3.dex */
public class jw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Object f8095a;
    public static Class<?> b;
    public static Method c;

    static {
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            b = cls;
            f8095a = cls.newInstance();
            b.getMethod("getUDID", Context.class);
            c = b.getMethod("getOAID", Context.class);
            b.getMethod("getVAID", Context.class);
            b.getMethod("getAAID", Context.class);
        } catch (Exception e2) {
            Log.e(IdentifierManager.TAG, "reflect exception!", e2);
        }
    }

    public static boolean a() {
        return (b == null || f8095a == null) ? false : true;
    }

    public static String a(Context context) {
        return a(context, c);
    }

    public static String a(Context context, Method method) {
        Object obj = f8095a;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(obj, context);
            if (objInvoke != null) {
                return (String) objInvoke;
            }
            return null;
        } catch (Exception e2) {
            Log.e(IdentifierManager.TAG, "invoke exception!", e2);
            return null;
        }
    }
}
