package supwisdom;

import android.content.Context;
import android.util.Log;
import javax.annotation.CheckReturnValue;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
public final class zh0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f10003a;

    public static synchronized void a(Context context) {
        if (f10003a != null) {
            Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
        } else if (context != null) {
            f10003a = context.getApplicationContext();
        }
    }
}
