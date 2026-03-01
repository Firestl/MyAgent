package io.dcloud.feature.weex.config;

import io.dcloud.common.util.BaseInfo;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class UniPathParser {
    public static String getAndroidPath(String str) {
        String str2 = BaseInfo.sCacheFsAppsPath;
        String str3 = BaseInfo.sDefaultBootApp + "/www/" + str;
        File file = new File(str2 + str3);
        if (file.exists()) {
            return file.getPath();
        }
        return "file:///android_asset/apps/" + str3;
    }
}
