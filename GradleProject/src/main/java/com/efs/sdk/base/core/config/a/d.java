package com.efs.sdk.base.core.config.a;

import com.efs.sdk.base.core.util.Log;
import com.igexin.assist.sdk.AssistPushConsts;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import io.dcloud.nineoldandroids.util.ReflectiveProperty;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SimpleDateFormat f1833a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA);

    public static boolean a(String str, b bVar) {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(com.igexin.push.core.b.Y);
            int i = jSONObject.getInt("cver");
            String strOptString = "";
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() > 0) {
                JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("common");
                if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.length() > 0) {
                    Iterator<String> itKeys = jSONObjectOptJSONObject2.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        map.put(next, jSONObjectOptJSONObject2.optString(next, ""));
                    }
                }
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("app_configs");
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObject2 = (JSONObject) jSONArrayOptJSONArray.get(i2);
                        if (jSONObject2 != null && jSONObject2.length() == 2) {
                            JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("conditions");
                            JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray(AssistPushConsts.MSG_TYPE_ACTIONS);
                            if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray3 != null && jSONArrayOptJSONArray3.length() > 0) {
                                a(map, jSONArrayOptJSONArray3);
                            }
                        }
                    }
                }
                strOptString = jSONObjectOptJSONObject.optString(WeiXinPay.PayInfoResult.KEY_SIGN);
            }
            bVar.a(map);
            bVar.f1827a = i;
            bVar.a(strOptString);
            return true;
        } catch (Throwable th) {
            Log.e("efs.config", "parseConfig error, data is ".concat(String.valueOf(str)), th);
            return false;
        }
    }

    public static void a(Map<String, String> map, JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                if (jSONObject != null && jSONObject.length() >= 2) {
                    String strOptString = jSONObject.optString("opt");
                    Object objOpt = jSONObject.opt(ReflectiveProperty.PREFIX_SET);
                    if (strOptString != null && objOpt != null) {
                        String strOptString2 = jSONObject.optString("lt", null);
                        String strOptString3 = jSONObject.optString("net", null);
                        if (strOptString2 != null) {
                            strOptString = strOptString + "_" + strOptString2;
                        }
                        if (strOptString3 != null) {
                            strOptString = strOptString + "_" + strOptString3;
                        }
                        map.put(strOptString, String.valueOf(objOpt));
                    }
                }
            } catch (Throwable th) {
                Log.e("efs.config", "updateConfigCond error", th);
                return;
            }
        }
    }
}
