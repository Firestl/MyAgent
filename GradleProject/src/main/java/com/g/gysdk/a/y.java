package com.g.gysdk.a;

import com.g.gysdk.a.ad;
import com.getui.gtc.base.http.MediaType;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.RequestBody;
import com.getui.gtc.base.http.crypt.PtRASCryptoInterceptor;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class y {

    public static class a extends ad.b {
        public a(boolean z) throws JSONException, NoSuchAlgorithmException {
            this.f1952a.put(com.umeng.commonsdk.statistics.idtracking.i.d, z ? "" : ah.a("dim-2-1-5-1"));
            this.f1952a.put("baseInfo", ag.a().a(z));
        }
    }

    public static class b extends ad.d {
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f2045e;
        public String f;
        public String g;

        public b(String str) throws JSONException {
            super(str);
            if (g() != 20000) {
                return;
            }
            JSONObject jSONObject = h().getJSONObject("data");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ct");
            if (jSONObjectOptJSONObject != null) {
                this.b = jSONObjectOptJSONObject.optString("id");
                this.c = jSONObjectOptJSONObject.optString("key");
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("cu");
            if (jSONObjectOptJSONObject2 != null) {
                this.f = jSONObjectOptJSONObject2.optString("id");
                this.g = jSONObjectOptJSONObject2.optString("key");
            }
            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("cm");
            if (jSONObjectOptJSONObject3 != null) {
                this.d = jSONObjectOptJSONObject3.optString("id");
                this.f2045e = jSONObjectOptJSONObject3.optString("key");
            }
        }

        public String a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }

        public String c() {
            return this.f;
        }

        public String d() {
            return this.g;
        }

        public String e() {
            return this.d;
        }

        public String f() {
            return this.f2045e;
        }

        @Override // com.g.gysdk.a.ad.d
        public /* bridge */ /* synthetic */ int g() {
            return super.g();
        }

        @Override // com.g.gysdk.a.ad.d
        public /* bridge */ /* synthetic */ JSONObject h() {
            return super.h();
        }

        @Override // com.g.gysdk.a.ad.c
        public /* bridge */ /* synthetic */ String i() {
            return super.i();
        }
    }

    public static b a() {
        try {
            return new b(ad.a("gy.as", "/v1/gy/ct_login/get_menukey", new Request.Builder().method("POST").body(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new a(true).toString())).cryptInterceptor(new PtRASCryptoInterceptor(d.j, d.i)).tag("do get_menukey"), -1, null));
        } catch (Throwable th) {
            ak.e("get_menukey error", th);
            return null;
        }
    }
}
