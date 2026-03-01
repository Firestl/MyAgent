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
public class ab {

    public static class a extends ad.b {
        public a(String str, String str2, int i, String str3) throws JSONException, NoSuchAlgorithmException {
            this.f1952a.put("baseInfo", ag.a().a(false));
            this.f1952a.put("phone", str);
            this.f1952a.put("token", str2);
            this.f1952a.put("operatorType", i);
            this.f1952a.put("newOperatorType", i);
            this.f1952a.put("processId", str3);
        }
    }

    public static class b extends ad.d {
        public b(String str) throws JSONException {
            super(str);
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

    public static b a(String str, String str2, int i, String str3) {
        try {
            return new b(ad.a("gy.as", "/v1/gy/ct_login/token_validate", new Request.Builder().method("POST").body(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new a(str, str2, i, str3).toString())).cryptInterceptor(new PtRASCryptoInterceptor(d.j, d.i)).tag("pn verify"), -1, null));
        } catch (Throwable th) {
            ak.e("pn verify error", th);
            return null;
        }
    }
}
