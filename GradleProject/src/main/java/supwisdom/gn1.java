package supwisdom;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: SwitchHostUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class gn1 {
    public static String a() {
        JSONArray array = JSON.parseArray("[{\"GROUP_ID\":\"1eef73d538405348bc3882d39d26bf4c6a3c\",\"NAME\":\"æ\u00ad£å¼\u008fç\u008e¯å¢\u0083\",\"CAS_HOST\":\"cas.zueb.edu.cn\",\"LOGIN_BASE_URL\":\"https://cas.zueb.edu.cn/token\",\"PORTAL_BASE_URL\":\"https://portal.zueb.edu.cn/portal-api\",\"PERSONAL_BASE_URL\":\"https://authx-service.zueb.edu.cn/personal/\",\"APP_BASE_URL\":\"https://superapp.zueb.edu.cn\",\"MESSAGE_BASE_URL\":\"https://message-service.zueb.edu.cn/center\",\"FORMFOLLOW_BASE_URL\":\"https://transaction.zueb.edu.cn/ttc\"}]");
        if (array.size() == 1) {
            return "";
        }
        String strC = sh1.c.c(fn1.s);
        if (TextUtils.isEmpty(strC)) {
            String string = array.getJSONObject(0).getString("GROUP_ID");
            String string2 = array.getJSONObject(0).getString("NAME");
            try {
                string2 = new String(string2.getBytes("iso8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            array.getJSONObject(0).put("NAME", (Object) string2);
            String jSONString = JSON.toJSONString(array.getJSONObject(0));
            sh1.c.b(fn1.s, string);
            return jSONString;
        }
        for (int i = 0; i < array.size(); i++) {
            if (strC.equals(array.getJSONObject(i).getString("GROUP_ID"))) {
                String string3 = array.getJSONObject(i).getString("NAME");
                try {
                    string3 = new String(string3.getBytes("iso8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException e3) {
                    e3.printStackTrace();
                }
                array.getJSONObject(i).put("NAME", (Object) string3);
                return JSON.toJSONString(array.getJSONObject(i));
            }
        }
        return "";
    }

    public static String b() {
        JSONArray array = JSON.parseArray("[{\"GROUP_ID\":\"1eef73d538405348bc3882d39d26bf4c6a3c\",\"NAME\":\"æ\u00ad£å¼\u008fç\u008e¯å¢\u0083\",\"CAS_HOST\":\"cas.zueb.edu.cn\",\"LOGIN_BASE_URL\":\"https://cas.zueb.edu.cn/token\",\"PORTAL_BASE_URL\":\"https://portal.zueb.edu.cn/portal-api\",\"PERSONAL_BASE_URL\":\"https://authx-service.zueb.edu.cn/personal/\",\"APP_BASE_URL\":\"https://superapp.zueb.edu.cn\",\"MESSAGE_BASE_URL\":\"https://message-service.zueb.edu.cn/center\",\"FORMFOLLOW_BASE_URL\":\"https://transaction.zueb.edu.cn/ttc\"}]");
        if (array.size() == 1) {
            return "";
        }
        for (int i = 0; i < array.size(); i++) {
            String string = array.getJSONObject(i).getString("NAME");
            try {
                string = new String(string.getBytes("iso8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            array.getJSONObject(i).put("NAME", (Object) string);
        }
        return JSON.toJSONString(array);
    }

    public static void a(String str) {
        JSONArray array = JSON.parseArray("[{\"GROUP_ID\":\"1eef73d538405348bc3882d39d26bf4c6a3c\",\"NAME\":\"æ\u00ad£å¼\u008fç\u008e¯å¢\u0083\",\"CAS_HOST\":\"cas.zueb.edu.cn\",\"LOGIN_BASE_URL\":\"https://cas.zueb.edu.cn/token\",\"PORTAL_BASE_URL\":\"https://portal.zueb.edu.cn/portal-api\",\"PERSONAL_BASE_URL\":\"https://authx-service.zueb.edu.cn/personal/\",\"APP_BASE_URL\":\"https://superapp.zueb.edu.cn\",\"MESSAGE_BASE_URL\":\"https://message-service.zueb.edu.cn/center\",\"FORMFOLLOW_BASE_URL\":\"https://transaction.zueb.edu.cn/ttc\"}]");
        if (!TextUtils.isEmpty(str) && "[{\"GROUP_ID\":\"1eef73d538405348bc3882d39d26bf4c6a3c\",\"NAME\":\"æ\u00ad£å¼\u008fç\u008e¯å¢\u0083\",\"CAS_HOST\":\"cas.zueb.edu.cn\",\"LOGIN_BASE_URL\":\"https://cas.zueb.edu.cn/token\",\"PORTAL_BASE_URL\":\"https://portal.zueb.edu.cn/portal-api\",\"PERSONAL_BASE_URL\":\"https://authx-service.zueb.edu.cn/personal/\",\"APP_BASE_URL\":\"https://superapp.zueb.edu.cn\",\"MESSAGE_BASE_URL\":\"https://message-service.zueb.edu.cn/center\",\"FORMFOLLOW_BASE_URL\":\"https://transaction.zueb.edu.cn/ttc\"}]".contains(str)) {
            for (int i = 0; i < array.size(); i++) {
                String string = array.getJSONObject(i).getString("GROUP_ID");
                if (str.equals(string)) {
                    String string2 = array.getJSONObject(i).getString("LOGIN_BASE_URL");
                    fn1.d = string2;
                    if (!string2.endsWith("/")) {
                        fn1.d += "/";
                    }
                    String string3 = array.getJSONObject(i).getString("APP_BASE_URL");
                    fn1.g = string3;
                    if (!string3.endsWith("/")) {
                        fn1.g += "/";
                    }
                    String string4 = array.getJSONObject(i).getString("PERSONAL_BASE_URL");
                    fn1.f = string4;
                    if (!string4.endsWith("/")) {
                        fn1.f += "/";
                    }
                    String string5 = array.getJSONObject(i).getString("PORTAL_BASE_URL");
                    fn1.f7621e = string5;
                    if (!string5.endsWith("/")) {
                        fn1.f7621e += "/";
                    }
                    fn1.j = fn1.g + "static/" + fn1.i + ".wgt";
                    fn1.k = array.getJSONObject(i).getString("CAS_HOST");
                    sh1.c.b(fn1.s, string);
                }
            }
            return;
        }
        String string6 = array.getJSONObject(0).getString("LOGIN_BASE_URL");
        fn1.d = string6;
        if (!string6.endsWith("/")) {
            fn1.d += "/";
        }
        String string7 = array.getJSONObject(0).getString("APP_BASE_URL");
        fn1.g = string7;
        if (!string7.endsWith("/")) {
            fn1.g += "/";
        }
        String string8 = array.getJSONObject(0).getString("PERSONAL_BASE_URL");
        fn1.f = string8;
        if (!string8.endsWith("/")) {
            fn1.f += "/";
        }
        String string9 = array.getJSONObject(0).getString("PORTAL_BASE_URL");
        fn1.f7621e = string9;
        if (!string9.endsWith("/")) {
            fn1.f7621e += "/";
        }
        fn1.j = fn1.g + "static/" + fn1.i + ".wgt";
        fn1.k = array.getJSONObject(0).getString("CAS_HOST");
        sh1.c.b(fn1.s, array.getJSONObject(0).getString("GROUP_ID"));
    }
}
