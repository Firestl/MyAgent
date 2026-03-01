package com.loc;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClientOption;
import com.taobao.weex.el.parse.Operators;
import com.tencent.open.SocialConstants;
import dc.squareup.okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: Parser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public StringBuilder f3777a = new StringBuilder();
    public AMapLocationClientOption b = new AMapLocationClientOption();

    private void a(ds dsVar, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb2.append(str);
            sb2.append(Operators.SPACE_STR);
        }
        if (!TextUtils.isEmpty(str2) && (this.b.getGeoLanguage() != AMapLocationClientOption.GeoLanguage.EN ? !str.contains("市") || !str.equals(str2) : !str2.equals(str))) {
            sb2.append(str2);
            sb2.append(Operators.SPACE_STR);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb2.append(str3);
            sb2.append(Operators.SPACE_STR);
        }
        if (!TextUtils.isEmpty(str4)) {
            sb2.append(str4);
            sb2.append(Operators.SPACE_STR);
        }
        if (!TextUtils.isEmpty(str5)) {
            sb2.append(str5);
            sb2.append(Operators.SPACE_STR);
        }
        if (!TextUtils.isEmpty(str6)) {
            if (TextUtils.isEmpty(str7) || this.b.getGeoLanguage() == AMapLocationClientOption.GeoLanguage.EN) {
                sb2.append("Near " + str6);
                sb = new StringBuilder("Near ");
                sb.append(str6);
            } else {
                sb2.append("靠近");
                sb2.append(str6);
                sb2.append(Operators.SPACE_STR);
                sb = new StringBuilder("在");
                sb.append(str6);
                sb.append("附近");
            }
            dsVar.setDescription(sb.toString());
        }
        Bundle bundle = new Bundle();
        bundle.putString("citycode", dsVar.getCityCode());
        bundle.putString(SocialConstants.PARAM_APP_DESC, sb2.toString());
        bundle.putString("adcode", dsVar.getAdCode());
        dsVar.setExtras(bundle);
        dsVar.g(sb2.toString());
        String adCode = dsVar.getAdCode();
        dsVar.setAddress((adCode == null || adCode.trim().length() <= 0 || this.b.getGeoLanguage() == AMapLocationClientOption.GeoLanguage.EN) ? sb2.toString() : sb2.toString().replace(Operators.SPACE_STR, ""));
    }

    public static String b(String str) {
        return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI.equals(str) ? "" : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.ds a(com.loc.ds r22, byte[] r23, com.loc.dm r24) {
        /*
            Method dump skipped, instruction units count: 710
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eg.a(com.loc.ds, byte[], com.loc.dm):com.loc.ds");
    }

    public final ds a(String str) {
        String str2;
        try {
            ds dsVar = new ds("");
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("regeocode");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("addressComponent");
            dsVar.setCountry(b(jSONObjectOptJSONObject2.optString(com.umeng.analytics.pro.bm.O)));
            String strB = b(jSONObjectOptJSONObject2.optString("province"));
            dsVar.setProvince(strB);
            String strB2 = b(jSONObjectOptJSONObject2.optString("citycode"));
            dsVar.setCityCode(strB2);
            String strOptString = jSONObjectOptJSONObject2.optString("city");
            if (!strB2.endsWith("010") && !strB2.endsWith("021") && !strB2.endsWith("022") && !strB2.endsWith("023")) {
                strOptString = b(strOptString);
                dsVar.setCity(strOptString);
            } else if (strB != null && strB.length() > 0) {
                dsVar.setCity(strB);
                strOptString = strB;
            }
            if (TextUtils.isEmpty(strOptString)) {
                dsVar.setCity(strB);
                strOptString = strB;
            }
            String strB3 = b(jSONObjectOptJSONObject2.optString("district"));
            dsVar.setDistrict(strB3);
            String strB4 = b(jSONObjectOptJSONObject2.optString("adcode"));
            dsVar.setAdCode(strB4);
            JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject("streetNumber");
            String strB5 = b(jSONObjectOptJSONObject3.optString("street"));
            dsVar.setStreet(strB5);
            dsVar.setRoad(strB5);
            String strB6 = b(jSONObjectOptJSONObject3.optString("number"));
            dsVar.setNumber(strB6);
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("pois");
            if (jSONArrayOptJSONArray.length() > 0) {
                String strB7 = b(jSONArrayOptJSONArray.getJSONObject(0).optString("name"));
                dsVar.setPoiName(strB7);
                str2 = strB7;
            } else {
                str2 = null;
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("aois");
            if (jSONArrayOptJSONArray2.length() > 0) {
                dsVar.setAoiName(b(jSONArrayOptJSONArray2.getJSONObject(0).optString("name")));
            }
            a(dsVar, strB, strOptString, strB3, strB5, strB6, str2, strB4);
            return dsVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final ds a(String str, Context context, aw awVar, dm dmVar) {
        ds dsVar = new ds("");
        dsVar.setErrorCode(7);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("#SHA1AndPackage#");
            stringBuffer.append(k.e(context));
            String str2 = awVar.b.get("gsid").get(0);
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append("#gsid#");
                stringBuffer.append(str2);
            }
            String str3 = awVar.c;
            if (!TextUtils.isEmpty(str3)) {
                stringBuffer.append("#csid#" + str3);
            }
        } catch (Throwable unused) {
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status") || !jSONObject.has(com.umeng.commonsdk.internal.utils.f.f5404a)) {
                dmVar.f("#0702");
                StringBuilder sb = this.f3777a;
                sb.append("json is error:");
                sb.append(str);
                sb.append(stringBuffer);
                sb.append("#0702");
            }
            String string = jSONObject.getString("status");
            String string2 = jSONObject.getString(com.umeng.commonsdk.internal.utils.f.f5404a);
            String string3 = jSONObject.getString("infocode");
            if ("0".equals(string)) {
                dmVar.f("#0701");
                StringBuilder sb2 = this.f3777a;
                sb2.append("auth fail:");
                sb2.append(string2);
                sb2.append(stringBuffer);
                sb2.append("#0701");
                en.a(awVar.d, string3, string2);
            }
        } catch (Throwable th) {
            dmVar.f("#0703");
            StringBuilder sb3 = this.f3777a;
            sb3.append("json exception error:");
            sb3.append(th.getMessage());
            sb3.append(stringBuffer);
            sb3.append("#0703");
            ej.a(th, "parser", "paseAuthFailurJson");
        }
        dsVar.setLocationDetail(this.f3777a.toString());
        if (this.f3777a.length() > 0) {
            StringBuilder sb4 = this.f3777a;
            sb4.delete(0, sb4.length());
        }
        return dsVar;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption == null) {
            this.b = new AMapLocationClientOption();
        } else {
            this.b = aMapLocationClientOption;
        }
    }
}
