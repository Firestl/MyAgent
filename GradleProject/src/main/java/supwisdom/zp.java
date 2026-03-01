package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class zp {
    public static Map<String, String> a(pp ppVar, String str) {
        Map<String, String> mapA = a();
        try {
            return a(str);
        } catch (Throwable th) {
            so.a(ppVar, "biz", "FormatResultEx", th);
            return mapA;
        }
    }

    public static String b(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(Operators.BLOCK_END_STR));
    }

    public static Map<String, String> a() {
        com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.CANCELED.a());
        HashMap map = new HashMap();
        map.put("resultStatus", Integer.toString(cVarB.a()));
        map.put("memo", cVarB.b());
        map.put("result", "");
        return map;
    }

    public static Map<String, String> a(String str) {
        String[] strArrSplit = str.split(";");
        HashMap map = new HashMap();
        for (String str2 : strArrSplit) {
            String strSubstring = str2.substring(0, str2.indexOf("={"));
            map.put(strSubstring, b(str2, strSubstring));
        }
        return map;
    }

    public static String a(String str, String str2) {
        try {
            Matcher matcher = Pattern.compile("(^|;)" + str2 + "=\\{([^}]*?)\\}").matcher(str);
            return matcher.find() ? matcher.group(2) : Operators.CONDITION_IF_STRING;
        } catch (Throwable th) {
            vp.a(th);
            return Operators.CONDITION_IF_STRING;
        }
    }
}
