package supwisdom;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class hc0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f7825a = jc0.f8043a;

    static {
        new hc0();
    }

    public int a(@RecentlyNonNull Context context) {
        return a(context, f7825a);
    }

    public boolean b(int i) {
        return jc0.b(i);
    }

    public int a(@RecentlyNonNull Context context, int i) {
        int iA = jc0.a(context, i);
        if (jc0.b(context, iA)) {
            return 18;
        }
        return iA;
    }

    @RecentlyNullable
    public Intent a(Context context, int i, String str) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return null;
            }
            return tg0.a("com.google.android.gms");
        }
        if (context != null && qh0.c(context)) {
            return tg0.a();
        }
        return tg0.a("com.google.android.gms", a(context, str));
    }

    @RecentlyNullable
    public PendingIntent a(@RecentlyNonNull Context context, int i, int i2) {
        return a(context, i, i2, null);
    }

    @RecentlyNullable
    public PendingIntent a(@RecentlyNonNull Context context, int i, int i2, String str) {
        Intent intentA = a(context, i, str);
        if (intentA == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i2, intentA, 134217728);
    }

    public String a(int i) {
        return jc0.a(i);
    }

    public static String a(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(f7825a);
        sb.append("-");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        if (context != null) {
            try {
                sb.append(wh0.b(context).b(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return sb.toString();
    }
}
