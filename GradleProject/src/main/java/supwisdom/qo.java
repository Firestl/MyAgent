package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public class qo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f8942a = false;
    public static String b;

    public static void a(String str) {
        b = str;
    }

    public static boolean b() {
        return f8942a;
    }

    public static String c() {
        com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.CANCELED.a());
        return a(cVarB.a(), cVarB.b(), "");
    }

    public static String d() {
        com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.DOUBLE_REQUEST.a());
        return a(cVarB.a(), cVarB.b(), "");
    }

    public static String e() {
        com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.PARAMS_ERROR.a());
        return a(cVarB.a(), cVarB.b(), "");
    }

    public static String a() {
        return b;
    }

    public static void a(boolean z) {
        f8942a = z;
    }

    public static String a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + Operators.BLOCK_END_STR;
    }
}
