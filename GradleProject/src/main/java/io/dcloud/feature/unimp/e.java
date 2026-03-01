package io.dcloud.feature.unimp;

import android.app.Activity;
import android.text.TextUtils;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JSONObject f6697a;
    public static JSONObject b;
    public static String c;
    public static String d;

    public static JSONObject a(Activity activity, String str) {
        JSONObject jSONObject = new JSONObject();
        String str2 = BaseInfo.sDefaultBootApp;
        String str3 = str2 + "/" + BaseInfo.APP_WWW_FS_DIR + BaseInfo.sConfigXML;
        c = BaseInfo.sCacheFsAppsPath + str2 + "/";
        d = BaseInfo.sBaseResAppsPath + str2 + "/";
        String str4 = BaseInfo.sBaseResAppsPath + str3;
        String str5 = BaseInfo.sCacheFsAppsPath + str3;
        int i = 1;
        f6697a = PdrUtil.getConfigData(activity, str2, str4, true);
        b = PdrUtil.getConfigData(activity, str2, str5, false);
        JSONObject jSONObject2 = f6697a;
        if (jSONObject2 != null) {
            String strOptString = jSONObject2.optString("id");
            if (TextUtils.isEmpty(strOptString) || !strOptString.equals(str)) {
                i = -1003;
            }
        }
        int iA = a(f6697a);
        int iA2 = a(b);
        if (iA == -1001 && iA2 == -1001) {
            i = -1001;
        } else if (iA == -1002 || iA2 == -1002) {
            i = -1002;
        }
        try {
            jSONObject.put("code", i);
            jSONObject.put("resCode", iA);
            jSONObject.put("fsCode", iA2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static int a(Activity activity, String str, JSONObject jSONObject) {
        int iOptInt = jSONObject.optInt("resCode");
        int iOptInt2 = jSONObject.optInt("fsCode");
        if (jSONObject.optInt("code") == 1) {
            if (iOptInt2 < 0 && iOptInt > 0) {
                DHFile.copyDir(d, c);
                try {
                    return PdrUtil.getConfigOrientation(f6697a);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (iOptInt > 0 && iOptInt2 > 0 && iOptInt > iOptInt2) {
                DHFile.delete(c);
                DHFile.copyDir(d, c);
                try {
                    return PdrUtil.getConfigOrientation(f6697a);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                try {
                    return PdrUtil.getConfigOrientation(b);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
        return 2;
    }

    public static int a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                return jSONObject.getJSONObject("version").getInt("code");
            } catch (Exception unused) {
            }
        }
        return -1001;
    }
}
