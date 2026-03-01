package com.g.gysdk.a;

import android.text.TextUtils;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.umeng.analytics.pro.bm;
import com.vivo.push.PushClientConstants;
import dc.squareup.okhttp3.HttpUrl;
import java.util.Collection;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ag {

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ag f1957a = new ag();
    }

    public static ag a() {
        return a.f1957a;
    }

    private JSONObject b(boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appName", d.b().b);
        jSONObject.put(PushClientConstants.TAG_PKG_NAME, d.b().f1955a);
        jSONObject.put("verCode", String.valueOf(d.b().d));
        jSONObject.put("verName", d.b().c);
        jSONObject.put(com.heytap.mcssdk.a.a.o, d.c);
        jSONObject.put("signatures", d.b().f1956e);
        jSONObject.put("channel", d.f);
        return jSONObject;
    }

    private JSONObject c(boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String strA = z ? "" : ah.a("dim-2-1-1-1");
        String strA2 = z ? "" : ah.a("dim-2-1-1-3");
        String strA3 = z ? "" : ah.a("dim-2-1-1-4");
        String strA4 = z ? "" : ah.a("dim-2-1-2-1");
        String strA5 = z ? "" : ah.a("dim-2-1-2-3");
        String strA6 = z ? "" : ah.a("dim-2-1-2-4");
        String strA7 = z ? "" : ah.a("dim-2-1-6-1");
        String strA8 = z ? "" : ah.a("dim-2-1-6-3");
        String strA9 = z ? "" : ah.a("dim-2-1-6-4");
        String strA10 = z ? "" : ah.a("dim-2-1-7-1");
        String strA11 = z ? "" : ah.a("dim-2-1-8-1");
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(strA)) {
            hashSet.add(strA);
        }
        if (!TextUtils.isEmpty(strA2)) {
            hashSet.add(strA2);
        }
        if (!TextUtils.isEmpty(strA3)) {
            hashSet.add(strA3);
        }
        JSONArray jSONArray = new JSONArray((Collection) hashSet);
        HashSet hashSet2 = new HashSet();
        if (!TextUtils.isEmpty(strA4)) {
            hashSet2.add(strA4);
        }
        if (!TextUtils.isEmpty(strA5)) {
            hashSet2.add(strA5);
        }
        if (!TextUtils.isEmpty(strA6)) {
            hashSet2.add(strA6);
        }
        JSONArray jSONArray2 = new JSONArray((Collection) hashSet2);
        HashSet hashSet3 = new HashSet();
        if (!TextUtils.isEmpty(strA7)) {
            hashSet3.add(strA7);
        }
        if (!TextUtils.isEmpty(strA8)) {
            hashSet3.add(strA8);
        }
        if (!TextUtils.isEmpty(strA9)) {
            hashSet3.add(strA9);
        }
        JSONArray jSONArray3 = new JSONArray((Collection) hashSet3);
        jSONObject.put("androidId", strA10);
        jSONObject.put("masterImei", strA);
        jSONObject.put(com.umeng.commonsdk.statistics.idtracking.g.f5442a, jSONArray);
        jSONObject.put("imsi", jSONArray2);
        jSONObject.put(bm.aa, jSONArray3);
        jSONObject.put("masterImsi", strA4);
        jSONObject.put("masterIccid", strA7);
        jSONObject.put("androidAdId", strA11);
        return jSONObject;
    }

    public JSONObject a(boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appid", d.f2000e);
        jSONObject.put("cid", d.g);
        jSONObject.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, d.d);
        JSONObject jSONObjectB = b(z);
        if (jSONObjectB != null) {
            jSONObject.put("appInfo", jSONObjectB);
        }
        JSONObject jSONObjectC = c(z);
        if (jSONObjectC != null) {
            jSONObject.put(ConstantHelper.LOG_DE, jSONObjectC);
        }
        jSONObject.put(bm.ac, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        return jSONObject;
    }
}
