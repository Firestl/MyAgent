package com.cmic.gen.sdk.a;

import android.text.TextUtils;
import com.cmic.gen.sdk.a.a;
import com.cmic.gen.sdk.e.k;
import com.cmic.gen.sdk.e.n;
import com.huawei.hms.framework.common.ContainerUtils;
import com.igexin.assist.sdk.AssistPushConsts;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static b c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.cmic.gen.sdk.a.a f1661a;
    public final com.cmic.gen.sdk.a.a b;
    public volatile boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f1662e;

    public interface a {
        void a(com.cmic.gen.sdk.a.a aVar);
    }

    public b(boolean z) {
        com.cmic.gen.sdk.a.a aVarA = new a.C0014a().a();
        this.b = aVarA;
        if (z) {
            this.f1661a = aVarA;
        } else {
            this.f1661a = d();
        }
    }

    public static b a(boolean z) {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b(z);
                }
            }
        }
        return c;
    }

    private String a(String str, String str2) {
        String str3;
        String[] strArrSplit = str.split("&");
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str3 = "";
                break;
            }
            str3 = strArrSplit[i];
            if (str3.contains(str2)) {
                break;
            }
            i++;
        }
        return !TextUtils.isEmpty(str3) ? str3.substring(str3.lastIndexOf(ContainerUtils.KEY_VALUE_DELIMITER) + 1) : str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        k.a aVarB = k.b("sso_config_xf");
        try {
            if (jSONObject.has("client_valid")) {
                aVarB.a("client_valid", System.currentTimeMillis() + (((long) Integer.parseInt(jSONObject.getString("client_valid"))) * 60 * 60 * 1000));
            }
            if (jSONObject.has("Configlist")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("Configlist");
                if (jSONObject2.has("CHANGE_HOST")) {
                    String string = jSONObject2.getString("CHANGE_HOST");
                    if (string.contains("M007")) {
                        String strA = a(string, "M007");
                        if (!TextUtils.isEmpty(strA)) {
                            aVarB.a("logHost", strA);
                        }
                    }
                    if (string.contains("M008")) {
                        String strA2 = a(string, "M008");
                        if (!TextUtils.isEmpty(strA2)) {
                            aVarB.a("https_get_phone_scrip_host", strA2);
                        }
                    }
                    if (string.contains("M009")) {
                        String strA3 = a(string, "M009");
                        if (!TextUtils.isEmpty(strA3)) {
                            aVarB.a("config_host", strA3);
                        }
                    }
                } else {
                    aVarB.a("logHost");
                    aVarB.a("https_get_phone_scrip_host");
                    aVarB.a("config_host");
                }
                a(jSONObject2, "CLOSE_FRIEND_WAPKS", "0", aVarB);
                a(jSONObject2, "CLOSE_LOGS_VERSION", "0", aVarB);
                a(jSONObject2, "CLOSE_IPV4_LIST", "0", aVarB);
                a(jSONObject2, "CLOSE_IPV6_LIST", "0", aVarB);
                a(jSONObject2, "CLOSE_M008_SDKVERSION_LIST", "0", aVarB);
                a(jSONObject2, "CLOSE_M008_APPID_LIST", "0", aVarB);
                if (jSONObject2.has("LOGS_CONTROL")) {
                    String[] strArrSplit = jSONObject2.getString("LOGS_CONTROL").replace("h", "").split("&");
                    if (strArrSplit.length == 2 && !TextUtils.isEmpty(strArrSplit[0]) && !TextUtils.isEmpty(strArrSplit[1])) {
                        try {
                            int i = Integer.parseInt(strArrSplit[0]);
                            int i2 = Integer.parseInt(strArrSplit[1]);
                            aVarB.a("maxFailedLogTimes", i);
                            aVarB.a("pauseTime", i2);
                        } catch (Exception unused) {
                            com.cmic.gen.sdk.e.c.a("UmcConfigHandle", "解析日志上报限制时间次数异常");
                        }
                    }
                } else {
                    aVarB.a("maxFailedLogTimes");
                    aVarB.a("pauseTime");
                }
            }
            aVarB.b();
        } catch (Exception e2) {
            com.cmic.gen.sdk.e.c.a("UmcConfigHandle", "配置项异常，配置失效");
            e2.printStackTrace();
        }
    }

    private void a(JSONObject jSONObject, String str, String str2, k.a aVar) {
        if (!jSONObject.has(str)) {
            aVar.a(str);
            return;
        }
        String strOptString = jSONObject.optString(str, str2);
        if ("CLOSE_FRIEND_WAPKS".equals(str)) {
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            if (!strOptString.contains("CU") && !strOptString.contains(AssistPushConsts.MSG_KEY_CONTENT) && !strOptString.contains("CM")) {
                return;
            }
        } else if (!"0".equals(strOptString) && !"1".equals(strOptString)) {
            return;
        }
        aVar.a(str, jSONObject.optString(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.cmic.gen.sdk.a aVar) {
        if (this.d) {
            com.cmic.gen.sdk.e.c.a("UmcConfigHandle", "正在获取配置中...");
        } else {
            this.d = true;
            com.cmic.gen.sdk.c.c.a.a().a(false, aVar, new com.cmic.gen.sdk.c.c.d() { // from class: com.cmic.gen.sdk.a.b.1
                @Override // com.cmic.gen.sdk.c.c.d
                public void a(String str, String str2, JSONObject jSONObject) {
                    try {
                        if ("103000".equals(str)) {
                            b.this.a(jSONObject);
                            k.a("sdk_config_version", com.cmic.gen.sdk.auth.c.SDK_VERSION);
                            b.this.f1661a = b.this.d();
                            if (b.this.f1662e != null) {
                                b.this.f1662e.a(b.this.f1661a);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    b.this.d = false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.cmic.gen.sdk.a.a d() {
        return new a.C0014a().a(d.b(this.b.a())).c(d.a(this.b.c())).b(d.b(this.b.b())).d(d.c(this.b.d())).d(d.a(this.b.h())).e(d.b(this.b.i())).a(d.e(this.b.e())).b(d.d(this.b.f())).c(d.c(this.b.g())).f(d.f(this.b.j())).a(d.a(this.b.k())).b(d.b(this.b.l())).a();
    }

    public com.cmic.gen.sdk.a.a a() {
        return this.b;
    }

    public void a(a aVar) {
        this.f1662e = aVar;
    }

    public void a(final com.cmic.gen.sdk.a aVar) {
        if (d.a()) {
            n.a(new n.a() { // from class: com.cmic.gen.sdk.a.b.2
                @Override // com.cmic.gen.sdk.e.n.a
                public void a() {
                    com.cmic.gen.sdk.e.c.b("UmcConfigHandle", "开始拉取配置..");
                    b.this.b(aVar);
                }
            });
        }
    }

    public com.cmic.gen.sdk.a.a b() {
        return this.f1661a;
    }

    public void c() {
        k.a aVarB = k.b("sso_config_xf");
        aVarB.c();
        aVarB.b();
    }
}
