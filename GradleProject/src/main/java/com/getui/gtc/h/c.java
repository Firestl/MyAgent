package com.getui.gtc.h;

import android.os.Build;
import android.text.TextUtils;
import com.baidu.speech.utils.analysis.Analysis;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.http.MediaType;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.RequestBody;
import com.getui.gtc.base.http.crypt.PtRASCryptoInterceptor;
import com.getui.gtc.base.util.ScheduleQueue;
import com.getui.gtc.dim.Caller;
import com.getui.gtc.dim.DimManager;
import com.getui.gtc.dim.DimRequest;
import com.getui.gtc.i.d.b;
import com.getui.gtc.server.ServerManager;
import com.umeng.commonsdk.statistics.idtracking.i;
import com.vivo.push.PushClientConstants;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import java.io.IOException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    public interface a {
        void a(String str);
    }

    public static String a(String str) throws Exception {
        while (true) {
            String server = ServerManager.getServer("gtc.as");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("version", "1.0");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject.put("data", jSONObject2);
                if (!TextUtils.isEmpty(str)) {
                    jSONObject2.put("gtcid", str);
                }
                com.getui.gtc.i.d.b unused = b.C0046b.f2252a;
                String str2 = (String) DimManager.getInstance().get(new DimRequest.Builder().key("dim-2-1-5-1").caller(Caller.UNKNOWN).useExpiredCacheForReserve(true).build());
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject2.put(i.d, str2);
                }
                if ("HONOR".equalsIgnoreCase(Build.MANUFACTURER)) {
                    com.getui.gtc.i.d.b unused2 = b.C0046b.f2252a;
                    String str3 = (String) DimManager.getInstance().get(new DimRequest.Builder().key("dim-2-1-5-2").caller(Caller.UNKNOWN).build());
                    if (!TextUtils.isEmpty(str3)) {
                        jSONObject2.put("oaid2", str3);
                    }
                }
                jSONObject2.put("gtAppid", com.getui.gtc.c.b.f2140a);
                jSONObject2.put(PushClientConstants.TAG_PKG_NAME, GtcProvider.context().getPackageName());
                jSONObject2.put("os", "android");
                com.getui.gtc.i.d.b unused3 = b.C0046b.f2252a;
                String str4 = (String) DimManager.getInstance().get(new DimRequest.Builder().key("dim-2-1-8-1").caller(Caller.UNKNOWN).useExpiredCacheForReserve(true).build());
                if (!TextUtils.isEmpty(str4)) {
                    jSONObject2.put("androidAid", str4);
                }
                com.getui.gtc.i.d.b unused4 = b.C0046b.f2252a;
                String str5 = (String) DimManager.getInstance().get(new DimRequest.Builder().key("dim-2-1-10-1").caller(Caller.UNKNOWN).useExpiredCacheForReserve(true).build());
                if (!TextUtils.isEmpty(str5)) {
                    jSONObject2.put("phoneModel", str5);
                }
                JSONObject jSONObject3 = new JSONObject(d.f2238a.newCall(new Request.Builder().url(String.format("%s/cidserver/getcid", server)).method("POST").body(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject.toString())).cryptInterceptor(new PtRASCryptoInterceptor(com.getui.gtc.c.b.i, com.getui.gtc.c.b.h)).tag("register gtcid").build()).execute().body().string());
                int i = jSONObject3.getInt(Analysis.KEY_RESPONSE_ERROR_CODE);
                String string = jSONObject3.getString(WeiXinOAuthService.KEY_ERRMSG);
                if (i == 0) {
                    return jSONObject3.getJSONObject("data").getString("gtcid");
                }
                throw new Exception(string);
            } catch (Throwable th) {
                if (!(th instanceof IOException) || !ServerManager.switchServer("gtc.as", server)) {
                    throw th;
                }
                com.getui.gtc.i.c.a.c("register gtcId failed with server: " + server + ", try again with: " + ServerManager.getServer("gtc.as"));
            }
        }
        throw th;
    }

    public static void a(final String str, final a aVar) {
        ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.h.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    String strA = c.a(str);
                    if (aVar != null) {
                        aVar.a(strA);
                    }
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.c("register gtcId failed: " + th.getMessage());
                }
            }
        });
    }
}
