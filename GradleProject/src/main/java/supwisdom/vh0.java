package supwisdom;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.RecentlyNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class vh0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9512a;

    public vh0(@RecentlyNonNull Context context) {
        this.f9512a = context;
    }

    @RecentlyNonNull
    public ApplicationInfo a(@RecentlyNonNull String str, int i) throws PackageManager.NameNotFoundException {
        return this.f9512a.getPackageManager().getApplicationInfo(str, i);
    }

    @RecentlyNonNull
    public PackageInfo b(@RecentlyNonNull String str, int i) throws PackageManager.NameNotFoundException {
        return this.f9512a.getPackageManager().getPackageInfo(str, i);
    }

    @RecentlyNonNull
    public CharSequence a(@RecentlyNonNull String str) throws PackageManager.NameNotFoundException {
        return this.f9512a.getPackageManager().getApplicationLabel(this.f9512a.getPackageManager().getApplicationInfo(str, 0));
    }
}
