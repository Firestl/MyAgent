package io.dcloud.feature.sdk;

import android.app.Activity;
import supwisdom.j7;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPPermissionUtil {
    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        if (activity == null || strArr == null) {
            return;
        }
        if (activity instanceof DCUniMPActivity) {
            ((DCUniMPActivity) activity).requestUniMPPermissions(strArr, i);
        } else {
            j7.a(activity, strArr, i);
        }
    }
}
