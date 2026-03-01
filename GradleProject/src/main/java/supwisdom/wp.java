package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;

/* JADX INFO: loaded from: classes.dex */
public class wp {
    public static void a(pp ppVar, Context context, String str) {
        try {
            String strA = a(str);
            vp.a("mspl", "trade token: " + strA);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            xp.a(ppVar, context, "pref_trade_token", strA);
        } catch (Throwable th) {
            so.a(ppVar, "biz", "SaveTradeTokenError", th);
            vp.a(th);
        }
    }

    public static String a(String str) {
        String strSubstring = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(";");
        for (int i = 0; i < strArrSplit.length; i++) {
            if (strArrSplit[i].startsWith("result={") && strArrSplit[i].endsWith(Operators.BLOCK_END_STR)) {
                String[] strArrSplit2 = strArrSplit[i].substring(8, strArrSplit[i].length() - 1).split("&");
                int i2 = 0;
                while (true) {
                    if (i2 >= strArrSplit2.length) {
                        break;
                    }
                    if (strArrSplit2[i2].startsWith("trade_token=\"") && strArrSplit2[i2].endsWith(JSUtil.QUOTE)) {
                        strSubstring = strArrSplit2[i2].substring(13, strArrSplit2[i2].length() - 1);
                        break;
                    }
                    if (strArrSplit2[i2].startsWith("trade_token=")) {
                        strSubstring = strArrSplit2[i2].substring(12);
                        break;
                    }
                    i2++;
                }
            }
        }
        return strSubstring;
    }

    public static String a(pp ppVar, Context context) {
        String strB = xp.b(ppVar, context, "pref_trade_token", "");
        vp.a("mspl", "get trade token: " + strB);
        return strB;
    }
}
