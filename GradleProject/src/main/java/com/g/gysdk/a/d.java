package com.g.gysdk.a;

import android.content.Context;
import android.text.TextUtils;
import com.g.gysdk.GyErrorCode;
import com.getui.gtc.api.GtcIdCallback;
import com.getui.gtc.api.GtcManager;
import com.getui.gtc.api.SdkInfo;
import com.getui.gtc.base.GtcProvider;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1999a = 0;
    public static Context b = null;
    public static String c = "GY-3.1.0.0";
    public static String d = "android";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f2000e = "";
    public static String f = "";
    public static String g = "";
    public static String h = "";
    public static String i = "MHwwDQYJKoZIhvcNAQEBBQADawAwaAJhAJp1rROuvBF7sBSnvLaesj2iFhMcY8aXyLvpnNLKs2wjL3JmEnyr++SlVa35liUlzi83tnAFkn3A9GB7pHBNzawyUkBh8WUhq5bnFIkk2RaDa6+5MpG84DEv52p7RR+aWwIDAQAB";
    public static String j = "69d747c4b9f641baf4004be4297e9f3b";
    public static af k;
    public static int l;
    public static int m;
    public static int n;

    public enum a {
        DEBUG_UI,
        NOT_TOKEN_UPLOAD
    }

    public static GyErrorCode a(Context context) {
        if (b == null) {
            b = context.getApplicationContext();
        }
        Context context2 = b;
        if (context2 == null) {
            throw new IllegalStateException("context null in init");
        }
        aq aqVar = new aq(context2);
        String strA = aqVar.a("GY_APP_ID", "");
        f2000e = strA;
        if (TextUtils.isEmpty(strA)) {
            f2000e = aqVar.a("GETUI_APPID", "");
        }
        if (TextUtils.isEmpty(f2000e)) {
            aj.c("GY_APP_ID and GETUI_APPID are all empty");
            return GyErrorCode.APPID_EMPTY;
        }
        if (TextUtils.isEmpty(f)) {
            f = aqVar.a(com.igexin.push.core.b.d, f);
        }
        i = aqVar.a("GY_PK", i);
        j = aqVar.a("GY_KI", j);
        ae.a(aqVar);
        g.a(b);
        String strInitialize = GtcManager.getInstance().initialize(b, new GtcIdCallback.Stub() { // from class: com.g.gysdk.a.d.1
            @Override // com.getui.gtc.api.GtcIdCallback
            public void onFailure(String str) {
                StringBuilder sb = new StringBuilder();
                sb.append("gtc init error: ");
                if (str == null) {
                    str = com.igexin.push.core.b.m;
                }
                sb.append(str);
                ak.e(sb.toString());
            }

            @Override // com.getui.gtc.api.GtcIdCallback
            public void onSuccess(String str) {
            }
        });
        g = strInitialize;
        if (strInitialize == null) {
            g = "";
        }
        h = g;
        GtcManager.getInstance().loadSdk(new SdkInfo.Builder().moduleName("gysdk").psUrl(ae.a("gy.cs") + "/api.php?format=json&t=1").appid(f2000e).cid(g).version("GY-3.1.0.0").build());
        return GyErrorCode.SUCCESS;
    }

    public static String a() {
        return "initApi_" + l + "-loginApi_" + m + "-uiApi_" + n;
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f = str;
    }

    public static boolean a(a aVar) {
        return ((f1999a >> aVar.ordinal()) & 1) != 0;
    }

    public static af b() {
        if (k == null) {
            k = new af(b);
        }
        return k;
    }

    public static Context c() {
        Context context = b;
        return context != null ? context : GtcProvider.context();
    }
}
