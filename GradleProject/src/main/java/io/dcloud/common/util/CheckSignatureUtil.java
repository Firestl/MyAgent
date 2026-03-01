package io.dcloud.common.util;

import android.text.TextUtils;
import io.dcloud.application.DCLoudApplicationImpl;

/* JADX INFO: loaded from: classes2.dex */
public class CheckSignatureUtil {
    public static boolean check(String str) {
        String signature = getSignature(str);
        if (TextUtils.isEmpty(signature)) {
            return true;
        }
        String appSignatureMd5 = LoadAppUtils.getAppSignatureMd5(DCLoudApplicationImpl.self().getContext().getApplicationContext(), DCLoudApplicationImpl.self().getContext().getPackageName());
        return TextUtils.isEmpty(appSignatureMd5) || appSignatureMd5.equalsIgnoreCase(signature);
    }

    public static String getSignature(String str) {
        String[] apkFileSignatureAndPackageName = LoadAppUtils.getApkFileSignatureAndPackageName(DCLoudApplicationImpl.self().getContext().getApplicationContext(), str);
        return (apkFileSignatureAndPackageName == null || apkFileSignatureAndPackageName.length <= 0) ? "" : apkFileSignatureAndPackageName[0];
    }
}
