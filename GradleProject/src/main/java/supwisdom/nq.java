package supwisdom;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class nq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static nq f8552a = new nq();

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception unused) {
            return "0.0.0";
        }
    }

    public static nq a() {
        return f8552a;
    }
}
