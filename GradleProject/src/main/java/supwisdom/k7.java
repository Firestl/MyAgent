package supwisdom;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: compiled from: ActivityOptionsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class k7 {

    /* JADX INFO: compiled from: ActivityOptionsCompat.java */
    public static class a extends k7 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActivityOptions f8132a;

        public a(ActivityOptions activityOptions) {
            this.f8132a = activityOptions;
        }

        @Override // supwisdom.k7
        public Bundle a() {
            return this.f8132a.toBundle();
        }
    }

    public static k7 a(Context context, int i, int i2) {
        return Build.VERSION.SDK_INT >= 16 ? new a(ActivityOptions.makeCustomAnimation(context, i, i2)) : new k7();
    }

    public Bundle a() {
        return null;
    }
}
