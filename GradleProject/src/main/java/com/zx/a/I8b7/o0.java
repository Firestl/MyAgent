package com.zx.a.I8b7;

import android.text.TextUtils;
import com.umeng.analytics.pro.bm;
import com.zx.sdk.api.ZXID;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o0 {
    public ZXID a(String str, String str2) {
        String string = null;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        ZXID zxid = new ZXID();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            JSONObject jSONObject2 = new JSONObject(jSONObject.getString("ext"));
            JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("aids").optJSONObject(str);
            zxid.setAids(jSONObjectOptJSONObject == null ? "" : jSONObjectOptJSONObject.toString());
            JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("tags");
            if (jSONArrayOptJSONArray != null) {
                string = jSONArrayOptJSONArray.toString();
            }
            zxid.setTags(string);
            String strOptString = jSONObject.optString(bm.al);
            zxid.setValue(strOptString);
            String[] strArrSplit = strOptString.split("-");
            zxid.setVersion(strArrSplit[0]);
            zxid.setExpiredTime(Long.parseLong(strArrSplit[1]) * 1000);
            String strOptString2 = jSONObject2.optString("openid");
            if (!TextUtils.isEmpty(strOptString2) && !"OPENID_CLOSED".equals(strOptString2)) {
                zxid.setOpenid(strOptString2);
            }
        } catch (Exception e2) {
            y1.a(e2);
        }
        return zxid;
    }

    public abstract void a(String str);
}
