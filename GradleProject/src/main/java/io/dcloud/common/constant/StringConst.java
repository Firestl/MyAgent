package io.dcloud.common.constant;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.util.AndroidResources;

/* JADX INFO: loaded from: classes2.dex */
public final class StringConst extends AndroidResources implements AbsoluteConst {
    public static String T_URL_BASE_DATA = "appid=%s&imei=%s&net=%d&md=%s&os=%d&vb=%s&sf=%d&p=a&d1=%d&sfd=%s&vd=%s&pn=%s";
    public static long sChangeTime;

    public static String STREAMAPP_KEY_BASESERVICEURL() {
        return mainHost();
    }

    public static String backupHost() {
        return "https://stream.mobihtml5.com/";
    }

    public static boolean canChangeHost(String str) {
        if (str.contains(backupHost())) {
            return false;
        }
        return str.contains(mainHost());
    }

    public static String changeHost(String str) {
        return str.replace(mainHost(), backupHost());
    }

    public static int getIntSF(String str) {
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        if ("barcode".equals(str)) {
            return 2;
        }
        if ("scheme".equals(str)) {
            return 3;
        }
        if (IApp.ConfigProperty.CONFIG_STREAM.equals(str)) {
            return 6;
        }
        if (IApp.ConfigProperty.CONFIG_SHORTCUT.equals(str)) {
            return 5;
        }
        if ("push".equals(str)) {
            return 4;
        }
        if ("myapp".equals(str)) {
            return 7;
        }
        if ("browser".equals(str)) {
            return 8;
        }
        if (str.indexOf("third:") == 0) {
            return 9;
        }
        if ("favorite".equals(str)) {
            return 10;
        }
        if ("engines".equals(str)) {
            return 11;
        }
        if ("apush".equals(str)) {
            return 40;
        }
        return "speech".equals(str) ? 30 : 1;
    }

    public static String mainHost() {
        return "https://stream.dcloud.net.cn/";
    }
}
