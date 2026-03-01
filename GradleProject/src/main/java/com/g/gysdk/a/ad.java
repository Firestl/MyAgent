package com.g.gysdk.a;

import android.text.TextUtils;
import com.baidu.speech.utils.analysis.Analysis;
import com.g.gysdk.a.al;
import com.getui.gtc.base.crypt.CryptTools;
import com.getui.gtc.base.http.GtHttpClient;
import com.getui.gtc.base.http.LoggerInterceptor;
import com.getui.gtc.base.http.Request;
import io.dcloud.common.util.Md5Utils;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final GtHttpClient f1949a = new GtHttpClient.Builder().addInterceptor(new LoggerInterceptor(ak.a())).build();

    public interface a {
        boolean a(String str);
    }

    public static abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public JSONObject f1952a;

        public b() throws JSONException, NoSuchAlgorithmException {
            this.f1952a = null;
            this.f1952a = new JSONObject();
            if (!TextUtils.isEmpty(com.g.gysdk.a.d.h)) {
                this.f1952a.put("gyuid", com.g.gysdk.a.d.h);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.f1952a.put("timestamp", String.valueOf(jCurrentTimeMillis));
            this.f1952a.put(WeiXinPay.PayInfoResult.KEY_SIGN, CryptTools.digestToHexString(Md5Utils.ALGORITHM, (com.g.gysdk.a.d.f2000e + com.g.gysdk.a.d.b().f1955a + jCurrentTimeMillis).getBytes()));
        }

        public String toString() {
            return this.f1952a.toString();
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final JSONObject f1953a;
        public final int b;
        public final String c;

        public c(String str) throws JSONException {
            this.c = str;
            JSONObject jSONObject = new JSONObject(str);
            this.f1953a = jSONObject;
            this.b = jSONObject.optInt(Analysis.KEY_RESPONSE_ERROR_CODE, -1);
        }

        public String i() {
            return this.c;
        }

        public int k() {
            return this.b;
        }

        public JSONObject l() {
            return this.f1953a;
        }
    }

    public static abstract class d extends c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final JSONObject f1954a;
        public final int b;
        public final String c;

        public d(String str) throws JSONException {
            super(str);
            if (k() != 0) {
                this.f1954a = null;
                this.b = -1;
                this.c = "";
            } else {
                JSONObject jSONObject = l().getJSONObject("data");
                this.f1954a = jSONObject;
                this.b = jSONObject.optInt("result", -1);
                this.c = this.f1954a.optString("msg", "");
            }
        }

        public int g() {
            return this.b;
        }

        public JSONObject h() {
            return this.f1954a;
        }
    }

    public interface e {
        void a(String str);

        void a(Throwable th);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0094, code lost:
    
        com.g.gysdk.a.ae.b(r3, r6);
        com.g.gysdk.a.ak.a("requestSync success, confirmServer");
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x009c, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r3, java.lang.String r4, com.getui.gtc.base.http.Request.Builder r5, int r6, com.g.gysdk.a.ad.a r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.g.gysdk.a.ad.a(java.lang.String, java.lang.String, com.getui.gtc.base.http.Request$Builder, int, com.g.gysdk.a.ad$a):java.lang.String");
    }

    public static void a(final String str, final String str2, final Request.Builder builder, final int i, final a aVar, final e eVar) {
        al.a(al.b.Work, new Runnable() { // from class: com.g.gysdk.a.ad.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String strA = ad.a(str, str2, builder, i, aVar);
                    if (eVar != null) {
                        eVar.a(strA);
                    }
                } catch (Throwable th) {
                    ak.e("requestAsync error", th);
                    e eVar2 = eVar;
                    if (eVar2 != null) {
                        eVar2.a(th);
                    }
                }
            }
        });
    }

    public static boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("data") || jSONObject.has(WeiXinOAuthService.KEY_ERRCODE) || jSONObject.has(WeiXinOAuthService.KEY_ERRMSG)) {
                return false;
            }
            return !jSONObject.has(Analysis.KEY_RESPONSE_ERROR_CODE);
        } catch (Throwable unused) {
            ak.c("needSwitchServer 数据结构不合法");
            return true;
        }
    }
}
