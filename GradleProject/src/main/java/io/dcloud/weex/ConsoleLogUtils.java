package io.dcloud.weex;

import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.AppConsoleLogUtil;
import io.dcloud.feature.uniapp.utils.AbsLogLevel;

/* JADX INFO: loaded from: classes3.dex */
public class ConsoleLogUtils {
    public static boolean checkLog(String str) {
        if (str.contains("Start windmill weex-vue-plugin")) {
            return false;
        }
        if (!str.contains("has been registered already!")) {
            return true;
        }
        Logger.i(str);
        return false;
    }

    public static void consoleLog(String str, String str2, AbsLogLevel absLogLevel) {
        String strReplace;
        String str3;
        if (!TextUtils.isEmpty(str) && "jsLog".equals(str) && checkLog(str2)) {
            if (str2.endsWith("__ERROR")) {
                strReplace = str2.replace("__ERROR", "");
                str3 = "ERROR";
            } else if (str2.endsWith("__LOG")) {
                strReplace = str2.replace("__LOG", "");
                str3 = UMConfigure.KEY_FILE_NAME_LOG;
            } else if (str2.endsWith("__INFO")) {
                strReplace = str2.replace("__INFO", "");
                str3 = "INFO";
            } else if (str2.endsWith("__WARN")) {
                strReplace = str2.replace("__WARN", "");
                str3 = "WARN";
            } else {
                strReplace = str2.replace("__DEBUG", "");
                str3 = "DEBUG";
            }
            strReplace.trim();
            if (strReplace.startsWith("v8performance")) {
                WXDotDataUtil.setValue("initJSEngineTime", strReplace.replace("v8performance:", ""));
            } else if (strReplace.startsWith("JSCPerformance")) {
                WXDotDataUtil.setValue("initJSEngineTime", strReplace.replace("JSCPerformance:", ""));
            } else {
                AppConsoleLogUtil.DCLog(strReplace, str3);
            }
        }
    }
}
