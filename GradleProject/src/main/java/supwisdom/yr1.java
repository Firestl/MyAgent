package supwisdom;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Process;
import android.util.Log;
import java.util.List;
import net.grandcentrix.tray.R;
import net.grandcentrix.tray.core.TrayRuntimeException;
import net.grandcentrix.tray.provider.TrayContentProvider;

/* JADX INFO: compiled from: TrayContract.java */
/* JADX INFO: loaded from: classes3.dex */
public class yr1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f9909a;

    public static void a(Context context) {
        if ("legacyTrayAuthority".equals(context.getString(R.string.tray__authority))) {
            return;
        }
        Log.e("Tray", "Deprecated way of defining the Tray authority detected\n#########################################\n#########################################\n#########################################\nDon't set the authority with `tray__authority` in your build.gradle.\nTo change the default authority override it inside the AndroidManifest\nSee https://github.com/grandcentrix/tray/wiki/Custom-Authority for instructions\n#########################################\n#########################################\n#########################################\n");
    }

    public static Uri b(Context context) {
        return a(context, "preferences");
    }

    public static Uri c(Context context) {
        return a(context, "internal_preferences");
    }

    public static synchronized String d(Context context) {
        if (f9909a != null) {
            return f9909a;
        }
        a(context);
        List<ProviderInfo> listQueryContentProviders = context.getPackageManager().queryContentProviders(context.getPackageName(), Process.myUid(), 0);
        if (listQueryContentProviders != null) {
            for (ProviderInfo providerInfo : listQueryContentProviders) {
                if (providerInfo.name.equals(TrayContentProvider.class.getName())) {
                    f9909a = providerInfo.authority;
                    vr1.b("found authority: " + f9909a);
                    return f9909a;
                }
            }
        }
        throw new TrayRuntimeException("Internal tray error. Could not find the provider authority. Please fill an issue at https://github.com/grandcentrix/tray/issues");
    }

    public static Uri a(Context context, String str) {
        return Uri.withAppendedPath(Uri.parse("content://" + d(context)), str);
    }
}
