package supwisdom;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class wg0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Object f9622a = new Object();

    @GuardedBy("sLock")
    public static boolean b;
    public static int c;

    public static int a(Context context) {
        b(context);
        return c;
    }

    public static void b(Context context) {
        synchronized (f9622a) {
            if (b) {
                return;
            }
            b = true;
            try {
                Bundle bundle = wh0.b(context).a(context.getPackageName(), 128).metaData;
                if (bundle == null) {
                    return;
                }
                bundle.getString("com.google.app.id");
                c = bundle.getInt("com.google.android.gms.version");
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("MetadataValueReader", "This should never happen.", e2);
            }
        }
    }
}
