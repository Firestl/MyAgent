package supwisdom;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/* JADX INFO: compiled from: DDVersionCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class zr {
    public static int a(Context context, int i) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.alibaba.android.rimet", 128);
            return applicationInfo.metaData != null ? applicationInfo.metaData.getInt("android.intent.ding.SHARE_SDK_KEY") : i;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return i;
        } catch (NullPointerException e3) {
            e3.printStackTrace();
            return i;
        }
    }
}
