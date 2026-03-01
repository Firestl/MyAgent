package com.g.gysdk.a;

import android.text.TextUtils;
import com.g.gysdk.a.ad;
import com.getui.gtc.base.http.MediaType;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.RequestBody;
import com.getui.gtc.base.http.crypt.PtRASCryptoInterceptor;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class z {

    public static class a extends ad.b {
        public a(boolean z) throws JSONException, NoSuchAlgorithmException {
            if (TextUtils.isEmpty(d.h)) {
                this.f1952a.put("registerId", z.b());
            }
            this.f1952a.put(com.umeng.commonsdk.statistics.idtracking.i.d, z ? "" : ah.a("dim-2-1-5-1"));
            this.f1952a.put("baseInfo", ag.a().a(z));
        }
    }

    public static class b extends ad.d {
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f2046e;
        public String f;
        public String g;
        public String h;
        public String i;

        public b(String str) throws JSONException {
            super(str);
            int iG = g();
            if (iG == 1000 || iG == 29001 || iG == 29002) {
                JSONObject jSONObject = h().getJSONObject("data");
                this.b = jSONObject.optString("gyuid", "");
                this.c = jSONObject.optString("cid", "");
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ct");
                if (jSONObjectOptJSONObject != null) {
                    this.d = jSONObjectOptJSONObject.optString("id");
                    this.f2046e = jSONObjectOptJSONObject.optString("key");
                }
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("cu");
                if (jSONObjectOptJSONObject2 != null) {
                    this.h = jSONObjectOptJSONObject2.optString("id");
                    this.i = jSONObjectOptJSONObject2.optString("key");
                }
                JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("cm");
                if (jSONObjectOptJSONObject3 != null) {
                    this.f = jSONObjectOptJSONObject3.optString("id");
                    this.g = jSONObjectOptJSONObject3.optString("key");
                }
            }
        }

        public String a() {
            return this.b;
        }

        public String b() {
            return this.d;
        }

        public String c() {
            return this.f2046e;
        }

        public String d() {
            return this.h;
        }

        public String e() {
            return this.i;
        }

        public String f() {
            return this.f;
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

        public String j() {
            return this.g;
        }
    }

    public static b a(boolean z) {
        try {
            return new b(ad.a("gy.as", "/v2/gy/register", new Request.Builder().method("POST").body(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new a(z).toString())).cryptInterceptor(new PtRASCryptoInterceptor(d.j, d.i)).tag("do register isLite=" + z), -1, null));
        } catch (Throwable th) {
            ak.e("register error", th);
            return null;
        }
    }

    public static String b() {
        return "A-" + ah.a("dim-2-1-1-1") + "-" + d.g + "-" + System.currentTimeMillis();
    }
}
