package supwisdom;

import android.text.TextUtils;
import com.synjones.mobilegroup.libofflinecodesdk.beans.BarcodeParBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.CodeBarPayInfoBean;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class rp1 {
    public static String a(Map<String, BarcodeParBean> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Set<Map.Entry<String, BarcodeParBean>> setEntrySet = map.entrySet();
        if (!setEntrySet.isEmpty()) {
            for (Map.Entry<String, BarcodeParBean> entry : setEntrySet) {
                jSONObject.put(entry.getKey(), entry.getValue().toJsonObject());
            }
        }
        return jSONObject.toString();
    }

    public static String b(Map<String, CodeBarPayInfoBean.DataBean> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, CodeBarPayInfoBean.DataBean> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue().toJsonObject());
        }
        return jSONObject.toString();
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
