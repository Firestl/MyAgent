package supwisdom;

import android.content.Context;
import com.loopj.android.http.RequestParams;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class lp extends ip {
    @Override // supwisdom.ip
    public String a(pp ppVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // supwisdom.ip
    public Map<String, String> a(boolean z, String str) {
        HashMap map = new HashMap();
        map.put("msp-gzip", String.valueOf(z));
        map.put("content-type", RequestParams.APPLICATION_OCTET_STREAM);
        map.put("des-mode", "CBC");
        return map;
    }

    @Override // supwisdom.ip
    public JSONObject a() throws JSONException {
        return null;
    }

    @Override // supwisdom.ip
    public String c() throws JSONException {
        HashMap<String, String> map = new HashMap<>();
        map.put("api_name", "/sdk/log");
        map.put("api_version", "1.0.0");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("log_v", "1.0");
        return a(map, map2);
    }

    @Override // supwisdom.ip
    public fp a(pp ppVar, Context context, String str) throws Throwable {
        return a(ppVar, context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}
