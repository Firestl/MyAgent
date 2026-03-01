package supwisdom;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import io.dcloud.common.util.JSUtil;

/* JADX INFO: loaded from: classes.dex */
public class ep {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] strArrSplit = str.split("&");
        if (strArrSplit.length == 0) {
            return "";
        }
        String strB = null;
        String strC = null;
        String strD = null;
        String strF = null;
        for (String str2 : strArrSplit) {
            if (TextUtils.isEmpty(strB)) {
                strB = b(str2);
            }
            if (TextUtils.isEmpty(strC)) {
                strC = c(str2);
            }
            if (TextUtils.isEmpty(strD)) {
                strD = d(str2);
            }
            if (TextUtils.isEmpty(strF)) {
                strF = f(str2);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(strB)) {
            sb.append("biz_type=" + strB + ";");
        }
        if (!TextUtils.isEmpty(strC)) {
            sb.append("biz_no=" + strC + ";");
        }
        if (!TextUtils.isEmpty(strD)) {
            sb.append("trade_no=" + strD + ";");
        }
        if (!TextUtils.isEmpty(strF)) {
            sb.append("app_userid=" + strF + ";");
        }
        String string = sb.toString();
        return string.endsWith(";") ? string.substring(0, string.length() - 1) : string;
    }

    public static String b(String str) {
        if (str.contains("biz_type")) {
            return e(str);
        }
        return null;
    }

    public static String c(String str) {
        if (str.contains("biz_no")) {
            return e(str);
        }
        return null;
    }

    public static String d(String str) {
        if (!str.contains("trade_no") || str.startsWith("out_trade_no")) {
            return null;
        }
        return e(str);
    }

    public static String e(String str) {
        String[] strArrSplit = str.split(ContainerUtils.KEY_VALUE_DELIMITER);
        if (strArrSplit.length <= 1) {
            return null;
        }
        String str2 = strArrSplit[1];
        return str2.contains(JSUtil.QUOTE) ? str2.replaceAll(JSUtil.QUOTE, "") : str2;
    }

    public static String f(String str) {
        if (str.contains("app_userid")) {
            return e(str);
        }
        return null;
    }
}
