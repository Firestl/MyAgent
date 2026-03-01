package com.g.gysdk.a;

import com.getui.gtc.base.http.MediaType;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.RequestBody;
import com.getui.gtc.base.http.crypt.PtRASCryptoInterceptor;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ac {
    public static boolean a(a aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("log", aVar.d());
            String strA = ad.a("gy.bs", "/gylogcenter/json/gyps/v1/type2", new Request.Builder().method("POST").body(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject.toString())).cryptInterceptor(new PtRASCryptoInterceptor(d.j, d.i)).tag("business log"), -1, null);
            if ("success".equals(new JSONObject(strA).optString(WeiXinOAuthService.KEY_ERRCODE))) {
                return true;
            }
            ak.e("uploadSync failed:" + strA);
            return false;
        } catch (Throwable th) {
            ak.e("business log error", th);
            return false;
        }
    }
}
