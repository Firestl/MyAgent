package supwisdom;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class ew1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f7537a;

    public static String a(Context context) {
        iw1 iw1VarA = iw1.a();
        return iw1VarA.a(context.getApplicationContext(), iw1VarA.b);
    }

    public static final boolean a() {
        Context context = null;
        try {
            if (f7537a == null) {
                Method method = Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]);
                f7537a = method;
                method.setAccessible(true);
            }
            context = (Context) f7537a.invoke(null, new Object[0]);
        } catch (Exception e2) {
            Log.e("OpenIdHelper", "ActivityThread:currentApplication --> " + e2.toString());
        }
        if (context == null) {
            return false;
        }
        return iw1.a().a(context, false);
    }
}
