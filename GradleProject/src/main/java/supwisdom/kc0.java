package supwisdom;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.zzd;
import com.google.android.gms.common.zzg;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
public class kc0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public static kc0 f8157a;

    public kc0(Context context) {
        context.getApplicationContext();
    }

    @RecentlyNonNull
    public static kc0 a(@RecentlyNonNull Context context) {
        pf0.a(context);
        synchronized (kc0.class) {
            if (f8157a == null) {
                zh0.a(context);
                f8157a = new kc0(context);
            }
        }
        return f8157a;
    }

    public static boolean a(@RecentlyNonNull PackageInfo packageInfo, boolean z) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? a(packageInfo, ai0.f6925a) : a(packageInfo, ai0.f6925a[0])) != null) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static zzd a(PackageInfo packageInfo, zzd... zzdVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzg zzgVar = new zzg(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzdVarArr.length; i++) {
            if (zzdVarArr[i].equals(zzgVar)) {
                return zzdVarArr[i];
            }
        }
        return null;
    }
}
