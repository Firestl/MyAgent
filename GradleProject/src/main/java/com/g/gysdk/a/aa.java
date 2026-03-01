package com.g.gysdk.a;

import android.text.TextUtils;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.g.gysdk.a.ad;
import com.g.gysdk.a.d;
import com.getui.gtc.base.http.MediaType;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.RequestBody;
import com.getui.gtc.base.http.crypt.PtRASCryptoInterceptor;
import io.dcloud.common.constant.AbsoluteConst;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class aa {

    public static class a extends ad.b {
        public a(String str, String str2, long j, int i, String str3) throws JSONException, NoSuchAlgorithmException {
            this.f1952a.put(com.umeng.commonsdk.statistics.idtracking.i.d, ah.a("dim-2-1-5-1"));
            this.f1952a.put("baseInfo", ag.a().a(false));
            this.f1952a.put("operatorType", i);
            this.f1952a.put("newOperatorType", i);
            this.f1952a.put("lineImsi", "");
            this.f1952a.put("operatorVersion", DefaultDiskStorage.DEFAULT_DISK_STORAGE_VERSION_PREFIX);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("processId", str);
            jSONObject.put(AbsoluteConst.JSON_SHARE_ACCESSTOKEN, str2);
            jSONObject.put("atExpiresIn", j);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("authCode", str3);
            }
            this.f1952a.put("ecountInfo", jSONObject);
        }
    }

    public static void a(String str, String str2, long j, int i, String str3) {
        if (d.a(d.a.NOT_TOKEN_UPLOAD)) {
            ak.e("demo not token_upload");
            return;
        }
        try {
            ad.a("gy.as", "/v1/gy/ct_login/token_upload", new Request.Builder().method("POST").body(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new a(str, str2, j, i, str3).toString())).cryptInterceptor(new PtRASCryptoInterceptor(d.j, d.i)).tag("upload login token"), -1, null, new ad.e() { // from class: com.g.gysdk.a.aa.1
                @Override // com.g.gysdk.a.ad.e
                public void a(String str4) {
                }

                @Override // com.g.gysdk.a.ad.e
                public void a(Throwable th) {
                }
            });
        } catch (Throwable th) {
            ak.e("upload login token error", th);
        }
    }
}
