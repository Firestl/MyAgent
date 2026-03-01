package supwisdom;

import android.content.Context;
import androidx.annotation.RecentlyNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class wh0 {
    public static wh0 b = new wh0();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public vh0 f9634a = null;

    @RecentlyNonNull
    public static vh0 b(@RecentlyNonNull Context context) {
        return b.a(context);
    }

    public final synchronized vh0 a(Context context) {
        if (this.f9634a == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.f9634a = new vh0(context);
        }
        return this.f9634a;
    }
}
