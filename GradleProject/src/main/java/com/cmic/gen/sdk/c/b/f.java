package com.cmic.gen.sdk.c.b;

import io.dcloud.feature.payment.weixin.WeiXinPay;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1711a;
    public a b;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public JSONObject f1712a;

        public JSONObject a() {
            return this.f1712a;
        }

        public void a(JSONObject jSONObject) {
            this.f1712a = jSONObject;
        }
    }

    public static class b extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1713a;
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f1714e;

        @Override // com.cmic.gen.sdk.c.b.g
        public String a() {
            return this.d;
        }

        @Override // com.cmic.gen.sdk.c.b.g
        public String a(String str) {
            return this.f1714e + this.d + this.c + this.b + "@Fdiwmxy7CBDDQNUI";
        }

        @Override // com.cmic.gen.sdk.c.b.g
        public JSONObject b() {
            return null;
        }

        public void b(String str) {
            this.f1714e = str;
        }

        public String c() {
            return this.f1714e;
        }

        public void c(String str) {
            this.d = str;
        }

        public String d() {
            return this.f1713a;
        }

        public void d(String str) {
            this.f1713a = str;
        }

        public String e() {
            return this.b;
        }

        public void e(String str) {
            this.b = str;
        }

        public String f() {
            return this.c;
        }

        public void f(String str) {
            this.c = str;
        }
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a() {
        return this.f1711a.d;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a(String str) {
        return null;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(b bVar) {
        this.f1711a = bVar;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject2.put(WeiXinPay.PayInfoResult.KEY_SIGN, this.f1711a.d());
            jSONObject2.put("msgid", this.f1711a.e());
            jSONObject2.put("systemtime", this.f1711a.f());
            jSONObject2.put("appid", this.f1711a.a());
            jSONObject2.put("version", this.f1711a.c());
            jSONObject.put("header", jSONObject2);
            jSONObject3.put("log", this.b.a());
            jSONObject.put("body", jSONObject3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
