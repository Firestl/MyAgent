package com.cmic.gen.sdk.d;

import android.text.TextUtils;
import com.cmic.gen.sdk.c.b.g;
import com.taobao.weex.common.WXConfig;
import com.xiaomi.mipush.sdk.Constants;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends g {
    public String A;
    public String B;
    public String C;
    public JSONArray o;
    public String y;
    public String z;
    public String b = null;
    public String c = null;
    public String d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1724e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = "";
    public String l = null;
    public String m = null;
    public String n = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = null;
    public String u = null;
    public String v = null;
    public String w = null;
    public String x = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CopyOnWriteArrayList<Throwable> f1723a = new CopyOnWriteArrayList<>();

    public void A(String str) {
        this.A = str;
    }

    public void B(String str) {
        this.B = str;
    }

    public void C(String str) {
        this.C = str;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a() {
        return null;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a(String str) {
        return null;
    }

    public void a(JSONArray jSONArray) {
        this.o = jSONArray;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", this.b);
            jSONObject.put("traceId", this.c);
            jSONObject.put("appName", this.d);
            jSONObject.put(WXConfig.appVersion, this.f1724e);
            jSONObject.put(com.heytap.mcssdk.a.a.o, com.cmic.gen.sdk.auth.c.SDK_VERSION);
            jSONObject.put("clientType", "android");
            jSONObject.put("timeOut", this.f);
            jSONObject.put("requestTime", this.g);
            jSONObject.put("responseTime", this.h);
            jSONObject.put("elapsedTime", this.i);
            jSONObject.put("requestType", this.j);
            jSONObject.put("interfaceType", this.k);
            jSONObject.put("interfaceCode", this.l);
            jSONObject.put("interfaceElasped", this.m);
            jSONObject.put("loginType", this.n);
            jSONObject.put("exceptionStackTrace", this.o);
            jSONObject.put("operatorType", this.p);
            jSONObject.put("networkType", this.q);
            jSONObject.put("networkClass", this.r);
            jSONObject.put(Constants.PHONE_BRAND, this.s);
            jSONObject.put("reqDevice", this.t);
            jSONObject.put("reqSystem", this.u);
            jSONObject.put("simCardNum", this.v);
            jSONObject.put("imsiState", this.w);
            jSONObject.put("resultCode", this.x);
            jSONObject.put("is_phoneStatePermission", this.y);
            jSONObject.put("AID", this.z);
            jSONObject.put("sysOperType", this.A);
            jSONObject.put("scripType", this.B);
            if (!TextUtils.isEmpty(this.C)) {
                jSONObject.put("networkTypeByAPI", this.C);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.y = str;
    }

    public void d(String str) {
        this.w = str;
    }

    public void e(String str) {
        this.x = str;
    }

    public void f(String str) {
        this.s = str;
    }

    public void g(String str) {
        this.m = str;
    }

    public void h(String str) {
        this.l = str;
    }

    public void i(String str) {
        this.k = str;
    }

    public void j(String str) {
        this.d = str;
    }

    public void k(String str) {
        this.f1724e = str;
    }

    public void l(String str) {
        this.f = str;
    }

    public void m(String str) {
        this.i = str;
    }

    public void n(String str) {
        this.v = str;
    }

    public void o(String str) {
        this.p = str;
    }

    public void p(String str) {
        this.t = str;
    }

    public void q(String str) {
        this.u = str;
    }

    public void r(String str) {
        this.n = str;
    }

    public void s(String str) {
        this.c = str;
    }

    public void t(String str) {
        this.g = str;
    }

    public void v(String str) {
        this.r = str;
    }

    public void w(String str) {
        this.h = str;
    }

    public void x(String str) {
        this.j = str;
    }

    public void y(String str) {
        this.q = str;
    }

    public void z(String str) {
        this.z = str;
    }
}
