package com.cmic.gen.sdk.c.b;

import io.dcloud.feature.payment.weixin.WeiXinPay;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1704a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1705e;
    public String f;
    public String g;

    @Override // com.cmic.gen.sdk.c.b.g
    public String a() {
        return this.f;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a(String str) {
        return this.f1704a + this.f1705e + this.f + "iYm0HAnkxQtpvN44";
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.f1704a);
            jSONObject.put("apptype", this.b);
            jSONObject.put("phone_ID", this.c);
            jSONObject.put("certflag", this.d);
            jSONObject.put("sdkversion", this.f1705e);
            jSONObject.put("appid", this.f);
            jSONObject.put("expandparams", "");
            jSONObject.put(WeiXinPay.PayInfoResult.KEY_SIGN, this.g);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void b(String str) {
        this.f1704a = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void d(String str) {
        this.c = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public void f(String str) {
        this.f1705e = str;
    }

    public void g(String str) {
        this.f = str;
    }

    public void h(String str) {
        this.g = str;
    }
}
